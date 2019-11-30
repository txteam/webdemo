/*
 * 描          述:  <描述>
 * 修  改   人:  
 * 修改时间:  
 * <修改描述:>
 */
package com.tx.local.content.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.tx.local.content.dao.ContentInfoDao;
import com.tx.local.content.model.ContentInfo;
import com.tx.core.exceptions.util.AssertUtils;
import com.tx.core.paged.model.PagedList;
import com.tx.core.querier.model.Filter;
import com.tx.core.querier.model.Querier;
import com.tx.core.querier.model.QuerierBuilder;

/**
 * 内容信息的业务层[ContentInfoService]
 * <功能详细描述>
 * 
 * @author  
 * @version [版本号]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Component("contentInfoService")
public class ContentInfoService {
    
    @SuppressWarnings("unused")
    private Logger logger = LoggerFactory.getLogger(ContentInfoService.class);
    
    @Resource(name = "contentInfoDao")
    private ContentInfoDao contentInfoDao;
    
    /**
     * 新增内容信息实例<br/>
     * 将contentInfo插入数据库中保存
     * 1、如果contentInfo 为空时抛出参数为空异常
     * 2、如果contentInfo 中部分必要参数为非法值时抛出参数不合法异常
     * 
     * @param contentInfo [参数说明]
     * @return void [返回类型说明]
     * @exception throws
     * @see [类、类#方法、类#成员]
     */
    @Transactional
    public void insert(ContentInfo contentInfo) {
        //验证参数是否合法
        AssertUtils.notNull(contentInfo, "contentInfo is null.");
           
        //FIXME:为添加的数据需要填入默认值的字段填入默认值
		contentInfo.setLastUpdateDate(new Date());
		contentInfo.setValid(true);
		contentInfo.setCreateDate(new Date());
        
        //调用数据持久层对实例进行持久化操作
        this.contentInfoDao.insert(contentInfo);
    }
    
    /**
     * 根据id删除内容信息实例
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
        
        ContentInfo condition = new ContentInfo();
        condition.setId(id);
        
        int resInt = this.contentInfoDao.delete(condition);
        boolean flag = resInt > 0;
        return flag;
    }
    
    /**
     * 根据id查询内容信息实例
     * 1、当id为empty时抛出异常
     *
     * @param id
     * @return ContentInfo [返回类型说明]
     * @exception throws
     * @see [类、类#方法、类#成员]
     */
    public ContentInfo findById(String id) {
        AssertUtils.notEmpty(id, "id is empty.");
        
        ContentInfo condition = new ContentInfo();
        condition.setId(id);
        
        ContentInfo res = this.contentInfoDao.find(condition);
        return res;
    }
    
    /**
     * 查询内容信息实例列表
     * <功能详细描述>
     * @param valid
     * @param params      
     * @return [参数说明]
     * 
     * @return List<ContentInfo> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public List<ContentInfo> queryList(
		Boolean valid,
		Map<String,Object> params   
    	) {
        //判断条件合法性
        
        //生成查询条件
        params = params == null ? new HashMap<String, Object>() : params;
		params.put("valid",valid);

        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        List<ContentInfo> resList = this.contentInfoDao.queryList(params);
        
        return resList;
    }
    
    /**
     * 查询内容信息实例列表
     * <功能详细描述>
     * @param valid
     * @param querier      
     * @return [参数说明]
     * 
     * @return List<ContentInfo> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public List<ContentInfo> queryList(
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
        List<ContentInfo> resList = this.contentInfoDao.queryList(querier);
        
        return resList;
    }
    
    /**
     * 分页查询内容信息实例列表
     * <功能详细描述>
     * @param valid
     * @param params    
     * @param pageIndex 当前页index从1开始计算
     * @param pageSize 每页显示行数
     * 
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<ContentInfo> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public PagedList<ContentInfo> queryPagedList(
		Boolean valid,
		Map<String,Object> params,
    	int pageIndex,
        int pageSize) {
        //T判断条件合法性
        
        //生成查询条件
        params = params == null ? new HashMap<String, Object>() : params;
		params.put("valid",valid);
 
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        PagedList<ContentInfo> resPagedList = this.contentInfoDao.queryPagedList(params, pageIndex, pageSize);
        
        return resPagedList;
    }
    
	/**
     * 分页查询内容信息实例列表
     * <功能详细描述>
     * @param valid
     * @param querier    
     * @param pageIndex 当前页index从1开始计算
     * @param pageSize 每页显示行数
     * 
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<ContentInfo> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public PagedList<ContentInfo> queryPagedList(
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
        PagedList<ContentInfo> resPagedList = this.contentInfoDao.queryPagedList(querier, pageIndex, pageSize);
        
        return resPagedList;
    }
    
    /**
     * 查询内容信息实例数量<br/>
     * <功能详细描述>
     * @param valid
     * @param params      
     * @return [参数说明]
     * 
     * @return List<ContentInfo> [返回类型说明]
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
        int res = this.contentInfoDao.count(params);
        
        return res;
    }
    
    /**
     * 查询内容信息实例数量<br/>
     * <功能详细描述>
     * @param valid
     * @param querier      
     * @return [参数说明]
     * 
     * @return List<ContentInfo> [返回类型说明]
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
        int res = this.contentInfoDao.count(querier);
        
        return res;
    }
    
    /**
     * 判断内容信息实例是否已经存在<br/>
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
        int res = this.contentInfoDao.count(params, excludeId);
        
        return res > 0;
    }
    
    /**
     * 判断内容信息实例是否已经存在<br/>
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
        int res = this.contentInfoDao.count(querier,excludeId);
        
        return res > 0;
    }
    
    /**
     * 根据id更新内容信息实例<br/>
     * <功能详细描述>
     * @param contentInfo
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Transactional
    public boolean updateById(String id,ContentInfo contentInfo) {
        //验证参数是否合法，必填字段是否填写
        AssertUtils.notNull(contentInfo, "contentInfo is null.");
        AssertUtils.notEmpty(id, "id is empty.");

        //生成需要更新字段的hashMap
        Map<String, Object> updateRowMap = new HashMap<String, Object>();
        //FIXME:需要更新的字段
		updateRowMap.put("fileId", contentInfo.getFileId());
		updateRowMap.put("fileUrl", contentInfo.getFileUrl());
		updateRowMap.put("lastUpdateOperatorId", contentInfo.getLastUpdateOperatorId());
		updateRowMap.put("linkUrl", contentInfo.getLinkUrl());
		updateRowMap.put("name", contentInfo.getName());
		updateRowMap.put("type", contentInfo.getType());
		updateRowMap.put("level", contentInfo.getLevel());
		updateRowMap.put("orderIndex", contentInfo.getOrderIndex());
		updateRowMap.put("title", contentInfo.getTitle());
		updateRowMap.put("valid", contentInfo.isValid());
		updateRowMap.put("createOperatorId", contentInfo.getCreateOperatorId());
		updateRowMap.put("remark", contentInfo.getRemark());
		updateRowMap.put("content", contentInfo.getContent());
		updateRowMap.put("category", contentInfo.getCategory());
		updateRowMap.put("keywords", contentInfo.getKeywords());
		updateRowMap.put("lastUpdateDate", new Date());

        boolean flag = this.contentInfoDao.update(id,updateRowMap); 
        //如果需要大于1时，抛出异常并回滚，需要在这里修改
        return flag;
    }
    
    /**
     * 根据id更新内容信息实例<br/>
     * <功能详细描述>
     * @param contentInfo
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Transactional
    public boolean updateById(ContentInfo contentInfo) {
        //验证参数是否合法，必填字段是否填写
        AssertUtils.notNull(contentInfo, "contentInfo is null.");
        AssertUtils.notEmpty(contentInfo.getId(), "contentInfo.id is empty.");

        boolean flag = updateById(contentInfo.getId(),contentInfo); 
        //如果需要大于1时，抛出异常并回滚，需要在这里修改
        return flag;
    }

    /**
     * 根据id禁用内容信息<br/>
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
        
        boolean flag = this.contentInfoDao.update(params) > 0;
        
        return flag;
    }
    
    /**
     * 根据id启用内容信息<br/>
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
        
        boolean flag = this.contentInfoDao.update(params) > 0;
        
        return flag;
    }
}
