/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2016年1月16日
 * <修改描述:>
 */
package com.tx.local.basicdata.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.tx.component.basicdata.model.BasicDataEnum;
import com.tx.component.basicdata.model.BasicDataEnumJsonSerializer;

/**
 * 支付渠道枚举<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2016年1月16日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@JsonSerialize(using = BasicDataEnumJsonSerializer.class)
public enum PaymentChannelEnum implements BasicDataEnum {
    
    BOCEST("BOCEST", "中国银行E商通", BankEnum.BOC, false),
    
    CMB("CMB", "招商银行银企直连", BankEnum.CMB, false),
    
    CNCB("CNCB", "中信银行银企直连", BankEnum.CNCB, false);
    
    /** key值 */
    private final String key;
    
    /** 名称 */
    private final String name;
    
    /** bank */
    private final BankEnum bank;
    
    /** 是否支持自动构建对应的子账户 */
    private final boolean autoBuild;
    
    /** <默认构造函数> */
    private PaymentChannelEnum(String key, String name, BankEnum bank,
            boolean autoBuild) {
        this.key = key;
        this.name = name;
        this.bank = bank;
        this.autoBuild = autoBuild;
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
     * @return 返回 bank
     */
    public BankEnum getBank() {
        return bank;
    }
    
    /**
     * @return 返回 autoBuild
     */
    public boolean isAutoBuild() {
        return autoBuild;
    }
    
}
