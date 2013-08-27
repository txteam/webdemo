/*
 * 描          述:  <描述>
 * 修  改   人:  PengQingyang
 * 修改时间:  2013-8-27
 * <修改描述:>
 */
package com.tx.component.operator.controller;



 /**
  * 职位显示层逻辑<br/>
  * <功能详细描述>
  * 
  * @author  PengQingyang
  * @version  [版本号, 2013-8-27]
  * @see  [相关类/方法]
  * @since  [产品/模块版本]
  */
public class PostController {
    
    /**
      * 跳转到查询职位列表<br/>
      *<功能详细描述>
      * @return [参数说明]
      * 
      * @return String [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public String toQueryPostList(){
        return "/operator/queryPostList";
    }
    
}
