/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2014年5月11日
 * <修改描述:>
 */
package com.tx.component.file.context;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.util.StringUtils;

import com.tx.component.file.model.FileDefinition;
import com.tx.component.file.service.FileDefinitionService;
import com.tx.core.dbscript.TableDefinition;
import com.tx.core.dbscript.XMLTableDefinition;
import com.tx.core.exceptions.util.AssertUtils;

/**
 * 文件容器构建器<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2014年5月11日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class FileContextBuilder extends FileContextConfigurator implements
        ApplicationContextAware, BeanNameAware {
    
    /** 权限表定义文件路径 */
    private static final String fileDefinitionLocation = "classpath:/com/tx/component/file/script/core_file_definition.xml";
    
    /** 权限引用表定义文件路径 */
    private static final String fileDefinitionHisLocation = "classpath:/com/tx/component/file/script/core_file_definition_his.xml";
    
    /** 容器名称 */
    protected String name;
    
    /** spring容器句柄 */
    protected ApplicationContext applicationContext;
    
    @Resource(name = "fileDefinitionService")
    private FileDefinitionService fileDefinitionService;
    
    /**
     * @param name
     */
    @Override
    public void setBeanName(String name) {
        this.name = name;
    }
    
    /**
     * @param applicationContext
     * @throws BeansException
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext)
            throws BeansException {
        this.applicationContext = applicationContext;
    }
    
    /**
     * InitializingBean接口的实现，用以在容器参数设置完成后加载相关权限
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        logger.info("初始化文件管理容器start...");
        databaseSchemaUpdate();
        
        super.afterPropertiesSet();
        logger.info("初始化文件管理容器end...");
    }
    
    /** 
     * 执行脚本自动升级
     * <功能详细描述> [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    private void databaseSchemaUpdate() {
        if (databaseSchemaUpdate && dbScriptExecutorContext != null) {
            TableDefinition fileDefinitionTable = new XMLTableDefinition(
                    fileDefinitionLocation);
            TableDefinition fileDefinitionHisTable = new XMLTableDefinition(
                    fileDefinitionHisLocation);
            
            this.dbScriptExecutorContext.createOrUpdateTable(fileDefinitionTable);
            this.dbScriptExecutorContext.createOrUpdateTable(fileDefinitionHisTable);
            
            logger.info(" 自动初始化权限容器表结构完成.表后缀名为：{}...");
        }
    }
    
    /**
      * 返回文件定义对象<br/>
      *<功能详细描述>
      * @param fileDefinitionId
      * @return [参数说明]
      * 
      * @return FileDefinition [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    protected FileDefinition doFindFileDefinitionByFileDefinitionId(
            String fileDefinitionId) {
        FileDefinition fileDefinition = this.fileDefinitionService.findFileDefinitionById(fileDefinitionId);
        FileDefinitionResource resource = this.driver.getResource(fileDefinition);
        fileDefinition.setResource(resource);
        return fileDefinition;
    }
    
    /**
      * 删除对应的文件，并将对应文件的相关记录移除到历史表中
      * <功能详细描述>
      * @param fileDefinitionId [参数说明]
      * 
      * @return void [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    protected void doDeleteFileByFileDefinitionId(String fileDefinitionId) {
        AssertUtils.notEmpty(fileDefinitionId, "fileDefinitionId is empty.");
        FileDefinition fileDefinition = this.fileDefinitionService.findFileDefinitionById(fileDefinitionId);
        AssertUtils.notNull(fileDefinition,
                "fileDefinition is null.fileDefinitionId:{}",
                fileDefinitionId);
        
        this.fileDefinitionService.moveToHisByFileDefinitionId(fileDefinitionId);
        
        this.driver.delete(fileDefinition);
    }
    
    /**
     * 
      *<保存文件，允许存在的文件写入>
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
    protected FileDefinition doSaveFile(String relativePath, String filename,
            InputStream input) {
        //持久化对应的文件对象
        FileDefinition fileDefinition = buildAndPersistFileDefinition(relativePath,
                filename);
        //对应的相对路径不存在，则创建对应的路径
        this.driver.save(fileDefinition, input);
        return fileDefinition;
    }
    
    /**
      * 新增文件，如果文件已经存在，将会抛出异常<br/> 
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
    protected FileDefinition doAddFile(String relativePath, String filename,
            InputStream input) {
        //持久化对应的文件对象
        FileDefinition fileDefinition = buildAndPersistFileDefinition(relativePath,
                filename);
        //对应的相对路径不存在，则创建对应的路径
        FileDefinitionResource resource = this.driver.add(fileDefinition, input);
        fileDefinition.setResource(resource);
        return fileDefinition;
    }
    
    /**
      * 创建fileDefinition并持久化对应的文件定义<br/>
      * <功能详细描述>
      * @param relativePath
      * @param filename
      * @return [参数说明]
      * 
      * @return FileDefinition [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    private FileDefinition buildAndPersistFileDefinition(String relativePath,
            String filename) {
        Date now = new Date();
        FileDefinition fileDefinition = new FileDefinition();
        fileDefinition.setCreateDate(now);
        fileDefinition.setFilename(filename);
        fileDefinition.setFilenameExtension(StringUtils.getFilenameExtension(relativePath));
        fileDefinition.setLastUpdateDate(now);
        fileDefinition.setModule(this.module);
        fileDefinition.setSystem(this.system);
        fileDefinition.setRelativePath(relativePath);
        
        this.fileDefinitionService.insertFileDefinition(fileDefinition);
        return fileDefinition;
    }
}
