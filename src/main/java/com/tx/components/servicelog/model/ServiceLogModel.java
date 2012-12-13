/*
 * 描          述:  <描述>
 * 修  改   人:  brady
 * 修改时间:  2012-12-12
 * <修改描述:>
 */
package com.tx.components.servicelog.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;


 /**
  * 业务日志模型类
  * <功能详细描述>
  * 
  * @author  brady
  * @version  [版本号, 2012-12-12]
  * @see  [相关类/方法]
  * @since  [产品/模块版本]
  */
@Entity
@Table(name="los_servicelog")
public class ServiceLogModel {
    
    /** 业务日志主键id */
    private String id;
    
    /** 业务日志创建时间 */
    private Date createDate;
    
    /** 业务日志信息 */
    private String message;
    
    /** 类名 */
    private String className;
    
    /** 方法名 */
    private String methodName;
    
    /** 业务模块信息 */
    private String module;
    
    /** 是否是controller中记录的日志 */
    private boolean isControllerLog;
    
    /** 
     * 当前操作人id 可为空，
     * 如果当前不存在操作人员，将记录为空
     * 将记录当前操作人员id 
     */
    private String currentOperatorId;
    
    private String xx;
}
