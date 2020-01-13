package com.tx.local.vitualcenter.model;

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
    
    /** 集团子公司1　*/
    JTZGS1("JTZGS1", "集团子公司1", JT),
    
    /** 集团子公司2　*/
    JTZGS2("JTZGS2", "集团子公司2", JT),
    
    /** 测试公司1 */
    CSGS1("CSGS1", "测试公司1"),
    
    /** 测试公司2 */
    CSGS2("CSGS2", "测试公司2");
    
    /** 名称 */
    private final String name;
    
    /** 编码 */
    private final String code;
    
    /** 父级虚中心 */
    private final VirtualCenterEnum parent;
    
    private VirtualCenterEnum(String code, String name) {
        this.name = name;
        this.code = code;
        this.parent = null;
    }
    
    private VirtualCenterEnum(String code, String name,
            VirtualCenterEnum parent) {
        this.name = name;
        this.code = code;
        this.parent = parent;
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
    
    /**
     * @return 返回 parent
     */
    public VirtualCenterEnum getParent() {
        return parent;
    }
}
