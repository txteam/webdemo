/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2014年7月4日
 * <修改描述:>
 */
package com.tx.component.file.model;

import java.util.Date;

import javax.persistence.Table;


 /**
  * 文件定义<br/>
  * @author  Administrator
  * @version  [版本号, 2014年7月4日]
  * @see  [相关类/方法]
  * @since  [产品/模块版本]
  */
@Table(name = "CORE_FILE_DEFINITION")
public class FileDefinition {
    
    /** 文件的存储id */
    private String id;
    
    /** 所属系统 */
    private String systemId;
    
    /** 文件名称 */
    private String filename;
    
    /** 存储路径 */
    private String savePath;
    
    /** 文件的类型：jpg,png,... */
    private String type;
    
    /** 文件的业务类型 billAttachment,... */
    private String serviceType;
    
    /** 文件对象编码：仅仅就是一个存储值，可以存，也可以不存入 */
    private String encoding;
    
    /** 创建时间 */
    private Date createDate;
    
    /** 创建操作人员id */
    private String createOperatorId;
    
    /** 最后更新时间 */
    private Date lastUpdateDate;
    
    /** 最后更新操作人员id */
    private String lastUpdateOperatorId;
    
    /** 所属分公司 */
    private String vcid;
    
    /** 所属组织  */
    private String organizationId;

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
     * @return 返回 systemId
     */
    public String getSystemId() {
        return systemId;
    }

    /**
     * @param 对systemId进行赋值
     */
    public void setSystemId(String systemId) {
        this.systemId = systemId;
    }

    /**
     * @return 返回 filename
     */
    public String getFilename() {
        return filename;
    }

    /**
     * @param 对filename进行赋值
     */
    public void setFilename(String filename) {
        this.filename = filename;
    }

    /**
     * @return 返回 savePath
     */
    public String getSavePath() {
        return savePath;
    }

    /**
     * @param 对savePath进行赋值
     */
    public void setSavePath(String savePath) {
        this.savePath = savePath;
    }

    /**
     * @return 返回 type
     */
    public String getType() {
        return type;
    }

    /**
     * @param 对type进行赋值
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return 返回 serviceType
     */
    public String getServiceType() {
        return serviceType;
    }

    /**
     * @param 对serviceType进行赋值
     */
    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    /**
     * @return 返回 encoding
     */
    public String getEncoding() {
        return encoding;
    }

    /**
     * @param 对encoding进行赋值
     */
    public void setEncoding(String encoding) {
        this.encoding = encoding;
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
}
