/*
 * 描          述:  <描述>
 * 修  改   人:  PengQingyang
 * 修改时间:  2012-10-14
 * <修改描述:>
 */
package com.tx.webdemo.demo.wsservice;

import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;


 /**
  * <HelloWorld 接口>
  * <功能详细描述>
  * 
  * @author  PengQingyang
  * @version  [版本号, 2012-10-14]
  * @see  [相关类/方法]
  * @since  [产品/模块版本]
  */
@WebService(name = "HelloWorld", targetNamespace = "tx")
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT, use = SOAPBinding.Use.LITERAL, parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface HelloWorldService {
    
    /** <默认构造函数> */
    @WebMethod(operationName = "getHelloWorld")
    @WebResult(name = "executeXMLReturn", targetNamespace = "tx")
    public String getHelloWorld();
    
}
