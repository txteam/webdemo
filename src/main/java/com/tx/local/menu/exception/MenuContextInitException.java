/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2019年4月20日
 * <修改描述:>
 */
package com.tx.local.menu.exception;

import com.tx.core.exceptions.SILException;

/**
 * 菜单容器初始化异常<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2019年4月20日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class MenuContextInitException extends SILException {
    
    /** 注释内容 */
    private static final long serialVersionUID = 4689057509475848100L;
    
    /** <默认构造函数> */
    public MenuContextInitException() {
        super();
    }
    
    /** <默认构造函数> */
    public MenuContextInitException(String message, Throwable cause) {
        super(message, cause);
    }
    
    /** <默认构造函数> */
    public MenuContextInitException(String message) {
        super(message);
    }
}
