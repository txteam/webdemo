/*
 * 描          述:  <描述>
 * 修  改   人:  
 * 修改时间:  
 * <修改描述:>
 */
package com.tx.local.organization.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.tx.core.TxConstants;
import com.tx.core.exceptions.util.AssertUtils;
import com.tx.core.paged.model.PagedList;
import com.tx.core.querier.model.Filter;
import com.tx.core.querier.model.Querier;
import com.tx.core.querier.model.QuerierBuilder;
import com.tx.core.util.UUIDUtils;
import com.tx.local.organization.dao.OrganizationDao;
import com.tx.local.organization.model.Organization;
import com.tx.local.organization.model.OrganizationTypeEnum;

/**
 * 组织的业务层[OrganizationService]
 * <功能详细描述>
 * 
 * @author  
 * @version [版本号]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Component("organizationService")
public class OrganizationService {
    
    @SuppressWarnings("unused")
    private Logger logger = LoggerFactory.getLogger(OrganizationService.class);
    
    @Resource(name = "organizationDao")
    private OrganizationDao organizationDao;
    
    /** 
     * 生成组织全名
     * <功能详细描述>
     * @param organization [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    private String generateFullName(String parentId, String organizationName) {
        StringBuilder sb = new StringBuilder(TxConstants.INITIAL_STR_LENGTH);
        sb.append(organizationName);
        
        //利用该集合避免循环引用以及自引用的情况，保证循环跳出
        if (!StringUtils.isEmpty(parentId)) {
            Organization parent = findById(parentId);
            if (parent != null) {
                sb.insert(0, "_");
                sb.insert(0, parent.getFullName());
            }
        }
        String newFullName = sb.toString();
        return newFullName;
    }
    
    /**
     * 新增组织实例<br/>
     * 将organization插入数据库中保存
     * 1、如果organization 为空时抛出参数为空异常
     * 2、如果organization 中部分必要参数为非法值时抛出参数不合法异常
     * 
     * @param organization [参数说明]
     * @return void [返回类型说明]
     * @exception throws
     * @see [类、类#方法、类#成员]
     */
    @Transactional
    public void insertToHis(Organization organization) {
        //验证参数是否合法
        AssertUtils.notNull(organization, "organization is null.");
        AssertUtils.notEmpty(organization.getCode(),
                "organization.code is empty.");
        AssertUtils.notEmpty(organization.getName(),
                "organization.name is empty.");
        AssertUtils.notEmpty(organization.getType(),
                "organization.type is empty.");
        AssertUtils.notEmpty(organization.getVcid(),
                "organization.vcid is empty.");
        
        AssertUtils.notEmpty(organization.getId(),
                "organization.parentId is empty.");
        
        //为添加的数据需要填入默认值的字段填入默认值
        Date now = new Date();
        organization.setLastUpdateDate(now);
        
        //调用数据持久层对实例进行持久化操作
        this.organizationDao.insertToHis(organization);
    }
    
    /**
     * 新增组织实例<br/>
     * 将organization插入数据库中保存
     * 1、如果organization 为空时抛出参数为空异常
     * 2、如果organization 中部分必要参数为非法值时抛出参数不合法异常
     * 
     * @param organization [参数说明]
     * @return void [返回类型说明]
     * @exception throws
     * @see [类、类#方法、类#成员]
     */
    @Transactional
    public void insert(Organization organization) {
        //验证参数是否合法
        AssertUtils.notNull(organization, "organization is null.");
        AssertUtils.notEmpty(organization.getCode(),
                "organization.code is empty.");
        AssertUtils.notEmpty(organization.getName(),
                "organization.name is empty.");
        AssertUtils.notEmpty(organization.getType(),
                "organization.type is empty.");
        AssertUtils.notEmpty(organization.getVcid(),
                "organization.vcid is empty.");
        
        //为添加的数据需要填入默认值的字段填入默认值:因需要写入公司id
        organization.setId(UUIDUtils.generateUUID());
        
        Date now = new Date();
        organization.setValid(true);
        organization.setCreateDate(now);
        organization.setLastUpdateDate(now);
        //生成组织全名
        organization.setFullName(generateFullName(organization.getParentId(),
                organization.getName()));
        
        switch (organization.getType()) {
            case DEPARTMENT:
            case BRANCH_DEPARTMENT:
            case GROUP:
                //部门，科室，分组所属公司不能为空
                AssertUtils.notEmpty(organization.getParentId(),
                        "[组织新增:]上级组织不能为空!");
                
                Organization parent = this.findById(organization.getParentId());
                //设置分公司
                if (parent != null) {
                    organization.setCompany(parent.getCompany());
                }
                break;
            default:
                organization.setCompany(organization);
                break;
        }
        
        //调用数据持久层对实例进行持久化操作
        this.organizationDao.insert(organization);
    }
    
    /**
     * 根据id删除组织实例
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
        
        Organization condition = new Organization();
        condition.setId(id);
        
        int resInt = this.organizationDao.delete(condition);
        boolean flag = resInt > 0;
        return flag;
    }
    
    /**
     * 根据code删除组织实例
     * 1、当code为empty时抛出异常
     * 2、执行删除后，将返回数据库中被影响的条数 > 0，则返回true
     *
     * @param code
     * @return Organization [返回类型说明]
     * @exception throws
     * @see [类、类#方法、类#成员]
     */
    @Transactional
    public boolean deleteByCode(String code) {
        AssertUtils.notEmpty(code, "code is empty.");
        
        Organization condition = new Organization();
        condition.setCode(code);
        
        int resInt = this.organizationDao.delete(condition);
        boolean flag = resInt > 0;
        return flag;
    }
    
    /**
     * 根据id查询组织实例
     * 1、当id为empty时抛出异常
     *
     * @param id
     * @return Organization [返回类型说明]
     * @exception throws
     * @see [类、类#方法、类#成员]
     */
    public Organization findById(String id) {
        AssertUtils.notEmpty(id, "id is empty.");
        
        Organization condition = new Organization();
        condition.setId(id);
        
        Organization res = this.organizationDao.find(condition);
        return res;
    }
    
    /**
     * 根据code查询组织实例
     * 1、当code为empty时抛出异常
     *
     * @param code
     * @return Organization [返回类型说明]
     * @exception throws
     * @see [类、类#方法、类#成员]
     */
    public Organization findByCode(String code) {
        AssertUtils.notEmpty(code, "code is empty.");
        
        Organization condition = new Organization();
        condition.setCode(code);
        
        Organization res = this.organizationDao.find(condition);
        return res;
    }
    
    /**
     * 查询组织实例列表
     * <功能详细描述>
     * @param valid
     * @param params      
     * @return [参数说明]
     * 
     * @return List<Organization> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public List<Organization> queryList(Boolean valid,
            Map<String, Object> params) {
        //判断条件合法性
        
        //生成查询条件
        params = params == null ? new HashMap<String, Object>() : params;
        params.put("valid", valid);
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        List<Organization> resList = this.organizationDao.queryList(params);
        
        return resList;
    }
    
    /**
     * 查询组织实例列表
     * <功能详细描述>
     * @param valid
     * @param querier      
     * @return [参数说明]
     * 
     * @return List<Organization> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public List<Organization> queryList(Boolean valid, Querier querier) {
        //判断条件合法性
        
        //生成查询条件
        querier = querier == null ? QuerierBuilder.newInstance().querier()
                : querier;
        if (valid != null) {
            querier.getFilters().add(Filter.eq("valid", valid));
        }
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        List<Organization> resList = this.organizationDao.queryList(querier);
        
        return resList;
    }
    
    /**
     * 分页查询组织实例列表
     * <功能详细描述>
     * @param valid
     * @param params    
     * @param pageIndex 当前页index从1开始计算
     * @param pageSize 每页显示行数
     * 
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<Organization> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public PagedList<Organization> queryPagedList(Boolean valid,
            Map<String, Object> params, int pageIndex, int pageSize) {
        //T判断条件合法性
        
        //生成查询条件
        params = params == null ? new HashMap<String, Object>() : params;
        params.put("valid", valid);
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        PagedList<Organization> resPagedList = this.organizationDao
                .queryPagedList(params, pageIndex, pageSize);
        
        return resPagedList;
    }
    
    /**
     * 分页查询组织实例列表
     * <功能详细描述>
     * @param valid
     * @param querier    
     * @param pageIndex 当前页index从1开始计算
     * @param pageSize 每页显示行数
     * 
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<Organization> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public PagedList<Organization> queryPagedList(Boolean valid,
            Querier querier, int pageIndex, int pageSize) {
        //T判断条件合法性
        
        //生成查询条件
        querier = querier == null ? QuerierBuilder.newInstance().querier()
                : querier;
        if (valid != null) {
            querier.getFilters().add(Filter.eq("valid", valid));
        }
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        PagedList<Organization> resPagedList = this.organizationDao
                .queryPagedList(querier, pageIndex, pageSize);
        
        return resPagedList;
    }
    
    /**
     * 查询组织实例数量<br/>
     * <功能详细描述>
     * @param valid
     * @param params      
     * @return [参数说明]
     * 
     * @return List<Organization> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public int count(Boolean valid, Map<String, Object> params) {
        //判断条件合法性
        
        //生成查询条件
        params = params == null ? new HashMap<String, Object>() : params;
        params.put("valid", valid);
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        int res = this.organizationDao.count(params);
        
        return res;
    }
    
    /**
     * 查询组织实例数量<br/>
     * <功能详细描述>
     * @param valid
     * @param querier      
     * @return [参数说明]
     * 
     * @return List<Organization> [返回类型说明]
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
        int res = this.organizationDao.count(querier);
        
        return res;
    }
    
    /**
     * 判断组织实例是否已经存在<br/>
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
        int res = this.organizationDao.count(params, excludeId);
        
        return res > 0;
    }
    
    /**
     * 判断组织实例是否已经存在<br/>
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
        int res = this.organizationDao.count(querier, excludeId);
        
        return res > 0;
    }
    
    /**
     * 根据id更新组织实例<br/>
     * <功能详细描述>
     * @param organization
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Transactional
    public boolean updateById(String id, Organization organization) {
        //验证参数是否合法，必填字段是否填写
        AssertUtils.notNull(organization, "organization is null.");
        AssertUtils.notEmpty(id, "id is empty.");
        AssertUtils.notEmpty(organization.getCode(),
                "organization.code is empty.");
        AssertUtils.notEmpty(organization.getName(),
                "organization.name is empty.");
        AssertUtils.notEmpty(organization.getType(),
                "organization.type is empty.");
        
        //生成需要更新字段的hashMap
        Map<String, Object> updateRowMap = new HashMap<String, Object>();
        
        updateRowMap.put("name", organization.getName());
        updateRowMap.put("fullName",
                generateFullName(organization.getParentId(),
                        organization.getName()));
        updateRowMap.put("remark", organization.getRemark());
        updateRowMap.put("alias", organization.getAlias());
        
        updateRowMap.put("districtId", organization.getDistrictId());
        updateRowMap.put("address", organization.getAddress());
        updateRowMap.put("fullAddress", organization.getFullAddress());
        
        updateRowMap.put("lastUpdateDate", new Date());
        
        //注意： 如果原组织类型为公司，原则上不允许进行修改，因为组织organization上有冗余字段company
        //如果进行更新，则有可能影响子集的company字段的正确性
        //如果想进行更新需要在底层考虑迭代更新
        if (organization.getType() != OrganizationTypeEnum.GROUP_COMPANY
                && organization.getType() != OrganizationTypeEnum.COMPANY
                && organization
                        .getType() != OrganizationTypeEnum.BRANCH_COMPANY) {
            updateRowMap.put("type", organization.getType());
        }
        
        //需要更新的字段
        //updateRowMap.put("parentId", organization.getParentId());
        //updateRowMap.put("vcid", organization.getVcid());
        //updateRowMap.put("code", organization.getCode());
        
        //updateRowMap.put("valid", organization.isValid());
        //updateRowMap.put("company", organization.getCompany());
        //updateRowMap.put("department", organization.getDepartment());
        
        boolean flag = this.organizationDao.update(id, updateRowMap);
        //如果需要大于1时，抛出异常并回滚，需要在这里修改
        return flag;
    }
    
    /**
     * 根据id更新组织实例<br/>
     *    
     * <功能详细描述>
     * @param organization
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Transactional
    public boolean updateById(Organization organization) {
        //验证参数是否合法，必填字段是否填写
        AssertUtils.notNull(organization, "organization is null.");
        AssertUtils.notEmpty(organization.getId(), "organization.id is empty.");
        
        boolean flag = updateById(organization.getId(), organization);
        //如果需要大于1时，抛出异常并回滚，需要在这里修改
        return flag;
    }
    
    /**
     * 根据id禁用组织<br/>
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
        
        boolean flag = this.organizationDao.update(params) > 0;
        
        return flag;
    }
    
    /**
     * 根据id启用组织<br/>
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
        
        boolean flag = this.organizationDao.update(params) > 0;
        
        return flag;
    }
    
    /**
     * 根据parentId查询组织子级实例列表<br/>
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
    public List<Organization> queryChildrenByParentId(String parentId,
            Boolean valid, Map<String, Object> params) {
        //判断条件合法性
        AssertUtils.notEmpty(parentId, "parentId is empty.");
        
        //生成查询条件
        params = params == null ? new HashMap<String, Object>() : params;
        params.put("parentId", parentId);
        params.put("valid", valid);
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        List<Organization> resList = this.organizationDao.queryList(params);
        
        return resList;
    }
    
    /**
     * 根据parentId查询组织子级实例列表<br/>
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
    public List<Organization> queryChildrenByParentId(String parentId,
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
        List<Organization> resList = this.organizationDao.queryList(querier);
        
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
    public List<Organization> queryDescendantsByParentId(String parentId,
            Boolean valid, Map<String, Object> params) {
        //判断条件合法性
        AssertUtils.notEmpty(parentId, "parentId is empty.");
        
        //生成查询条件
        params = params == null ? new HashMap<String, Object>() : params;
        Set<String> ids = new HashSet<>();
        Set<String> parentIds = new HashSet<>();
        parentIds.add(parentId);
        
        List<Organization> resList = doNestedQueryChildren(valid,
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
     * @return List<Organization> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    private List<Organization> doNestedQueryChildren(Boolean valid,
            Set<String> ids, Set<String> parentIds,
            Map<String, Object> params) {
        if (CollectionUtils.isEmpty(parentIds)) {
            return new ArrayList<Organization>();
        }
        
        //ids避免数据出错时导致无限循环
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.putAll(params);
        queryParams.put("parentIds", parentIds);
        List<Organization> resList = queryList(valid, queryParams);
        
        Set<String> newParentIds = new HashSet<>();
        for (Organization bdTemp : resList) {
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
    public List<Organization> queryDescendantsByParentId(String parentId,
            Boolean valid, Querier querier) {
        //判断条件合法性
        AssertUtils.notEmpty(parentId, "parentId is empty.");
        
        //生成查询条件
        querier = querier == null ? QuerierBuilder.newInstance().querier()
                : querier;
        Set<String> ids = new HashSet<>();
        Set<String> parentIds = new HashSet<>();
        parentIds.add(parentId);
        
        List<Organization> resList = doNestedQueryChildren(valid,
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
     * @return List<Organization> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    private List<Organization> doNestedQueryChildren(Boolean valid,
            Set<String> ids, Set<String> parentIds, Querier querier) {
        if (CollectionUtils.isEmpty(parentIds)) {
            return new ArrayList<Organization>();
        }
        
        //ids避免数据出错时导致无限循环
        Querier querierClone = (Querier) querier.clone();
        querierClone.getFilters().add(Filter.in("parentId", parentIds));
        List<Organization> resList = queryList(valid, querierClone);
        
        Set<String> newParentIds = new HashSet<>();
        for (Organization bdTemp : resList) {
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
    
    /**
     * 判断VirtualCenter实例是否可编辑<br/>
     * <功能详细描述>
     * @param key2valueMap
     * @param excludeId
     * @return
     * 
     * @return int [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public boolean modifyAble(String id) {
        AssertUtils.notEmpty(id, "id is empty");
        
        Organization condition = new Organization();
        condition.setId(id);
        Organization org = this.organizationDao.find(condition);
        if (org == null) {
            return false;
        }
        
        DateTime createDateTime = new DateTime(org.getCreateDate());
        Date now = new Date();
        //如果创建时间已经超过了一天
        if (createDateTime.plusDays(1).toDate().compareTo(now) <= 0) {
            return false;
        }
        
        Map<String, Object> params = new HashMap<>();
        params.put("parentId", id);
        int c = count(null, params);
        if (c > 0) {
            return false;
        }
        
        return true;
    }
}
