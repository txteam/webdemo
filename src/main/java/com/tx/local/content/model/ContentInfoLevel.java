/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2016年8月4日
 * <修改描述:>
 */
package com.tx.local.content.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.tx.component.basicdata.annotation.BasicDataEntity;
import com.tx.component.basicdata.model.BasicData;
import com.tx.component.basicdata.model.BasicDataViewTypeEnum;

import io.swagger.annotations.ApiModel;

/**
 * 内容信息级别<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2016年8月4日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Entity
@Table(name = "ci_content_level")
@BasicDataEntity(name = "内容信息级别", viewType = BasicDataViewTypeEnum.LIST)
@ApiModel("内容信息级别")
public class ContentInfoLevel implements BasicData {
    
    /** 注释内容 */
    private static final long serialVersionUID = -4169837039824055550L;
    
    /** 主键id */
    @Id
    private String id;
    
    /** 对应枚举关键字：该字段可以为空 */
    private String code;
    
    /** 类型 */
    @Column(name = "categoryCode")
    private ContentInfoCategory category;
    
    /** 内容信息类型名 */
    private String name;
    
    /** 内容信息类型备注 */
    private String remark;
    
    /** 内容信息是否有效 */
    private boolean valid = true;
    
    /** 是否可编辑 */
    private boolean modifyAble = true;
    
    /** 创建时间 */
    private Date createDate;
    
    /** 创建人 */
    private String createOperatorId;
    
    /** 最后更新时间 */
    private Date lastUpdateDate;
    
    /** 最后更新人 */
    private String lastUpdateOperatorId;
    
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
     * @return 返回 category
     */
    public ContentInfoCategory getCategory() {
        return category;
    }
    
    /**
     * @param 对category进行赋值
     */
    public void setCategory(ContentInfoCategory category) {
        this.category = category;
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
     * @return 返回 createOperatorId
     */
    public String getCreateOperatorId() {
        return createOperatorId;
    }
    
    /**
     * @param 对createOperatorId进行赋值
     */
    public void setCreateOperatorId(String createOperatorId) {
        this.createOperatorId = createOperatorId;
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
     * @return 返回 lastUpdateOperatorId
     */
    public String getLastUpdateOperatorId() {
        return lastUpdateOperatorId;
    }
    
    /**
     * @param 对lastUpdateOperatorId进行赋值
     */
    public void setLastUpdateOperatorId(String lastUpdateOperatorId) {
        this.lastUpdateOperatorId = lastUpdateOperatorId;
    }
    
}
