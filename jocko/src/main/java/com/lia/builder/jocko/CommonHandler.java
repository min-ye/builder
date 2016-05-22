package com.lia.builder.jocko;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lia.common.CommonObject;
import com.lia.common.exception.CancelInputException;

public abstract class CommonHandler {
   protected int selectObject(List<CommonObject> objectList, IInvokeConsole c) throws Exception{
      Map<Integer, CommonObject> objectOption = buildCommonObjectOption(objectList);
      if (objectOption.size() == 0)
      {
         c.write("No record.");
         throw new CancelInputException();
      }
      Integer index = c.chooseObject(objectOption);
      return index;
   }
   
   protected String selectProperty(CommonObject obj, IInvokeConsole c) throws Exception{
      Map<Integer, String> option = buildPropertyOption(obj);
      if (option.size() == 0)
      {
         c.write("No record.");
         throw new CancelInputException();
      }
      return option.get(c.chooseString(option));
   }
   
   protected String getPropertyValue(String propertyName, IInvokeConsole c) throws Exception{
      return c.read(String.format("Please input the value of %s:", propertyName));
   }
   
   protected Map<Integer, CommonObject> buildCommonObjectOption(List<CommonObject> objectList) {
      Map<Integer, CommonObject> option = new HashMap<Integer, CommonObject>();
      int index = 0;
      for(CommonObject obj : objectList){
         index = objectList.indexOf(obj);
         option.put(index, obj);
      }
      return option;
   }
   
   protected Map<Integer, String> buildPropertyOption(CommonObject obj) {
      Map<Integer, String> option = new HashMap<Integer, String>();
      int index = 0;
      for (String name : obj.fetchPropertyName()) {
         index ++;
         option.put(index, name);
      }
      return option;
   }
   
   protected boolean compare(CommonObject obj1, CommonObject obj2, String fieldName) throws Exception{
      if (obj1.getPropertyValue(fieldName) == obj2.getPropertyValue(fieldName)) {
         return true;
      }
      else{
         return false;
      }
   }
}
