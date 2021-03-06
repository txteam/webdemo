/*
 * 描       述:  <描述>
 * 修  改 人:  
 * 修改时间:
 * <修改描述:>
 */
package com.tx.local.operator.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tx.core.paged.model.PagedList;
import com.tx.local.operator.model.OperatorRoleCatalog;
import com.tx.local.operator.service.OperatorRoleCatalogService;
import com.tx.local.organization.model.OrganizationTypeEnum;
import com.tx.local.security.util.WebContextUtils;

/**
 * 角色分类控制层<br/>
 * 
 * @author []
 * @version [版本号]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Controller
@RequestMapping("/operatorRoleCatalog")
public class OperatorRoleCatalogController {
    
    //角色分类业务层
    @Resource(name = "operatorRoleCatalogService")
    private OperatorRoleCatalogService operatorRoleCatalogService;
    
    /**
     * 跳转到查询角色分类列表页面<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/toQueryTreeList")
    public String toQueryTreeList(ModelMap response) {
        
        return "operator/queryOperatorRoleCatalogTreeList";
    }
    
    /**
     * 跳转到新增角色分类页面<br/>
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
        OperatorRoleCatalog catalog = new OperatorRoleCatalog();
        String vcid = WebContextUtils.getVcid();
        catalog.setVcid(vcid);
        
        response.put("types", OrganizationTypeEnum.values());
        if (!StringUtils.isEmpty(parentId)) {
            OperatorRoleCatalog parent = this.operatorRoleCatalogService
                    .findById(parentId);
            response.put("parent", parent);
            
            //避免在操作期间出现VCID异常变动的情况
            if (parent != null && !parent.getVcid().equals(vcid)) {
                catalog.setParentId(null);
            }
        }
        response.put("operatorRoleCatalog", catalog);
        response.put("vcid", vcid);
        
        return "operator/addOperatorRoleCatalog";
    }
    
    /**
     * 跳转到编辑角色分类页面
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/toUpdate")
    public String toUpdate(@RequestParam("id") String id, ModelMap response) {
        OperatorRoleCatalog catalog = this.operatorRoleCatalogService
                .findById(id);
        response.put("operatorRoleCatalog", catalog);
        
        if (!StringUtils.isEmpty(catalog.getParentId())) {
            OperatorRoleCatalog parent = this.operatorRoleCatalogService
                    .findById(catalog.getParentId());
            response.put("parent", parent);
        }
        
        return "operator/updateOperatorRoleCatalog";
    }
    
    /**
     * 跳转到选择组织页面
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/toSelect")
    public String toSelect(
            @RequestParam(value = "eventName", required = true) String eventName,
            ModelMap responseMap) {
        responseMap.put("vcid", WebContextUtils.getVcid());
        responseMap.put("eventName", eventName);
        
        return "operator/selectOperatorRoleCatalog";
    }
    
    /**
     * 查询角色分类实例列表<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<OperatorRoleCatalog> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/queryList")
    public List<OperatorRoleCatalog> queryList(
            @RequestParam(value = "valid", required = false) Boolean valid,
            @RequestParam MultiValueMap<String, String> request) {
        Map<String, Object> params = new HashMap<>();
        //params.put("",request.getFirst(""));
        params.put("vcid", WebContextUtils.getVcid());
        
        List<OperatorRoleCatalog> resList = this.operatorRoleCatalogService
                .queryList(valid, params);
        
        return resList;
    }
    
    /**
     * 查询角色分类实例分页列表<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<OperatorRoleCatalog> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/queryPagedList")
    public PagedList<OperatorRoleCatalog> queryPagedList(
            @RequestParam(value = "valid", required = false) Boolean valid,
            @RequestParam(value = "pageNumber", required = false, defaultValue = "1") int pageIndex,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
            @RequestParam MultiValueMap<String, String> request) {
        Map<String, Object> params = new HashMap<>();
        //params.put("",request.getFirst(""));
        params.put("vcid", WebContextUtils.getVcid());
        
        PagedList<OperatorRoleCatalog> resPagedList = this.operatorRoleCatalogService
                .queryPagedList(valid, params, pageIndex, pageSize);
        return resPagedList;
    }
    
    /**
     * 新增角色分类实例
     * <功能详细描述>
     * @param operatorRoleCatalog [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/add")
    public boolean add(OperatorRoleCatalog operatorRoleCatalog) {
        operatorRoleCatalog.setVcid(WebContextUtils.getVcid());
        
        this.operatorRoleCatalogService.insert(operatorRoleCatalog);
        return true;
    }
    
    /**
     * 更新角色分类实例<br/>
     * <功能详细描述>
     * @param operatorRoleCatalog
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/update")
    public boolean update(OperatorRoleCatalog operatorRoleCatalog) {
        operatorRoleCatalog.setVcid(WebContextUtils.getVcid());
        boolean flag = this.operatorRoleCatalogService
                .updateById(operatorRoleCatalog);
        return flag;
    }
    
    /**
     * 根据主键查询角色分类实例<br/> 
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
    public OperatorRoleCatalog findById(@RequestParam(value = "id") String id) {
        OperatorRoleCatalog operatorRoleCatalog = this.operatorRoleCatalogService
                .findById(id);
        return operatorRoleCatalog;
    }
    
    /**
     * 删除角色分类实例<br/> 
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
        boolean flag = this.operatorRoleCatalogService.deleteById(id);
        return flag;
    }
    
    /**
     * 禁用角色分类实例
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
        boolean flag = this.operatorRoleCatalogService.disableById(id);
        return flag;
    }
    
    /**
     * 启用角色分类实例<br/>
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
        boolean flag = this.operatorRoleCatalogService.enableById(id);
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
        boolean flag = this.operatorRoleCatalogService.exists(params,
                excludeId);
        
        Map<String, String> resMap = new HashMap<String, String>();
        if (!flag) {
            resMap.put("ok", "");
        } else {
            resMap.put("error", "重复值");
        }
        return resMap;
    }
    
    /**
     * 根据条件查询角色分类子级列表<br/>
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
    public List<OperatorRoleCatalog> queryChildren(
            @RequestParam(value = "parentId", required = true) String parentId,
            @RequestParam(value = "valid", required = false) Boolean valid,
            @RequestParam MultiValueMap<String, String> request) {
        Map<String, Object> params = new HashMap<>();
        params.put("vcid", WebContextUtils.getVcid());
        
        List<OperatorRoleCatalog> resList = this.operatorRoleCatalogService
                .queryChildrenByParentId(parentId, valid, params);
        
        return resList;
    }
    
    /**
     * 根据条件查询角色分类子、孙级列表<br/>
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
    public List<OperatorRoleCatalog> queryDescendants(
            @RequestParam(value = "parentId", required = true) String parentId,
            @RequestParam(value = "valid", required = false) Boolean valid,
            @RequestParam MultiValueMap<String, String> request) {
        Map<String, Object> params = new HashMap<>();
        params.put("vcid", WebContextUtils.getVcid());
        
        List<OperatorRoleCatalog> resList = this.operatorRoleCatalogService
                .queryDescendantsByParentId(parentId, valid, params);
        
        return resList;
    }
    
}