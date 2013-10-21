/*
 * 描          述:  <描述>
 * 修  改   人:  brady
 * 修改时间:  2013-10-21
 * <修改描述:>
 */
package com.tx.component.mainframe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


 /**
  * 规范控制器<br/>
  * <功能详细描述>
  * 
  * @author  brady
  * @version  [版本号, 2013-10-21]
  * @see  [相关类/方法]
  * @since  [产品/模块版本]
  */
@Controller("standardRuleController")
@RequestMapping("/standardRule")
public class StandardRuleController {
    
    /**
      * 跳转到编码规范页面<br/>
      *<功能详细描述>
      * @return [参数说明]
      * 
      * @return String [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/javaCodeRule")
    public String javaCodeRule(){
        return "/mainframe/rule/javaCodeRule";
    }
    
    /**
      * 跳转到数据库编码规范<br/>
      *<功能详细描述>
      * @return [参数说明]
      * 
      * @return String [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/databaseCodeRule")
    public String databaseCodeRule(){
        return "/mainframe/rule/databaseCodeRule";
    }
    
    /**
      * 跳转到项目命名规范<br/>
      *<功能详细描述>
      * @return [参数说明]
      * 
      * @return String [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/projectNamedRule")
    public String projectNamedRule(){
        return "/mainframe/rule/projectNamedRule";
    }
    
    /**
      * 页面编码规范
      *<功能详细描述>
      * @return [参数说明]
      * 
      * @return String [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/pageCodeRule")
    public String pageCodeRule(){
        return "/mainframe/rule/pageCodeRule";
    }
}
