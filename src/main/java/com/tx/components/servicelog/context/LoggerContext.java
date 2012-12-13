package com.tx.components.servicelog.context;

import java.beans.PropertyDescriptor;
import java.util.Date;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

/**
 * 日志公共方法
 * 
 * @author liujun
 * 
 */
public class LoggerContext implements FactoryBean<LoggerContext>,
        InitializingBean {
    
    private static LoggerContext context = null;
    
    private static final String[] loggerInterfacePath = { "com.boda.web.controller" };
    
    /** 用以冲在配置属性使用 */
    private PropertyDescriptor properties;
    
    /** 配置文件存储路径 */
    private Resource configLocation;
    
    /** 资源加载工厂  */
    private FactoryBean<ResourceLoader> resourceLoaderFactory;
    
    
    
    /**
     * 私有化构造方法
     */
    private LoggerContext() {
        super();
        
    }
    
    private void setContext(LoggerContext context) {
        LoggerContext.context = context;
    }
    
    /**
     * 获取日志容器
     * 
     * @return
     */
    public static LoggerContext getContext() {
        return LoggerContext.context;
    }
    
    /**
     * 初始化读取配置文件
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        //reLoadConfig();
        
        setContext(this);
    }
    
    @Override
    public LoggerContext getObject() throws Exception {
        return LoggerContext.context;
    }
    
    @Override
    public Class<?> getObjectType() {
        return LoggerContext.class;
    }
    
    @Override
    public boolean isSingleton() {
        return true;
    }
    
    /**
     * @return the configLocation
     */
    public Resource getConfigLocation() {
        return configLocation;
    }
    
    /**
     * @param configLocation
     *            the configLocation to set
     */
    public void setConfigLocation(Resource configLocation) {
        this.configLocation = configLocation;
    }

    /**
     * LoggerInterface配置类是配置配置在类上还是在方法上
     * 
     * @author liujun
     * */
    public enum loggerInterfaceEnum {
        clazz, method
    }
}
