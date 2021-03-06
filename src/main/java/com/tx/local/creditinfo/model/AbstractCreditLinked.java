/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2020年1月14日
 * <修改描述:>
 */
package com.tx.local.creditinfo.model;

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
public abstract class AbstractCreditLinked implements CreditLinked {
    
    /** 注释内容 */
    private static final long serialVersionUID = -715232610546809800L;
    
    /** 主键id */
    @Id
    @Column(name = "id", nullable = false, length = 64, updatable = false)
    private String id;
    
    /** 信用信息ID */
    @Column(name = "clientId", nullable = false, length = 64, updatable = false)
    private String clientId;
    
    /** 信用信息ID */
    @Column(name = "creditInfoId", nullable = false, length = 64, updatable = false)
    private String creditInfoId;
    
    /** 最后更新时间 */
    @Column(name = "lastUpdateDate", nullable = false, updatable = true)
    private Date lastUpdateDate;
    
    /** 创建时间 */
    @Column(name = "createDate", nullable = false, updatable = false)
    private Date createDate;
    
    /**
     * @return 返回 id
     */
    @Override
    public String getId() {
        return id;
    }
    
    /**
     * @param 对id进行赋值
     */
    @Override
    public void setId(String id) {
        this.id = id;
    }
    
    /**
     * @return 返回 clientId
     */
    @Override
    public String getClientId() {
        return clientId;
    }
    
    /**
     * @param 对clientId进行赋值
     */
    @Override
    public void setClientId(String clientId) {
        this.clientId = clientId;
    }
    
    /**
     * @return 返回 creditInfoId
     */
    @Override
    public String getCreditInfoId() {
        return creditInfoId;
    }
    
    /**
     * @param 对creditInfoId进行赋值
     */
    @Override
    public void setCreditInfoId(String creditInfoId) {
        this.creditInfoId = creditInfoId;
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
}
