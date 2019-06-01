/*
 * 描       述:  <描述>
 * 修  改 人:  
 * 修改时间:
 * <修改描述:>
 */
package com.tx.local.clientinfo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tx.core.paged.model.PagedList;
import com.tx.local.clientinfo.model.ClientSource;
import com.tx.local.clientinfo.service.ClientSourceService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 客户来源API控制层<br/>
 * 
 * @author []
 * @version [版本号]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@RestController
@Api(tags = "客户来源API")
@RequestMapping("/api/ClientSource")
public class ClientSourceAPIController {
    
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
    @ApiOperation(value = "新增客户来源")
    @RequestMapping(value = "", method = RequestMethod.POST)
    public boolean insert(@RequestBody ClientSource clientSource) {
        this.clientSourceService.insert(clientSource);
        return true;
    }
    
    /**
     * 删除客户来源<br/> 
     * <功能详细描述>
     * @param id
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ApiOperation(value = "删除客户来源")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE) 
    public boolean deleteById(
    	@PathVariable(value = "id",required=true) String id) {
        boolean flag = this.clientSourceService.deleteById(id);
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
    @ApiOperation(value = "修改客户来源")
    @RequestMapping(value = "", method = RequestMethod.PUT)
    public boolean update(@RequestBody ClientSource clientSource) {
        boolean flag = this.clientSourceService.updateById(clientSource);
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
	@ApiOperation(value = "禁用客户来源")
    @RequestMapping(value = "/disable/{id}", method = RequestMethod.PATCH)
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
    @ApiOperation(value = "启用客户来源")
    @RequestMapping(value = "/enable/{id}", method = RequestMethod.PATCH)
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
    @ApiOperation(value = "根据主键查询客户来源")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
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
    @ApiOperation(value = "根据编码查询客户来源")
    @RequestMapping(value = "/code/{code}", method = RequestMethod.GET)
    public ClientSource findByCode(
            @PathVariable(value = "code", required = true) String code) {
        ClientSource res = this.clientSourceService.findByCode(code);
        
        return res;
    }

	/**
     * 查询客户来源实例列表<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<ClientSource> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ApiOperation(value = "查询客户来源列表")
    @RequestMapping(value = "/list/{valid}", method = RequestMethod.GET)
    public List<ClientSource> queryList(
			@PathVariable(value = "valid", required = false) Boolean valid,
    		@RequestParam MultiValueMap<String, String> request
    	) {
        Map<String,Object> params = new HashMap<>();
        //params.put("",request.getFirst(""));
    	
        List<ClientSource> resList = this.clientSourceService.queryList(
			valid,
			params         
        );
  
        return resList;
    }
    
    /**
     * 查询客户来源分页列表<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<ClientSource> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ApiOperation(value = "查询客户来源分页列表")
    @RequestMapping(value = "/pagedlist/{pageSize}/{pageNumber}/{valid}", method = RequestMethod.GET)
    public PagedList<ClientSource> queryPagedList(
			@PathVariable(value = "valid", required = false) Boolean valid,
			@PathVariable(value = "pageNumber", required = true) int pageIndex,
            @PathVariable(value = "pageSize", required = true) int pageSize,
            @RequestParam MultiValueMap<String, String> request
    	) {
		Map<String,Object> params = new HashMap<>();
		//params.put("",request.getFirst(""));

        PagedList<ClientSource> resPagedList = this.clientSourceService.queryPagedList(
			valid,
			params,
			pageIndex,
			pageSize
        );
        return resPagedList;
    }

	/**
     * 查询客户来源是否存在<br/>
	 * @param excludeId
     * @param params
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ApiOperation(value = "查询客户来源是否存在")
    @RequestMapping(value = "/exists/{excludeId}", method = RequestMethod.GET)
    public boolean exists(
            @PathVariable(value = "excludeId", required = false) String excludeId,
            @RequestParam Map<String, String> params) {
        boolean flag = this.clientSourceService.exists(params, excludeId);
        
        return flag;
    }
}