/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2019年12月23日
 * <修改描述:>
 */
package com.tx.plugin.login.controller;

import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tx.local.operator.service.OperSocialAccountService;
import com.tx.plugin.login.LoginPluginConstants;
import com.tx.plugin.login.LoginPluginUtils;

/**
 * 微博登陆插件控制层<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2019年12月23日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Controller
@RequestMapping("/loginplugin/")
public class LoginPluginController {
    
    @Resource
    private OperSocialAccountService operSocialAccountService;
    
    /**
     * 由于新浪微博在回调逻辑中只允许配置一个回调链接<br/>
     *   这里需要通过同一个链接，实现不同的参数进入不同的逻辑<br/>
     *   能够支持： login,bind操作
     * <功能详细描述>
     * @param request [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/callback/{plugin}")
    public String callback(
            @PathVariable(value = "plugin", required = true) String plugin,
            @RequestParam(value = "state", required = false) String state,
            @RequestParam(value = "code", required = false) String code,
            HttpServletRequest request, ModelMap response) {
        if (StringUtils.isEmpty(code)) {
            response.put("result", false);
            response.put("msg", "获取code失败.");
            return "loginplugin/postSignIn";
        }
        
        String targetName = LoginPluginUtils.popTargetName();
        //根据targetName(目标名)决定后续逻辑
        switch (targetName) {
            case LoginPluginConstants.TARGET_NAME_BIND_4_OPERATOR: {
                return "forward:/operator/social/bind/" + plugin;
            }
            case LoginPluginConstants.TARGET_NAME_BIND_4_CLIENT: {
                return "forward:/client/social/bind/" + plugin;
            }
            case LoginPluginConstants.TARGET_NAME_LOGIN_4_OPERATOR: {
                return "redirect:/operator/social/login/" + plugin + "?code=" + code + "&state=" + state;
            }
            case LoginPluginConstants.TARGET_NAME_LOGIN_4_CLIENT:
            default: {
                return "forward:/client/social/login/" + plugin;
            }
        }
    }
    
    /**
     * 通过回调进行解绑<br/>
     * <功能详细描述>
     * @param request [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/unbind/{plugin}")
    public void unbind(
            @PathVariable(value = "plugin", required = true) String plugin,
            @RequestParam Map<String, String> request) {
        for (Entry<String, String> entry : request.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }
}
