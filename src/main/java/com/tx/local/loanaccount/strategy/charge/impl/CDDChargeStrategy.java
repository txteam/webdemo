/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2017年7月2日
 * <修改描述:>
 */
package com.tx.local.loanaccount.strategy.charge.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.tx.local.basicdata.model.FeeItemCfg;
import com.tx.local.basicdata.model.FeeItemEnum;
import com.tx.local.loanaccount.handle.PaymentScheduleHandler;
import com.tx.local.loanaccount.model.LATradingRecord;
import com.tx.local.loanaccount.model.LoanAccount;
import com.tx.local.loanaccount.model.LoanAccountFeeItem;
import com.tx.local.loanaccount.model.OverdueInterestChargeRecord;
import com.tx.local.loanaccount.strategy.charge.AbstractChargeStrategy;

/**
 * 仓单贷计费策略<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2017年7月2日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class CDDChargeStrategy extends AbstractChargeStrategy {
    
    /**
     * @param loanAccount
     * @param feeItemMap
     * @param feeItemCfgMap
     * @param handler
     * @param tradingRecord
     * @param recordDate
     * @return
     */
    @Override
    public List<OverdueInterestChargeRecord> buildYQLXRecords(
            LoanAccount loanAccount,
            Map<FeeItemEnum, LoanAccountFeeItem> feeItemMap,
            Map<FeeItemEnum, FeeItemCfg> feeItemCfgMap,
            PaymentScheduleHandler handler, LATradingRecord tradingRecord,
            Date recordDate) {
        
        return null;
    }
    
}
