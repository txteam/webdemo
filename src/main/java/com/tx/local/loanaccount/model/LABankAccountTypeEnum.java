/*
` * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2017年5月6日
 * <修改描述:>
 */
package com.tx.local.loanaccount.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.tx.component.basicdata.model.BasicDataEnum;
import com.tx.component.basicdata.model.BasicDataEnumJsonSerializer;

/**
 * 贷款账户的：交易账户类型<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2017年5月6日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@JsonSerialize(using = BasicDataEnumJsonSerializer.class)
public enum LABankAccountTypeEnum implements BasicDataEnum {
    
    /** 退款 */
    TK("TK", "退款"),
    
    /** 发放贷款 */
    FFDK("FFDK", "发放贷款"),
    
    /** 总行发放 */
    ZHFF("ZHFF", "总行发放"),
    
    /** 来往账 */
    LWZ("LWZ", "来往账"),
    
    /** 待入账 */
    DRZ("DRZ", "待入账"),
    
    /** 现金还款 */
    XJ_HK("XJ_HK", "现金还款", RepayIntentionTypeEnum.CASH_REPAY),
    
    /** 转账还款 */
    ZZ_HK("ZZ_HK", "自动转账还款", RepayIntentionTypeEnum.TRANSFER_REPAY),
    
    /** POS机还款 */
    POS_HK("POS_HK", "POS机还款", RepayIntentionTypeEnum.POS_REPAY);
    
    /** 关键字 */
    private final String code;
    
    /** 名称 */
    private final String name;
    
    /** 还款意愿类型 */
    private final RepayIntentionTypeEnum repayIntentionType;
    
    /** <默认构造函数> */
    private LABankAccountTypeEnum(String code, String name,
            RepayIntentionTypeEnum repayIntentionType) {
        this.code = code;
        this.name = name;
        this.repayIntentionType = repayIntentionType;
    }
    
    /** <默认构造函数> */
    private LABankAccountTypeEnum(String code, String name) {
        this.code = code;
        this.name = name;
        this.repayIntentionType = null;
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
    
    /**
     * @return 返回 repayIntentionType
     */
    public RepayIntentionTypeEnum getRepayIntentionType() {
        return repayIntentionType;
    }
    
}
