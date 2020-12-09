/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2019年7月18日
 * <修改描述:>
 */
package com.tx.local.security.util;

import org.springframework.security.core.Authentication;

import com.tx.component.security.context.SecurityContext;
import com.tx.local.operator.model.Operator;
import com.tx.local.operator.model.OperatorRoleEnum;
import com.tx.local.organization.model.Organization;
import com.tx.security.model.OperatorUserDetails;

/**
 * WebContext容器工具类<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2019年7月18日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class WebContextUtils extends AbstractWebContextUtils {
    
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
     * 获取当前操作人员的id
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public static String getOperatorUsername() {
        Operator operator = getOperator();
        String operatorUsername = operator == null ? null
                : operator.getUsername();
        return operatorUsername;
    }
}
