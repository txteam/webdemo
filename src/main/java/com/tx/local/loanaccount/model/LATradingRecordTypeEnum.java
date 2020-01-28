/*
 * 描          述:  <描述>
 * 修  改   人:  lenovo
 * 修改时间:  2014年5月28日
 * <修改描述:>
 */
package com.tx.local.loanaccount.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.tx.component.basicdata.model.BasicDataEnum;
import com.tx.component.basicdata.model.BasicDataEnumJsonSerializer;

/**
 * 交易类型，用以区分：<br/>
 *       用户发起的交易、撤销交易、自动交易<br/>
 *       在用户界面中，仅显示未撤销并且为用户发起的交易的交易记录<br/>
 * 
 * @author  lenovo
 * @version  [版本号, 2014年5月28日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@JsonSerialize(using = BasicDataEnumJsonSerializer.class)
public enum LATradingRecordTypeEnum implements BasicDataEnum {
    
    /* ---------------- 构建账户 ----------------- */
    /** 构建贷款单账户 */
    BUILD_LOAN_BILL_LOAN_ACCOUNT("BUILD_LOAN_BILL_LOAN_ACCOUNT", "构建贷款单账户", LATradingCategoryEnum.BUILD, ""),
    
    /* ---------------- 修改账户信息 ----------------- */
    /** 修改账户信息 */
    CHANGE_LOCK_STATUS("CHANGE_LOCK_STATUS", "修改账户锁状态", LATradingCategoryEnum.CHANGE, ""),
    
    /** 修改账户信息 */
    CHANGE_ACCOUNT_INFO("CHANGE_ACCOUNT_INFO", "修改账户信息", LATradingCategoryEnum.CHANGE, ""),
    
    /** 修改账户催收信息 */
    CHANGE_COLLECTION_INFO("CHANGE_COLLECTION_INFO", "修改账户催收信息", LATradingCategoryEnum.CHANGE, ""),
    
    /** 修改账户催收信息 */
    CHANGE_OVERDUE_INFO("CHANGE_OVERDUE_INFO", "修改账户逾期信息", LATradingCategoryEnum.CHANGE, ""),
    
    /** 修改账户结息信息 */
    CHANGE_SETTLE_INTEREST_INFO("CHANGE_SETTLE_INTEREST_INFO", "修改账户结息信息", LATradingCategoryEnum.CHANGE, ""),
    
    /** 修改账户催收信息 */
    CHANGE_OUT_SOURCE_INFO("CHANGE_OUT_SOURCE_INFO", "修改账户委外信息", LATradingCategoryEnum.CHANGE, ""),
    
    /* ----------------------------- 计费 -------------------------- */
    
    /** 放款手续费记收 */
    CHARGE_LOAN_FEE("CHARGE_LOAN_FEE", "放款手续费记收", LATradingCategoryEnum.CHARGE, "放款手续费记收"),
    
    /** 撤销记收逾期利息 */
    REVOKE_CHARGE_OVERDUE_INTEREST("REVOKE_CHARGE_OVERDUE_INTEREST", "撤销逾期利息计费", LATradingCategoryEnum.REVOKE_CHARGE, ""),
    
    /** 记收逾期利息 */
    CHARGE_OVERDUE_INTEREST("CHARGE_OVERDUE_INTEREST", "逾期利息计费", LATradingCategoryEnum.CHARGE, "", LATradingRecordTypeEnum.REVOKE_CHARGE_OVERDUE_INTEREST),
    
    /** 撤销扣款失败手续费 */
    REVOKE_CHARGE_DEDUCT_FAIL_FEE("REVOKE_CHARGE_DEDUCT_FAIL_FEE", "撤销扣款失败手续费计费", LATradingCategoryEnum.REVOKE_CHARGE, ""),
    
    /** 记收扣款失败手续费 */
    CHARGE_DEDUCT_FAIL_FEE("CHARGE_DEDUCT_FAIL_FEE", "扣款失败手续费计费", LATradingCategoryEnum.CHARGE, "", LATradingRecordTypeEnum.REVOKE_CHARGE_DEDUCT_FAIL_FEE),
    
    /* ---------------------------- 豁免 --------------------------- */
    
    /** 撤销费用豁免 */
    REVOKE_EXEMPT("REVOKE_EXEMPT_FEEITEM", "撤销豁免", LATradingCategoryEnum.REVOKE_EXEMPT, "撤销费用豁免"),
    
    /** 费用豁免 */
    EXEMPT("EXEMPT", "豁免", LATradingCategoryEnum.EXEMPT, "豁免", LATradingRecordTypeEnum.REVOKE_EXEMPT),
    
    /* ---------------- 发放贷款 ----------------- */
    
    /** 新贷退回 */
    REVOKE_NEW_LOAN("REVOKE_NEW_LOAN", "新贷退回", LATradingCategoryEnum.REVOKE_LOAN, "新贷退回"),
    
    /** 新贷 */
    NEW_LOAN("NEW_LOAN", "新贷", LATradingCategoryEnum.LOAN, "发放贷款", LATradingRecordTypeEnum.REVOKE_NEW_LOAN),
    
    /** 再贷退回 */
    REVOKE_AGAIN_LOAN("REVOKE_AGAIN_LOAN", "再贷退回", LATradingCategoryEnum.REVOKE_LOAN, "再贷退回"),
    
    /** 再贷 */
    AGAIN_LOAN("AGAIN_LOAN", "再贷", LATradingCategoryEnum.LOAN, "发放贷款", LATradingRecordTypeEnum.REVOKE_AGAIN_LOAN),
    
    /** 续贷退回 */
    REVOKE_RENEW_LOAN("REVOKE_RENEW_LOAN", "续贷退回", LATradingCategoryEnum.REVOKE_LOAN, "续贷退回"),
    
    /** 续贷 */
    RENEW_LOAN("RENEW_LOAN", "续贷", LATradingCategoryEnum.LOAN, "发放贷款", LATradingRecordTypeEnum.REVOKE_RENEW_LOAN),
    
    /* ---------------- 还款 ----------------- */
    
    /** 撤销待入账还款 */
    REVOKE_WA_REPAY("REVOKE_WA_REPAY", "撤销待入账还款", LATradingCategoryEnum.REVOKE_REPAY, "撤销待入账还款"),
    
    /** 现金还款 */
    WA_REPAY("WA_REPAY", "待入账还款", LATradingCategoryEnum.REPAY, "待入账还款", LATradingRecordTypeEnum.REVOKE_WA_REPAY),
    
    /** 撤销现金还款 */
    REVOKE_CASH_REPAY("REVOKE_CASH_REPAY", "撤销现金还款", LATradingCategoryEnum.REVOKE_REPAY, "撤销现金还款"),
    
    /** 现金还款 */
    CASH_REPAY("CASH_REPAY", "现金还款", LATradingCategoryEnum.REPAY, "现金还款", LATradingRecordTypeEnum.REVOKE_CASH_REPAY),
    
    /** 撤销自动转账还款 */
    REVOKE_TRANSFER_REPAY("REVOKE_TRANSFER_REPAY", "撤销自动转账还款", LATradingCategoryEnum.REVOKE_REPAY, "撤销自动转账还款"),
    
    /** 自动转账还款 */
    TRANSFER_REPAY("TRANSFER_REPAY", "自动转账还款", LATradingCategoryEnum.REPAY, "自动转账还款", LATradingRecordTypeEnum.REVOKE_TRANSFER_REPAY),
    
    /** 撤销POS机还款 */
    REVOKE_POS_REPAY("REVOKE_POS_REPAY", "撤销POS机还款", LATradingCategoryEnum.REVOKE_REPAY, "撤销POS机还款"),
    
    /** POS机还款 */
    POS_REPAY("POS_REPAY", "POS机还款", LATradingCategoryEnum.REPAY, "POS机还款", LATradingRecordTypeEnum.REVOKE_POS_REPAY),
    
    /** 撤销待入账提前还款 */
    REVOKE_WA_EARLY_REPAY("REVOKE_WA_EARLY_REPAY", "撤销待入账提前还款", LATradingCategoryEnum.REVOKE_REPAY, "撤销待入账提前还款"),
    
    /** 待入账提前还款 */
    WA_EARLY_REPAY("WA_EARLY_REPAY", "待入账提前还款", LATradingCategoryEnum.REPAY, "待入账提前还款", LATradingRecordTypeEnum.REVOKE_WA_EARLY_REPAY),
    
    /** 撤销现金提前还款 */
    REVOKE_EARLY_CASH_REPAY("REVOKE_EARLY_CASH_REPAY", "撤销现金提前还款", LATradingCategoryEnum.REVOKE_REPAY, "撤销现金提前还款"),
    
    /** 现金提前还款 */
    CASH_EARLY_REPAY("CASH_EARLY_REPAY", "现金提前还款", LATradingCategoryEnum.REPAY, "现金提前还款", LATradingRecordTypeEnum.REVOKE_EARLY_CASH_REPAY),
    
    /** 撤销自动转账提前还款 */
    REVOKE_TRANSFER_EARLY_REPAY("REVOKE_TRANSFER_EARLY_REPAY", "撤销自动转账提前还款", LATradingCategoryEnum.REVOKE_REPAY, "撤销自动转账提前还款"),
    
    /** 自动转账提前还款 */
    TRANSFER_EARLY_REPAY("TRANSFER_EARLY_REPAY", "自动转账提前还款", LATradingCategoryEnum.REPAY, "自动转账提前还款", LATradingRecordTypeEnum.REVOKE_TRANSFER_EARLY_REPAY),
    
    /** 撤销POS机提前还款 */
    REVOKE_POS_EARLY_REPAY("REVOKE_POS_EARLY_REPAY", "撤销POS机提前还款", LATradingCategoryEnum.REVOKE_REPAY, "撤销POS机提前还款"),
    
    /** POS机提前还款 */
    POS_EARLY_REPAY("POS_EARLY_REPAY", "POS机提前还款", LATradingCategoryEnum.REPAY, "POS机提前还款", LATradingRecordTypeEnum.REVOKE_POS_EARLY_REPAY),
    
    /** 撤销超额还款 */
    REVOKE_OVER_REPAY("REVOKE_OVER_REPAY", "撤销超额还款", LATradingCategoryEnum.REVOKE_REPAY, "撤销超额还款"),
    
    /** 超额还款 */
    OVER_REPAY("OVER_REPAY", "超额还款", LATradingCategoryEnum.REPAY, "超额还款", LATradingRecordTypeEnum.REVOKE_OVER_REPAY),
    
    /** 归还款项 */
    REFUND("REFUND", "归还款项", LATradingCategoryEnum.REVOKE_REPAY, "归还款项"),
    
    /** 撤销至待入账 */
    REVOKE_TO_WA("REVOKE_TO_WA", "撤销至待入账", LATradingCategoryEnum.REVOKE_REPAY, "撤销至待入账"),
    
    /** 撤销暂缓 */
    REVOKE_SUSPEND("REVOKE_SUSPEND", "撤销暂缓", LATradingCategoryEnum.REVOKE_SUSPEND, ""),
    
    /** 暂缓 */
    SUSPEND("SUSPEND", "暂缓", LATradingCategoryEnum.SUSPEND, "", LATradingRecordTypeEnum.REVOKE_SUSPEND),
    
    /** 暂缓 */
    SUSPEND_RECEIVED("SUSPEND_RECEIVED", "暂缓到帐", LATradingCategoryEnum.SUSPEND_RECEIVED, "暂缓到帐"),
    
    /** 撤销自动转账还款 */
    REVOKE_SUSPEND_TRANSFER_REPAY("REVOKE_SUSPEND_TRANSFER_REPAY", "撤销暂缓自动转账还款", LATradingCategoryEnum.REVOKE_SUSPEND, "撤销自动转账还款"),
    
    /** 自动转账还款 */
    SUSPEND_TRANSFER_REPAY("SUSPEND_TRANSFER_REPAY", "暂缓自动转账还款", LATradingCategoryEnum.SUSPEND, "自动转账还款", LATradingRecordTypeEnum.REVOKE_SUSPEND_TRANSFER_REPAY),
    
    /** 自动转账还款 */
    SUSPEND_TRANSFER_REPAY_RECEIVED("SUSPEND_TRANSFER_REPAY", "暂缓自动转账还款到帐", LATradingCategoryEnum.SUSPEND_RECEIVED, "自动转账还款"),
    
    /* --------------- 到账 ------------------ */
    //    
    //    超额还款到帐,
    //    
    //    自动转账还款到帐,
    //    
    //    暂缓到帐,
    //    
    /* ---------------- 结清 ----------------- */
    
    /** 撤销续贷结清 */
    REVOKE_RENEW_SETTLE("REVOKE_RENEW_SETTLE", "撤销续贷结清", LATradingCategoryEnum.REVOKE_REPAY, "撤销续贷结清"),
    
    /** 续贷结清 */
    RENEW_SETTLE("RENEW_SETTLE", "续贷结清", LATradingCategoryEnum.REPAY, "续贷结清", LATradingRecordTypeEnum.REVOKE_RENEW_SETTLE),
    
    /** 撤销提前结清 */
    REVOKE_EARLY_SETTLE("REVOKE_EARLY_SETTLE", "撤销提前结清", LATradingCategoryEnum.REVOKE_REPAY, "撤销提前结清"),
    
    /** 提前结清 */
    EARLY_SETTLE("EARLY_SETTLE", "提前结清", LATradingCategoryEnum.REPAY, "提前结清", LATradingRecordTypeEnum.REVOKE_EARLY_SETTLE),
    
    /** 撤销一次性结清 */
    REVOKE_ONCE_SETTLE("REVOKE_EARLY_SETTLE", "撤销一次性结清", LATradingCategoryEnum.REVOKE_REPAY, "撤销一次性结清"),
    
    /** 一次性结清 */
    ONCE_SETTLE("ONCE_SETTLE", "一次性结清", LATradingCategoryEnum.REPAY, "一次性结清", LATradingRecordTypeEnum.REVOKE_ONCE_SETTLE),
    
    /* ---------------- 延期 ----------------- */
    //    
    //    撤销贷后展期,
    //    
    //    贷后展期(撤销贷后展期)
    ;
    
    /** 关键字 */
    private final String key;
    
    /** 交易类型 */
    private final String name;
    
    /** 交易分类 */
    @JsonIgnore
    private final LATradingCategoryEnum tradingCategory;
    
    /** 摘要 */
    @JsonIgnore
    private final String summary;
    
    /** 对应的(请求-撤销 或者 撤销-请求)类型 */
    @JsonIgnore
    private final LATradingRecordTypeEnum revokeTradingRecordType;
    
    /** <默认构造函数> */
    private LATradingRecordTypeEnum(String key, String name, LATradingCategoryEnum tradingCategory, String summary,
            LATradingRecordTypeEnum revokeTradingRecordType) {
        this.key = key;
        this.name = name;
        this.tradingCategory = tradingCategory;
        this.summary = summary;
        this.revokeTradingRecordType = revokeTradingRecordType;
    }
    
    /** <默认构造函数> */
    private LATradingRecordTypeEnum(String key, String name, LATradingCategoryEnum tradingCategory, String summary) {
        this.key = key;
        this.name = name;
        this.tradingCategory = tradingCategory;
        this.summary = summary;
        this.revokeTradingRecordType = null;
    }
    
    /**
     * @return 返回 revokeTradingRecordType
     */
    public LATradingRecordTypeEnum getRevokeTradingRecordType() {
        return revokeTradingRecordType;
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
     * @return 返回 tradingCategory
     */
    public LATradingCategoryEnum getTradingCategory() {
        return tradingCategory;
    }
    
    /**
     * @return 返回 summary
     */
    public String getSummary() {
        return summary;
    }
}
