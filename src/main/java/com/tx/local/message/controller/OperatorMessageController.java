/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2020年1月15日
 * <修改描述:>
 */
package com.tx.local.message.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tx.core.paged.model.PagedList;
import com.tx.local.message.model.MessageUserTypeEnum;
import com.tx.local.message.model.PrivateMessage;
import com.tx.local.message.model.PrivateMessageTypeEnum;
import com.tx.local.message.service.PrivateMessageService;
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
@RequestMapping("/operator/message")
public class OperatorMessageController {
    
    @Resource
    private PrivateMessageService privateMessageService;
    
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
        return "/message/operatorMessageMain";
    }
    
    /**
     * 获取私信的详情<br/>
     * <功能详细描述>
     * @param request
     * @return [参数说明]
     * 
     * @return int [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    public PrivateMessage detail(@PathVariable(name = "id", required = true) String id) {
        String vcid = WebContextUtils.getVcid();
        String operatorId = WebContextUtils.getOperatorId();
        
        PrivateMessage condition = new PrivateMessage();
        condition.setId(id);
        condition.setVcid(vcid);
        condition.setType(PrivateMessageTypeEnum.MESSAGE);
        condition.setUserType(MessageUserTypeEnum.OPERATOR);
        condition.setUserId(operatorId);
        
        PrivateMessage pm = this.privateMessageService.find(condition);
        return pm;
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
        //params.put("type", PrivateMessageTypeEnum.RECEIVE);
        params.put("unread", true);
        
        int res = this.privateMessageService.count(params);
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
    @ResponseBody
    @RequestMapping(value = "/pagedlist/{pageSize}/{pageNumber}", method = RequestMethod.GET)
    public PagedList<PrivateMessage> queryPrivateMessagePagedList(
            @PathVariable(value = "pageNumber", required = true) int pageIndex,
            @PathVariable(value = "pageSize", required = true) int pageSize,
            @RequestParam Map<String, String> request) {
        String vcid = WebContextUtils.getVcid();
        String operatorId = WebContextUtils.getOperatorId();
        
        Map<String, Object> params = new HashMap<String, Object>();
        //params.put("type", PrivateMessageTypeEnum.RECEIVE);
        params.put("vcid", vcid);
        params.put("userId", operatorId);
        params.put("userType", MessageUserTypeEnum.OPERATOR);
        
        PagedList<PrivateMessage> resPagedList = this.privateMessageService
                .queryPagedList(params, pageIndex, pageSize);
        return resPagedList;
    }

    /**
     * 查询私信实例分页列表<br/>
     * <功能详细描述>
     * @return [参数说明]
     *
     * @return List<PrivateMessage> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/queryPagedList")
    public PagedList<PrivateMessage> queryPagedList(
            @RequestParam(value = "pageNumber", required = false, defaultValue = "1") int pageIndex,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
            @RequestParam  Map<String, Object> params) {
        params.put("userId", WebContextUtils.getOperatorId());
        PagedList<PrivateMessage> resPagedList = this.privateMessageService
                .queryPagedList(params, pageIndex, pageSize);
        return resPagedList;
    }

    /**
     * 跳转到发送私信页面<br/>
     * <功能详细描述>
     * @return [参数说明]
     *
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/toSend")
    public String toSend(ModelMap response) {
        response.put("privateMessage", new PrivateMessage());

        response.put("types", PrivateMessageTypeEnum.values());
        response.put("userTypes", MessageUserTypeEnum.values());
        response.put("senderUserTypes", MessageUserTypeEnum.values());

        return "message/addPrivateMessage";
    }

    /**
     * 发送私信
     * <功能详细描述>
     * @param privateMessage [参数说明]
     *
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/send")
    public boolean send(PrivateMessage privateMessage) {
        privateMessage.setType(PrivateMessageTypeEnum.MESSAGE);
        privateMessage.setUserType(MessageUserTypeEnum.OPERATOR);
        privateMessage.setVcid(WebContextUtils.getVcid());
        privateMessage.setSenderUserId(WebContextUtils.getOperatorId());
        privateMessage.setSenderUsername(WebContextUtils.getOperatorUsername());
        privateMessage.setSenderUserType(MessageUserTypeEnum.OPERATOR);
        privateMessage.setUnread(true);
        this.privateMessageService.insert(privateMessage);
        return true;
    }

    /**
     * 跳转选择接收人页面
     * @param eventName
     * @param responseMap
     * @return
     */
    @RequestMapping("/toSelectReceivedOperator")
    public String toSelectReceivedOperator(
            @RequestParam(value = "eventName", required = false) String eventName,
            ModelMap responseMap) {
        responseMap.put("eventName", eventName);
        return "message/selectReceivedOperator";
    }

    /**
     * 跳转展示内容页面
     * @param content
     * @param responseMap
     * @return
     */
    @RequestMapping("/toShowContent")
    public String toShowContent(
            @RequestParam(value = "id", required = false) String id,
            @RequestParam(value = "content", required = false) String content,
            @RequestParam(value = "unread", required = false) boolean unread,
            ModelMap responseMap) {
        responseMap.put("id", id);
        responseMap.put("content", content);
        responseMap.put("unread", unread);
        return "message/showPrivateMessageContent";
    }

    /**
     * 全部标记为已读
     * @return
     */
    @ResponseBody
    @RequestMapping("/readAll")
    public boolean readAll() {
        boolean flag = this.privateMessageService.readAll();
        return flag;
    }

    /**
     * 根据id标记私信为已读
     * @return
     */
    @ResponseBody
    @RequestMapping("/readById")
    public boolean readById(@RequestParam(value = "id", required = true) String id) {
        boolean flag = this.privateMessageService.readById(id);
        return flag;
    }
}
