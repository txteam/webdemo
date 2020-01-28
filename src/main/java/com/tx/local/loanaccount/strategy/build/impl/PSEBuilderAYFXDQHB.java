/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2017年5月31日
 * <修改描述:>
 */
package com.tx.local.loanaccount.strategy.build.impl;

import java.math.BigDecimal;
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
 * 还款计划分项构建器<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2017年5月31日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Component("pseBuilderAYFXDQHB")
public class PSEBuilderAYFXDQHB
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
            BigDecimal totalSum = loanAmount.multiply(yearRate)
                    .multiply(new BigDecimal(totalPeriod))
                    .divide(new BigDecimal(12), scale, RoundingMode.UP);
            BigDecimal monthlyRate = yearRate.divide(new BigDecimal(12),
                    8,
                    RoundingMode.UP);
            
            buildMonthlyRepay(paymentScheduleEntryList,
                    ScheduleTypeEnum.MAIN,
                    FeeItemEnum.ZX_GLF,
                    loanAccountId,
                    loanAmount,
                    totalPeriod,
                    monthlyRate,
                    totalSum,
                    scale);
        }
        if (feeItemMap.containsKey(FeeItemEnum.DK_GLF)) {
            BigDecimal yearRate = new BigDecimal(
                    feeItemMap.get(FeeItemEnum.DK_GLF));
            BigDecimal totalSum = loanAmount.multiply(yearRate)
                    .multiply(new BigDecimal(totalPeriod))
                    .divide(new BigDecimal(12), scale, RoundingMode.UP);
            BigDecimal monthlyRate = yearRate.divide(new BigDecimal(12),
                    8,
                    RoundingMode.UP);
            
            buildMonthlyRepay(paymentScheduleEntryList,
                    ScheduleTypeEnum.MAIN,
                    FeeItemEnum.DK_GLF,
                    loanAccountId,
                    loanAmount,
                    totalPeriod,
                    monthlyRate,
                    totalSum,
                    scale);
        }
        
        //利息
        if (feeItemMap.containsKey(FeeItemEnum.DK_LX)) {
            BigDecimal yearRate = new BigDecimal(
                    feeItemMap.get(FeeItemEnum.DK_LX));
            BigDecimal totalSum = loanAmount.multiply(yearRate)
                    .multiply(new BigDecimal(totalPeriod))
                    .divide(new BigDecimal(12), scale, RoundingMode.UP);
            BigDecimal monthlyRate = yearRate.divide(new BigDecimal(12),
                    8,
                    RoundingMode.UP);
            
            buildMonthlyRepay(paymentScheduleEntryList,
                    ScheduleTypeEnum.MAIN,
                    FeeItemEnum.DK_LX,
                    loanAccountId,
                    loanAmount,
                    totalPeriod,
                    monthlyRate,
                    totalSum,
                    scale);
        }
        
        //本金
        createPaymentScheduleEntry(paymentScheduleEntryList,
                loanAccountId,
                ScheduleTypeEnum.MAIN,
                FeeItemEnum.DK_BJ,
                (totalPeriod - 1) == 0 ? (String) null : "" + (totalPeriod - 1),
                "" + totalPeriod,
                null,
                loanAmount);
        
        return paymentScheduleEntryList;
    }
}
