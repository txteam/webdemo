/*
 * 描          述:  <描述>
 * 修  改   人:  brady
 * 修改时间:  2012-11-30
 * <修改描述:>
 */
package com.tx.component.auth.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


 /**
  * <操作权限检测>
  * <功能详细描述>
  * 
  * @author  PengQingyang
  * @version  [版本号, 2012-11-30]
  * @see  [相关类/方法]
  * @since  [产品/模块版本]
  */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface AuthCheckForOperate {
    
    /** 需检测的权限项 */
    String keys();
    
}
