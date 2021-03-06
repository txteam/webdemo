/*
 * 描          述:  <描述>
 * 修  改   人:  brady
 * 修改时间:  2013-8-22
 * <修改描述:>
 */
package com.tx.local.menu.config;

import java.beans.PropertyDescriptor;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;

import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.converters.reflection.AbstractReflectionConverter;
import com.thoughtworks.xstream.converters.reflection.ReflectionProvider;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.mapper.Mapper;
import com.thoughtworks.xstream.mapper.MapperWrapper;

/**
 * 让菜单项目支持data放入任意值的设定<br/>
 * <功能详细描述>
 * 
 * @author  brady
 * @version  [版本号, 2013-8-22]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class MenuItemConfigConverter extends AbstractReflectionConverter {
    
    //菜单项目配置属性映射
    private static final Map<String, PropertyDescriptor> TYPE_PD_MAP = new HashMap<>();
    
    static {
        for (PropertyDescriptor pdTemp : BeanUtils
                .getPropertyDescriptors(MenuItemConfig.class)) {
            //|| !String.class.isAssignableFrom(pdTemp.getReadMethod().getReturnType())
            if (pdTemp.getReadMethod() == null
                    || pdTemp.getWriteMethod() == null) {
                continue;
            }
            TYPE_PD_MAP.put(pdTemp.getName(), pdTemp);
        }
    }
    
    /** <默认构造函数> */
    public MenuItemConfigConverter(Mapper mapper,
            ReflectionProvider reflectionProvider) {
        super(new MapperWrapper(mapper) {
            @SuppressWarnings("rawtypes")
            @Override
            public boolean shouldSerializeMember(Class definedIn,
                    String fieldName) {
                if (!super.shouldSerializeMember(definedIn, fieldName)) {
                    return false;
                } else {
                    if (FieldUtils.getDeclaredField(definedIn,
                            fieldName,
                            true) == null) {
                        return false;
                    } else {
                        return true;
                    }
                }
            }
        }, reflectionProvider);
    }
    
    /**
     * @param type
     * @return
     */
    @SuppressWarnings("rawtypes")
    @Override
    public boolean canConvert(Class type) {
        return canAccess(type) && MenuItemConfig.class.isAssignableFrom(type);
    }
    
    /**
     * @param source
     * @param writer
     * @param context
     */
    @SuppressWarnings("unchecked")
    @Override
    public void marshal(Object source, HierarchicalStreamWriter writer,
            MarshallingContext context) {
        if (source == null) {
            return;
        }
        
        //如果不为空
        BeanWrapper bw = PropertyAccessorFactory.forBeanPropertyAccess(source);
        Map<String, String> attributesMap = (Map<String, String>) bw
                .getPropertyDescriptor("attributes");
        //遍历数据，写入动态参数
        if (MapUtils.isEmpty(attributesMap)) {
            return;
        }
        for (Entry<String, String> entryTemp : attributesMap.entrySet()) {
            String key = entryTemp.getKey();
            if (TYPE_PD_MAP.containsKey(key)) {
                //已经存在的属性不进行重复写入
                continue;
            }
            writer.addAttribute(entryTemp.getKey(), entryTemp.getValue());
        }
        
        //调用超类写入
        super.marshal(source, writer, context);
    }
    
    /**
     * @param reader
     * @param context
     * @return
     */
    @Override
    public Object unmarshal(HierarchicalStreamReader reader,
            UnmarshallingContext context) {
        MenuItemConfig catalog = (MenuItemConfig) super.unmarshal(reader,
                context);
        
        Map<String, String> attributesMap = new HashMap<String, String>();
        @SuppressWarnings("unchecked")
        Iterator<String> attNameIterator = reader.getAttributeNames();
        while (attNameIterator.hasNext()) {
            String attNameTemp = attNameIterator.next();
            String value = reader.getAttribute(attNameTemp);
            if (TYPE_PD_MAP.containsKey(attNameTemp)) {
                continue;
            }
            attributesMap.put(attNameTemp, value);
        }
        catalog.setAttributes(attributesMap);
        
        return catalog;
    }
    
}
