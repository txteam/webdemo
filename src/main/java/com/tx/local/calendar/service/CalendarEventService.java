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

import com.tx.local.calendar.dao.CalendarEventDao;
import com.tx.local.calendar.model.CalendarEvent;
import com.tx.core.exceptions.util.AssertUtils;
import com.tx.core.paged.model.PagedList;
import com.tx.core.querier.model.Querier;
import com.tx.core.querier.model.QuerierBuilder;

/**
 * 日程的业务层[CalendarEventService]
 * <功能详细描述>
 * 
 * @author  
 * @version [版本号]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Component("calendarEventService")
public class CalendarEventService {
    
    @SuppressWarnings("unused")
    private Logger logger = LoggerFactory.getLogger(CalendarEventService.class);
    
    @Resource(name = "calendarEventDao")
    private CalendarEventDao calendarEventDao;
    
    /**
     * 新增日程实例<br/>
     * 将calendarEvent插入数据库中保存
     * 1、如果calendarEvent 为空时抛出参数为空异常
     * 2、如果calendarEvent 中部分必要参数为非法值时抛出参数不合法异常
     * 
     * @param calendarEvent [参数说明]
     * @return void [返回类型说明]
     * @exception throws
     * @see [类、类#方法、类#成员]
     */
    @Transactional
    public void insert(CalendarEvent calendarEvent) {
        //验证参数是否合法
        AssertUtils.notNull(calendarEvent, "calendarEvent is null.");
        AssertUtils.notEmpty(calendarEvent.getVcid(),
                "calendarEvent.vcid is empty.");
        AssertUtils.notEmpty(calendarEvent.getType(),
                "calendarEvent.type is empty.");
        
        AssertUtils.notEmpty(calendarEvent.getTopicType(),
                "calendarEvent.topicType is empty.");
        AssertUtils.notEmpty(calendarEvent.getTitle(),
                "calendarEvent.title is empty.");
        AssertUtils.notEmpty(calendarEvent.getCreator(),
                "calendarEvent.creator is empty.");
        
        //为添加的数据需要填入默认值的字段填入默认值
        calendarEvent.setLastUpdateDate(new Date());
        calendarEvent.setCreateDate(new Date());
        
        //调用数据持久层对实例进行持久化操作
        this.calendarEventDao.insert(calendarEvent);
    }
    
    /**
     * 根据id删除日程实例
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
        
        CalendarEvent condition = new CalendarEvent();
        condition.setId(id);
        
        int resInt = this.calendarEventDao.delete(condition);
        boolean flag = resInt > 0;
        return flag;
    }
    
    /**
     * 根据id删除日程实例
     * 1、如果入参数为空，则抛出异常
     * 2、执行删除后，将返回数据库中被影响的条数 > 0，则返回true
     *
     * @param id
     * @return boolean 删除的条数>0则为true [返回类型说明]
     * @exception throws 
     * @see [类、类#方法、类#成员]
     */
    @Transactional
    public boolean delete(CalendarEvent condition) {
        AssertUtils.notNull(condition, "condition is null.");
        AssertUtils.notEmpty(condition.getId(), "condition.id is empty.");
        
        int resInt = this.calendarEventDao.delete(condition);
        boolean flag = resInt > 0;
        return flag;
    }
    
    /**
     * 根据id查询日程实例
     * 1、当id为empty时抛出异常
     *
     * @param id
     * @return CalendarEvent [返回类型说明]
     * @exception throws
     * @see [类、类#方法、类#成员]
     */
    public CalendarEvent findById(String id) {
        AssertUtils.notEmpty(id, "id is empty.");
        
        CalendarEvent condition = new CalendarEvent();
        condition.setId(id);
        
        CalendarEvent res = this.calendarEventDao.find(condition);
        return res;
    }
    
    /**
     * 根据id查询日程实例
     * 1、当id为empty时抛出异常
     *
     * @param id
     * @return CalendarEvent [返回类型说明]
     * @exception throws
     * @see [类、类#方法、类#成员]
     */
    public CalendarEvent find(CalendarEvent condition) {
        AssertUtils.notNull(condition, "condition is null.");
        AssertUtils.notEmpty(condition.getId(), "condition.id is empty.");
        
        CalendarEvent res = this.calendarEventDao.find(condition);
        return res;
    }
    
    /**
     * 查询日程实例列表
     * <功能详细描述>
     * @param params      
     * @return [参数说明]
     * 
     * @return List<CalendarEvent> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public List<CalendarEvent> queryList(Map<String, Object> params) {
        //生成查询条件
        params = params == null ? new HashMap<String, Object>() : params;
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        List<CalendarEvent> resList = this.calendarEventDao.queryList(params);
        
        return resList;
    }
    
    /**
     * 查询日程实例列表
     * <功能详细描述>
     * @param querier      
     * @return [参数说明]
     * 
     * @return List<CalendarEvent> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public List<CalendarEvent> queryList(Querier querier) {
        //判断条件合法性
        
        //生成查询条件
        querier = querier == null ? QuerierBuilder.newInstance().querier()
                : querier;
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        List<CalendarEvent> resList = this.calendarEventDao.queryList(querier);
        
        return resList;
    }
    
    /**
     * 分页查询日程实例列表
     * <功能详细描述>
     * @param params    
     * @param pageIndex 当前页index从1开始计算
     * @param pageSize 每页显示行数
     * 
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<CalendarEvent> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public PagedList<CalendarEvent> queryPagedList(Map<String, Object> params,
            int pageIndex, int pageSize) {
        //T判断条件合法性
        
        //生成查询条件
        params = params == null ? new HashMap<String, Object>() : params;
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        PagedList<CalendarEvent> resPagedList = this.calendarEventDao
                .queryPagedList(params, pageIndex, pageSize);
        
        return resPagedList;
    }
    
    /**
     * 分页查询日程实例列表
     * <功能详细描述>
     * @param querier    
     * @param pageIndex 当前页index从1开始计算
     * @param pageSize 每页显示行数
     * 
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<CalendarEvent> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public PagedList<CalendarEvent> queryPagedList(Querier querier,
            int pageIndex, int pageSize) {
        //T判断条件合法性
        
        //生成查询条件
        querier = querier == null ? QuerierBuilder.newInstance().querier()
                : querier;
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        PagedList<CalendarEvent> resPagedList = this.calendarEventDao
                .queryPagedList(querier, pageIndex, pageSize);
        
        return resPagedList;
    }
    
    /**
     * 查询日程实例数量<br/>
     * <功能详细描述>
     * @param params      
     * @return [参数说明]
     * 
     * @return List<CalendarEvent> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public int count(Map<String, Object> params) {
        //判断条件合法性
        
        //生成查询条件
        params = params == null ? new HashMap<String, Object>() : params;
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        int res = this.calendarEventDao.count(params);
        
        return res;
    }
    
    /**
     * 查询日程实例数量<br/>
     * <功能详细描述>
     * @param querier      
     * @return [参数说明]
     * 
     * @return List<CalendarEvent> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public int count(Querier querier) {
        //判断条件合法性
        
        //生成查询条件
        querier = querier == null ? QuerierBuilder.newInstance().querier()
                : querier;
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        int res = this.calendarEventDao.count(querier);
        
        return res;
    }
    
    /**
     * 判断日程实例是否已经存在<br/>
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
        int res = this.calendarEventDao.count(params, excludeId);
        
        return res > 0;
    }
    
    /**
     * 判断日程实例是否已经存在<br/>
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
        int res = this.calendarEventDao.count(querier, excludeId);
        
        return res > 0;
    }
    
    /**
     * 根据id更新日程实例<br/>
     * <功能详细描述>
     * @param calendarEvent
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Transactional
    public boolean updateById(String id, CalendarEvent calendarEvent) {
        //验证参数是否合法，必填字段是否填写
        AssertUtils.notNull(calendarEvent, "calendarEvent is null.");
        AssertUtils.notEmpty(id, "id is empty.");
        
        AssertUtils.notEmpty(calendarEvent.getVcid(),
                "calendarEvent.vcid is empty.");
        AssertUtils.notEmpty(calendarEvent.getType(),
                "calendarEvent.type is empty.");
        AssertUtils.notEmpty(calendarEvent.getTitle(),
                "calendarEvent.title is empty.");
        AssertUtils.notEmpty(calendarEvent.getTopicType(),
                "calendarEvent.topicType is empty.");
        
        //生成需要更新字段的hashMap
        Map<String, Object> updateRowMap = new HashMap<String, Object>();
        //更新依据条件
        updateRowMap.put("vcid", calendarEvent.getVcid());
        updateRowMap.put("type", calendarEvent.getType());
        updateRowMap.put("topicId", calendarEvent.getTopicId());
        updateRowMap.put("topicType", calendarEvent.getTopicType());
        
        //需要更新的字段
        updateRowMap.put("repeated", calendarEvent.isRepeated());
        updateRowMap.put("title", calendarEvent.getTitle());
        updateRowMap.put("url", calendarEvent.getUrl());
        updateRowMap.put("allDay", calendarEvent.isAllDay());
        updateRowMap.put("start", calendarEvent.getStart());
        updateRowMap.put("end", calendarEvent.getEnd());
        updateRowMap.put("daysOfWeek", calendarEvent.getDaysOfWeek());
        updateRowMap.put("overlap", calendarEvent.isOverlap());
        updateRowMap.put("editable", calendarEvent.isEditable());
        updateRowMap.put("rrule", calendarEvent.getRrule());
        
        updateRowMap.put("catalog", calendarEvent.getCatalog());
        updateRowMap.put("attributes", calendarEvent.getAttributes());
        updateRowMap.put("className", calendarEvent.getClassName());
        updateRowMap.put("remark", calendarEvent.getRemark());
        
        updateRowMap.put("lastUpdateUserId",
                calendarEvent.getLastUpdateUserId());
        updateRowMap.put("lastUpdateDate", new Date());
        
        boolean flag = this.calendarEventDao.update(id, updateRowMap);
        //如果需要大于1时，抛出异常并回滚，需要在这里修改
        return flag;
    }
    
    /**
     * 根据id更新日程实例<br/>
     * <功能详细描述>
     * @param calendarEvent
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Transactional
    public boolean updateById(CalendarEvent calendarEvent) {
        //验证参数是否合法，必填字段是否填写
        AssertUtils.notNull(calendarEvent, "calendarEvent is null.");
        AssertUtils.notEmpty(calendarEvent.getId(),
                "calendarEvent.id is empty.");
        
        boolean flag = updateById(calendarEvent.getId(), calendarEvent);
        //如果需要大于1时，抛出异常并回滚，需要在这里修改
        return flag;
    }
}
