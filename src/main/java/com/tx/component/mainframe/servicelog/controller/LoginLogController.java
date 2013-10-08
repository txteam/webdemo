/*
 * 描          述:  <描述>
 * 修  改   人:  brady
 * 修改时间:  2013-10-8
 * <修改描述:>
 */
package com.tx.component.mainframe.servicelog.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tx.component.mainframe.servicelog.LoginLog;
import com.tx.component.servicelog.context.ServiceLoggerContext;
import com.tx.core.paged.model.PagedList;

/**
 * LoginLog查询显示逻辑
 * <功能详细描述>
 * 
 * @author  brady
 * @version  [版本号, 2013-10-8]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Component("loginLogController")
@RequestMapping("/servicelog/mainframe/loginLog")
public class LoginLogController {
    
    /**
      * 跳转到查询登录日志页面
      *<功能详细描述>
      * @return [参数说明]
      * 
      * @return String [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/toQueryLoginLog")
    public String toQueryLoginLog() {
        return "queryLoginLog";
    }
    
    /**
      * 分页查询登录日志<br/>
      *<功能详细描述>
      * @return [参数说明]
      * 
      * @return PagedList<LoginLog> [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public PagedList<LoginLog> queryLoginLogPagedList(Date minCreateDate,
            Date maxCreateDate, int pageIndex, int pageSize) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("minCreateDate", minCreateDate);
        params.put("maxCreateDate", maxCreateDate);
        
        PagedList<LoginLog> resPagedList = ServiceLoggerContext.getLogger(LoginLog.class)
                .queryPagedList(params, pageIndex, pageSize);
        return resPagedList;
    }
}
