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

import com.tx.component.operator.dao.PostDao;
import com.tx.component.operator.model.Post;
import com.tx.core.mybatis.model.Order;
import com.tx.core.mybatis.support.MyBatisDaoSupport;
import com.tx.core.paged.model.PagedList;

/**
 * Post持久层
 * <功能详细描述>
 * 
 * @author  
 * @version  [版本号, 2012-12-11]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Component("postDao")
public class PostDaoImpl implements PostDao {
    
    @Resource(name = "myBatisDaoSupport")
    private MyBatisDaoSupport myBatisDaoSupport;
    
    /**
     * @param condition
     */
    @Override
    public void insertPost(Post condition) {
        this.myBatisDaoSupport.insertUseUUID("newPost.insertPost", condition, "id");
    }
    
    /**
     * @param condition
     */
    @Override
    public void insertPostToHis(Post condition) {
        this.myBatisDaoSupport.insert("newPost.insertPostToHis", condition);
    }



    /**
     * @param condition
     * @return
     */
    @Override
    public int deletePost(Post condition) {
        return this.myBatisDaoSupport.delete("newPost.deletePost", condition);
    }
    
    /**
     * @param condition
     * @return
     */
    @Override
    public Post findPost(Post condition) {
        return this.myBatisDaoSupport.<Post> find("newPost.findPost", condition);
    }
    
    /**
     * @param params
     * @return
     */
    @Override
    public List<Post> queryPostList(Map<String, Object> params) {
        return this.myBatisDaoSupport.<Post> queryList("newPost.queryPost",
                params);
    }
    
    /**
     * @param params
     * @param orderList
     * @return
     */
    @Override
    public List<Post> queryPostList(Map<String, Object> params,
            List<Order> orderList) {
        return this.myBatisDaoSupport.<Post> queryList("newPost.queryPost",
                params,
                orderList);
    }
    
    /**
     * @param params
     * @return
     */
    @Override
    public int countPost(Map<String, Object> params) {
        return this.myBatisDaoSupport.<Integer> find("newPost.queryPostCount",
                params);
    }
    
    /**
     * @param params
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @Override
    public PagedList<Post> queryPostPagedList(Map<String, Object> params,
            int pageIndex, int pageSize) {
        return this.myBatisDaoSupport.<Post> queryPagedList("newPost.queryPost",
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
    public PagedList<Post> queryPostPagedList(Map<String, Object> params,
            int pageIndex, int pageSize, List<Order> orderList) {
        return this.myBatisDaoSupport.<Post> queryPagedList("newPost.queryPost",
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
    public int updatePost(Map<String, Object> updateRowMap) {
        return this.myBatisDaoSupport.update("newPost.updatePost", updateRowMap);
    }
}
