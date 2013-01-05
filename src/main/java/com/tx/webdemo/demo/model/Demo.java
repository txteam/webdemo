/*
 * 描          述:  <描述>
 * 修  改   人:  PengQingyang
 * 修改时间:  2012-10-14
 * <修改描述:>
 */
package com.tx.webdemo.demo.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.DecimalMax;

 /**
  * <demo模型>
  * <功能详细描述>
  * 
  * @author  PengQingyang
  * @version  [版本号, 2012-10-14]
  * @see  [相关类/方法]
  * @since  [产品/模块版本]
  */
/*
AssertFalse.class
AssertTrue.class
DecimalMax.class
DecimalMin.class
Digits.class
Future.class
Max.class
Min.class
NotNull.class
Null.class
Past.class
Pattern.class
Size.class
 */
@Entity
@Table(name="WD_DEMO")
public class Demo {
    
    /** 是否有效: 有效 */
    public static final int ISVALID_TRUE = 1;
    
    /** 是否有效: 无效 */
    public static final int ISVALID_FALSE = 0;
    
    /** 主键 */
    @Id
    private String id;
    
    /** 父id */
    private String parentId;
    
    /** 子demo */
    @OneToOne
    @JoinColumn(name="subdemoid")
    private Demo subDemo;
    
    /** demo名 */
    private String name;
    
    /** 登陆名 */
    private String loginName;
    
    /** 密码 */
    private String password;
    
    /** email */
    private String email;
    
    /** 创建时间 */
    private Date createDate;
    
    /** 最后更新时间 */
    private Date lastUpdateDate;
    
    /** 是否有效 */
    private int isValid = ISVALID_TRUE;
    
    private String description;
    
    @DecimalMax(value="5000",message="")
    private BigDecimal testBigDecimal = BigDecimal.ONE;
    
    private BigDecimal testNullBigDecimal;
    
    private Integer testIntegerObj = 0;
    
    private Integer testNullIntegerObj = 0;
    
    @AssertFalse
    private Boolean testBooleanObj = true;
    
    @AssertTrue(message="")
    private Boolean testNullBooleanObj;
    
    private int testInt;
    
    private boolean testBoolean;
    
    /** 子节点列表 */
    private List<Demo> childs;
    
    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the parentId
     */
    public String getParentId() {
        return parentId;
    }

    /**
     * @param parentId the parentId to set
     */
    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    /**
     * @return the childs
     */
    public List<Demo> getChilds() {
        return childs;
    }

    /**
     * @param childs the childs to set
     */
    public void setChilds(List<Demo> childs) {
        this.childs = childs;
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
     * @return 返回 passowrd
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param 对passowrd进行赋值
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return 返回 email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param 对email进行赋值
     */
    public void setEmail(String email) {
        this.email = email;
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
     * @return the subDemo
     */
    public Demo getSubDemo() {
        return subDemo;
    }

    /**
     * @param subDemo the subDemo to set
     */
    public void setSubDemo(Demo subDemo) {
        this.subDemo = subDemo;
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
     * @return 返回 isValid
     */
    public int getIsValid() {
        return isValid;
    }

    /**
     * @param 对isValid进行赋值
     */
    public void setIsValid(int isValid) {
        this.isValid = isValid;
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
     * @return 返回 description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param 对description进行赋值
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return 返回 testBigDecimal
     */
    public BigDecimal getTestBigDecimal() {
        return testBigDecimal;
    }

    /**
     * @param 对testBigDecimal进行赋值
     */
    public void setTestBigDecimal(BigDecimal testBigDecimal) {
        this.testBigDecimal = testBigDecimal;
    }

    /**
     * @return 返回 testNullBigDecimal
     */
    public BigDecimal getTestNullBigDecimal() {
        return testNullBigDecimal;
    }

    /**
     * @param 对testNullBigDecimal进行赋值
     */
    public void setTestNullBigDecimal(BigDecimal testNullBigDecimal) {
        this.testNullBigDecimal = testNullBigDecimal;
    }

    /**
     * @return 返回 testIntegerObj
     */
    public Integer getTestIntegerObj() {
        return testIntegerObj;
    }

    /**
     * @param 对testIntegerObj进行赋值
     */
    public void setTestIntegerObj(Integer testIntegerObj) {
        this.testIntegerObj = testIntegerObj;
    }

    /**
     * @return 返回 testNullIntegerObj
     */
    public Integer getTestNullIntegerObj() {
        return testNullIntegerObj;
    }

    /**
     * @param 对testNullIntegerObj进行赋值
     */
    public void setTestNullIntegerObj(Integer testNullIntegerObj) {
        this.testNullIntegerObj = testNullIntegerObj;
    }

    /**
     * @return 返回 testBooleanObj
     */
    public Boolean getTestBooleanObj() {
        return testBooleanObj;
    }

    /**
     * @param 对testBooleanObj进行赋值
     */
    public void setTestBooleanObj(Boolean testBooleanObj) {
        this.testBooleanObj = testBooleanObj;
    }

    /**
     * @return 返回 testNullBooleanObj
     */
    public Boolean getTestNullBooleanObj() {
        return testNullBooleanObj;
    }

    /**
     * @param 对testNullBooleanObj进行赋值
     */
    public void setTestNullBooleanObj(Boolean testNullBooleanObj) {
        this.testNullBooleanObj = testNullBooleanObj;
    }

    /**
     * @return 返回 testInt
     */
    public int getTestInt() {
        return testInt;
    }

    /**
     * @param 对testInt进行赋值
     */
    public void setTestInt(int testInt) {
        this.testInt = testInt;
    }

    /**
     * @return 返回 testBoolean
     */
    public boolean isTestBoolean() {
        return testBoolean;
    }

    /**
     * @param 对testBoolean进行赋值
     */
    public void setTestBoolean(boolean testBoolean) {
        this.testBoolean = testBoolean;
    }
}
