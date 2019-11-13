/*
 * 描       述:  <描述>
 * 修  改 人:  
 * 修改时间:
 * <修改描述:>
 */
package com.tx.local.content.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tx.core.paged.model.PagedList;
import com.tx.core.querier.model.Querier;
import com.tx.local.content.facade.ContentInfoCategoryFacade;
import com.tx.local.content.model.ContentInfoCategory;
import com.tx.local.content.service.ContentInfoCategoryService;

import io.swagger.annotations.Api;

/**
 * 内容信息分类API控制层[ContentInfoCategoryAPIController]<br/>
 * 
 * @author []
 * @version [版本号]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@RestController
@Api(tags = "内容信息分类API")
@RequestMapping("/api/contentInfoCategory")
public class ContentInfoCategoryAPIController implements ContentInfoCategoryFacade {
    
    //内容信息分类业务层
    @Resource(name = "contentInfoCategoryService")
    private ContentInfoCategoryService contentInfoCategoryService;
    
    /**
     * 新增内容信息分类<br/>
     * <功能详细描述>
     * @param contentInfoCategory [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public ContentInfoCategory insert(@RequestBody ContentInfoCategory contentInfoCategory) {
        this.contentInfoCategoryService.insert(contentInfoCategory);
        return contentInfoCategory;
    }
    
    /**
     * 根据id删除内容信息分类<br/> 
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
        boolean flag = this.contentInfoCategoryService.deleteById(id);
        return flag;
    }
	
	/**
     * 根据code删除内容信息分类<br/> 
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
        boolean flag = this.contentInfoCategoryService.deleteByCode(code);
        return flag;    
    }
    
    /**
     * 更新内容信息分类<br/>
     * <功能详细描述>
     * @param contentInfoCategory
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public boolean updateById(@PathVariable(value = "id",required=true) String id,
    		@RequestBody ContentInfoCategory contentInfoCategory) {
        boolean flag = this.contentInfoCategoryService.updateById(id,contentInfoCategory);
        return flag;
    }
    
    /**
     * 禁用内容信息分类<br/>
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
        boolean flag = this.contentInfoCategoryService.disableById(id);
        return flag;
    }
    
    /**
     * 启用内容信息分类<br/>
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
        boolean flag = this.contentInfoCategoryService.enableById(id);
        return flag;
    }

    /**
     * 根据主键查询内容信息分类<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return ContentInfoCategory [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public ContentInfoCategory findById(
            @PathVariable(value = "id", required = true) String id) {
        ContentInfoCategory res = this.contentInfoCategoryService.findById(id);
        
        return res;
    }

    /**
     * 根据编码查询内容信息分类<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return ContentInfoCategory [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public ContentInfoCategory findByCode(
            @PathVariable(value = "code", required = true) String code) {
        ContentInfoCategory res = this.contentInfoCategoryService.findByCode(code);
        
        return res;
    }

    /**
     * 查询内容信息分类实例列表<br/>
     * <功能详细描述>
     * @param valid
     * @param querier
     * @return [参数说明]
     * 
     * @return List<ContentInfoCategory> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public List<ContentInfoCategory> queryList(
			@RequestParam(value = "valid", required = false) Boolean valid,
    		@RequestBody Querier querier
    	) {
        List<ContentInfoCategory> resList = this.contentInfoCategoryService.queryList(
			valid,
			querier         
        );
  
        return resList;
    }
    
    /**
     * 查询内容信息分类分页列表<br/>
     * <功能详细描述>
     * @param valid
     * @param pageIndex
     * @param pageSize
     * @param querier
     * @return [参数说明]
     * 
     * @return PagedList<ContentInfoCategory> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public PagedList<ContentInfoCategory> queryPagedList(
			@RequestParam(value = "valid", required = false) Boolean valid,
			@RequestBody Querier querier,
			@PathVariable(value = "pageNumber", required = true) int pageIndex,
            @PathVariable(value = "pageSize", required = true) int pageSize
    	) {
        PagedList<ContentInfoCategory> resPagedList = this.contentInfoCategoryService.queryPagedList(
			valid,
			querier,
			pageIndex,
			pageSize
        );
        return resPagedList;
    }
    
	/**
     * 查询内容信息分类数量<br/>
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
        int count = this.contentInfoCategoryService.count(
			valid,
        	querier);
        
        return count;
    }

	/**
     * 查询内容信息分类是否存在<br/>
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
        boolean flag = this.contentInfoCategoryService.exists(querier, excludeId);
        
        return flag;
    }
}