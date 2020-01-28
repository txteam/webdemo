/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2014年6月10日
 * <修改描述:>
 */
package com.tx.local.loanaccount.context.request.trading;

import com.tx.local.loanaccount.context.request.RevokeTradingRecordAware;
import com.tx.local.loanaccount.model.LATradingRecord;
import com.tx.local.loanaccount.model.RequestSourceTypeEnum;

/**
 * 基础请求撤销类<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2014年6月10日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public abstract class AbstractRevokeTradingRequest extends AbstractTradingRequest implements RevokeTradingRecordAware {
    
    /** 撤销的交易记录 */
    private transient LATradingRecord revokeTradingRecord;
    
    /** 交易id */
    private String revokeTradingRecordId;
    
    /** 撤销原因 */
    private String revokeReason;
    
    /** 是否可见：当前产生的撤销交易 */
    private boolean viewAbleOfRevokedTradingRecord;
    
    /** <默认构造函数> */
    public AbstractRevokeTradingRequest() {
        super();
    }
    
    /** <默认构造函数> */
    public AbstractRevokeTradingRequest(String loanAccountId, String revokeTradingRecordId, String revokeReason,
            RequestSourceTypeEnum sourceType) {
        super(loanAccountId, sourceType);
        this.revokeTradingRecordId = revokeTradingRecordId;
        this.revokeReason = revokeReason;
        this.viewAbleOfRevokedTradingRecord = true;
    }
    
    /** <默认构造函数> */
    public AbstractRevokeTradingRequest(String loanAccountId, String revokeTradingRecordId, String revokeReason,
            boolean viewAble, RequestSourceTypeEnum sourceType) {
        super(loanAccountId, sourceType);
        this.revokeTradingRecordId = revokeTradingRecordId;
        this.revokeReason = revokeReason;
        this.viewAbleOfRevokedTradingRecord = viewAble;
    }
    
    /**
     * @return 返回 revokeTradingRecordId
     */
    public String getRevokeTradingRecordId() {
        return revokeTradingRecordId;
    }
    
    /**
     * @return 返回 revokeReason
     */
    public String getRevokeReason() {
        return revokeReason;
    }
    
    /**
     * @return 返回 revokeTradingRecord
     */
    public LATradingRecord getRevokeTradingRecord() {
        return revokeTradingRecord;
    }
    
    /**
     * @param 对revokeTradingRecord进行赋值
     */
    public void setRevokeTradingRecord(LATradingRecord revokeTradingRecord) {
        this.revokeTradingRecord = revokeTradingRecord;
    }
    
    /**
     * @param 对revokeTradingRecordId进行赋值
     */
    public void setRevokeTradingRecordId(String revokeTradingRecordId) {
        this.revokeTradingRecordId = revokeTradingRecordId;
    }
    
    /**
     * @param 对revokeReason进行赋值
     */
    public void setRevokeReason(String revokeReason) {
        this.revokeReason = revokeReason;
    }
    
    /**
     * @return 返回 viewAbleOfRevokedTradingRecord
     */
    public boolean isViewAbleOfRevokedTradingRecord() {
        return viewAbleOfRevokedTradingRecord;
    }
    
    /**
     * @param 对viewAbleOfRevokedTradingRecord进行赋值
     */
    public void setViewAbleOfRevokedTradingRecord(boolean viewAbleOfRevokedTradingRecord) {
        this.viewAbleOfRevokedTradingRecord = viewAbleOfRevokedTradingRecord;
    }
    
}
