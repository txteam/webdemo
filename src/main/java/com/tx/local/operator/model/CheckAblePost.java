/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2019年11月26日
 * <修改描述:>
 */
package com.tx.local.operator.model;

import java.io.Serializable;

import com.tx.local.organization.model.Post;

/**
 * 职位是否选中<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2019年11月26日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class CheckAblePost implements Serializable{
    
    /** 注释内容 */
    private static final long serialVersionUID = -1652451513714200381L;

    /** 权限项 */
    protected Post post;
    
    /** 是否选中 */
    private boolean checked = false;
    
    /** <默认构造函数> */
    public CheckAblePost() {
        super();
    }
    
    /** <默认构造函数> */
    public CheckAblePost(Post post) {
        super();
        this.post = post;
    }
    
    /**
     * @return
     */
    public String getId() {
        return this.post.getId();
    }
    
    /**
     * @return
     */
    public String getParentId() {
        return this.post.getParentId();
    }
    
    /**
     * @return
     */
    public String getName() {
        return this.post.getName();
    }
    
    /**
     * @return
     */
    public String getRemark() {
        return this.post.getRemark();
    }
    
    /**
     * @return 返回 checked
     */
    public boolean isChecked() {
        return checked;
    }
    
    /**
     * @param 对checked进行赋值
     */
    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    /**
     * @return 返回 post
     */
    public Post getPost() {
        return post;
    }

    /**
     * @param 对post进行赋值
     */
    public void setPost(Post post) {
        this.post = post;
    }
    
    
    
}
