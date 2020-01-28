/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2016年3月27日
 * <修改描述:>
 */
package com.tx.local.financeaccount.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 财务台帐变更记录<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2016年3月27日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Entity
@Table(name = "fi_acc_change_record")
public class AccountingChangeRecord {
    
    /** 唯一键 */
    @Id
    private String id;
    
    /** 变更来源 */
    private ChangeSourceEnum changeSource;
    
    /** 关联类型 */
    private String refType;
    
    /** 关联id */
    private String refId;
    
    /** 财务账户记录类型 */
    private AccountItemTypeEnum type;
    
    /** 名称 */
    private String name;
    
    /** 金额 */
    private BigDecimal amount;
    
    /** 备注 */
    private String remark;
    
    /** 创建时间 */
    private Date createDate;
    
    /** 当前操作人员 */
    private String createOperatorId;
    
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
     * @return 返回 changeSource
     */
    public ChangeSourceEnum getChangeSource() {
        return changeSource;
    }
    
    /**
     * @param 对changeSource进行赋值
     */
    public void setChangeSource(ChangeSourceEnum changeSource) {
        this.changeSource = changeSource;
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
     * @return 返回 type
     */
    public AccountItemTypeEnum getType() {
        return type;
    }
    
    /**
     * @param 对type进行赋值
     */
    public void setType(AccountItemTypeEnum type) {
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
     * @return 返回 amount
     */
    public BigDecimal getAmount() {
        return amount;
    }
    
    /**
     * @param 对amount进行赋值
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
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
}
