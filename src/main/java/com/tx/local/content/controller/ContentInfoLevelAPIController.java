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
import com.tx.local.content.facade.ContentInfoLevelFacade;
import com.tx.local.content.model.ContentInfoLevel;
import com.tx.local.content.service.ContentInfoLevelService;

import io.swagger.annotations.Api;

/**
 * 内容信息级别API控制层[ContentInfoLevelAPIController]<br/>
 * 
 * @author []
 * @version [版本号]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@RestController
@Api(tags = "内容信息级别API")
@RequestMapping("/api/contentInfoLevel")
public class ContentInfoLevelAPIController implements ContentInfoLevelFacade {
    
    //内容信息级别业务层
    @Resource(name = "contentInfoLevelService")
    private ContentInfoLevelService contentInfoLevelService;
    
    /**
     * 新增内容信息级别<br/>
     * <功能详细描述>
     * @param contentInfoLevel [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public ContentInfoLevel insert(@RequestBody ContentInfoLevel contentInfoLevel) {
        this.contentInfoLevelService.insert(contentInfoLevel);
        return contentInfoLevel;
    }
    
    /**
     * 根据id删除内容信息级别<br/> 
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
        boolean flag = this.contentInfoLevelService.deleteById(id);
        return flag;
    }
	
	/**
     * 根据code删除内容信息级别<br/> 
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
        boolean flag = this.contentInfoLevelService.deleteByCode(code);
        return flag;    
    }
    
    /**
     * 更新内容信息级别<br/>
     * <功能详细描述>
     * @param contentInfoLevel
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public boolean updateById(@PathVariable(value = "id",required=true) String id,
    		@RequestBody ContentInfoLevel contentInfoLevel) {
        boolean flag = this.contentInfoLevelService.updateById(id,contentInfoLevel);
        return flag;
    }
    
    /**
     * 禁用内容信息级别<br/>
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
        boolean flag = this.contentInfoLevelService.disableById(id);
        return flag;
    }
    
    /**
     * 启用内容信息级别<br/>
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
        boolean flag = this.contentInfoLevelService.enableById(id);
        return flag;
    }

    /**
     * 根据主键查询内容信息级别<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return ContentInfoLevel [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public ContentInfoLevel findById(
            @PathVariable(value = "id", required = true) String id) {
        ContentInfoLevel res = this.contentInfoLevelService.findById(id);
        
        return res;
    }

    /**
     * 根据编码查询内容信息级别<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return ContentInfoLevel [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public ContentInfoLevel findByCode(
            @PathVariable(value = "code", required = true) String code) {
        ContentInfoLevel res = this.contentInfoLevelService.findByCode(code);
        
        return res;
    }

    /**
     * 查询内容信息级别实例列表<br/>
     * <功能详细描述>
     * @param valid
     * @param querier
     * @return [参数说明]
     * 
     * @return List<ContentInfoLevel> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public List<ContentInfoLevel> queryList(
			@RequestParam(value = "valid", required = false) Boolean valid,
    		@RequestBody Querier querier
    	) {
        List<ContentInfoLevel> resList = this.contentInfoLevelService.queryList(
			valid,
			querier         
        );
  
        return resList;
    }
    
    /**
     * 查询内容信息级别分页列表<br/>
     * <功能详细描述>
     * @param valid
     * @param pageIndex
     * @param pageSize
     * @param querier
     * @return [参数说明]
     * 
     * @return PagedList<ContentInfoLevel> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public PagedList<ContentInfoLevel> queryPagedList(
			@RequestParam(value = "valid", required = false) Boolean valid,
			@RequestBody Querier querier,
			@PathVariable(value = "pageNumber", required = true) int pageIndex,
            @PathVariable(value = "pageSize", required = true) int pageSize
    	) {
        PagedList<ContentInfoLevel> resPagedList = this.contentInfoLevelService.queryPagedList(
			valid,
			querier,
			pageIndex,
			pageSize
        );
        return resPagedList;
    }
    
	/**
     * 查询内容信息级别数量<br/>
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
        int count = this.contentInfoLevelService.count(
			valid,
        	querier);
        
        return count;
    }

	/**
     * 查询内容信息级别是否存在<br/>
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
        boolean flag = this.contentInfoLevelService.exists(querier, excludeId);
        
        return flag;
    }
}