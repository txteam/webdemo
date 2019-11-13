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

import com.tx.component.auth.model.Auth;
import com.tx.local.operator.model.Operator;
import com.tx.local.operator.model.OperatorRole;
import com.tx.local.operator.model.OperatorPost;

/**
 * 操作人员用户详情<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2019年7月6日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class OperatorUserDetails implements UserDetails {
    
    /** 注释内容 */
    private static final long serialVersionUID = 6552523344301174231L;
    
    /** 操作人员 */
    private Operator operator;
    
    /** 主要职位 */
    private OperatorPost mainPost;
    
    /** 职位 */
    private List<OperatorPost> posts;
    
    /** 角色 */
    private List<OperatorRole> roles;
    
    /** 权限项 */
    private List<Auth> auths;
    
    /** 权限 */
    private Collection<? extends GrantedAuthority> authorities;
    
    /** <默认构造函数> */
    public OperatorUserDetails() {
        super();
    }
    
    /** <默认构造函数> */
    public OperatorUserDetails(Operator operator) {
        super();
        this.operator = operator;
    }
    
    /** <默认构造函数> */
    public OperatorUserDetails(Operator operator,
            Collection<? extends GrantedAuthority> authorities) {
        super();
        this.operator = operator;
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
        return this.operator.getPassword();
    }
    
    /**
     * @return
     */
    @Override
    public boolean isAccountNonLocked() {
        boolean isNonLocked = !this.operator.isLocked();
        return isNonLocked;
    }
    
    /**
     * @return
     */
    @Override
    public boolean isAccountNonExpired() {
        boolean isNonExpired = this.operator.isValid();
        return isNonExpired;
    }
    
    /**
     * @return
     */
    @Override
    public String getUsername() {
        return this.operator.getLoginName();
    }
    
    /**
     * @return
     */
    @Override
    public boolean isEnabled() {
        return this.operator != null && this.operator.isValid();
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
    public Operator getUser() {
        return operator;
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
        if (this.operator == null) {
            return null;
        }
        String id = this.operator.getId();
        return id;
    }

    /**
     * @return 返回 operator
     */
    public Operator getOperator() {
        return operator;
    }

    /**
     * @param 对operator进行赋值
     */
    public void setOperator(Operator operator) {
        this.operator = operator;
    }

    /**
     * @return 返回 mainPost
     */
    public OperatorPost getMainPost() {
        return mainPost;
    }

    /**
     * @param 对mainPost进行赋值
     */
    public void setMainPost(OperatorPost mainPost) {
        this.mainPost = mainPost;
    }

    /**
     * @return 返回 posts
     */
    public List<OperatorPost> getPosts() {
        return posts;
    }

    /**
     * @param 对posts进行赋值
     */
    public void setPosts(List<OperatorPost> posts) {
        this.posts = posts;
    }

    /**
     * @return 返回 roles
     */
    public List<OperatorRole> getRoles() {
        return roles;
    }

    /**
     * @param 对roles进行赋值
     */
    public void setRoles(List<OperatorRole> roles) {
        this.roles = roles;
    }

    /**
     * @return 返回 auths
     */
    public List<Auth> getAuths() {
        return auths;
    }

    /**
     * @param 对auths进行赋值
     */
    public void setAuths(List<Auth> auths) {
        this.auths = auths;
    }

    /**
     * @param 对authorities进行赋值
     */
    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }
}
