/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2018年12月11日
 * <修改描述:>
 */
package com.tx.local.security.service;

import java.util.*;
import java.util.stream.Collectors;

import javax.annotation.Resource;

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
import com.tx.local.clientinfo.model.*;
import com.tx.local.operator.model.OperatorRoleEnum;
import com.tx.local.operator.service.OperatorService;
import com.tx.local.security.model.AuthTypeEnum;
import com.tx.local.security.model.RoleTypeEnum;
import com.tx.local.security.util.WebContextUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.tx.core.exceptions.util.AssertUtils;
import com.tx.local.clientinfo.service.ClientInfoService;
import com.tx.local.security.model.ClientUserDetails;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;


/**
 * 操作人员用户登陆业务层<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2018年12月11日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class ClientUserDetailsService implements UserDetailsService , InitializingBean {

    @Resource
    private SecurityContext securityContext;

    /** 客户信息业务层 */
    @Resource
    private ClientInfoService clientInfoService;

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
        AssertUtils.notEmpty(username, "username is empty.");
        
        ClientInfo client = this.clientInfoService.findByUsername(username);
        if (client == null) {
            throw new UsernameNotFoundException(
                    "client is not exsits.username:" + username);
        }
        ClientUserDetails userDetail = loadUserDetailByClient(client);
        return userDetail;
    }
    
    private UserDetails mockUser() {
        ClientInfo user = new ClientInfo();
        user.setUsername("test");
        user.setPassword("E10ADC3949BA59ABBE56E057F20F883E");
        user.setId("1");
        user.setValid(true);
        user.setLocked(false);
        
        Collection<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_OPERATOR_ADMIN"));//用户所拥有的角色信息
        //AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER")
        
        ClientUserDetails userDetail = new ClientUserDetails(user, authorities);
        return userDetail;
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


        ClientInfo client = this.clientInfoService.findById(userId);
        if (client == null) {
            throw new UsernameNotFoundException(
                    "client is not exsits.userId:" + userId);
        }
        ClientUserDetails userDetail = loadUserDetailByClient(client);
        return userDetail;
    }

    /**
     * 根据client加载ClientUserDetails对象<br/>
     * <功能详细描述>
     * @param client
     * @return [参数说明]
     *
     * @return ClientUserDetails [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    private ClientUserDetails loadUserDetailByClient(ClientInfo client) {

        List<RoleRef> roleRefList = this.roleRefService.queryListByRef(true,
                RoleConstants.ROLEREFTYPE_CLIENT,
                client.getId(),
                null);
        List<Role> roles = new ArrayList<>();
        //无论是否是超级管理员，都需要将其所拥有的角色进行加载
        roleRefList.stream()
                .map(roleRefTemp -> roleRefTemp.getRoleId())
                .collect(Collectors.toSet())
                .stream()
                .forEach(roleIdTemp -> {
                    Role role = roleRegistry.findById(roleIdTemp);
                    if (role == null) {
                        return;
                    }
                    roles.add(role);
                });

        ClientUserDetails userDetail = new ClientUserDetails(client, roles, null);
        return userDetail;
    }

    /**
     * 设置用户权限
     * <功能详细描述>
     * @param clientId 用户ID
     * @param clientType 客户类型
     * @return
     */
    public boolean setClientUserRole(String clientId, ClientTypeEnum clientType){
        List<String> roleIdList =new ArrayList<String>();
        //如果是机构 或者机构成员则默认设置 企业端的登录权限
        if(clientType == ClientTypeEnum.ADM_INS ||clientType == ClientTypeEnum.INSTITUTION_MEMBER
                || clientType == ClientTypeEnum.COO_INS|| clientType == ClientTypeEnum.INSTITUTION){
            roleIdList.add(ClientRoleEnum.CLIENT.getId());
            roleIdList.add(ClientRoleEnum.CLIENT_ADMIN.getId());
        }
        //如果是wap端的个人用户或者企业用户与个体工商户
        if(clientType == ClientTypeEnum.PERSONAL ||clientType == ClientTypeEnum.ENTERPRISE
                ||clientType == ClientTypeEnum.SELF_EMPLOYED){
            roleIdList.add(WapClientRoleEnum.WAP_CLIENT.getId());
        }
        //默认分配该角色所拥有的客户的所有权限
        List<String> filterRoleIds = getAssignableRoleIds("ROLE_TYPE_CLIENT");
        this.roleRefService.saveForRoleIds(RoleConstants.ROLEREFTYPE_CLIENT,
                clientId,
                roleIdList,
                filterRoleIds);
        return true;
    }

    /**
     * 获取可分配的权限，如果为超级管理员，则所有权限都可以进行分配，否则仅能分配自己拥有的权限
     * <功能详细描述>
     * @param roleTypeId
     * @return [参数说明]
     *
     * @return List<String> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    private List<String> getAssignableRoleIds(String roleTypeId) {
        List<String> resList = WebContextUtils.getCurrentRoles()
                .stream()
                .filter(role -> roleTypeId.equals(role.getRoleTypeId()))
                .map(role -> role.getId())
                .collect(Collectors.toList());
        return resList;
    }
}
