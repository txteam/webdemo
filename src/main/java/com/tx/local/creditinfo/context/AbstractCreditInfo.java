/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2020年1月14日
 * <修改描述:>
 */
package com.tx.local.creditinfo.context;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;

/**
 * 抽象信用信息<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2020年1月14日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public abstract class AbstractCreditInfo implements Serializable {
    
    /** 注释内容 */
    private static final long serialVersionUID = -715232610546809800L;
    
    /** 主键id */
    @Id
    @Column(name = "id", nullable = false, length = 64, updatable = false)
    private String id;
    
    /** 信用信息ID */
    @Column(name = "creditInfoId", nullable = false, length = 64, updatable = false)
    private String creditInfoId;
    
    /** 版本类型 */
    @Column(name = "versionType", nullable = false, length = 64, updatable = false)
    private CreditInfoVersionTypeEnum versionType;
    
    /** 版本 */
    @Column(name = "version", nullable = false, length = 16, updatable = false)
    private int version;
    
    /** 创建用户id */
    @Column(name = "createUserId", nullable = true, length = 64, updatable = false)
    private String createUserId;
    
    /** 创建时间 */
    @Column(name = "createDate", nullable = false, updatable = false)
    private Date createDate;
    
    /** 最后更新用户id */
    @Column(name = "lastUpdateUserId", nullable = true, length = 64, updatable = true)
    private String lastUpdateUserId;
    
    /** 最后更新时间 */
    @Column(name = "lastUpdateDate", nullable = false, updatable = true)
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
     * @return 返回 creditInfoId
     */
    public String getCreditInfoId() {
        return creditInfoId;
    }
    
    /**
     * @param 对creditInfoId进行赋值
     */
    public void setCreditInfoId(String creditInfoId) {
        this.creditInfoId = creditInfoId;
    }
    
    /**
     * @return 返回 versionType
     */
    public CreditInfoVersionTypeEnum getVersionType() {
        return versionType;
    }
    
    /**
     * @param 对versionType进行赋值
     */
    public void setVersionType(CreditInfoVersionTypeEnum versionType) {
        this.versionType = versionType;
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
}
