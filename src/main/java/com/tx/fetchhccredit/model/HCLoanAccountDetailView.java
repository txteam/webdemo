/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2016年2月17日
 * <修改描述:>
 */
package com.tx.fetchhccredit.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


 /**
  * 恒昌贷款账户详情
  * <功能详细描述>
  * 
  * @author  Administrator
  * @version  [版本号, 2016年2月17日]
  * @see  [相关类/方法]
  * @since  [产品/模块版本]
  */
@Entity
@Table(name = "hc_la_loanAccount_detail")
public class HCLoanAccountDetailView {
    
    @Id
    private String id;
    
    /** 进件编号： */
    private String loanBillNumber;
    
    /** 业务状态： */
    private String status;
    
    /** 提交人： */
    private String createOperatorName;
    
    /** 提交时间： */
    private String createDate;
    
    //借款信息
    /** 借款类型 */
    private String creditProductName;
    
    /** 申请期限 */
    private String totalPeriod;
    
    /** 借款费率 */
    private String interestRate;
    
    /** 借款用途 */
    private String uses;
    
    /** 审批金额（万元） */
    private String loanAmount;
    
    /** 可接受最高还款金额 */
    private String maxMonthlyRepayAmount;
    
    //业务信息
    /** 客户姓名 */
    private String serviceClientName;
    
    /** 是否加急 */
    private String urgent;
    
    /** 客服专员 */
    private String customerServiceOfficer;
    
    /** 客服经理 */
    private String customerServiceManager;
    
    /** 客户经理 */
    private String customerServiceTeamManager;
    
    /** 申请日期 */
    private String applyDate;
    
    //个人信息
    /** 客户姓名 */
    private String clientName;
    
    /** 客户性别 */
    private String sex;
    
    /** 证件类型 */
    private String idCardType;
    
    /** 证件号码 */
    private String idCardNumber;
    
    /** 通话详单 */
    private String linkInfo;
    
    /** 移动电话 */
    private String telePhoneNumber;
    
    /** 进件城市 */
    private String districtName;
    
    /** 有无房产 */
    private String hasHouse;
    
    /** 房产有无按揭 */
    private String hasMortgage;
    
    /** 住宅电话 */
    private String housePhoneNumber;
    
    /** 起始居住时间 */
    private String startLiveDate;
    
    //工作情况
    /** 工作状况 */
    private String workInfo;
    
    /** 单位名称 */
    private String workUnitInfo;
    
    /** 单位电话 */
    private String workUnitPhoneNumber;
    
    /** 所属行业 */
    private String workUnitIndustry;
    
    //联系人情况
    private String spouseName;
    
    private String spouseWorkUnitPhoneNumber;
    
    private String spouseTelePhoneNumber;
    
    private String kinPhoneNumber1;
    
    private String kinPhoneNumber2;
    
    private String kinPhoneNumber3;
    
    private String kinPhoneNumber4;
    
    private String linkManPhoneNumber1;
    
    private String linkManPhoneNumber2;
    
    private String linkManPhoneNumber3;
    
    private String linkManPhoneNumber4;
    
    //信审信息
    /** 补件时间 */
    private String patchDate;
    
    /** 补件原因 */
    private String patchReason;
    
    /** 拒绝原因 */
    private String refuseReason;
    
    /** 子原因 */
    private String refuseOtherReason;
    
    /** 初审人员 */
    private String firstTrialOperator;
    
    /** 初审时间 */
    private String firstTrialDate;
    
    /** 终审人员 */
    private String finalTrialOperator;
    
    /** 终审时间 */
    private String finalTrialDate;
    
    /** 备注 */
    private String remark;
    
    /** 月份 */
    private String month;

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
     * @return 返回 loanBillNumber
     */
    public String getLoanBillNumber() {
        return loanBillNumber;
    }

    /**
     * @param 对loanBillNumber进行赋值
     */
    public void setLoanBillNumber(String loanBillNumber) {
        this.loanBillNumber = loanBillNumber;
    }

    /**
     * @return 返回 status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param 对status进行赋值
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return 返回 createOperatorName
     */
    public String getCreateOperatorName() {
        return createOperatorName;
    }

    /**
     * @param 对createOperatorName进行赋值
     */
    public void setCreateOperatorName(String createOperatorName) {
        this.createOperatorName = createOperatorName;
    }

    /**
     * @return 返回 createDate
     */
    public String getCreateDate() {
        return createDate;
    }

    /**
     * @param 对createDate进行赋值
     */
    public void setCreateDate(String createDate) {
        this.createDate = createDate;
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
     * @return 返回 totalPeriod
     */
    public String getTotalPeriod() {
        return totalPeriod;
    }

    /**
     * @param 对totalPeriod进行赋值
     */
    public void setTotalPeriod(String totalPeriod) {
        this.totalPeriod = totalPeriod;
    }

    /**
     * @return 返回 interestRate
     */
    public String getInterestRate() {
        return interestRate;
    }

    /**
     * @param 对interestRate进行赋值
     */
    public void setInterestRate(String interestRate) {
        this.interestRate = interestRate;
    }

    

    /**
     * @return 返回 loanAmount
     */
    public String getLoanAmount() {
        return loanAmount;
    }

    /**
     * @param 对loanAmount进行赋值
     */
    public void setLoanAmount(String loanAmount) {
        this.loanAmount = loanAmount;
    }

    /**
     * @return 返回 maxMonthlyRepayAmount
     */
    public String getMaxMonthlyRepayAmount() {
        return maxMonthlyRepayAmount;
    }

    /**
     * @param 对maxMonthlyRepayAmount进行赋值
     */
    public void setMaxMonthlyRepayAmount(String maxMonthlyRepayAmount) {
        this.maxMonthlyRepayAmount = maxMonthlyRepayAmount;
    }

    /**
     * @return 返回 serviceClientName
     */
    public String getServiceClientName() {
        return serviceClientName;
    }

    /**
     * @param 对serviceClientName进行赋值
     */
    public void setServiceClientName(String serviceClientName) {
        this.serviceClientName = serviceClientName;
    }

    /**
     * @return 返回 urgent
     */
    public String isUrgent() {
        return urgent;
    }

    /**
     * @param 对urgent进行赋值
     */
    public void setUrgent(String urgent) {
        this.urgent = urgent;
    }

    /**
     * @return 返回 customerServiceOfficer
     */
    public String getCustomerServiceOfficer() {
        return customerServiceOfficer;
    }

    /**
     * @param 对customerServiceOfficer进行赋值
     */
    public void setCustomerServiceOfficer(String customerServiceOfficer) {
        this.customerServiceOfficer = customerServiceOfficer;
    }

    /**
     * @return 返回 customerServiceManager
     */
    public String getCustomerServiceManager() {
        return customerServiceManager;
    }

    /**
     * @param 对customerServiceManager进行赋值
     */
    public void setCustomerServiceManager(String customerServiceManager) {
        this.customerServiceManager = customerServiceManager;
    }

    /**
     * @return 返回 customerServiceTeamManager
     */
    public String getCustomerServiceTeamManager() {
        return customerServiceTeamManager;
    }

    /**
     * @param 对customerServiceTeamManager进行赋值
     */
    public void setCustomerServiceTeamManager(String customerServiceTeamManager) {
        this.customerServiceTeamManager = customerServiceTeamManager;
    }

    /**
     * @return 返回 applyDate
     */
    public String getApplyDate() {
        return applyDate;
    }

    /**
     * @param 对applyDate进行赋值
     */
    public void setApplyDate(String applyDate) {
        this.applyDate = applyDate;
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
     * @return 返回 sex
     */
    public String getSex() {
        return sex;
    }

    /**
     * @param 对sex进行赋值
     */
    public void setSex(String sex) {
        this.sex = sex;
    }

    /**
     * @return 返回 idCardType
     */
    public String getIdCardType() {
        return idCardType;
    }

    /**
     * @param 对idCardType进行赋值
     */
    public void setIdCardType(String idCardType) {
        this.idCardType = idCardType;
    }

    /**
     * @return 返回 idCardNumber
     */
    public String getIdCardNumber() {
        return idCardNumber;
    }

    /**
     * @param 对idCardNumber进行赋值
     */
    public void setIdCardNumber(String idCardNumber) {
        this.idCardNumber = idCardNumber;
    }

    /**
     * @return 返回 linkInfo
     */
    public String getLinkInfo() {
        return linkInfo;
    }

    /**
     * @param 对linkInfo进行赋值
     */
    public void setLinkInfo(String linkInfo) {
        this.linkInfo = linkInfo;
    }

    /**
     * @return 返回 telePhoneNumber
     */
    public String getTelePhoneNumber() {
        return telePhoneNumber;
    }

    /**
     * @param 对telePhoneNumber进行赋值
     */
    public void setTelePhoneNumber(String telePhoneNumber) {
        this.telePhoneNumber = telePhoneNumber;
    }

    /**
     * @return 返回 districtName
     */
    public String getDistrictName() {
        return districtName;
    }

    /**
     * @param 对districtName进行赋值
     */
    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    /**
     * @return 返回 hasHouse
     */
    public String getHasHouse() {
        return hasHouse;
    }

    /**
     * @param 对hasHouse进行赋值
     */
    public void setHasHouse(String hasHouse) {
        this.hasHouse = hasHouse;
    }

    /**
     * @return 返回 hasMortgage
     */
    public String getHasMortgage() {
        return hasMortgage;
    }

    /**
     * @param 对hasMortgage进行赋值
     */
    public void setHasMortgage(String hasMortgage) {
        this.hasMortgage = hasMortgage;
    }

    /**
     * @return 返回 housePhoneNumber
     */
    public String getHousePhoneNumber() {
        return housePhoneNumber;
    }

    /**
     * @param 对housePhoneNumber进行赋值
     */
    public void setHousePhoneNumber(String housePhoneNumber) {
        this.housePhoneNumber = housePhoneNumber;
    }

    /**
     * @return 返回 startLiveDate
     */
    public String getStartLiveDate() {
        return startLiveDate;
    }

    /**
     * @param 对startLiveDate进行赋值
     */
    public void setStartLiveDate(String startLiveDate) {
        this.startLiveDate = startLiveDate;
    }

    /**
     * @return 返回 workInfo
     */
    public String getWorkInfo() {
        return workInfo;
    }

    /**
     * @param 对workInfo进行赋值
     */
    public void setWorkInfo(String workInfo) {
        this.workInfo = workInfo;
    }

    /**
     * @return 返回 workUnitInfo
     */
    public String getWorkUnitInfo() {
        return workUnitInfo;
    }

    /**
     * @param 对workUnitInfo进行赋值
     */
    public void setWorkUnitInfo(String workUnitInfo) {
        this.workUnitInfo = workUnitInfo;
    }

    /**
     * @return 返回 workUnitPhoneNumber
     */
    public String getWorkUnitPhoneNumber() {
        return workUnitPhoneNumber;
    }

    /**
     * @param 对workUnitPhoneNumber进行赋值
     */
    public void setWorkUnitPhoneNumber(String workUnitPhoneNumber) {
        this.workUnitPhoneNumber = workUnitPhoneNumber;
    }

    /**
     * @return 返回 workUnitIndustry
     */
    public String getWorkUnitIndustry() {
        return workUnitIndustry;
    }

    /**
     * @param 对workUnitIndustry进行赋值
     */
    public void setWorkUnitIndustry(String workUnitIndustry) {
        this.workUnitIndustry = workUnitIndustry;
    }

    /**
     * @return 返回 spouseName
     */
    public String getSpouseName() {
        return spouseName;
    }

    /**
     * @param 对spouseName进行赋值
     */
    public void setSpouseName(String spouseName) {
        this.spouseName = spouseName;
    }

    /**
     * @return 返回 spouseWorkUnitPhoneNumber
     */
    public String getSpouseWorkUnitPhoneNumber() {
        return spouseWorkUnitPhoneNumber;
    }

    /**
     * @param 对spouseWorkUnitPhoneNumber进行赋值
     */
    public void setSpouseWorkUnitPhoneNumber(String spouseWorkUnitPhoneNumber) {
        this.spouseWorkUnitPhoneNumber = spouseWorkUnitPhoneNumber;
    }

    /**
     * @return 返回 spouseTelePhoneNumber
     */
    public String getSpouseTelePhoneNumber() {
        return spouseTelePhoneNumber;
    }

    /**
     * @param 对spouseTelePhoneNumber进行赋值
     */
    public void setSpouseTelePhoneNumber(String spouseTelePhoneNumber) {
        this.spouseTelePhoneNumber = spouseTelePhoneNumber;
    }

    /**
     * @return 返回 kinPhoneNumber1
     */
    public String getKinPhoneNumber1() {
        return kinPhoneNumber1;
    }

    /**
     * @param 对kinPhoneNumber1进行赋值
     */
    public void setKinPhoneNumber1(String kinPhoneNumber1) {
        this.kinPhoneNumber1 = kinPhoneNumber1;
    }

    /**
     * @return 返回 kinPhoneNumber2
     */
    public String getKinPhoneNumber2() {
        return kinPhoneNumber2;
    }

    /**
     * @param 对kinPhoneNumber2进行赋值
     */
    public void setKinPhoneNumber2(String kinPhoneNumber2) {
        this.kinPhoneNumber2 = kinPhoneNumber2;
    }

    /**
     * @return 返回 kinPhoneNumber3
     */
    public String getKinPhoneNumber3() {
        return kinPhoneNumber3;
    }

    /**
     * @param 对kinPhoneNumber3进行赋值
     */
    public void setKinPhoneNumber3(String kinPhoneNumber3) {
        this.kinPhoneNumber3 = kinPhoneNumber3;
    }

    /**
     * @return 返回 kinPhoneNumber4
     */
    public String getKinPhoneNumber4() {
        return kinPhoneNumber4;
    }

    /**
     * @param 对kinPhoneNumber4进行赋值
     */
    public void setKinPhoneNumber4(String kinPhoneNumber4) {
        this.kinPhoneNumber4 = kinPhoneNumber4;
    }

    /**
     * @return 返回 linkManPhoneNumber1
     */
    public String getLinkManPhoneNumber1() {
        return linkManPhoneNumber1;
    }

    /**
     * @param 对linkManPhoneNumber1进行赋值
     */
    public void setLinkManPhoneNumber1(String linkManPhoneNumber1) {
        this.linkManPhoneNumber1 = linkManPhoneNumber1;
    }

    /**
     * @return 返回 linkManPhoneNumber2
     */
    public String getLinkManPhoneNumber2() {
        return linkManPhoneNumber2;
    }

    /**
     * @param 对linkManPhoneNumber2进行赋值
     */
    public void setLinkManPhoneNumber2(String linkManPhoneNumber2) {
        this.linkManPhoneNumber2 = linkManPhoneNumber2;
    }

    /**
     * @return 返回 linkManPhoneNumber3
     */
    public String getLinkManPhoneNumber3() {
        return linkManPhoneNumber3;
    }

    /**
     * @param 对linkManPhoneNumber3进行赋值
     */
    public void setLinkManPhoneNumber3(String linkManPhoneNumber3) {
        this.linkManPhoneNumber3 = linkManPhoneNumber3;
    }

    /**
     * @return 返回 linkManPhoneNumber4
     */
    public String getLinkManPhoneNumber4() {
        return linkManPhoneNumber4;
    }

    /**
     * @param 对linkManPhoneNumber4进行赋值
     */
    public void setLinkManPhoneNumber4(String linkManPhoneNumber4) {
        this.linkManPhoneNumber4 = linkManPhoneNumber4;
    }

    /**
     * @return 返回 patchDate
     */
    public String getPatchDate() {
        return patchDate;
    }

    /**
     * @param 对patchDate进行赋值
     */
    public void setPatchDate(String patchDate) {
        this.patchDate = patchDate;
    }

    /**
     * @return 返回 patchReason
     */
    public String getPatchReason() {
        return patchReason;
    }

    /**
     * @param 对patchReason进行赋值
     */
    public void setPatchReason(String patchReason) {
        this.patchReason = patchReason;
    }

    /**
     * @return 返回 refuseReason
     */
    public String getRefuseReason() {
        return refuseReason;
    }

    /**
     * @param 对refuseReason进行赋值
     */
    public void setRefuseReason(String refuseReason) {
        this.refuseReason = refuseReason;
    }

    /**
     * @return 返回 refuseOtherReason
     */
    public String getRefuseOtherReason() {
        return refuseOtherReason;
    }

    /**
     * @param 对refuseOtherReason进行赋值
     */
    public void setRefuseOtherReason(String refuseOtherReason) {
        this.refuseOtherReason = refuseOtherReason;
    }

    /**
     * @return 返回 firstTrialOperator
     */
    public String getFirstTrialOperator() {
        return firstTrialOperator;
    }

    /**
     * @param 对firstTrialOperator进行赋值
     */
    public void setFirstTrialOperator(String firstTrialOperator) {
        this.firstTrialOperator = firstTrialOperator;
    }

    /**
     * @return 返回 firstTrialDate
     */
    public String getFirstTrialDate() {
        return firstTrialDate;
    }

    /**
     * @param 对firstTrialDate进行赋值
     */
    public void setFirstTrialDate(String firstTrialDate) {
        this.firstTrialDate = firstTrialDate;
    }

    /**
     * @return 返回 finalTrialOperator
     */
    public String getFinalTrialOperator() {
        return finalTrialOperator;
    }

    /**
     * @param 对finalTrialOperator进行赋值
     */
    public void setFinalTrialOperator(String finalTrialOperator) {
        this.finalTrialOperator = finalTrialOperator;
    }

    /**
     * @return 返回 finalTrialDate
     */
    public String getFinalTrialDate() {
        return finalTrialDate;
    }

    /**
     * @param 对finalTrialDate进行赋值
     */
    public void setFinalTrialDate(String finalTrialDate) {
        this.finalTrialDate = finalTrialDate;
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
     * @return 返回 month
     */
    public String getMonth() {
        return month;
    }

    /**
     * @param 对month进行赋值
     */
    public void setMonth(String month) {
        this.month = month;
    }

    /**
     * @return 返回 uses
     */
    public String getUses() {
        return uses;
    }

    /**
     * @param 对uses进行赋值
     */
    public void setUses(String uses) {
        this.uses = uses;
    }
    
}
