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
      return output;
   }
   
   private String getPackageName(Entity entity) throws Exception {
      return entity.getPackageName();
   }
   
   private String getClassName(Entity entity) throws Exception {
      return entity.getClassName();
   }
   
   private String getPrivateVariableDefinition(List<CommonObject> fieldList) {
      String output = "";
      for (CommonObject obj : fieldList) {
         Field field = (Field) obj;
         output += (output.length() > 0? getTab(1): "");
         String variable = String.format("private %s %s", field.getType(), getPrivateVariableName(field.getFieldName()));
         output += variable + "\n";
      }
      return output;
   }
   
   private String getPrivateVariableName(String fieldName) {
      return "_" + fieldName.substring(0, 0).toLowerCase() + fieldName.substring(1, fieldName.length() -1);
   }
}
