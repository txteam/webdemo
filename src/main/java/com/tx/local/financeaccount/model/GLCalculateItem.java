/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2014年5月16日
 * <修改描述:>
 */
package com.tx.local.financeaccount.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.tx.component.basicdata.model.BasicData;
import com.tx.core.support.initable.model.ConfigInitAble;

/**
 * 核算项实例<br/>
 * 省 市  区 分公司 分行 产品
 * @author  Administrator
 * @version  [版本号, 2014年5月16日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Entity
@Table(name = "FI_GL_CALCULATE_ITEM")
public class GLCalculateItem implements Serializable, BasicData, ConfigInitAble {
    
    /** 注释内容 */
    private static final long serialVersionUID = -2523570416398084658L;
    
    /** 核算项id */
    @Id
    private String id;
    
    /** 核算项编码 */
    private String code;
    
    /** 核算项类型 */
    private GLAccountingItemTypeEnum type;
    
    /** 核算项名称 */
    private String name;
    
    /** 是否有效 */
    private boolean valid;
    
    /** 是否可编辑 */
    private boolean modifyAble;
    
    /** 核算项引用类型 */
    private GLAccountingItemRefTypeEnum refType;
    
    /** 核算项引用id */
    private String refId;
    
    /** 核算项引用名称 */
    private String refName;
    
    /** 创建时间 */
    private Date createDate;
    
    /** 最后更新时间 */
    private Date lastUpdateDate;
    
    /** 备注 */
    private String remark;
    
    /** <默认构造函数> */
    public GLCalculateItem() {
        super();
    }
    
    /** <默认构造函数> */
    public GLCalculateItem(String id, String refId, GLAccountingItemTypeEnum type,
            GLAccountingItemRefTypeEnum refType, String refName, String code) {
        super();
        this.id = id;
        this.refId = refId;
        this.type = type;
        this.refType = refType;
        this.refName = refName;
        this.code = code;
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
     * @return 返回 type
     */
    public GLAccountingItemTypeEnum getType() {
        return type;
    }
    
    /**
     * @param 对type进行赋值
     */
    public void setType(GLAccountingItemTypeEnum type) {
        this.type = type;
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
     * @return 返回 refType
     */
    public GLAccountingItemRefTypeEnum getRefType() {
        return refType;
    }
    
    /**
     * @param 对refType进行赋值
     */
    public void setRefType(GLAccountingItemRefTypeEnum refType) {
        this.refType = refType;
    }
    
    /**
     * @return 返回 refId
     */
    public String getRefId() {
        return refId;
    }
    
    /**
     * @param 对refId进行赋值
     */
    public void setRefId(String refId) {
        this.refId = refId;
    }
    
    /**
     * @return 返回 refName
     */
    public String getRefName() {
        return refName;
    }
    
    /**
     * @param 对refName进行赋值
     */
    public void setRefName(String refName) {
        this.refName = refName;
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
}
