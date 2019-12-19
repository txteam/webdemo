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
import com.tx.local.operator.facade.OperSecurityAccountFacade;
import com.tx.local.operator.model.OperSecurityAccount;
import com.tx.local.operator.service.OperSecurityAccountService;

import io.swagger.annotations.Api;

/**
 * 操作人员账户安全设置API控制层[OperSecurityAccountAPIController]<br/>
 * 
 * @author []
 * @version [版本号]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@RestController
@Api(tags = "操作人员账户安全设置API")
@RequestMapping("/api/operSecurityAccount")
public class OperSecurityAccountAPIController implements OperSecurityAccountFacade {
    
    //操作人员账户安全设置业务层
    @Resource(name = "operSecurityAccountService")
    private OperSecurityAccountService operSecurityAccountService;
    
    /**
     * 新增操作人员账户安全设置<br/>
     * <功能详细描述>
     * @param operSecurityAccount [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public OperSecurityAccount insert(@RequestBody OperSecurityAccount operSecurityAccount) {
        this.operSecurityAccountService.insert(operSecurityAccount);
        return operSecurityAccount;
    }
    
    /**
     * 根据id删除操作人员账户安全设置<br/> 
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
        boolean flag = this.operSecurityAccountService.deleteById(id);
        return flag;
    }
    
    /**
     * 更新操作人员账户安全设置<br/>
     * <功能详细描述>
     * @param operSecurityAccount
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public boolean updateById(@PathVariable(value = "id",required=true) String id,
    		@RequestBody OperSecurityAccount operSecurityAccount) {
        boolean flag = this.operSecurityAccountService.updateById(id,operSecurityAccount);
        return flag;
    }
    

    /**
     * 根据主键查询操作人员账户安全设置<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return OperSecurityAccount [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public OperSecurityAccount findById(
            @PathVariable(value = "id", required = true) String id) {
        OperSecurityAccount res = this.operSecurityAccountService.findById(id);
        
        return res;
    }

    /**
     * 查询操作人员账户安全设置实例列表<br/>
     * <功能详细描述>
     * @param querier
     * @return [参数说明]
     * 
     * @return List<OperSecurityAccount> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public List<OperSecurityAccount> queryList(
    		@RequestBody Querier querier
    	) {
        List<OperSecurityAccount> resList = this.operSecurityAccountService.queryList(
			querier         
        );
  
        return resList;
    }
    
    /**
     * 查询操作人员账户安全设置分页列表<br/>
     * <功能详细描述>
     * @param pageIndex
     * @param pageSize
     * @param querier
     * @return [参数说明]
     * 
     * @return PagedList<OperSecurityAccount> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public PagedList<OperSecurityAccount> queryPagedList(
			@RequestBody Querier querier,
			@PathVariable(value = "pageNumber", required = true) int pageIndex,
            @PathVariable(value = "pageSize", required = true) int pageSize
    	) {
        PagedList<OperSecurityAccount> resPagedList = this.operSecurityAccountService.queryPagedList(
			querier,
			pageIndex,
			pageSize
        );
        return resPagedList;
    }
    
	/**
     * 查询操作人员账户安全设置数量<br/>
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
        int count = this.operSecurityAccountService.count(
        	querier);
        
        return count;
    }

	/**
     * 查询操作人员账户安全设置是否存在<br/>
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
        boolean flag = this.operSecurityAccountService.exists(querier, excludeId);
        
        return flag;
    }
}