/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2014年6月9日
 * <修改描述:>
 */
package com.tx.local.loanaccount.context.request;

import java.util.Date;
import java.util.Map;

import com.tx.component.command.context.CommandRequest;
import com.tx.core.exceptions.util.AssertUtils;
import com.tx.core.util.UUIDUtils;
import com.tx.local.loanaccount.model.LATradingCategoryEnum;
import com.tx.local.loanaccount.model.LATradingRecordTypeEnum;
import com.tx.local.loanaccount.model.LoanAccountTypeEnum;
import com.tx.local.loanaccount.model.RequestSourceTypeEnum;
import com.tx.local.security.util.WebContextUtils;

/**
 * 账户请求基础类<br/>
 * 
 * @author  Tim.peng
 * @version  [版本号, 2014年6月9日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public abstract class AbstractLoanAccountRequest implements CommandRequest {
    
    /** 请求主键 */
    private String id;
    
    /** 分公司 */
    private String vcid;
    
    /** 组织 */
    private String organizationId;
    
    /** 操作员 */
    private String operatorId;
    
    /** 请求创建时间 */
    private Date createDate;
    
    /** 贷款账户 */
    private String loanAccountId;
    
    /** 贷款账户类型 */
    private LoanAccountTypeEnum loanAccountType;
    
    /** 请求来源 */
    private RequestSourceTypeEnum sourceType;
    
    /** 备注：对应撤销交易中的备注 */
    private String remark;
    
    /** 其他参数 */
    private Map<String, Object> attributes;
    
    /** <默认构造函数> */
    public AbstractLoanAccountRequest() {
        super();
        
        this.id = UUIDUtils.generateUUID();
    }
    
    /** 根据"账户ID"、"交易来源"和"贷款账户操作类型"构建'账户'请求 */
    public AbstractLoanAccountRequest(String loanAccountId,
            RequestSourceTypeEnum sourceType) {
        AssertUtils.notEmpty(loanAccountId, "loanAccountId is empty.");
        AssertUtils.notNull(sourceType, "sourceType is null.");
        
        this.id = UUIDUtils.generateUUID();
        this.loanAccountId = loanAccountId;
        this.sourceType = sourceType;
        
        this.createDate = new Date();
        this.vcid = WebContextUtils.getVcid();
        this.organizationId = WebContextUtils.getOrganizationId();
        this.operatorId = WebContextUtils.getOperatorId();
    }
    
    /** 根据"账户ID"、"交易来源"和"贷款账户操作类型"构建'账户'请求 */
    public AbstractLoanAccountRequest(String loanAccountId,
            LoanAccountTypeEnum loanAccountType,
            RequestSourceTypeEnum sourceType) {
        AssertUtils.notEmpty(loanAccountId, "loanAccountId is empty.");
        AssertUtils.notNull(loanAccountType, "loanAccountType is null.");
        AssertUtils.notNull(sourceType, "sourceType is null.");
        
        this.id = UUIDUtils.generateUUID();
        this.loanAccountId = UUIDUtils.generateUUID();
        this.loanAccountType = loanAccountType;
        this.sourceType = sourceType;
        
        this.createDate = new Date();
        this.vcid = WebContextUtils.getVcid();
        this.organizationId = WebContextUtils.getOrganizationId();
        this.operatorId = WebContextUtils.getOperatorId();
    }
    
    /**
     * @return 返回 loanAccountType
     */
    public LoanAccountTypeEnum getLoanAccountType() {
        return loanAccountType;
    }
    
    /**
     * @param 对loanAccountType进行赋值
     */
    public void setLoanAccountType(LoanAccountTypeEnum loanAccountType) {
        this.loanAccountType = loanAccountType;
    }
    
    /** 请求主键 */
    public String getId() {
        return id;
    }
    
    /** 请求主键 */
    public void setId(String id) {
        this.id = id;
    }
    
    /** 贷款账户 */
    public String getLoanAccountId() {
        return loanAccountId;
    }
    
    /** 贷款账户 */
    public void setLoanAccountId(String loanAccountId) {
        this.loanAccountId = loanAccountId;
    }
    
    /** 请求来源 */
    public RequestSourceTypeEnum getSourceType() {
        return sourceType;
    }
    
    /** 请求来源 */
    public void setSourceType(RequestSourceTypeEnum sourceType) {
        this.sourceType = sourceType;
    }
    
    /** 请求创建时间 */
    public Date getCreateDate() {
        return createDate;
    }
    
    /** 请求创建时间 */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    
    /** 分公司 */
    public String getVcid() {
        return vcid;
    }
    
    /** 分公司 */
    public void setVcid(String vcid) {
        this.vcid = vcid;
    }
    
    /** 组织 */
    public String getOrganizationId() {
        return organizationId;
    }
    
    /** 组织 */
    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }
    
    /** 操作员 */
    public String getOperatorId() {
        return operatorId;
    }
    
    /** 操作员 */
    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }
    
    /** 备注：对应撤销交易中的备注 */
    public String getRemark() {
        return remark;
    }
    
    /** 备注：对应撤销交易中的备注 */
    public void setRemark(String remark) {
        this.remark = remark;
    }
    
    /**
     * @return 返回 attributes
     */
    public Map<String, Object> getAttributes() {
        return attributes;
    }
    
    /**
     * @param 对attributes进行赋值
     */
    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }
    
    /**
     * 获取交易记录类型
     * 
     * @return TradingRecordTypeEnum 交易记录类型
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
    public abstract LATradingRecordTypeEnum getTradingRecordType();
    
    /**
     * 获取交易类型
     * 
     * @return TradingTypeEnum 交易类型
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
    public final LATradingCategoryEnum getTradingCategory() {
        LATradingRecordTypeEnum tradingRecordType = getTradingRecordType();
        AssertUtils.notNull(tradingRecordType, "tradingRecordType is null.");
        
        return tradingRecordType.getTradingCategory();
    }
    
    /**
      * 获取交易的摘要<br/>
      * 
      * @return TradingSummaryEnum 交易摘要
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public String getTradingSummary() {
        LATradingRecordTypeEnum tradingRecordType = getTradingRecordType();
        AssertUtils.notNull(tradingRecordType, "tradingRecordType is null.");
        
        return tradingRecordType.getSummary();
    }
    
}
