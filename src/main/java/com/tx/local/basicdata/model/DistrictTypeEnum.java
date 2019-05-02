/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2016年8月4日
 * <修改描述:>
 */
package com.tx.local.basicdata.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.tx.component.basicdata.model.BasicDataEnum;
import com.tx.component.basicdata.model.BasicDataEnumJsonSerializer;

/**
 * 地理区域类型<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2016年8月4日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@JsonSerialize(using = BasicDataEnumJsonSerializer.class)
public enum DistrictTypeEnum implements BasicDataEnum {
    
    VILLAGE("VILLAGE", "村", 4),
    
    TOWN("TOWN", "乡", 3),
    
    COUNTY("COUNTY", "县", 2),
    
    CITY("CITY", "市", 1),
    
    PROVINCE("PROVINCE", "省", 0);
    
    private final String key;
    
    private final String name;
    
    private final int level;
    
    /** <默认构造函数> */
    private DistrictTypeEnum(String key, String name, int level) {
        this.key = key;
        this.name = name;
        this.level = level;
    }
    
    /**
     * @return 返回 key
     */
    public String getKey() {
        return key;
    }
    
    /**
     * @return 返回 name
     */
    public String getName() {
        return name;
    }
    
    /**
     * @return 返回 level
     */
    public int getLevel() {
        return level;
    }
}
