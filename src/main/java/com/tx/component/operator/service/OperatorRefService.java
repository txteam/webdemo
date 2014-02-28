/*
 * 描          述:  <描述>
 * 修  改   人:  brady
 * 修改时间:  2013-11-1
 * <修改描述:>
 */
package com.tx.component.operator.service;

import java.util.Date;
import java.util.List;
import java.util.Set;

import com.tx.component.operator.model.OperatorRef;

/**
 * 操作员引用业务层
 *     负责处理操作员引用变更及配置的相关业务逻辑<br/>
 *     如：操作员所属职位
 *         操作员所属分组
 *         等业务逻辑
 * <功能详细描述>
 * 
 * @author  brady
 * @version  [版本号, 2013-11-1]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface OperatorRefService {
    
    /**
      * 根据refId以及refType查询对应的operator的id集合
      *<功能详细描述>
      * @param refType
      * @param refId
      * @return [参数说明]
      * 
      * @return Set<String> [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public Set<String> queryOperatorIdSetByRefId(String refType, String refId);
    
    /**
     * 根据operatorId以及refType查询对应的operator的id集合
     *<功能详细描述>
     * @param refType
     * @param operatorId
     * @return [参数说明]
     * 
     * @return Set<String> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
    public Set<String> queryRefIdSetByOperatorId(String refType,
            String operatorId);
    
    /**
      * 保存操作员和操作员引用列表间的关系
      *     自动根据原存在关系分析出哪些是增加的，哪些是减少的权限<br/>
      *     一般用于配置人员所属分组
      *     或人员所属职位等功能
      *<功能详细描述>
      * @param refType
      * @param operatorId
      * @param addRefIdList
      * @param deleteRefIdList
      * @param effectiveDate
      * @param invalidDate [参数说明]
      * 
      * @return void [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public void saveOperator2RefIdList(String refType, String operatorId,
            List<String> addRefIdList, List<String> deleteRefIdList,
            Date effectiveDate, Date invalidDate);
    
    /**
      * 保存操作员和操作员引用列表间的关系
      *     自动根据原存在关系分析出哪些是增加的，哪些是减少的权限<br/>
      *     一般用于配置人员所属分组
      *     或人员所属职位等功能
      *<功能详细描述>
      * @param refType
      * @param operatorId
      * @param addRefIdList
      * @param deleteRefIdList [参数说明]
      * 
      * @return void [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public void saveOperator2RefIdList(String refType, String operatorId,
            List<String> addRefIdList, List<String> deleteRefIdList);
    
    /**
      * 保存引用id,引用类型对操作员的引用关系
      *     一般用于处理配置职位对应的人员
      *     或分组对应的人员等功能
      *<功能详细描述>
      * @param refType
      * @param refId
      * @param addOperatorIdList
      * @param deleteOperatorIdList
      * @param effectiveDate
      * @param invalidDate [参数说明]
      * 
      * @return void [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public void saveRefId2OperatorIdList(String refType, String refId,
            List<String> addOperatorIdList, List<String> deleteOperatorIdList,
            Date effectiveDate, Date invalidDate);
    
    /**
      * 保存引用id,引用类型对操作员的引用关系
      *     一般用于处理配置职位对应的人员
      *     或分组对应的人员等功能
      *<功能详细描述>
      * @param refType
      * @param refId
      * @param addOperatorIdList
      * @param deleteOperatorIdList [参数说明]
      * 
      * @return void [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public void saveRefId2OperatorIdList(String refType, String refId,
            List<String> addOperatorIdList, List<String> deleteOperatorIdList);
    
    /**
     * 保存操作员和操作员引用列表间的关系
     *     自动根据原存在关系分析出哪些是增加的，哪些是减少的权限<br/>
     *     一般用于配置人员所属分组
     *     或人员所属职位等功能
      *<功能详细描述>
      * @param refType
      * @param operatorId
      * @param refIdList
      * @param effectiveDate
      * @param invalidDate [参数说明]
      * 
      * @return void [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public void saveOperator2RefIdList(String refType, String operatorId,
            List<String> refIdList, Date effectiveDate, Date invalidDate);
    
    /**
      * 保存操作员和操作员引用列表间的关系
      *     自动根据原存在关系分析出哪些是增加的，哪些是减少的权限<br/>
      *     一般用于配置人员所属分组
      *     或人员所属职位等功能
      *<功能详细描述>
      * @param refType
      * @param operatorId
      * @param refIdList [参数说明]
      * 
      * @return void [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public void saveOperator2RefIdList(String refType, String operatorId,
            List<String> refIdList);
    
    /**
     * 保存引用id,引用类型对操作员的引用关系
     *     一般用于处理配置职位对应的人员
     *     或分组对应的人员等功能
      *<功能详细描述>
      * @param refType
      * @param refId
      * @param operatorIdList
      * @param effectiveDate
      * @param invalidDate [参数说明]
      * 
      * @return void [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public void saveRefId2OperatorIdList(String refType, String refId,
            List<String> operatorIdList, Date effectiveDate, Date invalidDate);
    
    /**
      * 保存引用id,引用类型对操作员的引用关系
      *     一般用于处理配置职位对应的人员
      *     或分组对应的人员等功能
      *<功能详细描述>
      * @param refType
      * @param refId
      * @param operatorIdList [参数说明]
      * 
      * @return void [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public void saveRefId2OperatorIdList(String refType, String refId,
            List<String> operatorIdList);
    
    /**
      * 删除操作员引用
      *<功能详细描述>
      * @param operatorRef [参数说明]
      * 
      * @return void [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public void deleteByOperatorId(String operatorId);
    
    /** 
      * 根据引用id删除操作员引用
      *<功能详细描述>
      * @param refId [参数说明]
      * 
      * @return void [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public void deleteByRefId(String refId);
    
    /**
     * 将operatorRef实例插入数据库中保存
     * 1、如果operatorRef为空时抛出参数为空异常
     * 2、如果operatorRef中部分必要参数为非法值时抛出参数不合法异常
    * <功能详细描述>
    * @param district [参数说明]
    * 
    * @return void [返回类型说明]
    * @exception throws
    * @see [类、类#方法、类#成员]
    */
    public void insertOperatorRef(OperatorRef operatorRef);
    
    /**
      * 为操作员引用历史插入值 <br/>
      *<功能详细描述>
      * @param operatorRef [参数说明]
      * 
      * @return void [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public void insertOperatorRefHis(OperatorRef operatorRef);
}
