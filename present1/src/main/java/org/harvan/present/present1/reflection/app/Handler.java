package org.harvan.present.present1.reflection.app;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import javafx.util.Pair;

/**
 * @author Harvan Irsyadi
 */
public class Handler {

  private Pair<Object, Method> pair;

  public Handler(Pair<Object, Method> pair) {
    this.pair = pair;
  }

  public Object invoke() throws InvocationTargetException, IllegalAccessException {
    return pair.getValue().invoke(pair.getKey());
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("Handler{");
    sb.append("bean=").append(pair.getKey());
    sb.append(", method=").append(pair.getValue());
    sb.append('}');
    return sb.toString();
  }
}
