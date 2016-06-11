package com.lia.lego.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.lia.common.CommonObject;
import com.lia.common.mysql.FieldModel;

public class Inventory extends CommonObject {
   private UUID _setKey = null;
   private UUID _brickKey = null;
   private Int _quantity = 0;

   
   public Inventory(UUID setKey,
                    UUID brickKey,
                    Int quantity){
      this._setKey = setKey;
      this._brickKey = brickKey;
      this._quantity = quantity;
   }
   
   public Inventory(CommonObject commonObject){
      Object[] object = commonObject.fetchObject();
      this._setKey = ConvertToUUID(object[0]);
      this._brickKey = ConvertToUUID(object[1]);
      this._quantity = ConvertToInt(object[2]);
   }
   
   public UUID getSetKey() {
      return _setKey;
   }

   public void setSetKey(UUID setKey) {
      this._setKey = setKey
   }

   public UUID getBrickKey() {
      return _brickKey;
   }

   public void setBrickKey(UUID brickKey) {
      this._brickKey = brickKey
   }

   public Int getQuantity() {
      return _quantity;
   }

   public void setQuantity(Int quantity) {
      this._quantity = quantity
   }


   
   @Override
   public String fetchObjectName(){
      return "Inventory";
   }
   
   @Override
   public String getPropertyValue(String fieldName) throws Exception{
      switch (fieldName){
      case "SetKey":
         return this._setKey.toString();
      case "BrickKey":
         return this._brickKey.toString();
      case "Quantity":
         return this._quantity.toString();

      default:
         throw new Exception(String.format("Unknown Field Name:[%s]", fieldName));
      }
   }
   
   @Override
   public void setValue(String fieldName, String fieldValue) throws Exception
   {
      switch (fieldName) {
      case "SetKey":
         this._setKey = ConvertToUUID(fieldValue);
         break;
      case "BrickKey":
         this._brickKey = ConvertToUUID(fieldValue);
         break;
      case "Quantity":
         this._quantity = ConvertToInt(fieldValue);
         break;

      default:
         throw new Exception(String.format("Unknown Field Name:[%s]", fieldName));
      }
   }
   
   @Override
   public void importModel(Map<String, Object> item) throws Exception{
      for (Entry<String, Object> entry : item.entrySet()) {
         setValue(entry.getKey(), entry.getValue().toString());
      }
   }
   
   public Map<String, FieldModel> exportModel(){
      Map<String, FieldModel> modelMap = new HashMap<String, FieldModel>();
      modelMap.put("SetKey", new FieldModel("UUID", this._setKey, false));
      modelMap.put("BrickKey", new FieldModel("UUID", this._brickKey, false));
      modelMap.put("Quantity", new FieldModel("Int", this._quantity, false));

      return modelMap;
   }
   
   @Override
   public Map<String, String> exportPropertyMap(){
      Map<String, String> modelMap = new HashMap<String, String>();
      modelMap.put("SetKey", getPropertyValueUUID(this._setKey));
      modelMap.put("BrickKey", getPropertyValueUUID(this._brickKey));
      modelMap.put("Quantity", getPropertyValueInt(this._quantity));

      return modelMap;
   }
   
   @Override
   public Map<String, String> exportKeyPropertyMap(){
      Map<String, String> modelMap = new HashMap<String, String>();
      
      return modelMap;
   }
   
   @Override
   public Map<String, String> exportValuePropertyMap(){
      Map<String, String> modelMap = new HashMap<String, String>();
      modelMap.put("SetKey", getPropertyValueUUID(this._setKey));
      modelMap.put("BrickKey", getPropertyValueUUID(this._brickKey));
      modelMap.put("Quantity", getPropertyValueInt(this._quantity));

      return modelMap;
   }
   
   @Override
   public ArrayList<String> fetchPropertyName(){
      ArrayList<String> fieldNameList = new ArrayList<String>();
      fieldNameList.add("SetID");
      fieldNameList.add("Number");
      fieldNameList.add("Variant");
      fieldNameList.add("Theme");
      fieldNameList.add("SubTheme");
      fieldNameList.add("Year");
      fieldNameList.add("Name");
      fieldNameList.add("Minifigs");
      fieldNameList.add("Pieces");
      fieldNameList.add("PriceUK");
      fieldNameList.add("PriceUS");
      fieldNameList.add("PriceCA");
      fieldNameList.add("PriceEU");
      fieldNameList.add("ImageURL");
      return fieldNameList;
   }
   
   @Override
   public Object[] fetchObject() {
      Object[] obj = new Object[3];
      obj[0] = this._setID;
      obj[1] = this._number;
      obj[2] = this._variant;
      obj[3] = this._theme;
      obj[4] = this._subTheme;
      obj[5] = this._year;
      obj[6] = this._name;
      obj[7] = this._minifigs;
      obj[8] = this._pieces;
      obj[9] = this._priceUK;
      obj[10] = this._priceUS;
      obj[11] = this._priceCA;
      obj[12] = this._priceEU;
      obj[13] = this._imageURL;
      
      return obj;
   }

   @Override
   public String fetchDescription() {
      return String.format("%s(%s)", this._number, this._name);
   }
}
