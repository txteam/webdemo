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

import com.tx.local.content.model.ContentInfoCategory;
import com.tx.local.content.service.ContentInfoCategoryService;
import com.tx.core.paged.model.PagedList;


/**
 * 内容信息分类控制层<br/>
 * 
 * @author []
 * @version [版本号]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Controller
@RequestMapping("/contentInfoCategory")
public class ContentInfoCategoryController {
    
    //内容信息分类业务层
    @Resource(name = "contentInfoCategoryService")
    private ContentInfoCategoryService contentInfoCategoryService;
    
    /**
     * 跳转到查询内容信息分类列表页面<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/toQueryList")
    public String toQueryList(ModelMap response) {

        return "content/queryContentInfoCategoryList";
    }
    
    /**
     * 跳转到新增内容信息分类页面<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/toAdd")
    public String toAdd(ModelMap response) {
    	response.put("contentInfoCategory", new ContentInfoCategory());
    	

        return "content/addContentInfoCategory";
    }
    
    /**
     * 跳转到编辑内容信息分类页面
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
        ContentInfoCategory contentInfoCategory = this.contentInfoCategoryService.findById(id); 
        response.put("contentInfoCategory", contentInfoCategory);

        
        return "content/updateContentInfoCategory";
    }

    /**
     * 查询内容信息分类实例列表<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<ContentInfoCategory> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/queryList")
    public List<ContentInfoCategory> queryList(
			@RequestParam(value="valid",required=false) Boolean valid,
    		@RequestParam MultiValueMap<String, String> request
    	) {
        Map<String,Object> params = new HashMap<>();
        //params.put("",request.getFirst(""));
    	
        List<ContentInfoCategory> resList = this.contentInfoCategoryService.queryList(
			valid,
			params         
        );
  
        return resList;
    }
    
    /**
     * 查询内容信息分类实例分页列表<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<ContentInfoCategory> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/queryPagedList")
    public PagedList<ContentInfoCategory> queryPagedList(
			@RequestParam(value="valid",required=false) Boolean valid,
			@RequestParam(value = "pageNumber", required = false, defaultValue = "1") int pageIndex,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
            @RequestParam MultiValueMap<String, String> request
    	) {
		Map<String,Object> params = new HashMap<>();
		//params.put("",request.getFirst(""));

        PagedList<ContentInfoCategory> resPagedList = this.contentInfoCategoryService.queryPagedList(
			valid,
			params,
			pageIndex,
			pageSize
        );
        return resPagedList;
    }
    
    /**
     * 新增内容信息分类实例
     * <功能详细描述>
     * @param contentInfoCategory [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/add")
    public boolean add(ContentInfoCategory contentInfoCategory) {
        this.contentInfoCategoryService.insert(contentInfoCategory);
        return true;
    }
    
    /**
     * 更新内容信息分类实例<br/>
     * <功能详细描述>
     * @param contentInfoCategory
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/update")
    public boolean update(ContentInfoCategory contentInfoCategory) {
        boolean flag = this.contentInfoCategoryService.updateById(contentInfoCategory);
        return flag;
    }
    
    /**
     * 根据主键查询内容信息分类实例<br/> 
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
    public ContentInfoCategory findById(@RequestParam(value = "id") String id) {
        ContentInfoCategory contentInfoCategory = this.contentInfoCategoryService.findById(id);
        return contentInfoCategory;
    }

	/**
     * 根据编码查询内容信息分类实例<br/> 
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
    public ContentInfoCategory findByCode(@RequestParam(value = "code") String code) {
        ContentInfoCategory contentInfoCategory = this.contentInfoCategoryService.findByCode(code);
        return contentInfoCategory;
    }
    
    /**
     * 删除内容信息分类实例<br/> 
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
        boolean flag = this.contentInfoCategoryService.deleteById(id);
        return flag;
    }
    
    /**
     * 禁用内容信息分类实例
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
        boolean flag = this.contentInfoCategoryService.disableById(id);
        return flag;
    }
    
    /**
     * 启用内容信息分类实例<br/>
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
        boolean flag = this.contentInfoCategoryService.enableById(id);
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
        boolean flag = this.contentInfoCategoryService.exists(params, excludeId);
        
        Map<String, String> resMap = new HashMap<String, String>();
        if (!flag) {
            resMap.put("ok", "");
        } else {
            resMap.put("error", "重复值");
        }
        return resMap;
    }
    
}