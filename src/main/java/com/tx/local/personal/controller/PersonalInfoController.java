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

import com.tx.local.personal.model.PersonalInfo;
import com.tx.local.personal.service.PersonalInfoService;
import com.tx.core.paged.model.PagedList;

import com.tx.local.basicdata.model.SexEnum;
import com.tx.local.personal.model.PersonalTypeEnum;

/**
 * PersonalInfo控制层<br/>
 * 
 * @author []
 * @version [版本号]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Controller
@RequestMapping("/personalInfo")
public class PersonalInfoController {
    
    //PersonalInfo业务层
    @Resource(name = "personalInfoService")
    private PersonalInfoService personalInfoService;
    
    /**
     * 跳转到查询PersonalInfo分页列表页面<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/toQueryPagedList")
    public String toQueryPagedList(ModelMap response) {
		response.put("sexs", SexEnum.values());
		response.put("types", PersonalTypeEnum.values());

        return "personal/queryPersonalInfoPagedList";
    }
    
    /**
     * 跳转到新增PersonalInfo页面<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/toAdd")
    public String toAdd(ModelMap response) {
    	response.put("personalInfo", new PersonalInfo());
    	
		response.put("sexs", SexEnum.values());
		response.put("types", PersonalTypeEnum.values());

        return "personal/addPersonalInfo";
    }
    
    /**
     * 跳转到编辑PersonalInfo页面
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
        PersonalInfo personalInfo = this.personalInfoService.findById(id); 
        response.put("personalInfo", personalInfo);

		response.put("sexs", SexEnum.values());
		response.put("types", PersonalTypeEnum.values());
        
        return "personal/updatePersonalInfo";
    }

    /**
     * 查询PersonalInfo实例列表<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<PersonalInfo> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/queryList")
    public List<PersonalInfo> queryList(
    		@RequestParam MultiValueMap<String, String> request
    	) {
        Map<String, Object> params = new HashMap<>();
		params.put("name", request.getFirst("name"));
    	
        List<PersonalInfo> resList = this.personalInfoService.queryList(
			params         
        );
  
        return resList;
    }
    
    /**
     * 查询PersonalInfo实例分页列表<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<PersonalInfo> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/queryPagedList")
    public PagedList<PersonalInfo> queryPagedList(
			@RequestParam(value = "pageNumber", required = false, defaultValue = "1") int pageIndex,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
            @RequestParam MultiValueMap<String, String> request
    	) {
		Map<String, Object> params = new HashMap<>();
		params.put("name", request.getFirst("name"));

        PagedList<PersonalInfo> resPagedList = this.personalInfoService.queryPagedList(
			params,
			pageIndex,
			pageSize
        );
        return resPagedList;
    }
    
    /**
     * 新增PersonalInfo实例
     * <功能详细描述>
     * @param personalInfo [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/add")
    public boolean add(PersonalInfo personalInfo) {
        this.personalInfoService.insert(personalInfo);
        return true;
    }
    
    /**
     * 更新PersonalInfo实例<br/>
     * <功能详细描述>
     * @param personalInfo
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/update")
    public boolean update(PersonalInfo personalInfo) {
        boolean flag = this.personalInfoService.updateById(personalInfo);
        return flag;
    }
    
    /**
     * 根据主键查询PersonalInfo实例<br/> 
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
    public PersonalInfo findById(@RequestParam(value = "id") String id) {
        PersonalInfo personalInfo = this.personalInfoService.findById(id);
        return personalInfo;
    }

    /**
     * 删除PersonalInfo实例<br/> 
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
        boolean flag = this.personalInfoService.deleteById(id);
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
        boolean flag = this.personalInfoService.exists(params, excludeId);
        
        Map<String, String> resMap = new HashMap<String, String>();
        if (!flag) {
            resMap.put("ok", "");
        } else {
            resMap.put("error", "重复值");
        }
        return resMap;
    }
    
}