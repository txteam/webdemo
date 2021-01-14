/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2018年7月7日
 * <修改描述:>
 */
package com.tx.local.mainframe.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tx.local.menu.context.MenuContext;
import com.tx.local.security.SecurityConstants;
import com.tx.local.security.util.AuthenticationUtils;
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
@Controller("local.MainframeController")
@RequestMapping("")
public class MainframeController {
    
    @Resource(name = "menuContext")
    private MenuContext menuContext;
    
    /**
     * 跳转到主页面中<br/> 
     * <功能详细描述>
     * @param model
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = { "/mainframe" })
    public String toMainframe(Model model) {
        WebContextUtils.getSession()
                .setAttribute(SecurityConstants.ACCESS_DOMAIN_KEY,
                        SecurityConstants.ACCESS_DOMAIN_OPERATOR);
        
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        model.addAttribute("now", df.format(new Date()));
        model.addAttribute("username",
                WebContextUtils.getOperator().getUsername());
        
        return "mainframe/mainframe";
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
                if (e instanceof AuthenticationException) {
                    error = true;
                    response.put("code", -1);
                    response.put("message",
                            AuthenticationUtils.loginErrorMessage(
                                    (AuthenticationException) e));
                } else {
                    error = true;
                    response.put("code", -1);
                    response.put("message", e.getMessage());
                }
                session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
            }
        }
        
        if (!error) {
            response.put("code", 0);
            response.put("message", "");
        }
        //        if (PluginContext.getContext()
        //                .getConfig(WBLoginPlugin.class)
        //                .isEnable()) {
        //            response.put("wbLoginEnable", true);
        //        }
        //        if (PluginContext.getContext()
        //                .getConfig(GHLoginPlugin.class)
        //                .isEnable()) {
        //            response.put("ghLoginEnable", true);
        //        }
        //        if (false) {
        //            response.put("wxLoginEnable", false);
        //            response.put("qqLoginEnable", false);
        //        }
        response.put("registEnable", false);
        return "mainframe/login";
    }
}
