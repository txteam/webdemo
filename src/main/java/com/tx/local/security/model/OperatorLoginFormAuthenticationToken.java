/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2019年7月29日
 * <修改描述:>
 */
package com.tx.local.security.model;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

/**
 * 操作人员登陆表单认证Token<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2019年7月29日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class OperatorLoginFormAuthenticationToken
        extends UsernamePasswordAuthenticationToken {
    
    /** 注释内容 */
    private static final long serialVersionUID = -4280224210760891569L;
    
    /** 存储验证码 */
    private String captcha;
    
    /** <默认构造函数> */
    public OperatorLoginFormAuthenticationToken(String username,
            String password) {
        super(username, password);
        setAuthenticated(false);
    }
    
    /** <默认构造函数> */
    public OperatorLoginFormAuthenticationToken(String username,
            String password, String captcha) {
        this(username, password);
        this.captcha = captcha;
        setAuthenticated(false);
    }
    
    /**
     * @return 返回 captcha
     */
    public String getCaptcha() {
        return captcha;
    }
    
    /**
     * @param 对captcha进行赋值
     */
    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }
}
