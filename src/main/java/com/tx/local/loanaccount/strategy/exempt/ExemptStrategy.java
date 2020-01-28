/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2017年6月22日
 * <修改描述:>
 */
package com.tx.local.loanaccount.strategy.exempt;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.tx.component.strategy.context.Strategy;
import com.tx.local.basicdata.model.FeeItemEnum;
import com.tx.local.loanaccount.handle.PaymentScheduleHandler;
import com.tx.local.loanaccount.helper.exemptallocator.ExemptAllocator;
import com.tx.local.loanaccount.model.ExemptIntention;
import com.tx.local.loanaccount.model.ExemptRecordEntry;
import com.tx.local.loanaccount.model.LATradingRecord;
import com.tx.local.loanaccount.model.LoanAccount;

/**
 * 豁免策略(豁免分配策略)<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2017年6月22日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface ExemptStrategy extends Strategy {
    
    /**
     * 豁免自动分配<br/>
     * <功能详细描述>
     * @param loanAccount
     * @param exemptIntention
     * @return [参数说明]
     * 
     * @return Map<String,Map<FeeItemEnum,BigDecimal>> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public LATradingRecord exempt(LoanAccount loanAccount,
            ExemptIntention exemptIntention);
    
    /**
     * 豁免自动分配<br/>
     * <功能详细描述>
     * @param loanAccount
     * @param handler
     * @param exemptIntention
     * @return [参数说明]
     * 
     * @return Map<String,Map<FeeItemEnum,BigDecimal>> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public ExemptAllocator assignOfExempt(LoanAccount loanAccount,
            PaymentScheduleHandler handler, ExemptIntention exemptIntention);
    
    /**
     * 豁免主计划分配<br/>
     * <功能详细描述>
     * @param loanAccount
     * @param handler
     * @param exemptIntention
     * @return [参数说明]
     * 
     * @return Map<String,Map<FeeItemEnum,BigDecimal>> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public Map<FeeItemEnum, BigDecimal> mainAssignOfExempt(
            LoanAccount loanAccount, PaymentScheduleHandler handler,
            ExemptIntention exemptIntention);
    
    /**
     * 豁免自动分配<br/>
     * <功能详细描述>
     * @param tradingRecord
     * @param loanAccount
     * @param handler
     * @param exemptAllocator
     * @return [参数说明]
     * 
     * @return Map<String,Map<FeeItemEnum,BigDecimal>> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public List<ExemptRecordEntry> assignOfExempt(LATradingRecord tradingRecord,
            LoanAccount loanAccount, PaymentScheduleHandler handler,
            ExemptAllocator exemptAllocator);
    
}
