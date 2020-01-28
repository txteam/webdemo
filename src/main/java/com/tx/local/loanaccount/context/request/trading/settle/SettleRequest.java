/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2014年7月25日
 * <修改描述:>
 */
package com.tx.local.loanaccount.context.request.trading.settle;

import com.tx.core.exceptions.util.AssertUtils;
import com.tx.core.util.DateUtils;
import com.tx.local.loanaccount.model.LATradingRecordTypeEnum;
import com.tx.local.loanaccount.model.LoanAccount;
import com.tx.local.loanaccount.model.SettleIntention;

/**
 * 提前结清<br/>
 * 
 * @author  Tim.peng
 * @version  [版本号, 2014年7月25日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class SettleRequest extends AbstractSettleRequest {
    
    /** <默认构造函数> */
    public SettleRequest() {
        super();
    }
    
    /** <默认构造函数> */
    public SettleRequest(String loanAccountId, SettleIntention settleIntention) {
        super(loanAccountId, settleIntention);
    }
    
    /**
     * @return
     */
    @Override
    public LATradingRecordTypeEnum getTradingRecordType() {
        LoanAccount loanAccount = getLoanAccount();
        SettleIntention settleIntention = getSettleIntention();
        AssertUtils.notNull(loanAccount, "loanAccount is null.");
        AssertUtils.notNull(settleIntention, "settleIntention is null.");
        AssertUtils.notNull(settleIntention.getRepayDate(), "settleIntention.repayDate is null.");
        
        if (DateUtils.compareByDay(settleIntention.getRepayDate(), loanAccount.getExpiryDate()) >= 0) {
            return LATradingRecordTypeEnum.ONCE_SETTLE;
        } else {
            return LATradingRecordTypeEnum.EARLY_SETTLE;
        }
    }
    
}
