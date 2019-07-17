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
import com.tx.local.vitualcenter.model.VirtualCenter;

import io.swagger.annotations.ApiOperation;

/**
 * VirtualCenter接口门面层[VirtualCenterFacade]<br/>
 * 
 * @author []
 * @version [版本号]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public interface VirtualCenterFacade {
    
    /**
     * 新增VirtualCenter<br/>
     * <功能详细描述>
     * @param virtualCenter [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ApiOperation(value = "新增虚中心")
    @RequestMapping(value = "", method = RequestMethod.POST)
    public VirtualCenter insert(@RequestBody VirtualCenter virtualCenter);
    
    /**
     * 根据id删除VirtualCenter<br/> 
     * <功能详细描述>
     * @param id
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ApiOperation(value = "根据主键删除虚中心")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public boolean deleteById(
            @PathVariable(value = "id", required = true) String id);
    
    /**
     * 根据code删除VirtualCenter<br/> 
     * <功能详细描述>
     * @param code
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ApiOperation(value = "根据编码删除虚中心")
    @RequestMapping(value = "/code/{code}", method = RequestMethod.DELETE)
    public boolean deleteByCode(
            @PathVariable(value = "code", required = true) String code);
    
    /**
     * 更新VirtualCenter<br/>
     * <功能详细描述>
     * @param virtualCenter
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ApiOperation(value = "修改虚中心")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public boolean updateById(
            @PathVariable(value = "id", required = true) String id,
            @RequestBody VirtualCenter virtualCenter);
    
    /**
     * 禁用VirtualCenter<br/>
     * @param id
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ApiOperation(value = "禁用虚中心")
    @RequestMapping(value = "/disable/{id}", method = RequestMethod.PATCH)
    public boolean disableById(
            @PathVariable(value = "id", required = true) String id);
    
    /**
     * 启用VirtualCenter<br/>
     * <功能详细描述>
     * @param id
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ApiOperation(value = "启用虚中心")
    @RequestMapping(value = "/enable/{id}", method = RequestMethod.PATCH)
    public boolean enableById(
            @PathVariable(value = "id", required = true) String id);
    
    /**
     * 根据主键查询VirtualCenter<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return VirtualCenter [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ApiOperation(value = "根据主键查询虚中心")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public VirtualCenter findById(
            @PathVariable(value = "id", required = true) String id);
    
    /**
     * 根据编码查询VirtualCenter<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return VirtualCenter [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ApiOperation(value = "根据编码查询虚中心")
    @RequestMapping(value = "/code/{code}", method = RequestMethod.GET)
    public VirtualCenter findByCode(
            @PathVariable(value = "code", required = true) String code);
    
    /**
     * 查询VirtualCenter实例列表<br/>
     * <功能详细描述>
     * @param valid
     * @param querier
     * @return [参数说明]
     * 
     * @return List<VirtualCenter> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ApiOperation(value = "查询虚中心列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<VirtualCenter> queryList(
            @RequestParam(value = "valid", required = false) Boolean valid,
            @RequestBody Querier querier);
    
    /**
     * 查询VirtualCenter分页列表<br/>
     * <功能详细描述>
     * @param valid
     * @param pageIndex
     * @param pageSize
     * @param querier
     * @return [参数说明]
     * 
     * @return PagedList<VirtualCenter> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ApiOperation(value = "查询虚中心分页列表")
    @RequestMapping(value = "/pagedlist/{pageSize}/{pageNumber}", method = RequestMethod.GET)
    public PagedList<VirtualCenter> queryPagedList(
            @RequestParam(value = "valid", required = false) Boolean valid,
            @RequestBody Querier querier,
            @PathVariable(value = "pageNumber", required = true) int pageIndex,
            @PathVariable(value = "pageSize", required = true) int pageSize);
    
    /**
     * 查询VirtualCenter数量<br/>
     * <功能详细描述>
     * @param valid
     * @param querier
     * @return [参数说明]
     * 
     * @return int [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ApiOperation(value = "查询虚中心数量")
    @RequestMapping(value = "/count", method = RequestMethod.GET)
    public int count(
            @RequestParam(value = "valid", required = false) Boolean valid,
            @RequestBody Querier querier);
    
    /**
     * 查询VirtualCenter是否存在<br/>
     * @param excludeId
     * @param querier
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ApiOperation(value = "查询虚中心是否存在")
    @RequestMapping(value = "/exists", method = RequestMethod.GET)
    public boolean exists(@RequestBody Querier querier,
            @RequestParam(value = "excludeId", required = false) String excludeId);
    
    /**
     * 根据条件查询查询VirtualCenter子代列表<br/>
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
    @ApiOperation(value = "根据条件查询查询虚中心子代列表")
    @RequestMapping(value = "/children/{parentId}", method = RequestMethod.GET)
    public List<VirtualCenter> queryChildrenByParentId(
            @PathVariable(value = "parentId", required = true) String parentId,
            @RequestParam(value = "valid", required = false) Boolean valid,
            @RequestBody Querier querier);
    
    /**
     * 根据条件查询查询VirtualCenter后代列表<br/>
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
    @ApiOperation(value = "根据条件查询查询虚中心后代列表")
    @RequestMapping(value = "/descendants/{parentId}", method = RequestMethod.GET)
    public List<VirtualCenter> queryDescendantsByParentId(
            @PathVariable(value = "parentId", required = true) String parentId,
            @RequestParam(value = "valid", required = false) Boolean valid,
            @RequestBody Querier querier);
}