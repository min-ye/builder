package com.lia.builder.jocko;

import java.util.List;

import com.lia.common.CommonObject;
import com.lia.common.exception.CancelInputException;

public class CreateEntityHandler extends InputHandler {
   @Override
   public void run(List<CommonObject> input, IInvokeConsole c) throws Exception{
      try {
         CommonObject object = new Entity();
         for (String propertyName : object.fetchPropertyName()){
            String propertyValue = getPropertyValue(propertyName, c);
            object.setValue(propertyName, propertyValue);
         }
         input.add(object);
      }
      catch (CancelInputException ex) {
         return;
      }
   }
}
