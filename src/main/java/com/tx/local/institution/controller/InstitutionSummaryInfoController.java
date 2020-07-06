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

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tx.local.institution.model.InstitutionSummaryInfo;
import com.tx.local.institution.service.InstitutionSummaryInfoService;
import com.tx.core.paged.model.PagedList;

import com.tx.local.basicdata.model.IDCardTypeEnum;
import com.tx.local.basicdata.model.IDCardTypeEnum;

/**
 * InstitutionSummaryInfo控制层<br/>
 * 
 * @author []
 * @version [版本号]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Controller
@RequestMapping("/institutionSummaryInfo")
public class InstitutionSummaryInfoController {
    
    //InstitutionSummaryInfo业务层
    @Resource(name = "institutionSummaryInfoService")
    private InstitutionSummaryInfoService institutionSummaryInfoService;
    
    /**
     * 跳转到查询InstitutionSummaryInfo分页列表页面<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/toQueryPagedList")
    public String toQueryPagedList(ModelMap response) {
		response.put("agentIdCardTypes", IDCardTypeEnum.values());
		response.put("legalIdCardTypes", IDCardTypeEnum.values());

        return "institution/queryInstitutionSummaryInfoPagedList";
    }
    
    /**
     * 跳转到新增InstitutionSummaryInfo页面<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/toAdd")
    public String toAdd(ModelMap response) {
    	response.put("institutionSummaryInfo", new InstitutionSummaryInfo());
    	
		response.put("agentIdCardTypes", IDCardTypeEnum.values());
		response.put("legalIdCardTypes", IDCardTypeEnum.values());

        return "institution/addInstitutionSummaryInfo";
    }
    
    /**
     * 跳转到编辑InstitutionSummaryInfo页面
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
        InstitutionSummaryInfo institutionSummaryInfo = this.institutionSummaryInfoService.findById(id); 
        response.put("institutionSummaryInfo", institutionSummaryInfo);

		response.put("agentIdCardTypes", IDCardTypeEnum.values());
		response.put("legalIdCardTypes", IDCardTypeEnum.values());
        
        return "institution/updateInstitutionSummaryInfo";
    }

    /**
     * 查询InstitutionSummaryInfo实例列表<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<InstitutionSummaryInfo> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/queryList")
    public List<InstitutionSummaryInfo> queryList(
    		@RequestParam MultiValueMap<String, String> request
    	) {
        Map<String, Object> params = new HashMap<>();
		params.put("name", request.getFirst("name"));
    	
        List<InstitutionSummaryInfo> resList = this.institutionSummaryInfoService.queryList(
			params         
        );
  
        return resList;
    }
    
    /**
     * 查询InstitutionSummaryInfo实例分页列表<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<InstitutionSummaryInfo> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/queryPagedList")
    public PagedList<InstitutionSummaryInfo> queryPagedList(
			@RequestParam(value = "pageNumber", required = false, defaultValue = "1") int pageIndex,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
            @RequestParam MultiValueMap<String, String> request
    	) {
		Map<String, Object> params = new HashMap<>();
		params.put("name", request.getFirst("name"));

        PagedList<InstitutionSummaryInfo> resPagedList = this.institutionSummaryInfoService.queryPagedList(
			params,
			pageIndex,
			pageSize
        );
        return resPagedList;
    }
    
    /**
     * 新增InstitutionSummaryInfo实例
     * <功能详细描述>
     * @param institutionSummaryInfo [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/add")
    public boolean add(InstitutionSummaryInfo institutionSummaryInfo) {
        this.institutionSummaryInfoService.insert(institutionSummaryInfo);
        return true;
    }
    
    /**
     * 更新InstitutionSummaryInfo实例<br/>
     * <功能详细描述>
     * @param institutionSummaryInfo
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/update")
    public boolean update(InstitutionSummaryInfo institutionSummaryInfo) {
        boolean flag = this.institutionSummaryInfoService.updateById(institutionSummaryInfo);
        return flag;
    }
    
    /**
     * 根据主键查询InstitutionSummaryInfo实例<br/> 
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
    public InstitutionSummaryInfo findById(@RequestParam(value = "id") String id) {
        InstitutionSummaryInfo institutionSummaryInfo = this.institutionSummaryInfoService.findById(id);
        return institutionSummaryInfo;
    }

    /**
     * 删除InstitutionSummaryInfo实例<br/> 
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
        boolean flag = this.institutionSummaryInfoService.deleteById(id);
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
        boolean flag = this.institutionSummaryInfoService.exists(params, excludeId);
        
        Map<String, String> resMap = new HashMap<String, String>();
        if (!flag) {
            resMap.put("ok", "");
        } else {
            resMap.put("error", "重复值");
        }
        return resMap;
    }
    
}