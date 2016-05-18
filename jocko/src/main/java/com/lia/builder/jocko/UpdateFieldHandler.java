package com.lia.builder.jocko;

import java.util.List;

import com.lia.common.CommonObject;

public class UpdateFieldHandler extends InputHandler{
   
   @Override
   public void run(List<CommonObject> input, IInvokeConsole c) throws Exception{
      int index = selectObject(input, c);
      
      if (index != -1){
         CommonObject object = input.get(index);
         boolean quit = false;
         while (!quit){
            c.write(object.fetchDescription());
            String propertyName = selectProperty(object, c);
            if (propertyName.equals("0") || propertyName.length() == 0) {
               quit = true;
            }
            else{
               String originValue = object.getPropertyValue(propertyName);
               String propertyValue = getPropertyValue(propertyName + "[" + originValue + "]", c);
               if (propertyValue.length() > 0) {
                  object.setValue(propertyName, propertyValue);
               }
            }
         }
      }
      else {
         c.write(String.format("No entity exist.[%d]", index));
      }
   }
}
