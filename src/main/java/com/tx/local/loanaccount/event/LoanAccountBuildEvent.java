/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2014年5月20日
 * <修改描述:>
 */
package com.tx.local.loanaccount.event;

import java.util.List;

import com.tx.component.command.context.CommandRequest;
import com.tx.component.event.event.impl.EventImpl;
import com.tx.core.exceptions.util.AssertUtils;
import com.tx.local.loanaccount.model.LoanAccount;
import com.tx.local.loanaccount.model.PaymentSchedule;
import com.tx.local.loanaccount.model.PaymentScheduleEntry;
import com.tx.local.loanaccount.model.ScheduleAssociateMap;
import com.tx.local.loanaccount.model.ScheduleEntryAssociateMap;

/**
 * 创建贷款账户事件<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2014年5月20日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public abstract class LoanAccountBuildEvent extends EventImpl {
    
    /** 贷款单账户 */
    private final LoanAccount loanAccount;
    
    /** 还款计划 */
    private final List<PaymentSchedule> paymentScheduleList;
    
    /** 还款计划分项列表 */
    private final List<PaymentScheduleEntry> paymentScheduleEntryList;
    
    /** 还款计划项映 */
    private final ScheduleAssociateMap<PaymentSchedule> paymentSchedulesMap;
    
    /** 还款计划分项映射 */
    private final ScheduleEntryAssociateMap<PaymentScheduleEntry> paymentScheduleEntryMap;
    
    /** 构建账户请求 */
    private final CommandRequest request;
    
    /** <默认构造函数> */
    public LoanAccountBuildEvent(LoanAccount loanAccount,
            List<PaymentSchedule> paymentScheduleList,
            List<PaymentScheduleEntry> paymentScheduleEntryList,
            CommandRequest request) {
        super();
        AssertUtils.notNull(loanAccount, "loanAccount is null.");
        AssertUtils.notEmpty(paymentScheduleList, "paymentSchedules is null.");
        AssertUtils.notEmpty(paymentScheduleEntryList,
                "paymentScheduleEntryList is null.");
        
        this.loanAccount = loanAccount;
        this.paymentScheduleList = paymentScheduleList;
        this.paymentScheduleEntryList = paymentScheduleEntryList;
        this.request = request;
        
        this.paymentSchedulesMap = new ScheduleAssociateMap<>(
                this.paymentScheduleList);
        this.paymentScheduleEntryMap = new ScheduleEntryAssociateMap<>(
                this.paymentScheduleEntryList);
    }
    
    /**
     * @return 返回 loanAccount
     */
    public LoanAccount getLoanAccount() {
        return loanAccount;
    }
    
    /**
     * @return 返回 paymentSchedules
     */
    public List<PaymentSchedule> getPaymentScheduleList() {
        return paymentScheduleList;
    }
    
    /**
     * @return 返回 paymentSchedulesMap
     */
    public ScheduleAssociateMap<PaymentSchedule> getPaymentSchedulesMap() {
        return paymentSchedulesMap;
    }
    
    /**
     * @return 返回 paymentScheduleEntryList
     */
    public List<PaymentScheduleEntry> getPaymentScheduleEntryList() {
        return paymentScheduleEntryList;
    }
    
    /**
     * @return 返回 paymentScheduleEntryMap
     */
    public ScheduleEntryAssociateMap<PaymentScheduleEntry> getPaymentScheduleEntryMap() {
        return paymentScheduleEntryMap;
    }
    
    /**
     * @return 返回 request
     */
    public CommandRequest getRequest() {
        return request;
    }
}
