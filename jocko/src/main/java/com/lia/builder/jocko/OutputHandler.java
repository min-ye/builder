package com.lia.builder.jocko;

import java.util.List;

import com.lia.common.CommonObject;

public abstract class OutputHandler extends CommonHandler{
   public abstract String run(CommonObject _entity, List<CommonObject> _fieldList) throws Exception;
   
   protected String getTab(int count) {
      String output = "";
      for (int i = 0; i < count * 3; i++) {
         output += " ";
      }
      return output;
   }
}
