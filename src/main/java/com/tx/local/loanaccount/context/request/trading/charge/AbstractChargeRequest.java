/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2015年2月11日
 * <修改描述:>
 */
package com.tx.local.loanaccount.context.request.trading.charge;

import com.tx.local.loanaccount.context.request.trading.AbstractTradingRequest;
import com.tx.local.loanaccount.model.RequestSourceTypeEnum;

/**
 * 基础计费请求<br/>
 * 大部分描述"应收"变化的操作,以及其他适用的操作 
 * 
 * @author  Tim.peng
 * @version  [版本号, 2015年2月11日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public abstract class AbstractChargeRequest extends AbstractTradingRequest {
    
    /** 根据"账户ID"、"交易来源"和"贷款账户操作类型"构建交易请求 */
    public AbstractChargeRequest(String loanAccountId,
            RequestSourceTypeEnum sourceType) {
        super(loanAccountId, sourceType);
    }
}
