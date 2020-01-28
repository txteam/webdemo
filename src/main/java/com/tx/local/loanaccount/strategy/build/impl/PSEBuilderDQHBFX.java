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
 * 还款计划分项构建器(到期还本付息)<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2017年5月31日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Component("pseBuilderDQHBFX")
public class PSEBuilderDQHBFX
        extends AbstractPaymentScheduleEntryBuilder_ROUND_UP {
    
    /**
     * 
     * @param loanAccountId
     * @param feeItemMap
     * @param params
     * @param scale
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
        
        //管理费:
        if (feeItemMap.containsKey(FeeItemEnum.ZX_GLF)) {
            BigDecimal yearRate = new BigDecimal(
                    feeItemMap.get(FeeItemEnum.ZX_GLF));
            buildMonthlyRepay(paymentScheduleEntryList,
                    loanAccountId,
                    loanAmount,
                    totalPeriod,
                    yearRate,
                    scale,
                    FeeItemEnum.ZX_GLF);
        }
        if (feeItemMap.containsKey(FeeItemEnum.DK_GLF)) {
            BigDecimal yearRate = new BigDecimal(
                    feeItemMap.get(FeeItemEnum.DK_GLF));
            buildMonthlyRepay(paymentScheduleEntryList,
                    loanAccountId,
                    loanAmount,
                    totalPeriod,
                    yearRate,
                    scale,
                    FeeItemEnum.DK_GLF);
        }
        
        //利息
        if (feeItemMap.containsKey(FeeItemEnum.DK_LX)) {
            BigDecimal yearRate = new BigDecimal(
                    feeItemMap.get(FeeItemEnum.DK_LX));
            buildMonthlyRepay(paymentScheduleEntryList,
                    loanAccountId,
                    loanAmount,
                    totalPeriod,
                    yearRate,
                    scale,
                    FeeItemEnum.DK_LX);
        }
        
        //本金
        createPaymentScheduleEntry(paymentScheduleEntryList,
                loanAccountId,
                ScheduleTypeEnum.MAIN,
                FeeItemEnum.DK_BJ,
                null,
                "1",
                null,
                loanAmount);
        return paymentScheduleEntryList;
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
            BigDecimal yearRate, int scale, FeeItemEnum feeItem) {
        BigDecimal rate = new BigDecimal(12).divide(new BigDecimal(totalPeriod),
                8,
                RoundingMode.UP);
        BigDecimal endRate = yearRate.divide(rate, 8, RoundingMode.UP);
        BigDecimal repayAmount = loanAmount.multiply(endRate,
                MathContext.DECIMAL64);
        createPaymentScheduleEntry(paymentScheduleEntryList,
                loanAccountId,
                ScheduleTypeEnum.MAIN,
                feeItem,
                null,
                "1",
                null,
                repayAmount);
    }
}
