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
import com.tx.local.notepad.facade.NotepadCatalogFacade;
import com.tx.local.notepad.model.NotepadCatalog;
import com.tx.local.notepad.service.NotepadCatalogService;

import io.swagger.annotations.Api;

/**
 * 记事本分类API控制层[NotepadCatalogAPIController]<br/>
 * 
 * @author []
 * @version [版本号]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@RestController
@Api(tags = "记事本分类API")
@RequestMapping("/api/notepadCatalog")
public class NotepadCatalogAPIController implements NotepadCatalogFacade {
    
    //记事本分类业务层
    @Resource(name = "notepadCatalogService")
    private NotepadCatalogService notepadCatalogService;
    
    /**
     * 新增记事本分类<br/>
     * <功能详细描述>
     * @param notepadCatalog [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public NotepadCatalog insert(@RequestBody NotepadCatalog notepadCatalog) {
        this.notepadCatalogService.insert(notepadCatalog);
        return notepadCatalog;
    }
    
    /**
     * 根据id删除记事本分类<br/> 
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
        boolean flag = this.notepadCatalogService.deleteById(id);
        return flag;
    }
    
    /**
     * 更新记事本分类<br/>
     * <功能详细描述>
     * @param notepadCatalog
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public boolean updateById(
            @PathVariable(value = "id", required = true) String id,
            @RequestBody NotepadCatalog notepadCatalog) {
        boolean flag = this.notepadCatalogService.updateById(id,
                notepadCatalog);
        return flag;
    }
    
    /**
     * 根据主键查询记事本分类<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return NotepadCatalog [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public NotepadCatalog findById(
            @PathVariable(value = "id", required = true) String id) {
        NotepadCatalog res = this.notepadCatalogService.findById(id);
        
        return res;
    }
    
    /**
     * 查询记事本分类实例列表<br/>
     * <功能详细描述>
     * @param querier
     * @return [参数说明]
     * 
     * @return List<NotepadCatalog> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public List<NotepadCatalog> queryList(@RequestBody Querier querier) {
        List<NotepadCatalog> resList = this.notepadCatalogService
                .queryList(querier);
        
        return resList;
    }
    
    /**
     * 查询记事本分类分页列表<br/>
     * <功能详细描述>
     * @param pageIndex
     * @param pageSize
     * @param querier
     * @return [参数说明]
     * 
     * @return PagedList<NotepadCatalog> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public PagedList<NotepadCatalog> queryPagedList(
            @RequestBody Querier querier,
            @PathVariable(value = "pageNumber", required = true) int pageIndex,
            @PathVariable(value = "pageSize", required = true) int pageSize) {
        PagedList<NotepadCatalog> resPagedList = this.notepadCatalogService
                .queryPagedList(querier, pageIndex, pageSize);
        return resPagedList;
    }
    
    /**
     * 查询记事本分类数量<br/>
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
        int count = this.notepadCatalogService.count(querier);
        
        return count;
    }
    
    /**
     * 查询记事本分类是否存在<br/>
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
        boolean flag = this.notepadCatalogService.exists(querier, excludeId);
        
        return flag;
    }
    
    /**
     * 根据条件查询基础数据分页列表<br/>
     * <功能详细描述>
     * @param parentId
     * @param querier
     * @return [参数说明]
     * 
     * @return PagedList<T> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public List<NotepadCatalog> queryChildrenByParentId(
            @PathVariable(value = "parentId", required = true) String parentId,
            Querier querier) {
        List<NotepadCatalog> resList = this.notepadCatalogService
                .queryChildrenByParentId(parentId, querier);
        
        return resList;
    }
    
    /**
     * 根据条件查询基础数据分页列表<br/>
     * <功能详细描述>
     * @param parentId
     * @param querier
     * @return [参数说明]
     * 
     * @return PagedList<T> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public List<NotepadCatalog> queryDescendantsByParentId(
            @PathVariable(value = "parentId", required = true) String parentId,
            Querier querier) {
        List<NotepadCatalog> resList = this.notepadCatalogService
                .queryDescendantsByParentId(parentId, querier);
        
        return resList;
    }
}