/*
 * 描          述:  <描述>
 * 修  改   人:  PengQingyang
 * 修改时间:  2012-10-14
 * <修改描述:>
 */
package com.boda.los.demo.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.tx.core.tree.model.TreeAble;

 /**
  * <demo模型>
  * <功能详细描述>
  * 
  * @author  PengQingyang
  * @version  [版本号, 2012-10-14]
  * @see  [相关类/方法]
  * @since  [产品/模块版本]
  */
@Entity
@Table(name="wd_demo")
public class Demo implements TreeAble<List<Demo>, Demo>{
    
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
    
    @Column(name="login_name")
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
    
}
