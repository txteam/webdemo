/*
 * 描       述:  <描述>
 * 修  改 人:  
 * 修改时间:
 * <修改描述:>
 */
package com.tx.local.vitualcenter.controller;

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

import com.tx.local.vitualcenter.model.ConfigItem4VC;
import com.tx.local.vitualcenter.service.ConfigItem4VCService;
import com.tx.core.paged.model.PagedList;


/**
 * ConfigItem4VC控制层<br/>
 * 
 * @author []
 * @version [版本号]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Controller
@RequestMapping("/configItem4VC")
public class ConfigItem4VCController {
    
    //ConfigItem4VC业务层
    @Resource(name = "configItem4VCService")
    private ConfigItem4VCService configItem4VCService;
    
    /**
     * 跳转到查询ConfigItem4VC列表页面<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/toQueryTreeList")
    public String toQueryTreeList(ModelMap response) {

        return "vitualcenter/queryConfigItem4VCTreeList";
    }
    
    /**
     * 跳转到新增ConfigItem4VC页面<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/toAdd")
    public String toAdd(ModelMap response) {
    	response.put("configItem4VC", new ConfigItem4VC());
    	

        return "vitualcenter/addConfigItem4VC";
    }
    
    /**
     * 跳转到编辑ConfigItem4VC页面
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
        ConfigItem4VC configItem4VC = this.configItem4VCService.findById(id); 
        response.put("configItem4VC", configItem4VC);

        
        return "vitualcenter/updateConfigItem4VC";
    }

    /**
     * 查询ConfigItem4VC实例列表<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<ConfigItem4VC> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/queryList")
    public List<ConfigItem4VC> queryList(
    		@RequestParam MultiValueMap<String, String> request
    	) {
        Map<String, Object> params = new HashMap<>();
		params.put("code", request.getFirst("code"));
		params.put("name", request.getFirst("name"));
    	
        List<ConfigItem4VC> resList = this.configItem4VCService.queryList(
			params         
        );
  
        return resList;
    }
    
    /**
     * 查询ConfigItem4VC实例分页列表<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<ConfigItem4VC> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/queryPagedList")
    public PagedList<ConfigItem4VC> queryPagedList(
			@RequestParam(value = "pageNumber", required = false, defaultValue = "1") int pageIndex,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
            @RequestParam MultiValueMap<String, String> request
    	) {
		Map<String, Object> params = new HashMap<>();
		params.put("code", request.getFirst("code"));
		params.put("name", request.getFirst("name"));

        PagedList<ConfigItem4VC> resPagedList = this.configItem4VCService.queryPagedList(
			params,
			pageIndex,
			pageSize
        );
        return resPagedList;
    }
    
    /**
     * 新增ConfigItem4VC实例
     * <功能详细描述>
     * @param configItem4VC [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/add")
    public boolean add(ConfigItem4VC configItem4VC) {
        this.configItem4VCService.insert(configItem4VC);
        return true;
    }
    
    /**
     * 更新ConfigItem4VC实例<br/>
     * <功能详细描述>
     * @param configItem4VC
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/update")
    public boolean update(ConfigItem4VC configItem4VC) {
        boolean flag = this.configItem4VCService.updateById(configItem4VC);
        return flag;
    }
    
    /**
     * 根据主键查询ConfigItem4VC实例<br/> 
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
    public ConfigItem4VC findById(@RequestParam(value = "id") String id) {
        ConfigItem4VC configItem4VC = this.configItem4VCService.findById(id);
        return configItem4VC;
    }

	/**
     * 根据编码查询ConfigItem4VC实例<br/> 
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
    public ConfigItem4VC findByCode(@RequestParam(value = "code") String code) {
        ConfigItem4VC configItem4VC = this.configItem4VCService.findByCode(code);
        return configItem4VC;
    }
    
    /**
     * 删除ConfigItem4VC实例<br/> 
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
        boolean flag = this.configItem4VCService.deleteById(id);
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
        boolean flag = this.configItem4VCService.exists(params, excludeId);
        
        Map<String, String> resMap = new HashMap<String, String>();
        if (!flag) {
            resMap.put("ok", "");
        } else {
            resMap.put("error", "重复值");
        }
        return resMap;
    }
    
    /**
     * 根据条件查询ConfigItem4VC子级列表<br/>
     * <功能详细描述>
     * @param parentId
     * @param valid
     * @param request
     * @return [参数说明]
     * 
     * @return PagedList<T> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/queryChildren")
    public List<ConfigItem4VC> queryChildren(
            @RequestParam(value = "parentId", required = true) String parentId,
            @RequestParam MultiValueMap<String, String> request) {
        Map<String, Object> params = new HashMap<>();
		params.put("code", request.getFirst("code"));
		params.put("name", request.getFirst("name"));
	
        
        List<ConfigItem4VC> resList = this.configItem4VCService
                .queryChildrenByParentId(parentId, params);
        
        return resList;
    }
    
    /**
     * 根据条件查询ConfigItem4VC子、孙级列表<br/>
     * <功能详细描述>
     * @param parentId
     * @param valid
     * @param request
     * @return [参数说明]
     * 
     * @return PagedList<T> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/queryDescendants")
    public List<ConfigItem4VC> queryDescendants(
            @RequestParam(value = "parentId", required = true) String parentId,
            @RequestParam MultiValueMap<String, String> request) {
        Map<String, Object> params = new HashMap<>();
		params.put("code", request.getFirst("code"));
		params.put("name", request.getFirst("name"));
        
        List<ConfigItem4VC> resList = this.configItem4VCService
                .queryDescendantsByParentId(parentId, params);
        
        return resList;
    }
    
}