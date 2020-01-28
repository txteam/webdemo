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
 * 退回类型
 * <功能详细描述>
 * 
 * @author  lenovo
 * @version  [版本号, 2014年7月22日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@JsonSerialize(using = BasicDataEnumJsonSerializer.class)
public enum RevokeTypeEnum implements BasicDataEnum {
    
    REVOKE("REVOKE", "撤销"),
    
    REFUND("REFUND", "退款"),
    
    REVOKE_TO_WA("REVOKE_TO_WA", "撤销至待入账");
    
    /** key值 */
    private final String code;
    
    /** name值 */
    private final String name;
    
    /** <默认构造函数> */
    private RevokeTypeEnum(String code, String name) {
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
