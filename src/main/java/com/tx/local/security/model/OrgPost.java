/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2019年11月28日
 * <修改描述:>
 */
package com.tx.local.security.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import com.tx.core.exceptions.util.AssertUtils;
import com.tx.local.organization.model.Organization;
import com.tx.local.organization.model.Post;

/**
 * 组织职位树节点<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2019年11月28日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class OrgPost {
    
    private Organization organization;
    
    private Post parent;
    
    private Post post;
    
    private NodeTypeEnum type;
    
    /** <默认构造函数> */
    public OrgPost(Organization organization) {
        super();
        AssertUtils.notNull(organization, "organization is null.");
        this.organization = organization;
        this.type = NodeTypeEnum.organization;
    }
    
    /** <默认构造函数> */
    public OrgPost(Post post, Post parent) {
        super();
        AssertUtils.notNull(post, "post is null.");
        this.post = post;
        this.parent = parent;
        this.type = NodeTypeEnum.post;
    }
    
    /**
     * 获取id
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public String getId() {
        switch (type) {
            case organization:
                return this.organization.getId();
            default:
                return this.post.getId();
        }
    }
    
    /**
     * 获取父级节点id<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public String getParentId() {
        switch (type) {
            case organization:
                return this.organization.getParentId();
            default: {
                String parentId = this.post.getParentId();
                if (this.parent == null) {
                    parentId = this.post.getOrganizationId();
                } else if (this.parent.getOrganizationId()
                        .equals(this.post.getOrganizationId())) {
                    parentId = this.post.getParentId();
                } else {
                    parentId = this.post.getOrganizationId();
                }
                return parentId;
            }
        }
    }
    
    /**
     * 名称<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public String getName() {
        switch (type) {
            case organization:
                return this.organization.getName();
            default:
                return this.post.getName();
        }
    }
    
    /**
     * 组织id<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public String getOrganizationId() {
        switch (type) {
            case organization:
                return this.organization.getId();
            default:
                return this.post.getOrganizationId();
        }
    }
    
    /**
     * 组织id<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public String getOrganization() {
        switch (type) {
            case organization:
                return this.organization.getId();
            default:
                return this.post.getOrganizationId();
        }
    }
    
    /**
     * 职位id<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public String getPostId() {
        switch (type) {
            case organization:
                return null;
            default:
                return this.post.getId();
        }
    }
    
    /**
     * 变换<br/>
     * <功能详细描述>
     * @param orgList
     * @param postList
     * @return [参数说明]
     * 
     * @return List<OrganizationPostNode> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public static List<OrgPost> transform(List<Organization> orgList,
            List<Post> postList) {
        if (orgList == null) {
            orgList = new ArrayList<Organization>();
        }
        if (postList == null) {
            orgList = new ArrayList<Organization>();
        }
        List<OrgPost> resList = new ArrayList<>();
        
        Map<String, Post> postMap = new HashMap<String, Post>();
        postList.stream().forEach(post -> postMap.put(post.getId(), post));
        
        resList.addAll(orgList.stream()
                .map(org -> new OrgPost(org))
                .collect(Collectors.toList()));
        resList.addAll(postList.stream()
                .map(post -> new OrgPost(post,
                        StringUtils.isEmpty(post.getParentId()) ? null
                                : postMap.get(post.getParentId())))
                .collect(Collectors.toList()));
        return resList;
    }
    
    /**
     * @return 返回 type
     */
    public NodeTypeEnum getType() {
        return type;
    }
    
    /**
     * @param 对type进行赋值
     */
    public void setType(NodeTypeEnum type) {
        this.type = type;
    }
    
    /**
     * 节点类型<br/>
     * <功能详细描述>
     * 
     * @author  Administrator
     * @version  [版本号, 2019年11月28日]
     * @see  [相关类/方法]
     * @since  [产品/模块版本]
     */
    private static enum NodeTypeEnum {
        organization,
        
        post;
    }
}
