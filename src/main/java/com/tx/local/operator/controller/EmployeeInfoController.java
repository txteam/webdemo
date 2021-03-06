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

import com.tx.local.operator.model.EmployeeInfo;
import com.tx.local.operator.model.Operator;
import com.tx.local.operator.service.EmployeeInfoService;
import com.tx.core.paged.model.PagedList;

import com.tx.local.basicdata.model.IDCardTypeEnum;
import com.tx.local.basicdata.model.SexEnum;

/**
 * 员工信息控制层<br/>
 * 
 * @author []
 * @version [版本号]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Controller
@RequestMapping("/employeeInfo")
public class EmployeeInfoController {
    
    //员工信息业务层
    @Resource(name = "employeeInfoService")
    private EmployeeInfoService employeeInfoService;
    
    /**
     * 跳转到查询员工信息分页列表页面<br/>
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
        response.put("sexs", SexEnum.values());
        
        return "operator/queryEmployeeInfoPagedList";
    }
    
    /**
     * 跳转到新增员工信息页面<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/toAdd")
    public String toAdd(ModelMap response) {
        response.put("employeeInfo", new EmployeeInfo());
        
        response.put("idCardTypes", IDCardTypeEnum.values());
        response.put("sexs", SexEnum.values());
        
        return "operator/addEmployeeInfo";
    }
    
    /**
     * 跳转到编辑员工信息页面
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/toUpdate")
    public String toUpdate(@RequestParam("id") String id, ModelMap response) {
        EmployeeInfo employeeInfo = this.employeeInfoService.findById(id);
        response.put("employeeInfo", employeeInfo);
        
        response.put("idCardTypes", IDCardTypeEnum.values());
        response.put("sexs", SexEnum.values());
        
        return "operator/updateEmployeeInfo";
    }
    
    /**
     * 跳转到保存员工信息页面
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/toSave")
    public String toSave(@RequestParam(value = "operatorId") String operatorId,
            ModelMap response) {
        EmployeeInfo employeeInfo = this.employeeInfoService
                .findByOperatorId(operatorId);
        if (employeeInfo == null) {
            employeeInfo = new EmployeeInfo();
            employeeInfo.setOperator(new Operator());
            employeeInfo.getOperator().setId(operatorId);
        }
        response.put("employeeInfo", employeeInfo);
        
        response.put("idCardTypes", IDCardTypeEnum.values());
        response.put("sexs", SexEnum.values());
        return "operator/saveEmployeeInfo";
    }
    
    /**
     * 查询员工信息实例列表<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<EmployeeInfo> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/queryList")
    public List<EmployeeInfo> queryList(
            @RequestParam MultiValueMap<String, String> request) {
        Map<String, Object> params = new HashMap<>();
        params.put("name", request.getFirst("name"));
        
        List<EmployeeInfo> resList = this.employeeInfoService.queryList(params);
        
        return resList;
    }
    
    /**
     * 查询员工信息实例分页列表<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<EmployeeInfo> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/queryPagedList")
    public PagedList<EmployeeInfo> queryPagedList(
            @RequestParam(value = "pageNumber", required = false, defaultValue = "1") int pageIndex,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
            @RequestParam MultiValueMap<String, String> request) {
        Map<String, Object> params = new HashMap<>();
        params.put("name", request.getFirst("name"));
        
        PagedList<EmployeeInfo> resPagedList = this.employeeInfoService
                .queryPagedList(params, pageIndex, pageSize);
        return resPagedList;
    }
    
    /**
     * 新增员工信息实例
     * <功能详细描述>
     * @param employeeInfo [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/add")
    public boolean add(EmployeeInfo employeeInfo) {
        this.employeeInfoService.insert(employeeInfo);
        return true;
    }
    
    /**
     * 更新员工信息实例<br/>
     * <功能详细描述>
     * @param employeeInfo
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/update")
    public boolean update(EmployeeInfo employeeInfo) {
        boolean flag = this.employeeInfoService.updateById(employeeInfo);
        return flag;
    }
    
    /**
     * 更新员工信息实例<br/>
     * <功能详细描述>
     * @param employeeInfo
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/save")
    public boolean save(EmployeeInfo employeeInfo) {
        this.employeeInfoService.save(employeeInfo);
        boolean flag = true;
        return flag;
    }
    
    /**
     * 根据主键查询员工信息实例<br/> 
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
    public EmployeeInfo findById(@RequestParam(value = "id") String id) {
        EmployeeInfo employeeInfo = this.employeeInfoService.findById(id);
        return employeeInfo;
    }
    
    /**
     * 删除员工信息实例<br/> 
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
        boolean flag = this.employeeInfoService.deleteById(id);
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
        boolean flag = this.employeeInfoService.exists(params, excludeId);
        
        Map<String, String> resMap = new HashMap<String, String>();
        if (!flag) {
            resMap.put("ok", "");
        } else {
            resMap.put("error", "重复值");
        }
        return resMap;
    }
    
}