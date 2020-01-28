/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2014年5月20日
 * <修改描述:>
 */
package com.tx.local.loanaccount.context.request.trading;

import com.tx.core.exceptions.util.AssertUtils;
import com.tx.local.loanaccount.context.request.AbstractLoanAccountAwareRequest;
import com.tx.local.loanaccount.model.RequestSourceTypeEnum;

/**
 * 贷款账户操作请求<br/>
 * 一切跟钱相关的操作都应该从这个从这个请求开始<br/> 
 * 
 * @author  Tim.peng
 * @version  [版本号, 2014年5月20日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public abstract class AbstractTradingRequest extends AbstractLoanAccountAwareRequest {
    
    /** <默认构造函数> */
    public AbstractTradingRequest() {
        super();
    }
    
    /** 根据"账户ID"、"交易来源"和"贷款账户操作类型"构建交易请求 */
    public AbstractTradingRequest(String loanAccountId, RequestSourceTypeEnum sourceType) {
        super(loanAccountId, sourceType);
        AssertUtils.notEmpty(loanAccountId, "loanAccountId is empty.");
    }
}
