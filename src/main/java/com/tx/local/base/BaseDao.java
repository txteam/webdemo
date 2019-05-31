package com.tx.local.base;

import com.tx.core.paged.model.PagedList;
import com.tx.local.common.Pageable;

import java.util.List;
import java.util.Map;

public interface BaseDao<T> {
    //实体类的名称
    String entityName();



    /**
     * 批量插入对象实体
     * 1、auto generate
     * <功能详细描述>
     * @param condition [参数说明]
     *
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    //auto generate
    public void batchInsert(List<T> condition);

    /**
     * 批量更新\实体，
     * auto generate
     * 1、传入BankInfo中主键不能为空
     * <功能详细描述>
     * @param updateRowMapList
     * @return [参数说明]
     *
     * @return int [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public void batchUpdate(List<Map<String, Object>> updateRowMapList);

    /**
     * 插入对象实体
     * 1、auto generate
     * <功能详细描述>
     * @param condition [参数说明]
     *
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    //auto generate
    public void insert(T condition);

    /**
     * 删除对象
     * 1、auto generate
     * 2、根据入参条件进行删除
     * <功能详细描述>
     * @param condition [参数说明]
     *
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    //auto generate
    public int delete(T condition);

    /**
     * 查询实体
     * auto generate
     * <功能详细描述>
     * @param condition
     * @return [参数说明]
     *
     * @return T [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    //auto generate
    public T find(T condition);


    /**
     * 根据条件查询列表
     * auto generate
     * <功能详细描述>
     * @param params
     * @return [参数说明]
     *
     * @return List<T> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    //auto generate
    public List<T> queryList(Map<String, Object> params);

    /**
     * 根据条件查询列表总数
     * auto generated
     * <功能详细描述>
     * @param params
     * @return [参数说明]
     *
     * @return int [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public int count(Map<String, Object> params);

    /**
     * 分页查询列表
     * auto generate
     * <功能详细描述>
     * @param params
     * @param pageIndex
     * @param pageSize
     * @return [参数说明]
     *
     * @return PagedList<T> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public PagedList<T> queryPagedList(Map<String, Object> params,
                                              int pageIndex, int pageSize);

    public PagedList<T> queryPagedList(Map<String, Object> params,
                                              Pageable pageable);

    /**
     * 更新实体，
     * auto generate
     * 1、传入中主键不能为空
     * <功能详细描述>
     * @param updateRowMap
     * @return [参数说明]
     *
     * @return int [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public int update(Map<String, Object> updateRowMap);
}
