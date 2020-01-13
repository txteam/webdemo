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
    
    /** 删除时间 */
    //去除删除标志位的设计，考虑消息会逐步累积增多，删除就将该映射关系移除至历史表即可
    ///** 是否已删除(已删除的消息不再进行显示) */
    //private boolean deleted;
    private Date deleteDate;

    /** 是否置顶 */
    private boolean istop;
}
