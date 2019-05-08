/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2017年1月10日
 * <修改描述:>
 */
package com.tx.local.client.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.tx.component.basicdata.annotation.BasicDataEntity;
import com.tx.component.basicdata.model.BasicData;
import com.tx.component.basicdata.model.BasicDataViewTypeEnum;
import com.tx.core.jdbc.sqlsource.annotation.QueryConditionEqual;
import com.tx.core.jdbc.sqlsource.annotation.UpdateAble;
import com.tx.core.support.initable.model.ConfigInitAble;

/**
 * 客户来源<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2017年1月10日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Entity
@Table(name = "cli_client_source")
@BasicDataEntity(name = "客户来源", viewType = BasicDataViewTypeEnum.LIST)
public class ClientSource implements Serializable, ConfigInitAble, BasicData {
    
    /** 注释内容 */
    private static final long serialVersionUID = 3274221145495552537L;
    
    /** 银行账户id唯一键 */
    @Id
    private String id;
    
    /** 银行英文简称*/
    @UpdateAble
    @QueryConditionEqual
    private String code;
    
    /** 是否有效 */
    @UpdateAble
    @QueryConditionEqual
    private boolean valid;
    
    /** 是否可编辑 */
    @UpdateAble
    @QueryConditionEqual
    private boolean modifyAble = true;
    
    /** 银行名称*/
    @UpdateAble
    @QueryConditionEqual
    private String name;
    
    /** 备注 */
    @UpdateAble
    private String remark;
    
    /** 创建时间 */
    private Date createDate;
    
    /** 最后更新时间 */
    @UpdateAble
    @QueryConditionEqual
    private Date lastUpdateDate;
    
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
