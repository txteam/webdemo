/*
 * 描       述:  <描述>
 * 修  改 人:  
 * 修改时间:
 * <修改描述:>
 */
package com.tx.component.operator.controller;

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

import com.tx.component.auth.annotation.CheckOperateAuth;
import com.tx.component.operator.model.PostType;
import com.tx.component.operator.service.PostTypeService;
import com.tx.core.paged.model.PagedList;

/**
 * PostType显示层逻辑<br/>
 * <功能详细描述>
 * 
 * @author  PengQingyang
 * @version  [版本号, 2013-8-27]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
//TODO:指定自动生成的权限上级权限,以及对应的权限名称
@CheckOperateAuth(key = "postType_manage", name = "postType管理")
@Controller("postTypeController")
@RequestMapping("/postType")
public class PostTypeController {
    
    @Resource(name = "postTypeService")
    private PostTypeService postTypeService;
    
    /**
      * 跳转到查询PostType列表页面<br/>
      *<功能详细描述>
      * @return [参数说明]
      * 
      * @return String [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/toQueryPostTypeList")
    public String toQueryPostTypeList() {
        return "/operator/queryPostTypeList";
    }
    
     /**
      * 跳转到查询PostType分页列表页面<br/>
      *<功能详细描述>
      * @return [参数说明]
      * 
      * @return String [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/toQueryPostTypePagedList")
    public String toQueryPostTypePagedList() {
        return "/operator/queryPostTypePagedList";
    }
    
    /**
      * 跳转到添加PostType页面<br/>
      *<功能详细描述>
      * @return [参数说明]
      * 
      * @return String [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/toAddPostType")
    public String toAddPostType(ModelMap response) {
        response.put("postType", new PostType());
        
        return "/operator/addPostType";
    }
    
    /**
     * 跳转到编辑PostType页面
     *<功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
    @RequestMapping("/toUpdatePostType")
    public String toUpdatePostType(
    		@RequestParam("postTypeId") String postTypeId,
            ModelMap modelMap) {
        PostType resPostType = this.postTypeService.findPostTypeById(postTypeId); 
        modelMap.put("postType", resPostType);
        
        return "/operator/updatePostType";
    }


    /**
      * 判断PostType:name是否已经被使用
      * <功能详细描述>
	  * @param uniqueGetterName
      * @param code
      * @param excludePostTypeId
      * @return [参数说明]
      * 
      * @return boolean [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/validateNameIsExist")
    public Map<String, String> validateNameIsExist(
    		@RequestParam MultiValueMap<String, String> request,
            @RequestParam("name") String name,
            @RequestParam(value = "id", required = false) String excludePostTypeId) {
        Map<String, String> key2valueMap = new HashMap<String, String>();
        key2valueMap.put("name", name);
        
        boolean flag = this.postTypeService.isExist(key2valueMap, excludePostTypeId);
        
        Map<String, String> resMap = new HashMap<String, String>();
        if (!flag) {
        	//TODO:修改验证重复成功提示信息
            resMap.put("ok", "可用的postType name");
        } else {
        	//TODO:修改验证重复失败提示信息
            resMap.put("error", "已经存在的postType name");
        }
        return resMap;
    }
    

    /**
      * 判断PostType:code是否已经被使用
      * <功能详细描述>
	  * @param uniqueGetterName
      * @param code
      * @param excludePostTypeId
      * @return [参数说明]
      * 
      * @return boolean [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/validateCodeIsExist")
    public Map<String, String> validateCodeIsExist(
    		@RequestParam MultiValueMap<String, String> request,
            @RequestParam("code") String code,
            @RequestParam(value = "id", required = false) String excludePostTypeId) {
        Map<String, String> key2valueMap = new HashMap<String, String>();
        key2valueMap.put("code", code);
        
        boolean flag = this.postTypeService.isExist(key2valueMap, excludePostTypeId);
        
        Map<String, String> resMap = new HashMap<String, String>();
        if (!flag) {
        	//TODO:修改验证重复成功提示信息
            resMap.put("ok", "可用的postType code");
        } else {
        	//TODO:修改验证重复失败提示信息
            resMap.put("error", "已经存在的postType code");
        }
        return resMap;
    }
    
    

    /**
      * 查询PostType列表<br/>
      *<功能详细描述>
      * @return [参数说明]
      * 
      * @return List<PostType> [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/queryPostTypeListIncludeInvalid")
    public List<PostType> queryPostTypeListIncludeInvalid(
    		@RequestParam MultiValueMap<String, String> request,
			@RequestParam(value="code",required=false) String code,
			@RequestParam(value="name",required=false) String name
    	) {
        List<PostType> resList = this.postTypeService.queryPostTypeListIncludeInvalid(
			code,
			name
        );
        return resList;
    }
    
     /**
      * 查询PostType列表<br/>
      *<功能详细描述>
      * @return [参数说明]
      * 
      * @return List<PostType> [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/queryPostTypeListIncludeAppoint")
    public List<PostType> queryPostTypeListIncludeAppointId(
    		@RequestParam MultiValueMap<String, String> request,
			@RequestParam(value="code",required=false) String code,
			@RequestParam(value="name",required=false) String name,
			@RequestParam(value="appointId") String appointId
    	) {
        List<PostType> resList = this.postTypeService.queryPostTypeListIncludeAppointId(
			code,
			name,
			appointId       
        );
        return resList;
    }

    
    /**
     * 查询PostType列表<br/>
     *<功能详细描述>
     * @return [参数说明]
     * 
     * @return List<PostType> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
    @ResponseBody
    @RequestMapping("/queryPostTypeList")
    public List<PostType> queryPostTypeList(
    		@RequestParam MultiValueMap<String, String> request,
			@RequestParam(value="code",required=false) String code,
			@RequestParam(value="name",required=false) String name
    	) {
        List<PostType> resList = this.postTypeService.queryPostTypeList(
			code,
			name
        );
        return resList;
    }
    
    /**
     * 查询PostType分页列表<br/>
     *<功能详细描述>
     * @return [参数说明]
     * 
     * @return List<PostType> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
    @ResponseBody
    @RequestMapping("/queryPostTypePagedList")
    public PagedList<PostType> queryPostTypePagedList(
    		@RequestParam MultiValueMap<String, String> request,
			@RequestParam(value="code",required=false) String code,
			@RequestParam(value="name",required=false) String name,
			@RequestParam(value = "pageNumber", required = false, defaultValue = "1") int pageIndex,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize
    	) {
        PagedList<PostType> resPagedList = this.postTypeService.queryPostTypePagedList(
			code,
			name,
			pageIndex,
			pageSize
        );
        return resPagedList;
    }
    
    /**
     * 查询PostType分页列表(包含无效的实体)<br/>
     *<功能详细描述>
     * @return [参数说明]
     * 
     * @return PagedList<PostType> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
    @ResponseBody
    @RequestMapping("/queryPostTypePagedListIncludeInvalid")
    public PagedList<PostType> queryPostTypePagedListIncludeInvalid(
    		@RequestParam MultiValueMap<String, String> request,
			@RequestParam(value="code",required=false) String code,
			@RequestParam(value="name",required=false) String name,
			@RequestParam(value = "pageNumber", required = false, defaultValue = "1") int pageIndex,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize
    	) {
        PagedList<PostType> resPagedList = this.postTypeService.queryPostTypePagedListIncludeInvalid(
			code,
			name,
			pageIndex,
			pageSize
        );
        return resPagedList;
    }
    
    /**
     * 添加组织结构页面
     *<功能详细描述>
     * @param organization [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
    //TODO:修改删增加权限名称
    @CheckOperateAuth(key = "add_postType", name = "增加PostType")
    @RequestMapping("/addPostType")
    @ResponseBody
    public boolean addPostType(PostType postType) {
        this.postTypeService.insertPostType(postType);
        return true;
    }
    
    /**
      * 更新组织<br/>
      *<功能详细描述>
      * @param postType
      * @return [参数说明]
      * 
      * @return boolean [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    //TODO:修改删编辑权限名称
    @CheckOperateAuth(key = "update_postType", name = "编辑PostType")
    @RequestMapping("/updatePostType")
    @ResponseBody
    public boolean updatePostType(PostType postType) {
        this.postTypeService.updateById(postType);
        
        return true;
    }
    
    /**
      * 删除指定PostType<br/> 
      *<功能详细描述>
      * @param postTypeId
      * @return [参数说明]
      * 
      * @return boolean [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    //TODO:修改删除权限名称
    @CheckOperateAuth(key = "delete_postType", name = "删除PostType")
    @ResponseBody
    @RequestMapping("/deletePostTypeById")
    public boolean deletePostTypeById(@RequestParam(value = "postTypeId") String postTypeId) {
        boolean resFlag = this.postTypeService.deleteById(postTypeId);
        return resFlag;
    }
    
    /**
      * 禁用PostType
      * @param postTypeId
      * @return [参数说明]
      * 
      * @return boolean [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    //TODO:修改禁用权限名称
    @CheckOperateAuth(key = "disable_postType", name = "禁用PostType")
    @ResponseBody
    @RequestMapping("/disablePostTypeById")
    public boolean disablePostTypeById(@RequestParam(value = "postTypeId") String postTypeId) {
        boolean resFlag = this.postTypeService.disableById(postTypeId);
        return resFlag;
    }
    
    /**
      * 启用PostType<br/>
      *<功能详细描述>
      * @param postTypeId
      * @return [参数说明]
      * 
      * @return boolean [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    //TODO:修改启用权限名称
    @CheckOperateAuth(key = "enable_postType", name = "启用PostType")
    @ResponseBody
    @RequestMapping("/enablePostTypeById")
    public boolean enablePostTypeById(@RequestParam(value = "postTypeId") String postTypeId) {
        boolean resFlag = this.postTypeService.enableById(postTypeId);
        return resFlag;
    }
    
}
