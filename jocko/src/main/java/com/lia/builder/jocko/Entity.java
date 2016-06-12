package com.lia.builder.jocko;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.lia.common.CommonObject;
import com.lia.common.exception.UnknownPropertyException;

import java.util.UUID;

public class Entity extends CommonObject{
   private String _packageName = "";
   private String _className = "";
   private String _tableName = "";
   private UUID _key = null;

   public Entity(){
      
   }

   public Entity(String packageName, String className, String tableName, UUID key){
      this._packageName = packageName;
      this._className = className;
      this._tableName = tableName;
      this._key = key;
   }
   
   public Entity(Map<String, String> item){
      this._packageName = item.get("packageName");
      this._className = item.get("className");
      this._tableName = item.get("tableName");
      this._key = UUID.fromString(item.get("key"));
   }
   
   
   public String getPackageName() {
      return _packageName;
   }

   public void setPackageName(String _packageName) {
      this._packageName = _packageName;
   }

   public String getClassName() {
      return _className;
   }

   public void setClassName(String _className) {
      this._className = _className;
   }
   
   public String getTableName() {
      return _tableName;
   }

   public void setTableName(String _tableName) {
      this._tableName = _tableName;
   }

   public UUID getKey() {
      return _key;
   }

   public void setKey(UUID _key) {
      this._key = _key;
   }
   
   @Override
   public String fetchObjectName() {
      return "Entity";
   }

   @Override
   public String getPropertyValue(String fieldName) throws UnknownPropertyException {
      switch (fieldName){
      case "PackageName":
         return this._packageName;
      case "ClassName":
         return this._className;
      case "TableName":
         return this._tableName;
      case "Key":
         return this._key.toString();
      default:
         throw new UnknownPropertyException(fieldName);
      }
   }

   @Override
   public void setValue(String fieldName, String fieldValue) throws UnknownPropertyException {
      switch (fieldName){
      case "PackageName":
         this._packageName = fieldValue;
         break;
      case "ClassName":
         this._className = fieldValue;
         break;
      case "TableName":
         this._tableName = fieldValue;
         break;
      case "Key":
         if (fieldValue.length() > 0)
            this._key = UUID.fromString(fieldValue);
         else
            this._key = UUID.randomUUID();
         break;
      default:
         throw new UnknownPropertyException(fieldName);
      }
   }

   @Override
   public void importModel(Map<String, Object> item) throws Exception {
      for (Entry<String, Object> entry : item.entrySet()) {
         setValue(entry.getKey(), entry.getValue().toString());
      }
   }

   @Override
   public Map<String, String> exportPropertyMap() {
      Map<String, String> modelMap = new HashMap<String, String>();
      modelMap.put("PackageName", this._packageName);
      modelMap.put("ClassName", this._className);
      modelMap.put("TableName", this._tableName);
      modelMap.put("Key", this._key.toString());

      return modelMap;
   }

   @Override
   public Map<String, String> exportKeyPropertyMap() {
      Map<String, String> modelMap = new HashMap<String, String>();
      modelMap.put("Key", this._key.toString());

      return modelMap;
   }

   @Override
   public Map<String, String> exportValuePropertyMap() {
      Map<String, String> modelMap = new HashMap<String, String>();
      
      modelMap.put("ProjectName", this._packageName);
      modelMap.put("ClassName", this._className);
      modelMap.put("TableName", this._tableName);

      return modelMap;
   }

   @Override
   public List<String> fetchPropertyName() {
      List<String> fieldNameList = new ArrayList<String>();
      
      fieldNameList.add("PackageName");
      fieldNameList.add("ClassName");
      fieldNameList.add("TableName");
      fieldNameList.add("Key");
      return fieldNameList;
   }

   @Override
   public Object[] fetchObject() {
      Object[] obj = new Object[3];
      obj[0] = this._packageName;
      obj[1] = this._className;
      obj[2] = this._tableName;
      obj[3] = this._key;
      return obj;
   }

   @Override
   public String fetchDescription() {
      return this._className + "(" + this._packageName + ")";
   }

}
