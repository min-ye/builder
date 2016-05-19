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
   private static String _quitIdent = "!";

   public static void main(String[] arg) throws IOException{
      try{
         String folder = Profile.INSTANCE.getConfigValue(getConfigFile(), "data_folder");
         _entityJsonFileName = String.format("%sentity.json", folder);
         initializeEntityList();
         int choice = -1;
         Map<Integer, String> option = new HashMap<Integer, String>();
         option.put(1, "Input");
         option.put(2, "Output");
         do {
            choice = choose(option);
            switch(choice){
            case 1:
               handleInput();
               break;
            case 2:
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
   
   private static void handleInput() throws Exception{
      IInvokeConsole iic = new IInvokeConsole(){
         public String read(String prompt) throws CancelInputException, IOException{
            return readLine(prompt);
         }
         
         public void write(String prompt){
            writeLine(prompt);
         }
         
         public void write(List<String> message) {
            writeLine(message);
         }
         
         public Integer choose(Map<Integer, String> option) {
            return choose(option);
         }
         
         public Integer chooseObject(Map<Integer, CommonObject> option) {
            return chooseObject(option);
         }
      };
      
      Integer choice = -1;
      String className = "";
      
      Map<Integer, String> option = new HashMap<Integer, String>();
      option.put(1, "Delete Entity");
      option.put(2, "Update Entity");
      option.put(3, "Create Entity");
      option.put(5, "Delete Property");
      option.put(6, "Update Property");
      option.put(7, "Create Property");
      option.put(9, "Save");
      
      do {
         choice = choose(option);
         
         switch(choice){
         case 1:{
            className = "DeleteEntityHandler";
            InputHandler handler = InputHandlerFactory.createHandler(DeleteEntityHandler.class);
            handler.run(_entityList, iic);
         } 
            break;
         case 2: {
            className = "UpdateEntityHandler";
            InputHandler handler = InputHandlerFactory.createHandler(UpdateEntityHandler.class);
            handler.run(_entityList, iic);
         }
            break;
         case 3: {
            className = "CreateEntityHandler";
            InputHandler handler = InputHandlerFactory.createHandler(CreateEntityHandler.class);
            handler.run(_entityList, iic);
         }
            break;
         case 5: {
            className = "DeleteFieldHandler";
            InputHandler handler = InputHandlerFactory.createHandler(DeleteFieldHandler.class);
            handler.run(_entityList, iic);
         }
            break;
         case 6: {
            className = "CreateEntityHandler";
            InputHandler handler = InputHandlerFactory.createHandler(CreateEntityHandler.class);
            handler.run(_entityList, iic);
         }
            break;
         case 7: {
            className = "CreateEntityHandler";
            InputHandler handler = InputHandlerFactory.createHandler(CreateEntityHandler.class);
            handler.run(_entityList, iic);
         }
            break;
         case 9:
            save();
            break;
         }
         
      } while (choice != 0);
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
      String input = "";
      if (ide){
         System.out.println(prompt);
         input = b.readLine();
         if (input.length() == 0){
            System.out.print("Are you want to exit? (Y/N):");
            input = b.readLine();
            if (b.toString() == "Y") {
               throw new CancelInputException();
            }
         }
      }
      else {
         input = c.readLine(prompt);
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
   
   private static int choose(Map<Integer, String> option) throws Exception{
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
         
         try{
            index = Integer.getInteger(result);
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
   
   private static int chooseObject(Map<Integer, CommonObject> option) throws Exception{
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
         
         try{
            index = Integer.getInteger(result);
            if (option.containsKey(result)) {
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
   
   private static void save() throws Exception{
      JsonHelper.INSTANCE.writeJson(_entityList, _entityJsonFileName);
   }
   
   private static String getConfigFile() throws Exception {
      InputStream url = Console.class.getResourceAsStream("/config.json");
      return IOUtils.toString(url);
   }
}
