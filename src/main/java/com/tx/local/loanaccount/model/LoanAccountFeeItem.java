/**
 * 描          述：  <描述>
 * 修  改   人：   Administrator
 * 修改时间：  2014年4月16日
 * <修改描述： >
 */
package com.tx.local.loanaccount.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.tx.local.basicdata.model.FeeItemEnum;

/**
 * 贷款单账户费用配置项目
 * 
 * @author Administrator
 * @version [版本号, 2014年4月16日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Entity
@Table(name = "la_fee_item")
public class LoanAccountFeeItem implements Serializable {
    
    /** 注释内容 */
    private static final long serialVersionUID = -3914087808076270580L;
    
    /** 主键： */
    @Id
    private String id;
    
    /** 关联贷款账户主键： */
    private String loanAccountId;
    
    /** 费用项id： */
    private FeeItemEnum feeItem;
    
    /** 费率/费用金额:  递减利率将以分号分隔填入该值 */
    private String value;
    
    /** <默认构造函数> */
    public LoanAccountFeeItem() {
        super();
    }
    
    /** <默认构造函数> */
    public LoanAccountFeeItem(String loanAccountId, FeeItemEnum feeItem,
            String value) {
        super();
        this.loanAccountId = loanAccountId;
        this.feeItem = feeItem;
        this.value = value;
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
     * @return 返回 loanAccountId
     */
    public String getLoanAccountId() {
        return loanAccountId;
    }
    
    /**
     * @param 对loanAccountId进行赋值
     */
    public void setLoanAccountId(String loanAccountId) {
        this.loanAccountId = loanAccountId;
    }
    
    /**
     * @return 返回 feeItem
     */
    public FeeItemEnum getFeeItem() {
        return feeItem;
    }
    
    /**
     * @param 对feeItem进行赋值
     */
    public void setFeeItem(FeeItemEnum feeItem) {
        this.feeItem = feeItem;
    }
    
    /**
     * @return 返回 value
     */
    public String getValue() {
        return value;
    }
    
    /**
     * @param 对value进行赋值
     */
    public void setValue(String value) {
        this.value = value;
    }
    
    @Override
    public String toString() {
        return "LoanAccountFeeCfgItem [feeItem=" + feeItem + ", value=" + value
                + "]";
    }
}
