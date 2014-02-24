/*
 * 描          述:  <描述>
 * 修  改   人:  brady
 * 修改时间:  2013-8-22
 * <修改描述:>
 */
package com.tx.component.mainframe.config;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.ClassUtils;

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
public class MenuItemDataMapConverter implements Converter {
    
    /**
     * 解析data的正则表达式<br/>
     */
    private static Pattern dataPatter = Pattern.compile("^data[(.+?)]$");
    
    /**
     * @param type
     * @return
     */
    @SuppressWarnings("rawtypes")
    @Override
    public boolean canConvert(Class type) {
        return ClassUtils.isAssignable(Map.class, type);
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
            writer.addAttribute("data[" + entryTemp.getKey() + "]",
                    entryTemp.getValue());
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
        while(attNameIterator.hasNext()){
            String attNameTemp = attNameIterator.next();
            Matcher mTemp = dataPatter.matcher(attNameTemp);
            if(!mTemp.matches()){
                continue;
            }
            data.put(mTemp.group(1), reader.getAttribute(attNameTemp));
        }
        return data;
    }
    
}
