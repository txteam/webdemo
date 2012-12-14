/*
 * 描          述:  <描述>
 * 修  改   人:  brady
 * 修改时间:  2012-12-14
 * <修改描述:>
 */
package com.tx.components.auth.annotation;

import com.tx.components.auth.AuthConstant;


 /**
  * 操作权限注解
  * 添加于controller的method上
  * 
  * @author  brady
  * @version  [版本号, 2012-12-14]
  * @see  [相关类/方法]
  * @since  [产品/模块版本]
  */
public @interface AuthOperate {
    
    String key();
    
    String parentKey();
    
    String name();
}
