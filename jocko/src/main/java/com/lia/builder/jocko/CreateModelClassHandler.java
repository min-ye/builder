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
      output = output.replaceAll("<ConstructObjectArgumentAssignment>", getConstructObjectArgumentAssignment(fieldList));
      output = output.replaceAll("<Property>", getProperty(fieldList));
      output = output.replaceAll("<GetPropertyValue>", getGetPropertyValue(fieldList));
      output = output.replaceAll("<SetPropertyValue>", getSetPropertyValue(fieldList));
      output = output.replaceAll("<ExportModel>", getExportModel(fieldList));
      output = output.replaceAll("<ExportPropertyMap>", getExportPropertyMap(fieldList));
      output = output.replaceAll("<ExportKeyPropertyMap>", getExportKeyPropertyMap(fieldList));
      output = output.replaceAll("<ExportValuePropertyMap>", getExportValuePropertyMap(fieldList));
      output = output.replaceAll("<FetchPropertyName>", getFetchPropertyName(fieldList));
      output = output.replaceAll("<FetchObject>", getFetchObject(fieldList));
      output = output.replaceAll("<PropertyCount>", String.valueOf(fieldList.size()));
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
      case "Int":
      case "Short":
      case "Long":
      case "Float":
      case "Double":
         return "0";
      case "Boolean":
         return "false";
      case "Data":
         return "new Date()";
      case "UUID":
         return "null";
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
   
   private String getConstructObjectArgumentAssignment(List<CommonObject> fieldList) {
      String output = "";
      int index = 0;
      for (CommonObject obj : fieldList) {
         
         Field field = (Field) obj;
         String indent = "\r\n" + getTab(2);
         output += (output.length() > 0? indent : "");
         
         String variable = String.format("this.%s = convertTo%s(object[%d]);", getPrivateVariableName(field.getFieldName()), field.getType(), index);
         
         output += variable;
         index++;
      }
      return output;
   }
   
   private String getProperty(List<CommonObject> fieldList) {
      String output = "";
      for (CommonObject obj : fieldList) {
         
         Field field = (Field) obj;
         output += (output.length() > 0? getTab(1) : "");
         output += String.format("public %s get%s() {", field.getType(), field.getFieldName());
         output += "\r\n";
         output += String.format("%sreturn this.%s;", getTab(2), getPrivateVariableName(field.getFieldName()));
         output += "\r\n";
         output += String.format("%s}", getTab(1));
         output += "\r\n";
         output += "\r\n";
         output += String.format("%spublic void set%s(%s %s) {", getTab(1), field.getFieldName(), field.getType(), getVariableName(field.getFieldName()));
         output += "\r\n";
         output += String.format("%sthis.%s = %s;", getTab(2), getPrivateVariableName(field.getFieldName()), getVariableName(field.getFieldName()));
         output += "\r\n";
         output += String.format("%s}", getTab(1));
         output += "\r\n";
         output += "\r\n";
      }
      return output;
   }
   
   private String getGetPropertyValue(List<CommonObject> fieldList) {
      String output = "";
      for (CommonObject obj : fieldList) {
         Field field = (Field) obj;
         output += (output.length() > 0? getTab(2) : "");
         output += String.format("case \"%s\":", field.getFieldName());
         output += "\r\n";
         if (field.getType().toLowerCase().equals("string")) {
            output += String.format("%sreturn this.%s;", getTab(3), getPrivateVariableName(field.getFieldName()));
         }
         else {
            output += String.format("%sreturn this.%s.toString();", getTab(3), getPrivateVariableName(field.getFieldName()));
         }
         output += "\r\n";
      }
      return output;
   }
   
   private String getSetPropertyValue(List<CommonObject> fieldList) {
      String output = "";
      for (CommonObject obj : fieldList) {
         Field field = (Field) obj;
         output += (output.length() > 0? getTab(2) : "");
         output += String.format("case \"%s\":", field.getFieldName());
         output += "\r\n";
         if (field.getType().equals("String")) {
            output += String.format("%sthis.%s = fieldValue;", getTab(3), getPrivateVariableName(field.getFieldName()));
         }
         else {
            output += String.format("%sthis.%s = convertTo%s(fieldValue);", getTab(3), getPrivateVariableName(field.getFieldName()), field.getType());
         }
         output += "\r\n";
         output += String.format("%sbreak;", getTab(3));
         output += "\r\n";
      }
      return output;
   }

   private String getExportModel(List<CommonObject> fieldList) {
      String output = "";
      for (CommonObject obj : fieldList) {
         Field field = (Field) obj;
         output += (output.length() > 0? getTab(2) : "");
         String fieldName = field.getFieldName();
         if (field.getType().toLowerCase().equals("string")) {
            output += String.format("modelMap.put(\"%s\", new FieldModel(\"%s\", this.%s, %b));", fieldName, field.getType(), getPrivateVariableName(fieldName), field.isPrimary());
         }
         else {
            output += String.format("modelMap.put(\"%s\", new FieldModel(\"%s\", this.%s.toString(), %b));", fieldName, field.getType(), getPrivateVariableName(fieldName), field.isPrimary());
         }
         output += "\r\n";
      }
      return output;
   }
   
   private String getExportPropertyMap(List<CommonObject> fieldList) {
      String output = "";
      for (CommonObject obj : fieldList) {
         Field field = (Field) obj;
         output += (output.length() > 0? getTab(2) : "");
         String fieldName = field.getFieldName();
         output += String.format("modelMap.put(\"%s\", getPropertyValueString(this.%s));", fieldName, getPrivateVariableName(fieldName));
         output += "\r\n";
      }
      return output;
   }
   
   private String getExportKeyPropertyMap(List<CommonObject> fieldList) {
      String output = "";
      for (CommonObject obj : fieldList) {
         Field field = (Field) obj;
         if (field.isPrimary()) {
            output += (output.length() > 0? getTab(2) : "");
            String fieldName = field.getFieldName();
            output += String.format("modelMap.put(\"%s\", getPropertyValueString(this.%s));", fieldName, getPrivateVariableName(fieldName));
            output += "\r\n";
         }
      }
      return output;
   }
   
   private String getExportValuePropertyMap(List<CommonObject> fieldList) {
      String output = "";
      for (CommonObject obj : fieldList) {
         Field field = (Field) obj;
         if (!field.isPrimary()) {
            output += (output.length() > 0? getTab(2) : "");
            String fieldName = field.getFieldName();
            output += String.format("modelMap.put(\"%s\", getPropertyValueString(this.%s));", fieldName, getPrivateVariableName(fieldName));
            output += "\r\n";
         }
      }
      return output;
   }
   
   private String getFetchPropertyName(List<CommonObject> fieldList) {
      String output = "";
      for (CommonObject obj : fieldList) {
         Field field = (Field) obj;
         
         output += (output.length() > 0? getTab(2) : "");
         String fieldName = field.getFieldName();
         output += String.format("fieldNameList.add(\"%s\");", fieldName);
         output += "\r\n";
         
      }
      return output;
   }
   
   private String getFetchObject(List<CommonObject> fieldList) {
      String output = "";
      int index = 0;
      for (CommonObject obj : fieldList) {
         Field field = (Field) obj;
         
         output += (output.length() > 0? getTab(2) : "");
         String fieldName = field.getFieldName();
         output += String.format("obj[%d] = this.%s;", index, getPrivateVariableName(fieldName));
         output += "\r\n";
         index ++;
         
      }
      return output;
   }
}
