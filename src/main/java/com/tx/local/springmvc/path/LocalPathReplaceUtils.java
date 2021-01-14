/*
 * 描          述:  <描述>
 * 修  改   人:  PengQingyang
 * 修改时间:  2020年12月27日
 * <修改描述:>
 */
package com.tx.local.springmvc.path;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;

/**
 * local包中配置路径属性<br/>
 * <功能详细描述>
 * 
 * @author  PengQingyang
 * @version  [版本号, 2020年12月27日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class LocalPathReplaceUtils implements InitializingBean {
    
    /** 本地路径配置 */
    private LocalPathConfigProperties properties;
    
    /** 是否启用替换 */
    private boolean enable;
    
    /** 路径前缀 */
    private String prefix;
    
    /** local包中路径替换配置 */
    public LocalPathReplaceUtils(LocalPathConfigProperties properties) {
        super();
        this.properties = properties;
    }
    
    /**
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        if (StringUtils.isEmpty(this.properties.getPrefix())
                || "/".equals(this.properties.getPrefix())) {
            this.enable = false;
            this.prefix = "";
        } else {
            this.enable = true;
            if (!this.properties.getPrefix().startsWith("/")) {
                this.properties.setPrefix("/" + this.properties.getPrefix());
            }
            this.prefix = this.properties.getPrefix();
        }
    }
    
    /**
     * 转换路径<br/>
     * <功能详细描述>
     * @param path
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public String transform(String path) {
        if (!this.enable) {
            return path;
        }
        if (path.startsWith("/")) {
            path = this.prefix + path;
        } else {
            path = this.prefix + "/" + path;
        }
        return path;
    }
    
    /**
     * 转换路径<br/>
     * <功能详细描述>
     * @param path
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public String[] transform(String... paths) {
        if (ArrayUtils.isEmpty(paths)) {
            return paths;
        }
        String[] res = new String[paths.length];
        for (int i = 0; i < paths.length; i++) {
            res[i] = transform(paths[i]);
        }
        return res;
    }
    
    /**
     * @return 返回 enable
     */
    public boolean isEnable() {
        return enable;
    }
    
    /**
     * @param 对enable进行赋值
     */
    public void setEnable(boolean enable) {
        this.enable = enable;
    }
    
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
    
    /**
     * @return 返回 properties
     */
    public LocalPathConfigProperties getProperties() {
        return properties;
    }
    
    /**
     * @param 对properties进行赋值
     */
    public void setProperties(LocalPathConfigProperties properties) {
        this.properties = properties;
    }
}
