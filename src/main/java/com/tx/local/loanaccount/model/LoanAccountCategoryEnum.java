/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2014年4月10日
 * <修改描述:>
 */
package com.tx.local.loanaccount.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.tx.component.basicdata.model.BasicDataEnum;
import com.tx.component.basicdata.model.BasicDataEnumJsonSerializer;

/**
 * 贷款单账户类型<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2014年4月10日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@JsonSerialize(using = BasicDataEnumJsonSerializer.class)
public enum LoanAccountCategoryEnum implements BasicDataEnum {
    
    FINANCE_LEASE_ACCOUNT("FINANCE_LEASE_ACCOUNT", "融资租赁账户"),
    
    CREDIT_LOAN_ACCOUNT("CREDIT_LOAN_ACCOUNT", "信用贷款账户"),
    
    PAYABLE_ACCOUNT("PAYABLE_ACCOUNT", "应付账款账户");
    
    /** 关键字 */
    private final String key;
    
    /** 名称 */
    private final String name;
    
    /** 贷款账户分类 */
    private LoanAccountCategoryEnum(String key, String name) {
        this.key = key;
        this.name = name;
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
