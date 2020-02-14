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

import com.tx.local.message.dao.DialogMessageDao;
import com.tx.local.message.model.DialogMessage;
import com.tx.core.exceptions.util.AssertUtils;
import com.tx.core.paged.model.PagedList;
import com.tx.core.querier.model.Filter;
import com.tx.core.querier.model.Querier;
import com.tx.core.querier.model.QuerierBuilder;

/**
 * 会话消息的业务层[DialogMessageService]
 * <功能详细描述>
 * 
 * @author  
 * @version [版本号]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Component("dialogMessageService")
public class DialogMessageService {
    
    @SuppressWarnings("unused")
    private Logger logger = LoggerFactory.getLogger(DialogMessageService.class);
    
    @Resource(name = "dialogMessageDao")
    private DialogMessageDao dialogMessageDao;
    
    /**
     * 新增会话消息实例<br/>
     * 将dialogMessage插入数据库中保存
     * 1、如果dialogMessage 为空时抛出参数为空异常
     * 2、如果dialogMessage 中部分必要参数为非法值时抛出参数不合法异常
     * 
     * @param dialogMessage [参数说明]
     * @return void [返回类型说明]
     * @exception throws
     * @see [类、类#方法、类#成员]
     */
    @Transactional
    public void insert(DialogMessage dialogMessage) {
        //验证参数是否合法
        AssertUtils.notNull(dialogMessage, "dialogMessage is null.");
		AssertUtils.notEmpty(dialogMessage.getUserType(), "dialogMessage.userType is empty.");
		AssertUtils.notEmpty(dialogMessage.getVcid(), "dialogMessage.vcid is empty.");
		AssertUtils.notEmpty(dialogMessage.getTitle(), "dialogMessage.title is empty.");
		AssertUtils.notEmpty(dialogMessage.getTopicType(), "dialogMessage.topicType is empty.");
           
        //FIXME:为添加的数据需要填入默认值的字段填入默认值
		dialogMessage.setLastUpdateDate(new Date());
		dialogMessage.setCreateDate(new Date());
        
        //调用数据持久层对实例进行持久化操作
        this.dialogMessageDao.insert(dialogMessage);
    }
    
    /**
     * 根据id删除会话消息实例
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
        
        DialogMessage condition = new DialogMessage();
        condition.setId(id);
        
        int resInt = this.dialogMessageDao.delete(condition);
        boolean flag = resInt > 0;
        return flag;
    }
    
    /**
     * 根据id查询会话消息实例
     * 1、当id为empty时抛出异常
     *
     * @param id
     * @return DialogMessage [返回类型说明]
     * @exception throws
     * @see [类、类#方法、类#成员]
     */
    public DialogMessage findById(String id) {
        AssertUtils.notEmpty(id, "id is empty.");
        
        DialogMessage condition = new DialogMessage();
        condition.setId(id);
        
        DialogMessage res = this.dialogMessageDao.find(condition);
        return res;
    }
    
    /**
     * 查询会话消息实例列表
     * <功能详细描述>
     * @param params      
     * @return [参数说明]
     * 
     * @return List<DialogMessage> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public List<DialogMessage> queryList(
		Map<String,Object> params   
    	) {
        //判断条件合法性
        
        //生成查询条件
        params = params == null ? new HashMap<String, Object>() : params;

        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        List<DialogMessage> resList = this.dialogMessageDao.queryList(params);
        
        return resList;
    }
    
    /**
     * 查询会话消息实例列表
     * <功能详细描述>
     * @param querier      
     * @return [参数说明]
     * 
     * @return List<DialogMessage> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public List<DialogMessage> queryList(
		Querier querier   
    	) {
        //判断条件合法性
        
        //生成查询条件
        querier = querier == null ? QuerierBuilder.newInstance().querier()
                : querier;

        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        List<DialogMessage> resList = this.dialogMessageDao.queryList(querier);
        
        return resList;
    }
    
    /**
     * 分页查询会话消息实例列表
     * <功能详细描述>
     * @param params    
     * @param pageIndex 当前页index从1开始计算
     * @param pageSize 每页显示行数
     * 
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<DialogMessage> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public PagedList<DialogMessage> queryPagedList(
		Map<String,Object> params,
    	int pageIndex,
        int pageSize) {
        //T判断条件合法性
        
        //生成查询条件
        params = params == null ? new HashMap<String, Object>() : params;
 
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        PagedList<DialogMessage> resPagedList = this.dialogMessageDao.queryPagedList(params, pageIndex, pageSize);
        
        return resPagedList;
    }
    
	/**
     * 分页查询会话消息实例列表
     * <功能详细描述>
     * @param querier    
     * @param pageIndex 当前页index从1开始计算
     * @param pageSize 每页显示行数
     * 
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<DialogMessage> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public PagedList<DialogMessage> queryPagedList(
		Querier querier,
    	int pageIndex,
        int pageSize) {
        //T判断条件合法性
        
        //生成查询条件
        querier = querier == null ? QuerierBuilder.newInstance().querier()
                : querier;
 
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        PagedList<DialogMessage> resPagedList = this.dialogMessageDao.queryPagedList(querier, pageIndex, pageSize);
        
        return resPagedList;
    }
    
    /**
     * 查询会话消息实例数量<br/>
     * <功能详细描述>
     * @param params      
     * @return [参数说明]
     * 
     * @return List<DialogMessage> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public int count(
		Map<String,Object> params   
    	) {
        //判断条件合法性
        
        //生成查询条件
        params = params == null ? new HashMap<String, Object>() : params;

        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        int res = this.dialogMessageDao.count(params);
        
        return res;
    }
    
    /**
     * 查询会话消息实例数量<br/>
     * <功能详细描述>
     * @param querier      
     * @return [参数说明]
     * 
     * @return List<DialogMessage> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public int count(
		Querier querier   
    	) {
        //判断条件合法性
        
        //生成查询条件
        querier = querier == null ? QuerierBuilder.newInstance().querier()
                : querier;

        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        int res = this.dialogMessageDao.count(querier);
        
        return res;
    }
    
    /**
     * 判断会话消息实例是否已经存在<br/>
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
        int res = this.dialogMessageDao.count(params,excludeId);
        
        return res > 0;
    }
    
    /**
     * 判断会话消息实例是否已经存在<br/>
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
        int res = this.dialogMessageDao.count(querier,excludeId);
        
        return res > 0;
    }
    
    /**
     * 根据id更新会话消息实例<br/>
     * <功能详细描述>
     * @param dialogMessage
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Transactional
    public boolean updateById(String id,DialogMessage dialogMessage) {
        //验证参数是否合法，必填字段是否填写
        AssertUtils.notNull(dialogMessage, "dialogMessage is null.");
        AssertUtils.notEmpty(id, "id is empty.");
		AssertUtils.notEmpty(dialogMessage.getUserType(), "dialogMessage.userType is empty.");
		AssertUtils.notEmpty(dialogMessage.getVcid(), "dialogMessage.vcid is empty.");
		AssertUtils.notEmpty(dialogMessage.getTitle(), "dialogMessage.title is empty.");
		AssertUtils.notEmpty(dialogMessage.getTopicType(), "dialogMessage.topicType is empty.");

        //生成需要更新字段的hashMap
        Map<String, Object> updateRowMap = new HashMap<String, Object>();
        //FIXME:需要更新的字段
		updateRowMap.put("lastUpdateUserId", dialogMessage.getLastUpdateUserId());
		updateRowMap.put("userId", dialogMessage.getUserId());
		updateRowMap.put("userType", dialogMessage.getUserType());
		updateRowMap.put("vcid", dialogMessage.getVcid());
		updateRowMap.put("title", dialogMessage.getTitle());
		updateRowMap.put("topicId", dialogMessage.getTopicId());
		updateRowMap.put("topicType", dialogMessage.getTopicType());
		updateRowMap.put("parentId", dialogMessage.getParentId());
		updateRowMap.put("catalog", dialogMessage.getCatalog());
		updateRowMap.put("content", dialogMessage.getContent());
		updateRowMap.put("lastUpdateDate", new Date());

        boolean flag = this.dialogMessageDao.update(id,updateRowMap); 
        //如果需要大于1时，抛出异常并回滚，需要在这里修改
        return flag;
    }
    
    /**
     * 根据id更新会话消息实例<br/>
     * <功能详细描述>
     * @param dialogMessage
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Transactional
    public boolean updateById(DialogMessage dialogMessage) {
        //验证参数是否合法，必填字段是否填写
        AssertUtils.notNull(dialogMessage, "dialogMessage is null.");
        AssertUtils.notEmpty(dialogMessage.getId(), "dialogMessage.id is empty.");

        boolean flag = updateById(dialogMessage.getId(),dialogMessage); 
        //如果需要大于1时，抛出异常并回滚，需要在这里修改
        return flag;
    }

    /**
     * 根据parentId查询会话消息子级实例列表<br/>
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
    public List<DialogMessage> queryChildrenByParentId(String parentId,
			Map<String,Object> params) {
        //判断条件合法性
        AssertUtils.notEmpty(parentId,"parentId is empty.");
        
        //生成查询条件
        params = params == null ? new HashMap<String, Object>() : params;
        params.put("parentId", parentId);

        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        List<DialogMessage> resList = this.dialogMessageDao.queryList(params);
        
        return resList;
    }
    
    /**
     * 根据parentId查询会话消息子级实例列表<br/>
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
    public List<DialogMessage> queryChildrenByParentId(String parentId,
			Querier querier) {
        //判断条件合法性
        AssertUtils.notEmpty(parentId,"parentId is empty.");
        
        //生成查询条件
        querier = querier == null ? QuerierBuilder.newInstance().querier()
                : querier;
		querier.getFilters().add(Filter.eq("parentId", parentId));

        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        List<DialogMessage> resList = this.dialogMessageDao.queryList(querier);
        
        return resList;
    }
    
    /**
     * 根据parentId查询会话消息子、孙级实例列表<br/>
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
    public List<DialogMessage> queryDescendantsByParentId(String parentId,
            Map<String, Object> params) {
        //判断条件合法性
        AssertUtils.notEmpty(parentId,"parentId is empty.");
        
        //生成查询条件
        params = params == null ? new HashMap<String, Object>() : params;
        Set<String> ids = new HashSet<>();
        Set<String> parentIds = new HashSet<>();
        parentIds.add(parentId);
        
        List<DialogMessage> resList = doNestedQueryChildren(ids, parentIds, params);
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
     * @return List<DialogMessage> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    private List<DialogMessage> doNestedQueryChildren(
    		Set<String> ids,Set<String> parentIds,Map<String, Object> params) {
        if (CollectionUtils.isEmpty(parentIds)) {
            return new ArrayList<DialogMessage>();
        }
        
        //ids避免数据出错时导致无限循环
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.putAll(params);
        queryParams.put("parentIds", parentIds);
        List<DialogMessage> resList = queryList( queryParams);
        
        Set<String> newParentIds = new HashSet<>();
        for (DialogMessage bdTemp : resList) {
            if (!ids.contains(bdTemp.getId())) {
                newParentIds.add(bdTemp.getId());
            }
            ids.add(bdTemp.getId());
        }
        //嵌套查询下一层级
        resList.addAll(doNestedQueryChildren( ids, newParentIds, params));
        return resList;
    }
    
    /**
     * 根据parentId查询会话消息子、孙级实例列表<br/>
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
    public List<DialogMessage> queryDescendantsByParentId(String parentId,
            Querier querier) {
        //判断条件合法性
        AssertUtils.notEmpty(parentId,"parentId is empty.");
        
        //生成查询条件
        querier = querier == null ? QuerierBuilder.newInstance().querier()
                : querier;
        Set<String> ids = new HashSet<>();
        Set<String> parentIds = new HashSet<>();
        parentIds.add(parentId);
        
        List<DialogMessage> resList = doNestedQueryChildren(ids, parentIds, querier);
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
     * @return List<DialogMessage> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    private List<DialogMessage> doNestedQueryChildren(
    		Set<String> ids,
    		Set<String> parentIds,
    		Querier querier) {
        if (CollectionUtils.isEmpty(parentIds)) {
            return new ArrayList<DialogMessage>();
        }
        
        //ids避免数据出错时导致无限循环
        Querier querierClone = (Querier)querier.clone();
        querierClone.getFilters().add(Filter.in("parentId", parentIds));
        List<DialogMessage> resList = queryList(querierClone);
        
        Set<String> newParentIds = new HashSet<>();
        for (DialogMessage bdTemp : resList) {
            if (!ids.contains(bdTemp.getId())) {
                newParentIds.add(bdTemp.getId());
            }
            ids.add(bdTemp.getId());
        }
        //嵌套查询下一层级
        resList.addAll(doNestedQueryChildren( ids, newParentIds, querier));
        return resList;
    }
}
