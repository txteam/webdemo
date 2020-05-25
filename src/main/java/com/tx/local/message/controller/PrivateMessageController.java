/*
 * 描       述:  <描述>
 * 修  改 人:  
 * 修改时间:
 * <修改描述:>
 */
package com.tx.local.message.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.tx.local.security.util.WebContextUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tx.core.paged.model.PagedList;
import com.tx.local.message.model.MessageUserTypeEnum;
import com.tx.local.message.model.PrivateMessage;
import com.tx.local.message.model.PrivateMessageTypeEnum;
import com.tx.local.message.service.PrivateMessageService;

/**
 * 私信控制层<br/>
 * 
 * @author []
 * @version [版本号]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Controller
@RequestMapping("/privateMessage")
public class PrivateMessageController {
    
    //私信业务层
    @Resource(name = "privateMessageService")
    private PrivateMessageService privateMessageService;
    
    /**
     * 跳转到查询私信分页列表页面<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/toQueryPagedList")
    public String toQueryPagedList(ModelMap response) {
        response.put("types", PrivateMessageTypeEnum.values());
        response.put("userTypes", MessageUserTypeEnum.values());
        response.put("senderUserTypes", MessageUserTypeEnum.values());
        
        return "message/queryPrivateMessagePagedList";
    }
    
    /**
     * 跳转到新增私信页面<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/toAdd")
    public String toAdd(ModelMap response) {
        response.put("privateMessage", new PrivateMessage());
        
        response.put("types", PrivateMessageTypeEnum.values());
        response.put("userTypes", MessageUserTypeEnum.values());
        response.put("senderUserTypes", MessageUserTypeEnum.values());
        
        return "message/addPrivateMessage";
    }
    
    /**
     * 跳转到编辑私信页面
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/toUpdate")
    public String toUpdate(@RequestParam("id") String id, ModelMap response) {
        PrivateMessage privateMessage = this.privateMessageService.findById(id);
        response.put("privateMessage", privateMessage);
        
        response.put("types", PrivateMessageTypeEnum.values());
        response.put("userTypes", MessageUserTypeEnum.values());
        response.put("senderUserTypes", MessageUserTypeEnum.values());
        
        return "message/updatePrivateMessage";
    }
    
    /**
     * 查询私信实例列表<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<PrivateMessage> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/queryList")
    public List<PrivateMessage> queryList(
            @RequestParam MultiValueMap<String, String> request) {
        Map<String, Object> params = new HashMap<>();
        params.put("name", request.getFirst("name"));
        
        List<PrivateMessage> resList = this.privateMessageService
                .queryList(params);
        
        return resList;
    }
    
    /**
     * 查询私信实例分页列表<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<PrivateMessage> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/queryPagedList")
    public PagedList<PrivateMessage> queryPagedList(
            @RequestParam(value = "pageNumber", required = false, defaultValue = "1") int pageIndex,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
            @RequestParam MultiValueMap<String, String> request) {
        Map<String, Object> params = new HashMap<>();
        params.put("name", request.getFirst("name"));

        PagedList<PrivateMessage> resPagedList = this.privateMessageService
                .queryPagedList(params, pageIndex, pageSize);
        return resPagedList;
    }
    
    /**
     * 新增私信实例
     * <功能详细描述>
     * @param privateMessage [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/add")
    public boolean add(PrivateMessage privateMessage) {
        this.privateMessageService.insert(privateMessage);
        return true;
    }
    
    /**
     * 更新私信实例<br/>
     * <功能详细描述>
     * @param privateMessage
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/update")
    public boolean update(PrivateMessage privateMessage) {
        boolean flag = this.privateMessageService.updateById(privateMessage);
        return flag;
    }
    
    /**
     * 根据主键查询私信实例<br/> 
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
    public PrivateMessage findById(@RequestParam(value = "id") String id) {
        PrivateMessage privateMessage = this.privateMessageService.findById(id);
        return privateMessage;
    }
    
    /**
     * 删除私信实例<br/> 
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
        boolean flag = this.privateMessageService.deleteById(id);
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
        boolean flag = this.privateMessageService.exists(params, excludeId);
        
        Map<String, String> resMap = new HashMap<String, String>();
        if (!flag) {
            resMap.put("ok", "");
        } else {
            resMap.put("error", "重复值");
        }
        return resMap;
    }
    
}