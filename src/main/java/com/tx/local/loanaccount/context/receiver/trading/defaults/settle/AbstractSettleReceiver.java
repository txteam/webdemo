/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2017年6月25日
 * <修改描述:>
 */
package com.tx.local.loanaccount.context.receiver.trading.defaults.settle;

import java.math.BigDecimal;
import java.util.List;

import com.tx.component.command.context.CommandContext;
import com.tx.component.command.context.CommandResponse;
import com.tx.component.event.context.EventContext;
import com.tx.core.exceptions.util.AssertUtils;
import com.tx.core.util.MessageUtils;
import com.tx.local.loanaccount.LoanAccountConstants;
import com.tx.local.loanaccount.context.receiver.trading.defaults.AbstractDefaultTradingReceiver;
import com.tx.local.loanaccount.context.request.trading.repay.OverRepayRequest;
import com.tx.local.loanaccount.context.request.trading.settle.AbstractSettleRequest;
import com.tx.local.loanaccount.event.trading.RepayEvent;
import com.tx.local.loanaccount.handle.PaymentScheduleHandler;
import com.tx.local.loanaccount.helper.exemptallocator.ExemptAllocator;
import com.tx.local.loanaccount.helper.repay.RepayIntentionHelper;
import com.tx.local.loanaccount.helper.repayallocator.RepayAllocator;
import com.tx.local.loanaccount.model.AccountStatusEnum;
import com.tx.local.loanaccount.model.ChargeRecord;
import com.tx.local.loanaccount.model.ChargeRecordEntry;
import com.tx.local.loanaccount.model.ExemptRecord;
import com.tx.local.loanaccount.model.ExemptRecordEntry;
import com.tx.local.loanaccount.model.LATradingRecord;
import com.tx.local.loanaccount.model.LoanAccount;
import com.tx.local.loanaccount.model.PaymentRecord;
import com.tx.local.loanaccount.model.PaymentRecordEntry;
import com.tx.local.loanaccount.model.RepayIntention;
import com.tx.local.loanaccount.model.RepayIntentionTypeEnum;
import com.tx.local.loanaccount.model.SettleIntention;
import com.tx.local.loanaccount.strategy.loanaccount.LoanAccountStrategy;
import com.tx.local.loanaccount.strategy.loanaccount.LoanAccountStrategyHelper;

/**
  * 抽象还款处理器<br/>
  * <功能详细描述>
  * 
  * @author  Administrator
  * @version  [版本号, 2017年6月25日]
  * @see  [相关类/方法]
  * @since  [产品/模块版本]
  */
public class AbstractSettleReceiver<PR extends AbstractSettleRequest>
        extends AbstractDefaultTradingReceiver<PR> {
    
    /**
     * @param request
     * @param response
     * @return
     */
    @Override
    public boolean preHandle(PR request, CommandResponse response) {
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
                RepayIntention ri = new RepayIntention();
                ri.setAmount(request.getRepayAmount());
                ri.setRepayDate(request.getRepayDate());
                ri.setBankAccountId(request.getBankAccountId());
                ri.setLoanAccountId(request.getLoanAccountId());
                ri.setType(RepayIntentionTypeEnum.CASH_REPAY);
                
                OverRepayRequest overRepayRequest = new OverRepayRequest(
                        loanAccountId, ri, request.getSourceType());
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
    public void validateRequest(PR request, CommandResponse response,
            LoanAccount loanAccount,
            PaymentScheduleHandler paymentScheduleHandler) {
        BigDecimal sum = request.getSettleIntention().getRepayAmount();
        AssertUtils.notNull(sum, "还款金额为空.");
        AssertUtils.isTrue(sum.compareTo(BigDecimal.ZERO) > 0, "还款金额应大于0.");
        
        //还款金额应当大于还款意愿中的金额分布
        request.getSettleIntention().validate();
        
        //贷后取消的账户不能进行还款
        AssertUtils.notTrue(
                AccountStatusEnum.ACCN.equals(loanAccount.getAccountStatus()),
                "loanAccount is ACCN.");
        //判断贷款单账号是否锁定,被锁定的贷款账户不能进行现金还款，也不应当能够生成自动转账还款
        AssertUtils.notTrue(loanAccount.isLocked(), "loanAccount is locked");
    }
    
    /**
     * @param request
     * @param response
     * @param loanAccount
     * @param handler
     * @param tradingRecord
     * @return
     */
    @Override
    public List<ChargeRecordEntry> createChargeRecordEntryList(PR request,
            CommandResponse response, LoanAccount loanAccount,
            PaymentScheduleHandler handler, LATradingRecord tradingRecord) {
        //贷款账户处理策略
        LoanAccountStrategy strategy = LoanAccountStrategyHelper
                .getStrategy(loanAccount);
        
        //计费记录分项
        List<ChargeRecordEntry> creList = strategy.buildSettleChargeEntryList(
                loanAccount,
                loanAccount.getFeeItemMap(),
                loanAccount.getFeeItemCfgMap(),
                handler,
                tradingRecord,
                request.getRepayDate());
        return creList;
    }
    
    /**
     * @param request
     * @param response
     * @param loanAccount
     * @param handler
     * @param tradingRecord
     * @return
     */
    @Override
    public List<ExemptRecordEntry> createExemptRecordEntryList(PR request,
            CommandResponse response, LoanAccount loanAccount,
            PaymentScheduleHandler handler, LATradingRecord tradingRecord) {
        //贷款账户处理策略
        LoanAccountStrategy strategy = LoanAccountStrategyHelper
                .getStrategy(loanAccount);
        ExemptAllocator exemptAllocator = strategy.assignOfSettleExempt(
                loanAccount, handler, request.getSettleIntention());
        
        //豁免记录分项
        List<ExemptRecordEntry> ereList = strategy.assignOfSettleExempt(
                tradingRecord, loanAccount, handler, exemptAllocator);
        response.setAttribute(
                LoanAccountConstants.RESPONSE_KEY_EXEMPT_ALLOCATOR,
                exemptAllocator);
        
        return ereList;
    }
    
    /**
     * @param request
     * @param response
     * @param loanAccount
     * @param paymentScheduleHandler
     * @param tradingRecord
     * @return
     */
    @Override
    public List<PaymentRecordEntry> createPaymentRecordEntryList(PR request,
            CommandResponse response, LoanAccount loanAccount,
            PaymentScheduleHandler handler, LATradingRecord tradingRecord) {
        //贷款账户处理策略
        LoanAccountStrategy strategy = LoanAccountStrategyHelper
                .getStrategy(loanAccount);
        RepayAllocator repayAllocator = getRepayAllocator(loanAccount,
                handler,
                request.getSettleIntention(),
                response);
        
        //还款分配
        List<PaymentRecordEntry> preList = strategy.assignOfSettle(
                tradingRecord, loanAccount, handler, repayAllocator, true);
        response.setAttribute(LoanAccountConstants.RESPONSE_KEY_REPAY_ALLOCATOR,
                repayAllocator);
        
        //写入真正的交易金额
        tradingRecord.setSum(repayAllocator.getAmount()
                .subtract(repayAllocator.getOverRepayAmount()));
        tradingRecord.setRepaySum(repayAllocator.getAmount()
                .subtract(repayAllocator.getOverRepayAmount()));
        
        return preList;
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
    public void postHandle(PR request, CommandResponse response,
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
        
        loanAccount.setLastRepayAmount(tradingRecord.getSum());//更新最近还款金额
        loanAccount.setLastRepayDate(request.getRepayDate());//设置贷款账户的最后一次交易的还款日
        //将还款请求中的还款意愿填入交易记录中
        tradingRecord.setAttributes(
                RepayIntentionHelper.toString(request.getSettleIntention()));
    }
    
    /**
     * 后置处理<br/>
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
    public void afterHandle(PR request, CommandResponse response,
            LoanAccount loanAccount, PaymentScheduleHandler handler,
            LATradingRecord tradingRecord, List<ChargeRecord> chargeRecords,
            List<ExemptRecord> exemptRecords,
            List<PaymentRecord> paymentRecords) {
        //修改此处还款事件中的金额，使用交易金额
        RepayEvent event = new RepayEvent(tradingRecord.getSum(), loanAccount,
                tradingRecord, handler, paymentRecords, request);
        EventContext.getContext().trigger(event);
        
        //        //处理超额还款
        //        RepayAllocator repayAllocator = getRepayAllocator(loanAccount, handler, request.getSettleIntention(), response);
        //        BigDecimal overRepayAmount = repayAllocator.getOverRepayAmount();
        //        if (overRepayAmount.compareTo(BigDecimal.ZERO) > 0) {
        //            RepayIntention orRepayIntention = RepayIntentionHelper.buildRepayIntentionOfOverRepay(overRepayAmount,
        //                    request.getRepayIntention());
        //            OverRepayRequest orRequest = new OverRepayRequest(request.getLoanAccountId(), orRepayIntention,
        //                    request.getSourceType());
        //            CommandContext.getContext().post(orRequest);
        //        }
    }
    
    /**
     * 获取还款分配器<br/>
     * <功能详细描述>
     * @param loanAccount
     * @param handler
     * @param repayIntention
     * @param response
     * @return [参数说明]
     * 
     * @return RepayAllocator [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    protected RepayAllocator getRepayAllocator(LoanAccount loanAccount,
            PaymentScheduleHandler handler, SettleIntention settleIntention,
            CommandResponse response) {
        AssertUtils.notNull(loanAccount, "loanAccount is null.");
        AssertUtils.notNull(handler, "handler is null.");
        AssertUtils.notNull(settleIntention, "settleIntention is null.");
        AssertUtils.notNull(response, "response is null.");
        
        //贷款账户处理策略
        RepayAllocator repayAllocator = (RepayAllocator) response.getAttribute(
                LoanAccountConstants.RESPONSE_KEY_REPAY_ALLOCATOR);
        if (repayAllocator == null) {
            LoanAccountStrategy strategy = LoanAccountStrategyHelper
                    .getStrategy(loanAccount);
            repayAllocator = strategy.assignOfSettle(loanAccount,
                    handler,
                    settleIntention);
            
            response.setAttribute(
                    LoanAccountConstants.RESPONSE_KEY_REPAY_ALLOCATOR,
                    repayAllocator);
        }
        
        return repayAllocator;
    }
}
