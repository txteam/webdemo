/*
 * 描    /**     /**   述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2014年4月24日
 * <修改描述:>
 */
package com.tx.local.financeaccount.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.tx.local.basicdata.model.DebitCreditDirectionEnum;

/**
 * 贷款账户：财务总账（GL）General Ledger交易明细记录<br/>
 * <功能详细描述>
 * 
 * @author Administrator
 * @version [版本号, 2014年4月24日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Entity
@Table(name = "fi_la_gl_record")
public class LAGLRecord implements Serializable {
    
    /** 注释内容 */
    private static final long serialVersionUID = -6031308364401303434L;
    
    /** 主键：id; */
    @Id
    @Column(nullable = false, length = 64, updatable = false)
    private String id;
    
    /** 虚中心id */
    @Column(nullable = false, length = 64, updatable = false)
    private String vcid;
    
    /** 对应贷款账户id: */
    @Column(nullable = false, length = 64, updatable = false)
    private String loanAccountId;
    
    /** 合同id */
    @Column(nullable = false, length = 64, updatable = false)
    private String contractId;
    
    /** 合同号 */
    @Column(nullable = false, length = 64, updatable = false)
    private String contractNumber;
    
    /** 交易记录主键： */
    @Column(nullable = false, length = 64, updatable = false)
    private String tradingRecordId;
    
    /** 主贷人姓名 */
    private String clientName;
    
    /** 操作流水： */
    private String requestId;
    
    /** 客户账户: */
    private String clientAccountId;
    
    /** 创建时间: */
    private Date createDate;
    
    /** 借贷类型 */
    private DebitCreditDirectionEnum direction;
    
    /** 借贷方引用类型: */
    private GLRecordRefTypeEnum refType;
    
    /** 借贷方引用id: */
    private String refId;
    
    /** 借贷方名: */
    private String name;
    
    /** 借贷方科目主键： */
    private String accountTitleId;
    
    /** 借贷方科目编码： */
    private String accountTitleCode;
    
    /** 金额 */
    private BigDecimal amount;
    
    /** 递减账金额 */
    private BigDecimal amountIrr;
    
    /** GL摘要 */
    private String summary;
    
    /** 公司id */
    private String companyId;
    
    /** 公司名称 */
    private String companyName;
    
    /** 财务总账记录类型 */
    private GLRecordTypeEnum glRecordType;
    
    /** 核算项id（省） */
    private String provinceId;
    
    /** 核算项名（省） */
    private String provinceName;
    
    /** 核算项编码（省） */
    private String provinceCode;
    
    /** 核算项id（市） */
    private String cityId;
    
    /** 核算项名（市） */
    private String cityName;
    
    /** 核算项编码（市） */
    private String cityCode;
    
    /** 核算项id（区域） */
    private String areaId;
    
    /** 核算项名（区域） */
    private String areaName;
    
    /** 核算项编码（区域） */
    private String areaCode;
    
    /** 核算项id（分行） */
    private String branchId;
    
    /** 核算项名（分行） */
    private String branchName;
    
    /** 核算项编码（分行） */
    private String branchCode;
    
    /** 核算项id（产品） */
    private String creditProductId;
    
    /** 核算项名（产品） */
    private String creditProductName;
    
    /** 核算项编码（产品） */
    private String creditProductCode;
    
    /** 财务凭证号，不参与数据化持久 用于夜间事物生成excel时使用 */
    @Transient
    private int financePzNumber;
    
    /** 分录序号，不参与数据化持久 用于夜间事物生成excel时使用 */
    @Transient
    private int journalizing;
    
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
     * @return 返回 contractId
     */
    public String getContractId() {
        return contractId;
    }
    
    /**
     * @param 对contractId进行赋值
     */
    public void setContractId(String contractId) {
        this.contractId = contractId;
    }
    
    /**
     * @return 返回 contractNumber
     */
    public String getContractNumber() {
        return contractNumber;
    }
    
    /**
     * @param 对contractNumber进行赋值
     */
    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber;
    }
    
    /**
     * @return 返回 clientName
     */
    public String getClientName() {
        return clientName;
    }
    
    /**
     * @param 对clientName进行赋值
     */
    public void setClientName(String clientName) {
        this.clientName = clientName;
    }
    
    /**
     * @return 返回 requestId
     */
    public String getRequestId() {
        return requestId;
    }
    
    /**
     * @param 对requestId进行赋值
     */
    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }
    
    /**
     * @return 返回 tradingRecordId
     */
    public String getTradingRecordId() {
        return tradingRecordId;
    }
    
    /**
     * @param 对tradingRecordId进行赋值
     */
    public void setTradingRecordId(String tradingRecordId) {
        this.tradingRecordId = tradingRecordId;
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
     * @return 返回 clientAccountId
     */
    public String getClientAccountId() {
        return clientAccountId;
    }
    
    /**
     * @param 对clientAccountId进行赋值
     */
    public void setClientAccountId(String clientAccountId) {
        this.clientAccountId = clientAccountId;
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
     * @return 返回 direction
     */
    public DebitCreditDirectionEnum getDirection() {
        return direction;
    }
    
    /**
     * @param 对direction进行赋值
     */
    public void setDirection(DebitCreditDirectionEnum direction) {
        this.direction = direction;
    }
    
    /**
     * @return 返回 refType
     */
    public GLRecordRefTypeEnum getRefType() {
        return refType;
    }
    
    /**
     * @param 对refType进行赋值
     */
    public void setRefType(GLRecordRefTypeEnum refType) {
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
     * @return 返回 accountTitleId
     */
    public String getAccountTitleId() {
        return accountTitleId;
    }
    
    /**
     * @param 对accountTitleId进行赋值
     */
    public void setAccountTitleId(String accountTitleId) {
        this.accountTitleId = accountTitleId;
    }
    
    /**
     * @return 返回 accountTitleCode
     */
    public String getAccountTitleCode() {
        return accountTitleCode;
    }
    
    /**
     * @param 对accountTitleCode进行赋值
     */
    public void setAccountTitleCode(String accountTitleCode) {
        this.accountTitleCode = accountTitleCode;
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
     * @return 返回 amountIrr
     */
    public BigDecimal getAmountIrr() {
        return amountIrr;
    }
    
    /**
     * @param 对amountIrr进行赋值
     */
    public void setAmountIrr(BigDecimal amountIrr) {
        this.amountIrr = amountIrr;
    }
    
    /**
     * @return 返回 summary
     */
    public String getSummary() {
        return summary;
    }
    
    /**
     * @param 对summary进行赋值
     */
    public void setSummary(String summary) {
        this.summary = summary;
    }
    
    /**
     * @return 返回 companyId
     */
    public String getCompanyId() {
        return companyId;
    }
    
    /**
     * @param 对companyId进行赋值
     */
    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }
    
    /**
     * @return 返回 companyName
     */
    public String getCompanyName() {
        return companyName;
    }
    
    /**
     * @param 对companyName进行赋值
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    
    /**
     * @return 返回 glRecordType
     */
    public GLRecordTypeEnum getGlRecordType() {
        return glRecordType;
    }
    
    /**
     * @param 对glRecordType进行赋值
     */
    public void setGlRecordType(GLRecordTypeEnum glRecordType) {
        this.glRecordType = glRecordType;
    }
    
    /**
     * @return 返回 provinceId
     */
    public String getProvinceId() {
        return provinceId;
    }
    
    /**
     * @param 对provinceId进行赋值
     */
    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }
    
    /**
     * @return 返回 provinceName
     */
    public String getProvinceName() {
        return provinceName;
    }
    
    /**
     * @param 对provinceName进行赋值
     */
    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }
    
    /**
     * @return 返回 provinceCode
     */
    public String getProvinceCode() {
        return provinceCode;
    }
    
    /**
     * @param 对provinceCode进行赋值
     */
    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }
    
    /**
     * @return 返回 cityId
     */
    public String getCityId() {
        return cityId;
    }
    
    /**
     * @param 对cityId进行赋值
     */
    public void setCityId(String cityId) {
        this.cityId = cityId;
    }
    
    /**
     * @return 返回 cityName
     */
    public String getCityName() {
        return cityName;
    }
    
    /**
     * @param 对cityName进行赋值
     */
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
    
    /**
     * @return 返回 cityCode
     */
    public String getCityCode() {
        return cityCode;
    }
    
    /**
     * @param 对cityCode进行赋值
     */
    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }
    
    /**
     * @return 返回 areaId
     */
    public String getAreaId() {
        return areaId;
    }
    
    /**
     * @param 对areaId进行赋值
     */
    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }
    
    /**
     * @return 返回 areaName
     */
    public String getAreaName() {
        return areaName;
    }
    
    /**
     * @param 对areaName进行赋值
     */
    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }
    
    /**
     * @return 返回 areaCode
     */
    public String getAreaCode() {
        return areaCode;
    }
    
    /**
     * @param 对areaCode进行赋值
     */
    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }
    
    /**
     * @return 返回 branchId
     */
    public String getBranchId() {
        return branchId;
    }
    
    /**
     * @param 对branchId进行赋值
     */
    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }
    
    /**
     * @return 返回 branchName
     */
    public String getBranchName() {
        return branchName;
    }
    
    /**
     * @param 对branchName进行赋值
     */
    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }
    
    /**
     * @return 返回 branchCode
     */
    public String getBranchCode() {
        return branchCode;
    }
    
    /**
     * @param 对branchCode进行赋值
     */
    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }
    
    /**
     * @return 返回 creditProductId
     */
    public String getCreditProductId() {
        return creditProductId;
    }
    
    /**
     * @param 对creditProductId进行赋值
     */
    public void setCreditProductId(String creditProductId) {
        this.creditProductId = creditProductId;
    }
    
    /**
     * @return 返回 creditProductName
     */
    public String getCreditProductName() {
        return creditProductName;
    }
    
    /**
     * @param 对creditProductName进行赋值
     */
    public void setCreditProductName(String creditProductName) {
        this.creditProductName = creditProductName;
    }
    
    /**
     * @return 返回 creditProductCode
     */
    public String getCreditProductCode() {
        return creditProductCode;
    }
    
    /**
     * @param 对creditProductCode进行赋值
     */
    public void setCreditProductCode(String creditProductCode) {
        this.creditProductCode = creditProductCode;
    }
    
    /**
     * @return 返回 vcid
     */
    public String getVcid() {
        return vcid;
    }
    
    /**
     * @param 对vcid进行赋值
     */
    public void setVcid(String vcid) {
        this.vcid = vcid;
    }
    
    /**
     * @return 返回 financePzNumber
     */
    public int getFinancePzNumber() {
        return financePzNumber;
    }
    
    /**
     * @param 对financePzNumber进行赋值
     */
    public void setFinancePzNumber(int financePzNumber) {
        this.financePzNumber = financePzNumber;
    }
    
    /**
     * @return 返回 journalizing
     */
    public int getJournalizing() {
        return journalizing;
    }
    
    /**
     * @param 对journalizing进行赋值
     */
    public void setJournalizing(int journalizing) {
        this.journalizing = journalizing;
    }
}
