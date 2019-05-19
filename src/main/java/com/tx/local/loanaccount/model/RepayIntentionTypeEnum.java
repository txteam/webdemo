/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2014年7月2日
 * <修改描述:>
 */
package com.tx.local.loanaccount.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.tx.component.basicdata.model.BasicDataEnum;
import com.tx.component.basicdata.model.BasicDataEnumJsonSerializer;

/**
 * 还款模型类型<br/>
 * 
 * @author   Tim.peng
 * @version  [版本号, 2014年7月2日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@JsonSerialize(using = BasicDataEnumJsonSerializer.class)
public enum RepayIntentionTypeEnum implements BasicDataEnum {
    
    /** 待入账还款 */
    WA_REPAY("WA_REPAY", "待入账还款", LATradingRecordTypeEnum.WA_REPAY, "wa_repay_btn_auth"),
    
    /** pos机还款 */
    POS_REPAY("POS_REPAY", "POS机还款", LATradingRecordTypeEnum.POS_REPAY, "pos_repay_btn_auth"),
    
    /** 现金还款 */
    CASH_REPAY("CASH_REPAY", "现金还款", LATradingRecordTypeEnum.CASH_REPAY, "cash_repay_btn_auth"),
    
    /** 自动转账还款 */
    TRANSFER_REPAY("TRANSFER_REPAY", "自动转账还款", LATradingRecordTypeEnum.TRANSFER_REPAY, "transfer_repay_btn_auth"),
    
    /** 待入账还款 */
    WA_EARLY_REPAY("WA_EARLY_REPAY", "待入账提前还款", LATradingRecordTypeEnum.WA_EARLY_REPAY, "wa_early_repay_btn_auth"),
    
    /** pos机提前还款 */
    POS_EARLY_REPAY("POS_EARLY_REPAY", "POS机提前还款", LATradingRecordTypeEnum.POS_EARLY_REPAY, "pos_early_repay_btn_auth"),
    
    /** 现金提前还款 */
    CASH_EARLY_REPAY("CASH_EARLY_REPAY", "现金提前还款", LATradingRecordTypeEnum.CASH_EARLY_REPAY, "cash_early_repay_btn_auth"),
    
    /** 自动转账提前还款 */
    TRANSFER_EARLY_REPAY("TRANSFER_EARLY_REPAY", "自动转账提前还款", LATradingRecordTypeEnum.TRANSFER_EARLY_REPAY, "transfer_early_repay_btn_auth");
    
    /** 编码 */
    private final String code;
    
    /** 名称 */
    private final String name;
    
    /** 交易类型 */
    private final LATradingRecordTypeEnum tradingRecordType;
    
    /** 权限代码 */
    private final String authKey;
    
    /** 对应的交易类型 */
    private RepayIntentionTypeEnum(String code, String name, String authKey) {
        this.code = code;
        this.name = name;
        this.authKey = authKey;
        this.tradingRecordType = null;
    }
    
    /** 对应的交易类型 */
    private RepayIntentionTypeEnum(String code, String name, LATradingRecordTypeEnum tradingRecordType,
            String authKey) {
        this.code = code;
        this.name = name;
        this.authKey = authKey;
        this.tradingRecordType = tradingRecordType;
    }
    
    /** 编码 */
    public String getCode() {
        return code;
    }
    
    /** 名称 */
    public String getName() {
        return name;
    }
    
    /** 权限代码 */
    public String getAuthKey() {
        return authKey;
    }
    
    /**
     * @return 返回 tradingRecordType
     */
    public LATradingRecordTypeEnum getTradingRecordType() {
        return tradingRecordType;
    }
}
