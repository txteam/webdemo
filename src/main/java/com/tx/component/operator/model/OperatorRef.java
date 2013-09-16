/*
 * 描          述:  <描述>
 * 修  改   人:  brady
 * 修改时间:  2013-9-16
 * <修改描述:>
 */
package com.tx.component.operator.model;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 人员引用对象<br/>
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
    
    private String operatorId;
    
    private String refId;
    
    private OperatorRefTypeEnum refType;
    
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
    public OperatorRefTypeEnum getRefType() {
        return refType;
    }
    
    /**
     * @param 对refType进行赋值
     */
    public void setRefType(OperatorRefTypeEnum refType) {
        this.refType = refType;
    }
}
