package com.tx.local.clientinfo;

import java.util.Date;

import org.joda.time.DateTime;

/**
 * 客户信息常量类<br/>
 * 
 * @author bobby
 * @version [版本号, 2016年5月16日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public interface ClientInfoConstants {
    
    /** 文件容器模块名 */
    String MODULE_CLIENT_INFO = "clientinfo";
    
    /** 默认的到期日期 */
    Date DEFAULT_EXPIRY_DATE = (new DateTime(9999, 12, 31, 23, 59, 59)).toDate();
    
    int CHECKPASSWORDMAXERRORCOUNT = 5;
    
    int CLIENTINFOLOCKEDDATE = 20;
    
    /** 返回值关键字：客户信息 */
    String CL_RESPONSE_KEY_CLIENT_INFO = "CL_RESPONSE_KEY_CLIENT_INFO";

    /** 返回关键字: 机构信息 */
    String CL_RESPONSE_KEY_INSTITUTION  = "CL_RESPONSE_KEY_INSTITUTION";
    
    /** 交易关键字：客户ID-客户_映射 */
    String CL_SESSION_KEY_CLIENT_INFO_HANDLER = "CL_SESSION_KEY_CLIENT_INFO_HANDLER";
    
    /** 返回关键字：个人信息 */
    String CL_RESPONSE_KEY_PERSONAL  = "CL_RESPONSE_KEY_PERSONAL";

    /** 交易关键字：客户ID-客户_映射 */
    String CL_SESSION_KEY_CLIENT_INFO = "CL_SESSION_KEY_CLIENT_INFO";
    
    /** 交易关键字：客户ID-客户_映射 */
    String CL_SESSION_KEY_PERSONAL = "CL_SESSION_KEY_PERSONAL";
    
    /** 交易关键字：客户ID-客户_映射 */
    String CL_SESSION_KEY_INSTITUTION = "CL_SESSION_KEY_INSTITUTION";
    
    /** 交易关键字：客户ID-客户_映射 */
    String CL_SESSION_KEY_INSTITUTION_MEMBER = "CL_SESSION_KEY_INSTITUTION_MEMBER";
}
