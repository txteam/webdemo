///*
// * 描          述:  <描述>
// * 修  改   人:  Administrator
// * 修改时间:  2018年12月17日
// * <修改描述:>
// */
//package com.tx.local.security.filter;
//
//import java.io.IOException;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.apache.commons.lang3.StringUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.security.authentication.AuthenticationServiceException;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//import org.springframework.security.web.util.matcher.RequestMatcher;
//
//import com.tx.local.security.exception.KaptchaValidateException;
//
///**
// * 验证码验证权限过滤器<br/>
// * <功能详细描述>
// * 
// * @author  Administrator
// * @version  [版本号, 2018年12月17日]
// * @see  [相关类/方法]
// * @since  [产品/模块版本]
// */
//public class KaptchaValidateAuthenticationFilter
//        extends AbstractAuthenticationProcessingFilter {
//    
//    private Logger logger = LoggerFactory
//            .getLogger(KaptchaValidateAuthenticationFilter.class);
//    
//    private boolean postOnly = true;
//    
//    private String captchaCodeParameter = AUTHCON;
//    
//    private String captchaCodeSessionKey = SPRING_SECURITY_FORM_CAPTCHA_CODE_KEY;
//    
//    /** <默认构造函数> */
//    public KaptchaValidateAuthenticationFilter(String loginUrl) {
//        super(new AntPathRequestMatcher(loginUrl, "POST"));
//    }
//    
//    /** <默认构造函数> */
//    public KaptchaValidateAuthenticationFilter(
//            RequestMatcher requiresAuthenticationRequestMatcher) {
//        super(new AntPathRequestMatcher("/doLogin", "POST"));
//    }
//    
//    /**
//     * 
//     */
//    @Override
//    public void afterPropertiesSet() {
//        //覆盖父类方法，取消对authenticationManager的依赖
//    }
//    
//    /**
//     * @param request
//     * @param response
//     * @param chain
//     * @throws IOException
//     * @throws ServletException
//     */
//    @Override
//    public void doFilter(ServletRequest req, ServletResponse res,
//            FilterChain chain) throws IOException, ServletException {
//        HttpServletRequest request = (HttpServletRequest) req;
//        HttpServletResponse response = (HttpServletResponse) res;
//        
//        if (requiresAuthentication(request, response)) {
//            //提交验证码的逻辑应该为POST请求
//            if (postOnly && !request.getMethod().equals("POST")) {
//                throw new AuthenticationServiceException(
//                        "Authentication method not supported: "
//                                + request.getMethod());
//            }
//            
//            //从会话中获取验证码
//            String sessionCaptchaCode = obtainSessionCaptchaCode(request);
//            //获取验证码
//            String captchaCode = obtainRequestCaptchaCode(request);
//            
//            //日志记录
//            logger.debug("开始校验验证码，生成的验证码为：" + sessionCaptchaCode + " ，输入的验证码为："
//                    + captchaCode);
//            //忽略大小写验证验证码
//            if (captchaCode == null || !StringUtils
//                    .equalsIgnoreCase(captchaCode, sessionCaptchaCode)) {
//                unsuccessfulAuthentication(request,
//                        response,
//                        new KaptchaValidateException("验证错误."));
//                return;
//            }
//        }
//        
//        chain.doFilter(request, response);
//        return;
//    }
//    
//    /**
//     * 覆盖授权验证方法，这里可以做一些自己需要的session设置操作
//     * @param request
//     * @param response
//     * @return
//     * @throws AuthenticationException
//     * @throws IOException
//     * @throws ServletException
//     */
//    @Override
//    public Authentication attemptAuthentication(HttpServletRequest request,
//            HttpServletResponse response)
//            throws AuthenticationException, IOException, ServletException {
//        return null;
//    }
//    
//    /**
//     * 从请求中获取验证码<br/>
//     * <功能详细描述>
//     * @param request
//     * @return [参数说明]
//     * 
//     * @return String [返回类型说明]
//     * @exception throws [异常类型] [异常说明]
//     * @see [类、类#方法、类#成员]
//     */
//    protected String obtainSessionCaptchaCode(HttpServletRequest request) {
//        String sessionCaptchaCode = request.getSession() == null ? null
//                : (String) request.getSession()
//                        .getAttribute(this.captchaCodeSessionKey);
//        return sessionCaptchaCode;
//    }
//    
//    /**
//     * 从请求中获取验证码<br/>
//     * <功能详细描述>
//     * @param request
//     * @return [参数说明]
//     * 
//     * @return String [返回类型说明]
//     * @exception throws [异常类型] [异常说明]
//     * @see [类、类#方法、类#成员]
//     */
//    protected String obtainRequestCaptchaCode(HttpServletRequest request) {
//        return request.getParameter(this.captchaCodeParameter);
//    }
//    
//    /**
//     * @return 返回 captchaCodeParameter
//     */
//    public String getCaptchaCodeParameter() {
//        return captchaCodeParameter;
//    }
//    
//    /**
//     * @param 对captchaCodeParameter进行赋值
//     */
//    public void setCaptchaCodeParameter(String captchaCodeParameter) {
//        this.captchaCodeParameter = captchaCodeParameter;
//    }
//    
//    /**
//     * @return 返回 captchaCodeSessionKey
//     */
//    public String getCaptchaCodeSessionKey() {
//        return captchaCodeSessionKey;
//    }
//    
//    /**
//     * @param 对captchaCodeSessionKey进行赋值
//     */
//    public void setCaptchaCodeSessionKey(String captchaCodeSessionKey) {
//        this.captchaCodeSessionKey = captchaCodeSessionKey;
//    }
//    
//    /**
//     * @return 返回 postOnly
//     */
//    public boolean isPostOnly() {
//        return postOnly;
//    }
//    
//    /**
//     * @param 对postOnly进行赋值
//     */
//    public void setPostOnly(boolean postOnly) {
//        this.postOnly = postOnly;
//    }
//}
