/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2017年5月16日
 * <修改描述:>
 */
package com.tx.local.loanaccount.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.tx.component.basicdata.model.BasicDataEnum;
import com.tx.component.basicdata.model.BasicDataEnumJsonSerializer;

/**
  * 扣款记录状态<br/>
  * <功能详细描述>
  * 
  * @author  Administrator
  * @version  [版本号, 2017年5月16日]
  * @see  [相关类/方法]
  * @since  [产品/模块版本]
  */
@JsonSerialize(using = BasicDataEnumJsonSerializer.class)
public enum DeductRecordStatusEnum implements BasicDataEnum {
    
    /** 自动转账还款 */
    WAIT_RECEIPT("WAIT_RECEIPT","待回执"),
    
    /** 自动转账设置 */
    PART_SUCCESS("PART_SUCCESS","部分成功"),
    
    /** 自动转账设置 */
    SUCCESS("SUCCESS","扣款成功"),
    
    /** 逾期30天试扣款 */
    FAIL("FAIL","扣款失败");
    
    /** key值 */
    private final String key;
    
    /** name值 */
    private final String name;
    
    /** <默认构造函数> */
    private DeductRecordStatusEnum(String key, String name) {
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
