/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2016年8月6日
 * <修改描述:>
 */
package com.tx.local.content.config;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

/**
 * 内容信息存放文件<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2016年8月6日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@XStreamAlias("content_config")
public class ContentConfig {
    
    /** 内容分类配置 */
    @XStreamImplicit(itemFieldName = "category")
    private List<CategoryConfig> categoryConfigList;
    
    /**
     * @return 返回 categoryConfigList
     */
    public List<CategoryConfig> getCategoryConfigList() {
        return categoryConfigList;
    }
    
    /**
     * @param 对categoryConfigList进行赋值
     */
    public void setCategoryConfigList(List<CategoryConfig> categoryConfigList) {
        this.categoryConfigList = categoryConfigList;
    }
}
