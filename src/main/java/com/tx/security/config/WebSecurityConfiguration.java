package com.tx.security.config;

import java.util.Arrays;
import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.switchuser.SwitchUserFilter;
import org.springframework.security.web.util.matcher.AndRequestMatcher;
import org.springframework.security.web.util.matcher.MediaTypeRequestMatcher;
import org.springframework.security.web.util.matcher.NegatedRequestMatcher;
import org.springframework.security.web.util.matcher.RequestHeaderRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.accept.ContentNegotiationStrategy;
import org.springframework.web.accept.HeaderContentNegotiationStrategy;

import com.tx.component.security.filter.SecurityThreadLocalResourceSupportFilter;
import com.tx.local.operator.model.OperatorRoleEnum;
import com.tx.security.filter.OperatorAuthenticationProcessingFilter;
import com.tx.security.filter.OperatorSocialAuthenticationProcessingFilter;
import com.tx.security.handler.OperatorSecurityAuthenticationFailureHandler;
import com.tx.security.handler.OperatorSecurityAuthenticationSuccessHandler;
import com.tx.security.model.DefaultPasswordEncoder;
import com.tx.security.provider.OperatorLoginFormAuthenticationProvider;
import com.tx.security.provider.OperatorSocialAuthenticationProvider;
import com.tx.security.service.OperatorUserDetailsService;
import com.tx.security.strategy.OperatorSessionAuthenticationStrategy;

/**
 * SpringSecurity本地权限定制<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2018年7月7日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Order(value = 200)
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
    
    /** 日志记录句柄 */
    protected static Logger logger = LoggerFactory
            .getLogger(WebSecurityConfiguration.class);
    
    private final String LOGOUT_URL = "/operator/logout";
    
    private final String LOGIN_URL = "/login";
    
    private final String LOGIN_PROCESSING_URL = "/operator/login";
    
    private final String SOCIAL_LOGIN_PROCESSING_URL = "/operator/social/login";
    
    private final String TARGET_URL = "/mainframe";
    
    /**
     * webSecurity配置
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        //静态资源文件
        web.ignoring().antMatchers("/*.txt");//微信的认证文件
        
        web.ignoring().antMatchers("/webjars/**");//webjars中的资源
        //资源类型文件
        web.ignoring().antMatchers("css/**", "images/**", "js/**");
        web.ignoring()
                .antMatchers("/**/*.js",
                        "/**/*.css",
                        "/**/*.woff2",
                        "/**/*.jpg",
                        "/**/*.png",
                        "/**/*.svg",
                        "/**/*.ico");
        
        //性能监控
        web.ignoring()
                .antMatchers("/actuator/metrics/**",
                        "/actuator/httptrace/**",
                        "/actuator/redis/**");
        
        //swagger
        web.ignoring().antMatchers("/swagger-ui.html", "/swagger**/**");
        
        //druid
        web.ignoring().antMatchers("/druid/**", "/druid/**");
        
        //uploads
        //web.ignoring().antMatchers("uploads/**");
        //filterChainDefinitionMap.put("/doc.html", "anon");
        //filterChainDefinitionMap.put("/v2/**", "anon");
    }
    
    /**
     * httpSecurity配置
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
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
                .sessionAuthenticationStrategy(
                        operatorSessionAuthenticationStrategy());
        
        //添加登录过滤器
        //login配置，disable后利用filter实现替代
        http.formLogin().disable();
        //操作人员认证
        http.addFilterBefore(operatorAuthenticationProcessingFilter(),
                UsernamePasswordAuthenticationFilter.class);
        //第三方用户登陆
        http.addFilterBefore(operatorSocialAuthenticationProcessingFilter(),
                UsernamePasswordAuthenticationFilter.class);
        
        //logout配置
        http.logout()
                .logoutUrl(this.LOGOUT_URL)
                .logoutSuccessUrl(this.LOGIN_URL);
        
        //注册登录入口
        //注册认证用户，无权限的处理
        http.exceptionHandling().accessDeniedPage(this.LOGIN_URL);
        registerAuthenticationEntryPoint(http);
        
        //必须条件该过滤器，不然权限容器中线程变量逻辑会存在问题
        http.addFilterAfter(new SecurityThreadLocalResourceSupportFilter(),
                SwitchUserFilter.class);
        
        //允许兼容呈现
        http.authorizeRequests().antMatchers("/", "/index").permitAll();
        
        //登陆
        http.authorizeRequests()
                .antMatchers(this.LOGIN_URL, this.LOGIN_PROCESSING_URL)
                .permitAll();
        //第三方用户登陆
        http.authorizeRequests()
                .antMatchers("/operator/social/login/**")
                .permitAll();
        
        //验证码
        http.authorizeRequests().antMatchers("/captcha/**").permitAll();
        
        //所有请求都允许访问
        //允许直接访问的链接
        //接口不验证权限:后续可添加对指定ip地址不进行鉴权的控制
        http.authorizeRequests().antMatchers("/api/**").permitAll();
        //允许进入登陆页面
        http.authorizeRequests().antMatchers("/error/**").permitAll();
        //其他
        http.authorizeRequests().antMatchers("/oauth/authorize").permitAll();
        
        //其他请求需要鉴权
        http.authorizeRequests()
                .anyRequest()
                .hasAnyRole(OperatorRoleEnum.OPERATOR.getId());//需要操作人员角色
        //http.authorizeRequests().anyRequest().authenticated();//其他请求需要鉴权
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
        auth.authenticationProvider(operatorLoginFormAuthenticationProvider());
        auth.authenticationProvider(operatorSocialAuthenticationProvider());
    }
    
    /**
     * 认证Manager实例<br/>
     * @return
     * @throws Exception
     */
    @Override
    @Bean("authenticationManager")
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
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
     * @throws Exception 
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Bean("operator.authenticationProcessingFilter")
    public OperatorAuthenticationProcessingFilter operatorAuthenticationProcessingFilter()
            throws Exception {
        OperatorAuthenticationProcessingFilter filter = new OperatorAuthenticationProcessingFilter(
                this.LOGIN_PROCESSING_URL);
        filter.setAuthenticationSuccessHandler(successHandler());
        filter.setAuthenticationFailureHandler(failureHandler());
        filter.setAuthenticationManager(authenticationManagerBean());
        return filter;
    }
    
    /**
     * 第三方用户登陆拦截器<br/>
     * <功能详细描述>
     * @param authenticationManager
     * @return [参数说明]
     * 
     * @return OperatorSocialAuthenticationProcessingFilter [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Bean("operator.socialAuthenticationProcessingFilter")
    public OperatorSocialAuthenticationProcessingFilter operatorSocialAuthenticationProcessingFilter()
            throws Exception {
        OperatorSocialAuthenticationProcessingFilter filter = new OperatorSocialAuthenticationProcessingFilter(
                this.SOCIAL_LOGIN_PROCESSING_URL);
        filter.setAuthenticationSuccessHandler(successHandler());
        filter.setAuthenticationFailureHandler(failureHandler());
        filter.setAuthenticationManager(authenticationManagerBean());
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
    @Bean("operator.securityAuthenticationSuccessHandler")
    public OperatorSecurityAuthenticationSuccessHandler successHandler() {
        OperatorSecurityAuthenticationSuccessHandler handler = new OperatorSecurityAuthenticationSuccessHandler(
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
    @Bean("operator.securityAuthenticationFailureHandler")
    public OperatorSecurityAuthenticationFailureHandler failureHandler() {
        OperatorSecurityAuthenticationFailureHandler handler = new OperatorSecurityAuthenticationFailureHandler(
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
    @Bean("operator.sessionAuthenticationStrategy")
    public OperatorSessionAuthenticationStrategy operatorSessionAuthenticationStrategy() {
        OperatorSessionAuthenticationStrategy strategy = new OperatorSessionAuthenticationStrategy();
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
    @Bean("passwordEncoder")
    public PasswordEncoder passwordEncoder() {
        PasswordEncoder encoder = new DefaultPasswordEncoder();
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
        provider.setPasswordEncoder(passwordEncoder());
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
    
}
