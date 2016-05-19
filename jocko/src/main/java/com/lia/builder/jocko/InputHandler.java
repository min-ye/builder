package com.lia.builder.jocko;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lia.common.CommonObject;
import com.lia.common.exception.CancelInputException;

public abstract class InputHandler {
   public abstract void run(List<CommonObject> objectList, IInvokeConsole c) throws Exception;
   
   protected int selectObject(List<CommonObject> objectList, IInvokeConsole c) throws Exception{
      //int index = -1;
      Map<Integer, CommonObject> objectOption = buildCommonObjectOption(objectList);
      Integer index = c.chooseObject(objectOption);
      return index;
      /*showEntityList(objectList, c);
      boolean findEntity = false;
      String prompt = "Entity Key:";
      while (findEntity == false) {
         String input = c.read(prompt);
         
         if (input == "0") {
            findEntity = true;
         }
         else{
            int inputIndex = Integer.parseInt(input);
            for (CommonObject obj : objectList){
               if ( objectList.indexOf(obj) == inputIndex){
                  findEntity = true;
                  index = objectList.indexOf(obj);
               }
            }
         }
      }
      return index;*/
   }
   
   protected String selectProperty(CommonObject obj, IInvokeConsole c) throws IOException, CancelInputException{
      Map<Integer, String> option = buildPropertyOption(obj);
      return option.get(c.choose(option));
      /*String output = "";
      showPropertyNameList(obj, c);
      c.write("0: quit;");
      boolean findProperty = false;
      String prompt = "Entity Property Name:";
      while (findProperty == false) {
         String name = c.read(prompt);
         if (name.equals("0") || name.length() == 0) {
            output = "0";
            findProperty = true;
         }
         else{
            for (String propertyName : obj.fetchPropertyName()){
               c.write(propertyName);
               if (propertyName.equals(name)){
                  findProperty = true;
                  output = propertyName;
               }
            }
         }
      }
      return output;*/
   }
   
   protected String getPropertyValue(String propertyName, IInvokeConsole c) throws IOException, CancelInputException{
      return c.read(String.format("Please input the value of %s:", propertyName));
   }
   
   private Map<Integer, CommonObject> buildCommonObjectOption(List<CommonObject> objectList) {
      //List<CommonObject> entityList = getEntityList();
      Map<Integer, CommonObject> option = new HashMap<Integer, CommonObject>();
      int index = 0;
      for(CommonObject obj : objectList){
         index = objectList.indexOf(obj);
         option.put(index, obj);
         
      }
      return option;
   }
   
   private Map<Integer, String> buildPropertyOption(CommonObject obj) {
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
