/*
 * 描          述:  <描述>
 * 修  改   人:  
 * 修改时间:  
 * <修改描述:>
 */
package com.tx.local.message.service;

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

import com.tx.local.message.dao.MessageCatalogDao;
import com.tx.local.message.model.MessageCatalog;
import com.tx.core.exceptions.util.AssertUtils;
import com.tx.core.paged.model.PagedList;
import com.tx.core.querier.model.Filter;
import com.tx.core.querier.model.Querier;
import com.tx.core.querier.model.QuerierBuilder;

/**
 * 信息分类的业务层[MessageCatalogService]
 * <功能详细描述>
 * 
 * @author  
 * @version [版本号]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Component("messageCatalogService")
public class MessageCatalogService {
    
    @SuppressWarnings("unused")
    private Logger logger = LoggerFactory.getLogger(MessageCatalogService.class);
    
    @Resource(name = "messageCatalogDao")
    private MessageCatalogDao messageCatalogDao;
    
    /**
     * 新增信息分类实例<br/>
     * 将messageCatalog插入数据库中保存
     * 1、如果messageCatalog 为空时抛出参数为空异常
     * 2、如果messageCatalog 中部分必要参数为非法值时抛出参数不合法异常
     * 
     * @param messageCatalog [参数说明]
     * @return void [返回类型说明]
     * @exception throws
     * @see [类、类#方法、类#成员]
     */
    @Transactional
    public void insert(MessageCatalog messageCatalog) {
        //验证参数是否合法
        AssertUtils.notNull(messageCatalog, "messageCatalog is null.");
		AssertUtils.notEmpty(messageCatalog.getCode(), "messageCatalog.code is empty.");
		AssertUtils.notEmpty(messageCatalog.getName(), "messageCatalog.name is empty.");
		AssertUtils.notEmpty(messageCatalog.getVcid(), "messageCatalog.vcid is empty.");
		AssertUtils.notEmpty(messageCatalog.getMessageType(), "messageCatalog.messageType is empty.");
           
        //FIXME:为添加的数据需要填入默认值的字段填入默认值
		messageCatalog.setLastUpdateDate(new Date());
		messageCatalog.setValid(true);
		messageCatalog.setCreateDate(new Date());
        
        //调用数据持久层对实例进行持久化操作
        this.messageCatalogDao.insert(messageCatalog);
    }
    
    /**
     * 根据id删除信息分类实例
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
        
        MessageCatalog condition = new MessageCatalog();
        condition.setId(id);
        
        int resInt = this.messageCatalogDao.delete(condition);
        boolean flag = resInt > 0;
        return flag;
    }

    /**
     * 根据code删除信息分类实例
     * 1、当code为empty时抛出异常
     * 2、执行删除后，将返回数据库中被影响的条数 > 0，则返回true
     *
     * @param code
     * @return MessageCatalog [返回类型说明]
     * @exception throws
     * @see [类、类#方法、类#成员]
     */
    public boolean deleteByCode(String code) {
        AssertUtils.notEmpty(code, "code is empty.");
        
        MessageCatalog condition = new MessageCatalog();
        condition.setCode(code);
        
        int resInt = this.messageCatalogDao.delete(condition);
        boolean flag = resInt > 0;
        return flag;
    }
    
    /**
     * 根据id查询信息分类实例
     * 1、当id为empty时抛出异常
     *
     * @param id
     * @return MessageCatalog [返回类型说明]
     * @exception throws
     * @see [类、类#方法、类#成员]
     */
    public MessageCatalog findById(String id) {
        AssertUtils.notEmpty(id, "id is empty.");
        
        MessageCatalog condition = new MessageCatalog();
        condition.setId(id);
        
        MessageCatalog res = this.messageCatalogDao.find(condition);
        return res;
    }

    /**
     * 根据code查询信息分类实例
     * 1、当code为empty时抛出异常
     *
     * @param code
     * @return MessageCatalog [返回类型说明]
     * @exception throws
     * @see [类、类#方法、类#成员]
     */
    public MessageCatalog findByCode(String code) {
        AssertUtils.notEmpty(code, "code is empty.");
        
        MessageCatalog condition = new MessageCatalog();
        condition.setCode(code);
        
        MessageCatalog res = this.messageCatalogDao.find(condition);
        return res;
    }
    
    /**
     * 查询信息分类实例列表
     * <功能详细描述>
     * @param valid
     * @param params      
     * @return [参数说明]
     * 
     * @return List<MessageCatalog> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public List<MessageCatalog> queryList(
		Boolean valid,
		Map<String,Object> params   
    	) {
        //判断条件合法性
        
        //生成查询条件
        params = params == null ? new HashMap<String, Object>() : params;
		params.put("valid",valid);

        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        List<MessageCatalog> resList = this.messageCatalogDao.queryList(params);
        
        return resList;
    }
    
    /**
     * 查询信息分类实例列表
     * <功能详细描述>
     * @param valid
     * @param querier      
     * @return [参数说明]
     * 
     * @return List<MessageCatalog> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public List<MessageCatalog> queryList(
		Boolean valid,
		Querier querier   
    	) {
        //判断条件合法性
        
        //生成查询条件
        querier = querier == null ? QuerierBuilder.newInstance().querier()
                : querier;
		if (valid != null) {
            querier.getFilters().add(Filter.eq("valid", valid));
        }

        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        List<MessageCatalog> resList = this.messageCatalogDao.queryList(querier);
        
        return resList;
    }
    
    /**
     * 分页查询信息分类实例列表
     * <功能详细描述>
     * @param valid
     * @param params    
     * @param pageIndex 当前页index从1开始计算
     * @param pageSize 每页显示行数
     * 
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<MessageCatalog> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public PagedList<MessageCatalog> queryPagedList(
		Boolean valid,
		Map<String,Object> params,
    	int pageIndex,
        int pageSize) {
        //T判断条件合法性
        
        //生成查询条件
        params = params == null ? new HashMap<String, Object>() : params;
		params.put("valid",valid);
 
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        PagedList<MessageCatalog> resPagedList = this.messageCatalogDao.queryPagedList(params, pageIndex, pageSize);
        
        return resPagedList;
    }
    
	/**
     * 分页查询信息分类实例列表
     * <功能详细描述>
     * @param valid
     * @param querier    
     * @param pageIndex 当前页index从1开始计算
     * @param pageSize 每页显示行数
     * 
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<MessageCatalog> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public PagedList<MessageCatalog> queryPagedList(
		Boolean valid,
		Querier querier,
    	int pageIndex,
        int pageSize) {
        //T判断条件合法性
        
        //生成查询条件
        querier = querier == null ? QuerierBuilder.newInstance().querier()
                : querier;
		if (valid != null) {
            querier.getFilters().add(Filter.eq("valid", valid));
        }
 
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        PagedList<MessageCatalog> resPagedList = this.messageCatalogDao.queryPagedList(querier, pageIndex, pageSize);
        
        return resPagedList;
    }
    
    /**
     * 查询信息分类实例数量<br/>
     * <功能详细描述>
     * @param valid
     * @param params      
     * @return [参数说明]
     * 
     * @return List<MessageCatalog> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public int count(
		Boolean valid,
		Map<String,Object> params   
    	) {
        //判断条件合法性
        
        //生成查询条件
        params = params == null ? new HashMap<String, Object>() : params;
		params.put("valid",valid);

        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        int res = this.messageCatalogDao.count(params);
        
        return res;
    }
    
    /**
     * 查询信息分类实例数量<br/>
     * <功能详细描述>
     * @param valid
     * @param querier      
     * @return [参数说明]
     * 
     * @return List<MessageCatalog> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public int count(
		Boolean valid,
		Querier querier   
    	) {
        //判断条件合法性
        
        //生成查询条件
        querier = querier == null ? QuerierBuilder.newInstance().querier()
                : querier;
		if (valid != null) {
            querier.getFilters().add(Filter.eq("valid", valid));
        }

        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        int res = this.messageCatalogDao.count(querier);
        
        return res;
    }
    
    /**
     * 判断信息分类实例是否已经存在<br/>
     * <功能详细描述>
     * @param key2valueMap
     * @param excludeId
     * @return
     * 
     * @return int [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public boolean exists(Map<String,String> key2valueMap, String excludeId) {
        AssertUtils.notEmpty(key2valueMap, "key2valueMap is empty");
        
        //生成查询条件
        Map<String, Object> params = new HashMap<String, Object>();
        params.putAll(key2valueMap);
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        int res = this.messageCatalogDao.count(params,excludeId);
        
        return res > 0;
    }
    
    /**
     * 判断信息分类实例是否已经存在<br/>
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
        int res = this.messageCatalogDao.count(querier,excludeId);
        
        return res > 0;
    }
    
    /**
     * 根据id更新信息分类实例<br/>
     * <功能详细描述>
     * @param messageCatalog
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Transactional
    public boolean updateById(String id,MessageCatalog messageCatalog) {
        //验证参数是否合法，必填字段是否填写
        AssertUtils.notNull(messageCatalog, "messageCatalog is null.");
        AssertUtils.notEmpty(id, "id is empty.");
		AssertUtils.notEmpty(messageCatalog.getName(), "messageCatalog.name is empty.");

        //生成需要更新字段的hashMap
        Map<String, Object> updateRowMap = new HashMap<String, Object>();
        //FIXME:需要更新的字段
		updateRowMap.put("name", messageCatalog.getName());
		updateRowMap.put("modifyAble", messageCatalog.isModifyAble());
		updateRowMap.put("remark", messageCatalog.getRemark());
		updateRowMap.put("lastUpdateDate", new Date());

        boolean flag = this.messageCatalogDao.update(id,updateRowMap); 
        //如果需要大于1时，抛出异常并回滚，需要在这里修改
        return flag;
    }
    
    /**
     * 根据id更新信息分类实例<br/>
     * <功能详细描述>
     * @param messageCatalog
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Transactional
    public boolean updateById(MessageCatalog messageCatalog) {
        //验证参数是否合法，必填字段是否填写
        AssertUtils.notNull(messageCatalog, "messageCatalog is null.");
        AssertUtils.notEmpty(messageCatalog.getId(), "messageCatalog.id is empty.");

        boolean flag = updateById(messageCatalog.getId(),messageCatalog); 
        //如果需要大于1时，抛出异常并回滚，需要在这里修改
        return flag;
    }

    /**
     * 根据id禁用信息分类<br/>
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
	        
        boolean flag = this.messageCatalogDao.update(params) > 0;
        
        return flag;
    }
    
    /**
     * 根据id启用信息分类<br/>
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
	
        boolean flag = this.messageCatalogDao.update(params) > 0;
        
        return flag;
    }

    /**
     * 根据parentId查询信息分类子级实例列表<br/>
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
    public List<MessageCatalog> queryChildrenByParentId(String parentId,
			Boolean valid,
			Map<String,Object> params) {
        //判断条件合法性
        AssertUtils.notEmpty(parentId,"parentId is empty.");
        
        //生成查询条件
        params = params == null ? new HashMap<String, Object>() : params;
        params.put("parentId", parentId);
		params.put("valid",valid);

        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        List<MessageCatalog> resList = this.messageCatalogDao.queryList(params);
        
        return resList;
    }
    
    /**
     * 根据parentId查询信息分类子级实例列表<br/>
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
    public List<MessageCatalog> queryChildrenByParentId(String parentId,
			Boolean valid,
			Querier querier) {
        //判断条件合法性
        AssertUtils.notEmpty(parentId,"parentId is empty.");
        
        //生成查询条件
        querier = querier == null ? QuerierBuilder.newInstance().querier()
                : querier;
		if (valid != null) {
            querier.getFilters().add(Filter.eq("valid", valid));
        }
		querier.getFilters().add(Filter.eq("parentId", parentId));

        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        List<MessageCatalog> resList = this.messageCatalogDao.queryList(querier);
        
        return resList;
    }
    
    /**
     * 根据parentId查询信息分类子、孙级实例列表<br/>
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
    public List<MessageCatalog> queryDescendantsByParentId(String parentId,
			Boolean valid,
            Map<String, Object> params) {
        //判断条件合法性
        AssertUtils.notEmpty(parentId,"parentId is empty.");
        
        //生成查询条件
        params = params == null ? new HashMap<String, Object>() : params;
        Set<String> ids = new HashSet<>();
        Set<String> parentIds = new HashSet<>();
        parentIds.add(parentId);
        
        List<MessageCatalog> resList = doNestedQueryChildren(valid, ids, parentIds, params);
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
     * @return List<MessageCatalog> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    private List<MessageCatalog> doNestedQueryChildren(
			Boolean valid,
    		Set<String> ids,Set<String> parentIds,Map<String, Object> params) {
        if (CollectionUtils.isEmpty(parentIds)) {
            return new ArrayList<MessageCatalog>();
        }
        
        //ids避免数据出错时导致无限循环
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.putAll(params);
        queryParams.put("parentIds", parentIds);
        List<MessageCatalog> resList = queryList(valid, queryParams);
        
        Set<String> newParentIds = new HashSet<>();
        for (MessageCatalog bdTemp : resList) {
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
     * 根据parentId查询信息分类子、孙级实例列表<br/>
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
    public List<MessageCatalog> queryDescendantsByParentId(String parentId,
			Boolean valid,
            Querier querier) {
        //判断条件合法性
        AssertUtils.notEmpty(parentId,"parentId is empty.");
        
        //生成查询条件
        querier = querier == null ? QuerierBuilder.newInstance().querier()
                : querier;
        Set<String> ids = new HashSet<>();
        Set<String> parentIds = new HashSet<>();
        parentIds.add(parentId);
        
        List<MessageCatalog> resList = doNestedQueryChildren(valid, ids, parentIds, querier);
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
     * @return List<MessageCatalog> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    private List<MessageCatalog> doNestedQueryChildren(
			Boolean valid,
    		Set<String> ids,
    		Set<String> parentIds,
    		Querier querier) {
        if (CollectionUtils.isEmpty(parentIds)) {
            return new ArrayList<MessageCatalog>();
        }
        
        //ids避免数据出错时导致无限循环
        Querier querierClone = (Querier)querier.clone();
        querierClone.getFilters().add(Filter.in("parentId", parentIds));
        List<MessageCatalog> resList = queryList(valid, querierClone);
        
        Set<String> newParentIds = new HashSet<>();
        for (MessageCatalog bdTemp : resList) {
            if (!ids.contains(bdTemp.getId())) {
                newParentIds.add(bdTemp.getId());
            }
            ids.add(bdTemp.getId());
        }
        //嵌套查询下一层级
        resList.addAll(doNestedQueryChildren(valid, ids, newParentIds, querier));
        return resList;
    }
}
