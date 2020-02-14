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

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tx.core.paged.model.PagedList;
import com.tx.core.querier.model.Querier;
import com.tx.core.querier.model.QuerierBuilder;
import com.tx.local.calendar.CalendarEventRepeatRuleUtils;
import com.tx.local.calendar.model.CalendarEvent;
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
@RequestMapping("/operator/public/calendar")
public class OperatorPublicCalendarController {
    
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
        return "/calendar/operatorPublicCalendarMain";
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
        return "/calendar/queryOperatorPublicCalendarEventPagedList";
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
        return "/calendar/saveOperatorPublicCalendarEvent";
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
    @RequestMapping("/toUpdateEvent")
    public String toUpdateEvent(@RequestParam("id") String id,
            ModelMap response) {
        String vcid = WebContextUtils.getVcid();
        CalendarEvent condition = new CalendarEvent();
        condition.setId(id);
        condition.setVcid(vcid);
        condition.setType(CalendarEventTypeEnum.OPERATOR_PUBLIC_CALENDAR_EVENT);
        condition.setTopicType(CalendarEventTopicTypeEnum.OPERATOR);
        CalendarEvent calendarEvent = this.calendarEventService.find(condition);
        if (calendarEvent.isRepeated()) {
            calendarEvent.setRepeatRule(CalendarEventRepeatRuleUtils
                    .toObject(calendarEvent.getRrule()));
        }
        response.put("calendarEvent", calendarEvent);
        
        return "calendar/saveOperatorPublicCalendarEvent";
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
    public boolean addEvent(CalendarEvent event) {
        String vcid = WebContextUtils.getVcid();
        String operatorId = WebContextUtils.getOperatorId();
        
        event.setEditable(false);
        event.setVcid(vcid);
        event.setType(CalendarEventTypeEnum.OPERATOR_PUBLIC_CALENDAR_EVENT);
        event.setTopicType(CalendarEventTopicTypeEnum.OPERATOR);
        if (event.isRepeated() && event.getRepeatRule().getFreq() != null) {
            //event.setEditable(false);
            event.setAllDay(false);
            event.setStart(event.getRepeatRule().getDtstart());
            event.setEnd(event.getRepeatRule().getUntil());
            event.setRrule(
                    CalendarEventRepeatRuleUtils.toJson(event.getRepeatRule()));
        } else {
            event.setRepeated(false);
            //event.setEditable(true);
        }
        event.setCreateUserId(operatorId);
        event.setLastUpdateUserId(operatorId);
        event.setCreator(WebContextUtils.getOperatorUsername());
        
        this.calendarEventService.insert(event);
        return true;
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
        String vcid = WebContextUtils.getVcid();
        CalendarEvent condition = new CalendarEvent();
        condition.setId(id);
        condition.setVcid(vcid);
        condition.setType(CalendarEventTypeEnum.OPERATOR_PUBLIC_CALENDAR_EVENT);
        condition.setTopicType(CalendarEventTopicTypeEnum.OPERATOR);
        boolean flag = this.calendarEventService.delete(condition);
        return flag;
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
    public boolean updateEvent(@RequestParam(value = "id") String id,
            CalendarEvent event) {
        String vcid = WebContextUtils.getVcid();
        String operatorId = WebContextUtils.getOperatorId();
        
        event.setEditable(false);
        event.setVcid(vcid);
        event.setType(CalendarEventTypeEnum.OPERATOR_PUBLIC_CALENDAR_EVENT);
        event.setTopicType(CalendarEventTopicTypeEnum.OPERATOR);
        if (event.isRepeated() && event.getRepeatRule().getFreq() != null) {
            event.setRepeated(true);
            //event.setEditable(false);
            event.setAllDay(false);
            event.setStart(event.getRepeatRule().getDtstart());
            event.setEnd(event.getRepeatRule().getUntil());
            event.setRrule(
                    CalendarEventRepeatRuleUtils.toJson(event.getRepeatRule()));
        } else {
            event.setRepeated(false);
            //event.setEditable(true);
            event.setRrule(null);
        }
        event.setCreateUserId(operatorId);
        event.setLastUpdateUserId(operatorId);
        
        this.calendarEventService.updateById(id, event);
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
        
        Map<String, Object> params = new HashMap<>();
        //System.out.println(DateFormatUtils.format(start, "yyyy-MM-dd hh:mm:ss"));
        //System.out.println(DateFormatUtils.format(end, "yyyy-MM-dd hh:mm:ss"));
        params.put("notMinStart", end);
        params.put("notMaxEnd", start);
        params.put("vcid", vcid);
        params.put("type",
                CalendarEventTypeEnum.OPERATOR_PUBLIC_CALENDAR_EVENT);
        params.put("topicType", CalendarEventTopicTypeEnum.OPERATOR);
        params.put("repeated", BooleanUtils.toBooleanObject(request.get("repeated")));
        Querier querier = QuerierBuilder.newInstance().querier();
        querier.getParams().putAll(params);
        
        List<CalendarEvent> events = this.calendarEventService
                .queryList(querier);
        events.stream().forEach(e -> {
            if (e.isRepeated() && !StringUtils.isEmpty(e.getRrule())) {
                e.setRepeatRule(
                        CalendarEventRepeatRuleUtils.toObject(e.getRrule()));
            }
        });
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
    public PagedList<CalendarEvent> queryEventPagedList(
            @RequestParam(name = "start", required = false) Date start,
            @RequestParam(name = "end", required = false) Date end,
            @RequestParam(value = "pageNumber", required = false, defaultValue = "1") int pageIndex,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
            @RequestParam MultiValueMap<String, String> request) {
        String vcid = WebContextUtils.getVcid();
        
        Map<String, Object> params = new HashMap<>();
        params.put("title", request.getFirst("title"));
        params.put("notMinStart", end);
        params.put("notMaxEnd", start);
        params.put("vcid", vcid);
        params.put("type",
                CalendarEventTypeEnum.OPERATOR_PUBLIC_CALENDAR_EVENT);
        params.put("topicType", CalendarEventTopicTypeEnum.OPERATOR);
        Querier querier = QuerierBuilder.newInstance().querier();
        querier.getParams().putAll(params);
        
        PagedList<CalendarEvent> events = this.calendarEventService
                .queryPagedList(querier, pageIndex, pageSize);
        return events;
    }
    
}
