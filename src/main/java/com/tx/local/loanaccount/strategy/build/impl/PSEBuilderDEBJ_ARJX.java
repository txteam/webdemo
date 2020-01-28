/*
 * 描          述:  <描述>
 * 修  改   人:  Bobby
 * 修改时间:  2018年4月3日
 * <修改描述:>
 */
package com.tx.local.loanaccount.strategy.build.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;
import org.springframework.stereotype.Component;

import com.tx.core.exceptions.util.AssertUtils;
import com.tx.local.basicdata.model.FeeItemEnum;
import com.tx.local.basicdata.model.RepayWayEnum;
import com.tx.local.loanaccount.model.PaymentScheduleEntry;
import com.tx.local.loanaccount.model.ScheduleTypeEnum;
import com.tx.local.loanaccount.strategy.build.AbstractPaymentScheduleEntryBuilder_ROUND_UP;

/**
 * <等额本金订单贷>
 * <功能详细描述>
 * 
 * @author  Bobby
 * @version  [版本号, 2018年4月3日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Component("pseBuilderDEBJ_ARJX")
public class PSEBuilderDEBJ_ARJX
        extends AbstractPaymentScheduleEntryBuilder_ROUND_UP {
    
    /**
     * @param loanAccount
     * @param feeCfgItems
     * @param scheduleType
     * @param params
     * @return
     */
    @Override
    public List<PaymentScheduleEntry> buildPaymentScheduleEntryList(
            String loanAccountId, Map<FeeItemEnum, String> feeItemMap,
            Map<String, Object> params, int scale, RepayWayEnum repayWay) {
        AssertUtils.notEmpty(loanAccountId, "loanAccountId is empty.");
        AssertUtils.isTrue(params.containsKey("loanAmount"),
                "loanAmount is null.");
        AssertUtils.isTrue(params.containsKey("totalPeriod"),
                "loanAmount is null.");
        AssertUtils.isTrue(params.containsKey("firstRepayDate"),
                "firstRepayDate is null.");
        AssertUtils.isTrue(params.containsKey("factLoanDate"),
                "factLoanDate is null.");
        BigDecimal loanAmount = (BigDecimal) params.get("loanAmount");
        int totalPeriod = (Integer) params.get("totalPeriod");
        DateTime factLoanDate = new DateTime(params.get("factLoanDate"));
        //        DateTime firstRepayDate=new DateTime(params.get("firstRepayDate"));
        List<PaymentScheduleEntry> paymentScheduleEntryList = new ArrayList<>();
        BigDecimal monthlyPrincipal = loanAmount
                .divide(new BigDecimal(totalPeriod), 0, BigDecimal.ROUND_UP);//每月还本金
        //管理费:
        if (feeItemMap.containsKey(FeeItemEnum.ZX_GLF)) {
            BigDecimal yearRate = new BigDecimal(
                    feeItemMap.get(FeeItemEnum.ZX_GLF));
            BigDecimal dayRate = yearRate.divide(new BigDecimal(360),
                    8,
                    RoundingMode.UP);
            buildMonthlyRepay(paymentScheduleEntryList,
                    loanAccountId,
                    loanAmount,
                    totalPeriod,
                    dayRate,
                    monthlyPrincipal,
                    scale,
                    FeeItemEnum.ZX_GLF,
                    factLoanDate);
        }
        if (feeItemMap.containsKey(FeeItemEnum.DK_GLF)) {
            BigDecimal yearRate = new BigDecimal(
                    feeItemMap.get(FeeItemEnum.DK_GLF));
            BigDecimal dayRate = yearRate.divide(new BigDecimal(360),
                    8,
                    RoundingMode.UP);
            buildMonthlyRepay(paymentScheduleEntryList,
                    loanAccountId,
                    loanAmount,
                    totalPeriod,
                    dayRate,
                    monthlyPrincipal,
                    scale,
                    FeeItemEnum.DK_GLF,
                    factLoanDate);
        }
        //利息
        if (feeItemMap.containsKey(FeeItemEnum.DK_LX)) {
            BigDecimal yearRate = new BigDecimal(
                    feeItemMap.get(FeeItemEnum.DK_LX));
            BigDecimal dayRate = yearRate.divide(new BigDecimal(360),
                    8,
                    RoundingMode.UP);
            buildMonthlyRepay(paymentScheduleEntryList,
                    loanAccountId,
                    loanAmount,
                    totalPeriod,
                    dayRate,
                    monthlyPrincipal,
                    scale,
                    FeeItemEnum.DK_LX,
                    factLoanDate);
        }
        buildBJ(paymentScheduleEntryList,
                loanAmount,
                loanAccountId,
                monthlyPrincipal,
                totalPeriod);
        return paymentScheduleEntryList;
    }
    
    /**
     * 构建每月本金<br/>
     * <功能详细描述>
     * @param paymentScheduleEntryList
     * @param loanAccount
     * @param monthlyPI
     * @param lxMap [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    private void buildBJ(List<PaymentScheduleEntry> paymentScheduleEntryList,
            BigDecimal loanAmount, String loanAccountId,
            BigDecimal monthlyPrincipal, int totalPeriod) {
        //贷款账户相关信息
        //费用项
        FeeItemEnum feeItem = FeeItemEnum.DK_BJ;
        BigDecimal principalBalanceSum = BigDecimal.ZERO;
        
        for (int period = 1; period < totalPeriod; period++) {
            //平息应收//应收金额:
            BigDecimal receiveAbleAmount = monthlyPrincipal;
            //递减本机之和
            principalBalanceSum = principalBalanceSum.add(receiveAbleAmount);
            String prePeriod = period == 1 ? (String) null : "" + (period - 1);
            String nextPeriod = period == totalPeriod ? null
                    : "" + (period + 1);
            //创建还款分项目
            createPaymentScheduleEntry(paymentScheduleEntryList,
                    loanAccountId,
                    ScheduleTypeEnum.MAIN,
                    feeItem,
                    prePeriod,
                    "" + period,
                    nextPeriod,
                    receiveAbleAmount);
        }
        //创建还款分项目
        createPaymentScheduleEntry(paymentScheduleEntryList,
                loanAccountId,
                ScheduleTypeEnum.MAIN,
                feeItem,
                (totalPeriod - 1) == 0 ? (String) null : "" + (totalPeriod - 1),
                "" + totalPeriod,
                null,
                loanAmount.subtract(principalBalanceSum));
    }
    
    /**
     * 构建利息还款计划<br/>
     * <功能详细描述>
     * @param paymentScheduleEntryList
     * @param loanAccount
     * @param monthlyRate
     * @param monthlyPI
     * @param totalSum [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    private void buildMonthlyRepay(
            List<PaymentScheduleEntry> paymentScheduleEntryList,
            String loanAccountId, BigDecimal loanAmount, int totalPeriod,
            BigDecimal dayRate, BigDecimal monthlyPrincipal, int scale,
            FeeItemEnum feeItem, DateTime factLoanDate) {
        //本金结余
        BigDecimal principalBalance = loanAmount;
        //构建各期的利息
        for (int period = 1; period <= totalPeriod; period++) {
            //计算递减的费用项//应收递减金额  递减保留2位，向上取整
            int day = getMonthDays(factLoanDate.plusMonths(period - 1));
            //月利率
            BigDecimal monthRate = dayRate.multiply(new BigDecimal(day));
            BigDecimal receiveAbleAmount = principalBalance.multiply(monthRate)
                    .setScale(scale, BigDecimal.ROUND_UP);
            principalBalance = principalBalance.subtract(monthlyPrincipal);//计算本金结余
            String prePeriod = period == 1 ? (String) null : "" + (period - 1);
            String nextPeriod = period == totalPeriod ? null
                    : "" + (period + 1);
            //创建还款分项目
            createPaymentScheduleEntry(paymentScheduleEntryList,
                    loanAccountId,
                    ScheduleTypeEnum.MAIN,
                    feeItem,
                    prePeriod,
                    "" + period,
                    nextPeriod,
                    receiveAbleAmount);
        }
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
