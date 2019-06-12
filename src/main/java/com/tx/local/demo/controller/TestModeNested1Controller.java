/*
 * 描       述:  <描述>
 * 修  改 人:  
 * 修改时间:
 * <修改描述:>
 */
package com.tx.local.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tx.local.demo.model.TestModeNested1;
import com.tx.local.demo.service.TestModeNested1Service;
import com.tx.core.paged.model.PagedList;


/**
 * TestModeNested1控制层<br/>
 * 
 * @author []
 * @version [版本号]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Controller
@RequestMapping("/testModeNested1")
public class TestModeNested1Controller {
    
    //TestModeNested1业务层
    @Resource(name = "testModeNested1Service")
    private TestModeNested1Service testModeNested1Service;
    
    /**
     * 跳转到查询TestModeNested1列表页面<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/toQueryList")
    public String toQueryList(ModelMap response) {

        return "/demo/queryTestModeNested1List";
    }
    
    
    /**
     * 跳转到新增TestModeNested1页面<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/toAdd")
    public String toAdd(ModelMap response) {
    	response.put("testModeNested1", new TestModeNested1());
    	

        return "/demo/addTestModeNested1";
    }
    
    /**
     * 跳转到编辑TestModeNested1页面
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/toUpdate")
    public String toUpdate(
    		@RequestParam("id") String id,
            ModelMap response) {
        TestModeNested1 testModeNested1 = this.testModeNested1Service.findById(id); 
        response.put("testModeNested1", testModeNested1);

        
        return "/demo/updateTestModeNested1";
    }

    /**
     * 查询TestModeNested1实例列表<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<TestModeNested1> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/queryList")
    public List<TestModeNested1> queryList(
    		@RequestParam MultiValueMap<String, String> request
    	) {
        Map<String,Object> params = new HashMap<>();
        //params.put("",request.getFirst(""));
    	
        List<TestModeNested1> resList = this.testModeNested1Service.queryList(
			params         
        );
  
        return resList;
    }
    
    /**
     * 查询TestModeNested1实例分页列表<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<TestModeNested1> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/queryPagedList")
    public PagedList<TestModeNested1> queryPagedList(
			@RequestParam(value = "pageNumber", required = false, defaultValue = "1") int pageIndex,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
            @RequestParam MultiValueMap<String, String> request
    	) {
		Map<String,Object> params = new HashMap<>();
		//params.put("",request.getFirst(""));

        PagedList<TestModeNested1> resPagedList = this.testModeNested1Service.queryPagedList(
			params,
			pageIndex,
			pageSize
        );
        return resPagedList;
    }
    
    /**
     * 新增TestModeNested1实例
     * <功能详细描述>
     * @param testModeNested1 [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/add")
    public boolean add(TestModeNested1 testModeNested1) {
        this.testModeNested1Service.insert(testModeNested1);
        return true;
    }
    
    /**
     * 更新TestModeNested1实例<br/>
     * <功能详细描述>
     * @param testModeNested1
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/update")
    public boolean update(TestModeNested1 testModeNested1) {
        boolean flag = this.testModeNested1Service.updateById(testModeNested1);
        return flag;
    }
    
    /**
     * 根据主键查询TestModeNested1实例<br/> 
     * <功能详细描述>
     * @param id
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/findById")
    public TestModeNested1 findById(@RequestParam(value = "id") String id) {
        TestModeNested1 testModeNested1 = this.testModeNested1Service.findById(id);
        return testModeNested1;
    }

	/**
     * 根据编码查询TestModeNested1实例<br/> 
     * <功能详细描述>
     * @param code
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/findByCode")
    public TestModeNested1 findByCode(@RequestParam(value = "code") String code) {
        TestModeNested1 testModeNested1 = this.testModeNested1Service.findByCode(code);
        return testModeNested1;
    }
    
    /**
     * 删除TestModeNested1实例<br/> 
     * <功能详细描述>
     * @param id
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/deleteById")
    public boolean deleteById(@RequestParam(value = "id") String id) {
        boolean flag = this.testModeNested1Service.deleteById(id);
        return flag;
    }
    

	/**
     * 校验参数对应实例是否重复
	 * @param excludeId
     * @param params
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/check/{excludeId}")
    public Map<String, String> check(
            @PathVariable(value = "excludeId", required = false) String excludeId,
            @RequestParam Map<String, String> params) {
        boolean flag = this.testModeNested1Service.exists(params, excludeId);
        
        Map<String, String> resMap = new HashMap<String, String>();
        if (!flag) {
            resMap.put("ok", "");
        } else {
            resMap.put("error", "重复值");
        }
        return resMap;
    }
}