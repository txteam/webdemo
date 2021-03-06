package com.tx.front4wap.security.config;
//package com.tx.security4wapclient.config;
//
//import java.util.Arrays;
//import java.util.Collections;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.AutoConfigureBefore;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.annotation.Order;
//import org.springframework.http.MediaType;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.security.web.authentication.switchuser.SwitchUserFilter;
//import org.springframework.security.web.util.matcher.AndRequestMatcher;
//import org.springframework.security.web.util.matcher.MediaTypeRequestMatcher;
//import org.springframework.security.web.util.matcher.NegatedRequestMatcher;
//import org.springframework.security.web.util.matcher.RequestHeaderRequestMatcher;
//import org.springframework.security.web.util.matcher.RequestMatcher;
//import org.springframework.web.accept.ContentNegotiationStrategy;
//import org.springframework.web.accept.HeaderContentNegotiationStrategy;
//
//import com.tx.component.security.filter.SecurityThreadLocalResourceSupportFilter;
//import com.tx.security.config.WebSecurityConfiguration;
//import com.tx.security4front.strategy.ClientSessionAuthenticationStrategy;
//import com.tx.security4wapclient.filter.WapClientAuthenticationProcessingFilter;
//import com.tx.security4wapclient.filter.WapClientWXAuthenticationProcessingFilter;
//import com.tx.security4wapclient.handler.WapClientSecurityAuthenticationFailureHandler;
//import com.tx.security4wapclient.handler.WapClientSecurityAuthenticationSuccessHandler;
//import com.tx.security4wapclient.model.WapClientRoleEnum;
//import com.tx.security4wapclient.provider.WapClientAuthenticationProvider;
//import com.tx.security4wapclient.provider.WapClientSocialAuthenticationProvider;
//
///**
// * SpringSecurity本地权限定制<br/>
// * <功能详细描述>
// * 
// * @author  Administrator
// * @version  [版本号, 2018年7月7日]
// * @see  [相关类/方法]
// * @since  [产品/模块版本]
// */
//@Order(value = 202)
//@AutoConfigureBefore(WebSecurityConfiguration.class)
//@Configuration
//public class WapClientWebSecurityConfiguration
//        extends WebSecurityConfigurerAdapter {
//    
//    /** 日志记录句柄 */
//    protected static Logger logger = LoggerFactory
//            .getLogger(WapClientWebSecurityConfiguration.class);
//    
//    @Autowired
//    private AuthenticationManager authenticationManager;
//    
//    @Autowired
//    private WapClientWXAuthenticationProcessingFilter wapClientWXAuthenticationProcessingFilter;
//    
//    /**
//     * httpSecurity配置
//     * @param http
//     * @throws Exception
//     */
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.antMatcher("/wap/**");
//        
//        //关闭csrf(跨域请求伪造),内部管理系统每次请求没有必要都需要带一个签名token如果需要也问题不大，待access_token机制加入以后再进行调整
//        http.csrf().disable();
//        
//        //iframe header disable
//        http.headers().frameOptions().disable();
//        
//        //配置session
//        //ALWAYS:总是创建HttpSession
//        //IF_REQUIRED:Spring Security只会在需要时创建一个HttpSession
//        //NEVER:Spring Security不会创建HttpSession，但如果它已经存在，将可以使用HttpSession
//        //STATELESS:Spring Security永远不会创建HttpSession，它不会使用HttpSession来获取SecurityContext
//        http.sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
//                .sessionAuthenticationStrategy(sessionStrategy());
//        
//        //login配置，disable后利用filter实现替代
//        http.formLogin().disable();
//        
//        //添加登录过滤器
//        http.addFilterBefore(wapClientAuthenticationProcessingFilter(),
//                UsernamePasswordAuthenticationFilter.class);
//        //添加微信第三方登录过滤器
//        http.addFilterBefore(this.wapClientWXAuthenticationProcessingFilter,
//                UsernamePasswordAuthenticationFilter.class);
//        
//        //logout配置
//        //http.logout().disable();
//        http.logout()
//                .logoutUrl("/wap/client/logout")
//                .logoutSuccessUrl("/wap/client/login");
//        
//        //注册登录入口
//        registerAuthenticationEntryPoint(http);
//        
//        registerAccessDeniedHandler(http);
//        
//        //必须条件该过滤器，不然权限容器中线程变量逻辑会存在问题
//        http.addFilterAfter(new SecurityThreadLocalResourceSupportFilter(),
//                SwitchUserFilter.class);
//        
//        //所有请求都允许访问
//        //http.authorizeRequests().anyRequest().fullyAuthenticated();
//        //首页允许
//        /*http.authorizeRequests()
//                .antMatchers("/wap/client/**", "/wap/client/index")
//                .permitAll();*/
//        //第三方用户登陆
//        http.authorizeRequests()
//                .antMatchers("/wap/client/social/**")
//                .permitAll();
//        //后台登陆页
//        http.authorizeRequests().antMatchers("/wap/client/login").permitAll();
//        http.authorizeRequests().antMatchers("/wap/client/sign").permitAll();
//        
//        //验证码
//        http.authorizeRequests()
//                .antMatchers("/wap/client/captcha/**")
//                .permitAll();
//        
//        //其他
//        http.authorizeRequests()
//                .anyRequest()
//                .hasRole(WapClientRoleEnum.WAP_CLIENT.getId());//需要操作人员角色
//        //http.authorizeRequests().anyRequest().authenticated();//其他请求需要鉴权
//    }
//    
//    /**
//     * 注册登录入口<br/>
//     * <功能详细描述>
//     * @param http
//     * @throws Exception [参数说明]
//     * 
//     * @return void [返回类型说明]
//     * @exception throws [异常类型] [异常说明]
//     * @see [类、类#方法、类#成员]
//     */
//    private void registerAuthenticationEntryPoint(HttpSecurity http)
//            throws Exception {
//        ContentNegotiationStrategy contentNegotiationStrategy = http
//                .getSharedObject(ContentNegotiationStrategy.class);
//        if (contentNegotiationStrategy == null) {
//            contentNegotiationStrategy = new HeaderContentNegotiationStrategy();
//        }
//        
//        MediaTypeRequestMatcher mediaMatcher = new MediaTypeRequestMatcher(
//                contentNegotiationStrategy, MediaType.APPLICATION_XHTML_XML,
//                new MediaType("image", "*"), MediaType.TEXT_HTML,
//                MediaType.TEXT_PLAIN);
//        mediaMatcher.setIgnoredMediaTypes(Collections.singleton(MediaType.ALL));
//        RequestMatcher notXRequestedWith = new NegatedRequestMatcher(
//                new RequestHeaderRequestMatcher("X-Requested-With",
//                        "XMLHttpRequest"));
//        RequestMatcher preferredMatcher = new AndRequestMatcher(
//                Arrays.asList(notXRequestedWith, mediaMatcher));
//        
//        //注入默认的认证入口，异常拦截器
//        http.exceptionHandling().accessDeniedPage("/wap/client/login");
//        http.exceptionHandling()
//                .defaultAuthenticationEntryPointFor(entryPoint(),
//                        preferredMatcher);
//    }
//    
//    /**
//     * 注册登录入口<br/>
//     * <功能详细描述>
//     * @param http
//     * @throws Exception [参数说明]
//     *
//     * @return void [返回类型说明]
//     * @exception throws [异常类型] [异常说明]
//     * @see [类、类#方法、类#成员]
//     */
//    private void registerAccessDeniedHandler(HttpSecurity http)
//            throws Exception {
//        http.exceptionHandling()
//                .accessDeniedHandler(new WapClientSecurityAccessDeniedHandler(
//                        "/error/403.html"));
//    }
//    
//    /**
//     * 访问入口<br/>
//     * <功能详细描述>
//     * @return [参数说明]
//     * 
//     * @return SecurityLoginAuthenticationEntryPoint [返回类型说明]
//     * @exception throws [异常类型] [异常说明]
//     * @see [类、类#方法、类#成员]
//     */
//    @Bean("wap.client.entryPoint")
//    public WapSecurityLoginAuthenticationEntryPoint entryPoint() {
//        WapSecurityLoginAuthenticationEntryPoint point = new WapSecurityLoginAuthenticationEntryPoint(
//                "/wap/client/login");
//        point.setUseForward(true);
//        return point;
//    }
//    
//    /**
//     * 认证成功处理句柄<br/>
//     * <功能详细描述>
//     * @return [参数说明]
//     * 
//     * @return AuthenticationSuccessHandler [返回类型说明]
//     * @exception throws [异常类型] [异常说明]
//     * @see [类、类#方法、类#成员]
//     */
//    @Bean("wap.client.securityAuthenticationSuccessHandler")
//    public WapClientSecurityAuthenticationSuccessHandler successHandler() {
//        WapClientSecurityAuthenticationSuccessHandler handler = new WapClientSecurityAuthenticationSuccessHandler();
//        return handler;
//    }
//    
//    /**
//     * 认证失败处理句柄<br/>
//     * <功能详细描述>
//     * @return [参数说明]
//     * 
//     * @return AuthenticationFailureHandler [返回类型说明]
//     * @exception throws [异常类型] [异常说明]
//     * @see [类、类#方法、类#成员]
//     */
//    @Bean("wap.client.securityAuthenticationFailureHandler")
//    public WapClientSecurityAuthenticationFailureHandler failureHandler() {
//        WapClientSecurityAuthenticationFailureHandler handler = new WapClientSecurityAuthenticationFailureHandler();
//        return handler;
//    }
//    
//    /**
//     * 客户认证处理过滤器<br/>
//     * <功能详细描述>
//     * @return [参数说明]
//     * 
//     * @return ClientAuthenticationProcessingFilter [返回类型说明]
//     * @throws Exception 
//     * @exception throws [异常类型] [异常说明]
//     * @see [类、类#方法、类#成员]
//     */
//    @Bean("wap.client.authenticationProcessingFilter")
//    public WapClientAuthenticationProcessingFilter wapClientAuthenticationProcessingFilter()
//            throws Exception {
//        WapClientAuthenticationProcessingFilter filter = new WapClientAuthenticationProcessingFilter();
//        filter.setAuthenticationSuccessHandler(successHandler());
//        filter.setAuthenticationFailureHandler(failureHandler());
//        
//        filter.setAuthenticationManager(this.authenticationManager);
//        return filter;
//    }
//    
//    /**
//     * 操作人员会话处理过滤器<br/>
//     * <功能详细描述>
//     * @return [参数说明]
//     * 
//     * @return OperatorSessionAuthenticationStrategy [返回类型说明]
//     * @exception throws [异常类型] [异常说明]
//     * @see [类、类#方法、类#成员]
//     */
//    @Bean("wap.client.sessionAuthenticationStrategy")
//    public ClientSessionAuthenticationStrategy sessionStrategy() {
//        ClientSessionAuthenticationStrategy strategy = new ClientSessionAuthenticationStrategy();
//        return strategy;
//    }
//    
//    /**
//    *
//    * <功能详细描述>
//    * @return [参数说明]
//    *
//    * @return ClientSocialAuthenticationProvider [返回类型说明]
//    * @exception throws [异常类型] [异常说明]
//    * @see [类、类#方法、类#成员]
//    */
//    @Bean("wapClientSocialAuthenticationProvider")
//    public WapClientSocialAuthenticationProvider wapClientSocialAuthenticationProvider() {
//        WapClientSocialAuthenticationProvider provider = new WapClientSocialAuthenticationProvider();
//        // 设置userDetailsService
//        provider.setUserDetailsService(clientUserDetailsService());
//        // 禁止隐藏用户未找到异常
//        provider.setHideUserNotFoundExceptions(false);
//        return provider;
//    }
//    
//    /**
//    * WX客户认证处理过滤器<br/>
//    * <功能详细描述>
//    * @param authenticationManager
//    * @return [参数说明]
//    *
//    * @return ClientAuthenticationProcessingFilter [返回类型说明]
//    * @exception throws [异常类型] [异常说明]
//    * @see [类、类#方法、类#成员]
//    */
//    @Bean(name = "wapClientWXAuthenticationProcessingFilter")
//    public WapClientWXAuthenticationProcessingFilter wapClientWXAuthenticationProcessingFilter(
//            AuthenticationManager authenticationManager) {
//        WapClientWXAuthenticationProcessingFilter filter = new WapClientWXAuthenticationProcessingFilter();
//        
//        filter.setAuthenticationManager(authenticationManager);
//        return filter;
//    }
//    
//    /**
//     * Wap客户认证处理器<br/>
//     * <功能详细描述>
//     * @return [参数说明]
//     *
//     * @return ClientAuthenticationProvider [返回类型说明]
//     * @exception throws [异常类型] [异常说明]
//     * @see [类、类#方法、类#成员]
//     */
//    @Bean("wapClientAuthenticationProvider")
//    public WapClientAuthenticationProvider wapClientAuthenticationProvider() {
//        WapClientAuthenticationProvider provider = new WapClientAuthenticationProvider();
//        // 设置userDetailsService
//        provider.setUserDetailsService(clientUserDetailsService());
//        // 禁止隐藏用户未找到异常
//        provider.setHideUserNotFoundExceptions(false);
//        // 使用BCrypt进行密码的hash
//        provider.setPasswordEncoder(clientPasswordEncoder());
//        return provider;
//    }
//    
//}