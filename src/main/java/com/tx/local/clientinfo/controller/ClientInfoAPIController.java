/*
 * 描       述:  <描述>
 * 修  改 人:  
 * 修改时间:
 * <修改描述:>
 */
package com.tx.local.clientinfo.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tx.core.paged.model.PagedList;
import com.tx.core.querier.model.Querier;
import com.tx.local.basicdata.model.IDCardTypeEnum;
import com.tx.local.clientinfo.facade.ClientInfoFacade;
import com.tx.local.clientinfo.model.ClientInfo;
import com.tx.local.clientinfo.service.ClientInfoService;

import io.swagger.annotations.Api;

/**
 * ClientInfoAPI控制层[ClientInfoAPIController]<br/>
 * 
 * @author []
 * @version [版本号]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@RestController
@Api(tags = "ClientInfoAPI")
@RequestMapping("/api/clientInfo")
public class ClientInfoAPIController implements ClientInfoFacade {
    
    //ClientInfo业务层
    @Resource(name = "clientInfoService")
    private ClientInfoService clientInfoService;
    
    /**
     * 新增ClientInfo<br/>
     * <功能详细描述>
     * @param clientInfo [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public ClientInfo insert(@RequestBody ClientInfo clientInfo) {
        this.clientInfoService.insert(clientInfo);
        return clientInfo;
    }
    
    /**
     * 根据id删除ClientInfo<br/> 
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
        boolean flag = this.clientInfoService.deleteById(id);
        return flag;
    }
    
    /**
     * 更新ClientInfo<br/>
     * <功能详细描述>
     * @param clientInfo
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public boolean updateById(
            @PathVariable(value = "id", required = true) String id,
            @RequestBody ClientInfo clientInfo) {
        boolean flag = this.clientInfoService.updateById(id, clientInfo);
        return flag;
    }
    
    /**
     * @param id
     * @param creditInfoId
     * @param idCardType
     * @param idCardNumber
     * @return
     */
    @Override
    public boolean updateCreditInfo(
            @PathVariable(value = "id", required = true) String id,
            @PathVariable(value = "creditInfoId", required = true) String creditInfoId,
            @RequestParam(value = "idCardType", required = false) IDCardTypeEnum idCardType,
            @RequestParam(value = "idCardNumber", required = false) String idCardNumber) {
        boolean flag = this.clientInfoService
                .creditInfoBinding(id, creditInfoId, idCardType, idCardNumber);
        return flag;
    }
    
    /**
     * 禁用ClientInfo<br/>
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
        boolean flag = this.clientInfoService.disableById(id);
        return flag;
    }
    
    /**
     * 启用ClientInfo<br/>
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
        boolean flag = this.clientInfoService.enableById(id);
        return flag;
    }
    
    /**
     * 根据主键查询ClientInfo<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return ClientInfo [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public ClientInfo findById(
            @PathVariable(value = "id", required = true) String id) {
        ClientInfo res = this.clientInfoService.findById(id);
        
        return res;
    }
    
    /**
     * @param username
     * @return
     */
    @Override
    public ClientInfo findByUsername(String username) {
        ClientInfo res = this.clientInfoService.findByUsername(username);
        
        return res;
    }
    
    /**
     * 查询ClientInfo实例列表<br/>
     * <功能详细描述>
     * @param valid
     * @param querier
     * @return [参数说明]
     * 
     * @return List<ClientInfo> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public List<ClientInfo> queryList(
            @RequestParam(value = "valid", required = false) Boolean valid,
            @RequestBody Querier querier) {
        List<ClientInfo> resList = this.clientInfoService.queryList(valid,
                querier);
        
        return resList;
    }
    
    /**
     * 查询ClientInfo分页列表<br/>
     * <功能详细描述>
     * @param valid
     * @param pageIndex
     * @param pageSize
     * @param querier
     * @return [参数说明]
     * 
     * @return PagedList<ClientInfo> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public PagedList<ClientInfo> queryPagedList(
            @RequestParam(value = "valid", required = false) Boolean valid,
            @RequestBody Querier querier,
            @PathVariable(value = "pageNumber", required = true) int pageIndex,
            @PathVariable(value = "pageSize", required = true) int pageSize) {
        PagedList<ClientInfo> resPagedList = this.clientInfoService
                .queryPagedList(valid, querier, pageIndex, pageSize);
        return resPagedList;
    }
    
    /**
     * 查询ClientInfo数量<br/>
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
        int count = this.clientInfoService.count(valid, querier);
        
        return count;
    }
    
    /**
     * 查询ClientInfo是否存在<br/>
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
        boolean flag = this.clientInfoService.exists(querier, excludeId);
        
        return flag;
    }
}