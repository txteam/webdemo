/*
 * 描       述:  <描述>
 * 修  改 人:  
 * 修改时间:
 * <修改描述:>
 */
package com.tx.local.notepad.controller;

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

import com.tx.local.notepad.model.NotepadCatalog;
import com.tx.local.notepad.service.NotepadCatalogService;
import com.tx.core.paged.model.PagedList;

import com.tx.local.notepad.model.NotepadTypeEnum;
import com.tx.local.notepad.model.NotepadTopicTypeEnum;

/**
 * 记事本分类控制层<br/>
 * 
 * @author []
 * @version [版本号]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Controller
@RequestMapping("/notepadCatalog")
public class NotepadCatalogController {
    
    //记事本分类业务层
    @Resource(name = "notepadCatalogService")
    private NotepadCatalogService notepadCatalogService;
    
    /**
     * 跳转到查询记事本分类列表页面<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/toQueryList")
    public String toQueryList(ModelMap response) {
        response.put("types", NotepadTypeEnum.values());
        response.put("topicTypes", NotepadTopicTypeEnum.values());
        
        return "notepad/queryNotepadCatalogList";
    }
    
    /**
     * 跳转到新增记事本分类页面<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/toAdd")
    public String toAdd(ModelMap response) {
        response.put("notepadCatalog", new NotepadCatalog());
        
        response.put("types", NotepadTypeEnum.values());
        response.put("topicTypes", NotepadTopicTypeEnum.values());
        
        return "notepad/addNotepadCatalog";
    }
    
    /**
     * 跳转到编辑记事本分类页面
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/toUpdate")
    public String toUpdate(@RequestParam("id") String id, ModelMap response) {
        NotepadCatalog notepadCatalog = this.notepadCatalogService.findById(id);
        response.put("notepadCatalog", notepadCatalog);
        
        response.put("types", NotepadTypeEnum.values());
        response.put("topicTypes", NotepadTopicTypeEnum.values());
        
        return "notepad/updateNotepadCatalog";
    }
    
    /**
     * 查询记事本分类实例列表<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<NotepadCatalog> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/queryList")
    public List<NotepadCatalog> queryList(
            @RequestParam MultiValueMap<String, String> request) {
        Map<String, Object> params = new HashMap<>();
        params.put("name", request.getFirst("name"));
        
        List<NotepadCatalog> resList = this.notepadCatalogService
                .queryList(params);
        
        return resList;
    }
    
    /**
     * 查询记事本分类实例分页列表<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<NotepadCatalog> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/queryPagedList")
    public PagedList<NotepadCatalog> queryPagedList(
            @RequestParam(value = "pageNumber", required = false, defaultValue = "1") int pageIndex,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
            @RequestParam MultiValueMap<String, String> request) {
        Map<String, Object> params = new HashMap<>();
        params.put("name", request.getFirst("name"));
        
        PagedList<NotepadCatalog> resPagedList = this.notepadCatalogService
                .queryPagedList(params, pageIndex, pageSize);
        return resPagedList;
    }
    
    /**
     * 新增记事本分类实例
     * <功能详细描述>
     * @param notepadCatalog [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/add")
    public boolean add(NotepadCatalog notepadCatalog) {
        this.notepadCatalogService.insert(notepadCatalog);
        return true;
    }
    
    /**
     * 更新记事本分类实例<br/>
     * <功能详细描述>
     * @param notepadCatalog
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/update")
    public boolean update(NotepadCatalog notepadCatalog) {
        boolean flag = this.notepadCatalogService.updateById(notepadCatalog);
        return flag;
    }
    
    /**
     * 根据主键查询记事本分类实例<br/> 
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
    public NotepadCatalog findById(@RequestParam(value = "id") String id) {
        NotepadCatalog notepadCatalog = this.notepadCatalogService.findById(id);
        return notepadCatalog;
    }
    
    /**
     * 删除记事本分类实例<br/> 
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
        boolean flag = this.notepadCatalogService.deleteById(id);
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
        boolean flag = this.notepadCatalogService.exists(params, excludeId);
        
        Map<String, String> resMap = new HashMap<String, String>();
        if (!flag) {
            resMap.put("ok", "");
        } else {
            resMap.put("error", "重复值");
        }
        return resMap;
    }
    
    /**
     * 根据条件查询记事本分类子级列表<br/>
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
    public List<NotepadCatalog> queryChildren(
            @RequestParam(value = "parentId", required = true) String parentId,
            @RequestParam MultiValueMap<String, String> request) {
        Map<String, Object> params = new HashMap<>();
        params.put("name", request.getFirst("name"));
        
        List<NotepadCatalog> resList = this.notepadCatalogService
                .queryChildrenByParentId(parentId, params);
        
        return resList;
    }
    
    /**
     * 根据条件查询记事本分类子、孙级列表<br/>
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
    public List<NotepadCatalog> queryDescendants(
            @RequestParam(value = "parentId", required = true) String parentId,
            @RequestParam MultiValueMap<String, String> request) {
        Map<String, Object> params = new HashMap<>();
        params.put("name", request.getFirst("name"));
        
        List<NotepadCatalog> resList = this.notepadCatalogService
                .queryDescendantsByParentId(parentId, params);
        
        return resList;
    }
    
}