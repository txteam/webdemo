/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2019年4月20日
 * <修改描述:>
 */
package com.tx.front4wap.mainframe.controller;

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
@Controller(value = "wap.client.workbenchController")
@RequestMapping("/wap/client/workbench")
public class WorkbenchController {
    
    /**
     * default页<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = { "", "/" })
    public String index() {
        return "mainframe/default";
    }
}
