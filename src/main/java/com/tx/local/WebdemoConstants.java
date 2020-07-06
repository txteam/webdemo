/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2019年12月6日
 * <修改描述:>
 */
package com.tx.local;


/**
 * Webdemo中使用到的常量<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2019年12月6日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface WebdemoConstants {
    
    /* ********************* 权限类型 start**************************** */
    
    /** 操作员操作权限 */
    String AUTH_TYPE_OPERATOR_OPERATE = "AUTH_TYPE_OPERATOR_OPERATE";
    
    /** 操作员操作权限 */
    String AUTH_TYPE_OPERATOR_DATA = "AUTH_TYPE_OPERATOR_DATA";
    
    /* ********************* 权限类型 end ***************************** */
    
    /* ********************* 系统配置 start******************************** */
    /** 系统配置的默认密码关键字 */
    String CONFIG_DEFAULT_OPERATOR_PASSWORD_CODE = "system.config.operator.default.password";
    
    /** 系统配置的默认密码关键字 */
    String CONFIG_DEFAULT_CLIENT_PASSWORD_CODE = "system.config.client.default.password";
    
    /* ********************* 系统配置 end ***************************** */
}
