/*
 * 描       述:  <描述>
 * 修  改 人:  
 * 修改时间:
 * <修改描述:>
 */
package com.tx.local.vitualcenter.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tx.core.paged.model.PagedList;
import com.tx.local.vitualcenter.model.VirtualCenter;
import com.tx.local.vitualcenter.service.VirtualCenterService;

/**
 * VirtualCenter控制层<br/>
 * 
 * @author []
 * @version [版本号]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Controller
@RequestMapping("/virtualCenter")
public class VirtualCenterController {
    
    //VirtualCenter业务层
    @Resource(name = "virtualCenterService")
    private VirtualCenterService virtualCenterService;
    
    /**
     * 跳转到查询VirtualCenter列表页面<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/toQueryTreeList")
    public String toQueryTreeList(ModelMap response) {
        return "virtualcenter/queryVirtualCenterTreeList";
    }
    
    /**
     * 跳转到新增VirtualCenter页面<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/toAdd")
    public String toAdd(
            @RequestParam(value = "parentId", required = false) String parentId,
            ModelMap response) {
        response.put("virtualCenter", new VirtualCenter());
        
        if (!StringUtils.isEmpty(parentId)) {
            VirtualCenter parent = this.virtualCenterService.findById(parentId);
            response.put("parent", parent);
        }
        
        return "virtualcenter/addVirtualCenter";
    }
    
    /**
     * 跳转到编辑VirtualCenter页面
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/toUpdate")
    public String toUpdate(@RequestParam("id") String id, ModelMap response) {
        VirtualCenter virtualCenter = this.virtualCenterService.findById(id);
        response.put("virtualCenter", virtualCenter);
        if (!StringUtils.isEmpty(virtualCenter.getParentId())) {
            VirtualCenter parent = this.virtualCenterService
                    .findById(virtualCenter.getParentId());
            response.put("parent", parent);
        }
        
        return "virtualcenter/updateVirtualCenter";
    }
    
    /**
     * 跳转到选择虚中心页面
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/toSelect")
    public String toSelectVirtualCenter(
            @RequestParam(value = "eventName", required = false) String eventName,
            ModelMap responseMap) {
        responseMap.put("eventName", eventName);
        return "virtualcenter/selectVirtualCenter";
    }
    
    /**
     * 查询VirtualCenter实例列表<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<VirtualCenter> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/queryList")
    public List<VirtualCenter> queryList(
            @RequestParam(value = "valid", required = false) Boolean valid,
            @RequestParam MultiValueMap<String, String> request) {
        Map<String, Object> params = new HashMap<>();
        //params.put("",request.getFirst(""));
        
        List<VirtualCenter> resList = this.virtualCenterService.queryList(valid,
                params);
        
        return resList;
    }
    
    /**
     * 查询VirtualCenter实例分页列表<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<VirtualCenter> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/queryPagedList")
    public PagedList<VirtualCenter> queryPagedList(
            @RequestParam(value = "valid", required = false) Boolean valid,
            @RequestParam(value = "pageNumber", required = false, defaultValue = "1") int pageIndex,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
            @RequestParam MultiValueMap<String, String> request) {
        Map<String, Object> params = new HashMap<>();
        //params.put("",request.getFirst(""));
        
        PagedList<VirtualCenter> resPagedList = this.virtualCenterService
                .queryPagedList(valid, params, pageIndex, pageSize);
        return resPagedList;
    }
    
    /**
     * 新增VirtualCenter实例
     * <功能详细描述>
     * @param virtualCenter [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/add")
    public boolean add(VirtualCenter virtualCenter) {
        this.virtualCenterService.insert(virtualCenter);
        return true;
    }
    
    /**
     * 更新VirtualCenter实例<br/>
     * <功能详细描述>
     * @param virtualCenter
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/update")
    public boolean update(VirtualCenter virtualCenter) {
        boolean flag = this.virtualCenterService.updateById(virtualCenter);
        return flag;
    }
    
    /**
     * 根据主键查询VirtualCenter实例<br/> 
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
    public VirtualCenter findById(@RequestParam(value = "id") String id) {
        VirtualCenter virtualCenter = this.virtualCenterService.findById(id);
        return virtualCenter;
    }
    
    /**
     * 根据编码查询VirtualCenter实例<br/> 
     * <功能详细描述>
     * @param code
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/findByCode")
    public VirtualCenter findByCode(@RequestParam(value = "code") String code) {
        VirtualCenter virtualCenter = this.virtualCenterService
                .findByCode(code);
        return virtualCenter;
    }
    
    /**
     * 删除VirtualCenter实例<br/> 
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
        boolean flag = this.virtualCenterService.deleteById(id);
        return flag;
    }
    
    /**
     * 禁用VirtualCenter实例
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
        boolean flag = this.virtualCenterService.disableById(id);
        return flag;
    }
    
    /**
     * 启用VirtualCenter实例<br/>
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
        boolean flag = this.virtualCenterService.enableById(id);
        return flag;
    }
    
    /**
     * 跳转到添加虚中心结构页面
     * <功能详细描述>
     * @param virtualCenterId [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/validate")
    public Map<String, String> validate(
            @RequestParam(value = "id", required = false) String excludeId,
            @RequestParam Map<String, String> params) {
        params.remove("id");
        boolean flag = this.virtualCenterService.exists(params, excludeId);
        
        Map<String, String> resMap = new HashMap<String, String>();
        if (!flag) {
            resMap.put("ok", "");
        } else {
            resMap.put("error", "重复的虚中心编码");
        }
        return resMap;
    }
    
    /**
     * 是否可编辑<br/>
     * <功能详细描述>
     * @param id
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/modifyAble")
    public boolean modifyAble(@RequestParam(value = "id") String id) {
        boolean flag = this.virtualCenterService.modifyAble(id);
        return flag;
    }
    
    /**
     * 根据条件查询虚中心子级列表<br/>
     * <功能详细描述>
     * @param parentId
     * @param valid
     * @param request
     * @return [参数说明]
     * 
     * @return PagedList<T> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/queryChildren")
    public List<VirtualCenter> queryChildren(
            @PathVariable(value = "parentId", required = true) String parentId,
            @RequestParam(value = "valid", required = false) Boolean valid,
            @RequestParam MultiValueMap<String, String> request) {
        Map<String, Object> params = new HashMap<>();
        
        List<VirtualCenter> resList = this.virtualCenterService
                .queryChildrenByParentId(parentId, valid, params);
        
        return resList;
    }
    
    /**
     * 根据条件查询虚中心子、孙级列表<br/>
     * <功能详细描述>
     * @param parentId
     * @param valid
     * @param request
     * @return [参数说明]
     * 
     * @return PagedList<T> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/queryDescendants")
    public List<VirtualCenter> queryDescendants(
            @PathVariable(value = "parentId", required = true) String parentId,
            @RequestParam(value = "valid", required = false) Boolean valid,
            @RequestParam MultiValueMap<String, String> request) {
        Map<String, Object> params = new HashMap<>();
        
        List<VirtualCenter> resList = this.virtualCenterService
                .queryDescendantsByParentId(parentId, valid, params);
        
        return resList;
    }
}