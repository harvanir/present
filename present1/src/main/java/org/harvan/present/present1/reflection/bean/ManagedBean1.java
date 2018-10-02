package org.harvan.present.present1.reflection.bean;

import org.harvan.present.present1.reflection.annotation.MyClassAnnotation;
import org.harvan.present.present1.reflection.annotation.MyMethodAnnotation;

/**
 * @author Harvan Irsyadi
 */
@MyClassAnnotation(requestPrefix = "/v1/users")
public class ManagedBean1 {

  @MyMethodAnnotation(requestValue = "/login")
  public String getLoginValue() {
    return "Successfully logged in.";
  }

  @MyMethodAnnotation(requestValue = "/logout")
  public String getLogoutValue() {
    return "Successfully logged out.";
  }
}