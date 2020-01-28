package com.tx.local.loanaccount.event.trading;

import java.math.BigDecimal;
import java.util.List;

import com.tx.component.command.context.CommandRequest;
import com.tx.local.basicdata.model.FeeItemEnum;
import com.tx.local.loanaccount.event.LoanAccountTradingEvent;
import com.tx.local.loanaccount.handle.PaymentScheduleHandler;
import com.tx.local.loanaccount.model.LATradingRecord;
import com.tx.local.loanaccount.model.LoanAccount;
import com.tx.local.loanaccount.model.PaymentRecord;

/***
  * 客户还款事件<br/>
  * 
  * @author  lenovo
  * @version  [版本号, 2014年6月23日]
  * @see  [相关类/方法]
  * @since  [产品/模块版本]
 */
public class RepayEvent extends LoanAccountTradingEvent {
    
    /** 还款金额 */
    private final BigDecimal repayAmount;
    
    /** 还本金金额 */
    private final BigDecimal repayPrincipalAmount;
    
    /**
     * <默认构造函数>
     */
    public RepayEvent(BigDecimal repayAmount, LoanAccount loanAccount, LATradingRecord tradingRecord,
            PaymentScheduleHandler handler, List<PaymentRecord> paymentRecords, CommandRequest request) {
        super(loanAccount, tradingRecord, handler, request);
        
        this.repayAmount = repayAmount;
        this.repayPrincipalAmount = tradingRecord.getFeeItem2PaymentAmountMap().get(FeeItemEnum.DK_BJ) == null ? BigDecimal.ZERO
                : tradingRecord.getFeeItem2PaymentAmountMap().get(FeeItemEnum.DK_BJ);
        //        this.repayPrincipalAmountIrr = this.tradingRecord.getFeeItem2paymentAmountIrrMap().get(FeeItemEnum.BJ) == null
        //                ? BigDecimal.ZERO : this.tradingRecord.getFeeItem2paymentAmountIrrMap().get(FeeItemEnum.BJ);
    }
    
    /**
     * @return 返回 repayAmount
     */
    public BigDecimal getRepayAmount() {
        return repayAmount;
    }
    
    /**
     * @return 返回 repayPrincipalAmount
     */
    public BigDecimal getRepayPrincipalAmount() {
        return repayPrincipalAmount;
    }
}
