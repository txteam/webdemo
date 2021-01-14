package com.tx.front4client.security.config;

import java.util.Arrays;
import java.util.Collections;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AndRequestMatcher;
import org.springframework.security.web.util.matcher.MediaTypeRequestMatcher;
import org.springframework.security.web.util.matcher.NegatedRequestMatcher;
import org.springframework.security.web.util.matcher.RequestHeaderRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.accept.ContentNegotiationStrategy;
import org.springframework.web.accept.HeaderContentNegotiationStrategy;

import com.tx.front4client.security.filter.ClientAuthenticationProcessingFilter;
import com.tx.front4client.security.handler.ClientSecurityAuthenticationFailureHandler;
import com.tx.front4client.security.handler.ClientSecurityAuthenticationSuccessHandler;
import com.tx.front4client.security.provider.ClientAuthenticationProvider;
import com.tx.front4client.security.provider.ClientSocialAuthenticationProvider;
import com.tx.front4client.security.service.ClientUserDetailsService;
import com.tx.front4client.security.strategy.ClientSessionAuthenticationStrategy;
import com.tx.local.clientinfo.model.ClientRoleEnum;
import com.tx.local.security.config.WebSecurityConfiguration;

/**
 * SpringSecurity本地权限定制<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2018年7月7日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Order(value = 201)
@AutoConfigureBefore(WebSecurityConfiguration.class)
@Configuration
public class ClientWebSecurityConfiguration
        extends WebSecurityConfigurerAdapter {
    
    /** 日志记录句柄 */
    protected static Logger logger = LoggerFactory
            .getLogger(ClientWebSecurityConfiguration.class);
    
    @Resource
    private AuthenticationManager authenticationManager;
    
    @Resource
    private PasswordEncoder passwordEncoder;
    
    private final String LOGOUT_URL = "/client/logout";
    
    private final String LOGIN_URL = "/client/login";
    
    private final String LOGIN_PROCESSING_URL = "/client/client/login";
    
    //private final String SOCIAL_LOGIN_PROCESSING_URL = "/client/client/social/login";
    
    private final String TARGET_URL = "/client/mainframe";
    
    /**
     * httpSecurity配置
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.antMatcher("/client/**");
        
        //关闭csrf(跨域请求伪造),内部管理系统每次请求没有必要都需要带一个签名token如果需要也问题不大，待access_token机制加入以后再进行调整
        http.csrf().disable();
        
        //iframe header disable
        http.headers().frameOptions().disable();
        
        //配置session
        //ALWAYS:总是创建HttpSession
        //IF_REQUIRED:Spring Security只会在需要时创建一个HttpSession
        //NEVER:Spring Security不会创建HttpSession，但如果它已经存在，将可以使用HttpSession
        //STATELESS:Spring Security永远不会创建HttpSession，它不会使用HttpSession来获取SecurityContext
        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .sessionAuthenticationStrategy(sessionStrategy());
        
        //login配置，disable后利用filter实现替代
        http.formLogin().disable();
        //添加登录过滤器
        http.addFilterBefore(clientAuthenticationProcessingFilter(),
                UsernamePasswordAuthenticationFilter.class);
        
        //logout配置
        //http.logout().disable();
        http.logout()
                .logoutUrl(this.LOGOUT_URL)
                .logoutSuccessUrl(this.LOGIN_URL);
        
        //注册登录入口
        //注册认证用户，无权限的处理
        http.exceptionHandling().accessDeniedPage(this.LOGIN_URL);
        registerAuthenticationEntryPoint(http);
        
        //所有请求都允许访问
        //http.authorizeRequests().anyRequest().fullyAuthenticated();
        //首页允许
        http.authorizeRequests()
                .antMatchers("/client/", "/client/index")
                .permitAll();
        //登陆
        http.authorizeRequests()
                .antMatchers(this.LOGIN_URL, this.LOGIN_PROCESSING_URL)
                .permitAll();
        
        //第三方用户登陆
        http.authorizeRequests().antMatchers("/client/social/**").permitAll();
        
        //验证码
        http.authorizeRequests().antMatchers("/client/captcha/**").permitAll();
        
        //其他
        http.authorizeRequests()
                .anyRequest()
                .hasRole(ClientRoleEnum.CLIENT.getId());//需要操作人员角色
    }
    
    /**
     * 注册登录入口<br/>
     * <功能详细描述>
     * @param http
     * @throws Exception [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    private void registerAuthenticationEntryPoint(HttpSecurity http)
            throws Exception {
        ContentNegotiationStrategy contentNegotiationStrategy = http
                .getSharedObject(ContentNegotiationStrategy.class);
        if (contentNegotiationStrategy == null) {
            contentNegotiationStrategy = new HeaderContentNegotiationStrategy();
        }
        
        MediaTypeRequestMatcher mediaMatcher = new MediaTypeRequestMatcher(
                contentNegotiationStrategy, MediaType.APPLICATION_XHTML_XML,
                new MediaType("image", "*"), MediaType.TEXT_HTML,
                MediaType.TEXT_PLAIN);
        mediaMatcher.setIgnoredMediaTypes(Collections.singleton(MediaType.ALL));
        RequestMatcher notXRequestedWith = new NegatedRequestMatcher(
                new RequestHeaderRequestMatcher("X-Requested-With",
                        "XMLHttpRequest"));
        RequestMatcher preferredMatcher = new AndRequestMatcher(
                Arrays.asList(notXRequestedWith, mediaMatcher));
        
        //注入默认的认证入口，异常拦截器
        http.exceptionHandling()
                .defaultAuthenticationEntryPointFor(
                        new LoginUrlAuthenticationEntryPoint(this.LOGIN_URL),
                        preferredMatcher);
    }
    
    /**
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.authenticationProvider(clientAuthenticationProvider());
        auth.authenticationProvider(clientSocialAuthenticationProvider());
    }
    
    /**
     * 客户认证处理过滤器<br/>
     * <功能详细描述>
     * @param authenticationManager
     * @param successHandler
     * @param failureHandler
     * @return [参数说明]
     * 
     * @return ClientAuthenticationProcessingFilter [返回类型说明]
     * @throws Exception 
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Bean("client.authenticationProcessingFilter")
    public ClientAuthenticationProcessingFilter clientAuthenticationProcessingFilter()
            throws Exception {
        ClientAuthenticationProcessingFilter filter = new ClientAuthenticationProcessingFilter(
                this.LOGIN_PROCESSING_URL);
        filter.setAuthenticationSuccessHandler(successHandler());
        filter.setAuthenticationFailureHandler(failureHandler());
        filter.setAuthenticationManager(this.authenticationManager);
        return filter;
    }
    
    /**
     * 认证成功处理句柄<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return AuthenticationSuccessHandler [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Bean("client.securityAuthenticationSuccessHandler")
    public ClientSecurityAuthenticationSuccessHandler successHandler() {
        ClientSecurityAuthenticationSuccessHandler handler = new ClientSecurityAuthenticationSuccessHandler(
                this.TARGET_URL);
        return handler;
    }
    
    /**
     * 认证失败处理句柄<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return AuthenticationFailureHandler [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Bean("client.securityAuthenticationFailureHandler")
    public ClientSecurityAuthenticationFailureHandler failureHandler() {
        ClientSecurityAuthenticationFailureHandler handler = new ClientSecurityAuthenticationFailureHandler(
                this.LOGIN_URL);
        return handler;
    }
    
    /**
     * 操作人员会话处理过滤器<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return OperatorSessionAuthenticationStrategy [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Bean("client.sessionAuthenticationStrategy")
    public ClientSessionAuthenticationStrategy sessionStrategy() {
        ClientSessionAuthenticationStrategy strategy = new ClientSessionAuthenticationStrategy();
        return strategy;
    }
    
    /**
     * 客户的UserDetails业务层实现<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return ClientUserDetailsService [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Bean("clientUserDetailsService")
    public ClientUserDetailsService clientUserDetailsService() {
        ClientUserDetailsService service = new ClientUserDetailsService();
        return service;
    }
    
    /**
     * 客户认证处理器<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return ClientAuthenticationProvider [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Bean("clientAuthenticationProvider")
    public ClientAuthenticationProvider clientAuthenticationProvider() {
        ClientAuthenticationProvider provider = new ClientAuthenticationProvider();
        // 设置userDetailsService
        provider.setUserDetailsService(clientUserDetailsService());
        // 禁止隐藏用户未找到异常
        provider.setHideUserNotFoundExceptions(false);
        // 使用BCrypt进行密码的hash
        provider.setPasswordEncoder(this.passwordEncoder);
        return provider;
    }
    
    /**
     *
     * <功能详细描述>
     * @return [参数说明]
     *
     * @return ClientSocialAuthenticationProvider [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Bean("clientSocialAuthenticationProvider")
    public ClientSocialAuthenticationProvider clientSocialAuthenticationProvider() {
        ClientSocialAuthenticationProvider provider = new ClientSocialAuthenticationProvider();
        // 设置userDetailsService
        provider.setUserDetailsService(clientUserDetailsService());
        // 禁止隐藏用户未找到异常
        provider.setHideUserNotFoundExceptions(false);
        return provider;
    }
}