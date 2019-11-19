///*
// * 描          述:  <描述>
// * 修  改   人:  Administrator
// * 修改时间:  2019年11月19日
// * <修改描述:>
// */
//package com.tx.local.operator.controller;
//
//import java.util.List;
//import java.util.Set;
//
//import javax.annotation.Resource;
//
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.beans.factory.InitializingBean;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.ModelMap;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import com.tx.component.auth.context.AuthRegistry;
//import com.tx.component.auth.context.AuthTypeRegistry;
//import com.tx.component.auth.model.AuthItem;
//import com.tx.component.auth.model.AuthTypeItem;
//import com.tx.component.security.context.SecurityContext;
//import com.tx.core.TxConstants;
//import com.tx.local.operator.facade.OperatorRoleFacade;
//import com.tx.local.organization.facade.PostFacade;
//import com.tx.local.organization.model.Post;
//
///**
// * <功能简述>
// * <功能详细描述>
// * 
// * @author  Administrator
// * @version  [版本号, 2019年11月19日]
// * @see  [相关类/方法]
// * @since  [产品/模块版本]
// */
//@Controller("authController")
//@RequestMapping("/auth")
//public class OperatorAuthController implements InitializingBean{
//    
//    @Resource
//    private SecurityContext securityContext;
//    
//    @Resource
//    private PostFacade postFacade;
//    
//    @Resource
//    private OperatorRoleFacade operatorRoleFacade;
//    
//    private AuthTypeRegistry authTypeRegistry;
//    
//    private AuthRegistry authRegistry;
//    
//    /**
//     * @throws Exception
//     */
//    @Override
//    public void afterPropertiesSet() throws Exception {
//        this.authTypeRegistry = securityContext.getAuthTypeRegistry();
//        this.authRegistry = securityContext.getAuthRegistry();
//        
//        securityContext.getRoleTypeRegistry();
//        securityContext.getRoleRegistry();
//    }
//
//    /**
//     * 跳转到查询权限视图<br/>
//     * <功能详细描述>
//     * @return [参数说明]
//     * 
//     * @return String [返回类型说明]
//     * @exception throws [异常类型] [异常说明]
//     * @see [类、类#方法、类#成员]
//     */
//    @RequestMapping("/toQueryAuthMain")
//    public String toQueryAuthView(ModelMap modelMap) {
//        return "/mainframe/queryAuthMain";
//    }
//    
//    /**
//     * 跳转到配置职位权限页面
//     * <功能详细描述>
//     * @param postId
//     * @param modelMap
//     * @return [参数说明]
//     * 
//     * @return String [返回类型说明]
//     * @exception throws [异常类型] [异常说明]
//     * @see [类、类#方法、类#成员]
//     */
//    @RequestMapping("/toConfigPostAuth")
//    public String toConfigPostAuth(@RequestParam("postId") String postId,
//            @RequestParam("authTypeId") String authTypeId,
//            ModelMap modelMap) {
//        List<AuthTypeItem> hasChildAuthTypeList = this.authManageService
//                .queryCurrentOperatorAuthTypeItemOfHasChild();
//        
//        Post post = this.postFacade.findById(postId);
//        modelMap.put("postId", postId);
//        modelMap.put("post", post);
//        
//        modelMap.put("authTypeList", hasChildAuthTypeList);
//        
//        return "/auth/configPostAuth";
//    }
//    
//    /**
//     * 跳转到配置权限职位页面<br/>
//     *<功能详细描述>
//     * @param authItemId
//     * @param modelMap
//     * @return [参数说明]
//     * 
//     * @return String [返回类型说明]
//     * @exception throws [异常类型] [异常说明]
//     * @see [类、类#方法、类#成员]
//     */
//    @RequestMapping("/toConfigAuthPost")
//    public String toConfigAuthPost(@RequestParam("authId") String authId,
//            ModelMap modelMap) {
//        Set<AuthItem> addAuthItems = this.authManageService
//                .getParentAuthItems(authItemId);
//        Set<AuthItem> deleteAuthItems = this.authManageService
//                .getChildAuthItems(authItemId);
//        
//        StringBuilder addAuthItemNameSb = new StringBuilder(
//                TxConstants.INITIAL_STR_LENGTH);
//        for (AuthItem authItemTemp : addAuthItems) {
//            addAuthItemNameSb.append(authItemTemp.getName()).append(",");
//        }
//        StringBuilder deleteAuthItemNameSb = new StringBuilder(
//                TxConstants.INITIAL_STR_LENGTH);
//        for (AuthItem authItemTemp : deleteAuthItems) {
//            deleteAuthItemNameSb.append(authItemTemp.getName()).append(",");
//        }
//        modelMap.put("authItemName",
//                AuthContext.getContext()
//                        .getAuthItemFromContextById(authItemId)
//                        .getName());
//        modelMap.put("authItemId", authItemId);
//        modelMap.put("addAuthItemNames",
//                StringUtils.substring(addAuthItemNameSb.toString(), 0, -1));
//        modelMap.put("deleteAuthItemNames",
//                StringUtils.substring(deleteAuthItemNameSb.toString(), 0, -1));
//        
//        return "/mainframe/configAuthPost";
//    }
//    
//    /**
//     * 跳转到配置角色权限页面 
//     *<功能详细描述>
//     * @param postId
//     * @param modelMap
//     * @return [参数说明]
//     * 
//     * @return String [返回类型说明]
//     * @exception throws [异常类型] [异常说明]
//     * @see [类、类#方法、类#成员]
//     */
//    @RequestMapping("/toConfigRoleAuth")
//    public String toConfigRoleAuth(@RequestParam("roleId") String roleId,
//            ModelMap modelMap) {
//        List<AuthTypeItem> hasChildAuthTypeList = this.authManageService
//                .queryCurrentOperatorAuthTypeItemOfHasChild();
//        
//        modelMap.put("roleId", roleId);
//        modelMap.put("role", this.roleService.findById(roleId));
//        modelMap.put("authTypeList", hasChildAuthTypeList);
//        return "/auth/configRoleAuth";
//    }
//    
//    /**
//     * 跳转到配置权限职位页面<br/>
//      *<功能详细描述>
//      * @param authItemId
//      * @param modelMap
//      * @return [参数说明]
//      * 
//      * @return String [返回类型说明]
//      * @exception throws [异常类型] [异常说明]
//      * @see [类、类#方法、类#成员]
//     */
//    @RequestMapping("/toConfigAuthRole")
//    public String toConfigAuthRole(
//            @RequestParam("authItemId") String authItemId, ModelMap modelMap) {
//        Set<AuthItem> addAuthItems = this.authManageService
//                .getParentAuthItems(authItemId);
//        Set<AuthItem> deleteAuthItems = this.authManageService
//                .getChildAuthItems(authItemId);
//        
//        StringBuilder addAuthItemNameSb = new StringBuilder(
//                TxConstants.INITIAL_STR_LENGTH);
//        for (AuthItem authItemTemp : addAuthItems) {
//            addAuthItemNameSb.append(authItemTemp.getName()).append(",");
//        }
//        StringBuilder deleteAuthItemNameSb = new StringBuilder(
//                TxConstants.INITIAL_STR_LENGTH);
//        for (AuthItem authItemTemp : deleteAuthItems) {
//            deleteAuthItemNameSb.append(authItemTemp.getName()).append(",");
//        }
//        modelMap.put("authItemName",
//                AuthContext.getContext()
//                        .getAuthItemFromContextById(authItemId)
//                        .getName());
//        modelMap.put("authItemId", authItemId);
//        modelMap.put("addAuthItemNames",
//                StringUtils.substring(addAuthItemNameSb.toString(), 0, -1));
//        modelMap.put("deleteAuthItemNames",
//                StringUtils.substring(deleteAuthItemNameSb.toString(), 0, -1));
//        return "/mainframe/configAuthRole";
//    }
//    
//    /**
//     * 跳转到配置操作员权限列表页面<br/>
//     *<功能详细描述>
//     * @param modelMap
//     * @return [参数说明]
//     * 
//     * @return String [返回类型说明]
//     * @exception throws [异常类型] [异常说明]
//     * @see [类、类#方法、类#成员]
//     */
//    @RequestMapping("/toConfigOperatorAuth")
//    public String toConfigOperatorAuth(
//            @RequestParam("operatorId") String operatorId, ModelMap modelMap) {
//        List<AuthTypeItem> hasChildAuthTypeList = this.authManageService
//                .queryCurrentOperatorAuthTypeItemOfHasChild();
//        
//        modelMap.put("operatorId", operatorId);
//        modelMap.put("authTypeList", hasChildAuthTypeList);
//        return "/auth/configOperatorAuth";
//    }
//    
//    /**
//     * 跳转到配置权限人员页面<br/> 
//     *<功能详细描述>
//     * @param operatorId
//     * @param modelMap
//     * @return [参数说明]
//     * 
//     * @return String [返回类型说明]
//     * @exception throws [异常类型] [异常说明]
//     * @see [类、类#方法、类#成员]
//    */
//    @RequestMapping("/toConfigAuthOperator")
//    public String toConfigAuthOperator(
//            @RequestParam("authItemId") String authItemId, ModelMap modelMap) {
//        
//        Set<AuthItem> addAuthItems = this.authManageService
//                .getParentAuthItems(authItemId);
//        Set<AuthItem> deleteAuthItems = this.authManageService
//                .getChildAuthItems(authItemId);
//        
//        StringBuilder addAuthItemNameSb = new StringBuilder(
//                TxConstants.INITIAL_STR_LENGTH);
//        for (AuthItem authItemTemp : addAuthItems) {
//            addAuthItemNameSb.append(authItemTemp.getName()).append(",");
//        }
//        StringBuilder deleteAuthItemNameSb = new StringBuilder(
//                TxConstants.INITIAL_STR_LENGTH);
//        for (AuthItem authItemTemp : deleteAuthItems) {
//            deleteAuthItemNameSb.append(authItemTemp.getName()).append(",");
//        }
//        modelMap.put("authItemName",
//                AuthContext.getContext()
//                        .getAuthItemFromContextById(authItemId)
//                        .getName());
//        modelMap.put("authItemId", authItemId);
//        modelMap.put("addAuthItemNames",
//                StringUtils.substring(addAuthItemNameSb.toString(), 0, -1));
//        modelMap.put("deleteAuthItemNames",
//                StringUtils.substring(deleteAuthItemNameSb.toString(), 0, -1));
//        return "/mainframe/configAuthOperator";
//    }
//    
//}
