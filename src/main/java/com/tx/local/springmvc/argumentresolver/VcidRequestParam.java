///*
// * 描          述:  <描述>
// * 修  改   人:  Administrator
// * 修改时间:  2018年5月28日
// * <修改描述:>
// */
//package com.tx.local.springmvc.argumentresolver;
//
//import java.lang.annotation.Documented;
//import java.lang.annotation.ElementType;
//import java.lang.annotation.Retention;
//import java.lang.annotation.RetentionPolicy;
//import java.lang.annotation.Target;
//
///**
// * Json字段对应参数解析<br/>
// *   说明，之所以注释掉该实现，主要考虑在创建对象期间vcid无法接收的情况依然存在
// *   改变办法为，增加拦截器进行处理即可
// * <功能详细描述>
// * 
// * @author  Administrator
// * @version  [版本号, 2018年5月28日]
// * @see  [相关类/方法]
// * @since  [产品/模块版本]
// */
//@Target(ElementType.PARAMETER)
//@Retention(RetentionPolicy.RUNTIME)
//@Documented
//public @interface VcidRequestParam {
//    
//}