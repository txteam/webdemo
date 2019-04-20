/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2018年12月15日
 * <修改描述:>
 */
package com.tx.local.security.strategy;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.stereotype.Component;

import com.tx.local.operator.model.Operator;
import com.tx.local.security.SecurityConstants;
import com.tx.local.security.model.OperatorUserDetail;

/**
 * 用户完成认证后，向session中写入当前登录的用户<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2018年12月15日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Component
public class UserSessionAuthenticationStrategy
        implements SessionAuthenticationStrategy {
    
    /**
     * @param authentication
     * @param request
     * @param response
     * @throws SessionAuthenticationException
     */
    @Override
    public void onAuthentication(Authentication authentication,
            HttpServletRequest request, HttpServletResponse response)
            throws SessionAuthenticationException {
        OperatorUserDetail userDetail = (OperatorUserDetail) authentication
                .getPrincipal();
        Operator user = userDetail.getUser();
        
        request.getSession().setAttribute(SecurityConstants.SESSION_USER, user);
    }
    
}
