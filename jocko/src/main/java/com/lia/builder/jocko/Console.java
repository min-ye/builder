package com.lia.builder.jocko;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;

import com.lia.common.CommonObject;
import com.lia.common.JsonHelper;
import com.lia.common.Profile;
import com.lia.common.exception.CancelInputException;

public class Console {
   private static java.io.Console c = System.console();
   private static BufferedReader b = new BufferedReader(new InputStreamReader(System.in));
   private static List<CommonObject> _entityList = null;
   private static List<CommonObject> _propertyList = null;
   private static String _entityJsonFileName = "";
   private static boolean ide = true;

   public static void main(String[] arg) throws IOException{
      try{
         String folder = Profile.INSTANCE.getConfigValue(getConfigFile(), "data_folder");
         _entityJsonFileName = String.format("%sentity.json", folder);
         initializeEntityList();
         String choice = "";
         while (!choice.equals("0")){
            writeLine("1. Input;");
            writeLine("2. Output:");
            writeLine("0. Quit;");
            choice = readLine("Choice:");
            switch(choice){
            case "1":
               handleInput();
               break;
            case "2":
               break;
            }
         }
      }
      catch (CancelInputException ex){
         System.exit(0);
      }
      catch (Exception ex){
         writeLine(ex.getMessage());
      }
   }
   
   private static void handleInput() throws Exception{
      IInvokeConsole iic = new IInvokeConsole(){
         public String read(String prompt) throws CancelInputException, IOException{
            return readLine(prompt);
         }
         
         public void write(String prompt){
            writeLine(prompt);
         }
      };
      
      String choice = "";
      String className = "";
      
      while (!choice.equals("0")){
         writeLine("1. Delete Entity;");
         writeLine("2. Update Entity;");
         writeLine("3. Create Entity;");
         writeLine("5. Delete Property:");
         writeLine("6. Update Property:");
         writeLine("7. Create Property:");
         writeLine("9. Save:");
         writeLine("0. Quit;");
         choice = readLine("Choice: ");
         
         switch(choice){
         case "1":{
            className = "DeleteEntityHandler";
            InputHandler handler = InputHandlerFactory.createHandler(DeleteEntityHandler.class);
            handler.run(_entityList, iic);
         } 
            break;
         case "2": {
            className = "UpdateEntityHandler";
            InputHandler handler = InputHandlerFactory.createHandler(UpdateEntityHandler.class);
            handler.run(_entityList, iic);
         }
            break;
         case "3": {
            className = "CreateEntityHandler";
            InputHandler handler = InputHandlerFactory.createHandler(CreateEntityHandler.class);
            handler.run(_entityList, iic);
         }
            break;
         case "5": {
            className = "DeleteFieldHandler";
            InputHandler handler = InputHandlerFactory.createHandler(DeleteFieldHandler.class);
            handler.run(_entityList, iic);
         }
            break;
         case "6": {
            className = "CreateEntityHandler";
            InputHandler handler = InputHandlerFactory.createHandler(CreateEntityHandler.class);
            handler.run(_entityList, iic);
         }
            break;
         case "7": {
            className = "CreateEntityHandler";
            InputHandler handler = InputHandlerFactory.createHandler(CreateEntityHandler.class);
            handler.run(_entityList, iic);
         }
            break;
         case "9":
            save();
            break;
         }
         
      }
   }

   private void handleOutput(){
      String choice = "0";
      while (!choice.equals("0")){
         c.printf("1. Entity;\n");
         c.printf("2. Property:\n");
         c.printf("0. Quit;\n");
         choice = c.readLine("Choice: \n");
         switch(choice){
         case "1":
            break;
         case "2":
            break;
         }
      }
   }
   
   private static String readLine(String prompt) throws IOException, CancelInputException{
      StringBuffer result = new StringBuffer("");
      int c;  
      c = System.in.read();  
      System.out.println(String.format("%d", c));
      boolean complete = false;
      while(!complete)  
      {
         switch (c){
         case 10:
            complete = true;
            break;
         case 27:
            throw new CancelInputException();
         default:
            result.append((char)c);
            c = System.in.read();
         }
      }
      return result.toString();
   }
   
   private static void writeLine(String message) {
      if (ide){
         System.out.println(message);
      }
      else {
         c.printf(message);
      }
   }
   
   private static String choose(Map<String, String> option) throws Exception{
      boolean choosed = false;
      String result = "";
      while (!choosed){
         for (Map.Entry<String, String> entry : option.entrySet()) {
            writeLine(String.format("%s: %s", entry.getKey(), entry.getValue()));
         }
         result = readLine("Please choose:");
         if (option.containsKey(result)) {
            choosed = true;
         }
      }
      return result;
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
   
   private static void save() throws Exception{
      JsonHelper.INSTANCE.writeJson(_entityList, _entityJsonFileName);
   }
   
   private static String getConfigFile() throws Exception {
      InputStream url = Console.class.getResourceAsStream("/config.json");
      return IOUtils.toString(url);
   }
}
