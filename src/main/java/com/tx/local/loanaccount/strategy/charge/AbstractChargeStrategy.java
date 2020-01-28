/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2017年7月2日
 * <修改描述:>
 */
package com.tx.local.loanaccount.strategy.charge;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;

import com.tx.local.loanaccount.handle.PaymentScheduleHandler;
import com.tx.local.loanaccount.helper.chargerecord.ChargeRecordEntryHelper;
import com.tx.local.loanaccount.model.ChargeRecordEntry;
import com.tx.local.loanaccount.model.DeductFailChargeRecord;
import com.tx.local.loanaccount.model.LATradingRecord;
import com.tx.local.loanaccount.model.LoanAccount;
import com.tx.local.loanaccount.model.OutsourceChargeRecord;
import com.tx.local.loanaccount.model.OverdueInterestChargeRecord;
import com.tx.local.loanaccount.model.ScheduleTypeEnum;

/**
 * 车商贷计费策略<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2017年7月2日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public abstract class AbstractChargeStrategy implements ChargeStrategy {
    
    /**
     * @param loanAccount
     * @param handler
     * @param tradingRecord
     * @param overdueInterestChargeRecords
     * @return
     */
    @Override
    public List<ChargeRecordEntry> buildYQLXChargeEntryList(
            LoanAccount loanAccount, PaymentScheduleHandler handler,
            LATradingRecord tradingRecord,
            List<OverdueInterestChargeRecord> overdueInterestChargeRecords) {
        List<ChargeRecordEntry> creList = new ArrayList<>();
        if (CollectionUtils.isEmpty(overdueInterestChargeRecords)) {
            return creList;
        }
        for (OverdueInterestChargeRecord oicrTemp : overdueInterestChargeRecords) {
            for (ScheduleTypeEnum scheduleTypeTemp : handler
                    .getPaymentScheduleMap().keySet()) {
                creList.add(ChargeRecordEntryHelper.buildChargeRecordEntry(
                        tradingRecord,
                        handler,
                        scheduleTypeTemp,
                        oicrTemp.getPeriod(),
                        oicrTemp.getFeeItem(),
                        oicrTemp.getAmount()));
            }
        }
        
        return creList;
    }
    
    /**
     * @param loanAccount
     * @param handler
     * @param tradingRecord
     * @param deductFailChargeRecord
     * @return
     */
    @Override
    public List<ChargeRecordEntry> buildKQSBSXFEntryList(
            LoanAccount loanAccount, PaymentScheduleHandler handler,
            LATradingRecord tradingRecord,
            List<DeductFailChargeRecord> deductFailChargeRecords) {
        List<ChargeRecordEntry> creList = new ArrayList<>();
        if (CollectionUtils.isEmpty(deductFailChargeRecords)) {
            return creList;
        }
        for (DeductFailChargeRecord dfcrTemp : deductFailChargeRecords) {
            for (ScheduleTypeEnum scheduleTypeTemp : handler
                    .getPaymentScheduleMap().keySet()) {
                creList.add(ChargeRecordEntryHelper.buildChargeRecordEntry(
                        tradingRecord,
                        handler,
                        scheduleTypeTemp,
                        dfcrTemp.getPeriod(),
                        dfcrTemp.getFeeItem(),
                        dfcrTemp.getAmount()));
            }
        }
        
        return creList;
    }
    
    /**
     * @param loanAccount
     * @param feeItemMap
     * @param handler
     * @param tradingRecord
     * @param repayAmount
     * @param outsourcePercentage
     * @return
     */
    @Override
    public List<ChargeRecordEntry> buildWBYJEntryList(LoanAccount loanAccount,
            PaymentScheduleHandler handler, LATradingRecord tradingRecord,
            List<OutsourceChargeRecord> outsourceChargeRecords) {
        List<ChargeRecordEntry> creList = new ArrayList<>();
        if (CollectionUtils.isEmpty(outsourceChargeRecords)) {
            return creList;
        }
        
        for (OutsourceChargeRecord ocrTemp : outsourceChargeRecords) {
            for (ScheduleTypeEnum scheduleTypeTemp : handler
                    .getPaymentScheduleMap().keySet()) {
                creList.add(ChargeRecordEntryHelper.buildChargeRecordEntry(
                        tradingRecord,
                        handler,
                        scheduleTypeTemp,
                        ocrTemp.getPeriod(),
                        ocrTemp.getFeeItem(),
                        ocrTemp.getAmount()));
            }
        }
        return creList;
    }
}
