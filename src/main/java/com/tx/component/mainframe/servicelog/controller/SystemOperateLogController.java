/*
 * 描          述:  业务日志ServiceLog:SystemOperateLog
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

import com.tx.component.mainframe.servicelog.SystemOperateLog;
import com.tx.component.servicelog.context.ServiceLoggerContext;
import com.tx.core.paged.model.PagedList;

/**
 * SystemOperateLog查询显示逻辑
 * <功能详细描述>
 * 
 * @author  brady
 * @version  [版本号, 2013-10-8]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Controller("systemOperateLogController")
@RequestMapping("/servicelog/mainframe/systemOperateLog")
public class SystemOperateLogController {
    
    /**
      * 跳转到查询SystemOperateLog日志页面
      *<功能详细描述>
      * @return [参数说明]
      * 
      * @return String [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/toQuerySystemOperateLogPagedList")
    public String toQuerySystemOperateLogPagedList() {
        return "/mainframe/querySystemOperateLogPagedList";
    }
    
    /**
      * 分页查询SystemOperateLog日志<br/>
      *<功能详细描述>
      * @return [参数说明]
      * 
      * @return PagedList<LoginLog> [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/querySystemOperateLogPagedList")
    public PagedList<SystemOperateLog> querySystemOperateLogPagedList(
            @RequestParam MultiValueMap<String, String> requestParameters,
            @RequestParam(value = "minCreateDate", required = false) Date minCreateDate,
            @RequestParam(value = "maxCreateDate", required = false) Date maxCreateDate,
            @RequestParam(value = "pageNumber", required = false, defaultValue = "1") int pageIndex,
            @RequestParam(value = "pageSize", required = false, defaultValue = "1") int pageSize) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("minCreateDate", minCreateDate);
        params.put("maxCreateDate", maxCreateDate);
        
        PagedList<SystemOperateLog> resPagedList = ServiceLoggerContext.getLogger(SystemOperateLog.class)
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
    protected PagedList<SystemOperateLog> filter(
            PagedList<SystemOperateLog> resPagedList) {
        return resPagedList;
    }
}
