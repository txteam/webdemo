/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2014年5月11日
 * <修改描述:>
 */
package com.tx.component.file.context;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.springframework.core.io.Resource;
import org.springframework.transaction.annotation.Transactional;

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
    
    private static Map<String, FileContext> contextMapping = new HashMap<String, FileContext>();
    
    protected FileContext context;
    
    /**
     * 
      * 保存文件<br/>
      *    如果文件已经存在，则复写当前文件<br/>
      *    如果文件不存在，则创建文件后写入<br/>
      *    如果对应文件所在的文件夹不存在，对应文件夹会自动创建<br/>
      * @param relativePath 
      * @param filename
      * @param input
      * @return [参数说明]
      * 
      * @return FileDefinition [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    @Transactional
    public FileDefinition save(String relativePath, String filename,
            InputStream input) {
        FileDefinition fileDefinition = doSaveFile(relativePath,
                filename,
                input);
        return fileDefinition;
    }
    
    /**
      * 保存文件<br/>
      *    如果文件已经存在，则会抛出ResourceIsExistException<br/>
      *    如果文件不存在，则创建文件后写入<br/>
      *    如果对应文件所在的文件夹不存在，对应文件夹会自动创建<br/>
      *<功能详细描述>
      * @param relativePath
      * @param filename
      * @param input
      * @return [参数说明]
      * 
      * @return FileDefinition [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    @Transactional
    public FileDefinition add(String relativePath, String filename,
            InputStream input) {
        FileDefinition fileDefinition = doAddFile(relativePath, filename, input);
        return fileDefinition;
    }
    
    /**
      * 根据文件定义id删除对应的文件定义及对应的资源<br/>
      * <功能详细描述>
      * @param fileDefinitionId [参数说明]
      * 
      * @return void [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    @Transactional
    public void deleteByFileDefinitionId(String fileDefinitionId) {
        doDeleteFileByFileDefinitionId(fileDefinitionId);
    }
    
    /**
      * 根据文件定义id获取对应的文件定义实例对象<br/>
      * <功能详细描述>
      * @param fileDefinitionId
      * @return [参数说明]
      * 
      * @return FileDefinition [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    @Transactional
    public FileDefinition findByFileDefinitionId(String fileDefinitionId) {
        FileDefinition res = doFindFileDefinitionByFileDefinitionId(fileDefinitionId);
        return res;
    }
    
    /**
      * 根据文件定义id获取文件定义对应的资源<br/> 
      * <功能详细描述>
      * @param fileDefinitionId
      * @return [参数说明]
      * 
      * @return Resource [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    @Transactional
    public Resource getResourceByFileDefinitionId(String fileDefinitionId) {
        FileDefinition res = doFindFileDefinitionByFileDefinitionId(fileDefinitionId);
        Resource resResource = res.getResource();
        return resResource;
    }
}
