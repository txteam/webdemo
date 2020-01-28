/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2014年7月28日
 * <修改描述:>
 */
package com.tx.local.loanaccount.helper.paymentschedule;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.joda.time.DateTime;

import com.tx.component.command.context.AbstractHelper;
import com.tx.core.TxConstants;
import com.tx.core.exceptions.util.AssertUtils;
import com.tx.core.util.DateUtils;
import com.tx.local.basicdata.model.FeeItemEnum;
import com.tx.local.loanaccount.model.LoanAccount;
import com.tx.local.loanaccount.model.LoanAccountFeeItem;
import com.tx.local.loanaccount.model.PaymentSchedule;
import com.tx.local.loanaccount.model.PaymentScheduleDetail;
import com.tx.local.loanaccount.model.PaymentScheduleEntry;
import com.tx.local.loanaccount.model.ScheduleAssociateMap;
import com.tx.local.loanaccount.model.ScheduleEntryAssociateMap;
import com.tx.local.loanaccount.model.ScheduleTypeEnum;

/**
 * 还款计划帮助类<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2014年7月28日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public abstract class PaymentScheduleHelper
        extends AbstractHelper<PaymentScheduleHelper> {
    
    /** 
     * 得到指定月的天数 
     * */
    public static int getMonthDays(DateTime repaymentDateTime) {
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
     * 是否到期<br/>
     * <功能详细描述>
     * @param paymentSchedule
     * @param repayDate
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
    public static boolean isExpired(PaymentSchedule paymentSchedule,
            Date repayDate) {
        AssertUtils.notNull(paymentSchedule, "paymentSchedule is null.");
        AssertUtils.notNull(repayDate, "repayDate is null.");
        
        if (!NumberUtils.isDigits(paymentSchedule.getPeriod())) {
            return false;
        }
        
        AssertUtils.notNull(paymentSchedule.getRepaymentDate(),
                "paymentSchedule.repaymentDate is null.");
        if (DateUtils.compareByDay(paymentSchedule.getRepaymentDate(),
                repayDate) < 0) {
            return true;
        } else {
            return false;
        }
    }
    
    /** 
     * debug打印还款计划于日志中
     * <功能详细描述>
     * @param paymentSchedules [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public static String toString(List<PaymentSchedule> paymentScheduleList,
            List<PaymentScheduleEntry> paymentScheduleEntryList) {
        ScheduleAssociateMap<PaymentSchedule> saMap = new ScheduleAssociateMap<>(
                paymentScheduleList);
        ScheduleEntryAssociateMap<PaymentScheduleEntry> seaMap = new ScheduleEntryAssociateMap<PaymentScheduleEntry>(
                paymentScheduleEntryList);
        
        //遍历所有Entry获取所有的费用项、及除了MAIN以外的计划项
        TreeSet<FeeItemEnum> feeItems = new TreeSet<>();
        TreeSet<ScheduleTypeEnum> scheduleTypes = new TreeSet<>();
        for (PaymentScheduleEntry pse : paymentScheduleEntryList) {
            feeItems.add(pse.getFeeItem());
            if (!ScheduleTypeEnum.MAIN.equals(pse.getScheduleType())) {
                scheduleTypes.add(pse.getScheduleType());
            }
        }
        
        StringBuilder sb = new StringBuilder(TxConstants.INITIAL_STR_LENGTH);
        sb.append("<table>\r\n");
        sb.append("<tr>");
        sb.append("<th>").append("期数").append("</th>");
        sb.append("<th>").append("平息应收").append("</th>");
        sb.append("<th>").append("平息豁免").append("</th>");
        sb.append("<th>").append("平息实收").append("</th>");
        sb.append("<th>").append("&nbsp;:&nbsp;").append("</th>");
        for (FeeItemEnum fie : feeItems) {
            sb.append("<th>")
                    .append(fie.getName())
                    .append("_平息应收")
                    .append("</th>");
            sb.append("<th>")
                    .append(fie.getName())
                    .append("_平息豁免")
                    .append("</th>");
            sb.append("<th>")
                    .append(fie.getName())
                    .append("_平息实收")
                    .append("</th>");
        }
        sb.append("<th>").append("&nbsp;|&nbsp;").append("</th>");
        for (ScheduleTypeEnum st : scheduleTypes) {
            sb.append("<th>").append(st.getName()).append("应收").append("</th>");
            sb.append("<th>").append(st.getName()).append("豁免").append("</th>");
            sb.append("<th>").append(st.getName()).append("实收").append("</th>");
            sb.append("<th>").append("&nbsp;:&nbsp;").append("</th>");
            for (FeeItemEnum fie : feeItems) {
                sb.append("<th>")
                        .append(fie.getName())
                        .append("_")
                        .append(st.getName())
                        .append("应收")
                        .append("</th>");
                sb.append("<th>")
                        .append(fie.getName())
                        .append("_")
                        .append(st.getName())
                        .append("豁免")
                        .append("</th>");
                sb.append("<th>")
                        .append(fie.getName())
                        .append("_")
                        .append(st.getName())
                        .append("实收")
                        .append("</th>");
            }
        }
        sb.append("</tr>\r\n");
        
        Collections.sort(paymentScheduleList, PERIOD_COMPARATOR);
        for (PaymentSchedule ps : paymentScheduleList) {
            if (!ScheduleTypeEnum.MAIN.equals(ps.getScheduleType())) {
                continue;
            }
            sb.append("<tr>");
            sb.append("<td>").append(ps.getPeriod()).append("</td>");
            sb.append("<td>").append(ps.getReceivableSum()).append("</td>");
            sb.append("<td>").append(ps.getExemptSum()).append("</td>");
            sb.append("<td>").append(ps.getActualReceivedSum()).append("</td>");
            sb.append("<td>").append("&nbsp;|&nbsp;").append("</td>");
            for (FeeItemEnum fie : feeItems) {
                PaymentScheduleEntry pse = seaMap.get(ps.getScheduleType())
                        .get(ps.getPeriod())
                        .get(fie);
                sb.append("<td>")
                        .append(pse == null ? BigDecimal.ZERO
                                : pse.getReceivableAmount())
                        .append("</td>");
                sb.append("<td>")
                        .append(pse == null ? BigDecimal.ZERO
                                : pse.getExemptAmount())
                        .append("</td>");
                sb.append("<td>")
                        .append(pse == null ? BigDecimal.ZERO
                                : pse.getActualReceivedAmount())
                        .append("</td>");
            }
            sb.append("<td>").append("&nbsp;|&nbsp;").append("</td>");
            for (ScheduleTypeEnum st : scheduleTypes) {
                PaymentSchedule pst = saMap.get(st).get(ps.getPeriod());
                sb.append("<td>")
                        .append(pst.getReceivableSum())
                        .append("</td>");
                sb.append("<td>").append(pst.getExemptSum()).append("</td>");
                sb.append("<td>")
                        .append(pst.getActualReceivedSum())
                        .append("</td>");
                sb.append("<td>").append("&nbsp;:&nbsp;").append("</td>");
                for (FeeItemEnum fie : feeItems) {
                    PaymentScheduleEntry pse = seaMap.get(pst.getScheduleType())
                            .get(pst.getPeriod())
                            .get(fie);
                    sb.append("<td>")
                            .append(pse == null ? BigDecimal.ZERO
                                    : pse.getReceivableAmount())
                            .append("</td>");
                    sb.append("<td>")
                            .append(pse == null ? BigDecimal.ZERO
                                    : pse.getExemptAmount())
                            .append("</td>");
                    sb.append("<td>")
                            .append(pse == null ? BigDecimal.ZERO
                                    : pse.getActualReceivedAmount())
                            .append("</td>");
                }
            }
            sb.append("</tr>\r\n");
        }
        sb.append("</table>");
        return sb.toString();
    }
    
    //    /**
    //     * 计算在指定的还款日，指定费用项集合中逾期金额之和
    //     * <功能详细描述>
    //     * @return [参数说明]
    //     * 
    //     * @return boolean [返回类型说明]
    //     * @exception throws [异常类型] [异常说明]
    //     * @see [类、类#方法、类#成员]
    //    */
    //    public static BigDecimal caculateOverdueAmountSum(Map<String, PaymentSchedule> period2PaymentScheduleMap,
    //            Collection<FeeItemEnum> overdueDependFeeItemCollections, Date repayDate) {
    //        if (repayDate == null) {
    //            repayDate = new Date();
    //        }
    //        //获取到期期数
    //        String expiredPeriod = getExpiredPeriodOfRepayDate(period2PaymentScheduleMap, repayDate);
    //        if (expiredPeriod == null) {
    //            return BigDecimal.ZERO;
    //        }
    //        
    //        String nextPeriod = "1";
    //        BigDecimal overdueAmountSum = BigDecimal.ZERO;
    //        while (!StringUtils.isBlank(nextPeriod) && NumberUtils.toInt(nextPeriod) <= NumberUtils.toInt(expiredPeriod)) {
    //            PaymentSchedule paymentSchedule = period2PaymentScheduleMap.get(nextPeriod);
    //            
    //            for (PaymentScheduleEntry entryTemp : paymentSchedule.getPaymentScheduleEntryList()) {
    //                FeeItemEnum feeItem = entryTemp.getFeeItem();
    //                if (!overdueDependFeeItemCollections.contains(feeItem)) {
    //                    //非逾期依赖费用项直接跳过
    //                    continue;
    //                }
    //                
    //                BigDecimal amount = entryTemp.getReceivableAmount()
    //                        .add(entryTemp.getExemptAmount())
    //                        .subtract(entryTemp.getActualReceivedAmount());
    //                overdueAmountSum = overdueAmountSum.add(amount);
    //            }
    //            nextPeriod = paymentSchedule.getNextPeriod();
    //        }
    //        return overdueAmountSum;
    //    }
    
    /**
     * 校验贷款账户是否逾期
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
    public static boolean isOverdue(
            Map<String, PaymentSchedule> period2PaymentScheduleMap,
            Collection<FeeItemEnum> overdueDependFeeItemCollections,
            Date repayDate) {
        boolean isOverdue = false;
        
        if (repayDate == null) {
            repayDate = new Date();
        }
        String nextPeriod = "1";
        while (!StringUtils.isBlank(nextPeriod)) {
            PaymentSchedule paymentSchedule = period2PaymentScheduleMap
                    .get(nextPeriod);
            
            //如果nowDate <= RepaymentDate 即对应期数还没有到，所以计算逾期不会计算到对应还款计划
            if (com.tx.core.util.DateUtils.compareByDay(repayDate,
                    paymentSchedule.getRepaymentDate()) > 0) {
                //是否逾期仅看平息账中逾期依赖费用项
                for (PaymentScheduleEntry entryTemp : paymentSchedule
                        .getPaymentScheduleEntryList()) {
                    FeeItemEnum feeItem = entryTemp.getFeeItem();
                    if (!overdueDependFeeItemCollections.contains(feeItem)) {
                        //非逾期依赖费用项直接跳过
                        continue;
                    }
                    
                    BigDecimal check = entryTemp.getReceivableAmount()
                            .add(entryTemp.getExemptAmount())
                            .subtract(entryTemp.getActualReceivedAmount());
                    if (check.compareTo(BigDecimal.ZERO) > 0) {
                        //如果任意到期，费用项目的金额未还，则认为逾期
                        return true;
                    }
                }
            } else {
                break;
            }
            nextPeriod = paymentSchedule.getNextPeriod();
        }
        return isOverdue;
    }
    
    //    /**
    //     * 校验贷款账户是否逾期
    //     * <功能详细描述>
    //     * @return [参数说明]
    //     * 
    //     * @return boolean [返回类型说明]
    //     * @exception throws [异常类型] [异常说明]
    //     * @see [类、类#方法、类#成员]
    //    */
    //    public static Date getOverduePeriodExpireDate(Map<String, PaymentSchedule> period2PaymentScheduleMap,
    //            Collection<FeeItemEnum> overdueDependFeeItemCollections, Date repayDate) {
    //        if (repayDate == null) {
    //            repayDate = new Date();
    //        }
    //        Date overduePeriodExpirDate = null;
    //        
    //        String nextPeriod = "1";
    //        while (!StringUtils.isBlank(nextPeriod)) {
    //            PaymentSchedule paymentSchedule = period2PaymentScheduleMap.get(nextPeriod);
    //            
    //            //如果nowDate <= RepaymentDate 即对应期数还没有到，所以计算逾期不会计算到对应还款计划
    //            if (com.tx.core.util.DateUtils.compareByDay(repayDate, paymentSchedule.getRepaymentDate()) > 0) {
    //                //是否逾期仅看平息账中逾期依赖费用项
    //                for (PaymentScheduleEntry entryTemp : paymentSchedule.getPaymentScheduleEntryList()) {
    //                    FeeItemEnum feeItem = entryTemp.getFeeItem();
    //                    if (!overdueDependFeeItemCollections.contains(feeItem)) {
    //                        //非逾期依赖费用项直接跳过
    //                        continue;
    //                    }
    //                    
    //                    BigDecimal check = entryTemp.getReceivableAmount()
    //                            .add(entryTemp.getExemptAmount())
    //                            .subtract(entryTemp.getActualReceivedAmount());
    //                    if (check.compareTo(BigDecimal.ZERO) > 0) {
    //                        //如果任意到期，费用项目的金额未还，则认为当前逾期期数到期时间为该记录
    //                        return paymentSchedule.getRepaymentDate();
    //                    }
    //                }
    //            } else {
    //                break;
    //            }
    //            nextPeriod = paymentSchedule.getNextPeriod();
    //        }
    //        return overduePeriodExpirDate;
    //    }
    //    /**
    //     * 计算对应费用项剩余应还，即求对应费用项
    //     *    所有期数中对应费用项目的 应收 + 豁免 - 实收之和
    //     * <功能详细描述>
    //     * @param loanAccount
    //     * @param feeCfgItemMap
    //     * @param period2PaymentScheduleMap
    //     * @param repayDate
    //     * @param feeItem
    //     * @param isIrr
    //     * @return [参数说明]
    //     * 
    //     * @return BigDecimal [返回类型说明]
    //     * @exception throws [异常类型] [异常说明]
    //     * @see [类、类#方法、类#成员]
    //    */
    //    protected BigDecimal caculateReceivableBalanceExcludeEarlySettleDamageAmount(LoanAccount loanAccount,
    //            Map<FeeItemEnum, LoanAccountFeeCfgItem> feeCfgItemMap,
    //            Map<String, PaymentSchedule> period2PaymentScheduleMap, Date repayDate) {
    //        BigDecimal balanceSum = BigDecimal.ZERO;
    //        for (PaymentSchedule psTemp : period2PaymentScheduleMap.values()) {
    //            for (PaymentScheduleEntry psEntryTemp : psTemp.getPaymentScheduleEntryList()) {
    //                if (FeeItemEnum.TQHKWYJ.equals(psEntryTemp.getFeeItem())) {
    //                    continue;
    //                }
    //                //                if (FeeItemEnum.TQHKWYJ_ZX.equals(psEntryTemp.getFeeItem())) {
    //                //                    continue;
    //                //                }
    //                //平息    
    //                BigDecimal balanceReceivableAmountTemp = psEntryTemp.getReceivableAmount()
    //                        .add(psEntryTemp.getExemptAmount())
    //                        .subtract(psEntryTemp.getActualReceivedAmount());
    //                balanceSum = balanceSum.add(balanceReceivableAmountTemp);
    //            }
    //        }
    //        return balanceSum;
    //    }
    
    /**
      * 计算提前结清金额
      * <功能详细描述>
      * @param loanAccount
      * @param feeCfgItemMap
      * @param period2PaymentScheduleMap
      * @param repayDate
      * @return [参数说明]
      * 
      * @return BigDecimal [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public abstract BigDecimal caculateEarlySettleAmount(
            LoanAccount loanAccount,
            Map<FeeItemEnum, LoanAccountFeeItem> feeCfgItemMap,
            Map<String, PaymentSchedule> period2PaymentScheduleMap,
            Date repayDate);
    
    /**
      * 计算指定还款日的提前结清违约金
      * <功能详细描述>
      * @param loanAccount
      * @param feeCfgItemMap
      * @param period2PaymentScheduleMap
      * @param repayDate
      * @return [参数说明]
      * 
      * @return BigDecimal [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public abstract BigDecimal calculateEarlySettleDamageAmount(
            LoanAccount loanAccount,
            Map<FeeItemEnum, LoanAccountFeeItem> feeCfgItemMap,
            Map<String, PaymentSchedule> period2PaymentScheduleMap,
            Date repayDate);
    
    /**
     * 判断提前节结清豁免期间是否需要将未到期的费用项应收置0
     * <功能详细描述>
     * @param loanAccount
     * @param feeCfgItemMap
     * @param period2PaymentScheduleMap
     * @param repayDate
     * @return [参数说明]
     * 
     * @return BigDecimal [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
    public abstract boolean isNeedSetUpZeroWhenEarlySettleExempt(
            LoanAccount loanAccount,
            Map<FeeItemEnum, LoanAccountFeeItem> feeCfgItemMap,
            Map<String, PaymentSchedule> period2PaymentScheduleMap,
            Date repayDate);
    
    //    /**
    //      * 获取还款日对应的当前期数<br/>
    //      *    与应收期数区别，主要是在如果当天为扣款日时，已到期期数为上一期，应收期数为下一期<br/>
    //      * <功能详细描述>
    //      * @param period2PaymentScheduleMap
    //      * @param repayDate
    //      * @return [参数说明]
    //      * 
    //      * @return String [返回类型说明]
    //      * @exception throws [异常类型] [异常说明]
    //      * @see [类、类#方法、类#成员]
    //     */
    //    public static String getPeriodOfRepayDate(ScheduleAssociateMap<PaymentSchedule> scheduleAssociateMap,
    //            ScheduleTypeEnum scheduleType, Date repayDate) {
    //        AssertUtils.notNull(scheduleAssociateMap, "scheduleAssociateMap is null.");
    //        AssertUtils.notNull(scheduleType, "scheduleType is null.");
    //        
    //        Map<String, PaymentSchedule> paymentScheduleMap = scheduleAssociateMap.get(scheduleType);
    //        String nextPeriod = "1";
    //        String periodOfRepayDateTemp = null;
    //        while (!StringUtils.isBlank(nextPeriod)) {
    //            PaymentSchedule psTemp = paymentScheduleMap.get(nextPeriod);
    //            
    //            if (DateUtils.compareByDay(repayDate, psTemp.getRepaymentDate()) > 0) {
    //                periodOfRepayDateTemp = nextPeriod;
    //            } else if (DateUtils.compareByDay(repayDate, psTemp.getRepaymentDate()) == 0) {
    //                //同一天的情况,应当为该期的下一期，当前期应当不算所到期期数
    //                periodOfRepayDateTemp = nextPeriod;
    //                break;
    //            } else {
    //                //当大于的情况，还款日对应的到期期数，应当即为当期
    //                periodOfRepayDateTemp = nextPeriod;
    //                break;
    //            }
    //            nextPeriod = psTemp.getNextPeriod();
    //        }
    //        return periodOfRepayDateTemp;
    //    }
    
    /**
     * 获取最大豁免期数<br/>
     * <功能详细描述>
     * @param scheduleAssociateMap
     * @param scheduleType
     * @param repayDate
     * @return [参数说明]
     * 
     * @return int [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public static int getMaxExemptAblePeriod(
            ScheduleAssociateMap<PaymentSchedule> scheduleAssociateMap,
            ScheduleTypeEnum scheduleType, Date repayDate) {
        String waitReceivePeriodString = PaymentScheduleHelper
                .getWaitReceivePeriod(scheduleAssociateMap, scheduleType);
        String receivablePeriodString = PaymentScheduleHelper
                .getReceivablePeriod(scheduleAssociateMap,
                        scheduleType,
                        repayDate);
        int maxExemptAblePeriod = NumberUtils.max(
                NumberUtils.toInt(waitReceivePeriodString),
                NumberUtils.toInt(receivablePeriodString, 1));
        
        return maxExemptAblePeriod;
    }
    
    /**
     * 获取第一优先级待分配期数<br/>
     *    当x期未结清，则返回x
     *    当所有digist的期数，都已经结清，返回最后一期
     * <功能详细描述>
     * @param scheduleAssociateMap
     * @param scheduleType
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
    public static String getWaitReceivePeriod(
            ScheduleAssociateMap<PaymentSchedule> scheduleAssociateMap,
            ScheduleTypeEnum scheduleType) {
        AssertUtils.notNull(scheduleAssociateMap,
                "scheduleAssociateMap is null.");
        AssertUtils.notNull(scheduleType, "scheduleType is null.");
        
        Map<String, PaymentSchedule> paymentScheduleMap = scheduleAssociateMap
                .get(scheduleType);
        String nextPeriod = "1";
        //可能参与分配的还款计划
        while (StringUtils.isNotBlank(nextPeriod)) {
            PaymentSchedule ps = paymentScheduleMap.get(nextPeriod);
            
            BigDecimal checkAmount = BigDecimal.ZERO;
            checkAmount = checkAmount.add(ps.getReceivableSum())
                    .add(ps.getExemptSum())
                    .subtract(ps.getActualReceivedSum());
            
            //检查金额
            if (checkAmount.compareTo(BigDecimal.ZERO) > 0) {
                return nextPeriod;
            }
            if (StringUtils.isBlank(ps.getNextPeriod())) {
                return nextPeriod;
            } else {
                nextPeriod = ps.getNextPeriod();
            }
        }
        return nextPeriod;
    }
    
    /**
      * 根据还款日期计算当前期数
      *    
      * @param period2PaymentScheduleMap
      * @param repayDate
      * @param repayDatePeriod
      * @return [参数说明]
      * 
      * @return String [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public static String getExpiredPeriod(
            ScheduleAssociateMap<PaymentSchedule> scheduleAssociateMap,
            ScheduleTypeEnum scheduleType, Date repayDate) {
        AssertUtils.notNull(scheduleAssociateMap,
                "scheduleAssociateMap is null.");
        AssertUtils.notNull(scheduleType, "scheduleType is null.");
        
        Map<String, PaymentSchedule> paymentScheduleMap = scheduleAssociateMap
                .get(scheduleType);
        String nextPeriod = "1";
        String expiredPeriod = null;
        while (!StringUtils.isBlank(nextPeriod)) {
            PaymentSchedule psTemp = paymentScheduleMap.get(nextPeriod);
            AssertUtils.notNull(psTemp,
                    "period not exist.period:{},loanAccountId:{}",
                    new Object[] { nextPeriod,
                            paymentScheduleMap.get("1").getLoanAccountId() });
            
            if (DateUtils.compareByDay(repayDate,
                    psTemp.getRepaymentDate()) > 0) {
                expiredPeriod = nextPeriod;
            } else {
                //当大于的情况，还款日对应的到期期数，应当即为当期
                break;
            }
            
            nextPeriod = psTemp.getNextPeriod();
        }
        return expiredPeriod;
    }
    
    /**
     * 根据还款日期计算当前期数
     *    
     * @param period2PaymentScheduleMap
     * @param repayDate
     * @param repayDatePeriod
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
    public static String getReceivablePeriod(
            ScheduleAssociateMap<PaymentSchedule> scheduleAssociateMap,
            ScheduleTypeEnum scheduleType, Date repayDate) {
        AssertUtils.notNull(scheduleAssociateMap,
                "scheduleAssociateMap is null.");
        AssertUtils.notNull(scheduleType, "scheduleType is null.");
        
        Map<String, PaymentSchedule> paymentScheduleMap = scheduleAssociateMap
                .get(scheduleType);
        String nextPeriod = "1";
        String periodOfRepayDate = "1";
        while (!StringUtils.isBlank(nextPeriod)) {
            PaymentSchedule psTemp = paymentScheduleMap.get(nextPeriod);
            AssertUtils.notNull(psTemp,
                    "period not exist.period:{},loanAccountId:{}",
                    new Object[] { nextPeriod,
                            paymentScheduleMap.get("1").getLoanAccountId() });
            
            if (DateUtils.compareByDay(repayDate,
                    psTemp.getRepaymentDate()) >= 0) {
                if (!StringUtils.isBlank(psTemp.getNextPeriod())) {
                    periodOfRepayDate = psTemp.getNextPeriod();
                } else {
                    periodOfRepayDate = nextPeriod;
                }
            } else {
                break;
            }
            nextPeriod = psTemp.getNextPeriod();
        }
        return periodOfRepayDate;
    }
    
    /**
     * 根据还款计划列表，以及还款计划分项列表，构建期数与还款计划的映射关联<br/>
     * <功能详细描述>
     * @param paymentScheduleList
     * @param paymentScheduleEntryList
     * @return [参数说明]
     * 
     * @return Map<String,PaymentSchedule> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
    public static List<PaymentScheduleDetail> buildPaymentScheduleDetails(
            ScheduleAssociateMap<PaymentSchedule> paymentScheduleMap) {
        AssertUtils.notEmpty(paymentScheduleMap,
                "paymentScheduleMap is empty.");
        
        List<PaymentScheduleDetail> psdList = new ArrayList<>();
        for (PaymentSchedule ps : paymentScheduleMap
                .valueList(ScheduleTypeEnum.MAIN)) {
            psdList.add(new PaymentScheduleDetail(ps.getPeriod(),
                    paymentScheduleMap.valueList(ps.getPeriod())));
        }
        Collections.sort(psdList, PERIOD_DETAIL_COMPARATOR);
        
        return psdList;
    }
    
    /**
      * 根据还款计划列表，以及还款计划分项列表，构建期数与还款计划的映射关联<br/>
      * <功能详细描述>
      * @param paymentScheduleList
      * @param paymentScheduleEntryList
      * @return [参数说明]
      * 
      * @return Map<String,PaymentSchedule> [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public static ScheduleAssociateMap<PaymentSchedule> buildPeriod2PaymentScheduleMap(
            List<PaymentSchedule> paymentScheduleList,
            List<PaymentScheduleEntry> paymentScheduleEntryList) {
        AssertUtils.notEmpty(paymentScheduleList,
                "paymentScheduleList is empty.");
        AssertUtils.notEmpty(paymentScheduleEntryList,
                "paymentScheduleEntryList is empty.");
        
        ScheduleEntryAssociateMap<PaymentScheduleEntry> pseMap = new ScheduleEntryAssociateMap<>(
                paymentScheduleEntryList);
        ScheduleAssociateMap<PaymentSchedule> psMap = new ScheduleAssociateMap<>(
                paymentScheduleList);
        
        for (PaymentSchedule ps : paymentScheduleList) {
            ps.setPaymentScheduleEntryList(new ArrayList<>(
                    pseMap.valueList(ps.getScheduleType(), ps.getPeriod())));
        }
        for (PaymentScheduleEntry pse : paymentScheduleEntryList) {
            pse.setPaymentSchedule(
                    psMap.get(pse.getScheduleType(), pse.getPeriod()));
        }
        return psMap;
    }
    
    /**
     * 根据期数进行排序<br/>
     */
    public static Comparator<PaymentSchedule> PERIOD_COMPARATOR = new Comparator<PaymentSchedule>() {
        @Override
        public int compare(PaymentSchedule o1, PaymentSchedule o2) {
            if (!NumberUtils.isDigits(o1.getPeriod())
                    && !NumberUtils.isDigits(o2.getPeriod())) {
                return o1.getPeriod().compareTo(o2.getPeriod());
            } else if (!NumberUtils.isDigits(o1.getPeriod())) {
                return 1;
            } else if (!NumberUtils.isDigits(o2.getPeriod())) {
                return -1;
            } else {
                Integer o1Period = NumberUtils.toInt(o1.getPeriod());
                Integer o2Period = NumberUtils.toInt(o2.getPeriod());
                return o1Period.compareTo(o2Period);
            }
        }
    };
    
    /**
     * 根据期数进行排序<br/>
     */
    public static Comparator<PaymentScheduleDetail> PERIOD_DETAIL_COMPARATOR = new Comparator<PaymentScheduleDetail>() {
        @Override
        public int compare(PaymentScheduleDetail o1, PaymentScheduleDetail o2) {
            if (!NumberUtils.isDigits(o1.getPeriod())
                    && !NumberUtils.isDigits(o2.getPeriod())) {
                return o1.getPeriod().compareTo(o2.getPeriod());
            } else if (!NumberUtils.isDigits(o1.getPeriod())) {
                return 1;
            } else if (!NumberUtils.isDigits(o2.getPeriod())) {
                return -1;
            } else {
                Integer o1Period = NumberUtils.toInt(o1.getPeriod());
                Integer o2Period = NumberUtils.toInt(o2.getPeriod());
                return o1Period.compareTo(o2Period);
            }
        }
    };
    
    //    /**
    //     * 获取还款日期对应的期数
    //     *    指定还款日对应的应收期数，该方法返回值一定会是  1~totalPeriod的其中一期，如果repayDate>最后一期的还款日期时将返回最后一期的期数
    //     * @param period2PaymentScheduleMap
    //     * @param repayDate
    //     * @return [参数说明]
    //     * 
    //     * @return String [返回类型说明]
    //     * @exception throws [异常类型] [异常说明]
    //     * @see [类、类#方法、类#成员]
    //    */
    //    public static String getReceivablePeriodOfRepayDate(Map<String, PaymentSchedule> period2PaymentScheduleMap,
    //            Date repayDate) {
    //        String nextPeriod = "1";
    //        String receivablePeriodOfRepayDate = nextPeriod;
    //        while (!StringUtils.isBlank(nextPeriod)) {
    //            PaymentSchedule psTemp = period2PaymentScheduleMap.get(nextPeriod);
    //            AssertUtils.notNull(psTemp,
    //                    "period not exist.period:{},loanAccountId:{}",
    //                    new Object[] { nextPeriod, period2PaymentScheduleMap.get("1").getLoanAccountId() });
    //            if (DateUtils.compareByDay(repayDate, psTemp.getRepaymentDate()) > 0) {
    //                receivablePeriodOfRepayDate = nextPeriod;
    //            } else if (DateUtils.compareByDay(repayDate, psTemp.getRepaymentDate()) == 0) {
    //                //同一天的情况,应当为该期的下一期，如果存在
    //                if (!StringUtils.isEmpty(psTemp.getNextPeriod())) {
    //                    receivablePeriodOfRepayDate = psTemp.getNextPeriod();
    //                } else {
    //                    //该期为最后一期的情况
    //                    receivablePeriodOfRepayDate = nextPeriod;
    //                }
    //                break;
    //            } else {
    //                //当大于的情况，还款日对应的到期期数，应当即为当期
    //                receivablePeriodOfRepayDate = nextPeriod;
    //                break;
    //            }
    //            nextPeriod = psTemp.getNextPeriod();
    //        }
    //        return receivablePeriodOfRepayDate;
    //    }
    //    /**
    //     * 还款计划比较器<br/>
    //     * <功能详细描述>
    //     * 
    //     * @author  Administrator
    //     * @version  [版本号, 2014年12月28日]
    //     * @see  [相关类/方法]
    //     * @since  [产品/模块版本]
    //     */
    //    public static class ExpiredPaymentScheduleComparator implements Comparator<PaymentSchedule> {
    //        
    //        public static ExpiredPaymentScheduleComparator INSTANCE = new ExpiredPaymentScheduleComparator();
    //        
    //        /**
    //         * @param o1
    //         * @param o2
    //         * @return
    //         */
    //        @Override
    //        public int compare(PaymentSchedule o1, PaymentSchedule o2) {
    //            if (!NumberUtils.isDigits(o1.getPeriod()) && !NumberUtils.isDigits(o2.getPeriod())) {
    //                return o1.getPeriod().compareTo(o2.getPeriod());
    //            } else if (!NumberUtils.isDigits(o1.getPeriod())) {
    //                return -1;
    //            } else if (!NumberUtils.isDigits(o2.getPeriod())) {
    //                return 1;
    //            } else {
    //                Integer o1Period = NumberUtils.toInt(o1.getPeriod());
    //                Integer o2Period = NumberUtils.toInt(o2.getPeriod());
    //                
    //                return o1Period.compareTo(o2Period) * -1;
    //            }
    //        }
    //    }
    
}
