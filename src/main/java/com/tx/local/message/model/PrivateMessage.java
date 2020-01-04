/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2020年1月4日
 * <修改描述:>
 */
package com.tx.local.message.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;

/**
 * 私信<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2020年1月4日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Entity
@Table(name = "prm_private_message")
@ApiModel("私信")
public class PrivateMessage {
    
    /** 私信id */
    private String id;
    
    /** 接收人id */
    private String userId;
    
    /** 接收人类型 */
    private String userType;
    
    /** 发送人类型 */
    private String sendUserType;
    
    /** 发送人id */
    private String sendUserId;
    
    private Date createDate;
}
