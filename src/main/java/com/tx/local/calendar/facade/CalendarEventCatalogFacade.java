/*
 * 描       述:  <描述>
 * 修  改 人:  
 * 修改时间:
 * <修改描述:>
 */
package com.tx.local.calendar.facade;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.tx.core.paged.model.PagedList;
import com.tx.core.querier.model.Querier;
import com.tx.local.calendar.model.CalendarEventCatalog;

import io.swagger.annotations.ApiOperation;

/**
 * 日程分类接口门面层[CalendarEventCatalogFacade]<br/>
 * 
 * @author []
 * @version [版本号]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public interface CalendarEventCatalogFacade {
    
    /**
     * 新增日程分类<br/>
     * <功能详细描述>
     * @param calendarEventCatalog [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ApiOperation(value = "新增日程分类")
    @RequestMapping(value = "", method = RequestMethod.POST)
    public CalendarEventCatalog insert(@RequestBody CalendarEventCatalog calendarEventCatalog);
    
    /**
     * 根据id删除日程分类<br/> 
     * <功能详细描述>
     * @param id
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ApiOperation(value = "根据ID删除日程分类")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE) 
    public boolean deleteById(
    		@PathVariable(value = "id",required=true) String id);

    /**
     * 更新日程分类<br/>
     * <功能详细描述>
     * @param calendarEventCatalog
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ApiOperation(value = "修改日程分类")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public boolean updateById(@PathVariable(value = "id",required=true) String id,
    		@RequestBody CalendarEventCatalog calendarEventCatalog);

    /**
     * 根据主键查询日程分类<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return CalendarEventCatalog [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ApiOperation(value = "根据主键查询日程分类")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public CalendarEventCatalog findById(
            @PathVariable(value = "id", required = true) String id);
    

    /**
     * 查询日程分类实例列表<br/>
     * <功能详细描述>
     * @param querier
     * @return [参数说明]
     * 
     * @return List<CalendarEventCatalog> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ApiOperation(value = "查询日程分类列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<CalendarEventCatalog> queryList(
    		@RequestBody Querier querier
    	);
    
    /**
     * 查询日程分类分页列表<br/>
     * <功能详细描述>
     * @param pageIndex
     * @param pageSize
     * @param querier
     * @return [参数说明]
     * 
     * @return PagedList<CalendarEventCatalog> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ApiOperation(value = "查询日程分类分页列表")
    @RequestMapping(value = "/pagedlist/{pageSize}/{pageNumber}", method = RequestMethod.GET)
    public PagedList<CalendarEventCatalog> queryPagedList(
			@RequestBody Querier querier,
			@PathVariable(value = "pageNumber", required = true) int pageIndex,
            @PathVariable(value = "pageSize", required = true) int pageSize
    	);
    
	/**
     * 查询日程分类数量<br/>
     * <功能详细描述>
     * @param querier
     * @return [参数说明]
     * 
     * @return int [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ApiOperation(value = "查询日程分类数量")
    @RequestMapping(value = "/count", method = RequestMethod.GET)
    public int count(
            @RequestBody Querier querier);

	/**
     * 查询日程分类是否存在<br/>
	 * @param excludeId
     * @param querier
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ApiOperation(value = "查询日程分类是否存在")
    @RequestMapping(value = "/exists", method = RequestMethod.GET)
    public boolean exists(
    		@RequestBody Querier querier,
            @RequestParam(value = "excludeId", required = false) String excludeId
            );

	/**
     * 根据条件查询查询日程分类子代列表<br/>
     * <功能详细描述>
     * @param parentId
     * @param querier
     * @return [参数说明]
     * 
     * @return PagedList<T> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ApiOperation(value = "根据条件查询查询日程分类子代列表")
    @RequestMapping(value = "/children/{parentId}", method = RequestMethod.GET)
    public List<CalendarEventCatalog> queryChildrenByParentId(@PathVariable(value = "parentId", required = true) String parentId,
            @RequestBody Querier querier);

	/**
     * 根据条件查询查询日程分类后代列表<br/>
     * <功能详细描述>
     * @param parentId
     * @param querier
     * @return [参数说明]
     * 
     * @return PagedList<T> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ApiOperation(value = "根据条件查询查询日程分类后代列表")
    @RequestMapping(value = "/descendants/{parentId}", method = RequestMethod.GET)
    public List<CalendarEventCatalog> queryDescendantsByParentId(@PathVariable(value = "parentId", required = true) String parentId,
            @RequestBody Querier querier);
}