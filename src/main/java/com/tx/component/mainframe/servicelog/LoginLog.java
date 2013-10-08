/*
 * 描          述:  <描述>
 * 修  改   人:  brady
 * 修改时间:  2013-10-8
 * <修改描述:>
 */
package com.tx.component.mainframe.servicelog;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.tx.component.servicelog.defaultimpl.TXBaseServiceLog;

/**
 * 登录日志<br/>
 * <功能详细描述>
 * 
 * @author  brady
 * @version  [版本号, 2013-10-8]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Entity
@Table(name = "mainframe_login_log")
public class LoginLog extends TXBaseServiceLog {
    
    /** 所属系统id */
    private String systemId;
    
    /** 登录：0 登出 1 */
    private int loginType;
    
    /** <默认构造函数> */
    public LoginLog(String systemId, String type, String message,
            Object[] messageParams) {
        super(message, messageParams);
    }
    
    /**
     * @return 返回 systemId
     */
    public String getSystemId() {
        return systemId;
    }
    
    /**
     * @param 对systemId进行赋值
     */
    public void setSystemId(String systemId) {
        this.systemId = systemId;
    }

    /**
     * @return 返回 loginType
     */
    public int getLoginType() {
        return loginType;
    }

    /**
     * @param 对loginType进行赋值
     */
    public void setLoginType(int loginType) {
        this.loginType = loginType;
    }
}
