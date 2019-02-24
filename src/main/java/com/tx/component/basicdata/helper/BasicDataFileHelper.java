/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2017年2月19日
 * <修改描述:>
 */
package com.tx.component.basicdata.helper;

import org.springframework.util.StringUtils;

import com.tx.core.exceptions.util.AssertUtils;

/**
 * 基础数据源文件存储辅助类<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2017年2月19日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public abstract class BasicDataFileHelper {
    
    /**
     * 生成文件保存路径<br/>
     * <功能详细描述>
     * @param relativePath
     * @param filenameExtension
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
    public static String generateRelativePath(String relativePath) {
        AssertUtils.notEmpty(relativePath, "relativePath is empty.");
        
        //整理存储的relativePath相对路径
        relativePath = generateRelativePath(relativePath, null);
        return relativePath;
    }
    
    /**
     * 生成文件保存路径<br/>
     * <功能详细描述>
     * @param relativePath
     * @param filenameExtension
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
    public static String generateRelativePath(String relativePath,
            String filenameExtension) {
        AssertUtils.notEmpty(relativePath, "relativePath is empty.");
        
        //整理存储的relativePath相对路径
        relativePath = StringUtils.cleanPath(relativePath);
        while (relativePath.startsWith("/")) {
            //去除相对路径前面的"/"
            relativePath = relativePath.substring(1, relativePath.length());
        }
        if (!StringUtils.isEmpty(filenameExtension)) {
            if (!relativePath.endsWith(filenameExtension)) {
                //为存储相对路径追加后缀
                relativePath = relativePath + "." + filenameExtension;
            }
        }
        return relativePath;
    }
}
