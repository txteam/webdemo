/*
 * 描          述:  <描述>
 * 修  改   人:  brady
 * 修改时间:  2012-11-22
 * <修改描述:>
 */
package com.tx.webdemo.demo.resources;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;


 /**
  * <功能简述>
  * <功能详细描述>
  * 
  * @author  brady
  * @version  [版本号, 2012-11-22]
  * @see  [相关类/方法]
  * @since  [产品/模块版本]
  */
public class ResourcesDemo implements ApplicationContextAware{
    
//    private ApplicationContext applicationContext;
//    
//    private GenericApplicationContext genericApplicationContext;
//    
//    private ClassPathBeanDefinitionScanner scanner;
    
    /**
     * @param applicationContext
     * @throws BeansException
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext)
            throws BeansException {
        
    }
    
    public void test(){
    }
    
}
