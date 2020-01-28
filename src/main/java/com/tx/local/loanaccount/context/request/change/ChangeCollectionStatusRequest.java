/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2014年5月20日
 * <修改描述:>
 */
package com.tx.local.loanaccount.context.request.change;

import com.tx.local.loanaccount.model.CollectionStatusEnum;
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
public class ChangeCollectionStatusRequest extends AbstractChangeRequest {
    
    /** 催收状态枚举 */
    private CollectionStatusEnum collectionStatus;
    
    /** 是否死亡 */
    private Boolean isClosed;
    
    /** 是否法律程序 */
    private Boolean isLegalProcedure;
    
    /** 是否死亡 */
    private Boolean isDied;
    
    /** <默认构造函数> */
    public ChangeCollectionStatusRequest(String loanAccountId, RequestSourceTypeEnum sourceType) {
        super(loanAccountId, sourceType);
    }
    
    /** <默认构造函数> */
    public ChangeCollectionStatusRequest(String loanAccountId, CollectionStatusEnum collectionStatus,
            RequestSourceTypeEnum sourceType) {
        super(loanAccountId, sourceType);
        this.collectionStatus = collectionStatus;
    }
    
    /** <默认构造函数> */
    public ChangeCollectionStatusRequest(String loanAccountId, CollectionStatusEnum collectionStatus, Boolean isClosed,
            Boolean isLegalProcedure, Boolean isDied, RequestSourceTypeEnum sourceType) {
        super(loanAccountId, sourceType);
        this.collectionStatus = collectionStatus;
        this.isClosed = isClosed;
        this.isLegalProcedure = isLegalProcedure;
        this.isDied = isDied;
    }
    
    /**
     * @return 返回 collectionStatus
     */
    public CollectionStatusEnum getCollectionStatus() {
        return collectionStatus;
    }
    
    /**
     * @param 对collectionStatus进行赋值
     */
    public void setCollectionStatus(CollectionStatusEnum collectionStatus) {
        this.collectionStatus = collectionStatus;
    }
    
    /**
     * @return 返回 isClosed
     */
    public Boolean getIsClosed() {
        return isClosed;
    }
    
    /**
     * @param 对isClosed进行赋值
     */
    public void setIsClosed(Boolean isClosed) {
        this.isClosed = isClosed;
    }
    
    /**
     * @return 返回 isLegalProcedure
     */
    public Boolean getIsLegalProcedure() {
        return isLegalProcedure;
    }
    
    /**
     * @param 对isLegalProcedure进行赋值
     */
    public void setIsLegalProcedure(Boolean isLegalProcedure) {
        this.isLegalProcedure = isLegalProcedure;
    }
    
    /**
     * @return 返回 isDied
     */
    public Boolean getIsDied() {
        return isDied;
    }
    
    /**
     * @param 对isDied进行赋值
     */
    public void setIsDied(Boolean isDied) {
        this.isDied = isDied;
    }
    
    /** 交易记录类型 */
    @Override
    public LATradingRecordTypeEnum getTradingRecordType() {
        return LATradingRecordTypeEnum.CHANGE_COLLECTION_INFO;
    }
}
