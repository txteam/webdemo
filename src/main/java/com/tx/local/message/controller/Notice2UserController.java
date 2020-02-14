/*
 * 描       述:  <描述>
 * 修  改 人:  
 * 修改时间:
 * <修改描述:>
 */
package com.tx.local.message.controller;

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

import com.tx.local.message.model.Notice2User;
import com.tx.local.message.service.Notice2UserService;
import com.tx.core.paged.model.PagedList;

import com.tx.local.message.model.MessageUserTypeEnum;

/**
 * Notice2User控制层<br/>
 * 
 * @author []
 * @version [版本号]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Controller
@RequestMapping("/notice2User")
public class Notice2UserController {
    
    //Notice2User业务层
    @Resource(name = "notice2UserService")
    private Notice2UserService notice2UserService;
    
    /**
     * 跳转到查询Notice2User分页列表页面<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/toQueryPagedList")
    public String toQueryPagedList(ModelMap response) {
		response.put("userTypes", MessageUserTypeEnum.values());

        return "message/queryNotice2UserPagedList";
    }
    
    /**
     * 跳转到新增Notice2User页面<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/toAdd")
    public String toAdd(ModelMap response) {
    	response.put("notice2User", new Notice2User());
    	
		response.put("userTypes", MessageUserTypeEnum.values());

        return "message/addNotice2User";
    }
    
    /**
     * 跳转到编辑Notice2User页面
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
        Notice2User notice2User = this.notice2UserService.findById(id); 
        response.put("notice2User", notice2User);

		response.put("userTypes", MessageUserTypeEnum.values());
        
        return "message/updateNotice2User";
    }

    /**
     * 查询Notice2User实例列表<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<Notice2User> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/queryList")
    public List<Notice2User> queryList(
    		@RequestParam MultiValueMap<String, String> request
    	) {
        Map<String, Object> params = new HashMap<>();
		params.put("name", request.getFirst("name"));
    	
        List<Notice2User> resList = this.notice2UserService.queryList(
			params         
        );
  
        return resList;
    }
    
    /**
     * 查询Notice2User实例分页列表<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<Notice2User> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/queryPagedList")
    public PagedList<Notice2User> queryPagedList(
			@RequestParam(value = "pageNumber", required = false, defaultValue = "1") int pageIndex,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
            @RequestParam MultiValueMap<String, String> request
    	) {
		Map<String, Object> params = new HashMap<>();
		params.put("name", request.getFirst("name"));

        PagedList<Notice2User> resPagedList = this.notice2UserService.queryPagedList(
			params,
			pageIndex,
			pageSize
        );
        return resPagedList;
    }
    
    /**
     * 新增Notice2User实例
     * <功能详细描述>
     * @param notice2User [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/add")
    public boolean add(Notice2User notice2User) {
        this.notice2UserService.insert(notice2User);
        return true;
    }
    
    /**
     * 更新Notice2User实例<br/>
     * <功能详细描述>
     * @param notice2User
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/update")
    public boolean update(Notice2User notice2User) {
        boolean flag = this.notice2UserService.updateById(notice2User);
        return flag;
    }
    
    /**
     * 根据主键查询Notice2User实例<br/> 
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
    public Notice2User findById(@RequestParam(value = "id") String id) {
        Notice2User notice2User = this.notice2UserService.findById(id);
        return notice2User;
    }

    /**
     * 删除Notice2User实例<br/> 
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
        boolean flag = this.notice2UserService.deleteById(id);
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
        boolean flag = this.notice2UserService.exists(params, excludeId);
        
        Map<String, String> resMap = new HashMap<String, String>();
        if (!flag) {
            resMap.put("ok", "");
        } else {
            resMap.put("error", "重复值");
        }
        return resMap;
    }
    
}