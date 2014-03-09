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

import com.tx.component.operator.dao.PostTypeDao;
import com.tx.component.operator.model.PostType;
import com.tx.core.mybatis.model.Order;
import com.tx.core.mybatis.support.MyBatisDaoSupport;
import com.tx.core.paged.model.PagedList;

/**
 * PostType持久层
 * <功能详细描述>
 * 
 * @author  
 * @version  [版本号, 2012-12-11]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Component("postTypeDao")
public class PostTypeDaoImpl implements PostTypeDao {
    
    @Resource(name = "myBatisDaoSupport")
    private MyBatisDaoSupport myBatisDaoSupport;
    
    /**
     * @param condition
     */
    @Override
    public void insertPostType(PostType condition) {
        this.myBatisDaoSupport.insertUseUUID("postType.insertPostType", condition, "id");
    }
    
    /**
     * @param condition
     * @return
     */
    @Override
    public int deletePostType(PostType condition) {
        return this.myBatisDaoSupport.delete("postType.deletePostType", condition);
    }
    
    /**
     * @param condition
     * @return
     */
    @Override
    public PostType findPostType(PostType condition) {
        return this.myBatisDaoSupport.<PostType> find("postType.findPostType", condition);
    }
    
    /**
     * @param params
     * @return
     */
    @Override
    public List<PostType> queryPostTypeList(Map<String, Object> params) {
        return this.myBatisDaoSupport.<PostType> queryList("postType.queryPostType",
                params);
    }
    
    /**
     * @param params
     * @param orderList
     * @return
     */
    @Override
    public List<PostType> queryPostTypeList(Map<String, Object> params,
            List<Order> orderList) {
        return this.myBatisDaoSupport.<PostType> queryList("postType.queryPostType",
                params,
                orderList);
    }
    
    /**
     * @param params
     * @return
     */
    @Override
    public int countPostType(Map<String, Object> params) {
        return this.myBatisDaoSupport.<Integer> find("postType.queryPostTypeCount",
                params);
    }
    
    /**
     * @param params
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @Override
    public PagedList<PostType> queryPostTypePagedList(Map<String, Object> params,
            int pageIndex, int pageSize) {
        return this.myBatisDaoSupport.<PostType> queryPagedList("postType.queryPostType",
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
    public PagedList<PostType> queryPostTypePagedList(Map<String, Object> params,
            int pageIndex, int pageSize, List<Order> orderList) {
        return this.myBatisDaoSupport.<PostType> queryPagedList("postType.queryPostType",
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
    public int updatePostType(Map<String, Object> updateRowMap) {
        return this.myBatisDaoSupport.update("postType.updatePostType", updateRowMap);
    }
}
