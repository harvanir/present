package org.harvan.present.present1.reflection.demo.bean;

import org.harvan.present.present1.reflection.annotation.MyClassAnnotation;
import org.harvan.present.present1.reflection.annotation.MyMethodAnnotation;

/**
 * @author Harvan Irsyadi
 */
@MyClassAnnotation(path = "/v1/users")
public class ManagedBean1 {

  @MyMethodAnnotation(path = "/login")
  public String getLoginValue() {
    return "Successfully logged in.";
  }

  @MyMethodAnnotation(path = "/logout")
  public String getLogoutValue() {
    return "Successfully logged out.";
  }
}