/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2014年5月21日
 * <修改描述:>
 */
package com.tx.local.loanaccount.context.request.build;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import com.tx.local.basicdata.model.District;
import com.tx.local.basicdata.model.FeeItemEnum;
import com.tx.local.basicdata.model.IDCardTypeEnum;
import com.tx.local.basicdata.model.RepayWayEnum;
import com.tx.local.basicdata.model.TimeUnitTypeEnum;
import com.tx.local.loanaccount.context.request.AbstractLoanAccountRequest;
import com.tx.local.loanaccount.model.LATradingRecord;
import com.tx.local.loanaccount.model.LoanAccountTypeEnum;
import com.tx.local.loanaccount.model.RequestSourceTypeEnum;
import com.tx.local.security.util.WebContextUtils;

/**
 * 贷款账户构建请求<br/>
 * 
 * @author  Tim.peng
 * @version  [版本号, 2017年5月5日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public abstract class AbstractBuildRequest extends AbstractLoanAccountRequest {
    
    /** 远贷款续贷结清交易 */
    private LATradingRecord sourceTradingRecord;
    
    /** 客户信息 */
    private String clientInfoId;
    
    /** 客户姓名 */
    private String clientName;
    
    /** 客户证件类型 */
    private IDCardTypeEnum clientIdCardType;
    
    /** 客户证件号码 */
    private String clientIdCardNumber;
    
    /** 客户账户id */
    private String clientAccountId;
    
    /**  客户的信用信息 */
    private String creditInfoId;
    
    /** 信用信息Tag版本 */
    private String creditInfoVersion;
    
    /** 合同id */
    private String contractId;
    
    /** 合同编号 */
    private String contractNumber;
    
    /** 还款方式 */
    private RepayWayEnum repayWay;
    
    /** 贷款金额 */
    private BigDecimal loanAmount;
    
    /** 总期数 */
    private int totalPeriod;
    
    /** 时间计算单位 */
    private TimeUnitTypeEnum timeUnitType;
    
    /** 时间 */
    private int time;
    
    /** 实际放款金额 */
    private BigDecimal factOutLoanAmount;
    
    /** 实际放款日 */
    private Date factLoanDate;
    
    /** 生效日 */
    private Date effectiveDate;
    
    /** 到期日 */
    private Date expiryDate;
    
    /** 贷前延期天数 */
    private int beforeDelayDays;
    
    /** 计息日 */
    private Date interestDate;
    
    /** 首次还款日 */
    private Date firstRepayDate;
    
    /** 每月还款日  1~31 */
    private int monthlyRepayDay;
    
    /** 每月还款金额 */
    private BigDecimal monthlyRepayAmount;
    
    /** 归属区域 */
    private District district;
    
    /** 贷款账户费用项集合 */
    private Map<FeeItemEnum, String> feeItemMap;
    
    /** <默认构造函数> */
    public AbstractBuildRequest() {
        super();
    }
    
    /** <默认构造函数> */
    public AbstractBuildRequest(String loanAccountId, LoanAccountTypeEnum loanAccountType) {
        super(loanAccountId, loanAccountType, RequestSourceTypeEnum.RPC_REQUEST);
        
        String vcid = WebContextUtils.getVcid();
        String organizationId = WebContextUtils.getOperatorId();
        String operatorId = WebContextUtils.getOperatorId();
        
        //归属信息
        setVcid(vcid);
        setOrganizationId(organizationId);
        setOperatorId(operatorId);
    }
    
    /**
     * @return 返回 sourceTradingRecord
     */
    public LATradingRecord getSourceTradingRecord() {
        return sourceTradingRecord;
    }
    
    /**
     * @param 对sourceTradingRecord进行赋值
     */
    public void setSourceTradingRecord(LATradingRecord sourceTradingRecord) {
        this.sourceTradingRecord = sourceTradingRecord;
    }
    
    /**
     * @return 返回 interestDate
     */
    public Date getInterestDate() {
        if(this.interestDate == null){
            return this.factLoanDate;
        }
        return interestDate;
    }

    /**
     * @param 对interestDate进行赋值
     */
    public void setInterestDate(Date interestDate) {
        this.interestDate = interestDate;
    }

    /**
     * @return 返回 clientInfoId
     */
    public String getClientInfoId() {
        return clientInfoId;
    }
    
    /**
     * @param 对clientInfoId进行赋值
     */
    public void setClientInfoId(String clientInfoId) {
        this.clientInfoId = clientInfoId;
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
     * @return 返回 clientIdCardType
     */
    public IDCardTypeEnum getClientIdCardType() {
        return clientIdCardType;
    }
    
    /**
     * @param 对clientIdCardType进行赋值
     */
    public void setClientIdCardType(IDCardTypeEnum clientIdCardType) {
        this.clientIdCardType = clientIdCardType;
    }
    
    /**
     * @return 返回 clientIdCardNumber
     */
    public String getClientIdCardNumber() {
        return clientIdCardNumber;
    }
    
    /**
     * @param 对clientIdCardNumber进行赋值
     */
    public void setClientIdCardNumber(String clientIdCardNumber) {
        this.clientIdCardNumber = clientIdCardNumber;
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
     * @return 返回 creditInfoId
     */
    public String getCreditInfoId() {
        return creditInfoId;
    }
    
    /**
     * @param 对creditInfoId进行赋值
     */
    public void setCreditInfoId(String creditInfoId) {
        this.creditInfoId = creditInfoId;
    }
    
    /**
     * @return 返回 creditInfoVersion
     */
    public String getCreditInfoVersion() {
        return creditInfoVersion;
    }
    
    /**
     * @param 对creditInfoVersion进行赋值
     */
    public void setCreditInfoVersion(String creditInfoVersion) {
        this.creditInfoVersion = creditInfoVersion;
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
     * @return 返回 repayWay
     */
    public RepayWayEnum getRepayWay() {
        return repayWay;
    }
    
    /**
     * @param 对repayWay进行赋值
     */
    public void setRepayWay(RepayWayEnum repayWay) {
        this.repayWay = repayWay;
    }
    
    /**
     * @return 返回 loanAmount
     */
    public BigDecimal getLoanAmount() {
        return loanAmount;
    }
    
    /**
     * @param 对loanAmount进行赋值
     */
    public void setLoanAmount(BigDecimal loanAmount) {
        this.loanAmount = loanAmount;
    }
    
    /**
     * @return 返回 totalPeriod
     */
    public int getTotalPeriod() {
        return totalPeriod;
    }
    
    /**
     * @param 对totalPeriod进行赋值
     */
    public void setTotalPeriod(int totalPeriod) {
        this.totalPeriod = totalPeriod;
    }
    
    /**
     * @return 返回 timeUnitType
     */
    public TimeUnitTypeEnum getTimeUnitType() {
        return timeUnitType;
    }
    
    /**
     * @param 对timeUnitType进行赋值
     */
    public void setTimeUnitType(TimeUnitTypeEnum timeUnitType) {
        this.timeUnitType = timeUnitType;
    }
    
    /**
     * @return 返回 time
     */
    public int getTime() {
        return time;
    }
    
    /**
     * @param 对time进行赋值
     */
    public void setTime(int time) {
        this.time = time;
    }
    
    /**
     * @return 返回 firstRepayDate
     */
    public Date getFirstRepayDate() {
        return firstRepayDate;
    }

    /**
     * @param 对firstRepayDate进行赋值
     */
    public void setFirstRepayDate(Date firstRepayDate) {
        this.firstRepayDate = firstRepayDate;
    }

    /**
     * @return 返回 factOutLoanAmount
     */
    public BigDecimal getFactOutLoanAmount() {
        return factOutLoanAmount;
    }
    
    /**
     * @param 对factOutLoanAmount进行赋值
     */
    public void setFactOutLoanAmount(BigDecimal factOutLoanAmount) {
        this.factOutLoanAmount = factOutLoanAmount;
    }
    
    /**
     * @return 返回 factLoanDate
     */
    public Date getFactLoanDate() {
        return factLoanDate;
    }
    
    /**
     * @param 对factLoanDate进行赋值
     */
    public void setFactLoanDate(Date factLoanDate) {
        this.factLoanDate = factLoanDate;
        //如果未设置计息日期，则将实际放款日设置为开始计息日<br/>
        if(this.interestDate == null){
            this.interestDate = factLoanDate;
        }
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
     * @return 返回 beforeDelayDays
     */
    public int getBeforeDelayDays() {
        return beforeDelayDays;
    }
    
    /**
     * @param 对beforeDelayDays进行赋值
     */
    public void setBeforeDelayDays(int beforeDelayDays) {
        this.beforeDelayDays = beforeDelayDays;
    }
    
    /**
     * @return 返回 monthlyRepayDay
     */
    public int getMonthlyRepayDay() {
        return monthlyRepayDay;
    }
    
    /**
     * @param 对monthlyRepayDay进行赋值
     */
    public void setMonthlyRepayDay(int monthlyRepayDay) {
        this.monthlyRepayDay = monthlyRepayDay;
    }
    
    /**
     * @return 返回 monthlyRepayAmount
     */
    public BigDecimal getMonthlyRepayAmount() {
        return monthlyRepayAmount;
    }
    
    /**
     * @param 对monthlyRepayAmount进行赋值
     */
    public void setMonthlyRepayAmount(BigDecimal monthlyRepayAmount) {
        this.monthlyRepayAmount = monthlyRepayAmount;
    }
    
    /**
     * @return 返回 district
     */
    public District getDistrict() {
        return district;
    }
    
    /**
     * @param 对district进行赋值
     */
    public void setDistrict(District district) {
        this.district = district;
    }
    
    /**
     * @return 返回 feeItemMap
     */
    public Map<FeeItemEnum, String> getFeeItemMap() {
        return feeItemMap;
    }
    
    /**
     * @param 对feeItemMap进行赋值
     */
    public void setFeeItemMap(Map<FeeItemEnum, String> feeItemMap) {
        this.feeItemMap = feeItemMap;
    }
}
