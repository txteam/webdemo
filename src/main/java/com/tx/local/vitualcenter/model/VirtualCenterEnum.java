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
    
    /** 平台:前端显示系统，当内容是服务前端显示系统时需要通过该虚中心进行配置　*/
    PT("PT", "平台"),
    
    /** 城口　*/
    CK("CK", "城口"),
    
    /** 梁平　*/
    LP("LP", "梁平"),
    
    /** 丰都　*/
    FD("FD", "丰都"),
    
    /** 潼南 */
    TN("TN", "潼南"),
    //    /** 平台:前端显示系统，当内容是服务前端显示系统时需要通过该虚中心进行配置　*/
    //    PT_CK("PT_CK", "城口平台"),
    //    
    //    /** 社属机构　*/
    //    SSJG_CK("SSJG_CK", "社属机构",PT_CK),
    //    
    //    /** 机关　*/
    //    JG_SSJG_CK("JG_SSJG_CK", "机关", SSJG_CK),
    //    
    //    /** 机关　*/
    //    QY_SSJG_CK("QY_SSJG_CK", "企业", SSJG_CK),
    //    
    //    /** 事业单位　*/
    //    SYDW_SSJG_CK("SYDW_SSJG_CK", "事业单位", SSJG_CK),
    //    
    //    /** 社团组织　*/
    //    STZZ_SSJG_CK("STZZ_SSJG_CK", "社团组织", SSJG_CK),
    //    
    //    /** JCS　*/
    //    JCS_SSJG_CK("JCS_SSJG_CK", "基层社", SSJG_CK),
    //    
    //    /** JCSDW_SSJG_CK　*/
    //    JCSDW_SSJG_CK("JCSDW_SSJG_CK", "基层社单位", SSJG_CK),
    //    
    //    /** ZYHZS_SSJG_CK　*/
    //    ZYHZS_SSJG_CK("ZYHZS_SSJG_CK", "专业合作社", SSJG_CK),
    //    
    //    /** ZHFWS_SSJG_CK　*/
    //    ZHFWS_SSJG_CK("ZHFWS_SSJG_CK", "综合服务社", SSJG_CK),
    //    
    //    /** 城口行政机构　*/
    //    XZJG_CK("XZJG_CK", "行政机构",PT_CK),
    //    
    //    /** 驻村办　*/
    //    ZCB_XZJG_CK("ZCB_XZJG_CK", "驻村办",XZJG_CK)
    ;
    
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
