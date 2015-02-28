/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2014年12月21日
 * <修改描述:>
 */
package com.tx.component.file.context;

import java.io.InputStream;

import com.tx.component.file.model.FileDefinition;

/**
 * 文件定义资源驱动<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2014年12月21日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface FileDefinitionResourceDriver {
    
    /**
      * 获取资源对象<br/>
      * <功能详细描述>
      * @param fileDefinition
      * @return [参数说明]
      * 
      * @return Resource [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public FileDefinitionResource getResource(FileDefinition fileDefinition);
    
    /**
      * 存储文件<br/>
      *    如果文件已经存在，则进行替换
      *    如果不存在，则新建后进行写入
      *    如果目录不存在,则自动创建目录
      * <功能详细描述>
      * @param fileDefinition
      * @param input
      * @return [参数说明]
      * 
      * @return FileDefinitionResource [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public FileDefinitionResource save(FileDefinition fileDefinition,
            InputStream input);
    
    /**
      * 新增文件
      *    如果对应文件已经存在，则抛出异常<br/>
      *<功能详细描述>
      * @param fileDefinition
      * @param input
      * @return [参数说明]
      * 
      * @return FileDefinitionResource [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public FileDefinitionResource add(FileDefinition fileDefinition,
            InputStream input);
    
    /**
      * 删除对应的文件资源<br/> 
      * <功能详细描述>
      * @param fileDefinition [参数说明]
      * 
      * @return void [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public void delete(FileDefinition fileDefinition);
}
