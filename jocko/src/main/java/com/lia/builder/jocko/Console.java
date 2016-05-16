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

public class Console {
   private static java.io.Console c = System.console();
   private static BufferedReader b = new BufferedReader(new InputStreamReader(System.in));
   private static List<CommonObject> _entityList = null;
   private static List<CommonObject> _propertyList = null;
   private static String _entityJsonFileName = "";
   private static boolean ide = true;

   public static void main(String[] arg){
      
      try{
         String folder = Profile.INSTANCE.getConfigValue(getConfigFile(), "data_folder");
         _entityJsonFileName = String.format("%sentity.json", folder);
         initializeEntityList();
         String choice = "";
         while (!choice.equals("0")){
            writeConsole("1. Input;");
            writeConsole("2. Output:");
            writeConsole("0. Quit;");
            choice = readConsole("Choice:");
            switch(choice){
            case "1":
               handleInput();
               break;
            case "2":
               break;
            }
         }
      }
      catch (Exception ex){
         writeConsole(ex.getMessage());
      }
   }
   
   private static void handleInput() throws Exception{
      IInvokeConsole iic = new IInvokeConsole(){
         public String read(String prompt) throws IOException{
            return readConsole(prompt);
         }
         
         public void write(String prompt){
            writeConsole(prompt);
         }
      };
      
      String choice = "";
      String className = "";
      
      while (!choice.equals("0")){
         writeConsole("1. Delete Entity;");
         writeConsole("2. Update Entity;");
         writeConsole("3. Create Entity;");
         writeConsole("6. Update Property:");
         writeConsole("7. Create Property:");
         writeConsole("9. Save:");
         writeConsole("0. Quit;");
         choice = readConsole("Choice: ");
         
         switch(choice){
         case "1":{
            className = "DeleteEntityHandler";
            InputHandler handler = InputHandlerFactory.createHandler(DeleteEntityHandler.class);
            handler.run(_entityList, iic);
            break;
         }
         case "2": {
            className = "UpdateEntityHandler";
            InputHandler handler = InputHandlerFactory.createHandler(UpdateEntityHandler.class);
            handler.run(_entityList, iic);
            break;
         }
         case "3": {
            className = "CreateEntityHandler";
            InputHandler handler = InputHandlerFactory.createHandler(CreateEntityHandler.class);
            handler.run(_entityList, iic);
            break;
         }
         case "5":
            break;
         case "6":
            break;
         case "7":
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
   
   private static String readConsole(String prompt) throws IOException{
      if (ide){
         System.out.println(prompt);
         return b.readLine();
      }
      else {
         return c.readLine(prompt);
      }
   }
   
   private static void writeConsole(String message) {
      if (ide){
         System.out.println(message);
      }
      else {
         c.printf(message);
      }
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
