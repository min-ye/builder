package com.lia.builder.jocko;

import java.util.List;
import java.util.Map;

import com.lia.common.CommonObject;
import com.lia.common.exception.CancelInputException;

public class SelectHandler extends CommonHandler{
   public CommonObject select(List<CommonObject> objectList, IInvokeConsole c) throws Exception {
      try {
         Map<Integer, CommonObject> objectOption = buildCommonObjectOption(objectList);
         if (objectOption.size() == 0)
         {
            c.write("No record.");
            throw new CancelInputException();
         }
         Integer index = c.chooseObject(objectOption);
         return objectList.get(index);
      }
      catch (CancelInputException ex){
         return null;
      }

   }
}
