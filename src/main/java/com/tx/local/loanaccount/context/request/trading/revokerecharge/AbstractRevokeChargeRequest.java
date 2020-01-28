/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2015年2月11日
 * <修改描述:>
 */
package com.tx.local.loanaccount.context.request.trading.revokerecharge;

import com.tx.local.loanaccount.context.request.trading.AbstractRevokeTradingRequest;
import com.tx.local.loanaccount.model.RequestSourceTypeEnum;

/**
 * 基础撤销计费请求<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2015年2月11日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public abstract class AbstractRevokeChargeRequest extends AbstractRevokeTradingRequest {
    
    /** <默认构造函数> */
    public AbstractRevokeChargeRequest(String loanAccountId, String revokeTradingRecordId, String revokeReason) {
        super(loanAccountId, revokeTradingRecordId, revokeReason, RequestSourceTypeEnum.OPER_REQUEST);
    }
}
