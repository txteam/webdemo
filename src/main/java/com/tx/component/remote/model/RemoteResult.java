/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2017年2月10日
 * <修改描述:>
 */
package com.tx.component.remote.model;

import com.alibaba.fastjson.annotation.JSONField;

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
public class RemoteResult implements Serializable {

    /**
     * 注释内容
     */
    private static final long serialVersionUID = 7852301170871851821L;

    @JSONField(ordinal = 1)
    /** 远程调用结果 */
    private boolean result;

    @JSONField(ordinal = 2)
    /** 错误编码 */
    private int errorCode;

    @JSONField(ordinal = 3)
    /** 错误消息 */
    private String errorMessage;

    @JSONField(ordinal = 4)
    /** 返回的数据 */
    private Object data;

    /**
     * <默认构造函数>
     */
    public RemoteResult() {
        super();
        this.result = false;
        this.errorCode = -1;
        this.errorMessage = null;
    }

    /**
     * <默认构造函数>
     */
    public RemoteResult(Object data) {
        super();
        this.result = true;
        this.errorCode = -1;
        this.errorMessage = null;
        this.data = data;
    }

    /**
     * <默认构造函数>
     */
    public RemoteResult(int errorCode, String errorMessage) {
        super();
        this.result = false;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.data = null;
    }

    /**
     * <构造函数>
     */
    public RemoteResult(boolean result, int errorCode, String errorMessage,
                        Object data) {
        super();
        this.result = result;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.data = data;
    }

    public static RemoteResult SUCCESS(Object data) {
        RemoteResult remoteResult = new RemoteResult(true, 0, "成功", data);
        return remoteResult;
    }

    public static RemoteResult FAIL(int errorCode, String errorMessage) {
        RemoteResult remoteResult = new RemoteResult(false, errorCode, errorMessage,null);
        return remoteResult;
    }

    public static RemoteResult FAIL(int errorCode, String errorMessage,Object data) {
        RemoteResult remoteResult = new RemoteResult(false, errorCode, errorMessage,data);
        return remoteResult;
    }

    /**
     * @return 返回 errorCode
     */
    public int getErrorCode() {
        return errorCode;
    }

    /**
     * @return 返回 result
     */
    public boolean isResult() {
        return result;
    }

    /**
     * @return 返回 errorMessage
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * @return 返回 data
     */
    public Object getData() {
        return data;
    }

    /**
     * @param 对result进行赋值
     */
    public void setResult(boolean result) {
        this.result = result;
    }

    /**
     * @param 对errorCode进行赋值
     */
    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * @param 对errorMessage进行赋值
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * @param 对data进行赋值
     */
    public void setData(Object data) {
        this.data = data;
    }
}
