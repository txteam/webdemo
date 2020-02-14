/*
 * 描       述:  <描述>
 * 修  改 人:  
 * 修改时间:
 * <修改描述:>
 */
package com.tx.local.message.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tx.core.paged.model.PagedList;
import com.tx.core.querier.model.Querier;
import com.tx.local.message.facade.DialogMessageFacade;
import com.tx.local.message.model.DialogMessage;
import com.tx.local.message.service.DialogMessageService;

import io.swagger.annotations.Api;

/**
 * 会话消息API控制层[DialogMessageAPIController]<br/>
 * 
 * @author []
 * @version [版本号]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@RestController
@Api(tags = "会话消息API")
@RequestMapping("/api/dialogMessage")
public class DialogMessageAPIController implements DialogMessageFacade {
    
    //会话消息业务层
    @Resource(name = "dialogMessageService")
    private DialogMessageService dialogMessageService;
    
    /**
     * 新增会话消息<br/>
     * <功能详细描述>
     * @param dialogMessage [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public DialogMessage insert(@RequestBody DialogMessage dialogMessage) {
        this.dialogMessageService.insert(dialogMessage);
        return dialogMessage;
    }
    
    /**
     * 根据id删除会话消息<br/> 
     * <功能详细描述>
     * @param id
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public boolean deleteById(
    		@PathVariable(value = "id",required=true) String id) {
        boolean flag = this.dialogMessageService.deleteById(id);
        return flag;
    }
    
    /**
     * 更新会话消息<br/>
     * <功能详细描述>
     * @param dialogMessage
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public boolean updateById(@PathVariable(value = "id",required=true) String id,
    		@RequestBody DialogMessage dialogMessage) {
        boolean flag = this.dialogMessageService.updateById(id,dialogMessage);
        return flag;
    }
    

    /**
     * 根据主键查询会话消息<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return DialogMessage [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public DialogMessage findById(
            @PathVariable(value = "id", required = true) String id) {
        DialogMessage res = this.dialogMessageService.findById(id);
        
        return res;
    }

    /**
     * 查询会话消息实例列表<br/>
     * <功能详细描述>
     * @param querier
     * @return [参数说明]
     * 
     * @return List<DialogMessage> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public List<DialogMessage> queryList(
    		@RequestBody Querier querier
    	) {
        List<DialogMessage> resList = this.dialogMessageService.queryList(
			querier         
        );
  
        return resList;
    }
    
    /**
     * 查询会话消息分页列表<br/>
     * <功能详细描述>
     * @param pageIndex
     * @param pageSize
     * @param querier
     * @return [参数说明]
     * 
     * @return PagedList<DialogMessage> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public PagedList<DialogMessage> queryPagedList(
			@RequestBody Querier querier,
			@PathVariable(value = "pageNumber", required = true) int pageIndex,
            @PathVariable(value = "pageSize", required = true) int pageSize
    	) {
        PagedList<DialogMessage> resPagedList = this.dialogMessageService.queryPagedList(
			querier,
			pageIndex,
			pageSize
        );
        return resPagedList;
    }
    
	/**
     * 查询会话消息数量<br/>
     * <功能详细描述>
     * @param querier
     * @return [参数说明]
     * 
     * @return int [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public int count(
            @RequestBody Querier querier) {
        int count = this.dialogMessageService.count(
        	querier);
        
        return count;
    }

	/**
     * 查询会话消息是否存在<br/>
	 * @param excludeId
     * @param querier
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public boolean exists(@RequestBody Querier querier,
            @RequestParam(value = "excludeId", required = false) String excludeId) {
        boolean flag = this.dialogMessageService.exists(querier, excludeId);
        
        return flag;
    }

	/**
     * 根据条件查询基础数据分页列表<br/>
     * <功能详细描述>
     * @param parentId
     * @param querier
     * @return [参数说明]
     * 
     * @return PagedList<T> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public List<DialogMessage> queryChildrenByParentId(@PathVariable(value = "parentId", required = true) String parentId,
            Querier querier){
        List<DialogMessage> resList = this.dialogMessageService.queryChildrenByParentId(parentId,
			querier         
        );
  
        return resList;
    }

	/**
     * 根据条件查询基础数据分页列表<br/>
     * <功能详细描述>
     * @param parentId
     * @param querier
     * @return [参数说明]
     * 
     * @return PagedList<T> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public List<DialogMessage> queryDescendantsByParentId(@PathVariable(value = "parentId", required = true) String parentId,
            Querier querier){
        List<DialogMessage> resList = this.dialogMessageService.queryDescendantsByParentId(parentId,
			querier         
        );
  
        return resList;
    }
}