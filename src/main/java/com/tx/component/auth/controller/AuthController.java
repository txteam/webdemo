/*
 * 描          述:  <描述>
 * 修  改   人:  PengQingyang
 * 修改时间:  2013-8-26
 * <修改描述:>
 */
package com.tx.component.auth.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tx.component.auth.context.AuthSessionContext;
import com.tx.component.auth.model.AuthItem;
import com.tx.core.tree.util.TreeUtils;

/**
 * 权限显示层
 * <功能详细描述>
 * 
 * @author  PengQingyang
 * @version  [版本号, 2013-8-26]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Controller
@RequestMapping("auth")
public class AuthController {
    
    /**
      * 查询当前人员权限列表<br/> 
      *<功能详细描述>
      * @return [参数说明]
      * 
      * @return List<AuthItem> [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public List<AuthItem> queryAuthItemTreeListDependAuth() {
        List<AuthItem> authItemList = AuthSessionContext.getAuthItemListDependAuthRefOfSession();
        
        List<AuthItem> authItemTreeList = TreeUtils.changToTree(authItemList);
        return authItemTreeList;
    }
    
}
