/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2014年2月19日
 * <修改描述:>
 */
package com.tx.component.mainframe.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tx.component.auth.annotation.CheckOperateAuth;
import com.tx.component.configuration.context.ConfigContext;
import com.tx.component.configuration.model.ConfigProperty;
import com.tx.component.configuration.model.ConfigPropertyGroup;
import com.tx.component.mainframe.treeview.TreeNode;
import com.tx.component.mainframe.treeview.TreeNodeAdapter;
import com.tx.component.mainframe.treeview.TreeNodeUtils;
import com.tx.core.tree.util.TreeUtils;

/**
 * 配置属性控制器 <br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2014年2月19日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Controller("configurationController")
@RequestMapping("/configuration")
public class ConfigurationController {
    
    public static final int TREENODE_TYPE_CONFIGGROUP = 0;
    
    public static final int TREENODE_TYPE_CONFIGPROPERTY = 1;
    
    @Resource(name = "configContext")
    private ConfigContext configContext;
    
    /**
      * 查询配置属性页<br/>
      *<功能详细描述>
      * @return [参数说明]
      * 
      * @return String [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    @RequestMapping("toQueryConfigProperty")
    public String toQueryConfigProperty(ModelMap responseMap) {
        return "/mainframe/queryConfigProperty";
    }
    
    /**
      * 跳转到编辑配置属性页<br/>
      *<功能详细描述>
      * @param responseMap
      * @return [参数说明]
      * 
      * @return String [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    @RequestMapping("toUpdateConfigProperty")
    public String toUpdateConfigProperty(@RequestParam("key") String key,
            ModelMap responseMap) {
        responseMap.put("configProperty",
                configContext.getConfigPropertyByKey(key));
        return "/mainframe/updateConfigProperty";
    }
    
    /**
      * 更新配置属性值<br/>
      *<功能详细描述>
      * @param key
      * @param value
      * @return [参数说明]
      * 
      * @return boolean [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    @CheckOperateAuth(key = "update_configProperty", parentKey = "config_config", name = "修改配置项")
    @ResponseBody
    @RequestMapping("updateConfigPropertyValue")
    public boolean updateConfigPropertyValue(String key, String value) {
        configContext.getConfigPropertyByKey(key).update(value);
        return true;
    }
    
    /**
      * 查询所有的配置属性组
      *<功能详细描述>
      * @return [参数说明]
      * 
      * @return List<ConfigPropertyGroup> [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("getAllConfigPropertyGroup")
    public List<ConfigPropertyGroup> getAllConfigPropertyGroup() {
        List<ConfigPropertyGroup> configPropertyGroupList = configContext.getAllConfigPropertyGroup();
        return configPropertyGroupList;
    }
    
    /**
      * 获取配置属性树节点<br/>
      *<功能详细描述>
      * @param configPropertyGroupName
      * @return [参数说明]
      * 
      * @return List<TreeNode> [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("getConfigPropertyTreeNodeList")
    public List<TreeNode> getConfigPropertyTreeNodeList(
            @RequestParam(value = "configPropertyGroupName", required = false) String configPropertyGroupName) {
        List<ConfigPropertyGroup> configPropertyGroupList = new ArrayList<ConfigPropertyGroup>(
                configContext.getAllConfigPropertyGroup());
        List<ConfigProperty> configPropertyList = new ArrayList<ConfigProperty>(
                configContext.getAllConfigPropertyMap().values());
        
        List<TreeNode> resList = new ArrayList<TreeNode>();
        resList.addAll(TreeNodeUtils.transformedList(configPropertyGroupList,
                configGroupAdapter));
        resList.addAll(TreeNodeUtils.transformedList(configPropertyList,
                configPropertyAdapter));
        
        List<TreeNode> resTreeList = TreeUtils.changeToTree(resList);
        if (StringUtils.isEmpty(configPropertyGroupName)) {
            return resTreeList;
        } else {
            resTreeList = new ArrayList<TreeNode>();
            for (TreeNode treeNodeTemp : resList) {
                if (configPropertyGroupName.equals(treeNodeTemp.getParentId())) {
                    resTreeList.add(treeNodeTemp);
                }
            }
            return resTreeList;
        }
    }
    
    private static final TreeNodeAdapter<ConfigPropertyGroup> configGroupAdapter = new TreeNodeAdapter<ConfigPropertyGroup>() {
        
        @Override
        public Object getTarget(ConfigPropertyGroup obj) {
            return obj;
        }
        
        @Override
        public String getId(ConfigPropertyGroup obj) {
            return obj.getName();
        }
        
        @Override
        public int getType(ConfigPropertyGroup obj) {
            return ConfigurationController.TREENODE_TYPE_CONFIGGROUP;
        }
        
        @Override
        public String getParentId(ConfigPropertyGroup obj) {
            return obj.getParentName();
        }
        
        @Override
        public String getName(ConfigPropertyGroup obj) {
            return obj.getName();
        }
    };
    
    private static final TreeNodeAdapter<ConfigProperty> configPropertyAdapter = new TreeNodeAdapter<ConfigProperty>() {
        
        @Override
        public Object getTarget(ConfigProperty obj) {
            return obj;
        }
        
        @Override
        public String getId(ConfigProperty obj) {
            return obj.getKey();
        }
        
        @Override
        public int getType(ConfigProperty obj) {
            return ConfigurationController.TREENODE_TYPE_CONFIGPROPERTY;
        }
        
        @Override
        public String getParentId(ConfigProperty obj) {
            return obj.getConfigPropertyGroupName();
        }
        
        @Override
        public String getName(ConfigProperty obj) {
            return obj.getName();
        }
    };
    
}
