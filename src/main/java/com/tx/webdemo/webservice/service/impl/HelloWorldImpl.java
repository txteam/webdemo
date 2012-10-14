/*
 * 描          述:  <描述>
 * 修  改   人:  PengQingyang
 * 修改时间:  2012-10-14
 * <修改描述:>
 */
package com.tx.webdemo.webservice.service.impl;

import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;

import org.springframework.stereotype.Component;

import com.tx.webdemo.webservice.service.HelloWorld;


 /**
  * <功能简述>
  * <功能详细描述>
  * 
  * @author  PengQingyang
  * @version  [版本号, 2012-10-14]
  * @see  [相关类/方法]
  * @since  [产品/模块版本]
  */
@Component("helloWorld")
@WebService(name = "HelloWorld", targetNamespace = "tx", endpointInterface = "com.tx.webdemo.webservice.service.HelloWorld")
public class HelloWorldImpl implements HelloWorld {
    
    /**
     * @return
     */
    @WebMethod(operationName = "getHelloWorld")
    @WebResult(name = "executeXMLReturn", targetNamespace = "tx")
    public String getHelloWorld() {
        return "hello world";
    }
    
}
