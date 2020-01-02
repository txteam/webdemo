/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2019年11月26日
 * <修改描述:>
 */
package com.tx.local.security.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tx.core.querier.model.QuerierBuilder;
import com.tx.local.operator.model.Operator;
import com.tx.local.operator.service.OperatorRefService;
import com.tx.local.operator.service.OperatorService;
import com.tx.local.organization.facade.OrganizationFacade;
import com.tx.local.organization.facade.PostFacade;
import com.tx.local.security.util.WebContextUtils;

/**
 * 人员对职位控制层<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2019年11月26日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Controller("operator2PostController")
@RequestMapping({ "/operator/post" })
public class Operator2PostController implements InitializingBean {
    
    @Resource
    private OperatorService operatorService;
    
    @Resource
    private OrganizationFacade organizationFacade;
    
    @Resource
    private PostFacade postFacade;
    
    @Resource
    private OperatorRefService operatorRefService;
    
    /**
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
    }
    
    /**
     * 跳转到配置操作员职位列表页面<br/>
     * <功能详细描述>
     * @param modelMap
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/toConfigOperatorPost")
    public String toConfigOperatorPost(
            @RequestParam("operatorId") String operatorId, ModelMap modelMap) {
        modelMap.put("operatorId", operatorId);
        
        return "security/configOperatorPost";
    }
    
    /**
     * 查询当前人员拥有的职位id集合<br/> 
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<AuthItem> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/queryOperator2PostIds")
    public Set<String> queryOperator2PostIds(
            @RequestParam("operatorId") String operatorId,
            @RequestParam() MultiValueMap<String, String> request) {
        Operator o = this.operatorService.findById(operatorId);
        Set<String> postIds = new HashSet<>();
        postIds.add(o.getMainPostId());
        return postIds;
    }
    
    /**
     * 保存人员职位<br/>
     * <功能详细描述>
     * @param operatorId
     * @param postIds
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
    @ResponseBody
    @RequestMapping("/saveOperator2Post")
    public boolean saveOperator2Post(
            @RequestParam("operatorId") String operatorId,
            @RequestParam(value = "postId[]", required = false) String[] postIds,
            @RequestParam() MultiValueMap<String, String> request) {
        List<String> postIdList = null;
        if (postIds == null) {
            postIdList = new ArrayList<String>();
        } else {
            postIdList = Arrays.asList(postIds);
        }
        this.operatorService.updateMainPostById(operatorId,
                postIdList.size() == 0 ? null : postIdList.get(0));
        return true;
    }
    
    /**
     * 跳转到配置职位人员页面<br/>
     * <功能详细描述>
     * @param response
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
        String vcid = WebContextUtils.getVcid();
        response.put("organizations",
                this.organizationFacade.queryList(true,
                        QuerierBuilder.newInstance()
                                .searchProperty("vcid", vcid)
                                .querier()));
        response.put("posts",
                this.postFacade.queryList(true,
                        QuerierBuilder.newInstance()
                                .searchProperty("vcid", vcid)
                                .querier()));
        return "security/configPostOperator";
    }
    
    /**
     * 查询职位UI应的用户id集合<br/> 
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<AuthItem> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/queryPost2OperatorIds")
    public Set<String> queryPost2OperatorIds(
            @RequestParam("postId") String postId,
            @RequestParam(value = "operatorId[]", required = false) String[] operatorIds,
            @RequestParam() MultiValueMap<String, String> request) {
        if (ArrayUtils.isEmpty(operatorIds)) {
            return new HashSet<>();
        }
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("mainPostId", postId);
        params.put("ids", operatorIds);
        Set<String> operatorIdSet = this.operatorService.queryList(null, params)
                .stream()
                .map(postRef -> postRef.getId())
                .collect(Collectors.toSet());
        return operatorIdSet;
    }
    
    /**
     * 保存职位人员<br/>
     * <功能详细描述>
     * @param postId
     * @param operatorIds
     * @param filterOperatorIds
     * @param request
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/savePost2Operator")
    public boolean savePost2Operator(@RequestParam("postId") String postId,
            @RequestParam(value = "operatorId[]", required = false) String[] operatorIds,
            @RequestParam(value = "filterOperatorId[]", required = false) String[] filterOperatorIds,
            @RequestParam() MultiValueMap<String, String> request) {
        List<String> refIds = ArrayUtils.isEmpty(operatorIds)
                ? new ArrayList<>() : Arrays.asList(operatorIds);
        List<String> filterRefIds = ArrayUtils.isEmpty(filterOperatorIds)
                ? new ArrayList<>() : Arrays.asList(filterOperatorIds);
        this.operatorService.savePost2Operators(postId, refIds, filterRefIds);
        return true;
    }
}
