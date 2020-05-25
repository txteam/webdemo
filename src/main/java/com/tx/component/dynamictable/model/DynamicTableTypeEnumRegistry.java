/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2020年3月18日
 * <修改描述:>
 */
package com.tx.component.dynamictable.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.StringUtils;

import com.tx.core.util.ClassScanUtils;

/**
 * 动态表类型枚举注册机<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2020年3月18日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class DynamicTableTypeEnumRegistry implements InitializingBean {
    
    /** 错误编码映射 */
    private final Map<String, DynamicTableTypeEnum> typeMap;
    
    /** <默认构造函数> */
    public DynamicTableTypeEnumRegistry() {
        super();
        this.typeMap = new HashMap<>();
    }
    
    /**
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() {
        Set<Class<? extends DynamicTableTypeEnum>> classSet = ClassScanUtils
                .scanByParentClass(DynamicTableTypeEnum.class, "com.tx");
        for (Class<? extends DynamicTableTypeEnum> classTemp : classSet) {
            if (!classTemp.isEnum()) {
                continue;
            }
            DynamicTableTypeEnum[] types = classTemp.getEnumConstants();
            for (DynamicTableTypeEnum typeTemp : types) {
                if (StringUtils.isEmpty(typeTemp)) {
                    return;
                }
                this.typeMap.put(typeTemp.getCode(), typeTemp);
            }
        }
    }
    
    /**
     * 根据错误编码获取对应的错误消息<br/>
     * <功能详细描述>
     * @param errorCode
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public DynamicTableTypeEnum get(String code) {
        if (StringUtils.isEmpty(code)) {
            return null;
        }
        
        DynamicTableTypeEnum type = this.typeMap.get(code);
        return type;
    }
    
}
