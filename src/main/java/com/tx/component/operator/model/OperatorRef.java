/*
 * 描          述:  <描述>
 * 修  改   人:  brady
 * 修改时间:  2013-9-16
 * <修改描述:>
 */
package com.tx.component.operator.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 人员引用对象<br/>
 *     代码逻辑中暂时不考虑对生效时间的兼容支持，等以后有相应需求时再进行添加<br/>
 * <功能详细描述>
 * 
 * @author  brady
 * @version  [版本号, 2013-9-16]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Entity
@Table(name = "OPER_OPERATOR_REF")
public class OperatorRef {
    
    /** 操作员id */
    @Id
    private String operatorId;
    
    /** 引用id */
    private String refId;
    
    /** 引用类型 */
    private String refType;
    
    /** 创建时间 */
    private Date createDate;
    
    /** 生效时间 */
    private Date effectiveDate;
    
    /** 系统自动判定的无效时间:系统在查询具体是否存在引用过程中将根据该时间动态计算 */
    private Date invalidDate;
    
    /** 结束时间时间：具体对应到移动到_His表中的时间 */
    private Date endDate;
    
    /** <默认构造函数> */
    public OperatorRef() {
        super();
    }
    
    /**
     * <默认构造函数>
     */
    public OperatorRef(String operatorId, String refId, String refType) {
        super();
        this.operatorId = operatorId;
        this.refId = refId;
        this.refType = refType;
    }

    /** <默认构造函数> */
    public OperatorRef(String operatorId, String refId, String refType,
            Date effectiveDate, Date invalidDate) {
        super();
        Date now = new Date();
        this.operatorId = operatorId;
        this.refId = refId;
        this.refType = refType;
        this.effectiveDate = effectiveDate;
        this.invalidDate = invalidDate;
        this.createDate = now;
    }

    /**
     * @return 返回 operatorId
     */
    public String getOperatorId() {
        return operatorId;
    }
    
    /**
     * @param 对operatorId进行赋值
     */
    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
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
     * @return 返回 invalidDate
     */
    public Date getInvalidDate() {
        return invalidDate;
    }

    /**
     * @param 对invalidDate进行赋值
     */
    public void setInvalidDate(Date invalidDate) {
        this.invalidDate = invalidDate;
    }
    
    
}
