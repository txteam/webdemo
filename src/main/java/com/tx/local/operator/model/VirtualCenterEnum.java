package com.tx.local.operator.model;

/**
  * 虚中心枚举
  * <功能详细描述>
  * 
  * @author  Vincent.chen
  * @version  [版本号, 2015年2月6日]
  * @see  [相关类/方法]
  * @since  [产品/模块版本]
 */
public enum VirtualCenterEnum {
    
    /** 集团公司　*/
    JT("JT", "集团公司"),
    
    /** 添馨网路科技 */
    TXWLKJ("TXWLKJ","添馨网络科技");
    
    /** 名称 */
    private final String name;
    
    /** 编码 */
    private final String code;
    
    private VirtualCenterEnum(String code, String name) {
        this.name = name;
        this.code = code;
    }
    
    /**
     * @return 返回 name
     */
    public String getName() {
        return name;
    }
    
    /**
     * @return 返回 code
     */
    public String getCode() {
        return code;
    }
    
}
