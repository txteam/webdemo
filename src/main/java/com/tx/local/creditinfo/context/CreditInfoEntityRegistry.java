/*
 * 描          述:  <描述>
 * 修  改   人:  PengQingyang
 * 修改时间:  2020年6月18日
 * <修改描述:>
 */
package com.tx.local.creditinfo.context;

import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.ConstructorUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.AliasRegistry;

import com.tx.core.exceptions.util.AssertUtils;
import com.tx.core.util.ClassScanUtils;
import com.tx.local.creditinfo.model.CreditLinked;
import com.tx.local.creditinfo.model.CreditMultipLinked;
import com.tx.local.creditinfo.model.CreditSingleLinked;
import com.tx.local.creditinfo.service.CreditLinkedService;
import com.tx.local.creditinfo.service.CreditMultipLinkedService;
import com.tx.local.creditinfo.service.CreditSingleLinkedService;
import com.tx.local.creditinfo.service.impl.DefaultCreditMultipLinkedServiceImpl;
import com.tx.local.creditinfo.service.impl.DefaultCreditSingleLinkedServiceImpl;

/**
 * <功能简述>
 * <功能详细描述>
 * 
 * @author  PengQingyang
 * @version  [版本号, 2020年6月18日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class CreditInfoEntityRegistry
        implements InitializingBean, BeanFactoryAware, ApplicationContextAware {
    
    @SuppressWarnings("unused")
    private Logger logger = LoggerFactory
            .getLogger(CreditInfoEntityRegistry.class);
    
    /** spring容器 */
    private ApplicationContext applicationContext;
    
    /** bean定义注册机 */
    private BeanDefinitionRegistry beanDefinitionRegistry;
    
    /** 基础数据扫表包路径 */
    private String packages = "com.tx.local";
    
    /** 别名注册机 */
    private AliasRegistry aliasRegistry;
    
    /** 加载类即可 */
    private Set<Class<? extends CreditLinked>> loadedClassSet = new HashSet<>();
    
    /** 编码到类型的映射 */
    private final Map<String, Class<? extends CreditSingleLinked>> singleEntityClassMap = new HashMap<>();
    
    /** 编码到类型的映射 */
    private final Map<String, Class<? extends CreditMultipLinked>> multipEntityClassMap = new HashMap<>();
    
    /** 1:1 信用信息业务层 */
    private final Map<Class<? extends CreditSingleLinked>, CreditSingleLinkedService<? extends CreditSingleLinked>> singleServiceMap = new HashMap<>();
    
    /** 1:n 信用信息业务层 */
    private final Map<Class<? extends CreditMultipLinked>, CreditMultipLinkedService<? extends CreditMultipLinked>> multipServiceMap = new HashMap<>();
    
    /** <默认构造函数> */
    public CreditInfoEntityRegistry() {
        super();
    }
    
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
     * @param beanFactory
     * @throws BeansException
     */
    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        AssertUtils.isInstanceOf(AliasRegistry.class,
                beanFactory,
                "beanFactory is not SingletonBeanRegistry instance.");
        this.aliasRegistry = (AliasRegistry) beanFactory;
        
        AssertUtils.isInstanceOf(BeanDefinitionRegistry.class,
                beanFactory,
                "beanFactory is not BeanDefinitionRegistry instance.");
        this.beanDefinitionRegistry = (BeanDefinitionRegistry) beanFactory;
    }
    
    /**
     * @throws Exception
     */
    @SuppressWarnings({ "unused", "rawtypes", "unchecked" })
    @Override
    public void afterPropertiesSet() throws Exception {
        Collection<CreditLinkedService> services = this.applicationContext
                .getBeansOfType(CreditLinkedService.class)
                .values();
        
        //循环业务层
        for (CreditLinkedService service : services) {
            //注册业务层
            register(service);
            //加载类集合
            this.loadedClassSet.add(service.type());
        }
        
        //扫描类进行注册
        registerBeanDefinitions(this.beanDefinitionRegistry);
    }
    
    /**
     * 扫描类进行注册
     * <功能详细描述>
     * @param registry [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @SuppressWarnings("unchecked")
    public void registerBeanDefinitions(BeanDefinitionRegistry registry) {
        //扫描基础数据类,自动注册其对应的业务层类
        Set<Class<? extends CreditLinked>> clClassSet = new HashSet<>();
        String[] packageArray = StringUtils.splitByWholeSeparator(this.packages,
                ",");
        for (String packageTemp : packageArray) {
            if (StringUtils.isEmpty(packageTemp)) {
                continue;
            }
            Set<Class<? extends CreditLinked>> classSetTemp = ClassScanUtils
                    .scanByParentClass(CreditLinked.class, packageTemp);
            clClassSet.addAll(classSetTemp);
        }
        
        //加载类与业务层的映射关联
        for (Class<? extends CreditLinked> clType : clClassSet) {
            if (this.loadedClassSet.contains(clType)) {
                //跳过已经加载，或已经具有自定义实现的基础数据类型
                continue;
            }
            this.loadedClassSet.add(clType);
            
            if (clType.isInterface()
                    || Modifier.isAbstract(clType.getModifiers())) {
                //如果是接口或抽象类直接跳过
                continue;
            }
            
            //如果业务层已经被加载则直接跳过
            if (this.singleServiceMap.containsKey(clType)) {
                continue;
            } else if (this.multipServiceMap.containsKey(clType)) {
                continue;
            }
            
            if (CreditSingleLinked.class.isAssignableFrom(clType)) {
                CreditSingleLinkedService<? extends CreditSingleLinked> service = buildCreditSingleLinkedService(
                        registry,
                        (Class<? extends CreditSingleLinked>) clType);
                register(service);
            } else if (CreditMultipLinked.class.isAssignableFrom(clType)) {
                CreditMultipLinkedService<? extends CreditMultipLinked> service = buildCreditMultipLinkedService(
                        registry,
                        (Class<? extends CreditMultipLinked>) clType);
                register(service);
            }
        }
    }
    
    /**
     * 注册基础数据<br/>
     * <功能详细描述>
     * @param type [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @SuppressWarnings("unchecked")
    public void register(CreditLinkedService<? extends CreditLinked> service) {
        AssertUtils.notNull(service, "service is null.");
        
        /** 获取RawType(泛型类型) */
        Class<? extends CreditLinked> type = service.type();
        
        //类型需要为creditLinked的子类
        AssertUtils.isTrue(CreditLinked.class.isAssignableFrom(type),
                "type:{} is not assignableFrom CreditLinked.",
                new Object[] { type });
        //类型不能为接口或抽象类
        AssertUtils.isTrue(
                !type.isInterface()
                        && !Modifier.isAbstract(type.getModifiers()),
                "type is interface or abstract class.type:{}",
                new Object[] { type });
        AssertUtils.notNull(ConstructorUtils.getAccessibleConstructor(type),
                "类型不存在无参构造函数.type:{}",
                new Object[] { type });
        
        //注册写入<br/>
        if (service instanceof CreditSingleLinkedService) {
            Class<? extends CreditSingleLinked> typeTemp = (Class<? extends CreditSingleLinked>) type;
            this.singleServiceMap.put(typeTemp,
                    (CreditSingleLinkedService<? extends CreditSingleLinked>) service);
            this.singleEntityClassMap
                    .put(typeTemp.getSimpleName().toUpperCase(), typeTemp);
        } else if (service instanceof CreditMultipLinkedService) {
            Class<? extends CreditMultipLinked> typeTemp = (Class<? extends CreditMultipLinked>) type;
            this.multipServiceMap.put(typeTemp,
                    (CreditMultipLinkedService<? extends CreditMultipLinked>) service);
            this.multipEntityClassMap
                    .put(typeTemp.getSimpleName().toUpperCase(), typeTemp);
        }
    }
    
    //    /**
    //     * 根据类型名称获取对应的类实例<br/>
    //     * <功能详细描述>
    //     * @param type
    //     * @return [参数说明]
    //     * 
    //     * @return Class<? extends BasicData> [返回类型说明]
    //     * @exception throws [异常类型] [异常说明]
    //     * @see [类、类#方法、类#成员]
    //     */
    //    public Class<? extends BasicData> getEntityClass(String type) {
    //        AssertUtils.notEmpty(type, "type is empty.");
    //        
    //        Class<? extends BasicData> entityClass = this.entityClassMap.get(type);
    //        return entityClass;
    //    }
    
    //    /**
    //     * 获取类对应的类型名<br/>
    //     * <功能详细描述>
    //     * @param entityClass
    //     * @return [参数说明]
    //     * 
    //     * @return String [返回类型说明]
    //     * @exception throws [异常类型] [异常说明]
    //     * @see [类、类#方法、类#成员]
    //     */
    //    public String getType(Class<? extends BasicData> entityClass) {
    //        AssertUtils.notNull(entityClass, "entityClass is null.");
    //        
    //        String type = this.class2typeMap.get(entityClass);
    //        return type;
    //    }
    
    /**
     * 获取实例信息<br/>
     * <功能详细描述>
     * @param type
     * @return [参数说明]
     * 
     * @return BasicDataEntityInfo [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public CreditSingleLinkedService<? extends CreditSingleLinked> getSingleService(
            Class<? extends CreditSingleLinked> type) {
        AssertUtils.notNull(type, "type is null.");
        
        CreditSingleLinkedService<? extends CreditSingleLinked> service = this.singleServiceMap
                .get(type);
        return service;
    }
    
    /**
     * 获取实例信息<br/>
     * <功能详细描述>
     * @param entityClass
     * @return [参数说明]
     * 
     * @return BasicDataEntityInfo [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public CreditMultipLinkedService<? extends CreditMultipLinked> getMultipService(
            Class<? extends CreditMultipLinked> type) {
        AssertUtils.notNull(type, "type is null.");
        
        CreditMultipLinkedService<? extends CreditMultipLinked> service = this.multipServiceMap
                .get(type);
        return service;
    }
    
    /**
     * 构建默认的基础数据业务类<br/>
     * <功能详细描述>
     * @param type [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @SuppressWarnings({ "unchecked" })
    public <T extends CreditSingleLinked> CreditSingleLinkedService<T> buildCreditSingleLinkedService(
            BeanDefinitionRegistry registry, Class<T> type) {
        AssertUtils.notNull(registry, "registry is null.");
        AssertUtils.notNull(type, "type is null.");
        
        BeanDefinitionBuilder builder = BeanDefinitionBuilder
                .genericBeanDefinition(
                        DefaultCreditSingleLinkedServiceImpl.class);
        builder.addPropertyValue("type", type);
        
        //注册，生成，并返回业务层
        String beanName = generateServiceBeanName(type);
        if (!registry.containsBeanDefinition(beanName)) {
            logger.debug(
                    "动态注入基础数据业务层定义: beanName:{} Type:com.tx.local.creditinfo.service.impl.DefaultCreditSingleLinkedServiceImpl",
                    beanName);
            
            registry.registerBeanDefinition(beanName,
                    builder.getBeanDefinition());
        }
        //利用有参构造函数
        CreditSingleLinkedService<T> service = (CreditSingleLinkedService<T>) this.applicationContext
                .getBean(beanName);
        return service;
    }
    
    /**
     * 构建默认的基础数据业务类<br/>
     * <功能详细描述>
     * @param type [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @SuppressWarnings({ "unchecked" })
    public <T extends CreditMultipLinked> CreditMultipLinkedService<T> buildCreditMultipLinkedService(
            BeanDefinitionRegistry registry, Class<T> type) {
        AssertUtils.notNull(registry, "registry is null.");
        AssertUtils.notNull(type, "type is null.");
        
        BeanDefinitionBuilder builder = BeanDefinitionBuilder
                .genericBeanDefinition(
                        DefaultCreditMultipLinkedServiceImpl.class);
        builder.addPropertyValue("type", type);
        
        //注册，生成，并返回业务层
        String beanName = generateServiceBeanName(type);
        if (!registry.containsBeanDefinition(beanName)) {
            logger.debug(
                    "动态注入基础数据业务层定义: beanName:{} Type:com.tx.local.creditinfo.service.impl.DefaultCreditSingleLinkedServiceImpl",
                    beanName);
            
            registry.registerBeanDefinition(beanName,
                    builder.getBeanDefinition());
        }
        //利用有参构造函数
        CreditMultipLinkedService<T> service = (CreditMultipLinkedService<T>) this.applicationContext
                .getBean(beanName);
        return service;
    }
    
    /**
     * 生成对应的业务层Bean名称<br/>
     * <功能详细描述>
     * @param type
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    private <T extends CreditLinked> String generateServiceBeanName(
            Class<T> type) {
        String beanName = "credit."
                + StringUtils.uncapitalize(type.getSimpleName()) + "Service";
        return beanName;
    }
}
