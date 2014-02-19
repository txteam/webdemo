/*
 * 描          述:  <描述>
 * 修  改   人:  PengQingyang
 * 修改时间:  2013-12-14
 * <修改描述:>
 */
package com.tx.component.mainframe.exception;

import com.tx.core.exceptions.SILException;

/**
 * session丢失异常
 * <功能详细描述>
 * 
 * @author  PengQingyang
 * @version  [版本号, 2013-12-14]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class SessionLostException extends SILException {
    
    /** 注释内容 */
    private static final long serialVersionUID = -3287542432710769409L;
    
    /**
     * @return
     */
    @Override
    protected String doGetErrorCode() {
        return "SESSION_LOST_ERROR";
    }
    
    /**
     * @return
     */
    @Override
    protected String doGetErrorMessage() {
        return "用户登录超时，请重新登录";
    }

    /** <默认构造函数> */
    public SessionLostException(String message, Object[] parameters) {
        super(message, parameters);
    }

    /** <默认构造函数> */
    public SessionLostException(String message) {
        super(message);
    }
}
