/*
 * 描       述:  <描述>
 * 修  改 人:  
 * 修改时间:
 * <修改描述:>
 */
package com.tx.local.institution.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.tx.local.institution.model.InstitutionInfo;
import com.tx.local.institution.service.InstitutionInfoService;
import com.tx.local.security.util.WebContextUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tx.local.institution.model.InstitutionCapacity;
import com.tx.local.institution.service.InstitutionCapacityService;
import com.tx.core.paged.model.PagedList;

import com.tx.local.institution.model.CapacityTypeEnum;

/**
 * 机构产能控制层<br/>
 * 
 * @author []
 * @version [版本号]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Controller
@RequestMapping("/institutionCapacity")
public class InstitutionCapacityController {
    
    //机构产能业务层
    @Resource(name = "institutionCapacityService")
    private InstitutionCapacityService institutionCapacityService;
    
    /**
     * 跳转到查询机构产能分页列表页面<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/toQueryPagedList")
    public String toQueryPagedList(String type,String institutionId,ModelMap response) {
		response.put("types", CapacityTypeEnum.values());
        InstitutionCapacity item = new InstitutionCapacity();
        item.setInstitutionId(institutionId);
        item.setVcid(WebContextUtils.getVcid());
        item.setType(CapacityTypeEnum.valueOf(type));
        response.put("institutionCapacity", item);
        response.put("type", CapacityTypeEnum.valueOf(type));
        return "institution/queryInstitutionCapacityList";
    }

    @RequestMapping("/modfiyCapacityInfo")
    public String modfiyCapacityInfo(String institutionId,ModelMap response) {
        response.put("institutionId", institutionId);
        return "institution/modfiyCapacityInfo";
    }

    
    /**
     * 跳转到新增机构产能页面<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/toAdd")
    public String toAdd(ModelMap response) {
    	response.put("institutionCapacity", new InstitutionCapacity());
    	
		response.put("types", CapacityTypeEnum.values());

        return "institution/addInstitutionCapacity";
    }
    
    /**
     * 跳转到编辑机构产能页面
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
        InstitutionCapacity institutionCapacity = this.institutionCapacityService.findById(id); 
        response.put("institutionCapacity", institutionCapacity);

		response.put("types", CapacityTypeEnum.values());
        
        return "institution/updateInstitutionCapacity";
    }

    /**
     * 查询机构产能实例列表<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<InstitutionCapacity> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/queryList")
    public List<InstitutionCapacity> queryList(
    		@RequestParam MultiValueMap<String, String> request
    	) {
        Map<String, Object> params = new HashMap<>();
		params.put("name", request.getFirst("name"));
    	
        List<InstitutionCapacity> resList = this.institutionCapacityService.queryList(
			params         
        );
  
        return resList;
    }
    
    /**
     * 查询机构产能实例分页列表<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<InstitutionCapacity> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/queryPagedList")
    public PagedList<InstitutionCapacity> queryPagedList(
			@RequestParam(value = "pageNumber", required = false, defaultValue = "1") int pageIndex,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
            @RequestParam MultiValueMap<String, String> request
    	) {
		Map<String, Object> params = new HashMap<>();
		params.put("name", request.getFirst("name"));
        params.put("type", request.getFirst("type"));

        PagedList<InstitutionCapacity> resPagedList = this.institutionCapacityService.queryPagedList(
			params,
			pageIndex,
			pageSize
        );
        return resPagedList;
    }
    
    /**
     * 新增机构产能实例
     * <功能详细描述>
     * @param institutionCapacity [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/add")
    public boolean add(InstitutionCapacity institutionCapacity) {
        this.institutionCapacityService.insert(institutionCapacity);
        return true;
    }
    
    /**
     * 更新机构产能实例<br/>
     * <功能详细描述>
     * @param institutionCapacity
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/update")
    public boolean update(InstitutionCapacity institutionCapacity) {
        boolean flag = this.institutionCapacityService.updateById(institutionCapacity);
        return flag;
    }
    
    /**
     * 根据主键查询机构产能实例<br/> 
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
    public InstitutionCapacity findById(@RequestParam(value = "id") String id) {
        InstitutionCapacity institutionCapacity = this.institutionCapacityService.findById(id);
        return institutionCapacity;
    }

    /**
     * 删除机构产能实例<br/> 
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
        boolean flag = this.institutionCapacityService.deleteById(id);
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
        boolean flag = this.institutionCapacityService.exists(params, excludeId);
        
        Map<String, String> resMap = new HashMap<String, String>();
        if (!flag) {
            resMap.put("ok", "");
        } else {
            resMap.put("error", "重复值");
        }
        return resMap;
    }
    
}