/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2019年7月26日
 * <修改描述:>
 */
package com.tx.security4wapclient.provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.core.authority.mapping.NullAuthoritiesMapper;
import org.springframework.security.core.userdetails.UserCache;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsChecker;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.cache.NullUserCache;
import org.springframework.util.Assert;

import com.tx.component.security.exception.UserIdNotFoundException;
import com.tx.core.exceptions.util.AssertUtils;
import com.tx.security4client.model.ClientSocialAuthenticationToken;
import com.tx.security4client.service.ClientUserDetailsService;

/**
 * 操作人员认证处理器<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2019年7月26日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class WapClientSocialAuthenticationProvider implements
        AuthenticationProvider, InitializingBean, MessageSourceAware {
    
    /** 日志句柄 */
    protected final Logger logger = LoggerFactory.getLogger(getClass());
    
    protected MessageSourceAccessor messages = SpringSecurityMessageSource
            .getAccessor();
    
    private UserCache userCache = new NullUserCache();
    
    private boolean forcePrincipalAsString = false;
    
    protected boolean hideUserNotFoundExceptions = true;
    
    private UserDetailsChecker preAuthenticationChecks = new DefaultPreAuthenticationChecks();
    
    private UserDetailsChecker postAuthenticationChecks = new DefaultPostAuthenticationChecks();
    
    private GrantedAuthoritiesMapper authoritiesMapper = new NullAuthoritiesMapper();
    
    private ClientUserDetailsService userDetailsService;
    
    /**
     * @throws Exception
     */
    @Override
    public final void afterPropertiesSet() throws Exception {
        AssertUtils.notNull(this.userCache, "A user cache must be set");
        AssertUtils.notNull(this.messages, "A message source must be set");
        doAfterPropertiesSet();
    }
    
    /**
     * 初始化
     * @throws Exception
     */
    protected void doAfterPropertiesSet() throws Exception {
    }
    
    /**
     * @param messageSource
     */
    @Override
    public void setMessageSource(MessageSource messageSource) {
        this.messages = new MessageSourceAccessor(messageSource);
    }
    
    /**
     * @param authentication
     * @return
     */
    @Override
    public boolean supports(Class<?> authentication) {
        return (ClientSocialAuthenticationToken.class
                .isAssignableFrom(authentication));
    }
    
    /**
     * @param authentication
     * @return
     * @throws AuthenticationException
     */
    @Override
    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException {
        AssertUtils.isInstanceOf(ClientSocialAuthenticationToken.class,
                authentication,
                messages.getMessage(
                        "AbstractUserDetailsAuthenticationProvider.onlySupports",
                        "Only ClientSocialAuthenticationToken is supported"));
        
        // Determine userId
        String userId = (authentication.getPrincipal() == null)
                ? "NONE_PROVIDED"
                : ((ClientSocialAuthenticationToken) authentication)
                        .getUserId();
        boolean cacheWasUsed = true;
        UserDetails user = getUserCache().getUserFromCache(userId);
        if (user == null) {
            cacheWasUsed = false;
            try {
                user = retrieveUser(userId,
                        (ClientSocialAuthenticationToken) authentication);
            } catch (UserIdNotFoundException notFound) {
                logger.debug("User '" + userId + "' not found");
                if (hideUserNotFoundExceptions) {
                    throw new BadCredentialsException(messages.getMessage(
                            "AbstractUserDetailsAuthenticationProvider.badCredentials",
                            "Bad credentials"));
                } else {
                    throw notFound;
                }
            }
            Assert.notNull(user,
                    "retrieveUser returned null - a violation of the interface contract");
        }
        
        try {
            getPreAuthenticationChecks().check(user);
            additionalAuthenticationChecks(user,
                    (ClientSocialAuthenticationToken) authentication);
        } catch (AuthenticationException exception) {
            if (cacheWasUsed) {
                // There was a problem, so try again after checking
                // we're using latest data (i.e. not from the cache)
                cacheWasUsed = false;
                user = retrieveUser(userId,
                        (ClientSocialAuthenticationToken) authentication);
                getPreAuthenticationChecks().check(user);
                additionalAuthenticationChecks(user,
                        (ClientSocialAuthenticationToken) authentication);
            } else {
                throw exception;
            }
        }
        
        postAuthenticationChecks.check(user);
        if (!cacheWasUsed) {
            this.userCache.putUserInCache(user);
        }
        Object principalToReturn = user;
        if (forcePrincipalAsString) {
            principalToReturn = user.getUsername();
        }
        return createSuccessAuthentication(principalToReturn,
                authentication,
                user);
    }
    
    /**
     * Creates a successful {@link Authentication} object.
     * <p>
     * Protected so subclasses can override.
     * </p>
     * <p>
     * Subclasses will usually store the original credentials the user supplied (not
     * salted or encoded passwords) in the returned <code>Authentication</code> object.
     * </p>
     *
     * @param principal that should be the principal in the returned object (defined by
     * the {@link #isForcePrincipalAsString()} method)
     * @param authentication that was presented to the provider for validation
     * @param user that was loaded by the implementation
     *
     * @return the successful authentication token
     */
    protected Authentication createSuccessAuthentication(Object principal,
            Authentication authentication, UserDetails user) {
        // Ensure we return the original credentials the user supplied,
        // so subsequent attempts are successful even with encoded passwords.
        // Also ensure we return the original getDetails(), so that future
        // authentication events after cache expiry contain the details
        ClientSocialAuthenticationToken result = new ClientSocialAuthenticationToken(
                principal, authentication.getCredentials(),
                authoritiesMapper.mapAuthorities(user.getAuthorities()));
        result.setDetails(authentication.getDetails());
        return result;
    }
    
    /**
     * @param userDetails
     * @param authentication
     * @throws AuthenticationException
     */
    protected void additionalAuthenticationChecks(UserDetails userDetails,
            ClientSocialAuthenticationToken authentication)
            throws AuthenticationException {
    }
    
    /**
     * @param userId
     * @param authentication
     * @return
     * @throws AuthenticationException
     */
    protected UserDetails retrieveUser(String userId,
            ClientSocialAuthenticationToken authentication)
            throws AuthenticationException {
        try {
            UserDetails loadedUser = this.userDetailsService
                    .loadUserByUserId(userId);
            if (loadedUser == null) {
                throw new InternalAuthenticationServiceException(
                        "UserDetailsService returned null, which is an interface contract violation");
            }
            return loadedUser;
        } catch (UsernameNotFoundException ex) {
            throw ex;
        } catch (InternalAuthenticationServiceException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new InternalAuthenticationServiceException(ex.getMessage(),
                    ex);
        }
    }
    
    /**
     * @return 返回 messages
     */
    public MessageSourceAccessor getMessages() {
        return messages;
    }
    
    /**
     * @param 对messages进行赋值
     */
    public void setMessages(MessageSourceAccessor messages) {
        this.messages = messages;
    }
    
    /**
     * @return 返回 userCache
     */
    public UserCache getUserCache() {
        return userCache;
    }
    
    /**
     * @param 对userCache进行赋值
     */
    public void setUserCache(UserCache userCache) {
        this.userCache = userCache;
    }
    
    /**
     * @return 返回 forcePrincipalAsString
     */
    public boolean isForcePrincipalAsString() {
        return forcePrincipalAsString;
    }
    
    /**
     * @param 对forcePrincipalAsString进行赋值
     */
    public void setForcePrincipalAsString(boolean forcePrincipalAsString) {
        this.forcePrincipalAsString = forcePrincipalAsString;
    }
    
    /**
     * @return 返回 hideUserNotFoundExceptions
     */
    public boolean isHideUserNotFoundExceptions() {
        return hideUserNotFoundExceptions;
    }
    
    /**
     * @param 对hideUserNotFoundExceptions进行赋值
     */
    public void setHideUserNotFoundExceptions(
            boolean hideUserNotFoundExceptions) {
        this.hideUserNotFoundExceptions = hideUserNotFoundExceptions;
    }
    
    /**
     * @return 返回 authoritiesMapper
     */
    public GrantedAuthoritiesMapper getAuthoritiesMapper() {
        return authoritiesMapper;
    }
    
    /**
     * @param 对authoritiesMapper进行赋值
     */
    public void setAuthoritiesMapper(
            GrantedAuthoritiesMapper authoritiesMapper) {
        this.authoritiesMapper = authoritiesMapper;
    }
    
    /**
     * @return 返回 preAuthenticationChecks
     */
    public UserDetailsChecker getPreAuthenticationChecks() {
        return preAuthenticationChecks;
    }
    
    /**
     * @param 对preAuthenticationChecks进行赋值
     */
    public void setPreAuthenticationChecks(
            UserDetailsChecker preAuthenticationChecks) {
        this.preAuthenticationChecks = preAuthenticationChecks;
    }
    
    /**
     * @return 返回 postAuthenticationChecks
     */
    public UserDetailsChecker getPostAuthenticationChecks() {
        return postAuthenticationChecks;
    }
    
    /**
     * @param 对postAuthenticationChecks进行赋值
     */
    public void setPostAuthenticationChecks(
            UserDetailsChecker postAuthenticationChecks) {
        this.postAuthenticationChecks = postAuthenticationChecks;
    }
    
    /**
     * @return 返回 userDetailsService
     */
    public ClientUserDetailsService getUserDetailsService() {
        return userDetailsService;
    }
    
    /**
     * @param 对userDetailsService进行赋值
     */
    public void setUserDetailsService(
            ClientUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
    
    /**
     * 默认的UserDetail前置Checks<br/>
     * <功能详细描述>
     * 
     * @author  Administrator
     * @version  [版本号, 2019年12月30日]
     * @see  [相关类/方法]
     * @since  [产品/模块版本]
     */
    private class DefaultPreAuthenticationChecks implements UserDetailsChecker {
        @Override
        public void check(UserDetails user) {
            if (!user.isAccountNonLocked()) {
                logger.debug("User account is locked");
                
                throw new LockedException(messages.getMessage(
                        "AbstractUserDetailsAuthenticationProvider.locked",
                        "User account is locked"));
            }
            
            if (!user.isEnabled()) {
                logger.debug("User account is disabled");
                
                throw new DisabledException(messages.getMessage(
                        "AbstractUserDetailsAuthenticationProvider.disabled",
                        "User is disabled"));
            }
            
            if (!user.isAccountNonExpired()) {
                logger.debug("User account is expired");
                
                throw new AccountExpiredException(messages.getMessage(
                        "AbstractUserDetailsAuthenticationProvider.expired",
                        "User account has expired"));
            }
        }
    }
    
    /**
     * 默认的查询用户期间检查逻辑<br/>
     * <功能详细描述>
     * 
     * @author  Administrator
     * @version  [版本号, 2019年12月30日]
     * @see  [相关类/方法]
     * @since  [产品/模块版本]
     */
    private class DefaultPostAuthenticationChecks
            implements UserDetailsChecker {
        @Override
        public void check(UserDetails user) {
            if (!user.isCredentialsNonExpired()) {
                logger.debug("User account credentials have expired");
                
                throw new CredentialsExpiredException(messages.getMessage(
                        "AbstractUserDetailsAuthenticationProvider.credentialsExpired",
                        "User credentials have expired"));
            }
        }
    }
}
