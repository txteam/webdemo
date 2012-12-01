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
 * <支持数据权限声明>
 * <功能详细描述>
 * 
 * @author  PengQingyang
 * @version  [版本号, 2012-11-30]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface AuthCheckForDataColumn {
    
    /**
      * 无权限的处理方式：提供集中默认方式
      * 因页面显示字段均为String
      * 该处理器只在String类型字段时才生效，在非String类上自动失效
      * showEmpty : 显示为空
      * maskInfo ：遮罩信息   方式通过注入权限容器中可自定义添加
      * 
      * @return void [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    String noAuthProcessor() default "showEmpty";
    
    /**
      *<将传递给处理器的参数>
      * 如果为遮罩，则传入，遮罩规则
      * @return [参数说明]
      * 
      * @return String[] [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    String[] parameters() default "";
}
