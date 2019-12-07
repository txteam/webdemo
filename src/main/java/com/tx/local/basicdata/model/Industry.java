package com.tx.local.basicdata.model;

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

/**
 * 行业<br/>
 * <功能详细描述>
 * 
 * @author  Bobby
 * @version  [版本号, 2014年4月16日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Entity
@Table(name = "bd_industry")
@BasicDataEntity(name = "区域信息", viewType = BasicDataViewTypeEnum.PAGEDLIST)
public class Industry implements TreeAbleBasicData<Industry> {
    
    /** 注释内容 */
    private static final long serialVersionUID = 4404205135030732547L;
    
    /** id */
    @Id
    private String id;
    
    /**上级id**/
    @Column(name = "parentId")
    private Industry parent;
    
    /** 一级行业id */
    private String firstLevelId;
    
    /** 行业分级 */
    private int level;
    
    /** 编码 */
    private String code;
    
    /** 名称 */
    private String name;
    
    /** 是否可编辑 */
    private boolean modifyAble = true;
    
    /** 是否有效 */
    private boolean valid = true;
    
    /** 创建日期 */
    private Date createDate;
    
    /** 最后更新时间 */
    private Date lastUpdateDate;
    
    /** 备注 */
    private String remark;
    
    /** 子级节点 */
    @Transient
    private List<Industry> children;
    
    /**
     * @return 返回 parentId
     */
    @Override
    public String getParentId() {
        return this.parent != null ? this.parent.getId() : null;
    }
    
    /**
     * @return 返回 parent
     */
    public Industry getParent() {
        return parent;
    }
    
    /**
     * @param 对parent进行赋值
     */
    public void setParent(Industry parent) {
        this.parent = parent;
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
     * @return 返回 firstLevelId
     */
    public String getFirstLevelId() {
        return firstLevelId;
    }
    
    /**
     * @param 对firstLevelId进行赋值
     */
    public void setFirstLevelId(String firstLevelId) {
        this.firstLevelId = firstLevelId;
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
     * @return 返回 children
     */
    public List<Industry> getChildren() {
        return children;
    }
    
    /**
     * @param 对children进行赋值
     */
    public void setChildren(List<Industry> children) {
        this.children = children;
    }
    
}
