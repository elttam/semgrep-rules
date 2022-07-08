// Copied from: https://struts.apache.org/plugins/convention/#namespace-annotation
package com.example.actions;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;

// ruleid: convention-annotation-class
@Namespace("/custom")
public class HelloWorld extends ActionSupport {
  @Action("/different/url")
  public String execute() {
    return SUCCESS;
  }

  @Action("url")
  public String doSomething() {
    return SUCCESS;
  }
}
