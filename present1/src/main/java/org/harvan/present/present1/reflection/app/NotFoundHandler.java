package org.harvan.present.present1.reflection.app;

import java.lang.reflect.Method;
import javafx.util.Pair;

/**
 * @author Harvan Irsyadi
 */
class NotFoundHandler {

  private final Handler handler;

  NotFoundHandler() {
    Bean bean = new Bean();

    Pair<Object, Method> pair = null;
    try {
      pair = new Pair<>(bean, bean.getClass().getDeclaredMethod("notFound"));
    } catch (NoSuchMethodException e) {
      e.printStackTrace();
    }

    handler = new Handler(pair);
  }

  Handler getHandler() {
    return handler;
  }

  static class Bean {

    private static String response = "No handler found...";

    String notFound() {
      return response;
    }
  }
}