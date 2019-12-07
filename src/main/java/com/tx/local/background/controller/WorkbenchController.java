/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2019年4月20日
 * <修改描述:>
 */
package com.tx.local.background.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 工作台<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2019年4月20日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Controller("background.WorkbenchController")
@RequestMapping("/background/workbench")
public class WorkbenchController {
    
    @RequestMapping(value = { "", "/", "/index", "/index.html" })
    public String index() {
        return "background/default";
    }
}
