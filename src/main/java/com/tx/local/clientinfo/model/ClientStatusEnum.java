package com.tx.local.clientinfo.model;

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
    
    /** 编码 */
    private final String code;
    
    /** 名称 */
    private final String name;
    
    /** <默认构造函数> */
    private ClientStatusEnum(String code, String name) {
        this.name = name;
        this.code = code;
    }
    
    /**
     * @return 返回 name
     */
    public String getName() {
        return name;
    }
    
    /**
     * @return 返回 code
     */
    public String getCode() {
        return code;
    }
}
