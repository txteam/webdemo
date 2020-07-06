/*
 * 描          述:  <描述>
 * 修  改   人:  PengQingyang
 * 修改时间:  2020年6月11日
 * <修改描述:>
 */
package com.tx.local.qxb.client;

import java.io.Serializable;

/**
 * 企信宝远程调用结果<br/>
 * <功能详细描述>
 * 
 * @author  PengQingyang
 * @version  [版本号, 2020年6月11日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class QxbRemoteResult<T> implements Serializable {
    
    /** 注释内容 */
    private static final long serialVersionUID = 8256186153454445119L;
    
    /** 状态值 */
    private int status;
    
    /** 消息结果 */
    private String message;
    
    /** 签名 */
    private String sign;
    
    /** 数据 */
    private T data;
    
    /**
     * @return 返回 status
     */
    public int getStatus() {
        return status;
    }
    
    /**
     * @param 对status进行赋值
     */
    public void setStatus(int status) {
        this.status = status;
    }
    
    /**
     * @return 返回 message
     */
    public String getMessage() {
        return message;
    }
    
    /**
     * @param 对message进行赋值
     */
    public void setMessage(String message) {
        this.message = message;
    }
    
    /**
     * @return 返回 sign
     */
    public String getSign() {
        return sign;
    }
    
    /**
     * @param 对sign进行赋值
     */
    public void setSign(String sign) {
        this.sign = sign;
    }
    
    /**
     * @return 返回 data
     */
    public T getData() {
        return data;
    }
    
    /**
     * @param 对data进行赋值
     */
    public void setData(T data) {
        this.data = data;
    }
}
