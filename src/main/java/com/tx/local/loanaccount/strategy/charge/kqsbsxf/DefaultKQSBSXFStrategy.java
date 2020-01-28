/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2017年7月9日
 * <修改描述:>
 */
package com.tx.local.loanaccount.strategy.charge.kqsbsxf;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.tx.local.basicdata.model.FeeItemCfg;
import com.tx.local.basicdata.model.FeeItemEnum;
import com.tx.local.loanaccount.handle.PaymentScheduleHandler;
import com.tx.local.loanaccount.model.DeductFailChargeRecord;
import com.tx.local.loanaccount.model.LADeductRecord;
import com.tx.local.loanaccount.model.LATradingRecord;
import com.tx.local.loanaccount.model.LoanAccount;
import com.tx.local.loanaccount.model.LoanAccountFeeItem;
import com.tx.local.loanaccount.service.DeductFailChargeRecordService;
import com.tx.local.loanaccount.strategy.charge.KQSBSXFStrategy;

/**
 * 默认的扣款失败手续费策略<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2017年7月9日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Component("defaultKQSBSXFStrategy")
public class DefaultKQSBSXFStrategy implements KQSBSXFStrategy {
    
    /** 扣款失败手续费计费业务层 */
    @Resource(name = "deductFailChargeRecordService")
    private DeductFailChargeRecordService deductFailChargeRecordService;

    /**
     * @param loanAccount
     * @param feeItemMap
     * @param feeItemCfgMap
     * @param handler
     * @param tradingRecord
     * @param deductTask
     * @return
     */
    @Override
    public List<DeductFailChargeRecord> buildKQSBSXFChargeRecords(LoanAccount loanAccount,
            Map<FeeItemEnum, LoanAccountFeeItem> feeItemMap, Map<FeeItemEnum, FeeItemCfg> feeItemCfgMap,
            PaymentScheduleHandler handler, LATradingRecord tradingRecord, LADeductRecord deductRecord) {
        // TODO Auto-generated method stub
        return null;
    }
}
