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
    
    @RequestMapping("/menuConfigHelper")
    public String menuConfigHelper(){
        return "/mainframe/helper/menuConfigHelper";
    }
    
    @RequestMapping("/authConfigHelper")
    public String authConfigHelper(){
        return "/mainframe/helper/authConfigHelper";
    }
    
    @RequestMapping("/serviceLogHelper")
    public String serviceLogHelper(){
        return "/mainframe/helper/serviceLogHelper";
    }
    
    @RequestMapping("/basicdataHelper")
    public String basicdataHelper(){
        return "/mainframe/helper/basicdataHelper";
    }
}
