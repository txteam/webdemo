/*
 * 描          述:  <描述>
 * 修  改   人:  
 * 修改时间:  
 * <修改描述:>
 */
package com.tx.local.calendar.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.tx.local.calendar.dao.CalendarEventCatalogDao;
import com.tx.local.calendar.model.CalendarEventCatalog;
import com.tx.core.exceptions.util.AssertUtils;
import com.tx.core.paged.model.PagedList;
import com.tx.core.querier.model.Filter;
import com.tx.core.querier.model.Querier;
import com.tx.core.querier.model.QuerierBuilder;

/**
 * 日程分类的业务层[CalendarEventCatalogService]
 * <功能详细描述>
 * 
 * @author  
 * @version [版本号]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Component("calendarEventCatalogService")
public class CalendarEventCatalogService {
    
    @SuppressWarnings("unused")
    private Logger logger = LoggerFactory.getLogger(CalendarEventCatalogService.class);
    
    @Resource(name = "calendarEventCatalogDao")
    private CalendarEventCatalogDao calendarEventCatalogDao;
    
    /**
     * 新增日程分类实例<br/>
     * 将calendarEventCatalog插入数据库中保存
     * 1、如果calendarEventCatalog 为空时抛出参数为空异常
     * 2、如果calendarEventCatalog 中部分必要参数为非法值时抛出参数不合法异常
     * 
     * @param calendarEventCatalog [参数说明]
     * @return void [返回类型说明]
     * @exception throws
     * @see [类、类#方法、类#成员]
     */
    @Transactional
    public void insert(CalendarEventCatalog calendarEventCatalog) {
        //验证参数是否合法
        AssertUtils.notNull(calendarEventCatalog, "calendarEventCatalog is null.");
		AssertUtils.notEmpty(calendarEventCatalog.getCode(), "calendarEventCatalog.code is empty.");
		AssertUtils.notEmpty(calendarEventCatalog.getName(), "calendarEventCatalog.name is empty.");
		AssertUtils.notEmpty(calendarEventCatalog.getType(), "calendarEventCatalog.type is empty.");
		AssertUtils.notEmpty(calendarEventCatalog.getVcid(), "calendarEventCatalog.vcid is empty.");
		AssertUtils.notEmpty(calendarEventCatalog.getTopicType(), "calendarEventCatalog.topicType is empty.");
           
        //FIXME:为添加的数据需要填入默认值的字段填入默认值
		calendarEventCatalog.setLastUpdateDate(new Date());
		calendarEventCatalog.setValid(true);
		calendarEventCatalog.setCreateDate(new Date());
        
        //调用数据持久层对实例进行持久化操作
        this.calendarEventCatalogDao.insert(calendarEventCatalog);
    }
    
    /**
     * 根据id删除日程分类实例
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
        
        CalendarEventCatalog condition = new CalendarEventCatalog();
        condition.setId(id);
        
        int resInt = this.calendarEventCatalogDao.delete(condition);
        boolean flag = resInt > 0;
        return flag;
    }

    /**
     * 根据code删除日程分类实例
     * 1、当code为empty时抛出异常
     * 2、执行删除后，将返回数据库中被影响的条数 > 0，则返回true
     *
     * @param code
     * @return CalendarEventCatalog [返回类型说明]
     * @exception throws
     * @see [类、类#方法、类#成员]
     */
    public boolean deleteByCode(String code) {
        AssertUtils.notEmpty(code, "code is empty.");
        
        CalendarEventCatalog condition = new CalendarEventCatalog();
        condition.setCode(code);
        
        int resInt = this.calendarEventCatalogDao.delete(condition);
        boolean flag = resInt > 0;
        return flag;
    }
    
    /**
     * 根据id查询日程分类实例
     * 1、当id为empty时抛出异常
     *
     * @param id
     * @return CalendarEventCatalog [返回类型说明]
     * @exception throws
     * @see [类、类#方法、类#成员]
     */
    public CalendarEventCatalog findById(String id) {
        AssertUtils.notEmpty(id, "id is empty.");
        
        CalendarEventCatalog condition = new CalendarEventCatalog();
        condition.setId(id);
        
        CalendarEventCatalog res = this.calendarEventCatalogDao.find(condition);
        return res;
    }

    /**
     * 根据code查询日程分类实例
     * 1、当code为empty时抛出异常
     *
     * @param code
     * @return CalendarEventCatalog [返回类型说明]
     * @exception throws
     * @see [类、类#方法、类#成员]
     */
    public CalendarEventCatalog findByCode(String code) {
        AssertUtils.notEmpty(code, "code is empty.");
        
        CalendarEventCatalog condition = new CalendarEventCatalog();
        condition.setCode(code);
        
        CalendarEventCatalog res = this.calendarEventCatalogDao.find(condition);
        return res;
    }
    
    /**
     * 查询日程分类实例列表
     * <功能详细描述>
     * @param valid
     * @param params      
     * @return [参数说明]
     * 
     * @return List<CalendarEventCatalog> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public List<CalendarEventCatalog> queryList(
		Boolean valid,
		Map<String,Object> params   
    	) {
        //判断条件合法性
        
        //生成查询条件
        params = params == null ? new HashMap<String, Object>() : params;
		params.put("valid",valid);

        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        List<CalendarEventCatalog> resList = this.calendarEventCatalogDao.queryList(params);
        
        return resList;
    }
    
    /**
     * 查询日程分类实例列表
     * <功能详细描述>
     * @param valid
     * @param querier      
     * @return [参数说明]
     * 
     * @return List<CalendarEventCatalog> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public List<CalendarEventCatalog> queryList(
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
        List<CalendarEventCatalog> resList = this.calendarEventCatalogDao.queryList(querier);
        
        return resList;
    }
    
    /**
     * 分页查询日程分类实例列表
     * <功能详细描述>
     * @param valid
     * @param params    
     * @param pageIndex 当前页index从1开始计算
     * @param pageSize 每页显示行数
     * 
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<CalendarEventCatalog> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public PagedList<CalendarEventCatalog> queryPagedList(
		Boolean valid,
		Map<String,Object> params,
    	int pageIndex,
        int pageSize) {
        //T判断条件合法性
        
        //生成查询条件
        params = params == null ? new HashMap<String, Object>() : params;
		params.put("valid",valid);
 
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        PagedList<CalendarEventCatalog> resPagedList = this.calendarEventCatalogDao.queryPagedList(params, pageIndex, pageSize);
        
        return resPagedList;
    }
    
	/**
     * 分页查询日程分类实例列表
     * <功能详细描述>
     * @param valid
     * @param querier    
     * @param pageIndex 当前页index从1开始计算
     * @param pageSize 每页显示行数
     * 
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<CalendarEventCatalog> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public PagedList<CalendarEventCatalog> queryPagedList(
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
        PagedList<CalendarEventCatalog> resPagedList = this.calendarEventCatalogDao.queryPagedList(querier, pageIndex, pageSize);
        
        return resPagedList;
    }
    
    /**
     * 查询日程分类实例数量<br/>
     * <功能详细描述>
     * @param valid
     * @param params      
     * @return [参数说明]
     * 
     * @return List<CalendarEventCatalog> [返回类型说明]
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
        int res = this.calendarEventCatalogDao.count(params);
        
        return res;
    }
    
    /**
     * 查询日程分类实例数量<br/>
     * <功能详细描述>
     * @param valid
     * @param querier      
     * @return [参数说明]
     * 
     * @return List<CalendarEventCatalog> [返回类型说明]
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
        int res = this.calendarEventCatalogDao.count(querier);
        
        return res;
    }
    
    /**
     * 判断日程分类实例是否已经存在<br/>
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
        int res = this.calendarEventCatalogDao.count(params,excludeId);
        
        return res > 0;
    }
    
    /**
     * 判断日程分类实例是否已经存在<br/>
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
        int res = this.calendarEventCatalogDao.count(querier,excludeId);
        
        return res > 0;
    }
    
    /**
     * 根据id更新日程分类实例<br/>
     * <功能详细描述>
     * @param calendarEventCatalog
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Transactional
    public boolean updateById(String id,CalendarEventCatalog calendarEventCatalog) {
        //验证参数是否合法，必填字段是否填写
        AssertUtils.notNull(calendarEventCatalog, "calendarEventCatalog is null.");
        AssertUtils.notEmpty(id, "id is empty.");
		AssertUtils.notEmpty(calendarEventCatalog.getName(), "calendarEventCatalog.name is empty.");
		AssertUtils.notEmpty(calendarEventCatalog.getType(), "calendarEventCatalog.type is empty.");
		AssertUtils.notEmpty(calendarEventCatalog.getVcid(), "calendarEventCatalog.vcid is empty.");
		AssertUtils.notEmpty(calendarEventCatalog.getTopicType(), "calendarEventCatalog.topicType is empty.");

        //生成需要更新字段的hashMap
        Map<String, Object> updateRowMap = new HashMap<String, Object>();
        //FIXME:需要更新的字段
		updateRowMap.put("lastUpdateUserId", calendarEventCatalog.getLastUpdateUserId());
		updateRowMap.put("name", calendarEventCatalog.getName());
		updateRowMap.put("type", calendarEventCatalog.getType());
		updateRowMap.put("vcid", calendarEventCatalog.getVcid());
		updateRowMap.put("topicType", calendarEventCatalog.getTopicType());
		updateRowMap.put("modifyAble", calendarEventCatalog.isModifyAble());
		updateRowMap.put("remark", calendarEventCatalog.getRemark());
		updateRowMap.put("lastUpdateDate", new Date());

        boolean flag = this.calendarEventCatalogDao.update(id,updateRowMap); 
        //如果需要大于1时，抛出异常并回滚，需要在这里修改
        return flag;
    }
    
    /**
     * 根据id更新日程分类实例<br/>
     * <功能详细描述>
     * @param calendarEventCatalog
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Transactional
    public boolean updateById(CalendarEventCatalog calendarEventCatalog) {
        //验证参数是否合法，必填字段是否填写
        AssertUtils.notNull(calendarEventCatalog, "calendarEventCatalog is null.");
        AssertUtils.notEmpty(calendarEventCatalog.getId(), "calendarEventCatalog.id is empty.");

        boolean flag = updateById(calendarEventCatalog.getId(),calendarEventCatalog); 
        //如果需要大于1时，抛出异常并回滚，需要在这里修改
        return flag;
    }

    /**
     * 根据id禁用日程分类<br/>
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
	        
        boolean flag = this.calendarEventCatalogDao.update(params) > 0;
        
        return flag;
    }
    
    /**
     * 根据id启用日程分类<br/>
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
	
        boolean flag = this.calendarEventCatalogDao.update(params) > 0;
        
        return flag;
    }
}
