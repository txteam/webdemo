/*
u * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2015年1月27日
 * <修改描述:>
 */
package com.tx.local.loanaccount.helper.exemptallocator.repay;

import java.util.Comparator;

import com.tx.local.loanaccount.handle.PaymentScheduleHandler;
import com.tx.local.loanaccount.helper.exemptallocator.AbstractExemptAllocator;
import com.tx.local.loanaccount.helper.exemptallocator.comparator.ExemptAssignComparator;
import com.tx.local.loanaccount.helper.paymentschedule.PaymentScheduleHelper;
import com.tx.local.loanaccount.model.AssignEntryComparable;
import com.tx.local.loanaccount.model.ExemptIntention;
import com.tx.local.loanaccount.model.LoanAccount;
import com.tx.local.loanaccount.model.ScheduleTypeEnum;

/**
 * 还款分配器<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2015年1月27日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class DefaultRepayExemptAllocator extends AbstractExemptAllocator {
    
    /** <默认构造函数> */
    public DefaultRepayExemptAllocator(LoanAccount loanAccount,
            PaymentScheduleHandler handler, ExemptIntention exemptIntention) {
        super(loanAccount, handler, exemptIntention,
                PaymentScheduleHelper.getMaxExemptAblePeriod(
                        handler.getPaymentScheduleMap(),
                        ScheduleTypeEnum.MAIN,
                        exemptIntention.getRepayDate()));
    }
    
    /** <默认构造函数> */
    public DefaultRepayExemptAllocator(LoanAccount loanAccount,
            PaymentScheduleHandler handler, ExemptIntention exemptIntention,
            int maxExemptAblePeriod) {
        super(loanAccount, handler, exemptIntention, maxExemptAblePeriod);
    }
    
    /**
     * 构建分配比较器<br/>
     * @param scheduleType
     * @return
     */
    protected Comparator<AssignEntryComparable> buildAssignComparator(
            ScheduleTypeEnum scheduleType) {
        Comparator<AssignEntryComparable> comparator = new ExemptAssignComparator(
                this.loanAccount, feeItemCfgMap, this.handler, scheduleType,
                repayDate);
        return comparator;
    }
}
