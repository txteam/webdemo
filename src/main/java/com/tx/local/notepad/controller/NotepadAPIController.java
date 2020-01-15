/*
 * 描       述:  <描述>
 * 修  改 人:  
 * 修改时间:
 * <修改描述:>
 */
package com.tx.local.notepad.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tx.core.paged.model.PagedList;
import com.tx.core.querier.model.Querier;
import com.tx.local.notepad.facade.NotepadFacade;
import com.tx.local.notepad.model.Notepad;
import com.tx.local.notepad.service.NotepadService;

import io.swagger.annotations.Api;

/**
 * 记事本API控制层[NotepadAPIController]<br/>
 * 
 * @author []
 * @version [版本号]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@RestController
@Api(tags = "记事本API")
@RequestMapping("/api/notepad")
public class NotepadAPIController implements NotepadFacade {
    
    //记事本业务层
    @Resource(name = "notepadService")
    private NotepadService notepadService;
    
    /**
     * 新增记事本<br/>
     * <功能详细描述>
     * @param notepad [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public Notepad insert(@RequestBody Notepad notepad) {
        this.notepadService.insert(notepad);
        return notepad;
    }
    
    /**
     * 根据id删除记事本<br/> 
     * <功能详细描述>
     * @param id
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public boolean deleteById(
            @PathVariable(value = "id", required = true) String id) {
        boolean flag = this.notepadService.deleteById(id);
        return flag;
    }
    
    /**
     * 更新记事本<br/>
     * <功能详细描述>
     * @param notepad
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public boolean updateById(
            @PathVariable(value = "id", required = true) String id,
            @RequestBody Notepad notepad) {
        boolean flag = this.notepadService.updateById(id, notepad);
        return flag;
    }
    
    /**
     * 根据主键查询记事本<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return Notepad [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public Notepad findById(
            @PathVariable(value = "id", required = true) String id) {
        Notepad res = this.notepadService.findById(id);
        
        return res;
    }
    
    /**
     * 查询记事本实例列表<br/>
     * <功能详细描述>
     * @param querier
     * @return [参数说明]
     * 
     * @return List<Notepad> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public List<Notepad> queryList(@RequestBody Querier querier) {
        List<Notepad> resList = this.notepadService.queryList(querier);
        
        return resList;
    }
    
    /**
     * 查询记事本分页列表<br/>
     * <功能详细描述>
     * @param pageIndex
     * @param pageSize
     * @param querier
     * @return [参数说明]
     * 
     * @return PagedList<Notepad> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public PagedList<Notepad> queryPagedList(@RequestBody Querier querier,
            @PathVariable(value = "pageNumber", required = true) int pageIndex,
            @PathVariable(value = "pageSize", required = true) int pageSize) {
        PagedList<Notepad> resPagedList = this.notepadService
                .queryPagedList(querier, pageIndex, pageSize);
        return resPagedList;
    }
    
    /**
     * 查询记事本数量<br/>
     * <功能详细描述>
     * @param querier
     * @return [参数说明]
     * 
     * @return int [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public int count(@RequestBody Querier querier) {
        int count = this.notepadService.count(querier);
        
        return count;
    }
    
    /**
     * 查询记事本是否存在<br/>
     * @param excludeId
     * @param querier
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public boolean exists(@RequestBody Querier querier,
            @RequestParam(value = "excludeId", required = false) String excludeId) {
        boolean flag = this.notepadService.exists(querier, excludeId);
        
        return flag;
    }
}