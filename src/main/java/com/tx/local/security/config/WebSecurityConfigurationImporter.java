/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2019年7月25日
 * <修改描述:>
 */
package com.tx.local.security.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.tx.local.security.entrypoint.SecurityLoginAuthenticationEntryPoint;
import com.tx.local.security.filter.ClientAuthenticationProcessingFilter;
import com.tx.local.security.filter.OperatorAuthenticationProcessingFilter;
import com.tx.local.security.handler.SecurityAuthenticationFailureHandler;
import com.tx.local.security.handler.SecurityAuthenticationSuccessHandler;
import com.tx.local.security.model.ClientPasswordEncoder;
import com.tx.local.security.model.OperatorPasswordEncoder;
import com.tx.local.security.provider.ClientAuthenticationProvider;
import com.tx.local.security.provider.OperatorAuthenticationProvider;
import com.tx.local.security.service.ClientUserDetailsService;
import com.tx.local.security.service.OperatorUserDetailsService;

/**
 * <功能简述>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2019年7月25日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Configuration
public class WebSecurityConfigurationImporter {
    
    /**
     * 操作人员密码验证器<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return PasswordEncoder [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Bean("operatorPasswordEncoder")
    public PasswordEncoder operatorPasswordEncoder() {
        PasswordEncoder encoder = new OperatorPasswordEncoder();
        return encoder;
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
     * 操作人员详情业务层<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return OperatorUserDetailsService [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Bean(name = "operatorUserDetailsService")
    public OperatorUserDetailsService operatorUserDetailsService() {
        OperatorUserDetailsService service = new OperatorUserDetailsService();
        return service;
    }
    
    /**
     * 生成operatorAuthenticationProvider<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return DaoAuthenticationProvider [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Bean("operatorAuthenticationProvider")
    public OperatorAuthenticationProvider operatorAuthenticationProvider(
            OperatorUserDetailsService operatorUserDetailsService) {
        OperatorAuthenticationProvider provider = new OperatorAuthenticationProvider();
        // 设置userDetailsService
        provider.setUserDetailsService(operatorUserDetailsService);
        // 禁止隐藏用户未找到异常
        provider.setHideUserNotFoundExceptions(false);
        // 使用BCrypt进行密码的hash
        provider.setPasswordEncoder(operatorPasswordEncoder());
        return provider;
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
     * @param clientUserDetailsService
     * @return [参数说明]
     * 
     * @return ClientAuthenticationProvider [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Bean("clientAuthenticationProvider")
    public ClientAuthenticationProvider clientAuthenticationProvider(
            ClientUserDetailsService clientUserDetailsService) {
        ClientAuthenticationProvider provider = new ClientAuthenticationProvider();
        // 设置userDetailsService
        provider.setUserDetailsService(clientUserDetailsService);
        // 禁止隐藏用户未找到异常
        provider.setHideUserNotFoundExceptions(false);
        // 使用BCrypt进行密码的hash
        provider.setPasswordEncoder(clientPasswordEncoder());
        return provider;
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
    @Bean(name = "authenticationSuccessHandler")
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        SecurityAuthenticationSuccessHandler handler = new SecurityAuthenticationSuccessHandler();
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
    @Bean(name = "authenticationFailureHandler")
    public AuthenticationFailureHandler authenticationFailureHandler() {
        SecurityAuthenticationFailureHandler handler = new SecurityAuthenticationFailureHandler();
        return handler;
    }
    
    /**
     * 认证管理器实现<br/>
     * <功能详细描述>
     * @param providers
     * @return [参数说明]
     * 
     * @return AuthenticationManager [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Bean(name = "authenticationManager")
    public AuthenticationManager authenticationManager(
            List<AuthenticationProvider> providers) {
        ProviderManager manager = new ProviderManager(providers);
        return manager;
    }
    
    /**
     * 操作人员认证处理过滤器<br/>
     * <功能详细描述>
     * @param authenticationManager
     * @param successHandler
     * @param failureHandler
     * @return [参数说明]
     * 
     * @return OperatorAuthenticationProcessingFilter [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Bean(name = "operatorAuthenticationProcessingFilter")
    public OperatorAuthenticationProcessingFilter operatorAuthenticationProcessingFilter(
            AuthenticationManager authenticationManager,
            AuthenticationSuccessHandler successHandler,
            AuthenticationFailureHandler failureHandler) {
        OperatorAuthenticationProcessingFilter filter = new OperatorAuthenticationProcessingFilter();
        filter.setAuthenticationSuccessHandler(successHandler);
        filter.setAuthenticationFailureHandler(failureHandler);
        
        filter.setAuthenticationManager(authenticationManager);
        return filter;
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
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Bean(name = "clientAuthenticationProcessingFilter")
    public ClientAuthenticationProcessingFilter clientAuthenticationProcessingFilter(
            AuthenticationManager authenticationManager,
            AuthenticationSuccessHandler successHandler,
            AuthenticationFailureHandler failureHandler) {
        ClientAuthenticationProcessingFilter filter = new ClientAuthenticationProcessingFilter();
        filter.setAuthenticationSuccessHandler(successHandler);
        filter.setAuthenticationFailureHandler(failureHandler);
        
        filter.setAuthenticationManager(authenticationManager);
        return filter;
    }
    
    /**
     * 注册登录认证点<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return SecurityLoginAuthenticationEntryPoint [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Bean(name = "loginAuthenticationEntryPoint")
    public SecurityLoginAuthenticationEntryPoint loginAuthenticationEntryPoint() {
        SecurityLoginAuthenticationEntryPoint entry = new SecurityLoginAuthenticationEntryPoint(
                "/login");
        return entry;
    }
}
