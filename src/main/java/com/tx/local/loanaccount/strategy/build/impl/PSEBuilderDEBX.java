/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2017年5月31日
 * <修改描述:>
 */
package com.tx.local.loanaccount.strategy.build.impl;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.tx.core.exceptions.util.AssertUtils;
import com.tx.local.basicdata.model.FeeItemEnum;
import com.tx.local.basicdata.model.RepayWayEnum;
import com.tx.local.loanaccount.model.PaymentScheduleEntry;
import com.tx.local.loanaccount.model.ScheduleTypeEnum;
import com.tx.local.loanaccount.strategy.build.AbstractPaymentScheduleEntryBuilder_ROUND_UP;

/**
 * 还款计划分项构建器(等额本息)<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2017年5月31日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Component("pseBuilderDEBX")
public class PSEBuilderDEBX
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
        List<PaymentScheduleEntry> paymentScheduleEntryList = new ArrayList<>();
        // 管理费:
        if (feeItemMap.containsKey(FeeItemEnum.ZX_GLF)) {
            BigDecimal yearRate = new BigDecimal(
                    feeItemMap.get(FeeItemEnum.ZX_GLF));
            BigDecimal monthlyRate = yearRate.divide(new BigDecimal(12),
                    8,
                    RoundingMode.UP);
            BigDecimal monthGLF = loanAmount.multiply(monthlyRate,
                    MathContext.DECIMAL32);
            monthGLF = monthGLF.setScale(scale, BigDecimal.ROUND_UP);
            for (int period = 1; period <= totalPeriod; period++) {
                String prePeriod = period == 1 ? (String) null
                        : "" + (period - 1);
                String nextPeriod = period == totalPeriod ? null
                        : "" + (period + 1);
                createPaymentScheduleEntry(paymentScheduleEntryList,
                        loanAccountId,
                        ScheduleTypeEnum.MAIN,
                        FeeItemEnum.ZX_GLF,
                        prePeriod,
                        "" + period,
                        nextPeriod,
                        monthGLF);
            }
        }
        if (feeItemMap.containsKey(FeeItemEnum.DK_GLF)) {
            BigDecimal yearRate = new BigDecimal(
                    feeItemMap.get(FeeItemEnum.DK_GLF));
            BigDecimal monthlyRate = yearRate.divide(new BigDecimal(12),
                    8,
                    RoundingMode.UP);
            BigDecimal monthGLF = loanAmount.multiply(monthlyRate,
                    MathContext.DECIMAL32);
            monthGLF = monthGLF.setScale(scale, BigDecimal.ROUND_UP);
            for (int period = 1; period <= totalPeriod; period++) {
                String prePeriod = period == 1 ? (String) null
                        : "" + (period - 1);
                String nextPeriod = period == totalPeriod ? null
                        : "" + (period + 1);
                createPaymentScheduleEntry(paymentScheduleEntryList,
                        loanAccountId,
                        ScheduleTypeEnum.MAIN,
                        FeeItemEnum.DK_GLF,
                        prePeriod,
                        "" + period,
                        nextPeriod,
                        monthGLF);
            }
        }
        //利息
        if (feeItemMap.containsKey(FeeItemEnum.DK_LX)) {
            BigDecimal yearRate = new BigDecimal(
                    feeItemMap.get(FeeItemEnum.DK_LX));
            double monthlyRate = yearRate
                    .divide(new BigDecimal(12), 8, RoundingMode.UP)
                    .doubleValue();
            buildMonthlyRepay(paymentScheduleEntryList,
                    loanAccountId,
                    loanAmount,
                    totalPeriod,
                    monthlyRate,
                    scale,
                    FeeItemEnum.DK_LX);
            buildBJ(paymentScheduleEntryList,
                    loanAmount,
                    loanAccountId,
                    totalPeriod,
                    monthlyRate,
                    scale);
        }
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
            BigDecimal loanAmount, String loanAccountId, int totalPeriod,
            double monthRate, int scale) {
        Map<Integer, BigDecimal> bjMap = getPerMonthPrincipal(loanAmount,
                monthRate,
                totalPeriod,
                scale);
        for (Map.Entry<Integer, BigDecimal> entry : bjMap.entrySet()) {
            int period = entry.getKey();
            String prePeriod = period == 1 ? (String) null : "" + (period - 1);
            String nextPeriod = period == totalPeriod ? null
                    : "" + (period + 1);
            createPaymentScheduleEntry(paymentScheduleEntryList,
                    loanAccountId,
                    ScheduleTypeEnum.MAIN,
                    FeeItemEnum.DK_BJ,
                    prePeriod,
                    "" + period,
                    nextPeriod,
                    entry.getValue());
        }
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
            double monthRate, int scale, FeeItemEnum feeItem) {
        Map<Integer, BigDecimal> monthInterestMap = getPerMonthInterest(
                loanAmount, monthRate, totalPeriod, scale);
        for (Map.Entry<Integer, BigDecimal> entry : monthInterestMap
                .entrySet()) {
            int period = entry.getKey();
            String prePeriod = period == 1 ? (String) null : "" + (period - 1);
            String nextPeriod = period == totalPeriod ? null
                    : "" + (period + 1);
            createPaymentScheduleEntry(paymentScheduleEntryList,
                    loanAccountId,
                    ScheduleTypeEnum.MAIN,
                    feeItem,
                    prePeriod,
                    "" + period,
                    nextPeriod,
                    entry.getValue());
        }
    }
    
    /**
     * 
     *<等额本息计算获取还款方式为等额本息的每月偿还利息>
     *<公式：每月偿还利息=贷款本金×月利率×〔(1+月利率)^还款月数-(1+月利率)^(还款月序号-1)〕÷〔(1+月利率)^还款月数-1〕>
     * @param loanAmount  总借款额（贷款本金）  
     * @param monthRate    年利率  
     * @param totalPeriod    还款总月数  
     * @param scale              每月偿还本金和利息,不四舍五入，直接截取小数点最后两位  
     * @return [参数说明]
     * 
     * @return Map<Integer,BigDecimal> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    private static Map<Integer, BigDecimal> getPerMonthInterest(
            BigDecimal loanAmount, double monthRate, int totalPeriod,
            int scale) {
        Map<Integer, BigDecimal> map = new HashMap<Integer, BigDecimal>();
        BigDecimal monthInterest;
        for (int i = 1; i < totalPeriod + 1; i++) {
            BigDecimal multiply = loanAmount
                    .multiply(BigDecimal.valueOf(monthRate));
            BigDecimal sub = BigDecimal
                    .valueOf(Math.pow(1 + monthRate, totalPeriod)).subtract(
                            BigDecimal.valueOf(Math.pow(1 + monthRate, i - 1)));
            monthInterest = multiply.multiply(sub)
                    .divide(BigDecimal
                            .valueOf(Math.pow(1 + monthRate, totalPeriod) - 1),
                            6,
                            BigDecimal.ROUND_UP);
            monthInterest = monthInterest.setScale(scale, BigDecimal.ROUND_UP);
            map.put(i, monthInterest);
        }
        return map;
    }
    
    /**
     * 
     *<等额本息计算获取还款方式为等额本息的每月偿还本金  >
     *<功能详细描述>
     * @param loanAmount    总借款额（贷款本金）
     * @param monthRate      月利率
     * @param totalPeriod      还款总月数 
     * @param scale                每月偿还本金和利息,不四舍五入，直接截取小数点最后两位  
     * @return [参数说明]
     * 
     * @return Map<Integer,BigDecimal> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    private static Map<Integer, BigDecimal> getPerMonthPrincipal(
            BigDecimal loanAmount, double monthRate, int totalPeriod,
            int scale) {
        BigDecimal zbj = new BigDecimal(0);
        BigDecimal monthIncome = loanAmount
                .multiply(BigDecimal.valueOf(
                        monthRate * Math.pow(1 + monthRate, totalPeriod)))
                .divide(BigDecimal
                        .valueOf(Math.pow(1 + monthRate, totalPeriod) - 1),
                        scale,
                        BigDecimal.ROUND_DOWN);
        Map<Integer, BigDecimal> mapInterest = getPerMonthInterest(loanAmount,
                monthRate,
                totalPeriod,
                scale);
        Map<Integer, BigDecimal> mapPrincipal = new HashMap<Integer, BigDecimal>();
        for (Map.Entry<Integer, BigDecimal> entry : mapInterest.entrySet()) {
            mapPrincipal.put(entry.getKey(),
                    monthIncome.subtract(entry.getValue()));
            zbj = zbj.add(monthIncome.subtract(entry.getValue()));
        }
        mapPrincipal.put(mapPrincipal.size(),
                mapPrincipal.get(mapPrincipal.size())
                        .add(loanAmount.subtract(zbj)));
        return mapPrincipal;
    }
    
    /**
     * 
     *<等额本息计算获取还款方式为等额本息的每月偿还本金和利息>
     *<公式：每月偿还本息=〔贷款本金×月利率×(1＋月利率)＾还款月数〕÷〔(1＋月利率)＾还款月数-1〕>
     * @param loanAmount    总借款额（贷款本金）  
     * @param monthRate      月利率  
     * @param totalPeriod      还款总月数    每月偿还本金和利息,不四舍五入，直接截取小数点最后两位  
     * @param scale                每月偿还本金和利息,不四舍五入，直接截取小数点最后两位  
     * @return [参数说明]
     * 
     * @return BigDecimal [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    private static BigDecimal getPerMonthPrincipalInterest(
            BigDecimal loanAmount, double monthRate, int totalPeriod,
            int scale) {
        BigDecimal monthIncome = loanAmount
                .multiply(BigDecimal.valueOf(
                        monthRate * Math.pow(1 + monthRate, totalPeriod)))
                .divide(BigDecimal
                        .valueOf(Math.pow(1 + monthRate, totalPeriod) - 1),
                        scale,
                        BigDecimal.ROUND_DOWN);
        return monthIncome;
    }
    
    @Override
    public BigDecimal caculateMonthlyRepayAmount(BigDecimal loanAmount,
            int totalPeriod, Map<FeeItemEnum, String> feeItemMap,
            Map<String, Object> params, int scale, RepayWayEnum repayWay) {
        BigDecimal yearRate = new BigDecimal(feeItemMap.get(FeeItemEnum.DK_LX));
        double monthlyRate = yearRate
                .divide(new BigDecimal(12), 8, RoundingMode.UP).doubleValue();
        BigDecimal repayAmount = getPerMonthPrincipalInterest(loanAmount,
                monthlyRate,
                totalPeriod,
                scale);
        BigDecimal yearRateZXGLG = new BigDecimal(
                feeItemMap.get(FeeItemEnum.ZX_GLF));
        BigDecimal monthlyRateZXGLG = yearRateZXGLG.divide(new BigDecimal(12),
                8,
                RoundingMode.UP);
        BigDecimal monthZXGLF = loanAmount.multiply(monthlyRateZXGLG,
                MathContext.DECIMAL32);
        monthZXGLF.setScale(scale, BigDecimal.ROUND_UP);
        BigDecimal yearRateDKGLF = new BigDecimal(
                feeItemMap.get(FeeItemEnum.DK_GLF));
        BigDecimal monthlyRateDKGLF = yearRateDKGLF.divide(new BigDecimal(12),
                8,
                RoundingMode.UP);
        BigDecimal monthDKGLF = loanAmount.multiply(monthlyRateDKGLF,
                MathContext.DECIMAL32);
        monthDKGLF.setScale(scale, BigDecimal.ROUND_UP);
        return repayAmount.add(monthZXGLF).add(monthDKGLF);
    }
    
}
