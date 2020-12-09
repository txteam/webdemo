/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2019年7月29日
 * <修改描述:>
 */
package com.tx.security.model;

import java.util.Collection;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

/**
 * 操作人员登陆表单认证Token<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2019年7月29日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class OperatorDetailAuthenticationToken
        extends UsernamePasswordAuthenticationToken {
    
    /** 注释内容 */
    private static final long serialVersionUID = -4280224210760891569L;

    /** <默认构造函数> */
    public OperatorDetailAuthenticationToken(Object principal,
            Object credentials,
            Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
    }
    
    /** <默认构造函数> */
    public OperatorDetailAuthenticationToken(Object principal,
            Object credentials) {
        super(principal, credentials);
    }

    /**
     * @param details
     */
    @Override
    public void setDetails(Object details) {
        super.setDetails(details);
    }
}
