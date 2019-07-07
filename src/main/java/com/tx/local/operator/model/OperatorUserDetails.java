/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2019年7月6日
 * <修改描述:>
 */
package com.tx.local.operator.model;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.tx.component.auth.model.Auth;

/**
 * <功能简述>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2019年7月6日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class OperatorUserDetails implements UserDetails {
    
    private Operator operator;
    
    private List<Role> roles;
    
    private List<Auth> auths;
    
    /**
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // TODO Auto-generated method stub
        return null;
    }
    
    /**
     * @return
     */
    @Override
    public String getPassword() {
        // TODO Auto-generated method stub
        return null;
    }
    
    /**
     * @return
     */
    @Override
    public String getUsername() {
        // TODO Auto-generated method stub
        return null;
    }
    
    /**
     * @return
     */
    @Override
    public boolean isAccountNonExpired() {
        // TODO Auto-generated method stub
        return false;
    }
    
    /**
     * @return
     */
    @Override
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        return false;
    }
    
    /**
     * @return
     */
    @Override
    public boolean isCredentialsNonExpired() {
        // TODO Auto-generated method stub
        return false;
    }
    
    /**
     * @return
     */
    @Override
    public boolean isEnabled() {
        // TODO Auto-generated method stub
        return false;
    }
    
}
