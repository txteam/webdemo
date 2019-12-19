/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2017年5月1日
 * <修改描述:>
 */
package com.tx.local.basicdata.model;

import com.tx.component.basicdata.model.BasicDataEnum;

/**
 * 费用枚举<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2017年5月1日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public enum FeeEnum implements BasicDataEnum {
    
    GZK("GZK", "购置款"),
    
    BZJ("BZJ", "保证金"),
    
    YDKJQJE("YDKJQJE", "原贷款结欠金额"),
    
    TZJDFR("TZJDFR", "投资阶段分润"),
    
    DQKCF("DQKCF", "贷前考察"),
    
    YCXSXF("YCXSXF", "一次性手续费"),
    
    DQYQF("DQYQF", "贷前延期费"),
    
    KQSBSXF("KQSBSXF", "扣款失败手续费"),
    
    YQLX("YQLX", "逾期利息"),
    
    GLF("YCXSXF", "管理费"),
    
    LX("LX", "利息"),
    
    TQHKWYJ("TQHKWYJ", "提前还款违约金"),
    
    TYJ("TYJ", "体验金"),
    
    BJ("BJ", "本金"),
    
    DHZQF("DHZQF", "贷后展期费"),
    
    WBYJ("WBYJ", "外包佣金"),
    
    TZJE("TZJE", "调账金额"),
    
    CEHK("CEHK", "超额还款"),
    
    ZXHHK("ZXHHK", "注销后回款");
    
    /** 费用项关键字 */
    private final String code;
    
    /** 费用项名称 */
    private final String name;
    
    /** <默认构造函数> */
    private FeeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }
    
    /**
     * @return 返回 code
     */
    public String getCode() {
        return code;
    }
    
    /**
     * @return 返回 name
     */
    public String getName() {
        return name;
    }
    
}
