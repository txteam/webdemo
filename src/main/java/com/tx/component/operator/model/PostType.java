/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2014年2月19日
 * <修改描述:>
 */
package com.tx.component.operator.model;

import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;

import com.tx.core.jdbc.sqlsource.annotation.QueryConditionEqual;
import com.tx.core.jdbc.sqlsource.annotation.QueryConditionGreaterOrEqual;
import com.tx.core.jdbc.sqlsource.annotation.QueryConditionLess;
import com.tx.core.jdbc.sqlsource.annotation.UpdateAble;

/**
 * 职位类型（即为职位组）<br/>
 *     与职位间的关系为1:n的关系，由职位模型中直接持有postTypeId即可<br/>
 *     作为职位的补充<br/>
 *     因业务需要（需要构建销售分组，催收分组等，用于绩效计算）<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2014年2月19日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Table(name = "OPER_POSTTYPE")
public class PostType {
    
    /** 职位分组id */
    @Id
    private String id;
    
    /** 职位分组编码 */
    @UpdateAble
    @QueryConditionEqual
    private String code;
    
    /** 职位分组名 */
    @UpdateAble
    @QueryConditionEqual
    private String name;
    
    /** 职位分组是否有效 */
    @UpdateAble
    @QueryConditionEqual
    private boolean valid;
    
    /** 创建日期 */
    @QueryConditionGreaterOrEqual(key="minCreateDate")
    @QueryConditionLess(key="maxCreateDate")
    private Date createDate;
    
    /** 最后更新时间 */
    @UpdateAble
    private Date lastUpdateDate;
    
    /** 备注 */
    @UpdateAble
    private String remark;
    
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
