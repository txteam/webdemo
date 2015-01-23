/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2014年12月16日
 * <修改描述:>
 */
package com.tx.component.attachment.model;

import com.tx.component.servicelog.defaultimpl.TXBaseServiceLog;


 /**
  * 附件日志对象<br/>
  * <功能详细描述>
  * 
  * @author  Administrator
  * @version  [版本号, 2014年12月16日]
  * @see  [相关类/方法]
  * @since  [产品/模块版本]
  */
public class AttachmentLog extends TXBaseServiceLog{
    
    /** 附件id */
    private String attachementId;
    
    /** 业务类型 */
    private AttachmentServiceTypeEnum serviceType;
    
    /** 文件id */
    private String fileId;
    
    /** 操作类型：新增，插入。。。。删除等 */
    private String processType;

    /**
     * @return 返回 attachementId
     */
    public String getAttachementId() {
        return attachementId;
    }

    /**
     * @param 对attachementId进行赋值
     */
    public void setAttachementId(String attachementId) {
        this.attachementId = attachementId;
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
     * @return 返回 processType
     */
    public String getProcessType() {
        return processType;
    }

    /**
     * @param 对processType进行赋值
     */
    public void setProcessType(String processType) {
        this.processType = processType;
    }
}
