package org.harvan.present.present1.reflection;

import org.harvan.present.present1.reflection.annotation.MyClassAnnotation;

/**
 * @author Harvan Irsyadi
 */
@MyClassAnnotation(path = "ClassToInspect")
public class ClassToInspect {

  private String fieldString;

  private Integer fieldInteger;

  /**
   * {@link java.lang.Class#newInstance()}
   */
  public ClassToInspect() {
    // default
  }

  public ClassToInspect(String fieldString, Integer fieldInteger) {
    this.fieldString = fieldString;
    this.fieldInteger = fieldInteger;
  }

  public String getFieldString() {
    return fieldString;
  }

  public void setFieldString(String fieldString) {
    this.fieldString = fieldString;
  }

  public Integer getFieldInteger() {
    return fieldInteger;
  }

  public void setFieldInteger(Integer fieldInteger) {
    this.fieldInteger = fieldInteger;
  }
}