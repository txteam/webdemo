/*
 * 描       述:  <描述>
 * 修  改 人:  
 * 修改时间:
 * <修改描述:>
 */
package com.tx.local.operator.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tx.core.paged.model.PagedList;
import com.tx.core.querier.model.Querier;
import com.tx.local.operator.facade.OperatorRoleFacade;
import com.tx.local.operator.model.OperatorRole;
import com.tx.local.operator.service.OperatorRoleService;

import io.swagger.annotations.Api;

/**
 * 角色API控制层[OperatorRoleAPIController]<br/>
 * 
 * @author []
 * @version [版本号]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@RestController
@Api(tags = "角色API")
@RequestMapping("/api/operatorRole")
public class OperatorRoleAPIController implements OperatorRoleFacade {
    
    //角色业务层
    @Resource(name = "operatorRoleService")
    private OperatorRoleService operatorRoleService;
    
    /**
     * 新增角色<br/>
     * <功能详细描述>
     * @param operatorRole [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public OperatorRole insert(@RequestBody OperatorRole operatorRole) {
        this.operatorRoleService.insert(operatorRole);
        return operatorRole;
    }
    
    /**
     * 根据id删除角色<br/> 
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
        boolean flag = this.operatorRoleService.deleteById(id);
        return flag;
    }
    
    /**
     * 更新角色<br/>
     * <功能详细描述>
     * @param operatorRole
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public boolean updateById(
            @PathVariable(value = "id", required = true) String id,
            @RequestBody OperatorRole operatorRole) {
        boolean flag = this.operatorRoleService.updateById(id, operatorRole);
        return flag;
    }
    
    /**
     * 禁用角色<br/>
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
        boolean flag = this.operatorRoleService.disableById(id);
        return flag;
    }
    
    /**
     * 启用角色<br/>
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
        boolean flag = this.operatorRoleService.enableById(id);
        return flag;
    }
    
    /**
     * 根据主键查询角色<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return OperatorRole [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public OperatorRole findById(
            @PathVariable(value = "id", required = true) String id) {
        OperatorRole res = this.operatorRoleService.findById(id);
        
        return res;
    }
    
    /**
     * 查询角色实例列表<br/>
     * <功能详细描述>
     * @param valid
     * @param querier
     * @return [参数说明]
     * 
     * @return List<OperatorRole> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public List<OperatorRole> queryList(
            @RequestParam(value = "valid", required = false) Boolean valid,
            @RequestBody Querier querier) {
        List<OperatorRole> resList = this.operatorRoleService.queryList(valid,
                querier);
        
        return resList;
    }
    
    /**
     * 查询角色分页列表<br/>
     * <功能详细描述>
     * @param valid
     * @param pageIndex
     * @param pageSize
     * @param querier
     * @return [参数说明]
     * 
     * @return PagedList<OperatorRole> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public PagedList<OperatorRole> queryPagedList(
            @RequestParam(value = "valid", required = false) Boolean valid,
            @RequestBody Querier querier,
            @PathVariable(value = "pageNumber", required = true) int pageIndex,
            @PathVariable(value = "pageSize", required = true) int pageSize) {
        PagedList<OperatorRole> resPagedList = this.operatorRoleService
                .queryPagedList(valid, querier, pageIndex, pageSize);
        return resPagedList;
    }
    
    /**
     * 查询角色数量<br/>
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
        int count = this.operatorRoleService.count(valid, querier);
        
        return count;
    }
    
    /**
     * 查询角色是否存在<br/>
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
        boolean flag = this.operatorRoleService.exists(querier, excludeId);
        
        return flag;
    }
    
    /**
     * 根据条件查询查询职位子代列表<br/>
     * @param parentId
     * @param valid
     * @param querier
     * @return
     */
    @Override
    public List<OperatorRole> queryChildrenByParentId(String parentId,
            Boolean valid, Querier querier) {
        List<OperatorRole> resList = this.operatorRoleService
                .queryChildrenByParentId(parentId, valid, querier);
        
        return resList;
    }
    
    /**
     * 根据条件查询查询职位后代列表<br/>
     * @param parentId
     * @param valid
     * @param querier
     * @return
     */
    @Override
    public List<OperatorRole> queryDescendantsByParentId(String parentId,
            Boolean valid, Querier querier) {
        List<OperatorRole> resList = this.operatorRoleService
                .queryDescendantsByParentId(parentId, valid, querier);
        
        return resList;
    }
    
}