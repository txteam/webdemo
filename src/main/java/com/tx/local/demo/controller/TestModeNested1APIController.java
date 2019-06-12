/*
 * 描       述:  <描述>
 * 修  改 人:  
 * 修改时间:
 * <修改描述:>
 */
package com.tx.local.demo.controller;

import java.util.List;
import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tx.core.paged.model.PagedList;
import com.tx.core.querier.model.Querier;
import com.tx.local.demo.model.TestModeNested1;
import com.tx.local.demo.service.TestModeNested1Service;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * TestModeNested1API控制层<br/>
 * 
 * @author []
 * @version [版本号]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@RestController
@Api(tags = "TestModeNested1API")
@RequestMapping("/api/testModeNested1")
public class TestModeNested1APIController {
    
    //TestModeNested1业务层
    @Resource(name = "testModeNested1Service")
    private TestModeNested1Service testModeNested1Service;
    
    /**
     * 新增TestModeNested1<br/>
     * <功能详细描述>
     * @param testModeNested1 [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ApiOperation(value = "新增TestModeNested1")
    @RequestMapping(value = "", method = RequestMethod.POST)
    public boolean insert(@RequestBody TestModeNested1 testModeNested1) {
        this.testModeNested1Service.insert(testModeNested1);
        return true;
    }
    
    /**
     * 删除TestModeNested1<br/> 
     * <功能详细描述>
     * @param id
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ApiOperation(value = "删除TestModeNested1")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE) 
    public boolean deleteById(
    		@PathVariable(value = "id",required=true) String id) {
        boolean flag = this.testModeNested1Service.deleteById(id);
        return flag;
    }
    
    /**
     * 更新TestModeNested1<br/>
     * <功能详细描述>
     * @param testModeNested1
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ApiOperation(value = "修改TestModeNested1")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public boolean updateById(@PathVariable(value = "id",required=true) String id,
    		@RequestBody TestModeNested1 testModeNested1) {
        boolean flag = this.testModeNested1Service.updateById(id,testModeNested1);
        return flag;
    }
    

    /**
     * 根据主键查询TestModeNested1<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return TestModeNested1 [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ApiOperation(value = "根据主键查询TestModeNested1")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public TestModeNested1 findById(
            @PathVariable(value = "id", required = true) String id) {
        TestModeNested1 res = this.testModeNested1Service.findById(id);
        
        return res;
    }
    
    /**
     * 根据编码查询TestModeNested1<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return TestModeNested1 [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ApiOperation(value = "根据编码查询TestModeNested1")
    @RequestMapping(value = "/code/{code}", method = RequestMethod.GET)
    public TestModeNested1 findByCode(
            @PathVariable(value = "code", required = true) String code) {
        TestModeNested1 res = this.testModeNested1Service.findByCode(code);
        
        return res;
    }

    /**
     * 查询TestModeNested1实例列表<br/>
     * <功能详细描述>
     * @param valid
     * @param querier
     * @return [参数说明]
     * 
     * @return List<TestModeNested1> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ApiOperation(value = "查询TestModeNested1列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<TestModeNested1> queryList(
    		@RequestBody Querier querier
    	) {
        List<TestModeNested1> resList = this.testModeNested1Service.queryList(
			querier         
        );
  
        return resList;
    }
    
    /**
     * 查询TestModeNested1分页列表<br/>
     * <功能详细描述>
     * @param valid
     * @param pageIndex
     * @param pageSize
     * @param querier
     * @return [参数说明]
     * 
     * @return PagedList<TestModeNested1> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ApiOperation(value = "查询TestModeNested1分页列表")
    @RequestMapping(value = "/pagedlist/{pageSize}/{pageNumber}", method = RequestMethod.GET)
    public PagedList<TestModeNested1> queryPagedList(
			@PathVariable(value = "pageNumber", required = true) int pageIndex,
            @PathVariable(value = "pageSize", required = true) int pageSize,
            @RequestBody Querier querier
    	) {
        PagedList<TestModeNested1> resPagedList = this.testModeNested1Service.queryPagedList(
			querier,
			pageIndex,
			pageSize
        );
        return resPagedList;
    }
    
	/**
     * 查询TestModeNested1数量<br/>
     * <功能详细描述>
     * @param valid
     * @param querier
     * @return [参数说明]
     * 
     * @return int [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ApiOperation(value = "查询TestModeNested1数量")
    @RequestMapping(value = "/count", method = RequestMethod.GET)
    public int count(
            @RequestBody Querier querier) {
        int count = this.testModeNested1Service.count(
        	querier);
        
        return count;
    }

	/**
     * 查询TestModeNested1是否存在<br/>
	 * @param excludeId
     * @param querier
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ApiOperation(value = "查询TestModeNested1是否存在")
    @RequestMapping(value = "/exists/{excludeId}", method = RequestMethod.GET)
    public boolean exists(
            @PathVariable(value = "excludeId", required = false) String excludeId,
            @RequestBody Querier querier) {
        boolean flag = this.testModeNested1Service.exists(querier, excludeId);
        
        return flag;
    }
    

}