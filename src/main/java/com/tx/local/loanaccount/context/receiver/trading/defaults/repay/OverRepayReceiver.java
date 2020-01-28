/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2014年6月16日
 * <修改描述:>
 */
package com.tx.local.loanaccount.context.receiver.trading.defaults.repay;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.tx.component.command.context.CommandResponse;
import com.tx.component.command.context.CommandSessionContext;
import com.tx.component.event.context.EventContext;
import com.tx.core.exceptions.util.AssertUtils;
import com.tx.local.loanaccount.LoanAccountConstants;
import com.tx.local.loanaccount.context.receiver.trading.defaults.AbstractDefaultTradingReceiver;
import com.tx.local.loanaccount.context.request.trading.repay.OverRepayRequest;
import com.tx.local.loanaccount.event.trading.RepayEvent;
import com.tx.local.loanaccount.handle.PaymentScheduleHandler;
import com.tx.local.loanaccount.helper.repay.RepayIntentionHelper;
import com.tx.local.loanaccount.model.AccountStatusEnum;
import com.tx.local.loanaccount.model.ChargeRecord;
import com.tx.local.loanaccount.model.ExemptRecord;
import com.tx.local.loanaccount.model.LATradingRecord;
import com.tx.local.loanaccount.model.LoanAccount;
import com.tx.local.loanaccount.model.OverRepayRecord;
import com.tx.local.loanaccount.model.PaymentRecord;
import com.tx.local.loanaccount.model.PaymentRecordEntry;
import com.tx.local.loanaccount.service.OverRepayRecordService;
import com.tx.local.loanaccount.strategy.loanaccount.LoanAccountStrategy;
import com.tx.local.loanaccount.strategy.loanaccount.LoanAccountStrategyHelper;

/**
 * 超额还款
 *     算法描述：贷款账户产生超额还款后
 *          如果对应交易未到账则将交易转为
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2014年6月16日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Component("overRepayReceiver")
public class OverRepayReceiver extends
        AbstractDefaultTradingReceiver<OverRepayRequest> {
    
    /** 超额还款业务层 */
    @Resource(name = "overRepayRecordService")
    private OverRepayRecordService overRepayRecordService;
    
    /**
     * @param request
     * @param response
     * @param loanAccount
     * @param handler
     */
    @Override
    public void validateRequest(OverRepayRequest request,
            CommandResponse response, LoanAccount loanAccount,
            PaymentScheduleHandler handler) {
        BigDecimal sum = request.getRepayIntention().getAmount();
        AssertUtils.notNull(sum, "还款金额为空.");
        AssertUtils.isTrue(sum.compareTo(BigDecimal.ZERO) > 0, "还款金额应大于0.");
        
        //还款金额应当大于还款意愿中的金额分布
        request.getRepayIntention().validate();
        
        //贷后取消的账户不能进行还款
        AssertUtils.notTrue(AccountStatusEnum.ACCN.equals(loanAccount.getAccountStatus()),
                "loanAccount is ACCN.");
        //判断贷款单账号是否锁定,被锁定的贷款账户不能进行现金还款，也不应当能够生成自动转账还款
        AssertUtils.notTrue(loanAccount.isLocked(), "loanAccount is locked");
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
    public LATradingRecord createTradingRecord(OverRepayRequest request,
            CommandResponse response, LoanAccount loanAccount,
            PaymentScheduleHandler handler) {
        Date now = new Date();
        LATradingRecord tradingRecord = new LATradingRecord();
        
        //账户交易是否到账
        tradingRecord.setReceived(request.isReceived());
        tradingRecord.setReceiveDate(request.isReceived() ? now : null);
        
        //设置是否可见，是否可撤销
        tradingRecord.setViewAble(true);
        tradingRecord.setRevokeAble(true);
        
        //设置交易的关联交易
        tradingRecord.setRelatedTradingRecordId(null);
        
        //银行存款金额、超额还款金额
        tradingRecord.setBankAccountId(request.getRepayIntention()
                .getBankAccountId());
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
     * @param handler
     * @param tradingRecord
     * @return
     */
    @Override
    public List<PaymentRecordEntry> createPaymentRecordEntryList(
            OverRepayRequest request, CommandResponse response,
            LoanAccount loanAccount, PaymentScheduleHandler handler,
            LATradingRecord tradingRecord) {
        LoanAccountStrategy las = LoanAccountStrategyHelper.getStrategy(loanAccount);
        List<OverRepayRecord> overRepayRecords = las.buildOverRepayRecords(loanAccount,
                handler,
                tradingRecord,
                request.getRepayIntention());
        AssertUtils.notEmpty(overRepayRecords, "overRepayRecords is empty.");
        CommandSessionContext.getSession()
                .setAttribute(LoanAccountConstants.SESSION_KEY_OVERREPAYRECORDS,
                        overRepayRecords);
        
        List<PaymentRecordEntry> resList = las.buildCEHKEntryList(loanAccount,
                handler,
                tradingRecord,
                overRepayRecords,
                true);
        return resList;
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
    public void postHandle(OverRepayRequest request, CommandResponse response,
            LoanAccount loanAccount,
            PaymentScheduleHandler paymentScheduleHandler,
            LATradingRecord tradingRecord, List<ChargeRecord> chargeRecords,
            List<ExemptRecord> exemptRecords, List<PaymentRecord> paymentRecords) {
        super.postHandle(request,
                response,
                loanAccount,
                paymentScheduleHandler,
                tradingRecord,
                chargeRecords,
                exemptRecords,
                paymentRecords);
        BigDecimal amount = request.getRepayIntention().getAmount();
        AssertUtils.isTrue(amount.compareTo(BigDecimal.ZERO) > 0, "超额还款金 额应大于0");
        
        //写入账户最新信息
        loanAccount.setLastRepayAmount(tradingRecord.getSum());//更新最近还款金额
        loanAccount.setLastRepayDate(request.getRepayDate());//设置贷款账户的最后一次交易的还款日
        BigDecimal sourceAmount = loanAccount.getOverRepayAmount() == null ? BigDecimal.ZERO
                : loanAccount.getOverRepayAmount();
        BigDecimal targetAmount = sourceAmount.add(amount);
        AssertUtils.isTrue(targetAmount.compareTo(BigDecimal.ZERO) > 0,
                "贷款账户的超额还款总金额应大于0");
        loanAccount.setOverRepayAmount(targetAmount);
        
        //将还款请求中的还款意愿填入交易记录中
        tradingRecord.setAttributes(RepayIntentionHelper.toString(request.getRepayIntention()));
        
        @SuppressWarnings("unchecked")
        List<OverRepayRecord> overRepayRecords = (List<OverRepayRecord>) CommandSessionContext.getSession()
                .getAttribute(LoanAccountConstants.SESSION_KEY_OVERREPAYRECORDS);
        this.overRepayRecordService.batchInsert(overRepayRecords);
    }
    
    /**
     * 触发客户还款事件
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
    public void afterHandle(OverRepayRequest request, CommandResponse response,
            LoanAccount loanAccount,
            PaymentScheduleHandler paymentScheduleHandler,
            LATradingRecord tradingRecord, List<ChargeRecord> chargeRecords,
            List<ExemptRecord> exemptRecords, List<PaymentRecord> paymentRecords) {
        //修改此处还款事件中的金额，使用交易金额
        RepayEvent event = new RepayEvent(tradingRecord.getSum(), loanAccount,
                tradingRecord, paymentScheduleHandler, paymentRecords, request);
        EventContext.getContext().trigger(event);
    }
    
}
