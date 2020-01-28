package com.tx.local.loanaccount.context.request.change;

import java.util.Date;

import com.tx.local.loanaccount.model.LATradingRecordTypeEnum;
import com.tx.local.loanaccount.model.RequestSourceTypeEnum;

/**
 * 逾期账户 Request <br/>
 * 
 * @author  lenovo
 * @version  [版本号, 2014年6月11日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class ChangeToOverdueRequest extends AbstractChangeRequest {
    
    /** 账户逾期日期 */
    private final Date overdueDate;
    
    public ChangeToOverdueRequest(String loanAccountId,Date overdueDate,
            RequestSourceTypeEnum sourceType) {
        super(loanAccountId, sourceType);
        this.overdueDate = overdueDate;
    }
    
    /**
     * @return 返回 overdueDate
     */
    public Date getOverdueDate() {
        return overdueDate;
    }
    
    @Override
    public LATradingRecordTypeEnum getTradingRecordType() {
        return LATradingRecordTypeEnum.CHANGE_OVERDUE_INFO;
    }
}
