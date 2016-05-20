package com.lia.builder.jocko;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.lia.common.CommonObject;
import com.lia.common.exception.CancelInputException;

public interface IInvokeConsole {
   public String read(String prompt) throws Exception;  
   
   public void write(String message) throws Exception;
   
   public Integer chooseString(Map<Integer, String> option) throws Exception;
   
   public Integer chooseObject(Map<Integer, CommonObject> option) throws Exception;
   
   public void write(List<String> message) throws Exception;
}
