/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2014年11月20日
 * <修改描述:>
 */
package com.tx.component.attachment.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.annotation.Resource;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.tx.component.attachment.model.AttachmentServiceTypeEnum;
import com.tx.component.attachment.service.LoanBillAttachmentService;
import com.tx.component.configuration.context.ConfigContext;
import com.tx.component.file.context.FileContext;
import com.tx.core.exceptions.util.ExceptionWrapperUtils;

/**
 * 单据附件管理控制器<br/>
 * 
 * @author  Administrator
 * @version  [版本号, 2014年11月20日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@RequestMapping("loanBillAttachment")
@Controller("loanBillAttachmentController")
public class LoanBillAttachmentController {
    
    /**单据附件业务处理器*/
    @Resource(name = "loanBillAttachmentService")
    private LoanBillAttachmentService loanBillAttachmentService;
    
    /**
     * 
      *<单据附件上传页面>
      *<提供一个多文件和文件类型上传控件>
      * @return 单据上传jsp 页面路径
      * 
      * @return String [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/toViewLoanbillUpload")
    public String toViewLoanBillAttachmentUpload(Model requestAttrs) {
        return "/loanbillattachment/viewLoanbillUpload";
    }
    
    /**
     * 
      *<上传>
      *<功能详细描述>
      * @param requestId
      * @param processDefFile
      * @return [参数说明]
      * 
      * @return boolean [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/doUpload")
    public boolean upload(
            @RequestParam(value = "serviceType") String serviceType,
            @RequestParam(value = "loanBillId") String loanBillId,
            @RequestParam(value = "clientId") String clientId,
            @RequestParam(value = "processDefFile") CommonsMultipartFile processDefFile) {
        
        try {
            loanBillAttachmentService.saveLoanBillAttachment(processDefFile,
                    serviceType,
                    loanBillId,
                    clientId);
        } catch (Exception e) {
            // FIXME 何雨 在次框架下 异常不知道这么处理
            e.printStackTrace();
            return false;
        }
        
        return true;
    }
    
    /**
     * 查看单据附件管理列表
     * <主要展示单据附件列表,针对每个附件提供预览,删除,替换,启用禁用等功能(一部分功能待实现)>
     * @return 单据附件管理列表jsp 页面路径
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
    @RequestMapping("/toViewLoanbills")
    public String toViewLoanBillAttachments(Model requestAttrs) {
        
        return "/loanbillattachment/viewLoanBills";
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
        
        return "/loanbillattachment/uploadAttachments";
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
    @RequestMapping("/uploadLoanBillAttachments")
    public boolean uploadLoanBillAttachments(
            @RequestParam(value = "processDefFile") CommonsMultipartFile[] processDefFile,
            @RequestParam(value = "serviceType") String serviceType,
            @RequestParam(value = "loanBillId") String loanBillId,
            @RequestParam(value = "clientinfoId", required = false) String clientinfoId) {
        
        return true;
    }
}
