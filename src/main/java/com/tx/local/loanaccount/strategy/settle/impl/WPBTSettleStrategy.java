/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2017年7月10日
 * <修改描述:>
 */
package com.tx.local.loanaccount.strategy.settle.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.joda.time.DateTime;
import org.springframework.stereotype.Component;

import com.tx.core.exceptions.util.AssertUtils;
import com.tx.core.util.DateUtils;
import com.tx.local.basicdata.model.FeeItemCfg;
import com.tx.local.basicdata.model.FeeItemEnum;
import com.tx.local.loanaccount.LoanAccountConstants;
import com.tx.local.loanaccount.handle.PaymentScheduleHandler;
import com.tx.local.loanaccount.helper.chargerecord.ChargeRecordEntryHelper;
import com.tx.local.loanaccount.helper.paymentschedule.PaymentScheduleHelper;
import com.tx.local.loanaccount.model.ChargeRecordEntry;
import com.tx.local.loanaccount.model.LATradingRecord;
import com.tx.local.loanaccount.model.LoanAccount;
import com.tx.local.loanaccount.model.LoanAccountFeeItem;
import com.tx.local.loanaccount.model.PaymentSchedule;
import com.tx.local.loanaccount.model.PaymentScheduleEntry;
import com.tx.local.loanaccount.model.ScheduleTypeEnum;

/**
 * 车商贷结清策略<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2017年7月10日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Component("wpbtSettleStrategy")
public class WPBTSettleStrategy extends DefaultSettleStrategy {
    
    /**
     * @param loanAccount
     * @param handler
     * @param feeItemCfgMap
     * @param tradingRecord
     * @param repayDate
     * @return
     */
    @Override
    public List<ChargeRecordEntry> buildSettleChargeEntryList(
            LoanAccount loanAccount,
            Map<FeeItemEnum, LoanAccountFeeItem> feeItemMap,
            Map<FeeItemEnum, FeeItemCfg> feeItemCfgMap,
            PaymentScheduleHandler handler, LATradingRecord tradingRecord,
            Date repayDate) {
        String receivablePeriodString = PaymentScheduleHelper
                .getReceivablePeriod(handler.getPaymentScheduleMap(),
                        ScheduleTypeEnum.MAIN,
                        repayDate);
        int receivablePeriod = NumberUtils.toInt(receivablePeriodString, 1);
        
        BigDecimal esSumWithoutTQHKWYJ = handler.getSum();
        
        //计算提前结清计费
        List<ChargeRecordEntry> creListWithTQHKWYJ = doBuildEarlySettleChargeEntryList(
                loanAccount,
                feeItemMap,
                feeItemCfgMap,
                handler,
                tradingRecord,
                repayDate,
                receivablePeriod,
                2);
        BigDecimal esSumWithTQHKWYJ = esSumWithoutTQHKWYJ;
        for (ChargeRecordEntry creTemp : creListWithTQHKWYJ) {
            esSumWithTQHKWYJ = esSumWithTQHKWYJ.add(creTemp.getAmount());
        }
        if (esSumWithTQHKWYJ.compareTo(esSumWithoutTQHKWYJ) <= 0) {
            return creListWithTQHKWYJ;
        }
        
        //计算剩余应还计费
        List<ChargeRecordEntry> creListWithoutTQHKWYJ = doBuildBalanceSettleChargeEntryList(
                feeItemMap,
                feeItemCfgMap,
                handler,
                tradingRecord,
                repayDate,
                receivablePeriod);
        
        return creListWithoutTQHKWYJ;
    }
    
    /** 
     * 构建情节计费分项<br/>
     *    含提前结清手续费的情况<br/>
     * <功能详细描述>
     * @param feeItemCfgMap
     * @param handler
     * @param tradingRecord
     * @param repayDate
     * @param receivablePeriodString
     * @param receivablePeriod
     * @return [参数说明]
     * 
     * @return List<ChargeRecordEntry> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    protected List<ChargeRecordEntry> doBuildEarlySettleChargeEntryList(
            LoanAccount loanAccount,
            Map<FeeItemEnum, LoanAccountFeeItem> feeItemMap,
            Map<FeeItemEnum, FeeItemCfg> feeItemCfgMap,
            PaymentScheduleHandler handler, LATradingRecord tradingRecord,
            Date repayDate, int receivablePeriod, int scale) {
        List<ChargeRecordEntry> creList = new ArrayList<>();
        
        //>receivablePeriod的时候，冲0 
        Map<ScheduleTypeEnum, Map<FeeItemEnum, BigDecimal>> sumMap = new HashMap<>();
        for (PaymentSchedule psTemp : handler.getPaymentScheduleMap()
                .valueList()) {
            String period = psTemp.getPeriod();
            if (!NumberUtils.isDigits(period)) {
                continue;
            }
            if (NumberUtils.isDigits(period)
                    && NumberUtils.toInt(period) <= receivablePeriod) {
                //小于应收期数的时候无需进行冲抵
                continue;
            }
            
            ScheduleTypeEnum scheduleType = psTemp.getScheduleType();
            if (!sumMap.containsKey(scheduleType)) {
                sumMap.put(scheduleType, new HashMap<>());
            }
            Map<FeeItemEnum, BigDecimal> psMapTemp = sumMap.get(scheduleType);
            
            List<PaymentScheduleEntry> pseList = psTemp
                    .getPaymentScheduleEntryList();
            for (PaymentScheduleEntry pse : pseList) {
                FeeItemEnum feeItem = pse.getFeeItem();
                
                if (feeItemCfgMap.get(feeItem).isPrincipal()) {
                    BigDecimal sumTemp = pse.getReceivableAmount()
                            .add(pse.getExemptAmount())
                            .subtract(pse.getActualReceivedAmount());
                    //为本金的时候
                    if (psMapTemp.containsKey(feeItem)) {
                        psMapTemp.put(feeItem,
                                psMapTemp.get(feeItem).add(sumTemp));
                    } else {
                        psMapTemp.put(feeItem, sumTemp);
                    }
                }
                creList.add(ChargeRecordEntryHelper
                        .buildBalanceEntryList(tradingRecord, pse));
            }
        }
        
        //=receivablePeriod的时候计算应收
        for (PaymentSchedule psTemp : handler.getPaymentScheduleMap()
                .valueList(String.valueOf(receivablePeriod))) {
            ScheduleTypeEnum scheduleType = psTemp.getScheduleType();
            String period = psTemp.getPeriod();
            List<PaymentScheduleEntry> pseList = psTemp
                    .getPaymentScheduleEntryList();
            
            DateTime prePeriodRepayDate = null;
            if (!StringUtils.isEmpty(psTemp.getPrePeriod())
                    && handler.getPaymentScheduleMap()
                            .get(scheduleType, psTemp.getPrePeriod())
                            .getRepaymentDate() != null) {
                //存在上一期，并且上一期的还款日不为空
                prePeriodRepayDate = new DateTime(
                        handler.getPaymentScheduleMap()
                                .get(scheduleType, psTemp.getPrePeriod())
                                .getRepaymentDate());
            } else {
                prePeriodRepayDate = new DateTime(
                        loanAccount.getInterestDate());
            }
            int days = PaymentScheduleHelper.getMonthDays(prePeriodRepayDate);
            
            for (PaymentScheduleEntry pse : pseList) {
                FeeItemEnum feeItem = pse.getFeeItem();
                if (feeItemCfgMap.get(feeItem).isPrincipal()) {
                    continue;
                }
                
                AssertUtils.notNull(psTemp.getRepaymentDate(),
                        "ps.repaymentDate is null.");
                BigDecimal receivableAmountTemp = pse.getReceivableAmount();
                BigDecimal sumTemp = pse.getExemptAmount()
                        .abs()
                        .add(pse.getActualReceivedAmount());
                
                if (DateUtils.compareByDay(repayDate,
                        prePeriodRepayDate.toDate()) < 0) {
                    receivableAmountTemp = sumTemp;
                } else if (DateUtils.compareByDay(repayDate,
                        psTemp.getRepaymentDate()) < 0) {
                    //不到一个月
                    int daysTemp = DateUtils.calculateNumberOfDaysBetween(
                            repayDate, prePeriodRepayDate.toDate()) + 1;
                    BigDecimal amountTemp = pse.getReceivableAmount()
                            .divide(new BigDecimal(days), 8, RoundingMode.UP)
                            .multiply(new BigDecimal(daysTemp))
                            .setScale(scale, RoundingMode.UP);
                    receivableAmountTemp = amountTemp.compareTo(sumTemp) >= 0
                            ? amountTemp : sumTemp;
                } else {
                    receivableAmountTemp = pse.getReceivableAmount();
                }
                
                if (receivableAmountTemp
                        .compareTo(pse.getReceivableAmount()) < 0) {
                    //增加本金类型的费用项的应收
                    creList.add(ChargeRecordEntryHelper.buildChargeRecordEntry(
                            tradingRecord,
                            handler,
                            scheduleType,
                            period,
                            feeItem,
                            receivableAmountTemp
                                    .subtract(pse.getReceivableAmount())));
                }
            }
        }
        
        //写入>当前期数时，将应收写入当前期数的情形
        for (Entry<ScheduleTypeEnum, Map<FeeItemEnum, BigDecimal>> entryTemp : sumMap
                .entrySet()) {
            ScheduleTypeEnum scheduleTypeTemp = entryTemp.getKey();
            for (Entry<FeeItemEnum, BigDecimal> eTemp : entryTemp.getValue()
                    .entrySet()) {
                BigDecimal amountTemp = eTemp.getValue();
                if (amountTemp == null
                        || amountTemp.compareTo(BigDecimal.ZERO) == 0) {
                    continue;
                }
                
                AssertUtils.isTrue(amountTemp.compareTo(BigDecimal.ZERO) > 0,
                        "amount:{} should > 0.",
                        new Object[] { amountTemp });
                //增加本金类型的费用项的应收
                creList.add(ChargeRecordEntryHelper.buildChargeRecordEntry(
                        tradingRecord,
                        handler,
                        scheduleTypeTemp,
                        String.valueOf(receivablePeriod),
                        eTemp.getKey(),
                        amountTemp));
            }
        }
        
        //提前结清手续费
        
        if (feeItemMap.get(FeeItemEnum.DK_TQHKWYJ) == null || StringUtils
                .isEmpty(feeItemMap.get(FeeItemEnum.DK_TQHKWYJ).getValue())) {
            return creList;
        }
        String tqhksxfRateValue = feeItemMap.get(FeeItemEnum.DK_TQHKWYJ)
                .getValue();
        BigDecimal tqhksxfRate = new BigDecimal(tqhksxfRateValue);
        BigDecimal tqhksxf = handler.getLoanAccount()
                .getLoanAmount()
                .multiply(tqhksxfRate)
                .setScale(2, RoundingMode.UP);
        if (tqhksxf.compareTo(BigDecimal.ZERO) > 0) {
            for (ScheduleTypeEnum scheduleType : handler.getPaymentScheduleMap()
                    .keySet()) {
                creList.add(ChargeRecordEntryHelper.buildChargeRecordEntry(
                        tradingRecord,
                        handler,
                        scheduleType,
                        LoanAccountConstants.PERIOD_NA,
                        FeeItemEnum.DK_TQHKWYJ,
                        tqhksxf));
            }
        }
        return creList;
    }
}
