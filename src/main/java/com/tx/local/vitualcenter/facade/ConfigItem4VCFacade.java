/*
 * 描       述:  <描述>
 * 修  改 人:  
 * 修改时间:
 * <修改描述:>
 */
package com.tx.local.vitualcenter.facade;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.tx.core.paged.model.PagedList;
import com.tx.core.querier.model.Querier;
import com.tx.local.vitualcenter.model.ConfigItem4VC;

import io.swagger.annotations.ApiOperation;

/**
 * ConfigItem4VC接口门面层[ConfigItem4VCFacade]<br/>
 * 
 * @author []
 * @version [版本号]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public interface ConfigItem4VCFacade {
    
    /**
     * 新增ConfigItem4VC<br/>
     * <功能详细描述>
     * @param configItem4VC [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ApiOperation(value = "新增ConfigItem4VC")
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ConfigItem4VC insert(@RequestBody ConfigItem4VC configItem4VC);
    
    /**
     * 根据id删除ConfigItem4VC<br/> 
     * <功能详细描述>
     * @param id
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ApiOperation(value = "根据ID删除ConfigItem4VC")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE) 
    public boolean deleteById(
    		@PathVariable(value = "id",required=true) String id);
	
	/**
     * 根据code删除ConfigItem4VC<br/> 
     * <功能详细描述>
     * @param code
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ApiOperation(value = "根据编码删除ConfigItem4VC")
    @RequestMapping(value = "/code/{code}", method = RequestMethod.DELETE) 
    public boolean deleteByCode(
    		@PathVariable(value = "code",required=true) String code);

    /**
     * 更新ConfigItem4VC<br/>
     * <功能详细描述>
     * @param configItem4VC
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ApiOperation(value = "修改ConfigItem4VC")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public boolean updateById(@PathVariable(value = "id",required=true) String id,
    		@RequestBody ConfigItem4VC configItem4VC);

    /**
     * 根据主键查询ConfigItem4VC<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return ConfigItem4VC [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ApiOperation(value = "根据主键查询ConfigItem4VC")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ConfigItem4VC findById(
            @PathVariable(value = "id", required = true) String id);
    
    /**
     * 根据编码查询ConfigItem4VC<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return ConfigItem4VC [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ApiOperation(value = "根据编码查询ConfigItem4VC")
    @RequestMapping(value = "/code/{code}", method = RequestMethod.GET)
    public ConfigItem4VC findByCode(
            @PathVariable(value = "code", required = true) String code);

    /**
     * 查询ConfigItem4VC实例列表<br/>
     * <功能详细描述>
     * @param querier
     * @return [参数说明]
     * 
     * @return List<ConfigItem4VC> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ApiOperation(value = "查询ConfigItem4VC列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<ConfigItem4VC> queryList(
    		@RequestBody Querier querier
    	);
    
    /**
     * 查询ConfigItem4VC分页列表<br/>
     * <功能详细描述>
     * @param pageIndex
     * @param pageSize
     * @param querier
     * @return [参数说明]
     * 
     * @return PagedList<ConfigItem4VC> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ApiOperation(value = "查询ConfigItem4VC分页列表")
    @RequestMapping(value = "/pagedlist/{pageSize}/{pageNumber}", method = RequestMethod.GET)
    public PagedList<ConfigItem4VC> queryPagedList(
			@RequestBody Querier querier,
			@PathVariable(value = "pageNumber", required = true) int pageIndex,
            @PathVariable(value = "pageSize", required = true) int pageSize
    	);
    
	/**
     * 查询ConfigItem4VC数量<br/>
     * <功能详细描述>
     * @param querier
     * @return [参数说明]
     * 
     * @return int [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ApiOperation(value = "查询ConfigItem4VC数量")
    @RequestMapping(value = "/count", method = RequestMethod.GET)
    public int count(
            @RequestBody Querier querier);

	/**
     * 查询ConfigItem4VC是否存在<br/>
	 * @param excludeId
     * @param querier
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ApiOperation(value = "查询ConfigItem4VC是否存在")
    @RequestMapping(value = "/exists", method = RequestMethod.GET)
    public boolean exists(
    		@RequestBody Querier querier,
            @RequestParam(value = "excludeId", required = false) String excludeId
            );

	/**
     * 根据条件查询查询ConfigItem4VC子代列表<br/>
     * <功能详细描述>
     * @param parentId
     * @param querier
     * @return [参数说明]
     * 
     * @return PagedList<T> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ApiOperation(value = "根据条件查询查询ConfigItem4VC子代列表")
    @RequestMapping(value = "/children/{parentId}", method = RequestMethod.GET)
    public List<ConfigItem4VC> queryChildrenByParentId(@PathVariable(value = "parentId", required = true) String parentId,
            @RequestBody Querier querier);

	/**
     * 根据条件查询查询ConfigItem4VC后代列表<br/>
     * <功能详细描述>
     * @param parentId
     * @param querier
     * @return [参数说明]
     * 
     * @return PagedList<T> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ApiOperation(value = "根据条件查询查询ConfigItem4VC后代列表")
    @RequestMapping(value = "/descendants/{parentId}", method = RequestMethod.GET)
    public List<ConfigItem4VC> queryDescendantsByParentId(@PathVariable(value = "parentId", required = true) String parentId,
            @RequestBody Querier querier);
}