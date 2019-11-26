/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2018年12月11日
 * <修改描述:>
 */
package com.tx.local.security.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

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
import com.tx.component.security.context.SecurityContext;
import com.tx.local.operator.model.Operator;
import com.tx.local.operator.model.OperatorRole;
import com.tx.local.operator.model.OperatorRoleEnum;
import com.tx.local.operator.service.OperatorService;
import com.tx.local.organization.model.Organization;
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
public class OperatorUserDetailsService implements UserDetailsService,InitializingBean {
    
    @Resource
    private SecurityContext securityContext;
    
    @Resource
    private OperatorService operatorService;
    
    private AuthRefService authRefService;
    
    private AuthRegistry authRegistry;
    
    /**
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        this.authRefService = securityContext.getAuthRefService();
        this.authRegistry = securityContext.getAuthRegistry();
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
        Operator operator = this.operatorService.findByLoginName(username);
        
        MultiValueMap<String, String> refMap = new LinkedMultiValueMap<>();
        refMap.add(AuthConstants.AUTHREFTYPE_OPERATOR, operator.getId());
        
        List<AuthRef> authRefList = this.authRefService.queryListByRefMap(true, refMap);
        List<Auth> auths = new ArrayList<>();
        for(AuthRef arTemp : authRefList){
            Auth auth = authRegistry.findById(arTemp.getAuthId());
            if(auth == null){
                continue;
            }
            auths.add(auth);
        }
        OperatorUserDetails userDetail = new OperatorUserDetails(operator, new ArrayList<>(),
                auths);
        return userDetail;
    }
    
    private UserDetails mockUser() {
        String jtVcid = "1000000000";
        
        Operator user = new Operator();
        user.setVcid(jtVcid);
        user.setLoginName("admin");
        user.setUserName("adminUserName");
        user.setValid(true);
        user.setLocked(false);
        
        //System.out.println((new Md5PasswordEncoder()).encodePassword("123456", ""));
        user.setPassword("E10ADC3949BA59ABBE56E057F20F883E");
        user.setId("1");
        user.setValid(true);
        user.setLocked(false);
        
        List<OperatorRole> roles = new ArrayList<OperatorRole>();
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
}
