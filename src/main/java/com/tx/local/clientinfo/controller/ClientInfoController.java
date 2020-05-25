/*
 * 描       述:  <描述>
 * 修  改 人:  
 * 修改时间:
 * <修改描述:>
 */
package com.tx.local.clientinfo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tx.core.paged.model.PagedList;
import com.tx.local.basicdata.model.IDCardTypeEnum;
import com.tx.local.clientinfo.model.ClientInfo;
import com.tx.local.clientinfo.model.ClientStatusEnum;
import com.tx.local.clientinfo.model.ClientTypeEnum;
import com.tx.local.clientinfo.service.ClientInfoService;

/**
 * ClientInfo控制层<br/>
 * 
 * @author []
 * @version [版本号]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Controller
@RequestMapping("/clientInfo")
public class ClientInfoController {
    
    //ClientInfo业务层
    @Resource(name = "clientInfoService")
    private ClientInfoService clientInfoService;
    
    /**
     * 跳转到查询ClientInfo分页列表页面<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/toQueryPagedList")
    public String toQueryPagedList(ModelMap response) {
        response.put("idCardTypes", IDCardTypeEnum.values());
        response.put("types", ClientTypeEnum.values());
        response.put("statuss", ClientStatusEnum.values());
        
        return "clientinfo/queryClientInfoPagedList";
    }
    
    /**
     * 跳转到新增ClientInfo页面<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/toAdd")
    public String toAdd(ModelMap response) {
        response.put("clientInfo", new ClientInfo());
        
        response.put("idCardTypes", IDCardTypeEnum.values());
        response.put("types", ClientTypeEnum.values());
        response.put("statuss", ClientStatusEnum.values());
        
        return "clientinfo/addClientInfo";
    }
    
    /**
     * 跳转到编辑ClientInfo页面
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/toUpdate")
    public String toUpdate(@RequestParam("id") String id, ModelMap response) {
        ClientInfo clientInfo = this.clientInfoService.findById(id);
        response.put("clientInfo", clientInfo);
        
        response.put("idCardTypes", IDCardTypeEnum.values());
        response.put("types", ClientTypeEnum.values());
        response.put("statuss", ClientStatusEnum.values());
        
        return "clientinfo/updateClientInfo";
    }
    
    /**
     * 跳转到编辑客户信息主页面<br/>
     *   可编辑用户信息
     *   可在其中编辑雇员信息
     *   可在其中编辑安全账户信息
     *   可在其中实现绑定第三方账号以及解绑功能
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/toModifyMain")
    public String toModifyMain(@RequestParam("id") String id,
            ModelMap response) {
        ClientInfo clientInfo = this.clientInfoService.findById(id);
        response.put("clientInfo", clientInfo);
        
        response.put("idCardTypes", IDCardTypeEnum.values());
        response.put("types", ClientTypeEnum.values());
        response.put("statuses", ClientStatusEnum.values());
        
        return "clientinfo/modifyClientMain";
    }
    
    /**
     * 查询ClientInfo实例列表<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<ClientInfo> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/queryList")
    public List<ClientInfo> queryList(
            @RequestParam(value = "valid", required = false) Boolean valid,
            @RequestParam MultiValueMap<String, String> request) {
        Map<String, Object> params = new HashMap<>();
        params.put("name", request.getFirst("name"));
        
        List<ClientInfo> resList = this.clientInfoService.queryList(valid,
                params);
        
        return resList;
    }
    
    /**
     * 查询ClientInfo实例分页列表<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<ClientInfo> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/queryPagedList")
    public PagedList<ClientInfo> queryPagedList(
            @RequestParam(value = "valid", required = false) Boolean valid,
            @RequestParam(value = "pageNumber", required = false, defaultValue = "1") int pageIndex,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
            @RequestParam MultiValueMap<String, String> request) {
        Map<String, Object> params = new HashMap<>();
        params.put("name", request.getFirst("name"));
        
        PagedList<ClientInfo> resPagedList = this.clientInfoService
                .queryPagedList(valid, params, pageIndex, pageSize);
        return resPagedList;
    }
    
    /**
     * 新增ClientInfo实例
     * <功能详细描述>
     * @param clientInfo [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/add")
    public boolean add(ClientInfo clientInfo) {
        this.clientInfoService.insert(clientInfo);
        return true;
    }
    
    /**
     * 更新ClientInfo实例<br/>
     * <功能详细描述>
     * @param clientInfo
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/update")
    public boolean update(ClientInfo clientInfo) {
        boolean flag = this.clientInfoService.updateById(clientInfo);
        return flag;
    }
    
    /**
     * 根据主键查询ClientInfo实例<br/> 
     * <功能详细描述>
     * @param id
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/findById")
    public ClientInfo findById(@RequestParam(value = "id") String id) {
        ClientInfo clientInfo = this.clientInfoService.findById(id);
        return clientInfo;
    }
    
    /**
     * 删除ClientInfo实例<br/> 
     * <功能详细描述>
     * @param id
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/deleteById")
    public boolean deleteById(@RequestParam(value = "id") String id) {
        boolean flag = this.clientInfoService.deleteById(id);
        return flag;
    }
    
    /**
     * 禁用ClientInfo实例
     * @param id
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/disableById")
    public boolean disableById(@RequestParam(value = "id") String id) {
        boolean flag = this.clientInfoService.disableById(id);
        return flag;
    }
    
    /**
     * 启用ClientInfo实例<br/>
     * <功能详细描述>
     * @param id
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/enableById")
    public boolean enableById(@RequestParam(value = "id") String id) {
        boolean flag = this.clientInfoService.enableById(id);
        return flag;
    }
    
    /**
     * 锁定操作人员实例
     * @param id
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/lockById")
    public boolean lockById(@RequestParam(value = "id") String id) {
        boolean flag = this.clientInfoService.lockById(id);
        return flag;
    }
    
    /**
     * 启用操作人员实例<br/>
     * <功能详细描述>
     * @param id
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/unlockById")
    public boolean unlockById(@RequestParam(value = "id") String id) {
        boolean flag = this.clientInfoService.unlockById(id);
        return flag;
    }
    
    /**
     * 校验是否重复<br/>
     * @param excludeId
     * @param params
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/validate")
    public Map<String, String> validate(
            @RequestParam(value = "id", required = false) String excludeId,
            @RequestParam Map<String, String> params) {
        params.remove("id");
        boolean flag = this.clientInfoService.exists(params, excludeId);
        
        Map<String, String> resMap = new HashMap<String, String>();
        if (!flag) {
            resMap.put("ok", "");
        } else {
            resMap.put("error", "重复值");
        }
        return resMap;
    }
    
}