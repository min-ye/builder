package com.lia.builder.jocko;

import java.util.List;

import com.lia.common.CommonObject;
import com.lia.common.exception.CancelInputException;

public class DeleteHandler extends InputHandler{

   @Override
   public void run(List<CommonObject> input, IInvokeConsole c) throws Exception{
      try {
         int index = selectObject(input, c);
      
         if (index != -1){
            input.remove(index);
         }
         else{
            c.write(String.format("No entity exist.[%d]", index));
         }
      }
      catch (CancelInputException ex){
         return;
      }

   }
}
