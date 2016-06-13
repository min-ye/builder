package com.lia.builder.jocko;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.io.IOUtils;

import com.lia.common.CommonObject;
import com.lia.common.FileHelper;
import com.lia.common.JsonHelper;
import com.lia.common.Profile;
import com.lia.common.exception.CancelInputException;

public class Console {
   private static java.io.Console c = System.console();
   private static BufferedReader b = new BufferedReader(new InputStreamReader(System.in));
   private static List<CommonObject> _entityList = null;
   private static List<CommonObject> _fieldList = null;
   private static String _entityJsonFileName = "";
   private static boolean ide = true;
   private static String _quitIdent = " ";
   private static CommonObject _entity = null;
   
   private static IInvokeConsole _iic = new IInvokeConsole(){
      public String read(String prompt) throws Exception{
         return readLine(prompt);
      }
      
      public void write(String prompt) throws Exception{
         writeLine(prompt);
      }
      
      public void write(List<String> message) throws Exception{
         writeLine(message);
      }
      
      public Integer chooseString(Map<Integer, String> option) throws Exception {
         return readStringChoose(option);
      }
      
      public Integer chooseObject(Map<Integer, CommonObject> option) throws Exception {
         return readObjectChoose(option);
      }
   };

   public static void main(String[] arg) throws IOException{
      try{
         String folder = Profile.INSTANCE.getConfigValue(getConfigFile(), "data_folder");
         _entityJsonFileName = String.format("%sentity.json", folder);
         initializeEntityList();
         int choice = -1;
         Map<Integer, String> option = new HashMap<Integer, String>();
         option.put(1, "Entity");
         option.put(2, "Field");
         option.put(3, "Output");
         do {
            try {
               choice = readStringChoose(option);
            }
            catch (CancelInputException ex) {
               choice = 0;
            }
            switch(choice){
            case 1:
               handleEntity();
               break;
            case 2:
               handleField();
               break;
            case 3:
               handleOutput();
               break;
            }
         } while (choice != 0);
      }
      catch (CancelInputException ex){
         System.exit(0);
      }
      catch (Exception ex){
         writeLine(ex.getMessage());
      }
   }
   
   private static void handleEntity() throws Exception{
      Integer choice = 0;
      
      Map<Integer, String> option = new HashMap<Integer, String>();
      
      option.put(2, "Browse Entity");
      option.put(3, "Create Entity");
      option.put(5, "Update Entity");
      option.put(7, "Delete Entity");
      option.put(9, "Save");
      
      do {
         try {
         choice = readStringChoose(option);
         }
         catch (CancelInputException ex) {
            choice = 0;
         }
         
         switch(choice){
         case 7:{
            InputHandler handler = InputHandlerFactory.createHandler(DeleteHandler.class);
            handler.run(_entityList, _iic);
         } 
            break;
         case 5: {
            InputHandler handler = InputHandlerFactory.createHandler(UpdateHandler.class);
            handler.run(_entityList, _iic);
         }
            break;
         case 3: {
            InputHandler handler = InputHandlerFactory.createHandler(CreateEntityHandler.class);
            handler.run(_entityList, _iic);
         }
            break;
         case 2: {            
            InputHandler handler = InputHandlerFactory.createHandler(BrowseHandler.class);
            handler.run(_entityList, _iic);
         }
            break;
         case 9:
            saveEntity();
            break;
         }
      } while (choice != 0);
   }
   
   private static void handleField() throws Exception{
      Integer choice = 0;
      
      Map<Integer, String> option = new HashMap<Integer, String>();
      
      option.put(1, "Select Entity");
      option.put(2, "Browse Property");
      option.put(3, "Create Property");
      option.put(5, "Update Property");
      option.put(7, "Delete Property");
      option.put(9, "Save");
      
      do {
         try {
            choice = readStringChoose(option);
         }
         catch (CancelInputException ex) {
            choice = 0;
         }
         
         switch(choice){
         case 1:{
            initializeFieldList();
         } 
            break;
         case 2: {
            if (_entity == null) {
               initializeFieldList();
            }
            InputHandler handler = InputHandlerFactory.createHandler(BrowseHandler.class);
            handler.run(_fieldList, _iic);
         }
            break;
         case 3: {
            if (_entity == null) {
               initializeFieldList();
            }
            InputHandler handler = InputHandlerFactory.createHandler(CreateFieldHandler.class);
            handler.run(_fieldList, _iic);
         }
            break;
         case 5: {
            if (_entity == null) {
               initializeFieldList();
            }
            InputHandler handler = InputHandlerFactory.createHandler(UpdateHandler.class);
            handler.run(_fieldList, _iic);
         }
            break;
         case 7: {
            if (_entity == null) {
               initializeFieldList();
            }
            InputHandler handler = InputHandlerFactory.createHandler(DeleteHandler.class);
            handler.run(_fieldList, _iic);
         }
            break;
         case 9:
            if (_entity != null) {
               saveField();
            }
            break;
         }
      } while (choice != 0);
   }
   
   private static void handleOutput() throws Exception{
      initializeFieldList();
      OutputHandler handler = OutputHandlerFactory.createHandler(CreateModelClassHandler.class);
      String script = handler.run(_entity, _fieldList);
      String fileName = getModelOutputFileName(_entity);
      FileHelper.INSTANCE.saveContent(script, fileName);
      handler = OutputHandlerFactory.createHandler(CreateMySQLTableHandler.class);
      script = handler.run(_entity, _fieldList);
      fileName = getMySQLTableOutputFileName(_entity);
      FileHelper.INSTANCE.saveContent(script, fileName);
      handler = OutputHandlerFactory.createHandler(CreateHibernateHandler.class);
      script = handler.run(_entity, _fieldList);
      fileName = getHibernateOutputFileName(_entity);
      FileHelper.INSTANCE.saveContent(script, fileName);
   }
   
   private static String readLine(String prompt) throws IOException, CancelInputException{
      String input = "";
      if (ide){
         System.out.println(prompt);
         input = b.readLine();
         /*if (input.length() == 0){
            System.out.print("Are you want to exit? (Y/N):");
            input = b.readLine();
            if (input.toString().equals("Y")) {
               throw new CancelInputException();
            }
            else {
               input = "";
            }
         }*/
      }
      else {
         input = c.readLine(prompt);
         /*if (input.length() == 0){
            System.out.print("Are you want to exit? (Y/N):");
            input = b.readLine();
            if (input.toString().toUpperCase().equals("Y")) {
               throw new CancelInputException();
            }
            else {
               input = "";
            }
         }*/
      }
      return input;
   }
   
   private static void writeLine(String message) {
      if (ide){
         System.out.println(message);
      }
      else {
         c.printf(message);
      }
   }
   
   private static void writeLine(List<String> message) {
      if (ide){
         System.out.println("--------------------------------------------------");
      }
      else {
         c.printf("------------------------------------------------");
      }
      for (String m : message) {
         if (ide){
            System.out.println(m);
         }
         else {
            c.printf(m);
         }
      }
   }
   
   private static int readStringChoose(Map<Integer, String> option) throws Exception{
      writeLine("--------------------------------------------------");
      boolean choosed = false;
      String result = "";
      Integer index = -1;
      while (!choosed){
         for (Map.Entry<Integer, String> entry : option.entrySet()) {
            writeLine(String.format("%d: %s;", entry.getKey(), entry.getValue()));
         }
         writeLine(String.format("%s: quit;", _quitIdent));
         writeLine("--------------------------------------------------");
         result = readLine("Please choose:");
         
         if (result.equals(" ")) {
            throw new CancelInputException();
         }
         try{
               index = Integer.parseInt(result);
               if (option.containsKey(index)) {
                  choosed = true;
               }
            
         }
         catch (Exception ex) {
            writeLine(ex.getMessage());
            choosed = false;
         }
      }
      return index;
   }
   
   private static int readObjectChoose(Map<Integer, CommonObject> option) throws Exception{
      writeLine("--------------------------------------------------");
      boolean choosed = false;
      String result = "";
      Integer index = -1;
      while (!choosed){
         for (Entry<Integer, CommonObject> entry : option.entrySet()) {
            writeLine(String.format("%d: %s;", entry.getKey(), entry.getValue().fetchDescription()));
         }
         writeLine(String.format("%s: quit;", _quitIdent));
         writeLine("--------------------------------------------------");
         result = readLine("Please choose:");
         if (result.equals(" ")) {
            throw new CancelInputException();
         }
         try{
               index = Integer.parseInt(result);
               if (option.containsKey(index)) {
                  choosed = true;
               }
            
         }
         catch (Exception ex) {
            writeLine(ex.getMessage());
            choosed = false;
         }
      }
      return index;
   }
   
   private static void initializeEntityList() {
      if (_entityList == null) {
         
         _entityList = new ArrayList<CommonObject>();
         File file = new File(_entityJsonFileName);
         if (file.exists()) {
            for (Map<String, String> entity : JsonHelper.INSTANCE.readJson(_entityJsonFileName)){
               _entityList.add(new Entity(entity));
            }
         }
      }
   }
   
   private static void initializeFieldList() throws Exception {
      SelectHandler handler = SelectHandlerFactory.createHandler(SelectHandler.class);
      _entity = handler.select(_entityList, _iic);
      
      _fieldList = new ArrayList<CommonObject>();
      String folder = Profile.INSTANCE.getConfigValue(getConfigFile(), "data_folder");
      
      String fieldJsonFileName = String.format("%s%s.json", folder, _entity.getPropertyValue("Key"));
      File file = new File(fieldJsonFileName);
      if (file.exists()) {
         
         for (Map<String, String> field : JsonHelper.INSTANCE.readJson(fieldJsonFileName)){
            _fieldList.add(new Field(field));
         }
      }
   }
   
   private static void saveEntity() throws Exception{
      JsonHelper.INSTANCE.writeJson(_entityList, _entityJsonFileName);
   }
   
   private static void saveField() throws Exception{
      String folder = Profile.INSTANCE.getConfigValue(getConfigFile(), "data_folder");
      
      String fieldJsonFileName = String.format("%s%s.json", folder, _entity.getPropertyValue("Key"));
      JsonHelper.INSTANCE.writeJson(_fieldList, fieldJsonFileName);
   }
   
   private static String getConfigFile() throws Exception {
      InputStream url = Console.class.getResourceAsStream("/config.json");
      return IOUtils.toString(url);
   }
   
   private static String getModelOutputFileName(CommonObject entity) throws Exception {
      String folder = Profile.INSTANCE.getConfigValue(getConfigFile(), "output_folder");
      Entity entityObject = (Entity) entity;
      String packageFolder = entityObject.getPackageName();
      packageFolder = packageFolder.replace(".", "/");
      String output = String.format("%s%s/%s.java", folder, packageFolder, entityObject.getClassName());
      return output;
   }
   
   private static String getMySQLTableOutputFileName(CommonObject entity) throws Exception {
      String folder = Profile.INSTANCE.getConfigValue(getConfigFile(), "output_folder");
      Entity entityObject = (Entity) entity;
      String output = String.format("%sSQLTable/%s.sql", folder, entityObject.getTableName());
      return output;
   }
   
   private static String getHibernateOutputFileName(CommonObject entity) throws Exception {
      String folder = Profile.INSTANCE.getConfigValue(getConfigFile(), "output_folder");
      Entity entityObject = (Entity) entity;
      String output = String.format("%s%s/%s.hbm.xml", folder, "hibernate", entityObject.getTableName());
      return output;
   }
}
