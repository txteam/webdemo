/*
 * 描          述:  <描述>
 * 修  改   人:  brady
 * 修改时间:  2013-8-30
 * <修改描述:>
 */
package com.tx.component.mainframe.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.ListUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.tx.component.auth.context.AuthContext;
import com.tx.component.auth.context.AuthSessionContext;
import com.tx.component.auth.context.AuthTypeItemContext;
import com.tx.component.auth.model.AuthItem;
import com.tx.component.auth.model.AuthItemImpl;
import com.tx.component.auth.model.AuthItemRef;
import com.tx.component.auth.model.AuthTypeItem;
import com.tx.component.mainframe.MainframeConstants;
import com.tx.component.operator.service.OperatorService;
import com.tx.component.operator.service.OrganizationService;
import com.tx.component.operator.service.PostService;
import com.tx.core.exceptions.util.AssertUtils;

/**
 * 权限管理业务层逻辑<br/>
 * <功能详细描述>
 * 
 * @author  brady
 * @version  [版本号, 2013-8-30]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Component("authManageService")
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
     * 查询权限类型项<br/>
     *     如果viewAble为true则仅返回可见的权限类型项<br/>
      *<功能简述>
      *<功能详细描述>
      * @param viewAble
      * @return [参数说明]
      * 
      * @return List<AuthTypeItem> [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    @SuppressWarnings("unchecked")
    public List<AuthTypeItem> queryAuthTypeItem(boolean viewAble) {
        List<AuthTypeItem> authTypeItemList = AuthTypeItemContext.getContext()
                .getAllAuthTypeItemList();
        List<AuthTypeItem> resList = new ArrayList<AuthTypeItem>();
        
        for (AuthTypeItem typeTemp : authTypeItemList) {
            if (viewAble && !typeTemp.isViewAble()) {
                continue;
            }
            resList.add(typeTemp);
        }
        
        return ListUtils.unmodifiableList(resList);
    }
    
    /**
     * 查询当前人员可对外授权的权限项列表（不包括临时权限）<br/>
     *<功能详细描述>
     * @return [参数说明]
     * 
     * @return List<AuthItemImpl> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
    @SuppressWarnings("unchecked")
    public List<AuthItemImpl> queryCurrentPerpetualAuthList() {
        List<AuthItem> erpetualAuthItemList = AuthSessionContext.getPerpetualAuthItemListDependAuthRefOfSession();
        
        return ListUtils.unmodifiableList(erpetualAuthItemList);
    }
    
    /**
      * 参训当前登录人员所有可授权权限列表的权限类型和权限列表的映射
      *<功能详细描述>
      * @param viewAble
      * @return [参数说明]
      * 
      * @return Map<String,AuthItem> [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public MultiValueMap<String, AuthItem> queryCurrentPerpetualType2AuthMultiValueMap(
            boolean viewAble) {
        List<AuthItem> erpetualAuthItemList = AuthSessionContext.getPerpetualAuthItemListDependAuthRefOfSession();
        Map<String, AuthTypeItem> authTypeMap = AuthTypeItemContext.getContext()
                .getAllAuthTypeItemMap();
        
        MultiValueMap<String, AuthItem> res = new LinkedMultiValueMap<String, AuthItem>();
        for (AuthItem authItem : erpetualAuthItemList) {
            AuthTypeItem authTypeAuth = authTypeMap.get(authItem.getAuthType());
            boolean authTypeIsViewAble = authTypeAuth != null
                    && authTypeAuth.isViewAble() ? true : false;
            if (viewAble && !authTypeIsViewAble) {
                continue;
            }
            res.add(authItem.getAuthType(), authItem);
        }
        return res;
    }
    
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
    public void savePostAuth(String authType, String postId,
            List<String> authIdList) {
        AssertUtils.notEmpty(postId, "postId is empty");
        
        if (StringUtils.isEmpty(authType)) {
            authContext.saveAuthRefOfAuthItemIdList(MainframeConstants.AUTHREFTYPE_POST,
                    postId,
                    authIdList);
        } else {
            authContext.saveAuthRefOfAuthItemIdList(authType,
                    MainframeConstants.AUTHREFTYPE_POST,
                    postId,
                    authIdList);
        }
    }
    
    /**
      * 查询职位权限
      *<功能详细描述>
      * @param postId
      * @return [参数说明]
      * 
      * @return List<AuthItemRef> [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public List<AuthItemRef> queryPostAuth(String postId) {
        AssertUtils.notEmpty(postId, "postId is empty");
        
        List<AuthItemRef> AuthItemRefList = authContext.queryAuthItemRefListByAuthRefTypeAndRefId(MainframeConstants.AUTHREFTYPE_POST,
                postId);
        
        return AuthItemRefList;
    }
    
    /**
      * 查询组织权限
      *<功能详细描述>
      * @param organizationId
      * @return [参数说明]
      * 
      * @return List<AuthItemRef> [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public List<AuthItemRef> queryOrganizationAuth(String organizationId) {
        AssertUtils.notEmpty(organizationId, "postId is empty");
        
        List<AuthItemRef> AuthItemRefList = authContext.queryAuthItemRefListByAuthRefTypeAndRefId(MainframeConstants.AUTHREFTYPE_ORGANIZATION,
                organizationId);
        
        return AuthItemRefList;
    }
    
    /**
      * 查询操作权限
      *<功能详细描述>
      * @param operatorId
      * @return [参数说明]
      * 
      * @return List<AuthItemRef> [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public List<AuthItemRef> queryOperatorAuth(String operatorId) {
        AssertUtils.notEmpty(operatorId, "postId is empty");
        
        List<AuthItemRef> AuthItemRefList = authContext.queryAuthItemRefListByAuthRefTypeAndRefId(MainframeConstants.AUTHREFTYPE_ORGANIZATION,
                operatorId);
        
        return AuthItemRefList;
    }
    
    /**
      * 保存组织权限<br/>
      *<功能详细描述>
      * @param authType
      * @param organizationId
      * @param authIdList [参数说明]
      * 
      * @return void [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public void saveOrganizationAuth(String authType, String organizationId,
            List<String> authIdList) {
        AssertUtils.notEmpty(organizationId, "organizationId is empty");
        
        if (StringUtils.isEmpty(authType)) {
            authContext.saveAuthRefOfAuthItemIdList(MainframeConstants.AUTHREFTYPE_ORGANIZATION,
                    organizationId,
                    authIdList);
        } else {
            authContext.saveAuthRefOfAuthItemIdList(authType,
                    MainframeConstants.AUTHREFTYPE_ORGANIZATION,
                    organizationId,
                    authIdList);
        }
    }
    
    /**
      * 保存人员权限<br/>
      *<功能详细描述>
      * @param authType
      * @param operatorId
      * @param authIdList [参数说明]
      * 
      * @return void [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public void saveOperatorAuth(String authType, String operatorId,
            List<String> authIdList) {
        AssertUtils.notEmpty(operatorId, "operatorId is empty");
        
        if (StringUtils.isEmpty(authType)) {
            authContext.saveAuthRefOfAuthItemIdList(MainframeConstants.AUTHREFTYPE_OPERATOR,
                    operatorId,
                    authIdList);
        } else {
            authContext.saveAuthRefOfAuthItemIdList(authType,
                    MainframeConstants.AUTHREFTYPE_OPERATOR,
                    operatorId,
                    authIdList);
        }
    }
    
}
