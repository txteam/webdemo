/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2014年11月18日
 * <修改描述:>
 */
package com.tx.component.attachment.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;


 /**
  * 申请单附件<br/>
  * <功能详细描述>
  * 
  * @author  Administrator
  * @version  [版本号, 2014年11月18日]
  * @see  [相关类/方法]
  * @since  [产品/模块版本]
  */
@Entity
@Table(name = "ATTA_ATTACHMENT")
public class Attachment {
    
    /** id */
    private String id;
    
    /** 父附件id:预留字段 */
    private String parentId;
    
    /** 对应的下一个文件对象 */
    private String nextId;
    
    /** 对应的上一个文件对象 */
    private String preId;
    
    /** 对应的文件对象的id */
    private String fileId;
    
    /** 文件名 */
    private String filename;
    
    /** 文件的后缀名 */
    private String filenameExtension;
    
    /** 业务类型 */
    private AttachmentServiceTypeEnum serviceType;
    
    /** 创建日期 */
    private Date createDate;
    
    /** 创建操作员的id */
    private String createOperatorId;
    
    /** 最后跟新日期 */
    private Date lastUpdateDate;
    
    /** 最后跟新人员id */
    private String lastUpdateOperatorId;
    
    /** 删除日期 */
    private Date deleteDate;
    
    /** 删除人员id */
    private String deleteOperatorId;

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
     * @return 返回 nextId
     */
    public String getNextId() {
        return nextId;
    }

    /**
     * @param 对nextId进行赋值
     */
    public void setNextId(String nextId) {
        this.nextId = nextId;
    }

    /**
     * @return 返回 preId
     */
    public String getPreId() {
        return preId;
    }

    /**
     * @param 对preId进行赋值
     */
    public void setPreId(String preId) {
        this.preId = preId;
    }

    /**
     * @return 返回 fileId
     */
    public String getFileId() {
        return fileId;
    }

    /**
     * @param 对fileId进行赋值
     */
    public void setFileId(String fileId) {
        this.fileId = fileId;
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
     * @return 返回 filenameExtension
     */
    public String getFilenameExtension() {
        return filenameExtension;
    }

    /**
     * @param 对filenameExtension进行赋值
     */
    public void setFilenameExtension(String filenameExtension) {
        this.filenameExtension = filenameExtension;
    }

    /**
     * @return 返回 serviceType
     */
    public AttachmentServiceTypeEnum getServiceType() {
        return serviceType;
    }

    /**
     * @param 对serviceType进行赋值
     */
    public void setServiceType(AttachmentServiceTypeEnum serviceType) {
        this.serviceType = serviceType;
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
     * @return 返回 deleteDate
     */
    public Date getDeleteDate() {
        return deleteDate;
    }

    /**
     * @param 对deleteDate进行赋值
     */
    public void setDeleteDate(Date deleteDate) {
        this.deleteDate = deleteDate;
    }

    /**
     * @return 返回 deleteOperatorId
     */
    public String getDeleteOperatorId() {
        return deleteOperatorId;
    }

    /**
     * @param 对deleteOperatorId进行赋值
     */
    public void setDeleteOperatorId(String deleteOperatorId) {
        this.deleteOperatorId = deleteOperatorId;
    }
}
