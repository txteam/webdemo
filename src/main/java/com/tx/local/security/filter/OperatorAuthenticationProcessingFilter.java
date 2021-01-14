/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2019年7月23日
 * <修改描述:>
 */
package com.tx.local.security.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.tx.component.security.exception.CaptchaErrorException;
import com.tx.local.security.SecurityConstants;
import com.tx.local.security.model.OperatorLoginFormAuthenticationToken;

/**
 * 操作权认证处理过滤器<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2019年7月23日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class OperatorAuthenticationProcessingFilter
        extends AbstractAuthenticationProcessingFilter {
    
    /** 日志记录句柄 */
    private Logger logger = LoggerFactory
            .getLogger(OperatorAuthenticationProcessingFilter.class);
    
    public static final String SPRING_SECURITY_FORM_USERNAME_KEY = "username";
    
    public static final String SPRING_SECURITY_FORM_PASSWORD_KEY = "password";
    
    private String usernameParameter = SPRING_SECURITY_FORM_USERNAME_KEY;
    
    private String passwordParameter = SPRING_SECURITY_FORM_PASSWORD_KEY;
    
    /** 是否仅支持post方法 */
    private boolean postOnly = true;
    
    /** 是否校验验证码 */
    private boolean validateCaptcha = false;
    
    /** 验证码参数 */
    private String captchaParameter = SecurityConstants.CAPTCHA_FOR_OPERATOR;
    
    /** 验证码session值 */
    private String captchaSessionAtrribute = SecurityConstants.SESSION_CAPTCHA_FOR_OPERATOR;
    
    /** <默认构造函数> */
    public OperatorAuthenticationProcessingFilter() {
        super(new AntPathRequestMatcher("/operator/login", "POST"));
    }
    
    /** <默认构造函数> */
    public OperatorAuthenticationProcessingFilter(String loginProcessingUrl) {
        super(new AntPathRequestMatcher(loginProcessingUrl, "POST"));
    }
    
    /**
     * @param request
     * @param response
     * @return
     * @throws AuthenticationException
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
            HttpServletResponse response) throws AuthenticationException {
        if (postOnly && !request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException(
                    "Authentication method not supported: "
                            + request.getMethod());
        }
        
        String username = obtainUsername(request);
        String password = obtainPassword(request);
        
        if (username == null) {
            username = "";
        }
        
        if (password == null) {
            password = "";
        }
        
        username = username.trim();
        
        OperatorLoginFormAuthenticationToken authRequest = new OperatorLoginFormAuthenticationToken(
                username, password);
        // Allow subclasses to set the "details" property
        setDetails(request, authRequest);
        
        Authentication authentication = this.getAuthenticationManager()
                .authenticate(authRequest);
        return authentication;
    }
    
    /**
     * Provided so that subclasses may configure what is put into the authentication
     * request's details property.
     *
     * @param request that an authentication request is being created for
     * @param authRequest the authentication request object that should have its details
     * set
     */
    protected void setDetails(HttpServletRequest request,
            OperatorLoginFormAuthenticationToken authRequest) {
        authRequest
                .setDetails(authenticationDetailsSource.buildDetails(request));
    }
    
    /**
     * 校验验证码<br/>
     * <功能详细描述>
     * @param request
     * @param response [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public void validateCaptcha(HttpServletRequest request,
            HttpServletResponse response) throws AuthenticationException {
        if (!validateCaptcha) {
            return;
        }
        //从会话中获取验证码
        String sessionCaptchaCode = obtainSessionCaptchaCode(request);
        //获取验证码
        String captchaCode = obtainRequestCaptchaCode(request);
        
        //日志记录
        logger.debug("校验操作员登陆验证码.验证码：" + sessionCaptchaCode + " 输入验证码："
                + captchaCode);
        //忽略大小写验证验证码
        if (StringUtils.isEmpty(sessionCaptchaCode)
                || StringUtils.isEmpty(captchaCode)) {
            throw new CaptchaErrorException("验证错误.");
        }
        //忽略大小写验证验证码
        if (!StringUtils.equalsIgnoreCase(captchaCode, sessionCaptchaCode)) {
            throw new CaptchaErrorException("验证错误.");
        }
    }
    
    /**
     * 从请求中获取验证码<br/>
     * <功能详细描述>
     * @param request
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    protected String obtainSessionCaptchaCode(HttpServletRequest request) {
        String code = request.getSession(false) == null ? null
                : (String) request.getSession(false)
                        .getAttribute(this.captchaSessionAtrribute);
        code = code == null ? null : code.trim();
        return code;
    }
    
    /**
     * 从请求中获取验证码<br/>
     * <功能详细描述>
     * @param request
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    protected String obtainRequestCaptchaCode(HttpServletRequest request) {
        String code = request.getParameter(this.captchaParameter) == null ? null
                : (String) request.getParameter(this.captchaParameter);
        return code;
    }
    
    /**
     * 解析请求中的密码<br/>
     * <功能详细描述>
     * @param request
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    protected String obtainPassword(HttpServletRequest request) {
        return request.getParameter(passwordParameter);
    }
    
    /**
     * 解析请求中的用户名<br/>
     * <功能详细描述>
     * @param request
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    protected String obtainUsername(HttpServletRequest request) {
        return request.getParameter(usernameParameter);
    }
}
