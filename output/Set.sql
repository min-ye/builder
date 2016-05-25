package <PackageName>;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.lia.common.CommonObject;
import com.lia.common.mysql.FieldModel;

public class Set extends CommonObject {
   <PrivateVariable>
   
   public Set(<ConstructArgument>){
      <ConstructArgumentAssignment>
   }
   
   public Set(CommonObject commonObject){
      Object[] object = commonObject.fetchObject();
      <ConstructObjectArgumentAssignment>
   }
   
   <Property>
   
   @Override
   public String fetchObjectName(){
      return "Set";
   }
   
   @Override
   public String getPropertyValue(String fieldName) throws Exception{
      switch (fieldName){
      <GetPropertyValue>
      default:
         throw new Exception(String.format("Unknown Field Name:[%s]", fieldName));
      }
   }
   
   @Override
   public void setValue(String fieldName, String fieldValue) throws Exception
   {
      switch (fieldName) {
      <SetPropertyValue>
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
      <ExportModel>
      return modelMap;
   }
   
   @Override
   public Map<String, String> exportPropertyMap(){
      Map<String, String> modelMap = new HashMap<String, String>();
      <ExportPropertyMap>
      return modelMap;
   }
   
   @Override
   public Map<String, String> exportKeyPropertyMap(){
      Map<String, String> modelMap = new HashMap<String, String>();
      <ExportKeyPropertyMap>
      return modelMap;
   }
   
   @Override
   public Map<String, String> exportValuePropertyMap(){
      Map<String, String> modelMap = new HashMap<String, String>();
      <ExportValuePropertyMap>
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
      Object[] obj = new Object[<PropertyCount>];
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
