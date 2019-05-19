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
public enum SettleIntentionTypeEnum implements BasicDataEnum {
    
    /** 提前结清 */
    ONCE_SETTLE("ONCE_SETTLE", "一次性结清", "once_settle_btn_auth"),
    
    /** 提前结清 */
    EARLY_SETTLE("EARLY_SETTLE", "提前结清", "early_settle_btn_auth");
    
    /** 编码 */
    private final String code;
    
    /** 名称 */
    private final String name;
    
    /** 交易类型 */
    private final LATradingRecordTypeEnum tradingRecordType;
    
    /** 权限代码 */
    private final String authKey;
    
    /** 对应的交易类型 */
    private SettleIntentionTypeEnum(String code, String name, String authKey) {
        this.code = code;
        this.name = name;
        this.authKey = authKey;
        this.tradingRecordType = null;
    }
    
    /** 对应的交易类型 */
    private SettleIntentionTypeEnum(String code, String name, LATradingRecordTypeEnum tradingRecordType,
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
