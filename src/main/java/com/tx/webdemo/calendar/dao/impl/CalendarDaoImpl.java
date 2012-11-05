/*
 * 描          述:  <描述>
 * 修  改   人:  PengQingyang
 * 修改时间:  2012-11-5
 * <修改描述:>
 */
package com.tx.webdemo.calendar.dao.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.tx.core.mybatis.support.MyBatisDaoSupport;
import com.tx.core.paged.model.PagedList;
import com.tx.webdemo.calendar.dao.CalendarDao;
import com.tx.webdemo.calendar.model.CalendarEvent;

/**
 * <行事历持久层>
 * <功能详细描述>
 * 
 * @author  PengQingyang
 * @version  [版本号, 2012-11-5]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Component("calendar.calendarDao")
public class CalendarDaoImpl implements CalendarDao {
    
    @Resource(name = "myBatisDaoSupport")
    private MyBatisDaoSupport myBatisDaoSupport;
    
    /**
     * @param calEvent
     */
    public void insertCalendarEvent(CalendarEvent calEvent) {
        this.myBatisDaoSupport.insert("calendar.insertCalendarEvent", calEvent);
    }
    
    /**
     * @param calEvent
     * @return
     */
    public int deleteCalendarEvent(CalendarEvent calEvent) {
        // TODO Auto-generated method stub
        return 0;
    }
    
    /**
     * @param calEvent
     * @return
     */
    public CalendarEvent findCalendarEvent(CalendarEvent calEvent) {
        // TODO Auto-generated method stub
        return null;
    }
    
    /**
     * @param params
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<CalendarEvent> queryCalendarEventList(Map<String, Object> params) {
        return (List<CalendarEvent>) this.myBatisDaoSupport.queryList("calendarDao.queryCalendarEventList",
                params);
    }
    
    /**
     * @param params
     * @return
     */
    @Override
    public PagedList<CalendarEvent> queryCalendarEventPagedList(
            Map<String, Object> params) {
        // TODO Auto-generated method stub
        return null;
    }
    
    /**
     * @param params
     * @return
     */
    public int updateCalendarEvent(Map<String, Object> params) {
        // TODO Auto-generated method stub
        return 0;
    }
    
    /**
     * @param eventId
     * @param operators
     * @return
     */
    public int insertCalendarSubscriber(String eventId, String[] operators) {
        // TODO Auto-generated method stub
        return 0;
    }
    
    /**
     * @param eventId
     * @return
     */
    public int deleteCalendarSubscriber(String eventId) {
        // TODO Auto-generated method stub
        return 0;
    }
    
    /**
     * @param operId
     * @return
     */
    public List<Map<String, Object>> queryNotifies(int operId) {
        // TODO Auto-generated method stub
        return null;
    }
    
    /**
     * @param eventInstanceId
     * @param operId
     * @return
     */
    @Override
    public int insertNotifyReceiveRecord(String eventInstanceId, int operId) {
        // TODO Auto-generated method stub
        return 0;
    }
    
    /**
     * @param eventInstanceId
     * @param operId
     * @return
     */
    public Map<String, Object> queryNotify(String eventInstanceId, int operId) {
        // TODO Auto-generated method stub
        return null;
    }
    
    /**
     * @param eventInstanceId
     * @param operId
     * @param notifyCount
     * @return
     */
    public int update(String eventInstanceId, int operId, int notifyCount) {
        // TODO Auto-generated method stub
        return 0;
    }
    
}
