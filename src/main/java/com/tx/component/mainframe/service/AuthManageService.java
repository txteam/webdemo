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
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.tx.component.auth.context.AuthContext;
import com.tx.component.auth.context.AuthSessionContext;
import com.tx.component.auth.context.AuthTypeItemContext;
import com.tx.component.auth.model.AuthItem;
import com.tx.component.auth.model.AuthItemRef;
import com.tx.component.auth.model.AuthTypeItem;
import com.tx.component.mainframe.treeview.CheckAbleTreeNode;
import com.tx.component.mainframe.treeview.CheckAbleTreeNodeAdapter;
import com.tx.component.operator.service.OperatorService;
import com.tx.component.operator.service.OrganizationService;
import com.tx.component.operator.service.PostService;
import com.tx.core.TxConstants;
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
      * 根据权限项id获取其子集权限项集合<br/> 
      *<功能详细描述>
      * @param authItemId
      * @return [参数说明]
      * 
      * @return Set<AuthItem> [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public Set<AuthItem> getChildAuthItems(String authItemId) {
        AssertUtils.notEmpty(authItemId, "authItemId is empty.");
        AuthItem authItem = authContext.getAuthItemFromContextById(authItemId);
        AssertUtils.notNull(authItem, "authItemId:{} is not exist.", authItemId);
        
        Set<String> parentAuthItemIdSet = new HashSet<String>();
        parentAuthItemIdSet.add(authItemId);
        MultiValueMap<String, AuthItem> parentId2AuthItemMap = new LinkedMultiValueMap<String, AuthItem>();
        for (AuthItem authItemTemp : authContext.getAllAuthItemMapping()
                .values()) {
            parentId2AuthItemMap.add(authItemTemp.getParentId(), authItemTemp);
        }
        
        Set<AuthItem> resSet = new HashSet<AuthItem>(
                TxConstants.INITIAL_CONLLECTION_SIZE);
        iterateChildAuthItem(parentId2AuthItemMap, parentAuthItemIdSet, resSet);
        return resSet;
    }
    
    /**
      * 迭代获取子集权限项
      *<功能详细描述>
      * @param parentId2AuthItemMap
      * @param parentAuthItemIdSet
      * @param resSet [参数说明]
      * 
      * @return void [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    private void iterateChildAuthItem(
            MultiValueMap<String, AuthItem> parentId2AuthItemMap,
            Set<String> parentAuthItemIdSet, Set<AuthItem> resSet) {
        if (CollectionUtils.isEmpty(parentAuthItemIdSet)) {
            return;
        }
        Set<String> newParentAuthItemIdSet = new HashSet<String>(
                TxConstants.INITIAL_CONLLECTION_SIZE);
        for (String parentAuthItemIdTemp : parentAuthItemIdSet) {
            List<AuthItem> childAuthItems = parentId2AuthItemMap.get(parentAuthItemIdTemp);
            if (CollectionUtils.isEmpty(childAuthItems)) {
                continue;
            }
            for (AuthItem childAuthItemTemp : childAuthItems) {
                String id = childAuthItemTemp.getId();
                if (parentAuthItemIdSet.contains(id)) {
                    //防止由于配置疏忽导致parentId = authItemId的情况，这样会引起无限迭代的
                    //尽管已经在权限容器中加入了限制，这里也加入以下，避免这里的逻辑引起系统当机
                    continue;
                }
                newParentAuthItemIdSet.add(id);
                resSet.add(childAuthItemTemp);
            }
        }
        iterateChildAuthItem(parentId2AuthItemMap,
                newParentAuthItemIdSet,
                resSet);
    }
    
    /**
      * 根据权限id获取器对应的父级权限项目集合<br/>
      *<功能详细描述>
      * @param authItemId
      * @return [参数说明]
      * 
      * @return Set<AuthItem> [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public Set<AuthItem> getParentAuthItems(String authItemId) {
        AssertUtils.notEmpty(authItemId, "authItemId is empty.");
        AuthItem authItem = authContext.getAuthItemFromContextById(authItemId);
        AssertUtils.notNull(authItem, "authItemId:{} is not exist.", authItemId);
        
        Set<AuthItem> resSet = new HashSet<AuthItem>(
                TxConstants.INITIAL_CONLLECTION_SIZE);
        iterateParentAuthItem(authItem.getParentId(), resSet);
        return resSet;
    }
    
    /**
      * 父级权限项
      *<功能详细描述>
      * @param authItemId
      * @param res [参数说明]
      * 
      * @return void [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    private void iterateParentAuthItem(String authItemId, Set<AuthItem> resSet) {
        if (StringUtils.isEmpty(authItemId)
                || authContext.getAuthItemFromContextById(authItemId) == null) {
            return;
        }
        AuthItem authItem = authContext.getAuthItemFromContextById(authItemId);
        resSet.add(authItem);
        
        //继续迭代寻找其父级权限项
        String parentId = authItem.getParentId();
        if (authItemId.equals(parentId)) {
            //防止由于配置疏忽导致parentId = authItemId的情况，这样会引起无限迭代的
            //尽管已经在权限容器中加入了限制，这里也加入以下，避免这里的逻辑引起系统当机
            return;
        }
        iterateParentAuthItem(authItem.getParentId(), resSet);
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
    @Transactional
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
    
    /**
      * 保存权限项目对应的引用id集合<br/>
      *     由于权限存在上下级关系问题
      *     所以子集权限一旦授予某人或某职位
      *     则认为该子权限的父级权限也需要授予对应的引用项目<br/>
      *<功能详细描述>
      * @param authRefType
      * @param authItemId
      * @param addRefIdList
      * @param deleteRefIdList [参数说明]
      * 
      * @return void [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    @Transactional
    public void saveAuthItemId2RefIdList(String authRefType, String authItemId,
            List<String> addRefIdList, List<String> deleteRefIdList) {
        AssertUtils.notEmpty(authRefType, "authRefType is empty");
        AssertUtils.notEmpty(authItemId, "authItemId is empty");
        
        AuthItem authItem = authContext.getAuthItemFromContextById(authItemId);
        AssertUtils.isTrue(authItem.isValid(),
                "authItemId is inValid.can not be configed.");
        AssertUtils.isTrue(authItem.isConfigAble(),
                "authItemId is inValid.can not be configed.");
        
        Set<AuthItem> parentAuthItemSet = getParentAuthItems(authItemId);
        Set<AuthItem> childAuthItems = getChildAuthItems(authItemId);
        if (!CollectionUtils.isEmpty(parentAuthItemSet)) {
            for (AuthItem addAuthItem : parentAuthItemSet) {
                authContext.addAuthItemOfAuthRefIdList(authRefType,
                        addAuthItem.getId(),
                        addRefIdList);
            }
        }
        authContext.saveAuthItemOfAuthRefIdList(authRefType,
                authItemId,
                addRefIdList,
                deleteRefIdList);
        if (!CollectionUtils.isEmpty(childAuthItems)) {
            for (AuthItem deleteAuthItem : childAuthItems) {
                authContext.deleteAuthItemOfAuthRefIdList(authRefType,
                        deleteAuthItem.getId(),
                        deleteRefIdList);
            }
        }
    }
    
    /**
      * 根据全新啊项目id查询权限项对应的操作员id集合
      *<功能详细描述>
      * @param authItemId
      * @return [参数说明]
      * 
      * @return Set<String> [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public Set<String> queryRefIdSetByAuthItemId(String authRefType,
            String authItemId) {
        AssertUtils.notEmpty(authRefType, "authRefType is empty");
        AssertUtils.notEmpty(authItemId, "authItemId is empty");
        
        List<AuthItemRef> authItemRef = authContext.queryAuthItemRefListByAuthRefTypeAndAuthItemId(authRefType,
                authItemId);
        Set<String> resIdSet = new HashSet<String>();
        for (AuthItemRef authItemRefTemp : authItemRef) {
            resIdSet.add(authItemRefTemp.getRefId());
        }
        return resIdSet;
    }
    
    /**
      * 判断权限是否存在非虚拟或虚拟非空的子权限<br/>
      *<功能详细描述>
      * @param parentId2AuthItemMultiValueMap
      * @param authItem
      * @return [参数说明]
      * 
      * @return boolean [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
      * isNotVirtualOrHasNotEmptyChildVirtualAuth
     */
    private boolean isNeedSkip(
            MultiValueMap<String, AuthItem> parentKey2AuthItemMultiValueMap,
            AuthItem authItem, boolean isIncludeInvalid,
            boolean isIncludeCanNotConfigAble, boolean isIncludeCanNotEditAble) {
        //非虚拟权限
        if (!authItem.isVirtual()) {
            if (!isIncludeInvalid && !authItem.isValid()) {
                return true;
            }
            if (!isIncludeCanNotConfigAble && !authItem.isConfigAble()) {
                return true;
            }
            if (!isIncludeCanNotEditAble && !authItem.isEditAble()) {
                return true;
            }
            return false;
        }
        
        //判断节点为虚拟权限节点的情况:
        //如果为虚拟权限，则其子权限应当存在非虚拟权限或飞空虚拟权限
        List<AuthItem> childAuthItemList = parentKey2AuthItemMultiValueMap.get(authItem.getId());
        //如果子集权限为空，则直接判断该权限为虚拟权限
        if (CollectionUtils.isEmpty(childAuthItemList)) {
            return true;
        }
        for (AuthItem authItemTemp : childAuthItemList) {
            if (!authItemTemp.isVirtual()) {
                if (!isIncludeInvalid && !authItemTemp.isValid()) {
                    continue;
                }
                if (!isIncludeCanNotConfigAble && !authItemTemp.isConfigAble()) {
                    continue;
                }
                if (!isIncludeCanNotEditAble && !authItemTemp.isEditAble()) {
                    continue;
                }
                return false;
            } else {
                List<AuthItem> childOrChildAuthItemList = parentKey2AuthItemMultiValueMap.get(authItem.getId());
                if (CollectionUtils.isEmpty(childOrChildAuthItemList)) {
                    continue;
                }
                for (AuthItem childOfChildAuthItemTemp : childOrChildAuthItemList) {
                    if (!isNeedSkip(parentKey2AuthItemMultiValueMap,
                            childOfChildAuthItemTemp,
                            isIncludeInvalid,
                            isIncludeCanNotConfigAble,
                            isIncludeCanNotEditAble)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
    
    /**
      * 根据指定引用类型的引用id拥有的权限id集合
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
            boolean isIncludeCanNotConfigAble, boolean isIncludeCanNotEditAble) {
        AssertUtils.notEmpty(refId, "refId is empty.");
        AssertUtils.notEmpty(refType, "refType is empty.");
        
        //查询当前登录人员拥有的权限集合
        Map<String, List<AuthItem>> res = queryCurrentPerpetualType2AuthMultiValueMap(true);
        //查询指定职位的权限id集合
        Set<String> refAuthIdSet = queryAuthItemIdSetByRefId(refType, refId);
        //值map
        MultiValueMap<String, CheckAbleTreeNode> resMap = new LinkedMultiValueMap<String, CheckAbleTreeNode>();
        for (Entry<String, List<AuthItem>> entryTemp : res.entrySet()) {
            //如果对应权限类型不存在子集权限，或子集权限均为抽象权限则对应权限项不进行显示<br/>
            if (CollectionUtils.isEmpty(entryTemp.getValue())) {
                continue;
            }
            boolean isAllVirtual = true;
            MultiValueMap<String, AuthItem> parentKey2AuthItemMultiValueMap = new LinkedMultiValueMap<String, AuthItem>();
            for (AuthItem authItem : entryTemp.getValue()) {
                parentKey2AuthItemMultiValueMap.add(authItem.getParentId(),
                        authItem);
                if (!authItem.isVirtual()) {
                    isAllVirtual = false;
                }
            }
            if (isAllVirtual) {
                continue;
            }
            //权限项
            for (AuthItem authTemp : entryTemp.getValue()) {
                //如果对应节点为虚拟节点，并且虚拟节点以下的子节点为空或均为虚拟节点，则不加载该节点
                if (isNeedSkip(parentKey2AuthItemMultiValueMap,
                        authTemp,
                        isIncludeInvalid,
                        isIncludeCanNotConfigAble,
                        isIncludeCanNotEditAble)) {
                    continue;
                }
                if (refAuthIdSet.contains(authTemp.getId())) {
                    //如果对应已经有对应权限
                    resMap.add(entryTemp.getKey(), new CheckAbleTreeNode(
                            authAdapter, authTemp, true));
                } else {
                    //如果对应没有有对应权限
                    resMap.add(entryTemp.getKey(), new CheckAbleTreeNode(
                            authAdapter, authTemp, false));
                }
            }
        }
        return resMap;
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
        
        public Object getTarget(AuthItem obj) {
            return obj;
        }
    };
}
