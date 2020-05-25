/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2018年7月7日
 * <修改描述:>
 */
package com.tx.client.mainframe.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tx.local.security.SecurityConstants;
import com.tx.local.security.util.WebContextUtils;

/**
 * 主框架页面逻辑层<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2018年7月7日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Controller("client.indexController")
@RequestMapping(value = "/client")
public class IndexController {
    
    /**
     * 跳转到主页<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = { "", "/", "/index", "/index.html" })
    public String index(HttpSession session) {
        WebContextUtils.getSession().setAttribute(
                SecurityConstants.ACCESS_DOMAIN_KEY,
                SecurityConstants.ACCESS_DOMAIN_DEFAULT);
        return "redirect:/client/mainframe";
    }
    
    /**
     * 跳转到登录页面<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
    @RequestMapping("/login")
    public String toLogin() {
        return "mainframe/login";
    }
}
