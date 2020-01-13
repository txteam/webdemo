/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2016年10月3日
 * <修改描述:>
 */
package com.tx.local.creditinfo.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.tx.component.basicdata.model.BasicDataEnum;
import com.tx.component.basicdata.model.BasicDataEnumJsonSerializer;

/**
 * 客户扩展信息版本类型<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2016年10月3日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@JsonSerialize(using = BasicDataEnumJsonSerializer.class)
public enum CreditInfoVersionTypeEnum implements BasicDataEnum {
    
    TRUNK("TRUNK", "主线", "CCI_TRUNK"),
    
    BRANCH("BRANCH", "分支", "CCI_BRANCH"),
    
    TAG("TAG", "标记", "CCI_TAG");
    
    private final String key;
    
    private final String name;
    
    private final String dynamicTableType;
    
    public static CreditInfoVersionTypeEnum nextVersionType(
            CreditInfoVersionTypeEnum versionType) {
        if (null == versionType) {
            versionType = TRUNK;
        }
        CreditInfoVersionTypeEnum nextVersionType = versionType;
        switch (versionType) {
            case TRUNK:
                nextVersionType = CreditInfoVersionTypeEnum.BRANCH;
                break;
            case BRANCH:
                nextVersionType = CreditInfoVersionTypeEnum.TAG;
                break;
            default:
                break;
        }
        return nextVersionType;
    }
    
    /** <默认构造函数> */
    private CreditInfoVersionTypeEnum(String key, String name,
            String dynamicTableType) {
        this.key = key;
        this.name = name;
        this.dynamicTableType = dynamicTableType;
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
     * @return 返回 dynamicTableType
     */
    public String getDynamicTableType() {
        return dynamicTableType;
    }
}
