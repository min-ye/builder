package com.lia.builder.jocko;

import java.util.List;

import com.lia.common.CommonObject;

public abstract class InputHandler extends CommonHandler{
   public abstract void run(List<CommonObject> objectList, IInvokeConsole c) throws Exception;
}
