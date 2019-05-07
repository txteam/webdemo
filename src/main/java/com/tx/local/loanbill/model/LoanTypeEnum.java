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
    
    新贷("NEWLOANS", "新贷"),
    
    再贷("OPERATION", "再贷"),
    
    续贷("STOP", "续贷");
    
    private final String key;
    
    private final String name;
    
    private LoanTypeEnum(String key, String name) {
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
