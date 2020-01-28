/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2017年6月25日
 * <修改描述:>
 */
package com.tx.local.loanaccount.context.request;

import com.tx.local.loanaccount.model.LABankAccount;

/**
  * 银行账户注入处理器<br/>
  * <功能详细描述>
  * 
  * @author  Administrator
  * @version  [版本号, 2017年6月25日]
  * @see  [相关类/方法]
  * @since  [产品/模块版本]
  */
public interface LABankAccountAware {
    
    /**
     * 获取银行账户id<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
   public String getBankAccountId();
   
   /**
    * 银行账户注入<br/>
    * <功能详细描述>
    * @param bankAccount [参数说明]
    * 
    * @return void [返回类型说明]
    * @exception throws [异常类型] [异常说明]
    * @see [类、类#方法、类#成员]
    */
   public void setBankAccount(LABankAccount bankAccount);
   
   /**
     * 获取银行账户实例<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return ClientAccount [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
   public LABankAccount getBankAccount();
}
