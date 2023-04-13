#include <stdio.h>
#include <unistd.h>
int main(void) {
  // ok: exec
  void* system_wide = system_path(ETC_GITATTRIBUTES);
  printf("Main program started\n");
  char* argv[] = { "jim", "jams", NULL };
  char* envp[] = { "some", "environment", NULL };
  // ruleid: exec
  if (execve("./sub", argv, envp) == -1)
    perror("Could not execve");
  // ruleid: exec
  execlp(path, "emacsclient", "-e", man_page.buf, (char *)NULL);
  // ruleid: exec
  execl(SHELL_PATH, SHELL_PATH, "-c", shell_cmd.buf, (char *)NULL);
  // ruleid: exec
  execv(user_argv[0], (char *const *) user_argv);
  // ruleid: exec
  return system("echo 3 | sudo tee /proc/sys/vm/drop_caches");
  return 1;
}
