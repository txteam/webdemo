/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2020年1月13日
 * <修改描述:>
 */
package com.tx.local.message.model;

/**
 * 会话消息关联类型<br/>
 *    当前考虑会话消息可复用于商品等场景，
 *    未来如果遇到这种情况有可能需要新建对应的对象进行存储
 *    只是考虑到系统中一些非核心的需要以应答呈现的对象可以简单用该消息进行替换
 *    保留了topicType的设定
 *    重新考虑了一下，会话主题类型中暂时不考虑会话存在状态的这种情况
 *    一版来说如果会话中存在了状态都是比较特异的场景，这种情况最好新增对象及模块进行描述即可
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2020年1月13日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public enum DialogTopicTypeEnum {
    
    /* 销售商品评论: topicId=销售商品id */
    SALE_PRODUCT;
    
}
