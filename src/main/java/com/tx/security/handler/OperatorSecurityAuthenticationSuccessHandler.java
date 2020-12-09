/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2019年7月25日
 * <修改描述:>
 */
package com.tx.security.handler;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import com.tx.component.servicelogger.util.ServiceLoggerUtils;
import com.tx.core.remote.RemoteResult;
import com.tx.core.util.JsonUtils;
import com.tx.core.util.MessageUtils;
import com.tx.core.util.WebUtils;
import com.tx.local.operator.model.OperLoginLog;
import com.tx.local.operator.model.Operator;
import com.tx.local.operator.service.OperatorService;
import com.tx.security.model.OperatorSocialAuthenticationToken;
import com.tx.security.model.OperatorUserDetails;

/**
 * ajax认证成功句柄<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2019年7月25日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class OperatorSecurityAuthenticationSuccessHandler
        extends SavedRequestAwareAuthenticationSuccessHandler {
    
    /** 日志记录器 */
    private Logger logger = LoggerFactory
            .getLogger(OperatorSecurityAuthenticationSuccessHandler.class);
    
    /** 操作人员业务层 */
    @Resource
    private OperatorService operatorService;
    
    /** <默认构造函数> */
    public OperatorSecurityAuthenticationSuccessHandler() {
        super();
        setDefaultTargetUrl("/mainframe");
    }
    
    /** <默认构造函数> */
    public OperatorSecurityAuthenticationSuccessHandler(
            String defaultTargetUrl) {
        super();
        setDefaultTargetUrl(defaultTargetUrl);
    }
    
    /** 登陆成功处理逻辑 */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
            HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {
        logger.info("登录成功：" + authentication.getName());
        
        //登陆成功后处理逻辑
        postLoginSuccess(request, authentication);
        
        //记录登陆日志
        logLoginSuccess(request, authentication);
        
        if (WebUtils.isAjaxRequest(request)) {
            //如果为ajax请求，则返回ajax结果
            response.setContentType("application/json;charset=utf-8");
            PrintWriter out = response.getWriter();
            
            RemoteResult<String> result = RemoteResult
                    .SUCCESS("/background/mainframe");
            result.setMessage("登录成功");
            out.write(JsonUtils.toJson(result));
            
            out.flush();
            out.close();
            
            //清理最后一次错误信息
            clearAuthenticationAttributes(request);
            return;
        }
        super.onAuthenticationSuccess(request, response, authentication);
    }
    
    /**
     * 登陆成功后处理逻辑<br/>
     * <功能详细描述>
     * @param request
     * @param authentication [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    private void postLoginSuccess(HttpServletRequest request,
            Authentication authentication) {
        //如果用户密码错误次数>1，则重置密码错误次数为0
        OperatorUserDetails operUserDetail = (OperatorUserDetails) authentication
                .getPrincipal();
        Operator oper = operUserDetail.getOperator();
        if (oper.getPwdErrCount() > 0) {
            oper.setPwdErrCount(0);
            //this.operatorService.updatePwdErrorCountById(oper.getId(), 0);
        }
    }
    
    /** 
     * 登陆日志记录<br/>
     * <功能详细描述>
     * @param authentication [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    private void logLoginSuccess(HttpServletRequest request,
            Authentication authentication) {
        String loginMode = "";
        OperatorUserDetails operUserDetail = null;
        if (UsernamePasswordAuthenticationToken.class
                .isInstance(authentication)) {
            UsernamePasswordAuthenticationToken au = (UsernamePasswordAuthenticationToken) authentication;
            operUserDetail = (OperatorUserDetails) au.getPrincipal();
            loginMode = "用户名密码登陆";
        } else if (OperatorSocialAuthenticationToken.class
                .isInstance(authentication)) {
            OperatorSocialAuthenticationToken au = (OperatorSocialAuthenticationToken) authentication;
            operUserDetail = (OperatorUserDetails) au.getPrincipal();
            //QQ、WX WB...
            loginMode = "第三方账号登陆";
        } else {
            operUserDetail = (OperatorUserDetails) authentication
                    .getPrincipal();
            loginMode = "其他方式登陆";
        }
        
        String operatorId = operUserDetail.getOperator().getId();
        String operatorUsername = operUserDetail.getOperator().getUsername();
        
        ServiceLoggerUtils.log(OperLoginLog.builder()
                .operatorId(operatorId)
                .operatorUsername(operatorUsername)
                .loginMode(loginMode)
                .message(MessageUtils.format("用户{}登陆成功.登陆模式:{}.",
                        operatorUsername,
                        loginMode))
                .build());
    }
}
