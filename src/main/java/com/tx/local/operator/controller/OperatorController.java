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
import com.tx.local.operator.model.Operator;
import com.tx.local.operator.service.OperatorService;
import com.tx.local.security.util.WebContextUtils;

/**
 * 操作人员控制层<br/>
 * 
 * @author []
 * @version [版本号]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Controller
@RequestMapping("/operator")
public class OperatorController {
    
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
        
        return "operator/queryOperatorPagedList";
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
        
        return "operator/addOperator";
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
        
        return "operator/updateOperator";
    }
    
    /**
     * 跳转到配置人员角色界面<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/toConfigPost")
    public String toConfigPost(@RequestParam("id") String id,
            ModelMap response) {
        Operator operator = this.operatorService.findById(id);
        response.put("operator", operator);
        
        return "operator/configPost";
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
            @RequestParam(value = "valid", required = false) Boolean valid,
            @RequestParam MultiValueMap<String, String> request) {
        Map<String, Object> params = new HashMap<>();
        String vcid = WebContextUtils.getVcid();
        params.put("vcid", vcid);
        String operatorId = WebContextUtils.getOperatorId();
        params.put("currentOperatorId", operatorId);
        
        List<Operator> resList = this.operatorService.queryList(valid, params);
        resList.forEach(oper -> {
            if (StringUtils.equals(oper.getId(), operatorId)) {
                oper.setModifyAble(false);
            }
        });
        
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
            @RequestParam(value = "valid", required = false) Boolean valid,
            @RequestParam(value = "pageNumber", required = false, defaultValue = "1") int pageIndex,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
            @RequestParam MultiValueMap<String, String> request) {
        Map<String, Object> params = new HashMap<>();
        String vcid = WebContextUtils.getVcid();
        params.put("vcid", vcid);
        String operatorId = WebContextUtils.getOperatorId();
        params.put("currentOperatorId", operatorId);
        
        PagedList<Operator> resPagedList = this.operatorService
                .queryPagedList(valid, params, pageIndex, pageSize);
        resPagedList.getList().forEach(oper -> {
            if (StringUtils.equals(oper.getId(), operatorId)) {
                oper.setModifyAble(false);
            }
        });
        
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
        operator.setVcid(WebContextUtils.getVcid());
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
    
    /**
     * 根据主键查询操作人员实例<br/> 
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
    public Operator findById(@RequestParam(value = "id") String id) {
        Operator operator = this.operatorService.findById(id);
        return operator;
    }
    
    /**
     * 删除操作人员实例<br/> 
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
        boolean flag = this.operatorService.deleteById(id);
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
        params.remove(excludeId);
        boolean flag = this.operatorService.exists(params, excludeId);
        
        Map<String, String> resMap = new HashMap<String, String>();
        if (!flag) {
            resMap.put("ok", "");
        } else {
            resMap.put("error", "重复值");
        }
        return resMap;
    }
    
    /**
     * 禁用操作人员实例
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
        boolean flag = this.operatorService.disableById(id);
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
    @RequestMapping("/enableById")
    public boolean enableById(@RequestParam(value = "id") String id) {
        boolean flag = this.operatorService.enableById(id);
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
        boolean flag = this.operatorService.lockById(id);
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
        boolean flag = this.operatorService.unlockById(id);
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
    @RequestMapping("/resetPwdById")
    public boolean resetPwdById(@RequestParam(value = "id") String id) {
        boolean flag = this.operatorService.resetPwdById(id);
        return flag;
    }
    
    /**
     * 配置客户的职位<br/>
     * <功能详细描述>
     * @param id
     * @param postId
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/configPostById")
    public boolean configPostById(@RequestParam(value = "id") String id,
            @RequestParam(value = "postId") String postId) {
        boolean flag = this.operatorService.updateMainPostById(id, postId);
        return flag;
    }
    
}