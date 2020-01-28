/*
· * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2017年5月31日
 * <修改描述:>
 */
package com.tx.local.loanaccount.strategy.build;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import com.tx.core.exceptions.util.AssertUtils;
import com.tx.local.basicdata.model.FeeItemEnum;
import com.tx.local.basicdata.model.RepayWayEnum;
import com.tx.local.loanaccount.model.LoanAccount;
import com.tx.local.loanaccount.model.LoanAccountFeeItem;
import com.tx.local.loanaccount.model.PaymentScheduleEntry;
import com.tx.local.loanaccount.model.ScheduleTypeEnum;

/**
 * 融资租赁定制的还款计划<br/>
 * 等额本息还款法:
 *     每月月供额=〔贷款本金×月利率×(1＋月利率)＾还款月数〕÷〔(1＋月利率)＾还款月数-1〕
 *     每月应还利息=贷款本金×月利率×〔(1+月利率)^还款月数-(1+月利率)^(还款月序号-1)〕÷〔(1+月利率)^还款月数-1〕
 *     每月应还本金=贷款本金×月利率×(1+月利率)^(还款月序号-1)÷〔(1+月利率)^还款月数-1〕
 *     总利息=还款月数×每月月供额-贷款本金
 *     1等额本息还款法即把按揭贷款的本金总额与利息总额相加，然后平均分摊到还款期限的每个月中，每个月的还款额是固定的，
 *     但每月还款额中的本金比重逐月递增、利息比重逐月递减。这种方法是目前最为普遍，也是大部分银行长期推荐的方式。
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2017年5月31日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public abstract class AbstractPaymentScheduleEntryBuilder_ROUND_UP
        implements PaymentScheduleEntryBuilder {
    
    /**
     * 计算贷前收取费用<br/>
     * 
     * @param feeItemMap
     * @param params
     * @param scale
     * @return
     */
    @Override
    public Map<FeeItemEnum, BigDecimal> caculateFeeItemAmountMap(
            BigDecimal loanAmount, int totalPeriod,
            Map<FeeItemEnum, String> feeItemMap, Map<String, Object> params,
            int scale, RepayWayEnum repayWay) {
        AssertUtils.notNull(loanAmount, "loanAmount is null.");
        AssertUtils.isTrue(totalPeriod > 0, "totalPeriod should > 0.");
        
        Map<FeeItemEnum, BigDecimal> feeItemAmountMap = new HashMap<>();
        //一次性手续费
        if (feeItemMap.containsKey(FeeItemEnum.DK_YCXSXF)
                && NumberUtils.isParsable(feeItemMap.get(FeeItemEnum.DK_YCXSXF))
                && NumberUtils
                        .toDouble(feeItemMap.get(FeeItemEnum.DK_YCXSXF)) > 0) {
            feeItemAmountMap.put(FeeItemEnum.DK_YCXSXF,
                    doCaculateFeeItemAmountByRate(loanAmount,
                            FeeItemEnum.DK_YCXSXF,
                            feeItemMap.get(FeeItemEnum.DK_YCXSXF),
                            scale));
        }
        if (feeItemMap.containsKey(FeeItemEnum.ZX_YCXSXF)
                && NumberUtils.isParsable(feeItemMap.get(FeeItemEnum.ZX_YCXSXF))
                && NumberUtils
                        .toDouble(feeItemMap.get(FeeItemEnum.ZX_YCXSXF)) > 0) {
            feeItemAmountMap.put(FeeItemEnum.ZX_YCXSXF,
                    doCaculateFeeItemAmountByRate(loanAmount,
                            FeeItemEnum.ZX_YCXSXF,
                            feeItemMap.get(FeeItemEnum.ZX_YCXSXF),
                            scale));
        }
        //保证金
        if (feeItemMap.containsKey(FeeItemEnum.DK_BZJ)
                && NumberUtils.isParsable(feeItemMap.get(FeeItemEnum.DK_BZJ))
                && NumberUtils
                        .toDouble(feeItemMap.get(FeeItemEnum.DK_BZJ)) > 0) {
            feeItemAmountMap.put(FeeItemEnum.DK_BZJ,
                    doCaculateFeeItemAmountByRate(loanAmount,
                            FeeItemEnum.DK_BZJ,
                            feeItemMap.get(FeeItemEnum.DK_BZJ),
                            scale));
        }
        if (feeItemMap.containsKey(FeeItemEnum.ZX_BZJ)
                && NumberUtils.isParsable(feeItemMap.get(FeeItemEnum.ZX_BZJ))
                && NumberUtils
                        .toDouble(feeItemMap.get(FeeItemEnum.ZX_BZJ)) > 0) {
            feeItemAmountMap.put(FeeItemEnum.ZX_BZJ,
                    doCaculateFeeItemAmountByRate(loanAmount,
                            FeeItemEnum.ZX_BZJ,
                            feeItemMap.get(FeeItemEnum.ZX_BZJ),
                            scale));
        }
        //如果存在考察费、贷前展期费等场景，由于差异较大需要通过实现接口定制增加不能提供通用逻辑<br/>
        return feeItemAmountMap;
    }
    
    /**
     * 计算一次性手续费
     * <功能详细描述>
     * @param loanAmount
     * @param feeItem
     * @param rate
     * @param scale
     * @return [参数说明]
     * 
     * @return BigDecimal [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    protected final BigDecimal doCaculateFeeItemAmountByRate(
            BigDecimal loanAmount, FeeItemEnum feeItem, String rate,
            int scale) {
        AssertUtils.notNull(loanAmount, "loanAmount is null");
        AssertUtils.notNull(feeItem, "feeItem is null");
        AssertUtils.isTrue(NumberUtils.isParsable(rate), "rate is not number.");
        
        BigDecimal amount = loanAmount.multiply(new BigDecimal(rate))
                .setScale(scale, RoundingMode.UP);
        return amount;
    }
    
    /**
     * @param feeItemMap
     * @param params
     * @param scale
     * @return
     */
    @Override
    public BigDecimal caculateFactLoanAmount(BigDecimal loanAmount,
            int totalPeriod, Map<FeeItemEnum, String> feeItemMap,
            Map<String, Object> params, int scale, RepayWayEnum repayWay) {
        AssertUtils.notNull(loanAmount, "loanAmount is null.");
        AssertUtils.isTrue(totalPeriod > 0, "totalPeriod should > 0.");
        
        //计算费用项金额映射
        Map<FeeItemEnum, BigDecimal> feeItemAmountMap = caculateFeeItemAmountMap(
                loanAmount, totalPeriod, feeItemMap, params, scale, repayWay);
        
        //计算实际放款金额
        BigDecimal factLoanAmount = loanAmount;
        for (BigDecimal amountTemp : feeItemAmountMap.values()) {
            factLoanAmount = factLoanAmount.subtract(amountTemp);
        }
        
        return factLoanAmount;
    }
    
    /**
     * @param loanAmount
     * @param totalPeriod
     * @param feeItemMap
     * @param params
     * @param scale
     * @return
     */
    @Override
    public BigDecimal caculateMonthlyRepayAmount(BigDecimal loanAmount,
            int totalPeriod, Map<FeeItemEnum, String> feeItemMap,
            Map<String, Object> params, int scale, RepayWayEnum repayWay) {
        BigDecimal monthlyRepay = doCaculateMonthlyRepayAmount(loanAmount,
                totalPeriod,
                feeItemMap,
                params,
                scale);
        return monthlyRepay;
    }
    
    /**
     * 计算每月还款额<br/>
     * <功能详细描述>
     * @param loanAmount
     * @param totalPeriod
     * @param feeItemMap
     * @param params
     * @param scale
     * @return [参数说明]
     * 
     * @return BigDecimal [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public final BigDecimal doCaculateMonthlyRepayAmount(BigDecimal loanAmount,
            int totalPeriod, Map<FeeItemEnum, String> feeItemMap,
            Map<String, Object> params, int scale) {
        AssertUtils.notNull(loanAmount, "loanAmount is null.");
        AssertUtils.isTrue(totalPeriod > 0, "totalPeriod should > 0.");
        
        //每月还本金
        BigDecimal monthlyPrincipal = loanAmount
                .divide(new BigDecimal(totalPeriod), 4, BigDecimal.ROUND_UP);
        
        //每月还利息
        BigDecimal dk_lx_amount = BigDecimal.ZERO;
        if (StringUtils.isNotEmpty(feeItemMap.get(FeeItemEnum.DK_LX))
                && NumberUtils.isParsable(feeItemMap.get(FeeItemEnum.DK_LX))) {
            BigDecimal lxRate = (new BigDecimal(
                    feeItemMap.get(FeeItemEnum.DK_LX)))
                            .divide(new BigDecimal(12), 8, RoundingMode.UP);
            dk_lx_amount = loanAmount.multiply(lxRate).setScale(4,
                    BigDecimal.ROUND_UP);//平息每月利率
        }
        //每月还管理费_咨询
        BigDecimal zx_glf_amount = BigDecimal.ZERO;
        if (StringUtils.isNotEmpty(feeItemMap.get(FeeItemEnum.ZX_GLF))
                && NumberUtils.isParsable(feeItemMap.get(FeeItemEnum.ZX_GLF))) {
            BigDecimal rate = (new BigDecimal(
                    feeItemMap.get(FeeItemEnum.ZX_GLF)))
                            .divide(new BigDecimal(12), 8, RoundingMode.UP);
            dk_lx_amount = loanAmount.multiply(rate).setScale(4,
                    BigDecimal.ROUND_UP);//平息每月利率
        }
        //每月还管理费_管理费
        BigDecimal dk_glf_amount = BigDecimal.ZERO;
        if (StringUtils.isNotEmpty(feeItemMap.get(FeeItemEnum.DK_GLF))
                && NumberUtils.isParsable(feeItemMap.get(FeeItemEnum.DK_GLF))) {
            BigDecimal rate = (new BigDecimal(
                    feeItemMap.get(FeeItemEnum.DK_GLF)))
                            .divide(new BigDecimal(12), 8, RoundingMode.UP);
            dk_glf_amount = loanAmount.multiply(rate).setScale(4,
                    BigDecimal.ROUND_UP);//平息每月利率
        }
        
        BigDecimal monthlyPI = monthlyPrincipal.add(dk_lx_amount)
                .add(zx_glf_amount)
                .add(dk_glf_amount)
                .setScale(scale, RoundingMode.UP);
        return monthlyPI;
    }
    
    /**
     * @param loanAccount
     * @param feeItemMap
     * @param params
     * @return
     */
    @Override
    public List<PaymentScheduleEntry> buildPaymentScheduleEntryList(
            LoanAccount loanAccount,
            Map<FeeItemEnum, LoanAccountFeeItem> loanAccountFeeItemMap,
            Map<String, Object> params, int scale, RepayWayEnum repayWay) {
        AssertUtils.notNull(loanAccount, "loanAccount is null.");
        AssertUtils.notNull(loanAccountFeeItemMap,
                "loanAccountFeeItemMap is null.");
        AssertUtils.notEmpty(loanAccount.getId(), "loanAccount.id is null.");
        AssertUtils.notNull(loanAccount.getLoanAmount(),
                "loanAccount.loanAmount is null.");
        AssertUtils.isTrue(loanAccount.getTotalPeriod() > 0,
                "loanAccount.totalPeriod should > 0.");
        
        AssertUtils.notNull(loanAccount.getFactLoanDate(),
                "loanAccount.factLoanDate is null.");
        AssertUtils.notNull(loanAccount.getInterestDate(),
                "loanAccount.interestDate is null.");
        AssertUtils.notNull(loanAccount.getFirstRepayDate(),
                "loanAccount.firstRepayDate is null.");
        AssertUtils.notNull(loanAccount.getMonthlyRepayDay() > 0,
                "loanAccount.monthlyRepayDay is null.");
        
        params = params == null ? new HashMap<>() : params;
        params.put("loanAmount", loanAccount.getLoanAmount());
        params.put("totalPeriod", loanAccount.getTotalPeriod());
        params.put("factLoanDate", loanAccount.getFactLoanDate());
        params.put("interestDate", loanAccount.getInterestDate());
        params.put("firstRepayDate", loanAccount.getFirstRepayDate());
        params.put("monthlyRepayDay", loanAccount.getMonthlyRepayDay());
        params.put("beforeDelayDays", loanAccount.getBeforeDelayDays());
        
        Map<FeeItemEnum, String> feeItemMap = new HashMap<>();
        for (Entry<FeeItemEnum, LoanAccountFeeItem> entryTemp : loanAccountFeeItemMap
                .entrySet()) {
            if (entryTemp.getValue() == null) {
                continue;
            }
            //写入费用值
            feeItemMap.put(entryTemp.getKey(), entryTemp.getValue().getValue());
        }
        
        List<PaymentScheduleEntry> resList = buildPaymentScheduleEntryList(
                loanAccount.getId(), feeItemMap, params, scale, repayWay);
        return resList;
    }
    
    /**
     * 构建每月应还<br/>
     *    适用于每月还该费用项一致的费用,比如管理费，或平息算法中的平息月利息<br/>
     * <功能详细描述>
     * @param paymentScheduleEntryList
     * @param scheduleType
     * @param feeItem
     * @param loanAccountId
     * @param loanAmount
     * @param totalPeriod
     * @param monthlyRate
     * @param totalSum
     * @param scale
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
    protected void buildMonthlyRepay(
            List<PaymentScheduleEntry> paymentScheduleEntryList,
            ScheduleTypeEnum scheduleType, FeeItemEnum feeItem,
            String loanAccountId, BigDecimal loanAmount, int totalPeriod,
            BigDecimal monthlyRate, BigDecimal totalSum, int scale) {
        AssertUtils.notNull(paymentScheduleEntryList,
                "paymentScheduleEntryList is null.");
        AssertUtils.notNull(scheduleType, "scheduleType is null.");
        AssertUtils.notNull(feeItem, "feeItem is null.");
        AssertUtils.notNull(loanAmount, "loanAmount is null.");
        AssertUtils.isTrue(totalPeriod > 0, "totalPeriod should > 0.");
        AssertUtils.notNull(monthlyRate, "monthlyRate is null.");
        
        //管理费费率
        BigDecimal sum = BigDecimal.ZERO;
        for (int period = 1; period < totalPeriod; period++) {
            //平息应收//应收金额:
            BigDecimal receiveAbleAmount = loanAmount.multiply(monthlyRate)
                    .setScale(scale, BigDecimal.ROUND_UP);
            
            sum = sum.add(receiveAbleAmount);//已填入应收总金额
            
            //期数
            String prePeriod = period == 1 ? (String) null : "" + (period - 1);
            String nextPeriod = period == totalPeriod ? null
                    : "" + (period + 1);
            //创建还款分项目
            createPaymentScheduleEntry(paymentScheduleEntryList,
                    loanAccountId,
                    scheduleType,
                    feeItem,
                    prePeriod,
                    "" + period,
                    nextPeriod,
                    receiveAbleAmount);
        }
        
        if (totalSum == null || totalSum.compareTo(BigDecimal.ZERO) <= 0
                || totalSum.compareTo(sum) < 0) {
            totalSum = loanAmount.multiply(monthlyRate)
                    .multiply(new BigDecimal(totalPeriod))
                    .setScale(scale, BigDecimal.ROUND_UP);
        }
        
        //最后一期的利息递减 为平息总利息 - 递减以上各期之和
        BigDecimal lastInterestIrr = totalSum.subtract(sum);
        //创建还款分项目
        createPaymentScheduleEntry(paymentScheduleEntryList,
                loanAccountId,
                scheduleType,
                feeItem,
                (totalPeriod - 1) == 0 ? (String) null : "" + (totalPeriod - 1),
                "" + totalPeriod,
                null,
                lastInterestIrr);
    }
    
    /**
    * 构建利息还款计划<br/>
    * <功能详细描述>
    * @param paymentScheduleEntryList
    * @param scheduleType
    * @param feeItem
    * @param loanAccountId
    * @param loanAmount
    * @param totalPeriod
    * @param monthlyRateIrr
    * @param monthlyRepay
    * @param totalSum
    * @param scale
    * 
    * @return void [返回类型说明]
    * @exception throws [异常类型] [异常说明]
    * @see [类、类#方法、类#成员]
    */
    protected void buildMonthlyRepayIrrByPrincipalBalance(
            List<PaymentScheduleEntry> paymentScheduleEntryList,
            ScheduleTypeEnum scheduleType, FeeItemEnum feeItem,
            String loanAccountId, BigDecimal loanAmount, int totalPeriod,
            BigDecimal monthlyRateIrr, BigDecimal monthlyRepay,
            BigDecimal totalSum, int scale) {
        AssertUtils.notNull(paymentScheduleEntryList,
                "paymentScheduleEntryList is null.");
        AssertUtils.notNull(feeItem, "feeItem is null.");
        AssertUtils.notNull(loanAmount, "loanAmount is null.");
        AssertUtils.notNull(monthlyRateIrr, "monthlyRateIrr is null.");
        AssertUtils.notNull(monthlyRepay, "monthlyRepay is null.");
        
        //递减本金结余
        BigDecimal principalBalance = loanAmount;
        BigDecimal sum = BigDecimal.ZERO;
        
        // 构建各期的利息
        for (int period = 1; period < totalPeriod; period++) {
            AssertUtils.isTrue(principalBalance.compareTo(BigDecimal.ZERO) >= 0,
                    "principalBalance should >= 0.");
            
            //计算递减的费用项//应收递减金额  递减保留2位，向上取整
            BigDecimal receiveAbleAmount = principalBalance
                    .multiply(monthlyRateIrr).setScale(2, BigDecimal.ROUND_UP);
            BigDecimal principalReceiveAbleAmount = monthlyRepay
                    .subtract(receiveAbleAmount);//平息本金与利率之和 - 当月的月递减利率 = 当递减本金
            
            sum = sum.add(receiveAbleAmount);
            principalBalance = principalBalance
                    .compareTo(principalReceiveAbleAmount) >= 0
                            ? principalBalance
                                    .subtract(principalReceiveAbleAmount)
                            : BigDecimal.ZERO;//计算递减本金结余
            
            String prePeriod = period == 1 ? (String) null : "" + (period - 1);
            String nextPeriod = period == totalPeriod ? null
                    : "" + (period + 1);
            
            //创建还款分项目
            createPaymentScheduleEntry(paymentScheduleEntryList,
                    loanAccountId,
                    scheduleType,
                    feeItem,
                    prePeriod,
                    "" + period,
                    nextPeriod,
                    receiveAbleAmount);
        }
        
        if (totalSum == null || totalSum.compareTo(BigDecimal.ZERO) <= 0
                || totalSum.compareTo(sum) < 0) {
            //最后一期的利息递减 为平息总利息 - 递减以上各期之和
            BigDecimal receiveAbleAmount = principalBalance
                    .multiply(monthlyRateIrr).setScale(2, BigDecimal.ROUND_UP);
            //创建还款分项目
            createPaymentScheduleEntry(paymentScheduleEntryList,
                    loanAccountId,
                    scheduleType,
                    feeItem,
                    (totalPeriod - 1) == 0 ? (String) null
                            : "" + (totalPeriod - 1),
                    "" + totalPeriod,
                    null,
                    receiveAbleAmount);
        } else {
            //最后一期的利息递减 为平息总利息 - 递减以上各期之和
            BigDecimal lastInterestIrr = totalSum.subtract(sum);
            //创建还款分项目
            createPaymentScheduleEntry(paymentScheduleEntryList,
                    loanAccountId,
                    scheduleType,
                    feeItem,
                    (totalPeriod - 1) == 0 ? (String) null
                            : "" + (totalPeriod - 1),
                    "" + totalPeriod,
                    null,
                    lastInterestIrr);
        }
    }
    
    /**
     * 构建还款计划分项实例<br/>
     * <功能详细描述>
     * @param paymentScheduleEntryList
     * @param loanAccountId
     * @param scheduleType
     * @param feeItem
     * @param prePeriod
     * @param period
     * @param nextPeriod
     * @param receivableAmount [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    protected static void createPaymentScheduleEntry(
            List<PaymentScheduleEntry> paymentScheduleEntryList,
            String loanAccountId, ScheduleTypeEnum scheduleType,
            FeeItemEnum feeItem, String prePeriod, String period,
            String nextPeriod, BigDecimal receivableAmount) {
        PaymentScheduleEntry pse = new PaymentScheduleEntry();
        
        pse.setScheduleType(scheduleType);
        pse.setLoanAccountId(loanAccountId);
        pse.setPeriod(period);
        pse.setNextPeriod(nextPeriod);
        pse.setPrePeriod(prePeriod);
        pse.setFeeItem(feeItem);
        
        pse.setReceivableAmount(receivableAmount);
        paymentScheduleEntryList.add(pse);
    }
    
    /**
     * 获取指定时间的月份有多少天<br/>
     * <功能详细描述>
     * @param date
     * @return [参数说明]
     * 
     * @return int [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    protected static int getDaysOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }
    
}
