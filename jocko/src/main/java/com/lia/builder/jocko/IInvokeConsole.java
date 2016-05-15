package com.lia.builder.jocko;

import java.io.IOException;

public interface IInvokeConsole {
   public String read(String prompt) throws IOException;  
   
   public void write(String message) throws IOException;
}
