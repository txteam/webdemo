/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2019年4月19日
 * <修改描述:>
 */
package com.tx.local.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 编码规范<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2019年4月19日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Controller
@RequestMapping("/standard")
public class StandardController {
    
    /**
     * 跳转到编码规范页面<br/>
     *<功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/coding/java")
    public String javaCodingStandard() {
        return "/example/standard/javacoding";
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
    @RequestMapping("/coding/database")
    public String databaseCodingStandard() {
        return "/mainframe/standard/databasecoding";
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
    public String projectNamedRule() {
        return "/mainframe/standard/projectNamedRule";
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
    public String pageCodeRule() {
        return "/mainframe/standard/pageCodeRule";
    }
}
