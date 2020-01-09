///*
// * 描          述:  <描述>
// * 修  改   人:  Administrator
// * 修改时间:  2020年1月7日
// * <修改描述:>
// */
//package com.tx.local.boot;
//
//import java.util.Arrays;
//
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.annotation.After;
//import org.aspectj.lang.annotation.AfterReturning;
//import org.aspectj.lang.annotation.AfterThrowing;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.aspectj.lang.annotation.Pointcut;
//import org.springframework.stereotype.Component;
//
///**
// * 业务日志切面<br/>
// * 参考文章: https://www.sohu.com/a/231979874_355142
// * <功能详细描述>
// * 
// * @author  Administrator
// * @version  [版本号, 2020年1月7日]
// * @see  [相关类/方法]
// * @since  [产品/模块版本]
// */
//@Component
//@Aspect
//public class ServiceLoggerAop {
//    
//    @Pointcut("execution(public void com.tx..ServiceLogger.insert(..))")
//    public void PointcutDeclaration() {
//    }
//    
//    @Before("PointcutDeclaration()")
//    public void BeforeMethod(JoinPoint jp) {
//        String methodName = jp.getSignature().getName();
//        Object[] args = jp.getArgs();
//        System.out.println("BeforeMethod: The method=" + methodName
//                + " parameter=[" + Arrays.asList(args) + "]");
//    }
//    
//    //后置通知,方法执行之后执行(不管是否发生异常)
//    @After("PointcutDeclaration()")
//    public void AfterMethod(JoinPoint jp) {
//        String methodName = jp.getSignature().getName();
//        Object[] args = jp.getArgs();
//        System.out.println("AfterMethod: The method=" + methodName
//                + " parameter=[" + Arrays.asList(args) + "]");
//    }
//    
//    //返回通知,方法正常执行完毕之后执行
//    @AfterReturning(value = "PointcutDeclaration()", returning = "result")
//    public void AfterReturningMethod(JoinPoint jp, Object result) {
//        String methodName = jp.getSignature().getName();
//        Object[] args = jp.getArgs();
//        System.out.println("AfterReturningMethod: The method=" + methodName
//                + ",parameter=[" + Arrays.asList(args) + "],result=" + result);
//    }
//    
//    //异常通知,在方法抛出异常之后执行
//    @AfterThrowing(value = "PointcutDeclaration()", throwing = "e")
//    public void AfterThrowingMethod(JoinPoint jp, Exception e) {
//        String methodName = jp.getSignature().getName();
//        Object[] args = jp.getArgs();
//        System.out.println("AfterThrowingMethod: The method=" + methodName
//                + ",parameter=[" + Arrays.asList(args) + "],exception="
//                + e.getMessage());
//    }
//    
//    //    @Before("target(ServiceLogger)")
//    //    public void test4(JoinPoint joinPoint) {
//    //        System.out.println("test4 ...");
//    //    }
//    
//    @Pointcut("target(com.tx.component.servicelogger.support.ServiceLogger)")
//    protected void test3() {
//        System.out.println("test3 ...");
//    }
//    
//    @Before("test3()")
//    public void test2(JoinPoint joinPoint) {
//        System.out.println("test2 ...");
//    }
//    
//    @Before("target(com.tx.component.servicelogger.support.ServiceLogger)")
//    public void test1(JoinPoint joinPoint) {
//        System.out.println("test1 ...");
//    }
//}
