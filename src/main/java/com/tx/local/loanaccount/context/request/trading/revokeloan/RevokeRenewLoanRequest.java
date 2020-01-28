/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2014年4月29日
 * <修改描述:>
 */
package com.tx.local.loanaccount.context.request.trading.revokeloan;

import com.tx.local.loanaccount.model.LATradingRecordTypeEnum;

/**
 * 续贷退回<br/>
 * 
 * @author  Administrator
 * @version  [版本号, 2014年4月29日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class RevokeRenewLoanRequest extends AbstractRevokeLoanRequest {
    
    /** <默认构造函数> */
    public RevokeRenewLoanRequest(String loanAccountId, String revokeTradingRecordId, String revokeReason) {
        super(loanAccountId, revokeTradingRecordId, revokeReason);
    }
    
    /**
     * @return
     */
    @Override
    public LATradingRecordTypeEnum getTradingRecordType() {
        return LATradingRecordTypeEnum.REVOKE_RENEW_LOAN;
    }
}
