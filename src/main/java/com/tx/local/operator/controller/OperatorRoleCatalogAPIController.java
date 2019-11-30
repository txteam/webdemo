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
import com.tx.local.operator.facade.OperatorRoleCatalogFacade;
import com.tx.local.operator.model.OperatorRoleCatalog;
import com.tx.local.operator.service.OperatorRoleCatalogService;

import io.swagger.annotations.Api;

/**
 * 角色分类API控制层[OperatorRoleCatalogAPIController]<br/>
 * 
 * @author []
 * @version [版本号]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@RestController
@Api(tags = "角色分类API")
@RequestMapping("/api/operatorRoleCatalog")
public class OperatorRoleCatalogAPIController implements OperatorRoleCatalogFacade {
    
    //角色分类业务层
    @Resource(name = "operatorRoleCatalogService")
    private OperatorRoleCatalogService operatorRoleCatalogService;
    
    /**
     * 新增角色分类<br/>
     * <功能详细描述>
     * @param operatorRoleCatalog [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public OperatorRoleCatalog insert(@RequestBody OperatorRoleCatalog operatorRoleCatalog) {
        this.operatorRoleCatalogService.insert(operatorRoleCatalog);
        return operatorRoleCatalog;
    }
    
    /**
     * 根据id删除角色分类<br/> 
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
        boolean flag = this.operatorRoleCatalogService.deleteById(id);
        return flag;
    }
    
    /**
     * 更新角色分类<br/>
     * <功能详细描述>
     * @param operatorRoleCatalog
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public boolean updateById(@PathVariable(value = "id",required=true) String id,
    		@RequestBody OperatorRoleCatalog operatorRoleCatalog) {
        boolean flag = this.operatorRoleCatalogService.updateById(id,operatorRoleCatalog);
        return flag;
    }
    
    /**
     * 禁用角色分类<br/>
     * @param id
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
	@Override
    public boolean disableById(
    		@PathVariable(value = "id", required = true) String id) {
        boolean flag = this.operatorRoleCatalogService.disableById(id);
        return flag;
    }
    
    /**
     * 启用角色分类<br/>
     * <功能详细描述>
     * @param id
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public boolean enableById(
    		@PathVariable(value = "id", required = true) String id) {
        boolean flag = this.operatorRoleCatalogService.enableById(id);
        return flag;
    }

    /**
     * 根据主键查询角色分类<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return OperatorRoleCatalog [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public OperatorRoleCatalog findById(
            @PathVariable(value = "id", required = true) String id) {
        OperatorRoleCatalog res = this.operatorRoleCatalogService.findById(id);
        
        return res;
    }

    /**
     * 查询角色分类实例列表<br/>
     * <功能详细描述>
     * @param valid
     * @param querier
     * @return [参数说明]
     * 
     * @return List<OperatorRoleCatalog> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public List<OperatorRoleCatalog> queryList(
			@RequestParam(value = "valid", required = false) Boolean valid,
    		@RequestBody Querier querier
    	) {
        List<OperatorRoleCatalog> resList = this.operatorRoleCatalogService.queryList(
			valid,
			querier         
        );
  
        return resList;
    }
    
    /**
     * 查询角色分类分页列表<br/>
     * <功能详细描述>
     * @param valid
     * @param pageIndex
     * @param pageSize
     * @param querier
     * @return [参数说明]
     * 
     * @return PagedList<OperatorRoleCatalog> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public PagedList<OperatorRoleCatalog> queryPagedList(
			@RequestParam(value = "valid", required = false) Boolean valid,
			@RequestBody Querier querier,
			@PathVariable(value = "pageNumber", required = true) int pageIndex,
            @PathVariable(value = "pageSize", required = true) int pageSize
    	) {
        PagedList<OperatorRoleCatalog> resPagedList = this.operatorRoleCatalogService.queryPagedList(
			valid,
			querier,
			pageIndex,
			pageSize
        );
        return resPagedList;
    }
    
	/**
     * 查询角色分类数量<br/>
     * <功能详细描述>
     * @param valid
     * @param querier
     * @return [参数说明]
     * 
     * @return int [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public int count(
			@RequestParam(value = "valid", required = false) Boolean valid,
            @RequestBody Querier querier) {
        int count = this.operatorRoleCatalogService.count(
			valid,
        	querier);
        
        return count;
    }

	/**
     * 查询角色分类是否存在<br/>
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
        boolean flag = this.operatorRoleCatalogService.exists(querier, excludeId);
        
        return flag;
    }

	/**
     * 根据条件查询基础数据分页列表<br/>
     * <功能详细描述>
     * @param parentId
     * @param valid
     * @param querier
     * @return [参数说明]
     * 
     * @return PagedList<T> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public List<OperatorRoleCatalog> queryChildrenByParentId(@PathVariable(value = "parentId", required = true) String parentId,
			@RequestParam(value = "valid", required = false) Boolean valid,
            Querier querier){
        List<OperatorRoleCatalog> resList = this.operatorRoleCatalogService.queryChildrenByParentId(parentId,
			valid,
			querier         
        );
  
        return resList;
    }

	/**
     * 根据条件查询基础数据分页列表<br/>
     * <功能详细描述>
     * @param parentId
     * @param valid
     * @param querier
     * @return [参数说明]
     * 
     * @return PagedList<T> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public List<OperatorRoleCatalog> queryDescendantsByParentId(@PathVariable(value = "parentId", required = true) String parentId,
			@RequestParam(value = "valid", required = false) Boolean valid,
            Querier querier){
        List<OperatorRoleCatalog> resList = this.operatorRoleCatalogService.queryDescendantsByParentId(parentId,
			valid,
			querier         
        );
  
        return resList;
    }
}