/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2017年7月9日
 * <修改描述:>
 */
package com.tx.local.loanaccount.strategy.charge;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.tx.component.strategy.context.Strategy;
import com.tx.local.basicdata.model.FeeItemCfg;
import com.tx.local.basicdata.model.FeeItemEnum;
import com.tx.local.loanaccount.handle.PaymentScheduleHandler;
import com.tx.local.loanaccount.model.LATradingRecord;
import com.tx.local.loanaccount.model.LoanAccount;
import com.tx.local.loanaccount.model.LoanAccountFeeItem;
import com.tx.local.loanaccount.model.OverdueInterestChargeRecord;

/**
  * 外包佣金策略实现<br/>
  * <功能详细描述>
  * 
  * @author  Administrator
  * @version  [版本号, 2017年7月9日]
  * @see  [相关类/方法]
  * @since  [产品/模块版本]
  */
public interface WBYJChargeStrategy extends Strategy {
    
    /**
     * 构建逾期利息计费记录<br/>
     * <功能详细描述>
     * @param loanAccount
     * @param feeItemMap
     * @param feeItemCfgMap
     * @param handler
     * @param tradingRecord
     * @param recordDate [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public List<OverdueInterestChargeRecord> buildWBYJRecords(LoanAccount loanAccount,
            Map<FeeItemEnum, LoanAccountFeeItem> feeItemMap, Map<FeeItemEnum, FeeItemCfg> feeItemCfgMap,
            PaymentScheduleHandler handler, LATradingRecord tradingRecord, Date recordDate);
}
