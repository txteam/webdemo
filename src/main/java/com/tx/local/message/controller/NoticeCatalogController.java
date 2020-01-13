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

import com.tx.local.message.model.NoticeCatalog;
import com.tx.local.message.service.NoticeCatalogService;
import com.tx.core.paged.model.PagedList;

/**
 * NoticeCatalog控制层<br/>
 * 
 * @author []
 * @version [版本号]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Controller
@RequestMapping("/noticeCatalog")
public class NoticeCatalogController {
    
    //NoticeCatalog业务层
    @Resource(name = "noticeCatalogService")
    private NoticeCatalogService noticeCatalogService;
    
    /**
     * 跳转到查询NoticeCatalog列表页面<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/toQueryList")
    public String toQueryList(ModelMap response) {
        
        return "message/queryNoticeCatalogList";
    }
    
    /**
     * 跳转到新增NoticeCatalog页面<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/toAdd")
    public String toAdd(ModelMap response) {
        response.put("noticeCatalog", new NoticeCatalog());
        
        return "message/addNoticeCatalog";
    }
    
    /**
     * 跳转到编辑NoticeCatalog页面
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/toUpdate")
    public String toUpdate(@RequestParam("id") String id, ModelMap response) {
        NoticeCatalog noticeCatalog = this.noticeCatalogService.findById(id);
        response.put("noticeCatalog", noticeCatalog);
        
        return "message/updateNoticeCatalog";
    }
    
    /**
     * 查询NoticeCatalog实例列表<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<NoticeCatalog> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/queryList")
    public List<NoticeCatalog> queryList(
            @RequestParam(value = "valid", required = false) Boolean valid,
            @RequestParam MultiValueMap<String, String> request) {
        Map<String, Object> params = new HashMap<>();
        params.put("code", request.getFirst("code"));
        params.put("name", request.getFirst("name"));
        
        List<NoticeCatalog> resList = this.noticeCatalogService.queryList(valid,
                params);
        
        return resList;
    }
    
    /**
     * 查询NoticeCatalog实例分页列表<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<NoticeCatalog> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/queryPagedList")
    public PagedList<NoticeCatalog> queryPagedList(
            @RequestParam(value = "valid", required = false) Boolean valid,
            @RequestParam(value = "pageNumber", required = false, defaultValue = "1") int pageIndex,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
            @RequestParam MultiValueMap<String, String> request) {
        Map<String, Object> params = new HashMap<>();
        params.put("code", request.getFirst("code"));
        params.put("name", request.getFirst("name"));
        
        PagedList<NoticeCatalog> resPagedList = this.noticeCatalogService
                .queryPagedList(valid, params, pageIndex, pageSize);
        return resPagedList;
    }
    
    /**
     * 新增NoticeCatalog实例
     * <功能详细描述>
     * @param noticeCatalog [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/add")
    public boolean add(NoticeCatalog noticeCatalog) {
        this.noticeCatalogService.insert(noticeCatalog);
        return true;
    }
    
    /**
     * 更新NoticeCatalog实例<br/>
     * <功能详细描述>
     * @param noticeCatalog
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/update")
    public boolean update(NoticeCatalog noticeCatalog) {
        boolean flag = this.noticeCatalogService.updateById(noticeCatalog);
        return flag;
    }
    
    /**
     * 根据主键查询NoticeCatalog实例<br/> 
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
    public NoticeCatalog findById(@RequestParam(value = "id") String id) {
        NoticeCatalog noticeCatalog = this.noticeCatalogService.findById(id);
        return noticeCatalog;
    }
    
    /**
     * 根据编码查询NoticeCatalog实例<br/> 
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
    public NoticeCatalog findByCode(@RequestParam(value = "code") String code) {
        NoticeCatalog noticeCatalog = this.noticeCatalogService
                .findByCode(code);
        return noticeCatalog;
    }
    
    /**
     * 删除NoticeCatalog实例<br/> 
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
        boolean flag = this.noticeCatalogService.deleteById(id);
        return flag;
    }
    
    /**
     * 禁用NoticeCatalog实例
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
        boolean flag = this.noticeCatalogService.disableById(id);
        return flag;
    }
    
    /**
     * 启用NoticeCatalog实例<br/>
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
        boolean flag = this.noticeCatalogService.enableById(id);
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
        boolean flag = this.noticeCatalogService.exists(params, excludeId);
        
        Map<String, String> resMap = new HashMap<String, String>();
        if (!flag) {
            resMap.put("ok", "");
        } else {
            resMap.put("error", "重复值");
        }
        return resMap;
    }
    
}