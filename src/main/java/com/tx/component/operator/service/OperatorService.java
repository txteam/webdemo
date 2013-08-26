/*
 * 描          述:  <描述>
 * 修  改   人:  PengQingyang
 * 修改时间:  2013-8-26
 * <修改描述:>
 */
package com.tx.component.operator.service;

import org.springframework.stereotype.Component;

import com.tx.component.operator.model.Operator;
import com.tx.core.exceptions.util.AssertUtils;


 /**
  * 操作员业务层逻辑<br/>
  * <功能详细描述>
  * 
  * @author  PengQingyang
  * @version  [版本号, 2013-8-26]
  * @see  [相关类/方法]
  * @since  [产品/模块版本]
  */
@Component("operatorService")
public class OperatorService {
    
    public Operator login(String id,String password){
        AssertUtils.notEmpty(id, "is is empty.");
        AssertUtils.notEmpty(password, "password is empty.");
        
        Operator oper = new Operator();
        return oper;
    }
    
    /**
      * 操作员信息<br/>
      *     通过该接口会直接影响到多张表<br/>
      *     user_operator
      *     user_employee
      *<功能详细描述>
      * @param operator [参数说明]
      * 
      * @return void [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public void addOperator(Operator operator){
        AssertUtils.notNull(operator, "operator is null");
    }
    
}
