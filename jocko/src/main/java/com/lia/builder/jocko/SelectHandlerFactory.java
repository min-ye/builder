package com.lia.builder.jocko;

public class SelectHandlerFactory {
   public static <T extends SelectHandler> T createHandler(Class<T> c) throws InstantiationException, IllegalAccessException, ClassNotFoundException{
      SelectHandler handler = null;
      handler = (SelectHandler)Class.forName(c.getName()).newInstance();
      return (T) handler;
   }
}
