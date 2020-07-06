/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2019年7月23日
 * <修改描述:>
 */
package com.tx.local.security.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tx.component.role.RoleConstants;
import com.tx.component.role.model.Role;
import com.tx.component.role.model.RoleRef;
import com.tx.core.exceptions.util.AssertUtils;
import com.tx.local.security.model.ClientUserDetails;
import com.tx.plugin.login.exception.UserIdNotFoundException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.tx.core.exceptions.SILException;
import com.tx.local.clientinfo.model.ClientSocialAccount;
import com.tx.local.clientinfo.model.ClientSocialAccountTypeEnum;
import com.tx.local.clientinfo.service.ClientSocialAccountService;
import com.tx.local.security.exception.SocialUserLoginException;
import com.tx.local.security.model.ClientSocialAuthenticationToken;
import com.tx.local.security.service.ClientUserDetailsService;
import com.tx.plugin.login.LoginPlugin;
import com.tx.plugin.login.LoginPluginUtils;
import com.tx.plugin.login.model.LoginAccessToken;
import org.springframework.util.Assert;

/**
 * 操作权认证处理过滤器<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2019年7月23日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class WapClientWXAuthenticationProcessingFilter
        extends AbstractAuthenticationProcessingFilter {
    
    @Resource
    private ClientSocialAccountService clientSocialAccountService;
    
    @Resource
    private ClientUserDetailsService clientUserDetailsService;
    
    /** <默认构造函数> */
    public WapClientWXAuthenticationProcessingFilter() {
        super(new AntPathRequestMatcher("/wap/client/fillreport/**", "GET"));
        SimpleUrlAuthenticationFailureHandler failureHanlder = new SimpleUrlAuthenticationFailureHandler(
                "/wap/client/social/register");
        failureHanlder.setUseForward(true);
        setAuthenticationFailureHandler(failureHanlder);
    }
    
    /**
     * 是否有需要进行认证<br/>
     * @param request
     * @param response
     * @return
     */
    protected boolean requiresAuthentication(HttpServletRequest request,
            HttpServletResponse response) {
        if (StringUtils.isEmpty(request.getParameter("code"))) {
            return false;
        }
        return super.requiresAuthentication(request, response);
    }
    
    /**
     * @param request
     * @param response
     * @return
     * @throws AuthenticationException
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
            HttpServletResponse response)
            throws AuthenticationException, IOException, ServletException {
        LoginPlugin<?> loginPlugin = LoginPluginUtils.getLoginPlugin("WX");
        if (loginPlugin == null) {
            throw new SocialUserLoginException("插件未找到.plugin:" + "WX");
        }
        String code = request.getParameter("code");
        String state = request.getParameter("state");
        
        LoginAccessToken token = null;
        String uniqueId = null;
        try {
            token = loginPlugin.getAccessToken(code, state, request);
            uniqueId = loginPlugin.getUniqueId(token, request);
        } catch (SocialUserLoginException | SILException e) {
            throw new SocialUserLoginException("获取第三方唯一键异常.", e);
        } catch (Exception e) {
            throw new SocialUserLoginException("获取第三方唯一键异常.", e);
        }
        
        ClientSocialAccountTypeEnum type = ClientSocialAccountTypeEnum
                .valueOf("WX");
        //根据微信UUID查询是否存在客户
        ClientSocialAccount csa = this.clientSocialAccountService
                .findByUniqueId(uniqueId, type);
        if (csa == null) {
            request.getSession().setAttribute("uniqueId", uniqueId);
            throw new SocialUserLoginException("没有关联的账号 .");
        }
        
        String userId = csa.getClientId();



        String accessToken = token.getAccessToken();
        ClientSocialAuthenticationToken authenticationToken = new ClientSocialAuthenticationToken(
                userId, accessToken);
        
        // Allow subclasses to set the "details" property
        setDetails(request, authenticationToken);
        
        return this.getAuthenticationManager()
                .authenticate(authenticationToken);
    }
    
    /**
     * Provided so that subclasses may configure what is put into the authentication
     * request's details property.
     *
     * @param request that an authentication request is being created for
     * @param authRequest the authentication request object that should have its details
     * set
     */
    protected void setDetails(HttpServletRequest request,
            ClientSocialAuthenticationToken authRequest) {
        authRequest.setDetails(clientUserDetailsService.loadUserByUserId(authRequest.getUserId()));

    }
    
}
