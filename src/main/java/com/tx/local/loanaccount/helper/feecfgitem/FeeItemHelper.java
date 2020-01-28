/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2017年11月15日
 * <修改描述:>
 */
package com.tx.local.loanaccount.helper.feecfgitem;

import java.math.BigDecimal;

import com.tx.core.exceptions.util.AssertUtils;

/**
  * 费用项目辅助类<br/>
  * <功能详细描述>
  * 
  * @author  Administrator
  * @version  [版本号, 2017年11月15日]
  * @see  [相关类/方法]
  * @since  [产品/模块版本]
  */
public abstract class FeeItemHelper {
    
    /**
     * 获取递减月利率<br/>
     * <功能详细描述>
     * @param loanAmount
     * @param monthlyRate
     * @param totalPeriod
     * @return [参数说明]
     * 
     * @return BigDecimal [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public static BigDecimal caculateMonthlyRateIrr(double loanAmount, double monthlyRate, int totalPeriod) {
        AssertUtils.isTrue(loanAmount >= 0, "loanAmont < 0");
        AssertUtils.isTrue(monthlyRate >= 0, "monthlyInterestRate < 0");
        AssertUtils.isTrue(totalPeriod > 0, "totalPeriod <= 0");
        
        double precision = 0.00000001;
        //最小的递减月利率
        double minMonthlyInterestRateIrr = monthlyRate;
        //最大的递减月利率
        double maxMonthlyInterestRateIrr = monthlyRate + ((double) 1 / totalPeriod);
        //平息每月还款额
        double monthlyRepaySum = loanAmount / totalPeriod + loanAmount * monthlyRate;
        
        //递减月利率
        double monthlyInterestRateIrr = (minMonthlyInterestRateIrr + maxMonthlyInterestRateIrr) / 2;
        double principalBalanceIrr = loanAmount;
        
        //利息的精度值达到预设，并且利息最好能偏大一些，本金结余小于0说明利息低了前面收的本金多了
        while (Math.abs(principalBalanceIrr) > precision || principalBalanceIrr < 0) {
            if (principalBalanceIrr > 0) {
                //如果最后本金结余 > 0说明利息收多了
                maxMonthlyInterestRateIrr = monthlyInterestRateIrr;
            } else {
                //利息低了
                minMonthlyInterestRateIrr = monthlyInterestRateIrr;
            }
            monthlyInterestRateIrr = (minMonthlyInterestRateIrr + maxMonthlyInterestRateIrr) / 2;
            
            //计算倒数第
            //上一期的本金结余
            principalBalanceIrr = loanAmount;
            for (int i = 0; i < totalPeriod; i++) {
                //递减本金结余 = 递减本金结余 - (每月还款额 - 递减本金结余 * 递减月利率)
                principalBalanceIrr = principalBalanceIrr
                        - (monthlyRepaySum - principalBalanceIrr * monthlyInterestRateIrr);
                if (principalBalanceIrr < 0) {
                    break;
                }
            }
        }
        
        //利息多收一些
        BigDecimal monthlyInterestIrr = new BigDecimal(monthlyInterestRateIrr).setScale(8, BigDecimal.ROUND_UP);
        return monthlyInterestIrr;
    }
    
    public static void main(String[] args) {
        System.out.println(FeeItemHelper.caculateMonthlyRateIrr(100000, 0.07 / 12, 36));
        System.out.println(FeeItemHelper.caculateMonthlyRateIrr(100000, 0.07 / 12, 36).multiply(new BigDecimal(12)));
        
        System.out.println(FeeItemHelper.caculateMonthlyRateIrr(100000, 0.08 / 12, 36));
        System.out.println(FeeItemHelper.caculateMonthlyRateIrr(100000, 0.08 / 12, 36).multiply(new BigDecimal(12)));
    }
}
