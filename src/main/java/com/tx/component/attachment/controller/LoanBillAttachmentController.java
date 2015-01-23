/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2014年11月20日
 * <修改描述:>
 */
package com.tx.component.attachment.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.tx.component.attachment.model.Attachment;
import com.tx.component.attachment.model.AttachmentServiceTypeEnum;
import com.tx.component.attachment.service.LoanBillAttachmentService;

/**
 * 单据附件管理控制器<br/>
 * 
 * @author  Administrator
 * @version  [版本号, 2014年11月20日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@RequestMapping("loanBillAttachment")
@Component("loanBillAttachmentController")
public class LoanBillAttachmentController {
    
    @Resource(name = "loanBillAttachmentService")
    private LoanBillAttachmentService loanBillAttachmentService;
    
    /**
      * 查看loan
      * <功能详细描述>
      * @return [参数说明]
      * 
      * @return String [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public String toViewLoanBillAttachments(
            @RequestParam(value = "loanBillId") String loanBillId) {
        
        return "/attachment/viewLoanBillAttachments";
    }
    
    public List<Attachment> queryLoanBillAttachmentsByLoanBillId(){
        
        return null;
    }
    
    /**
      * 跳转到上传loanBill附件的页面<br/>
      * <功能详细描述>
      * @param serviceType
      * @param loanBillId
      * @param clientinfoId
      * @return [参数说明]
      * 
      * @return String [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public String toUploadLoanBillAttachments(
            @RequestParam(value = "serviceType") AttachmentServiceTypeEnum serviceType,
            @RequestParam(value = "loanBillId") String loanBillId,
            @RequestParam(value = "clientinfoId", required = false) String clientinfoId) {
        
        return "/attachment/uploadAttachments";
    }
    
    /**
      * 跳转到单据附件预览页面<br/>
      * <功能详细描述>
      * @return [参数说明]
      * 
      * @return String [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("uploadLoanBillAttachments")
    public boolean uploadLoanBillAttachments(
            @RequestParam(value = "processDefFile") CommonsMultipartFile[] processDefFile,
            @RequestParam(value = "serviceType") String serviceType,
            @RequestParam(value = "loanBillId") String loanBillId,
            @RequestParam(value = "clientinfoId", required = false) String clientinfoId) {
        
        return true;
    }
}
