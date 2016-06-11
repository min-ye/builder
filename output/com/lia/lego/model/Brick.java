package com.lia.lego.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.lia.common.CommonObject;
import com.lia.common.mysql.FieldModel;

public class Brick extends CommonObject {
   private String _elementID = "";
   private String _designID = "";
   private UUID _colorKey = null;
   private UUID _categoryKey = null;
   private String _name = "";
   private String _imageURL = "";

   
   public Brick(String elementID,
                String designID,
                UUID colorKey,
                UUID categoryKey,
                String name,
                String imageURL){
      this._elementID = elementID;
      this._designID = designID;
      this._colorKey = colorKey;
      this._categoryKey = categoryKey;
      this._name = name;
      this._imageURL = imageURL;
   }
   
   public Brick(CommonObject commonObject){
      Object[] object = commonObject.fetchObject();
      this._elementID = ConvertToString(object[0]);
      this._designID = ConvertToString(object[1]);
      this._colorKey = ConvertToUUID(object[2]);
      this._categoryKey = ConvertToUUID(object[3]);
      this._name = ConvertToString(object[4]);
      this._imageURL = ConvertToString(object[5]);
   }
   
   public String getElementID() {
      return _elementID;
   }

   public void setElementID(String elementID) {
      this._elementID = elementID
   }

   public String getDesignID() {
      return _designID;
   }

   public void setDesignID(String designID) {
      this._designID = designID
   }

   public UUID getColorKey() {
      return _colorKey;
   }

   public void setColorKey(UUID colorKey) {
      this._colorKey = colorKey
   }

   public UUID getCategoryKey() {
      return _categoryKey;
   }

   public void setCategoryKey(UUID categoryKey) {
      this._categoryKey = categoryKey
   }

   public String getName() {
      return _name;
   }

   public void setName(String name) {
      this._name = name
   }

   public String getImageURL() {
      return _imageURL;
   }

   public void setImageURL(String imageURL) {
      this._imageURL = imageURL
   }


   
   @Override
   public String fetchObjectName(){
      return "Brick";
   }
   
   @Override
   public String getPropertyValue(String fieldName) throws Exception{
      switch (fieldName){
      case "ElementID":
         return this._elementID.toString();
      case "DesignID":
         return this._designID.toString();
      case "ColorKey":
         return this._colorKey.toString();
      case "CategoryKey":
         return this._categoryKey.toString();
      case "Name":
         return this._name.toString();
      case "ImageURL":
         return this._imageURL.toString();

      default:
         throw new Exception(String.format("Unknown Field Name:[%s]", fieldName));
      }
   }
   
   @Override
   public void setValue(String fieldName, String fieldValue) throws Exception
   {
      switch (fieldName) {
      case "ElementID":
         this._elementID = fieldValue;
         break;
      case "DesignID":
         this._designID = fieldValue;
         break;
      case "ColorKey":
         this._colorKey = ConvertToUUID(fieldValue);
         break;
      case "CategoryKey":
         this._categoryKey = ConvertToUUID(fieldValue);
         break;
      case "Name":
         this._name = fieldValue;
         break;
      case "ImageURL":
         this._imageURL = fieldValue;
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
      modelMap.put("ElementID", new FieldModel("String", this._elementID, false));
      modelMap.put("DesignID", new FieldModel("String", this._designID, false));
      modelMap.put("ColorKey", new FieldModel("UUID", this._colorKey, false));
      modelMap.put("CategoryKey", new FieldModel("UUID", this._categoryKey, false));
      modelMap.put("Name", new FieldModel("String", this._name, false));
      modelMap.put("ImageURL", new FieldModel("String", this._imageURL, false));

      return modelMap;
   }
   
   @Override
   public Map<String, String> exportPropertyMap(){
      Map<String, String> modelMap = new HashMap<String, String>();
      modelMap.put("ElementID", getPropertyValueString(this._elementID));
      modelMap.put("DesignID", getPropertyValueString(this._designID));
      modelMap.put("ColorKey", getPropertyValueUUID(this._colorKey));
      modelMap.put("CategoryKey", getPropertyValueUUID(this._categoryKey));
      modelMap.put("Name", getPropertyValueString(this._name));
      modelMap.put("ImageURL", getPropertyValueString(this._imageURL));

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
      modelMap.put("ElementID", getPropertyValueString(this._elementID));
      modelMap.put("DesignID", getPropertyValueString(this._designID));
      modelMap.put("ColorKey", getPropertyValueUUID(this._colorKey));
      modelMap.put("CategoryKey", getPropertyValueUUID(this._categoryKey));
      modelMap.put("Name", getPropertyValueString(this._name));
      modelMap.put("ImageURL", getPropertyValueString(this._imageURL));

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
      Object[] obj = new Object[6];
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
