/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2014年5月15日
 * <修改描述:>
 */
package com.tx.local.loanbill.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.tx.component.basicdata.model.BasicDataEnum;
import com.tx.component.basicdata.model.BasicDataEnumJsonSerializer;

/**
 * 贷款类别<br/>
 * 
 * @author  Bobby
 * @version  [版本号, 2014年5月23日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@JsonSerialize(using = BasicDataEnumJsonSerializer.class)
public enum LoanTypeEnum implements BasicDataEnum {
    
    NEW_LOAN("NEW_LOAN", "新贷"),
    
    ONCE_AGAIN_LOAN("ONCE_AGAIN_LOAN", "再贷"),
    
    RENEW_LOAN("RENEW_LOAN", "续贷");
    
    /** 编码 */
    private final String code;
    
    /** 名称 */
    private final String name;
    
    /** <默认构造函数> */
    private LoanTypeEnum(String code, String name) {
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
