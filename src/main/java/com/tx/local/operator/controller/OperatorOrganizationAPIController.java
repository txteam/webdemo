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
import com.tx.local.operator.facade.OperatorOrganizationFacade;
import com.tx.local.operator.model.OperatorOrganization;
import com.tx.local.operator.service.OperatorOrganizationService;

import io.swagger.annotations.Api;

/**
 * 组织API控制层[OrganizationAPIController]<br/>
 * 
 * @author []
 * @version [版本号]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@RestController
@Api(tags = "组织API")
@RequestMapping("/api/organization")
public class OperatorOrganizationAPIController implements OperatorOrganizationFacade {
    
    //组织业务层
    @Resource(name = "operatorOrganizationService")
    private OperatorOrganizationService organizationService;
    
    /**
     * 新增组织<br/>
     * <功能详细描述>
     * @param organization [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public OperatorOrganization insert(@RequestBody OperatorOrganization organization) {
        this.organizationService.insert(organization);
        return organization;
    }
    
    /**
     * 根据id删除组织<br/> 
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
        boolean flag = this.organizationService.deleteById(id);
        return flag;
    }
    
    /**
     * 根据code删除组织<br/> 
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
        boolean flag = this.organizationService.deleteByCode(code);
        return flag;
    }
    
    /**
     * 更新组织<br/>
     * <功能详细描述>
     * @param organization
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public boolean updateById(
            @PathVariable(value = "id", required = true) String id,
            @RequestBody OperatorOrganization organization) {
        boolean flag = this.organizationService.updateById(id, organization);
        return flag;
    }
    
    /**
     * 禁用组织<br/>
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
        boolean flag = this.organizationService.disableById(id);
        return flag;
    }
    
    /**
     * 启用组织<br/>
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
        boolean flag = this.organizationService.enableById(id);
        return flag;
    }
    
    /**
     * 根据主键查询组织<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return Organization [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public OperatorOrganization findById(
            @PathVariable(value = "id", required = true) String id) {
        OperatorOrganization res = this.organizationService.findById(id);
        
        return res;
    }
    
    /**
     * 根据编码查询组织<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return Organization [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public OperatorOrganization findByCode(
            @PathVariable(value = "code", required = true) String code) {
        OperatorOrganization res = this.organizationService.findByCode(code);
        
        return res;
    }
    
    /**
     * 查询组织实例列表<br/>
     * <功能详细描述>
     * @param valid
     * @param querier
     * @return [参数说明]
     * 
     * @return List<Organization> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public List<OperatorOrganization> queryList(
            @RequestParam(value = "valid", required = false) Boolean valid,
            @RequestBody Querier querier) {
        List<OperatorOrganization> resList = this.organizationService.queryList(valid,
                querier);
        
        return resList;
    }
    
    /**
     * 查询组织分页列表<br/>
     * <功能详细描述>
     * @param valid
     * @param pageIndex
     * @param pageSize
     * @param querier
     * @return [参数说明]
     * 
     * @return PagedList<Organization> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public PagedList<OperatorOrganization> queryPagedList(
            @RequestParam(value = "valid", required = false) Boolean valid,
            @RequestBody Querier querier,
            @PathVariable(value = "pageNumber", required = true) int pageIndex,
            @PathVariable(value = "pageSize", required = true) int pageSize) {
        PagedList<OperatorOrganization> resPagedList = this.organizationService
                .queryPagedList(valid, querier, pageIndex, pageSize);
        return resPagedList;
    }
    
    /**
     * 查询组织数量<br/>
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
        int count = this.organizationService.count(valid, querier);
        
        return count;
    }
    
    /**
     * 查询组织是否存在<br/>
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
        boolean flag = this.organizationService.exists(querier, excludeId);
        
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
    public List<OperatorOrganization> queryChildrenByParentId(
            @PathVariable(value = "parentId", required = true) String parentId,
            @RequestParam(value = "valid", required = false) Boolean valid,
            Querier querier) {
        List<OperatorOrganization> resList = this.organizationService
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
    public List<OperatorOrganization> queryDescendantsByParentId(
            @PathVariable(value = "parentId", required = true) String parentId,
            @RequestParam(value = "valid", required = false) Boolean valid,
            Querier querier) {
        List<OperatorOrganization> resList = this.organizationService
                .queryDescendantsByParentId(parentId, valid, querier);
        
        return resList;
    }
}