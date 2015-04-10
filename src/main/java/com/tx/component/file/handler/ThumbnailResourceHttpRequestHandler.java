/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2014年12月24日
 * <修改描述:>
 */
package com.tx.component.file.handler;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.Resource;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import com.tx.component.file.context.FileContext;
import com.tx.core.exceptions.util.ExceptionWrapperUtils;


 /**
  * 缩略图资源RequestHandler处理器<br/>
  * <功能详细描述>
  * 
  * @author  Administrator
  * @version  [版本号, 2014年12月24日]
  * @see  [相关类/方法]
  * @since  [产品/模块版本]
  */
public class ThumbnailResourceHttpRequestHandler extends ResourceHttpRequestHandler{
    
    /** 文件容器 */
    private FileContext fileContext;
    
    /**
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        //覆写该方法去除locations必填的设定
        
    }
    
    /**
     * @param request
     * @return
     */
    @Override
    protected Resource getResource(HttpServletRequest request) {
        String fileDefinitionId = request.getParameter("fileDefinitionId");
        if (StringUtils.isEmpty(fileDefinitionId)) {
            return null;
        }
        //根据fileDefinitionId获取对应的资源
        Resource resource = fileContext.getResourceByFileDefinitionId(fileDefinitionId);
        String fileName = resource.getFilename();
        String fileExtension = org.springframework.util.StringUtils.getFilenameExtension(fileName);
        String fileNameSuffix = StringUtils.substringBeforeLast(fileName, ".");
        //缩略图文件名
        String thumbnailFileName = fileNameSuffix + "_thumbnail." + fileExtension;
        Resource thumbnailResource = null;
        try {
            thumbnailResource = resource.createRelative(thumbnailFileName);
        } catch (IOException e) {
            throw ExceptionWrapperUtils.wrapperIOException(e, e.getMessage());
        }
        if(thumbnailResource != null && thumbnailResource.exists()){
            return thumbnailResource;
        }
        
        //如果缩略图资源不存在，则创建缩略图
        try {
            File newFile = new File(thumbnailResource.getURI());
            FileUtils.copyFile(resource.getFile(), newFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        //返回图片资源
        return thumbnailResource;
    }

    /**
     * @return 返回 fileContext
     */
    public FileContext getFileContext() {
        return fileContext;
    }

    /**
     * @param 对fileContext进行赋值
     */
    public void setFileContext(FileContext fileContext) {
        this.fileContext = fileContext;
    }
}
