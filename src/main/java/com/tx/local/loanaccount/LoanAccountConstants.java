/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2014年5月21日
 * <修改描述:>
 */
package com.tx.local.loanaccount;

import java.math.BigDecimal;

/**
 * 贷款账户长常量类<br/>
 *    贷款账户：
 *       账单分期业务
 *       信用贷款业务
 *       带呀贷款业务
 *       票据贴现业务
 * <功能详细描述>
 * 
 * @author Administrator
 * @version [版本号, 2014年5月21日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public interface LoanAccountConstants {
    
    String TASK_PARENT_CODE = "LOAN_ACCOUNT_TASKS";
    
    /** ----------------------- 视图类型 start ---------------- */
    String VIEW_TYPE_DETAIL = "detail";
    
    String VIEW_TYPE_EXEMPT = "exempt";
    
    String VIEW_TYPE_REPAY = "repay";
    
    String VIEW_TYPE_SETTLE = "settle";
    
    String VIEW_TYPE_REVOKE = "revoke";
    
    /** ----------------------- 视图类型      end ---------------- */
    
    BigDecimal ONE_CENT = new BigDecimal("0.01");
    
    int STOP_TAX_SETTLE_DAYS = 91;
    
    /** 贷款账户：期数[无期数] */
    String PERIOD_NA = "NA";
    
    //    /** 贷款账户：期数[注销后期数] */
    //    String PERIOD_WO = "WO";
    //    /** 贷款账户：期数[超额还款] */
    //    String PERIOD_OR = "OR";
    
    /** 贷款账户 */
    String RESPONSE_KEY_LOANACCOUNT = "response_loanAccount";
    
    /** 交易记录ID */
    String RESPONSE_KEY_TRADINGRECORD = "response_tradingRecord";
    
    /** 交易记录ID */
    String RESPONSE_KEY_TRADINGRECORDID = "response_tradingRecordId";
    
    /** 还款计划 */
    String RESPONSE_KEY_PAYMENTSCHEDULE = "response_paymentSchedule";
    
    /** 被撤销的交易id */
    String RESPONSE_KEY_PAYMENTRECORDS = "response_paymentRecords";
    
    /** 还款计划 */
    String RESPONSE_KEY_PAYMENTSCHEDULEHANDLE = "response_paymentScheduleHandle";
    
    /** 还款分配器 */
    String RESPONSE_KEY_REPAY_ALLOCATOR = "response_repayAllocator";
    
    /** 豁免分配器 */
    String RESPONSE_KEY_EXEMPT_ALLOCATOR = "response_exemptAllocator";
    
    /** 被撤销的交易id */
    String RESPONSE_KEY_REVOKED_TRADINGRECORD = "response_revokedTradingRecord";
    
    /** 被撤销的交易id */
    String RESPONSE_KEY_REVOKED_TRADINGRECORDID = "response_revokedTradingRecordId";
    
    //
    //  /** 交易记录ID */
    //  String RESPONSE_KEY_TRADINGRECORDS_LIST = "response_tradingRecords";
    //    
    //  /** 交易记录ID */
    //  String RESPONSE_KEY_PAYMENTRECORDLIST = "response_paymentRecordList";
    //
    //    /** 是否超额还款 */
    //    String IS_OVER_REPAY = "is_over_repay";
    //    
    //    /** 超额还款金额 */
    //    String OVER_REPAY_AMOUNT = "over_repay_amount";
    
    String SESSION_KEY_OVERREPAYRECORDS = "session_overRepayRecords";
}
