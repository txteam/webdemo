/*
 * 描          述:  <描述>
 * 修  改   人:  PengQingyang
 * 修改时间:  2012-10-28
 * <修改描述:>
 */
package com.tx.webdemo.calendar.controller;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tx.core.exceptions.SILException;
import com.tx.webdemo.calendar.model.CalendarEvent;
import com.tx.webdemo.calendar.service.CalendarService;

/**
 * <行事历Controller>
 * 
 * @author  PengQingyang
 * @version  [版本号, 2012-10-28]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@RequestMapping("/canlendar")
@Controller("canlendar.controller")
public class CalendarController {
    
    private static final Logger logger = LoggerFactory.getLogger(CalendarController.class);
    
    /**
     * 行事历业务逻辑对象
     */
    @Resource(name = "calendar.calendarService")
    private CalendarService calendarService;
    
    /**
      *<查询日历事件列表>
      *<功能详细描述>
      * @param paraMap
      * @return [参数说明]
      * 
      * @return ModelAndView [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/queryCalendarEventList")
    @ResponseBody()
    public List<CalendarEvent> queryCalendarEventList(
            @RequestParam String startDate, @RequestParam String endDate) {
        //判断请求是否合法
        List<CalendarEvent> resList = null;
        try {
            resList = this.calendarService.queryCalendarEventByOperIdAndDate("888",
                    startDate,
                    endDate);
        } catch (SILException e) {
            e.printStackTrace();
        }
        
        //查询并返回数据
        return resList;
    }
    
}
