package com.tx.local.security.config;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.util.DigestUtils;

import com.tx.local.security.service.OperatorUserDetailService;
import com.tx.local.security.strategy.UserSessionAuthenticationStrategy;

/**
 * SpringSecurity本地权限定制<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2018年7月7日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
    
    protected static Logger logger = LoggerFactory
            .getLogger(WebSecurityConfiguration.class);
    
    @Autowired
    private UserSessionAuthenticationStrategy userSessionAuthenticationStrategy;
    
    @Resource(name = "userDetailService")
    private OperatorUserDetailService userDetailService;
    
    /** 登录处理链接 */
    private String loginProcessingUrl = "/doLogin";
    
    /**
     * webSecurity配置
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/webjars/**",
                "css/**",
                "images/**",
                "js/**",
                "uploads/**",
                "/**/*.js",
                "/**/*.css",
                "/**/*.jpg",
                "/**/*.png",
                "/**/*.woff2",
                "/**/*.svg",
                "/**/*.ico");
    }
    
    /**
     * 用户验证
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        AuthenticationProvider upProvider = usernamePasswordAuthenticationProvider();
        //注入Provider
        auth.authenticationProvider(upProvider);
    }
    
    /**
     * 生成UsernamePasswordAuthenticationProvider<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return DaoAuthenticationProvider [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Bean
    public DaoAuthenticationProvider usernamePasswordAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        // 设置userDetailsService
        provider.setUserDetailsService(userDetailService);
        // 禁止隐藏用户未找到异常
        provider.setHideUserNotFoundExceptions(false);
        // 使用BCrypt进行密码的hash
        provider.setPasswordEncoder(md5PasswordEncoder());
        return provider;
    }
    
    /**
     * 密码验证器<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return PasswordEncoder [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Bean("md5PasswordEncoder")
    public PasswordEncoder md5PasswordEncoder() {
        PasswordEncoder encoder = new PasswordEncoder() {
            
            @Override
            public boolean matches(CharSequence rawPassword,
                    String encodedPassword) {
                if (rawPassword == null) {
                    //如果为空
                    return false;
                }
                //全不为空
                String rawEncodedPassword = DigestUtils
                        .md5DigestAsHex(rawPassword.toString().getBytes());
                if (StringUtils.equalsAnyIgnoreCase(rawEncodedPassword,
                        encodedPassword)) {
                    return true;
                } else {
                    return false;
                }
            }
            
            @Override
            public String encode(CharSequence rawPassword) {
                String encodePassword = DigestUtils
                        .md5DigestAsHex(rawPassword.toString().getBytes());
                return encodePassword;
            }
        };
        return encoder;
    }
    
    /**
     * httpSecurity配置
     * @param httpSecurity
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //关闭csrf(跨域请求伪造),内部管理系统每次请求没有必要都需要带一个签名token如果需要也问题不大，待access_token机制加入以后再进行调整
        http.csrf().disable();
        
        //配置session
        //ALWAYS:总是创建HttpSession
        //IF_REQUIRED:Spring Security只会在需要时创建一个HttpSession
        //NEVER:Spring Security不会创建HttpSession，但如果它已经存在，将可以使用HttpSession
        //STATELESS:Spring Security永远不会创建HttpSession，它不会使用HttpSession来获取SecurityContext
        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .sessionAuthenticationStrategy(
                        userSessionAuthenticationStrategy);
        
        //logout配置
        http.logout().logoutUrl("/logout").logoutSuccessUrl("/login");
        //http.logout().disable();
        //.and().logout().logoutUrl("/logout").logoutSuccessUrl("/")
        //        http.logout().logoutUrl("/logout").permitAll().logoutSuccessHandler(
        //                (request, response, authentication) -> {
        //                    logger.info("登出成功：" + authentication.getName());
        //                    
        //                    response.setContentType("application/json;charset=utf-8");
        //                    PrintWriter out = response.getWriter();
        //                    out.write("{\"status\":\"ok\",\"msg\":\"登录成功\"}");
        //                    out.flush();
        //                    out.close();
        //                });
        
        //所有请求都允许访问
        //允许直接访问的链接
        
//        filterChainDefinitionMap.put("/sys/login", "anon"); //登录接口排除
//        filterChainDefinitionMap.put("/auth/2step-code", "anon");//登录验证码
//        filterChainDefinitionMap.put("/sys/common/view/**", "anon");//图片预览不限制token
//        filterChainDefinitionMap.put("/sys/common/download/**", "anon");//文件下载不限制token
//        filterChainDefinitionMap.put("/sys/common/pdf/**", "anon");//pdf预览
//        filterChainDefinitionMap.put("/generic/**", "anon");//pdf预览需要文件
//        filterChainDefinitionMap.put("/", "anon");
//        filterChainDefinitionMap.put("/doc.html", "anon");
//        filterChainDefinitionMap.put("/**/*.html", "anon");
//        filterChainDefinitionMap.put("/druid/**", "anon");
//        filterChainDefinitionMap.put("/swagger-ui.html", "anon");
//        filterChainDefinitionMap.put("/swagger**/**", "anon");
//        filterChainDefinitionMap.put("/webjars/**", "anon");
//        filterChainDefinitionMap.put("/v2/**", "anon");
//        //性能监控
//        filterChainDefinitionMap.put("/actuator/metrics/**", "anon");
//        filterChainDefinitionMap.put("/actuator/httptrace/**", "anon");
//        filterChainDefinitionMap.put("/actuator/redis/**", "anon");
        
        http.authorizeRequests()
                .antMatchers("/", "/index", "/loginer", "/login", "/toLogin")
                .permitAll()
                .antMatchers("/error/**")
                .permitAll()
                .antMatchers("/captcha/**")
                .permitAll()
                .antMatchers(this.loginProcessingUrl)
                .permitAll()
                .antMatchers("/test/**")
                .permitAll()
                .antMatchers("/test/authentication/**")
                .authenticated()
                .antMatchers("/oauth/authorize")
                .permitAll()
                .anyRequest()
                .authenticated();//其他请求需要鉴权
        
        //http.authorizeRequests().antMatchers("")
        //.hasIpAddress(ipaddressExpression)
        //.hasRole(role)
        //.hasAuthority(authority)
        //hasAnyRole(roles)
        //.hasAnyAuthority(authorities)
        
        //login配置
        //http.formLogin().disable();
        http.formLogin()
                .usernameParameter("username")
                .passwordParameter("password")
                .loginPage("/login")
                .loginProcessingUrl(this.loginProcessingUrl)
                .permitAll()
                .successHandler(successHandler)
                .failureHandler(failureHandler);
        
        //iframe header disable
        http.headers().frameOptions().disable();
        
        //用户名密码验证前先验证  验证码
        //        http.addFilterBefore(captchaValidateAuthenticationFilter(),
        //                UsernamePasswordAuthenticationFilter.class);
        
    }
    
    //    /**
    //     * 验证码验证过滤器<br/>
    //     * <功能详细描述>
    //     * @return [参数说明]
    //     * 
    //     * @return CaptchaValidateAuthenticationFilter [返回类型说明]
    //     * @exception throws [异常类型] [异常说明]
    //     * @see [类、类#方法、类#成员]
    //     */
    //    @Bean
    //    public CaptchaValidateAuthenticationFilter captchaValidateAuthenticationFilter() {
    //        CaptchaValidateAuthenticationFilter filter = new CaptchaValidateAuthenticationFilter(
    //                this.loginProcessingUrl);
    //        filter.setCaptchaCodeParameter(CaptchaConstants.REQUEST_LOGIN_CAPTCHA_CODE_KEY);
    //        filter.setCaptchaCodeSessionKey(CaptchaConstants.SESSION_LOGIN_CAPTCHA_CODE_KEY);
    //        filter.setAuthenticationFailureHandler(this.failureHandler);
    //        return filter;
    //    }
    
    /** 登录成功处理句柄  */
    private AuthenticationSuccessHandler successHandler = new AuthenticationSuccessHandler() {
        
        @Override
        public void onAuthenticationSuccess(HttpServletRequest request,
                HttpServletResponse response, Authentication authentication)
                throws IOException, ServletException {
            logger.info("登录成功：" + authentication.getName());
            
            response.setContentType("application/json;charset=utf-8");
            PrintWriter out = response.getWriter();
            out.write(
                    "{\"status\":\"success\",\"msg\":\"登录成功\",\"data\":\"mainframe/mainframe\"}");
            out.flush();
            out.close();
        }
    };
    
    /** 登录失败处理句柄 */
    private AuthenticationFailureHandler failureHandler = new AuthenticationFailureHandler() {
        
        @Override
        public void onAuthenticationFailure(HttpServletRequest request,
                HttpServletResponse response, AuthenticationException exception)
                throws IOException, ServletException {
            logger.warn("登录失败：" + exception.getMessage());
            
            String errorMessage = loginErrorMessage(exception);
            
            response.setContentType("application/json;charset=utf-8");
            PrintWriter out = response.getWriter();
            out.write(
                    "{\"status\":\"error\",\"msg\":\"" + errorMessage + "\"}");
            out.flush();
            out.close();
        }
    };
    
    /**
     * 解析登录异常信息<br/>
     * <功能详细描述>
     * @param exception
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public static String loginErrorMessage(AuthenticationException exception) {
        String errorMessage = "用户名或密码错误.";
        if (exception == null) {
            errorMessage = "系统内部错误，请联系管理员.";
            return errorMessage;
        }
        Class<? extends AuthenticationException> exceptionClass = exception
                .getClass();
        if (ClassUtils.isAssignable(exceptionClass,
                AccountStatusException.class)) {
            errorMessage = "用户账户状态异常.";
            if (ClassUtils.isAssignable(exceptionClass,
                    AccountExpiredException.class)) {
                errorMessage = "用户账户已过期.";
            } else if (ClassUtils.isAssignable(exceptionClass,
                    LockedException.class)) {
                errorMessage = "用户账户已锁定.";
            } else if (ClassUtils.isAssignable(exceptionClass,
                    DisabledException.class)) {
                errorMessage = "用户账户已锁定.";
            } else if (ClassUtils.isAssignable(exceptionClass,
                    CredentialsExpiredException.class)) {
                errorMessage = "用户授权已过期，请重新登录.";
            }
        } else if (ClassUtils.isAssignable(exceptionClass,
                AuthenticationCredentialsNotFoundException.class)) {
            errorMessage = "用户授权不存在，非法授权或已过期授权，请重新登录.";
        }
        
        return errorMessage;
    }
}
