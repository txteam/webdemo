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

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tx.core.paged.model.PagedList;
import com.tx.local.operator.model.Operator;
import com.tx.local.operator.service.OperatorService;

/**
 * 操作人员控制层<br/>
 * 
 * @author []
 * @version [版本号]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Controller
@RequestMapping("/admin/operator")
public class AdminOperatorController {
    
    //操作人员业务层
    @Resource(name = "operatorService")
    private OperatorService operatorService;
    
    /**
     * 跳转到查询操作人员列表页面<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/toQueryPagedList")
    public String toQueryPagedList(ModelMap response) {
        
        return "/operator/admin/queryOperatorPagedList";
    }
    
    /**
     * 跳转到新增操作人员页面<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/toAdd")
    public String toAdd(
            @RequestParam(value = "vcid", required = false) String vcid,
            @RequestParam(value = "organizationId", required = false) String organizationId,
            ModelMap response) {
        response.put("operator", new Operator());
        
        return "/operator/admin/addOperator";
    }
    
    /**
     * 跳转到编辑操作人员页面
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/toUpdate")
    public String toUpdate(@RequestParam("id") String id, ModelMap response) {
        Operator operator = this.operatorService.findById(id);
        response.put("operator", operator);
        
        return "/operator/admin/updateOperator";
    }
    
    /**
     * 查询操作人员实例列表<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<Operator> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/queryList")
    public List<Operator> queryList(
            @RequestParam(value = "vcid", required = false) String vcid,
            @RequestParam(value = "valid", required = false) Boolean valid,
            @RequestParam MultiValueMap<String, String> request) {
        Map<String, Object> params = new HashMap<>();
        params.put("vcid", vcid);
        
        List<Operator> resList = this.operatorService.queryList(valid, params);
        
        return resList;
    }
    
    /**
     * 查询操作人员实例分页列表<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<Operator> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/queryPagedList")
    public PagedList<Operator> queryPagedList(
            @RequestParam(value = "vcid", required = false) String vcid,
            @RequestParam(value = "valid", required = false) Boolean valid,
            @RequestParam(value = "pageNumber", required = false, defaultValue = "1") int pageIndex,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
            @RequestParam MultiValueMap<String, String> request) {
        Map<String, Object> params = new HashMap<>();
        params.put("vcid", vcid);
        
        PagedList<Operator> resPagedList = this.operatorService
                .queryPagedList(valid, params, pageIndex, pageSize);
        return resPagedList;
    }
    
    /**
     * 新增操作人员实例
     * <功能详细描述>
     * @param operator [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/add")
    public boolean add(Operator operator) {
        this.operatorService.insert(operator);
        return true;
    }
    
    /**
     * 更新操作人员实例<br/>
     * <功能详细描述>
     * @param operator
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/update")
    public boolean update(Operator operator) {
        boolean flag = this.operatorService.updateById(operator);
        return flag;
    }
}