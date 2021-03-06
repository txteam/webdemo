/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2020年1月15日
 * <修改描述:>
 */
package com.tx.local.message.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tx.core.paged.model.PagedList;
import com.tx.local.message.model.MessageUserTypeEnum;
import com.tx.local.message.model.NoticeMessageDetail;
import com.tx.local.security.util.WebContextUtils;

/**
 * 操作人员通知<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2020年1月15日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@PreAuthorize("hasRole('ROLE_OPERATOR')")
@Controller
@RequestMapping("/operator/notice")
public class OperatorNoticeMessageController {
    
    /**
     * 操作人员行事历行事历<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = { "", "/" })
    public String index(@RequestParam Map<String, String> request,
            ModelMap response) {
        return "/message/operatorNoticeMain";
    }
    
    /**
     * 统计未读消息<br/>
     * <功能详细描述>
     * @param request
     * @return [参数说明]
     * 
     * @return int [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping(value = "/count/unread", method = RequestMethod.GET)
    public int countUnread(@RequestParam Map<String, String> request) {
        String vcid = WebContextUtils.getVcid();
        String operatorId = WebContextUtils.getOperatorId();
        
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("vcid", vcid);
        params.put("userId", operatorId);
        params.put("userType", MessageUserTypeEnum.OPERATOR);
        params.put("unread", true);
        
        int res = 2;
        return res;
    }
    
    /**
     * 查询通知消息的
     * <功能详细描述>
     * @param querier
     * @param pageIndex
     * @param pageSize
     * @return [参数说明]
     * 
     * @return PagedList<NoticeMessageDetail> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/pagedlist/{pageSize}/{pageNumber}", method = RequestMethod.GET)
    public PagedList<NoticeMessageDetail> queryNoticeMessageDetailPagedList(
            @PathVariable(value = "pageNumber", required = true) int pageIndex,
            @PathVariable(value = "pageSize", required = true) int pageSize,
            @RequestParam Map<String, String> request) {
        String vcid = WebContextUtils.getVcid();
        String operatorId = WebContextUtils.getOperatorId();
        
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("vcid", vcid);
        params.put("userId", operatorId);
        params.put("userType", MessageUserTypeEnum.OPERATOR);
        params.put("read", false);
        
        return new PagedList<>();
    }
    
    /**
     * 查询通知消息的
     * <功能详细描述>
     * @param querier
     * @param pageIndex
     * @param pageSize
     * @return [参数说明]
     * 
     * @return PagedList<NoticeMessageDetail> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    public NoticeMessageDetail findNoticeMessageDetail(
            @PathVariable(value = "id", required = true) String id,
            @RequestParam Map<String, String> request) {
        String vcid = WebContextUtils.getVcid();
        String operatorId = WebContextUtils.getOperatorId();
        
        NoticeMessageDetail detail = null;
        return detail;
    }
}
