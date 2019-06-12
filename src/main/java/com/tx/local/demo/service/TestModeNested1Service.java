/*
 * 描          述:  <描述>
 * 修  改   人:  
 * 修改时间:  
 * <修改描述:>
 */
package com.tx.local.demo.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.tx.local.demo.dao.TestModeNested1Dao;
import com.tx.local.demo.model.TestModeNested1;
import com.tx.core.exceptions.util.AssertUtils;
import com.tx.core.paged.model.PagedList;
import com.tx.core.querier.model.Querier;
import com.tx.core.querier.model.QuerierBuilder;

/**
 * TestModeNested1的业务层[TestModeNested1Service]
 * <功能详细描述>
 * 
 * @author  
 * @version [版本号]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Component("testModeNested1Service")
public class TestModeNested1Service {
    
    @SuppressWarnings("unused")
    private Logger logger = LoggerFactory.getLogger(TestModeNested1Service.class);
    
    @Resource(name = "testModeNested1Dao")
    private TestModeNested1Dao testModeNested1Dao;
    
    /**
     * 新增TestModeNested1实例<br/>
     * 将testModeNested1插入数据库中保存
     * 1、如果testModeNested1 为空时抛出参数为空异常
     * 2、如果testModeNested1 中部分必要参数为非法值时抛出参数不合法异常
     * 
     * @param testModeNested1 [参数说明]
     * @return void [返回类型说明]
     * @exception throws
     * @see [类、类#方法、类#成员]
     */
    @Transactional
    public void insert(TestModeNested1 testModeNested1) {
        //验证参数是否合法
        AssertUtils.notNull(testModeNested1, "testModeNested1 is null.");
           
        //FIXME:为添加的数据需要填入默认值的字段填入默认值
		testModeNested1.setLastUpdateDate(new Date());
		testModeNested1.setCreateDate(new Date());
        
        //调用数据持久层对实例进行持久化操作
        this.testModeNested1Dao.insert(testModeNested1);
    }
    
    /**
     * 根据id删除TestModeNested1实例
     * 1、如果入参数为空，则抛出异常
     * 2、执行删除后，将返回数据库中被影响的条数 > 0，则返回true
     *
     * @param id
     * @return boolean 删除的条数>0则为true [返回类型说明]
     * @exception throws 
     * @see [类、类#方法、类#成员]
     */
    @Transactional
    public boolean deleteById(String id) {
        AssertUtils.notEmpty(id, "id is empty.");
        
        TestModeNested1 condition = new TestModeNested1();
        condition.setId(id);
        
        int resInt = this.testModeNested1Dao.delete(condition);
        boolean flag = resInt > 0;
        return flag;
    }
    
    /**
     * 根据id查询TestModeNested1实例
     * 1、当id为empty时抛出异常
     *
     * @param id
     * @return TestModeNested1 [返回类型说明]
     * @exception throws
     * @see [类、类#方法、类#成员]
     */
    public TestModeNested1 findById(String id) {
        AssertUtils.notEmpty(id, "id is empty.");
        
        TestModeNested1 condition = new TestModeNested1();
        condition.setId(id);
        
        TestModeNested1 res = this.testModeNested1Dao.find(condition);
        return res;
    }
    
    /**
     * 根据code查询TestModeNested1实例
     * 1、当code为empty时抛出异常
     *
     * @param code
     * @return TestModeNested1 [返回类型说明]
     * @exception throws
     * @see [类、类#方法、类#成员]
     */
    public TestModeNested1 findByCode(String code) {
        AssertUtils.notEmpty(code, "code is empty.");
        
        TestModeNested1 condition = new TestModeNested1();
        condition.setCode(code);
        
        TestModeNested1 res = this.testModeNested1Dao.find(condition);
        return res;
    }
    
    /**
     * 查询TestModeNested1实例列表
     * <功能详细描述>
     * @param params      
     * @return [参数说明]
     * 
     * @return List<TestModeNested1> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public List<TestModeNested1> queryList(
		Map<String,Object> params   
    	) {
        //判断条件合法性
        
        //生成查询条件
        params = params == null ? new HashMap<String, Object>() : params;

        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        List<TestModeNested1> resList = this.testModeNested1Dao.queryList(params);
        
        return resList;
    }
    
    /**
     * 查询TestModeNested1实例列表
     * <功能详细描述>
     * @param querier      
     * @return [参数说明]
     * 
     * @return List<TestModeNested1> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public List<TestModeNested1> queryList(
		Querier querier   
    	) {
        //判断条件合法性
        
        //生成查询条件
        querier = querier == null ? QuerierBuilder.newInstance().querier()
                : querier;

        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        List<TestModeNested1> resList = this.testModeNested1Dao.queryList(querier);
        
        return resList;
    }
    
    /**
     * 分页查询TestModeNested1实例列表
     * <功能详细描述>
     * @param params    
     * @param pageIndex 当前页index从1开始计算
     * @param pageSize 每页显示行数
     * 
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<TestModeNested1> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public PagedList<TestModeNested1> queryPagedList(
		Map<String,Object> params,
    	int pageIndex,
        int pageSize) {
        //T判断条件合法性
        
        //生成查询条件
        params = params == null ? new HashMap<String, Object>() : params;
 
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        PagedList<TestModeNested1> resPagedList = this.testModeNested1Dao.queryPagedList(params, pageIndex, pageSize);
        
        return resPagedList;
    }
    
	/**
     * 分页查询TestModeNested1实例列表
     * <功能详细描述>
     * @param querier    
     * @param pageIndex 当前页index从1开始计算
     * @param pageSize 每页显示行数
     * 
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<TestModeNested1> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public PagedList<TestModeNested1> queryPagedList(
		Querier querier,
    	int pageIndex,
        int pageSize) {
        //T判断条件合法性
        
        //生成查询条件
        querier = querier == null ? QuerierBuilder.newInstance().querier()
                : querier;
 
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        PagedList<TestModeNested1> resPagedList = this.testModeNested1Dao.queryPagedList(querier, pageIndex, pageSize);
        
        return resPagedList;
    }
    
    /**
     * 查询TestModeNested1实例数量<br/>
     * <功能详细描述>
     * @param params      
     * @return [参数说明]
     * 
     * @return List<TestModeNested1> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public int count(
		Map<String,Object> params   
    	) {
        //判断条件合法性
        
        //生成查询条件
        params = params == null ? new HashMap<String, Object>() : params;

        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        int res = this.testModeNested1Dao.count(params);
        
        return res;
    }
    
    /**
     * 查询TestModeNested1实例数量<br/>
     * <功能详细描述>
     * @param querier      
     * @return [参数说明]
     * 
     * @return List<TestModeNested1> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public int count(
		Querier querier   
    	) {
        //判断条件合法性
        
        //生成查询条件
        querier = querier == null ? QuerierBuilder.newInstance().querier()
                : querier;

        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        int res = this.testModeNested1Dao.count(querier);
        
        return res;
    }
    
    /**
     * 判断TestModeNested1实例是否已经存在<br/>
     * <功能详细描述>
     * @param key2valueMap
     * @param excludeId
     * @return
     * 
     * @return int [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public boolean exists(Map<String,String> key2valueMap, String excludeId) {
        AssertUtils.notEmpty(key2valueMap, "key2valueMap is empty");
        
        //生成查询条件
        Map<String, Object> params = new HashMap<String, Object>();
        params.putAll(key2valueMap);
        params.put("excludeId", excludeId);
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        int res = this.testModeNested1Dao.count(params);
        
        return res > 0;
    }
    
    /**
     * 判断TestModeNested1实例是否已经存在<br/>
     * <功能详细描述>
     * @param key2valueMap
     * @param excludeId
     * @return
     * 
     * @return int [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public boolean exists(Querier querier, String excludeId) {
        AssertUtils.notNull(querier, "querier is null.");
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        int res = this.testModeNested1Dao.count(querier,excludeId);
        
        return res > 0;
    }
    
    /**
     * 根据id更新TestModeNested1实例<br/>
     * <功能详细描述>
     * @param testModeNested1
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Transactional
    public boolean updateById(String id,TestModeNested1 testModeNested1) {
        //验证参数是否合法，必填字段是否填写
        AssertUtils.notNull(testModeNested1, "testModeNested1 is null.");
        AssertUtils.notEmpty(id, "id is empty.");

        //生成需要更新字段的hashMap
        Map<String, Object> updateRowMap = new HashMap<String, Object>();
        //FIXME:需要更新的字段
		updateRowMap.put("code", testModeNested1.getCode());
		updateRowMap.put("name", testModeNested1.getName());
		updateRowMap.put("testInt", testModeNested1.getTestInt());
		updateRowMap.put("testLong", testModeNested1.getTestLong());
		updateRowMap.put("remark", testModeNested1.getRemark());
		updateRowMap.put("attributes", testModeNested1.getAttributes());
		updateRowMap.put("description", testModeNested1.getDescription());
		updateRowMap.put("lastUpdateDate", new Date());

        boolean flag = this.testModeNested1Dao.update(id,updateRowMap); 
        //如果需要大于1时，抛出异常并回滚，需要在这里修改
        return flag;
    }
    
    /**
     * 根据id更新TestModeNested1实例<br/>
     * <功能详细描述>
     * @param testModeNested1
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Transactional
    public boolean updateById(TestModeNested1 testModeNested1) {
        //验证参数是否合法，必填字段是否填写
        AssertUtils.notNull(testModeNested1, "testModeNested1 is null.");
        AssertUtils.notEmpty(testModeNested1.getId(), "testModeNested1.id is empty.");

        boolean flag = updateById(testModeNested1.getId(),testModeNested1); 
        //如果需要大于1时，抛出异常并回滚，需要在这里修改
        return flag;
    }
}
