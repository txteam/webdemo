/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2014年4月29日
 * <修改描述:>
 */
package com.tx.local.financeaccount.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.tx.component.basicdata.model.BasicData;
import com.tx.local.basicdata.model.AccountTitle;
import com.tx.local.basicdata.model.AccountTitleCompany;

/**
 * 会计科目<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2014年4月29日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Entity
@Table(name = "fi_account_title")
public class GLAccountTitleItem implements Serializable, BasicData {
    
    /** 注释内容 */
    private static final long serialVersionUID = 6974461860290899421L;
    
    /** 科目id */
    @Id
    @Column(nullable = false, length = 64, updatable = false)
    private String id;
    
    /** 所属虚中心 */
    @Column(nullable = false, length = 64, updatable = false)
    private String vcid;
    
    /** 编码: */
    @Column(nullable = false, length = 64, updatable = false)
    private String code;
    
    /** 公司对应枚举 */
    @Transient
    private String accountTitleCompanyCode;
    
    /** 对应的科目枚举 */
    @Transient
    private String accountTitleCode;
    
    /** 科目名称：*/
    @Column(nullable = false, length = 64, updatable = true)
    private String name;
    
    /** 描述：*/
    @Column(length = 256, updatable = true)
    private String remark;
    
    /** 是否有效: */
    private boolean valid;
    
    /** 是否可编辑 */
    private boolean modifyAble;
    
    /** 创建时间 */
    @Column(nullable = false, updatable = false)
    private Date createDate;
    
    /** 最后修改时间 */
    @Column(nullable = false, updatable = true)
    private Date lastUpdateDate;
    
    /** 公司对应枚举 */
    @Transient
    private Enum<? extends AccountTitleCompany> accountTitleCompany;
    
    /** 对应的科目枚举 */
    @Transient
    private Enum<? extends AccountTitle> accountTitle;
    
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
     * @return 返回 accountTitleCompanyCode
     */
    public String getAccountTitleCompanyCode() {
        return accountTitleCompanyCode;
    }
    
    /**
     * @param 对accountTitleCompanyCode进行赋值
     */
    public void setAccountTitleCompanyCode(String accountTitleCompanyCode) {
        this.accountTitleCompanyCode = accountTitleCompanyCode;
    }
    
    /**
     * @return 返回 accountTitleCode
     */
    public String getAccountTitleCode() {
        return accountTitleCode;
    }
    
    /**
     * @param 对accountTitleCode进行赋值
     */
    public void setAccountTitleCode(String accountTitleCode) {
        this.accountTitleCode = accountTitleCode;
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
     * @return 返回 accountTitleCompany
     */
    public Enum<? extends AccountTitleCompany> getAccountTitleCompany() {
        return accountTitleCompany;
    }
    
    /**
     * @param 对accountTitleCompany进行赋值
     */
    public void setAccountTitleCompany(
            Enum<? extends AccountTitleCompany> accountTitleCompany) {
        this.accountTitleCompany = accountTitleCompany;
    }
    
    /**
     * @return 返回 accountTitle
     */
    public Enum<? extends AccountTitle> getAccountTitle() {
        return accountTitle;
    }
    
    /**
     * @param 对accountTitle进行赋值
     */
    public void setAccountTitle(Enum<? extends AccountTitle> accountTitle) {
        this.accountTitle = accountTitle;
    }
}
