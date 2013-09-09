/*
 * 描          述:  <描述>
 * 修  改   人:  PengQingyang
 * 修改时间:  2013-9-9
 * <修改描述:>
 */
package com.tx.component.operator.service;


 /**
  * 关联组织接口<br/>
  *     删除、停用组织时将会自动调用该接口判断是否有对象关联了对应组织<br/>
  * <功能详细描述>
  * 
  * @author  PengQingyang
  * @version  [版本号, 2013-9-9]
  * @see  [相关类/方法]
  * @since  [产品/模块版本]
  */
public interface RelateOrganization {
    
    /**
      * 组织引用名称<br/>
      *<功能详细描述>
      * @return [参数说明]
      * 
      * @return String [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public String organizationReferenceName();
    
    /**
      * 判断对应组织是否被关联<br/>
      *<功能详细描述>
      * @param organizationId
      * @return [参数说明]
      * 
      * @return boolean [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public boolean isReferenceOrganization(String organizationId);
}
