/*
c * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2014年8月29日
 * <修改描述:>
 */
package com.tx.local.loanaccount.context.receiver.trading.defaults.loan;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.tx.component.command.context.CommandResponse;
import com.tx.core.TxConstants;
import com.tx.core.exceptions.util.AssertUtils;
import com.tx.local.basicdata.model.FeeItemEnum;
import com.tx.local.loanaccount.LoanAccountConstants;
import com.tx.local.loanaccount.context.receiver.trading.defaults.AbstractDefaultTradingReceiver;
import com.tx.local.loanaccount.context.request.trading.loan.AbstractLoanRequest;
import com.tx.local.loanaccount.handle.PaymentScheduleHandler;
import com.tx.local.loanaccount.helper.chargerecord.ChargeRecordEntryHelper;
import com.tx.local.loanaccount.helper.paymentrecord.PaymentRecordEntryHelper;
import com.tx.local.loanaccount.model.AccountStatusEnum;
import com.tx.local.loanaccount.model.ChargeRecordEntry;
import com.tx.local.loanaccount.model.CollectionStatusEnum;
import com.tx.local.loanaccount.model.LATradingRecord;
import com.tx.local.loanaccount.model.LATradingRecordEntry;
import com.tx.local.loanaccount.model.LoanAccount;
import com.tx.local.loanaccount.model.PaymentRecord;
import com.tx.local.loanaccount.model.PaymentRecordEntry;
import com.tx.local.loanaccount.model.ScheduleTypeEnum;

/**
 * 基础还款计费处理器<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2014年8月29日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public abstract class AbstractLoanReceiver<PR extends AbstractLoanRequest> extends AbstractDefaultTradingReceiver<PR> {
    
    /**
     * @param request
     * @param response
     * @param loanAccount
     * @param paymentScheduleHandler
     */
    @Override
    public void validateRequest(PR request, CommandResponse response, LoanAccount loanAccount,
            PaymentScheduleHandler paymentScheduleHandler) {
        //账户状态为AC
        AssertUtils.isTrue(AccountStatusEnum.AC.equals(loanAccount.getAccountStatus()), "loanAccount is AC");
        //账户催收状态为NA
        AssertUtils.isTrue(CollectionStatusEnum.NA.equals(loanAccount.getCollectionStatus()), "loanAccount is NA");
        
        //费用项
        Map<FeeItemEnum, BigDecimal> feeItem2amountMap = request.getFeeItem2AmountMap();
        BigDecimal sum = BigDecimal.ZERO;
        StringBuilder sb = new StringBuilder(TxConstants.INITIAL_STR_LENGTH);
        for (Entry<FeeItemEnum, BigDecimal> entryTemp : feeItem2amountMap.entrySet()) {
            if (entryTemp.getValue() == null || entryTemp.getValue().compareTo(BigDecimal.ZERO) <= 0) {
                continue;
            }
            sum = sum.add(entryTemp.getValue());
            sb.append(" + ").append(entryTemp.getKey().getName()); 
        }
        
        //判断 贷款金额 = 实际放款金额 + 费用（该验证作为公用逻辑，先注释，需要此验证需要实现自己的Receiver.validateRequest方法 2017-12-14）
        //        AssertUtils.isTrue(loanAccount.getLoanAmount().compareTo(loanAccount.getFactOutLoanAmount().add(sum)) == 0,
        //                "（贷款金额 <> 实际放款金额:'{}' 费用不相等.loanAmount:{} <> factOutLoanAmount():{} + sum:{}",
        //                new Object[] { sb.toString(), loanAccount.getLoanAmount(), loanAccount.getFactOutLoanAmount(), sum });
    }
    
    /**
     * 新贷计费记录分录集合构建
     * @param request
     * @param loanAccount
     * @param paymentSchedules
     * @param tradingRecord
     * @return
     */
    @Override
    public List<ChargeRecordEntry> createChargeRecordEntryList(PR request, CommandResponse response,
            LoanAccount loanAccount, PaymentScheduleHandler handler, LATradingRecord tradingRecord) {
        List<ChargeRecordEntry> chargeRecordEntryList = new ArrayList<>(TxConstants.INITIAL_CONLLECTION_SIZE);
        
        for (ScheduleTypeEnum scheduleType : handler.getPaymentScheduleMap().keySet()) {
            for (Entry<FeeItemEnum, BigDecimal> entryTemp : request.getFeeItem2AmountMap().entrySet()) {
                if (entryTemp.getValue() == null || entryTemp.getValue().compareTo(BigDecimal.ZERO) <= 0) {
                    continue;
                }
                //咨询公司延期费
                chargeRecordEntryList.add(ChargeRecordEntryHelper.buildChargeRecordEntry(tradingRecord,
                        handler,
                        scheduleType,
                        LoanAccountConstants.PERIOD_NA,
                        entryTemp.getKey(),
                        entryTemp.getValue()));
            }
        }
        return chargeRecordEntryList;
    }
    
    /**
     * @param request
     * @param loanAccount
     * @param paymentSchedules
     * @param tradingRecord
     * @return
     */
    @Override
    public List<PaymentRecordEntry> createPaymentRecordEntryList(PR request, CommandResponse response,
            LoanAccount loanAccount, PaymentScheduleHandler handler, LATradingRecord tradingRecord) {
        List<PaymentRecordEntry> paymentRecordEntryList = new ArrayList<>(TxConstants.INITIAL_CONLLECTION_SIZE);
        
        for (ScheduleTypeEnum scheduleType : handler.getPaymentScheduleMap().keySet()) {
            for (Entry<FeeItemEnum, BigDecimal> entryTemp : request.getFeeItem2AmountMap().entrySet()) {
                if (entryTemp.getValue() == null || entryTemp.getValue().compareTo(BigDecimal.ZERO) <= 0) {
                    continue;
                }
                //各费用项实收
                paymentRecordEntryList.add(PaymentRecordEntryHelper.buildPaymentRecordEntry(tradingRecord,
                        handler,
                        scheduleType,
                        LoanAccountConstants.PERIOD_NA,
                        entryTemp.getKey(),
                        entryTemp.getValue(),
                        true));
            }
        }
        return paymentRecordEntryList;
    }
    
    /**
     * 新贷的交易分项
     *      借方：本金_贷款金额
     *      贷方 :  银行存款
     *                贷前延期
     *                一次性手续费
     * @param request
     * @param loanAccount
     * @param paymentSchedules
     * @param tradingRecord
     * @param delayRecord
     * @param chargeRecords
     * @param paymentRecords
     * @return
     */
    @Override
    public List<LATradingRecordEntry> createTradingRecordEntryList(PR request, CommandResponse response,
            LoanAccount loanAccount, PaymentScheduleHandler handler, LATradingRecord tradingRecord,
            List<PaymentRecord> paymentRecords) {
        List<LATradingRecordEntry> tradingRecordEntryList = new ArrayList<>(TxConstants.INITIAL_CONLLECTION_SIZE);
        return tradingRecordEntryList;
    }
}
