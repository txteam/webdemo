/*
 * 描          述:  <描述>
 * 修  改   人:  
 * 修改时间:  
 * <修改描述:>
 */
package com.tx.local.operator.dao;

import java.util.List;

import com.tx.core.mybatis.dao.MybatisBaseDao;
import com.tx.local.operator.model.OperatorRefItem;

/**
 * OperatorRefItem持久层
 * <功能详细描述>
 * 
 * @author  
 * @version [版本号]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public interface OperatorRefItemDao
        extends MybatisBaseDao<OperatorRefItem, String> {
    
    /**
     * 插入OperatorRefItem对象实体
     * 1、auto generate
     * <功能详细描述>
     * @param operatorRef [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public void insertToHis(OperatorRefItem operatorRef);
    
    /**
     * 批量插入OperatorRefItem
     * <功能详细描述>
     * @param operatorRefs [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public void batchInsertToHis(List<OperatorRefItem> operatorRefs);
}
