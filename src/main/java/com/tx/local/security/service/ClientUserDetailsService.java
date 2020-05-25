/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2018年12月11日
 * <修改描述:>
 */
package com.tx.local.security.service;

import java.util.Collection;
import java.util.HashSet;

import javax.annotation.Resource;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

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
public class ClientUserDetailsService implements UserDetailsService {
    
    /** 客户信息业务层 */
    @Resource
    private ClientInfoService clientInfoService;
    
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
                    "Operator is not exsits.username:" + username);
        }
        return null;
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
        
        //FIXME:
        //Operator operator = this..findById(userId);
        //if (operator == null) {
        //    throw new UserIdNotFoundException(
        //            "Operator is not exsits.userId:" + userId);
        //}
        UserDetails userDetail = mockUser();//loadUserDetailByOperator(operator);
        return userDetail;
    }
}
