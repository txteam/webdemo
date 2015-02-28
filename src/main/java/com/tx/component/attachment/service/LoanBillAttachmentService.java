/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2014年11月20日
 * <修改描述:>
 */
package com.tx.component.attachment.service;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.fileupload.FileItem;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.tx.component.attachment.dao.AttachmentDao;
import com.tx.component.attachment.dao.AttachmentRefDao;
import com.tx.component.attachment.model.Attachment;
import com.tx.component.attachment.model.AttachmentRefTypeEnum;
import com.tx.component.attachment.model.AttachmentServiceTypeEnum;
import com.tx.component.configuration.context.ConfigContext;
import com.tx.component.file.context.FileContext;
import com.tx.component.file.model.FileDefinition;
import com.tx.core.exceptions.util.ExceptionWrapperUtils;

/**
 * 申请单附件业务层<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2014年11月20日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Service("loanBillAttachmentService")
public class LoanBillAttachmentService {
    
    /**资源文件容器*/
    @Resource(name = "loanBillFileContext")
    private FileContext fileContext;
    
    /**单据附件保存地址 */
    private String savePath = ConfigContext.getContext()
            .getConfigPropertyValueByKey("test.upload.local.savePath");
    
    /**单据附件 Dao*/
    @Resource(name = "attachmentDao")
    private AttachmentDao attachmentDao;
    
    /**单据附件数据引用 Dao*/
    @Resource(name = "attachmentRefDao")
    private AttachmentRefDao AttachmentRefDao;
    
    /**单据附件数据引用处理器*/
    @Resource(name = "attachmentRefService")
    private AttachmentRefService attachmentRefService;
    
    //    private AttachmentFacade attachmentFacade;
    
    /**
     * 
      *<保存单据附件>
      *<如果存在同名单据附件,则内部自动处理>
      * @param processDefFile 附件 
      * @param serviceType 业务类型
      * @param loanBillId 单据 id
      * @param clientId 客户 id
      * 
      * @return void [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    @Transactional
    public void saveLoanBillAttachment(CommonsMultipartFile processDefFile,
            String serviceType, String loanBillId, String clientId) {
        String path = savePath;
        try {
            FileItem fileItem = processDefFile.getFileItem(); // 获得文件描述
            
            String relativePath = path + File.separatorChar + serviceType
                    + File.separatorChar
                    + UUID.randomUUID().toString().replaceAll("-", ""); // 获得文件存储全路径
            
            // 保存附件(保存附件描述到数据库,保存附件到存储设备)
            FileDefinition fileDef = fileContext.save(relativePath,
                    fileItem.getName(),
                    fileItem.getInputStream());
            
            // 保存单据附件信息
            Attachment attachment = buildAndPersistAttachment(fileDef,
                    serviceType);
            
            // 保存单据附件引用信息
            attachmentRefService.insertAttachmentRefByAttachment(attachment.getId(),
                    AttachmentRefTypeEnum.CLIENT,
                    clientId);
            attachmentRefService.insertAttachmentRefByAttachment(attachment.getId(),
                    AttachmentRefTypeEnum.LOANBILL,
                    loanBillId);
            
        } catch (IOException e) {
            throw ExceptionWrapperUtils.wrapperIOException(e,
                    "get inputstream error.",
                    path);
        }
        
    }
    
    /**
     * 
      *<创建持久化的单据附件实体>
      *<功能详细描述>
      * @param fileDef 资源文件定义
      * @param serviceType 业务类型
      * @return 单据附件
      * 
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    private Attachment buildAndPersistAttachment(FileDefinition fileDef,
            String serviceType) {
        Attachment att = new Attachment();
        Date nowDate = new Date();
        
        att.setCreateDate(nowDate); // 创建时间
        att.setCreateOperatorId(null); // FIXME 何雨 询问彭总这么获取当前登录人员
        att.setFileId(fileDef.getId()); // 资源文件 id
        att.setFilename(fileDef.getFilename());// 文件名称
        att.setFilenameExtension(fileDef.getFilenameExtension());// 文件后缀名
        att.setLastUpdateDate(nowDate); // 最后修改时间
        att.setLastUpdateOperatorId(null); // FIXME 何雨 询问彭总这么获取当前登录人员
        att.setServiceType(AttachmentServiceTypeEnum.valueOf(serviceType)); // 业务类型
        
        attachmentDao.insertAttachment(att);
        return att;
    }
}
