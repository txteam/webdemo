/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2014年6月27日
 * <修改描述:>
 */
package com.tx.local.loanaccount.context.request.trading.revokerepay;

import com.tx.local.loanaccount.model.LATradingRecordTypeEnum;
import com.tx.local.loanaccount.model.RequestSourceTypeEnum;

/**
 * 撤销超额还款<br/>
 * 
 * @author Administrator
 * @version [版本号, 2014年6月27日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class RevokeOverRepayRequest extends AbstractRevokeRepayRequest {
    
    /** <默认构造函数> */
    public RevokeOverRepayRequest() {
        super();
    }
    
    /** <默认构造函数> */
    public RevokeOverRepayRequest(String loanAccountId, String revokeTradingRecordId, String revokeReason) {
        super(loanAccountId, revokeTradingRecordId, revokeReason, RequestSourceTypeEnum.OPER_REQUEST);
    }
    
    /**
     * @return
     */
    @Override
    public LATradingRecordTypeEnum getTradingRecordType() {
        return LATradingRecordTypeEnum.REVOKE_OVER_REPAY;
    }
    
}
