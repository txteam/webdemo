/*
 * 描          述:  <描述>
 * 修  改   人:  brady
 * 修改时间:  2013-8-30
 * <修改描述:>
 */
package com.tx.component.mainframe.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
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
import com.tx.component.mainframe.treeview.CheckAbleTreeNode;
import com.tx.component.mainframe.treeview.CheckAbleTreeNodeAdapter;
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
     * 生成引用的权限树
     *     根据引用项id以及引用类型查询
     *<功能详细描述>
     * @param postId
     * @return [参数说明]
     * 
     * @return MultiValueMap<String,CheckAbleTreeNode> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
    public MultiValueMap<String, CheckAbleTreeNode> queryAuthType2TreeNodeMapByRefId(
            String refType, String refId, boolean isIncludeInvalid,
            boolean isIncludeCanNotConfigAble,boolean isIncludeCanNotEditAble) {
        AssertUtils.notEmpty(refId, "refId is empty.");
        AssertUtils.notEmpty(refType, "refType is empty.");
        
        //查询当前登录人员拥有的权限集合
        Map<String, List<AuthItem>> res = queryCurrentPerpetualType2AuthMultiValueMap(true);
        //查询指定职位的权限id集合
        Set<String> refAuthIdSet = queryAuthItemIdSetByRefId(refType, refId);
        
        //值map
        MultiValueMap<String, CheckAbleTreeNode> resMap = new LinkedMultiValueMap<String, CheckAbleTreeNode>();
        for (Entry<String, List<AuthItem>> entryTemp : res.entrySet()) {
            if (CollectionUtils.isEmpty(entryTemp.getValue())) {
                continue;
            }
            for (AuthItem authTemp : entryTemp.getValue()) {
                if(!isIncludeInvalid &&  !authTemp.isValid()){
                    continue;
                }
                if(!isIncludeCanNotConfigAble &&  !authTemp.isConfigAble()){
                    continue;
                }
                if(!isIncludeCanNotEditAble && authTemp.isEditAble()){
                    continue;
                }
                
                if (refAuthIdSet.contains(authTemp.getId())) {
                    //如果对应职位已经有对应权限
                    resMap.add(entryTemp.getKey(), new CheckAbleTreeNode(
                            authAdapter, authTemp, true));
                } else {
                    //如果对应职位没有有对应权限
                    resMap.add(entryTemp.getKey(), new CheckAbleTreeNode(
                            authAdapter, authTemp, false));
                }
            }
        }
        
        return resMap;
    }
    
    /**
      * 差尊指定引用类型的引用id拥有的权限id集合
      *<功能详细描述>
      * @param refType
      * @param refId
      * @return [参数说明]
      * 
      * @return Set<String> [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public Set<String> queryAuthItemIdSetByRefId(String refType, String refId) {
        AssertUtils.notEmpty(refId, "refId is empty.");
        AssertUtils.notEmpty(refType, "refType is empty.");
        
        Set<String> resSet = new HashSet<String>();
        List<AuthItemRef> authItemRefList = AuthContext.getContext()
                .queryAuthItemRefListByAuthRefTypeAndRefId(refType, refId);
        if (authItemRefList != null) {
            for (AuthItemRef refTemp : authItemRefList) {
                resSet.add(refTemp.getAuthItem().getId());
            }
        }
        
        return resSet;
    }
    
//    /**
//      * 查询指定引用类型的引用id集合
//      *<功能详细描述>
//      * @param refType
//      * @param authItemId
//      * @return [参数说明]
//      * 
//      * @return Set<String> [返回类型说明]
//      * @exception throws [异常类型] [异常说明]
//      * @see [类、类#方法、类#成员]
//     */
//    public Set<String> queryRefIdSetByAuthItemId(String refType,
//            String authItemId) {
//        AssertUtils.notEmpty(authItemId, "authItemId is empty.");
//        AssertUtils.notEmpty(refType, "refType is empty.");
//        
//        Set<String> resSet = new HashSet<String>();
//        List<AuthItemRef> authItemRefList = AuthContext.getContext()
//                .queryAuthItemRefListByAuthRefTypeAndAuthItemId(refType,
//                        authItemId);
//        if (authItemRefList != null) {
//            for (AuthItemRef refTemp : authItemRefList) {
//                resSet.add(refTemp.getAuthItem().getId());
//            }
//        }
//        
//        return resSet;
//    }
    
//    /**
//     * 查询权限类型项<br/>
//     *     如果viewAble为true则仅返回可见的权限类型项<br/>
//      *<功能简述>
//      *<功能详细描述>
//      * @param viewAble
//      * @return [参数说明]
//      * 
//      * @return List<AuthTypeItem> [返回类型说明]
//      * @exception throws [异常类型] [异常说明]
//      * @see [类、类#方法、类#成员]
//     */
//    @SuppressWarnings("unchecked")
//    public List<AuthTypeItem> queryAuthTypeItem(boolean viewAble) {
//        List<AuthTypeItem> authTypeItemList = AuthTypeItemContext.getContext()
//                .getAllAuthTypeItemList();
//        List<AuthTypeItem> resList = new ArrayList<AuthTypeItem>();
//        
//        for (AuthTypeItem typeTemp : authTypeItemList) {
//            if (viewAble && !typeTemp.isViewAble()) {
//                continue;
//            }
//            resList.add(typeTemp);
//        }
//        
//        return ListUtils.unmodifiableList(resList);
//    }
    
//    /**
//     * 查询当前人员可对外授权的权限项列表（不包括临时权限）<br/>
//     *<功能详细描述>
//     * @return [参数说明]
//     * 
//     * @return List<AuthItemImpl> [返回类型说明]
//     * @exception throws [异常类型] [异常说明]
//     * @see [类、类#方法、类#成员]
//    */
//    @SuppressWarnings("unchecked")
//    public List<AuthItemImpl> queryCurrentPerpetualAuthList() {
//        List<AuthItem> erpetualAuthItemList = AuthSessionContext.getPerpetualAuthItemListDependAuthRefOfSession();
//        
//        return ListUtils.unmodifiableList(erpetualAuthItemList);
//    }
    
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
    public void saveRefId2AuthItemIdList(String authType, String refType,
            String refId, List<String> authIdList) {
        AssertUtils.notEmpty(refId, "refId is empty");
        AssertUtils.notEmpty(refType, "refType is empty");
        
        if (StringUtils.isEmpty(authType)) {
            authContext.saveAuthRefOfAuthItemIdList(refType, refId, authIdList);
        } else {
            authContext.saveAuthRefOfAuthItemIdList(authType,
                    refType,
                    refId,
                    authIdList);
        }
    }
    
//    /**
//      * 查询职位权限
//      *<功能详细描述>
//      * @param postId
//      * @return [参数说明]
//      * 
//      * @return List<AuthItemRef> [返回类型说明]
//      * @exception throws [异常类型] [异常说明]
//      * @see [类、类#方法、类#成员]
//     */
//    public List<AuthItemRef> queryPostAuth(String postId) {
//        AssertUtils.notEmpty(postId, "postId is empty");
//        
//        List<AuthItemRef> AuthItemRefList = authContext.queryAuthItemRefListByAuthRefTypeAndRefId(MainframeConstants.AUTHREFTYPE_POST,
//                postId);
//        
//        return AuthItemRefList;
//    }
    
//    /**
//      * 查询组织权限
//      *<功能详细描述>
//      * @param organizationId
//      * @return [参数说明]
//      * 
//      * @return List<AuthItemRef> [返回类型说明]
//      * @exception throws [异常类型] [异常说明]
//      * @see [类、类#方法、类#成员]
//     */
//    public List<AuthItemRef> queryOrganizationAuth(String organizationId) {
//        AssertUtils.notEmpty(organizationId, "postId is empty");
//        
//        List<AuthItemRef> AuthItemRefList = authContext.queryAuthItemRefListByAuthRefTypeAndRefId(MainframeConstants.AUTHREFTYPE_ORGANIZATION,
//                organizationId);
//        
//        return AuthItemRefList;
//    }
    
//    /**
//      * 查询操作权限
//      *<功能详细描述>
//      * @param operatorId
//      * @return [参数说明]
//      * 
//      * @return List<AuthItemRef> [返回类型说明]
//      * @exception throws [异常类型] [异常说明]
//      * @see [类、类#方法、类#成员]
//     */
//    public List<AuthItemRef> queryOperatorAuth(String operatorId) {
//        AssertUtils.notEmpty(operatorId, "postId is empty");
//        
//        List<AuthItemRef> AuthItemRefList = authContext.queryAuthItemRefListByAuthRefTypeAndRefId(MainframeConstants.AUTHREFTYPE_ORGANIZATION,
//                operatorId);
//        
//        return AuthItemRefList;
//    }
    
    /**
      * 查询当前人员拥有权限的权限类型集合（不包含）
      *<功能详细描述>
      * @return [参数说明]
      * 
      * @return List<AuthTypeItem> [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public List<AuthTypeItem> queryCurrentOperatorAuthTypeItemOfHasChild() {
        MultiValueMap<String, AuthItem> authType2AuthItemListMap = queryCurrentPerpetualType2AuthMultiValueMap(true);
        
        List<String> hasAuthTypeIdList = new ArrayList<String>();
        if (authType2AuthItemListMap != null) {
            for (Entry<String, List<AuthItem>> entryTemp : authType2AuthItemListMap.entrySet()) {
                if (CollectionUtils.isEmpty(entryTemp.getValue())
                        && hasAuthTypeIdList.contains(entryTemp.getKey())) {
                    continue;
                }
                hasAuthTypeIdList.add(entryTemp.getKey());
            }
        }
        
        Map<String, AuthTypeItem> authTypeMap = AuthTypeItemContext.getContext()
                .getAllAuthTypeItemMap();
        List<AuthTypeItem> hasChildAuthTypeList = new ArrayList<AuthTypeItem>();
        for (String authTypeKeyTemp : hasAuthTypeIdList) {
            hasChildAuthTypeList.add(authTypeMap.get(authTypeKeyTemp));
        }
        
        return hasChildAuthTypeList;
    }
    
    /** 职位转换为树节点的适配器 */
    private static CheckAbleTreeNodeAdapter<AuthItem> authAdapter = new CheckAbleTreeNodeAdapter<AuthItem>() {
        
        public String getId(AuthItem obj) {
            return obj.getId();
        }
        
        public int getType(AuthItem obj) {
            return 0;
        }
        
        public String getParentId(AuthItem obj) {
            return obj.getParentId();
        }
        
        public String getName(AuthItem obj) {
            return obj.getName();
        }
        
        public boolean isChecked(AuthItem obj) {
            return false;
        }
    };
}
