/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2014年5月28日
 * <修改描述:>
 */
package com.tx.local.loanaccount.helper.paymentschedule;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.tx.core.exceptions.util.AssertUtils;
import com.tx.local.basicdata.model.FeeItemEnum;
import com.tx.local.loanaccount.LoanAccountConstants;
import com.tx.local.loanaccount.model.PaymentScheduleEntry;

/**
 * 还款计划构建帮助类<br/>
 * 
 * @author  Administrator
 * @version  [版本号, 2014年5月28日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class PaymentScheduleEntryBuildHelper {
    
    //private static LoanAccount loanAccount;
    private String loanAccountId;
    
    private BigDecimal loanAmount = new BigDecimal("10000");
    
    private int totalPeriod = 12;
    
    /** 递减月利率 */
    private BigDecimal monthlyInterestIrr = BigDecimal.ZERO;
    
    /** 还款计划分项 */
    private List<PaymentScheduleEntry> paymentScheduleEntryList = new ArrayList<>();
    
    @SuppressWarnings("unused")
    private static double decreasingInterestRates(int factTimeLimit, double pmt,
            double pv) {
        double i = 0.0;
        double inovo = 0.01;
        //精度修改为：00000001 原来是：00001 robin 
        while (Math.abs(inovo - i) > 0.00000001) {
            i = inovo;
            
            double f = (1 - (Math.pow((1 + i), (-factTimeLimit)))) * pmt / i
                    - pv;
            double df = pmt * (factTimeLimit
                    * (Math.pow((1 + i), (-factTimeLimit - 1))) / i
                    - (1 - (Math.pow((1 + i), (-factTimeLimit)))) / (i * i));
            
            inovo = i - (f / df);
            
        }
        
        return i;
    }
    
    /**
     * 获取贷款账户递减月利息 
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return BigDecimal [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
    public static BigDecimal getMonthlyInterestIrrRate(
            BigDecimal loanAmountBigDecimal, double monthlyInterestRate,
            int totalPeriod, BigDecimal monthlyInterestIrr) {
        double loanAmount = loanAmountBigDecimal.doubleValue();
        AssertUtils.isTrue(loanAmount >= 0, "loanAmont < 0");
        AssertUtils.isTrue(monthlyInterestRate >= 0, "monthlyInterestRate < 0");
        AssertUtils.isTrue(totalPeriod > 0, "totalPeriod <= 0");
        
        double precision = 0.00000001;
        //递减月利息如果已经存在
        if (monthlyInterestIrr != null
                && BigDecimal.ZERO.compareTo(monthlyInterestIrr) != 0) {
            return monthlyInterestIrr;
        }
        
        //最小的递减月利率
        double minMonthlyInterestRateIrr = monthlyInterestRate;
        //最大的递减月利率
        double maxMonthlyInterestRateIrr = monthlyInterestRate
                + ((double) 1 / totalPeriod);
        //平息每月还款额
        double monthlyRepaySum = loanAmount / totalPeriod
                + loanAmount * monthlyInterestRate;
        
        @SuppressWarnings("unused")
        double preMonthlyInterestRateIrr = minMonthlyInterestRateIrr;
        //递减月利率
        double monthlyInterestRateIrr = (minMonthlyInterestRateIrr
                + maxMonthlyInterestRateIrr) / 2;
        double principalBalanceIrr = 0;
        do {
            //计算倒数第
            //上一期的本金结余
            principalBalanceIrr = loanAmount;
            for (int i = 0; i < totalPeriod; i++) {
                //递减本金结余 = 递减本金结余 - (每月还款额 - 递减本金结余 * 递减月利率)
                principalBalanceIrr = principalBalanceIrr - (monthlyRepaySum
                        - principalBalanceIrr * monthlyInterestRateIrr);
                if (principalBalanceIrr < 0) {
                    break;
                }
            }
            
            preMonthlyInterestRateIrr = monthlyInterestRateIrr;
            if (principalBalanceIrr > 0) {
                //如果最后本金结余 > 0说明利息收多了
                maxMonthlyInterestRateIrr = monthlyInterestRateIrr;
            } else {
                //利息低了
                minMonthlyInterestRateIrr = monthlyInterestRateIrr;
            }
            monthlyInterestRateIrr = (minMonthlyInterestRateIrr
                    + maxMonthlyInterestRateIrr) / 2;
            //System.out.println("monthlyInterestRateIrr:" + monthlyInterestRateIrr + " principalBalanceIrr:" + principalBalanceIrr);
            //前后两个递减利率之间的精度只差小于设定值
        } while (principalBalanceIrr < 0 || principalBalanceIrr > precision);
        
        BigDecimal bigDecimal = new BigDecimal(monthlyInterestRateIrr)
                .setScale(4, BigDecimal.ROUND_DOWN);
        monthlyInterestIrr = bigDecimal;
        return bigDecimal;
    }
    
    /**
      *生成还款计划分项<br/>
      *<功能详细描述>
      * @param loanAccountId
      * @param feeItem
      * @param prePeriod
      * @param period
      * @param nextPeriod
      * @param receivableAmount
      * @param receivableAmountIrr [参数说明]
      * 
      * @return void [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    private static void createPaymentScheduleEntry(
            List paymentScheduleEntryList, String loanAccountId,
            FeeItemEnum feeItem, String prePeriod, String period,
            String nextPeriod, BigDecimal receivableAmount,
            BigDecimal receivableAmountIrr) {
        PaymentScheduleEntry pse = new PaymentScheduleEntry();
        pse.setReceivableAmount(receivableAmount);
        pse.setLoanAccountId(loanAccountId);
        pse.setPeriod(period);
        pse.setNextPeriod(nextPeriod);
        pse.setPrePeriod(prePeriod);
        pse.setFeeItem(feeItem);
        
        paymentScheduleEntryList.add(pse);
    }
    
    /**
      *管理费<br/>
      *<功能详细描述>
      * @param param [参数说明]
      * 
      * @return void [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public void managementFee(Map<String, Object> param) {
        //费用项
        FeeItemEnum feeItem = (FeeItemEnum) param.get("feeItem");
        //费率值
        String value = (String) param.get("value");
        //获取管理费平息费率
        BigDecimal managementFeeRate = new BigDecimal(value);
        //管理费递减费率与平息费率一致
        BigDecimal feeRateIrr = new BigDecimal(value);
        for (int period = 1; period < totalPeriod + 1; period++) {
            //平息应收//应收金额:
            //贷款金额  * 平息管理费费率  向上取整
            BigDecimal receiveAbleAmount = loanAmount
                    .multiply(managementFeeRate)
                    .setScale(0, BigDecimal.ROUND_UP);
            //计算递减的费用项//应收递减金额
            //贷款金额  * 递减管理费费率（实际值和平息值是一样的）  向上取整
            BigDecimal receiveAbleAmountIrr = loanAmount.multiply(feeRateIrr)
                    .setScale(0, BigDecimal.ROUND_UP);
            String prePeriod = period == 1 ? (String) null : "" + (period - 1);
            String nextPeriod = period == totalPeriod ? null
                    : "" + (period + 1);
            //创建还款分项目
            createPaymentScheduleEntry(paymentScheduleEntryList,
                    loanAccountId,
                    feeItem,
                    prePeriod,
                    "" + period,
                    nextPeriod,
                    receiveAbleAmount,
                    receiveAbleAmountIrr);
        }
    }
    
    /**
      *月利息<br/>
      *<功能详细描述>
      * @param param [参数说明]
      * 
      * @return void [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public void monthlyInterest(Map<String, Object> param) {
        //费用项
        FeeItemEnum feeItem = (FeeItemEnum) param.get("feeItem");
        //费率值
        String value = (String) param.get("value");
        //获取平息费率
        BigDecimal monthlyInterestRate = new BigDecimal(value);
        //获取递减利率
        monthlyInterestIrr = getMonthlyInterestIrrRate(loanAmount,
                monthlyInterestRate.doubleValue(),
                totalPeriod,
                monthlyInterestIrr);
        //平息每月归还本金//向上取整
        BigDecimal monthlyPrincipal = loanAmount
                .divide(new BigDecimal(totalPeriod), 2)
                .setScale(0, BigDecimal.ROUND_UP);
        //平息每月利率
        BigDecimal monthlyInterest = loanAmount.multiply(monthlyInterestRate)
                .setScale(0, BigDecimal.ROUND_UP);
        //每月本金和月利率之和
        BigDecimal monthlyPriAndInt = monthlyPrincipal.add(monthlyInterest);
        //递减本金结余
        BigDecimal principalBalanceIrr = loanAmount;
        BigDecimal interestIrrSum = BigDecimal.ZERO;
        
        for (int period = 1; period < totalPeriod; period++) {
            //平息应收//应收金额:
            BigDecimal receiveAbleAmount = monthlyInterest;
            //计算递减的费用项//应收递减金额  递减保留2位，向上取整
            BigDecimal currentMonthInterestIrr = principalBalanceIrr
                    .multiply(monthlyInterestIrr)
                    .setScale(2, BigDecimal.ROUND_UP);
            //应收金额递减
            BigDecimal receiveAbleAmountIrr = currentMonthInterestIrr;
            //平息本金与利率之和 - 当月的月递减利率 = 当递减本金
            BigDecimal principalIrr = monthlyPriAndInt
                    .subtract(currentMonthInterestIrr);
            interestIrrSum = interestIrrSum.add(currentMonthInterestIrr);
            //计算递减本金结余
            principalBalanceIrr = principalBalanceIrr.subtract(principalIrr);
            String prePeriod = period == 1 ? (String) null : "" + (period - 1);
            String nextPeriod = period == totalPeriod ? null
                    : "" + (period + 1);
            
            //创建还款分项目
            createPaymentScheduleEntry(paymentScheduleEntryList,
                    loanAccountId,
                    feeItem,
                    prePeriod,
                    "" + period,
                    nextPeriod,
                    receiveAbleAmount,
                    receiveAbleAmountIrr);
        }
        
        //最后一期的利息递减 为平息总利息 - 递减以上各期之和
        BigDecimal lastInterestIrr = monthlyInterest
                .multiply(new BigDecimal(totalPeriod)).subtract(interestIrrSum);
        //创建还款分项目
        createPaymentScheduleEntry(paymentScheduleEntryList,
                loanAccountId,
                feeItem,
                "" + (totalPeriod - 1),
                "" + totalPeriod,
                null,
                monthlyInterest,
                lastInterestIrr);
    }
    
    /**
      * 本金<br/>
      *<功能详细描述>
      * @param param [参数说明]
      * 
      * @return void [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public void principal(Map<String, Object> param,
            Map<String, Object> monthlyInterestParam) {
        //费用项
        FeeItemEnum feeItem = (FeeItemEnum) param.get("feeItem");
        //递减费率
        BigDecimal monthlyInterestRate = new BigDecimal(
                (String) monthlyInterestParam.get("value"));
        //获取递减利率
        monthlyInterestIrr = getMonthlyInterestIrrRate(loanAmount,
                monthlyInterestRate.doubleValue(),
                totalPeriod,
                monthlyInterestIrr);
        
        //平息每月归还本金//向上取整
        BigDecimal monthlyPrincipal = loanAmount
                .divide(new BigDecimal(totalPeriod), 2)
                .setScale(0, BigDecimal.ROUND_UP);
        //平息每月利率
        BigDecimal monthlyInterest = loanAmount.multiply(monthlyInterestRate)
                .setScale(0, BigDecimal.ROUND_UP);
        //每月本金和月利率之和
        BigDecimal monthlyPriAndInt = monthlyPrincipal.add(monthlyInterest);
        //递减本金结余
        BigDecimal principalBalanceIrr = loanAmount;
        BigDecimal principalBalance = loanAmount;
        BigDecimal principalIrrSum = BigDecimal.ZERO;
        
        for (int period = 1; period < totalPeriod; period++) {
            //平息应收//应收金额:
            BigDecimal receiveAbleAmount = monthlyPrincipal;
            //计算递减的费用项//应收递减金额  递减保留2位，向上取整
            BigDecimal currentMonthInterestIrr = principalBalanceIrr
                    .multiply(monthlyInterestIrr)
                    .setScale(2, BigDecimal.ROUND_UP);
            //平息本金与利率之和 - 当月的月递减利率 = 当递减本金
            BigDecimal principalIrr = monthlyPriAndInt
                    .subtract(currentMonthInterestIrr);
            //应收金额递减
            BigDecimal receiveAbleAmountIrr = principalIrr;
            //递减本机之和
            principalIrrSum = principalIrrSum.add(principalIrr);
            //计算递减本金结余
            principalBalanceIrr = principalBalanceIrr.subtract(principalIrr);
            principalBalance = principalBalance.subtract(monthlyPrincipal);
            
            String prePeriod = period == 1 ? (String) null : "" + (period - 1);
            String nextPeriod = period == totalPeriod ? null
                    : "" + (period + 1);
            
            //创建还款分项目
            createPaymentScheduleEntry(paymentScheduleEntryList,
                    loanAccountId,
                    feeItem,
                    prePeriod,
                    "" + period,
                    nextPeriod,
                    receiveAbleAmount,
                    receiveAbleAmountIrr);
        }
        
        //最后一期的利息递减 为平息总利息 - 递减以上各期之和
        BigDecimal lastPrincipalIrr = loanAmount.subtract(principalIrrSum);
        AssertUtils.isTrue(lastPrincipalIrr.compareTo(principalBalanceIrr) == 0,
                "lastPrincipalIrr:{} not equal principalBalanceIrr:{}",
                new Object[] { lastPrincipalIrr, principalBalanceIrr });
        BigDecimal lastPrincipal = loanAmount.subtract(
                monthlyPrincipal.multiply(new BigDecimal(totalPeriod - 1)));
        AssertUtils.isTrue(principalBalance.compareTo(lastPrincipal) == 0,
                "lastPrincipal:{} not equal principalBalance:{}",
                new Object[] { lastPrincipal, principalBalance });
        
        //创建还款分项目
        createPaymentScheduleEntry(paymentScheduleEntryList,
                loanAccountId,
                feeItem,
                "" + (totalPeriod - 1),
                "" + totalPeriod,
                null,
                principalBalance,
                principalBalanceIrr);
    }
    
    /** 
     *一次性手续费 NA<br/>
     *<功能详细描述>
     * @param managementFeeCfgItem [参数说明]
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public void disposableCharge(Map<String, Object> param) {
        //费用项
        FeeItemEnum feeItem = (FeeItemEnum) param.get("feeItem");
        //创建还款分项目
        createPaymentScheduleEntry(paymentScheduleEntryList,
                loanAccountId,
                feeItem,
                null,
                LoanAccountConstants.PERIOD_NA,
                null,
                BigDecimal.ZERO,
                BigDecimal.ZERO);
    }
    
    /** 
     *贷前延期费 NA <br/>
     *<功能详细描述> [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public void preLoanCharge(Map<String, Object> param, int days) {
        //费用项
        FeeItemEnum feeItem = (FeeItemEnum) param.get("feeItem");
        
        createPaymentScheduleEntry(paymentScheduleEntryList,
                loanAccountId,
                feeItem,
                null,
                LoanAccountConstants.PERIOD_NA,
                null,
                BigDecimal.ZERO,
                BigDecimal.ZERO);
    }
    
    /** 
     *贷后延期费 NA <br/>
     *<功能详细描述> [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public void nextLoanCharge(Map<String, Object> param, int days) {
        //费用项
        FeeItemEnum feeItem = (FeeItemEnum) param.get("feeItem");
        
        createPaymentScheduleEntry(paymentScheduleEntryList,
                loanAccountId,
                feeItem,
                null,
                LoanAccountConstants.PERIOD_NA,
                null,
                BigDecimal.ZERO,
                BigDecimal.ZERO);
    }
    
    /** 
     *退款手续费<br/>
     *<功能详细描述>
     * @param param [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public void refundCharge(Map<String, Object> param) {
        //费用项
        FeeItemEnum feeItem = (FeeItemEnum) param.get("feeItem");
        //费率值
        //String value = (String) param.get("value");
        createPaymentScheduleEntry(paymentScheduleEntryList,
                loanAccountId,
                feeItem,
                null,
                LoanAccountConstants.PERIOD_NA,
                null,
                BigDecimal.ZERO,
                BigDecimal.ZERO);
    }
    
    /** 
     *逾期利息<br/>
     *<功能详细描述>
     * @param param [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public void overdueCharge(Map<String, Object> param) {
        //费用项
        FeeItemEnum feeItem = (FeeItemEnum) param.get("feeItem");
        
        createPaymentScheduleEntry(paymentScheduleEntryList,
                loanAccountId,
                feeItem,
                null,
                LoanAccountConstants.PERIOD_NA,
                null,
                BigDecimal.ZERO,
                BigDecimal.ZERO);
        
    }
    
    /** 
     *提前还款违约金<br/>
     *<功能详细描述>
     * @param param [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public void repaymentCharge(Map<String, Object> param) {
        //费用项
        FeeItemEnum feeItem = (FeeItemEnum) param.get("feeItem");
        
        createPaymentScheduleEntry(paymentScheduleEntryList,
                loanAccountId,
                feeItem,
                null,
                LoanAccountConstants.PERIOD_NA,
                null,
                BigDecimal.ZERO,
                BigDecimal.ZERO);
    }
    
    /**
      * 外包佣金<br/>
      *<功能详细描述>
      * @param param [参数说明]
      * 
      * @return void [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public void outSourceFee(Map<String, Object> param) {
        //费用项
        FeeItemEnum feeItem = (FeeItemEnum) param.get("feeItem");
        
        createPaymentScheduleEntry(paymentScheduleEntryList,
                loanAccountId,
                feeItem,
                null,
                LoanAccountConstants.PERIOD_NA,
                null,
                BigDecimal.ZERO,
                BigDecimal.ZERO);
    }
    
    /**
     * 超额还款<br/>
     *<功能详细描述>
     * @param param [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
    public void receivedFee(Map<String, Object> param) {
        //费用项
        FeeItemEnum feeItem = (FeeItemEnum) param.get("feeItem");
        
        createPaymentScheduleEntry(paymentScheduleEntryList,
                loanAccountId,
                feeItem,
                null,
                LoanAccountConstants.PERIOD_NA,
                null,
                BigDecimal.ZERO,
                BigDecimal.ZERO);
    }
    
    /**
     * 坏账回收<br/>
     *<功能详细描述>
     * @param param [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
    public void badLoanFee(Map<String, Object> param) {
        //费用项
        FeeItemEnum feeItem = (FeeItemEnum) param.get("feeItem");
        
        createPaymentScheduleEntry(paymentScheduleEntryList,
                loanAccountId,
                feeItem,
                null,
                LoanAccountConstants.PERIOD_NA,
                null,
                BigDecimal.ZERO,
                BigDecimal.ZERO);
    }
    
    /**
     * 应收账款<br/>
     *<功能详细描述>
     * @param param [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
    public void accountsReceivableFee(Map<String, Object> param) {
        //费用项
        FeeItemEnum feeItem = (FeeItemEnum) param.get("feeItem");
        
        createPaymentScheduleEntry(paymentScheduleEntryList,
                loanAccountId,
                feeItem,
                null,
                LoanAccountConstants.PERIOD_NA,
                null,
                BigDecimal.ZERO,
                BigDecimal.ZERO);
    }
    
    /**
     * 营业外收入<br/>
     *<功能详细描述>
     * @param param [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
    public void nonbusinessIncome(Map<String, Object> param) {
        //费用项
        FeeItemEnum feeItem = (FeeItemEnum) param.get("feeItem");
        
        createPaymentScheduleEntry(paymentScheduleEntryList,
                loanAccountId,
                feeItem,
                null,
                LoanAccountConstants.PERIOD_NA,
                null,
                BigDecimal.ZERO,
                BigDecimal.ZERO);
    }
    
    /**
     * 扣款失败手续费<br/>
     *<功能详细描述>
     * @param param [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public void withholdBadFee(Map<String, Object> param) {
        //费用项
        FeeItemEnum feeItem = (FeeItemEnum) param.get("feeItem");
        
        createPaymentScheduleEntry(paymentScheduleEntryList,
                loanAccountId,
                feeItem,
                null,
                LoanAccountConstants.PERIOD_NA,
                null,
                BigDecimal.ZERO,
                BigDecimal.ZERO);
    }
    
}
