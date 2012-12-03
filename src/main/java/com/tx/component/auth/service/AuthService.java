/*
g * 描          述:  <描述>
 * 修  改   人:  brady
 * 修改时间:  2012-11-30
 * <修改描述:>
 */
package com.tx.component.auth.service;

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

import com.tx.component.auth.AuthConstant;
import com.tx.component.auth.dao.AuthDao;
import com.tx.component.auth.model.AuthItem;
import com.tx.component.auth.model.AuthItemRef;
import com.tx.core.exceptions.parameter.ParameterIsEmptyException;
import com.tx.core.exceptions.resource.ResourceLoadException;

/**
 * <权限处理业务层>
 * <功能详细描述>
 * 
 * @author  brady
 * @version  [版本号, 2012-11-30]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Component("authService")
public class AuthService implements ApplicationContextAware {
    
    /** 日志记录器 */
    private static final Logger logger = LoggerFactory.getLogger(AuthService.class);
    
    /** 业务日志记录器，默认使用logback日志，可注入业务日志记录器记录业务日志  */
    private Logger serviceLogger = LoggerFactory.getLogger(AuthService.class);
    
    private String adminId = "admin";
    
    /** context */
    private ApplicationContext context = null;
    
    /** 权限配置地址 */
    private String[] authConfigLocaions = new String[] { "classpath:authcontext/*_auth_config.xml" };
    
    @javax.annotation.Resource(name = "authDao")
    private AuthDao authDao;
    
    /**
     * @param applicationContext
     * @throws BeansException
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext)
            throws BeansException {
        this.context = applicationContext;
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
        
        List<AuthItemRef> authItemRefList = this.authDao.queryItemAuthRefListByOperId(operatorId);
        return authItemRefList;
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
                    "queryAuthItemRefListByAuthRefType({},{}) operatorId is empty.",
                    userId, authRefType);
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
    public void delAuthIds(String userId, String refId, String authRefType,
            List<String> newAuthIds) {
        
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
    public void addAuthIds(String userId, String refId,
            List<String> newAuthIds, String authRefType) {
        
        //TODO:
        serviceLogger.info(" {}于 {} 新增类型为{}的日志引用{}.", new String[] { userId,
                DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"),
                authRefType, ArrayUtils.toString(newAuthIds) });
    }
    
    /**
      * 重新加载权限配置
      *<功能详细描述> [参数说明]
      * 
      * @return void [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public void reloadAuthItemConfig() {
        loadAuthItemConfig();
    }
    
    /**
      * 加载权限项配置
      *<功能详细描述> [参数说明]
      * 
      * @return void [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    private void loadAuthItemConfig() {
        //加载配置资源列表
        List<Resource> configResourceList = null;
        try {
            configResourceList = getConfigResourceList();
        }
        catch (Exception e) {
            logger.error("加载权限配置异常信息配置路径为:{}异常信息{}",
                    this.authConfigLocaions,
                    e.toString());
            logger.error("加载权限配置失败", e);
            throw new ResourceLoadException("权限配置加载异常.", e);
        }
        
        //初始化局部权限映射以及，根权限树
        Map<String, AuthItem> authItemMap = new HashMap<String, AuthItem>();
        AuthItem authItemTree = new AuthItem();
        authItemTree.setId(AuthConstant.AUTH_ABS);
        authItemTree.setAuthType(AuthConstant.AUTH_ABS);
        authItemTree.setName(AuthConstant.AUTH_ABS_NAME);
        
        //配置权限列表
        if (configResourceList == null || configResourceList.size() == 0) {
            //TODO:
            return;
        }
        
        //加载配置资源集
        for (Resource resourceTemp : configResourceList) {
            SAXReader saxReader = new SAXReader();
            InputStream io = null;
            try {
                io = resourceTemp.getInputStream();
                Document doc = saxReader.read(io);
                Element rootElement = doc.getRootElement();
                //根据配置资源加载权限
                loadAuthItemConfig(authItemMap, authItemTree, rootElement);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            finally {
                IOUtils.closeQuietly(io);
            }
        }
        
    }
    
    /**
      * 加载权限配置项
      *<功能详细描述>
      * @param authItemMap
      * @param parentAuthItem
      * @param parentElement [参数说明]
      * 
      * @return void [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    private void loadAuthItemConfig(Map<String, AuthItem> authItemMap,
            AuthItem parentAuthItem, Element parentElement) {
        @SuppressWarnings("unchecked")
        List<Element> authElList = parentElement.elements(AuthConstant.AUTH_ELEMENT_NAME);
        if (CollectionUtils.isEmpty(authElList)) {
            return;
        }
        
        //循环子权限列表
        for (Element authElTemp : authElList) {
            //读取权限配置的属性值
            String id = authElTemp.attributeValue(AuthConstant.AUTH_ELEMENT_ATTR_ID);
            String name = authElTemp.attributeValue(AuthConstant.AUTH_ELEMENT_ATTR_NAME);
            String authType = authElTemp.attributeValue(AuthConstant.AUTH_ELEMENT_ATTR_AUTHTYPE);
            String description = authElTemp.attributeValue(AuthConstant.AUTH_ELEMENT_ATTR_DESCRIPTION);
            boolean isAbstract = false;
            //如果为抽象权限，则设置权限id为抽象权限的权限type本身
            if (authType.endsWith(AuthConstant.ABSTRACT_AUTH_END)) {
                id = authType;
                isAbstract = true;
            }
            
            AuthItem newAuthItem = null;
            if (authItemMap.containsKey(id)) {
                //如果对应权限已经存在则获取对应权限
                newAuthItem = authItemMap.get(id);
            }
            else {
                //如果该权限原不存在则新生成
                newAuthItem = parentAuthItem.createChildAuthItem(id,
                        authType,
                        name,
                        isAbstract,
                        description);
            }
            
            //加入子权限
            parentAuthItem.getChilds().add(newAuthItem);
            authItemMap.put(id, newAuthItem);
            
            //迭代生成子权限
            loadAuthItemConfig(authItemMap, newAuthItem, authElTemp);
        }
    }
    
    /**
      * 获取权限配置资源列表
      * 1、根据配置路径  authConfigLocaions 加载资源
      * <功能详细描述>
      * @return
      * @throws IOException [参数说明]
      * 
      * @return List<Resource> [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    private List<Resource> getConfigResourceList() throws IOException {
        List<Resource> configResourceList = new ArrayList<Resource>();
        for (String location : this.authConfigLocaions) {
            Resource[] resources = this.context.getResources(location);
            if (resources == null || resources.length == 0) {
                continue;
            }
            for (Resource resourceTemp : resources) {
                if (!resourceTemp.exists()) {
                    continue;
                }
                configResourceList.add(resourceTemp);
            }
        }
        return configResourceList;
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
}
