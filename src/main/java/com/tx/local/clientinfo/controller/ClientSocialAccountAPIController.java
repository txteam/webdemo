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
import com.tx.local.clientinfo.facade.ClientSocialAccountFacade;
import com.tx.local.clientinfo.model.ClientSocialAccount;
import com.tx.local.clientinfo.service.ClientSocialAccountService;

import io.swagger.annotations.Api;

/**
 * 客户第三方账户API控制层[ClientSocialAccountAPIController]<br/>
 * 
 * @author []
 * @version [版本号]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@RestController
@Api(tags = "客户第三方账户API")
@RequestMapping("/api/clientSocialAccount")
public class ClientSocialAccountAPIController implements ClientSocialAccountFacade {
    
    //客户第三方账户业务层
    @Resource(name = "clientSocialAccountService")
    private ClientSocialAccountService clientSocialAccountService;
    
    /**
     * 新增客户第三方账户<br/>
     * <功能详细描述>
     * @param clientSocialAccount [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public ClientSocialAccount insert(@RequestBody ClientSocialAccount clientSocialAccount) {
        this.clientSocialAccountService.insert(clientSocialAccount);
        return clientSocialAccount;
    }
    
    /**
     * 根据id删除客户第三方账户<br/> 
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
        boolean flag = this.clientSocialAccountService.deleteById(id);
        return flag;
    }
    
    /**
     * 更新客户第三方账户<br/>
     * <功能详细描述>
     * @param clientSocialAccount
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public boolean updateById(@PathVariable(value = "id",required=true) String id,
    		@RequestBody ClientSocialAccount clientSocialAccount) {
        boolean flag = this.clientSocialAccountService.updateById(id,clientSocialAccount);
        return flag;
    }
    

    /**
     * 根据主键查询客户第三方账户<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return ClientSocialAccount [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public ClientSocialAccount findById(
            @PathVariable(value = "id", required = true) String id) {
        ClientSocialAccount res = this.clientSocialAccountService.findById(id);
        
        return res;
    }

    /**
     * 查询客户第三方账户实例列表<br/>
     * <功能详细描述>
     * @param querier
     * @return [参数说明]
     * 
     * @return List<ClientSocialAccount> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public List<ClientSocialAccount> queryList(
    		@RequestBody Querier querier
    	) {
        List<ClientSocialAccount> resList = this.clientSocialAccountService.queryList(
			querier         
        );
  
        return resList;
    }
    
    /**
     * 查询客户第三方账户分页列表<br/>
     * <功能详细描述>
     * @param pageIndex
     * @param pageSize
     * @param querier
     * @return [参数说明]
     * 
     * @return PagedList<ClientSocialAccount> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public PagedList<ClientSocialAccount> queryPagedList(
			@RequestBody Querier querier,
			@PathVariable(value = "pageNumber", required = true) int pageIndex,
            @PathVariable(value = "pageSize", required = true) int pageSize
    	) {
        PagedList<ClientSocialAccount> resPagedList = this.clientSocialAccountService.queryPagedList(
			querier,
			pageIndex,
			pageSize
        );
        return resPagedList;
    }
    
	/**
     * 查询客户第三方账户数量<br/>
     * <功能详细描述>
     * @param querier
     * @return [参数说明]
     * 
     * @return int [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public int count(
            @RequestBody Querier querier) {
        int count = this.clientSocialAccountService.count(
        	querier);
        
        return count;
    }

	/**
     * 查询客户第三方账户是否存在<br/>
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
        boolean flag = this.clientSocialAccountService.exists(querier, excludeId);
        
        return flag;
    }
}