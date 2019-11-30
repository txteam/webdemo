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
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.tx.component.security.context.SecurityContext;
import com.tx.component.security.model.AuthAuthority;
import com.tx.core.exceptions.util.AssertUtils;
import com.tx.local.operator.model.Operator;
import com.tx.local.operator.model.OperatorRoleEnum;
import com.tx.local.organization.model.Organization;
import com.tx.local.security.model.OperatorUserDetails;

/**
 * WebContext容器工具类<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2019年7月18日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class WebContextUtils {
    
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
     * @return Set<GrantedAuthority> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public static Set<GrantedAuthority> getCurrentAuthorities() {
        Authentication a = getAuthentication();
        Set<GrantedAuthority> authorities = a != null
                ? new HashSet<>(a.getAuthorities()) : new HashSet<>();
        return authorities;
    }
    
    /**
     * 是否为超级管理员<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public static boolean isSuperAdmin() {
        boolean hasSuperAdminRole = SecurityContext.getContext()
                .hasRole(OperatorRoleEnum.SUPER_ADMIN.getId());
        return hasSuperAdminRole;
    }
    
    /**
     * 是否为超级管理员<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public static boolean isSystemAdmin() {
        boolean hasSuperAdminRole = SecurityContext.getContext()
                .hasRole(OperatorRoleEnum.SYSTEM_ADMIN.getId());
        return hasSuperAdminRole || isSuperAdmin();
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
     * 获取OperatorUserDetails实例<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return OperatorUserDetails [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public static OperatorUserDetails getUserDetails() {
        Authentication authentication = getAuthentication();
        if (authentication != null && authentication.getPrincipal() != null
                && authentication
                        .getPrincipal() instanceof OperatorUserDetails) {
            OperatorUserDetails details = (OperatorUserDetails) authentication
                    .getPrincipal();
            return details;
        } else {
            return null;
        }
    }
    
    /**
     * 获取当前登录人员的虚中心id如果不存在则返回null
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public static String getVcid() {
        OperatorUserDetails details = getUserDetails();
        if (null == details) {
            return null;
        }
        
        String vcid = details.getVcid();
        return vcid;
    }
    
    /**
     * 从会话中获取当前登录人员所属组织<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return Organization [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public static Organization getOrganization() {
        OperatorUserDetails details = getUserDetails();
        if (null == details) {
            return null;
        }
        
        Organization organization = details.getOrganization();
        return organization;
    }
    
    /**
      * 获取当前组织id<br/>
      * <功能详细描述>
      * @return [参数说明]
      * 
      * @return String [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public static String getOrganizationId() {
        Organization organization = getOrganization();
        String organizationId = organization == null ? null
                : organization.getId();
        return organizationId;
    }
    
    /**
     * 从会话中获取当前登录人员
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return Operator [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
    public static Operator getOperator() {
        OperatorUserDetails details = getUserDetails();
        if (null == details) {
            return null;
        }
        
        Operator operator = details.getOperator();
        return operator;
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
    public static String getOperatorId() {
        Operator operator = getOperator();
        String operatorId = operator == null ? null : operator.getId();
        return operatorId;
    }
    
    /**
     * 将当前登录人员放入当前会话中
     *<功能详细描述>
     * @param operator [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public static void putInSession(String attributeName, Object object) {
        AssertUtils.notEmpty(attributeName, "attributeName is null");
        AssertUtils.notNull(object, "object is null");
        
        HttpSession session = getSession(true);
        session.setAttribute(attributeName, object);
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
