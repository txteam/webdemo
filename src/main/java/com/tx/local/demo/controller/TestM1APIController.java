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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tx.core.paged.model.PagedList;
import com.tx.core.querier.model.Querier;
import com.tx.local.demo.facade.TestM1Facade;
import com.tx.local.demo.model.TestM1;
import com.tx.local.demo.service.TestM1Service;

import io.swagger.annotations.Api;

/**
 * TestM1API控制层[TestM1APIController]<br/>
 * 
 * @author []
 * @version [版本号]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@RestController
@Api(tags = "TestM1API")
@RequestMapping("/api/testM1")
public class TestM1APIController implements TestM1Facade {
    
    //TestM1业务层
    @Resource(name = "testM1Service")
    private TestM1Service testM1Service;
    
    /**
     * 新增TestM1<br/>
     * <功能详细描述>
     * @param testM1 [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public TestM1 insert(@RequestBody TestM1 testM1) {
        this.testM1Service.insert(testM1);
        return testM1;
    }
    
    /**
     * 根据id删除TestM1<br/> 
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
        boolean flag = this.testM1Service.deleteById(id);
        return flag;
    }
	
	/**
     * 根据code删除TestM1<br/> 
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
        boolean flag = this.testM1Service.deleteByCode(code);
        return flag;    
    }
    
    /**
     * 更新TestM1<br/>
     * <功能详细描述>
     * @param testM1
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public boolean updateById(@PathVariable(value = "id",required=true) String id,
    		@RequestBody TestM1 testM1) {
        boolean flag = this.testM1Service.updateById(id,testM1);
        return flag;
    }
    
    /**
     * 禁用TestM1<br/>
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
        boolean flag = this.testM1Service.disableById(id);
        return flag;
    }
    
    /**
     * 启用TestM1<br/>
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
        boolean flag = this.testM1Service.enableById(id);
        return flag;
    }

    /**
     * 根据主键查询TestM1<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return TestM1 [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public TestM1 findById(
            @PathVariable(value = "id", required = true) String id) {
        TestM1 res = this.testM1Service.findById(id);
        
        return res;
    }

    /**
     * 根据编码查询TestM1<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return TestM1 [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public TestM1 findByCode(
            @PathVariable(value = "code", required = true) String code) {
        TestM1 res = this.testM1Service.findByCode(code);
        
        return res;
    }

    /**
     * 查询TestM1实例列表<br/>
     * <功能详细描述>
     * @param valid
     * @param querier
     * @return [参数说明]
     * 
     * @return List<TestM1> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public List<TestM1> queryList(
			@RequestParam(value = "valid", required = false) Boolean valid,
    		@RequestBody Querier querier
    	) {
        List<TestM1> resList = this.testM1Service.queryList(
			valid,
			querier         
        );
  
        return resList;
    }
    
    /**
     * 查询TestM1分页列表<br/>
     * <功能详细描述>
     * @param valid
     * @param pageIndex
     * @param pageSize
     * @param querier
     * @return [参数说明]
     * 
     * @return PagedList<TestM1> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public PagedList<TestM1> queryPagedList(
			@RequestParam(value = "valid", required = false) Boolean valid,
			@RequestBody Querier querier,
			@PathVariable(value = "pageNumber", required = true) int pageIndex,
            @PathVariable(value = "pageSize", required = true) int pageSize
    	) {
        PagedList<TestM1> resPagedList = this.testM1Service.queryPagedList(
			valid,
			querier,
			pageIndex,
			pageSize
        );
        return resPagedList;
    }
    
	/**
     * 查询TestM1数量<br/>
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
        int count = this.testM1Service.count(
			valid,
        	querier);
        
        return count;
    }

	/**
     * 查询TestM1是否存在<br/>
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
        boolean flag = this.testM1Service.exists(querier, excludeId);
        
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
    public List<TestM1> queryChildrenByParentId(@PathVariable(value = "parentId", required = true) String parentId,
			@RequestParam(value = "valid", required = false) Boolean valid,
            Querier querier){
        List<TestM1> resList = this.testM1Service.queryChildrenByParentId(parentId,
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
    public List<TestM1> queryDescendantsByParentId(@PathVariable(value = "parentId", required = true) String parentId,
			@RequestParam(value = "valid", required = false) Boolean valid,
            Querier querier){
        List<TestM1> resList = this.testM1Service.queryDescendantsByParentId(parentId,
			valid,
			querier         
        );
  
        return resList;
    }
}