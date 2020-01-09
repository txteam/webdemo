package com.tx.local.security.config;

import java.util.Arrays;
import java.util.Collections;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
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
import com.tx.local.security.entrypoint.SecurityLoginAuthenticationEntryPoint;
import com.tx.local.security.filter.ClientAuthenticationProcessingFilter;
import com.tx.local.security.filter.OperatorAuthenticationProcessingFilter;
import com.tx.local.security.filter.OperatorSocialAuthenticationProcessingFilter;
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
@Import(value = { WebSecurityConfigurationImporter.class })
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
    
    /** 日志记录句柄 */
    protected static Logger logger = LoggerFactory
            .getLogger(WebSecurityConfiguration.class);
    
    @Autowired
    private UserSessionAuthenticationStrategy userSessionAuthenticationStrategy;
    
    @Autowired
    private OperatorSocialAuthenticationProcessingFilter operatorSocialAuthenticationProcessingFilter;
    
    @Autowired
    private OperatorAuthenticationProcessingFilter operatorAuthenticationProcessingFilter;
    
    @Autowired
    private ClientAuthenticationProcessingFilter clientAuthenticationProcessingFilter;
    
    @Resource(name = "loginAuthenticationEntryPoint")
    private SecurityLoginAuthenticationEntryPoint loginAuthenticationEntryPoint;
    
    /**
     * webSecurity配置
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/webjars/**");
        web.ignoring().antMatchers("css/**", "images/**", "js/**");
        web.ignoring().antMatchers("/**/*.js",
                "/**/*.css",
                "/**/*.jpg",
                "/**/*.png",
                "/**/*.woff2",
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
    
    ///**
    // * 用户验证
    // * @param auth
    // * @throws Exception
    // */
    //@Override
    //protected void configure(AuthenticationManagerBuilder builder)
    //        throws Exception {
    //    //AuthenticationProvider upProvider = usernamePasswordAuthenticationProvider();
    //    //注入usernamePasswordAuthenticationProvider
    //    //auth.authenticationProvider(upProvider);
    //    //auth.userDetailsService(userDetailsService)
    //}
    
    /**
     * httpSecurity配置
     * @param httpSecurity
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
                        userSessionAuthenticationStrategy);
        
        //logout配置
        //http.logout().disable();
        http.logout().logoutUrl("/logout").logoutSuccessUrl("/background/login");
        
        //login配置，disable后利用filter实现替代
        http.formLogin().disable();
        //http.formLogin().loginPage(loginPage)
        
        //注册登录入口
        registerAuthenticationEntryPoint(http);
        
        //添加登录过滤器
        http.addFilterBefore(this.operatorSocialAuthenticationProcessingFilter,
                UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(this.operatorAuthenticationProcessingFilter,
                UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(this.clientAuthenticationProcessingFilter,
                UsernamePasswordAuthenticationFilter.class);
        //必须条件该过滤器，不然权限容器中线程变量逻辑会存在问题
        http.addFilterAfter(new SecurityThreadLocalResourceSupportFilter(),
                SwitchUserFilter.class);
        
        //.usernameParameter("username").passwordParameter("password").loginPage("/login")
        //.loginProcessingUrl(this.loginProcessingUrl).permitAll().successHandler(successHandler).failureHandler(failureHandler);
        //http.authorizeRequests().antMatchers("")
        //.hasIpAddress(ipaddressExpression)
        //.hasRole(role)
        //.hasAuthority(authority)
        //.hasAnyRole(roles)
        //.hasAnyAuthority(authorities)
        //所有请求都允许访问
        //允许直接访问的链接
        //接口不验证权限:后续可添加对指定ip地址不进行鉴权的控制
        http.authorizeRequests().antMatchers("/api/**").permitAll();
        
        //允许进入登陆页面
        http.authorizeRequests().antMatchers("/error/**").permitAll();
        http.authorizeRequests()
                .antMatchers("/", "/index", "/login", "/client/login")
                .permitAll();
        
        //第三方用户登陆
        http.authorizeRequests()
                .antMatchers("/operator/social/**", "/loginplugin/**")
                .permitAll();
        //后台登陆页
        http.authorizeRequests()
                .antMatchers("/background",
                        "/background/",
                        "/background/index",
                        "/background/login",
                        "/operator/login")
                .permitAll();
        //验证码
        http.authorizeRequests().antMatchers("/captcha/**").permitAll();
        
        //其他
        http.authorizeRequests()
                .antMatchers("/test/**")
                .permitAll()
                .antMatchers("/test/authentication/**")
                .authenticated()
                .antMatchers("/oauth/authorize")
                .permitAll()
                .anyRequest()
                .authenticated();//其他请求需要鉴权
        
        //用户名密码验证前先验证  验证码
        //http.addFilterBefore(captchaValidateAuthenticationFilter(),
        //                UsernamePasswordAuthenticationFilter.class);
        //http.addFilterAt(filter, atFilter)
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
        http.exceptionHandling().defaultAuthenticationEntryPointFor(
                this.loginAuthenticationEntryPoint, preferredMatcher);
    }
}
