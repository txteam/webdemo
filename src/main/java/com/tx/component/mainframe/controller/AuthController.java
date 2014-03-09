/*
 * 描          述:  <描述>
 * 修  改   人:  PengQingyang
 * 修改时间:  2013-8-26
 * <修改描述:>
 */
package com.tx.component.mainframe.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tx.component.auth.AuthConstant;
import com.tx.component.auth.annotation.CheckOperateAuth;
import com.tx.component.auth.context.AuthContext;
import com.tx.component.auth.model.AuthItem;
import com.tx.component.auth.model.AuthTypeItem;
import com.tx.component.mainframe.service.AuthManageService;
import com.tx.component.mainframe.treeview.CheckAbleTreeNode;
import com.tx.component.operator.service.PostService;
import com.tx.core.TxConstants;

/**
 * 权限显示层
 * <功能详细描述>
 * 
 * @author  PengQingyang
 * @version  [版本号, 2013-8-26]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Controller("authControllerNew")
@RequestMapping("/auth")
@CheckOperateAuth(key = "auth_manage")
public class AuthController {
    
    @Resource(name = "authManageService")
    private AuthManageService authManageService;
    
    @Resource(name = "postService")
    private PostService postService;
    
    /**
      * 跳转到查询权限视图<br/>
      *<功能详细描述>
      * @return [参数说明]
      * 
      * @return String [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/toQueryAuthView")
    public String toQueryAuthView(ModelMap modelMap) {
        List<AuthTypeItem> hasChildAuthTypeList = this.authManageService.queryCurrentOperatorAuthTypeItemOfHasChild();
        
        modelMap.put("authTypeList", hasChildAuthTypeList);
        return "/mainframe/queryAuthView";
    }
    
    /**
     * 根据当前人员权限类型与权限列表<br/> 
     *<功能详细描述>
     * @return [参数说明]
     * 
     * @return List<AuthItem> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/queryAuthType2AuthItemListMap")
    public Map<String, List<AuthItem>> queryAuthType2AuthItemListMap() {
        MultiValueMap<String, AuthItem> authType2AuthItemListMap = this.authManageService.queryCurrentPerpetualType2AuthMultiValueMap(true);
        
        return authType2AuthItemListMap;
    }
    
    /**
     * 跳转到配置职位权限页面
     *<功能详细描述>
     * @param postId
     * @param modelMap
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
    @RequestMapping("/toConfigPostAuth")
    public String toConfigPostAuth(@RequestParam("postId") String postId,
            ModelMap modelMap) {
        List<AuthTypeItem> hasChildAuthTypeList = this.authManageService.queryCurrentOperatorAuthTypeItemOfHasChild();
        
        modelMap.put("postId", postId);
        modelMap.put("post", this.postService.findPostById(postId));
        modelMap.put("authTypeList", hasChildAuthTypeList);
        return "/mainframe/configPostAuth";
    }
    
    /**
     * 根据当前人员权限类型与权限列表<br/> 
     *<功能详细描述>
     * @return [参数说明]
     * 
     * @return List<AuthItem> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/queryPostAuthType2AuthItemListMap")
    public Map<String, List<CheckAbleTreeNode>> queryPostAuthType2AuthItemListMap(
            @RequestParam("postId") String postId) {
        MultiValueMap<String, CheckAbleTreeNode> authType2AuthItemListMap = this.authManageService.queryAuthType2TreeNodeMapByRefId(AuthConstant.AUTHREFTYPE_POST,
                postId,
                false,
                false,
                true);
        
        return authType2AuthItemListMap;
    }
    
    /**
      * 保存角色权限<br/>
      *<功能详细描述>
      * @param postId
      * @param authType
      * @param authItemIds
      * @return [参数说明]
      * 
      * @return boolean [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @CheckOperateAuth(key = "config_post_auth", name = "配置职位权限")
    @RequestMapping("/savePost2AuthItemList")
    public boolean savePost2AuthItemList(
            @RequestParam("postId") String postId,
            @RequestParam("authType") String authType,
            @RequestParam(value = "authItemId[]", required = false) String[] authItemIds,
            @RequestParam() MultiValueMap<String, String> request) {
        List<String> authIdList = null;
        if (authItemIds == null) {
            authIdList = new ArrayList<String>();
        } else {
            authIdList = Arrays.asList(authItemIds);
        }
        this.authManageService.saveRefId2AuthItemIdList(authType,
                AuthConstant.AUTHREFTYPE_POST,
                postId,
                authIdList);
        return true;
    }
    
    /**
     * 跳转到配置操作员权限列表页面<br/>
     *<功能详细描述>
     * @param modelMap
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
    @RequestMapping("/toConfigOperatorAuth")
    public String toConfigOperatorAuth(
            @RequestParam("operatorId") String operatorId, ModelMap modelMap) {
        List<AuthTypeItem> hasChildAuthTypeList = this.authManageService.queryCurrentOperatorAuthTypeItemOfHasChild();
        
        modelMap.put("operatorId", operatorId);
        modelMap.put("authTypeList", hasChildAuthTypeList);
        return "/mainframe/configOperatorAuth";
    }
    
    /**
     * 根据当前人员权限类型与权限列表<br/> 
     *<功能详细描述>
     * @return [参数说明]
     * 
     * @return List<AuthItem> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/queryOperatorAuthType2AuthItemListMap")
    public Map<String, List<CheckAbleTreeNode>> queryOperatorAuthType2AuthItemListMap(
            @RequestParam("operatorId") String operatorId) {
        MultiValueMap<String, CheckAbleTreeNode> authType2AuthItemListMap = this.authManageService.queryAuthType2TreeNodeMapByRefId(AuthConstant.AUTHREFTYPE_OPERATOR,
                operatorId,
                false,
                false,
                true);
        
        return authType2AuthItemListMap;
    }
    
    /**
     * 保存角色权限<br/>
     *<功能详细描述>
     * @param postId
     * @param authType
     * @param authItemIds
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
    @ResponseBody
    @CheckOperateAuth(key = "config_operator_auth", name = "配置人员权限")
    @RequestMapping("/saveOperator2AuthItemList")
    public boolean saveOperator2AuthItemList(
            @RequestParam("operatorId") String operatorId,
            @RequestParam("authType") String authType,
            @RequestParam(value = "authItemId[]", required = false) String[] authItemIds,
            @RequestParam() MultiValueMap<String, String> request) {
        List<String> authIdList = null;
        if (authItemIds == null) {
            authIdList = new ArrayList<String>();
        } else {
            authIdList = Arrays.asList(authItemIds);
        }
        this.authManageService.saveRefId2AuthItemIdList(authType,
                AuthConstant.AUTHREFTYPE_OPERATOR,
                operatorId,
                authIdList);
        return true;
    }
    
    /**
      * 跳转到配置权限人员页面<br/> 
      *<功能详细描述>
      * @param operatorId
      * @param modelMap
      * @return [参数说明]
      * 
      * @return String [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/toConfigAuthOperator")
    public String toConfigAuthOperator(
            @RequestParam("authItemId") String authItemId, ModelMap modelMap) {
        
        Set<AuthItem> addAuthItems = this.authManageService.getParentAuthItems(authItemId);
        Set<AuthItem> deleteAuthItems = this.authManageService.getChildAuthItems(authItemId);
        
        StringBuilder addAuthItemNameSb = new StringBuilder(
                TxConstants.INITIAL_STR_LENGTH);
        for (AuthItem authItemTemp : addAuthItems) {
            addAuthItemNameSb.append(authItemTemp.getName()).append(",");
        }
        StringBuilder deleteAuthItemNameSb = new StringBuilder(
                TxConstants.INITIAL_STR_LENGTH);
        for (AuthItem authItemTemp : deleteAuthItems) {
            deleteAuthItemNameSb.append(authItemTemp.getName()).append(",");
        }
        modelMap.put("authItemName", AuthContext.getContext().getAuthItemFromContextById(authItemId).getName());
        modelMap.put("authItemId", authItemId);
        modelMap.put("addAuthItemNames",
                StringUtils.substring(addAuthItemNameSb.toString(), 0, -1));
        modelMap.put("deleteAuthItemNames",
                StringUtils.substring(deleteAuthItemNameSb.toString(), 0, -1));
        return "/mainframe/configAuthOperator";
    }
    
    /**
      * 保存权限项对应的人员id列表<br/>
      *<功能详细描述>
      * @param authItemId
      * @param authRefType
      * @param addRefIds
      * @param deleteRefIds
      * @param request
      * @return [参数说明]
      * 
      * @return boolean [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @CheckOperateAuth(key = "config_auth_operator", name = "配置权限人员")
    @RequestMapping("/saveAuthItem2OperatorIdList")
    public boolean saveAuthItem2OperatorIdList(
            @RequestParam("authItemId") String authItemId,
            @RequestParam(value = "addRefIds[]", required = false) String[] addRefIds,
            @RequestParam(value = "deleteRefIds[]", required = false) String[] deleteRefIds,
            @RequestParam() MultiValueMap<String, String> request) {
        if (addRefIds == null) {
            addRefIds = new String[0];
        }
        if (deleteRefIds == null) {
            deleteRefIds = new String[0];
        }
        List<String> addRefIdList = Arrays.asList(addRefIds);
        List<String> deleteRefIdList = Arrays.asList(deleteRefIds);
        this.authManageService.saveAuthItemId2RefIdList(AuthConstant.AUTHREFTYPE_OPERATOR,
                authItemId,
                addRefIdList,
                deleteRefIdList);
        return true;
    }
    
    /**
      * 查询有该权限的人员集合<br/>
      *<功能详细描述>
      * @param authItemId
      * @param operatorIds
      * @return [参数说明]
      * 
      * @return Set<String> [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/queryChoosedOperatorIdSetByAuthItemId")
    public Set<String> queryChoosedOperatorIdSetByAuthItemId(
            @RequestParam("authItemId") String authItemId,
            @RequestParam(value = "operatorIds[]", required = false) String[] operatorIds) {
        if (ArrayUtils.isEmpty(operatorIds)) {
            return new HashSet<String>();
        }
        Set<String> operatorIdSet = this.authManageService.queryRefIdSetByAuthItemId(AuthConstant.AUTHREFTYPE_OPERATOR,
                authItemId);
        Set<String> choosedOperatorId = new HashSet<String>();
        for (String operatorIdTemp : operatorIds) {
            if (operatorIdSet.contains(operatorIdTemp)) {
                choosedOperatorId.add(operatorIdTemp);
            }
        }
        return choosedOperatorId;
    }
    
    /**
     * 跳转到配置权限职位页面<br/>
      *<功能详细描述>
      * @param authItemId
      * @param modelMap
      * @return [参数说明]
      * 
      * @return String [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/toConfigAuthPost")
    public String toConfigAuthPost(
            @RequestParam("authItemId") String authItemId, ModelMap modelMap) {
        Set<AuthItem> addAuthItems = this.authManageService.getParentAuthItems(authItemId);
        Set<AuthItem> deleteAuthItems = this.authManageService.getChildAuthItems(authItemId);
        
        StringBuilder addAuthItemNameSb = new StringBuilder(
                TxConstants.INITIAL_STR_LENGTH);
        for (AuthItem authItemTemp : addAuthItems) {
            addAuthItemNameSb.append(authItemTemp.getName()).append(",");
        }
        StringBuilder deleteAuthItemNameSb = new StringBuilder(
                TxConstants.INITIAL_STR_LENGTH);
        for (AuthItem authItemTemp : deleteAuthItems) {
            deleteAuthItemNameSb.append(authItemTemp.getName()).append(",");
        }
        modelMap.put("authItemName", AuthContext.getContext().getAuthItemFromContextById(authItemId).getName());
        modelMap.put("authItemId", authItemId);
        modelMap.put("addAuthItemNames",
                StringUtils.substring(addAuthItemNameSb.toString(), 0, -1));
        modelMap.put("deleteAuthItemNames",
                StringUtils.substring(deleteAuthItemNameSb.toString(), 0, -1));
        return "/mainframe/configAuthPost";
    }
    
    /**
     * 保存权限项对应的职位id列表<br/>
     *<功能详细描述>
     * @param authItemId
     * @param authRefType
     * @param addRefIds
     * @param deleteRefIds
     * @param request
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
    @ResponseBody
    @RequestMapping("/saveAuthItem2PostIdList")
    @CheckOperateAuth(key = "config_auth_post", name = "配置权限职位")
    public boolean saveAuthItem2PostIdList(
            @RequestParam("authItemId") String authItemId,
            @RequestParam(value = "addRefIds[]", required = false) String[] addRefIds,
            @RequestParam(value = "deleteRefIds[]", required = false) String[] deleteRefIds,
            @RequestParam() MultiValueMap<String, String> request) {
        if (addRefIds == null) {
            addRefIds = new String[0];
        }
        if (deleteRefIds == null) {
            deleteRefIds = new String[0];
        }
        List<String> addRefIdList = Arrays.asList(addRefIds);
        List<String> deleteRefIdList = Arrays.asList(deleteRefIds);
        this.authManageService.saveAuthItemId2RefIdList(AuthConstant.AUTHREFTYPE_POST,
                authItemId,
                addRefIdList,
                deleteRefIdList);
        return true;
    }
    
    /**
     * 跳转到配置人员职位页面
     *<功能详细描述>
     * @param operatorId
     * @return [参数说明]
     * 
     * @return Set<String> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
    @ResponseBody
    @RequestMapping("/queryPostIdSetByAuthItemId")
    public Set<String> queryPostIdSetByAuthItemId(
            @RequestParam("authItemId") String authItemId) {
        Set<String> postIdSet = this.authManageService.queryRefIdSetByAuthItemId(AuthConstant.AUTHREFTYPE_POST,
                authItemId);
        return postIdSet;
    }
    
}
