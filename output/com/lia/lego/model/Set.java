package com.lia.lego.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.lia.common.CommonObject;
import com.lia.common.mysql.FieldModel;

public class Set extends CommonObject {
   private String _number = "";
   private Int _variant = 0;
   private UUID _subThemeKey = null;
   private Short _year = 0;
   private String _name = "";
   private Short _minifigs = 0;
   private Int _piece = 0;
   private Float _priceUK = 0;
   private Float _priceUS = 0;
   private Float _priceCA = 0;
   private Float _priceEU = 0;
   private String _imageURL = "";
   private UUID _key = null;

   
   public Set(String number,
              Int variant,
              UUID subThemeKey,
              Short year,
              String name,
              Short minifigs,
              Int piece,
              Float priceUK,
              Float priceUS,
              Float priceCA,
              Float priceEU,
              String imageURL,
              UUID key){
      this._number = number;
      this._variant = variant;
      this._subThemeKey = subThemeKey;
      this._year = year;
      this._name = name;
      this._minifigs = minifigs;
      this._piece = piece;
      this._priceUK = priceUK;
      this._priceUS = priceUS;
      this._priceCA = priceCA;
      this._priceEU = priceEU;
      this._imageURL = imageURL;
      this._key = key;
   }
   
   public Set(CommonObject commonObject){
      Object[] object = commonObject.fetchObject();
      this._number = ConvertToString(object[0]);
      this._variant = ConvertToInt(object[1]);
      this._subThemeKey = ConvertToUUID(object[2]);
      this._year = ConvertToShort(object[3]);
      this._name = ConvertToString(object[4]);
      this._minifigs = ConvertToShort(object[5]);
      this._piece = ConvertToInt(object[6]);
      this._priceUK = ConvertToFloat(object[7]);
      this._priceUS = ConvertToFloat(object[8]);
      this._priceCA = ConvertToFloat(object[9]);
      this._priceEU = ConvertToFloat(object[10]);
      this._imageURL = ConvertToString(object[11]);
      this._key = ConvertToUUID(object[12]);
   }
   
   public String getNumber() {
      return _number;
   }

   public void setNumber(String number) {
      this._number = number
   }

   public Int getVariant() {
      return _variant;
   }

   public void setVariant(Int variant) {
      this._variant = variant
   }

   public UUID getSubThemeKey() {
      return _subThemeKey;
   }

   public void setSubThemeKey(UUID subThemeKey) {
      this._subThemeKey = subThemeKey
   }

   public Short getYear() {
      return _year;
   }

   public void setYear(Short year) {
      this._year = year
   }

   public String getName() {
      return _name;
   }

   public void setName(String name) {
      this._name = name
   }

   public Short getMinifigs() {
      return _minifigs;
   }

   public void setMinifigs(Short minifigs) {
      this._minifigs = minifigs
   }

   public Int getPiece() {
      return _piece;
   }

   public void setPiece(Int piece) {
      this._piece = piece
   }

   public Float getPriceUK() {
      return _priceUK;
   }

   public void setPriceUK(Float priceUK) {
      this._priceUK = priceUK
   }

   public Float getPriceUS() {
      return _priceUS;
   }

   public void setPriceUS(Float priceUS) {
      this._priceUS = priceUS
   }

   public Float getPriceCA() {
      return _priceCA;
   }

   public void setPriceCA(Float priceCA) {
      this._priceCA = priceCA
   }

   public Float getPriceEU() {
      return _priceEU;
   }

   public void setPriceEU(Float priceEU) {
      this._priceEU = priceEU
   }

   public String getImageURL() {
      return _imageURL;
   }

   public void setImageURL(String imageURL) {
      this._imageURL = imageURL
   }

   public UUID getKey() {
      return _key;
   }

   public void setKey(UUID key) {
      this._key = key
   }


   
   @Override
   public String fetchObjectName(){
      return "Set";
   }
   
   @Override
   public String getPropertyValue(String fieldName) throws Exception{
      switch (fieldName){
      case "Number":
         return this._number.toString();
      case "Variant":
         return this._variant.toString();
      case "SubThemeKey":
         return this._subThemeKey.toString();
      case "Year":
         return this._year.toString();
      case "Name":
         return this._name.toString();
      case "Minifigs":
         return this._minifigs.toString();
      case "Piece":
         return this._piece.toString();
      case "PriceUK":
         return this._priceUK.toString();
      case "PriceUS":
         return this._priceUS.toString();
      case "PriceCA":
         return this._priceCA.toString();
      case "PriceEU":
         return this._priceEU.toString();
      case "ImageURL":
         return this._imageURL.toString();
      case "Key":
         return this._key.toString();

      default:
         throw new Exception(String.format("Unknown Field Name:[%s]", fieldName));
      }
   }
   
   @Override
   public void setValue(String fieldName, String fieldValue) throws Exception
   {
      switch (fieldName) {
      case "Number":
         this._number = fieldValue;
         break;
      case "Variant":
         this._variant = ConvertToInt(fieldValue);
         break;
      case "SubThemeKey":
         this._subThemeKey = ConvertToUUID(fieldValue);
         break;
      case "Year":
         this._year = ConvertToShort(fieldValue);
         break;
      case "Name":
         this._name = fieldValue;
         break;
      case "Minifigs":
         this._minifigs = ConvertToShort(fieldValue);
         break;
      case "Piece":
         this._piece = ConvertToInt(fieldValue);
         break;
      case "PriceUK":
         this._priceUK = ConvertToFloat(fieldValue);
         break;
      case "PriceUS":
         this._priceUS = ConvertToFloat(fieldValue);
         break;
      case "PriceCA":
         this._priceCA = ConvertToFloat(fieldValue);
         break;
      case "PriceEU":
         this._priceEU = ConvertToFloat(fieldValue);
         break;
      case "ImageURL":
         this._imageURL = fieldValue;
         break;
      case "Key":
         this._key = ConvertToUUID(fieldValue);
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
      modelMap.put("Number", new FieldModel("String", this._number, false));
      modelMap.put("Variant", new FieldModel("Int", this._variant, false));
      modelMap.put("SubThemeKey", new FieldModel("UUID", this._subThemeKey, false));
      modelMap.put("Year", new FieldModel("Short", this._year, false));
      modelMap.put("Name", new FieldModel("String", this._name, false));
      modelMap.put("Minifigs", new FieldModel("Short", this._minifigs, false));
      modelMap.put("Piece", new FieldModel("Int", this._piece, false));
      modelMap.put("PriceUK", new FieldModel("Float", this._priceUK, false));
      modelMap.put("PriceUS", new FieldModel("Float", this._priceUS, false));
      modelMap.put("PriceCA", new FieldModel("Float", this._priceCA, false));
      modelMap.put("PriceEU", new FieldModel("Float", this._priceEU, false));
      modelMap.put("ImageURL", new FieldModel("String", this._imageURL, false));
      modelMap.put("Key", new FieldModel("UUID", this._key, true));

      return modelMap;
   }
   
   @Override
   public Map<String, String> exportPropertyMap(){
      Map<String, String> modelMap = new HashMap<String, String>();
      modelMap.put("Number", getPropertyValueString(this._number));
      modelMap.put("Variant", getPropertyValueInt(this._variant));
      modelMap.put("SubThemeKey", getPropertyValueUUID(this._subThemeKey));
      modelMap.put("Year", getPropertyValueShort(this._year));
      modelMap.put("Name", getPropertyValueString(this._name));
      modelMap.put("Minifigs", getPropertyValueShort(this._minifigs));
      modelMap.put("Piece", getPropertyValueInt(this._piece));
      modelMap.put("PriceUK", getPropertyValueFloat(this._priceUK));
      modelMap.put("PriceUS", getPropertyValueFloat(this._priceUS));
      modelMap.put("PriceCA", getPropertyValueFloat(this._priceCA));
      modelMap.put("PriceEU", getPropertyValueFloat(this._priceEU));
      modelMap.put("ImageURL", getPropertyValueString(this._imageURL));
      modelMap.put("Key", getPropertyValueUUID(this._key));

      return modelMap;
   }
   
   @Override
   public Map<String, String> exportKeyPropertyMap(){
      Map<String, String> modelMap = new HashMap<String, String>();
      modelMap.put("Key", getPropertyValueUUID(this._key));

      return modelMap;
   }
   
   @Override
   public Map<String, String> exportValuePropertyMap(){
      Map<String, String> modelMap = new HashMap<String, String>();
      modelMap.put("Number", getPropertyValueString(this._number));
      modelMap.put("Variant", getPropertyValueInt(this._variant));
      modelMap.put("SubThemeKey", getPropertyValueUUID(this._subThemeKey));
      modelMap.put("Year", getPropertyValueShort(this._year));
      modelMap.put("Name", getPropertyValueString(this._name));
      modelMap.put("Minifigs", getPropertyValueShort(this._minifigs));
      modelMap.put("Piece", getPropertyValueInt(this._piece));
      modelMap.put("PriceUK", getPropertyValueFloat(this._priceUK));
      modelMap.put("PriceUS", getPropertyValueFloat(this._priceUS));
      modelMap.put("PriceCA", getPropertyValueFloat(this._priceCA));
      modelMap.put("PriceEU", getPropertyValueFloat(this._priceEU));
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
      Object[] obj = new Object[13];
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
