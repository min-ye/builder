package com.lia.builder.jocko;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.lia.common.CommonObject;
import com.lia.common.exception.CancelInputException;

public interface IInvokeConsole {
   public String read(String prompt) throws IOException, CancelInputException;  
   
   public void write(String message) throws IOException;
   
   public Integer choose(Map<Integer, String> option);
   
   public Integer chooseObject(Map<Integer, CommonObject> option);
   
   public void write(List<String> message);
}
