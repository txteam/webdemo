///*
// * 描          述:  <描述>
// * 修  改   人:  Administrator
// * 修改时间:  2019年12月25日
// * <修改描述:>
// */
//package com.tx.local.security.controller;
//
//import javax.annotation.Resource;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;
//
//import org.apache.commons.lang3.StringUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.security.web.WebAttributes;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.ModelMap;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.servlet.ModelAndView;
//
//import com.tx.core.exceptions.util.AssertUtils;
//import com.tx.core.remote.RemoteConstants;
//import com.tx.core.remote.RemoteResult;
//import com.tx.core.util.MessageUtils;
//import com.tx.local.operator.model.OperSocialAccount;
//import com.tx.local.operator.model.OperSocialAccountTypeEnum;
//import com.tx.local.operator.service.OperSocialAccountService;
//import com.tx.local.security.util.WebContextUtils;
//import com.tx.plugin.login.LoginPlugin;
//import com.tx.plugin.login.LoginPluginConstants;
//import com.tx.plugin.login.LoginPluginUtils;
//import com.tx.plugin.login.exception.SocialAuthorizeException;
//import com.tx.plugin.login.model.LoginAccessToken;
//import com.tx.plugin.login.model.LoginUserInfo;
//
///**
// * 操作人员第三方用户登陆
// * <功能详细描述>
// * 
// * @author  Administrator
// * @version  [版本号, 2019年12月25日]
// * @see  [相关类/方法]
// * @since  [产品/模块版本]
// */
//@Controller
//@RequestMapping("/operator/social/")
//public class OperatorSocialLoginController {
//    
//    /** 日志记录句柄 */
//    protected final Logger logger = LoggerFactory.getLogger(getClass());
//    
//    /** 第三方用户账户业务层 */
//    @Resource
//    private OperSocialAccountService operSocialAccountService;
//    
//    /**
//     * 请求编码forbind<br/>
//     * <功能详细描述>
//     * @param type
//     * @param request
//     * @param response
//     * @return [参数说明]
//     * 
//     * @return ModelAndView [返回类型说明]
//     * @exception throws [异常类型] [异常说明]
//     * @see [类、类#方法、类#成员]
//     */
//    @RequestMapping("/toBind/{plugin}")
//    public ModelAndView toBind(
//            @PathVariable(value = "plugin", required = true) String plugin,
//            @RequestParam(value = "operatorId", required = false) String operatorId,
//            HttpServletRequest request, ModelMap response) {
//        if (!WebContextUtils.isSuperAdmin()
//                || StringUtils.isEmpty(operatorId)) {
//            //如果不为超级管理员，或操作员id为空时，默认取值为当前登陆人员
//            operatorId = WebContextUtils.getOperatorId();
//        }
//        
//        LoginPlugin<?> loginPlugin = LoginPluginUtils.getLoginPlugin(plugin);
//        if (loginPlugin == null) {
//            ModelAndView mv = new ModelAndView();
//            RemoteResult<?> result = RemoteResult.FAIL(-1,
//                    MessageUtils.format("登陆插件不存在.plugin:{}", plugin));
//            mv.getModelMap().put("result", result);
//            mv.setViewName("/loginplugin/result");
//            return mv;
//        }
//        
//        //将targetName写入会话中
//        LoginPluginUtils.pushTargetName(
//                LoginPluginConstants.TARGET_NAME_BIND_4_OPERATOR);
//        ModelAndView mv = loginPlugin.bindHandle(operatorId, request);
//        return mv;
//    }
//    
//    /**
//     * 请求编码forbind<br/>
//     * <功能详细描述>
//     * @param type
//     * @param request
//     * @param response
//     * @return [参数说明]
//     * 
//     * @return ModelAndView [返回类型说明]
//     * @exception throws [异常类型] [异常说明]
//     * @see [类、类#方法、类#成员]
//     */
//    @RequestMapping("/toLogin/{plugin}")
//    public ModelAndView toLogin(
//            @PathVariable(value = "plugin", required = true) String plugin,
//            HttpServletRequest request, ModelMap response) {
//        LoginPlugin<?> loginPlugin = LoginPluginUtils.getLoginPlugin(plugin);
//        if (loginPlugin == null) {
//            HttpSession session = request.getSession(true);
//            SocialAuthorizeException e = new SocialAuthorizeException(
//                    MessageUtils.format("登陆插件不存在.plugin:{}", plugin));
//            session.setAttribute(WebAttributes.AUTHENTICATION_EXCEPTION, e);
//            
//            ModelAndView mv = new ModelAndView();
//            mv.setViewName("redirect:/background/login");
//            return mv;
//        }
//        
//        //将targetName写入会话中
//        LoginPluginUtils.pushTargetName(
//                LoginPluginConstants.TARGET_NAME_LOGIN_4_OPERATOR);
//        ModelAndView mv = loginPlugin.loginHandle(request);
//        return mv;
//    }
//    
//    /**
//     * 请求编码forbind<br/>
//     * <功能详细描述>
//     * @param type
//     * @param request
//     * @param response
//     * @return [参数说明]
//     * 
//     * @return ModelAndView [返回类型说明]
//     * @exception throws [异常类型] [异常说明]
//     * @see [类、类#方法、类#成员]
//     */
//    @RequestMapping("/bind/{plugin}")
//    public String bind(
//            @PathVariable(value = "plugin", required = true) String plugin,
//            @RequestParam(value = "state", required = false) String state,
//            @RequestParam(value = "code", required = false) String code,
//            HttpServletRequest request, ModelMap response) {
//        if (StringUtils.isEmpty(code)) {
//            RemoteResult<?> result = RemoteResult.FAIL(-1,
//                    MessageUtils.format("绑定失败.code is empty."));
//            response.put("result", result);
//            return "/loginplugin/result";
//        }
//        LoginPlugin<?> loginPlugin = LoginPluginUtils.getLoginPlugin(plugin);
//        if (loginPlugin == null) {
//            RemoteResult<?> result = RemoteResult.FAIL(-1,
//                    MessageUtils.format("登陆插件不存在.plugin:{}", plugin));
//            response.put("result", result);
//            return "/loginplugin/result";
//        }
//        
//        //将用户id出栈
//        String operatorId = LoginPluginUtils.popUserId();
//        LoginUserInfo userInfo = null;
//        OperSocialAccountTypeEnum type = LoginPluginUtils
//                .getTypeByPlugin(plugin);
//        try {
//            if (type == null) {
//                RemoteResult<?> result = RemoteResult.FAIL(-1,
//                        MessageUtils.format("绑定失败：账号类型解析失败..plugin:{}",
//                                plugin));
//                response.put("result", result);
//                return "/loginplugin/result";
//            }
//            AssertUtils.notNull(type, "type is null.plugin:{}", plugin);
//            
//            LoginAccessToken accessToken = loginPlugin.getAccessToken(code,
//                    state,
//                    request);
//            userInfo = loginPlugin.getUserInfo(accessToken, request);
//            OperSocialAccount account = this.operSocialAccountService
//                    .findByUniqueId(userInfo.getUniqueId(), type);
//            if (account != null) {
//                RemoteResult<?> result = RemoteResult.FAIL(-1,
//                        MessageUtils.format("绑定失败：该账户已与其他账号绑定."));
//                response.put("result", result);
//                return "/loginplugin/result";
//            }
//            account = new OperSocialAccount();
//            account.setType(type);
//            account.setOperatorId(operatorId);
//            account.setUniqueId(userInfo.getUniqueId());
//            account.setUsername(userInfo.getUsername());
//            account.setHeadImgUrl(userInfo.getHeadImgUrl());
//            account.setAttributes(userInfo.getAttributes());
//            this.operSocialAccountService.insert(account);
//            
//            RemoteResult<?> result = RemoteResult
//                    .RESULT(RemoteConstants.CODE_SUCCESS, "绑定成功.", true);
//            response.put("result", result);
//        } catch (Exception e) {
//            RemoteResult<?> result = RemoteResult.FAIL(-1,
//                    MessageUtils.format("绑定账户失败:" + e.getMessage()));
//            response.put("result", result);
//        }
//        return "/loginplugin/result";
//    }
//}
