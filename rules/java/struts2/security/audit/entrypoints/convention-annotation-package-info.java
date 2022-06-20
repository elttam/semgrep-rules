// Copied from: https://struts.apache.org/plugins/convention/#namespace-annotation
// Hit for https://github.com/returntocorp/semgrep/issues/5420
// Fixed in https://github.com/returntocorp/semgrep/pull/5424
//
// latest version 0.97.0 has it but annotations still don't work
// new bug I guess.
//
// todoruleid: convention-annotation-package-info
@org.apache.struts2.convention.annotation.Namespace("/custom")
package com.example.actions;
