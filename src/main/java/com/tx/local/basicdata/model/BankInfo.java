/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2015年10月20日
 * <修改描述:>
 */
package com.tx.local.basicdata.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.alibaba.fastjson.JSONObject;
import com.tx.component.basicdata.annotation.BasicDataEntity;
import com.tx.component.basicdata.model.BasicData;
import com.tx.component.basicdata.model.BasicDataViewTypeEnum;
import com.tx.core.jdbc.sqlsource.annotation.UpdateAble;
import com.tx.core.support.initable.model.ConfigInitAble;
import com.tx.core.support.json.JSONAttributesSupport;

/**
 * 银行信息<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2015年10月20日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Entity
@Table(name = "bd_bankInfo")
@BasicDataEntity(name = "银行信息", viewType = BasicDataViewTypeEnum.PAGEDLIST)
public class BankInfo implements Serializable, ConfigInitAble, BasicData{



    /** 注释内容 */
    private static final long serialVersionUID = -8931475549426903123L;
    
    /** 银行账户id唯一键 */
    @Id
    private String id;
    
    /** 银行英文简称*/
    private String code;
    
    /** 是否有效 */
    @UpdateAble
    private boolean valid;
    
    /** 是否可编辑 */
    private boolean modifyAble = true;
    
    /** 银行名称*/
    private String name;
    
    /** 银行别名 */
    private String aliases;
    
    /**logo文件ID*/
    private String logoFileId = "";
    
    /** 银行logo地址*/
    private String logoUrl;
    
    /** 个人网银登录url */
    private String personalLoginUrl = "";
    
    /** 机构网银登录url */
    private String institutionLoginUrl = "";
    
    /** 备注 */
    private String remark;
    
    /** 参数 */
    private String attributes;
    
    /** 创建时间 */
    private Date createDate;
    
    /** 最后更新时间 */
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
     * @return 返回 aliases
     */
    public String getAliases() {
        return aliases;
    }
    
    /**
     * @param 对aliases进行赋值
     */
    public void setAliases(String aliases) {
        this.aliases = aliases;
    }
    
    /**
     * @return 返回 logoUrl
     */
    public String getLogoUrl() {
        return logoUrl;
    }
    
    /**
     * @param 对logoUrl进行赋值
     */
    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }
    
    /**
     * @return 返回 logoFileId
     */
    public String getLogoFileId() {
        return logoFileId;
    }
    
    /**
     * @param 对logoFileId进行赋值
     */
    public void setLogoFileId(String logoFileId) {
        this.logoFileId = logoFileId;
    }
    
    /**
     * @return 返回 personalLoginUrl
     */
    public String getPersonalLoginUrl() {
        return personalLoginUrl;
    }
    
    /**
     * @param 对personalLoginUrl进行赋值
     */
    public void setPersonalLoginUrl(String personalLoginUrl) {
        this.personalLoginUrl = personalLoginUrl;
    }
    
    /**
     * @return 返回 institutionLoginUrl
     */
    public String getInstitutionLoginUrl() {
        return institutionLoginUrl;
    }
    
    /**
     * @param 对institutionLoginUrl进行赋值
     */
    public void setInstitutionLoginUrl(String institutionLoginUrl) {
        this.institutionLoginUrl = institutionLoginUrl;
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
     * @return 返回 attributes
     */
    public String getAttributes() {
        return attributes;
    }
    
    /**
     * @param 对attributes进行赋值
     */
    public void setAttributes(String attributes) {
        this.attributes = attributes;
    }
    
}
