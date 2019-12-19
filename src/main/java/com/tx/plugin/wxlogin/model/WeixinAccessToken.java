/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2019年12月17日
 * <修改描述:>
 */
package com.tx.plugin.wxlogin.model;

/**
 * 微信用户信息<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2019年12月17日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class WeixinAccessToken {
    
    /** 唯一键 */
    private String id;
    
    /** access_token对应的临时code */
    private String code;
    
    /** 操作员id */
    private String userId;
    
    /** 用户的唯一键id==openId */
    private String uniqueId;
    
    /** 单次访问的accessToken */
    private String accessToken;

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
     * @return 返回 code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param 对code进行赋值
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return 返回 userId
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param 对userId进行赋值
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * @return 返回 uniqueId
     */
    public String getUniqueId() {
        return uniqueId;
    }

    /**
     * @param 对uniqueId进行赋值
     */
    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    /**
     * @return 返回 accessToken
     */
    public String getAccessToken() {
        return accessToken;
    }

    /**
     * @param 对accessToken进行赋值
     */
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
