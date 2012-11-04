/*
 * 描          述:  <描述>
 * 修  改   人:  PengQingyang
 * 修改时间:  2012-11-4
 * <修改描述:>
 */
package com.tx.webdemo.calendar.dao;

import java.util.List;
import java.util.Map;

import com.tx.webdemo.calendar.model.CalendarEvent;

/**
 * <行事历的数持久层>
 * <功能详细描述>
 * 
 * @author  PengQingyang
 * @version  [版本号, 2012-11-4]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface CalendarDAO {
    
    /**
     * 插入日历事件
     * @param calEvent
     * @return
     */
    int insertCalendarEvent(CalendarEvent calEvent);
    
    /**
     * 此方法用于在修改时查询
     * @param eventId
     * @return
     */
    CalendarEvent findCalendarEventById(String eventId);
    
    /**
     * 修改日历事件
     * @param calEvent
     * @return
     */
    int updateCalendarEvent(CalendarEvent calEvent);
    
    /**
     * 查询日历事件
     * @param params operid-工号 startdate-开始时间 enddate-结束时间
     * @return
     */
    List<CalendarEvent> queryCalendarEventList(Map<String, Object> params);
    
    /**
     * 插入公共事件的目标工号
     * @param eventId事件id
     * @param operators操作员列表
     * @param vcid vcid
     * @return
     */
    int insertCalendarSubscriber(String eventId, String[] operators, int vcid);
    
    /**
     * 删除
     * @param eventid
     * @return
     */
    int deleteCalendarEvent(String eventid);
    
    /**
     * 用于修改/删除时删除原有的记录
     * @param params
     * @return
     */
    int deleteCalendarSubscriber(String eventId);
    
    /**
     * 管理界面，查询事件的总行数
     * @param params
     * @return
     */
    Integer queryCalendarEventListForUpdateCount(Map<String, Object> params);
    
    /**
     * 管理界面分页数据
     * @param params
     * @return
     */
    List<CalendarEvent> queryCalendarEventListForUpdate(
            Map<String, Object> params);
    
    /**
     * 查找提醒信息
     * @param operId操作员ID
     * @return 提醒信息
     */
    List<Map<String, Object>> queryNotifies(int operId);
    
    /**
     * 添加提醒阅读记录
     * @param eventInstanceId事件实例ID
     * @param operId操作员ID
     * @return 插入的行数
     */
    int insertNotifyReceiveRecord(String eventInstanceId, int operId);
    
    /**
     * 查找提醒阅读记录
     * 
     * @param eventInstanceId事件实例ID
     * @param operId操作员ID
     * @return 提醒阅读记录
     */
    Map<String, Object> queryNotify(String eventInstanceId, int operId);
    
    /**
     * 更新提醒阅读记录
     * 
     * @param eventInstanceId事件实例ID
     * @param operId操作员ID
     * @param notifyCount提醒次数
     * @return 更新的行数
     */
    int update(String eventInstanceId, int operId, int notifyCount);
}
