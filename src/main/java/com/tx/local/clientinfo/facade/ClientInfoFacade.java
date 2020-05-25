/*
 * 描       述:  <描述>
 * 修  改 人:  
 * 修改时间:
 * <修改描述:>
 */
package com.tx.local.clientinfo.facade;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.tx.core.paged.model.PagedList;
import com.tx.core.querier.model.Querier;
import com.tx.local.clientinfo.model.ClientInfo;

import io.swagger.annotations.ApiOperation;

/**
 * ClientInfo接口门面层[ClientInfoFacade]<br/>
 * 
 * @author []
 * @version [版本号]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public interface ClientInfoFacade {
    
    /**
     * 新增ClientInfo<br/>
     * <功能详细描述>
     * @param clientInfo [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ApiOperation(value = "新增客户信息")
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ClientInfo insert(@RequestBody ClientInfo clientInfo);
    
    /**
     * 根据id删除ClientInfo<br/> 
     * <功能详细描述>
     * @param id
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ApiOperation(value = "根据ID删除客户信息")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public boolean deleteById(
            @PathVariable(value = "id", required = true) String id);
    
    /**
     * 更新ClientInfo<br/>
     * <功能详细描述>
     * @param clientInfo
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ApiOperation(value = "修改客户信息")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public boolean updateById(
            @PathVariable(value = "id", required = true) String id,
            @RequestBody ClientInfo clientInfo);
    
    /**
     * 禁用ClientInfo<br/>
     * @param id
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ApiOperation(value = "禁用客户信息")
    @RequestMapping(value = "/disable/{id}", method = RequestMethod.PATCH)
    public boolean disableById(
            @PathVariable(value = "id", required = true) String id);
    
    /**
     * 启用ClientInfo<br/>
     * <功能详细描述>
     * @param id
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ApiOperation(value = "启用客户信息")
    @RequestMapping(value = "/enable/{id}", method = RequestMethod.PATCH)
    public boolean enableById(
            @PathVariable(value = "id", required = true) String id);
    
    /**
     * 根据主键查询ClientInfo<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return ClientInfo [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ApiOperation(value = "根据主键查询客户信息")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ClientInfo findById(
            @PathVariable(value = "id", required = true) String id);
    
    /**
     * 根据主键查询ClientInfo<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return ClientInfo [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ApiOperation(value = "根据用户名查询客户信息")
    @RequestMapping(value = "/username/{username}", method = RequestMethod.GET)
    public ClientInfo findByUsername(
            @PathVariable(value = "username", required = true) String username);
    
    /**
     * 查询ClientInfo实例列表<br/>
     * <功能详细描述>
     * @param valid
     * @param querier
     * @return [参数说明]
     * 
     * @return List<ClientInfo> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ApiOperation(value = "查询客户信息列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<ClientInfo> queryList(
            @RequestParam(value = "valid", required = false) Boolean valid,
            @RequestBody Querier querier);
    
    /**
     * 查询ClientInfo分页列表<br/>
     * <功能详细描述>
     * @param valid
     * @param pageIndex
     * @param pageSize
     * @param querier
     * @return [参数说明]
     * 
     * @return PagedList<ClientInfo> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ApiOperation(value = "查询客户信息分页列表")
    @RequestMapping(value = "/pagedlist/{pageSize}/{pageNumber}", method = RequestMethod.GET)
    public PagedList<ClientInfo> queryPagedList(
            @RequestParam(value = "valid", required = false) Boolean valid,
            @RequestBody Querier querier,
            @PathVariable(value = "pageNumber", required = true) int pageIndex,
            @PathVariable(value = "pageSize", required = true) int pageSize);
    
    /**
     * 查询ClientInfo数量<br/>
     * <功能详细描述>
     * @param valid
     * @param querier
     * @return [参数说明]
     * 
     * @return int [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ApiOperation(value = "查询客户信息数量")
    @RequestMapping(value = "/count", method = RequestMethod.GET)
    public int count(
            @RequestParam(value = "valid", required = false) Boolean valid,
            @RequestBody Querier querier);
    
    /**
     * 查询ClientInfo是否存在<br/>
     * @param excludeId
     * @param querier
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ApiOperation(value = "查询客户信息是否存在")
    @RequestMapping(value = "/exists", method = RequestMethod.GET)
    public boolean exists(@RequestBody Querier querier,
            @RequestParam(value = "excludeId", required = false) String excludeId);
}