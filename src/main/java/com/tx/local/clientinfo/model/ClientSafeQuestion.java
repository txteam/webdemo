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
 * @author  ZHAOBING
 * @version  [版本号, 2016年9月1日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Entity
@Table(name = "cli_safe_question")
public class ClientSafeQuestion implements Serializable {
    
    /** 注释内容 */
    private static final long serialVersionUID = 4336951373185606933L;
    
    @Id
    private String id;
    
    @UpdateAble
    @QueryConditionEqual
    private String title;
    
    @UpdateAble
    @QueryConditionEqual
    private String remark;
    
    /** 是否可用 */
    @UpdateAble
    @QueryConditionEqual
    private boolean valid = true;
    
    /** 是否可编辑 */
    @UpdateAble
    @QueryConditionEqual
    private boolean modifyAble = true;
    
    /** 是否是通用问题 */
    @UpdateAble
    @QueryConditionEqual
    private boolean common = false;
    
    /** 客户id:如果为通用问题该字段为空 */
    @QueryConditionEqual
    private String clientId;
    
    /** 创建日期 */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @QueryConditionEqual
    private Date createDate;
    
    /** 最后更新时间 */
    @UpdateAble
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastUpdateDate = new Date();
    
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
     * @return 返回 title
     */
    public String getTitle() {
        return title;
    }
    
    /**
     * @param 对title进行赋值
     */
    public void setTitle(String title) {
        this.title = title;
    }
    
    /**
     * @return 返回 remark
     */
    public String getRemark() {
        return remark;
    }
    
    /**
     * @param 对remark进行赋值
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }
    
    /**
     * @return 返回 valid
     */
    public boolean isValid() {
        return valid;
    }
    
    /**
     * @param 对valid进行赋值
     */
    public void setValid(boolean valid) {
        this.valid = valid;
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
     * @return 返回 modifyAble
     */
    public boolean isModifyAble() {
        return modifyAble;
    }
    
    /**
     * @param 对modifyAble进行赋值
     */
    public void setModifyAble(boolean modifyAble) {
        this.modifyAble = modifyAble;
    }
    
    /**
     * @return 返回 common
     */
    public boolean isCommon() {
        return common;
    }
    
    /**
     * @param 对common进行赋值
     */
    public void setCommon(boolean common) {
        this.common = common;
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
}
