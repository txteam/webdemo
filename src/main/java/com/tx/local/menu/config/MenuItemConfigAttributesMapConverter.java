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

import org.apache.commons.lang3.ClassUtils;
import org.springframework.beans.BeanUtils;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

/**
 * 让菜单项目支持data放入任意值的设定<br/>
 * <功能详细描述>
 * 
 * @author  brady
 * @version  [版本号, 2013-8-22]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class MenuItemConfigAttributesMapConverter implements Converter {
    
    //菜单项目配置属性映射
    private static final Map<String, PropertyDescriptor> TYPE_PD_MAP = new HashMap<>();
    
    static{
        for(PropertyDescriptor pdTemp : BeanUtils.getPropertyDescriptors(MenuItemConfig.class)){
            if(pdTemp.getReadMethod() == null || pdTemp.getWriteMethod() == null){
                continue;
            }
            TYPE_PD_MAP.put(pdTemp.getName(), pdTemp);
        }
    }
    
    /** <默认构造函数> */
    public MenuItemConfigAttributesMapConverter() {
        super();
    }
    
    /**
     * @param type
     * @return
     */
    @SuppressWarnings("rawtypes")
    @Override
    public boolean canConvert(Class type) {
        return ClassUtils.isAssignable(type,Map.class);
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
        Map<String, String> data = (Map<String, String>) source;
        //遍历数据，写入动态参数
        for (Entry<String, String> entryTemp : data.entrySet()) {
            String key = entryTemp.getKey();
            if(TYPE_PD_MAP.containsKey(key)){
                continue;
            }
            writer.addAttribute(entryTemp.getKey(), entryTemp.getValue());
        }
    }
    
    /**
     * @param reader
     * @param context
     * @return
     */
    @Override
    public Object unmarshal(HierarchicalStreamReader reader,
            UnmarshallingContext context) {
        Map<String, String> data = new HashMap<String, String>();
        
        @SuppressWarnings("unchecked")
        Iterator<String> attNameIterator = reader.getAttributeNames();
        while (attNameIterator.hasNext()) {
            String attNameTemp = attNameIterator.next();
            if(TYPE_PD_MAP.containsKey(attNameTemp)){
                continue;
            }
            
            data.put(attNameTemp, reader.getAttribute(attNameTemp));
        }
        return data;
    }
    
}
