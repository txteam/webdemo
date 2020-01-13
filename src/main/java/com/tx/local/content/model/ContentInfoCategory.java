/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2015年12月2日
 * <修改描述:>
 */
package com.tx.local.content.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.tx.component.basicdata.annotation.BasicDataEntity;
import com.tx.component.basicdata.model.BasicDataViewTypeEnum;
import com.tx.component.basicdata.model.TreeAbleBasicData;

import io.swagger.annotations.ApiModel;

/**
 * 内容信息类型<br/>
 *    后续如果有需要可以增加类型分组的概念基本改动量也不是很大，所以暂不进行支持<br/>
 *    该对象由配置文件进行启动加载，只做增量加载，配置文件加载的内容信息内容将不能够被编辑<br/>
 * 
 * @author  Administrator
 * @version  [版本号, 2015年12月2日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Entity
@Table(name = "ci_content_category")
@BasicDataEntity(name = "内容信息分类", viewType=BasicDataViewTypeEnum.PAGEDLIST)
@ApiModel("内容信息分类")
public class ContentInfoCategory implements
        TreeAbleBasicData<ContentInfoCategory> {
    
    /** 注释内容 */
    private static final long serialVersionUID = 4145588558934289893L;
    
    /** 主键id */
    @Id
    private String id;
    
    /** 父级分类 */
    @Column(name = "parentId")
    private ContentInfoCategory parent;
    
    /** 对应枚举关键字：该字段可以为空 */
    private String code;
    
    /** 内容信息类型名 */
    private String name;
    
    /** 内容类型:界面根据该值加载不同的新增，编辑的界面 */
    @Column(name = "typeCode")
    private ContentInfoTypeEnum type;
    
    /** 内容的树层级，设定方面，父级id一旦设置后则不能修改，所以下级的内容仅需要渠道其父级内容level+1即可 */
    private int level = 0;
    
    /** 类型类型排序号 */
    private int orderIndex = 0;
    
    /** 内容信息类型备注 */
    private String remark;
    
    /** 内容信息是否有效 */
    private boolean valid = true;
    
    /** 是否可编辑 */
    private boolean modifyAble = true;
    
    /** 创建时间 */
    private Date createDate;
    
    /** 创建人 */
    private String createOperatorId;
    
    /** 最后更新时间 */
    private Date lastUpdateDate;
    
    /** 最后更新人 */
    private String lastUpdateOperatorId;
    
    /** 子节点 */
    @Transient
    private List<ContentInfoCategory> children;
    
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
     * @return 返回 parent
     */
    public ContentInfoCategory getParent() {
        return parent;
    }
    
    /**
     * @param 对parent进行赋值
     */
    public void setParent(ContentInfoCategory parent) {
        this.parent = parent;
    }
    
    /**
     * @return 返回 type
     */
    public ContentInfoTypeEnum getType() {
        return type;
    }
    
    /**
     * @param 对type进行赋值
     */
    public void setType(ContentInfoTypeEnum type) {
        this.type = type;
    }
    
    /**
     * @return 返回 parentId
     */
    public String getParentId() {
        if (this.parent == null) {
            return null;
        }
        return this.parent.getId();
    }
    
    /**
     * @return 返回 level
     */
    public int getLevel() {
        return level;
    }
    
    /**
     * @param 对level进行赋值
     */
    public void setLevel(int level) {
        this.level = level;
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
     * @return 返回 orderIndex
     */
    public int getOrderIndex() {
        return orderIndex;
    }
    
    /**
     * @param 对orderIndex进行赋值
     */
    public void setOrderIndex(int orderIndex) {
        this.orderIndex = orderIndex;
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
     * @return 返回 createOperatorId
     */
    public String getCreateOperatorId() {
        return createOperatorId;
    }
    
    /**
     * @param 对createOperatorId进行赋值
     */
    public void setCreateOperatorId(String createOperatorId) {
        this.createOperatorId = createOperatorId;
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
     * @return 返回 lastUpdateOperatorId
     */
    public String getLastUpdateOperatorId() {
        return lastUpdateOperatorId;
    }
    
    /**
     * @param 对lastUpdateOperatorId进行赋值
     */
    public void setLastUpdateOperatorId(String lastUpdateOperatorId) {
        this.lastUpdateOperatorId = lastUpdateOperatorId;
    }

    /**
     * @return 返回 children
     */
    public List<ContentInfoCategory> getChildren() {
        return children;
    }

    /**
     * @param 对children进行赋值
     */
    public void setChildren(List<ContentInfoCategory> children) {
        this.children = children;
    }
}
