package com.tx.local.security.config;

import java.util.Arrays;
import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
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
import com.tx.local.security.entrypoint.OperatorSecurityAccessDeniedHandler;
import com.tx.local.security.entrypoint.OperatorSecurityLoginAuthenticationEntryPoint;
import com.tx.local.security.filter.OperatorAuthenticationProcessingFilter;
import com.tx.local.security.filter.OperatorSocialAuthenticationProcessingFilter;
import com.tx.local.security.handler.OperatorSecurityAuthenticationFailureHandler;
import com.tx.local.security.handler.OperatorSecurityAuthenticationSuccessHandler;
import com.tx.local.security.strategy.OperatorSessionAuthenticationStrategy;

/**
 * SpringSecurity本地权限定制<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2018年7月7日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Import(value = { WebSecurityConfigurationImporter.class })
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
    
    /** 日志记录句柄 */
    protected static Logger logger = LoggerFactory
            .getLogger(WebSecurityConfiguration.class);
    
    @Autowired
    private AuthenticationManager authenticationManager;
    
    /**
     * webSecurity配置
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        //静态资源文件
        web.ignoring().antMatchers("/*.txt");//微信的认证文件
        web.ignoring().antMatchers("/webjars/**");
        web.ignoring().antMatchers("css/**", "images/**", "js/**");
        web.ignoring().antMatchers("/**/*.js",
                "/**/*.css",
                "/**/*.woff2",
                "/**/*.jpg",
                "/**/*.png",
                "/**/*.svg",
                "/**/*.ico");
        
        //性能监控
        web.ignoring().antMatchers("/actuator/metrics/**",
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
        http.addFilterBefore(operatorSocialAuthenticationProcessingFilter(),
                UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(operatorAuthenticationProcessingFilter(),
                UsernamePasswordAuthenticationFilter.class);
        
        //logout配置
        //http.logout().disable();
        http.logout()
                .logoutUrl("/operator/logout")
                .logoutSuccessUrl("/admin/login");
        
        //注册登录入口
        registerAuthenticationEntryPoint(http);
        //注册认证用户，无权限的处理
        registerAccessDeniedHandler(http);
        
        //必须条件该过滤器，不然权限容器中线程变量逻辑会存在问题
        http.addFilterAfter(new SecurityThreadLocalResourceSupportFilter(),
                SwitchUserFilter.class);
        
        //允许兼容呈现
        http.authorizeRequests().antMatchers("/", "/index").permitAll();
        http.authorizeRequests()
                .antMatchers("/background/", "/background/index")
                .permitAll();
        http.authorizeRequests()
                .antMatchers("/admin/", "/admin/index")
                .permitAll();
        
        //第三方用户登陆
        http.authorizeRequests().antMatchers("/operator/social/**").permitAll();
        //后台登陆页
        http.authorizeRequests()
                .antMatchers("/login", "/background/login", "/admin/login")
                .permitAll();
        http.authorizeRequests().antMatchers("/operator/sign").permitAll();
        
        //验证码
        http.authorizeRequests().antMatchers("/captcha/**").permitAll();
        
        //所有请求都允许访问
        //允许直接访问的链接
        //接口不验证权限:后续可添加对指定ip地址不进行鉴权的控制
        http.authorizeRequests().antMatchers("/api/**").permitAll();
        //允许进入登陆页面
        http.authorizeRequests().antMatchers("/error/**").permitAll();
        
        //其他
        http.authorizeRequests()
                .antMatchers("/test/**")
                .permitAll()
                .antMatchers("/test/authentication/**")
                .authenticated()
                .antMatchers("/oauth/authorize")
                .permitAll();
        
        //其他请求需要鉴权
        http.authorizeRequests()
                .anyRequest()
                .hasRole(OperatorRoleEnum.OPERATOR.getId());//需要操作人员角色
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
        //已认证用户的无权限控制
        //未认证用户的无权限控制
        http.exceptionHandling().defaultAuthenticationEntryPointFor(
                new OperatorSecurityLoginAuthenticationEntryPoint(
                        "/admin/login"), preferredMatcher);
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
    private void registerAccessDeniedHandler(HttpSecurity http)
            throws Exception {
        http.exceptionHandling().accessDeniedHandler(new OperatorSecurityAccessDeniedHandler("/error/403.html"));
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
    public OperatorSecurityAuthenticationSuccessHandler operatorSecurityAuthenticationSuccessHandler() {
        OperatorSecurityAuthenticationSuccessHandler handler = new OperatorSecurityAuthenticationSuccessHandler();
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
    public OperatorSecurityAuthenticationFailureHandler operatorSecurityAuthenticationFailureHandler() {
        OperatorSecurityAuthenticationFailureHandler handler = new OperatorSecurityAuthenticationFailureHandler();
        return handler;
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
        OperatorAuthenticationProcessingFilter filter = new OperatorAuthenticationProcessingFilter();
        filter.setAuthenticationSuccessHandler(
                operatorSecurityAuthenticationSuccessHandler());
        filter.setAuthenticationFailureHandler(
                operatorSecurityAuthenticationFailureHandler());
        filter.setAuthenticationManager(this.authenticationManager);
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
        OperatorSocialAuthenticationProcessingFilter filter = new OperatorSocialAuthenticationProcessingFilter();
        filter.setAuthenticationSuccessHandler(
                operatorSecurityAuthenticationSuccessHandler());
        filter.setAuthenticationFailureHandler(
                operatorSecurityAuthenticationFailureHandler());
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
    @Bean("operator.sessionAuthenticationStrategy")
    public OperatorSessionAuthenticationStrategy operatorSessionAuthenticationStrategy() {
        OperatorSessionAuthenticationStrategy strategy = new OperatorSessionAuthenticationStrategy();
        return strategy;
    }
    
}
