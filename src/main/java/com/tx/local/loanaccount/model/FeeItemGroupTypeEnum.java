/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2014年6月12日
 * <修改描述:>
 */
package com.tx.local.loanaccount.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.tx.component.basicdata.model.BasicDataEnum;
import com.tx.component.basicdata.model.BasicDataEnumJsonSerializer;

/**
 * 还款金额类型<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2014年6月12日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@JsonSerialize(using = BasicDataEnumJsonSerializer.class)
public enum FeeItemGroupTypeEnum implements BasicDataEnum {
    /**
     * 还入每月应还：还入逾期依赖
     */
    MONTHLY_REPAY_SUM("MONTHLY_REPAY_SUM", "每月应还"),
    /**
     * 应还总额: 还入所有应还
     */
    RECEIVABLE_REPAY_SUM("RECEIVABLE_REPAY_SUM", "应还总金额");
    
    /** code */
    private final String code;
    
    /** 关键字 */
    private final String key;
    
    /** name */
    private final String name;
    
    private FeeItemGroupTypeEnum(String code, String name) {
        this.code = code;
        this.key = code;
        this.name = name;
    }
    
    /**
     * @return 返回 code
     */
    public String getCode() {
        return code;
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
}
