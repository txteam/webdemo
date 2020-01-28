/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2017年6月22日
 * <修改描述:>
 */
package com.tx.local.loanaccount.strategy.settle;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.tx.component.strategy.context.Strategy;
import com.tx.local.basicdata.model.FeeItemCfg;
import com.tx.local.basicdata.model.FeeItemEnum;
import com.tx.local.loanaccount.handle.PaymentScheduleHandler;
import com.tx.local.loanaccount.helper.exemptallocator.ExemptAllocator;
import com.tx.local.loanaccount.helper.repayallocator.RepayAllocator;
import com.tx.local.loanaccount.model.ChargeRecordEntry;
import com.tx.local.loanaccount.model.ExemptRecordEntry;
import com.tx.local.loanaccount.model.LATradingRecord;
import com.tx.local.loanaccount.model.LoanAccount;
import com.tx.local.loanaccount.model.LoanAccountFeeItem;
import com.tx.local.loanaccount.model.LoanAccountSettleDetail;
import com.tx.local.loanaccount.model.PaymentRecordEntry;
import com.tx.local.loanaccount.model.SettleIntention;

/**
 * 还款策略<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2017年6月22日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface SettleStrategy extends Strategy {
    
    /**
     * 构建贷款账户详情<br/>
     * <功能详细描述>
     * @param loanAccount
     * @param feeItemMap
     * @param feeItemCfgMap
     * @param handler
     * @param repayDate
     * @return [参数说明]
     * 
     * @return LoanAccountDetail [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
    public LoanAccountSettleDetail buildLoanAccountSettleDetail(
            LoanAccount loanAccount,
            Map<FeeItemEnum, LoanAccountFeeItem> feeItemMap,
            Map<FeeItemEnum, FeeItemCfg> feeItemCfgMap,
            PaymentScheduleHandler handler, Date repayDate);
    
    /**
     * 构建提前结清计费冲抵分项<br/>
     * @param loanAccount
     * @param feeItemMap
     * @param feeItemCfgMap
     * @param handler
     * @param tradingRecord
     * @param repayDate
     * @return [参数说明]
     * 
     * @return List<ChargeRecordEntry> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public List<ChargeRecordEntry> buildSettleChargeEntryList(
            LoanAccount loanAccount,
            Map<FeeItemEnum, LoanAccountFeeItem> feeItemMap,
            Map<FeeItemEnum, FeeItemCfg> feeItemCfgMap,
            PaymentScheduleHandler handler, LATradingRecord tradingRecord,
            Date repayDate);
    
    /**
     * 还款自动分配<br/>
     * <功能详细描述>
     * @param loanAccount
     * @param feeItemMap
     * @param feeItemCfgMap
     * @param handler
     * @param settleIntention
     * @return [参数说明]
     * 
     * @return Map<String,Map<FeeItemEnum,BigDecimal>> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public Map<FeeItemEnum, BigDecimal> autoAssignOfSettle(
            LoanAccount loanAccount,
            Map<FeeItemEnum, LoanAccountFeeItem> feeItemMap,
            Map<FeeItemEnum, FeeItemCfg> feeItemCfgMap,
            PaymentScheduleHandler handler, SettleIntention settleIntention);
    
    /**
     * 结清<br/>
     * <功能详细描述>
     * @param loanAccount
     * @param settleIntention
     * @return [参数说明]
     * 
     * @return LATradingRecord [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public LATradingRecord settle(LoanAccount loanAccount,
            SettleIntention settleIntention);
    
    /**
     * 还款自动分配<br/>
     * <功能详细描述>
     * @param loanAccount
     * @param handler
     * @param settleIntention
     * @return [参数说明]
     * 
     * @return RepayAllocator [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public RepayAllocator assignOfSettle(LoanAccount loanAccount,
            PaymentScheduleHandler handler, SettleIntention settleIntention);
    
    /**
     * 结清自动分配<br/>
     * <功能详细描述>
     * @param tradingRecord
     * @param loanAccount
     * @param handler
     * @param assignAllocator
     * @param isReceived
     * @return [参数说明]
     * 
     * @return List<PaymentRecordEntry> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public List<PaymentRecordEntry> assignOfSettle(
            LATradingRecord tradingRecord, LoanAccount loanAccount,
            PaymentScheduleHandler handler, RepayAllocator assignAllocator,
            boolean isReceived);
    
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
    public ExemptAllocator assignOfSettleExempt(LoanAccount loanAccount,
            PaymentScheduleHandler handler, SettleIntention settleIntention);
    
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
    public List<ExemptRecordEntry> assignOfSettleExempt(
            LATradingRecord tradingRecord, LoanAccount loanAccount,
            PaymentScheduleHandler handler, ExemptAllocator exemptAllocator);
}
