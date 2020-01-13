/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2020年1月10日
 * <修改描述:>
 */
package com.tx.local.calendar.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.tx.local.basicdata.model.UserTypeEnum;

import io.swagger.annotations.ApiModel;

/**
 * 行事历对应事件<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2020年1月10日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Entity
@Table(name = "cal_calendar_event")
@ApiModel("会话消息")
public class CalendarEvent {
    
    /** 可选，事件唯一标识，重复的事件具有相同的id */
    @Id
    @Column(name = "id", nullable = false)
    private String id;
    
    private UserTypeEnum userType;
    
    private String userId;
    
    /** 必须，事件在日历上显示的title */
    private String title;
    
    /** 可选，true or false，是否是全天事件 */
    private boolean allDay;
    
    @Column(name = "start", nullable = false)
    private Date start;
    
    @Column(name = "end", nullable = false)
    private Date end;
    
    private boolean editable;
    
    private Date createDate;
    
    private Date lastUpdateDate;
    
    private String url;
    
    private String className;
}
