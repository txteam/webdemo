/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2020年1月14日
 * <修改描述:>
 */
package com.tx.local.message.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;

/**
 * 消息附件<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2020年1月14日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Entity
@Table(name = "msg_attachment")
@ApiModel("消息附件")
public class Msg2Attachment {
    
    private String id;
    
    private MsgTypeEnum msgType;
    
    private String messageId;
    
    private String attachmentId;
}
