/*
 * 描       述:  <描述>
 * 修  改 人:  
 * 修改时间:
 * <修改描述:>
 */
package com.tx.local.clientinfo.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tx.core.paged.model.PagedList;
import com.tx.core.querier.model.Querier;
import com.tx.local.clientinfo.facade.ClientSourceFacade;
import com.tx.local.clientinfo.model.ClientSource;
import com.tx.local.clientinfo.service.ClientSourceService;

import io.swagger.annotations.Api;

/**
 * 客户来源API控制层[ClientSourceAPIController]<br/>
 * 
 * @author []
 * @version [版本号]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@RestController
@Api(tags = "客户来源API")
@RequestMapping("/api/clientSource")
public class ClientSourceAPIController implements ClientSourceFacade {
    
    //客户来源业务层
    @Resource(name = "clientSourceService")
    private ClientSourceService clientSourceService;
    
    /**
     * 新增客户来源<br/>
     * <功能详细描述>
     * @param clientSource [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public ClientSource insert(@RequestBody ClientSource clientSource) {
        this.clientSourceService.insert(clientSource);
        return clientSource;
    }
    
    /**
     * 根据id删除客户来源<br/> 
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
            @PathVariable(value = "id", required = true) String id) {
        boolean flag = this.clientSourceService.deleteById(id);
        return flag;
    }
    
    /**
     * 根据code删除客户来源<br/> 
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
            @PathVariable(value = "code", required = true) String code) {
        boolean flag = this.clientSourceService.deleteByCode(code);
        return flag;
    }
    
    /**
     * 更新客户来源<br/>
     * <功能详细描述>
     * @param clientSource
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public boolean updateById(
            @PathVariable(value = "id", required = true) String id,
            @RequestBody ClientSource clientSource) {
        boolean flag = this.clientSourceService.updateById(id, clientSource);
        return flag;
    }
    
    /**
     * 禁用客户来源<br/>
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
        boolean flag = this.clientSourceService.disableById(id);
        return flag;
    }
    
    /**
     * 启用客户来源<br/>
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
        boolean flag = this.clientSourceService.enableById(id);
        return flag;
    }
    
    /**
     * 根据主键查询客户来源<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return ClientSource [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public ClientSource findById(
            @PathVariable(value = "id", required = true) String id) {
        ClientSource res = this.clientSourceService.findById(id);
        
        return res;
    }
    
    /**
     * 根据编码查询客户来源<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return ClientSource [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public ClientSource findByCode(
            @PathVariable(value = "code", required = true) String code) {
        ClientSource res = this.clientSourceService.findByCode(code);
        
        return res;
    }
    
    /**
     * 查询客户来源实例列表<br/>
     * <功能详细描述>
     * @param valid
     * @param querier
     * @return [参数说明]
     * 
     * @return List<ClientSource> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public List<ClientSource> queryList(
            @RequestParam(value = "valid", required = false) Boolean valid,
            @RequestBody Querier querier) {
        List<ClientSource> resList = this.clientSourceService.queryList(valid,
                querier);
        
        return resList;
    }
    
    /**
     * 查询客户来源分页列表<br/>
     * <功能详细描述>
     * @param valid
     * @param pageIndex
     * @param pageSize
     * @param querier
     * @return [参数说明]
     * 
     * @return PagedList<ClientSource> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public PagedList<ClientSource> queryPagedList(
            @RequestParam(value = "valid", required = false) Boolean valid,
            @RequestBody Querier querier,
            @PathVariable(value = "pageNumber", required = true) int pageIndex,
            @PathVariable(value = "pageSize", required = true) int pageSize) {
        PagedList<ClientSource> resPagedList = this.clientSourceService
                .queryPagedList(valid, querier, pageIndex, pageSize);
        return resPagedList;
    }
    
    /**
     * 查询客户来源数量<br/>
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
        int count = this.clientSourceService.count(valid, querier);
        
        return count;
    }
    
    /**
     * 查询客户来源是否存在<br/>
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
        boolean flag = this.clientSourceService.exists(querier, excludeId);
        
        return flag;
    }
}