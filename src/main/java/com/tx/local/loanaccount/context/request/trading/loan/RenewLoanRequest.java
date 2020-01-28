/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2014年9月3日
 * <修改描述:>
 */
package com.tx.local.loanaccount.context.request.trading.loan;

import java.math.BigDecimal;
import java.util.Map;

import com.tx.local.basicdata.model.FeeItemEnum;
import com.tx.local.loanaccount.model.LATradingRecordTypeEnum;
import com.tx.local.loanaccount.model.LoanAccount;

/**
 * 续贷<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2014年9月3日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class RenewLoanRequest extends AbstractLoanRequest {
    
    /** <默认构造函数> */
    public RenewLoanRequest(LoanAccount loanAccount,
            Map<FeeItemEnum, BigDecimal> feeItem2AmountMap) {
        super(loanAccount, feeItem2AmountMap);
    }
    
    /**
     * @return
     */
    @Override
    public LATradingRecordTypeEnum getTradingRecordType() {
        return LATradingRecordTypeEnum.RENEW_LOAN;
    }
}
