/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2019年12月4日
 * <修改描述:>
 */
package com.tx.local.security.model;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.DigestUtils;

/**
 * MD5密码加密编码器<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2019年12月4日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class DefaultPasswordEncoder implements PasswordEncoder {
    
    /**
     * @param rawPassword
     * @return
     */
    @Override
    public String encode(CharSequence rawPassword) {
        String encodePassword = DigestUtils
                .md5DigestAsHex(rawPassword.toString().getBytes())
                .toUpperCase();
        return encodePassword;
    }
    
    /**
     * @param rawPassword
     * @param encodedPassword
     * @return
     */
    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        if (rawPassword == null) {
            //如果为空
            return false;
        }
        //全不为空
        String rawEncodedPassword = DigestUtils
                .md5DigestAsHex(rawPassword.toString().getBytes())
                .toUpperCase();
        if (StringUtils.equalsAnyIgnoreCase(rawEncodedPassword,
                encodedPassword)) {
            return true;
        } else {
            return false;
        }
    }
    
}
