/*
 * 描          述:  <描述>
 * 修  改   人:  PengQingyang
 * 修改时间:  2012-12-2
 * <修改描述:>
 */
package com.tx.component.auth.model;


 /**
  * 操作员权限项引用
  * <功能详细描述>
  * 
  * @author  PengQingyang
  * @version  [版本号, 2012-12-2]
  * @see  [相关类/方法]
  * @since  [产品/模块版本]
  */
public class OperatorAuthItemRef extends AuthItemRef {
    
    /** 注释内容 */
    private static final long serialVersionUID = -5225480864798103809L;
    
    /** 操作员id */
    private String operatorId;
    
    /**
     * @return
     */
    @Override
    public String getAuthRefType() {
        return AuthItemRef.AUTHREFTYPE_OPERATOR;
    }
    
    /**
     * @return
     */
    @Override
    public String getRefId() {
        return this.operatorId;
    }
    
}
