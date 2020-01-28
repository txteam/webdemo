/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2014年5月20日
 * <修改描述:>
 */
package com.tx.local.loanaccount.context.request.change;

import com.tx.local.loanaccount.model.LATradingRecordTypeEnum;
import com.tx.local.loanaccount.model.RequestSourceTypeEnum;

/**
 * 改变催收状态请求<br/>
 * 
 * @author  Administrator
 * @version  [版本号, 2014年5月20日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class ChangeToUnlockRequest extends AbstractChangeRequest {
    
    /** <默认构造函数> */
    public ChangeToUnlockRequest(String loanAccountId,
            RequestSourceTypeEnum sourceType) {
        super(loanAccountId, sourceType);
    }
    
    /**
     * @return
     */
    @Override
    public LATradingRecordTypeEnum getTradingRecordType() {
        return LATradingRecordTypeEnum.CHANGE_LOCK_STATUS;
    }
    
}
