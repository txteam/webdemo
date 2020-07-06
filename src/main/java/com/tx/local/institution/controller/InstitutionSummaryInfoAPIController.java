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
import com.tx.local.institution.facade.InstitutionSummaryInfoFacade;
import com.tx.local.institution.model.InstitutionSummaryInfo;
import com.tx.local.institution.service.InstitutionSummaryInfoService;

import io.swagger.annotations.Api;

/**
 * InstitutionSummaryInfoAPI控制层[InstitutionSummaryInfoAPIController]<br/>
 * 
 * @author []
 * @version [版本号]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@RestController
@Api(tags = "InstitutionSummaryInfoAPI")
@RequestMapping("/api/institutionSummaryInfo")
public class InstitutionSummaryInfoAPIController implements InstitutionSummaryInfoFacade {
    
    //InstitutionSummaryInfo业务层
    @Resource(name = "institutionSummaryInfoService")
    private InstitutionSummaryInfoService institutionSummaryInfoService;
    
    /**
     * 新增InstitutionSummaryInfo<br/>
     * <功能详细描述>
     * @param institutionSummaryInfo [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public InstitutionSummaryInfo insert(@RequestBody InstitutionSummaryInfo institutionSummaryInfo) {
        this.institutionSummaryInfoService.insert(institutionSummaryInfo);
        return institutionSummaryInfo;
    }
    
    /**
     * 根据id删除InstitutionSummaryInfo<br/> 
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
        boolean flag = this.institutionSummaryInfoService.deleteById(id);
        return flag;
    }
    
    /**
     * 更新InstitutionSummaryInfo<br/>
     * <功能详细描述>
     * @param institutionSummaryInfo
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public boolean updateById(@PathVariable(value = "id",required=true) String id,
    		@RequestBody InstitutionSummaryInfo institutionSummaryInfo) {
        boolean flag = this.institutionSummaryInfoService.updateById(id,institutionSummaryInfo);
        return flag;
    }
    

    /**
     * 根据主键查询InstitutionSummaryInfo<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return InstitutionSummaryInfo [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public InstitutionSummaryInfo findById(
            @PathVariable(value = "id", required = true) String id) {
        InstitutionSummaryInfo res = this.institutionSummaryInfoService.findById(id);
        
        return res;
    }

    /**
     * 查询InstitutionSummaryInfo实例列表<br/>
     * <功能详细描述>
     * @param querier
     * @return [参数说明]
     * 
     * @return List<InstitutionSummaryInfo> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public List<InstitutionSummaryInfo> queryList(
    		@RequestBody Querier querier
    	) {
        List<InstitutionSummaryInfo> resList = this.institutionSummaryInfoService.queryList(
			querier         
        );
  
        return resList;
    }
    
    /**
     * 查询InstitutionSummaryInfo分页列表<br/>
     * <功能详细描述>
     * @param pageIndex
     * @param pageSize
     * @param querier
     * @return [参数说明]
     * 
     * @return PagedList<InstitutionSummaryInfo> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public PagedList<InstitutionSummaryInfo> queryPagedList(
			@RequestBody Querier querier,
			@PathVariable(value = "pageNumber", required = true) int pageIndex,
            @PathVariable(value = "pageSize", required = true) int pageSize
    	) {
        PagedList<InstitutionSummaryInfo> resPagedList = this.institutionSummaryInfoService.queryPagedList(
			querier,
			pageIndex,
			pageSize
        );
        return resPagedList;
    }
    
	/**
     * 查询InstitutionSummaryInfo数量<br/>
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
        int count = this.institutionSummaryInfoService.count(
        	querier);
        
        return count;
    }

	/**
     * 查询InstitutionSummaryInfo是否存在<br/>
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
        boolean flag = this.institutionSummaryInfoService.exists(querier, excludeId);
        
        return flag;
    }
}