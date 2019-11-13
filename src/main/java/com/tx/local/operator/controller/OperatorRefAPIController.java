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
import com.tx.local.operator.facade.OperatorRefFacade;
import com.tx.local.operator.model.OperatorRef;
import com.tx.local.operator.service.OperatorRefService;

import io.swagger.annotations.Api;

/**
 * OperatorRefAPI控制层[OperatorRefAPIController]<br/>
 * 
 * @author []
 * @version [版本号]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@RestController
@Api(tags = "OperatorRefAPI")
@RequestMapping("/api/operatorRef")
public class OperatorRefAPIController implements OperatorRefFacade {
    
    //OperatorRef业务层
    @Resource(name = "operatorRefService")
    private OperatorRefService operatorRefService;
    
    /**
     * 新增OperatorRef<br/>
     * <功能详细描述>
     * @param operatorRef [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public OperatorRef insert(@RequestBody OperatorRef operatorRef) {
        this.operatorRefService.insert(operatorRef);
        return operatorRef;
    }
    
    /**
     * 根据id删除OperatorRef<br/> 
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
        boolean flag = this.operatorRefService.deleteById(id);
        return flag;
    }
    
    /**
     * 更新OperatorRef<br/>
     * <功能详细描述>
     * @param operatorRef
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public boolean updateById(@PathVariable(value = "id",required=true) String id,
    		@RequestBody OperatorRef operatorRef) {
        boolean flag = this.operatorRefService.updateById(id,operatorRef);
        return flag;
    }
    

    /**
     * 根据主键查询OperatorRef<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return OperatorRef [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public OperatorRef findById(
            @PathVariable(value = "id", required = true) String id) {
        OperatorRef res = this.operatorRefService.findById(id);
        
        return res;
    }

    /**
     * 查询OperatorRef实例列表<br/>
     * <功能详细描述>
     * @param querier
     * @return [参数说明]
     * 
     * @return List<OperatorRef> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public List<OperatorRef> queryList(
    		@RequestBody Querier querier
    	) {
        List<OperatorRef> resList = this.operatorRefService.queryList(
			querier         
        );
  
        return resList;
    }
    
    /**
     * 查询OperatorRef分页列表<br/>
     * <功能详细描述>
     * @param pageIndex
     * @param pageSize
     * @param querier
     * @return [参数说明]
     * 
     * @return PagedList<OperatorRef> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public PagedList<OperatorRef> queryPagedList(
			@RequestBody Querier querier,
			@PathVariable(value = "pageNumber", required = true) int pageIndex,
            @PathVariable(value = "pageSize", required = true) int pageSize
    	) {
        PagedList<OperatorRef> resPagedList = this.operatorRefService.queryPagedList(
			querier,
			pageIndex,
			pageSize
        );
        return resPagedList;
    }
    
	/**
     * 查询OperatorRef数量<br/>
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
        int count = this.operatorRefService.count(
        	querier);
        
        return count;
    }

	/**
     * 查询OperatorRef是否存在<br/>
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
        boolean flag = this.operatorRefService.exists(querier, excludeId);
        
        return flag;
    }
}