/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2014年12月21日
 * <修改描述:>
 */
package com.tx.component.file.context.driver;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.FileSystemResource;

import com.tx.component.file.context.FileDefinitionResource;
import com.tx.component.file.context.FileDefinitionResourceDriver;
import com.tx.component.file.context.resource.DefaultFileDefinitionResource;
import com.tx.component.file.model.FileDefinition;
import com.tx.core.exceptions.io.ResourceIsExistException;
import com.tx.core.exceptions.util.AssertUtils;
import com.tx.core.exceptions.util.ExceptionWrapperUtils;

/**
 * 默认文件定义资源驱动
 *    基鱼FileSystemResource的实现进行编写<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2014年12月21日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class DefaultFileDefinitionResourceDriver implements
        FileDefinitionResourceDriver {
    
    private String path;
    
    private FileSystemResource contextLocationResource;
    
    /** <默认构造函数> */
    public DefaultFileDefinitionResourceDriver(String path) {
        super();
        AssertUtils.notEmpty(path, "location is empty.");
        this.path = path;
        this.contextLocationResource = new FileSystemResource(this.path);
    }
    
    /** <默认构造函数> */
    public DefaultFileDefinitionResourceDriver(
            FileSystemResource contextLocationResource) {
        super();
        AssertUtils.notNull(contextLocationResource,
                "contextLocationResource is empty.");
        this.contextLocationResource = contextLocationResource;
    }
    
    /**
     * @param fileDefinition
     * @return
     */
    @Override
    public FileDefinitionResource getResource(FileDefinition fileDefinition) {
        validateFileDefinition(fileDefinition);
        
        String relativePath = fileDefinition.getRelativePath();
        FileSystemResource resource = (FileSystemResource) this.contextLocationResource.createRelative(relativePath);
        
        FileDefinitionResource resResource = new DefaultFileDefinitionResource(
                resource);
        fileDefinition.setResource(resResource);
        return resResource;
    }
    
    /**
     * @param fileDefinition
     * @param input
     * @return
     */
    @Override
    public FileDefinitionResource add(FileDefinition fileDefinition,
            InputStream input) {
        validateFileDefinition(fileDefinition);
        
        String relativePath = fileDefinition.getRelativePath();
        FileSystemResource resource = (FileSystemResource) this.contextLocationResource.createRelative(relativePath);
        try {
            //如果对应的文件资源已经存在，则进行替换
            if (resource.exists()) {
                throw new ResourceIsExistException(
                        "resource is exist. path:{}",
                        new Object[] { relativePath });
            }
            writeFile(resource.getFile(), input);
        } catch (IOException e) {
            throw ExceptionWrapperUtils.wrapperIOException(e,
                    "save file error.filePath:{}",
                    relativePath);
        }
        FileDefinitionResource resFileDefinitionResource = new DefaultFileDefinitionResource(
                resource);
        return resFileDefinitionResource;
    }
    
    
    @Override
    public FileDefinitionResource save(FileDefinition fileDefinition,
            InputStream input) {
        validateFileDefinition(fileDefinition); // 检查文件合法性
        
        String relativePath = fileDefinition.getRelativePath(); // 存储路径
        FileSystemResource resource = (FileSystemResource) this.contextLocationResource.createRelative(relativePath);
        //如果对应的文件资源已经存在，则进行替换
        try {
            if (!resource.exists()) {
                //如果不存在，则检查文件所在的目录是否存在
                File newFile = resource.getFile();
                File fileFolder = newFile.getParentFile();
                if (!fileFolder.exists()) {
                    //如果目录不存在，则创建对应目录
                    FileUtils.forceMkdir(fileFolder);
                }
                newFile.createNewFile();
            }
            writeFile(resource.getFile(), input);
        } catch (IOException e) {
            throw ExceptionWrapperUtils.wrapperIOException(e,
                    "save file error.filePath:{}",
                    relativePath);
        }
        FileDefinitionResource resFileDefinitionResource = new DefaultFileDefinitionResource(
                resource);
        return resFileDefinitionResource;
    }
    
    /**
     * @param fileDefinition
     */
    @Override
    public void delete(FileDefinition fileDefinition) {
        validateFileDefinition(fileDefinition);
        
        String relativePath = fileDefinition.getRelativePath();
        FileSystemResource resource = (FileSystemResource) this.contextLocationResource.createRelative(relativePath);
        FileUtils.deleteQuietly(resource.getFile());
    }
    
    /**
      * 将输入流写入文件中
      * <功能详细描述>
      * @param file
      * @param input
      * @throws IOException [参数说明]
      * 
      * @return void [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    private void writeFile(File file, InputStream input) throws IOException {
        AssertUtils.notNull(file, "file is null.");
        AssertUtils.isExist(file, "file is not exist.");
        
        OutputStream output = null;
        try {
            output = new FileOutputStream(file);
            IOUtils.copy(input, output);
        } finally {
            IOUtils.closeQuietly(output);
        }
    }
    
    /** 
     * 校验文件定义的合法性<br/>
     * <功能详细描述>
     * @param fileDefinition [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    private void validateFileDefinition(FileDefinition fileDefinition) {
        AssertUtils.notNull(this.contextLocationResource,
                "this.contextLocationResource is null.");
        
        AssertUtils.notNull(fileDefinition, "fileDefinition is null.");
        AssertUtils.notEmpty(fileDefinition.getRelativePath(),
                "fileDefinition.relativePath is empty.");
    }
}
