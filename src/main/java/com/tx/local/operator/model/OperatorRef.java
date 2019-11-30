/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2019年11月29日
 * <修改描述:>
 */
package com.tx.local.operator.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户引用关联接口<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2019年11月29日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface OperatorRef extends Serializable {
    
    /**
     * 权限引用项唯一键<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    String getId();
    
    /**
     * 权限引用项的类型<br/>
     * 利用该类型<br/>
     * 实现              人员权限   AUTHREFTYPE_OPERATOR          <br/>
     *         临时权限   AUTHREFTYPE_OPERATOR_TEMP     <br/>
     *         角色权限   AUTHREFTYPE_ROLE              <br/>
     *         职位权限   ...
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    String getRefType();
    
    /**
     * 权限关联项id 
     * 可以是角色的id,
     * 可以是职位的id
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    String getRefId();
    
    /**
     * 获取创建人Id
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    String getCreateOperatorId();
    
    /**
     * 获取创建时间
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return Date [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    Date getCreateDate();
    
    /**
     * 返回权限引用项生效时间
     * when isTemp() == true 有效
     *<功能详细描述>
     * @return [参数说明]
     * 
     * @return Date [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    Date getEffectiveDate();
    
    /**
     * 返回权限引用项生效时间
     * when isTemp() == true 有效
     *<功能详细描述>
     * @return [参数说明]
     * 
     * @return Date [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    Date getExpiryDate();
    
    /**
     * 获取操作员id<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    String getOperatorId();
}
