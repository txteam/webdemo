/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2019年8月6日
 * <修改描述:>
 */
package com.tx.local.security.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import com.tx.core.util.WebRequestUtils;
import com.tx.local.security.entrypoint.SecurityLoginAuthenticationEntryPoint;

/**
 * 访问控制处理<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2019年8月6日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class SecurityAccessDeniedHandler implements AccessDeniedHandler {
    
    @SuppressWarnings("unused")
    private SecurityLoginAuthenticationEntryPoint loginAuthenticationEntryPoint;
    
    /**
     * @param request
     * @param response
     * @param accessDeniedException
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
            AccessDeniedException accessDeniedException)
            throws IOException, ServletException {
        if (WebRequestUtils.isAjaxRequest(request)) {
            //AJAX请求,使用response发送403
            response.sendError(403);
        } else if (!response.isCommitted()) {
            //非AJAX请求，跳转系统默认的403错误界面，在web.xml中配置
            response.sendError(HttpServletResponse.SC_FORBIDDEN,
                    accessDeniedException.getMessage());
        }
    }
    
}
