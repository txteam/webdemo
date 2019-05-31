/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2016年9月5日
 * <修改描述:>
 */
package com.tx.local.noticemessage.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.tx.core.jdbc.sqlsource.annotation.UpdateAble;

/**
 * 站内消息<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2016年9月5日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Entity
@Table(name = "nm_message_2_client")
public class NoticeMessage2Client {
    
    /** 站内消息id */
    @Id
    private String id;
    
    /** 站内消息id */
    private String noticeMessageId;
    
    /** 客户类型 */
    private NoticeMessageClientTypeEnum clientType;
    
    /** 客户id */
    private String clientId;
    
    /** 接收消息时间 */
    private Date receiveDate;
    
    /** 是否阅读 */
    @UpdateAble
    private boolean readFlag;
    
    /** 阅读时间 */
    @UpdateAble
    private Date readDate;
    
    /** 是否删除 */
    @UpdateAble
    private boolean deleteFlag;
    
    /** 删除时间 */
    @UpdateAble
    private Date deleteDate;
    
    /**
     * @return 返回 id
     */
    public String getId() {
        return id;
    }
    
    /**
     * @param 对id进行赋值
     */
    public void setId(String id) {
        this.id = id;
    }
    
    /**
     * @return 返回 noticeMessageId
     */
    public String getNoticeMessageId() {
        return noticeMessageId;
    }
    
    /**
     * @param 对noticeMessageId进行赋值
     */
    public void setNoticeMessageId(String noticeMessageId) {
        this.noticeMessageId = noticeMessageId;
    }
    
    /**
     * @return 返回 clientType
     */
    public NoticeMessageClientTypeEnum getClientType() {
        return clientType;
    }
    
    /**
     * @param 对clientType进行赋值
     */
    public void setClientType(NoticeMessageClientTypeEnum clientType) {
        this.clientType = clientType;
    }
    
    /**
     * @return 返回 clientId
     */
    public String getClientId() {
        return clientId;
    }
    
    /**
     * @param 对clientId进行赋值
     */
    public void setClientId(String clientId) {
        this.clientId = clientId;
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
     * @return 返回 readFlag
     */
    public boolean isReadFlag() {
        return readFlag;
    }
    
    /**
     * @param 对readFlag进行赋值
     */
    public void setReadFlag(boolean readFlag) {
        this.readFlag = readFlag;
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
     * @return 返回 deleteFlag
     */
    public boolean isDeleteFlag() {
        return deleteFlag;
    }
    
    /**
     * @param 对deleteFlag进行赋值
     */
    public void setDeleteFlag(boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
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
