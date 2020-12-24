/*
 * 描          述:  <描述>
 * 修  改   人:  PengQingyang
 * 修改时间:  2020年12月14日
 * <修改描述:>
 */
package com.tx.security.service;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.rememberme.AbstractRememberMeServices;
import org.springframework.security.web.authentication.rememberme.CookieTheftException;
import org.springframework.security.web.authentication.rememberme.InvalidCookieException;
import org.springframework.security.web.authentication.rememberme.RememberMeAuthenticationException;

import com.tx.core.util.WebUtils;
import com.tx.security.model.OperatorRememberMeToken;

/**
 * 操作人员记住我业务层<br/>
 * <功能详细描述>
 * 
 * @author  PengQingyang
 * @version  [版本号, 2020年12月14日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class OperatorRememberMeServices extends AbstractRememberMeServices {
    
    private OperatorRememberMeTokenJwtService rememberMeTokenJwtService;
    
    private OperatorRememberMeTokenRepository tokenRepository;
    
    private SecureRandom random;
    
    public static final int DEFAULT_SERIES_LENGTH = 16;
    
    public static final int DEFAULT_TOKEN_LENGTH = 2000;
    
    private int seriesLength = DEFAULT_SERIES_LENGTH;
    
    private int tokenLength = DEFAULT_TOKEN_LENGTH;
    
    /** 操作人员记住我业务层 */
    public OperatorRememberMeServices(String key,
            UserDetailsService userDetailsService) {
        super(key, userDetailsService);
        random = new SecureRandom();
    }
    
    @Override
    public void afterPropertiesSet() throws Exception {
        super.afterPropertiesSet();
    }
    
    /**
     * @param request
     * @param response
     * @param successfulAuthentication
     */
    @Override
    protected void onLoginSuccess(HttpServletRequest request,
            HttpServletResponse response,
            Authentication successfulAuthentication) {
        String username = successfulAuthentication.getName();
        
        logger.debug("Creating new persistent login for user " + username);
        
        OperatorRememberMeToken persistentToken = new OperatorRememberMeToken(
                generateSeriesData(), username, generateTokenData(username),
                WebUtils.getClientIpAddress(request), new Date());
        try {
            tokenRepository.createNewToken(persistentToken);
            addCookie(persistentToken, request, response);
        } catch (Exception e) {
            logger.error("Failed to save persistent token ", e);
        }
    }
    
    /**
     * @param cookieTokens
     * @param request
     * @param response
     * @return
     * @throws RememberMeAuthenticationException
     * @throws UsernameNotFoundException
     */
    @Override
    protected UserDetails processAutoLoginCookie(String[] cookieTokens,
            HttpServletRequest request, HttpServletResponse response)
            throws RememberMeAuthenticationException,
            UsernameNotFoundException {
        if (cookieTokens.length != 2) {
            throw new InvalidCookieException("Cookie token did not contain " + 2
                    + " tokens, but contained '" + Arrays.asList(cookieTokens)
                    + "'");
        }
        
        final String presentedSeries = cookieTokens[0];
        final String presentedToken = cookieTokens[1];
        
        OperatorRememberMeToken token = tokenRepository
                .getTokenForSeries(presentedSeries);
        if (token == null) {
            // No series match, so we can't authenticate using this cookie
            throw new RememberMeAuthenticationException(
                    "No persistent token found for series id: "
                            + presentedSeries);
        }
        
        // We have a match for this user/series combination
        if (!presentedToken.equals(token.getToken())) {
            // Token doesn't match series value. Delete all logins for this user and throw
            // an exception to warn them.
            tokenRepository.removeUserTokens(token.getUsername());
            
            throw new CookieTheftException(messages.getMessage(
                    "PersistentTokenBasedRememberMeServices.cookieStolen",
                    "Invalid remember-me token (Series/token) mismatch. Implies previous cookie theft attack."));
        }
        
        //判断token的时间是否过期
        if (token.getLastUseDate().getTime()
                + getTokenValiditySeconds() * 1000L < System.currentTimeMillis()
                || !rememberMeTokenJwtService.validateToken(token.getToken(),
                        token.getUsername())) {
            tokenRepository.removeUserTokens(token.getUsername());
            
            throw new RememberMeAuthenticationException(
                    "Remember-me login has expired");
        }
        
        // Token also matches, so login is valid. Update the token value, keeping the
        // *same* series number.
        if (logger.isDebugEnabled()) {
            logger.debug("Refreshing persistent login token for user '"
                    + token.getUsername() + "', series '" + token.getId()
                    + "'");
        }
        
        /** 更新token值 */
        OperatorRememberMeToken newToken = new OperatorRememberMeToken(
                token.getId(), token.getUsername(),
                generateTokenData(token.getUsername()),
                WebUtils.getClientIpAddress(request), new Date());
        try {
            tokenRepository.updateToken(newToken.getId(),
                    newToken.getToken(),
                    newToken.getIpAddress(),
                    newToken.getLastUseDate());
            
            addCookie(newToken, request, response);
        } catch (Exception e) {
            logger.error("Failed to update token: ", e);
            throw new RememberMeAuthenticationException(
                    "Autologin failed due to data access problem");
        }
        return getUserDetailsService().loadUserByUsername(token.getUsername());
    }
    
    /**
     * 登出的处理句柄<br/>
     * @param request
     * @param response
     * @param authentication
     */
    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) {
        super.logout(request, response, authentication);
        
        if (authentication != null) {
            tokenRepository.removeUserTokens(authentication.getName());
        }
    }
    
    /**
     * 生成SeriesData<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    protected String generateSeriesData() {
        byte[] newSeries = new byte[seriesLength];
        random.nextBytes(newSeries);
        return new String(Base64.getEncoder().encode(newSeries));
    }
    
    /**
     * 生成token值
     * <功能详细描述>
     * @param username
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    protected String generateTokenData(String username) {
        this.rememberMeTokenJwtService
                .generateToken(username, null, getTokenValiditySeconds());
        byte[] newToken = new byte[tokenLength];
        random.nextBytes(newToken);
        return new String(Base64.getEncoder().encode(newToken));
    }
    
    /**
     * 写入token值 <br/>
     * <功能详细描述>
     * @param token
     * @param request
     * @param response [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    protected void addCookie(OperatorRememberMeToken token,
            HttpServletRequest request, HttpServletResponse response) {
        setCookie(new String[] { token.getId(), token.getToken() },
                getTokenValiditySeconds(),
                request,
                response);
    }
    
    /**
     * @param 对seriesLength进行赋值
     */
    public void setSeriesLength(int seriesLength) {
        this.seriesLength = seriesLength;
    }
    
    /**
     * @param 对tokenLength进行赋值
     */
    public void setTokenLength(int tokenLength) {
        this.tokenLength = tokenLength;
    }
    
    /**
     * @param 对rememberMeTokenJwtService进行赋值
     */
    public void setRememberMeTokenJwtService(
            OperatorRememberMeTokenJwtService rememberMeTokenJwtService) {
        this.rememberMeTokenJwtService = rememberMeTokenJwtService;
    }
    
    /**
     * @param 对tokenRepository进行赋值
     */
    public void setTokenRepository(
            OperatorRememberMeTokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }
    
}
