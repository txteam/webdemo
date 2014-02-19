/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2014年2月19日
 * <修改描述:>
 */
package com.tx.component.operator.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.tx.component.mainframe.treeview.CheckAbleTreeNode;


 /**
  * 操作员职位业务逻辑类
  * <功能详细描述>
  * 
  * @author  Administrator
  * @version  [版本号, 2014年2月19日]
  * @see  [相关类/方法]
  * @since  [产品/模块版本]
  */
@Component("operator2PostService")
public class Operator2PostService {
    
    @Resource(name = "operatorRefService")
    private OperatorRefService operatorRefService;
    
    /**
      * 根据用户id查询用户的角色<br/>
      *<功能详细描述>
      * @param postId
      * @return [参数说明]
      * 
      * @return List<CheckAbleTreeNode> [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public List<CheckAbleTreeNode> queryPostCheckAbleTreeNodeListByOperatorId(String operatorId){
        
        return null;
    }
    
}
