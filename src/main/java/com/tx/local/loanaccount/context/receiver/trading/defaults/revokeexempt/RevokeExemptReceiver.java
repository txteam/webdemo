/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2014年8月28日
 * <修改描述:>
 */
package com.tx.local.loanaccount.context.receiver.trading.defaults.revokeexempt;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.tx.component.command.context.CommandResponse;
import com.tx.local.loanaccount.context.request.trading.revokeexempt.RevokeExemptRequest;
import com.tx.local.loanaccount.handle.PaymentScheduleHandler;
import com.tx.local.loanaccount.model.LATradingRecord;
import com.tx.local.loanaccount.model.LoanAccount;

/**
 * 撤销还款豁免处理器<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2014年8月28日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Component("revokeExemptReceiver")
public class RevokeExemptReceiver extends
        AbstractRevokeExemptReceiver<RevokeExemptRequest> {
    
    /**
     * @param request
     * @param loanAccount
     * @param paymentScheduleMap
     */
    @Override
    public void validateRequest(RevokeExemptRequest request, CommandResponse response, LoanAccount loanAccount,
            PaymentScheduleHandler handler) {
        super.validateRequest(request, response, loanAccount, handler);
    }
    
    /**
     * @param request
     * @param loanAccount
     * @param paymentScheduleMap
     * @return
     */
    @Override
    public LATradingRecord createTradingRecord(RevokeExemptRequest request, CommandResponse response,
            LoanAccount loanAccount, PaymentScheduleHandler handler) {
        LATradingRecord revokedTradingRecord = getRevokedTradingRecord(request);
        
        Date now = new Date();
        LATradingRecord tradingRecord = new LATradingRecord();
        
        //账户交易是否到账
        tradingRecord.setReceived(revokedTradingRecord.isReceived());
        tradingRecord.setReceiveDate(now);
        
        //撤销提前结清计费交易可见，不可被撤销
        tradingRecord.setViewAble(true);
        tradingRecord.setRevokeAble(false);
        
        //设置交易的交易渠分组类型
        tradingRecord.setRelatedTradingRecordId(request.getRevokeTradingRecordId());//关联交易
        
        //设置交易的关联交易
        tradingRecord.setBankAccountId(revokedTradingRecord.getBankAccountId());
        tradingRecord.setSum(revokedTradingRecord.getSum().negate());
        tradingRecord.setRepaySum(revokedTradingRecord.getRepaySum().negate());
        
        tradingRecord.setExpireDate(revokedTradingRecord.getExpireDate());//到期日为空
        tradingRecord.setRepayDate(revokedTradingRecord.getRepayDate());//还款日期为贷款生效日
        
        tradingRecord.setRemark(request.getRemark());
        
        tradingRecord.setRevokeResean(request.getRevokeReason());
        tradingRecord.setRemark(request.getRemark());
        
        //FIXME: 委外分派记录id,是否委外还款
        //tradingRecord.setOutsourceAssignRecordId();
        //tradingRecord.setOutsourceRepay();
        
        return tradingRecord;
    }
    
    
}
