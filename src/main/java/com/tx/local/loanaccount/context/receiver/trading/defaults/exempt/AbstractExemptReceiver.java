/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2017年6月25日
 * <修改描述:>
 */
package com.tx.local.loanaccount.context.receiver.trading.defaults.exempt;

import java.math.BigDecimal;
import java.util.List;

import com.tx.component.command.context.CommandResponse;
import com.tx.core.exceptions.util.AssertUtils;
import com.tx.core.util.MessageUtils;
import com.tx.local.loanaccount.LoanAccountConstants;
import com.tx.local.loanaccount.context.receiver.trading.defaults.AbstractDefaultTradingReceiver;
import com.tx.local.loanaccount.context.request.trading.exempt.AbstractExemptRequest;
import com.tx.local.loanaccount.handle.PaymentScheduleHandler;
import com.tx.local.loanaccount.helper.exemptallocator.ExemptAllocator;
import com.tx.local.loanaccount.model.AccountStatusEnum;
import com.tx.local.loanaccount.model.ChargeRecord;
import com.tx.local.loanaccount.model.ExemptIntention;
import com.tx.local.loanaccount.model.ExemptRecord;
import com.tx.local.loanaccount.model.ExemptRecordEntry;
import com.tx.local.loanaccount.model.LATradingRecord;
import com.tx.local.loanaccount.model.LoanAccount;
import com.tx.local.loanaccount.model.PaymentRecord;
import com.tx.local.loanaccount.strategy.loanaccount.LoanAccountStrategy;
import com.tx.local.loanaccount.strategy.loanaccount.LoanAccountStrategyHelper;

/**
  * 抽象豁免处理器<br/>
  * <功能详细描述>
  * 
  * @author  Administrator
  * @version  [版本号, 2017年6月25日]
  * @see  [相关类/方法]
  * @since  [产品/模块版本]
  */
public class AbstractExemptReceiver<PR extends AbstractExemptRequest> extends AbstractDefaultTradingReceiver<PR> {
    
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
            case AC:
            case SL:
            case RSL:
            case ESL:
                break;
            case ACCN:
            case ES:
            case RS:
            case FP:
            default:
                throw new IllegalArgumentException(
                        MessageUtils.format("错误的贷款账户状态:{}", new Object[] { loanAccount.getAccountStatus() }));
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
    public void validateRequest(PR request, CommandResponse response, LoanAccount loanAccount,
            PaymentScheduleHandler paymentScheduleHandler) {
        BigDecimal sum = request.getExemptIntention().getAmount();
        AssertUtils.notNull(sum, "还款金额为空.");
        AssertUtils.isTrue(sum.compareTo(BigDecimal.ZERO) > 0, "还款金额应大于0.");
        
        //还款金额应当大于还款意愿中的金额分布
        request.getExemptIntention().validate();
        //贷后取消的账户不能进行还款
        AssertUtils.notTrue(AccountStatusEnum.ACCN.equals(loanAccount.getAccountStatus()), "loanAccount is ACCN.");
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
    public List<ExemptRecordEntry> createExemptRecordEntryList(PR request, CommandResponse response,
            LoanAccount loanAccount, PaymentScheduleHandler handler, LATradingRecord tradingRecord) {
        //贷款账户处理策略
        LoanAccountStrategy strategy = LoanAccountStrategyHelper.getStrategy(loanAccount);
        ExemptAllocator exemptAllocator = getExemptAllocator(loanAccount,
                handler,
                request.getExemptIntention(),
                response);
        //还款分配
        List<ExemptRecordEntry> preList = strategy.assignOfExempt(tradingRecord, loanAccount, handler, exemptAllocator);
        return preList;
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
    public void afterHandle(PR request, CommandResponse response, LoanAccount loanAccount,
            PaymentScheduleHandler handler, LATradingRecord tradingRecord, List<ChargeRecord> chargeRecords,
            List<ExemptRecord> exemptRecords, List<PaymentRecord> paymentRecords) {
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
    protected ExemptAllocator getExemptAllocator(LoanAccount loanAccount, PaymentScheduleHandler handler,
            ExemptIntention exemptIntention, CommandResponse response) {
        AssertUtils.notNull(loanAccount, "loanAccount is null.");
        AssertUtils.notNull(handler, "handler is null.");
        AssertUtils.notNull(exemptIntention, "exemptIntention is null.");
        AssertUtils.notNull(response, "response is null.");
        
        ExemptAllocator exemptAllocator = (ExemptAllocator) response
                .getAttribute(LoanAccountConstants.RESPONSE_KEY_EXEMPT_ALLOCATOR);
        if (exemptAllocator == null) {
            LoanAccountStrategy strategy = LoanAccountStrategyHelper.getStrategy(loanAccount);
            exemptAllocator = strategy.assignOfExempt(loanAccount, handler, exemptIntention);
        }
        
        return exemptAllocator;
    }
}
