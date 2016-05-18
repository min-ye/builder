package com.lia.builder.jocko;

import java.util.List;

import com.lia.common.CommonObject;

public class CreateFieldHandler extends InputHandler {
   @Override
   public void run(List<CommonObject> input, IInvokeConsole c) throws Exception{
      CommonObject object = new Field();
      for (String propertyName : object.fetchPropertyName()){
         String propertyValue = getPropertyValue(propertyName, c);
         object.setValue(propertyName, propertyValue);
      }
      input.add(object);
   }
}
