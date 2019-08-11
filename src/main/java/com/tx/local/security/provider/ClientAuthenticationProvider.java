/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2019年7月26日
 * <修改描述:>
 */
package com.tx.local.security.provider;

import org.springframework.security.authentication.dao.DaoAuthenticationProvider;

import com.tx.local.security.model.ClientLoginFormAuthenticationToken;

/**
 * 操作人员认证处理器<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2019年7月26日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class ClientAuthenticationProvider extends DaoAuthenticationProvider {
    
    /**
     * @param authentication
     * @return
     */
    @Override
    public boolean supports(Class<?> authentication) {
        return (ClientLoginFormAuthenticationToken.class
                .isAssignableFrom(authentication));
    }
}
