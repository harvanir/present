package org.harvan.present.present1.reflection;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;
import org.harvan.present.present1.reflection.annotation.MyClassAnnotation;
import org.harvan.present.present1.reflection.annotation.MyMethodAnnotation;
import org.junit.Assert;
import org.junit.Test;

/**
 * <pre>
 *   {@link java.lang.Class}
 *   {@link java.lang.reflect.Constructor}
 *   {@link java.lang.reflect.Modifier}
 *   {@link java.lang.reflect.Field}
 *   {@link java.lang.reflect.Method}
 * </pre>
 *
 * @author Harvan Irsyadi
 */
public class ReflectionTest {

  private static final String CLASS_STRING = "org.harvan.present.present1.reflection.ClassToInspect";

  private void printField(Field[] fields) {
    for (Field field : fields) {
      System.out.println(String.format("Field: '%s'", field)); // NOSONAR
    }
  }

  /**
   * <pre>
   *   {@link java.lang.Class}
   *   {@link java.lang.Class#newInstance()}
   * </pre>
   */
  @SuppressWarnings("unchecked")
  @Test
  public void test1InstantiateClass() throws Exception { // NOSONAR
    // load class by class loader
    Class<?> clazz = Class.forName(CLASS_STRING);
    Assert.assertNotNull(clazz);

    // instantiate raw class
    Object object = clazz.newInstance();
    Assert.assertNotNull(object);
    Assert.assertTrue(object instanceof ClassToInspect);

    // casting raw class to it's Type
    ClassToInspect classToInspect = (ClassToInspect) object;
    Assert.assertNull(classToInspect.getFieldString());
    Assert.assertNull(classToInspect.getFieldInteger());
  }

  /**
   * <pre>
   *   {@link java.lang.reflect.Field}
   *   {@link java.lang.reflect.Modifier#isPrivate(int)}
   *   {@link java.lang.reflect.Field#getModifiers()}
   * </pre>
   */
  @Test
  public void test2FindFields() throws NoSuchFieldException {
    Field[] fields = ClassToInspect.class.getDeclaredFields();
    printField(fields);

    Field fieldString = ClassToInspect.class.getDeclaredField("fieldString");
    Field fieldInteger = ClassToInspect.class.getDeclaredField("fieldInteger");

    Assert.assertEquals(2, fields.length);
    Assert.assertNotNull(fieldString);
    Assert.assertNotNull(fieldInteger);

    // assert private modifier
    Assert.assertTrue(Modifier.isPrivate(fieldString.getModifiers()));
    Assert.assertTrue(Modifier.isPrivate(fieldInteger.getModifiers()));
  }

  /**
   * <pre>
   *   {@link java.lang.Class#getConstructor(Class[])}
   *   {@link java.lang.reflect.Constructor}
   *   {@link java.lang.reflect.Constructor#newInstance(Object...)}
   * </pre>
   */
  @Test
  public void test3InstantiateConstructor()
      throws Exception { //NOSONAR
    // load class by class loader
    Class<?> clazz = Class.forName(CLASS_STRING);
    Assert.assertNotNull(clazz);

    // get parameterize constructor
    Constructor<?> constructor = clazz.getConstructor(String.class, Integer.class);
    Assert.assertNotNull(constructor);

    // instantiate class
    String stringVal = "stringVal";
    Integer integerVal = 10;
    Object object = constructor.newInstance(stringVal, integerVal);
    Assert.assertNotNull(object);
  }

  /**
   * <pre>
   *   {@link java.lang.reflect.Field#setAccessible(boolean)}
   *   {@link java.lang.reflect.Field#get(Object)}
   * </pre>
   */
  @Test
  public void test4InstantiateConstructorAndItsField()
      throws Exception { //NOSONAR
    // load class by class loader
    Class<?> clazz = Class.forName(CLASS_STRING);

    // get parameterize constructor
    Constructor<?> constructor = clazz.getConstructor(String.class, Integer.class);

    // instantiate class
    String stringVal = "stringVal";
    Integer integerVal = 10;
    Object object = constructor.newInstance(stringVal, integerVal);

    Field[] fields = clazz.getDeclaredFields();

    // make private field accessible for operation
    fields[0].setAccessible(true);
    fields[1].setAccessible(true);

    Assert.assertTrue(hasEqualsValue(object, fields, stringVal));
    Assert.assertTrue(hasEqualsValue(object, fields, integerVal));
  }

  private boolean hasEqualsValue(Object objectClass, Field[] fields, Object expected) {
    return Arrays.stream(fields).anyMatch(field -> {
      try {
        // get field requestValue of instantiate class
        Object fieldObject = field.get(objectClass);

        return expected.equals(fieldObject);
      } catch (IllegalAccessException e) {
        e.printStackTrace(); // NOSONAR
      }
      return false;
    });
  }

  private void printObject(List<Class<?>> classes) {
    System.out.println("Classes:"); // NOSONAR

    for (Class<?> clazz : classes) {
      System.out.println(clazz); // NOSONAR
    }
  }

  /**
   * <pre>
   *   {@link java.lang.annotation.Annotation}
   *   {@link MyClassAnnotation}
   *   {@link MyMethodAnnotation}
   * </pre>
   */
  @Test
  public void test5GetAnnotation() {
    Annotation classAnnotation = ClassToInspect.class.getAnnotation(MyClassAnnotation.class);
    Assert.assertNotNull(classAnnotation);

    Method[] methods = MethodToInspect.class.getDeclaredMethods();
    boolean foundMethodAnnotation = false;
    for (Method method : methods) {
      if (method.getAnnotation(MyMethodAnnotation.class) != null) {
        foundMethodAnnotation = true;
        break;
      }
    }

    Assert.assertTrue(foundMethodAnnotation);
  }

  /**
   * <pre>
   *   {@link org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider}
   * </pre>
   */
  @Test
  public void test6LoadAllClass() {
    List<Class<?>> classes = ClassUtils
        .getClassByPackage("org.harvan.present.present1.reflection");
    printObject(classes);

    Assert.assertFalse(classes.isEmpty());
  }
}