/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2014年11月20日
 * <修改描述:>
 */
package com.tx.component.attachment.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.tx.component.attachment.dao.AttachmentDao;
import com.tx.component.attachment.dao.AttachmentRefDao;
import com.tx.component.attachment.model.Attachment;
import com.tx.component.attachment.model.AttachmentRef;
import com.tx.component.attachment.model.AttachmentRefTypeEnum;
import com.tx.component.attachment.model.AttachmentServiceTypeEnum;
import com.tx.component.configuration.context.ConfigContext;
import com.tx.component.file.context.FileContext;
import com.tx.component.file.model.FileDefinition;
import com.tx.component.mainframe.context.WebContextUtils;
import com.tx.core.TxConstants;
import com.tx.core.exceptions.util.AssertUtils;
import com.tx.core.exceptions.util.ExceptionWrapperUtils;
import com.tx.core.util.UUIDUtils;

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
    
    /**单据附件 Dao*/
    @Resource(name = "attachmentDao")
    private AttachmentDao attachmentDao;
    
    /**单据附件数据引用 Dao*/
    @Resource(name = "attachmentRefDao")
    private AttachmentRefDao AttachmentRefDao;
    
    /**单据附件数据引用处理器*/
    @Resource(name = "attachmentRefService")
    private AttachmentRefService attachmentRefService;
    
    /**单据附件保存地址 */
    private String savePath = ConfigContext.getContext()
            .getConfigPropertyValueByKey("test.upload.local.savePath");
    
    private String loanBillAttachmentSaveBasePath = "/loanbill";
    
    /**
      * 保存单据附件
      * <如果存在同名单据附件,则内部自动处理>
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
    public void saveLoanBillAttachments(CommonsMultipartFile[] processDefFiles,
            AttachmentServiceTypeEnum serviceType, String loanBillId,
            String clientInfoId) {
        AssertUtils.notEmpty(serviceType, "serviceType is null.");
        AssertUtils.notEmpty(loanBillId, "loanBillId is empty.");
        AssertUtils.notEmpty(clientInfoId, "clientInfoId is empty.");
        
        Map<String, Attachment> attachmentsMap = new HashMap<>();
        Map<Attachment, String> attachmentsPreFileNameMap = new HashMap<>();
        Map<Attachment, String> attachmentsNextFileNameMap = new HashMap<>();
        for (CommonsMultipartFile fileTemp : processDefFiles) {
            FileItem fileItem = fileTemp.getFileItem();
            String fileName = fileItem.getName();
            String filenameExtension = org.springframework.util.StringUtils.getFilenameExtension(fileItem.getName());
            String[] fileNames = StringUtils.splitPreserveAllTokens(fileName,
                    "_");
            AssertUtils.isTrue(fileNames.length >= 3 ,"fileNames length < 3,fileName :{}",fileName);
            String currentFileName = fileNames[0];
            String preFileName = fileNames[1];
            String nextFileName = fileNames[2];
            
            StringBuilder sb = new StringBuilder(TxConstants.INITIAL_STR_LENGTH);
            sb.append(loanBillAttachmentSaveBasePath).append("/");
            sb.append(clientInfoId).append("/");
            sb.append(serviceType).append("/");
            sb.append(currentFileName).append(".").append(filenameExtension);
            
            // 保存附件(保存附件描述到数据库,保存附件到存储设备)
            FileDefinition fileDef = null;
            try {
                fileDef = fileContext.save(sb.toString(),
                        currentFileName + "." + filenameExtension,
                        fileTemp.getInputStream());
            } catch (IOException e) {
                throw ExceptionWrapperUtils.wrapperIOException(e,
                        e.getMessage());
            }
            
            // 保存单据附件信息
            Attachment attachment = buildAttachment(fileDef, serviceType);
            attachmentsMap.put(currentFileName, attachment);
            attachmentsPreFileNameMap.put(attachment, preFileName);
            attachmentsNextFileNameMap.put(attachment, nextFileName);
        }
        
        //处理附件的上一张，下一张
        List<AttachmentRef> refList = new ArrayList<AttachmentRef>(TxConstants.INITIAL_CONLLECTION_SIZE);
        for(Attachment attTemp : attachmentsMap.values()){
            String preFileName = attachmentsPreFileNameMap.get(attTemp);
            String nextFileName = attachmentsPreFileNameMap.get(attTemp);
            if(attachmentsMap.containsKey(preFileName)){
                attTemp.setPreId(attachmentsMap.get(preFileName).getId());
            }
            if(attachmentsMap.containsKey(nextFileName)){
                attTemp.setNextId(attachmentsMap.get(nextFileName).getId());
            }
            
            refList.add(buildAttachmentRef(attTemp, AttachmentRefTypeEnum.CLIENT, clientInfoId));
            refList.add(buildAttachmentRef(attTemp, AttachmentRefTypeEnum.LOANBILL, loanBillId));
        }
    }
    
    /**
      * 构建附件引用对象<br/>
      * <功能详细描述>
      * @param attachment
      * @param refType
      * @param refId
      * @return [参数说明]
      * 
      * @return AttachmentRef [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    private AttachmentRef buildAttachmentRef(Attachment attachment,
            AttachmentRefTypeEnum refType, String refId) {
        AttachmentRef attRef = new AttachmentRef();
        attRef.setAttachmentId(attachment.getId());
        attRef.setRefId(refId);
        attRef.setRefType(refType);
        return attRef;
    }
    
    /**
      * 构建附件对象<br/>
      * <功能详细描述>
      * @param fileDef 资源文件定义
      * @param serviceType 业务类型
      * @return 单据附件
      * 
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    private Attachment buildAttachment(FileDefinition fileDefinition,
            AttachmentServiceTypeEnum serviceType) {
        Attachment att = new Attachment();
        Date nowDate = new Date();
        
        String currentOperatorId = WebContextUtils.getCurrentOperatorId();
        att.setId(UUIDUtils.generateUUID());
        att.setCreateDate(nowDate); // 创建时间
        
        att.setCreateOperatorId(currentOperatorId); //
        att.setLastUpdateOperatorId(currentOperatorId); // 询问彭总这么获取当前登录人员
        
        att.setFileId(fileDefinition.getId()); // 资源文件 id
        att.setFilename(fileDefinition.getFilename());// 文件名称
        att.setFilenameExtension(fileDefinition.getFilenameExtension());// 文件后缀名
        att.setLastUpdateDate(nowDate); // 最后修改时间
        
        att.setServiceType(serviceType); // 业务类型
        return att;
    }
}
