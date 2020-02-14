/*
 * 描       述:  <描述>
 * 修  改 人:  
 * 修改时间:
 * <修改描述:>
 */
package com.tx.local.calendar.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tx.local.calendar.CalendarEventRepeatRuleUtils;
import com.tx.local.calendar.model.CalendarEvent;
import com.tx.local.calendar.service.CalendarEventService;
import com.tx.core.paged.model.PagedList;

import com.tx.local.calendar.model.CalendarEventTypeEnum;
import com.tx.local.calendar.model.CalendarEventTopicTypeEnum;

/**
 * 日程控制层<br/>
 * 
 * @author []
 * @version [版本号]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Controller
@RequestMapping("/calendarEvent")
public class CalendarEventController {
    
    //日程业务层
    @Resource(name = "calendarEventService")
    private CalendarEventService calendarEventService;
    
    /**
     * 跳转到查询日程分页列表页面<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/toQueryPagedList")
    public String toQueryPagedList(ModelMap response) {
        response.put("types", CalendarEventTypeEnum.values());
        response.put("topicTypes", CalendarEventTopicTypeEnum.values());
        
        return "calendar/queryCalendarEventPagedList";
    }
    
    /**
     * 跳转到新增日程页面<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/toAdd")
    public String toAdd(ModelMap response) {
        response.put("calendarEvent", new CalendarEvent());
        
        response.put("types", CalendarEventTypeEnum.values());
        response.put("topicTypes", CalendarEventTopicTypeEnum.values());
        
        return "calendar/addCalendarEvent";
    }
    
    /**
     * 跳转到编辑日程页面
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/toUpdate")
    public String toUpdate(@RequestParam("id") String id, ModelMap response) {
        CalendarEvent calendarEvent = this.calendarEventService.findById(id);
        if (calendarEvent.isRepeated()) {
            calendarEvent.setRepeatRule(CalendarEventRepeatRuleUtils
                    .toObject(calendarEvent.getRrule()));
        }
        response.put("calendarEvent", calendarEvent);
        
        response.put("types", CalendarEventTypeEnum.values());
        response.put("topicTypes", CalendarEventTopicTypeEnum.values());
        
        return "calendar/updateCalendarEvent";
    }
    
    /**
     * 查询日程实例列表<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<CalendarEvent> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/queryList")
    public List<CalendarEvent> queryList(
            @RequestParam MultiValueMap<String, String> request) {
        Map<String, Object> params = new HashMap<>();
        params.put("name", request.getFirst("name"));
        
        List<CalendarEvent> resList = this.calendarEventService
                .queryList(params);
        
        return resList;
    }
    
    /**
     * 查询日程实例分页列表<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<CalendarEvent> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/queryPagedList")
    public PagedList<CalendarEvent> queryPagedList(
            @RequestParam(value = "pageNumber", required = false, defaultValue = "1") int pageIndex,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
            @RequestParam MultiValueMap<String, String> request) {
        Map<String, Object> params = new HashMap<>();
        params.put("name", request.getFirst("name"));
        
        PagedList<CalendarEvent> resPagedList = this.calendarEventService
                .queryPagedList(params, pageIndex, pageSize);
        return resPagedList;
    }
    
    /**
     * 新增日程实例
     * <功能详细描述>
     * @param calendarEvent [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/add")
    public boolean add(CalendarEvent calendarEvent) {
        this.calendarEventService.insert(calendarEvent);
        return true;
    }
    
    /**
     * 更新日程实例<br/>
     * <功能详细描述>
     * @param calendarEvent
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/update")
    public boolean update(CalendarEvent calendarEvent) {
        boolean flag = this.calendarEventService.updateById(calendarEvent);
        return flag;
    }
    
    /**
     * 根据主键查询日程实例<br/> 
     * <功能详细描述>
     * @param id
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/findById")
    public CalendarEvent findById(@RequestParam(value = "id") String id) {
        CalendarEvent calendarEvent = this.calendarEventService.findById(id);
        return calendarEvent;
    }
    
    /**
     * 删除日程实例<br/> 
     * <功能详细描述>
     * @param id
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/deleteById")
    public boolean deleteById(@RequestParam(value = "id") String id) {
        boolean flag = this.calendarEventService.deleteById(id);
        return flag;
    }
    
    /**
     * 校验是否重复<br/>
     * @param excludeId
     * @param params
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/validate")
    public Map<String, String> validate(
            @RequestParam(value = "id", required = false) String excludeId,
            @RequestParam Map<String, String> params) {
        params.remove("id");
        boolean flag = this.calendarEventService.exists(params, excludeId);
        
        Map<String, String> resMap = new HashMap<String, String>();
        if (!flag) {
            resMap.put("ok", "");
        } else {
            resMap.put("error", "重复值");
        }
        return resMap;
    }
    
}