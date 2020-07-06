/*
 * 描       述:  <描述>
 * 修  改 人:  
 * 修改时间:
 * <修改描述:>
 */
package com.tx.local.creditinfo.facade;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.tx.core.paged.model.PagedList;
import com.tx.core.querier.model.Querier;
import com.tx.local.creditinfo.model.CreditInfoRecord;

import io.swagger.annotations.ApiOperation;

/**
 * CreditInfoRecord接口门面层[CreditInfoRecordFacade]<br/>
 * 
 * @author []
 * @version [版本号]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public interface CreditInfoRecordFacade {
    
    /**
     * 新增CreditInfoRecord<br/>
     * <功能详细描述>
     * @param creditInfoRecord [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ApiOperation(value = "新增信用信息版本")
    @RequestMapping(value = "", method = RequestMethod.POST)
    public CreditInfoRecord insert(
            @RequestBody CreditInfoRecord creditInfoRecord);
    
    /**
     * 根据id删除CreditInfoRecord<br/> 
     * <功能详细描述>
     * @param id
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ApiOperation(value = "根据ID删除信用信息")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public boolean deleteById(
            @PathVariable(value = "id", required = true) String id);
    
    /**
     * 更新CreditInfoRecord<br/>
     * <功能详细描述>
     * @param creditInfoRecord
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ApiOperation(value = "修改信用信息记录")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public boolean updateById(
            @PathVariable(value = "id", required = true) String id,
            @RequestBody CreditInfoRecord creditInfoRecord);
    
    /**
     * 根据主键查询CreditInfoRecord<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return CreditInfoRecord [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ApiOperation(value = "根据主键查询信用信息")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public CreditInfoRecord findById(
            @PathVariable(value = "id", required = true) String id);
    
    /**
     * 查询CreditInfoRecord实例列表<br/>
     * <功能详细描述>
     * @param querier
     * @return [参数说明]
     * 
     * @return List<CreditInfoRecord> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ApiOperation(value = "查询信用信息列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<CreditInfoRecord> queryList(@RequestBody Querier querier);
    
    /**
     * 查询CreditInfoRecord分页列表<br/>
     * <功能详细描述>
     * @param pageIndex
     * @param pageSize
     * @param querier
     * @return [参数说明]
     * 
     * @return PagedList<CreditInfoRecord> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ApiOperation(value = "查询信用信息分页列表")
    @RequestMapping(value = "/pagedlist/{pageSize}/{pageNumber}", method = RequestMethod.GET)
    public PagedList<CreditInfoRecord> queryPagedList(
            @RequestBody Querier querier,
            @PathVariable(value = "pageNumber", required = true) int pageIndex,
            @PathVariable(value = "pageSize", required = true) int pageSize);
    
    /**
     * 查询CreditInfoRecord数量<br/>
     * <功能详细描述>
     * @param querier
     * @return [参数说明]
     * 
     * @return int [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ApiOperation(value = "查询CreditInfoRecord数量")
    @RequestMapping(value = "/count", method = RequestMethod.GET)
    public int count(@RequestBody Querier querier);
    
    /**
     * 查询CreditInfoRecord是否存在<br/>
     * @param excludeId
     * @param querier
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ApiOperation(value = "查询CreditInfoRecord是否存在")
    @RequestMapping(value = "/exists", method = RequestMethod.GET)
    public boolean exists(@RequestBody Querier querier,
            @RequestParam(value = "excludeId", required = false) String excludeId);
}