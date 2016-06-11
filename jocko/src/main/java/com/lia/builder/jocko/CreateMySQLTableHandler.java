package com.lia.builder.jocko;

import java.io.InputStream;
import java.util.List;

import org.apache.commons.io.IOUtils;

import com.lia.common.CommonObject;

public class CreateMySQLTableHandler extends OutputHandler {
   @Override
   public String run(CommonObject entity, List<CommonObject> fieldList) throws Exception{
      InputStream url = Console.class.getResourceAsStream("/MySQLTable.bit");
      String output = IOUtils.toString(url);
      Entity entityObject = (Entity) entity;
      output = output.replaceAll("<TableName>", getClassName(entityObject));
      output = output.replaceAll("<ColumnDefinition>", getColumnDefinition(fieldList));
      output = output.replaceAll("<PrimaryKey>", getPrimaryKey(fieldList));
      return output;
   }
   
   private String getClassName(Entity entity) throws Exception {
      return entity.getClassName();
   }

   private String getColumnDefinition(List<CommonObject> fieldList) throws Exception {
      String output = "";
      for (CommonObject obj : fieldList) {
         Field field = (Field) obj;
            output += (output.length() > 0? getTab(1) : "");
            String fieldName = field.getFieldName();
            output += String.format("`%s` %s %s,", fieldName, getTypeDefinition(field), getAllowNull(field));
            output += "\r\n";

      }
      return output;
   }
   
   private String getPrimaryKey(List<CommonObject> fieldList) throws Exception {
      String output = "";
      for (CommonObject obj : fieldList) {
         Field field = (Field) obj;
         if (field.isPrimary()) {
            output += (output.length() > 0? "," : "");
            String fieldName = field.getFieldName();
            output += String.format("`%s`", fieldName);
         }
      }
      return output;
   }
   
   private String getTypeDefinition(Field field) throws Exception {
      switch (field.getType()) {
      case "String":
         return String.format("VARCHAR(%d)", field.getSize());
      case "Long":
         return "INTEGER";
      case "Int":
      case "Integer":
         return "SMALLINT";
      case "Short":
         return "SMALLINT";
      case "Double":
         return "DOUBLE";
      case "Float":
         return "FLOAT";
      case "Date":
         return "DATE";
      case "Time":
         return "TIME";
      case "Boolean":
         return "BIT";
      case "UUID":
         return "VARCHAR(36)";
      default:
         throw new Exception(String.format("Unknown Type [%s]", field.getType()));   
      }
   }
   
   private String getAllowNull(Field field) {
      if (field.isAllowNull()) {
         return "NULL";
      }
      else {
         return "NOT NULL";
      }
   }
}
