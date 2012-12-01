/*
 * 描          述:  <描述>
 * 修  改   人:  brady
 * 修改时间:  2012-11-30
 * <修改描述:>
 */
package com.tx.component.auth.service;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.cxf.common.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tx.component.auth.model.AuthItemRef;
import com.tx.core.exceptions.parameter.ParameterIsEmptyException;

/**
 * <权限处理业务层>
 * <功能详细描述>
 * 
 * @author  brady
 * @version  [版本号, 2012-11-30]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class AuthService {
    
    private String adminId = "admin";
    
    /** 日志记录器 */
    private Logger logger = LoggerFactory.getLogger(AuthService.class);
    
    /** 业务日志记录器，默认使用logback日志，可注入业务日志记录器记录业务日志  */
    private Logger serviceLogger = LoggerFactory.getLogger(AuthService.class);
    
    /**
      *<根据登录人id查询权限项列表>
      * 后续通过重载该方法可以针对具体的项目让authContext修改性提出去
      * @param operatorId
      * @return [参数说明]
      * 
      * @return List<AuthItemRef> [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    protected List<AuthItemRef> queryAuthItemRefList(String operatorId) {
        if (StringUtils.isEmpty(operatorId)) {
            throw new ParameterIsEmptyException(
                    "queryAuthItemRefListByOperatorId(operatorId) operatorId is empty.");
        }
        
        //TODO:
        return null;
    }
    
    /**
      *<判断是否为admin>
      * 如果被判断认为是超级管理员
      * 系统将默认该人员具有所有权限
      * 可以通过扩展该方法支持管理员组的概念
      * 如果为组，可考虑在系统初始化时将管理员组的operatorId加载于容器中
      * 然后利用contains判断即可
      * 
      * @param userId 入参为当前登录人员id
      * @return [参数说明]
      * 
      * @return boolean [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    protected boolean isAdmin(String userId) {
        return adminId.equals(userId);
    }
    
    /**
      *<更新权限引用项，并记录相应的业务日志>
      *<功能详细描述>
      * @return [参数说明]
      * 
      * @return boolean [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public boolean updateAuthItemRef(String operId, String refId,
            List<String> newAuthIds, String authRefType) {
        if (StringUtils.isEmpty(operId) || StringUtils.isEmpty(refId)
                || StringUtils.isEmpty(authRefType)) {
            throw new ParameterIsEmptyException(
                    "updateAuthItemRef(operatorId) operatorId is empty.");
        }
        
        //TODO:
        return false;
    }
    
    /**
     *<根据操作员以及权限引用类型查询当前操作人员具有哪些权限>
     *<功能详细描述>
     * @param operatorId
     * @param authRefType
     * @return [参数说明]
     * 
     * @return List<AuthItemRef> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
    public List<AuthItemRef> queryAuthItemRefListByAuthRefType(String userId,
            String authRefType) {
        if (StringUtils.isEmpty(userId) || StringUtils.isEmpty(authRefType)) {
            throw new ParameterIsEmptyException(
                    "queryAuthItemRefListByAuthRefType(operatorId) operatorId is empty.");
        }
        
        //TODO:
        return null;
    }
    
    /**
      *<删除日志项>
      *<功能详细描述>
      * @param userId
      * @param refId
      * @param newAuthIds
      * @param authRefType [参数说明]
      * 
      * @return void [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    private void delAuthIds(String userId, String refId,
            List<String> newAuthIds, String authRefType) {
        
        //TODO:
        serviceLogger.info(" {}于 {} 删除类型为{}的日志引用{}.", new String[] { userId,
                DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"),
                authRefType, ArrayUtils.toString(newAuthIds) });
    }
    
    /**
      *<新增日志项>
      *<功能详细描述>
      * @param userId
      * @param refId
      * @param newAuthIds
      * @param authRefType [参数说明]
      * 
      * @return void [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    private void addAuthIds(String userId, String refId,
            List<String> newAuthIds, String authRefType) {
        
        //TODO:
        serviceLogger.info(" {}于 {} 新增类型为{}的日志引用{}.", new String[] { userId,
                DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"),
                authRefType, ArrayUtils.toString(newAuthIds) });
    }
}
