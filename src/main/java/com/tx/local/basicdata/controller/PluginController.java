/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2019年12月13日
 * <修改描述:>
 */
package com.tx.local.basicdata.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tx.component.configuration.context.ConfigContext;
import com.tx.component.plugin.context.Plugin;
import com.tx.component.plugin.context.PluginConfig;
import com.tx.component.plugin.context.PluginContext;
import com.tx.component.plugin.model.PluginInstance;
import com.tx.component.plugin.service.PluginInstanceService;
import com.tx.core.TxConstants;
import com.tx.core.exceptions.util.AssertUtils;

/**
 * 插件逻辑控制层<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2019年12月13日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Controller
@RequestMapping("/plugin")
public class PluginController implements InitializingBean, ResourceLoaderAware {
    
    @Resource
    private PluginContext pluginContext;
    
    /** 插件实例业务层 */
    private PluginInstanceService pluginInstanceService;
    
    /** 默认的配置页 */
    private final String DEFAULT_SETTING_PAGE = "plugin/setting";
    
    /** 页面映射 */
    private final Map<String, String> pageMap = new HashMap<>();
    
    /** 资源读取器 */
    private ResourceLoader resourceLoader;
    
    /**
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        this.pluginInstanceService = this.pluginContext
                .getPluginInstanceService();
    }
    
    /**
     * @param resourceLoader
     */
    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }
    
    /**
     * 跳转到插件配置主页面<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/toQueryMain")
    public String toQueryMain(
            @RequestParam(value = "catalog", required = false) String catalog,
            @RequestParam Map<String, String> requestMap, ModelMap response) {
        return "plugin/queryPluginMain";
    }
    
    /**
     * 跳转到查询DataDict列表页面<br/>
     * <功能详细描述>
     * @return [参数说明]
     *
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/toQueryList")
    public String toQueryList(
            @RequestParam(value = "catalog", required = true) String catalog,
            @RequestParam Map<String, String> requestMap, ModelMap response) {
        //基础数据类型集合
        response.put("catalog", catalog);
        return "plugin/queryPluginList";
    }
    
    /**
     * 查询插件实例<br/>
     * <功能详细描述>
     * @param valid
     * @param catalog
     * @param requestMap
     * @return [参数说明]
     * 
     * @return List<PluginInstance> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/queryList")
    public List<PluginInstance> queryList(
            @RequestParam(value = "valid", required = false) Boolean valid,
            @RequestParam(value = "catalog", required = false) String catalog,
            @RequestParam Map<String, String> requestMap) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("catalog", catalog);
        List<PluginInstance> resList = this.pluginInstanceService
                .queryList(valid, params);
        return resList;
    }
    
    /**
     * 安装插件<br/>
     * <功能详细描述>
     * @param id
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/install")
    public boolean install(
            @RequestParam(value = "id", required = false) String id) {
        Plugin<?> plugin = PluginContext.getContext().getPlugin(id);
        AssertUtils.notNull(plugin, "plugin is null.id:{}", id);
        
        boolean flag = plugin.install();
        return flag;
    }
    
    /**
     * 卸载插件<br/>
     * <功能详细描述>
     * @param id
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/uninstall")
    public boolean uninstall(
            @RequestParam(value = "id", required = false) String id) {
        Plugin<?> plugin = PluginContext.getContext().getPlugin(id);
        AssertUtils.notNull(plugin, "plugin is null.id:{}", id);
        
        boolean flag = plugin.uninstall();
        return flag;
    }
    
    /**
     * 跳转到插件配置页<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/toSetting")
    public String toSetting(
            @RequestParam(value = "id", required = true) String id,
            @RequestParam Map<String, String> requestMap, ModelMap response) {
        Plugin<?> plugin = PluginContext.getContext().getPlugin(id);
        AssertUtils.notNull(plugin, "plugin is null.id:{}", id);
        
        response.put("pluginId", id);
        String path = getSettingPage(plugin);
        if (DEFAULT_SETTING_PAGE.equals(path)) {
            response.put("properties",
                    ConfigContext.getContext().parse(plugin.getPrefix(),
                            plugin.getConfigEntityType()));
        } else {
            PluginConfig config = plugin.getConfig();
            response.put("config", config);
        }
        
        return path;
    }
    
    /**
     * 获取页面名称<br/>
     * <功能详细描述>
     * @param pageType
     * @param basicDataTypeCode
     * @return [参数说明]
     *
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    private String getSettingPage(Plugin<?> plugin) {
        AssertUtils.notNull(plugin, "plugin is null.");
        
        //页面缓存键
        String pageCacheKey = plugin.getId();
        String page = "";
        //pageMap.clear();
        if (pageMap.containsKey(pageCacheKey)) {
            page = pageMap.get(pageCacheKey);
            return page;
        }
        
        String[] paths = StringUtils.splitByWholeSeparator(
                AopUtils.getTargetClass(plugin).getName(), ".");
        if (paths.length <= 4) {
            page = "plugin/setting";
        } else {
            StringBuilder sb = new StringBuilder(
                    TxConstants.INITIAL_STR_LENGTH);
            sb.append("plugin/");
            for (int i = 3; i < paths.length - 1; i++) {
                sb.append(paths[i]).append("/");
            }
            sb.append("setting");
            page = sb.toString();
        }
        
        if (!existPage(page)) {
            //如果页面不存在获取其对应的默认页面
            page = DEFAULT_SETTING_PAGE;
        }
        pageMap.put(pageCacheKey, page);
        return page;
    }
    
    /**
     * 判断html页面是否存在
     * <功能详细描述>
     * @param page
     * @return [参数说明]
     *
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    private boolean existPage(String page) {
        //View view = null;
        //try {
        //    view = this.viewResolverComposite.resolveViewName(page, null);
        //} catch (Exception e) {
        //    view = null;
        //}
        //if (view == null) {
        //    return false;
        //} else {
        //    return true;
        //}
        StringBuilder pageSB = new StringBuilder("classpath:templates/");
        pageSB.append(page).append(".html");
        org.springframework.core.io.Resource pageResource = this.resourceLoader
                .getResource(pageSB.toString());
        if (pageResource.exists()) {
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * 跳转到插件配置页<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/setting")
    public boolean setting(
            @RequestParam(value = "pluginId", required = true) String pluginId,
            @RequestParam Map<String, String> params, ModelMap response) {
        Plugin<?> plugin = PluginContext.getContext().getPlugin(pluginId);
        AssertUtils.notNull(plugin, "plugin is null.id:{}", pluginId);
        
        boolean flag = plugin.setting(params);
        return flag;
    }
    
    /**
     * 启用插件<br/>
     * <功能详细描述>
     * @param id
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/enable")
    public boolean enable(
            @RequestParam(value = "id", required = false) String id) {
        Plugin<?> plugin = PluginContext.getContext().getPlugin(id);
        AssertUtils.notNull(plugin, "plugin is null.id:{}", id);
        
        boolean flag = plugin.enable();
        return flag;
    }
    
    /**
     * 禁用插件<br/>
     * <功能详细描述>
     * @param id
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/disable")
    public boolean disable(
            @RequestParam(value = "id", required = false) String id) {
        Plugin<?> plugin = PluginContext.getContext().getPlugin(id);
        AssertUtils.notNull(plugin, "plugin is null.id:{}", id);
        
        boolean flag = plugin.disable();
        return flag;
    }
}
