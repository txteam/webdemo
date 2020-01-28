/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2014年4月26日
 * <修改描述:>
 */
package com.tx.local.loanaccount.context.request.trading.suspend;

import com.tx.local.loanaccount.context.request.trading.repay.AbstractRepayRequest;
import com.tx.local.loanaccount.model.LATradingRecordTypeEnum;
import com.tx.local.loanaccount.model.PaymentSchedule;
import com.tx.local.loanaccount.model.RepayIntention;
import com.tx.local.loanaccount.model.RequestSourceTypeEnum;

/**
 * 暂缓请求<br/>
 *       逾期账户  或  锁定账户
 * 
 * @author Administrator
 * @version [版本号, 2014年4月26日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class SuspendRequest extends AbstractRepayRequest {
    
    /**还款计划*/
    private PaymentSchedule paymentSchedule ;
    
    /** <默认构造函数> */
    public SuspendRequest() {
        super();
    }
    
    /** <默认构造函数> */
    public SuspendRequest(String loanAccountId, RepayIntention repayIntention,PaymentSchedule paymentSchedule ) {
        super(loanAccountId, repayIntention,
                RequestSourceTypeEnum.AUTO_SCHEDULE_REQUEST);
        this.paymentSchedule=paymentSchedule;
    }
    
    /**
     * @return
     */
    @Override
    public LATradingRecordTypeEnum getTradingRecordType() {
        return LATradingRecordTypeEnum.SUSPEND;
    }

    /**
     * @return 返回 paymentSchedule
     */
    public PaymentSchedule getPaymentSchedule() {
        return paymentSchedule;
    }

    /**
     * @param 对paymentSchedule进行赋值
     */
    public void setPaymentSchedule(PaymentSchedule paymentSchedule) {
        this.paymentSchedule = paymentSchedule;
    }
    
    
}
