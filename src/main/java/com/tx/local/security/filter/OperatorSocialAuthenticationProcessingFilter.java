package com.tx.local.security.filter;
///*
// * 描          述:  <描述>
// * 修  改   人:  Administrator
// * 修改时间:  2019年7月23日
// * <修改描述:>
// */
//package com.tx.security.filter;
//
//import java.io.IOException;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//import javax.annotation.Resource;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//
//import com.tx.component.security.plugin.LoginPlugin;
//import com.tx.component.security.plugin.model.LoginAccessToken;
//import com.tx.core.exceptions.SILException;
//import com.tx.local.operator.model.OperSocialAccount;
//import com.tx.local.operator.model.OperSocialAccountTypeEnum;
//import com.tx.local.operator.service.OperSocialAccountService;
//import com.tx.plugin.login.LoginPluginUtils;
//import com.tx.security.exception.SocialUserLoginException;
//import com.tx.security.model.OperatorSocialAuthenticationToken;
//import com.tx.security.service.OperatorUserDetailsService;
//
///**
// * 操作权认证处理过滤器<br/>
// * <功能详细描述>
// * 
// * @author  Administrator
// * @version  [版本号, 2019年7月23日]
// * @see  [相关类/方法]
// * @since  [产品/模块版本]
// */
//public class OperatorSocialAuthenticationProcessingFilter
//        extends AbstractAuthenticationProcessingFilter {
//    
//    @Resource
//    private OperSocialAccountService operSocialAccountService;
//    
//    @Resource
//    private OperatorUserDetailsService operatorUserDetailsService;
//    
//    //请求的url表达式
//    private static final Pattern URL_PATTERN = Pattern
//            .compile("^/operator/social/login/(.+?)([/|\\?].*)*$");
//    
//    /** <默认构造函数> */
//    public OperatorSocialAuthenticationProcessingFilter() {
//        super(new AntPathRequestMatcher("/operator/social/login", "GET"));
//    }
//    
//    /** <默认构造函数> */
//    public OperatorSocialAuthenticationProcessingFilter(
//            String socialLoginProcessingUrl) {
//        super(new AntPathRequestMatcher(socialLoginProcessingUrl, "GET"));
//    }
//    
//    /**
//     * @param request
//     * @param response
//     * @return
//     * @throws AuthenticationException
//     * @throws IOException
//     * @throws ServletException
//     */
//    @Override
//    public Authentication attemptAuthentication(HttpServletRequest request,
//            HttpServletResponse response)
//            throws AuthenticationException, IOException, ServletException {
//        String servletUrl = request.getServletPath();
//        Matcher m = URL_PATTERN.matcher(servletUrl);
//        if (!m.matches()) {
//            throw new SocialUserLoginException(
//                    "解析servletUrl中的plugin值失败.servletUrl:" + servletUrl);
//        }
//        String plugin = m.group(1);
//        LoginPlugin<?> loginPlugin = LoginPluginUtils.getLoginPlugin(plugin);
//        if (loginPlugin == null) {
//            throw new SocialUserLoginException("插件未找到.plugin:" + plugin);
//        }
//        
//        String code = request.getParameter("code");
//        String state = request.getParameter("state");
//        LoginAccessToken token = null;
//        String uniqueId = null;
//        try {
//            token = loginPlugin.getAccessToken(code, state, request);
//            uniqueId = loginPlugin.getUniqueId(token, request);
//        } catch (SocialUserLoginException | SILException e) {
//            throw new SocialUserLoginException("获取第三方唯一键异常.", e);
//        } catch (Exception e) {
//            throw new SocialUserLoginException("获取第三方唯一键异常.", e);
//        }
//        
//        OperSocialAccountTypeEnum type = LoginPluginUtils
//                .getOperSocialTypeByPlugin(plugin);
//        if (type == null) {
//            throw new SocialUserLoginException("关联类型解析异常.plugin:" + plugin);
//        }
//        OperSocialAccount acc = this.operSocialAccountService
//                .findByUniqueId(uniqueId, type);
//        if (acc == null) {
//            throw new SocialUserLoginException("没有关联的账号 .");
//        }
//        
//        String userId = acc.getOperatorId();
//        String accessToken = token.getAccessToken();
//        
//        OperatorSocialAuthenticationToken authenticationToken = new OperatorSocialAuthenticationToken(
//                userId, accessToken);
//        // Allow subclasses to set the "details" property
//        setDetails(request, authenticationToken);
//        
//        return this.getAuthenticationManager()
//                .authenticate(authenticationToken);
//    }
//    
//    /**
//     * Provided so that subclasses may configure what is put into the authentication
//     * request's details property.
//     *
//     * @param request that an authentication request is being created for
//     * @param authRequest the authentication request object that should have its details
//     * set
//     */
//    protected void setDetails(HttpServletRequest request,
//            OperatorSocialAuthenticationToken authRequest) {
//        authRequest
//                .setDetails(authenticationDetailsSource.buildDetails(request));
//    }
//    
//    @Override
//    protected boolean requiresAuthentication(HttpServletRequest request,
//            HttpServletResponse response) {
//        return super.requiresAuthentication(request, response);
//    }
//    
//}
