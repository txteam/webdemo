/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2014年12月14日
 * <修改描述:>
 */
package com.tx.local.loanaccount.context.request.change;

import java.math.BigDecimal;

import com.tx.local.loanaccount.model.LATradingRecordTypeEnum;
import com.tx.local.loanaccount.model.RequestSourceTypeEnum;

/**
 * 委外请求<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2014年12月14日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class ChangeToOutsourceRequest extends AbstractChangeRequest {
    
    /** 是否正在发生委外 */
    private boolean outsource = true;
    
    /** 对应委外分配记录id */
    private String outsourceAssignRecordId;
    
    /** 委外外包佣金比率 */
    private BigDecimal outsourcePercentage;
    
    /** 委外委托金额 */
    private BigDecimal outsourceAmount;
    
    /** <默认构造函数> */
    public ChangeToOutsourceRequest(String outsourceAssignRecordId,
            BigDecimal outsourcePercentage, BigDecimal outsourceAmount,
            String loanAccountId, RequestSourceTypeEnum sourceType) {
        super(loanAccountId, sourceType);
        this.outsourceAssignRecordId = outsourceAssignRecordId;
        this.outsourcePercentage = outsourcePercentage;
        this.outsourceAmount = outsourceAmount;
    }
    
    /**
     * @return 返回 outsource
     */
    public boolean isOutsource() {
        return outsource;
    }
    
    /**
     * @param 对outsource进行赋值
     */
    public void setOutsource(boolean outsource) {
        this.outsource = outsource;
    }
    
    /**
     * @return 返回 outsourceAssignRecordId
     */
    public String getOutsourceAssignRecordId() {
        return outsourceAssignRecordId;
    }
    
    /**
     * @param 对outsourceAssignRecordId进行赋值
     */
    public void setOutsourceAssignRecordId(String outsourceAssignRecordId) {
        this.outsourceAssignRecordId = outsourceAssignRecordId;
    }
    
    /**
     * @return 返回 outsourcePercentage
     */
    public BigDecimal getOutsourcePercentage() {
        return outsourcePercentage;
    }
    
    /**
     * @param 对outsourcePercentage进行赋值
     */
    public void setOutsourcePercentage(BigDecimal outsourcePercentage) {
        this.outsourcePercentage = outsourcePercentage;
    }
    
    /**
     * @return 返回 outsourceAmount
     */
    public BigDecimal getOutsourceAmount() {
        return outsourceAmount;
    }
    
    /**
     * @param 对outsourceAmount进行赋值
     */
    public void setOutsourceAmount(BigDecimal outsourceAmount) {
        this.outsourceAmount = outsourceAmount;
    }
    
    @Override
    public LATradingRecordTypeEnum getTradingRecordType() {
        return LATradingRecordTypeEnum.CHANGE_OUT_SOURCE_INFO;
    }
}
