/*
 * 描       述:  <描述>
 * 修  改 人:  
 * 修改时间:
 * <修改描述:>
 */
package com.tx.local.content.facade;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.tx.core.paged.model.PagedList;
import com.tx.core.querier.model.Querier;
import com.tx.local.content.model.ContentInfoLevel;

import io.swagger.annotations.ApiOperation;

/**
 * 内容信息级别接口门面层[ContentInfoLevelFacade]<br/>
 * 
 * @author []
 * @version [版本号]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public interface ContentInfoLevelFacade {
    
    /**
     * 新增内容信息级别<br/>
     * <功能详细描述>
     * @param contentInfoLevel [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ApiOperation(value = "新增内容信息级别")
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ContentInfoLevel insert(@RequestBody ContentInfoLevel contentInfoLevel);
    
    /**
     * 根据id删除内容信息级别<br/> 
     * <功能详细描述>
     * @param id
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ApiOperation(value = "根据主键删除内容信息级别")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE) 
    public boolean deleteById(
    		@PathVariable(value = "id",required=true) String id);
	
	/**
     * 根据code删除内容信息级别<br/> 
     * <功能详细描述>
     * @param code
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ApiOperation(value = "根据编码删除内容信息级别")
    @RequestMapping(value = "/code/{code}", method = RequestMethod.DELETE) 
    public boolean deleteByCode(
    		@PathVariable(value = "code",required=true) String code);

    /**
     * 更新内容信息级别<br/>
     * <功能详细描述>
     * @param contentInfoLevel
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ApiOperation(value = "修改内容信息级别")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public boolean updateById(@PathVariable(value = "id",required=true) String id,
    		@RequestBody ContentInfoLevel contentInfoLevel);

    /**
     * 禁用内容信息级别<br/>
     * @param id
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
	@ApiOperation(value = "禁用内容信息级别")
    @RequestMapping(value = "/disable/{id}", method = RequestMethod.PATCH)
    public boolean disableById(
    		@PathVariable(value = "id", required = true) String id);
    
    /**
     * 启用内容信息级别<br/>
     * <功能详细描述>
     * @param id
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ApiOperation(value = "启用内容信息级别")
    @RequestMapping(value = "/enable/{id}", method = RequestMethod.PATCH)
    public boolean enableById(
    		@PathVariable(value = "id", required = true) String id);

    /**
     * 根据主键查询内容信息级别<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return ContentInfoLevel [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ApiOperation(value = "根据主键查询内容信息级别")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ContentInfoLevel findById(
            @PathVariable(value = "id", required = true) String id);
    
    /**
     * 根据编码查询内容信息级别<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return ContentInfoLevel [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ApiOperation(value = "根据编码查询内容信息级别")
    @RequestMapping(value = "/code/{code}", method = RequestMethod.GET)
    public ContentInfoLevel findByCode(
            @PathVariable(value = "code", required = true) String code);

    /**
     * 查询内容信息级别实例列表<br/>
     * <功能详细描述>
     * @param valid
     * @param querier
     * @return [参数说明]
     * 
     * @return List<ContentInfoLevel> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ApiOperation(value = "查询内容信息级别列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<ContentInfoLevel> queryList(
			@RequestParam(value = "valid", required = false) Boolean valid,
    		@RequestBody Querier querier
    	);
    
    /**
     * 查询内容信息级别分页列表<br/>
     * <功能详细描述>
     * @param valid
     * @param pageIndex
     * @param pageSize
     * @param querier
     * @return [参数说明]
     * 
     * @return PagedList<ContentInfoLevel> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ApiOperation(value = "查询内容信息级别分页列表")
    @RequestMapping(value = "/pagedlist/{pageSize}/{pageNumber}", method = RequestMethod.GET)
    public PagedList<ContentInfoLevel> queryPagedList(
			@RequestParam(value = "valid", required = false) Boolean valid,
			@RequestBody Querier querier,
			@PathVariable(value = "pageNumber", required = true) int pageIndex,
            @PathVariable(value = "pageSize", required = true) int pageSize
    	);
    
	/**
     * 查询内容信息级别数量<br/>
     * <功能详细描述>
     * @param valid
     * @param querier
     * @return [参数说明]
     * 
     * @return int [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ApiOperation(value = "查询内容信息级别数量")
    @RequestMapping(value = "/count", method = RequestMethod.GET)
    public int count(
			@RequestParam(value = "valid", required = false) Boolean valid,
            @RequestBody Querier querier);

	/**
     * 查询内容信息级别是否存在<br/>
	 * @param excludeId
     * @param querier
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ApiOperation(value = "查询内容信息级别是否存在")
    @RequestMapping(value = "/exists", method = RequestMethod.GET)
    public boolean exists(
    		@RequestBody Querier querier,
            @RequestParam(value = "excludeId", required = false) String excludeId
            );
}