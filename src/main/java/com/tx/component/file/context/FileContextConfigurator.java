/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2014年5月11日
 * <修改描述:>
 */
package com.tx.component.file.context;

import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 文件容器配置器<br/>
 * 
 * @author  Administrator
 * @version  [版本号, 2014年5月11日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class FileContextConfigurator implements InitializingBean,
        ApplicationContextAware {
    
    /** spring容器句柄 */
    protected ApplicationContext applicationContext;
    
    /** 如果没有指定系统，则默认的系统id */
    protected String defaultSystemId = "default";
    
    /** 本地文件存储的默认路径 */
    protected String defaultPath;
    
    /** 资源存储映射 serviceType : locationPath */
    protected Map<String, String> locationMapping;
    
    /**
     * @param applicationContext
     * @throws BeansException
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext)
            throws BeansException {
        this.applicationContext = applicationContext;
    }

    /**
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
    }
}
