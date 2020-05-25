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
 * 动态表业务类型枚举注册机<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2020年3月18日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class DynamicTableServiceTypeEnumRegistry implements InitializingBean {
    
    /** 错误编码映射 */
    private final Map<String, DynamicTableServiceTypeEnum> serviceTypeMap;
    
    /** <默认构造函数> */
    public DynamicTableServiceTypeEnumRegistry() {
        super();
        this.serviceTypeMap = new HashMap<>();
    }
    
    /**
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() {
        Set<Class<? extends DynamicTableServiceTypeEnum>> classSet = ClassScanUtils
                .scanByParentClass(DynamicTableServiceTypeEnum.class, "com.tx");
        for (Class<? extends DynamicTableServiceTypeEnum> classTemp : classSet) {
            if (!classTemp.isEnum()) {
                continue;
            }
            DynamicTableServiceTypeEnum[] types = classTemp.getEnumConstants();
            for (DynamicTableServiceTypeEnum typeTemp : types) {
                if (StringUtils.isEmpty(typeTemp)) {
                    return;
                }
                this.serviceTypeMap.put(typeTemp.getCode(), typeTemp);
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
    public DynamicTableServiceTypeEnum get(String serviceTypeCode) {
        if (StringUtils.isEmpty(serviceTypeCode)) {
            return null;
        }
        
        DynamicTableServiceTypeEnum type = this.serviceTypeMap
                .get(serviceTypeCode);
        return type;
    }
    
}
