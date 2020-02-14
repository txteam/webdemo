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

import com.tx.local.message.model.MessageCatalog;
import com.tx.local.message.service.MessageCatalogService;
import com.tx.core.paged.model.PagedList;

import com.tx.local.message.model.MessageTypeEnum;

/**
 * 信息分类控制层<br/>
 * 
 * @author []
 * @version [版本号]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Controller
@RequestMapping("/messageCatalog")
public class MessageCatalogController {
    
    //信息分类业务层
    @Resource(name = "messageCatalogService")
    private MessageCatalogService messageCatalogService;
    
    /**
     * 跳转到查询信息分类列表页面<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/toQueryTreeList")
    public String toQueryTreeList(ModelMap response) {
		response.put("messageTypes", MessageTypeEnum.values());

        return "message/queryMessageCatalogTreeList";
    }
    
    /**
     * 跳转到新增信息分类页面<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/toAdd")
    public String toAdd(ModelMap response) {
    	response.put("messageCatalog", new MessageCatalog());
    	
		response.put("messageTypes", MessageTypeEnum.values());

        return "message/addMessageCatalog";
    }
    
    /**
     * 跳转到编辑信息分类页面
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
        MessageCatalog messageCatalog = this.messageCatalogService.findById(id); 
        response.put("messageCatalog", messageCatalog);

		response.put("messageTypes", MessageTypeEnum.values());
        
        return "message/updateMessageCatalog";
    }

    /**
     * 查询信息分类实例列表<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<MessageCatalog> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/queryList")
    public List<MessageCatalog> queryList(
			@RequestParam(value="valid",required=false) Boolean valid,
    		@RequestParam MultiValueMap<String, String> request
    	) {
        Map<String, Object> params = new HashMap<>();
		params.put("code", request.getFirst("code"));
		params.put("name", request.getFirst("name"));
    	
        List<MessageCatalog> resList = this.messageCatalogService.queryList(
			valid,
			params         
        );
  
        return resList;
    }
    
    /**
     * 查询信息分类实例分页列表<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<MessageCatalog> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/queryPagedList")
    public PagedList<MessageCatalog> queryPagedList(
			@RequestParam(value="valid",required=false) Boolean valid,
			@RequestParam(value = "pageNumber", required = false, defaultValue = "1") int pageIndex,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
            @RequestParam MultiValueMap<String, String> request
    	) {
		Map<String, Object> params = new HashMap<>();
		params.put("code", request.getFirst("code"));
		params.put("name", request.getFirst("name"));

        PagedList<MessageCatalog> resPagedList = this.messageCatalogService.queryPagedList(
			valid,
			params,
			pageIndex,
			pageSize
        );
        return resPagedList;
    }
    
    /**
     * 新增信息分类实例
     * <功能详细描述>
     * @param messageCatalog [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/add")
    public boolean add(MessageCatalog messageCatalog) {
        this.messageCatalogService.insert(messageCatalog);
        return true;
    }
    
    /**
     * 更新信息分类实例<br/>
     * <功能详细描述>
     * @param messageCatalog
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/update")
    public boolean update(MessageCatalog messageCatalog) {
        boolean flag = this.messageCatalogService.updateById(messageCatalog);
        return flag;
    }
    
    /**
     * 根据主键查询信息分类实例<br/> 
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
    public MessageCatalog findById(@RequestParam(value = "id") String id) {
        MessageCatalog messageCatalog = this.messageCatalogService.findById(id);
        return messageCatalog;
    }

	/**
     * 根据编码查询信息分类实例<br/> 
     * <功能详细描述>
     * @param code
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/findByCode")
    public MessageCatalog findByCode(@RequestParam(value = "code") String code) {
        MessageCatalog messageCatalog = this.messageCatalogService.findByCode(code);
        return messageCatalog;
    }
    
    /**
     * 删除信息分类实例<br/> 
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
        boolean flag = this.messageCatalogService.deleteById(id);
        return flag;
    }
    
    /**
     * 禁用信息分类实例
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
        boolean flag = this.messageCatalogService.disableById(id);
        return flag;
    }
    
    /**
     * 启用信息分类实例<br/>
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
        boolean flag = this.messageCatalogService.enableById(id);
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
        boolean flag = this.messageCatalogService.exists(params, excludeId);
        
        Map<String, String> resMap = new HashMap<String, String>();
        if (!flag) {
            resMap.put("ok", "");
        } else {
            resMap.put("error", "重复值");
        }
        return resMap;
    }
    
    /**
     * 根据条件查询信息分类子级列表<br/>
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
    public List<MessageCatalog> queryChildren(
            @RequestParam(value = "parentId", required = true) String parentId,
			@RequestParam(value="valid",required=false) Boolean valid,
            @RequestParam MultiValueMap<String, String> request) {
        Map<String, Object> params = new HashMap<>();
		params.put("code", request.getFirst("code"));
		params.put("name", request.getFirst("name"));
	
        
        List<MessageCatalog> resList = this.messageCatalogService
                .queryChildrenByParentId(parentId, valid, params);
        
        return resList;
    }
    
    /**
     * 根据条件查询信息分类子、孙级列表<br/>
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
    public List<MessageCatalog> queryDescendants(
            @RequestParam(value = "parentId", required = true) String parentId,
			@RequestParam(value="valid",required=false) Boolean valid,
            @RequestParam MultiValueMap<String, String> request) {
        Map<String, Object> params = new HashMap<>();
		params.put("code", request.getFirst("code"));
		params.put("name", request.getFirst("name"));
        
        List<MessageCatalog> resList = this.messageCatalogService
                .queryDescendantsByParentId(parentId, valid, params);
        
        return resList;
    }
    
}