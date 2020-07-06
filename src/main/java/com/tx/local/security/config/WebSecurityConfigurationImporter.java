package com.tx.local.security.config;

import java.util.List;

import com.tx.local.security.filter.WapClientWXAuthenticationProcessingFilter;
import com.tx.local.security.provider.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.tx.local.security.model.ClientPasswordEncoder;
import com.tx.local.security.model.OperatorPasswordEncoder;
import com.tx.local.security.service.ClientUserDetailsService;
import com.tx.local.security.service.OperatorUserDetailsService;

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
    @Bean("operatorLoginFormAuthenticationProvider")
    public OperatorLoginFormAuthenticationProvider operatorLoginFormAuthenticationProvider() {
        OperatorLoginFormAuthenticationProvider provider = new OperatorLoginFormAuthenticationProvider();
        // 设置userDetailsService
        provider.setUserDetailsService(operatorUserDetailsService());
        // 禁止隐藏用户未找到异常
        provider.setHideUserNotFoundExceptions(false);
        // 使用BCrypt进行密码的hash
        provider.setPasswordEncoder(operatorPasswordEncoder());
        return provider;
    }
    
    /**
     * 第三方用户登陆Provider<br/>
     * <功能详细描述>
     * @param operatorUserDetailsService
     * @return [参数说明]
     * 
     * @return OperatorSocialAuthenticationProvider [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Bean("operatorSocialAuthenticationProvider")
    public OperatorSocialAuthenticationProvider operatorSocialAuthenticationProvider() {
        OperatorSocialAuthenticationProvider provider = new OperatorSocialAuthenticationProvider();
        // 设置userDetailsService
        provider.setUserDetailsService(operatorUserDetailsService());
        // 禁止隐藏用户未找到异常
        provider.setHideUserNotFoundExceptions(false);
        return provider;
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


    /**
     * Wap客户认证处理器<br/>
     * <功能详细描述>
     * @return [参数说明]
     *
     * @return ClientAuthenticationProvider [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Bean("wapClientAuthenticationProvider")
    public WapClientAuthenticationProvider wapClientAuthenticationProvider() {
        WapClientAuthenticationProvider provider = new WapClientAuthenticationProvider();
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
    @Bean("wapClientSocialAuthenticationProvider")
    public WapClientSocialAuthenticationProvider wapClientSocialAuthenticationProvider() {
        WapClientSocialAuthenticationProvider provider = new WapClientSocialAuthenticationProvider();
        // 设置userDetailsService
        provider.setUserDetailsService(clientUserDetailsService());
        // 禁止隐藏用户未找到异常
        provider.setHideUserNotFoundExceptions(false);
        return provider;
    }

    /**
     * WX客户认证处理过滤器<br/>
     * <功能详细描述>
     * @param authenticationManager
     * @return [参数说明]
     *
     * @return ClientAuthenticationProcessingFilter [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Bean(name = "wapClientWXAuthenticationProcessingFilter")
    public WapClientWXAuthenticationProcessingFilter wapClientWXAuthenticationProcessingFilter(
            AuthenticationManager authenticationManager) {
        WapClientWXAuthenticationProcessingFilter filter = new WapClientWXAuthenticationProcessingFilter();

        filter.setAuthenticationManager(authenticationManager);
        return filter;
    }

    /**
     * AuthenticationManager实例化<br/>
     * <功能详细描述>
     * @param providers
     * @return [参数说明]
     * 
     * @return AuthenticationManager [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Bean
    public AuthenticationManager authenticationManager(
            List<AuthenticationProvider> providers) {
        ProviderManager pm = new ProviderManager(providers);
        return pm;
    }
}
