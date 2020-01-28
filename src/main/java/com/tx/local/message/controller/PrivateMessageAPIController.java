/*
 * 描       述:  <描述>
 * 修  改 人:  
 * 修改时间:
 * <修改描述:>
 */
package com.tx.local.message.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tx.core.paged.model.PagedList;
import com.tx.core.querier.model.Querier;
import com.tx.local.message.facade.PrivateMessageFacade;
import com.tx.local.message.model.PrivateMessage;
import com.tx.local.message.service.PrivateMessageService;

import io.swagger.annotations.Api;

/**
 * 私信API控制层[PrivateMessageAPIController]<br/>
 * 
 * @author []
 * @version [版本号]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@RestController
@Api(tags = "私信API")
@RequestMapping("/api/privateMessage")
public class PrivateMessageAPIController implements PrivateMessageFacade {
    
    //私信业务层
    @Resource(name = "privateMessageService")
    private PrivateMessageService privateMessageService;
    
    /**
     * 新增私信<br/>
     * <功能详细描述>
     * @param privateMessage [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public PrivateMessage insert(@RequestBody PrivateMessage privateMessage) {
        this.privateMessageService.insert(privateMessage);
        return privateMessage;
    }
    
    /**
     * 根据id删除私信<br/> 
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
        boolean flag = this.privateMessageService.deleteById(id);
        return flag;
    }
    
    /**
     * 更新私信<br/>
     * <功能详细描述>
     * @param privateMessage
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public boolean updateById(
            @PathVariable(value = "id", required = true) String id,
            @RequestBody PrivateMessage privateMessage) {
        boolean flag = this.privateMessageService.updateById(id,
                privateMessage);
        return flag;
    }
    
    /**
     * 根据主键查询私信<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return PrivateMessage [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public PrivateMessage findById(
            @PathVariable(value = "id", required = true) String id) {
        PrivateMessage res = this.privateMessageService.findById(id);
        
        return res;
    }
    
    /**
     * 查询私信实例列表<br/>
     * <功能详细描述>
     * @param querier
     * @return [参数说明]
     * 
     * @return List<PrivateMessage> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public List<PrivateMessage> queryList(@RequestBody Querier querier) {
        List<PrivateMessage> resList = this.privateMessageService
                .queryList(querier);
        
        return resList;
    }
    
    /**
     * 查询私信分页列表<br/>
     * <功能详细描述>
     * @param pageIndex
     * @param pageSize
     * @param querier
     * @return [参数说明]
     * 
     * @return PagedList<PrivateMessage> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public PagedList<PrivateMessage> queryPagedList(
            @RequestBody Querier querier,
            @PathVariable(value = "pageNumber", required = true) int pageIndex,
            @PathVariable(value = "pageSize", required = true) int pageSize) {
        PagedList<PrivateMessage> resPagedList = this.privateMessageService
                .queryPagedList(querier, pageIndex, pageSize);
        return resPagedList;
    }
    
    /**
     * 查询私信数量<br/>
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
        int count = this.privateMessageService.count(querier);
        
        return count;
    }
    
    /**
     * 查询私信是否存在<br/>
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
        boolean flag = this.privateMessageService.exists(querier, excludeId);
        
        return flag;
    }
}