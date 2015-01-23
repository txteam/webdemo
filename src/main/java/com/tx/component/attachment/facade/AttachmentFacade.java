/*
9 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2014年12月16日
 * <修改描述:>
 */
package com.tx.component.attachment.facade;

import java.util.List;
import java.util.Map;

import com.tx.component.attachment.model.Attachment;
import com.tx.component.attachment.model.AttachmentRef;
import com.tx.component.attachment.model.AttachmentServiceTypeEnum;

/**
 * 附件相关接口<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2014年12月16日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface AttachmentFacade {
    
    public void saveAttachment(Attachment attachment,
            Map<AttachmentServiceTypeEnum, AttachmentRef> attachmentRef);
    
    public void addAttachment(Attachment attachment,
            Map<AttachmentServiceTypeEnum, AttachmentRef> attachmentRef);
    
    public void deleteAttachmentById(String attachmentId);
    
    /**
      * 更新附件信息<br/>
      * <功能详细描述>
      * @param attachment [参数说明]
      * 
      * @return void [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public void updateAttachment(Attachment attachment);
    
    /**
      * 根据业务类型以及附件引用id查询附件列表<br/>
      * <功能详细描述>
      * @param serviceType
      * @param attachmentRefId
      * @return [参数说明]
      * 
      * @return List<Attachment> [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public List<Attachment> queryAttachmentListByServiceTypeAndRefParams(
            AttachmentServiceTypeEnum serviceType,
            Map<AttachmentServiceTypeEnum, String> attachmentRefId);
}
