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
import com.tx.local.operator.facade.OperatorFacade;
import com.tx.local.operator.model.Operator;
import com.tx.local.operator.service.OperatorService;

import io.swagger.annotations.Api;

/**
 * 操作人员API控制层[OperatorAPIController]<br/>
 * 
 * @author []
 * @version [版本号]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@RestController
@Api(tags = "操作人员API")
@RequestMapping("/api/operator")
public class OperatorAPIController implements OperatorFacade {
    
    //操作人员业务层
    @Resource(name = "operatorService")
    private OperatorService operatorService;
    
    /**
     * 新增操作人员<br/>
     * <功能详细描述>
     * @param operator [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public Operator insert(@RequestBody Operator operator) {
        this.operatorService.insert(operator);
        return operator;
    }
    
    /**
     * 根据id删除操作人员<br/> 
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
        boolean flag = this.operatorService.deleteById(id);
        return flag;
    }
    
    /**
     * 更新操作人员<br/>
     * <功能详细描述>
     * @param operator
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public boolean updateById(
            @PathVariable(value = "id", required = true) String id,
            @RequestBody Operator operator) {
        boolean flag = this.operatorService.updateById(id, operator);
        return flag;
    }
    
    /**
     * 禁用操作人员<br/>
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
        boolean flag = this.operatorService.disableById(id);
        return flag;
    }
    
    /**
     * 启用操作人员<br/>
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
        boolean flag = this.operatorService.enableById(id);
        return flag;
    }
    
    /**
     * 根据主键查询操作人员<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return Operator [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public Operator findById(
            @PathVariable(value = "id", required = true) String id) {
        Operator res = this.operatorService.findById(id);
        
        return res;
    }
    
    /**
     * @param username
     * @return
     */
    @Override
    public Operator findByUsername(String username) {
        Operator res = this.operatorService.findByUsername(username);
        
        return res;
    }
    
    /**
     * 查询操作人员实例列表<br/>
     * <功能详细描述>
     * @param valid
     * @param querier
     * @return [参数说明]
     * 
     * @return List<Operator> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public List<Operator> queryList(
            @RequestParam(value = "valid", required = false) Boolean valid,
            @RequestBody Querier querier) {
        List<Operator> resList = this.operatorService.queryList(valid, querier);
        
        return resList;
    }
    
    /**
     * 查询操作人员分页列表<br/>
     * <功能详细描述>
     * @param valid
     * @param pageIndex
     * @param pageSize
     * @param querier
     * @return [参数说明]
     * 
     * @return PagedList<Operator> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public PagedList<Operator> queryPagedList(
            @RequestParam(value = "valid", required = false) Boolean valid,
            @RequestBody Querier querier,
            @PathVariable(value = "pageNumber", required = true) int pageIndex,
            @PathVariable(value = "pageSize", required = true) int pageSize) {
        PagedList<Operator> resPagedList = this.operatorService
                .queryPagedList(valid, querier, pageIndex, pageSize);
        return resPagedList;
    }
    
    /**
     * 查询操作人员数量<br/>
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
        int count = this.operatorService.count(valid, querier);
        
        return count;
    }
    
    /**
     * 查询操作人员是否存在<br/>
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
        boolean flag = this.operatorService.exists(querier, excludeId);
        
        return flag;
    }
}