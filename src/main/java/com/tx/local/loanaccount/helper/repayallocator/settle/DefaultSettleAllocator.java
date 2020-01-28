/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2015年2月14日
 * <修改描述:>
 */
package com.tx.local.loanaccount.helper.repayallocator.settle;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;

import org.apache.commons.collections4.CollectionUtils;

import com.tx.core.exceptions.util.AssertUtils;
import com.tx.local.loanaccount.handle.PaymentScheduleHandler;
import com.tx.local.loanaccount.helper.repayallocator.AbstractRepayAllocator;
import com.tx.local.loanaccount.helper.repayallocator.comparator.SettleAssignComparator;
import com.tx.local.loanaccount.model.AssignEntryComparable;
import com.tx.local.loanaccount.model.FeeItemAssignGroup;
import com.tx.local.loanaccount.model.FeeItemGroup;
import com.tx.local.loanaccount.model.LoanAccount;
import com.tx.local.loanaccount.model.RepayIntention;
import com.tx.local.loanaccount.model.ScheduleTypeEnum;

/**
 * 中山：提前结清还款分配器<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2015年2月14日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class DefaultSettleAllocator extends AbstractRepayAllocator {
    
    /** <默认构造函数> */
    public DefaultSettleAllocator(LoanAccount loanAccount, PaymentScheduleHandler handler,
            RepayIntention repayIntention) {
        super(loanAccount, handler, repayIntention);
    }
    
    /**
     * @param scheduleType
     * @return
     */
    @Override
    protected Comparator<AssignEntryComparable> buildAssignComparator(ScheduleTypeEnum scheduleType) {
        Comparator<AssignEntryComparable> comparator = new SettleAssignComparator(loanAccount, feeItemCfgMap,
                this.handler, scheduleType, repayDate);
        return comparator;
    }
    
    /**
     * @param scheduleType
     * @return
     */
    @Override
    protected Collection<FeeItemAssignGroup> buildFeeItemAssignGroups(ScheduleTypeEnum scheduleType) {
        AssertUtils.notNull(scheduleType, "scheduleType is null.");
        Collection<FeeItemGroup> feeItemGroups = this.loanAccountStrategy.getFeeItemGroupsOfSettle();
        
        if (CollectionUtils.isEmpty(feeItemGroups)) {
            return new ArrayList<FeeItemAssignGroup>();
        }
        ////构建费用分配分组
        //if (ScheduleTypeEnum.MAIN.equals(scheduleType)) {
        //    Collection<FeeItemAssignGroup> res = FeeItemAssignGroup.buildFeeItemAssignGroups(ScheduleTypeEnum.MAIN,
        //            feeItemGroups,
        //            this.feeItem2AmountMap,
        //            this.groupCode2AmountMap);
        //    return res;
        //} else {
        //    Collection<FeeItemAssignGroup> res = FeeItemAssignGroup.buildFeeItemAssignGroups(scheduleType,
        //            feeItemGroups,
        //            this.mainAssignAmountMap);
        //    return res;
        //}
        Collection<FeeItemAssignGroup> res = FeeItemAssignGroup.buildFeeItemAssignGroups(scheduleType,
                feeItemGroups,
                this.mainAssignAmountMap);
        return res;
    }
    
}
