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
import com.tx.local.message.facade.Notice2UserFacade;
import com.tx.local.message.model.Notice2User;
import com.tx.local.message.service.Notice2UserService;

import io.swagger.annotations.Api;

/**
 * Notice2UserAPI控制层[Notice2UserAPIController]<br/>
 * 
 * @author []
 * @version [版本号]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@RestController
@Api(tags = "Notice2UserAPI")
@RequestMapping("/api/notice2User")
public class Notice2UserAPIController implements Notice2UserFacade {
    
    //Notice2User业务层
    @Resource(name = "notice2UserService")
    private Notice2UserService notice2UserService;
    
    /**
     * 新增Notice2User<br/>
     * <功能详细描述>
     * @param notice2User [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public Notice2User insert(@RequestBody Notice2User notice2User) {
        this.notice2UserService.insert(notice2User);
        return notice2User;
    }
    
    /**
     * 根据id删除Notice2User<br/> 
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
        boolean flag = this.notice2UserService.deleteById(id);
        return flag;
    }
    
    /**
     * 更新Notice2User<br/>
     * <功能详细描述>
     * @param notice2User
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public boolean updateById(@PathVariable(value = "id",required=true) String id,
    		@RequestBody Notice2User notice2User) {
        boolean flag = this.notice2UserService.updateById(id,notice2User);
        return flag;
    }
    

    /**
     * 根据主键查询Notice2User<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return Notice2User [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public Notice2User findById(
            @PathVariable(value = "id", required = true) String id) {
        Notice2User res = this.notice2UserService.findById(id);
        
        return res;
    }

    /**
     * 查询Notice2User实例列表<br/>
     * <功能详细描述>
     * @param querier
     * @return [参数说明]
     * 
     * @return List<Notice2User> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public List<Notice2User> queryList(
    		@RequestBody Querier querier
    	) {
        List<Notice2User> resList = this.notice2UserService.queryList(
			querier         
        );
  
        return resList;
    }
    
    /**
     * 查询Notice2User分页列表<br/>
     * <功能详细描述>
     * @param pageIndex
     * @param pageSize
     * @param querier
     * @return [参数说明]
     * 
     * @return PagedList<Notice2User> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public PagedList<Notice2User> queryPagedList(
			@RequestBody Querier querier,
			@PathVariable(value = "pageNumber", required = true) int pageIndex,
            @PathVariable(value = "pageSize", required = true) int pageSize
    	) {
        PagedList<Notice2User> resPagedList = this.notice2UserService.queryPagedList(
			querier,
			pageIndex,
			pageSize
        );
        return resPagedList;
    }
    
	/**
     * 查询Notice2User数量<br/>
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
        int count = this.notice2UserService.count(
        	querier);
        
        return count;
    }

	/**
     * 查询Notice2User是否存在<br/>
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
        boolean flag = this.notice2UserService.exists(querier, excludeId);
        
        return flag;
    }
}