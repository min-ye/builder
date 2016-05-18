package com.lia.builder.jocko;

import java.util.List;

import com.lia.common.CommonObject;

public class DeleteFieldHandler extends InputHandler{

   @Override
   public void run(List<CommonObject> input, IInvokeConsole c) throws Exception{
      int index = selectObject(input, c);
      
      if (index != -1){
         input.remove(index);
      }
      else{
         c.write(String.format("No entity exist.[%d]", index));
      }
   }
}
