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
import com.tx.local.message.model.Notice2User;

import io.swagger.annotations.ApiOperation;

/**
 * Notice2User接口门面层[Notice2UserFacade]<br/>
 * 
 * @author []
 * @version [版本号]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public interface Notice2UserFacade {
    
    /**
     * 新增Notice2User<br/>
     * <功能详细描述>
     * @param notice2User [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ApiOperation(value = "新增Notice2User")
    @RequestMapping(value = "", method = RequestMethod.POST)
    public Notice2User insert(@RequestBody Notice2User notice2User);
    
    /**
     * 根据id删除Notice2User<br/> 
     * <功能详细描述>
     * @param id
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ApiOperation(value = "根据ID删除Notice2User")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE) 
    public boolean deleteById(
    		@PathVariable(value = "id",required=true) String id);

    /**
     * 更新Notice2User<br/>
     * <功能详细描述>
     * @param notice2User
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ApiOperation(value = "修改Notice2User")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public boolean updateById(@PathVariable(value = "id",required=true) String id,
    		@RequestBody Notice2User notice2User);

    /**
     * 根据主键查询Notice2User<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return Notice2User [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ApiOperation(value = "根据主键查询Notice2User")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Notice2User findById(
            @PathVariable(value = "id", required = true) String id);
    

    /**
     * 查询Notice2User实例列表<br/>
     * <功能详细描述>
     * @param querier
     * @return [参数说明]
     * 
     * @return List<Notice2User> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ApiOperation(value = "查询Notice2User列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<Notice2User> queryList(
    		@RequestBody Querier querier
    	);
    
    /**
     * 查询Notice2User分页列表<br/>
     * <功能详细描述>
     * @param pageIndex
     * @param pageSize
     * @param querier
     * @return [参数说明]
     * 
     * @return PagedList<Notice2User> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ApiOperation(value = "查询Notice2User分页列表")
    @RequestMapping(value = "/pagedlist/{pageSize}/{pageNumber}", method = RequestMethod.GET)
    public PagedList<Notice2User> queryPagedList(
			@RequestBody Querier querier,
			@PathVariable(value = "pageNumber", required = true) int pageIndex,
            @PathVariable(value = "pageSize", required = true) int pageSize
    	);
    
	/**
     * 查询Notice2User数量<br/>
     * <功能详细描述>
     * @param querier
     * @return [参数说明]
     * 
     * @return int [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ApiOperation(value = "查询Notice2User数量")
    @RequestMapping(value = "/count", method = RequestMethod.GET)
    public int count(
            @RequestBody Querier querier);

	/**
     * 查询Notice2User是否存在<br/>
	 * @param excludeId
     * @param querier
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ApiOperation(value = "查询Notice2User是否存在")
    @RequestMapping(value = "/exists", method = RequestMethod.GET)
    public boolean exists(
    		@RequestBody Querier querier,
            @RequestParam(value = "excludeId", required = false) String excludeId
            );
}