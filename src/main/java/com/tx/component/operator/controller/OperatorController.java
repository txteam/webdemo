/*
 * 描          述:  <描述>
 * 修  改   人:  brady
 * 修改时间:  2013-8-28
 * <修改描述:>
 */
package com.tx.component.operator.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tx.component.auth.annotation.CheckOperateAuth;
import com.tx.component.operator.model.Operator;
import com.tx.component.operator.model.OperatorStateEnum;
import com.tx.component.operator.model.Organization;
import com.tx.component.operator.service.OperatorService;
import com.tx.component.operator.service.OrganizationService;
import com.tx.core.paged.model.PagedList;

/**
 * 人员操作视图逻辑层
 * <功能详细描述>
 * 
 * @author  brady
 * @version  [版本号, 2013-8-28]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Controller
@RequestMapping("/operator")
@CheckOperateAuth(key = "operator_manage")
public class OperatorController {
    
    /** 操作员业务层逻辑 */
    @Resource(name = "operatorService")
    private OperatorService operatorService;
    
    /** 组织业务层逻辑 */
    @Resource(name = "organizationService")
    private OrganizationService organizationService;
    
    /**
     * 跳转到查询职位列表(单选)<br/>
     *<功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
    @RequestMapping("/toSingleChooseOperator")
    public String toSingleChooseOperator(
            @RequestParam(value = "eventName", required = false) String chooseEventName,
            @RequestParam(value = "organizationId", required = false) String organizationId,
            ModelMap responseMap) {
        responseMap.put("eventName", chooseEventName);
        responseMap.put("organizationId", organizationId);
        return "/operator/singleChooseOperator";
    }
    
    /**
     * 跳转到查询职位列表（复选）<br/>
     *<功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
    @RequestMapping("/toMultiChooseOperator")
    public String toMultiChooseOperator(
            @RequestParam(value = "eventName", required = false) String chooseEventName,
            @RequestParam(value = "organizationId", required = false) String organizationId,
            ModelMap responseMap) {
        responseMap.put("eventName", chooseEventName);
        responseMap.put("organizationId", organizationId);
        return "/operator/multiChooseOperator";
    }
    
    /**
      * 跳转到查询人员列表页面<BR/>
      *<功能详细描述>
      * @return [参数说明]
      * 
      * @return String [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/toQueryOperatorList")
    public String toQueryOperatorList() {
        return "/operator/queryOperatorList";
    }
    
    /**
      * 跳转到新增操作员列表页面<br/>
      *<功能详细描述>
      * @return [参数说明]
      * 
      * @return String [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/toAddOperator")
    public String toAddOperator(
            @RequestParam(value = "organizationId", required = false) String organizationId,
            ModelMap response) {
        if (!StringUtils.isEmpty(organizationId)) {
            Organization organization = this.organizationService.findOrganizationById(organizationId);
            response.put("organization", organization);
        }
        response.put("operator", new Operator());
        return "/operator/addOperator";
    }
    
    /**
      * 跳转到更新操作员信息页面<br/>
      *<功能详细描述>
      * @return [参数说明]
      * 
      * @return String [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/toUpdateOperator")
    public String toUpdateOperator(
            @RequestParam("operatorId") String operatorId, ModelMap modelMap) {
        Operator resOper = this.operatorService.findOperatorById(operatorId);
        Organization organization = null;
        if (resOper.getOrganization() != null
                && !StringUtils.isEmpty(resOper.getOrganization().getId())) {
            organization = this.organizationService.findOrganizationById(resOper.getOrganization()
                    .getId());
        }
        modelMap.put("organization", organization);
        modelMap.put("operator", resOper);
        
        return "/operator/updateOperator";
    }
    
    /**
      * 分页查询操作人员<br/>
      *<功能详细描述>
      * @param requestMap
      * @param pageIndex
      * @param pageSize
      * @param responseMap
      * @return [参数说明]
      * 
      * @return PagedList<Operator> [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    @RequestMapping("queryOperatorPagedListIncludeInvalid")
    @ResponseBody
    public PagedList<Operator> queryOperatorPagedListIncludeInvalid(
            @RequestParam Map<String, String> requestMap,
            @RequestParam(value = "pageNumber", required = false, defaultValue = "1") int pageIndex,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
            ModelMap responseMap) {
        String organizationId = requestMap.get("organizationId");
        String loginName = requestMap.get("loginName");
        String userName = requestMap.get("userName");
        String code = requestMap.get("code");
        String stateValue = requestMap.get("state");
        String postId = requestMap.get("postId");
        OperatorStateEnum state = EnumUtils.getEnumMap(OperatorStateEnum.class)
                .get(stateValue);
        
        //String organizationId = requestMap.get("");
        PagedList<Operator> resPagedList = this.operatorService.queryOperatorPagedListByOrganizationIdIncludeInvalid(organizationId,
                loginName,
                userName,
                code,
                state,
                postId,
                pageIndex,
                pageSize);
        return resPagedList;
    }
    
    /**
     * 分页查询操作人员<br/>
     *<功能详细描述>
     * @param requestMap
     * @param pageIndex
     * @param pageSize
     * @param responseMap
     * @return [参数说明]
     * 
     * @return PagedList<Operator> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
    @RequestMapping("queryOperatorPagedList")
    @ResponseBody
    public PagedList<Operator> queryOperatorPagedList(
            @RequestParam Map<String, String> requestMap,
            @RequestParam(value = "pageNumber", required = false, defaultValue = "1") int pageIndex,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
            ModelMap responseMap) {
        String organizationId = requestMap.get("organizationId");
        String loginName = requestMap.get("loginName");
        String userName = requestMap.get("userName");
        String code = requestMap.get("code");
        String stateValue = requestMap.get("state");
        OperatorStateEnum state = EnumUtils.getEnumMap(OperatorStateEnum.class)
                .get(stateValue);
        
        //String organizationId = requestMap.get("");
        PagedList<Operator> resPagedList = this.operatorService.queryOperatorPagedListByOrganizationId(organizationId,
                loginName,
                userName,
                code,
                state,
                pageIndex,
                pageSize);
        return resPagedList;
    }
    
    /**
     * 添加组织结构页面
     *<功能详细描述>
     * @param organization [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
    @CheckOperateAuth(key = "add_operator", name = "增加操作员")
    @RequestMapping("/addOperator")
    @ResponseBody
    public boolean addOperator(Operator operator) {
        this.operatorService.insertOperator(operator);
        return true;
    }
    
    /**
     * 更新操作员<br/>
     *<功能详细描述>
     * @param operator
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
    @CheckOperateAuth(key = "update_operator", name = "编辑操作员")
    @RequestMapping("/updateOperator")
    @ResponseBody
    public boolean updateOperator(Operator operator) {
        boolean flag = this.operatorService.updateById(operator);
        
        return flag;
    }
    
    /**
     * 删除指定职位<br/> 
     *<功能详细描述>
     * @param postId
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
    @CheckOperateAuth(key = "delete_operator", name = "删除操作员", configAble = false)
    @ResponseBody
    @RequestMapping("/deleteOperatorById")
    public boolean deleteOperatorById(
            @RequestParam(value = "operatorId") String operatorId) {
        boolean resFlag = this.operatorService.deleteById(operatorId);
        return resFlag;
    }
    
    /**
      * 判断登录名是否已经在系统中存在<br/>
      *<功能详细描述>
      * @param loginName
      * @return [参数说明]
      * 
      * @return boolean [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/loginNameIsExist")
    public Map<String, String> loginNameIsExist(
            @RequestParam(value = "loginName") String loginName,
            @RequestParam(value = "id", required = false) String excludeOperatorId) {
        boolean resFlag = this.operatorService.loginNameIsExist(loginName,
                excludeOperatorId);
        Map<String, String> resMap = new HashMap<String, String>();
        if (!resFlag) {
            resMap.put("ok", "可用的登录名");
        } else {
            resMap.put("error", "已经存在的登录名");
        }
        return resMap;
    }
    
    /**
      * 禁用操作员<br/>
      *<功能详细描述>
      * @param operatorId
      * @return [参数说明]
      * 
      * @return boolean [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    @CheckOperateAuth(key = "disable_operator", name = "禁用操作员")
    @ResponseBody
    @RequestMapping("/disableOperatorById")
    public boolean disableOperatorById(
            @RequestParam(value = "operatorId") String operatorId) {
        boolean resFlag = this.operatorService.disableOperatorById(operatorId);
        return resFlag;
    }
    
    /**
      * 启用操作员<br/>
      *<功能详细描述>
      * @param operatorId
      * @return [参数说明]
      * 
      * @return boolean [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    @CheckOperateAuth(key = "enable_operator", name = "启用操作员")
    @ResponseBody
    @RequestMapping("/enableOperatorById")
    public boolean enableOperatorById(
            @RequestParam(value = "operatorId") String operatorId) {
        boolean resFlag = this.operatorService.enableOperatorById(operatorId);
        return resFlag;
    }
    
    /**
     * 解锁操作员<br/>
     *<功能详细描述>
     * @param operatorId
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
    @CheckOperateAuth(key = "unlock_operator", name = "解锁操作员")
    @ResponseBody
    @RequestMapping("/unlockOperatorById")
    public boolean unlockOperatorById(
            @RequestParam(value = "operatorId") String operatorId) {
        boolean resFlag = this.operatorService.unlockOperatorById(operatorId);
        return resFlag;
    }
    
}
