/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2019年7月25日
 * <修改描述:>
 */
package com.tx.security.handler;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import com.tx.core.remote.RemoteResult;
import com.tx.core.util.JsonUtils;
import com.tx.core.util.WebUtils;
import com.tx.local.security.util.AuthenticationUtils;

/**
 * ajax认证错误处理器<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2019年7月25日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class OperatorSecurityAuthenticationFailureHandler
        extends SimpleUrlAuthenticationFailureHandler {
    
    /** 日志记录器 */
    private Logger logger = LoggerFactory
            .getLogger(OperatorSecurityAuthenticationFailureHandler.class);
    
    /** <默认构造函数> */
    public OperatorSecurityAuthenticationFailureHandler() {
        super("/login");
    }
    
    /** <默认构造函数> */
    public OperatorSecurityAuthenticationFailureHandler(
            String defaultFailureUrl) {
        super(defaultFailureUrl);
    }
    
    /**
     * @param request
     * @param response
     * @param exception
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
            HttpServletResponse response, AuthenticationException exception)
            throws IOException, ServletException {
        logger.warn("登录失败：" + exception.getMessage());
        
        if (WebUtils.isAjaxRequest(request)) {
            response.setContentType("application/json;charset=utf-8");
            
            PrintWriter out = response.getWriter();
            RemoteResult<?> result = RemoteResult.FAIL(-1,
                    AuthenticationUtils.loginErrorMessage(exception));
            out.write(JsonUtils.toJson(result));
            out.flush();
            out.close();
        } else {
            super.onAuthenticationFailure(request, response, exception);
        }
    }
    
}
