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
import com.tx.local.operator.model.OperatorPost;

import io.swagger.annotations.ApiOperation;

/**
 * 职位接口门面层[OperatorPostFacade]<br/>
 * 
 * @author []
 * @version [版本号]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public interface OperatorPostFacade {
    
    /**
     * 新增职位<br/>
     * <功能详细描述>
     * @param operatorPost [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ApiOperation(value = "新增职位")
    @RequestMapping(value = "", method = RequestMethod.POST)
    public OperatorPost insert(@RequestBody OperatorPost operatorPost);
    
    /**
     * 根据id删除职位<br/> 
     * <功能详细描述>
     * @param id
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ApiOperation(value = "根据主键删除职位")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public boolean deleteById(
            @PathVariable(value = "id", required = true) String id);
    
    /**
     * 根据code删除职位<br/> 
     * <功能详细描述>
     * @param code
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ApiOperation(value = "根据编码删除职位")
    @RequestMapping(value = "/code/{code}", method = RequestMethod.DELETE)
    public boolean deleteByCode(
            @PathVariable(value = "code", required = true) String code);
    
    /**
     * 更新职位<br/>
     * <功能详细描述>
     * @param operatorPost
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ApiOperation(value = "修改职位")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public boolean updateById(
            @PathVariable(value = "id", required = true) String id,
            @RequestBody OperatorPost operatorPost);
    
    /**
     * 禁用职位<br/>
     * @param id
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ApiOperation(value = "禁用职位")
    @RequestMapping(value = "/disable/{id}", method = RequestMethod.PATCH)
    public boolean disableById(
            @PathVariable(value = "id", required = true) String id);
    
    /**
     * 启用职位<br/>
     * <功能详细描述>
     * @param id
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ApiOperation(value = "启用职位")
    @RequestMapping(value = "/enable/{id}", method = RequestMethod.PATCH)
    public boolean enableById(
            @PathVariable(value = "id", required = true) String id);
    
    /**
     * 根据主键查询职位<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return OperatorPost [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ApiOperation(value = "根据主键查询职位")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public OperatorPost findById(
            @PathVariable(value = "id", required = true) String id);
    
    /**
     * 根据编码查询职位<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return OperatorPost [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ApiOperation(value = "根据编码查询职位")
    @RequestMapping(value = "/code/{code}", method = RequestMethod.GET)
    public OperatorPost findByCode(
            @PathVariable(value = "code", required = true) String code);
    
    /**
     * 查询职位实例列表<br/>
     * <功能详细描述>
     * @param valid
     * @param querier
     * @return [参数说明]
     * 
     * @return List<OperatorPost> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ApiOperation(value = "查询职位列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<OperatorPost> queryList(
            @RequestParam(value = "valid", required = false) Boolean valid,
            @RequestBody Querier querier);
    
    /**
     * 查询职位分页列表<br/>
     * <功能详细描述>
     * @param valid
     * @param pageIndex
     * @param pageSize
     * @param querier
     * @return [参数说明]
     * 
     * @return PagedList<OperatorPost> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ApiOperation(value = "查询职位分页列表")
    @RequestMapping(value = "/pagedlist/{pageSize}/{pageNumber}", method = RequestMethod.GET)
    public PagedList<OperatorPost> queryPagedList(
            @RequestParam(value = "valid", required = false) Boolean valid,
            @RequestBody Querier querier,
            @PathVariable(value = "pageNumber", required = true) int pageIndex,
            @PathVariable(value = "pageSize", required = true) int pageSize);
    
    /**
     * 查询职位数量<br/>
     * <功能详细描述>
     * @param valid
     * @param querier
     * @return [参数说明]
     * 
     * @return int [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ApiOperation(value = "查询职位数量")
    @RequestMapping(value = "/count", method = RequestMethod.GET)
    public int count(
            @RequestParam(value = "valid", required = false) Boolean valid,
            @RequestBody Querier querier);
    
    /**
     * 查询职位是否存在<br/>
     * @param excludeId
     * @param querier
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ApiOperation(value = "查询职位是否存在")
    @RequestMapping(value = "/exists", method = RequestMethod.GET)
    public boolean exists(@RequestBody Querier querier,
            @RequestParam(value = "excludeId", required = false) String excludeId);
    
    /**
     * 根据条件查询查询职位子代列表<br/>
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
    @ApiOperation(value = "根据条件查询查询职位子代列表")
    @RequestMapping(value = "/children/{parentId}", method = RequestMethod.GET)
    public List<OperatorPost> queryChildrenByParentId(
            @PathVariable(value = "parentId", required = true) String parentId,
            @RequestParam(value = "valid", required = false) Boolean valid,
            @RequestBody Querier querier);
    
    /**
     * 根据条件查询查询职位后代列表<br/>
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
    @ApiOperation(value = "根据条件查询查询职位后代列表")
    @RequestMapping(value = "/descendants/{parentId}", method = RequestMethod.GET)
    public List<OperatorPost> queryDescendantsByParentId(
            @PathVariable(value = "parentId", required = true) String parentId,
            @RequestParam(value = "valid", required = false) Boolean valid,
            @RequestBody Querier querier);
}