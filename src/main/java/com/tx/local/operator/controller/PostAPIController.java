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
import com.tx.local.operator.facade.PostFacade;
import com.tx.local.operator.model.Post;
import com.tx.local.operator.service.PostService;

import io.swagger.annotations.Api;

/**
 * 职位API控制层[PostAPIController]<br/>
 * 
 * @author []
 * @version [版本号]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@RestController
@Api(tags = "职位API")
@RequestMapping("/api/post")
public class PostAPIController implements PostFacade {
    
    //职位业务层
    @Resource(name = "postService")
    private PostService postService;
    
    /**
     * 新增职位<br/>
     * <功能详细描述>
     * @param post [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public Post insert(@RequestBody Post post) {
        this.postService.insert(post);
        return post;
    }
    
    /**
     * 根据id删除职位<br/> 
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
        boolean flag = this.postService.deleteById(id);
        return flag;
    }
	
	/**
     * 根据code删除职位<br/> 
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
        boolean flag = this.postService.deleteByCode(code);
        return flag;    
    }
    
    /**
     * 更新职位<br/>
     * <功能详细描述>
     * @param post
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public boolean updateById(@PathVariable(value = "id",required=true) String id,
    		@RequestBody Post post) {
        boolean flag = this.postService.updateById(id,post);
        return flag;
    }
    
    /**
     * 禁用职位<br/>
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
        boolean flag = this.postService.disableById(id);
        return flag;
    }
    
    /**
     * 启用职位<br/>
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
        boolean flag = this.postService.enableById(id);
        return flag;
    }

    /**
     * 根据主键查询职位<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return Post [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public Post findById(
            @PathVariable(value = "id", required = true) String id) {
        Post res = this.postService.findById(id);
        
        return res;
    }

    /**
     * 根据编码查询职位<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return Post [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public Post findByCode(
            @PathVariable(value = "code", required = true) String code) {
        Post res = this.postService.findByCode(code);
        
        return res;
    }

    /**
     * 查询职位实例列表<br/>
     * <功能详细描述>
     * @param valid
     * @param querier
     * @return [参数说明]
     * 
     * @return List<Post> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public List<Post> queryList(
			@RequestParam(value = "valid", required = false) Boolean valid,
    		@RequestBody Querier querier
    	) {
        List<Post> resList = this.postService.queryList(
			valid,
			querier         
        );
  
        return resList;
    }
    
    /**
     * 查询职位分页列表<br/>
     * <功能详细描述>
     * @param valid
     * @param pageIndex
     * @param pageSize
     * @param querier
     * @return [参数说明]
     * 
     * @return PagedList<Post> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public PagedList<Post> queryPagedList(
			@RequestParam(value = "valid", required = false) Boolean valid,
			@RequestBody Querier querier,
			@PathVariable(value = "pageNumber", required = true) int pageIndex,
            @PathVariable(value = "pageSize", required = true) int pageSize
    	) {
        PagedList<Post> resPagedList = this.postService.queryPagedList(
			valid,
			querier,
			pageIndex,
			pageSize
        );
        return resPagedList;
    }
    
	/**
     * 查询职位数量<br/>
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
        int count = this.postService.count(
			valid,
        	querier);
        
        return count;
    }

	/**
     * 查询职位是否存在<br/>
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
        boolean flag = this.postService.exists(querier, excludeId);
        
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
    public List<Post> queryChildrenByParentId(@PathVariable(value = "parentId", required = true) String parentId,
			@RequestParam(value = "valid", required = false) Boolean valid,
            Querier querier){
        List<Post> resList = this.postService.queryChildrenByParentId(parentId,
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
    public List<Post> queryDescendantsByParentId(@PathVariable(value = "parentId", required = true) String parentId,
			@RequestParam(value = "valid", required = false) Boolean valid,
            Querier querier){
        List<Post> resList = this.postService.queryDescendantsByParentId(parentId,
			valid,
			querier         
        );
  
        return resList;
    }
}