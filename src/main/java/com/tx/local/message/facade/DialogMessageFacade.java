/*
 * 描       述:  <描述>
 * 修  改 人:  
 * 修改时间:
 * <修改描述:>
 */
package com.tx.local.message.facade;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.tx.core.paged.model.PagedList;
import com.tx.core.querier.model.Querier;
import com.tx.local.message.model.DialogMessage;

import io.swagger.annotations.ApiOperation;

/**
 * 会话消息接口门面层[DialogMessageFacade]<br/>
 * 
 * @author []
 * @version [版本号]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public interface DialogMessageFacade {
    
    /**
     * 新增会话消息<br/>
     * <功能详细描述>
     * @param dialogMessage [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ApiOperation(value = "新增会话消息")
    @RequestMapping(value = "", method = RequestMethod.POST)
    public DialogMessage insert(@RequestBody DialogMessage dialogMessage);
    
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
    @ApiOperation(value = "根据ID删除会话消息")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE) 
    public boolean deleteById(
    		@PathVariable(value = "id",required=true) String id);

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
    @ApiOperation(value = "修改会话消息")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public boolean updateById(@PathVariable(value = "id",required=true) String id,
    		@RequestBody DialogMessage dialogMessage);

    /**
     * 根据主键查询会话消息<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return DialogMessage [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ApiOperation(value = "根据主键查询会话消息")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public DialogMessage findById(
            @PathVariable(value = "id", required = true) String id);
    

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
    @ApiOperation(value = "查询会话消息列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<DialogMessage> queryList(
    		@RequestBody Querier querier
    	);
    
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
    @ApiOperation(value = "查询会话消息分页列表")
    @RequestMapping(value = "/pagedlist/{pageSize}/{pageNumber}", method = RequestMethod.GET)
    public PagedList<DialogMessage> queryPagedList(
			@RequestBody Querier querier,
			@PathVariable(value = "pageNumber", required = true) int pageIndex,
            @PathVariable(value = "pageSize", required = true) int pageSize
    	);
    
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
    @ApiOperation(value = "查询会话消息数量")
    @RequestMapping(value = "/count", method = RequestMethod.GET)
    public int count(
            @RequestBody Querier querier);

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
    @ApiOperation(value = "查询会话消息是否存在")
    @RequestMapping(value = "/exists", method = RequestMethod.GET)
    public boolean exists(
    		@RequestBody Querier querier,
            @RequestParam(value = "excludeId", required = false) String excludeId
            );

	/**
     * 根据条件查询查询会话消息子代列表<br/>
     * <功能详细描述>
     * @param parentId
     * @param querier
     * @return [参数说明]
     * 
     * @return PagedList<T> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ApiOperation(value = "根据条件查询查询会话消息子代列表")
    @RequestMapping(value = "/children/{parentId}", method = RequestMethod.GET)
    public List<DialogMessage> queryChildrenByParentId(@PathVariable(value = "parentId", required = true) String parentId,
            @RequestBody Querier querier);

	/**
     * 根据条件查询查询会话消息后代列表<br/>
     * <功能详细描述>
     * @param parentId
     * @param querier
     * @return [参数说明]
     * 
     * @return PagedList<T> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ApiOperation(value = "根据条件查询查询会话消息后代列表")
    @RequestMapping(value = "/descendants/{parentId}", method = RequestMethod.GET)
    public List<DialogMessage> queryDescendantsByParentId(@PathVariable(value = "parentId", required = true) String parentId,
            @RequestBody Querier querier);
}