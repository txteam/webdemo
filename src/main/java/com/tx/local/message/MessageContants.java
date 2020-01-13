/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2020年1月4日
 * <修改描述:>
 */
package com.tx.local.message;


/**
 * 消息常量<br/>
 *   划分标准： 发送的消息，能够以4000字以内的字段进行存储
 *   消息模块功能：能够抽象为三种类型： 问答类型(建议回复型)、通知类型(接收者可能为一个群体)、发送接收型(消息有具体的发送者，接收者)
 *   问答类型被抽象为：会话消息 CommMessage(CommunicationMessage)
 *   通知类型被抽象为：通知消息 NoticeMessage
 *   发送接收型：一般为发送方发送消息后，接收方接收消息，以两张表进行管理。无具体的类型，状态，优先级的概念，接收消息可以没有发送方，比如提醒
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2020年1月4日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface MessageContants {
    
}
