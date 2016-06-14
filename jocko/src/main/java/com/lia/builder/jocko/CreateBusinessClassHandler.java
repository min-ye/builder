package com.lia.builder.jocko;

import java.io.InputStream;
import java.util.List;

import org.apache.commons.io.IOUtils;

import com.lia.common.CommonObject;

public class CreateBusinessClassHandler extends OutputHandler {
   @Override
   public String run(CommonObject entity, List<CommonObject> fieldList) throws Exception{
      InputStream url = Console.class.getResourceAsStream("/BusinessClass.bit");
      String output = IOUtils.toString(url);
      Entity entityObject = (Entity) entity;
      output = output.replaceAll("<PackageName>", getPackageName(entityObject));
      output = output.replaceAll("<ClassName>", getClassName(entityObject));
      output = output.replaceAll("<TableName>", getTableName(entityObject));
      output = output.replaceAll("<ClassVariableName>", getClassVariableName(entityObject));
      output = output.replaceAll("<FullClassName>", getFullClassName(entityObject));
      output = output.replaceAll("<TableAlias>", getTableAlias(entityObject));
      output = output.replaceAll("<ClassListVariableName>", getClassListVariableName(entityObject));
      return output;
   }
   
   private String getPackageName(Entity entity) throws Exception {
      return entity.getPackageName();
   }
   
   private String getClassName(Entity entity) throws Exception {
      return entity.getClassName();
   }
   
   private String getTableName(Entity entity) throws Exception {
      return entity.getTableName();
   }
   
   private String getClassVariableName(Entity entity) {
      String className = entity.getClassName();
      return className.substring(0, 1).toLowerCase() + className.substring(1, className.length());
   }
   
   private String getFullClassName(Entity entity) {
      return String.format("%s.%s", entity.getPackageName(), entity.getClassName());
   }
   
   private String getTableAlias(Entity entity){
      return entity.getClassName().substring(0, 1).toLowerCase();
   }
   
   private String getClassListVariableName(Entity entity) {
      String className = entity.getClassName();
      return String.format("%sList", className.substring(0, 1).toLowerCase() + className.substring(1, className.length()));
   }
}
