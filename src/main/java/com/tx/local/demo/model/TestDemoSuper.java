/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2019年9月12日
 * <修改描述:>
 */
package com.tx.local.demo.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import javax.persistence.Column;
import javax.persistence.Transient;

/**
 * 被继承类<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2019年9月12日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class TestDemoSuper {
    
    @Column(name = "superDemoCode")
    private TestDemoSuper superDemo;
    
    @Column(updatable = false, nullable = false, length = 32)
    private String code;
    
    private BigDecimal superBigDeceimal;
    
    private boolean superBoolean;
    
    private Boolean superIsBooleanObject;
    
    private int superInt;
    
    private Integer superIntegerObject;
    
    @Transient
    private HashMap<String, String> superHashMap;
    
    @Transient
    private ArrayList<String> superArrayList;
    
    @Transient
    private HashSet<String> superHashSet;
    
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
     * @return 返回 superDemo
     */
    public TestDemoSuper getSuperDemo() {
        return superDemo;
    }
    
    /**
     * @param 对superDemo进行赋值
     */
    public void setSuperDemo(TestDemoSuper superDemo) {
        this.superDemo = superDemo;
    }
    
    /**
     * @return 返回 superBigDeceimal
     */
    public BigDecimal getSuperBigDeceimal() {
        return superBigDeceimal;
    }
    
    /**
     * @param 对superBigDeceimal进行赋值
     */
    public void setSuperBigDeceimal(BigDecimal superBigDeceimal) {
        this.superBigDeceimal = superBigDeceimal;
    }
    
    /**
     * @return 返回 superBoolean
     */
    public boolean isSuperBoolean() {
        return superBoolean;
    }
    
    /**
     * @param 对superBoolean进行赋值
     */
    public void setSuperBoolean(boolean superBoolean) {
        this.superBoolean = superBoolean;
    }
    
    /**
     * @return 返回 superIsBooleanObject
     */
    public Boolean getSuperIsBooleanObject() {
        return superIsBooleanObject;
    }
    
    /**
     * @param 对superIsBooleanObject进行赋值
     */
    public void setSuperIsBooleanObject(Boolean superIsBooleanObject) {
        this.superIsBooleanObject = superIsBooleanObject;
    }
    
    /**
     * @return 返回 superInt
     */
    public int getSuperInt() {
        return superInt;
    }
    
    /**
     * @param 对superInt进行赋值
     */
    public void setSuperInt(int superInt) {
        this.superInt = superInt;
    }
    
    /**
     * @return 返回 superIntegerObject
     */
    public Integer getSuperIntegerObject() {
        return superIntegerObject;
    }
    
    /**
     * @param 对superIntegerObject进行赋值
     */
    public void setSuperIntegerObject(Integer superIntegerObject) {
        this.superIntegerObject = superIntegerObject;
    }
    
    /**
     * @return 返回 superHashMap
     */
    public HashMap<String, String> getSuperHashMap() {
        return superHashMap;
    }
    
    /**
     * @param 对superHashMap进行赋值
     */
    public void setSuperHashMap(HashMap<String, String> superHashMap) {
        this.superHashMap = superHashMap;
    }
    
    /**
     * @return 返回 superArrayList
     */
    public ArrayList<String> getSuperArrayList() {
        return superArrayList;
    }
    
    /**
     * @param 对superArrayList进行赋值
     */
    public void setSuperArrayList(ArrayList<String> superArrayList) {
        this.superArrayList = superArrayList;
    }
    
    /**
     * @return 返回 superHashSet
     */
    public HashSet<String> getSuperHashSet() {
        return superHashSet;
    }
    
    /**
     * @param 对superHashSet进行赋值
     */
    public void setSuperHashSet(HashSet<String> superHashSet) {
        this.superHashSet = superHashSet;
    }
}
