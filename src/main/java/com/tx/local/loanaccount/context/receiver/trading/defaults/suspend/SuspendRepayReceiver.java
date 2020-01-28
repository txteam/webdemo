package com.tx.local.loanaccount.context.receiver.trading.defaults.suspend;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.tx.component.command.context.CommandContext;
import com.tx.component.command.context.CommandResponse;
import com.tx.core.exceptions.util.AssertUtils;
import com.tx.core.util.MessageUtils;
import com.tx.local.loanaccount.context.receiver.trading.defaults.repay.AbstractRepayReceiver;
import com.tx.local.loanaccount.context.request.trading.repay.OverRepayRequest;
import com.tx.local.loanaccount.context.request.trading.suspend.SuspendRepayRequest;
import com.tx.local.loanaccount.handle.PaymentScheduleHandler;
import com.tx.local.loanaccount.helper.repayallocator.RepayAllocator;
import com.tx.local.loanaccount.model.AccountStatusEnum;
import com.tx.local.loanaccount.model.ChargeRecord;
import com.tx.local.loanaccount.model.ExemptRecord;
import com.tx.local.loanaccount.model.LATradingRecord;
import com.tx.local.loanaccount.model.LoanAccount;
import com.tx.local.loanaccount.model.PaymentRecord;

/**
 * 未到账自动转账还款<br/>
 * 账户未逾期并且未锁定
 * 
 * @author  huangdonggang
 * @version  [版本号, 2017年12月13日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Component("suspendRepayReceiver")
public class SuspendRepayReceiver
        extends AbstractRepayReceiver<SuspendRepayRequest> {
    
    /**
     * @param request
     * @param response
     * @return
     */
    @Override
    public boolean preHandle(SuspendRepayRequest request,
            CommandResponse response) {
        String loanAccountId = request.getLoanAccountId();
        AssertUtils.notEmpty(loanAccountId, "loanAccountId is empty.");
        LoanAccount loanAccount = request.getLoanAccount();
        AssertUtils.notNull(loanAccount, "loanAccount is null.");
        
        switch (loanAccount.getAccountStatus()) {
            case ES:
            case RS:
            case FP:
                //如果账户已经结清，将未到账的交易转换为暂缓，已到账的交易转换为超额还款
                //如果已经到账的金额，发起超额还款
                OverRepayRequest overRepayRequest = new OverRepayRequest(
                        loanAccountId, request.getRepayIntention(), false,
                        request.getSourceType());
                CommandContext.getContext().post(overRepayRequest, response);
                return false;
            case AC:
            case SL:
            case RSL:
            case ESL:
                break;
            case ACCN:
            default:
                throw new IllegalArgumentException(MessageUtils.format(
                        "错误的贷款账户状态:{}",
                        new Object[] { loanAccount.getAccountStatus() }));
        }
        return true;
    }
    
    /**
     * 验证请求合法性<br/>
     * @param request
     * @param response
     * @param loanAccount
     * @param paymentScheduleHandler
     */
    @Override
    public void validateRequest(SuspendRepayRequest request,
            CommandResponse response, LoanAccount loanAccount,
            PaymentScheduleHandler paymentScheduleHandler) {
        BigDecimal sum = request.getRepayIntention().getAmount();
        AssertUtils.notNull(sum, "还款金额为空.");
        AssertUtils.isTrue(sum.compareTo(BigDecimal.ZERO) > 0, "还款金额应大于0.");
        
        //还款金额应当大于还款意愿中的金额分布
        request.getRepayIntention().validate();
        
        //贷后取消的账户不能进行还款
        AssertUtils.notTrue(
                AccountStatusEnum.ACCN.equals(loanAccount.getAccountStatus()),
                "loanAccount is ACCN.");
        //判断贷款单账号是否锁定,被锁定的贷款账户不能进行现金还款，也不应当能够生成自动转账还款
        AssertUtils.notTrue(loanAccount.isLocked(), "loanAccount is locked.");
        AssertUtils.notTrue(loanAccount.isOverdue(), "loanAccount is overdue.");
    }
    
    /**
     *  创建交易记录<br/>
      *<功能详细描述>
      * @param request
      * @param response
      * @param loanAccount
      * @param handler
      * @return [参数说明]
      * 
      * @return LATradingRecord [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    @Override
    public LATradingRecord createTradingRecord(SuspendRepayRequest request,
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
        
        //获取还款分配器
        RepayAllocator repayAllocator = getRepayAllocator(loanAccount,
                handler,
                request.getRepayIntention(),
                response);
        
        //银行存款金额、超额还款金额
        tradingRecord.setBankAccountId(
                request.getRepayIntention().getBankAccountId());
        tradingRecord.setSum(repayAllocator.getAmount()
                .subtract(repayAllocator.getOverRepayAmount()));
        tradingRecord.setRepaySum(repayAllocator.getAmount()
                .subtract(repayAllocator.getOverRepayAmount()));
        
        tradingRecord.setExpireDate(loanAccount.getCurrentPeriodExpireDate());//到期日为空
        tradingRecord.setRepayDate(request.getRepayIntention().getRepayDate());//还款日期为贷款生效日
        
        //FIXME:是否外包期间还款
        //tradingRecord.setOutsourceAssignRecordId(request。 );
        //tradingRecord.setOutsourceRepay(outsourceRepay);
        
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
    public void postHandle(SuspendRepayRequest request,
            CommandResponse response, LoanAccount loanAccount,
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
