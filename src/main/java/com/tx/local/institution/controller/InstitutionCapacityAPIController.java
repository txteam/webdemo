/*
 * 描       述:  <描述>
 * 修  改 人:  
 * 修改时间:
 * <修改描述:>
 */
package com.tx.local.institution.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tx.core.paged.model.PagedList;
import com.tx.core.querier.model.Querier;
import com.tx.local.institution.facade.InstitutionCapacityFacade;
import com.tx.local.institution.model.InstitutionCapacity;
import com.tx.local.institution.service.InstitutionCapacityService;

import io.swagger.annotations.Api;

/**
 * 机构产能API控制层[InstitutionCapacityAPIController]<br/>
 * 
 * @author []
 * @version [版本号]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@RestController
@Api(tags = "机构产能API")
@RequestMapping("/api/institutionCapacity")
public class InstitutionCapacityAPIController implements InstitutionCapacityFacade {
    
    //机构产能业务层
    @Resource(name = "institutionCapacityService")
    private InstitutionCapacityService institutionCapacityService;
    
    /**
     * 新增机构产能<br/>
     * <功能详细描述>
     * @param institutionCapacity [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public InstitutionCapacity insert(@RequestBody InstitutionCapacity institutionCapacity) {
        this.institutionCapacityService.insert(institutionCapacity);
        return institutionCapacity;
    }
    
    /**
     * 根据id删除机构产能<br/> 
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
        boolean flag = this.institutionCapacityService.deleteById(id);
        return flag;
    }
    
    /**
     * 更新机构产能<br/>
     * <功能详细描述>
     * @param institutionCapacity
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public boolean updateById(@PathVariable(value = "id",required=true) String id,
    		@RequestBody InstitutionCapacity institutionCapacity) {
        boolean flag = this.institutionCapacityService.updateById(id,institutionCapacity);
        return flag;
    }
    

    /**
     * 根据主键查询机构产能<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return InstitutionCapacity [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public InstitutionCapacity findById(
            @PathVariable(value = "id", required = true) String id) {
        InstitutionCapacity res = this.institutionCapacityService.findById(id);
        
        return res;
    }

    /**
     * 查询机构产能实例列表<br/>
     * <功能详细描述>
     * @param querier
     * @return [参数说明]
     * 
     * @return List<InstitutionCapacity> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public List<InstitutionCapacity> queryList(
    		@RequestBody Querier querier
    	) {
        List<InstitutionCapacity> resList = this.institutionCapacityService.queryList(
			querier         
        );
  
        return resList;
    }
    
    /**
     * 查询机构产能分页列表<br/>
     * <功能详细描述>
     * @param pageIndex
     * @param pageSize
     * @param querier
     * @return [参数说明]
     * 
     * @return PagedList<InstitutionCapacity> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public PagedList<InstitutionCapacity> queryPagedList(
			@RequestBody Querier querier,
			@PathVariable(value = "pageNumber", required = true) int pageIndex,
            @PathVariable(value = "pageSize", required = true) int pageSize
    	) {
        PagedList<InstitutionCapacity> resPagedList = this.institutionCapacityService.queryPagedList(
			querier,
			pageIndex,
			pageSize
        );
        return resPagedList;
    }
    
	/**
     * 查询机构产能数量<br/>
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
        int count = this.institutionCapacityService.count(
        	querier);
        
        return count;
    }

	/**
     * 查询机构产能是否存在<br/>
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
        boolean flag = this.institutionCapacityService.exists(querier, excludeId);
        
        return flag;
    }
}