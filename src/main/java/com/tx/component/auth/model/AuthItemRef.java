/*
 * 描          述:  <描述>
 * 修  改   人:  PengQingyang
 * 修改时间:  2012-12-1
 * <修改描述:>
 */
package com.tx.component.auth.model;

import java.io.Serializable;
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
public abstract class AuthItemRef implements Serializable{
    
    /** 注释内容 */
    private static final long serialVersionUID = -7928952142014599323L;
    
    /** 操作员权限 */
    public String AUTHREFTYPE_OPERATOR = "AUTHREFTYPE_OPERATOR";
    
    /** 操作员临时权限 */
    public String AUTHREFTYPE_OPERATOR_TEMP = "AUTHREFTYPE_OPERATOR_TEMP"; 
    
    /** 角色权限 */
    public String AUTHREFTYPE_ROLE = "AUTHREFTYPE_ROLE";
    
    /** 
     * 权限引用对应的权限id
     */
    private String authId;
    
    /**
     * 应用项id 
     * 可以是角色的id,
     * 可以是只为的id
     */
    private String refId;

    /** 权限引用项的创建(授予)时间 */
    private Date createDate;
    
    /** 权限引用项的失效时间 */
    private String endDate;
    
    /**
     * 权限引用项的类型
     * 利用该类型
     * 实现            人员权限   AUTHREFTYPE_OPERATOR
     *         临时权限   AUTHREFTYPE_OPERATOR_TEMP
     *         角色权限   AUTHREFTYPE_ROLE
     *         职位权限   ...
     * 这里用String虽没有int查询快，但能让sql可读性增强
     * @return 返回 authRefType
     */
    public abstract String getAuthRefType();

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
     * @return 返回 authId
     */
    public String getAuthId() {
        return authId;
    }

    /**
     * @param 对authId进行赋值
     */
    public void setAuthId(String authId) {
        this.authId = authId;
    }

    /**
     * @return 返回 refId
     */
    public String getRefId() {
        return refId;
    }

    /**
     * @param 对refId进行赋值
     */
    public void setRefId(String refId) {
        this.refId = refId;
    }

    /**
     * @return 返回 endDate
     */
    public String getEndDate() {
        return endDate;
    }

    /**
     * @param 对endDate进行赋值
     */
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
