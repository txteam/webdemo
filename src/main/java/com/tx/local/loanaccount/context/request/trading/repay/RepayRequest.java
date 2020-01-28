/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2014年4月26日
 * <修改描述:>
 */
package com.tx.local.loanaccount.context.request.trading.repay;

import com.tx.core.exceptions.util.AssertUtils;
import com.tx.local.loanaccount.model.LATradingRecordTypeEnum;
import com.tx.local.loanaccount.model.RepayIntention;
import com.tx.local.loanaccount.model.RequestSourceTypeEnum;

/**
 * 现金还款<br/>
 * 
 * @author  Administrator
 * @version  [版本号, 2014年4月26日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class RepayRequest extends AbstractRepayRequest {
    
    /** <默认构造函数> */
    public RepayRequest() {
        super();
    }
    
    /** <默认构造函数> */
    public RepayRequest(String loanAccountId, RepayIntention repayIntention) {
        super(loanAccountId, repayIntention, RequestSourceTypeEnum.OPER_REQUEST);
    }
    
    /**
     * @return
     */
    @Override
    public LATradingRecordTypeEnum getTradingRecordType() {
        AssertUtils.notNull(getBankAccount(), "bankAccount is null.");
        AssertUtils.notNull(getBankAccount().getBankAccountType(), "bankAccount.bankAccountType is null.");
        AssertUtils.notNull(getBankAccount().getBankAccountType().getRepayIntentionType(),
                "repayIntention.bankAccountType is null.");
        AssertUtils.notNull(getBankAccount().getBankAccountType().getRepayIntentionType().getTradingRecordType(),
                "repayIntention.bankAccountType.tradingRecordType is null.");
        
        LATradingRecordTypeEnum tradingRecordType = getBankAccount().getBankAccountType()
                .getRepayIntentionType()
                .getTradingRecordType();
        return tradingRecordType;
    }
}
