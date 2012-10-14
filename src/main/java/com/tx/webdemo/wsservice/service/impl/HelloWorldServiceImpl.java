/*
 * 描          述:  <描述>
 * 修  改   人:  PengQingyang
 * 修改时间:  2012-10-14
 * <修改描述:>
 */
package com.tx.webdemo.wsservice.service.impl;

import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.tx.webdemo.wsservice.service.HelloWorldService;


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
@WebService(name = "HelloWorld", targetNamespace = "tx", endpointInterface = "com.tx.webdemo.wsservice.service.HelloWorldService")
public class HelloWorldServiceImpl implements HelloWorldService {
    
    /* 是否需要引入日志记录，根据具体业务定，这里是为了打印启动加载情况才加入的  */
    private Logger logger = LoggerFactory.getLogger(HelloWorldServiceImpl.class);
    
    public HelloWorldServiceImpl(){
        logger.info("Instance HelloWorldServiceImpl............................");
    }
    
    /**
     * @return
     */
    @WebMethod(operationName = "getHelloWorld")
    @WebResult(name = "executeXMLReturn", targetNamespace = "tx")
    public String getHelloWorld() {
        return "hello world";
    }
    
}
