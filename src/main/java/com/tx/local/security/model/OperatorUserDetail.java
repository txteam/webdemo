/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2018年12月11日
 * <修改描述:>
 */
package com.tx.local.security.model;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.tx.component.operator.model.Operator;

/**
 * 基础用户详情<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2018年12月11日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class OperatorUserDetail implements UserDetails {
    
    /** 注释内容 */
    private static final long serialVersionUID = -9154349007645385326L;
    
    /** 基本用户 */
    private final Operator user;
    
    /** 授权 */
    private final Collection<GrantedAuthority> authorities;
    
    /** <默认构造函数> */
    public OperatorUserDetail(Operator user) {
        super();
        this.user = user;
        this.authorities = new ArrayList<>();
    }
    
    /** <默认构造函数> */
    public OperatorUserDetail(Operator user,
            Collection<GrantedAuthority> authorities) {
        super();
        this.user = user;
        this.authorities = authorities;
    }
    
    /**
     * @return
     */
    @Override
    public String getPassword() {
        return this.user.getPassword();
    }
    
    /**
     * @return
     */
    @Override
    public boolean isAccountNonLocked() {
        boolean isNonLocked = !this.user.isLocked();
        return isNonLocked;
    }
    
    /**
     * @return
     */
    @Override
    public boolean isAccountNonExpired() {
        boolean isNonExpired = !this.user.isValid();
        return isNonExpired;
    }
    
    /**
     * @return
     */
    @Override
    public String getUsername() {
        return this.user.getLoginName();
    }
    
    /**
     * @return
     */
    @Override
    public boolean isEnabled() {
        return this.user != null;
    }
    
    /**
     * @return
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    
    /**
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }
    
    /**
     * @return 返回 user
     */
    public Operator getUser() {
        return user;
    }
    
    /**
     * 获取用户Id<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return Long [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public String getUserId() {
        if (this.user == null) {
            return null;
        }
        String id = this.user.getId();
        return id;
    }
}
