/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2015年11月28日
 * <修改描述:>
 */
package com.tx.component.operator.model;

/**
 * 操作员类型<br/>
 *     用于系统其他功能中如果存在客户id作为操作员记录的情况<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2015年11月28日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public enum OperatorTypeEnum {
    
    SYSTEM_MANAGER,
    
    CLIENT;
    
    private String key;
    
    private String name;

    /**
     * @return 返回 key
     */
    public String getKey() {
        return key;
    }

    /**
     * @param 对key进行赋值
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * @return 返回 name
     */
    public String getName() {
        return name;
    }

    /**
     * @param 对name进行赋值
     */
    public void setName(String name) {
        this.name = name;
    }
}
