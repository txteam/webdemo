/*
 * 描          述:  <描述>
 * 修  改   人:  PengQingyang
 * 修改时间:  2012-12-1
 * <修改描述:>
 */
package com.tx.component.operator.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;


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
@Table(name = "OPER_OPERATOR")
public class Operator implements Serializable{
    
    /** 注释内容 */
    private static final long serialVersionUID = 6236689049474522678L;

    /** 用户唯一键  */
    @Id
    private String id;
    
    /**登录名*/
    private String loginName;
    
    /**用户名*/
    private String userName;
    
    /** 密码 */
    private String password;
    
    /** 历史密码 */
    private String historyPwd;
    
    /** 审批密码 */
    private String examinePwd;
    
    /** 用户输错密码的次数 */
    private Integer pwdErrCount = 0;
    
    /**是否可用*/
    private boolean valid = true;
    
    /** 创建时间  */
    private Date createDate;
    
    /** 最后一次修改时间 */
    private Date lastUpdateDate;
    
    /**用户最近一次修改密码时间*/
    private Date pwdUpdateDate;
    
    /** 失效时间 */
    private Date invalidDate;
    
    /** 账户是否被锁定 */
    private boolean locked = false;
    
    /** 员工信息,如果为公司员工，则该信息不为空  */
    @Transient
    private EmployeeInfo employeeInfo;
    
    /** 
     * 所属组织 
     * 在代码中控制组织id不能为空，考虑到超级管理员的组织为空的情况
     * 由界面中创建的人员组织id不能为空
     */
    @ManyToOne
    @JoinColumn(name="organizationId")
    private Organization organization;
    
    /** 主要职位 */
    @ManyToOne
    @JoinColumn(name="mainPostId")
    private Post mainPost;
    
    /** 职位 */
    @ManyToMany
    private List<Post> postList;

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
     * @return 返回 loginName
     */
    public String getLoginName() {
        return loginName;
    }

    /**
     * @param 对loginName进行赋值
     */
    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    /**
     * @return 返回 userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param 对userName进行赋值
     */
    public void setUserName(String userName) {
        this.userName = userName;
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
     * @return 返回 employeeInfo
     */
    public EmployeeInfo getEmployeeInfo() {
        return employeeInfo;
    }

    /**
     * @param 对employeeInfo进行赋值
     */
    public void setEmployeeInfo(EmployeeInfo employeeInfo) {
        this.employeeInfo = employeeInfo;
    }

    /**
     * @return 返回 organization
     */
    public Organization getOrganization() {
        return organization;
    }

    /**
     * @param 对organization进行赋值
     */
    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    /**
     * @return 返回 postList
     */
    public List<Post> getPostList() {
        return postList;
    }

    /**
     * @param 对postList进行赋值
     */
    public void setPostList(List<Post> postList) {
        this.postList = postList;
    }

    /**
     * @return 返回 mainPost
     */
    public Post getMainPost() {
        return mainPost;
    }

    /**
     * @param 对mainPost进行赋值
     */
    public void setMainPost(Post mainPost) {
        this.mainPost = mainPost;
    }
}
