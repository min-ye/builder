package com.lia.builder.jocko;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lia.common.CommonObject;
import com.lia.common.exception.CancelInputException;

public abstract class InputHandler extends CommonHandler{
   public abstract void run(List<CommonObject> objectList, IInvokeConsole c) throws Exception;
}
