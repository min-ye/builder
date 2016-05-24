package com.lia.builder.jocko.code;

import java.util.List;

import com.lia.common.CommonObject;

public class ConstructArgumentHandler implements Handler {

   List<CommonObject> _fieldList = null;
   public ConstructArgumentHandler(List<CommonObject> fieldList) {
      _fieldList = fieldList;
   }
   
   @Override
   public String build() {
      // TODO Auto-generated method stub
      return null;
   }

}
