/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2017年2月10日
 * <修改描述:>
 */
package com.tx.component.remote.model;

import java.io.Serializable;

/**
 * 远程调用结果<br/>
 * <功能详细描述>
 *
 * @author Administrator
 * @version [版本号, 2017年2月10日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public enum RemoteResultCode implements Serializable {
    SUCCESS("SUCCESS", 0, "成功"),
    VALIDATE_PARAM_EMPTY("VALIDATE_PARAM_EMPTY", 101, "参数空验证"),
    VALIDATE_PARAM_INVALID("VALIDATE_PARAM_INVALID", 102, "参数非法验证"),
    VALIDATE_REPEAT("VALIDATE_REPEAT", 103, "重复验证"),

    SERVICE_FAIL("SERVICE_FAIL", 201, "业务处理失败"),
    SERVICE_PROCESSING("SERVICE_PROCESSING", 202, "业务处理中"),

    SYSTEM_ERROR("SYSTEM_ERROR", 999, "系统错误"),;

    private final String key;
    private final int code;
    private final String desc;

    RemoteResultCode(String key, int code, String desc) {
        this.key = key;
        this.code = code;
        this.desc = desc;
    }

    public String getKey() {
        return key;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
