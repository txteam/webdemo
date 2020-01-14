/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2016年10月3日
 * <修改描述:>
 */
package com.tx.local.creditinfo.context;

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
    
    TRUNK("TRUNK", "主线", "CI_"),
    
    BRANCH("BRANCH", "分支", "CI_BR_"),
    
    TAG("TAG", "标签", "CI_TAG_");
    
    /** 版本编码 */
    private final String code;
    
    /** 版本名称 */
    private final String name;
    
    /** 表前缀 */
    private final String prefix;
    
    /** <默认构造函数> */
    private CreditInfoVersionTypeEnum(String code, String name, String prefix) {
        this.code = code;
        this.name = name;
        this.prefix = prefix;
    }
    
    /**
     * @return 返回 code
     */
    public String getCode() {
        return code;
    }
    
    /**
     * @return 返回 name
     */
    public String getName() {
        return name;
    }
    
    /**
     * @return 返回 prefix
     */
    public String getPrefix() {
        return prefix;
    }
}
