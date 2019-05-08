/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2017年3月5日
 * <修改描述:>
 */
package com.tx.local.content.context;

import com.tx.local.content.model.ContentInfo;

/**
 * 内容类型处理器<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2017年3月5日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface ContentTypeHandler {
    
    /**
     * 内容类型<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    String type();
    
    /**
     * 内容类型名<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    String name();
    
    /**
     * 内容类型备注<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    String remark();
    
    /**
     * 添加内容的视图<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    String addView();
    
    /**
     * 添加内容的视图<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    String updateView();
    
    /**
     * 预处理内容<br/>
     * <功能详细描述>
     * @param contentInfo [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    void validate(ContentInfo contentInfo);
    
    /**
     * 预处理内容<br/>
     * <功能详细描述>
     * @param contentInfo [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    void doBeforeInsertHandle(ContentInfo contentInfo);
    
    /**
     * 预处理内容<br/>
     * <功能详细描述>
     * @param contentInfo [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    void doBeforeUpdateHandle(ContentInfo contentInfo);
    
    /**
     * 预处理内容<br/>
     * <功能详细描述>
     * @param contentInfo [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    void doAfterDeleteHandle(ContentInfo contentInfo);
    
    /**
     * 预处理内容<br/>
     * <功能详细描述>
     * @param contentInfo [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    void doAfterFindHandle(ContentInfo contentInfo);
}
