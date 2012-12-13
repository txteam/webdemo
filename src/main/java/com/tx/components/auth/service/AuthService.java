/*
g * 描          述:  <描述>
 * 修  改   人:  brady
 * 修改时间:  2012-11-30
 * <修改描述:>
 */
package com.tx.components.auth.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.cxf.common.util.StringUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.tx.components.auth.AuthConstant;
import com.tx.components.auth.dao.AuthDao;
import com.tx.components.auth.model.AuthItem;
import com.tx.components.auth.model.AuthItemRef;
import com.tx.core.exceptions.parameter.ParameterIsEmptyException;
import com.tx.core.exceptions.resource.ResourceLoadException;

/**
 * <权限处理业务层> <功能详细描述>
 * 
 * @author brady
 * @version [版本号, 2012-11-30]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Component("authService")
public class AuthService{
    
    /** 日志记录器 */
    private static final Logger logger = LoggerFactory.getLogger(AuthService.class);
    
    /** 业务日志记录器，默认使用logback日志，可注入业务日志记录器记录业务日志 */
    private Logger serviceLogger = LoggerFactory.getLogger(AuthService.class);
    
    private String adminId = "admin";
    
    /** 权限配置地址 */
    private String[] authConfigLocaions = new String[] { "classpath:authcontext/*_auth_config.xml" };
    
    @javax.annotation.Resource(name = "authDao")
    private AuthDao authDao;
    

    
    /**
     * <判断是否为admin> 如果被判断认为是超级管理员 系统将默认该人员具有所有权限 可以通过扩展该方法支持管理员组的概念
     * 如果为组，可考虑在系统初始化时将管理员组的operatorId加载于容器中 然后利用contains判断即可
     * 
     * @param userId
     *            入参为当前登录人员id
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
     * <根据登录人id查询权限项列表> 后续通过重载该方法可以针对具体的项目让authContext修改性提出去
     * 
     * @param operatorId
     * @return [参数说明]
     * 
     * @return List<AuthItemRef> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public List<AuthItemRef> queryAuthItemRefSetByOperatorId(String operatorId) {
        if (StringUtils.isEmpty(operatorId)) {
            throw new ParameterIsEmptyException(
                    "queryAuthItemRefSetByOperatorId(operatorId) operatorId is empty.");
        }
        
        List<AuthItemRef> authItemRefList = this.authDao.queryItemAuthRefListByOperId(operatorId);
        return authItemRefList;
    }
    
    /**
     * <更新权限引用项，并记录相应的业务日志> <功能详细描述>
     * 
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Transactional
    public void updateAuthItemRef(String operId, String refId,
            String authRefType, List<String> newAuthIds) {
        if (StringUtils.isEmpty(operId) || StringUtils.isEmpty(refId)
                || StringUtils.isEmpty(authRefType)) {
            throw new ParameterIsEmptyException(
                    "updateAuthItemRef({}) operatorId is empty.", refId);
        }
        
    }
    
    /**
     * <根据操作员以及权限引用类型查询当前操作人员具有哪些权限> <功能详细描述>
     * 
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
                    "queryAuthItemRefListByAuthRefType({},{}) operatorId is empty.",
                    userId, authRefType);
        }
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("operatorId", userId);
        params.put("authRefType", authRefType);
        List<AuthItemRef> authItemRefList = this.authDao.queryItemAuthRefList(params);
        return authItemRefList;
    }
    
    /**
     * <删除日志项> <功能详细描述>
     * 
     * @param userId
     * @param refId
     * @param newAuthIds
     * @param authRefType
     *            [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public void delAuthIds(String userId, String refId, String authRefType,
            List<String> newAuthIds) {
        List<AuthItemRef> authItemRefList = new ArrayList<AuthItemRef>();
        AuthItemRef authItemRef = null;
        for (String newAuthId : newAuthIds) {
            authItemRef = new AuthItemRef();
            authItemRef.setCreateDate(new Date());
            authItemRef.setAuthRefType(authRefType);
            authItemRef.setRefId(refId);
            authItemRef.setCreateOperId(userId);
            authItemRef.setAuthId(newAuthId);
            authItemRefList.add(authItemRef);
        }
        this.authDao.delAuthItemRefList(authItemRefList);
        serviceLogger.info(" {}于 {} 删除类型为{}的日志引用{}.", new String[] { userId,
                DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"),
                authRefType, ArrayUtils.toString(newAuthIds) });
    }
    
    /**
     * <新增日志项> <功能详细描述>
     * 
     * @param userId
     * @param refId
     * @param newAuthIds
     * @param authRefType
     *            [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public void addAuthIds(String userId, String refId,
            List<String> newAuthIds, String authRefType) {
        List<AuthItemRef> authItemRefList = new ArrayList<AuthItemRef>();
        AuthItemRef authItemRef = null;
        for (String newAuthId : newAuthIds) {
            authItemRef = new AuthItemRef();
            authItemRef.setCreateDate(new Date());
            authItemRef.setAuthRefType(authRefType);
            authItemRef.setRefId(refId);
            authItemRef.setCreateOperId(userId);
            authItemRef.setAuthId(newAuthId);
            authItemRefList.add(authItemRef);
        }
        this.authDao.addAuthItemRefList(authItemRefList);
        serviceLogger.info(" {}于 {} 新增类型为{}的日志引用{}.", new String[] { userId,
                DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"),
                authRefType, ArrayUtils.toString(newAuthIds) });
    }
    

    
    /**
     * @return 返回 adminId
     */
    public String getAdminId() {
        return adminId;
    }
    
    /**
     * @param 对adminId进行赋值
     */
    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }
    
    /**
     * @return 返回 serviceLogger
     */
    public Logger getServiceLogger() {
        return serviceLogger;
    }
    
    /**
     * @param 对serviceLogger进行赋值
     */
    public void setServiceLogger(Logger serviceLogger) {
        this.serviceLogger = serviceLogger;
    }
    
    /**
     * @return 返回 authConfigLocaions
     */
    public String[] getAuthConfigLocaions() {
        return authConfigLocaions;
    }
    
    /**
     * @param 对authConfigLocaions进行赋值
     */
    public void setAuthConfigLocaions(String[] authConfigLocaions) {
        this.authConfigLocaions = authConfigLocaions;
    }
    
    /**
     * @return 返回 authItemTree
     */
    public AuthItem getAuthItemTree() {
        return authItemTree;
    }
    
    /**
     * @param 对authItemTree进行赋值
     */
    public void setAuthItemTree(AuthItem authItemTree) {
        this.authItemTree = authItemTree;
    }
    
    /**
     * @return 返回 authItemMapping
     */
    public Map<String, AuthItem> getAuthItemMapping() {
        return authItemMapping;
    }
    
    /**
     * @param 对authItemMapping进行赋值
     */
    public void setAuthItemMapping(Map<String, AuthItem> authItemMapping) {
        this.authItemMapping = authItemMapping;
    }
}
