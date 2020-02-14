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

import com.tx.local.message.model.DialogMessage;
import com.tx.local.message.service.DialogMessageService;
import com.tx.core.paged.model.PagedList;

import com.tx.local.message.model.MessageUserTypeEnum;
import com.tx.local.message.model.DialogTopicTypeEnum;

/**
 * 会话消息控制层<br/>
 * 
 * @author []
 * @version [版本号]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Controller
@RequestMapping("/dialogMessage")
public class DialogMessageController {
    
    //会话消息业务层
    @Resource(name = "dialogMessageService")
    private DialogMessageService dialogMessageService;
    
    /**
     * 跳转到查询会话消息分页列表页面<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/toQueryPagedList")
    public String toQueryPagedList(ModelMap response) {
		response.put("userTypes", MessageUserTypeEnum.values());
		response.put("topicTypes", DialogTopicTypeEnum.values());

        return "message/queryDialogMessagePagedList";
    }
    
    /**
     * 跳转到新增会话消息页面<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/toAdd")
    public String toAdd(ModelMap response) {
    	response.put("dialogMessage", new DialogMessage());
    	
		response.put("userTypes", MessageUserTypeEnum.values());
		response.put("topicTypes", DialogTopicTypeEnum.values());

        return "message/addDialogMessage";
    }
    
    /**
     * 跳转到编辑会话消息页面
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
        DialogMessage dialogMessage = this.dialogMessageService.findById(id); 
        response.put("dialogMessage", dialogMessage);

		response.put("userTypes", MessageUserTypeEnum.values());
		response.put("topicTypes", DialogTopicTypeEnum.values());
        
        return "message/updateDialogMessage";
    }

    /**
     * 查询会话消息实例列表<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<DialogMessage> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/queryList")
    public List<DialogMessage> queryList(
    		@RequestParam MultiValueMap<String, String> request
    	) {
        Map<String, Object> params = new HashMap<>();
		params.put("name", request.getFirst("name"));
    	
        List<DialogMessage> resList = this.dialogMessageService.queryList(
			params         
        );
  
        return resList;
    }
    
    /**
     * 查询会话消息实例分页列表<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<DialogMessage> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/queryPagedList")
    public PagedList<DialogMessage> queryPagedList(
			@RequestParam(value = "pageNumber", required = false, defaultValue = "1") int pageIndex,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
            @RequestParam MultiValueMap<String, String> request
    	) {
		Map<String, Object> params = new HashMap<>();
		params.put("name", request.getFirst("name"));

        PagedList<DialogMessage> resPagedList = this.dialogMessageService.queryPagedList(
			params,
			pageIndex,
			pageSize
        );
        return resPagedList;
    }
    
    /**
     * 新增会话消息实例
     * <功能详细描述>
     * @param dialogMessage [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/add")
    public boolean add(DialogMessage dialogMessage) {
        this.dialogMessageService.insert(dialogMessage);
        return true;
    }
    
    /**
     * 更新会话消息实例<br/>
     * <功能详细描述>
     * @param dialogMessage
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/update")
    public boolean update(DialogMessage dialogMessage) {
        boolean flag = this.dialogMessageService.updateById(dialogMessage);
        return flag;
    }
    
    /**
     * 根据主键查询会话消息实例<br/> 
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
    public DialogMessage findById(@RequestParam(value = "id") String id) {
        DialogMessage dialogMessage = this.dialogMessageService.findById(id);
        return dialogMessage;
    }

    /**
     * 删除会话消息实例<br/> 
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
        boolean flag = this.dialogMessageService.deleteById(id);
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
        boolean flag = this.dialogMessageService.exists(params, excludeId);
        
        Map<String, String> resMap = new HashMap<String, String>();
        if (!flag) {
            resMap.put("ok", "");
        } else {
            resMap.put("error", "重复值");
        }
        return resMap;
    }
    
    /**
     * 根据条件查询会话消息子级列表<br/>
     * <功能详细描述>
     * @param parentId
     * @param valid
     * @param request
     * @return [参数说明]
     * 
     * @return PagedList<T> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/queryChildren")
    public List<DialogMessage> queryChildren(
            @RequestParam(value = "parentId", required = true) String parentId,
            @RequestParam MultiValueMap<String, String> request) {
        Map<String, Object> params = new HashMap<>();
		params.put("name", request.getFirst("name"));
	
        
        List<DialogMessage> resList = this.dialogMessageService
                .queryChildrenByParentId(parentId, params);
        
        return resList;
    }
    
    /**
     * 根据条件查询会话消息子、孙级列表<br/>
     * <功能详细描述>
     * @param parentId
     * @param valid
     * @param request
     * @return [参数说明]
     * 
     * @return PagedList<T> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/queryDescendants")
    public List<DialogMessage> queryDescendants(
            @RequestParam(value = "parentId", required = true) String parentId,
            @RequestParam MultiValueMap<String, String> request) {
        Map<String, Object> params = new HashMap<>();
		params.put("name", request.getFirst("name"));
        
        List<DialogMessage> resList = this.dialogMessageService
                .queryDescendantsByParentId(parentId, params);
        
        return resList;
    }
    
}