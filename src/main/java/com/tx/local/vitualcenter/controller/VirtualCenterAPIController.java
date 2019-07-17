/*
 * 描       述:  <描述>
 * 修  改 人:  
 * 修改时间:
 * <修改描述:>
 */
package com.tx.local.vitualcenter.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tx.core.paged.model.PagedList;
import com.tx.core.querier.model.Querier;
import com.tx.local.vitualcenter.facade.VirtualCenterFacade;
import com.tx.local.vitualcenter.model.VirtualCenter;
import com.tx.local.vitualcenter.service.VirtualCenterService;

import io.swagger.annotations.Api;

/**
 * VirtualCenterAPI控制层[VirtualCenterAPIController]<br/>
 * 
 * @author []
 * @version [版本号]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@RestController
@Api(tags = "VirtualCenterAPI")
@RequestMapping("/api/virtualCenter")
public class VirtualCenterAPIController implements VirtualCenterFacade {
    
    //VirtualCenter业务层
    @Resource(name = "virtualCenterService")
    private VirtualCenterService virtualCenterService;
    
    /**
     * 新增VirtualCenter<br/>
     * <功能详细描述>
     * @param virtualCenter [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public VirtualCenter insert(@RequestBody VirtualCenter virtualCenter) {
        this.virtualCenterService.insert(virtualCenter);
        return virtualCenter;
    }
    
    /**
     * 根据id删除VirtualCenter<br/> 
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
        boolean flag = this.virtualCenterService.deleteById(id);
        return flag;
    }
    
    /**
     * 根据code删除VirtualCenter<br/> 
     * <功能详细描述>
     * @param code
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public boolean deleteByCode(
            @PathVariable(value = "code", required = true) String code) {
        boolean flag = this.virtualCenterService.deleteByCode(code);
        return flag;
    }
    
    /**
     * 更新VirtualCenter<br/>
     * <功能详细描述>
     * @param virtualCenter
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public boolean updateById(
            @PathVariable(value = "id", required = true) String id,
            @RequestBody VirtualCenter virtualCenter) {
        boolean flag = this.virtualCenterService.updateById(id, virtualCenter);
        return flag;
    }
    
    /**
     * 禁用VirtualCenter<br/>
     * @param id
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public boolean disableById(
            @PathVariable(value = "id", required = true) String id) {
        boolean flag = this.virtualCenterService.disableById(id);
        return flag;
    }
    
    /**
     * 启用VirtualCenter<br/>
     * <功能详细描述>
     * @param id
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public boolean enableById(
            @PathVariable(value = "id", required = true) String id) {
        boolean flag = this.virtualCenterService.enableById(id);
        return flag;
    }
    
    /**
     * 根据主键查询VirtualCenter<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return VirtualCenter [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public VirtualCenter findById(
            @PathVariable(value = "id", required = true) String id) {
        VirtualCenter res = this.virtualCenterService.findById(id);
        
        return res;
    }
    
    /**
     * 根据编码查询VirtualCenter<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return VirtualCenter [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public VirtualCenter findByCode(
            @PathVariable(value = "code", required = true) String code) {
        VirtualCenter res = this.virtualCenterService.findByCode(code);
        
        return res;
    }
    
    /**
     * 查询VirtualCenter实例列表<br/>
     * <功能详细描述>
     * @param valid
     * @param querier
     * @return [参数说明]
     * 
     * @return List<VirtualCenter> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public List<VirtualCenter> queryList(
            @RequestParam(value = "valid", required = false) Boolean valid,
            @RequestBody Querier querier) {
        List<VirtualCenter> resList = this.virtualCenterService.queryList(valid,
                querier);
        
        return resList;
    }
    
    /**
     * 查询VirtualCenter分页列表<br/>
     * <功能详细描述>
     * @param valid
     * @param pageIndex
     * @param pageSize
     * @param querier
     * @return [参数说明]
     * 
     * @return PagedList<VirtualCenter> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public PagedList<VirtualCenter> queryPagedList(
            @RequestParam(value = "valid", required = false) Boolean valid,
            @RequestBody Querier querier,
            @PathVariable(value = "pageNumber", required = true) int pageIndex,
            @PathVariable(value = "pageSize", required = true) int pageSize) {
        PagedList<VirtualCenter> resPagedList = this.virtualCenterService
                .queryPagedList(valid, querier, pageIndex, pageSize);
        return resPagedList;
    }
    
    /**
     * 查询VirtualCenter数量<br/>
     * <功能详细描述>
     * @param valid
     * @param querier
     * @return [参数说明]
     * 
     * @return int [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public int count(
            @RequestParam(value = "valid", required = false) Boolean valid,
            @RequestBody Querier querier) {
        int count = this.virtualCenterService.count(valid, querier);
        
        return count;
    }
    
    /**
     * 查询VirtualCenter是否存在<br/>
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
        boolean flag = this.virtualCenterService.exists(querier, excludeId);
        
        return flag;
    }
    
    /**
     * 根据条件查询基础数据分页列表<br/>
     * <功能详细描述>
     * @param parentId
     * @param valid
     * @param querier
     * @return [参数说明]
     * 
     * @return PagedList<T> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public List<VirtualCenter> queryChildrenByParentId(
            @PathVariable(value = "parentId", required = true) String parentId,
            @RequestParam(value = "valid", required = false) Boolean valid,
            Querier querier) {
        List<VirtualCenter> resList = this.virtualCenterService
                .queryChildrenByParentId(parentId, valid, querier);
        
        return resList;
    }
    
    /**
     * 根据条件查询基础数据分页列表<br/>
     * <功能详细描述>
     * @param parentId
     * @param valid
     * @param querier
     * @return [参数说明]
     * 
     * @return PagedList<T> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public List<VirtualCenter> queryDescendantsByParentId(
            @PathVariable(value = "parentId", required = true) String parentId,
            @RequestParam(value = "valid", required = false) Boolean valid,
            Querier querier) {
        List<VirtualCenter> resList = this.virtualCenterService
                .queryDescendantsByParentId(parentId, valid, querier);
        
        return resList;
    }
}