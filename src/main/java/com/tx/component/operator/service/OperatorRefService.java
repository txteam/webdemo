/*
 * 描          述:  <描述>
 * 修  改   人:  brady
 * 修改时间:  2013-11-1
 * <修改描述:>
 */
package com.tx.component.operator.service;

import java.util.List;

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
      * 删除操作员引用
      *<功能详细描述>
      * @param operatorRef [参数说明]
      * 
      * @return void [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public void deleteOperatorRef(OperatorRef operatorRef);
    
    /**
      * 批量删除操作员引用
      *<功能详细描述>
      * @param operatorRefs [参数说明]
      * 
      * @return void [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public void batchDeleteOperatorRef(List<OperatorRef> operatorRefs);
    
    /**
      * 插入操作员的引用实例<br/> 
      *<功能详细描述>
      * @param operatorRef [参数说明]
      * 
      * @return void [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public void insertOperatorRef(OperatorRef operatorRef);
    
    /**
      * 批量插入操作员的引用 
      *<功能详细描述>
      * @param operatorRefs [参数说明]
      * 
      * @return void [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public void batchInsertOperatorRef(List<OperatorRef> operatorRefs);
    
    /**
     * 插入操作员的引用实例<br/> 
     *<功能详细描述>
     * @param operatorRef [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
    public void insertOperatorRefHis(OperatorRef operatorRef);
    
    /**
      * 批量插入操作员的引用 
      *<功能详细描述>
      * @param operatorRefs [参数说明]
      * 
      * @return void [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public void batchInsertOperatorRefHis(List<OperatorRef> operatorRefs);
}
