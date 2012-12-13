/*
 * 描          述:  <描述>
 * 修  改   人:  PengQingyang
 * 修改时间:  2012-10-14
 * <修改描述:>
 */
package com.boda.los.demo.interceptor;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.boda.los.demo.annotation.DemoAnotation;


 /**
  * <功能简述>
  * <功能详细描述>
  * 
  * @author  PengQingyang
  * @version  [版本号, 2012-10-14]
  * @see  [相关类/方法]
  * @since  [产品/模块版本]
  */
@Component("demoInterceptor")
@Aspect
public class DemoInterceptor {
    
    private Logger logger = LoggerFactory.getLogger(DemoInterceptor.class);
    
    /** <默认构造函数> */
    public DemoInterceptor() {
        logger.info("Init DemoInterceptor...................");
    }
    
    @Around("execution(public void com..*.*(..)) &&"
            + "@annotation(demoAnnotation)")
    public Object doBasicProfiling(ProceedingJoinPoint pjp,
            DemoAnotation demoAnnotation) throws Throwable {
        
        try {
            logger.info("before proceed...............");
            Object res = pjp.proceed();
            logger.info("after proceed...............");
            return res;
        }
        catch (Exception e) {
            logger.info("cache exception...............");
            throw e;
        }finally{
            logger.info("finally...............");
        }
        
    }
    
}
