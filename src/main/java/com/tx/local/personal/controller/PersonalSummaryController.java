/*
 * 描       述:  <描述>
 * 修  改 人:  
 * 修改时间:
 * <修改描述:>
 */
package com.tx.local.personal.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tx.local.personal.model.PersonalSummary;
import com.tx.local.personal.service.PersonalSummaryService;
import com.tx.core.paged.model.PagedList;


/**
 * PersonalSummary控制层<br/>
 * 
 * @author []
 * @version [版本号]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Controller
@RequestMapping("/personalSummary")
public class PersonalSummaryController {
    
    //PersonalSummary业务层
    @Resource(name = "personalSummaryService")
    private PersonalSummaryService personalSummaryService;
    
    /**
     * 跳转到查询PersonalSummary分页列表页面<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/toQueryPagedList")
    public String toQueryPagedList(ModelMap response) {

        return "personal/queryPersonalSummaryPagedList";
    }
    
    /**
     * 跳转到新增PersonalSummary页面<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/toAdd")
    public String toAdd(ModelMap response) {
    	response.put("personalSummary", new PersonalSummary());
    	

        return "personal/addPersonalSummary";
    }
    
    /**
     * 跳转到编辑PersonalSummary页面
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
        PersonalSummary personalSummary = this.personalSummaryService.findById(id); 
        response.put("personalSummary", personalSummary);

        
        return "personal/updatePersonalSummary";
    }

    /**
     * 查询PersonalSummary实例列表<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<PersonalSummary> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/queryList")
    public List<PersonalSummary> queryList(
    		@RequestParam MultiValueMap<String, String> request
    	) {
        Map<String, Object> params = new HashMap<>();
		params.put("name", request.getFirst("name"));
    	
        List<PersonalSummary> resList = this.personalSummaryService.queryList(
			params         
        );
  
        return resList;
    }
    
    /**
     * 查询PersonalSummary实例分页列表<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<PersonalSummary> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/queryPagedList")
    public PagedList<PersonalSummary> queryPagedList(
			@RequestParam(value = "pageNumber", required = false, defaultValue = "1") int pageIndex,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
            @RequestParam MultiValueMap<String, String> request
    	) {
		Map<String, Object> params = new HashMap<>();
		params.put("name", request.getFirst("name"));

        PagedList<PersonalSummary> resPagedList = this.personalSummaryService.queryPagedList(
			params,
			pageIndex,
			pageSize
        );
        return resPagedList;
    }
    
    /**
     * 新增PersonalSummary实例
     * <功能详细描述>
     * @param personalSummary [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/add")
    public boolean add(PersonalSummary personalSummary) {
        this.personalSummaryService.insert(personalSummary);
        return true;
    }
    
    /**
     * 更新PersonalSummary实例<br/>
     * <功能详细描述>
     * @param personalSummary
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/update")
    public boolean update(PersonalSummary personalSummary) {
        boolean flag = this.personalSummaryService.updateById(personalSummary);
        return flag;
    }
    
    /**
     * 根据主键查询PersonalSummary实例<br/> 
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
    public PersonalSummary findById(@RequestParam(value = "id") String id) {
        PersonalSummary personalSummary = this.personalSummaryService.findById(id);
        return personalSummary;
    }

    /**
     * 删除PersonalSummary实例<br/> 
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
        boolean flag = this.personalSummaryService.deleteById(id);
        return flag;
    }
    
	/**
     * 校验是否重复<br/>
	 * @param excludeId
     * @param params
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/validate")
    public Map<String, String> validate(
            @RequestParam(value = "id", required = false) String excludeId,
            @RequestParam Map<String, String> params) {
        params.remove("id");
        boolean flag = this.personalSummaryService.exists(params, excludeId);
        
        Map<String, String> resMap = new HashMap<String, String>();
        if (!flag) {
            resMap.put("ok", "");
        } else {
            resMap.put("error", "重复值");
        }
        return resMap;
    }
    
}