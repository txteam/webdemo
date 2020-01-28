/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2014年6月6日
 * <修改描述:>
 */
package com.tx.local.loanaccount.helper.chargerecord;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.tx.core.TxConstants;
import com.tx.core.exceptions.util.AssertUtils;
import com.tx.local.basicdata.model.FeeItemEnum;
import com.tx.local.loanaccount.handle.PaymentScheduleHandler;
import com.tx.local.loanaccount.model.ChargeRecordEntry;
import com.tx.local.loanaccount.model.LATradingRecord;
import com.tx.local.loanaccount.model.PaymentScheduleEntry;
import com.tx.local.loanaccount.model.ScheduleTypeEnum;

/**
 * 计费记录帮助类实现<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2014年6月6日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public abstract class ChargeRecordEntryHelper {
    
    /**
     * 构建计费分项实体<br/>
     * <功能详细描述>
     * @param tradingRecord
     * @param handler
     * @param scheduleType
     * @param period
     * @param feeItem
     * @param amount
     * @return [参数说明]
     * 
     * @return ChargeRecordEntry [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public static ChargeRecordEntry buildChargeRecordEntry(
            LATradingRecord tradingRecord, PaymentScheduleHandler handler,
            ScheduleTypeEnum scheduleType, String period, FeeItemEnum feeItem,
            BigDecimal amount) {
        AssertUtils.notNull(handler, "handler is empty.");
        AssertUtils.notNull(scheduleType, "scheduleType is null.");
        AssertUtils.notEmpty(period, "period is empty.");
        AssertUtils.notNull(feeItem, "feeItem is null.");
        AssertUtils.notNull(amount, "amount is null.");
        
        PaymentScheduleEntry paymentScheduleEntry = handler
                .getPaymentScheduleEntry(scheduleType, period, feeItem);
        
        ChargeRecordEntry entry = new ChargeRecordEntry();
        entry.setLoanAccountId(tradingRecord != null
                ? tradingRecord.getLoanAccountId() : null);
        //设置分项对应的交易记录
        entry.setTradingRecord(tradingRecord);
        // 根据费用项计算费用
        entry.setScheduleType(scheduleType);
        entry.setPeriod(period);
        entry.setFeeItem(feeItem);
        entry.setPaymentScheduleEntry(paymentScheduleEntry);
        
        entry.setSourceAmount(paymentScheduleEntry.getReceivableAmount());//平息应收
        entry.setAmount(amount);//增量
        entry.setTargetAmount(
                paymentScheduleEntry.getReceivableAmount().add(amount));//目标值
        
        //设置默认值
        entry.setRevoked(false);
        entry.setRevokeDate(null);
        Date now = new Date();
        entry.setCreateDate(now);
        entry.setLastUpdateDate(now);
        
        return entry;
    }
    
    /**
     * 构建撤销计费记录分项<br/>
     * <功能详细描述>
     * @param tradingRecord
     * @param handler
     * @param sourceChargeRecordEntryList
     * @return [参数说明]
     * 
     * @return List<ChargeRecordEntry> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public static List<ChargeRecordEntry> buildRevokeEntryList(
            LATradingRecord tradingRecord, PaymentScheduleHandler handler,
            List<ChargeRecordEntry> sourceChargeRecordEntryList) {
        List<ChargeRecordEntry> chargeRecordEntryList = new ArrayList<>(
                TxConstants.INITIAL_CONLLECTION_SIZE);
        if (CollectionUtils.isEmpty(sourceChargeRecordEntryList)) {
            return chargeRecordEntryList;
        }
        for (ChargeRecordEntry entry : sourceChargeRecordEntryList) {
            chargeRecordEntryList.add(buildChargeRecordEntry(tradingRecord,
                    handler,
                    entry.getScheduleType(),
                    entry.getPeriod(),
                    entry.getFeeItem(),
                    entry.getAmount().negate()));
        }
        return chargeRecordEntryList;
    }
    
    /**
     * 根据还款计划项构建，应收冲的计费记录<br/>
     * <功能详细描述>
     * @param tradingRecord
     * @param paymentScheduleEntry
     * @return [参数说明]
     * 
     * @return ChargeRecordEntry [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public static ChargeRecordEntry buildBalanceEntryList(
            LATradingRecord tradingRecord,
            PaymentScheduleEntry paymentScheduleEntry) {
        ChargeRecordEntry chargeRecordEntry = new ChargeRecordEntry();
        
        chargeRecordEntry.setLoanAccountId(tradingRecord != null
                ? tradingRecord.getLoanAccountId() : null);
        //设置分项对应的交易记录
        chargeRecordEntry.setTradingRecord(tradingRecord);
        // 根据费用项计算费用
        chargeRecordEntry
                .setScheduleType(paymentScheduleEntry.getScheduleType());
        chargeRecordEntry.setPeriod(paymentScheduleEntry.getPeriod());
        chargeRecordEntry.setFeeItem(paymentScheduleEntry.getFeeItem());
        chargeRecordEntry.setPaymentScheduleEntry(paymentScheduleEntry);
        
        BigDecimal amount = paymentScheduleEntry.getReceivableAmount()
                .add(paymentScheduleEntry.getExemptAmount())
                .subtract(paymentScheduleEntry.getActualReceivedAmount())
                .negate();
        chargeRecordEntry
                .setSourceAmount(paymentScheduleEntry.getReceivableAmount());//平息应收
        chargeRecordEntry.setAmount(amount);//增量
        chargeRecordEntry.setTargetAmount(
                paymentScheduleEntry.getReceivableAmount().add(amount));//目标值
        
        //设置默认值
        chargeRecordEntry.setRevoked(false);
        
        Date now = new Date();
        chargeRecordEntry.setRevokeDate(now);
        chargeRecordEntry.setCreateDate(now);
        chargeRecordEntry.setLastUpdateDate(now);
        
        return chargeRecordEntry;
    }
    
    /**
     * 根据还款计划项构建，应收冲的计费记录<br/>
     * <功能详细描述>
     * @param tradingRecord
     * @param paymentScheduleEntry
     * @return [参数说明]
     * 
     * @return ChargeRecordEntry [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public static ChargeRecordEntry buildBalanceEntryList(
            LATradingRecord tradingRecord,
            PaymentScheduleEntry paymentScheduleEntry, int receivablePeriod,
            Date repayDate, int scale) {
        ChargeRecordEntry chargeRecordEntry = new ChargeRecordEntry();
        
        chargeRecordEntry.setLoanAccountId(tradingRecord != null
                ? tradingRecord.getLoanAccountId() : null);
        //设置分项对应的交易记录
        chargeRecordEntry.setTradingRecord(tradingRecord);
        // 根据费用项计算费用
        chargeRecordEntry
                .setScheduleType(paymentScheduleEntry.getScheduleType());
        chargeRecordEntry.setPeriod(paymentScheduleEntry.getPeriod());
        chargeRecordEntry.setFeeItem(paymentScheduleEntry.getFeeItem());
        chargeRecordEntry.setPaymentScheduleEntry(paymentScheduleEntry);
        
        BigDecimal amount = paymentScheduleEntry.getReceivableAmount()
                .add(paymentScheduleEntry.getExemptAmount())
                .subtract(paymentScheduleEntry.getActualReceivedAmount());
        chargeRecordEntry
                .setSourceAmount(paymentScheduleEntry.getReceivableAmount());//平息应收
        switch (paymentScheduleEntry.getFeeItem()) {
            case DK_BJ:
                chargeRecordEntry.setAmount(BigDecimal.ZERO);//增量
                chargeRecordEntry.setTargetAmount(amount);//目标值
                break;
            default:
                chargeRecordEntry.setAmount(amount.negate());//增量
                chargeRecordEntry.setTargetAmount(paymentScheduleEntry
                        .getReceivableAmount().add(amount.negate()));//目标值
                break;
        }
        
        //设置默认值
        chargeRecordEntry.setRevoked(false);
        
        Date now = new Date();
        chargeRecordEntry.setRevokeDate(now);
        chargeRecordEntry.setCreateDate(now);
        chargeRecordEntry.setLastUpdateDate(now);
        
        return chargeRecordEntry;
    }
    
}
