/*
 * 描          述:  <描述>
 * 修  改   人:  PengQingyang
 * 修改时间:  2012-12-14
 * <修改描述:>
 */
package com.tx.components.auth.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.IOUtils;
import org.apache.cxf.common.util.StringUtils;
import org.apache.ibatis.io.ResolverUtil;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.io.Resource;

import com.tx.components.auth.AuthConstant;
import com.tx.components.auth.model.AuthItem;
import com.tx.components.auth.service.AuthChecker;
import com.tx.components.auth.service.AuthLoader;
import com.tx.core.exceptions.resource.ResourceLoadException;


 /**
  * 通过xml配置文件加载权限项
  * 1、通过指定权限配置的资源，加载权限资源后，生成系统权限集合
  * 
  * @author  PengQingyang
  * @version  [版本号, 2012-12-14]
  * @see  [相关类/方法]
  * @since  [产品/模块版本]
  */
public class XmlAuthLoader implements AuthLoader {
    
    /** 权限树根节点 */
    private AuthItem authItemTree;
    
    /** 权限项映射 */
    private Map<String, AuthItem> authItemMapping;
    
    /**
     * @return
     */
    @Override
    public Set<AuthItem> loadAuthItems() {
        // TODO Auto-generated method stub
        return null;
    }
    
    /**
     * 重新加载权限配置 <功能详细描述> [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public void reloadAuthItemConfig() {
        loadAuthItemConfig();
    }
    
    /**
     * 加载权限项配置 <功能详细描述> [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public void loadAuthItemConfig() {
        // 加载配置资源列表
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
        
        // 初始化局部权限映射以及，根权限树
        Map<String, AuthItem> authItemMap = new HashMap<String, AuthItem>();
        AuthItem authItemTree = new AuthItem();
        authItemTree.setId(AuthConstant.AUTH_ABS);
        authItemTree.setAuthType(AuthConstant.AUTH_ABS);
        authItemTree.setName(AuthConstant.AUTH_ABS_NAME);
        
        // 配置权限列表
        if (configResourceList == null || configResourceList.size() == 0) {
            // TODO:
            return;
        }
        
        // 加载配置资源集
        for (Resource resourceTemp : configResourceList) {
            SAXReader saxReader = new SAXReader();
            InputStream io = null;
            try {
                io = resourceTemp.getInputStream();
                Document doc = saxReader.read(io);
                Element rootElement = doc.getRootElement();
                // 根据配置资源加载权限
                loadAuthItemConfig(authItemMap, authItemTree, rootElement);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            finally {
                IOUtils.closeQuietly(io);
            }
        }
        
        this.authItemTree = authItemTree;
        this.authItemMapping = authItemMap;
    }
    
    /**
     * 加载权限配置项 <功能详细描述>
     * 
     * @param authItemMap
     * @param parentAuthItem
     * @param parentElement
     *            [参数说明]
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
        
        // 循环子权限列表
        for (Element authElTemp : authElList) {
            // 读取权限配置的属性值
            String id = authElTemp.attributeValue(AuthConstant.AUTH_ELEMENT_ATTR_ID);
            String name = authElTemp.attributeValue(AuthConstant.AUTH_ELEMENT_ATTR_NAME);
            String authType = authElTemp.attributeValue(AuthConstant.AUTH_ELEMENT_ATTR_AUTHTYPE);
            String description = authElTemp.attributeValue(AuthConstant.AUTH_ELEMENT_ATTR_DESCRIPTION);
            boolean isAbstract = false;
            // 如果为抽象权限，则设置权限id为抽象权限的权限type本身
            if (authType.endsWith(AuthConstant.ABSTRACT_AUTH_END)) {
                id = authType;
                isAbstract = true;
            }
            
            AuthItem newAuthItem = null;
            if (authItemMap.containsKey(id)) {
                // 如果对应权限已经存在则获取对应权限
                newAuthItem = authItemMap.get(id);
            }
            else {
                // 如果该权限原不存在则新生成
                newAuthItem = parentAuthItem.createChildAuthItem(id,
                        authType,
                        name,
                        isAbstract,
                        description);
            }
            
            // 加入子权限
            parentAuthItem.getChilds().add(newAuthItem);
            authItemMap.put(id, newAuthItem);
            
            // 迭代生成子权限
            loadAuthItemConfig(authItemMap, newAuthItem, authElTemp);
        }
    }
    
    /**
     * 获取权限配置资源列表 1、根据配置路径 authConfigLocaions 加载资源 <功能详细描述>
     * 
     * @return
     * @throws IOException
     *             [参数说明]
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
    

    
}
