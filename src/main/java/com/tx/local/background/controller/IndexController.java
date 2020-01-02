/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2018年7月7日
 * <修改描述:>
 */
package com.tx.local.background.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tx.component.plugin.context.PluginContext;
import com.tx.local.security.SecurityConstants;
import com.tx.local.security.util.WebContextUtils;
import com.tx.plugin.login.github.GHLoginPlugin;
import com.tx.plugin.login.weibo.WBLoginPlugin;

/**
 * 主框架页面逻辑层<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2018年7月7日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Controller("background.IndexController")
@RequestMapping(value = "/background")
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
    public String index() {
        WebContextUtils.getSession().setAttribute(
                SecurityConstants.ACCESS_DOMAIN_KEY,
                SecurityConstants.ACCESS_DOMAIN_OPERATOR);
        return "redirect:/background/mainframe";
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
    public String toLogin(HttpServletRequest request, ModelMap response) {
        boolean error = false;
        HttpSession session = request.getSession(false);
        if (session != null) {
            Exception e = (Exception) session
                    .getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
            if (e != null) {
                error = true;
                response.put("code", -1);
                response.put("message", e.getMessage());
            }
        }
        
        if (!error) {
            response.put("code", 0);
            response.put("message", "");
        }
        if (PluginContext.getContext()
                .getConfig(WBLoginPlugin.class)
                .isEnable()) {
            response.put("wbLoginEnable", true);
        }
        if (PluginContext.getContext()
                .getConfig(GHLoginPlugin.class)
                .isEnable()) {
            response.put("ghLoginEnable", true);
        }
        //        if (false) {
        //            response.put("wxLoginEnable", false);
        //            response.put("qqLoginEnable", false);
        //        }
        response.put("registEnable", false);
        return "background/login";
    }
}
