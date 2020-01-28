///*
// * 描          述:  <描述>
// * 修  改   人:  Administrator
// * 修改时间:  2015年1月29日
// * <修改描述:>
// */
//package com.tx.local.taxsettle.model;
//
//import java.io.Serializable;
//import java.math.BigDecimal;
//import java.util.Date;
//
//import javax.persistence.Entity;
//import javax.persistence.Table;
//
//import com.tx.component.basicdata.model.AccountTitleEnum;
//import com.tx.component.basicdata.model.CompanyEnum;
//import com.tx.component.basicdata.model.DebitCreditRefTypeEnum;
//import com.tx.component.basicdata.model.DebitCreditTypeEnum;
//import com.tx.component.basicdata.model.FeeItemEnum;
//import com.tx.component.basicdata.model.OwnershipEnum;
//
///**
// * 贷款账户结息记录<br/>
// * <功能详细描述>
// * 
// * @author Administrator
// * @version [版本号, 2015年1月29日]
// * @see [相关类/方法]
// * @since [产品/模块版本]
// */
//@Entity
//@Table(name = "TAX_SETTLE_RECORD")
//public class TaxSettleRecord implements Serializable {
//    
//    /** 注释内容 */
//    private static final long serialVersionUID = -6031308364401303434L;
//    
//    /** 主键： */
//    private String id;
//    
//    /** 贷款账户id */
//    private String loanAccountId;
//    
//    /** 从贷款账户中获取的归属虚中心 */
//    private String vcid;
//    
//    /** 费用项 */
//    private FeeItemEnum feeItem;
//    
//    /** 平息金额： */
//    private BigDecimal amount;
//    
//    /** 所属公司key */
//    private CompanyEnum companyKey;
//    
//    /** 所属科目key */
//    private AccountTitleEnum accountTitleKey;
//    
//    /** 借贷类型 */
//    private DebitCreditTypeEnum debitCreditType;
//    
//    /** 对应科目主键: */
//    private String accountTitleId;
//    
//    /** 记录创建时间 */
//    private Date createDate;
//    
//    /** 结息记录备注 */
//    private String summary;
//    
//    public TaxSettleRecord() {
//        super();
//    }
//    
//    /**
//     * @return 返回 accountTitleId
//     */
//    public String getAccountTitleId() {
//        return accountTitleId;
//    }
//    
//    /**
//     * @return 返回 accountTitleKey
//     */
//    public AccountTitleEnum getAccountTitleKey() {
//        return accountTitleKey;
//    }
//    
//    /**
//     * @return 返回 amount
//     */
//    public BigDecimal getAmount() {
//        return amount;
//    }
//    
//    /**
//     * @return 返回 amountIrr
//     */
//    public BigDecimal getAmountIrr() {
//        return amountIrr;
//    }
//    
//    /**
//     * @return 返回 companyKey
//     */
//    public CompanyEnum getCompanyKey() {
//        return companyKey;
//    }
//    
//    /**
//     * @return 返回 createDate
//     */
//    public Date getCreateDate() {
//        return createDate;
//    }
//    
//    /**
//     * @return 返回 debitCreditType
//     */
//    public DebitCreditTypeEnum getDebitCreditType() {
//        return debitCreditType;
//    }
//    
//    /**
//     * @return 返回 feeCfgItemId
//     */
//    public String getFeeCfgItemId() {
//        return feeCfgItemId;
//    }
//    
//    /**
//     * @return 返回 feeItem
//     */
//    public FeeItemEnum getFeeItem() {
//        return feeItem;
//    }
//    
//    /**
//     * @return 返回 id
//     */
//    public String getId() {
//        return id;
//    }
//    
//    /**
//     * @return 返回 loanAccountId
//     */
//    public String getLoanAccountId() {
//        return loanAccountId;
//    }
//    
//    /** 归属方 */
//    public OwnershipEnum getOwnership() {
//        return ownership;
//    }
//    
//    /**
//     * @return 返回 refId
//     */
//    public String getRefId() {
//        return refId;
//    }
//    
//    /**
//     * @return 返回 refType
//     */
//    public DebitCreditRefTypeEnum getRefType() {
//        return refType;
//    }
//    
//    /**
//     * @return 返回 summary
//     */
//    public TaxSettleSummaryEnum getSummary() {
//        return summary;
//    }
//    
//    /**
//     * @return 返回 vcid
//     */
//    public String getVcid() {
//        return vcid;
//    }
//    
//    /**
//     * @param 对accountTitleId进行赋值
//     */
//    public void setAccountTitleId(String accountTitleId) {
//        this.accountTitleId = accountTitleId;
//    }
//    
//    /**
//     * @param 对accountTitleKey进行赋值
//     */
//    public void setAccountTitleKey(AccountTitleEnum accountTitleKey) {
//        this.accountTitleKey = accountTitleKey;
//    }
//    
//    /**
//     * @param 对amount进行赋值
//     */
//    public void setAmount(BigDecimal amount) {
//        this.amount = amount;
//    }
//    
//    /**
//     * @param 对amountIrr进行赋值
//     */
//    public void setAmountIrr(BigDecimal amountIrr) {
//        this.amountIrr = amountIrr;
//    }
//    
//    /**
//     * @param 对companyKey进行赋值
//     */
//    public void setCompanyKey(CompanyEnum companyKey) {
//        this.companyKey = companyKey;
//    }
//    
//    /**
//     * @param 对createDate进行赋值
//     */
//    public void setCreateDate(Date createDate) {
//        this.createDate = createDate;
//    }
//    
//    /**
//     * @param 对debitCreditType进行赋值
//     */
//    public void setDebitCreditType(DebitCreditTypeEnum debitCreditType) {
//        this.debitCreditType = debitCreditType;
//    }
//    
//    /**
//     * @param 对feeCfgItemId进行赋值
//     */
//    public void setFeeCfgItemId(String feeCfgItemId) {
//        this.feeCfgItemId = feeCfgItemId;
//    }
//    
//    /**
//     * @param 对feeItem进行赋值
//     */
//    public void setFeeItem(FeeItemEnum feeItem) {
//        this.feeItem = feeItem;
//    }
//    
//    /**
//     * @param 对id进行赋值
//     */
//    public void setId(String id) {
//        this.id = id;
//    }
//    
//    /**
//     * @param 对loanAccountId进行赋值
//     */
//    public void setLoanAccountId(String loanAccountId) {
//        this.loanAccountId = loanAccountId;
//    }
//    
//    /** 归属方 */
//    public void setOwnership(OwnershipEnum ownership) {
//        this.ownership = ownership;
//    }
//    
//    /**
//     * @param 对refId进行赋值
//     */
//    public void setRefId(String refId) {
//        this.refId = refId;
//    }
//    
//    /**
//     * @param 对refType进行赋值
//     */
//    public void setRefType(DebitCreditRefTypeEnum refType) {
//        this.refType = refType;
//    }
//    
//    /**
//     * @param 对summary进行赋值
//     */
//    public void setSummary(TaxSettleSummaryEnum summary) {
//        this.summary = summary;
//    }
//    
//    /**
//     * @param 对vcid进行赋值
//     */
//    public void setVcid(String vcid) {
//        this.vcid = vcid;
//    }
//}
