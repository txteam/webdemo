/*
 * 描          述:  <描述>
 * 修  改   人:  
 * 修改时间:  
 * <修改描述:>
 */
package com.tx.local.basicdata.service;

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

import com.tx.component.basicdata.service.AbstractBasicDataService;
import com.tx.core.exceptions.util.AssertUtils;
import com.tx.core.paged.model.PagedList;
import com.tx.core.querier.model.Filter;
import com.tx.core.querier.model.Querier;
import com.tx.core.querier.model.QuerierBuilder;
import com.tx.local.basicdata.dao.DistrictDao;
import com.tx.local.basicdata.model.District;

/**
 * District的业务层
 * <功能详细描述>
 *
 * @author
 * @version [版本号, ]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Component("districtService")
public class DistrictService extends AbstractBasicDataService<District> {
    
    @SuppressWarnings("unused")
    private Logger logger = LoggerFactory.getLogger(DistrictService.class);
    
    @Resource(name = "districtDao")
    private DistrictDao districtDao;
    
    /**
     * 将district实例插入数据库中保存
     * 1、如果district 为空时抛出参数为空异常
     * 2、如果district 中部分必要参数为非法值时抛出参数不合法异常
     *
     * @param district [参数说明]
     * @return void [返回类型说明]
     * @throws throws
     * @see [类、类#方法、类#成员]
     */
    @Transactional
    public void insert(District district) {
        //验证参数是否合法
        AssertUtils.notNull(district, "district is null.");
        AssertUtils.notEmpty(district.getCode(), "district.code is empty.");
        AssertUtils.notNull(district.getType(), "district.type is null.");
        AssertUtils.notEmpty(district.getName(), "district.name is empty.");
        AssertUtils.notEmpty(district.getZipCode(),
                "district.zipCode is empty.");
        
        //为添加的数据需要填入默认值的字段填入默认值
        if (district.getParent() == null) {
            district.setLevel(0);
        } else {
            AssertUtils.notEmpty(district.getParent().getId(),
                    "ditrict.parent.id is empty.");
            District parent = findById(district.getParent().getId());
            district.setLevel(parent.getLevel() + 1);
        }
        district.setValid(true);
        Date now = new Date();
        district.setCreateDate(now);
        district.setLastUpdateDate(now);
        
        //调用数据持久层对实体进行持久化操作
        this.districtDao.insert(district);
    }
    
    /**
     * 根据id删除district实例
     * 1、如果入参数为空，则抛出异常
     * 2、执行删除后，将返回数据库中被影响的条数 > 0，则返回true
     *
     * @param id
     * @return boolean 删除的条数>0则为true [返回类型说明]
     * @throws throws
     * @see [类、类#方法、类#成员]
     */
    @Transactional
    public boolean deleteById(String id) {
        AssertUtils.notEmpty(id, "id is empty.");
        
        District condition = new District();
        condition.setId(id);
        int resInt = this.districtDao.delete(condition);
        
        boolean flag = resInt > 0;
        return flag;
    }
    
    /**
     * @param arg0
     * @return
     */
    @Override
    @Transactional
    public boolean deleteByCode(String code) {
        AssertUtils.notEmpty(code, "code is empty.");
        
        District condition = new District();
        condition.setCode(code);
        int resInt = this.districtDao.delete(condition);
        
        boolean flag = resInt > 0;
        return flag;
    }
    
    /**
     * 根据Id查询District实体
     * 1、当id为empty时抛出异常
     *
     * @param id
     * @return District [返回类型说明]
     * @throws throws
     * @see [类、类#方法、类#成员]
     */
    public District findById(String id) {
        AssertUtils.notEmpty(id, "id is empty.");
        
        District condition = new District();
        condition.setId(id);
        
        District res = this.districtDao.find(condition);
        return res;
    }
    
    /**
     * @param arg0
     * @return
     */
    @Override
    public District findByCode(String code) {
        AssertUtils.notEmpty(code, "code is empty.");
        
        District condition = new District();
        condition.setCode(code);
        
        District res = this.districtDao.find(condition);
        return res;
    }
    
    /**
     * 查询District实体列表
     * <功能详细描述>
     *
     * @param valid
     * @param params
     * @return List<District> [返回类型说明]
     * @throws throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public List<District> queryList(Boolean valid, Map<String, Object> params) {
        //判断条件合法性
        
        //生成查询条件
        params = params == null ? new HashMap<String, Object>() : params;
        params.put("valid", valid);
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        List<District> resList = this.districtDao.queryList(params);
        
        return resList;
    }
    
    /**
     * @param valid
     * @param querier
     * @return
     */
    @Override
    public List<District> queryList(Boolean valid, Querier querier) {
        querier = querier == null ? new Querier() : querier;
        querier.getParams().put("valid", valid);
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        List<District> resList = this.districtDao.queryList(querier);
        
        return resList;
    }
    
    /**
     * 分页查询District实体列表
     * <功能详细描述>
     *
     * @param valid
     * @param params
     * @param pageIndex 当前页index从1开始计算
     * @param pageSize  每页显示行数
     *                  <p>
     *                  <功能详细描述>
     * @return List<District> [返回类型说明]
     * @throws throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public PagedList<District> queryPagedList(Boolean valid,
            Map<String, Object> params, int pageIndex, int pageSize) {
        //T判断条件合法性
        
        //生成查询条件
        params = params == null ? new HashMap<String, Object>() : params;
        params.put("valid", valid);
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        PagedList<District> resPagedList = this.districtDao
                .queryPagedList(params, pageIndex, pageSize);
        
        return resPagedList;
    }
    
    /**
     * @param valid
     * @param querier
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @Override
    public PagedList<District> queryPagedList(Boolean valid, Querier querier,
            int pageIndex, int pageSize) {
        querier = querier == null ? new Querier() : querier;
        querier.getParams().put("valid", valid);
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        PagedList<District> resPagedList = this.districtDao
                .queryPagedList(querier, pageIndex, pageSize);
        
        return resPagedList;
    }
    
    /**
     * 查询District实例数量<br/>
     * <功能详细描述>
     * @param valid
     * @param params      
     * @return [参数说明]
     * 
     * @return List<District> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public int count(Boolean valid, Map<String, Object> params) {
        //判断条件合法性
        
        //生成查询条件
        params = params == null ? new HashMap<String, Object>() : params;
        params.put("valid", valid);
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        int res = this.districtDao.count(params);
        
        return res;
    }
    
    /**
     * 查询District实例数量<br/>
     * <功能详细描述>
     * @param valid
     * @param querier      
     * @return [参数说明]
     * 
     * @return List<District> [返回类型说明]
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
        int res = this.districtDao.count(querier);
        
        return res;
    }
    
    /**
     * 判断District实例是否已经存在<br/>
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
        int res = this.districtDao.count(params);
        
        return res > 0;
    }
    
    /**
     * 判断District实例是否已经存在<br/>
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
        int res = this.districtDao.count(querier, excludeId);
        
        return res > 0;
    }
    
    /**
     * 根据id更新District实例<br/>
     * <功能详细描述>
     * @param district
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Transactional
    public boolean updateById(String id, District district) {
        //验证参数是否合法，必填字段是否填写
        AssertUtils.notNull(district, "district is null.");
        AssertUtils.notEmpty(id, "id is empty.");
        
        //生成需要更新字段的hashMap
        Map<String, Object> updateRowMap = new HashMap<String, Object>();
        //需要更新的字段
        //updateRowMap.put("code", district.getCode());
        updateRowMap.put("level", district.getLevel());
        updateRowMap.put("zipCode", district.getZipCode());
        
        updateRowMap.put("name", district.getName());
        updateRowMap.put("modifyAble", district.isModifyAble());
        
        updateRowMap.put("parent", district.getParent());
        updateRowMap.put("province", district.getProvince());
        updateRowMap.put("city", district.getCity());
        updateRowMap.put("county", district.getCounty());
        
        updateRowMap.put("pinyin", district.getPinyin());
        updateRowMap.put("py", district.getPy());
        updateRowMap.put("fullName", district.getFullName());
        updateRowMap.put("type", district.getType());
        updateRowMap.put("valid", district.isValid());
        
        updateRowMap.put("remark", district.getRemark());
        updateRowMap.put("attributes", district.getAttributes());
        updateRowMap.put("lastUpdateDate", new Date());
        
        boolean flag = this.districtDao.update(id, updateRowMap);
        //如果需要大于1时，抛出异常并回滚，需要在这里修改
        return flag;
    }
    
    /**
     * 根据id更新District实例<br/>
     * <功能详细描述>
     * @param district
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Transactional
    public boolean updateById(District district) {
        //验证参数是否合法，必填字段是否填写
        AssertUtils.notNull(district, "district is null.");
        AssertUtils.notEmpty(district.getId(), "district.id is empty.");
        
        boolean flag = updateById(district.getId(), district);
        //如果需要大于1时，抛出异常并回滚，需要在这里修改
        return flag;
    }
    
    /**
     * @param data
     * @return
     */
    @Override
    @Transactional
    public boolean updateByCode(District district) {
        //验证参数是否合法，必填字段是否填写，
        AssertUtils.notNull(district, "district is null.");
        AssertUtils.notEmpty(district.getCode(), "district.code is empty.");
        
        //生成需要更新字段的hashMap
        Map<String, Object> updateRowMap = new HashMap<String, Object>();
        updateRowMap.put("code", district.getCode());
        
        //需要更新的字段
        updateRowMap.put("level", district.getLevel());
        updateRowMap.put("remark", district.getRemark());
        updateRowMap.put("py", district.getPy());
        updateRowMap.put("zipCode", district.getZipCode());
        updateRowMap.put("name", district.getName());
        updateRowMap.put("modifyAble", district.isModifyAble());
        updateRowMap.put("fullName", district.getFullName());
        updateRowMap.put("code", district.getCode());
        updateRowMap.put("pinyin", district.getPinyin());
        updateRowMap.put("lastUpdateDate", district.getLastUpdateDate());
        int updateRowCount = this.districtDao.update(updateRowMap);
        
        //如果需要大于1时，抛出异常并回滚，需要在这里修改
        return updateRowCount >= 1;
    }
    
    /**
     * 根据id禁用District<br/>
     * <功能详细描述>
     *
     * @param id
     * @return boolean [返回类型说明]
     * @throws throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Transactional
    public boolean disableById(String id) {
        AssertUtils.notEmpty(id, "id is empty.");
        
        //生成查询条件
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", id);
        params.put("valid", false);
        
        this.districtDao.update(params);
        return true;
    }
    
    /**
     * 根据id启用District<br/>
     * <功能详细描述>
     *
     * @param postId
     * @return boolean [返回类型说明]
     * @throws throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Transactional
    public boolean enableById(String id) {
        AssertUtils.notEmpty(id, "id is empty.");
        
        //生成查询条件
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", id);
        params.put("valid", true);
        this.districtDao.update(params);
        
        return true;
    }
    
    /**
     * 根据parentId查询District子级实例列表<br/>
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
    public List<District> queryChildrenByParentId(String parentId,
            Boolean valid, Map<String, Object> params) {
        //判断条件合法性
        AssertUtils.notEmpty(parentId, "parentId is empty.");
        
        //生成查询条件
        params = params == null ? new HashMap<String, Object>() : params;
        params.put("parentId", parentId);
        params.put("valid", valid);
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        List<District> resList = this.districtDao.queryList(params);
        
        return resList;
    }
    
    /**
     * 根据parentId查询District子级实例列表<br/>
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
    public List<District> queryChildrenByParentId(String parentId,
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
        List<District> resList = this.districtDao.queryList(querier);
        
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
    public List<District> queryDescendantsByParentId(String parentId,
            Boolean valid, Map<String, Object> params) {
        //判断条件合法性
        AssertUtils.notEmpty(parentId, "parentId is empty.");
        
        //生成查询条件
        params = params == null ? new HashMap<String, Object>() : params;
        Set<String> ids = new HashSet<>();
        Set<String> parentIds = new HashSet<>();
        parentIds.add(parentId);
        
        List<District> resList = doNestedQueryChildren(valid,
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
    private List<District> doNestedQueryChildren(Boolean valid, Set<String> ids,
            Set<String> parentIds, Map<String, Object> params) {
        if (CollectionUtils.isEmpty(parentIds)) {
            return new ArrayList<District>();
        }
        
        //ids避免数据出错时导致无限循环
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.putAll(params);
        queryParams.put("parentIds", parentIds);
        List<District> resList = queryList(valid, queryParams);
        
        Set<String> newParentIds = new HashSet<>();
        for (District bdTemp : resList) {
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
    public List<District> queryDescendantsByParentId(String parentId,
            Boolean valid, Querier querier) {
        //判断条件合法性
        AssertUtils.notEmpty(parentId, "parentId is empty.");
        
        //生成查询条件
        querier = querier == null ? QuerierBuilder.newInstance().querier()
                : querier;
        Set<String> ids = new HashSet<>();
        Set<String> parentIds = new HashSet<>();
        parentIds.add(parentId);
        
        List<District> resList = doNestedQueryChildren(valid,
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
    private List<District> doNestedQueryChildren(Boolean valid, Set<String> ids,
            Set<String> parentIds, Querier querier) {
        if (CollectionUtils.isEmpty(parentIds)) {
            return new ArrayList<District>();
        }
        
        //ids避免数据出错时导致无限循环
        Querier querierClone = (Querier) querier.clone();
        querierClone.getFilters().add(Filter.in("parentId", parentIds));
        List<District> resList = queryList(valid, querierClone);
        
        Set<String> newParentIds = new HashSet<>();
        for (District bdTemp : resList) {
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
