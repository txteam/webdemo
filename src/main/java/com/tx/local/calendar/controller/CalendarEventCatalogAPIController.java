/*
 * 描       述:  <描述>
 * 修  改 人:  
 * 修改时间:
 * <修改描述:>
 */
package com.tx.local.calendar.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tx.core.paged.model.PagedList;
import com.tx.core.querier.model.Querier;
import com.tx.local.calendar.facade.CalendarEventCatalogFacade;
import com.tx.local.calendar.model.CalendarEventCatalog;
import com.tx.local.calendar.service.CalendarEventCatalogService;

import io.swagger.annotations.Api;

/**
 * 日程分类API控制层[CalendarEventCatalogAPIController]<br/>
 * 
 * @author []
 * @version [版本号]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@RestController
@Api(tags = "日程分类API")
@RequestMapping("/api/calendarEventCatalog")
public class CalendarEventCatalogAPIController implements CalendarEventCatalogFacade {
    
    //日程分类业务层
    @Resource(name = "calendarEventCatalogService")
    private CalendarEventCatalogService calendarEventCatalogService;
    
    /**
     * 新增日程分类<br/>
     * <功能详细描述>
     * @param calendarEventCatalog [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public CalendarEventCatalog insert(@RequestBody CalendarEventCatalog calendarEventCatalog) {
        this.calendarEventCatalogService.insert(calendarEventCatalog);
        return calendarEventCatalog;
    }
    
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
    @Override
    public boolean deleteById(
    		@PathVariable(value = "id",required=true) String id) {
        boolean flag = this.calendarEventCatalogService.deleteById(id);
        return flag;
    }
	
	/**
     * 根据code删除日程分类<br/> 
     * <功能详细描述>
     * @param code
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public boolean deleteByCode(
    		@PathVariable(value = "code",required=true) String code){
        boolean flag = this.calendarEventCatalogService.deleteByCode(code);
        return flag;    
    }
    
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
    @Override
    public boolean updateById(@PathVariable(value = "id",required=true) String id,
    		@RequestBody CalendarEventCatalog calendarEventCatalog) {
        boolean flag = this.calendarEventCatalogService.updateById(id,calendarEventCatalog);
        return flag;
    }
    
    /**
     * 禁用日程分类<br/>
     * @param id
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
	@Override
    public boolean disableById(
    		@PathVariable(value = "id", required = true) String id) {
        boolean flag = this.calendarEventCatalogService.disableById(id);
        return flag;
    }
    
    /**
     * 启用日程分类<br/>
     * <功能详细描述>
     * @param id
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public boolean enableById(
    		@PathVariable(value = "id", required = true) String id) {
        boolean flag = this.calendarEventCatalogService.enableById(id);
        return flag;
    }

    /**
     * 根据主键查询日程分类<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return CalendarEventCatalog [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public CalendarEventCatalog findById(
            @PathVariable(value = "id", required = true) String id) {
        CalendarEventCatalog res = this.calendarEventCatalogService.findById(id);
        
        return res;
    }

    /**
     * 根据编码查询日程分类<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return CalendarEventCatalog [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public CalendarEventCatalog findByCode(
            @PathVariable(value = "code", required = true) String code) {
        CalendarEventCatalog res = this.calendarEventCatalogService.findByCode(code);
        
        return res;
    }

    /**
     * 查询日程分类实例列表<br/>
     * <功能详细描述>
     * @param valid
     * @param querier
     * @return [参数说明]
     * 
     * @return List<CalendarEventCatalog> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public List<CalendarEventCatalog> queryList(
			@RequestParam(value = "valid", required = false) Boolean valid,
    		@RequestBody Querier querier
    	) {
        List<CalendarEventCatalog> resList = this.calendarEventCatalogService.queryList(
			valid,
			querier         
        );
  
        return resList;
    }
    
    /**
     * 查询日程分类分页列表<br/>
     * <功能详细描述>
     * @param valid
     * @param pageIndex
     * @param pageSize
     * @param querier
     * @return [参数说明]
     * 
     * @return PagedList<CalendarEventCatalog> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public PagedList<CalendarEventCatalog> queryPagedList(
			@RequestParam(value = "valid", required = false) Boolean valid,
			@RequestBody Querier querier,
			@PathVariable(value = "pageNumber", required = true) int pageIndex,
            @PathVariable(value = "pageSize", required = true) int pageSize
    	) {
        PagedList<CalendarEventCatalog> resPagedList = this.calendarEventCatalogService.queryPagedList(
			valid,
			querier,
			pageIndex,
			pageSize
        );
        return resPagedList;
    }
    
	/**
     * 查询日程分类数量<br/>
     * <功能详细描述>
     * @param valid
     * @param querier
     * @return [参数说明]
     * 
     * @return int [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public int count(
			@RequestParam(value = "valid", required = false) Boolean valid,
            @RequestBody Querier querier) {
        int count = this.calendarEventCatalogService.count(
			valid,
        	querier);
        
        return count;
    }

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
    @Override
    public boolean exists(@RequestBody Querier querier,
            @RequestParam(value = "excludeId", required = false) String excludeId) {
        boolean flag = this.calendarEventCatalogService.exists(querier, excludeId);
        
        return flag;
    }
}