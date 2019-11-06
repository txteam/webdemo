/*
 * 描          述:  <描述>
 * 修  改   人:  PengQingyang
 * 修改时间:  2013-8-23
 * <修改描述:>
 */
package com.tx.local.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 门户视图逻辑控制层
 * <功能详细描述>
 * 
 * @author  PengQingyang
 * @version  [版本号, 2013-8-23]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Controller
@RequestMapping("/easyui/demo")
public class EasyUIDemoController {
    
    /**
     * 跳转到demo页
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/{page}")
    public String toDemoPage(
            @PathVariable(value = "page", required = true) String page) {
        String actualPage = "easyuidemo/" + page;
        return actualPage;
    }
    
}
