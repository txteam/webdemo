package com.tx.local.security.config;

import java.util.Arrays;
import java.util.Collections;
import java.util.UUID;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
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
import org.springframework.security.web.util.matcher.AndRequestMatcher;
import org.springframework.security.web.util.matcher.MediaTypeRequestMatcher;
import org.springframework.security.web.util.matcher.NegatedRequestMatcher;
import org.springframework.security.web.util.matcher.RequestHeaderRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.accept.ContentNegotiationStrategy;
import org.springframework.web.accept.HeaderContentNegotiationStrategy;

import com.tx.local.operator.model.OperatorRoleEnum;
import com.tx.local.security.filter.OperatorAuthenticationProcessingFilter;
import com.tx.local.security.handler.OperatorSecurityAuthenticationFailureHandler;
import com.tx.local.security.handler.OperatorSecurityAuthenticationSuccessHandler;
import com.tx.local.security.model.DefaultPasswordEncoder;
import com.tx.local.security.provider.OperatorLoginFormAuthenticationProvider;
import com.tx.local.security.provider.OperatorSocialAuthenticationProvider;
import com.tx.local.security.service.OperatorRememberMeServices;
import com.tx.local.security.service.OperatorRememberMeTokenJwtService;
import com.tx.local.security.service.OperatorRememberMeTokenRepository;
import com.tx.local.security.service.OperatorUserDetailsService;
import com.tx.local.security.strategy.OperatorSessionAuthenticationStrategy;
import com.tx.local.springmvc.path.LocalPathReplaceUtils;

/**
 * SpringSecurity本地权限定制<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2018年7月7日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Order(value = 300)
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter
        implements InitializingBean {
    
    /** 日志记录句柄 */
    protected static Logger logger = LoggerFactory
            .getLogger(WebSecurityConfiguration.class);
    
    private static final String DEFAULT_REMEMBER_ME_NAME = "remember-me";
    
    private String rememberMeParameter = DEFAULT_REMEMBER_ME_NAME;
    
    private String rememberMeCookieName = DEFAULT_REMEMBER_ME_NAME;
    
    @Resource
    private DataSource datasource;
    
    private String logoutUrl = "/operator/logout";
    
    private String loginUrl = "/login";
    
    private String loginProcessingUrl = "/operator/login";
    
    @SuppressWarnings("unused")
    private String socialLoginProcessingUrl = "/operator/social/login/**";
    
    private String targetUrl = "/mainframe";
    
    /** rememberMe token 有效时间 */
    private int tokenValiditySeconds = 1000 * 60 * 60 * 24;
    
    private String rememberMeCookieDomain;
    
    private Boolean useSecureCookie;
    
    private Boolean alwaysRemember;
    
    @Resource
    private LocalPathReplaceUtils localPathReplaceUtils;
    
    /**
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        this.logoutUrl = this.localPathReplaceUtils.transform(this.logoutUrl);
        this.loginUrl = this.localPathReplaceUtils.transform(this.loginUrl);
        this.loginProcessingUrl = this.localPathReplaceUtils
                .transform(this.loginProcessingUrl);
        this.socialLoginProcessingUrl = this.localPathReplaceUtils
                .transform(this.socialLoginProcessingUrl);
    }
    
    /**
     * webSecurity配置
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        //静态资源文件
        web.ignoring()
                .antMatchers(this.localPathReplaceUtils.transform("/*.txt"));//微信的认证文件
        
        web.ignoring()
                .antMatchers(
                        this.localPathReplaceUtils.transform("/webjars/**"));//webjars中的资源
        //资源类型文件
        web.ignoring()
                .antMatchers(this.localPathReplaceUtils
                        .transform("css/**", "images/**", "js/**"));
        web.ignoring()
                .antMatchers(this.localPathReplaceUtils.transform("/**/*.js",
                        "/**/*.css",
                        "/**/*.woff2",
                        "/**/*.jpg",
                        "/**/*.png",
                        "/**/*.svg",
                        "/**/*.ico"));
        
        //性能监控
        web.ignoring()
                .antMatchers("/actuator/metrics/**",
                        "/actuator/httptrace/**",
                        "/actuator/redis/**");
        //druid
        web.ignoring().antMatchers("/druid/**", "/druid/**");
        //swagger
        web.ignoring().antMatchers("/swagger-ui.html", "/swagger**/**");
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
        //        http.addFilterBefore(operatorSocialAuthenticationProcessingFilter(),
        //                UsernamePasswordAuthenticationFilter.class);6
        
        //rememberMe的配置
        OperatorRememberMeServices rememberMeServices = rememberMeServices();
        http.rememberMe()
                .rememberMeServices(rememberMeServices)
                .key(rememberMeServices.getKey())
                .authenticationSuccessHandler(successHandler());
        
        //logout配置
        http.logout().logoutUrl(this.logoutUrl).logoutSuccessUrl(this.loginUrl);
        
        //注册登录入口
        //注册认证用户，无权限的处理
        http.exceptionHandling().accessDeniedPage(this.loginUrl);
        registerAuthenticationEntryPoint(http);
        
        //登陆
        http.authorizeRequests()
                .antMatchers(this.loginUrl, this.loginProcessingUrl)
                .permitAll();
        
        //允许兼容呈现
        http.authorizeRequests()
                .antMatchers(
                        this.localPathReplaceUtils.transform("/", "/index"))
                .permitAll();
        //验证码
        http.authorizeRequests()
                .antMatchers(
                        this.localPathReplaceUtils.transform("/captcha/**"))
                .permitAll();
        
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
                        new LoginUrlAuthenticationEntryPoint(this.loginUrl),
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
     * 生成rememberMeServices
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return RememberMeServices [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Bean("operator.rememberMeServices")
    public OperatorRememberMeServices rememberMeServices() {
        //创建rememberMeServices: 通过自定义实现覆写token生成规则
        OperatorRememberMeServices tokenRememberMeServices = new OperatorRememberMeServices(
                UUID.randomUUID().toString(), operatorUserDetailsService());
        tokenRememberMeServices.setTokenRepository(rememberMeTokenRepository());
        //写入token生成业务层
        tokenRememberMeServices
                .setRememberMeTokenJwtService(rememberMeTokenJwtService());
        tokenRememberMeServices.setParameter(this.rememberMeParameter);
        tokenRememberMeServices.setCookieName(this.rememberMeCookieName);
        tokenRememberMeServices
                .setTokenValiditySeconds(this.tokenValiditySeconds);
        if (this.rememberMeCookieDomain != null) {
            tokenRememberMeServices
                    .setCookieDomain(this.rememberMeCookieDomain);
        }
        if (this.useSecureCookie != null) {
            tokenRememberMeServices.setUseSecureCookie(this.useSecureCookie);
        }
        if (this.alwaysRemember != null) {
            tokenRememberMeServices.setAlwaysRemember(this.alwaysRemember);
        }
        return tokenRememberMeServices;
    }
    
    /**
     * rememberMeTokenRepository<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return OperatorRememberMeTokenRepository [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Bean("operator.rememberMeTokenRepository")
    public OperatorRememberMeTokenRepository rememberMeTokenRepository() {
        OperatorRememberMeTokenRepository repository = new OperatorRememberMeTokenRepository(
                this.tokenValiditySeconds);
        repository.setDataSource(this.datasource);
        return repository;
    }
    
    /**
     * 操作人员RememberMeTokenJwtService
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return OperatorRememberMeTokenJwtService [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Bean("operator.rememberMeTokenJwtService")
    public OperatorRememberMeTokenJwtService rememberMeTokenJwtService() {
        OperatorRememberMeTokenJwtService jwtService = new OperatorRememberMeTokenJwtService();
        jwtService.setSigningKey("secret");
        return jwtService;
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
                this.loginProcessingUrl);
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
    //    @Bean("operator.socialAuthenticationProcessingFilter")
    //    public OperatorSocialAuthenticationProcessingFilter operatorSocialAuthenticationProcessingFilter()
    //            throws Exception {
    //        OperatorSocialAuthenticationProcessingFilter filter = new OperatorSocialAuthenticationProcessingFilter(
    //                this.SOCIAL_LOGIN_PROCESSING_URL);
    //        filter.setAuthenticationSuccessHandler(successHandler());
    //        filter.setAuthenticationFailureHandler(failureHandler());
    //        filter.setAuthenticationManager(authenticationManagerBean());
    //        return filter;
    //    }
    
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
                this.targetUrl);
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
                this.loginUrl);
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
