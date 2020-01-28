/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2016年1月13日
 * <修改描述:>
 */
package com.tx.local.loanaccount.context.request;

import com.tx.local.loanaccount.model.LoanAccount;


 /**
  * 客户账户注入<br/>
  * <功能详细描述>
  * 
  * @author  Administrator
  * @version  [版本号, 2016年1月13日]
  * @see  [相关类/方法]
  * @since  [产品/模块版本]
  */
public interface LoanAccountAware {
    
    /**
      * 获取客户id<br/>
      * <功能详细描述>
      * @return [参数说明]
      * 
      * @return String [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public String getLoanAccountId();
    
    /**
      * 将贷款账户设置入请求对象中<br/>
      * <功能详细描述>
      * @param loanAccount [参数说明]
      * 
      * @return void [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public void setLoanAccount(LoanAccount loanAccount);
    
    /**
      * 获取贷款账户实例<br/>
      * <功能详细描述>
      * @return [参数说明]
      * 
      * @return ClientAccount [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public LoanAccount getLoanAccount();
}
