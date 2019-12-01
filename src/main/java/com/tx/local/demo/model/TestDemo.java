/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2019年5月28日
 * <修改描述:>
 */
package com.tx.local.demo.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.tx.core.tree.model.TreeAble;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 测试对象
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2019年5月28日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Entity
@Table(name = "test_demo")
@ApiModel("测试对象")
public class TestDemo extends TestDemoSuper
        implements TreeAble<List<TestDemo>, TestDemo> {
    
    @Id
    private String id;
    
    private String parentId;
    
    private TestDemoTypeEnum type;
    
    @Column(name = "nested1Id")
    private TestDemoNested1 nested1;
    
    @JoinColumn(name = "nested2Code", nullable = false)
    private TestDemoNested2 nested2;
    
    @Column(nullable = false, length = 32)
    private String name;
    
    private String remark;
    
    private String description;
    
    private String attributes;
    
    @Column(nullable = false, updatable = false)
    private Date createDate;
    
    @Column(updatable = false)
    private String createOperatorId;
    
    @Column(nullable = false)
    private Date lastUpdateDate;
    
    private String lastUpdateOperatorId;
    
    @Column(nullable = false)
    private boolean valid = true;
    
    private boolean modifyAble;
    
    @ApiModelProperty("是否成功")
    private Boolean success;
    
    private Date effictiveDate;
    
    private Date expiryDate;
    
    @ApiModelProperty("测试Integer值")
    private Integer testInteger;
    
    @ApiModelProperty("测试BigDecimal值")
    private BigDecimal testBigDecimal;
    
    @ApiModelProperty("测试int值")
    private int testInt;
    
    @ApiModelProperty("测试Long值")
    private Long testLong;
    
    @Transient
    private HashMap<String, String> testHashMap;
    
    @Transient
    private HashSet<String> testHashSet;
    
    @Transient
    private ArrayList<String> testArrayList;
    
    @Transient
    private List<TestDemo> children;
    
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
     * @return 返回 nested1
     */
    public TestDemoNested1 getNested1() {
        return nested1;
    }
    
    /**
     * @param 对nested1进行赋值
     */
    public void setNested1(TestDemoNested1 nested1) {
        this.nested1 = nested1;
    }
    
    /**
     * @return 返回 nested2
     */
    public TestDemoNested2 getNested2() {
        return nested2;
    }
    
    /**
     * @param 对nested2进行赋值
     */
    public void setNested2(TestDemoNested2 nested2) {
        this.nested2 = nested2;
    }
    
    /**
     * @return 返回 testInteger
     */
    public Integer getTestInteger() {
        return testInteger;
    }
    
    /**
     * @param 对testInteger进行赋值
     */
    public void setTestInteger(Integer testInteger) {
        this.testInteger = testInteger;
    }
    
    /**
     * @return 返回 testHashMap
     */
    public HashMap<String, String> getTestHashMap() {
        return testHashMap;
    }
    
    /**
     * @param 对testHashMap进行赋值
     */
    public void setTestHashMap(HashMap<String, String> testHashMap) {
        this.testHashMap = testHashMap;
    }
    
    /**
     * @return 返回 testHashSet
     */
    public HashSet<String> getTestHashSet() {
        return testHashSet;
    }
    
    /**
     * @param 对testHashSet进行赋值
     */
    public void setTestHashSet(HashSet<String> testHashSet) {
        this.testHashSet = testHashSet;
    }
    
    /**
     * @return 返回 testArrayList
     */
    public ArrayList<String> getTestArrayList() {
        return testArrayList;
    }
    
    /**
     * @param 对testArrayList进行赋值
     */
    public void setTestArrayList(ArrayList<String> testArrayList) {
        this.testArrayList = testArrayList;
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
     * @return 返回 attributes
     */
    public String getAttributes() {
        return attributes;
    }
    
    /**
     * @param 对attributes进行赋值
     */
    public void setAttributes(String attributes) {
        this.attributes = attributes;
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
     * @return 返回 testLong
     */
    public Long getTestLong() {
        return testLong;
    }
    
    /**
     * @param 对testLong进行赋值
     */
    public void setTestLong(Long testLong) {
        this.testLong = testLong;
    }
    
    /**
     * @return 返回 type
     */
    public TestDemoTypeEnum getType() {
        return type;
    }
    
    /**
     * @param 对type进行赋值
     */
    public void setType(TestDemoTypeEnum type) {
        this.type = type;
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
     * @return 返回 children
     */
    public List<TestDemo> getChildren() {
        return children;
    }
    
    /**
     * @param 对children进行赋值
     */
    public void setChildren(List<TestDemo> children) {
        this.children = children;
    }
    
    /**
     * @return 返回 testMode2List
     */
    /*public List<TestMode2> getTestMode2List() {
        return testMode2List;
    }
    
    *//**
      * @param 对testMode2List进行赋值
      *//*
        public void setTestMode2List(List<TestMode2> testMode2List) {
         this.testMode2List = testMode2List;
        }*/
    
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
     * @return 返回 success
     */
    public Boolean getSuccess() {
        return success;
    }
    
    /**
     * @param 对success进行赋值
     */
    public void setSuccess(Boolean success) {
        this.success = success;
    }
    
    /**
     * @return 返回 effictiveDate
     */
    public Date getEffictiveDate() {
        return effictiveDate;
    }
    
    /**
     * @param 对effictiveDate进行赋值
     */
    public void setEffictiveDate(Date effictiveDate) {
        this.effictiveDate = effictiveDate;
    }
    
    /**
     * @return 返回 expiryDate
     */
    public Date getExpiryDate() {
        return expiryDate;
    }
    
    /**
     * @param 对expiryDate进行赋值
     */
    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }
}
