/*
 * 描          述:  <描述>
 * 修  改   人:  PengQingyang
 * 修改时间:  2020年12月27日
 * <修改描述:>
 */
package com.tx.local.springmvc.path;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * local包中配置路径属性<br/>
 * <功能详细描述>
 * 
 * @author  PengQingyang
 * @version  [版本号, 2020年12月27日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@ConfigurationProperties(prefix = "tx.local.path-config")
public class LocalPathConfigProperties {
    
    /** 前置 */
    private String prefix;
    
    /**
     * @return 返回 prefix
     */
    public String getPrefix() {
        return prefix;
    }
    
    /**
     * @param 对prefix进行赋值
     */
    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }
}
