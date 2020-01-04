/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2020年1月5日
 * <修改描述:>
 */
package com.tx.local.message.model;

import java.util.Date;

/**
 * <功能简述>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2020年1月5日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class Notice2UserDetail extends Notice {
    
    /** 用户id */
    private String userId;
    
    /** 接收消息时间 */
    private Date receiveDate;
    
    /** 是否阅读 */
    //注: read的被动语态也是read
    private boolean read;
    
    /** 阅读时间 */
    private Date readDate;
    
    /** 是否已删除(已删除的消息不再进行显示) */
    private boolean deleted;
    
    /** 删除时间 */
    private Date deleteDate;
    
    /**
     * @return 返回 userId
     */
    public String getUserId() {
        return userId;
    }
    
    /**
     * @param 对userId进行赋值
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    /**
     * @return 返回 receiveDate
     */
    public Date getReceiveDate() {
        return receiveDate;
    }
    
    /**
     * @param 对receiveDate进行赋值
     */
    public void setReceiveDate(Date receiveDate) {
        this.receiveDate = receiveDate;
    }
    
    /**
     * @return 返回 read
     */
    public boolean isRead() {
        return read;
    }
    
    /**
     * @param 对read进行赋值
     */
    public void setRead(boolean read) {
        this.read = read;
    }
    
    /**
     * @return 返回 readDate
     */
    public Date getReadDate() {
        return readDate;
    }
    
    /**
     * @param 对readDate进行赋值
     */
    public void setReadDate(Date readDate) {
        this.readDate = readDate;
    }
    
    /**
     * @return 返回 deleted
     */
    public boolean isDeleted() {
        return deleted;
    }
    
    /**
     * @param 对deleted进行赋值
     */
    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
    
    /**
     * @return 返回 deleteDate
     */
    public Date getDeleteDate() {
        return deleteDate;
    }
    
    /**
     * @param 对deleteDate进行赋值
     */
    public void setDeleteDate(Date deleteDate) {
        this.deleteDate = deleteDate;
    }
}
