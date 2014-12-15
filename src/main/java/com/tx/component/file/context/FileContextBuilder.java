/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2014年5月11日
 * <修改描述:>
 */
package com.tx.component.file.context;

import java.io.InputStream;

import org.apache.commons.io.IOUtils;

/**
 * 文件容器构建器<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2014年5月11日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class FileContextBuilder extends FileContextConfigurator {
    
    protected String getCurrentOperatorId() {
        
        return "";
    }
    
    protected String getCurrentVcid() {
        
        return "";
    }
    
    protected String getCurrentOrganizationId() {
        
        return "";
    }
    
    /**
      * 将文件流存放于实际的文件中<br/>
      *    对于不同的系统，不同的模块在存放文件时可能需要存放在不同的路径中
      *    在该方法中，将对指定路径的文件的存放文件夹，优先进行创建（如果没有创建的话）
      *    不同的系统的相同文件可能存在于一个路径中
      *    指定的存放路径可以为一个相对路径，或者是一个绝对路径
      *    相对路径，如果能够映射到对应的locationMap中的设置，则使用指定的路径
      *    如果没有指定路径，则使用默认路径
      * <功能详细描述>
      * @param inputStream [参数说明]
      * 
      * @return void [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    protected void doSaveFile(InputStream inputStream){
        
    }
    
}
