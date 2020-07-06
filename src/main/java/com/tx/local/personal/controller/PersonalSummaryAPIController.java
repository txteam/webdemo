/*
 * 描       述:  <描述>
 * 修  改 人:  
 * 修改时间:
 * <修改描述:>
 */
package com.tx.local.personal.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tx.core.paged.model.PagedList;
import com.tx.core.querier.model.Querier;
import com.tx.local.personal.facade.PersonalSummaryFacade;
import com.tx.local.personal.model.PersonalSummary;
import com.tx.local.personal.service.PersonalSummaryService;

import io.swagger.annotations.Api;

/**
 * PersonalSummaryAPI控制层[PersonalSummaryAPIController]<br/>
 * 
 * @author []
 * @version [版本号]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@RestController
@Api(tags = "PersonalSummaryAPI")
@RequestMapping("/api/personalSummary")
public class PersonalSummaryAPIController implements PersonalSummaryFacade {
    
    //PersonalSummary业务层
    @Resource(name = "personalSummaryService")
    private PersonalSummaryService personalSummaryService;
    
    /**
     * 新增PersonalSummary<br/>
     * <功能详细描述>
     * @param personalSummary [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public PersonalSummary insert(@RequestBody PersonalSummary personalSummary) {
        this.personalSummaryService.insert(personalSummary);
        return personalSummary;
    }
    
    /**
     * 根据id删除PersonalSummary<br/> 
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
        boolean flag = this.personalSummaryService.deleteById(id);
        return flag;
    }
    
    /**
     * 更新PersonalSummary<br/>
     * <功能详细描述>
     * @param personalSummary
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public boolean updateById(@PathVariable(value = "id",required=true) String id,
    		@RequestBody PersonalSummary personalSummary) {
        boolean flag = this.personalSummaryService.updateById(id,personalSummary);
        return flag;
    }
    

    /**
     * 根据主键查询PersonalSummary<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return PersonalSummary [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public PersonalSummary findById(
            @PathVariable(value = "id", required = true) String id) {
        PersonalSummary res = this.personalSummaryService.findById(id);
        
        return res;
    }

    /**
     * 查询PersonalSummary实例列表<br/>
     * <功能详细描述>
     * @param querier
     * @return [参数说明]
     * 
     * @return List<PersonalSummary> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public List<PersonalSummary> queryList(
    		@RequestBody Querier querier
    	) {
        List<PersonalSummary> resList = this.personalSummaryService.queryList(
			querier         
        );
  
        return resList;
    }
    
    /**
     * 查询PersonalSummary分页列表<br/>
     * <功能详细描述>
     * @param pageIndex
     * @param pageSize
     * @param querier
     * @return [参数说明]
     * 
     * @return PagedList<PersonalSummary> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public PagedList<PersonalSummary> queryPagedList(
			@RequestBody Querier querier,
			@PathVariable(value = "pageNumber", required = true) int pageIndex,
            @PathVariable(value = "pageSize", required = true) int pageSize
    	) {
        PagedList<PersonalSummary> resPagedList = this.personalSummaryService.queryPagedList(
			querier,
			pageIndex,
			pageSize
        );
        return resPagedList;
    }
    
	/**
     * 查询PersonalSummary数量<br/>
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
        int count = this.personalSummaryService.count(
        	querier);
        
        return count;
    }

	/**
     * 查询PersonalSummary是否存在<br/>
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
        boolean flag = this.personalSummaryService.exists(querier, excludeId);
        
        return flag;
    }
}