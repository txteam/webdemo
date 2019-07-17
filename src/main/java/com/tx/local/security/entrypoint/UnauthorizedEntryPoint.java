/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2019年7月8日
 * <修改描述:>
 */
package com.tx.local.security.entrypoint;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import com.tx.core.util.WebRequestUtils;

/**
 * 无权限时进入节点<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2019年7月8日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class UnauthorizedEntryPoint implements AuthenticationEntryPoint {
    
    /**
     * @param request
     * @param response
     * @param authException
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void commence(HttpServletRequest request,
            HttpServletResponse response, AuthenticationException authException)
            throws IOException, ServletException {
        if (WebRequestUtils.isAjaxRequest(request)) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED,
                    authException.getMessage());
        } else {
            response.sendRedirect("/login");
        }
        
    }
}
