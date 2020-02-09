/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2020年1月13日
 * <修改描述:>
 */
package com.tx.local.calendar.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tx.core.querier.model.Querier;
import com.tx.core.querier.model.QuerierBuilder;
import com.tx.local.calendar.model.CalendarEvent;
import com.tx.local.calendar.model.CalendarEventRRule;
import com.tx.local.calendar.model.CalendarEventTopicTypeEnum;
import com.tx.local.calendar.model.CalendarEventTypeEnum;
import com.tx.local.calendar.service.CalendarEventService;
import com.tx.local.security.util.WebContextUtils;

/**
 * 行事历<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2020年1月13日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@PreAuthorize("hasRole('ROLE_OPERATOR')")
@Controller
@RequestMapping("/operator/calendar")
public class OperatorCalendarController {
    
    /** 行事历事件业务层 */
    @Resource
    private CalendarEventService calendarEventService;
    
    /**
     * 操作人员行事历行事历<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = { "", "/" })
    public String index(@RequestParam Map<String, String> request,
            ModelMap response) {
        return "/calendar/operatorCalendarMain";
    }
    
    /**
     * 操作人员行事历行事历<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/toQueryPagedList")
    public String toQueryPagedList(@RequestParam Map<String, String> request,
            ModelMap response) {
        return "/calendar/queryOperatorCalendarPagedList";
    }
    
    /**
     * 操作人员行事历行事历<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/toAddEvent")
    public String toAddEvent(@RequestParam Map<String, String> request,
            ModelMap response) {
        response.put("calendarEvent", new CalendarEvent());
        return "/calendar/addOperatorCalendarEvent";
    }
    
    /**
     * 新增记事本分类<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping(value = "/addEvent")
    public boolean addEvent(CalendarEvent event, CalendarEventRRule rrule) {
        String vcid = WebContextUtils.getVcid();
        String operatorId = WebContextUtils.getOperatorId();
        event.setVcid(vcid);
        event.setType(CalendarEventTypeEnum.OPERATOR_CALENDAR_EVENT);
        event.setTopicType(CalendarEventTopicTypeEnum.OPERATOR);
        if (CalendarEventTypeEnum.OPERATOR_CALENDAR_EVENT
                .equals(event.getType())) {
            event.setTopicId(operatorId);
            if(!event.isAllDay() && rrule.getFreq() == null){
                event.setEditable(true);
            }
        }
        
        event.setCreateUserId(operatorId);
        event.setLastUpdateUserId(operatorId);
        event.setCreator(WebContextUtils.getOperatorUsername());
        
        this.calendarEventService.insert(event);
        return true;
    }
    
    /**
     * 新增记事本分类<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping(value = "/updateEvent")
    public boolean updateEvent(
            @PathVariable(name = "id", required = true) String id,
            CalendarEvent event) {
        String vcid = WebContextUtils.getVcid();
        String operatorId = WebContextUtils.getOperatorId();
        
        event.setVcid(vcid);
        event.setType(CalendarEventTypeEnum.OPERATOR_CALENDAR_EVENT);
        event.setTopicType(CalendarEventTopicTypeEnum.OPERATOR);
        event.setTopicId(operatorId);
        
        event.setCreateUserId(operatorId);
        event.setLastUpdateUserId(operatorId);
        
        this.calendarEventService.insert(event);
        return true;
    }
    
    /**
     * 新增记事本分类<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping(value = "/queryEventList")
    public List<CalendarEvent> queryEventList(
            @RequestParam(name = "start") Date start,
            @RequestParam(name = "end") Date end,
            @RequestParam Map<String, String> request) {
        String vcid = WebContextUtils.getVcid();
        String operatorId = WebContextUtils.getOperatorId();
        
        Map<String, Object> params = new HashMap<>();
        params.put("between.start", start);
        params.put("between.end", end);
        params.put("vcid", vcid);
        params.put("type", CalendarEventTypeEnum.OPERATOR_CALENDAR_EVENT);
        params.put("topicType", CalendarEventTopicTypeEnum.OPERATOR);
        params.put("topicId", operatorId);
        Querier querier = QuerierBuilder.newInstance().querier();
        querier.getParams().putAll(params);
        
        List<CalendarEvent> events = this.calendarEventService
                .queryList(querier);
        return events;
    }
    
    /**
     * 查询日程事件<br/>
     * <功能详细描述>
     * @param request
     * @return [参数说明]
     * 
     * @return List<CalendarEvent> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping(value = "/queryEventPagedList")
    public List<CalendarEvent> queryEventPagedList(
            @RequestParam Map<String, String> request) {
        String vcid = WebContextUtils.getVcid();
        String operatorId = WebContextUtils.getOperatorId();
        
        Map<String, Object> params = new HashMap<>();
        params.put("vcid", vcid);
        params.put("type", CalendarEventTypeEnum.OPERATOR_CALENDAR_EVENT);
        params.put("topicType", CalendarEventTopicTypeEnum.OPERATOR);
        params.put("topicId", operatorId);
        Querier querier = QuerierBuilder.newInstance().querier();
        querier.getParams().putAll(params);
        
        List<CalendarEvent> events = this.calendarEventService
                .queryList(querier);
        return events;
    }
}
