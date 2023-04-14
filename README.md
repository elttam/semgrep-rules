# elttam's semgrep-rules
[![powered by semgrep](https://img.shields.io/badge/powered%20by-semgrep-1B2F3D?labelColor=lightgrey&link=https://semgrep.dev/&style=flat-square&logo=data%3Aimage%2Fpng%3Bbase64%2CiVBORw0KGgoAAAANSUhEUgAAAA0AAAAOCAYAAAD0f5bSAAAABmJLR0QA/gD+AP+cH+QUAAAACXBIWXMAAA3XAAAN1wFCKJt4AAAAB3RJTUUH5AYMEy0l8dkqrQAAAvFJREFUKBUB5gIZ/QEAAP8BAAAAAAMG6AD9+hn/GzA//wD//wAAAAD+AAAAAgABAQDl0MEBAwbmAf36GQAAAAAAAQEC9QH//gv/Gi1GFQEC+OoAAAAAAAAAAAABAQAA//8AAAAAAAAAAAD//ggX5tO66gID9AEBFSRxAgYLzRQAAADpAAAAAP7+/gDl0cMPAAAA+wAAAPkbLz39AgICAAAAAAAAAAAs+vU12AEbLz4bAAAA5P8AAAAA//4A5NDDEwEBAO///wABAQEAAP//ABwcMD7hAQEBAAAAAAAAAAAaAgAAAOAAAAAAAQEBAOXRwxUAAADw//8AAgAAAAD//wAAAAAA5OXRwhcAAQEAAAAAAAAAAOICAAAABP3+/gDjzsAT//8A7gAAAAEAAAD+AAAA/wAAAAAAAAAA//8A7ePOwA/+/v4AAAAABAIAAAAAAAAAAAAAAO8AAAABAAAAAAAAAAIAAAABAAAAAAAAAAgAAAD/AAAA8wAAAAAAAAAAAgAAAAAAAAAAAAAAAAAAAA8AAAAEAAAA/gAAAP8AAAADAAAA/gAAAP8AAAAAAAAAAAAAAAACAAAAAAAAAAAAAAAAAAAA7wAAAPsAAAARAAAABAAAAP4AAAAAAAAAAgAAABYAAAAAAAAAAAIAAAD8AwICAB0yQP78/v4GAAAA/wAAAPAAAAD9AAAA/wAAAPr9//8aHTJA6AICAgAAAAD8AgAAADIAAAAAAP//AB4wPvgAAAARAQEA/gEBAP4BAQABAAAAGB0vPeIA//8AAAAAAAAAABAC+vUz1QAAAA8AAAAAAwMDABwwPu3//wAe//8AAv//ABAcMD7lAwMDAAAAAAAAAAAG+vU0+QEBAvUB//4L/xotRhUBAvjqAAAAAAAAAAAAAQEAAP//AAAAAAAAAAAA//4IF+bTuuoCA/QBAQAA/wEAAAAAAwboAP36Gf8bMD//AP//AAAAAP4AAAACAAEBAOXQwQEDBuYB/foZAAAAAAD4I6qbK3+1zQAAAABJRU5ErkJggg==)](https://semgrep.dev/)
[![r2c community slack](https://img.shields.io/badge/slack-join-green?style=flat-square)](https://r2c.dev/slack)

Welcome to [elttam][]'s public semgrep rules repository.

![semgrep rules](./docs/semgrep-rules.png)

We regularly develop rules during our code-assisted security audits and software security research. We will be frequently adding new rules and improving what's here for the semgrep community. We hope product security engineers and code auditors who use semgrep find these useful!

If you find any bugs, please raise a GitHub issue.


## Getting started

This guide assumes you are familiar with [Semgrep](https://semgrep.dev/docs) and have it already installed.

This repo has split the rules into two broard categories:

  1. `rules/` -- Rules useful for developers or AppSec teams, these are generally vulnerabilities.
  2. `rules-audit/` -- Rules useful for source code auditing, identifying intersting behaviour, and enumerating entrypoints, to augment manual source code review.

Test out this repo with the following command:

```bash
git clone https://github.com/elttam/semgrep-rules.git
```

To test out the rules, run:

```bash
semgrep --config semgrep-rules/rules semgrep-rules/rules/
```

To test out the audit focused rules, run:

```bash
semgrep --config semgrep-rules/rules-audit semgrep-rules/rules-audit/
```

## Rules

These rules are focused on identifying vulnerabiltiies or other weaknesses.


Rule Path                                                   | Language | Technology     | Description
----------------------------------------------------------- | -------- | -------------- | -------------
generic/jsp-likely-xss                                      | Generic  | JSP            | Detect XSS with unsafe rendering in template
go/lang/security/audit/executable-symlink                   | Go       |                | Potential symlink takeover with os.Executable
go/lang/security/audit/sprintf-plain-string                 | Go       |                | Sprintf unescaped control characters
go/lang/security/audit/uintptr-nonatomic                    | Go       |                | Non-atomic use of converted uintptr
java/lang/security/audit/crypto/gcm-static-iv               | Java     |                | Find GCM using same values for key and IV
java/lang/security/audit/crypto/randomstringutils           | Java     | Apache Commons | Find RandomStringUtils using default java.util.Random, which is not suiatble for security
java/spring/security/audit/remoting/                        | Java     | Spring         | Find use of Spring Remoting
php/php-filter-chain-read-from-oracle                       | PHP      |                | Detect possible PHP filter chain error based oracle
yaml/github-actions/security/save-state                     | YAML     | Github-Actions | Find use of unsafe and deprecated `::save-state`
yaml/github-actions/security/set-output                     | YAML     | Github-Actions | Find use of unsafe and deprecated `::set-output`
yaml/kubernetes/audit/network-policy-ingress-any            | YAML     | Kubernetes     | Find container specs with `NetworkPolicy` with Ingress Permit ANY
yaml/kubernetes/audit/privileged-container                  | YAML     | Kubernetes     | Find container specs with security context privileged
yaml/kubernetes/security/allow-privileged-escalation        | YAML     | Kubernetes     | Find container specs, including init containers, allowing privilege escalation (`allowPrivilegeEscalation`)
yaml/kubernetes/security/no-security-context                | YAML     | Kubernetes     | Find containers specs, with no security context, considers pod or container security context.
yaml/kubernetes/security/run-as-non-root                    | YAML     | Kubernetes     | Find container specs, allowing running as root (missing `runAsNonRoot`), considers pod and container security context [PR][Upstream PR 2630]
yaml/kubernetes/security/run-as-non-root-unsafe-value       | YAML     | Kubernetes     | Find container specs, explicitly allowing running as root (`runAsNonRoot: false`), considers pod and container secuirity context [PR][Upstream PR 2630]


## Audit Focused Rules

These rules are useful for aiding manual source code review by identifying interesting behaviour or enumerating entrypoints.

Rule Path                                                   | Language | Technology     | Description
----------------------------------------------------------- | -------- | -------------- | -------------
c/lang/security/audit/exec                                  | C        |                | Find execution of processes
csharp/dotnet/security/audit/entrypoints/apsnet-controller  | C#       | ASP.NET        | Find ASP.NET REST entrypoints
java/jackson/polymorphic-typing                             | Java     | Jackson        | Find polymorphic typing to aid in finding vulnerable Jackson deserialisation issues.
java/jackson/type-resolver-override                         | Java     | Jackson        | Find polymorphic typing using `TypeResolveBuilder`
java/jax-rs/security/audit/entrypoints/                     | Java     | JAX-RS         | Find JAX-RS REST entrypoints
java/lang/security/audit/processbuilder                     | Java     |                | Find execution of processes with `ProcessBuilder`
java/spring/security/audit/entrypoints/                     | Java     | Spring         | Find Spring REST entrypoints
java/struts2/security/audit/devmode/                        | Java     | Struts2        | Find the use of `devMode`
java/struts2/security/audit/dmi/                            | Java     | Struts2        | Find the use of Dynamic Method Invocation
java/struts2/security/audit/entrypoints/                    | Java     | Struts2        | Find Struts2 REST entrypoints
java/xstream/security/audit/fromxml                         | Java     | XStream        | Find use of `XStream.fromXML(...)`
kotlin/spark/security/audit/entrypoints/                    | Kotlin   | Spark          | Find Spark REST entrypoints
python/flask/security/audit/entrypoints/                    | Python   | Flask          | Find Flask REST entrypoints



[Upstream PR 2630]: https://github.com/returntocorp/semgrep-rules/pull/2630
[elttam]: https://elttam.com/
