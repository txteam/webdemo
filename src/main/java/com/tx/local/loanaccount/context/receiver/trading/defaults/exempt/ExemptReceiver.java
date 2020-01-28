package com.tx.local.loanaccount.context.receiver.trading.defaults.exempt;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.tx.component.command.context.CommandResponse;
import com.tx.local.loanaccount.context.request.trading.exempt.ExemptRequest;
import com.tx.local.loanaccount.handle.PaymentScheduleHandler;
import com.tx.local.loanaccount.model.LATradingRecord;
import com.tx.local.loanaccount.model.LoanAccount;

/**
  * 现金还款<br/>
  * <功能详细描述>
  * 
  * @author  Administrator
  * @version  [版本号, 2014年6月7日]
  * @see  [相关类/方法]
  * @since  [产品/模块版本]
 */
@Component("exemptReceiver")
public class ExemptReceiver extends AbstractExemptReceiver<ExemptRequest> {
    
    /**
     * @param request
     * @param response
     * @param loanAccount
     * @param paymentScheduleHandler
     * @return
     */
    @Override
    public LATradingRecord createTradingRecord(ExemptRequest request, CommandResponse response, LoanAccount loanAccount,
            PaymentScheduleHandler paymentScheduleHandler) {
        Date now = new Date();
        LATradingRecord tradingRecord = new LATradingRecord();
        
        //账户交易是否到账
        tradingRecord.setReceived(true);
        tradingRecord.setReceiveDate(now);
        
        //设置是否可见，是否可撤销
        tradingRecord.setViewAble(true);
        tradingRecord.setRevokeAble(true);
        
        //设置交易的关联交易
        tradingRecord.setRelatedTradingRecordId(null);
        
        //银行存款金额、超额还款金额
        BigDecimal sum = request.getExemptIntention().getAmount();
        tradingRecord.setSum(sum.negate());
        tradingRecord.setRepaySum(BigDecimal.ZERO);
        
        tradingRecord.setExpireDate(loanAccount.getCurrentPeriodExpireDate());//到期日为空
        tradingRecord.setRepayDate(request.getExemptIntention().getRepayDate());//还款日期为贷款生效日
        
        tradingRecord.setRemark(request.getRemark());
        
        return tradingRecord;
    }
    
}
