/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2020年2月8日
 * <修改描述:>
 */
package com.tx.local.calendar.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;

/**
 * RRULE:循环规则
 *      https://fullcalendar.io/docs/rrule-plugin
 *      https://jakubroztocil.github.io/rrule/
 * 
 * @author  Administrator
 * @version  [版本号, 2020年2月8日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class CalendarEventRepeatRule {
    
    //tzid
    /** 必须，事件在日历上显示的title */
    @Column(nullable = true, updatable = true, length = 500)
    private FrequencyEnum freq;
    
    private Date dtstart;
    
    private Date until;
    
    private Integer count;
    
    private Integer interval;
    
    private List<String> wkst;
    
    private List<ByWeekdayEnum> byweekday;
    
    private List<ByMonthEnum> bymonth;
    
    private String bysetposIf;
    
    private String bymonthdayIf;
    
    private String byyearday;
    
    private String byweeknoIf;
    
    private String byhourIf;
    
    private String byminute;
    
    private String byeaster;
    
    /**
     * @return 返回 freq
     */
    public FrequencyEnum getFreq() {
        return freq;
    }
    
    /**
     * @param 对freq进行赋值
     */
    public void setFreq(FrequencyEnum freq) {
        this.freq = freq;
    }
    
    /**
     * @return 返回 dtstart
     */
    public Date getDtstart() {
        return dtstart;
    }
    
    /**
     * @param 对dtstart进行赋值
     */
    public void setDtstart(Date dtstart) {
        this.dtstart = dtstart;
    }
    
    /**
     * @return 返回 until
     */
    public Date getUntil() {
        return until;
    }
    
    /**
     * @param 对until进行赋值
     */
    public void setUntil(Date until) {
        this.until = until;
    }
    
    /**
     * @return 返回 count
     */
    public Integer getCount() {
        return count;
    }
    
    /**
     * @param 对count进行赋值
     */
    public void setCount(Integer count) {
        this.count = count;
    }
    
    /**
     * @return 返回 interval
     */
    public Integer getInterval() {
        return interval;
    }
    
    /**
     * @param 对interval进行赋值
     */
    public void setInterval(Integer interval) {
        this.interval = interval;
    }
    
    /**
     * @return 返回 wkst
     */
    public List<String> getWkst() {
        return wkst;
    }

    /**
     * @param 对wkst进行赋值
     */
    public void setWkst(List<String> wkst) {
        this.wkst = wkst;
    }

    /**
     * @return 返回 byweekday
     */
    public List<ByWeekdayEnum> getByweekday() {
        return byweekday;
    }

    /**
     * @param 对byweekday进行赋值
     */
    public void setByweekday(List<ByWeekdayEnum> byweekday) {
        this.byweekday = byweekday;
    }

    /**
     * @return 返回 bymonth
     */
    public List<ByMonthEnum> getBymonth() {
        return bymonth;
    }

    /**
     * @param 对bymonth进行赋值
     */
    public void setBymonth(List<ByMonthEnum> bymonth) {
        this.bymonth = bymonth;
    }

    /**
     * @return 返回 bysetposIf
     */
    public String getBysetposIf() {
        return bysetposIf;
    }
    
    /**
     * @param 对bysetposIf进行赋值
     */
    public void setBysetposIf(String bysetposIf) {
        this.bysetposIf = bysetposIf;
    }
    
    /**
     * @return 返回 bymonthdayIf
     */
    public String getBymonthdayIf() {
        return bymonthdayIf;
    }
    
    /**
     * @param 对bymonthdayIf进行赋值
     */
    public void setBymonthdayIf(String bymonthdayIf) {
        this.bymonthdayIf = bymonthdayIf;
    }
    
    /**
     * @return 返回 byyearday
     */
    public String getByyearday() {
        return byyearday;
    }
    
    /**
     * @param 对byyearday进行赋值
     */
    public void setByyearday(String byyearday) {
        this.byyearday = byyearday;
    }
    
    /**
     * @return 返回 byweeknoIf
     */
    public String getByweeknoIf() {
        return byweeknoIf;
    }
    
    /**
     * @param 对byweeknoIf进行赋值
     */
    public void setByweeknoIf(String byweeknoIf) {
        this.byweeknoIf = byweeknoIf;
    }
    
    /**
     * @return 返回 byhourIf
     */
    public String getByhourIf() {
        return byhourIf;
    }
    
    /**
     * @param 对byhourIf进行赋值
     */
    public void setByhourIf(String byhourIf) {
        this.byhourIf = byhourIf;
    }
    
    /**
     * @return 返回 byminute
     */
    public String getByminute() {
        return byminute;
    }
    
    /**
     * @param 对byminute进行赋值
     */
    public void setByminute(String byminute) {
        this.byminute = byminute;
    }
    
    /**
     * @return 返回 byeaster
     */
    public String getByeaster() {
        return byeaster;
    }
    
    /**
     * @param 对byeaster进行赋值
     */
    public void setByeaster(String byeaster) {
        this.byeaster = byeaster;
    }
    
    /**
     * 循环枚举<br/>
     * <功能详细描述>
     * 
     * @author  Administrator
     * @version  [版本号, 2020年2月9日]
     * @see  [相关类/方法]
     * @since  [产品/模块版本]
     */
    public static enum FrequencyEnum {
        yearly,
        
        monthly,
        
        weekly,
        
        daily,
        
        hourly,
        
        minutely,
        
        secondly;
    }
    
    /**
     * 周枚举<br/>
     * <功能详细描述>
     * 
     * @author  Administrator
     * @version  [版本号, 2020年2月9日]
     * @see  [相关类/方法]
     * @since  [产品/模块版本]
     */
    public static enum ByWeekdayEnum {
        mo,
        
        tu,
        
        we,
        
        th,
        
        fr,
        
        sa,
        
        su;
    }
    
    /**
     * 月枚举<br/>
     * <功能详细描述>
     * 
     * @author  Administrator
     * @version  [版本号, 2020年2月9日]
     * @see  [相关类/方法]
     * @since  [产品/模块版本]
     */
    public static enum ByMonthEnum {
        jan,
        
        feb,
        
        mar,
        
        apr,
        
        may,
        
        jun,
        
        jul,
        
        aug,
        
        sep,
        
        oct,
        
        nov,
        
        dec;
    }
    
}
