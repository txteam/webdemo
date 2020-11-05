/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2018年12月11日
 * <修改描述:>
 */
package com.tx.local.security.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.tx.component.role.RoleConstants;
import com.tx.component.role.context.RoleRegistry;
import com.tx.component.role.model.Role;
import com.tx.component.role.model.RoleRef;
import com.tx.component.role.service.RoleRefService;
import com.tx.component.security.context.SecurityContext;
import com.tx.core.exceptions.util.AssertUtils;
import com.tx.local.clientinfo.model.ClientInfo;
import com.tx.local.clientinfo.service.ClientInfoService;
import com.tx.local.security.model.ClientUserDetails;

/**
 * 操作人员用户登陆业务层<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2018年12月11日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class ClientUserDetailsService
        implements UserDetailsService, InitializingBean {
    
    @Resource
    private SecurityContext securityContext;
    
    /** 客户信息业务层 */
    @Resource
    private ClientInfoService clientInfoService;
    
    private RoleRefService roleRefService;
    
    private RoleRegistry roleRegistry;
    
    /**
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
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
    
    //    private UserDetails mockUser() {
    //        ClientInfo user = new ClientInfo();
    //        user.setUsername("test");
    //        user.setPassword("E10ADC3949BA59ABBE56E057F20F883E");
    //        user.setId("1");
    //        user.setValid(true);
    //        user.setLocked(false);
    //        
    //        Collection<GrantedAuthority> authorities = new HashSet<>();
    //        authorities.add(new SimpleGrantedAuthority("ROLE_OPERATOR_ADMIN"));//用户所拥有的角色信息
    //        //AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER")
    //        
    //        ClientUserDetails userDetail = new ClientUserDetails(user, authorities);
    //        return userDetail;
    //    }
    
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
        
        ClientUserDetails userDetail = new ClientUserDetails(client, roles,
                null);
        return userDetail;
    }
}
