/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2019年7月29日
 * <修改描述:>
 */
package com.tx.security.model;

import java.util.Collection;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

/**
 * 操作人员登陆表单认证Token<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2019年7月29日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class OperatorSocialAuthenticationToken
        extends AbstractAuthenticationToken {
    
    /** 注释内容 */
    private static final long serialVersionUID = 3734790035098259128L;
    
    /** 主键 */
    private Object principal;
    
    /** 证书 */
    private Object credentials;
    
    /** <默认构造函数> */
    public OperatorSocialAuthenticationToken(Object principal,
            Object credentials) {
        super(null);
        this.principal = principal;
        this.credentials = credentials;
        setAuthenticated(false);
    }
    
    /** <默认构造函数> */
    public OperatorSocialAuthenticationToken(Object principal,
            Object credentials,
            Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
        this.credentials = credentials;
        super.setAuthenticated(true); // must use super, as we override
    }
    
    /**
     * 获取用户id<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public String getUserId() {
        if (this.getPrincipal() instanceof OperatorUserDetails) {
            return ((OperatorUserDetails) this.getPrincipal()).getUserId();
        }
        return (this.getPrincipal() == null) ? "" : this.getPrincipal().toString();
    }
    
    /**
     * @return
     */
    @Override
    public Object getPrincipal() {
        return this.principal;
    }
    
    /**
     * @return
     */
    @Override
    public Object getCredentials() {
        return this.credentials;
    }
    
    /**
     * 覆盖改写是否认证状态<br/>
     * @param isAuthenticated
     * @throws IllegalArgumentException
     */
    public void setAuthenticated(boolean isAuthenticated)
            throws IllegalArgumentException {
        if (isAuthenticated) {
            throw new IllegalArgumentException(
                    "Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead");
        }
        super.setAuthenticated(false);
    }
    
    @Override
    public void eraseCredentials() {
        super.eraseCredentials();
        credentials = null;
    }
}
