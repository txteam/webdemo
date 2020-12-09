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
    
    /** 购置款 */
    GZK("GZK", "购置款"),
    
    /** 保证金 */
    BZJ("BZJ", "保证金"),
    
    /** 原贷款结欠金额 */
    YDKJQJE("YDKJQJE", "原贷款结欠金额"),
    
    /** 投资阶段分润 */
    TZJDFR("TZJDFR", "投资阶段分润"),
    
    /** 贷前考察 */
    DQKCF("DQKCF", "贷前考察"),
    
    /** 一次性手续费 */
    YCXSXF("YCXSXF", "一次性手续费"),
    
    /** 贷前延期费 */
    DQYQF("DQYQF", "贷前延期费"),
    
    /** 扣款失败手续费 */
    KQSBSXF("KQSBSXF", "扣款失败手续费"),
    
    /** 逾期利息 */
    YQLX("YQLX", "逾期利息"),
    
    /** 管理费 */
    GLF("YCXSXF", "管理费"),
    
    /** 利息 */
    LX("LX", "利息"),
    
    /** 提前还款违约金 */
    TQHKWYJ("TQHKWYJ", "提前还款违约金"),
    
    /** 体验金 */
    TYJ("TYJ", "体验金"),
    
    /** 本金 */
    BJ("BJ", "本金"),
    
    /** 贷后展期费 */
    DHZQF("DHZQF", "贷后展期费"),
    
    /** 外包佣金 */
    WBYJ("WBYJ", "外包佣金"),
    
    /** 调账金额 */
    TZJE("TZJE", "调账金额"),
    
    /** 超额还款 */
    CEHK("CEHK", "超额还款"),
    
    /** 注销后回款 */
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
