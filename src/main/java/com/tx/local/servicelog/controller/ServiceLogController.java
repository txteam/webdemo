/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2020年1月8日
 * <修改描述:>
 */
package com.tx.local.servicelog.controller;

import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.OrderComparator;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tx.component.servicelogger.annotation.ServiceLog;
import com.tx.component.servicelogger.context.ServiceLoggerContext;
import com.tx.component.servicelogger.support.ServiceLogger;
import com.tx.core.exceptions.util.AssertUtils;
import com.tx.core.paged.model.PagedList;
import com.tx.core.querier.model.Querier;
import com.tx.core.querier.model.QuerierBuilder;
import com.tx.core.util.ClassScanUtils;
import com.tx.local.servicelog.model.ServiceLogProperty;

import io.swagger.annotations.ApiModelProperty;

/**
 * 业务日志控制层<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2020年1月8日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Controller("ServiceLogController")
@RequestMapping("/servicelog")
public class ServiceLogController
        implements InitializingBean, ResourceLoaderAware {
    
    @Resource
    private ServiceLoggerContext context;
    
    /** 页面映射 */
    private final Map<String, String> pageMap = new HashMap<>();
    
    /** 默认的配置页 */
    private final String DEFAULT_QUERY_PAGEDLIST_PAGE = "servicelog/queryPagedList";
    
    /** 资源读取器 */
    private ResourceLoader resourceLoader;
    
    /** 业务日志类型映射 */
    private final Map<String, Class<?>> typeMap = new HashMap<>();
    
    /** 类属性映射 */
    private final Map<Class<?>, List<ServiceLogProperty>> typePropertyMap = new HashMap<>();
    
    /**
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        Set<Class<?>> types = ClassScanUtils.scanByAnnotation(ServiceLog.class,
                "com.tx");
        types.stream().forEach(t -> {
            AssertUtils.isTrue(
                    !this.typeMap.containsKey(t.getSimpleName().toLowerCase()),
                    "duplicat type:{}",
                    t.getSimpleName().toLowerCase());
            this.typeMap.put(t.getSimpleName().toLowerCase(), t);
        });
    }
    
    /**
     * @param resourceLoader
     */
    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }
    
    /**
     * 跳转到查询DataDict列表页面<br/>
     * <功能详细描述>
     * @return [参数说明]
     *
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/toQueryPagedList")
    public String toQueryPagedList(
            @RequestParam(value = "type", required = true) String type,
            @RequestParam Map<String, String> requestMap, ModelMap response) {
        //基础数据类型集合
        if (!this.typeMap.containsKey(type.toLowerCase())) {
            return DEFAULT_QUERY_PAGEDLIST_PAGE;
        }
        
        Class<?> clazz = this.typeMap.get(type.toLowerCase());
        String page = getQueryPage(clazz);//DEFAULT_QUERY_PAGEDLIST_PAGE;
        if (DEFAULT_QUERY_PAGEDLIST_PAGE.equals(page)) {
            List<ServiceLogProperty> properties = null;
            if (typePropertyMap.containsKey(clazz)) {
                properties = typePropertyMap.get(clazz);
            } else {
                properties = parse(clazz);
                typePropertyMap.put(clazz, properties);
            }
            response.put("properties", properties);
        }
        response.put("type", type);
        return page;
    }
    
    /**
     * 查询业务日志分页列表<br/>
     * <功能详细描述>
     * @param type
     * @param pageIndex
     * @param pageSize
     * @param requestMap
     * @param response
     * @return [参数说明]
     * 
     * @return List<?> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @SuppressWarnings("rawtypes")
    @ResponseBody
    @RequestMapping("/queryPagedList")
    public PagedList<?> queryPagedList(
            @RequestParam(value = "type", required = true) String type,
            @RequestParam(value = "pageNumber", required = false, defaultValue = "1") int pageIndex,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
            @RequestParam Map<String, String> requestMap, ModelMap response) {
        if (!this.typeMap.containsKey(type.toLowerCase())) {
            return new PagedList<>();
        }
        
        Class<?> clazz = this.typeMap.get(type.toLowerCase());
        ServiceLogger logger = this.context.getLogger(clazz);
        Querier querier = QuerierBuilder.newInstance().querier();
        querier.getParams().putAll(requestMap);
        PagedList<?> pagedList = logger.queryPagedList(querier,
                pageIndex,
                pageSize);
        return pagedList;
    }
    
    /**
     * 获取页面名称<br/>
     * <功能详细描述>
     * @param pageType
     * @param basicDataTypeCode
     * @return [参数说明]
     *
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    private String getQueryPage(Class<?> type) {
        AssertUtils.notNull(type, "type is emt.");
        
        //页面缓存键
        String pageCacheKey = type.getName();
        String page = "";
        //未来注释掉,方便调试加入
        //pageMap.clear();
        if (pageMap.containsKey(pageCacheKey)) {
            page = pageMap.get(pageCacheKey);
            return page;
        }
        
        String parentPackage = type.getName();
        String[] paths = StringUtils.splitByWholeSeparator(type.getName(), ".");
        parentPackage = paths.length >= 3 ? paths[paths.length - 3] : "";
        if (!StringUtils.isEmpty(parentPackage)) {
            page = "servicelog/" + parentPackage + "/query"
                    + type.getSimpleName() + "PagedList";
            if (existPage(page)) {
                pageMap.put(pageCacheKey, page);
                return page;
            }
        }
        
        page = "servicelog/" + "query" + type.getSimpleName() + "PagedList";
        if (!existPage(page)) {
            //如果页面不存在获取其对应的默认页面
            page = DEFAULT_QUERY_PAGEDLIST_PAGE;
        }
        pageMap.put(pageCacheKey, page);
        return page;
    }
    
    /**
     * 判断html页面是否存在
     * <功能详细描述>
     * @param page
     * @return [参数说明]
     *
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    private boolean existPage(String page) {
        StringBuilder pageSB = new StringBuilder("classpath:templates/");
        pageSB.append(page).append(".html");
        org.springframework.core.io.Resource pageResource = this.resourceLoader
                .getResource(pageSB.toString());
        if (pageResource.exists()) {
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * 解析业务日志属性<br/>
     * <功能详细描述>
     * @param beanType
     * @return [参数说明]
     * 
     * @return List<ServiceLogProperty> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    private static List<ServiceLogProperty> parse(Class<?> beanType) {
        AssertUtils.notNull(beanType, "beanType is null.");
        
        List<ServiceLogProperty> properties = new ArrayList<ServiceLogProperty>();
        BeanWrapper bw = new BeanWrapperImpl(beanType);
        for (PropertyDescriptor pd : bw.getPropertyDescriptors()) {
            if (pd.getReadMethod() == null || pd.getWriteMethod() == null) {
                continue;
            }
            String field = pd.getName();
            TypeDescriptor td = bw.getPropertyTypeDescriptor(field);
            ApiModelProperty pa = td.getAnnotation(ApiModelProperty.class);
            String title = field;
            boolean hidden = false;
            int order = 0;
            if (pa != null) {
                if (!StringUtils.isBlank(pa.name())) {
                    title = pa.name();
                }
                if (!StringUtils.isBlank(pa.value())) {
                    title = pa.value();
                }
                hidden = pa.hidden();
                order = pa.position();
            }
            ServiceLogProperty prop = new ServiceLogProperty(field, title,
                    hidden, order);
            properties.add(prop);
        }
        Collections.sort(properties, OrderComparator.INSTANCE);
        return properties;
    }
}
