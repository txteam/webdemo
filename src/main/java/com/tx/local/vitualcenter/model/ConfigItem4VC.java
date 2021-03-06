/*
 * 描          述:  <描述>
 * 修  改   人:  PengQingyang
 * 修改时间:  2020年4月20日
 * <修改描述:>
 */
package com.tx.local.vitualcenter.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 虚中心设置<br/>
 * <功能详细描述>
 * 
 * @author  PengQingyang
 * @version  [版本号, 2020年4月20日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Entity
@Table(name = "vc_config_context")
public class ConfigItem4VC {
    
    /** 虚中心配置项id */
    @Id
    private String id;
    
    @Column(nullable = false, updatable = false, length = 64)
    private String vcid;
    
    /** 数据库存储唯一键 */
    @Column(nullable = true, updatable = true, length = 64)
    private String parentId;
    
    /** 配置属性key */
    @Column(nullable = false, updatable = false, length = 64)
    private String code;
    
    /** 配置属性名 */
    @Column(nullable = false, updatable = true, length = 64)
    private String name;
    
    /** 配置属性的值 */
    @Column(nullable = false, updatable = true, length = 64)
    private String value;
    
    /** 配置属性描述 */
    @Column(nullable = true, updatable = true, length = 500)
    private String remark;
    
    /** 是否可编辑 */
    private boolean modifyAble;
    
    /** 是否叶节点:设置为true表明本节点没有子节点 */
    private boolean leaf;
    
    /** 创建时间 */
    @Column(nullable = false, updatable = false)
    private Date createDate;
    
    /** 最后更新时间 */
    @Column(nullable = false, updatable = true)
    private Date lastUpdateDate;
    
    /** 子配置项 */
    private List<ConfigItem4VC> children;

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
     * @return 返回 vcid
     */
    public String getVcid() {
        return vcid;
    }

    /**
     * @param 对vcid进行赋值
     */
    public void setVcid(String vcid) {
        this.vcid = vcid;
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
     * @return 返回 code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param 对code进行赋值
     */
    public void setCode(String code) {
        this.code = code;
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
     * @return 返回 value
     */
    public String getValue() {
        return value;
    }

    /**
     * @param 对value进行赋值
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * @return 返回 remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * @param 对remark进行赋值
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * @return 返回 modifyAble
     */
    public boolean isModifyAble() {
        return modifyAble;
    }

    /**
     * @param 对modifyAble进行赋值
     */
    public void setModifyAble(boolean modifyAble) {
        this.modifyAble = modifyAble;
    }

    /**
     * @return 返回 leaf
     */
    public boolean isLeaf() {
        return leaf;
    }

    /**
     * @param 对leaf进行赋值
     */
    public void setLeaf(boolean leaf) {
        this.leaf = leaf;
    }

    /**
     * @return 返回 createDate
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * @param 对createDate进行赋值
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * @return 返回 lastUpdateDate
     */
    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    /**
     * @param 对lastUpdateDate进行赋值
     */
    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    /**
     * @return 返回 children
     */
    public List<ConfigItem4VC> getChildren() {
        return children;
    }

    /**
     * @param 对children进行赋值
     */
    public void setChildren(List<ConfigItem4VC> children) {
        this.children = children;
    }
}
