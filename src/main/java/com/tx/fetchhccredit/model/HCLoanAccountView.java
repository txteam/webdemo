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
  * 贷款账户
  * <功能详细描述>
  * 
  * @author  Administrator
  * @version  [版本号, 2016年2月17日]
  * @see  [相关类/方法]
  * @since  [产品/模块版本]
  */
@Entity
@Table(name = "hc_la_loanAccount")
public class HCLoanAccountView {
    
    @Id
    private String id;
    
    private String status;
    
    private String districtName;
    
    private String branchName;
    
    private String clientName;
    
    private String loanBillNumber;
    
    private String idCardNumber;
    
    private String phoneNumber;
    
    private String applyDate;
    
    private String creditProductName;
    
    private String customerServiceOfficer;
    
    private String customerServiceManager;
    
    private String customerServiceTeamManager;

    /** <默认构造函数> */
    public HCLoanAccountView(String id, String status, String districtName,
            String branchName, String clientName, String loanBillNumber,
            String idCardNumber, String phoneNumber, String applyDate,
            String creditProductName, String customerServiceOfficer,
            String customerServiceManager, String customerServiceTeamManager) {
        super();
        this.id = id;
        this.status = status;
        this.districtName = districtName;
        this.branchName = branchName;
        this.clientName = clientName;
        this.loanBillNumber = loanBillNumber;
        this.idCardNumber = idCardNumber;
        this.phoneNumber = phoneNumber;
        this.applyDate = applyDate;
        this.creditProductName = creditProductName;
        this.customerServiceOfficer = customerServiceOfficer;
        this.customerServiceManager = customerServiceManager;
        this.customerServiceTeamManager = customerServiceTeamManager;
    }

    /** <默认构造函数> */
    public HCLoanAccountView() {
        super();
        // TODO Auto-generated constructor stub
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
     * @return 返回 phoneNumber
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * @param 对phoneNumber进行赋值
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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
}
