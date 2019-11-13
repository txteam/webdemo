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
import com.tx.local.content.facade.ContentInfoTypeFacade;
import com.tx.local.content.model.ContentInfoType;
import com.tx.local.content.service.ContentInfoTypeService;

import io.swagger.annotations.Api;

/**
 * 内容信息类型API控制层[ContentInfoTypeAPIController]<br/>
 * 
 * @author []
 * @version [版本号]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@RestController
@Api(tags = "内容信息类型API")
@RequestMapping("/api/contentInfoType")
public class ContentInfoTypeAPIController implements ContentInfoTypeFacade {
    
    //内容信息类型业务层
    @Resource(name = "contentInfoTypeService")
    private ContentInfoTypeService contentInfoTypeService;
    
    /**
     * 新增内容信息类型<br/>
     * <功能详细描述>
     * @param contentInfoType [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public ContentInfoType insert(@RequestBody ContentInfoType contentInfoType) {
        this.contentInfoTypeService.insert(contentInfoType);
        return contentInfoType;
    }
    
    /**
     * 根据id删除内容信息类型<br/> 
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
        boolean flag = this.contentInfoTypeService.deleteById(id);
        return flag;
    }
	
	/**
     * 根据code删除内容信息类型<br/> 
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
        boolean flag = this.contentInfoTypeService.deleteByCode(code);
        return flag;    
    }
    
    /**
     * 更新内容信息类型<br/>
     * <功能详细描述>
     * @param contentInfoType
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public boolean updateById(@PathVariable(value = "id",required=true) String id,
    		@RequestBody ContentInfoType contentInfoType) {
        boolean flag = this.contentInfoTypeService.updateById(id,contentInfoType);
        return flag;
    }
    
    /**
     * 禁用内容信息类型<br/>
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
        boolean flag = this.contentInfoTypeService.disableById(id);
        return flag;
    }
    
    /**
     * 启用内容信息类型<br/>
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
        boolean flag = this.contentInfoTypeService.enableById(id);
        return flag;
    }

    /**
     * 根据主键查询内容信息类型<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return ContentInfoType [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public ContentInfoType findById(
            @PathVariable(value = "id", required = true) String id) {
        ContentInfoType res = this.contentInfoTypeService.findById(id);
        
        return res;
    }

    /**
     * 根据编码查询内容信息类型<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return ContentInfoType [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public ContentInfoType findByCode(
            @PathVariable(value = "code", required = true) String code) {
        ContentInfoType res = this.contentInfoTypeService.findByCode(code);
        
        return res;
    }

    /**
     * 查询内容信息类型实例列表<br/>
     * <功能详细描述>
     * @param valid
     * @param querier
     * @return [参数说明]
     * 
     * @return List<ContentInfoType> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public List<ContentInfoType> queryList(
			@RequestParam(value = "valid", required = false) Boolean valid,
    		@RequestBody Querier querier
    	) {
        List<ContentInfoType> resList = this.contentInfoTypeService.queryList(
			valid,
			querier         
        );
  
        return resList;
    }
    
    /**
     * 查询内容信息类型分页列表<br/>
     * <功能详细描述>
     * @param valid
     * @param pageIndex
     * @param pageSize
     * @param querier
     * @return [参数说明]
     * 
     * @return PagedList<ContentInfoType> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public PagedList<ContentInfoType> queryPagedList(
			@RequestParam(value = "valid", required = false) Boolean valid,
			@RequestBody Querier querier,
			@PathVariable(value = "pageNumber", required = true) int pageIndex,
            @PathVariable(value = "pageSize", required = true) int pageSize
    	) {
        PagedList<ContentInfoType> resPagedList = this.contentInfoTypeService.queryPagedList(
			valid,
			querier,
			pageIndex,
			pageSize
        );
        return resPagedList;
    }
    
	/**
     * 查询内容信息类型数量<br/>
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
        int count = this.contentInfoTypeService.count(
			valid,
        	querier);
        
        return count;
    }

	/**
     * 查询内容信息类型是否存在<br/>
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
        boolean flag = this.contentInfoTypeService.exists(querier, excludeId);
        
        return flag;
    }
}