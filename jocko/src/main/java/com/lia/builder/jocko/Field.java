package com.lia.builder.jocko;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.lia.common.CommonObject;

import java.util.UUID;

public class Field extends CommonObject{
   private String _propertyName = "";
   private DataType _type = DataType.String;
   private int _size = 0;
   private boolean _primary = false;
   private UUID _key = java.util.UUID.fromString("");
   
   public String getPropertyName() {
      return _propertyName;
   }

   public void setPropertyName(String propertyName) {
      this._propertyName = propertyName;
   }

   public DataType getType() {
      return _type;
   }

   public void setType(DataType type) {
      this._type = type;
   }

   public int getSize() {
      return _size;
   }

   public void setSize(int size) {
      this._size = size;
   }

   public boolean isPrimary() {
      return _primary;
   }

   public void setPrimary(boolean primary) {
      this._primary = primary;
   }

   public UUID getKey() {
      return _key;
   }

   public void setKey(UUID key) {
      this._key = key;
   }

   @Override
   public String fetchObjectName() {
      return "Property";
   }

   @Override
   public String getPropertyValue(String propertyName) throws Exception {
      switch (propertyName){
      case "PropertyName":
         return this._propertyName;
      case "Type":
         return this._type.toString();
      case "Size":
         return Integer.toString(this._size);
      case "Primary":
         return Boolean.toString(_primary);
      case "Key":
         return this._key.toString();
      default:
         throw new Exception(String.format("Unknown Field[%s]", propertyName));
      }
   }

   @Override
   public void setValue(String propertyName, String propertyValue) throws Exception {
      switch (propertyName){
      case "PropertyName":
         this._propertyName = propertyValue;
         break;
      case "DataType":
         this._type = DataType.valueOf(propertyValue);
         break;
      case "Size":
         this._size = Integer.parseInt(propertyValue);
         break;
      case "Primary":
         this._primary = Boolean.parseBoolean(propertyValue);
         break;
      case "Key":
         if (propertyValue.length() > 0)
         this._key = UUID.fromString(propertyValue);
         else
            this._key = UUID.randomUUID();
         break;
      default:
         throw new Exception(String.format("Unknown Field[%s]", propertyName));
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
      modelMap.put("PropertyName", this._propertyName);
      modelMap.put("Type", this._type.toString());
      modelMap.put("Size", Integer.toString(this._size));
      modelMap.put("Primary", Boolean.toString(this._primary));
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
   public Map<String, String> exportValueFieldMap() {
      Map<String, String> modelMap = new HashMap<String, String>();
      
      modelMap.put("PropertyName", this._propertyName);
      modelMap.put("Type", this._type.toString());
      modelMap.put("Size", Integer.toString(this._size));
      modelMap.put("Primary", Boolean.toString(this._primary));

      return modelMap;
   }

   @Override
   public List<String> fetchPropertyName() {
      List<String> fieldNameList = new ArrayList<String>();
      
      fieldNameList.add("PropertyName");
      fieldNameList.add("Type");
      fieldNameList.add("Size");
      fieldNameList.add("Primary");
      fieldNameList.add("Key");
      return fieldNameList;
   }

   @Override
   public Object[] fetchObject() {
      Object[] obj = new Object[5];
      obj[0] = this._propertyName;
      obj[1] = this._type;
      obj[2] = this._size;
      obj[3] = this._primary;
      obj[4] = this._key;
      return obj;
   }

   @Override
   public String fetchDescription() {
      return String.format("%s(type: %s; size: %d; primary: %s)", this._propertyName, this._type.toString(), this._size, String.valueOf(this._primary) );
   }
}
