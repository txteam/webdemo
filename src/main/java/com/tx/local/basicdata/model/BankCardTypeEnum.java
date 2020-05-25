/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2014年6月8日
 * <修改描述:>
 */
package com.tx.local.basicdata.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.tx.component.basicdata.model.BasicDataEnum;
import com.tx.component.basicdata.model.BasicDataEnumJsonSerializer;

/**
 * 银行卡类型<br/>
 *     银行卡
 *     存折
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2014年6月8日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@JsonSerialize(using = BasicDataEnumJsonSerializer.class)
public enum BankCardTypeEnum implements BasicDataEnum {
    
    /** 虚拟卡(非储蓄卡或信用卡) */
    VIRTUAL_CARD("VIRTUAL_CARD", "虚拟卡"),
    
    /** 信用卡(贷记卡和准贷记卡) */
    CREDIT_CARD("CREDIT_CARD", "信用卡"),
    
    /** 储蓄卡 */
    DEBIT_CARD("DEBIT_CARD", "储蓄卡");
    
    /** 银行卡类型编码 */
    private final String code;
    
    /** 银行卡名称 */
    private final String name;
    
    /** <默认构造函数> */
    private BankCardTypeEnum(String code, String name) {
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
