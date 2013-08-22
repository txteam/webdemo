/*
 * 描          述:  <描述>
 * 修  改   人:  PengQingyang
 * 修改时间:  2012-12-16
 * <修改描述:>
 */
package com.tx.component.mainframe.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tx.component.auth.AuthConstant;
import com.tx.component.auth.context.AuthContext;
import com.tx.component.mainframe.context.MenuContext;
import com.tx.component.mainframe.context.WebContextUtils;
import com.tx.component.mainframe.model.MenuItem;
import com.tx.component.mainframe.service.OperatorService;
import com.tx.component.operator.model.Operator;

/**
 * 登录功能入口
 * <功能详细描述>
 * 
 * @author  PengQingyang
 * @version  [版本号, 2012-12-16]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
//@SessionAttributes({WebContextUtils.SESSION_CURRENT_AUTHMAP,WebContextUtils.SESSION_CURRENT_OPERATOR})
@Controller("mainframeController")
@RequestMapping("/mainframe")
public class MainframeController {
    
    @SuppressWarnings("unused")
    private Logger logger = LoggerFactory.getLogger(MainframeController.class);
    
    @Resource(name = "operatorService")
    private OperatorService operatorService;
    
    @Resource(name = "authContext")
    private AuthContext authContext;
    
    @RequestMapping("/toLogin")
    public String toLogin(){
        return "/mainframe/login";
    }
    
    /**
      * 登录<br/>
      * 1、检查输入的用户名和密码是否正确<br/>
      * 2、如果正确，加载用户权限<br/>
      * 3、查询页面菜单<br/>
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
    @RequestMapping("/login")
    public String login(@RequestParam("loginName")String loginName, @RequestParam("password")String password, Model model) {
        //登录人员
        Operator oper = this.operatorService.login(loginName, password);
        
        //如果登录不成功继续返回登录页面
        if (oper == null) {
            return "/mainframe/login";
        }
        
        model.addAttribute(WebContextUtils.SESSION_CURRENT_OPERATOR, oper);
        
        //初始化用户权限到当前会话中
        //authSessionContext.initCurrentUserAuthContextWhenLogin("123456");//初始化用户权限到当前会话中 
        Map<String, String> refType2RefIdMapping = new HashMap<String, String>();
        refType2RefIdMapping.put(AuthConstant.AUTHREFTYPE_OPERATOR, "123456");
        refType2RefIdMapping.put(AuthConstant.AUTHREFTYPE_OPERATOR_TEMPORARY, "123456");
        //refType2RefIdMapping.put(AuthConstant.AUTHREFTYPE_POST, postId);
        //refType2RefIdMapping.put(AuthConstant.AUTHREFTYPE_ORGANIZATION, loginOrganization.getId());
        authContext.login(refType2RefIdMapping);
        
        //根据当前用户权限获取当前用户的菜单列表
        List<MenuItem> mainMenuItemTreeList = MenuContext.getContext().getMenuItemTreeListFromCurrentSession(MenuItem.TYPE_MAIN_MENU);
        model.addAttribute("menuItemTreeList", mainMenuItemTreeList);
        
        return "/mainframe/mainframe";
    }
}
