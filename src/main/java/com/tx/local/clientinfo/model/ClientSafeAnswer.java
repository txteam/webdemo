/*
 * 描          述:  <描述>
 * 修  改   人: ZHAOBING
 * 修改时间:  2016年9月1日
 * <修改描述:>
 */
package com.tx.local.clientinfo.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.tx.core.jdbc.sqlsource.annotation.QueryConditionEqual;
import com.tx.core.jdbc.sqlsource.annotation.UpdateAble;

/**
 * 客户问题库<br/>
 * <功能详细描述>
 * 
 * @author  PengQY
 * @version  [版本号, 2017年3月12日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Entity
@Table(name = "cli_safe_answer")
public class ClientSafeAnswer implements Serializable {
    
    /** 注释内容 */
    private static final long serialVersionUID = 4336951373185606933L;
    
    @Id
    private String id;
    
    @UpdateAble
    @QueryConditionEqual
    private String clientId;
    
    /** 客户问题回答编码：如:问题一、问题二、问题三 */
    private String code;
    
    @UpdateAble
    @QueryConditionEqual
    private String questionId;
    
    @UpdateAble
    @QueryConditionEqual
    private String questionTitle;
    
    @UpdateAble
    @QueryConditionEqual
    private String answer;
    
    /** 创建日期 */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @QueryConditionEqual
    private Date createDate;
    
    /** 最后更新时间 */
    @UpdateAble
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastUpdateDate;
    
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
     * @return 返回 clientId
     */
    public String getClientId() {
        return clientId;
    }
    
    /**
     * @param 对clientId进行赋值
     */
    public void setClientId(String clientId) {
        this.clientId = clientId;
    }
    
    /**
     * @return 返回 questionId
     */
    public String getQuestionId() {
        return questionId;
    }
    
    /**
     * @param 对questionId进行赋值
     */
    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }
    
    /**
     * @return 返回 questionTitle
     */
    public String getQuestionTitle() {
        return questionTitle;
    }
    
    /**
     * @param 对questionTitle进行赋值
     */
    public void setQuestionTitle(String questionTitle) {
        this.questionTitle = questionTitle;
    }
    
    /**
     * @return 返回 answer
     */
    public String getAnswer() {
        return answer;
    }
    
    /**
     * @param 对answer进行赋值
     */
    public void setAnswer(String answer) {
        this.answer = answer;
    }
    
    /**
     * @return 返回 createDate
     */
    public Date getCreateDate() {
        return createDate;
    }
    
    /**
     * @param 对createDate进行赋值
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    
    /**
     * @return 返回 lastUpdateDate
     */
    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }
    
    /**
     * @param 对lastUpdateDate进行赋值
     */
    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
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
    
}
