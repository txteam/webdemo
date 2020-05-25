/*
 * 描          述:  <描述>
 * 修  改   人:  PengQingyang
 * 修改时间:  2020年4月29日
 * <修改描述:>
 */
package com.tx.local.creditinfo.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.tx.local.basicdata.model.IDCardTypeEnum;
import com.tx.local.creditinfo.context.CreditInfoTypeEnum;
import com.tx.local.creditinfo.model.CreditInfo;

/**
 * 个人信用信息<br/>
 * <功能详细描述>
 * 
 * @author  PengQingyang
 * @version  [版本号, 2020年4月29日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Entity
@Table(name = "cli_credit_info")
public class CreditInfoRecord
        implements Serializable, CreditInfo {
    
    /** 注释内容 */
    private static final long serialVersionUID = 3127440845195599675L;
    
    /** 主键:唯一键 */
    @Id
    @Column(length = 64, updatable = false, nullable = false)
    private String id;
    
    /** 版本类型 */
    @Column(length = 64, updatable = false, nullable = false)
    private CreditInfoTypeEnum type;
    
    /** 版本是否锁定，如果版本锁定，则不能被更新，必须先解除锁定 */
    @Column(updatable = true, nullable = false)
    private boolean locked;
    
    /** 基础版本：版本创建之初版本号 */
    @Column(updatable = true, nullable = false)
    private int baseVersion;
    
    /** 版本号：每一次信用信息变更，版本号+1 */
    @Column(updatable = true, nullable = false)
    private int version;
    
    /** 证件类型 */
    @Column(length = 64, updatable = false, nullable = false)
    private IDCardTypeEnum idCardType;
    
    /** 身份证号码 */
    @Column(length = 64, updatable = false, nullable = false)
    private String idCardNumber;
    
    /** 最后更新时间 */
    @Column(nullable = false, updatable = true)
    private Date lastUpdateDate;
    
    /** 最后更新用户 */
    @Column(nullable = true, updatable = true)
    private String lastUpdateUserId;
    
    /** 创建时间 */
    @Column(nullable = false, updatable = false)
    private Date createDate;
    
    /** 创建用户ID */
    @Column(nullable = true, updatable = false)
    private String createUserId;

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
     * @return 返回 type
     */
    public CreditInfoTypeEnum getType() {
        return type;
    }

    /**
     * @param 对type进行赋值
     */
    public void setType(CreditInfoTypeEnum type) {
        this.type = type;
    }

    /**
     * @return 返回 locked
     */
    public boolean isLocked() {
        return locked;
    }

    /**
     * @param 对locked进行赋值
     */
    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    /**
     * @return 返回 baseVersion
     */
    public int getBaseVersion() {
        return baseVersion;
    }

    /**
     * @param 对baseVersion进行赋值
     */
    public void setBaseVersion(int baseVersion) {
        this.baseVersion = baseVersion;
    }

    /**
     * @return 返回 version
     */
    public int getVersion() {
        return version;
    }

    /**
     * @param 对version进行赋值
     */
    public void setVersion(int version) {
        this.version = version;
    }

    /**
     * @return 返回 idCardType
     */
    public IDCardTypeEnum getIdCardType() {
        return idCardType;
    }

    /**
     * @param 对idCardType进行赋值
     */
    public void setIdCardType(IDCardTypeEnum idCardType) {
        this.idCardType = idCardType;
    }

    /**
     * @return 返回 idCardNumber
     */
    public String getIdCardNumber() {
        return idCardNumber;
    }

    /**
     * @param 对idCardNumber进行赋值
     */
    public void setIdCardNumber(String idCardNumber) {
        this.idCardNumber = idCardNumber;
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
     * @return 返回 lastUpdateUserId
     */
    public String getLastUpdateUserId() {
        return lastUpdateUserId;
    }

    /**
     * @param 对lastUpdateUserId进行赋值
     */
    public void setLastUpdateUserId(String lastUpdateUserId) {
        this.lastUpdateUserId = lastUpdateUserId;
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
     * @return 返回 createUserId
     */
    public String getCreateUserId() {
        return createUserId;
    }

    /**
     * @param 对createUserId进行赋值
     */
    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }
}
