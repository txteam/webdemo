/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2020年1月5日
 * <修改描述:>
 */
package com.tx.local.message.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 消息到用户的映射关联<br/>
 *  //参考：https://blog.csdn.net/weixin_36910300/article/details/79104536
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
    @Column(nullable = false, length = 64)
    private String noticeId;
    
    /** 客户类型:这里的类型可以与具体公告中的不一样，这里只有toClient,toOperator不考虑对角色等的支持 */
    @Column(nullable = false, length = 64)
    private MessageUserTypeEnum userType;
    
    /** 用户id */
    @Column(nullable = false, length = 64)
    private String userId;
    
    /** 是否阅读 */
    //注: read的被动语态也是read,但read在创建脚本时为特殊字符需要添添加引号才能创建表成功
    private boolean unread;
    
    /** 是否置顶 */
    private boolean top;
    
    /** 阅读时间 */
    private Date readDate;

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
    public MessageUserTypeEnum getUserType() {
        return userType;
    }

    /**
     * @param 对userType进行赋值
     */
    public void setUserType(MessageUserTypeEnum userType) {
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
     * @return 返回 unread
     */
    public boolean isUnread() {
        return unread;
    }

    /**
     * @param 对unread进行赋值
     */
    public void setUnread(boolean unread) {
        this.unread = unread;
    }

    /**
     * @return 返回 top
     */
    public boolean isTop() {
        return top;
    }

    /**
     * @param 对top进行赋值
     */
    public void setTop(boolean top) {
        this.top = top;
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
}
