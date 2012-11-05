/*
 * 描          述:  <描述>
 * 修  改   人:  PengQingyang
 * 修改时间:  2012-11-4
 * <修改描述:>
 */
package com.tx.webdemo.calendar.service;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.time.DateUtils;
import org.apache.cxf.common.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.tx.core.exceptions.parameter.ParameterIsEmptyException;
import com.tx.core.exceptions.parameter.ParameterIsInvalidException;
import com.tx.webdemo.calendar.dao.CalendarDao;
import com.tx.webdemo.calendar.model.CalendarEvent;

/**
 * <行事历业务层>
 * <功能详细描述>
 * 
 * @author  PengQingyang
 * @version  [版本号, 2012-11-4]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Component("calendar.calendarService")
public class CalendarService {
    
    /** 日志对象 */
    private static Logger logger = LoggerFactory.getLogger(CalendarService.class);
    
    /** 日期格式化的格式 */
    private static final String[] PARSE_FMT = { "yyyy-MM-dd HH:mm:ss",
            "yyyy-MM-dd" };
    
    /** 行事历数据访问层对象 */
    @Resource(name = "calendar.calendarDao")
    private CalendarDao calendarDao;
    
    /**
      *<根据当前操作人员id查询日历事件>
      *<功能详细描述>
      * @param operId
      * @param startDateStr
      * @param endDateStr
      * @return [参数说明]
      * 
      * @return List<CalendarEvent> [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public List<CalendarEvent> queryCalendarEventByOperIdAndDate(String operId,
            String startDateStr, String endDateStr) {
        if (StringUtils.isEmpty(operId) || StringUtils.isEmpty(startDateStr)
                || StringUtils.isEmpty(endDateStr)) {
            throw new ParameterIsEmptyException(
                    "operId or startDateStr or endDateStr is empty.");
        }
        Date startDate;
        Date endDate;
        try {
            startDate = DateUtils.parseDate(startDateStr, PARSE_FMT);
            endDate = DateUtils.parseDate(endDateStr, PARSE_FMT);
        }
        catch (ParseException e) {
            throw new ParameterIsInvalidException(
                    "startDateStr or endDateStr is invalid.");
        }
        
        Map<String, Object> queryParams = new HashMap<String, Object>();
        queryParams.put("operId", operId);
        queryParams.put("startDate", startDate);
        queryParams.put("endDate", endDate);
        List<CalendarEvent> eventList = this.calendarDao.queryCalendarEventList(queryParams);
        
        return eventList;
    }
    
}
