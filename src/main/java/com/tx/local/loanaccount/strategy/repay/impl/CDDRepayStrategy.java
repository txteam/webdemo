/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2017年6月25日
 * <修改描述:>
 */
package com.tx.local.loanaccount.strategy.repay.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.joda.time.DateTime;
import org.springframework.stereotype.Component;

import com.tx.component.strategy.context.StrategyContext;
import com.tx.core.exceptions.util.AssertUtils;
import com.tx.core.util.DateUtils;
import com.tx.local.basicdata.model.FeeItemCfg;
import com.tx.local.basicdata.model.FeeItemEnum;
import com.tx.local.loanaccount.handle.PaymentScheduleHandler;
import com.tx.local.loanaccount.helper.chargerecord.ChargeRecordEntryHelper;
import com.tx.local.loanaccount.helper.paymentschedule.PaymentScheduleHelper;
import com.tx.local.loanaccount.model.ChargeRecordEntry;
import com.tx.local.loanaccount.model.LATradingRecord;
import com.tx.local.loanaccount.model.LoanAccount;
import com.tx.local.loanaccount.model.LoanAccountFeeItem;
import com.tx.local.loanaccount.model.OverRepayRecord;
import com.tx.local.loanaccount.model.PaymentSchedule;
import com.tx.local.loanaccount.model.PaymentScheduleEntry;
import com.tx.local.loanaccount.model.RepayIntention;
import com.tx.local.loanaccount.model.ScheduleTypeEnum;
import com.tx.local.loanaccount.strategy.repay.CEHKStrategy;

/**
 * 
 * <仓单贷还款策略实现>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2017年12月5日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Component("cddRepayStrategy")
public class CDDRepayStrategy extends AbstractRepayStrategy {
    
    /**
     * 构建超额还款记录<br/>
     * @param loanAccount
     * @param handler
     * @param tradingRecord
     * @param repayIntention
     * @return
     */
    @Override
    public List<OverRepayRecord> buildOverRepayRecords(LoanAccount loanAccount,
            PaymentScheduleHandler handler, LATradingRecord tradingRecord,
            RepayIntention repayIntention) {
        CEHKStrategy cehkStrategy = StrategyContext.getContext()
                .getStrategy(CEHKStrategy.class, "defaultCEHKStrategy");
        List<OverRepayRecord> orrList = cehkStrategy.buildOverRepayRecords(
                loanAccount, handler, tradingRecord, repayIntention);
        return orrList;
    }
    
    /**
     * @param loanAccount
     * @param feeItemMap
     * @param feeItemCfgMap
     * @param handler
     * @param tradingRecord
     * @param repayDate
     * @return
     */
    @Override
    public List<ChargeRecordEntry> buildRepayChargeEntryList(
            LoanAccount loanAccount,
            Map<FeeItemEnum, LoanAccountFeeItem> feeItemMap,
            Map<FeeItemEnum, FeeItemCfg> feeItemCfgMap,
            PaymentScheduleHandler handler, LATradingRecord tradingRecord,
            Date repayDate) {
        Date lastPeriodRepayDate = handler
                .getPaymentSchedule(ScheduleTypeEnum.MAIN,
                        String.valueOf(loanAccount.getTotalPeriod()))
                .getRepaymentDate();
        if (DateUtils.compareByDay(repayDate, lastPeriodRepayDate) > 0) {
            return null;
        }
        
        String receivablePeriodString = PaymentScheduleHelper
                .getReceivablePeriod(handler.getPaymentScheduleMap(),
                        ScheduleTypeEnum.MAIN,
                        repayDate);
        int receivablePeriod = NumberUtils.toInt(receivablePeriodString, 1);
        
        //还款计费
        List<ChargeRecordEntry> creListWithTQHKWYJ = doBuildRepayChargeEntryList(
                loanAccount,
                feeItemMap,
                feeItemCfgMap,
                handler,
                tradingRecord,
                repayDate,
                receivablePeriod,
                0);
        return creListWithTQHKWYJ;
    }
    
    /** 
     * 构建情节计费分项<br/>
     * 含提前结清手续费的情况<br/>
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
    protected List<ChargeRecordEntry> doBuildRepayChargeEntryList(
            LoanAccount loanAccount,
            Map<FeeItemEnum, LoanAccountFeeItem> feeItemMap,
            Map<FeeItemEnum, FeeItemCfg> feeItemCfgMap,
            PaymentScheduleHandler handler, LATradingRecord tradingRecord,
            Date repayDate, int receivablePeriod, int scale) {
        List<ChargeRecordEntry> creList = new ArrayList<>();
        //>receivablePeriod的时候，冲0 
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
            
            List<PaymentScheduleEntry> pseList = psTemp
                    .getPaymentScheduleEntryList();
            for (PaymentScheduleEntry pse : pseList) {
                creList.add(ChargeRecordEntryHelper.buildBalanceEntryList(
                        tradingRecord,
                        pse,
                        receivablePeriod,
                        repayDate,
                        scale));
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
                //存在上一期，`
                prePeriodRepayDate = new DateTime(
                        handler.getPaymentScheduleMap()
                                .get(scheduleType, psTemp.getPrePeriod())
                                .getRepaymentDate());
            } else {
                prePeriodRepayDate = new DateTime(
                        loanAccount.getInterestDate());
            }
            int days = getMonthDays(prePeriodRepayDate);
            
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
                        psTemp.getRepaymentDate()) < 0
                        && pse.getReceivableAmount()
                                .compareTo(BigDecimal.ZERO) == 1) {
                    //不到一个月
                    int daysTemp = DateUtils.calculateNumberOfDaysBetween(
                            psTemp.getRepaymentDate(), repayDate) - 1;
                    //获取本金结余
                    PaymentScheduleEntry psEntry = handler
                            .getPaymentScheduleEntry(pse.getScheduleType(),
                                    pse.getPeriod(),
                                    FeeItemEnum.DK_BJ);
                    BigDecimal principalBalance = psTemp.getPrincipalBalance()
                            .add(psEntry.getReceivableAmount()
                                    .subtract(psEntry.getActualReceivedAmount())
                                    .add(psEntry.getExemptAmount()));
                    //年利率
                    BigDecimal yearRate = new BigDecimal(
                            feeItemMap.get(feeItem).getValue());
                    //月利率
                    BigDecimal monthlyRate = yearRate.divide(new BigDecimal(12),
                            8,
                            RoundingMode.UP);
                    BigDecimal mlx = principalBalance.multiply(monthlyRate)
                            .setScale(scale, BigDecimal.ROUND_UP);
                    BigDecimal amountTemp = mlx
                            .divide(new BigDecimal(days),
                                    8,
                                    BigDecimal.ROUND_UP)
                            .multiply(new BigDecimal(days - daysTemp))
                            .setScale(scale, BigDecimal.ROUND_UP);
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
        return creList;
    }
    
    /** 
     * 得到指定月的天数 
     * */
    private static int getMonthDays(DateTime repaymentDateTime) {
        AssertUtils.notNull(repaymentDateTime, "repaymentDateTime is null.");
        
        int year = repaymentDateTime.getYear();
        int month = repaymentDateTime.getMonthOfYear();
        Calendar a = Calendar.getInstance();
        a.set(Calendar.YEAR, year);
        a.set(Calendar.MONTH, month - 1);
        a.set(Calendar.DATE, 1);//把日期设置为当月第一天  
        a.roll(Calendar.DATE, -1);//日期回滚一天，也就是最后一天  
        int days = a.get(Calendar.DATE);
        return days;
    }
    
    /**
     * @param loanAccount
     * @param handler
     * @param chargeRecordEntryList
     * @return
     */
    @Override
    public List<ChargeRecordEntry> resetChargeRecordEntryList(
            LoanAccount loanAccount, PaymentScheduleHandler handler,
            List<ChargeRecordEntry> chargeRecordEntryList) {
        if (CollectionUtils.isEmpty(chargeRecordEntryList)) {
            return null;
        }
        Map<FeeItemEnum, FeeItemCfg> feeItemCfgMap = handler.getFeeItemCfgMap();
        for (ChargeRecordEntry chargeRecordEntry : chargeRecordEntryList) {
            //当费用项不为本金且计费的冲销金额小于0
            if (!feeItemCfgMap.get(chargeRecordEntry.getFeeItem()).isPrincipal()
                    && chargeRecordEntry.getAmount()
                            .compareTo(BigDecimal.ZERO) == -1) {
                PaymentScheduleEntry psEntry = handler.getPaymentScheduleEntry(
                        chargeRecordEntry.getScheduleType(),
                        chargeRecordEntry.getPeriod(),
                        FeeItemEnum.DK_BJ);
                PaymentSchedule ps = handler.getPaymentSchedule(
                        chargeRecordEntry.getScheduleType(),
                        chargeRecordEntry.getPeriod());
                //获取剩余应还本金金额
                BigDecimal amount = psEntry.getReceivableAmount()
                        .add(psEntry.getExemptAmount())
                        .subtract(psEntry.getActualReceivedAmount());
                ps.setReceivableSum(ps.getReceivableSum()
                        .add(chargeRecordEntry.getAmount().negate()));
                PaymentScheduleEntry pse = handler.getPaymentScheduleEntry(
                        chargeRecordEntry.getScheduleType(),
                        chargeRecordEntry.getPeriod(),
                        chargeRecordEntry.getFeeItem());
                pse.setReceivableAmount(chargeRecordEntry.getSourceAmount());
                if (amount.compareTo(BigDecimal.ZERO) == 1) {
                    //剩余本金计算当前费用应还款额
                    BigDecimal receAmount = (ps.getPrincipalBalance()
                            .add(amount))
                                    .divide(ps.getPrincipalBalance()
                                            .add(psEntry.getReceivableAmount()),
                                            16,
                                            BigDecimal.ROUND_UP)
                                    .multiply(chargeRecordEntry.getAmount()
                                            .negate())
                                    .setScale(0, BigDecimal.ROUND_UP);
                    chargeRecordEntry.setTargetAmount(chargeRecordEntry
                            .getTargetAmount().add(receAmount));
                    chargeRecordEntry.setAmount(
                            chargeRecordEntry.getAmount().add(receAmount));
                } else {
                    //根据本金结余计算当期应收金额
                    DateTime prePeriodRepayDate = null;
                    LATradingRecord record = chargeRecordEntry
                            .getTradingRecord();
                    if (!StringUtils.isEmpty(ps.getPrePeriod())
                            && handler.getPaymentScheduleMap()
                                    .get(chargeRecordEntry.getScheduleType(),
                                            ps.getPrePeriod())
                                    .getRepaymentDate() != null) {
                        //存在上一期，`
                        prePeriodRepayDate = new DateTime(handler
                                .getPaymentScheduleMap()
                                .get(chargeRecordEntry.getScheduleType(),
                                        ps.getPrePeriod())
                                .getRepaymentDate());
                    } else {
                        prePeriodRepayDate = new DateTime(
                                loanAccount.getInterestDate());
                    }
                    int days = getMonthDays(prePeriodRepayDate);
                    //不到一个月
                    int daysTemp = DateUtils.calculateNumberOfDaysBetween(
                            ps.getRepaymentDate(), record.getRepayDate()) - 1;
                    daysTemp = daysTemp > days ? days : daysTemp;
                    //获取本金结余
                    BigDecimal principalBalance = handler
                            .getPrincipalBalance(ScheduleTypeEnum.MAIN);
                    Map<FeeItemEnum, LoanAccountFeeItem> feeItemMap = handler
                            .getFeeItemMap();
                    //年利率
                    BigDecimal yearRate = new BigDecimal(feeItemMap
                            .get(chargeRecordEntry.getFeeItem()).getValue());
                    //月利率
                    BigDecimal monthlyRate = yearRate.divide(new BigDecimal(12),
                            8,
                            RoundingMode.UP);
                    BigDecimal mlx = principalBalance.multiply(monthlyRate)
                            .setScale(0, BigDecimal.ROUND_UP);
                    BigDecimal amountTemp = mlx
                            .divide(new BigDecimal(days),
                                    8,
                                    BigDecimal.ROUND_UP)
                            .multiply(new BigDecimal(daysTemp))
                            .setScale(0, BigDecimal.ROUND_UP);
                    amountTemp = amountTemp.compareTo(mlx) >= 0 ? mlx
                            : amountTemp;
                    chargeRecordEntry.setTargetAmount(chargeRecordEntry
                            .getTargetAmount().add(amountTemp));
                    chargeRecordEntry.setAmount(
                            chargeRecordEntry.getAmount().add(amountTemp));
                }
            }
        }
        return chargeRecordEntryList;
    }
}
