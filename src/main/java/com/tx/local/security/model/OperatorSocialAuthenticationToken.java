/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2019年7月29日
 * <修改描述:>
 */
package com.tx.local.security.model;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 操作人员登陆表单认证Token<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2019年7月29日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class OperatorSocialAuthenticationToken
        extends AbstractAuthenticationToken {
    
    /** 注释内容 */
    private static final long serialVersionUID = 5955091348782437065L;
    
    /** 插件 */
    private String plugin;
    
    /** 编码 */
    private String code;
    
    /** 状态值 */
    private String state;
    
    /** <默认构造函数> */
    public OperatorSocialAuthenticationToken() {
        super(null);
    }
    
    /**
     * @param details
     */
    @Override
    public void setDetails(Object details) {
        super.setDetails(details);
    }
    
    /**
     * @return
     */
    @Override
    public Object getCredentials() {
        // TODO Auto-generated method stub
        return null;
    }
    
    /**
     * @return
     */
    @Override
    public Object getPrincipal() {
        // TODO Auto-generated method stub
        return null;
    }
    
}
