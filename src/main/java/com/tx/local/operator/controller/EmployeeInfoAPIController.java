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
import com.tx.local.operator.facade.EmployeeInfoFacade;
import com.tx.local.operator.model.EmployeeInfo;
import com.tx.local.operator.service.EmployeeInfoService;

import io.swagger.annotations.Api;

/**
 * 员工信息API控制层[EmployeeInfoAPIController]<br/>
 * 
 * @author []
 * @version [版本号]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@RestController
@Api(tags = "员工信息API")
@RequestMapping("/api/employeeInfo")
public class EmployeeInfoAPIController implements EmployeeInfoFacade {
    
    //员工信息业务层
    @Resource(name = "employeeInfoService")
    private EmployeeInfoService employeeInfoService;
    
    /**
     * 新增员工信息<br/>
     * <功能详细描述>
     * @param employeeInfo [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public EmployeeInfo insert(@RequestBody EmployeeInfo employeeInfo) {
        this.employeeInfoService.insert(employeeInfo);
        return employeeInfo;
    }
    
    /**
     * 根据id删除员工信息<br/> 
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
        boolean flag = this.employeeInfoService.deleteById(id);
        return flag;
    }
	
	/**
     * 根据code删除员工信息<br/> 
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
    		@PathVariable(value = "code",required=true) String code){
        boolean flag = this.employeeInfoService.deleteByCode(code);
        return flag;    
    }
    
    /**
     * 更新员工信息<br/>
     * <功能详细描述>
     * @param employeeInfo
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public boolean updateById(@PathVariable(value = "id",required=true) String id,
    		@RequestBody EmployeeInfo employeeInfo) {
        boolean flag = this.employeeInfoService.updateById(id,employeeInfo);
        return flag;
    }
    

    /**
     * 根据主键查询员工信息<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return EmployeeInfo [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public EmployeeInfo findById(
            @PathVariable(value = "id", required = true) String id) {
        EmployeeInfo res = this.employeeInfoService.findById(id);
        
        return res;
    }

    /**
     * 根据编码查询员工信息<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return EmployeeInfo [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public EmployeeInfo findByCode(
            @PathVariable(value = "code", required = true) String code) {
        EmployeeInfo res = this.employeeInfoService.findByCode(code);
        
        return res;
    }

    /**
     * 查询员工信息实例列表<br/>
     * <功能详细描述>
     * @param querier
     * @return [参数说明]
     * 
     * @return List<EmployeeInfo> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public List<EmployeeInfo> queryList(
    		@RequestBody Querier querier
    	) {
        List<EmployeeInfo> resList = this.employeeInfoService.queryList(
			querier         
        );
  
        return resList;
    }
    
    /**
     * 查询员工信息分页列表<br/>
     * <功能详细描述>
     * @param pageIndex
     * @param pageSize
     * @param querier
     * @return [参数说明]
     * 
     * @return PagedList<EmployeeInfo> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public PagedList<EmployeeInfo> queryPagedList(
			@RequestBody Querier querier,
			@PathVariable(value = "pageNumber", required = true) int pageIndex,
            @PathVariable(value = "pageSize", required = true) int pageSize
    	) {
        PagedList<EmployeeInfo> resPagedList = this.employeeInfoService.queryPagedList(
			querier,
			pageIndex,
			pageSize
        );
        return resPagedList;
    }
    
	/**
     * 查询员工信息数量<br/>
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
        int count = this.employeeInfoService.count(
        	querier);
        
        return count;
    }

	/**
     * 查询员工信息是否存在<br/>
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
        boolean flag = this.employeeInfoService.exists(querier, excludeId);
        
        return flag;
    }
}