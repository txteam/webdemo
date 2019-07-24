/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2019年7月23日
 * <修改描述:>
 */
package com.tx.local.security.filter;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

/**
 * 操作权认证处理过滤器<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2019年7月23日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class OperatorWXAuthenticationProcessingFilter extends AbstractAuthenticationProcessingFilter{

    /** <默认构造函数> */
    protected OperatorWXAuthenticationProcessingFilter(
            RequestMatcher requiresAuthenticationRequestMatcher) {
        super(new AntPathRequestMatcher("/operator/wx/doLogin", "POST"));
    }

    /**
     * @param request
     * @param response
     * @return
     * @throws AuthenticationException
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
            HttpServletResponse response)
            throws AuthenticationException, IOException, ServletException {
        // TODO Auto-generated method stub
        return null;
    }
    
    
}
