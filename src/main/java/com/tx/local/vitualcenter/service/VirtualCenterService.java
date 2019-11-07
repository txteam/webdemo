/*
 * 描          述:  <描述>
 * 修  改   人:  
 * 修改时间:  
 * <修改描述:>
 */
package com.tx.local.vitualcenter.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.StringUtils;
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
import com.tx.local.vitualcenter.dao.VirtualCenterDao;
import com.tx.local.vitualcenter.model.VirtualCenter;
import com.tx.local.vitualcenter.model.VirtualCenterEnum;

/**
 * VirtualCenter的业务层[VirtualCenterService]
 * <功能详细描述>
 * 
 * @author  
 * @version [版本号]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Component("virtualCenterService")
public class VirtualCenterService implements InitializingBean {
    
    @SuppressWarnings("unused")
    private Logger logger = LoggerFactory.getLogger(VirtualCenterService.class);
    
    @Resource(name = "virtualCenterDao")
    private VirtualCenterDao virtualCenterDao;
    
    @Resource
    private TransactionTemplate transactionTemplate;
    
    /**
     * 初始化方法
     *     根据虚中心枚举，如果对应的虚中心没有被自动创建，则系统自动创建<br/>
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            
            @Override
            protected void doInTransactionWithoutResult(
                    TransactionStatus status) {
                init();
            }
        });
    }
    
    /**
     * 启动期间检查初始化数据<br/>
     * <功能详细描述> [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @SuppressWarnings("rawtypes")
    private void init() {
        //虚中心
        Map<String, VirtualCenter> virtualCenterMap = new HashMap<String, VirtualCenter>();
        @SuppressWarnings("unchecked")
        List<VirtualCenter> virtualCenterList = queryList(null, (Map) null);
        for (VirtualCenter virtualCenter : virtualCenterList) {
            if (virtualCenter.getCode() == null) {
                continue;
            }
            virtualCenterMap.put(virtualCenter.getCode().toUpperCase(),
                    virtualCenter);
        }
        
        //处理自动新增以及自动更新逻辑（不包括树形结构写入）
        Map<String, VirtualCenterEnum> virtualCenterEnumMap = EnumUtils
                .<VirtualCenterEnum> getEnumMap(VirtualCenterEnum.class);
        for (VirtualCenterEnum virtualCenterKeyTemp : virtualCenterEnumMap
                .values()) {
            if (virtualCenterMap.containsKey(
                    virtualCenterKeyTemp.getCode().toUpperCase())) {
                VirtualCenter vc = virtualCenterMap
                        .get(virtualCenterKeyTemp.getCode().toUpperCase());
                if (!vc.isValid() || vc.isModifyAble() || !vc.getName()
                        .equals(virtualCenterKeyTemp.getName())) {
                    Map<String, Object> ur = new HashMap<>();
                    ur.put("id", vc.getId());
                    ur.put("name", virtualCenterKeyTemp.getName());
                    ur.put("valid", true);
                    ur.put("modifyAble", false);
                    
                    //code无法更新
                    this.virtualCenterDao.update(ur);
                    
                    //更新以后重新查询出来进行写入
                    vc = findById(vc.getId());
                    virtualCenterMap.put(
                            virtualCenterKeyTemp.getCode().toUpperCase(), vc);
                }
                //给枚举写入id
                virtualCenterKeyTemp.setId(vc.getId());
                continue;
            } else {
                //将新的虚中心插入表中
                VirtualCenter virtualCenter = new VirtualCenter();
                virtualCenter.setName(virtualCenterKeyTemp.getName());
                virtualCenter.setCode(virtualCenterKeyTemp.getCode());
                virtualCenter.setModifyAble(false);
                this.insert(virtualCenter);
                
                //给枚举写入id
                virtualCenterMap.put(
                        virtualCenterKeyTemp.getCode().toUpperCase(),
                        virtualCenter);
                //给枚举写入id
                virtualCenterKeyTemp.setId(virtualCenter.getId());
            }
        }
        
        //更新父级虚中心id
        for (VirtualCenterEnum virtualCenterKeyTemp : virtualCenterEnumMap
                .values()) {
            VirtualCenter vc = virtualCenterMap
                    .get(virtualCenterKeyTemp.getCode().toUpperCase());
            AssertUtils.notNull(vc,
                    "vc is null.code:{}",
                    virtualCenterKeyTemp.getCode());
            if (StringUtils.equals(vc.getParentId(),
                    virtualCenterKeyTemp.getParent() == null ? null
                            : virtualCenterKeyTemp.getParent().getId())) {
                continue;
            }
            
            Map<String, Object> ur = new HashMap<>();
            ur.put("id", vc.getId());
            ur.put("parentId", virtualCenterKeyTemp.getParent().getId());
            
            this.virtualCenterDao.update(ur);
            vc.setParentId(virtualCenterKeyTemp.getParent().getId());
            
            //更新以后重新查询出来进行写入
            vc = findById(vc.getId());
            virtualCenterMap.put(virtualCenterKeyTemp.getCode().toUpperCase(),
                    vc);
        }
    }
    
    /**
     * 新增VirtualCenter实例<br/>
     * 将virtualCenter插入数据库中保存
     * 1、如果virtualCenter 为空时抛出参数为空异常
     * 2、如果virtualCenter 中部分必要参数为非法值时抛出参数不合法异常
     * 
     * @param virtualCenter [参数说明]
     * @return void [返回类型说明]
     * @exception throws
     * @see [类、类#方法、类#成员]
     */
    @Transactional
    public void insert(VirtualCenter virtualCenter) {
        //验证参数是否合法
        AssertUtils.notNull(virtualCenter, "virtualCenter is null.");
        AssertUtils.notEmpty(virtualCenter.getCode(),
                "virtualCenter.code is empty.");
        AssertUtils.notEmpty(virtualCenter.getName(),
                "virtualCenter.name is empty.");
        AssertUtils.notEmpty(virtualCenter.isModifyAble(),
                "virtualCenter.modifyAble is empty.");
        
        //FIXME:为添加的数据需要填入默认值的字段填入默认值
        virtualCenter.setValid(true);
        virtualCenter.setCreateDate(new Date());
        
        //调用数据持久层对实例进行持久化操作
        this.virtualCenterDao.insert(virtualCenter);
    }
    
    /**
     * 根据id删除VirtualCenter实例
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
        
        VirtualCenter condition = new VirtualCenter();
        condition.setId(id);
        
        int resInt = this.virtualCenterDao.delete(condition);
        boolean flag = resInt > 0;
        return flag;
    }
    
    /**
     * 根据code删除VirtualCenter实例
     * 1、当code为empty时抛出异常
     * 2、执行删除后，将返回数据库中被影响的条数 > 0，则返回true
     *
     * @param code
     * @return VirtualCenter [返回类型说明]
     * @exception throws
     * @see [类、类#方法、类#成员]
     */
    public boolean deleteByCode(String code) {
        AssertUtils.notEmpty(code, "code is empty.");
        
        VirtualCenter condition = new VirtualCenter();
        condition.setCode(code);
        
        int resInt = this.virtualCenterDao.delete(condition);
        boolean flag = resInt > 0;
        return flag;
    }
    
    /**
     * 根据id查询VirtualCenter实例
     * 1、当id为empty时抛出异常
     *
     * @param id
     * @return VirtualCenter [返回类型说明]
     * @exception throws
     * @see [类、类#方法、类#成员]
     */
    public VirtualCenter findById(String id) {
        AssertUtils.notEmpty(id, "id is empty.");
        
        VirtualCenter condition = new VirtualCenter();
        condition.setId(id);
        
        VirtualCenter res = this.virtualCenterDao.find(condition);
        return res;
    }
    
    /**
     * 根据code查询VirtualCenter实例
     * 1、当code为empty时抛出异常
     *
     * @param code
     * @return VirtualCenter [返回类型说明]
     * @exception throws
     * @see [类、类#方法、类#成员]
     */
    public VirtualCenter findByCode(String code) {
        AssertUtils.notEmpty(code, "code is empty.");
        
        VirtualCenter condition = new VirtualCenter();
        condition.setCode(code);
        
        VirtualCenter res = this.virtualCenterDao.find(condition);
        return res;
    }
    
    /**
     * 查询VirtualCenter实例列表
     * <功能详细描述>
     * @param valid
     * @param params      
     * @return [参数说明]
     * 
     * @return List<VirtualCenter> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public List<VirtualCenter> queryList(Boolean valid,
            Map<String, Object> params) {
        //判断条件合法性
        
        //生成查询条件
        params = params == null ? new HashMap<String, Object>() : params;
        params.put("valid", valid);
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        List<VirtualCenter> resList = this.virtualCenterDao.queryList(params);
        
        return resList;
    }
    
    /**
     * 查询VirtualCenter实例列表
     * <功能详细描述>
     * @param valid
     * @param querier      
     * @return [参数说明]
     * 
     * @return List<VirtualCenter> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public List<VirtualCenter> queryList(Boolean valid, Querier querier) {
        //判断条件合法性
        
        //生成查询条件
        querier = querier == null ? QuerierBuilder.newInstance().querier()
                : querier;
        if (valid != null) {
            querier.getFilters().add(Filter.eq("valid", valid));
        }
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        List<VirtualCenter> resList = this.virtualCenterDao.queryList(querier);
        
        return resList;
    }
    
    /**
     * 分页查询VirtualCenter实例列表
     * <功能详细描述>
     * @param valid
     * @param params    
     * @param pageIndex 当前页index从1开始计算
     * @param pageSize 每页显示行数
     * 
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<VirtualCenter> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public PagedList<VirtualCenter> queryPagedList(Boolean valid,
            Map<String, Object> params, int pageIndex, int pageSize) {
        //T判断条件合法性
        
        //生成查询条件
        params = params == null ? new HashMap<String, Object>() : params;
        params.put("valid", valid);
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        PagedList<VirtualCenter> resPagedList = this.virtualCenterDao
                .queryPagedList(params, pageIndex, pageSize);
        
        return resPagedList;
    }
    
    /**
     * 分页查询VirtualCenter实例列表
     * <功能详细描述>
     * @param valid
     * @param querier    
     * @param pageIndex 当前页index从1开始计算
     * @param pageSize 每页显示行数
     * 
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<VirtualCenter> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public PagedList<VirtualCenter> queryPagedList(Boolean valid,
            Querier querier, int pageIndex, int pageSize) {
        //T判断条件合法性
        
        //生成查询条件
        querier = querier == null ? QuerierBuilder.newInstance().querier()
                : querier;
        if (valid != null) {
            querier.getFilters().add(Filter.eq("valid", valid));
        }
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        PagedList<VirtualCenter> resPagedList = this.virtualCenterDao
                .queryPagedList(querier, pageIndex, pageSize);
        
        return resPagedList;
    }
    
    /**
     * 查询VirtualCenter实例数量<br/>
     * <功能详细描述>
     * @param valid
     * @param params      
     * @return [参数说明]
     * 
     * @return List<VirtualCenter> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public int count(Boolean valid, Map<String, Object> params) {
        //判断条件合法性
        
        //生成查询条件
        params = params == null ? new HashMap<String, Object>() : params;
        params.put("valid", valid);
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        int res = this.virtualCenterDao.count(params);
        
        return res;
    }
    
    /**
     * 查询VirtualCenter实例数量<br/>
     * <功能详细描述>
     * @param valid
     * @param querier      
     * @return [参数说明]
     * 
     * @return List<VirtualCenter> [返回类型说明]
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
        int res = this.virtualCenterDao.count(querier);
        
        return res;
    }
    
    /**
     * 判断VirtualCenter实例是否已经存在<br/>
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
        params.put("excludeId", excludeId);
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        int res = this.virtualCenterDao.count(params);
        
        return res > 0;
    }
    
    /**
     * 判断VirtualCenter实例是否已经存在<br/>
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
        int res = this.virtualCenterDao.count(querier, excludeId);
        
        return res > 0;
    }
    
    /**
     * 根据id更新VirtualCenter实例<br/>
     * <功能详细描述>
     * @param virtualCenter
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Transactional
    public boolean updateById(String id, VirtualCenter virtualCenter) {
        //验证参数是否合法，必填字段是否填写
        AssertUtils.notNull(virtualCenter, "virtualCenter is null.");
        AssertUtils.notEmpty(id, "id is empty.");
        AssertUtils.notEmpty(virtualCenter.getCode(),
                "virtualCenter.code is empty.");
        AssertUtils.notEmpty(virtualCenter.getName(),
                "virtualCenter.name is empty.");
        AssertUtils.notEmpty(virtualCenter.isModifyAble(),
                "virtualCenter.modifyAble is empty.");
        
        //生成需要更新字段的hashMap
        Map<String, Object> updateRowMap = new HashMap<String, Object>();
        //需要更新的字段
        updateRowMap.put("code", virtualCenter.getCode());
        updateRowMap.put("name", virtualCenter.getName());
        updateRowMap.put("valid", virtualCenter.isValid());
        updateRowMap.put("modifyAble", virtualCenter.isModifyAble());
        updateRowMap.put("parentId", virtualCenter.getParentId());
        updateRowMap.put("remark", virtualCenter.getRemark());
        
        boolean flag = this.virtualCenterDao.update(id, updateRowMap);
        //如果需要大于1时，抛出异常并回滚，需要在这里修改
        return flag;
    }
    
    /**
     * 根据id更新VirtualCenter实例<br/>
     * <功能详细描述>
     * @param virtualCenter
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Transactional
    public boolean updateById(VirtualCenter virtualCenter) {
        //验证参数是否合法，必填字段是否填写
        AssertUtils.notNull(virtualCenter, "virtualCenter is null.");
        AssertUtils.notEmpty(virtualCenter.getId(),
                "virtualCenter.id is empty.");
        
        boolean flag = updateById(virtualCenter.getId(), virtualCenter);
        //如果需要大于1时，抛出异常并回滚，需要在这里修改
        return flag;
    }
    
    /**
     * 根据id禁用VirtualCenter<br/>
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
        
        boolean flag = this.virtualCenterDao.update(params) > 0;
        
        return flag;
    }
    
    /**
     * 根据id启用VirtualCenter<br/>
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
        
        boolean flag = this.virtualCenterDao.update(params) > 0;
        
        return flag;
    }
    
    /**
     * 根据parentId查询VirtualCenter子级实例列表<br/>
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
    public List<VirtualCenter> queryChildrenByParentId(String parentId,
            Boolean valid, Map<String, Object> params) {
        //判断条件合法性
        AssertUtils.notEmpty(parentId, "parentId is empty.");
        
        //生成查询条件
        params = params == null ? new HashMap<String, Object>() : params;
        params.put("parentId", parentId);
        params.put("valid", valid);
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        List<VirtualCenter> resList = this.virtualCenterDao.queryList(params);
        
        return resList;
    }
    
    /**
     * 根据parentId查询VirtualCenter子级实例列表<br/>
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
    public List<VirtualCenter> queryChildrenByParentId(String parentId,
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
        List<VirtualCenter> resList = this.virtualCenterDao.queryList(querier);
        
        return resList;
    }
    
    /**
     * 根据条件查询基础数据分页列表<br/>
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
    public List<VirtualCenter> queryDescendantsByParentId(String parentId,
            Boolean valid, Map<String, Object> params) {
        //判断条件合法性
        AssertUtils.notEmpty(parentId, "parentId is empty.");
        
        //生成查询条件
        params = params == null ? new HashMap<String, Object>() : params;
        Set<String> ids = new HashSet<>();
        Set<String> parentIds = new HashSet<>();
        parentIds.add(parentId);
        
        List<VirtualCenter> resList = doNestedQueryChildren(valid,
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
     * @return List<VirtualCenter> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    private List<VirtualCenter> doNestedQueryChildren(Boolean valid,
            Set<String> ids, Set<String> parentIds,
            Map<String, Object> params) {
        if (CollectionUtils.isEmpty(parentIds)) {
            return new ArrayList<VirtualCenter>();
        }
        
        //ids避免数据出错时导致无限循环
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.putAll(params);
        queryParams.put("parentIds", parentIds);
        List<VirtualCenter> resList = queryList(valid, queryParams);
        
        Set<String> newParentIds = new HashSet<>();
        for (VirtualCenter bdTemp : resList) {
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
     * 根据条件查询基础数据分页列表<br/>
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
    public List<VirtualCenter> queryDescendantsByParentId(String parentId,
            Boolean valid, Querier querier) {
        //判断条件合法性
        AssertUtils.notEmpty(parentId, "parentId is empty.");
        
        //生成查询条件
        querier = querier == null ? QuerierBuilder.newInstance().querier()
                : querier;
        Set<String> ids = new HashSet<>();
        Set<String> parentIds = new HashSet<>();
        parentIds.add(parentId);
        
        List<VirtualCenter> resList = doNestedQueryChildren(valid,
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
     * @return List<VirtualCenter> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    private List<VirtualCenter> doNestedQueryChildren(Boolean valid,
            Set<String> ids, Set<String> parentIds, Querier querier) {
        if (CollectionUtils.isEmpty(parentIds)) {
            return new ArrayList<VirtualCenter>();
        }
        
        //ids避免数据出错时导致无限循环
        Querier querierClone = (Querier) querier.clone();
        querierClone.getFilters().add(Filter.in("parentId", parentIds));
        List<VirtualCenter> resList = queryList(valid, querierClone);
        
        Set<String> newParentIds = new HashSet<>();
        for (VirtualCenter bdTemp : resList) {
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
