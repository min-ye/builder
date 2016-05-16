package com.lia.builder.jocko;

public class InputHandlerFactory {
   public static <T extends InputHandler> T createHandler(Class<T> c) throws InstantiationException, IllegalAccessException, ClassNotFoundException{
      InputHandler handler = null;
      System.out.println(c.getName());
      handler = (InputHandler)Class.forName(c.getName()).newInstance();
      
      return (T) handler;
   }
}
