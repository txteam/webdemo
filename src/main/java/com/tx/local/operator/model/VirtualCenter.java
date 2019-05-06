/*
 * 描          述:  <描述>
 * 修  改   人:  PengQingyang
 * 修改时间:  2013-8-26
 * <修改描述:>
 */
package com.tx.local.operator.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.tx.core.tree.model.TreeAble;
import com.tx.core.util.ObjectUtils;

/**
 * 虚中心id
 * 用以支持组织架构划分
 * 主要用于区分不同的公司，如果不同的公司共表的情况利用该vcid区分
 * vcid又支持虚中心的树形结构即主公司，与分公司的层次结构<br/>
 * <功能详细描述>
 * 
 * @author  PengQingyang
 * @version  [版本号, 2013-8-26]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Table(name = "oper_vc")
public class VirtualCenter implements
        TreeAble<List<VirtualCenter>, VirtualCenter>, Serializable {
    
    /** 注释内容 */
    private static final long serialVersionUID = 299142584465484552L;
    
    /** 虚中心唯一id */
    @Id
    private String id;
    
    /** 对应的虚中心枚举 */
    private VirtualCenterEnum virtualCenterKey;
    
    /** 上级虚中心id */
    private String parentId;
    
    /** 虚中心名 */
    private String name;
    
    /** 虚中心说明 */
    private String remark;
    
    /** 是否有效 */
    private boolean valid = true;
    
    /** 是否可编辑 */
    private boolean editAble = true;
    
    /** 创建时间 */
    private Date createDate;
    
    /** 子级虚中心 */
    @Transient
    private List<VirtualCenter> childs;
    
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
     * @return 返回 virtualCenterKey
     */
    public VirtualCenterEnum getVirtualCenterKey() {
        return virtualCenterKey;
    }
    
    /**
     * @param 对virtualCenterKey进行赋值
     */
    public void setVirtualCenterKey(VirtualCenterEnum virtualCenterKey) {
        this.virtualCenterKey = virtualCenterKey;
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
     * @return 返回 valid
     */
    public boolean isValid() {
        return valid;
    }
    
    /**
     * @param 对valid进行赋值
     */
    public void setValid(boolean valid) {
        this.valid = valid;
    }
    
    /**
     * @return 返回 editAble
     */
    public boolean isEditAble() {
        return editAble;
    }
    
    /**
     * @param 对editAble进行赋值
     */
    public void setEditAble(boolean editAble) {
        this.editAble = editAble;
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
     * @return 返回 childs
     */
    public List<VirtualCenter> getChildren() {
        return childs;
    }
    
    /**
     * @param 对childs进行赋值
     */
    public void setChildren(List<VirtualCenter> childs) {
        this.childs = childs;
    }
    
    /**
     * @return
     */
    @Override
    public int hashCode() {
        return ObjectUtils.generateHashCode(super.hashCode(), this, "id");
    }
    
    /**
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        return ObjectUtils.equals(this, obj, "id");
    }
    
}
