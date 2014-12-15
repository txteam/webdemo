/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2014年11月20日
 * <修改描述:>
 */
package com.tx.component.attachment.controller;

import org.springframework.web.bind.annotation.RequestMapping;

import com.tx.component.attachment.model.BillAttachment;
import com.tx.core.paged.model.PagedList;

/**
 * 单据附件管理控制器<br/>
 * 
 * @author  Administrator
 * @version  [版本号, 2014年11月20日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class BillAttachmentController {
    
    /**
      * 跳转到单据附件预览页面<br/>
      * <功能详细描述>
      * @return [参数说明]
      * 
      * @return String [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    @RequestMapping("viewBillAttachments")
    public String viewBillAttachments() {
        
        return "/billattachment/viewBillAttachments";
    }
    
    public PagedList<BillAttachment> queryBillAttachmentPagedList(){
        
        return null;
    }
    
    public void uploadBillAttachment() {
        
    }
    
    public void insertUploadBillAttachment() {
        
    }
    
    public void deleteBillAttachment() {
        
    }
    
}
