package com.lia.builder.jocko;

import java.util.List;

import com.lia.common.CommonObject;

public class UpdateEntityHandler extends InputHandler{
   
   @Override
   public void run(List<CommonObject> input, IInvokeConsole c) throws Exception{
      int index = selectObject(input, c);
      
      if (index != -1){
         CommonObject object = input.get(index);
         String propertyName = selectProperty(object, c);
         String propertyValue = getPropertyValue(propertyName, c);
         object.setValue(propertyName, propertyValue);
      }
      else {
         c.write(String.format("No entity exist.[%d]", index));
      }
   }
}
