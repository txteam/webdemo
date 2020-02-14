/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2020年1月5日
 * <修改描述:>
 */
package com.tx.local.message.model;

import java.io.Serializable;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.tx.component.basicdata.model.BasicDataEnumJsonSerializer;

/**
 * 消息用户类型枚举<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2020年1月5日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@JsonSerialize(using = BasicDataEnumJsonSerializer.class)
public enum MessageUserTypeEnum implements Serializable {
    
    CLIENT_ALL,
    
    CLIENT,
    
    CLIENT_ROLE,
    
    OPERATOR_ALL,
    
    OPERATOR,
    
    OPERATOR_ROLE;
}
