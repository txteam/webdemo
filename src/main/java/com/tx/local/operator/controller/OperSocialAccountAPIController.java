/*
 * 描       述:  <描述>
 * 修  改 人:  
 * 修改时间:
 * <修改描述:>
 */
package com.tx.local.operator.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tx.core.paged.model.PagedList;
import com.tx.core.querier.model.Querier;
import com.tx.local.operator.facade.OperSocialAccountFacade;
import com.tx.local.operator.model.OperSocialAccount;
import com.tx.local.operator.service.OperSocialAccountService;

import io.swagger.annotations.Api;

/**
 * 操作人员第三方账户API控制层[OperSocialAccountAPIController]<br/>
 * 
 * @author []
 * @version [版本号]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@RestController
@Api(tags = "操作人员第三方账户API")
@RequestMapping("/api/operSocialAccount")
public class OperSocialAccountAPIController implements OperSocialAccountFacade {
    
    //操作人员第三方账户业务层
    @Resource(name = "operSocialAccountService")
    private OperSocialAccountService operSocialAccountService;
    
    /**
     * 新增操作人员第三方账户<br/>
     * <功能详细描述>
     * @param operSocialAccount [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public OperSocialAccount insert(@RequestBody OperSocialAccount operSocialAccount) {
        this.operSocialAccountService.insert(operSocialAccount);
        return operSocialAccount;
    }
    
    /**
     * 根据id删除操作人员第三方账户<br/> 
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
        boolean flag = this.operSocialAccountService.deleteById(id);
        return flag;
    }
    
    /**
     * 更新操作人员第三方账户<br/>
     * <功能详细描述>
     * @param operSocialAccount
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public boolean updateById(@PathVariable(value = "id",required=true) String id,
    		@RequestBody OperSocialAccount operSocialAccount) {
        boolean flag = this.operSocialAccountService.updateById(id,operSocialAccount);
        return flag;
    }
    

    /**
     * 根据主键查询操作人员第三方账户<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return OperSocialAccount [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public OperSocialAccount findById(
            @PathVariable(value = "id", required = true) String id) {
        OperSocialAccount res = this.operSocialAccountService.findById(id);
        
        return res;
    }

    /**
     * 查询操作人员第三方账户实例列表<br/>
     * <功能详细描述>
     * @param querier
     * @return [参数说明]
     * 
     * @return List<OperSocialAccount> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public List<OperSocialAccount> queryList(
    		@RequestBody Querier querier
    	) {
        List<OperSocialAccount> resList = this.operSocialAccountService.queryList(
			querier         
        );
  
        return resList;
    }
    
    /**
     * 查询操作人员第三方账户分页列表<br/>
     * <功能详细描述>
     * @param pageIndex
     * @param pageSize
     * @param querier
     * @return [参数说明]
     * 
     * @return PagedList<OperSocialAccount> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public PagedList<OperSocialAccount> queryPagedList(
			@RequestBody Querier querier,
			@PathVariable(value = "pageNumber", required = true) int pageIndex,
            @PathVariable(value = "pageSize", required = true) int pageSize
    	) {
        PagedList<OperSocialAccount> resPagedList = this.operSocialAccountService.queryPagedList(
			querier,
			pageIndex,
			pageSize
        );
        return resPagedList;
    }
    
	/**
     * 查询操作人员第三方账户数量<br/>
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
        int count = this.operSocialAccountService.count(
        	querier);
        
        return count;
    }

	/**
     * 查询操作人员第三方账户是否存在<br/>
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
        boolean flag = this.operSocialAccountService.exists(querier, excludeId);
        
        return flag;
    }
}