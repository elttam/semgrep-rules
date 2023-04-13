#define _XOPEN_SOURCE_EXTENDED 1
#include <unistd.h>
#include <spawn.h>
#include <stdio.h>
#include <errno.h>
#include <sys/types.h>

/* This program uses spawn instead of fork/exec to create a child
 * process and uses unnamed pipes to allow the parent and child to
 * exchange communication.
 */


void main(int argc, char *argv[])  {
    pid_t child;
    int fd_count, fd_map[10];
    struct inheritance inherit;
    const char *c_argv[10], *c_envp[10];
    char buf[256];
    int nbytes;

    int c_stdin[2], c_stdout[2], c_stderr[2];  /* Pipes for child
        * communication */

    /* Create pipes to communicate with child via stdin/stdout/stderr */
    if(pipe(c_stdin)  ||
       pipe(c_stdout) ||
       pipe(c_stderr) ) {
        perror("Bad pipe");
                exit(-1);
                }

    /* Set up file descriptor map for child process */
    fd_map[0]=dup(c_stdin[0]);  /* child stdin is read end of pipe */
    fd_map[1]=dup(c_stdout[1]); /* child stdout is write end of pipe */
    fd_map[2]=dup(c_stderr[1]); /* child stderr is write end of pipe */
    fd_count=3;

    /* Close unused end of pipes for the parent */
        close(c_stdin[0]); close(c_stdout[1]); close(c_stderr[1]);

    /* Build the argument structure for child arguments.
        *  [0] is the program name */
    c_argv[0]="spawnc";
    c_argv[1]="arg1"; c_argv[2]="arg2"; c_argv[3]=NULL;

    /* Build the environment structure which defines the child's
        * environment variables */
    c_envp[0]="TEST_ENV=YES"; c_envp[1]="BPX_SHAREAS=NO"; c_envp[2]=NULL;

    /* Spawn the child process */
        // ruleid: exec
    child=spawnp("spawnc", fd_count, fd_map, &inherit, c_argv, c_envp);
        // ruleid: exec
    child=spawn("/usr/bin/spawnc", fd_count, fd_map, &inherit, c_argv, c_envp);
    if(child==-1) {
        perror("Error on spawn");
        exit(-1);
        }
    else printf("Spawned %i\n", child);

    /* Test interaction with the child process */
    printf("parent: Asking child, \"what are you doing?\\n\"\n");
    strcpy(buf, "child from parent: what are you doing?\n");
    if(write(c_stdin[1], buf, sizeof(buf))==-1)  {
        perror("write stdout");
        exit(-1);
        }

    memset(buf, 0, 255);  /* Just zeroing out the buffer */
    printf("parent: reading from child now\n");
    if((nbytes=read(c_stdout[0], buf, 255))==-1) {
        perror("read error:");
        exit(-1);
        }
    printf("parent: child says, \"%s\"\n", buf);

    /* Cleanup pipes before exiting */
    close(c_stdin[1]); close(c_stdout[0]); close(c_stderr[0]);

     exit(0);
}
