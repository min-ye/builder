package com.lia.builder.jocko;

public class OutputHandlerFactory {
   public static <T extends OutputHandler> T createHandler(Class<T> c) throws InstantiationException, IllegalAccessException, ClassNotFoundException{
      OutputHandler handler = null;
      handler = (OutputHandler)Class.forName(c.getName()).newInstance();
      return (T) handler;
   }
}
