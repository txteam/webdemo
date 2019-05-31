package com.tx.local.base;

import com.tx.core.mybatis.support.MyBatisDaoSupport;
import com.tx.core.paged.model.PagedList;
import com.tx.local.common.Pageable;
import org.joda.time.base.BaseDateTime;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

public abstract class  BaseDaoImpl<T>  implements BaseDao<T> {
     @Resource(name = "myBatisDaoSupport")
    private MyBatisDaoSupport myBatisDaoSupport;



     private String getStatement(String key){
         String firstUpperCaseEntityName = entityName().substring(0, 1).toUpperCase() + entityName().substring(1);
         return entityName()+"."+key +firstUpperCaseEntityName;
     }

    /**
     * @param condition
     */
    @Override
    public void batchInsert(List<T> condition) {
        this.myBatisDaoSupport.batchInsertUseUUID(getStatement("insert"),
                condition,
                "id",
                true);
    }

    /**
     * @param updateRowMapList
     */
    @Override
    public void batchUpdate(List<Map<String, Object>> updateRowMapList) {
        this.myBatisDaoSupport.batchUpdate(getStatement("update"),
                updateRowMapList,
                true);
    }

    /**
     * @param condition
     */
    @Override
    public void insert(T condition) {
        this.myBatisDaoSupport.insertUseUUID(getStatement("insert"),
                condition,
                "id");
    }

    /**
     * @param condition
     * @return
     */
    @Override
    public int delete(T condition) {
        return this.myBatisDaoSupport.delete(getStatement("delete"),
                condition);
    }

    /**
     * @param condition
     * @return
     */
    @Override
    public T find(T condition) {
        return this.myBatisDaoSupport.<T> find(getStatement("find"),
                condition);
    }

    /**
     * @param params
     * @return
     */
    @Override
    public List<T> queryList(Map<String, Object> params) {
        return this.myBatisDaoSupport
                .<T> queryList(getStatement("query"), params);
    }

    /**
     * @param params
     * @return
     */
    @Override
    public int count(Map<String, Object> params) {
        return this.myBatisDaoSupport
                .<Integer> find(getStatement("query")+"Count", params);
    }

    /**
     * @param params
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @Override
    public PagedList<T> queryPagedList(Map<String, Object> params,
                                              int pageIndex, int pageSize) {
        return this.myBatisDaoSupport.<T> queryPagedList(
                getStatement("query"), params, pageIndex, pageSize);
    }

    @Override
    public PagedList<T> queryPagedList(Map<String, Object> params, Pageable pageable) {
        return this.myBatisDaoSupport.<T> queryPagedList(
                getStatement("query"), params, pageable.getPageNumber(), pageable.getPageSize());
    }

    /**
     * @param updateRowMap
     * @return
     */
    @Override
    public int update(Map<String, Object> updateRowMap) {
        return this.myBatisDaoSupport.update(getStatement("update"),
                updateRowMap);
    }


}
