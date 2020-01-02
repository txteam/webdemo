package com.tx.plugin.login.github.model;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * git hub 用户信息
 *
 * @author huan.fu
 * @date 2018/11/26 - 18:11
 */
public class GitHubUserInfo {
    
    private long id;
    
    private String name;
    
    private String username;
    
    private String location;
    
    private String company;
    
    private String blog;
    
    private String email;
    
    @JSONField(name="createdDate")
    private Date createdDate;
    
    private String profileImageUrl;
    
    private String avatarUrl;
    
    /**
     * @return 返回 id
     */
    public long getId() {
        return id;
    }
    
    /**
     * @param 对id进行赋值
     */
    public void setId(long id) {
        this.id = id;
    }
    
    /**
     * @return 返回 name
     */
    public String getName() {
        return name;
    }
    
    /**
     * @param 对name进行赋值
     */
    public void setName(String name) {
        this.name = name;
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
     * @return 返回 location
     */
    public String getLocation() {
        return location;
    }
    
    /**
     * @param 对location进行赋值
     */
    public void setLocation(String location) {
        this.location = location;
    }
    
    /**
     * @return 返回 company
     */
    public String getCompany() {
        return company;
    }
    
    /**
     * @param 对company进行赋值
     */
    public void setCompany(String company) {
        this.company = company;
    }
    
    /**
     * @return 返回 blog
     */
    public String getBlog() {
        return blog;
    }
    
    /**
     * @param 对blog进行赋值
     */
    public void setBlog(String blog) {
        this.blog = blog;
    }
    
    /**
     * @return 返回 email
     */
    public String getEmail() {
        return email;
    }
    
    /**
     * @param 对email进行赋值
     */
    public void setEmail(String email) {
        this.email = email;
    }
    
    /**
     * @return 返回 createdDate
     */
    public Date getCreatedDate() {
        return createdDate;
    }
    
    /**
     * @param 对createdDate进行赋值
     */
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
    
    /**
     * @return 返回 profileImageUrl
     */
    public String getProfileImageUrl() {
        return profileImageUrl;
    }
    
    /**
     * @param 对profileImageUrl进行赋值
     */
    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }
    
    /**
     * @return 返回 avatarUrl
     */
    public String getAvatarUrl() {
        return avatarUrl;
    }
    
    /**
     * @param 对avatarUrl进行赋值
     */
    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }
    
}
