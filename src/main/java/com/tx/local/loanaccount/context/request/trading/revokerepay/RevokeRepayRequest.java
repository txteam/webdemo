/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2014年4月26日
 * <修改描述:>
 */
package com.tx.local.loanaccount.context.request.trading.revokerepay;

import com.tx.local.loanaccount.model.LATradingRecordTypeEnum;
import com.tx.local.loanaccount.model.RequestSourceTypeEnum;

/**
 * 撤销现金还款<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2014年4月26日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class RevokeRepayRequest extends AbstractRevokeRepayRequest {
    
    /** <默认构造函数> */
    public RevokeRepayRequest() {
        super();
    }
    
    /** <默认构造函数> */
    public RevokeRepayRequest(String loanAccountId, String revokeTradingRecordId, String revokeReason,
            RequestSourceTypeEnum sourceType) {
        super(loanAccountId, revokeTradingRecordId, revokeReason, sourceType);
    }
    
    /**
     * @return
     */
    @Override
    public LATradingRecordTypeEnum getTradingRecordType() {
        return getRevokeTradingRecord().getType().getRevokeTradingRecordType();
    }
}
