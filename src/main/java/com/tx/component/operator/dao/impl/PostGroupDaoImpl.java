/*
 * 描          述:  <描述>
 * 修  改   人:  
 * 修改时间:  
 * <修改描述:>
 */
package com.tx.component.operator.dao.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.tx.component.operator.dao.PostGroupDao;
import com.tx.component.operator.model.PostGroup;
import com.tx.core.mybatis.model.Order;
import com.tx.core.mybatis.support.MyBatisDaoSupport;
import com.tx.core.paged.model.PagedList;

/**
 * PostGroup持久层
 * <功能详细描述>
 * 
 * @author  
 * @version  [版本号, 2012-12-11]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Component("postGroupDao")
public class PostGroupDaoImpl implements PostGroupDao {
    
    @Resource(name = "myBatisDaoSupport")
    private MyBatisDaoSupport myBatisDaoSupport;
    
    /**
     * @param condition
     */
    @Override
    public void insertPostGroup(PostGroup condition) {
        this.myBatisDaoSupport.insertUseUUID("postGroup.insertPostGroup", condition, "id");
    }
    
    /**
     * @param condition
     * @return
     */
    @Override
    public int deletePostGroup(PostGroup condition) {
        return this.myBatisDaoSupport.delete("postGroup.deletePostGroup", condition);
    }
    
    /**
     * @param condition
     * @return
     */
    @Override
    public PostGroup findPostGroup(PostGroup condition) {
        return this.myBatisDaoSupport.<PostGroup> find("postGroup.findPostGroup", condition);
    }
    
    /**
     * @param params
     * @return
     */
    @Override
    public List<PostGroup> queryPostGroupList(Map<String, Object> params) {
        return this.myBatisDaoSupport.<PostGroup> queryList("postGroup.queryPostGroup",
                params);
    }
    
    /**
     * @param params
     * @param orderList
     * @return
     */
    @Override
    public List<PostGroup> queryPostGroupList(Map<String, Object> params,
            List<Order> orderList) {
        return this.myBatisDaoSupport.<PostGroup> queryList("postGroup.queryPostGroup",
                params,
                orderList);
    }
    
    /**
     * @param params
     * @return
     */
    @Override
    public int countPostGroup(Map<String, Object> params) {
        return this.myBatisDaoSupport.<Integer> find("postGroup.queryPostGroupCount",
                params);
    }
    
    /**
     * @param params
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @Override
    public PagedList<PostGroup> queryPostGroupPagedList(Map<String, Object> params,
            int pageIndex, int pageSize) {
        return this.myBatisDaoSupport.<PostGroup> queryPagedList("postGroup.queryPostGroup",
                params,
                pageIndex,
                pageSize);
    }
    
    /**
     * @param params
     * @param pageIndex
     * @param pageSize
     * @param orderList
     * @return
     */
    @Override
    public PagedList<PostGroup> queryPostGroupPagedList(Map<String, Object> params,
            int pageIndex, int pageSize, List<Order> orderList) {
        return this.myBatisDaoSupport.<PostGroup> queryPagedList("postGroup.queryPostGroup",
                params,
                pageIndex,
                pageSize,
                orderList);
    }
    
    /**
     * @param updateRowMap
     * @return
     */
    @Override
    public int updatePostGroup(Map<String, Object> updateRowMap) {
        return this.myBatisDaoSupport.update("postGroup.updatePostGroup", updateRowMap);
    }
}
