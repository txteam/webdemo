/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2014年8月10日
 * <修改描述:>
 */
package com.tx.local.loanaccount.event.trading;

import java.util.Date;

import com.tx.component.command.context.CommandRequest;
import com.tx.local.loanaccount.event.LoanAccountTradingEvent;
import com.tx.local.loanaccount.handle.PaymentScheduleHandler;
import com.tx.local.loanaccount.model.LATradingRecord;
import com.tx.local.loanaccount.model.LoanAccount;

/**
 * 逾期日期改变事件<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2014年8月10日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class OverdueDateChangeEvent extends LoanAccountTradingEvent {
    
    /** 源逾期日期 */
    private final Date sourceOverdueDate;
    
    /** 目标逾期日期 */
    private final Date targetOverdueDate;
    
    /** <默认构造函数> */
    public OverdueDateChangeEvent(LoanAccount loanAccount, LATradingRecord tradingRecord,
            PaymentScheduleHandler handler, Date sourceOverdueDate, Date targetOverdueDate,
            CommandRequest tradingRequest) {
        super(loanAccount, tradingRecord, handler, tradingRequest);
        this.sourceOverdueDate = sourceOverdueDate;
        this.targetOverdueDate = targetOverdueDate;
    }
    
    /**
     * @return 返回 sourceOverdueDate
     */
    public Date getSourceOverdueDate() {
        return sourceOverdueDate;
    }
    
    /**
     * @return 返回 targetOverdueDate
     */
    public Date getTargetOverdueDate() {
        return targetOverdueDate;
    }
}
