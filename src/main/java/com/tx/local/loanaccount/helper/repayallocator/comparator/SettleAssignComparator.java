/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2014年6月12日
 * <修改描述:>
 */
package com.tx.local.loanaccount.helper.repayallocator.comparator;

import java.util.Comparator;
import java.util.Date;
import java.util.Map;

import org.apache.commons.lang3.math.NumberUtils;

import com.tx.core.exceptions.util.AssertUtils;
import com.tx.local.basicdata.model.FeeItemCfg;
import com.tx.local.basicdata.model.FeeItemEnum;
import com.tx.local.loanaccount.handle.PaymentScheduleHandler;
import com.tx.local.loanaccount.helper.paymentschedule.PaymentScheduleHelper;
import com.tx.local.loanaccount.model.AssignEntryComparable;
import com.tx.local.loanaccount.model.LoanAccount;
import com.tx.local.loanaccount.model.PaymentSchedule;
import com.tx.local.loanaccount.model.ScheduleAssociateMap;
import com.tx.local.loanaccount.model.ScheduleTypeEnum;
import com.tx.local.loanaccount.model.SettleInterestStatusEnum;

/**
 * SettleAssign比较器<br/>
 *    本金将作为第一优先级进行分配<br/>
 * 
 * @author  Administrator
 * @version  [版本号, 2014年6月12日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class SettleAssignComparator implements Comparator<AssignEntryComparable> {
    
    //贷款账户
    private LoanAccount loanAccount;
    
    //费用项列表由还款优先级进行
    private Map<FeeItemEnum, FeeItemCfg> feeItemCfgMap;
    
    //还款计划处理器
    private PaymentScheduleHandler paymentScheduleHandler;
    
    //是否递减分配
    private final ScheduleTypeEnum scheduleType;
    
    //贷款账户对应的还款计划
    private final ScheduleAssociateMap<PaymentSchedule> paymentScheduleMap;
    
    //首期待分配期数
    private final int waitReceivePeriod;
    
    //应收期数
    private final int receivablePeriod;
    
    /** <默认构造函数> */
    public SettleAssignComparator(LoanAccount loanAccount, Map<FeeItemEnum, FeeItemCfg> feeItemCfgMap,
            PaymentScheduleHandler paymentScheduleHandler, ScheduleTypeEnum scheduleType, Date repayDate) {
        super();
        AssertUtils.notNull(loanAccount, "loanAccount is null.");
        AssertUtils.notEmpty(feeItemCfgMap, "feeItemCfgMap is empty.");
        AssertUtils.notNull(paymentScheduleHandler, "paymentScheduleHandler is null.");
        AssertUtils.notNull(scheduleType, "scheduleType is null.");
        
        this.loanAccount = loanAccount;
        this.feeItemCfgMap = feeItemCfgMap;
        this.paymentScheduleHandler = paymentScheduleHandler;
        this.scheduleType = scheduleType;
        
        this.paymentScheduleMap = this.paymentScheduleHandler.getPaymentScheduleMap();
        //this.totalPeriod = this.loanAccount.getTotalPeriod();
        
        String waitReceivePeriodString = PaymentScheduleHelper.getWaitReceivePeriod(this.paymentScheduleMap,
                this.scheduleType);
        String receivablePeriodString = PaymentScheduleHelper.getReceivablePeriod(this.paymentScheduleMap,
                this.scheduleType,
                repayDate);
        AssertUtils.notNull(waitReceivePeriodString, "待收期数不能为空.");
        //AssertUtils.notNull(receivablePeriodString, "应收期数不能为空.");
        this.waitReceivePeriod = NumberUtils.toInt(waitReceivePeriodString);
        this.receivablePeriod = NumberUtils.toInt(receivablePeriodString,1);
    }
    
    /**
    * o1 与 o2之间比较
    * @param o1
    * @param o2
    * @return
    */
    @Override
    public int compare(AssignEntryComparable o1, AssignEntryComparable o2) {
        AssertUtils.notNull(o1.getFeeItem(), "o1.feeItem is null.");
        AssertUtils.notNull(o2.getFeeItem(), "o1.feeItem is null.");
        
        FeeItemEnum o1FeeItem = o1.getFeeItem();
        FeeItemEnum o2FeeItem = o2.getFeeItem();
        AssertUtils.isTrue(this.feeItemCfgMap.containsKey(o1FeeItem),
                "feeItem not exist.feeItem:{}",
                new Object[] { o1FeeItem });
        AssertUtils.isTrue(this.feeItemCfgMap.containsKey(o2FeeItem),
                "feeItem not exist.feeItem:{}",
                new Object[] { o2FeeItem });
        FeeItemCfg o1FeeItemCfg = this.feeItemCfgMap.get(o1FeeItem);
        FeeItemCfg o2FeeItemCfg = this.feeItemCfgMap.get(o2FeeItem);
        
        int o1PeriodWeight = getPeriodWeight(o1.getPeriod());
        int o2PeriodWeight = getPeriodWeight(o2.getPeriod());
        
        if (o1FeeItemCfg.isPrincipal() && !o2FeeItemCfg.isPrincipal()) {
            return -1;
        } else if (!o1FeeItemCfg.isPrincipal() && o2FeeItemCfg.isPrincipal()) {
            return 1;
        } else {
            if (o1PeriodWeight > o2PeriodWeight) {
                //如果o1大于o2的期数权重
                return 1;
            } else if (o1PeriodWeight < o2PeriodWeight) {
                //如果o1小于o2的期数权重
                return -1;
            } else {
                int o1Priority = this.feeItemCfgMap.get(o1FeeItem).getRepayPriority();
                int o2Priority = this.feeItemCfgMap.get(o2FeeItem).getRepayPriority();
                
                if (o1Priority == o2Priority) {
                    //应为取权重的时候，NA期内，或停息的费用项之间权重相同，此时又需要根据期数进行比较
                    if (o1.getPeriod().equals(o2.getPeriod())) {
                        return 0;
                    } else if (!NumberUtils.isDigits(o1.getPeriod()) && !NumberUtils.isDigits(o2.getPeriod())) {
                        return o1.getPeriod().compareTo(o2.getPeriod());
                    } else if (NumberUtils.isDigits(o1.getPeriod())) {
                        return -1;
                    } else if (NumberUtils.isDigits(o2.getPeriod())) {
                        return 1;
                    } else {
                        Integer o1Period = NumberUtils.toInt(o1.getPeriod());
                        Integer o2Period = NumberUtils.toInt(o2.getPeriod());
                        return o1Period.compareTo(o2Period);
                    }
                } else if (o1Priority > o2Priority) {
                    return 1;
                } else {
                    return -1;
                }
            }
        }
    }
    
    /**
     * 获取期数的权重值<br/>
     * <功能详细描述>
     * @param period
     * @param priority
     * @return [参数说明]
     * 
     * @return int [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    private int getPeriodWeight(String period) {
        int periodWeight = 0;
        if (SettleInterestStatusEnum.SSI.equals(this.loanAccount.getSettleInterestStatus())) {
            if (!NumberUtils.isDigits(period)) {
                periodWeight = this.receivablePeriod;
            } else if (NumberUtils.toInt(period) <= this.receivablePeriod) {
                periodWeight = this.receivablePeriod;
            } else {
                periodWeight = NumberUtils.toInt(period);
            }
        } else {
            if (!NumberUtils.isDigits(period)) {
                periodWeight = this.waitReceivePeriod;
            } else {
                periodWeight = NumberUtils.toInt(period);
            }
        }
        return periodWeight;
    }
}