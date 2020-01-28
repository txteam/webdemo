/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2017年5月31日
 * <修改描述:>
 */
package com.tx.local.loanaccount.strategy.build;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.tx.component.strategy.context.Strategy;
import com.tx.local.basicdata.model.FeeItemEnum;
import com.tx.local.basicdata.model.RepayWayEnum;
import com.tx.local.loanaccount.model.LoanAccount;
import com.tx.local.loanaccount.model.LoanAccountFeeItem;
import com.tx.local.loanaccount.model.PaymentScheduleEntry;

/**
 * 还款计划分项构建策略<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2017年5月31日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface PaymentScheduleEntryBuilder extends Strategy {
    
    /**
     * 构建每月还款额<br/>
     * <功能详细描述>
     * @param feeItemMap
     * @param params
     * @param scale
     * @return [参数说明]
     * 
     * @return BigDecimal [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public BigDecimal caculateMonthlyRepayAmount(BigDecimal loanAmount,
            int totalPeriod, Map<FeeItemEnum, String> feeItemMap,
            Map<String, Object> params, int scale, RepayWayEnum repayWay);
    
    /**
     * 计算费用项目金【作用于费用项金额】<br/>
     * <功能详细描述>
     * @param feeItemMap
     * @param params
     * @param scale
     * @return [参数说明]
     * 
     * @return BigDecimal [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public Map<FeeItemEnum, BigDecimal> caculateFeeItemAmountMap(
            BigDecimal loanAmount, int totalPeriod,
            Map<FeeItemEnum, String> feeItemMap, Map<String, Object> params,
            int scale, RepayWayEnum repayWay);
    
    /**
     * 计算实际放款金额<br/>
     * <功能详细描述>
     * @param feeItemMap
     * @param params
     * @param scale
     * @return [参数说明]
     * 
     * @return BigDecimal [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public BigDecimal caculateFactLoanAmount(BigDecimal loanAmount,
            int totalPeriod, Map<FeeItemEnum, String> feeItemMap,
            Map<String, Object> params, int scale, RepayWayEnum repayWay);
    
    /**
     * 构建还款计划分项类列表<br/>
     * <功能详细描述>
     * @param loanAccount
     * @param feeCfgItems
     * @return [参数说明]
     * 
     * @return List<PaymentScheduleEntry> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public List<PaymentScheduleEntry> buildPaymentScheduleEntryList(
            String loanAccountId, Map<FeeItemEnum, String> feeItemMap,
            Map<String, Object> params, int scale, RepayWayEnum repayWay);
    
    /**
     * 构建还款计划分项类列表<br/>
     * <功能详细描述>
     * @param loanAccount
     * @param feeCfgItems
     * @return [参数说明]
     * 
     * @return List<PaymentScheduleEntry> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public List<PaymentScheduleEntry> buildPaymentScheduleEntryList(
            LoanAccount loanAccount,
            Map<FeeItemEnum, LoanAccountFeeItem> loanAccountFeeItemMap,
            Map<String, Object> params, int scale, RepayWayEnum repayWay);
}
