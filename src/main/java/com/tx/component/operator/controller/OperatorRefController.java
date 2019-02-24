/*
 * 描 述: <描述> 修 改 人: Administrator 修改时间: 2014年2月23日 <修改描述:>
 */
package com.tx.component.operator.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tx.component.auth.annotation.CheckOperateAuth;
import com.tx.component.operator.OperatorConstants;
import com.tx.component.operator.service.OperatorRefService;

/**
 * 配置职位人员Controller层 <功能详细描述>
 * 
 * @author Administrator
 * @version [版本号, 2014年2月23日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Controller
@RequestMapping("/operatorRef")
public class OperatorRefController {
    
    @Resource(name = "operatorRefService")
    private OperatorRefService operatorRefService;
    
    /**
     * 跳转到配置职位对应的人员页面 <功能详细描述>
     * 
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/toConfigPostOperator")
    public String toConfigPostOperator(@RequestParam("postId") String postId, ModelMap response) {
        response.put("postId", postId);
        return "/operator/configPostOperator";
    }
    
    /**
     * 跳转到配置角色对应的人员页面 <功能详细描述>
     * 
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/toConfigRoleOperator")
    public String toConfigRoleOperator(@RequestParam("roleId") String roleId, ModelMap response) {
        response.put("roleId", roleId);
        return "/operator/configRoleOperator";
    }
    
    /**
     * 跳转到配置人员职位页面 <功能详细描述>
     * 
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/toConfigOperatorPost")
    public String toConfigOperatorPost(@RequestParam(value = "operatorId", required = false) String operatorId, ModelMap response) {
        response.put("operatorId", operatorId);
        return "/operator/configOperatorPost";
    }
    
    /**
     * 跳转到配置人员职位页面 <功能详细描述>
     * 
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/toConfigOperatorRole")
    public String toConfigOperatorRole(@RequestParam("operatorId") String operatorId, ModelMap response) {
        response.put("operatorId", operatorId);
        return "/operator/configOperatorRole";
    }
    
    
    /**
     * 根据职位id查询拥有改职位的操作员id集合 <功能详细描述>
     * 
     * @param postId
     * @return [参数说明]
     * 
     * @return Set<String> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/queryChoosedOperatorIdSetByPostId")
    public Set<String> queryChoosedOperatorIdSetByPostId(@RequestParam("postId") String postId,
            @RequestParam(value = "operatorIds[]", required = false) String[] operatorIds) {
        if (ArrayUtils.isEmpty(operatorIds)) {
            return new HashSet<String>();
        }
        Set<String> operatorIdSet = this.operatorRefService.queryOperatorIdSetByRefId(OperatorConstants.OPERATORREF_TYPE_POST, postId);
        Set<String> choosedOperatorId = new HashSet<String>();
        for (String operatorIdTemp : operatorIds) {
            if (operatorIdSet.contains(operatorIdTemp)) {
                choosedOperatorId.add(operatorIdTemp);
            }
        }
        return choosedOperatorId;
    }
    
    /**
     * 根据职位id查询拥有改职位的操作员id集合 <功能详细描述>
     * 
     * @param postId
     * @return [参数说明]
     * 
     * @return Set<String> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/queryChoosedOperatorIdSetByRoleId")
    public Set<String> queryChoosedOperatorIdSetByRoleId(@RequestParam("roleId") String value,
            @RequestParam(value = "operatorIds[]", required = false) String[] operatorIds) {
        if (ArrayUtils.isEmpty(operatorIds)) {
            return new HashSet<String>();
        }
        Set<String> operatorIdSet = this.operatorRefService.queryOperatorIdSetByRefId(OperatorConstants.OPERATORREF_TYPE_ROLE, value);
        Set<String> choosedOperatorId = new HashSet<String>();
        for (String operatorIdTemp : operatorIds) {
            if (operatorIdSet.contains(operatorIdTemp)) {
                choosedOperatorId.add(operatorIdTemp);
            }
        }
        return choosedOperatorId;
    }
    
    /**
     * 跳转到配置人员职位页面 <功能详细描述>
     * 
     * @param operatorId
     * @return [参数说明]
     * 
     * @return Set<String> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/queryPostIdSetByOperatorId")
    public Set<String> queryPostIdSetByOperatorId(@RequestParam("operatorId") String operatorId) {
        Set<String> postIdSet = this.operatorRefService.queryRefIdSetByOperatorId(OperatorConstants.OPERATORREF_TYPE_POST, operatorId);
        return postIdSet;
    }
    
    /**
     * 跳转到配置人员职位页面 <功能详细描述>
     * 
     * @param operatorId
     * @return [参数说明]
     * 
     * @return Set<String> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/queryRoleIdSetByOperatorId")
    public Set<String> queryRoleIdSetByOperatorId(@RequestParam("operatorId") String operatorId) {
        Set<String> roleIdSet = this.operatorRefService.queryRefIdSetByOperatorId(OperatorConstants.OPERATORREF_TYPE_ROLE, operatorId);
        return roleIdSet;
    }
    
    /**
     * 配置职位对应的人员<br/>
     * <功能详细描述>
     * 
     * @param postId
     * @param selectedOperatorId
     * @param unSelectedOperatorId
     * @param request
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/configPostOperatorId")
    @CheckOperateAuth(key = "config_post_operator", parentKey = "post_manage", name = "配置职位人员")
    public boolean configPostOperatorId(@RequestParam("postId") String postId,
            @RequestParam(value = "selectedOperatorId[]", required = false) String[] selectedOperatorId,
            @RequestParam(value = "unSelectedOperatorId[]", required = false) String[] unSelectedOperatorId,
            @RequestParam() MultiValueMap<String, String> request) {
        List<String> selectOperatorIdList = selectedOperatorId == null ? new ArrayList<String>() : Arrays.asList(selectedOperatorId);
        List<String> unSelectOperatorIdList = unSelectedOperatorId == null ? new ArrayList<String>() : Arrays.asList(unSelectedOperatorId);
        this.operatorRefService.saveRefId2OperatorIdList(OperatorConstants.OPERATORREF_TYPE_POST,
                postId,
                selectOperatorIdList,
                unSelectOperatorIdList);
        return true;
    }
    
    /**
     * 配置职位对应的人员<br/>
     * <功能详细描述>
     * 
     * @param postId
     * @param selectedOperatorId
     * @param unSelectedOperatorId
     * @param request
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/configRoleOperatorId")
    public boolean configRoleOperatorId(@RequestParam("roleId") String roleId,
            @RequestParam(value = "selectedOperatorId[]", required = false) String[] selectedOperatorId,
            @RequestParam(value = "unSelectedOperatorId[]", required = false) String[] unSelectedOperatorId,
            @RequestParam() MultiValueMap<String, String> request) {
        List<String> selectOperatorIdList = selectedOperatorId == null ? new ArrayList<String>() : Arrays.asList(selectedOperatorId);
        List<String> unSelectOperatorIdList = unSelectedOperatorId == null ? new ArrayList<String>() : Arrays.asList(unSelectedOperatorId);
        this.operatorRefService.saveRefId2OperatorIdList(OperatorConstants.OPERATORREF_TYPE_ROLE,
                roleId,
                selectOperatorIdList,
                unSelectOperatorIdList);
        return true;
    }
    
    /**
     * 配置人员职位 <功能详细描述>
     * 
     * @param operatorId
     * @param selectedPostId
     * @param unSelectedPostId
     * @param request
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/configOperatorPostId")
    @CheckOperateAuth(key = "config_operator_post", parentKey = "operator_manage", name = "配置人员职位")
    public boolean configOperatorPostId(@RequestParam("operatorId") String operatorId,
            @RequestParam(value = "selectedPostId[]", required = false) String[] selectedPostId,
            @RequestParam(value = "unSelectedPostId[]", required = false) String[] unSelectedPostId,
            @RequestParam() MultiValueMap<String, String> request) {
        List<String> selectPostIdList = selectedPostId == null ? new ArrayList<String>() : Arrays.asList(selectedPostId);
        List<String> unSelectPostIdList = unSelectedPostId == null ? new ArrayList<String>() : Arrays.asList(unSelectedPostId);
        this.operatorRefService.saveOperator2RefIdList(OperatorConstants.OPERATORREF_TYPE_POST,
                operatorId,
                selectPostIdList,
                unSelectPostIdList);
        return true;
    }
    
    /**
     * 配置人员角色 <功能详细描述>
     * 
     * @param operatorId
     * @param selectedPostId
     * @param unSelectedPostId
     * @param request
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/configOperatorRoleId")
    public boolean configOperatorRoleId(@RequestParam("operatorId") String operatorId,
            @RequestParam(value = "selectedRoleId[]", required = false) String[] selectedRoleId,
            @RequestParam(value = "unSelectedRoleId[]", required = false) String[] unSelectedRoleId,
            @RequestParam() MultiValueMap<String, String> request) {
        List<String> selectRoleIdList = selectedRoleId == null ? new ArrayList<String>() : Arrays.asList(selectedRoleId);
        List<String> unSelectRoleIdList = unSelectedRoleId == null ? new ArrayList<String>() : Arrays.asList(unSelectedRoleId);
        this.operatorRefService.saveOperator2RefIdList(OperatorConstants.OPERATORREF_TYPE_ROLE,
                operatorId,
                selectRoleIdList,
                unSelectRoleIdList);
        return true;
    }
}
