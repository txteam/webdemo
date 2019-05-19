/*
 * 描          述:  <描述>
 * 修  改   人:  lenovo
 * 修改时间:  2014年7月22日
 * <修改描述:>
 */
package com.tx.local.loanaccount.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.tx.component.basicdata.model.BasicDataEnum;
import com.tx.component.basicdata.model.BasicDataEnumJsonSerializer;

/**
 * 撤销分类<br/>
 * <功能详细描述>
 * 
 * @author  lenovo
 * @version  [版本号, 2014年7月22日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@JsonSerialize(using = BasicDataEnumJsonSerializer.class)
public enum RevokeCategoryEnum implements BasicDataEnum {
    
    /** 撤销交易 */
    REVOKE_TRADING("REVOKE_TRADING", "撤销交易"),
    
    /** 撤销还款交易 */
    REVOKE_REPAY_TRADING("REVOKE_REPAY_TRADING", "撤销还款交易");
    
    /** key值 */
    private final String key;
    
    /** name值 */
    private final String name;
    
    /** <默认构造函数> */
    private RevokeCategoryEnum(String key, String name) {
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
