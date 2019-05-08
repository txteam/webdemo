package com.tx.local.client.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.tx.core.jdbc.sqlsource.annotation.QueryConditionEqual;
import com.tx.core.jdbc.sqlsource.annotation.QueryConditionGreaterOrEqual;
import com.tx.core.jdbc.sqlsource.annotation.QueryConditionLess;
import com.tx.core.jdbc.sqlsource.annotation.UpdateAble;
import com.tx.local.client.ClientInfoConstants;

/**
 * 客户引用对象<br/>
 *    客户与客户角色
 *    客户与客户职位等相关的依据类<br/>
 * @author bobby
 * @version [版本号, 2016年1月3日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Entity
@Table(name = "cli_client_ref")
public class ClientRef implements Serializable {
    
    /** 注释内容 */
    private static final long serialVersionUID = 5508606866162811703L;
    
    /** 客户引用唯一键 */
    @Id
    private String id;
    
    /** 客户id */
    @QueryConditionEqual
    private String clientId;
    
    /** 引用类型 */
    @QueryConditionEqual
    private String refType;
    
    /** 引用id */
    @QueryConditionEqual
    private String refId;
    
    /** 生效时间 */
    @UpdateAble
    @QueryConditionGreaterOrEqual(key = "minEffectiveDate")
    @QueryConditionLess(key = "maxEffectiveDate")
    private Date effectiveDate;
    
    /** 系统自动判定的无效时间:系统在查询具体是否存在引用过程中将根据该时间动态计算 */
    @UpdateAble
    @QueryConditionGreaterOrEqual(key = "minExpiryDate")
    @QueryConditionLess(key = "maxExpiryDate")
    private Date expiryDate;
    
    /** 结束时间 */
    @UpdateAble
    private Date endDate;
    
    /** 创建时间 */
    private Date createDate;
    
    /** 最后更新时间 */
    private Date lastUpdateDate;
    
    /** <默认构造函数> */
    public ClientRef() {
        super();
    }
    
    public ClientRef(String refType, String clientId, String refId) {
        super();
        
        this.refType = refType;
        this.clientId = clientId;
        this.refId = refId;
        this.effectiveDate = new Date();
        this.expiryDate = ClientInfoConstants.DEFAULT_EXPIRY_DATE;
    }
    
    public ClientRef(String refType, String clientId, String refId,
            Date effectiveDate, Date expiryDate) {
        super();
        
        this.refType = refType;
        this.clientId = clientId;
        this.refId = refId;
        this.effectiveDate = effectiveDate;
        this.expiryDate = expiryDate;
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
     * @return 返回 refType
     */
    public String getRefType() {
        return refType;
    }

    /**
     * @param 对refType进行赋值
     */
    public void setRefType(String refType) {
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
     * @return 返回 effectiveDate
     */
    public Date getEffectiveDate() {
        return effectiveDate;
    }

    /**
     * @param 对effectiveDate进行赋值
     */
    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    /**
     * @return 返回 expiryDate
     */
    public Date getExpiryDate() {
        return expiryDate;
    }

    /**
     * @param 对expiryDate进行赋值
     */
    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    /**
     * @return 返回 endDate
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * @param 对endDate进行赋值
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
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
}
