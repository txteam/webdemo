/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2019年7月6日
 * <修改描述:>
 */
package com.tx.local.security.model;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.tx.local.clientinfo.model.ClientInfo;
import com.tx.local.clientinfo.model.ClientRole;

/**
 * 操作人员用户详情<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2019年7月6日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class ClientUserDetails implements UserDetails {
    
    /** 注释内容 */
    private static final long serialVersionUID = 6552523344301174231L;
    
    /** 操作人员 */
    private ClientInfo client;
    
    /** 角色 */
    private List<ClientRole> roles;
    
    /** 权限 */
    private Collection<? extends GrantedAuthority> authorities;
    
    /** <默认构造函数> */
    public ClientUserDetails() {
        super();
    }
    
    /** <默认构造函数> */
    public ClientUserDetails(ClientInfo client) {
        super();
        this.client = client;
    }
    
    /** <默认构造函数> */
    public ClientUserDetails(ClientInfo client,
            Collection<? extends GrantedAuthority> authorities) {
        super();
        this.client = client;
        this.authorities = authorities;
    }
    
    /**
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }
    
    /**
     * @return
     */
    @Override
    public String getPassword() {
        return this.client.getPassword();
    }
    
    /**
     * @return
     */
    @Override
    public boolean isAccountNonLocked() {
        boolean isNonLocked = !this.client.isLocked();
        return isNonLocked;
    }
    
    /**
     * @return
     */
    @Override
    public boolean isAccountNonExpired() {
        boolean isNonExpired = this.client.isValid();
        return isNonExpired;
    }
    
    /**
     * @return
     */
    @Override
    public String getUsername() {
        return this.client.getLoginname();
    }
    
    /**
     * @return
     */
    @Override
    public boolean isEnabled() {
        return this.client != null && this.client.isValid();
    }
    
    /**
     * @return
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    
    /**
     * @return 返回 user
     */
    public ClientInfo getUser() {
        return client;
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
        if (this.client == null) {
            return null;
        }
        String id = this.client.getId();
        return id;
    }

    

    /**
     * @return 返回 client
     */
    public ClientInfo getClient() {
        return client;
    }

    /**
     * @param 对client进行赋值
     */
    public void setClient(ClientInfo client) {
        this.client = client;
    }

    /**
     * @return 返回 roles
     */
    public List<ClientRole> getRoles() {
        return roles;
    }

    /**
     * @param 对roles进行赋值
     */
    public void setRoles(List<ClientRole> roles) {
        this.roles = roles;
    }

    /**
     * @param 对authorities进行赋值
     */
    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }
}
