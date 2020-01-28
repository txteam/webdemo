/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2014年6月16日
 * <修改描述:>
 */
package com.tx.local.loanaccount.context.request.trading.repay;

import com.tx.local.loanaccount.model.LATradingRecordTypeEnum;
import com.tx.local.loanaccount.model.RepayIntention;
import com.tx.local.loanaccount.model.RequestSourceTypeEnum;

/**
 * 超额还款<br/>
 * 
 * @author  Tim.peng
 * @version  [版本号, 2014年6月16日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class OverRepayRequest extends AbstractRepayRequest {
    
    /** 是否到账 */
    private boolean received = true;
    
    /** <默认构造函数> */
    public OverRepayRequest(String loanAccountId,
            RepayIntention repayIntention, boolean received,
            RequestSourceTypeEnum sourceType) {
        super(loanAccountId, repayIntention, sourceType);
        this.received = received;
    }
    
    /** <默认构造函数> */
    public OverRepayRequest(String loanAccountId,
            RepayIntention repayIntention, RequestSourceTypeEnum sourceType) {
        super(loanAccountId, repayIntention, sourceType);
    }
    
    /** <默认构造函数> */
    public OverRepayRequest() {
        super();
    }
    
    /**
     * @return 返回 received
     */
    public boolean isReceived() {
        return received;
    }
    
    /**
     * @param 对received进行赋值
     */
    public void setReceived(boolean received) {
        this.received = received;
    }
    
    /**
     * @return
     */
    @Override
    public LATradingRecordTypeEnum getTradingRecordType() {
        return LATradingRecordTypeEnum.OVER_REPAY;
    }
}
