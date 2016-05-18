package com.lia.builder.jocko;

import java.io.IOException;

import com.lia.common.exception.CancelInputException;

public interface IInvokeConsole {
   public String read(String prompt) throws IOException, CancelInputException;  
   
   public void write(String message) throws IOException;
}
