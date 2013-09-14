/*
 * 描          述:  <描述>
 * 修  改   人:  PengQingyang
 * 修改时间:  2013-8-26
 * <修改描述:>
 */
package com.tx.component.mainframe.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tx.component.auth.AuthConstant;
import com.tx.component.auth.model.AuthItem;
import com.tx.component.auth.model.AuthTypeItem;
import com.tx.component.mainframe.service.AuthManageService;
import com.tx.component.mainframe.treeview.CheckAbleTreeNode;
import com.tx.component.operator.service.PostService;

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
public class AuthController {
    
    @Resource(name = "authManageService")
    private AuthManageService authManageService;
    
    @Resource(name = "newPostService")
    private PostService postService;
    
    //    @Resource(name = "organizationService")
    //    private OrganizationService organizationService;
    
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
                postId);
        
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
    @RequestMapping("/savePost2AuthItemList")
    public boolean savePost2AuthItemList(@RequestParam("postId") String postId,
            @RequestParam("authType") String authType,
            @RequestParam(value="authItemId[]",required=false) String[] authItemIds,
            @RequestParam()MultiValueMap<String, String> request) {
        List<String> authIdList = null;
        if(authItemIds == null){
            authIdList = new ArrayList<String>();
        }else{
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
    public String toConfigOperatorAuth(String operatorId, ModelMap modelMap) {
        List<AuthTypeItem> hasChildAuthTypeList = this.authManageService.queryCurrentOperatorAuthTypeItemOfHasChild();
        
        modelMap.put("operatorId", operatorId);
        modelMap.put("authTypeList", hasChildAuthTypeList);
        return "/mainframe/configOperatorAuth";
    }
    
    /**
      * 跳转到配置组织权限页面<br/>
      *<功能详细描述>
      * @param organizationId
      * @param modelMap
      * @return [参数说明]
      * 
      * @return String [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/toConfigOrganizationAuth")
    public String toConfigOrganizationAuth(String organizationId,
            ModelMap modelMap) {
        List<AuthTypeItem> hasChildAuthTypeList = this.authManageService.queryCurrentOperatorAuthTypeItemOfHasChild();
        
        modelMap.put("organizationId", organizationId);
        modelMap.put("authTypeList", hasChildAuthTypeList);
        return "/mainframe/configOrganizationAuth";
    }
}
