package com.tx.component.mainframe.model;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
  * <tab配置>
  * <功能详细描述>
  * 
  * @author  brady
  * @version  [版本号, 2012-11-30]
  * @see  [相关类/方法]
  * @since  [产品/模块版本]
 */
public class TabConfig {
    
    //tab配置的保留字，关键字
    public final static Set<String> TAB_KEY_WORD_SET = new HashSet<String>();
    
    static {
        TAB_KEY_WORD_SET.add("tabs");
        TAB_KEY_WORD_SET.add("tab");
        TAB_KEY_WORD_SET.add("id");
        TAB_KEY_WORD_SET.add("right");
        TAB_KEY_WORD_SET.add("label");
        TAB_KEY_WORD_SET.add("url");
        TAB_KEY_WORD_SET.add("closable");
        TAB_KEY_WORD_SET.add("lazyLoad");
        TAB_KEY_WORD_SET.add("class");
    }
    
    /** tab的唯一键 */
    private String id;
    
    /** tab项目显示名称 */
    private String label;
    
    /** tab连接的url */
    private String url;
    
    /** tab所需的权限项 */
    private String right;
    
    /** tab是否可关闭 */
    private boolean closable;
    
    /** tab是否可懒加载 */
    private boolean lazyLoad;
    
    /** //tab扩展属性，利用该属性可对tab配置进行扩展 */
    private Map<String, String> extTabAtt;

    /**
     * @return 返回 id
     */
    public String getId() {
        return id;
    }

    /**
     * @param 对id进行赋值
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return 返回 label
     */
    public String getLabel() {
        return label;
    }

    /**
     * @param 对label进行赋值
     */
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * @return 返回 url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param 对url进行赋值
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return 返回 right
     */
    public String getRight() {
        return right;
    }

    /**
     * @param 对right进行赋值
     */
    public void setRight(String right) {
        this.right = right;
    }

    /**
     * @return 返回 closable
     */
    public boolean isClosable() {
        return closable;
    }

    /**
     * @param 对closable进行赋值
     */
    public void setClosable(boolean closable) {
        this.closable = closable;
    }

    /**
     * @return 返回 lazyLoad
     */
    public boolean isLazyLoad() {
        return lazyLoad;
    }

    /**
     * @param 对lazyLoad进行赋值
     */
    public void setLazyLoad(boolean lazyLoad) {
        this.lazyLoad = lazyLoad;
    }

    /**
     * @return 返回 extTabAtt
     */
    public Map<String, String> getExtTabAtt() {
        return extTabAtt;
    }

    /**
     * @param 对extTabAtt进行赋值
     */
    public void setExtTabAtt(Map<String, String> extTabAtt) {
        this.extTabAtt = extTabAtt;
    }
}
