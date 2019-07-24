/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2019年7月23日
 * <修改描述:>
 */
package com.tx.local.security.filter;

import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;

/**
 * 操作权认证处理过滤器<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2019年7月23日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class OperatorAuthenticationProcessingFilter
        extends UsernamePasswordAuthenticationFilter {
    
    /** <默认构造函数> */
    protected OperatorAuthenticationProcessingFilter(
            RequestMatcher requiresAuthenticationRequestMatcher) {
        super();
        setFilterProcessesUrl("/operator/doLogin");
    }
    
}
