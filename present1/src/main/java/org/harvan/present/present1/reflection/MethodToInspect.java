package org.harvan.present.present1.reflection;

import org.harvan.present.present1.reflection.annotation.MyMethodAnnotation;

/**
 * @author Harvan Irsyadi
 */
public class MethodToInspect {

  private String stringValue;

  @MyMethodAnnotation(requestValue = "requestValue")
  public String getStringValue() {
    return stringValue;
  }

  public void setStringValue(String stringValue) {
    this.stringValue = stringValue;
  }
}