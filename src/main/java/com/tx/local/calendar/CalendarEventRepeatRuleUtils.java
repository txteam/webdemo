/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2020年2月10日
 * <修改描述:>
 */
package com.tx.local.calendar;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tx.core.util.JsonUtils;
import com.tx.local.calendar.model.CalendarEventRepeatRule;

/**
 * 日程重复规则工具类<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2020年2月10日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public abstract class CalendarEventRepeatRuleUtils {
    
    //日志记录句柄
    private static final Logger logger = LoggerFactory
            .getLogger(CalendarEventRepeatRuleUtils.class);
    
    /**
     * 将对象转换为json字符串<br/>
     * <功能详细描述>
     * @param repeatRule
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public static String toJson(CalendarEventRepeatRule repeatRule) {
        if (repeatRule == null || repeatRule.getFreq() == null) {
            return "{}";
        }
        String json = JsonUtils.toJson(repeatRule);
        return json;
    }
    
    /**
     * 将日程事件转换为对象<br/>
     * <功能详细描述>
     * @param rrule
     * @return [参数说明]
     * 
     * @return CalendarEventRepeatRule [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public static CalendarEventRepeatRule toObject(String rrule) {
        if (StringUtils.isEmpty(rrule)) {
            return null;
        }
        CalendarEventRepeatRule repeatRule = null;
        try {
            repeatRule = JsonUtils.toObject(rrule,
                    CalendarEventRepeatRule.class);
        } catch (Exception e) {
            //donothing
            logger.warn("CalendarEventRepeatRuleUtils.toObject error.", e);
        }
        return repeatRule;
    }
}
