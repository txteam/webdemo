/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2017年4月25日
 * <修改描述:>
 */
package com.tx.local.basicdata.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.tx.component.basicdata.model.BasicDataEnum;
import com.tx.component.basicdata.model.BasicDataEnumJsonSerializer;

/**
  * 归属方枚举<br/>
  * <功能详细描述>
  * 
  * @author  Administrator
  * @version  [版本号, 2017年4月25日]
  * @see  [相关类/方法]
  * @since  [产品/模块版本]
  */
@JsonSerialize(using = BasicDataEnumJsonSerializer.class)
public enum FeeOwnershipEnum implements BasicDataEnum {
    
    QD("QD", "渠道"),
    
    PT("PT", "平台"),
    
    DK("DK", "贷款公司"),
    
    ZX("ZX", "咨询公司"),
    
    KH("KH", "客户");
    
    /** 费用项关键字 */
    private final String key;
    
    /** 费用项名称 */
    private final String name;
    
    /** <默认构造函数> */
    private FeeOwnershipEnum(String key, String name) {
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
