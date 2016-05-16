package com.lia.builder.jocko;

import java.io.IOException;
import java.util.List;

import com.lia.common.CommonObject;

public abstract class InputHandler {
   public abstract void run(List<CommonObject> objectList, IInvokeConsole c) throws Exception;
   
   protected int selectObject(List<CommonObject> objectList, IInvokeConsole c) throws Exception{
      int index = -1;
      showEntityList(objectList, c);
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
      return index;
   }
   
   protected String selectProperty(CommonObject obj, IInvokeConsole c) throws IOException{
      String output = "";
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
      return output;
   }
   
   protected String getPropertyValue(String propertyName, IInvokeConsole c) throws IOException{
      return c.read(String.format("Please input the value of %s:", propertyName));
   }
   
   private void showEntityList(List<CommonObject> objectList, IInvokeConsole c) throws Exception{
      //List<CommonObject> entityList = getEntityList();
      int index = 0;
      for(CommonObject obj : objectList){
         index = objectList.indexOf(obj);
         
         c.write(String.format("%d\t%s", 
                  index,
                  obj.fetchDescription()));
      }
   }
   
   private void showPropertyNameList(CommonObject obj, IInvokeConsole c) throws IOException{
      for (String name : obj.fetchPropertyName()) {
         c.write(String.format("%s", name));
      }
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
