/*
 * 描          述:  <描述>
 * 修  改   人:  PengQingyang
 * 修改时间:  2013-8-26
 * <修改描述:>
 */
package com.tx.component.operator.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.tx.core.tree.model.TreeAble;


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
@Table(name = "OPER_VC")
public class VirtualCenter implements TreeAble<List<VirtualCenter>, VirtualCenter>,Serializable{
    
    /** 注释内容 */
    private static final long serialVersionUID = 299142584465484552L;

    /**
     * 虚中心唯一id
     */
    @Id
    private String id;
    
    /**
     * 上级虚中心id
     */
    private String parentId;
    
    /**
     * 虚中心名
     */
    private String name;
    
    /**
     * 虚中心说明
     */
    private String remark;
    
    /**
     * 子级虚中心
     */
    @Transient
    private List<VirtualCenter> childs;

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
     * @return 返回 childs
     */
    public List<VirtualCenter> getChilds() {
        return childs;
    }

    /**
     * @param 对childs进行赋值
     */
    public void setChilds(List<VirtualCenter> childs) {
        this.childs = childs;
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
}
