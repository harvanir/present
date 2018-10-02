package org.harvan.present.present1.reflection.app;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import javafx.util.Pair;
import org.harvan.present.present1.reflection.ClassUtils;
import org.harvan.present.present1.reflection.annotation.MyClassAnnotation;
import org.harvan.present.present1.reflection.annotation.MyMethodAnnotation;

/**
 * @author Harvan Irsyadi
 */
public class ApplicationManager {

  private static final Map<String, Handler> HANDLER_MAP = new HashMap<>();

  private static final Handler notFoundHandler = new NotFoundHandler().getHandler();

  private ApplicationManager() {
    // hide
  }

  private static Handler getAppropriateHandler(String string) {
    Handler handler = HANDLER_MAP.get(string);

    if (handler != null) {
      return handler;
    }

    return notFoundHandler;
  }

  public static void run(Class<?> applicationClass)
      throws IllegalAccessException, InstantiationException, InvocationTargetException {
    List<Class<?>> classes = ClassUtils.getClassByPackage(applicationClass.getPackage().getName());

    initBean(classes);
    runScanner();
  }

  private static void runScanner() throws InvocationTargetException, IllegalAccessException {
    Scanner scanner = new Scanner(System.in);
    System.out.println("To stop the application, enter '#stop'");
    System.out.println("Enter your request:");

    String input = null;
    while ((input = scanner.next()) != null) {
      if ("#stop".equals(input)) {
        System.out.println("Stopping application...");
        System.exit(0);
      }
      Handler method = ApplicationManager.getAppropriateHandler(input);

      System.out.println("Response: " + method.invoke());
    }

    System.out.println(input);
  }

  private static void initBean(List<Class<?>> classes)
      throws InstantiationException, IllegalAccessException {
    for (Class<?> clazz : classes) {
      if (isBeanToManage(clazz)) {
        loadBean(clazz);
      }
    }
  }

  private static boolean isBeanToManage(Class<?> clazz) {
    return clazz.isAnnotationPresent(MyClassAnnotation.class);
  }

  private static void loadBean(Class<?> clazz)
      throws IllegalAccessException, InstantiationException {
    String prefix = clazz.getAnnotation(MyClassAnnotation.class).requestPrefix();
    Object object = clazz.newInstance();

    loadHandler(object, prefix);
  }

  private static void loadHandler(Object object, String prefix) {
    Method[] methods = object.getClass().getDeclaredMethods();

    for (Method method : methods) {
      MyMethodAnnotation annotation = method.getDeclaredAnnotation(MyMethodAnnotation.class);

      if (annotation != null) {
        String finalRequest = prefix + annotation.requestValue();
        Handler handler = HANDLER_MAP.get(finalRequest);

        if (handler != null) {
          throw new ApplicationException(
              String
                  .format("Duplicate for request '%s' with exsiting '%s'", finalRequest, handler));
        }

        System.out.println(String.format("Register handler for request : '%s'", finalRequest));
        HANDLER_MAP.put(finalRequest, new Handler(new Pair<>(object, method)));
      }
    }
  }
}