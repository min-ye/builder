package com.lia.builder.jocko;

import java.io.InputStream;
import java.util.List;

import org.apache.commons.io.IOUtils;

import com.lia.common.CommonObject;

public class CreateModelClassHandler extends OutputHandler {
   @Override
   public String run(CommonObject entity, List<CommonObject> fieldList) throws Exception{
      InputStream url = Console.class.getResourceAsStream("/ModelClass.bit");
      String output = IOUtils.toString(url);
      Entity entityObject = (Entity) entity;
      output = output.replaceAll("<PackageName>", getPackageName(entityObject));
      output = output.replaceAll("<ClassName>", getClassName(entityObject));
      output = output.replaceAll("<PrivateVariable>", getPrivateVariableDefinition(fieldList));
      int length = entityObject.getClassName().length();
      output = output.replaceAll("<ConstructArgument>", getConstructArgument(fieldList, length));
      output = output.replaceAll("<ConstructArgumentAssignment>", getConstructArgumentAssignment(fieldList));
      return output;
   }
   
   private String getPackageName(Entity entity) throws Exception {
      return entity.getPackageName();
   }
   
   private String getClassName(Entity entity) throws Exception {
      return entity.getClassName();
   }
   
   private String getPrivateVariableDefinition(List<CommonObject> fieldList) throws Exception {
      String output = "";
      for (CommonObject obj : fieldList) {
         Field field = (Field) obj;
         output += (output.length() > 0? getTab(1): "");
         String variable = String.format("private %s %s = %s;", field.getType(), getPrivateVariableName(field.getFieldName()), getPrivateVariableDefaultValue(field.getType()));
         output += variable + "\r\n";
      }
      return output;
   }
   
   private String getPrivateVariableName(String fieldName) {
      return "_" + fieldName.substring(0, 1).toLowerCase() + fieldName.substring(1, fieldName.length());
   }
   
   private String getVariableName(String fieldName) {
      return fieldName.substring(0, 1).toLowerCase() + fieldName.substring(1, fieldName.length());
   }
   
   private String getPrivateVariableDefaultValue(String type) throws Exception {
      switch (type) {
      case "String":
         return "\"\"";
      case "Integer":
      case "Short":
      case "Long":
      case "Float":
      case "Double":
         return "0";
      case "Boolean":
         return "false";
      case "Data":
         return "new Date()";
      default:
         throw new Exception(String.format("Unknown Data Type [%s]", type));
      }
   }
   
   private String getConstructArgument(List<CommonObject> fieldList, int classNameLength) {
      String output = "";
      for (CommonObject obj : fieldList) {
         
         Field field = (Field) obj;
         String indent = ",\r\n" + getSpace(11 + classNameLength);
         output += (output.length() > 0? indent : "");
         String variable = String.format("%s %s", field.getType(), getVariableName(field.getFieldName()));
         
         output += variable;
         

      }
      return output;
   }
   
   private String getConstructArgumentAssignment(List<CommonObject> fieldList) {
      String output = "";
      for (CommonObject obj : fieldList) {
         
         Field field = (Field) obj;
         String indent = "\r\n" + getTab(2);
         output += (output.length() > 0? indent : "");
         String variable = String.format("this.%s = %s;", getPrivateVariableName(field.getFieldName()), getVariableName(field.getFieldName()));
         
         output += variable;
         

      }
      return output;
   }
}
