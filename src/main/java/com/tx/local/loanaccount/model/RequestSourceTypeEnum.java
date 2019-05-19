/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2014年4月23日
 * <修改描述:>
 */
package com.tx.local.loanaccount.model;

/**
 * 交易来源枚举<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2014年4月23日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public enum RequestSourceTypeEnum {
    /**
     * 自动调度
     */
    AUTO_SCHEDULE_REQUEST("AUTO_SCHEDULE_REQUEST","自动调度"),
    /**
     * 关联调度
     */
    RELATE_SCHEDULE("RELATE_SCHEDULE","关联调度"),
    /**
     * 接口调用
     */
    RPC_REQUEST("RPC_REQUEST","接口调用"),
    /**
     * 操作请求
     */
    OPER_REQUEST("OPER_REQUEST","操作请求"),
    /**
     * 操作请求
     */
    CLIENT_REQUEST("CLIENT_REQUEST","操作请求");
    
    /** key值 */
    private final String key;
    
    /** name值 */
    private final String name;
    
    /** <默认构造函数> */
    private RequestSourceTypeEnum(String key, String name) {
        this.key = key;
        this.name = name;
    }
    
    /**
     * @return 返回 key
     */
    public String getKey() {
        return key;
    }
    
    /**
     * @return 返回 name
     */
    public String getName() {
        return name;
    }
}
