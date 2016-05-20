package com.lia.builder.jocko;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.lia.common.CommonObject;
import com.lia.common.exception.CancelInputException;

public class BrowseHandler extends InputHandler{
   
   @Override
   public void run(List<CommonObject> input, IInvokeConsole c) throws Exception{
      Map<Integer, CommonObject> objectOption = buildCommonObjectOption(input);
      List<String> output = new ArrayList<String>();
      for (Entry<Integer, CommonObject> entry : objectOption.entrySet()) {
         output.add(String.format("%d: %s;", entry.getKey(), entry.getValue().fetchDescription()));
      }
      if (objectOption.size() == 0) {
         c.write("No record.");
      }
   }
}
