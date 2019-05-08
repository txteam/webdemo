/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2016年9月5日
 * <修改描述:>
 */
package com.tx.local.noticemessage.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.tx.component.basicdata.model.BasicDataEnum;
import com.tx.component.basicdata.model.BasicDataEnumJsonSerializer;

/**
 * 站内消息优先级<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2016年9月5日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@JsonSerialize(using = BasicDataEnumJsonSerializer.class)
public enum NoticeMessagePriorityEnum implements BasicDataEnum {
    
    JJ("JJ", "紧急"),
    
    G("G", "高"),
    
    PT("PT", "普通");
    
    /** 关键字 */
    private final String key;
     
    /** 名称 */
    private final String name;
    
    /** <默认构造函数> */
    private NoticeMessagePriorityEnum(String key, String name) {
        this.key = key;
        this.name = name;
    }
    
    /**
     * @return 返回 key
     */
    public String getKey() {
        return key;
    }
    
    /**
     * @return 返回 name
     */
    public String getName() {
        return name;
    }
}
