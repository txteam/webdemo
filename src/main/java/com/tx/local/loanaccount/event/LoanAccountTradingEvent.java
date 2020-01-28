/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2014年4月26日
 * <修改描述:>
 */
package com.tx.local.loanaccount.event;

import com.tx.component.command.context.CommandRequest;
import com.tx.component.event.event.impl.EventImpl;
import com.tx.core.exceptions.util.AssertUtils;
import com.tx.local.loanaccount.handle.PaymentScheduleHandler;
import com.tx.local.loanaccount.model.LATradingRecord;
import com.tx.local.loanaccount.model.LoanAccount;

/**
 * 贷款单账户操作事件基类<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2014年4月26日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public abstract class LoanAccountTradingEvent extends EventImpl {
    
    /** 贷款账户 */
    private final LoanAccount loanAccount;
    
    /** 交易记录 */
    private final LATradingRecord tradingRecord;
    
    /** 还款计划映射 */
    private final PaymentScheduleHandler paymentScheduleHandler;
    
    /** 操作请求 */
    private final CommandRequest request;
    
    /** <默认构造函数> */
    public LoanAccountTradingEvent(LoanAccount loanAccount, LATradingRecord tradingRecord,
            PaymentScheduleHandler paymentScheduleHandler, CommandRequest processRequest) {
        super();
        AssertUtils.notNull(loanAccount, "loanAccount is null.");
        AssertUtils.notNull(tradingRecord, "tradingRecord is null.");
        
        this.loanAccount = loanAccount;
        this.tradingRecord = tradingRecord;
        this.paymentScheduleHandler = paymentScheduleHandler;
        this.request = processRequest;
    }
    
    /**
     * @return 返回 loanAccount
     */
    public LoanAccount getLoanAccount() {
        return loanAccount;
    }
    
    /**
     * @return 返回 tradingRecord
     */
    public LATradingRecord getTradingRecord() {
        return tradingRecord;
    }
    
    /**
     * @return 返回 paymentScheduleHandler
     */
    public PaymentScheduleHandler getPaymentScheduleHandler() {
        return paymentScheduleHandler;
    }
    
    /**
     * @return 返回 processRequest
     */
    public CommandRequest getRequest() {
        return request;
    }
}
