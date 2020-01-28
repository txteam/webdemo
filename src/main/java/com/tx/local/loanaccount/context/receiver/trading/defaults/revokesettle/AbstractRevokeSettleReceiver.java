/*
 * 描          述:  <描述>
 * 修  改   人:  lenovo
 * 修改时间:  2014年7月22日
 * <修改描述:>
 */
package com.tx.local.loanaccount.context.receiver.trading.defaults.revokesettle;

import java.util.List;

import com.tx.component.command.context.CommandResponse;
import com.tx.component.event.context.EventContext;
import com.tx.core.exceptions.util.AssertUtils;
import com.tx.local.loanaccount.context.receiver.trading.defaults.AbstractRevokeReceiver;
import com.tx.local.loanaccount.context.request.trading.revokesettle.AbstractRevokeSettleRequest;
import com.tx.local.loanaccount.event.trading.RepayEvent;
import com.tx.local.loanaccount.handle.PaymentScheduleHandler;
import com.tx.local.loanaccount.model.AccountStatusEnum;
import com.tx.local.loanaccount.model.ChargeRecord;
import com.tx.local.loanaccount.model.ExemptRecord;
import com.tx.local.loanaccount.model.LATradingRecord;
import com.tx.local.loanaccount.model.LoanAccount;
import com.tx.local.loanaccount.model.PaymentRecord;

/**
 * 基础撤销还款基类<br/>
 * <功能详细描述>
 * 
 * @author  lenovo
 * @version  [版本号, 2014年7月22日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public abstract class AbstractRevokeSettleReceiver<PR extends AbstractRevokeSettleRequest>
        extends AbstractRevokeReceiver<PR> {
    
    /**
     * @param request
     * @param response
     * @param loanAccount
     * @param paymentScheduleHandler
     */
    @Override
    public void validateRequest(PR request, CommandResponse response, LoanAccount loanAccount,
            PaymentScheduleHandler paymentScheduleHandler) {
        AssertUtils.notNull(request, "request is null.");
        AssertUtils.notNull(response, "response is null.");
        AssertUtils.notEmpty(request.getLoanAccountId(), "request.loanAccountId is empty.");
        
        //贷款账户状态为AC
        AssertUtils.isTrue(!AccountStatusEnum.ACCN.equals(loanAccount.getAccountStatus()), "账户状态不为ACCN.");
        
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
    
    /**
     * @param request
     * @param response
     * @param loanAccount
     * @param handler
     * @param tradingRecord
     * @param chargeRecords
     * @param exemptRecords
     * @param paymentRecords
     */
    @Override
    public void afterHandle(PR request, CommandResponse response, LoanAccount loanAccount,
            PaymentScheduleHandler handler, LATradingRecord tradingRecord, List<ChargeRecord> chargeRecords,
            List<ExemptRecord> exemptRecords, List<PaymentRecord> paymentRecords) {
        super.afterHandle(request,
                response,
                loanAccount,
                handler,
                tradingRecord,
                chargeRecords,
                exemptRecords,
                paymentRecords);
        
        //发送还款事件，便于催收监听后修改对应的还款数据
        RepayEvent revokeRepayEvent = new RepayEvent(tradingRecord.getSum(), loanAccount, tradingRecord, handler,
                paymentRecords, request);
        EventContext.getContext().trigger(revokeRepayEvent);
    }
}
