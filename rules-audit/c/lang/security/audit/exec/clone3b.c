#define _GNU_SOURCE
#include <err.h>
#include <errno.h>
#include <fcntl.h>
#include <linux/sched.h>
#include <linux/types.h>
#include <sched.h>
#include <signal.h>
#include <stdint.h>
#include <stdio.h>
#include <stdlib.h>
#include <sys/mount.h>
#include <sys/socket.h>
#include <sys/stat.h>
#include <sys/syscall.h>
#include <sys/sysmacros.h>
#include <sys/types.h>
#include <sys/un.h>
#include <sys/wait.h>
#include <unistd.h>

#ifndef CLONE_PIDFD
#define CLONE_PIDFD 0x00001000
#endif

#ifndef __NR_clone3
#define __NR_clone3 -1
#endif

static pid_t sys_clone3(struct clone_args *args)
{
    // ruleid: exec
    return syscall(__NR_clone3, args, sizeof(struct clone_args));
}

static int wait_for_pid(pid_t pid)
{
    int status, ret;

again:
    ret = waitpid(pid, &status, 0);
    if (ret == -1) {
        if (errno == EINTR)
            goto again;

        return -1;
    }

    if (ret != pid)
        goto again;

    if (!WIFEXITED(status) || WEXITSTATUS(status) != 0)
        return -1;

    return 0;
}

#define ptr_to_u64(ptr) ((__u64)((uintptr_t)(ptr)))

int main(int argc, char *argv[])
{
    int pidfd = -1;
    pid_t parent_tid = -1, pid = -1;
    struct clone_args args = {0};

    args.parent_tid = ptr_to_u64(&parent_tid); /* CLONE_PARENT_SETTID */
    args.pidfd = ptr_to_u64(&pidfd); /* CLONE_PIDFD */
    args.flags = CLONE_PIDFD | CLONE_PARENT_SETTID;
    args.exit_signal = SIGCHLD;

    // ruleid: exec
    pid = sys_clone3(&args);
    if (pid < 0) {
        fprintf(stderr, "%s - Failed to create new process\n", strerror(errno));
        exit(EXIT_FAILURE);
    }

    if (pid == 0) {
        printf("Child process with pid %d\n", getpid());
        exit(EXIT_SUCCESS);
    }

    printf("Parent process received child's pid %d as return value\n", pid);
    printf("Parent process received child's pidfd %d\n", *(int *)args.pidfd);
    printf("Parent process received child's pid %d as return argument\n",
           *(pid_t *)args.parent_tid);

    if (0) {
        if (waitid(P_ALL, pid, NULL, 0) == 0) {
            fprintf(stderr, "Managed to wait on CLONE_NO_WAITALL process with waitid(P_ALL)\n");
            exit(EXIT_FAILURE);
        }
        printf("Child process %d requested CLONE_NO_WAITALL\n", pid);
    } else {
        printf("Child process %d did not request CLONE_NO_WAITALL\n", pid);
    }

    if (wait_for_pid(pid))
        exit(EXIT_FAILURE);

    if (pid != *(pid_t *)args.parent_tid)
        exit(EXIT_FAILURE);

    close(pidfd);

    return 0;
}
