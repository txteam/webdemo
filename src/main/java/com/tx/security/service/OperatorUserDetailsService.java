/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2018年12月11日
 * <修改描述:>
 */
package com.tx.security.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.tx.component.auth.AuthConstants;
import com.tx.component.auth.context.AuthRegistry;
import com.tx.component.auth.model.Auth;
import com.tx.component.auth.model.AuthRef;
import com.tx.component.auth.service.AuthRefService;
import com.tx.component.role.RoleConstants;
import com.tx.component.role.context.RoleRegistry;
import com.tx.component.role.model.Role;
import com.tx.component.role.model.RoleRef;
import com.tx.component.role.service.RoleRefService;
import com.tx.component.security.context.SecurityContext;
import com.tx.core.exceptions.util.AssertUtils;
import com.tx.local.operator.model.Operator;
import com.tx.local.operator.model.OperatorRoleEnum;
import com.tx.local.operator.service.OperatorService;
import com.tx.plugin.login.exception.UserIdNotFoundException;
import com.tx.security.model.OperatorAuthTypeEnum;
import com.tx.security.model.OperatorUserDetails;

/**
 * 操作人员用户登陆业务层<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2018年12月11日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class OperatorUserDetailsService
        implements UserDetailsService, InitializingBean {
    
    /** 安全容器 */
    @Resource
    private SecurityContext securityContext;
    
    /** 操作人员业务层 */
    @Resource
    private OperatorService operatorService;
    
    /** 权限引用业务层 */
    private AuthRefService authRefService;
    
    /** 权限注册表 */
    private AuthRegistry authRegistry;
    
    /** 角色引用业务层 */
    private RoleRefService roleRefService;
    
    /** 角色注册表 */
    private RoleRegistry roleRegistry;
    
    /**
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        this.authRefService = securityContext.getAuthRefService();
        this.authRegistry = securityContext.getAuthRegistry();
        this.roleRefService = securityContext.getRoleRefService();
        this.roleRegistry = securityContext.getRoleRegistry();
    }
    
    /**
     * 根据用户id加载UserDetail对象<br/>
     * <功能详细描述>
     * @param userId
     * @return [参数说明]
     * 
     * @return UserDetails [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public UserDetails loadUserByUserId(String userId) {
        AssertUtils.notEmpty(userId, "userId is empty.");
        
        Operator operator = this.operatorService.findById(userId);
        if (operator == null) {
            throw new UserIdNotFoundException(
                    "Operator is not exsits.userId:" + userId);
        }
        OperatorUserDetails userDetail = loadUserDetailByOperator(operator);
        return userDetail;
    }
    
    /**
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        AssertUtils.notEmpty(username, "username is empty.");
        
        Operator operator = this.operatorService.findByUsername(username);
        if (operator == null) {
            throw new UsernameNotFoundException(
                    "Operator is not exsits.username:" + username);
        }
        OperatorUserDetails userDetail = loadUserDetailByOperator(operator);
        return userDetail;
    }
    
    /** 
     * 根据operator加载OperatorUserDetail对象<br/>
     * <功能详细描述>
     * @param operator
     * @return [参数说明]
     * 
     * @return OperatorUserDetails [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    private OperatorUserDetails loadUserDetailByOperator(Operator operator) {
        //用户权限
        MultiValueMap<String, String> refMap = new LinkedMultiValueMap<>();
        //人员权限
        refMap.add(AuthConstants.AUTHREFTYPE_OPERATOR, operator.getId());
        //组织权限
        refMap.add(AuthConstants.AUTHREFTYPE_ORGANIZATION,
                operator.getOrganizationId());
        //岗位(职位)权限
        if (!StringUtils.isEmpty(operator.getMainPostId())) {
            refMap.add(AuthConstants.AUTHREFTYPE_POST,
                    operator.getMainPostId());
        }
        
        //加载操作人员所拥有的角色
        //查询所拥有的角色
        List<RoleRef> roleRefList = this.roleRefService.queryListByRef(true,
                RoleConstants.ROLEREFTYPE_OPERATOR,
                operator.getId(),
                null);
        Set<Role> roles = new HashSet<>();
        //无论是否是超级管理员，都需要将其所拥有的角色进行加载
        roleRefList.stream()
                .map(roleRefTemp -> roleRefTemp.getRoleId())
                .collect(Collectors.toSet())
                .stream()
                .forEach(roleIdTemp -> {
                    Role role = roleRegistry.findById(roleIdTemp);
                    //过滤掉不存在的角色
                    if (role == null) {
                        return;
                    }
                    roles.add(role);
                    refMap.add(AuthConstants.AUTHREFTYPE_ROLE, roleIdTemp);
                });
        
        //写入系统中预制的角色
        //判断是否超级管理员
        boolean isSuperAdmin = isSuperAdmin(operator, roleRefList);
        //判断是否是系统管理员
        boolean isAdmin = isAdmin(operator, roleRefList);
        if (isSuperAdmin) {
            //如果为超级管理员，则将超级管理员角色加入
            roles.add(roleRegistry
                    .findById(OperatorRoleEnum.SUPER_ADMIN.getId()));
        }
        if (isAdmin) {
            //如果为系统管理员，则将系统管理员角色加入
            roles.add(roleRegistry.findById(OperatorRoleEnum.ADMIN.getId()));
        }
        //所有的操作人员均拥有角色OPERATOR
        roles.add(roleRegistry.findById(OperatorRoleEnum.OPERATOR.getId()));
        
        //查询用户的权限：根据用户的所属组织，职位，角色查询
        Set<Auth> auths = new HashSet<>();
        if (isSuperAdmin || isAdmin) {
            //查询系统所有可授予的操作人员操作权限以及数据权限，当人员为超级管理员或系统管理员时，不再读取实际配置了哪些权限
            String[] authTypeIds = Arrays.asList(
                    OperatorAuthTypeEnum.AUTH_TYPE_OPERATOR_OPERATE.getId(),
                    OperatorAuthTypeEnum.AUTH_TYPE_OPERATOR_DATA.getId())
                    .stream()
                    .toArray(String[]::new);
            auths.addAll(this.authRegistry.queryList(authTypeIds));
        } else {
            //查询所有系统配置的权限
            List<AuthRef> authRefList = this.authRefService
                    .queryListByRefMap(true, refMap);
            for (AuthRef arTemp : authRefList) {
                Auth auth = authRegistry.findById(arTemp.getAuthId());
                if (auth == null) {
                    continue;
                }
                auths.add(auth);
            }
        }
        
        //构建用户详情
        OperatorUserDetails userDetail = new OperatorUserDetails(operator,
                new ArrayList<>(roles), new ArrayList<>(auths));
        return userDetail;
    }
    
    /** 
     * 判断当前用户是否为超级管理员<br/>
     * <功能详细描述>
     * @param operator
     * @param isSuperAdmin
     * @param roleRefList
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    protected boolean isSuperAdmin(Operator operator,
            List<RoleRef> roleRefList) {
        AssertUtils.notNull(operator, "operator is null.");
        
        //如果用户名为admin
        String username = operator.getUsername();
        if (StringUtils.equals(username, "admin")) {
            return true;
        }
        //或者有这个角色
        for (RoleRef rf : roleRefList) {
            if (OperatorRoleEnum.SUPER_ADMIN.getId().equals(rf.getId())) {
                return true;
            }
        }
        return false;
    }
    
    /** 
     * 判断当前用户是否为超级管理员<br/>
     * <功能详细描述>
     * @param operator
     * @param isSuperAdmin
     * @param roleRefList
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    protected boolean isAdmin(Operator operator, List<RoleRef> roleRefList) {
        AssertUtils.notNull(operator, "operator is null.");
        
        //如果操作人员中的标志位信息为系统管理员
        if (operator.isAdmin()) {
            return true;
        }
        for (RoleRef rf : roleRefList) {
            if (OperatorRoleEnum.ADMIN.getId().equals(rf.getRoleId())) {
                return true;
            }
        }
        return false;
    }
}
