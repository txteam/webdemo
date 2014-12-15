/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2014年11月18日
 * <修改描述:>
 */
package com.tx.component.attachment.model;

import java.util.Date;


 /**
  * 申请单附件<br/>
  * <功能详细描述>
  * 
  * @author  Administrator
  * @version  [版本号, 2014年11月18日]
  * @see  [相关类/方法]
  * @since  [产品/模块版本]
  */
public class BillAttachment {
    
    /** id */
    private String id;
    
    /** loanBillId */
    private String loanBillId;
    
    /** 单据附件类型 */
    private BillAttachmentTypeEnum billAttachmentType;
    
    /** 对应的文件对象的id */
    private String fileId;
    
    /** 对应的下一个文件对象 */
    private String nextFileId;
    
    /** 对应的上一个文件对象 */
    private String preFileId;
    
    /** 文件名 */
    private String fileName;
    
    /** 存放路径 */
    private String savePath;
    
    /** 创建日期 */
    private Date createDate;

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
     * @return 返回 loanBillId
     */
    public String getLoanBillId() {
        return loanBillId;
    }

    /**
     * @param 对loanBillId进行赋值
     */
    public void setLoanBillId(String loanBillId) {
        this.loanBillId = loanBillId;
    }

    /**
     * @return 返回 billAttachmentType
     */
    public BillAttachmentTypeEnum getBillAttachmentType() {
        return billAttachmentType;
    }

    /**
     * @param 对billAttachmentType进行赋值
     */
    public void setBillAttachmentType(BillAttachmentTypeEnum billAttachmentType) {
        this.billAttachmentType = billAttachmentType;
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
     * @return 返回 nextFileId
     */
    public String getNextFileId() {
        return nextFileId;
    }

    /**
     * @param 对nextFileId进行赋值
     */
    public void setNextFileId(String nextFileId) {
        this.nextFileId = nextFileId;
    }

    /**
     * @return 返回 preFileId
     */
    public String getPreFileId() {
        return preFileId;
    }

    /**
     * @param 对preFileId进行赋值
     */
    public void setPreFileId(String preFileId) {
        this.preFileId = preFileId;
    }

    /**
     * @return 返回 fileName
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * @param 对fileName进行赋值
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
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
}
