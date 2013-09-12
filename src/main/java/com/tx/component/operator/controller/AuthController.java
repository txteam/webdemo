/*
 * 描          述:  <描述>
 * 修  改   人:  PengQingyang
 * 修改时间:  2013-8-26
 * <修改描述:>
 */
package com.tx.component.operator.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tx.component.auth.model.AuthItem;
import com.tx.component.auth.model.AuthTypeItem;
import com.tx.component.operator.service.AuthManageService;

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
    public String toConfigPostAuth(String postId, ModelMap modelMap) {
        List<AuthTypeItem> hasChildAuthTypeList = this.authManageService.queryCurrentOperatorAuthTypeItemOfHasChild();
        
        modelMap.put("postId", postId);
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
    @RequestMapping("/queryAuthType2AuthItemListMap")
    public Map<String, List<AuthItem>> queryAuthType2AuthItemListMap() {
        MultiValueMap<String, AuthItem> authType2AuthItemListMap = this.authManageService.queryCurrentPerpetualType2AuthMultiValueMap(true);
        
        /*
        Map<String, List<AuthItem>> newMap = new HashMap<String, List<AuthItem>>();
        if (authType2AuthItemListMap != null) {
            for (Entry<String, List<AuthItem>> entryTemp : authType2AuthItemListMap.entrySet()) {
                if (CollectionUtils.isEmpty(entryTemp.getValue())) {
                    continue;
                }
                newMap.put(entryTemp.getKey(),TreeUtils.changToTree(entryTemp.getValue()));
            }
        }
        */
        return authType2AuthItemListMap;
    }
    
}
