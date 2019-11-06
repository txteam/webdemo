/*
 * 描          述:  <描述>
 * 修  改   人:  brady
 * 修改时间:  2013-10-21
 * <修改描述:>
 */
package com.tx.local.documentation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


 /**
  * 帮助显示业务逻辑层
  * <功能详细描述>
  * 
  * @author  brady
  * @version  [版本号, 2013-10-21]
  * @see  [相关类/方法]
  * @since  [产品/模块版本]
  */
@Controller("helperController")
@RequestMapping("/helper")
public class HelperController {
    
    /**
     * 跳转到菜单配置帮助<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/menuConfigHelper")
    public String menuConfigHelper(){
        return "helper/menuConfigHelper";
    }
    
    /**
     * 跳转到权限配置帮助<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/authConfigHelper")
    public String authConfigHelper(){
        return "helper/authConfigHelper";
    }
    
    /**
     * 跳转到业务日志帮助<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/serviceLogHelper")
    public String serviceLogHelper(){
        return "helper/serviceLogHelper";
    }
    
    /**
     * 跳转到基础数据帮助<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/basicdataHelper")
    public String basicdataHelper(){
        return "helper/basicdataHelper";
    }
}
