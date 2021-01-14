/*
 * 描       述:  <描述>
 * 修  改 人:  
 * 修改时间:
 * <修改描述:>
 */
package com.tx.local.calendar.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tx.core.paged.model.PagedList;
import com.tx.core.querier.model.Querier;
import com.tx.local.calendar.facade.CalendarEventFacade;
import com.tx.local.calendar.model.CalendarEvent;
import com.tx.local.calendar.service.CalendarEventService;

import io.swagger.annotations.Api;

/**
 * 日程API控制层[CalendarEventAPIController]<br/>
 * 
 * @author []
 * @version [版本号]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@RestController
@Api(tags = "日程API")
@RequestMapping("/api/calendarEvent")
public class CalendarEventAPIController implements CalendarEventFacade {
    
    //日程业务层
    @Resource(name = "calendarEventService")
    private CalendarEventService calendarEventService;
    
    /**
     * 新增日程<br/>
     * <功能详细描述>
     * @param calendarEvent [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public CalendarEvent insert(@RequestBody CalendarEvent calendarEvent) {
        this.calendarEventService.insert(calendarEvent);
        return calendarEvent;
    }
    
    /**
     * 根据id删除日程<br/> 
     * <功能详细描述>
     * @param id
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public boolean deleteById(
            @PathVariable(value = "id", required = true) String id) {
        boolean flag = this.calendarEventService.deleteById(id);
        return flag;
    }
    
    /**
     * 更新日程<br/>
     * <功能详细描述>
     * @param calendarEvent
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public boolean updateById(
            @PathVariable(value = "id", required = true) String id,
            @RequestBody CalendarEvent calendarEvent) {
        boolean flag = this.calendarEventService.updateById(id, calendarEvent);
        return flag;
    }
    
    /**
     * 根据主键查询日程<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return CalendarEvent [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public CalendarEvent findById(
            @PathVariable(value = "id", required = true) String id) {
        CalendarEvent res = this.calendarEventService.findById(id);
        
        return res;
    }
    
    /**
     * 查询日程实例列表<br/>
     * <功能详细描述>
     * @param querier
     * @return [参数说明]
     * 
     * @return List<CalendarEvent> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public List<CalendarEvent> queryList(@RequestBody Querier querier) {
        List<CalendarEvent> resList = this.calendarEventService
                .queryList(querier);
        
        return resList;
    }
    
    /**
     * 查询日程分页列表<br/>
     * <功能详细描述>
     * @param pageIndex
     * @param pageSize
     * @param querier
     * @return [参数说明]
     * 
     * @return PagedList<CalendarEvent> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public PagedList<CalendarEvent> queryPagedList(@RequestBody Querier querier,
            @PathVariable(value = "pageNumber", required = true) int pageIndex,
            @PathVariable(value = "pageSize", required = true) int pageSize) {
        PagedList<CalendarEvent> resPagedList = this.calendarEventService
                .queryPagedList(querier, pageIndex, pageSize);
        return resPagedList;
    }
    
    /**
     * 查询日程数量<br/>
     * <功能详细描述>
     * @param querier
     * @return [参数说明]
     * 
     * @return int [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public int count(@RequestBody Querier querier) {
        int count = this.calendarEventService.count(querier);
        
        return count;
    }
    
    /**
     * 查询日程是否存在<br/>
     * @param excludeId
     * @param querier
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public boolean exists(@RequestBody Querier querier,
            @RequestParam(value = "excludeId", required = false) String excludeId) {
        boolean flag = this.calendarEventService.exists(querier, excludeId);
        
        return flag;
    }
}