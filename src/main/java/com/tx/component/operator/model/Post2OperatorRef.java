/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2014年2月16日
 * <修改描述:>
 */
package com.tx.component.operator.model;

import com.tx.component.operator.OperatorConstants;

/**
 * 职位对操作员的引用<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2014年2月16日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class Post2OperatorRef extends OperatorRef {
    
    /** <默认构造函数> */
    public Post2OperatorRef() {
        setRefType(OperatorConstants.OPERATORREF_TYPE_POST);
    }
    
    /**
      * 获取职位id
      *<功能详细描述>
      * @return [参数说明]
      * 
      * @return String [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public String getPostId() {
        return getRefId();
    }
    
    /**
      * 指定职位id
      *<功能详细描述>
      * @param postId [参数说明]
      * 
      * @return void [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public void setPostId(String postId) {
        setRefId(postId);
    }
    
}
