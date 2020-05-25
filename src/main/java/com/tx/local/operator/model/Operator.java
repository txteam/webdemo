/*
 * 描          述:  <描述>
 * 修  改   人:  PengQingyang
 * 修改时间:  2012-12-1
 * <修改描述:>
 */
package com.tx.local.operator.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import io.swagger.annotations.ApiModel;

/**
 * 操作员<br/>
 * <功能详细描述>
 * 
 * @author  PengQingyang
 * @version  [版本号, 2012-12-1]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Entity
@Table(name = "oper_operator")
@ApiModel("操作人员")
public class Operator implements Serializable {
    
    /** 注释内容 */
    private static final long serialVersionUID = 6236689049474522678L;
    
    /** 用户唯一键  */
    @Id
    private String id;
    
    /** 所属虚中心id */
    private String vcid;
    
    /** 所属组织: 在代码中控制组织id不能为空，考虑到超级管理员的组织为空的情况,由界面中创建的人员组织id不能为空 */
    private String organizationId;
    
    /** 主要职位 */
    private String mainPostId;
    
    /** 用户名 */
    private String username;
    
    /** 用户名 */
    private int usernameChangeCount = 0;
    
    /** 用户名是否能够变更 */
    private boolean usernameChangeAble = true;
    
    /** 操作员姓名 */
    private String name;
    
    /** 审批密码 */
    private String examinePwd;
    
    /** 密码 */
    private String password;
    
    /** 历史密码 */
    private String historyPwd;
    
    /** 用户输错密码的次数 */
    private Integer pwdErrCount = 0;
    
    /**用户最近一次修改密码时间*/
    private Date pwdUpdateDate;
    
    /** 账户是否被锁定 */
    private boolean locked = false;
    
    /** 当该对象的创建，更新，删除都是依赖于其他对象时，对象将被标定为不可编辑，尤其是不能被删除，该控制逻辑放到controller中 */
    private boolean modifyAble;
    
    /** 是否可用 */
    private boolean valid = true;
    
    /** 是否为系统管理员：系统管理员不仅这个值为true，也需要拥有角色，这里仅仅服务于显示 */
    private boolean admin = false;
    
    /** 失效时间 */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date invalidDate;
    
    /** 创建时间  */
    private Date createDate;
    
    /** 最后一次修改时间 */
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
     * @return 返回 organizationId
     */
    public String getOrganizationId() {
        return organizationId;
    }
    
    /**
     * @param 对organizationId进行赋值
     */
    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }
    
    /**
     * @return 返回 mainPostId
     */
    public String getMainPostId() {
        return mainPostId;
    }
    
    /**
     * @param 对mainPostId进行赋值
     */
    public void setMainPostId(String mainPostId) {
        this.mainPostId = mainPostId;
    }
    
    /**
     * @return 返回 username
     */
    public String getUsername() {
        return username;
    }
    
    /**
     * @param 对username进行赋值
     */
    public void setUsername(String username) {
        this.username = username;
    }
    
    /**
     * @return 返回 usernameChangeCount
     */
    public int getUsernameChangeCount() {
        return usernameChangeCount;
    }
    
    /**
     * @param 对usernameChangeCount进行赋值
     */
    public void setUsernameChangeCount(int usernameChangeCount) {
        this.usernameChangeCount = usernameChangeCount;
    }
    
    /**
     * @return 返回 usernameChangeAble
     */
    public boolean isUsernameChangeAble() {
        return usernameChangeAble;
    }
    
    /**
     * @param 对usernameChangeAble进行赋值
     */
    public void setUsernameChangeAble(boolean usernameChangeAble) {
        this.usernameChangeAble = usernameChangeAble;
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
     * @return 返回 examinePwd
     */
    public String getExaminePwd() {
        return examinePwd;
    }
    
    /**
     * @param 对examinePwd进行赋值
     */
    public void setExaminePwd(String examinePwd) {
        this.examinePwd = examinePwd;
    }
    
    /**
     * @return 返回 password
     */
    public String getPassword() {
        return password;
    }
    
    /**
     * @param 对password进行赋值
     */
    public void setPassword(String password) {
        this.password = password;
    }
    
    /**
     * @return 返回 historyPwd
     */
    public String getHistoryPwd() {
        return historyPwd;
    }
    
    /**
     * @param 对historyPwd进行赋值
     */
    public void setHistoryPwd(String historyPwd) {
        this.historyPwd = historyPwd;
    }
    
    /**
     * @return 返回 pwdErrCount
     */
    public Integer getPwdErrCount() {
        return pwdErrCount;
    }
    
    /**
     * @param 对pwdErrCount进行赋值
     */
    public void setPwdErrCount(Integer pwdErrCount) {
        this.pwdErrCount = pwdErrCount;
    }
    
    /**
     * @return 返回 pwdUpdateDate
     */
    public Date getPwdUpdateDate() {
        return pwdUpdateDate;
    }
    
    /**
     * @param 对pwdUpdateDate进行赋值
     */
    public void setPwdUpdateDate(Date pwdUpdateDate) {
        this.pwdUpdateDate = pwdUpdateDate;
    }
    
    /**
     * @return 返回 locked
     */
    public boolean isLocked() {
        return locked;
    }
    
    /**
     * @param 对locked进行赋值
     */
    public void setLocked(boolean locked) {
        this.locked = locked;
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
     * @return 返回 admin
     */
    public boolean isAdmin() {
        return admin;
    }
    
    /**
     * @param 对admin进行赋值
     */
    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
    
    /**
     * @return 返回 invalidDate
     */
    public Date getInvalidDate() {
        return invalidDate;
    }
    
    /**
     * @param 对invalidDate进行赋值
     */
    public void setInvalidDate(Date invalidDate) {
        this.invalidDate = invalidDate;
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
