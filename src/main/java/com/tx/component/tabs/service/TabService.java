/*
 * 描          述:  <描述>
 * 修  改   人:  brady
 * 修改时间:  2012-11-30
 * <修改描述:>
 */
package com.tx.component.tabs.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.quartz.Scheduler;
import org.springframework.core.io.Resource;

import com.tx.component.tabs.model.TabConfig;

/**
 * <tab的业务逻辑层>
 * <功能详细描述>
 * 
 * @author  brady
 * @version  [版本号, 2012-11-30]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class TabService {
    
    /** tab配置文件地址 */
    private Resource tabsConfigLocation;
    
    /** 调度器 */
    private Scheduler scheduler;
    
    /** 加载时间表达式 */
    private String reLoadCronExpresion;
    
    /** 配置 */
    private Map<String, List<TabConfig>> tabsMap;
    
    public void refreshTabsConfig() throws Exception {
        Map<String, List<TabConfig>> tabsMapToUse = new HashMap<String, List<TabConfig>>();
        
        if(!this.tabsConfigLocation.exists()){
            //TODO: 异常
        }
        SAXReader reader = new SAXReader();
        Document doc = reader.read(this.tabsConfigLocation.getInputStream());
        Element root = doc.getRootElement(); // 取根元素
        
        @SuppressWarnings("unchecked")
        List<Element> tabsList = root.elements("tabs");
        
        for (Element tabs : tabsList) {
            List<TabConfig> tabList = new ArrayList<TabConfig>();
            tabsMapToUse.put(tabs.attributeValue("id"), tabList);
            List<Element> eTabList = tabs.elements("tab");
            for (Element eTab : eTabList) {
                TabConfig tab = new TabConfig();
                tab.setId(eTab.attributeValue("id"));
                tab.setRight(StringUtils.trim(eTab.attributeValue("right")));
                tab.setLabel(eTab.attributeValue("label"));
                tab.setClosable("true".equals(eTab.attributeValue("closable")));
                tab.setUrl(eTab.elementText("url"));
                tab.setLazyLoad(!"false".equals(eTab.attributeValue("lazyLoad")));
                List<Attribute> attList = eTab.attributes();
                if (attList == null) {
                    continue;
                }
                Map<String, String> extTabAtt = new HashMap<String, String>();
                for (Attribute attTemp : attList) {
                    String name = attTemp.getName();
                    // 如果配置文件中属性不属于tab的保留关键字，则记录入扩展字段中
                    if (!TabConfig.TAB_KEY_WORD_SET.contains(name)) {
                        extTabAtt.put(name, attTemp.getValue());
                    }
                }
                tab.setExtTabAtt(extTabAtt);
                tabList.add(tab);
            }
        }
        
        tabsMap = tabsMapToUse;
    }
    
}
