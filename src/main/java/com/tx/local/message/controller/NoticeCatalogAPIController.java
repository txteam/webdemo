/*
 * 描       述:  <描述>
 * 修  改 人:  
 * 修改时间:
 * <修改描述:>
 */
package com.tx.local.message.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tx.core.paged.model.PagedList;
import com.tx.core.querier.model.Querier;
import com.tx.local.message.facade.NoticeCatalogFacade;
import com.tx.local.message.model.NoticeCatalog;
import com.tx.local.message.service.NoticeCatalogService;

import io.swagger.annotations.Api;

/**
 * NoticeCatalogAPI控制层[NoticeCatalogAPIController]<br/>
 * 
 * @author []
 * @version [版本号]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@RestController
@Api(tags = "NoticeCatalogAPI")
@RequestMapping("/api/noticeCatalog")
public class NoticeCatalogAPIController implements NoticeCatalogFacade {
    
    //NoticeCatalog业务层
    @Resource(name = "noticeCatalogService")
    private NoticeCatalogService noticeCatalogService;
    
    /**
     * 新增NoticeCatalog<br/>
     * <功能详细描述>
     * @param noticeCatalog [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public NoticeCatalog insert(@RequestBody NoticeCatalog noticeCatalog) {
        this.noticeCatalogService.insert(noticeCatalog);
        return noticeCatalog;
    }
    
    /**
     * 根据id删除NoticeCatalog<br/> 
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
        boolean flag = this.noticeCatalogService.deleteById(id);
        return flag;
    }
	
	/**
     * 根据code删除NoticeCatalog<br/> 
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
        boolean flag = this.noticeCatalogService.deleteByCode(code);
        return flag;    
    }
    
    /**
     * 更新NoticeCatalog<br/>
     * <功能详细描述>
     * @param noticeCatalog
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public boolean updateById(@PathVariable(value = "id",required=true) String id,
    		@RequestBody NoticeCatalog noticeCatalog) {
        boolean flag = this.noticeCatalogService.updateById(id,noticeCatalog);
        return flag;
    }
    
    /**
     * 禁用NoticeCatalog<br/>
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
        boolean flag = this.noticeCatalogService.disableById(id);
        return flag;
    }
    
    /**
     * 启用NoticeCatalog<br/>
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
        boolean flag = this.noticeCatalogService.enableById(id);
        return flag;
    }

    /**
     * 根据主键查询NoticeCatalog<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return NoticeCatalog [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public NoticeCatalog findById(
            @PathVariable(value = "id", required = true) String id) {
        NoticeCatalog res = this.noticeCatalogService.findById(id);
        
        return res;
    }

    /**
     * 根据编码查询NoticeCatalog<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return NoticeCatalog [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public NoticeCatalog findByCode(
            @PathVariable(value = "code", required = true) String code) {
        NoticeCatalog res = this.noticeCatalogService.findByCode(code);
        
        return res;
    }

    /**
     * 查询NoticeCatalog实例列表<br/>
     * <功能详细描述>
     * @param valid
     * @param querier
     * @return [参数说明]
     * 
     * @return List<NoticeCatalog> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public List<NoticeCatalog> queryList(
			@RequestParam(value = "valid", required = false) Boolean valid,
    		@RequestBody Querier querier
    	) {
        List<NoticeCatalog> resList = this.noticeCatalogService.queryList(
			valid,
			querier         
        );
  
        return resList;
    }
    
    /**
     * 查询NoticeCatalog分页列表<br/>
     * <功能详细描述>
     * @param valid
     * @param pageIndex
     * @param pageSize
     * @param querier
     * @return [参数说明]
     * 
     * @return PagedList<NoticeCatalog> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public PagedList<NoticeCatalog> queryPagedList(
			@RequestParam(value = "valid", required = false) Boolean valid,
			@RequestBody Querier querier,
			@PathVariable(value = "pageNumber", required = true) int pageIndex,
            @PathVariable(value = "pageSize", required = true) int pageSize
    	) {
        PagedList<NoticeCatalog> resPagedList = this.noticeCatalogService.queryPagedList(
			valid,
			querier,
			pageIndex,
			pageSize
        );
        return resPagedList;
    }
    
	/**
     * 查询NoticeCatalog数量<br/>
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
        int count = this.noticeCatalogService.count(
			valid,
        	querier);
        
        return count;
    }

	/**
     * 查询NoticeCatalog是否存在<br/>
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
        boolean flag = this.noticeCatalogService.exists(querier, excludeId);
        
        return flag;
    }
}