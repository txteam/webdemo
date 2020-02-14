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

import com.tx.local.message.model.MessageAttachment;
import com.tx.local.message.service.MessageAttachmentService;
import com.tx.core.paged.model.PagedList;

import com.tx.local.message.model.MessageTypeEnum;

/**
 * 消息附件控制层<br/>
 * 
 * @author []
 * @version [版本号]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Controller
@RequestMapping("/messageAttachment")
public class MessageAttachmentController {
    
    //消息附件业务层
    @Resource(name = "messageAttachmentService")
    private MessageAttachmentService messageAttachmentService;
    
    /**
     * 跳转到查询消息附件分页列表页面<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/toQueryPagedList")
    public String toQueryPagedList(ModelMap response) {
		response.put("messageTypes", MessageTypeEnum.values());

        return "message/queryMessageAttachmentPagedList";
    }
    
    /**
     * 跳转到新增消息附件页面<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/toAdd")
    public String toAdd(ModelMap response) {
    	response.put("messageAttachment", new MessageAttachment());
    	
		response.put("messageTypes", MessageTypeEnum.values());

        return "message/addMessageAttachment";
    }
    
    /**
     * 跳转到编辑消息附件页面
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
        MessageAttachment messageAttachment = this.messageAttachmentService.findById(id); 
        response.put("messageAttachment", messageAttachment);

		response.put("messageTypes", MessageTypeEnum.values());
        
        return "message/updateMessageAttachment";
    }

    /**
     * 查询消息附件实例列表<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<MessageAttachment> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/queryList")
    public List<MessageAttachment> queryList(
    		@RequestParam MultiValueMap<String, String> request
    	) {
        Map<String, Object> params = new HashMap<>();
		params.put("name", request.getFirst("name"));
    	
        List<MessageAttachment> resList = this.messageAttachmentService.queryList(
			params         
        );
  
        return resList;
    }
    
    /**
     * 查询消息附件实例分页列表<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<MessageAttachment> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/queryPagedList")
    public PagedList<MessageAttachment> queryPagedList(
			@RequestParam(value = "pageNumber", required = false, defaultValue = "1") int pageIndex,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
            @RequestParam MultiValueMap<String, String> request
    	) {
		Map<String, Object> params = new HashMap<>();
		params.put("name", request.getFirst("name"));

        PagedList<MessageAttachment> resPagedList = this.messageAttachmentService.queryPagedList(
			params,
			pageIndex,
			pageSize
        );
        return resPagedList;
    }
    
    /**
     * 新增消息附件实例
     * <功能详细描述>
     * @param messageAttachment [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/add")
    public boolean add(MessageAttachment messageAttachment) {
        this.messageAttachmentService.insert(messageAttachment);
        return true;
    }
    
    /**
     * 更新消息附件实例<br/>
     * <功能详细描述>
     * @param messageAttachment
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/update")
    public boolean update(MessageAttachment messageAttachment) {
        boolean flag = this.messageAttachmentService.updateById(messageAttachment);
        return flag;
    }
    
    /**
     * 根据主键查询消息附件实例<br/> 
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
    public MessageAttachment findById(@RequestParam(value = "id") String id) {
        MessageAttachment messageAttachment = this.messageAttachmentService.findById(id);
        return messageAttachment;
    }

    /**
     * 删除消息附件实例<br/> 
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
        boolean flag = this.messageAttachmentService.deleteById(id);
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
        boolean flag = this.messageAttachmentService.exists(params, excludeId);
        
        Map<String, String> resMap = new HashMap<String, String>();
        if (!flag) {
            resMap.put("ok", "");
        } else {
            resMap.put("error", "重复值");
        }
        return resMap;
    }
    
}