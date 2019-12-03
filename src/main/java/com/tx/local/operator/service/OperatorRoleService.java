/*
 * 描          述:  <描述>
 * 修  改   人:  
 * 修改时间:  
 * <修改描述:>
 */
package com.tx.local.operator.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.tx.core.exceptions.util.AssertUtils;
import com.tx.core.paged.model.PagedList;
import com.tx.core.querier.model.Filter;
import com.tx.core.querier.model.Querier;
import com.tx.core.querier.model.QuerierBuilder;
import com.tx.local.operator.dao.OperatorRoleDao;
import com.tx.local.operator.model.OperatorRole;
import com.tx.local.vitualcenter.facade.VirtualCenterFacade;

/**
 * OperatorRole的业务层[OperatorRoleService]
 * <功能详细描述>
 * 
 * @author  
 * @version [版本号]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Component("operatorRoleService")
public class OperatorRoleService implements InitializingBean {
    
    @SuppressWarnings("unused")
    private Logger logger = LoggerFactory.getLogger(OperatorRoleService.class);
    
    @Resource(name = "operatorRoleDao")
    private OperatorRoleDao operatorRoleDao;
    
    @Resource
    private TransactionTemplate transactionTemplate;
    
    @Resource
    private VirtualCenterFacade virtualCenterFacade;
    
    /**
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(
                    TransactionStatus status) {
                //init();
            }
        });
    }
    
    /**
     * 新增OperatorRole实例<br/>
     * 将operatorRole插入数据库中保存
     * 1、如果operatorRole 为空时抛出参数为空异常
     * 2、如果operatorRole 中部分必要参数为非法值时抛出参数不合法异常
     * 
     * @param operatorRole [参数说明]
     * @return void [返回类型说明]
     * @exception throws
     * @see [类、类#方法、类#成员]
     */
    @Transactional
    public void insert(OperatorRole operatorRole) {
        //验证参数是否合法
        AssertUtils.notNull(operatorRole, "operatorRole is null.");
        AssertUtils.notEmpty(operatorRole.getVcid(),
                "operatorRole.vcid is empty.");
        AssertUtils.notEmpty(operatorRole.getName(),
                "operatorRole.name is empty.");
        AssertUtils.notEmpty(operatorRole.getId(), "operatorRole.id is empty.");
        
        //为添加的数据需要填入默认值的字段填入默认值
        Date now = new Date();
        operatorRole.setValid(true);
        operatorRole.setCreateDate(now);
        operatorRole.setLastUpdateDate(now);
        
        //调用数据持久层对实例进行持久化操作
        this.operatorRoleDao.insert(operatorRole);
    }
    
    /**
     * 根据id删除OperatorRole实例
     * 1、如果入参数为空，则抛出异常
     * 2、执行删除后，将返回数据库中被影响的条数 > 0，则返回true
     *
     * @param id
     * @return boolean 删除的条数>0则为true [返回类型说明]
     * @exception throws 
     * @see [类、类#方法、类#成员]
     */
    @Transactional
    public boolean deleteById(String id) {
        AssertUtils.notEmpty(id, "id is empty.");
        
        OperatorRole condition = new OperatorRole();
        condition.setId(id);
        
        int resInt = this.operatorRoleDao.delete(condition);
        boolean flag = resInt > 0;
        return flag;
    }
    
    /**
     * 根据id查询OperatorRole实例
     * 1、当id为empty时抛出异常
     *
     * @param id
     * @return OperatorRole [返回类型说明]
     * @exception throws
     * @see [类、类#方法、类#成员]
     */
    public OperatorRole findById(String id) {
        AssertUtils.notEmpty(id, "id is empty.");
        
        OperatorRole condition = new OperatorRole();
        condition.setId(id);
        
        OperatorRole res = this.operatorRoleDao.find(condition);
        return res;
    }
    
    /**
     * 查询OperatorRole实例列表
     * <功能详细描述>
     * @param valid
     * @param params      
     * @return [参数说明]
     * 
     * @return List<OperatorRole> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public List<OperatorRole> queryList(Boolean valid,
            Map<String, Object> params) {
        //生成查询条件
        params = params == null ? new HashMap<String, Object>() : params;
        params.put("valid", valid);
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        List<OperatorRole> resList = this.operatorRoleDao.queryList(params);
        
        return resList;
    }
    
    /**
     * 查询OperatorRole实例列表
     * <功能详细描述>
     * @param valid
     * @param querier      
     * @return [参数说明]
     * 
     * @return List<OperatorRole> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public List<OperatorRole> queryList(Boolean valid, Querier querier) {
        //生成查询条件
        querier = querier == null ? QuerierBuilder.newInstance().querier()
                : querier;
        if (valid != null) {
            querier.getFilters().add(Filter.eq("valid", valid));
        }
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        List<OperatorRole> resList = this.operatorRoleDao.queryList(querier);
        
        return resList;
    }
    
    /**
     * 分页查询OperatorRole实例列表
     * <功能详细描述>
     * @param valid
     * @param params    
     * @param pageIndex 当前页index从1开始计算
     * @param pageSize 每页显示行数
     * 
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<OperatorRole> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public PagedList<OperatorRole> queryPagedList(Boolean valid,
            Map<String, Object> params, int pageIndex, int pageSize) {
        //T判断条件合法性
        
        //生成查询条件
        params = params == null ? new HashMap<String, Object>() : params;
        params.put("valid", valid);
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        PagedList<OperatorRole> resPagedList = this.operatorRoleDao
                .queryPagedList(params, pageIndex, pageSize);
        
        return resPagedList;
    }
    
    /**
     * 分页查询OperatorRole实例列表
     * <功能详细描述>
     * @param valid
     * @param querier    
     * @param pageIndex 当前页index从1开始计算
     * @param pageSize 每页显示行数
     * 
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<OperatorRole> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public PagedList<OperatorRole> queryPagedList(Boolean valid,
            Querier querier, int pageIndex, int pageSize) {
        //T判断条件合法性
        
        //生成查询条件
        querier = querier == null ? QuerierBuilder.newInstance().querier()
                : querier;
        if (valid != null) {
            querier.getFilters().add(Filter.eq("valid", valid));
        }
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        PagedList<OperatorRole> resPagedList = this.operatorRoleDao
                .queryPagedList(querier, pageIndex, pageSize);
        
        return resPagedList;
    }
    
    /**
     * 查询OperatorRole实例数量<br/>
     * <功能详细描述>
     * @param valid
     * @param params      
     * @return [参数说明]
     * 
     * @return List<OperatorRole> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public int count(Boolean valid, Map<String, Object> params) {
        //判断条件合法性
        
        //生成查询条件
        params = params == null ? new HashMap<String, Object>() : params;
        params.put("valid", valid);
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        int res = this.operatorRoleDao.count(params);
        
        return res;
    }
    
    /**
     * 查询OperatorRole实例数量<br/>
     * <功能详细描述>
     * @param valid
     * @param querier      
     * @return [参数说明]
     * 
     * @return List<OperatorRole> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public int count(Boolean valid, Querier querier) {
        //判断条件合法性
        
        //生成查询条件
        querier = querier == null ? QuerierBuilder.newInstance().querier()
                : querier;
        if (valid != null) {
            querier.getFilters().add(Filter.eq("valid", valid));
        }
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        int res = this.operatorRoleDao.count(querier);
        
        return res;
    }
    
    /**
     * 判断OperatorRole实例是否已经存在<br/>
     * <功能详细描述>
     * @param key2valueMap
     * @param excludeId
     * @return
     * 
     * @return int [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public boolean exists(Map<String, String> key2valueMap, String excludeId) {
        AssertUtils.notEmpty(key2valueMap, "key2valueMap is empty");
        
        //生成查询条件
        Map<String, Object> params = new HashMap<String, Object>();
        params.putAll(key2valueMap);
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        int res = this.operatorRoleDao.count(params, excludeId);
        
        return res > 0;
    }
    
    /**
     * 判断OperatorRole实例是否已经存在<br/>
     * <功能详细描述>
     * @param key2valueMap
     * @param excludeId
     * @return
     * 
     * @return int [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public boolean exists(Querier querier, String excludeId) {
        AssertUtils.notNull(querier, "querier is null.");
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        int res = this.operatorRoleDao.count(querier, excludeId);
        
        return res > 0;
    }
    
    /**
     * 根据id更新OperatorRole实例<br/>
     * <功能详细描述>
     * @param operatorRole
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Transactional
    public boolean updateById(String id, OperatorRole operatorRole) {
        //验证参数是否合法，必填字段是否填写
        AssertUtils.notNull(operatorRole, "operatorRole is null.");
        AssertUtils.notEmpty(id, "id is empty.");
        
        //生成需要更新字段的hashMap
        Map<String, Object> updateRowMap = new HashMap<String, Object>();
        //需要更新的字段
        //updateRowMap.put("vcid", operatorRole.getVcid());
        //updateRowMap.put("valid", operatorRole.isValid());
        //updateRowMap.put("modifyAble", operatorRole.isModifyAble());
        updateRowMap.put("parentId", operatorRole.getParentId());
        updateRowMap.put("catalogId", operatorRole.getCatalogId());
        updateRowMap.put("name", operatorRole.getName());
        updateRowMap.put("remark", operatorRole.getRemark());
        updateRowMap.put("lastUpdateDate", new Date());
        
        boolean flag = this.operatorRoleDao.update(id, updateRowMap);
        //如果需要大于1时，抛出异常并回滚，需要在这里修改
        return flag;
    }
    
    /**
     * 根据id更新OperatorRole实例<br/>
     * <功能详细描述>
     * @param operatorRole
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Transactional
    public boolean updateById(OperatorRole operatorRole) {
        //验证参数是否合法，必填字段是否填写
        AssertUtils.notNull(operatorRole, "operatorRole is null.");
        AssertUtils.notEmpty(operatorRole.getId(), "operatorRole.id is empty.");
        
        boolean flag = updateById(operatorRole.getId(), operatorRole);
        //如果需要大于1时，抛出异常并回滚，需要在这里修改
        return flag;
    }
    
    /**
     * 根据id禁用OperatorRole<br/>
     * <功能详细描述>
     * @param id
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Transactional
    public boolean disableById(String id) {
        AssertUtils.notEmpty(id, "id is empty.");
        
        //生成条件
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", id);
        params.put("valid", false);
        params.put("lastUpdateDate", new Date());
        
        boolean flag = this.operatorRoleDao.update(params) > 0;
        
        return flag;
    }
    
    /**
     * 根据id启用OperatorRole<br/>
     * <功能详细描述>
     * @param id
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Transactional
    public boolean enableById(String id) {
        AssertUtils.notEmpty(id, "id is empty.");
        
        //生成查询条件
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", id);
        params.put("valid", true);
        params.put("lastUpdateDate", new Date());
        
        boolean flag = this.operatorRoleDao.update(params) > 0;
        
        return flag;
    }
    
    /**
     * 根据parentId查询角色子级实例列表<br/>
     * <功能详细描述>
     * @param parentId
     * @param valid
     * @param params
     * @return [参数说明]
     * 
     * @return List<T> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public List<OperatorRole> queryChildrenByParentId(String parentId,
            Boolean valid, Map<String, Object> params) {
        //判断条件合法性
        AssertUtils.notEmpty(parentId, "parentId is empty.");
        
        //生成查询条件
        params = params == null ? new HashMap<String, Object>() : params;
        params.put("parentId", parentId);
        params.put("valid", valid);
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        List<OperatorRole> resList = this.operatorRoleDao.queryList(params);
        
        return resList;
    }
    
    /**
     * 根据parentId查询角色子级实例列表<br/>
     * <功能详细描述>
     * @param parentId
     * @param valid
     * @param querier
     * @return [参数说明]
     * 
     * @return List<T> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public List<OperatorRole> queryChildrenByParentId(String parentId,
            Boolean valid, Querier querier) {
        //判断条件合法性
        AssertUtils.notEmpty(parentId, "parentId is empty.");
        
        //生成查询条件
        querier = querier == null ? QuerierBuilder.newInstance().querier()
                : querier;
        if (valid != null) {
            querier.getFilters().add(Filter.eq("valid", valid));
        }
        querier.getFilters().add(Filter.eq("parentId", parentId));
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        List<OperatorRole> resList = this.operatorRoleDao.queryList(querier);
        
        return resList;
    }
    
    /**
     * 根据parentId查询角色子、孙级实例列表<br/>
     * <功能详细描述>
     * @param parentId
     * @param valid
     * @param params
     * @return [参数说明]
     * 
     * @return PagedList<T> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public List<OperatorRole> queryDescendantsByParentId(String parentId,
            Boolean valid, Map<String, Object> params) {
        //判断条件合法性
        AssertUtils.notEmpty(parentId, "parentId is empty.");
        
        //生成查询条件
        params = params == null ? new HashMap<String, Object>() : params;
        Set<String> ids = new HashSet<>();
        Set<String> parentIds = new HashSet<>();
        parentIds.add(parentId);
        
        List<OperatorRole> resList = doNestedQueryChildren(valid,
                ids,
                parentIds,
                params);
        return resList;
    }
    
    /**
     * 查询嵌套列表<br/>
     * <功能详细描述>
     * @param ids
     * @param parentIds
     * @param params
     * @return [参数说明]
     * 
     * @return List<Post> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    private List<OperatorRole> doNestedQueryChildren(Boolean valid,
            Set<String> ids, Set<String> parentIds,
            Map<String, Object> params) {
        if (CollectionUtils.isEmpty(parentIds)) {
            return new ArrayList<OperatorRole>();
        }
        
        //ids避免数据出错时导致无限循环
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.putAll(params);
        queryParams.put("parentIds", parentIds);
        List<OperatorRole> resList = queryList(valid, queryParams);
        
        Set<String> newParentIds = new HashSet<>();
        for (OperatorRole bdTemp : resList) {
            if (!ids.contains(bdTemp.getId())) {
                newParentIds.add(bdTemp.getId());
            }
            ids.add(bdTemp.getId());
        }
        //嵌套查询下一层级
        resList.addAll(doNestedQueryChildren(valid, ids, newParentIds, params));
        return resList;
    }
    
    /**
     * 根据parentId查询角色子、孙级实例列表<br/>
     * <功能详细描述>
     * @param parentId
     * @param valid
     * @param querier
     * @return [参数说明]
     * 
     * @return PagedList<T> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public List<OperatorRole> queryDescendantsByParentId(String parentId,
            Boolean valid, Querier querier) {
        //判断条件合法性
        AssertUtils.notEmpty(parentId, "parentId is empty.");
        
        //生成查询条件
        querier = querier == null ? QuerierBuilder.newInstance().querier()
                : querier;
        Set<String> ids = new HashSet<>();
        Set<String> parentIds = new HashSet<>();
        parentIds.add(parentId);
        
        List<OperatorRole> resList = doNestedQueryChildren(valid,
                ids,
                parentIds,
                querier);
        return resList;
    }
    
    /**
     * 嵌套查询列表<br/>
     * <功能详细描述>
     * @param ids
     * @param parentIds
     * @param querier
     * @return [参数说明]
     * 
     * @return List<Post> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    private List<OperatorRole> doNestedQueryChildren(Boolean valid,
            Set<String> ids, Set<String> parentIds, Querier querier) {
        if (CollectionUtils.isEmpty(parentIds)) {
            return new ArrayList<OperatorRole>();
        }
        
        //ids避免数据出错时导致无限循环
        Querier querierClone = (Querier) querier.clone();
        querierClone.getFilters().add(Filter.in("parentId", parentIds));
        List<OperatorRole> resList = queryList(valid, querierClone);
        
        Set<String> newParentIds = new HashSet<>();
        for (OperatorRole bdTemp : resList) {
            if (!ids.contains(bdTemp.getId())) {
                newParentIds.add(bdTemp.getId());
            }
            ids.add(bdTemp.getId());
        }
        //嵌套查询下一层级
        resList.addAll(
                doNestedQueryChildren(valid, ids, newParentIds, querier));
        return resList;
    }
}
