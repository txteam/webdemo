/*
 * 描          述:  <描述>
 * 修  改   人:  PengQingyang
 * 修改时间:  2012-11-4
 * <修改描述:>
 */
package com.tx.webdemo.calendar.service;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang.time.DateUtils;
import org.codehaus.jackson.JsonGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;

import com.tx.webdemo.calendar.dao.CalendarDAO;
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
            "yyyy-MM-dd", };
    
    /**
     * 行事历数据访问层对象
     */
    private CalendarDAO calendarDAO;
    
    /**
      *<功能简述>
      *<功能详细描述>
      * @param calEvent
      * @param operaters [参数说明]
      * 
      * @return void [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public void saveCalendarEvent(CalendarEvent calEvent, String[] operaters) {
        Map<String, Object> result = new HashMap<String, Object>();
        
        final Date currDate = new Date();
        
        if (StringUtils.isEmpty(calEvent.getId())) {
            // this is Insert
            if(StringUtils.isEmpty(calEvent.getCreateOperId())){
                calEvent.setCreateOperId(calEvent.getUpdateOperId());
            }
            calEvent.setCreateDate(currDate);
            calEvent.setUpdateDate(currDate);
            CalendarService.this.calendarDAO.insertCalendarEvent(calEvent);
        }
        else {
            //如果有id执行更新操作
            calEvent.setUpdateDate(currDate);
            CalendarService.this.calendarDAO.updateCalendarEvent(calEvent);
            CalendarService.this.calendarDAO.deleteCalendarSubscriber(calEvent.getId());
        }
        
        if (CalendarEvent.EVENT_TYPE_PUBLIC == calEvent.getEventType()) {
            // if eventtype==1 如果为公共事件
            if (!ArrayUtils.isEmpty(operaters)) {
//                this.calendarDAO.insertCalendarSubscriber(calEvent.getId(),
//                        operaters,
//                        calEvent.getVcid());
            }
        }
    }
    
    
}
