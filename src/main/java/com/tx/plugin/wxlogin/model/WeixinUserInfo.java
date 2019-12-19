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
public class WeixinUserInfo {
    
    /** 唯一键 */
    private String id;
    
    /** 操作员id */
    private String operatorId;
    
    /** 用户的唯一键id==openId */
    private String uniqueId;
    
    /** 单次访问的accessToken */
    private String accessToken;
    
    /** 昵称 */
    private String nickname;
    
    /** 性别 */
    private String sex;
    
    private String province;
    
    private String country;
    
    private String headImgUrl;
    
    private String privilege;
    
    private String unionid;
    
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
     * @return 返回 operatorId
     */
    public String getOperatorId() {
        return operatorId;
    }
    
    /**
     * @param 对operatorId进行赋值
     */
    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
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
    
    /**
     * @return 返回 nickname
     */
    public String getNickname() {
        return nickname;
    }
    
    /**
     * @param 对nickname进行赋值
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    
    /**
     * @return 返回 sex
     */
    public String getSex() {
        return sex;
    }
    
    /**
     * @param 对sex进行赋值
     */
    public void setSex(String sex) {
        this.sex = sex;
    }
    
    /**
     * @return 返回 province
     */
    public String getProvince() {
        return province;
    }
    
    /**
     * @param 对province进行赋值
     */
    public void setProvince(String province) {
        this.province = province;
    }
    
    /**
     * @return 返回 country
     */
    public String getCountry() {
        return country;
    }
    
    /**
     * @param 对country进行赋值
     */
    public void setCountry(String country) {
        this.country = country;
    }
    
    /**
     * @return 返回 headImgUrl
     */
    public String getHeadImgUrl() {
        return headImgUrl;
    }
    
    /**
     * @param 对headImgUrl进行赋值
     */
    public void setHeadImgUrl(String headImgUrl) {
        this.headImgUrl = headImgUrl;
    }
    
    /**
     * @return 返回 privilege
     */
    public String getPrivilege() {
        return privilege;
    }
    
    /**
     * @param 对privilege进行赋值
     */
    public void setPrivilege(String privilege) {
        this.privilege = privilege;
    }
    
    /**
     * @return 返回 unionid
     */
    public String getUnionid() {
        return unionid;
    }
    
    /**
     * @param 对unionid进行赋值
     */
    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }
}
