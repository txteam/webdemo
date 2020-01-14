/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2020年1月14日
 * <修改描述:>
 */
package com.tx.local.creditinfo.service;

import java.util.List;

import com.tx.core.querier.model.Querier;
import com.tx.local.creditinfo.context.AbstractCreditInfo;

/**
 * 1:n的信用信息业务层<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2020年1月14日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface MultipCreditInfoService<T extends AbstractCreditInfo> {
    
    /**
     * 实体业务层对应的信用信息实体类型<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return T [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    Class<T> type();
    
    /**
     * 查询信用信息<br/>
     * <功能详细描述>
     * @param creditInfoId
     * @param version
     * @param querier
     * @return [参数说明]
     * 
     * @return List<T> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    List<T> queryFromBranch(String creditInfoId, int version, Querier querier);
    
    /**
     * 查询所有版本<br/>
     * <功能详细描述>
     * @param creditInfoId
     * @return [参数说明]
     * 
     * @return List<Integer> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    List<Integer> listVersionsFromBranch(String creditInfoId);
    
//    /**
//     * 保存信用信息<br/>
//     * <功能详细描述>
//     * @param infos [参数说明]
//     * 
//     * @return void [返回类型说明]
//     * @exception throws [异常类型] [异常说明]
//     * @see [类、类#方法、类#成员]
//     */
//    void tag(List<T> infos);
    
    /**
     * 查询所有版本<br/>
     * <功能详细描述>
     * @param creditInfoId
     * @return [参数说明]
     * 
     * @return List<Integer> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    List<Integer> listVersionsFromTag(String creditInfoId);
    
    /**
     * 查询信用信息<br/>
     * <功能详细描述>
     * @param creditInfoId
     * @param version
     * @param querier
     * @return [参数说明]
     * 
     * @return List<T> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    List<T> queryFromTag(String creditInfoId, int version, Querier querier);
}
