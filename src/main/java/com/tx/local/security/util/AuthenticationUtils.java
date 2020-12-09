/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2019年7月25日
 * <修改描述:>
 */
package com.tx.local.security.util;

import org.apache.commons.lang3.ClassUtils;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;

/**
 * 认证工具类<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2019年7月25日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public abstract class AuthenticationUtils {
    
    /**
     * 解析登录异常信息<br/>
     * <功能详细描述>
     * @param exception
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public static String loginErrorMessage(AuthenticationException exception) {
        String errorMessage = "用户名或密码错误.";
        if (exception == null) {
            errorMessage = "系统内部错误，请联系管理员.";
            return errorMessage;
        }
        Class<? extends AuthenticationException> exceptionClass = exception
                .getClass();
        if (ClassUtils.isAssignable(exceptionClass,
                AccountStatusException.class)) {
            errorMessage = "用户账户状态异常.";
            if (ClassUtils.isAssignable(exceptionClass,
                    AccountExpiredException.class)) {
                errorMessage = "用户账户已过期.";
            } else if (ClassUtils.isAssignable(exceptionClass,
                    LockedException.class)) {
                errorMessage = "用户账户已锁定.";
            } else if (ClassUtils.isAssignable(exceptionClass,
                    DisabledException.class)) {
                errorMessage = "用户账户已锁定.";
            } else if (ClassUtils.isAssignable(exceptionClass,
                    CredentialsExpiredException.class)) {
                errorMessage = "用户授权已过期，请重新登录.";
            }
        } else if (ClassUtils.isAssignable(exceptionClass,
                AuthenticationCredentialsNotFoundException.class)) {
            errorMessage = "用户授权不存在，非法授权或已过期授权，请重新登录.";
        }
        return errorMessage;
    }
}
