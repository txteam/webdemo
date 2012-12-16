/*
 * 描          述:  <描述>
 * 修  改   人:  PengQingyang
 * 修改时间:  2012-12-16
 * <修改描述:>
 */
package com.tx.components.mainframe.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import com.tx.components.auth.context.AuthContext;
import com.tx.components.mainframe.model.Operator;
import com.tx.components.mainframe.service.MenuService;
import com.tx.components.mainframe.service.OperatorService;

/**
 * 登录功能入口
 * <功能详细描述>
 * 
 * @author  PengQingyang
 * @version  [版本号, 2012-12-16]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Controller("loginController")
public class LoginController {
    
    @Resource(name = "operatorService")
    private OperatorService operatorService;
    
    @Resource(name= "menuService")
    private MenuService menuService;
    
    /**
      * 登录<br/>
      * 1、检查输入的用户名和密码是否正确<br/>
      * 2、如果正确，加载用户权限<br/>
      * 3、页面的用户菜单 tab等，等待页面加载完成后再进行ajax加载
      * 
      * @param loginName
      * @param password
      * @param style 登录界面
      * @return [参数说明]
      * 
      * @return String [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public String login(String loginName, String password, String style) {
        
        Operator oper = this.operatorService.login(loginName, password);
        
        if (oper == null) {
            return "/view/mainframe/login";
        }
        
        //初始化用户权限到当前会话中
        AuthContext.getContext().initCurrentUserAuthContextWhenLogin(loginName);
        
        return "mainframe";
    }
}
