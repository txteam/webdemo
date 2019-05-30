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

import com.tx.local.client.model.ClientSource;
import com.tx.local.client.service.ClientSourceService;
import com.tx.core.paged.model.PagedList;


/**
 * 客户来源控制层<br/>
 * 
 * @author []
 * @version [版本号]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Controller
@RequestMapping("/clientSource")
public class ClientSourceController {
    
    //客户来源业务层
    @Resource(name = "clientSourceService")
    private ClientSourceService clientSourceService;
    
    /**
     * 跳转到查询客户来源列表页面<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/toQueryList")
    public String toQueryList(ModelMap response) {

        return "/clientinfo/queryClientSourceList";
    }
    
    
    /**
     * 跳转到新增客户来源页面<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/toAdd")
    public String toAdd(ModelMap response) {
    	response.put("clientSource", new ClientSource());
    	

        return "/client/addClientSource";
    }
    
    /**
     * 跳转到编辑客户来源页面
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/toUpdate")
    public String toUpdate(
    		@RequestParam("clientSourceId") String clientSourceId,
            ModelMap response) {
        ClientSource clientSource = this.clientSourceService.findById(clientSourceId); 
        response.put("clientSource", clientSource);

        
        return "/client/updateClientSource";
    }

    /**
     * 查询客户来源实例列表<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<ClientSource> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/queryList")
    public List<ClientSource> queryList(
			@RequestParam(value="valid",required=false) Boolean valid,
    		@RequestParam MultiValueMap<String, String> request
    	) {
        Map<String,Object> params = new HashMap<>();
        //params.put("",request.getFirst(""));
    	
        List<ClientSource> resList = this.clientSourceService.queryList(
			valid,
			params         
        );
  
        return resList;
    }
    
    /**
     * 查询客户来源实例分页列表<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<ClientSource> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/queryPagedList")
    public PagedList<ClientSource> queryPagedList(
			@RequestParam(value="valid",required=false) Boolean valid,
			@RequestParam(value = "pageNumber", required = false, defaultValue = "1") int pageIndex,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
            @RequestParam MultiValueMap<String, String> request
    	) {
		Map<String,Object> params = new HashMap<>();
		//params.put("",request.getFirst(""));

        PagedList<ClientSource> resPagedList = this.clientSourceService.queryPagedList(
			valid,
			params,
			pageIndex,
			pageSize
        );
        return resPagedList;
    }
    
    /**
     * 新增客户来源实例
     * <功能详细描述>
     * @param clientSource [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/add")
    public boolean add(ClientSource clientSource) {
        this.clientSourceService.insert(clientSource);
        return true;
    }
    
    /**
     * 更新客户来源实例<br/>
     * <功能详细描述>
     * @param clientSource
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/update")
    public boolean update(ClientSource clientSource) {
        boolean flag = this.clientSourceService.updateById(clientSource);
        return flag;
    }
    
    /**
     * 删除客户来源实例<br/> 
     * <功能详细描述>
     * @param clientSourceId
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/deleteById")
    public boolean deleteById(@RequestParam(value = "clientSourceId") String clientSourceId) {
        boolean flag = this.clientSourceService.deleteById(clientSourceId);
        return flag;
    }
    
    /**
     * 禁用客户来源实例
     * @param clientSourceId
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/disableById")
    public boolean disableById(@RequestParam(value = "clientSourceId") String clientSourceId) {
        boolean flag = this.clientSourceService.disableById(clientSourceId);
        return flag;
    }
    
    /**
     * 启用客户来源实例<br/>
     * <功能详细描述>
     * @param clientSourceId
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/enableById")
    public boolean enableById(@RequestParam(value = "clientSourceId") String clientSourceId) {
        boolean flag = this.clientSourceService.enableById(clientSourceId);
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
        boolean flag = this.clientSourceService.exists(params, excludeId);
        
        Map<String, String> resMap = new HashMap<String, String>();
        if (!flag) {
            resMap.put("ok", "");
        } else {
            resMap.put("error", "重复值");
        }
        return resMap;
    }
}