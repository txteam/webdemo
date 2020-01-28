/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2014年5月20日
 * <修改描述:>
 */
package com.tx.local.loanaccount.context.request.change;

import com.tx.local.loanaccount.model.LATradingRecordTypeEnum;
import com.tx.local.loanaccount.model.RequestSourceTypeEnum;
import com.tx.local.loanaccount.model.SettleInterestStatusEnum;

/**
 * 改变催收状态请求<br/>
 * 
 * @author  Administrator
 * @version  [版本号, 2014年5月20日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class ChangeSettleInterestStatusRequest extends AbstractChangeRequest {
    
    /** 催收状态枚举 */
    private SettleInterestStatusEnum settleInterestStatus;;
    
    /** <默认构造函数> */
    public ChangeSettleInterestStatusRequest(String loanAccountId, SettleInterestStatusEnum settleInterestStatus,
            RequestSourceTypeEnum sourceType) {
        super(loanAccountId, sourceType);
        this.settleInterestStatus = settleInterestStatus;
    }
    
    /**
     * @return 返回 settleInterestStatus
     */
    public SettleInterestStatusEnum getSettleInterestStatus() {
        return settleInterestStatus;
    }
    
    /**
     * @param 对settleInterestStatus进行赋值
     */
    public void setSettleInterestStatus(SettleInterestStatusEnum settleInterestStatus) {
        this.settleInterestStatus = settleInterestStatus;
    }
    
    @Override
    public LATradingRecordTypeEnum getTradingRecordType() {
        return LATradingRecordTypeEnum.CHANGE_SETTLE_INTEREST_INFO;
    }
}
