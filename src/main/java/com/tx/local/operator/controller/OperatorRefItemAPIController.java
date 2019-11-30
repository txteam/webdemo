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
import com.tx.local.operator.facade.OperatorRefItemFacade;
import com.tx.local.operator.model.OperatorRefItem;
import com.tx.local.operator.service.OperatorRefItemService;

import io.swagger.annotations.Api;

/**
 * OperatorRefItemAPI控制层[OperatorRefItemAPIController]<br/>
 * 
 * @author []
 * @version [版本号]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@RestController
@Api(tags = "用户引用API")
@RequestMapping("/api/operatorRefItem")
public class OperatorRefItemAPIController implements OperatorRefItemFacade {
    
    //OperatorRefItem业务层
    @Resource(name = "operatorRefItemService")
    private OperatorRefItemService operatorRefItemService;
    
    /**
     * 新增OperatorRefItem<br/>
     * <功能详细描述>
     * @param operatorRefItem [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public OperatorRefItem insert(
            @RequestBody OperatorRefItem operatorRefItem) {
        this.operatorRefItemService.insert(operatorRefItem);
        return operatorRefItem;
    }
    
    /**
     * 根据id删除OperatorRefItem<br/> 
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
        boolean flag = this.operatorRefItemService.deleteById(id);
        return flag;
    }
    
    /**
     * 更新OperatorRefItem<br/>
     * <功能详细描述>
     * @param operatorRefItem
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public boolean updateById(
            @PathVariable(value = "id", required = true) String id,
            @RequestBody OperatorRefItem operatorRefItem) {
        boolean flag = this.operatorRefItemService.updateById(id,
                operatorRefItem);
        return flag;
    }
    
    /**
     * 根据主键查询OperatorRefItem<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return OperatorRefItem [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public OperatorRefItem findById(
            @PathVariable(value = "id", required = true) String id) {
        OperatorRefItem res = this.operatorRefItemService.findById(id);
        
        return res;
    }
    
    /**
     * 查询OperatorRefItem实例列表<br/>
     * <功能详细描述>
     * @param querier
     * @return [参数说明]
     * 
     * @return List<OperatorRefItem> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public List<OperatorRefItem> queryList(@RequestBody Querier querier) {
        List<OperatorRefItem> resList = this.operatorRefItemService
                .queryList(querier);
        
        return resList;
    }
    
    /**
     * 查询OperatorRefItem分页列表<br/>
     * <功能详细描述>
     * @param pageIndex
     * @param pageSize
     * @param querier
     * @return [参数说明]
     * 
     * @return PagedList<OperatorRefItem> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public PagedList<OperatorRefItem> queryPagedList(
            @RequestBody Querier querier,
            @PathVariable(value = "pageNumber", required = true) int pageIndex,
            @PathVariable(value = "pageSize", required = true) int pageSize) {
        PagedList<OperatorRefItem> resPagedList = this.operatorRefItemService
                .queryPagedList(querier, pageIndex, pageSize);
        return resPagedList;
    }
    
    /**
     * 查询OperatorRefItem数量<br/>
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
        int count = this.operatorRefItemService.count(querier);
        
        return count;
    }
    
    /**
     * 查询OperatorRefItem是否存在<br/>
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
        boolean flag = this.operatorRefItemService.exists(querier, excludeId);
        
        return flag;
    }
}