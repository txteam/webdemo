/*
 * 描          述:  <描述>
 * 修  改   人:  brady
 * 修改时间:  2013-8-22
 * <修改描述:>
 */
package com.tx.component.mainframe.exception;

import com.tx.core.exceptions.SILException;


 /**
  * 菜单容器初始化异常<br/>
  * <功能详细描述>
  * 
  * @author  brady
  * @version  [版本号, 2013-8-22]
  * @see  [相关类/方法]
  * @since  [产品/模块版本]
  */
public class MenuContextInitException extends SILException{

    /** 注释内容 */
    private static final long serialVersionUID = 2163148213756924259L;
    
    /**
     * @return
     */
    @Override
    protected String doGetErrorCode() {
        return "MENU_CONTEXT_INIT_ERROR";
    }

    /**
     * @return
     */
    @Override
    protected String doGetErrorMessage() {
        return "菜单容器初始化异常";
    }

    /** <默认构造函数> */
    public MenuContextInitException(String message, Object[] parameters) {
        super(message, parameters);
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
