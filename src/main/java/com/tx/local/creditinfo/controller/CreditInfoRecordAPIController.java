/*
 * 描       述:  <描述>
 * 修  改 人:  
 * 修改时间:
 * <修改描述:>
 */
package com.tx.local.creditinfo.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tx.core.paged.model.PagedList;
import com.tx.core.querier.model.Querier;
import com.tx.local.creditinfo.facade.CreditInfoRecordFacade;
import com.tx.local.creditinfo.model.CreditInfoRecord;
import com.tx.local.creditinfo.service.CreditInfoRecordService;

import io.swagger.annotations.Api;

/**
 * CreditInfoRecordAPI控制层[CreditInfoRecordAPIController]<br/>
 * 
 * @author []
 * @version [版本号]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@RestController
@Api(tags = "CreditInfoRecordAPI")
@RequestMapping("/api/creditInfoRecord")
public class CreditInfoRecordAPIController implements CreditInfoRecordFacade {
    
    //CreditInfoRecord业务层
    @Resource(name = "creditInfoRecordService")
    private CreditInfoRecordService creditInfoRecordService;
    
    /**
     * 新增CreditInfoRecord<br/>
     * <功能详细描述>
     * @param creditInfoRecord [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public CreditInfoRecord insert(@RequestBody CreditInfoRecord creditInfoRecord) {
        this.creditInfoRecordService.insert(creditInfoRecord);
        return creditInfoRecord;
    }
    
    /**
     * 根据id删除CreditInfoRecord<br/> 
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
        boolean flag = this.creditInfoRecordService.deleteById(id);
        return flag;
    }
    
    /**
     * 更新CreditInfoRecord<br/>
     * <功能详细描述>
     * @param creditInfoRecord
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public boolean updateById(@PathVariable(value = "id",required=true) String id,
    		@RequestBody CreditInfoRecord creditInfoRecord) {
        boolean flag = this.creditInfoRecordService.updateById(id,creditInfoRecord);
        return flag;
    }
    

    /**
     * 根据主键查询CreditInfoRecord<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return CreditInfoRecord [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public CreditInfoRecord findById(
            @PathVariable(value = "id", required = true) String id) {
        CreditInfoRecord res = this.creditInfoRecordService.findById(id);
        
        return res;
    }

    /**
     * 查询CreditInfoRecord实例列表<br/>
     * <功能详细描述>
     * @param querier
     * @return [参数说明]
     * 
     * @return List<CreditInfoRecord> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public List<CreditInfoRecord> queryList(
    		@RequestBody Querier querier
    	) {
        List<CreditInfoRecord> resList = this.creditInfoRecordService.queryList(
			querier         
        );
  
        return resList;
    }
    
    /**
     * 查询CreditInfoRecord分页列表<br/>
     * <功能详细描述>
     * @param pageIndex
     * @param pageSize
     * @param querier
     * @return [参数说明]
     * 
     * @return PagedList<CreditInfoRecord> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public PagedList<CreditInfoRecord> queryPagedList(
			@RequestBody Querier querier,
			@PathVariable(value = "pageNumber", required = true) int pageIndex,
            @PathVariable(value = "pageSize", required = true) int pageSize
    	) {
        PagedList<CreditInfoRecord> resPagedList = this.creditInfoRecordService.queryPagedList(
			querier,
			pageIndex,
			pageSize
        );
        return resPagedList;
    }
    
	/**
     * 查询CreditInfoRecord数量<br/>
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
        int count = this.creditInfoRecordService.count(
        	querier);
        
        return count;
    }

	/**
     * 查询CreditInfoRecord是否存在<br/>
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
        boolean flag = this.creditInfoRecordService.exists(querier, excludeId);
        
        return flag;
    }
}