/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2014年4月29日
 * <修改描述:>
 */
package com.tx.local.loanaccount.context.request.trading.loan;

import java.math.BigDecimal;
import java.util.Map;

import com.tx.local.basicdata.model.FeeItemEnum;
import com.tx.local.loanaccount.model.LATradingRecordTypeEnum;
import com.tx.local.loanaccount.model.LoanAccount;

/**
 * 新贷<br/>
 * 
 * @author  Administrator
 * @version  [版本号, 2017年6月25日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class NewLoanRequest extends AbstractLoanRequest {
    
    /** <默认构造函数> */
    public NewLoanRequest() {
        super();
    }
    
    /** <默认构造函数> */
    public NewLoanRequest(LoanAccount loanAccount,
            Map<FeeItemEnum, BigDecimal> feeItem2AmountMap) {
        super(loanAccount, feeItem2AmountMap);
    }
    
    /**
     * @return
     */
    @Override
    public LATradingRecordTypeEnum getTradingRecordType() {
        return LATradingRecordTypeEnum.NEW_LOAN;
    }
    
}
