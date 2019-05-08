///*
// * 描          述:  <描述>
// * 修  改   人:  Administrator
// * 修改时间:  2017年3月5日
// * <修改描述:>
// */
//package com.tx.local.content.context.typehandler;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.nio.charset.Charset;
//
//import org.apache.commons.lang3.StringUtils;
//import org.joda.time.DateTime;
//import org.springframework.core.io.ByteArrayResource;
//import org.springframework.core.io.Resource;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.support.TransactionSynchronizationAdapter;
//import org.springframework.transaction.support.TransactionSynchronizationManager;
//
//import com.alibaba.fastjson.util.IOUtils;
//import com.tx.component.content.ContentConstants;
//import com.tx.component.content.helper.ContentFileHelper;
//import com.tx.component.file.context.FileContext;
//import com.tx.component.file.model.FileDefinition;
//import com.tx.core.exceptions.util.AssertUtils;
//import com.tx.local.content.context.AbstractContentTypeHandler;
//import com.tx.local.content.model.ContentInfo;
//
///**
// * 默认的内容类型处理器<br/>
// * <功能详细描述>
// * 
// * @author  Administrator
// * @version  [版本号, 2017年3月5日]
// * @see  [相关类/方法]
// * @since  [产品/模块版本]
// */
//@Component("fileRichContentHandler")
//public class FileRichContentHandler extends AbstractContentTypeHandler {
//    
//    private Charset charset = Charset.forName("UTF-8");
//    
//    /**
//     * @return
//     */
//    @Override
//    public String type() {
//        return "fileRichContent";
//    }
//    
//    /**
//     * @return
//     */
//    @Override
//    public String name() {
//        //文件存储类型
//        return "富文本内容FILE类型";
//    }
//    
//    /**
//     * @return
//     */
//    @Override
//    public String remark() {
//        return "存储内容至文件容器,content内容不限.";
//    }
//    
//    /**
//     * @param contentInfo
//     */
//    @Override
//    public void validate(ContentInfo contentInfo) {
//        AssertUtils.notNull(contentInfo, "contentInfo is null.");
//    }
//    
//    /**
//     * @param contentInfo
//     */
//    @Override
//    public void doBeforeInsertHandle(final ContentInfo contentInfo) {
//        doBeforePersist(contentInfo);
//    }
//    
//    /**
//     * @param contentInfo
//     */
//    @Override
//    public void doBeforeUpdateHandle(final ContentInfo contentInfo) {
//        doBeforePersist(contentInfo);
//    }
//    
//    /** 
//     * 持久化内容前覆写content内容<br/>
//     * <功能详细描述>
//     * @param contentInfo [参数说明]
//     * 
//     * @return void [返回类型说明]
//     * @exception throws [异常类型] [异常说明]
//     * @see [类、类#方法、类#成员]
//     */
//    private void doBeforePersist(ContentInfo contentInfo) {
//        String content = contentInfo.getContent();
//        content = StringUtils.isEmpty(content) ? "" : content;
//        
//        Resource resource = new ByteArrayResource(content.getBytes(charset));
//        FileDefinition fd = FileContext.getContext()
//                .save(ContentConstants.FILE_MODULE,
//                        ContentFileHelper.generateRelativePath(contentInfo.getCategory()
//                                .getCode(),
//                                new DateTime(contentInfo.getCreateDate()),
//                                contentInfo.getId(),
//                                "content.txt"),
//                        resource);
//        
//        contentInfo.setContent(fd.getId());
//    }
//    
//    /**
//     * @param contentInfo
//     */
//    @Override
//    public void doAfterFindHandle(ContentInfo contentInfo) {
//        if (contentInfo == null) {
//            return;
//        }
//        String fdId = contentInfo.getContent();
//        
//        //获取文件定义
//        FileDefinition fd = FileContext.getContext().findWithResourceById(fdId);
//        if (fd != null && fd.getResource() != null) {
//            InputStream in = null;
//            try {
//                in = fd.getResource().getInputStream();
//                String content = IOUtils.toString(in, "UTF-8");
//                contentInfo.setContent(content);
//            } catch (IOException e) {
//                AssertUtils.wrap(e, "resource.inputStream io exception.");
//            } finally {
//                IOUtils.closeQuietly(in);
//            }
//        }
//    }
//    
//    /**
//     * @param contentInfo
//     */
//    @Override
//    public void doAfterDeleteHandle(final ContentInfo contentInfo) {
//        if (contentInfo == null) {
//            return;
//        }
//        TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronizationAdapter() {
//            @Override
//            public void beforeCommit(boolean readOnly) {
//                String fdId = contentInfo.getContent();
//                
//                //获取文件定义
//                FileDefinition fd = FileContext.getContext().findById(fdId);
//                if (fd != null) {
//                    FileContext.getContext().deleteById(fdId);
//                }
//            }
//        });
//    }
//}
