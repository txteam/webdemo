/*
 * 描          述:  <描述>
 * 修  改   人:  brady
 * 修改时间:  2013-9-10
 * <修改描述:>
 */
package com.tx.component.operator.treeview;

import org.apache.commons.lang.StringUtils;

import com.tx.component.operator.model.Organization;
import com.tx.component.operator.model.Post;

/**
 * 组织职位树节点
 * <功能详细描述>
 * 
 * @author  brady
 * @version  [版本号, 2013-9-10]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class OrganizationPostTreeNode {
    
    /** 组织职位树 节点类型：组织 */
    public static final int TYPE_ORGANIZATION = 0;
    
    /** 组织职位树 节点类型：职位 */
    public static final int TYPE_POST = 1;
    
    /** 组织、职位id */
    private String id;
    
    /** 
     * 组织的parentId为组织id
     * 职位的parentId，如果职位存在上级职位，则为上级职位的parentId
     * 如果不存在上级职位，则为组织的id
     */
    private String parentId;
    
    /**
     * 职位、组织的名字
     */
    private String name;
    
    /**
     * 组织职位节点<br/>
     */
    private int type;
    
    /** <默认构造函数> */
    public OrganizationPostTreeNode(Organization org) {
        this.id = org.getId();
        this.parentId = org.getParentId();
        this.name = org.getName();
        this.type = TYPE_ORGANIZATION;
    }
    
    /** <默认构造函数> */
    public OrganizationPostTreeNode(Post post) {
        this.id = post.getId();
        this.parentId = StringUtils.isEmpty(post.getParentId()) ? post.getOrganization()
                .getId()
                : post.getParentId();
        this.name = post.getName();
        this.type = TYPE_POST;
    }

    /**
     * @return 返回 id
     */
    public String getId() {
        return id;
    }

    /**
     * @param 对id进行赋值
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return 返回 parentId
     */
    public String getParentId() {
        return parentId;
    }

    /**
     * @param 对parentId进行赋值
     */
    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    /**
     * @return 返回 name
     */
    public String getName() {
        return name;
    }

    /**
     * @param 对name进行赋值
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return 返回 type
     */
    public int getType() {
        return type;
    }

    /**
     * @param 对type进行赋值
     */
    public void setType(int type) {
        this.type = type;
    }
}
