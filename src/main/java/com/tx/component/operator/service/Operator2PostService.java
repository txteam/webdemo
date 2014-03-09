/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2014年2月19日
 * <修改描述:>
 */
package com.tx.component.operator.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import com.tx.component.operator.OperatorConstants;

/**
 * 操作员职位业务逻辑类
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2014年2月19日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Component("operator2PostService")
public class Operator2PostService {
    
    @Resource(name = "operatorRefService")
    private OperatorRefService operatorRefService;
    
    /**
      * 配置职位人员<br/>
      *<功能详细描述>
      * @param postId
      * @param selectOperatorIds
      * @param unSelectOperatorIds [参数说明]
      * 
      * @return void [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    @CacheEvict(value = "operator2PostService", allEntries = true)
    public void configPostOperator(String postId, String[] selectOperatorIds,
            String[] unSelectOperatorIds) {
        List<String> selectOperatorIdList = selectOperatorIds == null ? new ArrayList<String>()
                : Arrays.asList(selectOperatorIds);
        List<String> unSelectOperatorIdList = unSelectOperatorIds == null ? new ArrayList<String>()
                : Arrays.asList(unSelectOperatorIds);
        this.operatorRefService.saveRefId2OperatorIdList(OperatorConstants.OPERATORREF_TYPE_POST,
                postId,
                selectOperatorIdList,
                unSelectOperatorIdList);
    }
    
    /**
     * 配置人员拥有的职位
      *<功能详细描述>
      * @param operatorId
      * @param selectPostIds
      * @param unSelectPostIds [参数说明]
      * 
      * @return void [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    @CacheEvict(value = "operator2PostService", allEntries = true)
    public void configOperatorPost(String operatorId, String[] selectPostIds,
            String[] unSelectPostIds) {
        List<String> selectPostIdList = selectPostIds == null ? new ArrayList<String>()
                : Arrays.asList(selectPostIds);
        List<String> unSelectPostIdList = unSelectPostIds == null ? new ArrayList<String>()
                : Arrays.asList(unSelectPostIds);
        this.operatorRefService.saveOperator2RefIdList(OperatorConstants.OPERATORREF_TYPE_POST,
                operatorId,
                selectPostIdList,
                unSelectPostIdList);
    }
    
    /**
      * 获取拥有指定职位id的人员id集合
      * DO:!!!人员本应不如此设计，不过在目前看来，人员暂时不会引起这里一次查询的人数使得系统不能负载的可能性
      *             暂时如此设计即可
      *<功能详细描述>
      * @param postId
      * @return [参数说明]
      * 
      * @return Set<String> [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    @Cacheable(value = "operator2PostService", key = "'queryOperatorIdSet_' + #postId")
    public Set<String> queryOperatorIdSetByPostId(String postId) {
        Set<String> operatorIdSet = this.operatorRefService.queryOperatorIdSetByRefId(OperatorConstants.OPERATORREF_TYPE_POST,
                postId);
        return operatorIdSet;
    }
    
    /**
      * 获取对应人员拥有的职位id集合
      *<功能详细描述>
      * @param operatorId
      * @return [参数说明]
      * 
      * @return Set<String> [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    @Cacheable(value = "operator2PostService", key = "'queryPostIdSet_' + #operatorId")
    public Set<String> queryPostIdSetByOperatorId(String operatorId) {
        Set<String> postIdSet = this.operatorRefService.queryRefIdSetByOperatorId(OperatorConstants.OPERATORREF_TYPE_POST,
                operatorId);
        return postIdSet;
    }
    
}
