/*
 * 描          述:  业务日志ServiceLog:LoginLog
 * 修  改   人:  
 * 修改时间:  
 * <修改描述:>
 */
package com.tx.component.mainframe.servicelog.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
@Controller("loginLogController")
@RequestMapping("/servicelog/mainframe/loginLog")
public class LoginLogController {
    
    /**
      * 跳转到查询LoginLog日志页面
      *<功能详细描述>
      * @return [参数说明]
      * 
      * @return String [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/toQueryLoginLogPagedList")
    public String toQueryLoginLogPagedList() {
        return "/mainframe/queryLoginLogPagedList";
    }
    
    /**
      * 分页查询LoginLog日志<br/>
      *<功能详细描述>
      * @return [参数说明]
      * 
      * @return PagedList<LoginLog> [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/queryLoginLogPagedList")
    public PagedList<LoginLog> queryLoginLogPagedList(
            @RequestParam MultiValueMap<String, String> requestParameters,
            @RequestParam(value = "minCreateDate", required = false) Date minCreateDate,
            @RequestParam(value = "maxCreateDate", required = false) Date maxCreateDate,
            @RequestParam(value = "pageNumber", required = false, defaultValue = "1") int pageIndex,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("minCreateDate", minCreateDate);
        params.put("maxCreateDate", maxCreateDate);
        
        PagedList<LoginLog> resPagedList = ServiceLoggerContext.getLogger(LoginLog.class)
                .queryPagedList(params, pageIndex, pageSize);
        return resPagedList;
    }
    
    /**
      * 处理返回数据<br/>
      *<功能详细描述>
      * @param resPagedList
      * @return [参数说明]
      * 
      * @return PagedList<LoginLog> [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    protected PagedList<LoginLog> filter(
            PagedList<LoginLog> resPagedList) {
        return resPagedList;
    }
}
