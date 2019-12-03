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
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.tx.local.operator.dao.OperatorRoleCatalogDao;
import com.tx.local.operator.model.OperatorRoleCatalog;
import com.tx.core.exceptions.util.AssertUtils;
import com.tx.core.paged.model.PagedList;
import com.tx.core.querier.model.Filter;
import com.tx.core.querier.model.Querier;
import com.tx.core.querier.model.QuerierBuilder;

/**
 * 角色分类的业务层[OperatorRoleCatalogService]
 * <功能详细描述>
 * 
 * @author  
 * @version [版本号]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Component("operatorRoleCatalogService")
public class OperatorRoleCatalogService {
    
    @SuppressWarnings("unused")
    private Logger logger = LoggerFactory
            .getLogger(OperatorRoleCatalogService.class);
    
    @Resource(name = "operatorRoleCatalogDao")
    private OperatorRoleCatalogDao operatorRoleCatalogDao;
    
    /**
     * 新增角色分类实例<br/>
     * 将operatorRoleCatalog插入数据库中保存
     * 1、如果operatorRoleCatalog 为空时抛出参数为空异常
     * 2、如果operatorRoleCatalog 中部分必要参数为非法值时抛出参数不合法异常
     * 
     * @param operatorRoleCatalog [参数说明]
     * @return void [返回类型说明]
     * @exception throws
     * @see [类、类#方法、类#成员]
     */
    @Transactional
    public void insert(OperatorRoleCatalog operatorRoleCatalog) {
        //验证参数是否合法
        AssertUtils.notNull(operatorRoleCatalog,
                "operatorRoleCatalog is null.");
        
        //为添加的数据需要填入默认值的字段填入默认值
        operatorRoleCatalog.setLastUpdateDate(new Date());
        operatorRoleCatalog.setValid(true);
        operatorRoleCatalog.setCreateDate(new Date());
        
        //调用数据持久层对实例进行持久化操作
        this.operatorRoleCatalogDao.insert(operatorRoleCatalog);
    }
    
    /**
     * 根据id删除角色分类实例
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
        
        OperatorRoleCatalog condition = new OperatorRoleCatalog();
        condition.setId(id);
        
        int resInt = this.operatorRoleCatalogDao.delete(condition);
        boolean flag = resInt > 0;
        return flag;
    }
    
    /**
     * 根据id查询角色分类实例
     * 1、当id为empty时抛出异常
     *
     * @param id
     * @return OperatorRoleCatalog [返回类型说明]
     * @exception throws
     * @see [类、类#方法、类#成员]
     */
    public OperatorRoleCatalog findById(String id) {
        AssertUtils.notEmpty(id, "id is empty.");
        
        OperatorRoleCatalog condition = new OperatorRoleCatalog();
        condition.setId(id);
        
        OperatorRoleCatalog res = this.operatorRoleCatalogDao.find(condition);
        return res;
    }
    
    /**
     * 查询角色分类实例列表
     * <功能详细描述>
     * @param valid
     * @param params      
     * @return [参数说明]
     * 
     * @return List<OperatorRoleCatalog> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public List<OperatorRoleCatalog> queryList(Boolean valid,
            Map<String, Object> params) {
        //判断条件合法性
        
        //生成查询条件
        params = params == null ? new HashMap<String, Object>() : params;
        params.put("valid", valid);
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        List<OperatorRoleCatalog> resList = this.operatorRoleCatalogDao
                .queryList(params);
        
        return resList;
    }
    
    /**
     * 查询角色分类实例列表
     * <功能详细描述>
     * @param valid
     * @param querier      
     * @return [参数说明]
     * 
     * @return List<OperatorRoleCatalog> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public List<OperatorRoleCatalog> queryList(Boolean valid, Querier querier) {
        //判断条件合法性
        
        //生成查询条件
        querier = querier == null ? QuerierBuilder.newInstance().querier()
                : querier;
        if (valid != null) {
            querier.getFilters().add(Filter.eq("valid", valid));
        }
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        List<OperatorRoleCatalog> resList = this.operatorRoleCatalogDao
                .queryList(querier);
        
        return resList;
    }
    
    /**
     * 分页查询角色分类实例列表
     * <功能详细描述>
     * @param valid
     * @param params    
     * @param pageIndex 当前页index从1开始计算
     * @param pageSize 每页显示行数
     * 
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<OperatorRoleCatalog> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public PagedList<OperatorRoleCatalog> queryPagedList(Boolean valid,
            Map<String, Object> params, int pageIndex, int pageSize) {
        //T判断条件合法性
        
        //生成查询条件
        params = params == null ? new HashMap<String, Object>() : params;
        params.put("valid", valid);
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        PagedList<OperatorRoleCatalog> resPagedList = this.operatorRoleCatalogDao
                .queryPagedList(params, pageIndex, pageSize);
        
        return resPagedList;
    }
    
    /**
     * 分页查询角色分类实例列表
     * <功能详细描述>
     * @param valid
     * @param querier    
     * @param pageIndex 当前页index从1开始计算
     * @param pageSize 每页显示行数
     * 
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<OperatorRoleCatalog> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public PagedList<OperatorRoleCatalog> queryPagedList(Boolean valid,
            Querier querier, int pageIndex, int pageSize) {
        //T判断条件合法性
        
        //生成查询条件
        querier = querier == null ? QuerierBuilder.newInstance().querier()
                : querier;
        if (valid != null) {
            querier.getFilters().add(Filter.eq("valid", valid));
        }
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        PagedList<OperatorRoleCatalog> resPagedList = this.operatorRoleCatalogDao
                .queryPagedList(querier, pageIndex, pageSize);
        
        return resPagedList;
    }
    
    /**
     * 查询角色分类实例数量<br/>
     * <功能详细描述>
     * @param valid
     * @param params      
     * @return [参数说明]
     * 
     * @return List<OperatorRoleCatalog> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public int count(Boolean valid, Map<String, Object> params) {
        //判断条件合法性
        
        //生成查询条件
        params = params == null ? new HashMap<String, Object>() : params;
        params.put("valid", valid);
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        int res = this.operatorRoleCatalogDao.count(params);
        
        return res;
    }
    
    /**
     * 查询角色分类实例数量<br/>
     * <功能详细描述>
     * @param valid
     * @param querier      
     * @return [参数说明]
     * 
     * @return List<OperatorRoleCatalog> [返回类型说明]
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
        int res = this.operatorRoleCatalogDao.count(querier);
        
        return res;
    }
    
    /**
     * 判断角色分类实例是否已经存在<br/>
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
        int res = this.operatorRoleCatalogDao.count(params, excludeId);
        
        return res > 0;
    }
    
    /**
     * 判断角色分类实例是否已经存在<br/>
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
        int res = this.operatorRoleCatalogDao.count(querier, excludeId);
        
        return res > 0;
    }
    
    /**
     * 根据id更新角色分类实例<br/>
     * <功能详细描述>
     * @param operatorRoleCatalog
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Transactional
    public boolean updateById(String id,
            OperatorRoleCatalog operatorRoleCatalog) {
        //验证参数是否合法，必填字段是否填写
        AssertUtils.notNull(operatorRoleCatalog,
                "operatorRoleCatalog is null.");
        AssertUtils.notEmpty(id, "id is empty.");
        
        //生成需要更新字段的hashMap
        Map<String, Object> updateRowMap = new HashMap<String, Object>();
        //需要更新的字段
        //updateRowMap.put("vcid", operatorRoleCatalog.getVcid());
        //updateRowMap.put("modifyAble", operatorRoleCatalog.isModifyAble());
        updateRowMap.put("name", operatorRoleCatalog.getName());
        updateRowMap.put("parentId", operatorRoleCatalog.getParentId());
        updateRowMap.put("remark", operatorRoleCatalog.getRemark());
        updateRowMap.put("lastUpdateDate", new Date());
        
        boolean flag = this.operatorRoleCatalogDao.update(id, updateRowMap);
        //如果需要大于1时，抛出异常并回滚，需要在这里修改
        return flag;
    }
    
    /**
     * 根据id更新角色分类实例<br/>
     * <功能详细描述>
     * @param operatorRoleCatalog
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Transactional
    public boolean updateById(OperatorRoleCatalog operatorRoleCatalog) {
        //验证参数是否合法，必填字段是否填写
        AssertUtils.notNull(operatorRoleCatalog,
                "operatorRoleCatalog is null.");
        AssertUtils.notEmpty(operatorRoleCatalog.getId(),
                "operatorRoleCatalog.id is empty.");
        
        boolean flag = updateById(operatorRoleCatalog.getId(),
                operatorRoleCatalog);
        //如果需要大于1时，抛出异常并回滚，需要在这里修改
        return flag;
    }
    
    /**
     * 根据id禁用角色分类<br/>
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
        
        boolean flag = this.operatorRoleCatalogDao.update(params) > 0;
        
        return flag;
    }
    
    /**
     * 根据id启用角色分类<br/>
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
        
        boolean flag = this.operatorRoleCatalogDao.update(params) > 0;
        
        return flag;
    }
    
    /**
     * 根据parentId查询角色分类子级实例列表<br/>
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
    public List<OperatorRoleCatalog> queryChildrenByParentId(String parentId,
            Boolean valid, Map<String, Object> params) {
        //判断条件合法性
        AssertUtils.notEmpty(parentId, "parentId is empty.");
        
        //生成查询条件
        params = params == null ? new HashMap<String, Object>() : params;
        params.put("parentId", parentId);
        params.put("valid", valid);
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        List<OperatorRoleCatalog> resList = this.operatorRoleCatalogDao
                .queryList(params);
        
        return resList;
    }
    
    /**
     * 根据parentId查询角色分类子级实例列表<br/>
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
    public List<OperatorRoleCatalog> queryChildrenByParentId(String parentId,
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
        List<OperatorRoleCatalog> resList = this.operatorRoleCatalogDao
                .queryList(querier);
        
        return resList;
    }
    
    /**
     * 根据parentId查询角色分类子、孙级实例列表<br/>
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
    public List<OperatorRoleCatalog> queryDescendantsByParentId(String parentId,
            Boolean valid, Map<String, Object> params) {
        //判断条件合法性
        AssertUtils.notEmpty(parentId, "parentId is empty.");
        
        //生成查询条件
        params = params == null ? new HashMap<String, Object>() : params;
        Set<String> ids = new HashSet<>();
        Set<String> parentIds = new HashSet<>();
        parentIds.add(parentId);
        
        List<OperatorRoleCatalog> resList = doNestedQueryChildren(valid,
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
     * @return List<OperatorRoleCatalog> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    private List<OperatorRoleCatalog> doNestedQueryChildren(Boolean valid,
            Set<String> ids, Set<String> parentIds,
            Map<String, Object> params) {
        if (CollectionUtils.isEmpty(parentIds)) {
            return new ArrayList<OperatorRoleCatalog>();
        }
        
        //ids避免数据出错时导致无限循环
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.putAll(params);
        queryParams.put("parentIds", parentIds);
        List<OperatorRoleCatalog> resList = queryList(valid, queryParams);
        
        Set<String> newParentIds = new HashSet<>();
        for (OperatorRoleCatalog bdTemp : resList) {
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
     * 根据parentId查询角色分类子、孙级实例列表<br/>
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
    public List<OperatorRoleCatalog> queryDescendantsByParentId(String parentId,
            Boolean valid, Querier querier) {
        //判断条件合法性
        AssertUtils.notEmpty(parentId, "parentId is empty.");
        
        //生成查询条件
        querier = querier == null ? QuerierBuilder.newInstance().querier()
                : querier;
        Set<String> ids = new HashSet<>();
        Set<String> parentIds = new HashSet<>();
        parentIds.add(parentId);
        
        List<OperatorRoleCatalog> resList = doNestedQueryChildren(valid,
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
     * @return List<OperatorRoleCatalog> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    private List<OperatorRoleCatalog> doNestedQueryChildren(Boolean valid,
            Set<String> ids, Set<String> parentIds, Querier querier) {
        if (CollectionUtils.isEmpty(parentIds)) {
            return new ArrayList<OperatorRoleCatalog>();
        }
        
        //ids避免数据出错时导致无限循环
        Querier querierClone = (Querier) querier.clone();
        querierClone.getFilters().add(Filter.in("parentId", parentIds));
        List<OperatorRoleCatalog> resList = queryList(valid, querierClone);
        
        Set<String> newParentIds = new HashSet<>();
        for (OperatorRoleCatalog bdTemp : resList) {
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
