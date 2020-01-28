/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2017年6月22日
 * <修改描述:>
 */
package com.tx.local.loanaccount.strategy.repay;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.tx.component.strategy.context.Strategy;
import com.tx.local.basicdata.model.FeeItemCfg;
import com.tx.local.basicdata.model.FeeItemEnum;
import com.tx.local.loanaccount.handle.PaymentScheduleHandler;
import com.tx.local.loanaccount.helper.repayallocator.RepayAllocator;
import com.tx.local.loanaccount.model.ChargeRecordEntry;
import com.tx.local.loanaccount.model.LATradingRecord;
import com.tx.local.loanaccount.model.LoanAccount;
import com.tx.local.loanaccount.model.LoanAccountFeeItem;
import com.tx.local.loanaccount.model.OverRepayRecord;
import com.tx.local.loanaccount.model.PaymentRecordEntry;
import com.tx.local.loanaccount.model.RepayIntention;

/**
  * 还款策略<br/>
  * <功能详细描述>
  * 
  * @author  Administrator
  * @version  [版本号, 2017年6月22日]
  * @see  [相关类/方法]
  * @since  [产品/模块版本]
  */
public interface RepayStrategy extends CEHKStrategy, Strategy {
    
    /**
     * 还款自动分配<br/>
     * <功能详细描述>
     * @param loanAccount
     * @param repayIntention
     * @return [参数说明]
     * 
     * @return Map<String,Map<FeeItemEnum,BigDecimal>> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public LATradingRecord repay(LoanAccount loanAccount, RepayIntention repayIntention);
    
    /**
     * 还款自动分配<br/>
     * <功能详细描述>
     * @param loanAccount
     * @param repayIntention
     * @return [参数说明]
     * 
     * @return Map<String,Map<FeeItemEnum,BigDecimal>> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public RepayAllocator assignOfRepay(LoanAccount loanAccount, PaymentScheduleHandler handler,
            RepayIntention repayIntention);
    
    /**
     * 还款自动分配<br/>
     * <功能详细描述>
     * @param loanAccount
     * @param repayIntention
     * @return [参数说明]
     * 
     * @return Map<String,Map<FeeItemEnum,BigDecimal>> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public Map<FeeItemEnum, BigDecimal> autoAssignOfRepay(LoanAccount loanAccount, PaymentScheduleHandler handler,
            RepayIntention repayIntention);
    
    /**
     * 还款自动分配<br/>
     * <功能详细描述>
     * @param tradingRecord
     * @param loanAccount
     * @param handler
     * @param assignAllocator
     * @param isReceived
     * @return [参数说明]
     * 
     * @return Map<String,Map<FeeItemEnum,BigDecimal>> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public List<PaymentRecordEntry> assignOfRepay(LATradingRecord tradingRecord, LoanAccount loanAccount,
            PaymentScheduleHandler handler, RepayAllocator assignAllocator, boolean isReceived);
    
    /**
     * 计算逾期利息<br/>
     * <功能详细描述>
     * @param loanAccount
     * @param feeItemMap
     * @param handler
     * @param tradingRecord
     * @param overRepayRecords
     * @param isReceived
     * @return [参数说明]
     * 
     * @return List<ChargeRecordEntry> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public List<PaymentRecordEntry> buildCEHKEntryList(LoanAccount loanAccount, PaymentScheduleHandler handler,
            LATradingRecord tradingRecord, List<OverRepayRecord> overRepayRecords, boolean isReceived);
    
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
    public List<ChargeRecordEntry> buildRepayChargeEntryList(LoanAccount loanAccount,
            Map<FeeItemEnum, LoanAccountFeeItem> feeItemMap, Map<FeeItemEnum, FeeItemCfg> feeItemCfgMap,
            PaymentScheduleHandler handler, LATradingRecord tradingRecord, Date repayDate);
    
    /**
      * 
      *<重置计费记录>
      *<功能详细描述>
      * @param loanAccount
      * @param handler
      * @param chargeRecordEntryList
      * @return [参数说明]
      * 
      * @return List<ChargeRecordEntry> [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public List<ChargeRecordEntry> resetChargeRecordEntryList(LoanAccount loanAccount, PaymentScheduleHandler handler,
            List<ChargeRecordEntry> chargeRecordEntryList);
}
