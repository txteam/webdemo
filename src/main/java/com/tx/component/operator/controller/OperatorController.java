/*
 * 描          述:  <描述>
 * 修  改   人:  brady
 * 修改时间:  2013-8-28
 * <修改描述:>
 */
package com.tx.component.operator.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.EnumUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tx.component.auth.annotation.CheckOperateAuth;
import com.tx.component.operator.model.Operator;
import com.tx.component.operator.model.OperatorStateEnum;
import com.tx.component.operator.model.Post;
import com.tx.component.operator.service.OperatorService;
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
    
    @Resource(name = "operatorService")
    private OperatorService operatorService;
    
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
    public String toAddOperator() {
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
    public String toUpdateOperator() {
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
            @RequestParam(value = "pageIndex", required = false, defaultValue = "1") int pageIndex,
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
        PagedList<Operator> resPagedList = this.operatorService.queryOperatorPagedListByOrganizationIdIncludeInvalid(organizationId,
                loginName,
                userName,
                code,
                state,
                pageIndex + 1,
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
            @RequestParam(value = "pageIndex", required = false, defaultValue = "1") int pageIndex,
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
   public boolean deleteOperatorById(@RequestParam(value = "operatorId") String operatorId) {
       boolean resFlag = this.operatorService.deleteById(operatorId);
       return resFlag;
   }
   
   
//   @CheckOperateAuth(key = "disable_post", name = "禁用职位")
//   @ResponseBody
//   @RequestMapping("/disablePostById")
//   public boolean disablePostById(@RequestParam(value = "postId") String postId) {
//       boolean resFlag = this.postService.disableById(postId);
//       return resFlag;
//   }
//   
//   @CheckOperateAuth(key = "enable_post", name = "启用职位")
//   @ResponseBody
//   @RequestMapping("/enablePostById")
//   public boolean enablePostById(@RequestParam(value = "postId") String postId) {
//       boolean resFlag = this.postService.enableById(postId);
//       return resFlag;
//   }
    
}
