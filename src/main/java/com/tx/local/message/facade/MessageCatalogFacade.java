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
import com.tx.local.message.model.MessageCatalog;

import io.swagger.annotations.ApiOperation;

/**
 * 信息分类接口门面层[MessageCatalogFacade]<br/>
 * 
 * @author []
 * @version [版本号]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public interface MessageCatalogFacade {
    
    /**
     * 新增信息分类<br/>
     * <功能详细描述>
     * @param messageCatalog [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ApiOperation(value = "新增信息分类")
    @RequestMapping(value = "", method = RequestMethod.POST)
    public MessageCatalog insert(@RequestBody MessageCatalog messageCatalog);
    
    /**
     * 根据id删除信息分类<br/> 
     * <功能详细描述>
     * @param id
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ApiOperation(value = "根据ID删除信息分类")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE) 
    public boolean deleteById(
    		@PathVariable(value = "id",required=true) String id);
	
	/**
     * 根据code删除信息分类<br/> 
     * <功能详细描述>
     * @param code
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ApiOperation(value = "根据编码删除信息分类")
    @RequestMapping(value = "/code/{code}", method = RequestMethod.DELETE) 
    public boolean deleteByCode(
    		@PathVariable(value = "code",required=true) String code);

    /**
     * 更新信息分类<br/>
     * <功能详细描述>
     * @param messageCatalog
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ApiOperation(value = "修改信息分类")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public boolean updateById(@PathVariable(value = "id",required=true) String id,
    		@RequestBody MessageCatalog messageCatalog);

    /**
     * 禁用信息分类<br/>
     * @param id
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
	@ApiOperation(value = "禁用信息分类")
    @RequestMapping(value = "/disable/{id}", method = RequestMethod.PATCH)
    public boolean disableById(
    		@PathVariable(value = "id", required = true) String id);
    
    /**
     * 启用信息分类<br/>
     * <功能详细描述>
     * @param id
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ApiOperation(value = "启用信息分类")
    @RequestMapping(value = "/enable/{id}", method = RequestMethod.PATCH)
    public boolean enableById(
    		@PathVariable(value = "id", required = true) String id);

    /**
     * 根据主键查询信息分类<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return MessageCatalog [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ApiOperation(value = "根据主键查询信息分类")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public MessageCatalog findById(
            @PathVariable(value = "id", required = true) String id);
    
    /**
     * 根据编码查询信息分类<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return MessageCatalog [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ApiOperation(value = "根据编码查询信息分类")
    @RequestMapping(value = "/code/{code}", method = RequestMethod.GET)
    public MessageCatalog findByCode(
            @PathVariable(value = "code", required = true) String code);

    /**
     * 查询信息分类实例列表<br/>
     * <功能详细描述>
     * @param valid
     * @param querier
     * @return [参数说明]
     * 
     * @return List<MessageCatalog> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ApiOperation(value = "查询信息分类列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<MessageCatalog> queryList(
			@RequestParam(value = "valid", required = false) Boolean valid,
    		@RequestBody Querier querier
    	);
    
    /**
     * 查询信息分类分页列表<br/>
     * <功能详细描述>
     * @param valid
     * @param pageIndex
     * @param pageSize
     * @param querier
     * @return [参数说明]
     * 
     * @return PagedList<MessageCatalog> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ApiOperation(value = "查询信息分类分页列表")
    @RequestMapping(value = "/pagedlist/{pageSize}/{pageNumber}", method = RequestMethod.GET)
    public PagedList<MessageCatalog> queryPagedList(
			@RequestParam(value = "valid", required = false) Boolean valid,
			@RequestBody Querier querier,
			@PathVariable(value = "pageNumber", required = true) int pageIndex,
            @PathVariable(value = "pageSize", required = true) int pageSize
    	);
    
	/**
     * 查询信息分类数量<br/>
     * <功能详细描述>
     * @param valid
     * @param querier
     * @return [参数说明]
     * 
     * @return int [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ApiOperation(value = "查询信息分类数量")
    @RequestMapping(value = "/count", method = RequestMethod.GET)
    public int count(
			@RequestParam(value = "valid", required = false) Boolean valid,
            @RequestBody Querier querier);

	/**
     * 查询信息分类是否存在<br/>
	 * @param excludeId
     * @param querier
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ApiOperation(value = "查询信息分类是否存在")
    @RequestMapping(value = "/exists", method = RequestMethod.GET)
    public boolean exists(
    		@RequestBody Querier querier,
            @RequestParam(value = "excludeId", required = false) String excludeId
            );

	/**
     * 根据条件查询查询信息分类子代列表<br/>
     * <功能详细描述>
     * @param parentId
     * @param valid
     * @param querier
     * @return [参数说明]
     * 
     * @return PagedList<T> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ApiOperation(value = "根据条件查询查询信息分类子代列表")
    @RequestMapping(value = "/children/{parentId}", method = RequestMethod.GET)
    public List<MessageCatalog> queryChildrenByParentId(@PathVariable(value = "parentId", required = true) String parentId,
			@RequestParam(value = "valid", required = false) Boolean valid,
            @RequestBody Querier querier);

	/**
     * 根据条件查询查询信息分类后代列表<br/>
     * <功能详细描述>
     * @param parentId
     * @param valid
     * @param querier
     * @return [参数说明]
     * 
     * @return PagedList<T> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ApiOperation(value = "根据条件查询查询信息分类后代列表")
    @RequestMapping(value = "/descendants/{parentId}", method = RequestMethod.GET)
    public List<MessageCatalog> queryDescendantsByParentId(@PathVariable(value = "parentId", required = true) String parentId,
			@RequestParam(value = "valid", required = false) Boolean valid,
            @RequestBody Querier querier);
}