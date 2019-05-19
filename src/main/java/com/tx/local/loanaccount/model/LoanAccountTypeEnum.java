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
public enum LoanAccountTypeEnum implements BasicDataEnum {
    
    CDD("CDD", "仓单贷", LoanAccountCategoryEnum.CREDIT_LOAN_ACCOUNT, "cdd"),
    
    DDD("DDD", "订单贷", LoanAccountCategoryEnum.CREDIT_LOAN_ACCOUNT, "ddd");
    
    /** 关键字 */
    private final String key;
    
    /** 名称 */
    private final String name;
    
    /** 贷款账户分类 */
    private final LoanAccountCategoryEnum category;
    
    /** 对应页面所在路径 */
    private final String path;
    
    /** <默认构造函数> */
    private LoanAccountTypeEnum(String key, String name, LoanAccountCategoryEnum category) {
        this.key = key;
        this.name = name;
        this.category = category;
        this.path = "";
    }
    
    /** <默认构造函数> */
    private LoanAccountTypeEnum(String key, String name, LoanAccountCategoryEnum category, String path) {
        this.key = key;
        this.name = name;
        this.category = category;
        this.path = path.toLowerCase();
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
     * @return 返回 category
     */
    public LoanAccountCategoryEnum getCategory() {
        return category;
    }
    
    /**
     * @return 返回 path
     */
    public String getPath() {
        return path;
    }
    
}
