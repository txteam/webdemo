/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2014年7月29日
 * <修改描述:>
 */
package com.tx.local.loanaccount.context.request.trading.revokeexempt;

import com.tx.local.loanaccount.model.LATradingRecordTypeEnum;

/**
 * 撤销还款豁免<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2014年7月29日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class RevokeExemptRequest extends AbstractRevokeExemptRequest {
    
    /** <默认构造函数> */
    public RevokeExemptRequest() {
        super();
    }
    
    /** <默认构造函数> */
    public RevokeExemptRequest(String loanAccountId, String revokeTradingRecordId, String revokeReason) {
        super(loanAccountId, revokeTradingRecordId, revokeReason);
    }
    
    /**
     * @return
     */
    @Override
    public LATradingRecordTypeEnum getTradingRecordType() {
        return LATradingRecordTypeEnum.REVOKE_EXEMPT;
    }
}
