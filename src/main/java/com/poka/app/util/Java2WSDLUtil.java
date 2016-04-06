package com.poka.app.util;



import org.apache.cxf.tools.java2ws.JavaToWS;

import com.poka.app.cb.ws.ICBPospSW;


public class Java2WSDLUtil {  
    private Class<?> className;  
    public String[] args1,args2,args3;  
    /** 
     * 构造函数 
     * @param args 要生成WSDL的Java类 
     */  
    public Java2WSDLUtil(Class<?> className) {  
    	 this.className = className;   
         
         //根据Hello.class生成Hello.wsdl,生成的wsdl文件放在resource目录下  
         args1=new String[]{"-wsdl","-d","./resource",this.className.getName()};  
         //根据Hello.class生成Hello.wsdl，生成的文件放在根目录下的.example下  
         args2=new String[]{"-wsdl","-cp", "./example",this.className.getName()};  
         // 根据Hello.class生成wsdl文件，文件命名为myHello.wsdl，放在根目录下  
         args3=new String[]{"-o","myHello.wsdl","-wsdl",this.className.getName()};  
    }  
    /** 
     * 构造函数 
     * @param className 要生成WSDL的Java类 
     * @param wsdlFileName 要生成WSDL的文件名称（不包含扩展名） 
     */  
    public Java2WSDLUtil(Class<?> className,String wsdlFileName) {  
        this.className = className;   
          
        //根据Hello.class生成Hello.wsdl,生成的wsdl文件放在resource目录下  
        args1=new String[]{"-wsdl","-d","./resource",this.className.getName()};  
        //根据Hello.class生成Hello.wsdl，生成的文件放在根目录下的.example下  
        args2=new String[]{"-wsdl","-cp", "./example",this.className.getName()};  
        // 根据Hello.class生成wsdl文件，文件命名为myHello.wsdl，放在根目录下  
        args3=new String[]{"-o",wsdlFileName+".wsdl","-wsdl",this.className.getName()};  
    }  
  
    public void java2WSDL(String[] args){  
    	JavaToWS.main(args);
    }  
  
    public static void main(String[] args) {  
        Java2WSDLUtil java2WSDL = new Java2WSDLUtil(ICBPospSW.class);  
        java2WSDL.java2WSDL(java2WSDL.args1);  
    }  
}  
