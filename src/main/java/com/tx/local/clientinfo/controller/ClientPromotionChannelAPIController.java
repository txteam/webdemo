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
import com.tx.local.clientinfo.facade.ClientPromotionChannelFacade;
import com.tx.local.clientinfo.model.ClientPromotionChannel;
import com.tx.local.clientinfo.service.ClientPromotionChannelService;

import io.swagger.annotations.Api;

/**
 * 客户推广渠道API控制层[ClientPromotionChannelAPIController]<br/>
 * 
 * @author []
 * @version [版本号]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@RestController
@Api(tags = "客户推广渠道API")
@RequestMapping("/api/clientPromotionChannel")
public class ClientPromotionChannelAPIController implements ClientPromotionChannelFacade {
    
    //客户推广渠道业务层
    @Resource(name = "clientPromotionChannelService")
    private ClientPromotionChannelService clientPromotionChannelService;
    
    /**
     * 新增客户推广渠道<br/>
     * <功能详细描述>
     * @param clientPromotionChannel [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public ClientPromotionChannel insert(@RequestBody ClientPromotionChannel clientPromotionChannel) {
        this.clientPromotionChannelService.insert(clientPromotionChannel);
        return clientPromotionChannel;
    }
    
    /**
     * 根据id删除客户推广渠道<br/> 
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
    		@PathVariable(value = "id",required=true) String id) {
        boolean flag = this.clientPromotionChannelService.deleteById(id);
        return flag;
    }
	
	/**
     * 根据code删除客户推广渠道<br/> 
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
    		@PathVariable(value = "code",required=true) String code){
        boolean flag = this.clientPromotionChannelService.deleteByCode(code);
        return flag;    
    }
    
    /**
     * 更新客户推广渠道<br/>
     * <功能详细描述>
     * @param clientPromotionChannel
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public boolean updateById(@PathVariable(value = "id",required=true) String id,
    		@RequestBody ClientPromotionChannel clientPromotionChannel) {
        boolean flag = this.clientPromotionChannelService.updateById(id,clientPromotionChannel);
        return flag;
    }
    
    /**
     * 禁用客户推广渠道<br/>
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
        boolean flag = this.clientPromotionChannelService.disableById(id);
        return flag;
    }
    
    /**
     * 启用客户推广渠道<br/>
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
        boolean flag = this.clientPromotionChannelService.enableById(id);
        return flag;
    }

    /**
     * 根据主键查询客户推广渠道<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return ClientPromotionChannel [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public ClientPromotionChannel findById(
            @PathVariable(value = "id", required = true) String id) {
        ClientPromotionChannel res = this.clientPromotionChannelService.findById(id);
        
        return res;
    }

    /**
     * 根据编码查询客户推广渠道<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return ClientPromotionChannel [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public ClientPromotionChannel findByCode(
            @PathVariable(value = "code", required = true) String code) {
        ClientPromotionChannel res = this.clientPromotionChannelService.findByCode(code);
        
        return res;
    }

    /**
     * 查询客户推广渠道实例列表<br/>
     * <功能详细描述>
     * @param valid
     * @param querier
     * @return [参数说明]
     * 
     * @return List<ClientPromotionChannel> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public List<ClientPromotionChannel> queryList(
			@RequestParam(value = "valid", required = false) Boolean valid,
    		@RequestBody Querier querier
    	) {
        List<ClientPromotionChannel> resList = this.clientPromotionChannelService.queryList(
			valid,
			querier         
        );
  
        return resList;
    }
    
    /**
     * 查询客户推广渠道分页列表<br/>
     * <功能详细描述>
     * @param valid
     * @param pageIndex
     * @param pageSize
     * @param querier
     * @return [参数说明]
     * 
     * @return PagedList<ClientPromotionChannel> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public PagedList<ClientPromotionChannel> queryPagedList(
			@RequestParam(value = "valid", required = false) Boolean valid,
			@RequestBody Querier querier,
			@PathVariable(value = "pageNumber", required = true) int pageIndex,
            @PathVariable(value = "pageSize", required = true) int pageSize
    	) {
        PagedList<ClientPromotionChannel> resPagedList = this.clientPromotionChannelService.queryPagedList(
			valid,
			querier,
			pageIndex,
			pageSize
        );
        return resPagedList;
    }
    
	/**
     * 查询客户推广渠道数量<br/>
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
        int count = this.clientPromotionChannelService.count(
			valid,
        	querier);
        
        return count;
    }

	/**
     * 查询客户推广渠道是否存在<br/>
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
        boolean flag = this.clientPromotionChannelService.exists(querier, excludeId);
        
        return flag;
    }
}