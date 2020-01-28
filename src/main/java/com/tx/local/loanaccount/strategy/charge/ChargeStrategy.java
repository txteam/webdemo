/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2017年7月2日
 * <修改描述:>
 */
package com.tx.local.loanaccount.strategy.charge;

import java.util.List;

import com.tx.local.loanaccount.handle.PaymentScheduleHandler;
import com.tx.local.loanaccount.model.ChargeRecordEntry;
import com.tx.local.loanaccount.model.DeductFailChargeRecord;
import com.tx.local.loanaccount.model.LATradingRecord;
import com.tx.local.loanaccount.model.LoanAccount;
import com.tx.local.loanaccount.model.OutsourceChargeRecord;
import com.tx.local.loanaccount.model.OverdueInterestChargeRecord;

/**
  * 计费策略<br/>
  * <功能详细描述>
  * 
  * @author  Administrator
  * @version  [版本号, 2017年7月2日]
  * @see  [相关类/方法]
  * @since  [产品/模块版本]
  */
public interface ChargeStrategy extends YQLXChargeStrategy {
    
    /**
     * 计算逾期利息<br/>
     * <功能详细描述>
     * @param loanAccount
     * @param handler
     * @param tradingRecord
     * @param overdueInterestChargeRecords
     * @return [参数说明]
     * 
     * @return List<ChargeRecordEntry> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public List<ChargeRecordEntry> buildYQLXChargeEntryList(LoanAccount loanAccount, PaymentScheduleHandler handler,
            LATradingRecord tradingRecord, List<OverdueInterestChargeRecord> overdueInterestChargeRecords);
    
    /**
     * 计算扣款失败手续费<br/>
     * <功能详细描述>
     * @param loanAccount
     * @param handler
     * @param tradingRecord
     * @param deductFailChargeRecords
     * @return [参数说明]
     * 
     * @return List<ChargeRecordEntry> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public List<ChargeRecordEntry> buildKQSBSXFEntryList(LoanAccount loanAccount, PaymentScheduleHandler handler,
            LATradingRecord tradingRecord, List<DeductFailChargeRecord> deductFailChargeRecords);
    
    /**
     * 计算外包佣金<br/>
     * <功能详细描述>
     * @param loanAccount
     * @param feeItemMap
     * @param handler
     * @param tradingRecord
     * @param outsourceChargeRecords
     * @return [参数说明]
     * 
     * @return List<ChargeRecordEntry> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public List<ChargeRecordEntry> buildWBYJEntryList(LoanAccount loanAccount, PaymentScheduleHandler handler,
            LATradingRecord tradingRecord, List<OutsourceChargeRecord> outsourceChargeRecords);
    
}
