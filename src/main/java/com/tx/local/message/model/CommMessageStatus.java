/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2020年1月8日
 * <修改描述:>
 */
package com.tx.local.message.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;

import com.tx.component.basicdata.annotation.BasicDataEntity;
import com.tx.component.basicdata.model.BasicData;
import com.tx.component.basicdata.model.BasicDataViewTypeEnum;

/**
 * 意见反馈状态<br/>
 * <功能详细描述>
 * 注：
 *  1.枚举类型，可数，数量有限的状态一般用state；
 *  2.不可数，不确定的状态用status；
 *  3.物理状态、心理状态用state；
 *  4.人的身份地位、身体/生活状态、物品的交易、事件的阶段/进度用status；
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2020年1月8日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@BasicDataEntity(name = "会话消息状态", viewType = BasicDataViewTypeEnum.LIST)
public class CommMessageStatus implements BasicData {
    
    /** 注释内容 */
    private static final long serialVersionUID = 9068372865889955343L;
    
    /** 唯一键id */
    @Id
    private String id;
    
    /** 所属虚中心id */
    @Column(nullable = false)
    private String vcid;
    
    /** 分类 */
    @Column(name = "catalogId", nullable = false)
    private CommMessageCatalog catalog;
    
    /** 通知消息类型编码 */
    private String code;
    
    /** 消息类型名称 */
    private String name;
    
    /** 是否有效 */
    private boolean valid;
    
    /** 是否可编辑 */
    private boolean modifyAble;
    
    /** 备注 */
    private String remark;
    
    /** 最后更新时间 */
    @Column(nullable = false, updatable = true)
    private Date lastUpdateDate;
    
    /** 创建时间 */
    @Column(nullable = false, updatable = false)
    private Date createDate;
    
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
}
