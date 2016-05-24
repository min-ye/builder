package com.lia.builder.jocko.code;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import com.lia.common.CommonObject;

public class Factory {
   public static <T extends Handler> T createHandler(Class<T> c, CommonObject entity) throws InstantiationException, IllegalAccessException, ClassNotFoundException{
      Handler handler = null;
      handler = (Handler)Class.forName(c.getName()).newInstance();
      return (T) handler;
   }
   
   public static <T extends Handler> T createHandler(Class<T> c, List<CommonObject> fieldList) throws InstantiationException, IllegalAccessException, ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException{
      //Handler handler = null;
      //handler = (Handler)Class.forName(c.getName()).newInstance();
      Class cl = Class.forName(c.getName());   
      /*以下调用无参的、私有构造函数*/   
      //Constructor con = object.getDeclaredConstructor();   
      //con.setAccessible(true);   
      //Handler handler = (Handler)con.newInstance();   
      /*以下调用带参的、私有构造函数*/   
      Constructor con = cl.getDeclaredConstructor(new Class[]{List.class});   
      con.setAccessible(true);   
      Handler handler = (Handler)con.newInstance(fieldList);   
      return (T) handler;
   }
   
   public static <T extends Handler> T createHandler(Class<T> c, CommonObject entity, List<CommonObject> fieldList) throws InstantiationException, IllegalAccessException, ClassNotFoundException{
      Handler handler = null;
      handler = (Handler)Class.forName(c.getName()).newInstance();
      return (T) handler;
   }
}

