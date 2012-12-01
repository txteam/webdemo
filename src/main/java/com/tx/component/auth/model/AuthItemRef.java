/*
 * 描          述:  <描述>
 * 修  改   人:  PengQingyang
 * 修改时间:  2012-12-1
 * <修改描述:>
 */
package com.tx.component.auth.model;

import java.util.Date;


 /**
  * <功能简述>
  * <功能详细描述>
  * 
  * @author  PengQingyang
  * @version  [版本号, 2012-12-1]
  * @see  [相关类/方法]
  * @since  [产品/模块版本]
  */
public class AuthItemRef extends AuthItem{
    
    /** 注释内容 */
    private static final long serialVersionUID = -7928952142014599323L;

    /** 权限引用项的创建(授予)时间 */
    private Date createDate;
    
    /** 权限引用项的授予人 */
    private String operId;
    
    /** 
     * 权限引用项的类型
     * 利用该类型实现临时权限 
     */
    private Date authRefType;
    
    /**
     * 权限失效时间
     */
    private Date inValidDate;

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
     * @return 返回 operId
     */
    public String getOperId() {
        return operId;
    }

    /**
     * @param 对operId进行赋值
     */
    public void setOperId(String operId) {
        this.operId = operId;
    }

    /**
     * @return 返回 authRefType
     */
    public Date getAuthRefType() {
        return authRefType;
    }

    /**
     * @param 对authRefType进行赋值
     */
    public void setAuthRefType(Date authRefType) {
        this.authRefType = authRefType;
    }

    /**
     * @return 返回 inValidDate
     */
    public Date getInValidDate() {
        return inValidDate;
    }

    /**
     * @param 对inValidDate进行赋值
     */
    public void setInValidDate(Date inValidDate) {
        this.inValidDate = inValidDate;
    }
}
