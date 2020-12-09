/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2019年7月18日
 * <修改描述:>
 */
package com.tx.local.security.util;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.tx.component.auth.model.Auth;
import com.tx.component.role.model.Role;
import com.tx.component.security.model.AuthAuthority;
import com.tx.component.security.model.RoleAuthority;
import com.tx.core.exceptions.util.AssertUtils;

/**
 * WebContext容器工具类<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2019年7月18日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public abstract class AbstractWebContextUtils {
    
    /**
     * 获取OperatorUserDetails实例<br/>
     * <功能详细描述>
     * @return [参数说明]
     *
     * @return OperatorUserDetails [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public static UserDetails getUserDetails() {
        Authentication authentication = getAuthentication();
        if (authentication != null && authentication.getPrincipal() != null
                && authentication.getPrincipal() instanceof UserDetails) {
            UserDetails details = (UserDetails) authentication.getPrincipal();
            return details;
        } else {
            return null;
        }
    }
    
    /**
     * 获取当前操作人员的id
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public static String getUsername() {
        UserDetails details = getUserDetails();
        if (null == details) {
            return null;
        }
        
        return details.getUsername();
    }
    
    /**
     * 获取当前用户拥有的权限集合<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return Set<String> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public static Set<String> getCurrentRoleIds() {
        Set<GrantedAuthority> gas = getCurrentAuthorities();
        if (CollectionUtils.isEmpty(gas)) {
            return new HashSet<>();
        }
        
        Set<String> roleIds = gas.stream()
                .filter(a -> RoleAuthority.class.isInstance(a))
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toSet());
        return roleIds;
    }
    
    /**
     * 获取当前用户拥有的权限集合<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return Set<String> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public static Set<Role> getCurrentRoles() {
        Set<GrantedAuthority> gas = getCurrentAuthorities();
        if (CollectionUtils.isEmpty(gas)) {
            return new HashSet<>();
        }
        
        Set<Role> roles = gas.stream()
                .filter(a -> RoleAuthority.class.isInstance(a))
                .map(a -> ((RoleAuthority) a).getRole())
                .collect(Collectors.toSet());
        return roles;
    }
    
    /**
     * 获取当前用户拥有的权限集合<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return Set<String> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public static Set<String> getCurrentAuthIds() {
        Set<GrantedAuthority> gas = getCurrentAuthorities();
        if (CollectionUtils.isEmpty(gas)) {
            return new HashSet<>();
        }
        
        Set<String> authIds = gas.stream()
                .filter(a -> AuthAuthority.class.isInstance(a))
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toSet());
        return authIds;
    }
    
    /**
     * 获取当前用户拥有的权限集合<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return Set<String> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public static Set<Auth> getCurrentAuths() {
        Set<GrantedAuthority> gas = getCurrentAuthorities();
        if (CollectionUtils.isEmpty(gas)) {
            return new HashSet<>();
        }
        
        Set<Auth> auths = gas.stream()
                .filter(a -> AuthAuthority.class.isInstance(a))
                .map(a -> ((AuthAuthority) a).getAuth())
                .collect(Collectors.toSet());
        return auths;
    }
    
    /**
     * 获取当前用户拥有的权限集合<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return Set<GrantedAuthority> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public static Set<GrantedAuthority> getCurrentAuthorities() {
        Authentication a = getAuthentication();
        Set<GrantedAuthority> authorities = a != null
                ? new HashSet<>(a.getAuthorities())
                : new HashSet<>();
        return authorities;
    }
    
    /**
     * 获取安全框架认证信息<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return Authentication [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public static Authentication getAuthentication() {
        Authentication authentication = SecurityContextHolder.getContext()
                .getAuthentication();
        return authentication;
    }
    
    /**
     * 将当前登录人员放入当前会话中
     *<功能详细描述>
     * @param attributeName [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public static void setAttributeToSession(String attributeName,
            Object object) {
        AssertUtils.notEmpty(attributeName, "attributeName is null");
        AssertUtils.notNull(object, "object is null");
        
        HttpSession session = getSession(true);
        session.setAttribute(attributeName, object);
    }
    
    /**
     * 从session中取值<br/>
     * <功能详细描述>
     * @param <T>
     * @param attributeName
     * @param type
     * @return [参数说明]
     * 
     * @return T [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public static <T> T getAttributeFromSession(String attributeName,
            Class<T> type) {
        AssertUtils.notEmpty(attributeName, "attributeName is null");
        AssertUtils.notNull(type, "type is null");
        
        HttpSession session = getSession(true);
        Object obj = session.getAttribute(attributeName);
        
        AssertUtils.isInstanceOf(type,
                obj,
                "attributeName:{} is not instance of class:{}",
                new Object[] { attributeName, type });
        
        return null;
    }
    
    /**
     * 获取当前会话<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return HttpSession [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public static HttpSession getSession() {
        return getSession(true);
    }
    
    /**
     * 获取当前会话<br/>
     * <功能详细描述>
     * @param flag
     * @return [参数说明]
     * 
     * @return HttpSession [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public static HttpSession getSession(boolean flag) {
        if (getRequest() == null) {
            return null;
        }
        return getRequest().getSession(flag);
    }
    
    /**
     * 获取当前请求request<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return HttpServletRequest [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public static HttpServletRequest getRequest() {
        RequestAttributes requestAttributes = getRequestAttributes();
        if (requestAttributes != null) {
            return ((ServletRequestAttributes) requestAttributes).getRequest();
        } else {
            return null;
        }
    }
    
    /**
     * 获取当前请求RequestAttributes
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return HttpServletRequest [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public static RequestAttributes getRequestAttributes() {
        RequestAttributes requestAttributes = RequestContextHolder
                .getRequestAttributes();
        
        return requestAttributes;
    }
}
