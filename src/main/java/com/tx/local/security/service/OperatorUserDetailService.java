/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2018年12月11日
 * <修改描述:>
 */
package com.tx.local.security.service;

import java.util.Collection;
import java.util.HashSet;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.tx.component.operator.model.Operator;
import com.tx.local.security.model.OperatorUserDetail;


/**
 * <功能简述>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2018年12月11日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Component("userDetailService")
public class OperatorUserDetailService implements UserDetailsService {
    
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
        return null;
    }
    
    private UserDetails mockUser() {
        Operator user = new Operator();
        user.setLoginName("admin");
        //System.out.println((new Md5PasswordEncoder()).encodePassword("123456", ""));
        user.setPassword("E10ADC3949BA59ABBE56E057F20F883E");
        user.setId("1");
        user.setValid(true);
        user.setLocked(false);
        
        Collection<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_OPERATOR_ADMIN"));//用户所拥有的角色信息
        //AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER")
        
        OperatorUserDetail userDetail = new OperatorUserDetail(user, authorities);
        return userDetail;
    }
}
