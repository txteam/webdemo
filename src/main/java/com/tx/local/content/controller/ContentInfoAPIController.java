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
import com.tx.local.content.facade.ContentInfoFacade;
import com.tx.local.content.model.ContentInfo;
import com.tx.local.content.service.ContentInfoService;

import io.swagger.annotations.Api;

/**
 * 内容信息API控制层[ContentInfoAPIController]<br/>
 * 
 * @author []
 * @version [版本号]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@RestController
@Api(tags = "内容信息API")
@RequestMapping("/api/contentInfo")
public class ContentInfoAPIController implements ContentInfoFacade {
    
    //内容信息业务层
    @Resource(name = "contentInfoService")
    private ContentInfoService contentInfoService;
    
    /**
     * 新增内容信息<br/>
     * <功能详细描述>
     * @param contentInfo [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public ContentInfo insert(@RequestBody ContentInfo contentInfo) {
        this.contentInfoService.insert(contentInfo);
        return contentInfo;
    }
    
    /**
     * 根据id删除内容信息<br/> 
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
        boolean flag = this.contentInfoService.deleteById(id);
        return flag;
    }
    
    /**
     * 更新内容信息<br/>
     * <功能详细描述>
     * @param contentInfo
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public boolean updateById(@PathVariable(value = "id",required=true) String id,
    		@RequestBody ContentInfo contentInfo) {
        boolean flag = this.contentInfoService.updateById(id,contentInfo);
        return flag;
    }
    
    /**
     * 禁用内容信息<br/>
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
        boolean flag = this.contentInfoService.disableById(id);
        return flag;
    }
    
    /**
     * 启用内容信息<br/>
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
        boolean flag = this.contentInfoService.enableById(id);
        return flag;
    }

    /**
     * 根据主键查询内容信息<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return ContentInfo [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public ContentInfo findById(
            @PathVariable(value = "id", required = true) String id) {
        ContentInfo res = this.contentInfoService.findById(id);
        
        return res;
    }

    /**
     * 查询内容信息实例列表<br/>
     * <功能详细描述>
     * @param valid
     * @param querier
     * @return [参数说明]
     * 
     * @return List<ContentInfo> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public List<ContentInfo> queryList(
			@RequestParam(value = "valid", required = false) Boolean valid,
    		@RequestBody Querier querier
    	) {
        List<ContentInfo> resList = this.contentInfoService.queryList(
			valid,
			querier         
        );
  
        return resList;
    }
    
    /**
     * 查询内容信息分页列表<br/>
     * <功能详细描述>
     * @param valid
     * @param pageIndex
     * @param pageSize
     * @param querier
     * @return [参数说明]
     * 
     * @return PagedList<ContentInfo> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public PagedList<ContentInfo> queryPagedList(
			@RequestParam(value = "valid", required = false) Boolean valid,
			@RequestBody Querier querier,
			@PathVariable(value = "pageNumber", required = true) int pageIndex,
            @PathVariable(value = "pageSize", required = true) int pageSize
    	) {
        PagedList<ContentInfo> resPagedList = this.contentInfoService.queryPagedList(
			valid,
			querier,
			pageIndex,
			pageSize
        );
        return resPagedList;
    }
    
	/**
     * 查询内容信息数量<br/>
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
        int count = this.contentInfoService.count(
			valid,
        	querier);
        
        return count;
    }

	/**
     * 查询内容信息是否存在<br/>
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
        boolean flag = this.contentInfoService.exists(querier, excludeId);
        
        return flag;
    }
}