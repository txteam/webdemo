/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2020年1月4日
 * <修改描述:>
 */
package com.tx.local.message.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 会话消息：包含请求、意见、建议、提问<br/>
 *      CommunicationMessage
 * 包含请求、意见、建议、提问、回答、回复、等内容<br/>
 * 通过catalog进行分类，可以存在状态
 * 状态归属为具体的分类
 * 可通过配置进行初始化
 *    ：信息发布分为两种，允许html的内容以及不允许的
 *    客户发送的信息需要经不允许的接口填入，防止持久性注入攻击
 * 
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2020年1月4日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Entity
@Table(name = "msg_comm_message")
@ApiModel("会话消息")
@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommMessage implements Serializable {
    
    /** 注释内容 */
    private static final long serialVersionUID = 760874841288229239L;
    
    /** 主键  */
    @Id
    @Column(nullable = false)
    private String id;
    
    /** 所属虚中心id */
    @Column(nullable = false)
    private String vcid;
    
    /** 回复信息时，填入被回复的信息 */
    @Column(nullable = true)
    private String parentId;
    
    /** 分类 */
    @Column(name = "catalogId", nullable = false)
    private CommMessageCatalog catalog;
    
    /** 状态 */
    @Column(name = "statusId", nullable = true)
    private CommMessageStatus status;
    
    /** 关联类型 */
    @Column(nullable = false)
    private CommMessageTopicTypeEnum topicType;
    
    /** 关联id */
    @Column(nullable = true)
    private String topicId;
    
    /** 客户类型 */
    @Column(nullable = false)
    private MsgUserTypeEnum userType;
    
    /** 用户id */
    @Column(nullable = true)
    private String userId;
    
    /** 站内消息标题 */
    @Column(nullable = false, updatable = true, length = 100)
    private String title;
    
    /** 站内消息内容 */
    @Column(length = 4000)
    private String content;
    
    /** 最后修改时间 */
    @Column(nullable = false, updatable = true)
    private Date lastUpdateDate;
    
    /** 最后更新用户 */
    @Column(nullable = true, updatable = true)
    private String lastUpdateUserId;
    
    /** 创建时间 */
    @Column(nullable = false, updatable = false)
    private Date createDate;
    
    /** 创建用户 */
    @Column(nullable = true, updatable = false)
    private String createUserId;
}
