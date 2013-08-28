/*
 * 描          述:  <描述>
 * 修  改   人:  PengQingyang
 * 修改时间:  2013-8-26
 * <修改描述:>
 */
package com.tx.component.operator.model;

import javax.persistence.Table;


 /**
  * 虚中心id
  * 用以支持组织架构划分
  * 主要用于区分不同的公司，如果不同的公司共表的情况利用该vcid区分
  * vcid又支持虚中心的树形结构即主公司，与分公司的层次结构<br/>
  * <功能详细描述>
  * 
  * @author  PengQingyang
  * @version  [版本号, 2013-8-26]
  * @see  [相关类/方法]
  * @since  [产品/模块版本]
  */
@Table(name = "user_vcid")
public class Vcid{
    
    /**
     * 虚中心唯一id
     */
    private String id;
    
    /**
     * 虚中心名
     */
    private String name;
    
    /**
     * 虚中心说明
     */
    private String description;

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
