/*
 * 描       述:  <描述>
 * 修  改 人:  
 * 修改时间:
 * <修改描述:>
 */
package com.tx.local.calendar.controller;

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

import com.tx.local.calendar.model.CalendarEventCatalog;
import com.tx.local.calendar.service.CalendarEventCatalogService;
import com.tx.core.paged.model.PagedList;

import com.tx.local.calendar.model.CalendarEventTypeEnum;
import com.tx.local.calendar.model.CalendarEventTopicTypeEnum;

/**
 * 日程分类控制层<br/>
 * 
 * @author []
 * @version [版本号]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Controller
@RequestMapping("/calendarEventCatalog")
public class CalendarEventCatalogController {
    
    //日程分类业务层
    @Resource(name = "calendarEventCatalogService")
    private CalendarEventCatalogService calendarEventCatalogService;
    
    /**
     * 跳转到查询日程分类列表页面<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/toQueryList")
    public String toQueryList(ModelMap response) {
		response.put("types", CalendarEventTypeEnum.values());
		response.put("topicTypes", CalendarEventTopicTypeEnum.values());

        return "calendar/queryCalendarEventCatalogList";
    }
    
    /**
     * 跳转到新增日程分类页面<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/toAdd")
    public String toAdd(ModelMap response) {
    	response.put("calendarEventCatalog", new CalendarEventCatalog());
    	
		response.put("types", CalendarEventTypeEnum.values());
		response.put("topicTypes", CalendarEventTopicTypeEnum.values());

        return "calendar/addCalendarEventCatalog";
    }
    
    /**
     * 跳转到编辑日程分类页面
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
        CalendarEventCatalog calendarEventCatalog = this.calendarEventCatalogService.findById(id); 
        response.put("calendarEventCatalog", calendarEventCatalog);

		response.put("types", CalendarEventTypeEnum.values());
		response.put("topicTypes", CalendarEventTopicTypeEnum.values());
        
        return "calendar/updateCalendarEventCatalog";
    }

    /**
     * 查询日程分类实例列表<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<CalendarEventCatalog> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/queryList")
    public List<CalendarEventCatalog> queryList(
			@RequestParam(value="valid",required=false) Boolean valid,
    		@RequestParam MultiValueMap<String, String> request
    	) {
        Map<String, Object> params = new HashMap<>();
		params.put("code", request.getFirst("code"));
		params.put("name", request.getFirst("name"));
    	
        List<CalendarEventCatalog> resList = this.calendarEventCatalogService.queryList(
			valid,
			params         
        );
  
        return resList;
    }
    
    /**
     * 查询日程分类实例分页列表<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<CalendarEventCatalog> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/queryPagedList")
    public PagedList<CalendarEventCatalog> queryPagedList(
			@RequestParam(value="valid",required=false) Boolean valid,
			@RequestParam(value = "pageNumber", required = false, defaultValue = "1") int pageIndex,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
            @RequestParam MultiValueMap<String, String> request
    	) {
		Map<String, Object> params = new HashMap<>();
		params.put("code", request.getFirst("code"));
		params.put("name", request.getFirst("name"));

        PagedList<CalendarEventCatalog> resPagedList = this.calendarEventCatalogService.queryPagedList(
			valid,
			params,
			pageIndex,
			pageSize
        );
        return resPagedList;
    }
    
    /**
     * 新增日程分类实例
     * <功能详细描述>
     * @param calendarEventCatalog [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/add")
    public boolean add(CalendarEventCatalog calendarEventCatalog) {
        this.calendarEventCatalogService.insert(calendarEventCatalog);
        return true;
    }
    
    /**
     * 更新日程分类实例<br/>
     * <功能详细描述>
     * @param calendarEventCatalog
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/update")
    public boolean update(CalendarEventCatalog calendarEventCatalog) {
        boolean flag = this.calendarEventCatalogService.updateById(calendarEventCatalog);
        return flag;
    }
    
    /**
     * 根据主键查询日程分类实例<br/> 
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
    public CalendarEventCatalog findById(@RequestParam(value = "id") String id) {
        CalendarEventCatalog calendarEventCatalog = this.calendarEventCatalogService.findById(id);
        return calendarEventCatalog;
    }

	/**
     * 根据编码查询日程分类实例<br/> 
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
    public CalendarEventCatalog findByCode(@RequestParam(value = "code") String code) {
        CalendarEventCatalog calendarEventCatalog = this.calendarEventCatalogService.findByCode(code);
        return calendarEventCatalog;
    }
    
    /**
     * 删除日程分类实例<br/> 
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
        boolean flag = this.calendarEventCatalogService.deleteById(id);
        return flag;
    }
    
    /**
     * 禁用日程分类实例
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
        boolean flag = this.calendarEventCatalogService.disableById(id);
        return flag;
    }
    
    /**
     * 启用日程分类实例<br/>
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
        boolean flag = this.calendarEventCatalogService.enableById(id);
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
        boolean flag = this.calendarEventCatalogService.exists(params, excludeId);
        
        Map<String, String> resMap = new HashMap<String, String>();
        if (!flag) {
            resMap.put("ok", "");
        } else {
            resMap.put("error", "重复值");
        }
        return resMap;
    }
    
}