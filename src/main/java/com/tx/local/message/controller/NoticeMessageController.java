/*
 * 描       述:  <描述>
 * 修  改 人:  
 * 修改时间:
 * <修改描述:>
 */
package com.tx.local.message.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tx.local.message.model.NoticeMessage;
import com.tx.local.message.service.NoticeMessageService;
import com.tx.core.paged.model.PagedList;

import com.tx.local.message.model.NoticePriorityEnum;

/**
 * 公告消息控制层<br/>
 * 
 * @author []
 * @version [版本号]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Controller
@RequestMapping("/noticeMessage")
public class NoticeMessageController {
    
    //公告消息业务层
    @Resource(name = "noticeMessageService")
    private NoticeMessageService noticeMessageService;
    
    /**
     * 跳转到查询公告消息分页列表页面<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/toQueryPagedList")
    public String toQueryPagedList(ModelMap response) {
		response.put("prioritys", NoticePriorityEnum.values());

        return "message/queryNoticeMessagePagedList";
    }
    
    /**
     * 跳转到新增公告消息页面<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/toAdd")
    public String toAdd(ModelMap response) {
    	response.put("noticeMessage", new NoticeMessage());
    	
		response.put("prioritys", NoticePriorityEnum.values());

        return "message/addNoticeMessage";
    }
    
    /**
     * 跳转到编辑公告消息页面
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/toUpdate")
    public String toUpdate(
    		@RequestParam("id") String id,
            ModelMap response) {
        NoticeMessage noticeMessage = this.noticeMessageService.findById(id); 
        response.put("noticeMessage", noticeMessage);

		response.put("prioritys", NoticePriorityEnum.values());
        
        return "message/updateNoticeMessage";
    }

    /**
     * 查询公告消息实例列表<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<NoticeMessage> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/queryList")
    public List<NoticeMessage> queryList(
			@RequestParam(value="valid",required=false) Boolean valid,
    		@RequestParam MultiValueMap<String, String> request
    	) {
        Map<String, Object> params = new HashMap<>();
		params.put("name", request.getFirst("name"));
    	
        List<NoticeMessage> resList = this.noticeMessageService.queryList(
			valid,
			params         
        );
  
        return resList;
    }
    
    /**
     * 查询公告消息实例分页列表<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<NoticeMessage> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/queryPagedList")
    public PagedList<NoticeMessage> queryPagedList(
			@RequestParam(value="valid",required=false) Boolean valid,
			@RequestParam(value = "pageNumber", required = false, defaultValue = "1") int pageIndex,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
            @RequestParam MultiValueMap<String, String> request
    	) {
		Map<String, Object> params = new HashMap<>();
		params.put("name", request.getFirst("name"));

        PagedList<NoticeMessage> resPagedList = this.noticeMessageService.queryPagedList(
			valid,
			params,
			pageIndex,
			pageSize
        );
        return resPagedList;
    }
    
    /**
     * 新增公告消息实例
     * <功能详细描述>
     * @param noticeMessage [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/add")
    public boolean add(NoticeMessage noticeMessage) {
        this.noticeMessageService.insert(noticeMessage);
        return true;
    }
    
    /**
     * 更新公告消息实例<br/>
     * <功能详细描述>
     * @param noticeMessage
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/update")
    public boolean update(NoticeMessage noticeMessage) {
        boolean flag = this.noticeMessageService.updateById(noticeMessage);
        return flag;
    }
    
    /**
     * 根据主键查询公告消息实例<br/> 
     * <功能详细描述>
     * @param id
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/findById")
    public NoticeMessage findById(@RequestParam(value = "id") String id) {
        NoticeMessage noticeMessage = this.noticeMessageService.findById(id);
        return noticeMessage;
    }

    /**
     * 删除公告消息实例<br/> 
     * <功能详细描述>
     * @param id
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/deleteById")
    public boolean deleteById(@RequestParam(value = "id") String id) {
        boolean flag = this.noticeMessageService.deleteById(id);
        return flag;
    }
    
    /**
     * 禁用公告消息实例
     * @param id
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/disableById")
    public boolean disableById(@RequestParam(value = "id") String id) {
        boolean flag = this.noticeMessageService.disableById(id);
        return flag;
    }
    
    /**
     * 启用公告消息实例<br/>
     * <功能详细描述>
     * @param id
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/enableById")
    public boolean enableById(@RequestParam(value = "id") String id) {
        boolean flag = this.noticeMessageService.enableById(id);
        return flag;
    }
    
	/**
     * 校验是否重复<br/>
	 * @param excludeId
     * @param params
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/validate")
    public Map<String, String> validate(
            @RequestParam(value = "id", required = false) String excludeId,
            @RequestParam Map<String, String> params) {
        params.remove("id");
        boolean flag = this.noticeMessageService.exists(params, excludeId);
        
        Map<String, String> resMap = new HashMap<String, String>();
        if (!flag) {
            resMap.put("ok", "");
        } else {
            resMap.put("error", "重复值");
        }
        return resMap;
    }
    
}