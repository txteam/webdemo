/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2018年12月11日
 * <修改描述:>
 */
package com.tx.local.security.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
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
import com.tx.local.operator.model.Operator;
import com.tx.local.operator.model.OperatorRole;
import com.tx.local.operator.model.OperatorRoleEnum;
import com.tx.local.operator.service.OperatorService;
import com.tx.local.organization.model.Organization;
import com.tx.local.security.model.AuthTypeEnum;
import com.tx.local.security.model.OperatorUserDetails;

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
    
    @Resource
    private SecurityContext securityContext;
    
    @Resource
    private OperatorService operatorService;
    
    private AuthRefService authRefService;
    
    private AuthRegistry authRegistry;
    
    private RoleRefService roleRefService;
    
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
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        if ("admin".equalsIgnoreCase(username)) {
            UserDetails user = mockUser();
            return user;
        }
        Operator operator = this.operatorService.findByUsername(username);
        //用户权限
        MultiValueMap<String, String> refMap = new LinkedMultiValueMap<>();
        refMap.add(AuthConstants.AUTHREFTYPE_OPERATOR, operator.getId());
        //组织权限
        refMap.add(AuthConstants.AUTHREFTYPE_ORGANIZATION,
                operator.getOrganizationId());
        //岗位(职位)权限
        if (!StringUtils.isEmpty(operator.getMainPostId())) {
            refMap.add(AuthConstants.AUTHREFTYPE_POST,
                    operator.getMainPostId());
        }
        
        //角色权限
        boolean isSuperAdmin = false;
        boolean isSystemAdmin = false;
        List<RoleRef> roleRefList = this.roleRefService.queryListByRef(true,
                RoleConstants.ROLEREFTYPE_OPERATOR,
                operator.getId(),
                null);
        List<Role> roles = new ArrayList<>();
        roleRefList.stream()
                .map(roleRefTemp -> roleRefTemp.getRoleId())
                .collect(Collectors.toSet())
                .stream()
                .forEach(roleIdTemp -> {
                    //                    if(OperatorRoleEnum.SUPER_ADMIN.getId().equals(roleIdTemp)){
                    //                        isSuperAdmin = true;
                    //                    }
                    //                    if(OperatorRoleEnum.SYSTEM_ADMIN.getId().equals(roleIdTemp)){
                    //                        isSystemAdmin = true;
                    //                    }
                    Role role = roleRegistry.findById(roleIdTemp);
                    if (role == null) {
                        return;
                    }
                    roles.add(role);
                    refMap.add(AuthConstants.AUTHREFTYPE_ROLE, roleIdTemp);
                });
        
        //查询用户的权限：根据用户的所属组织，职位，角色查询
        List<Auth> auths = new ArrayList<>();
        if (isSuperAdmin || isSystemAdmin) {
            List<AuthRef> authRefList = this.authRefService
                    .queryListByRefMap(true, refMap);
            for (AuthRef arTemp : authRefList) {
                Auth auth = authRegistry.findById(arTemp.getAuthId());
                if (auth == null) {
                    continue;
                }
                auths.add(auth);
            }
        } else {
            String[] authTypeIds = Arrays
                    .asList(AuthTypeEnum.AUTH_TYPE_OPERATOR_OPERATE.getId(),
                            AuthTypeEnum.AUTH_TYPE_OPERATOR_DATA.getId())
                    .stream()
                    .toArray(String[]::new);
            auths.addAll(SecurityContext.getContext()
                    .getAuthRegistry()
                    .queryList(authTypeIds));
        }
        OperatorUserDetails userDetail = new OperatorUserDetails(operator,
                roles, auths);
        return userDetail;
    }
    
    protected UserDetails mockUser() {
        String jtVcid = "1000000000";
        
        Operator user = new Operator();
        user.setVcid(jtVcid);
        user.setUsername("admin");
        user.setName("超级管理员");
        user.setValid(true);
        user.setLocked(false);
        
        //System.out.println((new Md5PasswordEncoder()).encodePassword("123456", ""));
        user.setPassword("E10ADC3949BA59ABBE56E057F20F883E");
        user.setId("1");
        user.setValid(true);
        user.setLocked(false);
        
        List<Role> roles = new ArrayList<Role>();
        OperatorRole role1 = new OperatorRole();
        role1.setId(OperatorRoleEnum.SUPER_ADMIN.getId());
        role1.setName(OperatorRoleEnum.SUPER_ADMIN.getName());
        roles.add(role1);
        OperatorRole role2 = new OperatorRole();
        role2.setId(OperatorRoleEnum.SYSTEM_ADMIN.getId());
        role2.setName(OperatorRoleEnum.SYSTEM_ADMIN.getName());
        roles.add(role2);
        
        List<Auth> auths = new ArrayList<>();
        String[] authTypeIds = SecurityContext.getContext()
                .getAuthTypeRegistry()
                .queryList()
                .stream()
                .map(at -> at.getId())
                .toArray(String[]::new);
        auths.addAll(SecurityContext.getContext()
                .getAuthRegistry()
                .queryList(authTypeIds));
        
        OperatorUserDetails userDetail = new OperatorUserDetails(user, roles,
                auths);
        Organization org = new Organization();
        userDetail.setOrganization(org);
        return userDetail;
    }
    
    public static void main(String[] args) {
        String rawPwd = PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("123321qQ");
        System.out.println(rawPwd);
    }
}
