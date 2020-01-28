/*
 * 描          述:  <描述>
 * 修  改   人:  huangdonggang
 * 修改时间:  2017年12月14日
 * <修改描述:>
 */
package com.tx.local.loanaccount.context.receiver.trading.defaults.suspend;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.tx.component.command.context.CommandResponse;
import com.tx.core.exceptions.util.AssertUtils;
import com.tx.local.loanaccount.context.receiver.trading.defaults.repay.AbstractRepayReceiver;
import com.tx.local.loanaccount.context.request.trading.suspend.SuspendRequest;
import com.tx.local.loanaccount.handle.PaymentScheduleHandler;
import com.tx.local.loanaccount.model.AccountStatusEnum;
import com.tx.local.loanaccount.model.ChargeRecord;
import com.tx.local.loanaccount.model.ExemptRecord;
import com.tx.local.loanaccount.model.LATradingRecord;
import com.tx.local.loanaccount.model.LoanAccount;
import com.tx.local.loanaccount.model.PaymentRecord;

/**
 * 暂缓处理器<br/>
 * 逾期或锁定
 * 
 * @author  huangdonggang
 * @version  [版本号, 2017年12月14日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Component("suspendReceiver")
public class SuspendReceiver extends AbstractRepayReceiver<SuspendRequest> {
    
    /**
     * @param request
     * @param response
     * @param loanAccount
     * @param handler
     */
    @Override
    public void validateRequest(SuspendRequest request,
            CommandResponse response, LoanAccount loanAccount,
            PaymentScheduleHandler handler) {
        BigDecimal sum = request.getRepayIntention().getAmount();
        AssertUtils.notNull(sum, "还款金额为空.");
        AssertUtils.isTrue(sum.compareTo(BigDecimal.ZERO) > 0, "还款金额应大于0.");
        
        //还款金额应当大于还款意愿中的金额分布
        request.getRepayIntention().validate();
        
        //贷后取消的账户不能进行还款
        AssertUtils.notTrue(
                AccountStatusEnum.ACCN.equals(loanAccount.getAccountStatus()),
                "loanAccount is ACCN.");
    }
    
    /**
     * 创建交易记录<br/>
     * @param request
     * @param response
     * @param loanAccount
     * @param handler
     * @return
     */
    @Override
    public LATradingRecord createTradingRecord(SuspendRequest request,
            CommandResponse response, LoanAccount loanAccount,
            PaymentScheduleHandler handler) {
        LATradingRecord tradingRecord = new LATradingRecord();
        
        //账户交易是否到账
        tradingRecord.setReceived(false);
        tradingRecord.setReceiveDate(null);
        
        //设置是否可见，是否可撤销
        tradingRecord.setViewAble(true);
        tradingRecord.setRevokeAble(true);
        
        //设置交易的关联交易
        tradingRecord.setRelatedTradingRecordId(null);
        
        //银行存款金额、超额还款金额
        tradingRecord.setBankAccountId(
                request.getRepayIntention().getBankAccountId());
        BigDecimal sum = request.getRepayIntention().getAmount();
        tradingRecord.setSum(sum);
        tradingRecord.setRepaySum(sum);
        
        tradingRecord.setExpireDate(loanAccount.getCurrentPeriodExpireDate());//到期日为空
        tradingRecord.setRepayDate(request.getRepayIntention().getRepayDate());//还款日期为贷款生效日
        
        tradingRecord.setRemark(request.getRemark());
        
        return tradingRecord;
    }
    
    /**
     * @param request
     * @param response
     * @param loanAccount
     * @param paymentScheduleHandler
     * @param tradingRecord
     * @param chargeRecords
     * @param exemptRecords
     * @param paymentRecords
     */
    @Override
    public void postHandle(SuspendRequest request, CommandResponse response,
            LoanAccount loanAccount,
            PaymentScheduleHandler paymentScheduleHandler,
            LATradingRecord tradingRecord, List<ChargeRecord> chargeRecords,
            List<ExemptRecord> exemptRecords,
            List<PaymentRecord> paymentRecords) {
        super.postHandle(request,
                response,
                loanAccount,
                paymentScheduleHandler,
                tradingRecord,
                chargeRecords,
                exemptRecords,
                paymentRecords);
        
        loanAccount.setLocked(true);
        loanAccount.setLastLockDate(new Date());
    }
    
}
