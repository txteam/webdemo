/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2014年12月16日
 * <修改描述:>
 */
package com.tx.component.attachment.model;

import javax.persistence.Entity;
import javax.persistence.Table;


 /**
  * 附件关联信息<br/>
  * <功能详细描述>
  * 
  * @author  Administrator
  * @version  [版本号, 2014年12月16日]
  * @see  [相关类/方法]
  * @since  [产品/模块版本]
  */
@Entity
@Table(name = "ATTA_ATTACHMENT_REF")
public class AttachmentRef {
    
    /** 主键 */
    private String id;
    
    /** 附件id */
    private String attachmentId;
    
    /** 附件关联的关联id */
    private String refId;
    
    /** 附件的关联类型:refType */
    private AttachmentRefTypeEnum refType;

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
     * @return 返回 attachmentId
     */
    public String getAttachmentId() {
        return attachmentId;
    }

    /**
     * @param 对attachmentId进行赋值
     */
    public void setAttachmentId(String attachmentId) {
        this.attachmentId = attachmentId;
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
     * @return 返回 refType
     */
    public AttachmentRefTypeEnum getRefType() {
        return refType;
    }

    /**
     * @param 对refType进行赋值
     */
    public void setRefType(AttachmentRefTypeEnum refType) {
        this.refType = refType;
    }
}
