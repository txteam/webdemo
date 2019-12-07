/*
 * 描       述:  <描述>
 * 修  改 人:  
 * 修改时间:
 * <修改描述:>
 */
package com.tx.local.content.controller;

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

import com.tx.local.content.model.ContentInfoLevel;
import com.tx.local.content.service.ContentInfoLevelService;
import com.tx.core.paged.model.PagedList;


/**
 * 内容信息级别控制层<br/>
 * 
 * @author []
 * @version [版本号]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Controller
@RequestMapping("/contentInfoLevel")
public class ContentInfoLevelController {
    
    //内容信息级别业务层
    @Resource(name = "contentInfoLevelService")
    private ContentInfoLevelService contentInfoLevelService;
    
    /**
     * 跳转到查询内容信息级别列表页面<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/toQueryList")
    public String toQueryList(ModelMap response) {

        return "content/queryContentInfoLevelList";
    }
    
    /**
     * 跳转到新增内容信息级别页面<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/toAdd")
    public String toAdd(ModelMap response) {
    	response.put("contentInfoLevel", new ContentInfoLevel());
    	

        return "content/addContentInfoLevel";
    }
    
    /**
     * 跳转到编辑内容信息级别页面
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
        ContentInfoLevel contentInfoLevel = this.contentInfoLevelService.findById(id); 
        response.put("contentInfoLevel", contentInfoLevel);

        
        return "content/updateContentInfoLevel";
    }

    /**
     * 查询内容信息级别实例列表<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<ContentInfoLevel> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/queryList")
    public List<ContentInfoLevel> queryList(
			@RequestParam(value="valid",required=false) Boolean valid,
    		@RequestParam MultiValueMap<String, String> request
    	) {
        Map<String,Object> params = new HashMap<>();
        //params.put("",request.getFirst(""));
    	
        List<ContentInfoLevel> resList = this.contentInfoLevelService.queryList(
			valid,
			params         
        );
  
        return resList;
    }
    
    /**
     * 查询内容信息级别实例分页列表<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<ContentInfoLevel> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/queryPagedList")
    public PagedList<ContentInfoLevel> queryPagedList(
			@RequestParam(value="valid",required=false) Boolean valid,
			@RequestParam(value = "pageNumber", required = false, defaultValue = "1") int pageIndex,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
            @RequestParam MultiValueMap<String, String> request
    	) {
		Map<String,Object> params = new HashMap<>();
		//params.put("",request.getFirst(""));

        PagedList<ContentInfoLevel> resPagedList = this.contentInfoLevelService.queryPagedList(
			valid,
			params,
			pageIndex,
			pageSize
        );
        return resPagedList;
    }
    
    /**
     * 新增内容信息级别实例
     * <功能详细描述>
     * @param contentInfoLevel [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/add")
    public boolean add(ContentInfoLevel contentInfoLevel) {
        this.contentInfoLevelService.insert(contentInfoLevel);
        return true;
    }
    
    /**
     * 更新内容信息级别实例<br/>
     * <功能详细描述>
     * @param contentInfoLevel
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/update")
    public boolean update(ContentInfoLevel contentInfoLevel) {
        boolean flag = this.contentInfoLevelService.updateById(contentInfoLevel);
        return flag;
    }
    
    /**
     * 根据主键查询内容信息级别实例<br/> 
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
    public ContentInfoLevel findById(@RequestParam(value = "id") String id) {
        ContentInfoLevel contentInfoLevel = this.contentInfoLevelService.findById(id);
        return contentInfoLevel;
    }

	/**
     * 根据编码查询内容信息级别实例<br/> 
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
    public ContentInfoLevel findByCode(@RequestParam(value = "code") String code) {
        ContentInfoLevel contentInfoLevel = this.contentInfoLevelService.findByCode(code);
        return contentInfoLevel;
    }
    
    /**
     * 删除内容信息级别实例<br/> 
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
        boolean flag = this.contentInfoLevelService.deleteById(id);
        return flag;
    }
    
    /**
     * 禁用内容信息级别实例
     * @param id
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/disableById")
    public boolean disableById(@RequestParam(value = "id") String id) {
        boolean flag = this.contentInfoLevelService.disableById(id);
        return flag;
    }
    
    /**
     * 启用内容信息级别实例<br/>
     * <功能详细描述>
     * @param id
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/enableById")
    public boolean enableById(@RequestParam(value = "id") String id) {
        boolean flag = this.contentInfoLevelService.enableById(id);
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
            @RequestParam(value = "excludeId", required = false) String excludeId,
            @RequestParam Map<String, String> params) {
        boolean flag = this.contentInfoLevelService.exists(params, excludeId);
        
        Map<String, String> resMap = new HashMap<String, String>();
        if (!flag) {
            resMap.put("ok", "");
        } else {
            resMap.put("error", "重复值");
        }
        return resMap;
    }
    
}