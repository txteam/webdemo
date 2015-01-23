/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2014年11月20日
 * <修改描述:>
 */
package com.tx.component.attachment.service;

import javax.annotation.Resource;

import org.springframework.web.multipart.MultipartFile;

import com.tx.component.attachment.facade.AttachmentFacade;
import com.tx.component.file.context.FileContext;


 /**
  * 申请单附件业务层<br/>
  * <功能详细描述>
  * 
  * @author  Administrator
  * @version  [版本号, 2014年11月20日]
  * @see  [相关类/方法]
  * @since  [产品/模块版本]
  */
public class LoanBillAttachmentService {
    
    @Resource(name = "loanBillFileContext")
    private FileContext fileContext;
    
    private AttachmentFacade attachmentFacade;
    
    public void saveLoanBillAttachments(MultipartFile[] files,
            String serviceType,String loanBillId,String clientId){
        //fileContext.save(relativePath, filename, input);
    }
}
