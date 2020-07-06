/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2020年1月13日
 * <修改描述:>
 */
package com.tx.local.creditinfo.context;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.tx.core.exceptions.util.AssertUtils;
import com.tx.core.util.ClassScanUtils;
import com.tx.local.creditinfo.model.CreditMultipLinked;
import com.tx.local.creditinfo.model.CreditSingleLinked;
import com.tx.local.creditinfo.service.CreditMultipLinkedService;
import com.tx.local.creditinfo.service.CreditSingleLinkedService;

/**
 * 信用信息容器<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2020年1月13日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class CreditInfoContextBuilder
        implements InitializingBean, ApplicationContextAware {
    
    private Map<Class<? extends CreditSingleLinked>, CreditSingleLinkedService<? extends CreditSingleLinked>> singleServiceMap = new HashMap<>();
    
    private Map<Class<? extends CreditMultipLinked>, CreditMultipLinkedService<? extends CreditMultipLinked>> multipServiceMap = new HashMap<>();
    
    private ApplicationContext applicationContext;
    
    /**
     * @param applicationContext
     * @throws BeansException
     */
    @SuppressWarnings("unchecked")
    @Override
    public void setApplicationContext(ApplicationContext applicationContext)
            throws BeansException {
        this.applicationContext = applicationContext;
        
        //1：1业务层加载
        for (CreditSingleLinkedService<? extends CreditSingleLinked> service : this.applicationContext
                .getBeansOfType(CreditSingleLinkedService.class)
                .values()) {
            AssertUtils.notNull(service.type(),
                    "service.type is null.{}",
                    new Object[] { service });
            
            //
            singleServiceMap.put(service.type(), service);
        }
        Set<Class<? extends CreditSingleLinked>> singleTypeSet = ClassScanUtils
                .scanByParentClass(CreditSingleLinked.class, "com.tx.local");
        
        for (Class<? extends CreditSingleLinked> type : singleTypeSet) {
            if (singleServiceMap.containsKey(type)) {
                continue;
            }
            
            //singleServiceMap.put(type, service);
        }
        
        for (CreditMultipLinkedService<? extends CreditMultipLinked> service : this.applicationContext
                .getBeansOfType(CreditMultipLinkedService.class)
                .values()) {
            AssertUtils.notNull(service.type(),
                    "service.type is null.{}",
                    new Object[] { service });
            
            multipServiceMap.put(service.type(), service);
        }
        
    }
    
    /**
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        // TODO Auto-generated method stub
        
    }
    
}
