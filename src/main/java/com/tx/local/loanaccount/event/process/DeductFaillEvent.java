/*
 * 描          述:  <描述>
 * 修  改   人:  lenovo
 * 修改时间:  2014年6月22日
 * <修改描述:>
 */
package com.tx.local.loanaccount.event.process;

import java.math.BigDecimal;

import com.tx.component.command.context.CommandRequest;
import com.tx.local.loanaccount.event.LoanAccountProcessEvent;
import com.tx.local.loanaccount.model.LoanAccount;

/**
 * 用户扣款失败事件<br/>
 * 
 * @author lenovo
 * @version [版本号, 2014年6月22日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class DeductFaillEvent extends LoanAccountProcessEvent {
    
    /** 扣款失败金额，该金额可能小于或等于扣款金额 */
    private final BigDecimal deductFailAmount;
    
    /**
     * 用户扣款失败事件<br/>
     * 
     * @param loanAccount 贷款账户
     * @param deductFailAmount 扣款失败金额，该金额可能小于或等于扣款金额
     * @param processRequest 操作请求
     *            
     * @version [版本号, 2015年12月21日]
     * @see [相关类/方法]
     * @since [产品/模块版本]
     * @author rain
     */
    public DeductFaillEvent(LoanAccount loanAccount,
            BigDecimal deductFailAmount, CommandRequest processRequest) {
        super(loanAccount, processRequest);
        this.deductFailAmount = deductFailAmount;
    }
    
    /** 扣款失败金额，该金额可能小于或等于扣款金额 */
    public BigDecimal getDeductAmount() {
        return deductFailAmount;
    }
}
