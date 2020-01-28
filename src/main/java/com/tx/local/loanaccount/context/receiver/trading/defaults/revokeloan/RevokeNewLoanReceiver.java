package com.tx.local.loanaccount.context.receiver.trading.defaults.revokeloan;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.tx.component.command.context.CommandResponse;
import com.tx.local.loanaccount.context.request.trading.revokeloan.RevokeNewLoanRequest;
import com.tx.local.loanaccount.handle.PaymentScheduleHandler;
import com.tx.local.loanaccount.model.ChargeRecord;
import com.tx.local.loanaccount.model.ExemptRecord;
import com.tx.local.loanaccount.model.LATradingRecord;
import com.tx.local.loanaccount.model.LoanAccount;
import com.tx.local.loanaccount.model.PaymentRecord;

/**
  * 新贷撤销
  *     新产生一条为新贷撤销的交易记录
  *     将还款计划所有应收清为0
  *     将原交易关联的paymentRecord冲出
  * <功能详细描述>
  * 
  * @author  Administrator
  * @version  [版本号, 2014年6月8日]
  * @see  [相关类/方法]
  * @since  [产品/模块版本]
 */
@Component("revokeNewLoanReceiver")
public class RevokeNewLoanReceiver extends AbstractRevokeLoanReceiver<RevokeNewLoanRequest> {
    
    /**
     * 创建交易记录<br/>
     * @param request
     * @param response
     * @param loanAccount
     * @param handler
     * @return
     */
    @Override
    public LATradingRecord createTradingRecord(RevokeNewLoanRequest request, CommandResponse response,
            LoanAccount loanAccount, PaymentScheduleHandler handler) {
        Date now = new Date();
        LATradingRecord sourceTradingRecord = getRevokedTradingRecord(request);
        
        LATradingRecord tradingRecord = new LATradingRecord();
        
        //账户交易是否到账
        tradingRecord.setReceived(true);
        tradingRecord.setReceiveDate(now);
        
        //设置是否可见，是否可撤销
        tradingRecord.setViewAble(true);
        tradingRecord.setRevokeAble(false);
        
        //设置交易的关联交易
        tradingRecord.setRelatedTradingRecordId(request.getRevokeTradingRecordId());//关联交易为其撤销的交易
        tradingRecord.setCurrentPeriod(sourceTradingRecord.getCurrentPeriod());
        
        //还款渠道，银行账户，交易金额，还款日期，到期日期，均使用原交易记录中信息
        tradingRecord.setBankAccountId(sourceTradingRecord.getBankAccountId());
        tradingRecord.setSum(sourceTradingRecord.getSum().negate());
        tradingRecord.setRepaySum(sourceTradingRecord.getRepaySum().negate());
        
        tradingRecord.setRepayDate(sourceTradingRecord.getRepayDate());
        tradingRecord.setExpireDate(sourceTradingRecord.getExpireDate());//到期日为当前期数到期日
        
        tradingRecord.setOutsourceAssignRecordId(sourceTradingRecord.getOutsourceAssignRecordId());
        tradingRecord.setOutsourceRepay(sourceTradingRecord.isOutsourceRepay());
        
        tradingRecord.setRevokeResean(request.getRevokeReason());
        tradingRecord.setRemark(request.getRemark());
        
        return tradingRecord;
    }
    
    /**
     * @param request
     * @param response
     * @param loanAccount
     * @param handler
     * @param paymentScheduleMap
     * @param tradingRecord
     * @param delayRecord
     * @param chargeRecords
     * @param paymentRecords
     */
    @Override
    public void postHandle(RevokeNewLoanRequest request, CommandResponse response, LoanAccount loanAccount,
            PaymentScheduleHandler paymentScheduleHandler, LATradingRecord tradingRecord,
            List<ChargeRecord> chargeRecords, List<ExemptRecord> exemptRecords, List<PaymentRecord> paymentRecords) {
        super.postHandle(request,
                response,
                loanAccount,
                paymentScheduleHandler,
                tradingRecord,
                chargeRecords,
                exemptRecords,
                paymentRecords);
        
        loanAccount.setRevoked(true);
        loanAccount.setRevokeDate(new Date());
        loanAccount.setRevokeReason(request.getRevokeReason());
    }
    
}
