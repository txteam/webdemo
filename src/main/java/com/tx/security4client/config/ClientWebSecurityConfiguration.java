package com.tx.security4client.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.switchuser.SwitchUserFilter;

import com.tx.component.security.filter.SecurityThreadLocalResourceSupportFilter;
import com.tx.local.clientinfo.model.ClientRoleEnum;
import com.tx.security.config.WebSecurityConfiguration;
import com.tx.security4client.filter.ClientAuthenticationProcessingFilter;
import com.tx.security4client.handler.ClientSecurityAuthenticationFailureHandler;
import com.tx.security4client.handler.ClientSecurityAuthenticationSuccessHandler;
import com.tx.security4client.model.ClientPasswordEncoder;
import com.tx.security4client.provider.ClientAuthenticationProvider;
import com.tx.security4client.provider.ClientSocialAuthenticationProvider;
import com.tx.security4client.service.ClientUserDetailsService;
import com.tx.security4client.strategy.ClientSessionAuthenticationStrategy;

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
    
    @Autowired
    private AuthenticationManager authenticationManager;
    
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
                .logoutUrl("/client/logout")
                .logoutSuccessUrl("/client/login");
        
        //注册登录入口
        //registerAuthenticationEntryPoint(http);
        
        //registerAccessDeniedHandler(http);
        
        //必须条件该过滤器，不然权限容器中线程变量逻辑会存在问题
        http.addFilterAfter(new SecurityThreadLocalResourceSupportFilter(),
                SwitchUserFilter.class);
        
        //所有请求都允许访问
        //http.authorizeRequests().anyRequest().fullyAuthenticated();
        //首页允许
        /*http.authorizeRequests()
                .antMatchers("/client/**", "/client/index")
                .permitAll();*/
        //第三方用户登陆
        http.authorizeRequests().antMatchers("/client/social/**").permitAll();
        //后台登陆页
        http.authorizeRequests().antMatchers("/client/login").permitAll();
        http.authorizeRequests().antMatchers("/client/sign").permitAll();
        
        //验证码
        http.authorizeRequests().antMatchers("/client/captcha/**").permitAll();
        
        //其他
        http.authorizeRequests()
                .anyRequest()
                .hasRole(ClientRoleEnum.CLIENT.getId());//需要操作人员角色
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
        ClientSecurityAuthenticationSuccessHandler handler = new ClientSecurityAuthenticationSuccessHandler();
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
        ClientSecurityAuthenticationFailureHandler handler = new ClientSecurityAuthenticationFailureHandler();
        return handler;
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
        ClientAuthenticationProcessingFilter filter = new ClientAuthenticationProcessingFilter();
        filter.setAuthenticationSuccessHandler(successHandler());
        filter.setAuthenticationFailureHandler(failureHandler());
        
        filter.setAuthenticationManager(this.authenticationManager);
        return filter;
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
     * 操作人员密码验证器<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return PasswordEncoder [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Bean("clientPasswordEncoder")
    public PasswordEncoder clientPasswordEncoder() {
        PasswordEncoder encoder = new ClientPasswordEncoder();
        return encoder;
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
        provider.setPasswordEncoder(clientPasswordEncoder());
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