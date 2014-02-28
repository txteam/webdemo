/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2014年2月23日
 * <修改描述:>
 */
package com.tx.component.operator.controller;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang.ArrayUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tx.component.auth.annotation.CheckOperateAuth;
import com.tx.component.operator.service.Operator2PostService;

/**
 * 配置职位人员Controller层
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2014年2月23日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Controller
@RequestMapping("/Operator2Post")
public class Operator2PostController {
    
    @Resource(name = "operator2PostService")
    private Operator2PostService operator2PostService;
    
    /**
      * 跳转到配置职位对应的人员页面 
      *<功能详细描述>
      * @return [参数说明]
      * 
      * @return String [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/toConfigPostOperator")
    public String toConfigPostOperator(@RequestParam("postId") String postId,
            ModelMap response) {
        response.put("postId", postId);
        return "/operator/configPostOperator";
    }
    
    /**
     * 跳转到配置人员职位页面
     *<功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
    @RequestMapping("/toConfigOperatorPost")
    public String toConfigOperatorPost(
            @RequestParam("operatorId") String operatorId, ModelMap response) {
        response.put("operatorId", operatorId);
        return "/operator/configOperatorPost";
    }
    
    /**
      * 根据职位id查询拥有改职位的操作员id集合 
      *<功能详细描述>
      * @param postId
      * @return [参数说明]
      * 
      * @return Set<String> [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    @Deprecated
    @ResponseBody
    @RequestMapping("/queryOperatorIdSetByPostId")
    public Set<String> queryOperatorIdSetByPostId(
            @RequestParam("postId") String postId) {
        Set<String> operatorIdSet = this.operator2PostService.queryOperatorIdSetByPostId(postId);
        return operatorIdSet;
    }
    
    /**
     * 根据职位id查询拥有改职位的操作员id集合 
     *<功能详细描述>
     * @param postId
     * @return [参数说明]
     * 
     * @return Set<String> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
    @ResponseBody
    @RequestMapping("/queryChoosedOperatorIdSetByPostId")
    public Set<String> queryChoosedOperatorIdSetByPostId(
            @RequestParam("postId") String postId,
            @RequestParam(value = "operatorIds[]", required = false) String[] operatorIds) {
        if (ArrayUtils.isEmpty(operatorIds)) {
            return new HashSet<String>();
        }
        Set<String> operatorIdSet = this.operator2PostService.queryOperatorIdSetByPostId(postId);
        Set<String> choosedOperatorId = new HashSet<String>();
        for (String operatorIdTemp : operatorIds) {
            if (operatorIdSet.contains(operatorIdTemp)) {
                choosedOperatorId.add(operatorIdTemp);
            }
        }
        return choosedOperatorId;
    }
    
    /**
      * 跳转到配置人员职位页面
      *<功能详细描述>
      * @param operatorId
      * @return [参数说明]
      * 
      * @return Set<String> [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/queryPostIdSetByOperatorId")
    public Set<String> queryPostIdSetByOperatorId(
            @RequestParam("operatorId") String operatorId) {
        Set<String> postIdSet = this.operator2PostService.queryPostIdSetByOperatorId(operatorId);
        return postIdSet;
    }
    
    /**
      * 配置职位对应的人员<br/>
      *<功能详细描述>
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
    public boolean configPostOperatorId(
            @RequestParam("postId") String postId,
            @RequestParam(value = "selectedOperatorId[]", required = false) String[] selectedOperatorId,
            @RequestParam(value = "unSelectedOperatorId[]", required = false) String[] unSelectedOperatorId,
            @RequestParam() MultiValueMap<String, String> request) {
        this.operator2PostService.configPostOperator(postId,
                selectedOperatorId,
                unSelectedOperatorId);
        return true;
    }
    
    /**
      * 配置人员职位 
      *<功能详细描述>
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
    public boolean configOperatorPostId(
            @RequestParam("operatorId") String operatorId,
            @RequestParam(value = "selectedPostId[]", required = false) String[] selectedPostId,
            @RequestParam(value = "unSelectedPostId[]", required = false) String[] unSelectedPostId,
            @RequestParam() MultiValueMap<String, String> request) {
        this.operator2PostService.configOperatorPost(operatorId,
                selectedPostId,
                unSelectedPostId);
        return true;
    }
    
}
