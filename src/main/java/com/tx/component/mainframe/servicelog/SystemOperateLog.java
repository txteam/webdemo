/*
 * 描          述:  <描述>
 * 修  改   人:  brady
 * 修改时间:  2013-10-9
 * <修改描述:>
 */
package com.tx.component.mainframe.servicelog;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.tx.component.servicelog.defaultimpl.TXBaseServiceLog;

/**
 * 系统操作日志
 * <功能详细描述>
 * 
 * @author  brady
 * @version  [版本号, 2013-10-9]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Entity
@Table(name = "MAINFRAME_SYSOPE_LOG")
public class SystemOperateLog extends TXBaseServiceLog {
    
    /** 所属系统id */
    private String systemId;
    
    /** 所属功能 */
    private String function;
    
    /** <默认构造函数> */
    public SystemOperateLog() {
    }
    
    /** <默认构造函数> */
    public SystemOperateLog(String systemId, String function, String message,
            Object[] params) {
        super(message, params);
        this.systemId = systemId;
        this.function = function;
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
     * @return 返回 function
     */
    public String getFunction() {
        return function;
    }

    /**
     * @param 对function进行赋值
     */
    public void setFunction(String function) {
        this.function = function;
    }
}
