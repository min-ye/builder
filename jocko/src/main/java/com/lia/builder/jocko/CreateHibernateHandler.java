package com.lia.builder.jocko;

import java.io.InputStream;
import java.util.List;

import org.apache.commons.io.IOUtils;

import com.lia.common.CommonObject;

public class CreateHibernateHandler extends OutputHandler {
   @Override
   public String run(CommonObject entity, List<CommonObject> fieldList) throws Exception{
      InputStream url = Console.class.getResourceAsStream("/Hibernate.bit");
      String output = IOUtils.toString(url);
      Entity entityObject = (Entity) entity;
      output = output.replaceAll("<ClassName>", getClassName(entityObject));
      output = output.replaceAll("<TableName>", getClassName(entityObject));
      output = output.replaceAll("<IDProperty>", getIDPropertyDefinition(fieldList));
      output = output.replaceAll("<Property>", getPropertyDefinition(fieldList));
      return output;
   }
   
   private String getClassName(Entity entity) throws Exception {
      return entity.getClassName();
   }

   private String getPropertyDefinition(List<CommonObject> fieldList) throws Exception {
      String output = "";
      for (CommonObject obj : fieldList) {
         Field field = (Field) obj;
         if (!field.isPrimary()) {
            output += (output.length() > 0? getTab(2) : "");
            String fieldName = field.getFieldName();
            if (getHibernateTypeName(field.getType()).equals("string")) {
               output += String.format("<property name=\"%s\" column=\"%s\" type=\"%s\" length=\"%s\"></property>", fieldName, fieldName, getHibernateTypeName(field.getType()), field.getSize());
            }
            else {
               output += String.format("<property name=\"%s\" column=\"%s\" type=\"%s\"></property>", fieldName, fieldName, getHibernateTypeName(field.getType()));
            }
            output += "\r\n";
         }
      }
      return output;
   }
   
   private String getIDPropertyDefinition(List<CommonObject> fieldList) throws Exception {
      String output = "";
      for (CommonObject obj : fieldList) {
         Field field = (Field) obj;
         if (field.isPrimary()) {
            output += (output.length() > 0? getTab(2) : "");
            String fieldName = field.getFieldName();
            if (getHibernateTypeName(field.getType()).equals("string")) {
               output += String.format("<id name=\"%s\" column=\"%s\" type=\"%s\" length=\"%s\"></id>", fieldName, getColumnName(fieldName), getHibernateTypeName(field.getType()), field.getSize());
            }
            else {
               output += String.format("<id name=\"%s\" column=\"%s\" type=\"%s\"></id>", fieldName, getColumnName(fieldName), getHibernateTypeName(field.getType()));
            }
         }
      }
      return output;
   }
   
   private String getColumnName(String columnName) {
      if (columnName.equals("Key")) {
         return "`Key`";
      }
      else {
         return columnName;
      }
   }
   
   private String getHibernateTypeName(String typeName) {
      String output = "";
      typeName = typeName.toLowerCase();
      switch (typeName) {
      case "uuid":
         output = "org.hibernate.type.UUIDCharType";
         break;
      case "string":
         output = "string";
         break;
      case "int":
      case "integer":
         output = "integer";
         break;
      case "short":
         output = "short";
         break;
      case "float":
         output = "float";
         break;
      case "double":
         output = "double";
         break;
      case "boolean":
         output = "boolean";
         break;
      case "date":
         output = "date";
         break;
      case "time":
         output = "time";
         break;
      }
      return output;
   }
}
