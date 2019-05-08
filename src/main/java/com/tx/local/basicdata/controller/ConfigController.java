/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2014年2月19日
 * <修改描述:>
 */
package com.tx.local.basicdata.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tx.component.configuration.context.ConfigContext;
import com.tx.component.configuration.model.ConfigProperty;

/**
 * 配置属性控制器 <br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2014年2月19日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Controller
@RequestMapping("/config")
public class ConfigController {
    
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
    @RequestMapping("toQueryConfigPropertyMain")
    public String toQueryConfigPropertyMain(ModelMap responseMap) {
        return "/basicdata/queryConfigPropertyMain";
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
    public String toUpdateConfigProperty(@RequestParam("code") String code,
            ModelMap responseMap) {
        responseMap.put("configProperty", configContext.find(code));
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
    @ResponseBody
    @RequestMapping("update")
    public boolean update(String code, String value) {
        this.configContext.patch(code, value);
        return true;
    }
    
    /**
     * 查询所有的根节点属性<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<ConfigPropertyGroup> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("queryRootList")
    public List<ConfigProperty> queryRootList(
            @RequestParam Map<String, Object> params) {
        params = params == null ? new HashMap<>() : params;
        params.put("leaf", false);
        
        List<ConfigProperty> resList = configContext.queryList(params);
        return resList;
    }
    
    /**
     * 查询所有节点<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<ConfigProperty> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("queryList")
    public List<ConfigProperty> queryList(
            @RequestParam Map<String, Object> params) {
        List<ConfigProperty> resList = configContext.queryList(null);
        return resList;
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
    @RequestMapping("queryChildrenByParentId")
    public List<ConfigProperty> queryChildrenByParentId(
            @RequestParam(required = false, name = "parentId") String parentId,
            @RequestParam Map<String, Object> params) {
        List<ConfigProperty> resList = null;
        if (StringUtils.isEmpty(parentId)) {
            resList = configContext.queryList(params);
        } else {
            resList = configContext.queryChildrenByParentId(parentId, params);
        }
        return resList;
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
    @RequestMapping("queryDescendantsByParentId")
    public List<ConfigProperty> queryDescendantsByParentId(
            @RequestParam(required = false, name = "parentId") String parentId,
            @RequestParam Map<String, Object> params) {
        List<ConfigProperty> resList = null;
        if (StringUtils.isEmpty(parentId)) {
            resList = configContext.queryList(params);
        } else {
            resList = configContext.queryDescendantsByParentId(parentId,
                    params);
        }
        return resList;
    }
    
}
