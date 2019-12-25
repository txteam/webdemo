/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2019年11月26日
 * <修改描述:>
 */
package com.tx.local.operator.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tx.core.querier.model.Filter;
import com.tx.core.querier.model.QuerierBuilder;
import com.tx.local.operator.OperatorConstants;
import com.tx.local.operator.model.CheckAblePost;
import com.tx.local.operator.service.OperatorRefService;
import com.tx.local.organization.facade.PostFacade;
import com.tx.local.organization.model.Post;
import com.tx.local.security.util.WebContextUtils;

/**
 * 权限显示层<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2019年11月26日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Controller("operatorRefController")
@RequestMapping({ "/operator/ref" })
public class OperatorRefController implements InitializingBean {
    
    @Resource
    private OperatorRefService operatorRefService;
    
    @Resource
    private PostFacade postFacade;
    
    /**
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
    }
    
    /**
     * 根据当前人员权限类型与权限列表<br/> 
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<AuthItem> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/queryPostList")
    public List<Post> queryPostList() {
        String vcid = WebContextUtils.getVcid();
        List<Post> resList = postFacade.queryList(true,
                QuerierBuilder.newInstance()
                        .addFilter(Filter.eq("vcid", vcid))
                        .querier());
        return resList;
    }
    
    /**
     * 跳转到配置操作员权限列表页面<br/>
     *<功能详细描述>
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
        String vcid = WebContextUtils.getVcid();
        
        modelMap.put("vcid", vcid);
        modelMap.put("operatorId", operatorId);
        
        return "operator/configOperatorPost";
    }
    
    /**
     * 根据当前人员权限类型与权限列表<br/> 
     *<功能详细描述>
     * @return [参数说明]
     * 
     * @return List<AuthItem> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/queryOperator2PostList")
    public List<CheckAblePost> queryOperator2AuthList(
            @RequestParam("operatorId") String operatorId,
            @RequestParam() MultiValueMap<String, String> request) {
        String vcid = WebContextUtils.getVcid();
        
        //获取可分配的权限，如果为超级管理员，则所有权限都可以进行分配，否则仅能分配自己拥有的权限
        List<CheckAblePost> resList = postFacade
                .queryList(true,
                        QuerierBuilder.newInstance()
                                .addFilter(Filter.eq("vcid", vcid))
                                .querier())
                .stream()
                .map(role -> new CheckAblePost(role))
                .collect(Collectors.toList());
        
        //让拥有的权限被选中
        Set<String> hasPostIdSet = this.operatorRefService
                .queryListByOperatorId(true,
                        operatorId,
                        OperatorConstants.OPERATOR_REF_TYPE_POST,
                        null)
                .stream()
                .map(operRef -> operRef.getRefId())
                .collect(Collectors.toSet());
        resList.stream().forEach(caAuth -> caAuth
                .setChecked(hasPostIdSet.contains(caAuth.getId())));
        
        return resList;
    }
    
    /**
     * 保存角色权限<br/>
     * <功能详细描述>
     * @param roleTypeId
     * @param operatorId
     * @param roleIds
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
    @ResponseBody
    @RequestMapping("/saveOperator2Post")
    public boolean saveOperator2Role(
            @RequestParam("operatorId") String operatorId,
            @RequestParam(value = "postId[]", required = false) String[] postIds,
            @RequestParam() MultiValueMap<String, String> request) {
        List<String> postIdList = null;
        if (postIds == null) {
            postIdList = new ArrayList<String>();
        } else {
            postIdList = Arrays.asList(postIds);
        }
        
        this.operatorRefService.saveForRefIds(operatorId,
                OperatorConstants.OPERATOR_REF_TYPE_POST,
                postIdList,
                null);
        return true;
    }
}
