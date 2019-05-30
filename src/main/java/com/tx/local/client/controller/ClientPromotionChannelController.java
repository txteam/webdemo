/*
 * 描       述:  <描述>
 * 修  改 人:  
 * 修改时间:
 * <修改描述:>
 */
package com.tx.local.client.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tx.local.client.model.ClientPromotionChannel;
import com.tx.local.client.service.ClientPromotionChannelService;
import com.tx.core.paged.model.PagedList;


/**
 * 客户推广渠道控制层<br/>
 * 
 * @author []
 * @version [版本号]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Controller
@RequestMapping("/clientPromotionChannel")
public class ClientPromotionChannelController {
    
    //客户推广渠道业务层
    @Resource(name = "clientPromotionChannelService")
    private ClientPromotionChannelService clientPromotionChannelService;
    
    /**
     * 跳转到查询客户推广渠道列表页面<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/toQueryList")
    public String toQueryList(ModelMap response) {

        return "/client/queryClientPromotionChannelList";
    }
    
    
    /**
     * 跳转到新增客户推广渠道页面<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/toAdd")
    public String toAdd(ModelMap response) {
    	response.put("clientPromotionChannel", new ClientPromotionChannel());
    	

        return "/client/addClientPromotionChannel";
    }
    
    /**
     * 跳转到编辑客户推广渠道页面
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/toUpdate")
    public String toUpdate(
    		@RequestParam("clientPromotionChannelId") String clientPromotionChannelId,
            ModelMap response) {
        ClientPromotionChannel clientPromotionChannel = this.clientPromotionChannelService.findById(clientPromotionChannelId); 
        response.put("clientPromotionChannel", clientPromotionChannel);

        
        return "/client/updateClientPromotionChannel";
    }

    /**
     * 查询客户推广渠道实例列表<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<ClientPromotionChannel> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/queryList")
    public List<ClientPromotionChannel> queryList(
			@RequestParam(value="valid",required=false) Boolean valid,
    		@RequestParam MultiValueMap<String, String> request
    	) {
        Map<String,Object> params = new HashMap<>();
        //params.put("",request.getFirst(""));
    	
        List<ClientPromotionChannel> resList = this.clientPromotionChannelService.queryList(
			valid,
			params         
        );
  
        return resList;
    }
    
    /**
     * 查询客户推广渠道实例分页列表<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<ClientPromotionChannel> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/queryPagedList")
    public PagedList<ClientPromotionChannel> queryPagedList(
			@RequestParam(value="valid",required=false) Boolean valid,
			@RequestParam(value = "pageNumber", required = false, defaultValue = "1") int pageIndex,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
            @RequestParam MultiValueMap<String, String> request
    	) {
		Map<String,Object> params = new HashMap<>();
		//params.put("",request.getFirst(""));

        PagedList<ClientPromotionChannel> resPagedList = this.clientPromotionChannelService.queryPagedList(
			valid,
			params,
			pageIndex,
			pageSize
        );
        return resPagedList;
    }
    
    /**
     * 新增客户推广渠道实例
     * <功能详细描述>
     * @param clientPromotionChannel [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/add")
    public boolean add(ClientPromotionChannel clientPromotionChannel) {
        this.clientPromotionChannelService.insert(clientPromotionChannel);
        return true;
    }
    
    /**
     * 更新客户推广渠道实例<br/>
     * <功能详细描述>
     * @param clientPromotionChannel
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/update")
    public boolean update(ClientPromotionChannel clientPromotionChannel) {
        boolean flag = this.clientPromotionChannelService.updateById(clientPromotionChannel);
        return flag;
    }
    
    /**
     * 删除客户推广渠道实例<br/> 
     * <功能详细描述>
     * @param clientPromotionChannelId
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/deleteById")
    public boolean deleteById(@RequestParam(value = "clientPromotionChannelId") String clientPromotionChannelId) {
        boolean flag = this.clientPromotionChannelService.deleteById(clientPromotionChannelId);
        return flag;
    }
    
    /**
     * 禁用客户推广渠道实例
     * @param clientPromotionChannelId
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/disableById")
    public boolean disableById(@RequestParam(value = "clientPromotionChannelId") String clientPromotionChannelId) {
        boolean flag = this.clientPromotionChannelService.disableById(clientPromotionChannelId);
        return flag;
    }
    
    /**
     * 启用客户推广渠道实例<br/>
     * <功能详细描述>
     * @param clientPromotionChannelId
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/enableById")
    public boolean enableById(@RequestParam(value = "clientPromotionChannelId") String clientPromotionChannelId) {
        boolean flag = this.clientPromotionChannelService.enableById(clientPromotionChannelId);
        return flag;
    }

	/**
     * 校验参数对应实例是否重复
	 * @param excludeId
     * @param params
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/check/{excludeId}")
    public Map<String, String> check(
            @PathVariable(value = "excludeId", required = false) String excludeId,
            @RequestParam Map<String, String> params) {
        boolean flag = this.clientPromotionChannelService.exists(params, excludeId);
        
        Map<String, String> resMap = new HashMap<String, String>();
        if (!flag) {
            resMap.put("ok", "");
        } else {
            resMap.put("error", "重复值");
        }
        return resMap;
    }
}