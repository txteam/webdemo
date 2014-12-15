/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2014年5月11日
 * <修改描述:>
 */
package com.tx.component.file.context;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.core.io.Resource;

import com.tx.component.file.model.FileDefinition;

/**
 * 文件处理容器<br/>
 *    利用该容器能够实现存放文件
 *    获取文件
 *    存放临时文件
 *    自动清理临时文件
 *    获取临时文件流
 *    等功能
 *    不建议在该封装中对业务逻辑相关查询进行支撑
 *    文件容器对文件的获取最好都是find的逻辑关系
 *    文件容器，作为控件支撑系统中的文件存放逻辑
 * 
 * @author  Administrator
 * @version  [版本号, 2014年5月11日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class FileContext extends FileContextBuilder {
    
    /**
      * 文件存放<br/>
      * <功能详细描述>
      * @param file
      * @return [参数说明]
      * 
      * @return FileDefinition [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public FileDefinition saveFile(File file) {
        
        return null;
    }
    
    public FileDefinition saveFile(InputStream in, String fileName) {
        
        return null;
    }
    
    public FileDefinition saveFile(Resource resource) {
        try {
            resource.getFilename();
            InputStream in = resource.getInputStream();
            
        } catch (IOException e) {
            
        }
        
        return null;
    }
    
    public FileDefinition findFile(String fileDefinitionId) {
        return null;
    }
    
    public InputStream getFileInputStream() {
        return null;
    }
    
    public InputStream getFileInputStreamByFileDefinitionId(
            String fileDefinitionId) {
        return null;
    }
    
    public File getFile() {
        return null;
    }
    
    public File getFileByFileDefinitionId(String fileDefinitionId) {
        return null;
    }
    
    public Resource getFileResource() {
        return null;
    }
    
    public Resource getFileResourceByFileDefinitionId(String fileDefinitionId) {
        return null;
    }
    
}
