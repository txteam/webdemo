/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2020年1月5日
 * <修改描述:>
 */
package com.tx.local.message.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 消息到用户的映射关联<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2020年1月5日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Entity
@Table(name = "msg_notice_2_user")
public class Notice2User {
    
    /** 站内消息id */
    @Id
    private String id;
    
    /** 站内消息id */
    private String noticeId;
    
    /** 客户类型:这里的类型可以与具体公告中的不一样，这里只有toClient,toOperator不考虑对角色等的支持 */
    private MsgUserTypeEnum userType;
    
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
     * @return 返回 noticeId
     */
    public String getNoticeId() {
        return noticeId;
    }

    /**
     * @param 对noticeId进行赋值
     */
    public void setNoticeId(String noticeId) {
        this.noticeId = noticeId;
    }

    /**
     * @return 返回 userType
     */
    public MsgUserTypeEnum getUserType() {
        return userType;
    }

    /**
     * @param 对userType进行赋值
     */
    public void setUserType(MsgUserTypeEnum userType) {
        this.userType = userType;
    }

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
