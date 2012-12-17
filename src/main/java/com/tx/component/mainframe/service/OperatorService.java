/*
 * 描          述:  <描述>
 * 修  改   人:  PengQingyang
 * 修改时间:  2012-12-16
 * <修改描述:>
 */
package com.tx.component.mainframe.service;

import com.tx.component.mainframe.model.Operator;


 /**
  * 操作员业务层
  * 
  * @author  PengQingyang
  * @version  [版本号, 2012-12-16]
  * @see  [相关类/方法]
  * @since  [产品/模块版本]
  */
public class OperatorService {
    
    /**
      * 操作员登录
      * <功能详细描述>
      * @param loginName
      * @param password
      * @return [参数说明]
      * 
      * @return Operator [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public Operator login(String loginName,String password){
        if(!"admin".equals(loginName) ||
                !"admin".equals(password)){
            return null;
        }
        Operator oper = new Operator();
        oper.setId("123456");
        oper.setCode("000");
        oper.setName("管理员");
        
        return oper;
    }
}
