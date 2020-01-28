/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2017年5月7日
 * <修改描述:>
 */
package com.tx.local.loanaccount.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.tx.component.basicdata.model.BasicDataEnum;
import com.tx.component.basicdata.model.BasicDataEnumJsonSerializer;

/**
  * 贷款账户与客户关联类型枚举<br/>
  * <功能详细描述>
  * 
  * @author  Administrator
  * @version  [版本号, 2017年5月7日]
  * @see  [相关类/方法]
  * @since  [产品/模块版本]
  */
@JsonSerialize(using = BasicDataEnumJsonSerializer.class)
public enum LAClientTypeEnum implements BasicDataEnum {
    
    ZD("ZD", "主贷人"),
    
    CD("CD", "次贷人"),
    
    DB("DB", "担保人");
    
    /** 关键字 */
    private final String code;
    
    /** 名称 */
    private final String name;
    
    /**  */
    private LAClientTypeEnum(String code, String name) {
        this.code = code;
        this.name = name;
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
}
