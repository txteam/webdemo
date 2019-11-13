/*
 * 描       述:  <描述>
 * 修  改 人:  
 * 修改时间:
 * <修改描述:>
 */
package com.tx.local.operator.facade;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.tx.core.paged.model.PagedList;
import com.tx.core.querier.model.Querier;
import com.tx.local.operator.model.EmployeeInfo;

import io.swagger.annotations.ApiOperation;

/**
 * EmployeeInfo接口门面层[EmployeeInfoFacade]<br/>
 * 
 * @author []
 * @version [版本号]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public interface EmployeeInfoFacade {
    
    /**
     * 新增EmployeeInfo<br/>
     * <功能详细描述>
     * @param employeeInfo [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ApiOperation(value = "新增EmployeeInfo")
    @RequestMapping(value = "", method = RequestMethod.POST)
    public EmployeeInfo insert(@RequestBody EmployeeInfo employeeInfo);
    
    /**
     * 根据id删除EmployeeInfo<br/> 
     * <功能详细描述>
     * @param id
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ApiOperation(value = "根据主键删除EmployeeInfo")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE) 
    public boolean deleteById(
    		@PathVariable(value = "id",required=true) String id);
	
	/**
     * 根据code删除EmployeeInfo<br/> 
     * <功能详细描述>
     * @param code
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ApiOperation(value = "根据编码删除EmployeeInfo")
    @RequestMapping(value = "/code/{code}", method = RequestMethod.DELETE) 
    public boolean deleteByCode(
    		@PathVariable(value = "code",required=true) String code);

    /**
     * 更新EmployeeInfo<br/>
     * <功能详细描述>
     * @param employeeInfo
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ApiOperation(value = "修改EmployeeInfo")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public boolean updateById(@PathVariable(value = "id",required=true) String id,
    		@RequestBody EmployeeInfo employeeInfo);

    /**
     * 根据主键查询EmployeeInfo<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return EmployeeInfo [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ApiOperation(value = "根据主键查询EmployeeInfo")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public EmployeeInfo findById(
            @PathVariable(value = "id", required = true) String id);
    
    /**
     * 根据编码查询EmployeeInfo<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return EmployeeInfo [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ApiOperation(value = "根据编码查询EmployeeInfo")
    @RequestMapping(value = "/code/{code}", method = RequestMethod.GET)
    public EmployeeInfo findByCode(
            @PathVariable(value = "code", required = true) String code);

    /**
     * 查询EmployeeInfo实例列表<br/>
     * <功能详细描述>
     * @param querier
     * @return [参数说明]
     * 
     * @return List<EmployeeInfo> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ApiOperation(value = "查询EmployeeInfo列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<EmployeeInfo> queryList(
    		@RequestBody Querier querier
    	);
    
    /**
     * 查询EmployeeInfo分页列表<br/>
     * <功能详细描述>
     * @param pageIndex
     * @param pageSize
     * @param querier
     * @return [参数说明]
     * 
     * @return PagedList<EmployeeInfo> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ApiOperation(value = "查询EmployeeInfo分页列表")
    @RequestMapping(value = "/pagedlist/{pageSize}/{pageNumber}", method = RequestMethod.GET)
    public PagedList<EmployeeInfo> queryPagedList(
			@RequestBody Querier querier,
			@PathVariable(value = "pageNumber", required = true) int pageIndex,
            @PathVariable(value = "pageSize", required = true) int pageSize
    	);
    
	/**
     * 查询EmployeeInfo数量<br/>
     * <功能详细描述>
     * @param querier
     * @return [参数说明]
     * 
     * @return int [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ApiOperation(value = "查询EmployeeInfo数量")
    @RequestMapping(value = "/count", method = RequestMethod.GET)
    public int count(
            @RequestBody Querier querier);

	/**
     * 查询EmployeeInfo是否存在<br/>
	 * @param excludeId
     * @param querier
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ApiOperation(value = "查询EmployeeInfo是否存在")
    @RequestMapping(value = "/exists", method = RequestMethod.GET)
    public boolean exists(
    		@RequestBody Querier querier,
            @RequestParam(value = "excludeId", required = false) String excludeId
            );
}