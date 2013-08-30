/*
 * 描          述:  <描述>
 * 修  改   人:  brady
 * 修改时间:  2013-8-30
 * <修改描述:>
 */
package com.tx.component.mainframe.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;

import com.tx.component.auth.context.AuthContext;
import com.tx.component.operator.service.OperatorService;
import com.tx.component.operator.service.OrganizationService;
import com.tx.component.operator.service.PostService;
import com.tx.core.exceptions.util.AssertUtils;


 /**
  * <功能简述>
  * <功能详细描述>
  * 
  * @author  brady
  * @version  [版本号, 2013-8-30]
  * @see  [相关类/方法]
  * @since  [产品/模块版本]
  */
public class AuthManageService {
    
    @Resource(name = "organizationService")
    private OrganizationService organizationService;
    
    @Resource(name = "postService")
    private PostService postService;
    
    @Resource(name = "operatorService")
    private OperatorService operatorService;
    
    @Resource(name = "authContext")
    private AuthContext authContext;
    
    /**
      * 保存职位权限<br/>
      *<功能详细描述>
      * @param authType
      * @param postId
      * @param authIdList [参数说明]
      * 
      * @return void [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public void savePostAuth(String authType,String postId,List<String> authIdList){
        AssertUtils.notEmpty(postId, "postId is empty");
        
        if(StringUtils.isEmpty(authType)){
            authContext.saveAuthRefOfAuthItemIdList(authRefType, postId, authItemIdList)
        }else{
            
        }
    }
}
