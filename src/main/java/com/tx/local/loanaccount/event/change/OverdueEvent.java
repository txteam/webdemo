package com.tx.local.loanaccount.event.change;

import java.util.Date;

import com.tx.local.loanaccount.event.LoanAccountChangeEvent;
import com.tx.local.loanaccount.model.LoanAccount;
/**
 * 
  * 账户发生逾期时，产生的事件
  * 
  * @author  kashima
  * @version  [版本号, 2014-5-25]
  * @see  [相关类/方法]
  * @since  [产品/模块版本]
 */
public class OverdueEvent extends LoanAccountChangeEvent {
    
    /** 逾期发生时间 */
    private Date overdueDate;

    /** <默认构造函数> */
    public OverdueEvent(LoanAccount loanAccount, Date overdueDate) {
        super(loanAccount);
        this.overdueDate = overdueDate;
    }

    /**
     * @return 返回 overdueDate
     */
    public Date getOverdueDate() {
        return overdueDate;
    }

    /**
     * @param 对overdueDate进行赋值
     */
    public void setOverdueDate(Date overdueDate) {
        this.overdueDate = overdueDate;
    }
}
