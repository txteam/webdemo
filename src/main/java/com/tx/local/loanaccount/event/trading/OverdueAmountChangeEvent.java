/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2014年8月10日
 * <修改描述:>
 */
package com.tx.local.loanaccount.event.trading;

import java.math.BigDecimal;

import com.tx.component.command.context.CommandRequest;
import com.tx.local.loanaccount.event.LoanAccountTradingEvent;
import com.tx.local.loanaccount.handle.PaymentScheduleHandler;
import com.tx.local.loanaccount.model.LATradingRecord;
import com.tx.local.loanaccount.model.LoanAccount;

/**
 * 逾期金额改变事件<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2014年8月10日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class OverdueAmountChangeEvent extends LoanAccountTradingEvent {
    
    /** 源逾期日期 */
    private final BigDecimal sourceOverdueAmount;
    
    /** 目标逾期日期 */
    private final BigDecimal targetOverdueAmount;
    
    /** <默认构造函数> */
    public OverdueAmountChangeEvent(LoanAccount loanAccount, LATradingRecord tradingRecord,
            PaymentScheduleHandler handler, BigDecimal sourceOverdueAmount, BigDecimal targetOverdueAmount,
            CommandRequest tradingRequest) {
        super(loanAccount, tradingRecord, handler, tradingRequest);
        this.sourceOverdueAmount = sourceOverdueAmount;
        this.targetOverdueAmount = targetOverdueAmount;
    }
    
    /**
     * @return 返回 sourceOverdueAmount
     */
    public BigDecimal getSourceOverdueAmount() {
        return sourceOverdueAmount;
    }
    
    /**
     * @return 返回 targetOverdueAmount
     */
    public BigDecimal getTargetOverdueAmount() {
        return targetOverdueAmount;
    }
}
