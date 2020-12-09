/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2018年12月15日
 * <修改描述:>
 */
package com.tx.local.security;

/**
 * 安全容器常量<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2018年12月15日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface SecurityConstants {
    
    String[] IGNORING_MATCHERS = { "/webjars/**", "css/**", "images/**",
            "js/**", "/**/*.js", "/**/*.css", "/**/*.jpg", "/**/*.png",
            "/**/*.woff2", "/**/*.svg", "/**/*.ico", "/actuator/metrics/**",
            "/actuator/httptrace/**", "/actuator/redis/**", "/swagger-ui.html",
            "/swagger**/**", "/druid/**", "/druid/**" };
    
    /** 当前登录用户名 */
    String COOKIE_CURRENT_USERNAME = "currentUsername";
    
    /** 当前登录用户 */
    String SESSION_USER = "currentUser";
    
    String ACCESS_DOMAIN_KEY = "ACCESS_DOMAIN";
    
    String ACCESS_DOMAIN_DEFAULT = "CLIENT";
    
    /** 操作人员 */
    String ACCESS_DOMAIN_OPERATOR = "OPERATOR";
    
    /** 客户信息 */
    String ACCESS_DOMAIN_CLIENT = "CLIENT";
    
    /** 操作员登陆界面验证码 */
    String CAPTCHA_FOR_OPERATOR = "captcha";
    
    /** 操作员登陆session中存储的验证码 */
    String SESSION_CAPTCHA_FOR_OPERATOR = "session_captcha";
    
    /** 客户登陆界面验证码 */
    String CAPTCHA_FOR_CLIENT = "captcha";
    
    /** 客户登陆会话中存储的验证码 */
    String SESSION_CAPTCHA_FOR_CLIENT = "session_captcha";
}
