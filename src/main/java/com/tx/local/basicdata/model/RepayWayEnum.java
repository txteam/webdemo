/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2017年4月29日
 * <修改描述:>
 */
package com.tx.local.basicdata.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.tx.component.basicdata.model.BasicDataEnum;
import com.tx.component.basicdata.model.BasicDataEnumJsonSerializer;

/**
  * 还款方式<br/>
  * <功能详细描述>
  * 
  * @author  Administrator
  * @version  [版本号, 2017年4月29日]
  * @see  [相关类/方法]
  * @since  [产品/模块版本]
  */
@JsonSerialize(using = BasicDataEnumJsonSerializer.class)
public enum RepayWayEnum implements BasicDataEnum{
    
    DEBX("DEBX", "等额本息"),
    
    DEBJ("DEBJ", "等额本金"),
    
    DBDX("DBDX", "等本等息"),
    
    DEBJ_ARJX("DEBJ_ARJX","等额本金按日计息"),
    
    AYFXDQHB("AYFXDQHB", "按月付息到期还本"),
    
    DQHBFX("DQHBFX", "到期还本付息");
    
    /** key值 */
    private final String key;
    
    /** name值 */
    private final String name;
    
    /** <默认构造函数> */
    private RepayWayEnum(String key, String name) {
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
