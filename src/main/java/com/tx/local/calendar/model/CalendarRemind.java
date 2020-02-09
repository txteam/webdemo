/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2020年2月8日
 * <修改描述:>
 */
package com.tx.local.calendar.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;

/**
 * 日程提醒设置<br/>
 *    暂不作象棋设计以及实现
 *    提醒功能可以与日程呈现1：N的形式独立进行设置
 *    独立创建去支持，发私信，短信，提前提醒或每隔多久进行提醒的设置<br/>
 *    
 *    该对象的作用主要描述：
 *      1、如何提醒（短信...私信...）
 *      2、给谁提醒（）
 *      3、间隔多久提醒
 *      4、提醒的内容，等等...暂时想不清楚，就将该对象进行了剥离
 *      
 *    这样考虑的话日程对象实现上就完全不用考虑提醒的耦合问题等了
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2020年2月8日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Entity
@Table(name = "cal_calendar_remind")
@ApiModel("日程")
public class CalendarRemind implements Serializable{
    
    /** 注释内容 */
    private static final long serialVersionUID = -4966927149974134430L;

    /** 可选，事件唯一标识，重复的事件具有相同的id */
    @Id
    @Column(nullable = false)
    private String id;
    
    private String eventId;
    
    //......

    /**
     * @return 返回 id
     */
    public String getId() {
        return id;
    }

    /**
     * @param 对id进行赋值
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return 返回 eventId
     */
    public String getEventId() {
        return eventId;
    }

    /**
     * @param 对eventId进行赋值
     */
    public void setEventId(String eventId) {
        this.eventId = eventId;
    }
}
