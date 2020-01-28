/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2017年12月4日
 * <修改描述:>
 */
package com.tx.local.loanaccount.context.receiver.trading.defaults.settle;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.tx.component.command.context.CommandResponse;
import com.tx.local.loanaccount.context.request.trading.settle.SettleRequest;
import com.tx.local.loanaccount.handle.PaymentScheduleHandler;
import com.tx.local.loanaccount.model.LATradingRecord;
import com.tx.local.loanaccount.model.LoanAccount;

/**
  * 结清处理器<br/>
  * <功能详细描述>
  * 
  * @author  Administrator
  * @version  [版本号, 2017年12月4日]
  * @see  [相关类/方法]
  * @since  [产品/模块版本]
  */
@Component("settleReceiver")
public class SettleReceiver extends AbstractSettleReceiver<SettleRequest> {
    
    /**
     * @param request
     * @param response
     * @param loanAccount
     * @param paymentScheduleHandler
     * @return
     */
    @Override
    public LATradingRecord createTradingRecord(SettleRequest request,
            CommandResponse response, LoanAccount loanAccount,
            PaymentScheduleHandler handler) {
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
        tradingRecord.setBankAccountId(
                request.getSettleIntention().getBankAccountId());
        tradingRecord.setSum(request.getRepayAmount());
        tradingRecord.setRepaySum(request.getRepayAmount());
        
        tradingRecord.setExpireDate(loanAccount.getCurrentPeriodExpireDate());//到期日为空
        tradingRecord.setRepayDate(request.getSettleIntention().getRepayDate());//还款日期为贷款生效日
        
        //FIXME:是否外包期间还款
        //tradingRecord.setOutsourceAssignRecordId(request。 );
        //tradingRecord.setOutsourceRepay(outsourceRepay);
        
        tradingRecord.setRemark(request.getRemark());
        
        return tradingRecord;
    }
}
