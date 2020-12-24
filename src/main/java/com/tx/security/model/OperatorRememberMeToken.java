/*
 * 描          述:  <描述>
 * 修  改   人:  PengQingyang
 * 修改时间:  2020年12月14日
 * <修改描述:>
 */
package com.tx.security.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 操作人员记住我Token
 * <功能详细描述>
 * 
 * @author  PengQingyang
 * @version  [版本号, 2020年12月14日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class OperatorRememberMeToken implements Serializable {
    
    /** 注释内容 */
    private static final long serialVersionUID = -5137838445593341528L;
    
    /** 序列号 */
    private String id;
    
    /** 用户名 */
    private String username;
    
    /** token值 */
    private String token;
    
    /** token最后使用时间 */
    private Date lastUseDate;
    
    /** ip地址 */
    private String ipAddress;
    
    /**  <默认构造函数> */
    public OperatorRememberMeToken() {
        super();
    }
    
    public OperatorRememberMeToken(String id, String username, String token,
            String ipAddress, Date lastUseDate) {
        super();
        this.id = id;
        this.username = username;
        this.token = token;
        this.ipAddress = ipAddress;
        this.lastUseDate = lastUseDate;
    }
    
    /**
     * @return 返回 id
     */
    public String getId() {
        return id;
    }
    
    /**
     * @param 对id进行赋值
     */
    public void setId(String id) {
        this.id = id;
    }
    
    /**
     * @return 返回 username
     */
    public String getUsername() {
        return username;
    }
    
    /**
     * @param 对username进行赋值
     */
    public void setUsername(String username) {
        this.username = username;
    }
    
    /**
     * @return 返回 token
     */
    public String getToken() {
        return token;
    }
    
    /**
     * @param 对token进行赋值
     */
    public void setToken(String token) {
        this.token = token;
    }
    
    /**
     * @return 返回 lastUseDate
     */
    public Date getLastUseDate() {
        return lastUseDate;
    }
    
    /**
     * @param 对lastUseDate进行赋值
     */
    public void setLastUseDate(Date lastUseDate) {
        this.lastUseDate = lastUseDate;
    }
    
    /**
     * @return 返回 ipAddress
     */
    public String getIpAddress() {
        return ipAddress;
    }
    
    /**
     * @param 对ipAddress进行赋值
     */
    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }
    
}
