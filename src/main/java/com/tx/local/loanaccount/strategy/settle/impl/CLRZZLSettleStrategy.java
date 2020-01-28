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
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.joda.time.DateTime;
import org.springframework.stereotype.Component;

import com.tx.component.command.context.CommandContext;
import com.tx.component.command.context.CommandResponse;
import com.tx.core.exceptions.util.AssertUtils;
import com.tx.core.util.DateUtils;
import com.tx.local.basicdata.model.FeeItemCfg;
import com.tx.local.basicdata.model.FeeItemEnum;
import com.tx.local.loanaccount.LoanAccountConstants;
import com.tx.local.loanaccount.context.request.trading.settle.SettleRequest;
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
import com.tx.local.loanaccount.model.SettleIntention;
import com.tx.local.loanaccount.strategy.settle.AbstractSettleStrategy;

/**
  * 车辆融资租赁结清策略<br/>
  * <功能详细描述>
  * 
  * @author  bobby
  * @version  [版本号, 2017年10月9日]
  * @see  [相关类/方法]
  * @since  [产品/模块版本]
 */
@Component("clrzzlSettleStrategy")
public class CLRZZLSettleStrategy extends AbstractSettleStrategy {
    
    /**
     * @param loanAccount
     * @param settleIntention
     * @return
     */
    @Override
    public LATradingRecord settle(LoanAccount loanAccount,
            SettleIntention settleIntention) {
        AssertUtils.notNull(loanAccount, "loanAccount is null.");
        AssertUtils.notEmpty(loanAccount.getId(), "loanAccount.id is empty.");
        AssertUtils.notNull(settleIntention, "settleIntention is null.");
        AssertUtils.notEmpty(settleIntention.getLoanAccountId(),
                "settleIntention.loanAccountId is empty.");
        AssertUtils.notEmpty(settleIntention.getBankAccountId(),
                "settleIntention.bankAccountId is empty.");
        
        AssertUtils.notNull(settleIntention.getRepayDate(),
                "settleIntention.repayDate is null.");
        AssertUtils.notNull(settleIntention.getRepayAmount(),
                "settleIntention.amount is null.");
        
        String loanAccountId = loanAccount.getId();
        AssertUtils.isTrue(
                loanAccountId.equals(settleIntention.getLoanAccountId()),
                "loanAccountId is not matches.");
        
        SettleRequest rRequest = new SettleRequest(loanAccountId,
                settleIntention);
        CommandResponse response = CommandContext.getContext().post(rRequest);
        
        LATradingRecord tr = (LATradingRecord) response
                .getAttribute(LoanAccountConstants.RESPONSE_KEY_TRADINGRECORD);
        return tr;
    }
    
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
        return creListWithTQHKWYJ;
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
                
                //购置款和本金需要冲销至0，并进行记录
                if (feeItemCfgMap.get(feeItem).isPrincipal() || FeeItemEnum.DK_GZK.equals(feeItem)) {
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
        
        return creList;
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
    protected List<ChargeRecordEntry> doBuildBalanceSettleChargeEntryList(
            Map<FeeItemEnum, LoanAccountFeeItem> feeItemMap,
            Map<FeeItemEnum, FeeItemCfg> feeItemCfgMap,
            PaymentScheduleHandler handler, LATradingRecord tradingRecord,
            Date repayDate, int receivablePeriod) {
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
                BigDecimal sumTemp = pse.getReceivableAmount()
                        .add(pse.getExemptAmount())
                        .subtract(pse.getActualReceivedAmount());
                
                //冲抵金额，用于写入当前应收
                if (psMapTemp.containsKey(feeItem)) {
                    psMapTemp.put(feeItem, psMapTemp.get(feeItem).add(sumTemp));
                } else {
                    psMapTemp.put(feeItem, sumTemp);
                }
                //冲抵 
                creList.add(ChargeRecordEntryHelper
                        .buildBalanceEntryList(tradingRecord, pse));
            }
        }
        
        //=receivablePeriod的时候计算当前期应收
        for (PaymentSchedule psTemp : handler.getPaymentScheduleMap()
                .valueList(String.valueOf(receivablePeriod))) {
            ScheduleTypeEnum scheduleType = psTemp.getScheduleType();
            String period = psTemp.getPeriod();
            List<PaymentScheduleEntry> pseList = psTemp
                    .getPaymentScheduleEntryList();
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
                DateTime dt = new DateTime(psTemp.getRepaymentDate());
                dt = dt.plusMonths(-1);//获取上一月
                int days = getMonthDays(dt);
                
                if (DateUtils.compareByDay(repayDate, dt.toDate()) < 0) {
                    receivableAmountTemp = sumTemp;
                } else if (DateUtils.compareByDay(repayDate,
                        psTemp.getRepaymentDate()) < 0) {
                    //不到一个月
                    int daysTemp = DateUtils.calculateNumberOfDaysBetween(
                            repayDate, dt.toDate()) + 1;
                    BigDecimal amountTemp = pse.getReceivableAmount()
                            .divide(new BigDecimal(days), 8, RoundingMode.UP)
                            .multiply(new BigDecimal(daysTemp))
                            .setScale(0, RoundingMode.UP);
                    receivableAmountTemp = amountTemp.compareTo(sumTemp) >= 0
                            ? amountTemp : sumTemp;
                } else {
                    receivableAmountTemp = pse.getReceivableAmount();
                }
                
                if (receivableAmountTemp
                        .compareTo(pse.getReceivableAmount()) == 0) {
                    continue;
                }
                AssertUtils.isTrue(
                        receivableAmountTemp
                                .compareTo(pse.getReceivableAmount()) < 0,
                        "receivableAmountTemp:{} should < pse.getReceivableAmount():{}.",
                        new Object[] { receivableAmountTemp,
                                pse.getReceivableAmount() });
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
}
