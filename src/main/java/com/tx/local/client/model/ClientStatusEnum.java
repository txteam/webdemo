package com.tx.local.client.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.tx.component.basicdata.model.BasicDataEnum;
import com.tx.component.basicdata.model.BasicDataEnumJsonSerializer;

/**
 * 客户状态<br/>
 * 
 * @author bobby
 * @version [版本号, 2015年11月9日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@JsonSerialize(using = BasicDataEnumJsonSerializer.class)
public enum ClientStatusEnum implements BasicDataEnum {
    
    /** 激活: 激活的账户才能在前端系统进行直接登录 */
    ACTIVATED("ACTIVATED", "激活"),
    
    /** 激活: 激活的账户才能在前端系统进行直接登录 */
    WAIT_ACTIVATE("WAIT_ACTIVATE", "待激活"),
    
    /** 禁用: 禁用的账户不能在前端系统进行登录 */
    DISABLED("DISABLED", "禁用");
    
    private final String name;
    
    private final String key;
    
    private ClientStatusEnum(String key, String name) {
        this.name = name;
        this.key = key;
    }
    
    public static ClientStatusEnum getProcessStatus(String key) {
        for (ClientStatusEnum clientInfoStatusEnum : ClientStatusEnum
                .values()) {
            if (clientInfoStatusEnum.getKey().equals(key)) {
                return clientInfoStatusEnum;
            }
        }
        return null;
    }
    
    /**
     * @return 返回 name
     */
    public String getName() {
        return name;
    }
    
    /**
     * @return 返回 key
     */
    public String getKey() {
        return key;
    }
    
}
