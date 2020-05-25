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
import com.tx.local.institution.facade.InstitutionInfoFacade;
import com.tx.local.institution.model.InstitutionInfo;
import com.tx.local.institution.service.InstitutionInfoService;

import io.swagger.annotations.Api;

/**
 * InstitutionInfoAPI控制层[InstitutionInfoAPIController]<br/>
 * 
 * @author []
 * @version [版本号]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@RestController
@Api(tags = "InstitutionInfoAPI")
@RequestMapping("/api/institutionInfo")
public class InstitutionInfoAPIController implements InstitutionInfoFacade {
    
    //InstitutionInfo业务层
    @Resource(name = "institutionInfoService")
    private InstitutionInfoService institutionInfoService;
    
    /**
     * 新增InstitutionInfo<br/>
     * <功能详细描述>
     * @param institutionInfo [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public InstitutionInfo insert(@RequestBody InstitutionInfo institutionInfo) {
        this.institutionInfoService.insert(institutionInfo);
        return institutionInfo;
    }
    
    /**
     * 根据id删除InstitutionInfo<br/> 
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
        boolean flag = this.institutionInfoService.deleteById(id);
        return flag;
    }
    
    /**
     * 更新InstitutionInfo<br/>
     * <功能详细描述>
     * @param institutionInfo
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public boolean updateById(@PathVariable(value = "id",required=true) String id,
    		@RequestBody InstitutionInfo institutionInfo) {
        boolean flag = this.institutionInfoService.updateById(id,institutionInfo);
        return flag;
    }
    

    /**
     * 根据主键查询InstitutionInfo<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return InstitutionInfo [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public InstitutionInfo findById(
            @PathVariable(value = "id", required = true) String id) {
        InstitutionInfo res = this.institutionInfoService.findById(id);
        
        return res;
    }

    /**
     * 查询InstitutionInfo实例列表<br/>
     * <功能详细描述>
     * @param querier
     * @return [参数说明]
     * 
     * @return List<InstitutionInfo> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public List<InstitutionInfo> queryList(
    		@RequestBody Querier querier
    	) {
        List<InstitutionInfo> resList = this.institutionInfoService.queryList(
			querier         
        );
  
        return resList;
    }
    
    /**
     * 查询InstitutionInfo分页列表<br/>
     * <功能详细描述>
     * @param pageIndex
     * @param pageSize
     * @param querier
     * @return [参数说明]
     * 
     * @return PagedList<InstitutionInfo> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public PagedList<InstitutionInfo> queryPagedList(
			@RequestBody Querier querier,
			@PathVariable(value = "pageNumber", required = true) int pageIndex,
            @PathVariable(value = "pageSize", required = true) int pageSize
    	) {
        PagedList<InstitutionInfo> resPagedList = this.institutionInfoService.queryPagedList(
			querier,
			pageIndex,
			pageSize
        );
        return resPagedList;
    }
    
	/**
     * 查询InstitutionInfo数量<br/>
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
        int count = this.institutionInfoService.count(
        	querier);
        
        return count;
    }

	/**
     * 查询InstitutionInfo是否存在<br/>
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
        boolean flag = this.institutionInfoService.exists(querier, excludeId);
        
        return flag;
    }
}