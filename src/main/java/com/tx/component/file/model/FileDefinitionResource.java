/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2014年11月24日
 * <修改描述:>
 */
package com.tx.component.file.model;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;

import org.springframework.core.io.Resource;


 /**
  * 文件定义资源实体<br/>
  * <功能详细描述>
  * 
  * @author  Administrator
  * @version  [版本号, 2014年11月24日]
  * @see  [相关类/方法]
  * @since  [产品/模块版本]
  */
public class FileDefinitionResource implements Resource{
    
    private FileDefinition fileDefinition;
    
    /** <默认构造函数> */
    public FileDefinitionResource(FileDefinition fileDefinition) {
        super();
        this.fileDefinition = fileDefinition;
    }

    /**
     * @return
     * @throws IOException
     */
    @Override
    public InputStream getInputStream() throws IOException {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * @return
     */
    @Override
    public boolean exists() {
        // TODO Auto-generated method stub
        return false;
    }

    /**
     * @return
     */
    @Override
    public boolean isReadable() {
        // TODO Auto-generated method stub
        return false;
    }

    /**
     * @return
     */
    @Override
    public boolean isOpen() {
        // TODO Auto-generated method stub
        return false;
    }

    /**
     * @return
     * @throws IOException
     */
    @Override
    public URL getURL() throws IOException {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * @return
     * @throws IOException
     */
    @Override
    public URI getURI() throws IOException {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * @return
     * @throws IOException
     */
    @Override
    public File getFile() throws IOException {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * @return
     * @throws IOException
     */
    @Override
    public long contentLength() throws IOException {
        // TODO Auto-generated method stub
        return 0;
    }

    /**
     * @return
     * @throws IOException
     */
    @Override
    public long lastModified() throws IOException {
        // TODO Auto-generated method stub
        return 0;
    }

    /**
     * @param relativePath
     * @return
     * @throws IOException
     */
    @Override
    public Resource createRelative(String relativePath) throws IOException {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * @return
     */
    @Override
    public String getFilename() {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * @return
     */
    @Override
    public String getDescription() {
        // TODO Auto-generated method stub
        return null;
    }
}
