/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2016年3月27日
 * <修改描述:>
 */
package com.tx.local.financeaccount.model;

/**
 * 财务台帐记录类型<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2016年3月27日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public enum AccountItemTypeEnum {
    
    INVEST_SUM("INVEST_SUM", "累计投资金额", "累计投资金额"),
    
    REPAY_SUM("REPAY_SUM", "累计还款金额", "累计还款金额"),
    
    INVESTING_SUM("INVESTING_SUM", "投资中金额", "投资中金额"),
    
    JDZF_RECHARGE_SUM("JDZF_CLIENT_RECHARGE_SUM", "京东支付累计充值金额", "京东支付累计充值金额"),
    
    JDZF_CASHOUT_SUM("JDZF_CASHOUT_SUM", "京东支付累计提现金额", "京东支付累计提现金额");
    
    /** 关键字 */
    private final String code;
    
    /** 名称 */
    private final String name;
    
    /** 备注 */
    private final String remark;
    
    /** <默认构造函数> */
    private AccountItemTypeEnum(String code, String name, String remark) {
        this.code = code;
        this.name = name;
        this.remark = remark;
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
     * @return 返回 remark
     */
    public String getRemark() {
        return remark;
    }
    
}
