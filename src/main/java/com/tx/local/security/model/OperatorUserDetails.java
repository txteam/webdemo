/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2019年7月6日
 * <修改描述:>
 */
package com.tx.local.security.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.tx.component.auth.model.Auth;
import com.tx.component.auth.model.AuthAuthorityImpl;
import com.tx.component.role.model.Role;
import com.tx.component.role.model.RoleAuthorityImpl;
import com.tx.local.operator.model.Operator;
import com.tx.local.organization.model.Organization;
import com.tx.local.organization.model.Post;

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
    
    /** 所属虚中心 */
    private String vcid;
    
    /** 操作人员 */
    private Operator operator;
    
    /** 所属组织 */
    private Organization organization;
    
    /** 主要职位 */
    private Post mainPost;
    
    /** 职位 */
    private List<Post> posts;
    
    /** 权限项 */
    private List<Auth> auths;
    
    /** 角色 */
    private List<Role> roles;
    
    /** 权限 */
    private Collection<? extends GrantedAuthority> authorities;
    
    /** <默认构造函数> */
    public OperatorUserDetails() {
        super();
    }
    
    /** <默认构造函数> */
    public OperatorUserDetails(Operator operator, List<Role> roleList,
            List<Auth> authList) {
        super();
        this.operator = operator;
        this.vcid = this.operator.getVcid();
        
        this.roles = roleList == null ? new ArrayList<Role>() : roleList;
        this.auths = authList == null ? new ArrayList<Auth>() : authList;
        initAuthority();
    }
    
    /**
     * 初始化clientDetail权限<br/>
     * <功能详细描述> [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    private void initAuthority() {
        List<GrantedAuthority> newAuthorites = new ArrayList<>();
        for (Role roleTemp : this.roles) {
            newAuthorites.add(new RoleAuthorityImpl(roleTemp));
        }
        for (Auth authTemp : this.auths) {
            newAuthorites.add(new AuthAuthorityImpl(authTemp));
        }
        this.authorities = newAuthorites;
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
    public boolean isEnabled() {
        return this.operator != null && this.operator.isValid();
    }
    
    /**
     * 授权是否过期<br/>
     * @return
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return this.operator != null && this.operator.isValid();
    }
    
    /**
     * @return
     */
    @Override
    public String getUsername() {
        return this.operator.getUsername();
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
     * @return 返回 vcid
     */
    public String getVcid() {
        return vcid;
    }
    
    /**
     * @return 返回 organization
     */
    public Organization getOrganization() {
        return organization;
    }
    
    /**
     * @return 返回 mainPost
     */
    public Post getMainPost() {
        return mainPost;
    }
    
    /**
     * @return 返回 posts
     */
    public List<Post> getPosts() {
        return posts;
    }
    
    /**
     * @return 返回 roles
     */
    public List<Role> getRoles() {
        return roles;
    }
    
    /**
     * @return 返回 auths
     */
    public List<Auth> getAuths() {
        return auths;
    }
    
    /**
     * @param 对vcid进行赋值
     */
    public void setVcid(String vcid) {
        this.vcid = vcid;
    }
    
    /**
     * @param 对organization进行赋值
     */
    public void setOrganization(Organization organization) {
        this.organization = organization;
    }
    
    /**
     * @param 对posts进行赋值
     */
    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
    
    /**
     * @param 对roles进行赋值
     */
    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
