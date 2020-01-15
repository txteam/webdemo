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

import com.tx.local.notepad.model.Notepad;
import com.tx.local.notepad.service.NotepadService;
import com.tx.core.paged.model.PagedList;

import com.tx.local.notepad.model.NotepadTypeEnum;
import com.tx.local.notepad.model.NotepadTopicTypeEnum;

/**
 * 记事本控制层<br/>
 * 
 * @author []
 * @version [版本号]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Controller
@RequestMapping("/notepad")
public class NotepadController {
    
    //记事本业务层
    @Resource(name = "notepadService")
    private NotepadService notepadService;
    
    /**
     * 跳转到查询记事本列表页面<br/>
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
        
        return "notepad/queryNotepadList";
    }
    
    /**
     * 跳转到新增记事本页面<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/toAdd")
    public String toAdd(ModelMap response) {
        response.put("notepad", new Notepad());
        
        response.put("types", NotepadTypeEnum.values());
        response.put("topicTypes", NotepadTopicTypeEnum.values());
        
        return "notepad/addNotepad";
    }
    
    /**
     * 跳转到编辑记事本页面
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/toUpdate")
    public String toUpdate(@RequestParam("id") String id, ModelMap response) {
        Notepad notepad = this.notepadService.findById(id);
        response.put("notepad", notepad);
        
        response.put("types", NotepadTypeEnum.values());
        response.put("topicTypes", NotepadTopicTypeEnum.values());
        
        return "notepad/updateNotepad";
    }
    
    /**
     * 查询记事本实例列表<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<Notepad> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/queryList")
    public List<Notepad> queryList(
            @RequestParam MultiValueMap<String, String> request) {
        Map<String, Object> params = new HashMap<>();
        params.put("name", request.getFirst("name"));
        
        List<Notepad> resList = this.notepadService.queryList(params);
        
        return resList;
    }
    
    /**
     * 查询记事本实例分页列表<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<Notepad> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/queryPagedList")
    public PagedList<Notepad> queryPagedList(
            @RequestParam(value = "pageNumber", required = false, defaultValue = "1") int pageIndex,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
            @RequestParam MultiValueMap<String, String> request) {
        Map<String, Object> params = new HashMap<>();
        params.put("name", request.getFirst("name"));
        
        PagedList<Notepad> resPagedList = this.notepadService
                .queryPagedList(params, pageIndex, pageSize);
        return resPagedList;
    }
    
    /**
     * 新增记事本实例
     * <功能详细描述>
     * @param notepad [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/add")
    public boolean add(Notepad notepad) {
        this.notepadService.insert(notepad);
        return true;
    }
    
    /**
     * 更新记事本实例<br/>
     * <功能详细描述>
     * @param notepad
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/update")
    public boolean update(Notepad notepad) {
        boolean flag = this.notepadService.updateById(notepad);
        return flag;
    }
    
    /**
     * 根据主键查询记事本实例<br/> 
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
    public Notepad findById(@RequestParam(value = "id") String id) {
        Notepad notepad = this.notepadService.findById(id);
        return notepad;
    }
    
    /**
     * 删除记事本实例<br/> 
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
        boolean flag = this.notepadService.deleteById(id);
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
        boolean flag = this.notepadService.exists(params, excludeId);
        
        Map<String, String> resMap = new HashMap<String, String>();
        if (!flag) {
            resMap.put("ok", "");
        } else {
            resMap.put("error", "重复值");
        }
        return resMap;
    }
    
}