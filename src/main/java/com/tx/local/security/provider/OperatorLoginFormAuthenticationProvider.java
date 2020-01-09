/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2019年7月26日
 * <修改描述:>
 */
package com.tx.local.security.provider;

import javax.annotation.Resource;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.core.authority.mapping.NullAuthoritiesMapper;
import org.springframework.security.core.userdetails.UserDetails;

import com.tx.core.exceptions.util.AssertUtils;
import com.tx.core.util.MessageUtils;
import com.tx.local.operator.service.OperatorService;
import com.tx.local.security.model.OperatorLoginFormAuthenticationToken;
import com.tx.local.security.model.OperatorUserDetails;

/**
 * 操作人员认证处理器<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2019年7月26日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class OperatorLoginFormAuthenticationProvider
        extends DaoAuthenticationProvider {
    
    private GrantedAuthoritiesMapper authoritiesMapper = new NullAuthoritiesMapper();
    
    @Resource
    private OperatorService operatorService;
    
    /**
     * @param authentication
     * @return
     */
    @Override
    public boolean supports(Class<?> authentication) {
        return (OperatorLoginFormAuthenticationToken.class
                .isAssignableFrom(authentication));
    }
    
    /**
     * 密码检查逻辑<br/>
     * @param userDetails
     * @param authentication
     * @throws AuthenticationException
     */
    protected void additionalAuthenticationChecks(UserDetails userDetails,
            UsernamePasswordAuthenticationToken authentication)
            throws AuthenticationException {
        if (authentication.getCredentials() == null) {
            logger.debug("Authentication failed: no credentials provided");
            
            throw new BadCredentialsException(messages.getMessage(
                    "AbstractUserDetailsAuthenticationProvider.badCredentials",
                    "Bad credentials"));
        }
        
        String presentedPassword = authentication.getCredentials().toString();
        if (!getPasswordEncoder().matches(presentedPassword,
                userDetails.getPassword())) {
            logger.debug(
                    "Authentication failed: password does not match stored value");
            
            //更新客户密码错误次数
            AssertUtils.isInstanceOf(OperatorUserDetails.class,
                    userDetails,
                    MessageUtils.format(
                            "userDetails:{} should instance of OperatorUserDetails.",
                            new Object[] { userDetails }));
            OperatorUserDetails oud = (OperatorUserDetails) userDetails;
            this.operatorService.updatePwdErrorCountById(
                    oud.getOperator().getId(),
                    oud.getOperator().getPwdErrCount() + 1);
            if(oud.getOperator().getPwdErrCount() + 1 >= 3){
                this.operatorService.lockById(oud.getOperator().getId());
            }
            
            throw new BadCredentialsException(messages.getMessage(
                    "AbstractUserDetailsAuthenticationProvider.badCredentials",
                    "Bad credentials"));
        }
    }
    
    /**
     * @param principal
     * @param authentication
     * @param user
     * @return
     */
    @Override
    protected Authentication createSuccessAuthentication(Object principal,
            Authentication authentication, UserDetails user) {
        OperatorLoginFormAuthenticationToken result = new OperatorLoginFormAuthenticationToken(
                principal, authentication.getCredentials(),
                authoritiesMapper.mapAuthorities(user.getAuthorities()));
        result.setDetails(authentication.getDetails());
        
        return result;
    }
}
