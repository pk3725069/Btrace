package com.trace;

import static com.sun.btrace.BTraceUtils.println;  
import static com.sun.btrace.BTraceUtils.str;  
import static com.sun.btrace.BTraceUtils.strcat;  
import static com.sun.btrace.BTraceUtils.timeMillis;  
  
import com.sun.btrace.annotations.BTrace;  
import com.sun.btrace.annotations.Kind;  
import com.sun.btrace.annotations.Location;  
import com.sun.btrace.annotations.OnMethod;  
import com.sun.btrace.annotations.ProbeClassName;  
import com.sun.btrace.annotations.ProbeMethodName;  
import com.sun.btrace.annotations.TLS; 
//这个类是btrace脚本
@BTrace  
public class TraceHelloWorld {  
      
    @TLS  
    private static long startTime = 0;  
      //中clazz标明要监控那个类，也可以用正则匹配的方式，method标明要监控类的哪个方法
    @OnMethod(clazz = "com.Btrace.test.MainSleep", method = "Sleepfun")  
    public static void startMethod(){  
        startTime = timeMillis();  
    }  
      
    @OnMethod(clazz = "com.Btrace.test.MainSleep", method = "Sleepfun", location = @Location(Kind.RETURN))  
    public static void endMethod(){  
        println(strcat("the class method execute time=>", str(timeMillis()-startTime)));  
        println("-------------------------------------------");  
    }  
      
    @OnMethod(clazz = "com.Btrace.test.MainSleep", method = "Sleepfun", location = @Location(Kind.RETURN))  
    public static void traceExecute(@ProbeClassName String name,@ProbeMethodName String method,int sleepTime){
    	 // int sleepTime 为传入的参数
        println(strcat("the class name=>", name));  
        println(strcat("the class method=>", method));  
        println(strcat("the class method params=>", str(sleepTime)));  
          
    }  
}
