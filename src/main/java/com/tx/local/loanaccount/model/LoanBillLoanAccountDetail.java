/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2017年5月1日
 * <修改描述:>
 */
package com.tx.local.loanaccount.model;

import com.tx.local.loanbill.model.LoanTypeEnum;

/**
  * 贷款单|贷款账户详情<br/>
  * <功能详细描述>
  * 
  * @author  Administrator
  * @version  [版本号, 2017年5月1日]
  * @see  [相关类/方法]
  * @since  [产品/模块版本]
  */
public interface LoanBillLoanAccountDetail extends LoanAccountDetail {
    
    /** 贷款类别:新贷、再贷、续贷 */
    public LoanTypeEnum getLoanType();
    
    /** 客户贷款账户id */
    public String getClientLoanAccountId();
    
    /** 贷款申请单id */
    public String getLoanBillId();
    
    /** 贷款申请单编号 */
    public String getLoanBillNumber();
    
    /** 贷款产品id */
    public String getLoanProductId();
    
    /** 贷款产品名 */
    public String getLoanProductName();
}
