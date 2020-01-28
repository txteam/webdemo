/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2014年8月28日
 * <修改描述:>
 */
package com.tx.local.loanaccount.context.receiver.trading.defaults.revokeexempt;

import java.util.List;

import com.tx.component.command.context.CommandResponse;
import com.tx.core.exceptions.util.AssertUtils;
import com.tx.core.util.MessageUtils;
import com.tx.local.loanaccount.context.receiver.trading.defaults.AbstractRevokeReceiver;
import com.tx.local.loanaccount.context.request.trading.revokeexempt.AbstractRevokeExemptRequest;
import com.tx.local.loanaccount.handle.PaymentScheduleHandler;
import com.tx.local.loanaccount.model.ChargeRecord;
import com.tx.local.loanaccount.model.ExemptRecord;
import com.tx.local.loanaccount.model.LATradingRecord;
import com.tx.local.loanaccount.model.LoanAccount;
import com.tx.local.loanaccount.model.PaymentRecord;

/**
 * 撤销还款豁免处理器<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2014年8月28日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public abstract class AbstractRevokeExemptReceiver<PR extends AbstractRevokeExemptRequest>
        extends AbstractRevokeReceiver<PR> {
    
    /**
     * @param request
     * @param loanAccount
     * @param paymentScheduleMap
     */
    @Override
    public void validateRequest(PR request, CommandResponse response, LoanAccount loanAccount,
            PaymentScheduleHandler handler) {
        switch (loanAccount.getAccountStatus()) {
            case AC:
            case SL:
            case RSL:
            case ESL:
                break;
            case ES:
            case RS:
            case FP:
            case ACCN:
            default:
                throw new IllegalArgumentException(
                        MessageUtils.format("贷款账户状态为'{}',不能进行豁免操作.", new Object[] { loanAccount.getAccountStatus() }));
        }
        
        //撤销的交易记录
        LATradingRecord laTradingRecord = getRevokedTradingRecord(request);
        AssertUtils.notNull(laTradingRecord, "laTradingRecord is null.");
        AssertUtils.isTrue(laTradingRecord.isRevokeAble(), "laTradingRecord.revokeAble is false.");
        AssertUtils.isTrue(!laTradingRecord.isRevoked(), "laTradingRecord.revoked is true.");
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
    public void postHandle(PR request, CommandResponse response, LoanAccount loanAccount,
            PaymentScheduleHandler handler, LATradingRecord tradingRecord, List<ChargeRecord> chargeRecords,
            List<ExemptRecord> exemptRecords, List<PaymentRecord> paymentRecords) {
        super.postHandle(request,
                response,
                loanAccount,
                handler,
                tradingRecord,
                chargeRecords,
                exemptRecords,
                paymentRecords);
    }
    
}
