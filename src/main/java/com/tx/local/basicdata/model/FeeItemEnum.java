/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2016年1月24日
 * <修改描述:>
 */
package com.tx.local.basicdata.model;

import com.tx.component.basicdata.model.BasicDataEnum;

/**
 * 费用项枚举<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2016年1月24日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public enum FeeItemEnum implements BasicDataEnum {
    
    /* ------------ invest account ---------------- */
    DK_GZK("DK_GZK", FeeEnum.GZK, FeeOwnershipEnum.DK, "贷款公司_购置款"),
    
    ZX_GZK("ZX_GZK", FeeEnum.GZK, FeeOwnershipEnum.ZX, "咨询公司_购置款"),
    
    /** 原贷款结欠金额 */
    DK_YDKJQJE("DK_YDKJQJE", FeeEnum.YDKJQJE, FeeOwnershipEnum.DK, "贷款公司_原贷款结欠金额"),
    
    DK_BZJ("DK_BZJ", FeeEnum.BZJ, FeeOwnershipEnum.DK, "贷款公司_保证金", false),
    
    ZX_BZJ("ZX_BZJ", FeeEnum.BZJ, FeeOwnershipEnum.ZX, "咨询公司_保证金", false),
    
    /** 投资阶段分润 */
    KH_TZJDFR("KH_TZJDFR", FeeEnum.TZJDFR, FeeOwnershipEnum.KH, "客户_投资阶段分润"),
    
    /** 体验金 */
    KH_TYJ("KH_TYJ", FeeEnum.TYJ, FeeOwnershipEnum.KH, "客户_体验金"),
    
    /* ------------ loan account ---------------- */
    
    /** 本金 */
    DK_BJ("DK_BJ", FeeEnum.BJ, FeeOwnershipEnum.DK, "贷款公司_本金"),
    
    KH_BJ("KH_BJ", FeeEnum.BJ, FeeOwnershipEnum.KH, "客户_本金"),
    
    /** 一次性手续费 */
    DK_YCXSXF("DK_YCXSXF", FeeEnum.YCXSXF, FeeOwnershipEnum.DK, "贷款公司_一次性手续费"),
    
    ZX_YCXSXF("ZX_YCXSXF", FeeEnum.YCXSXF, FeeOwnershipEnum.ZX, "咨询公司_一次性手续费"),
    
    /** 贷前延期费 */
    DK_DQYQF("DK_DQYQF", FeeEnum.YCXSXF, FeeOwnershipEnum.DK, "贷款公司_贷前延期费"),
    
    ZX_DQYQF("ZX_DQYQF", FeeEnum.YCXSXF, FeeOwnershipEnum.ZX, "咨询公司_贷前延期费"),
    
    /** 贷前考察费 */
    DK_DQKCF("DK_DQKCF", FeeEnum.YCXSXF, FeeOwnershipEnum.DK, "贷款公司_贷前考察费"),
    
    ZX_DQKCF("ZX_DQKCF", FeeEnum.YCXSXF, FeeOwnershipEnum.ZX, "咨询公司_贷前考察费"),
    
    /** 咨询公司管理费 */
    PT_GLF("PT_GLF", FeeEnum.GLF, FeeOwnershipEnum.PT, "平台_管理费"),
    
    /** 咨询公司管理费 */
    ZX_GLF("ZX_GLF", FeeEnum.GLF, FeeOwnershipEnum.ZX, "咨询公司_管理费"),
    
    /** 咨询公司管理费 */
    DK_GLF("DK_GLF", FeeEnum.GLF, FeeOwnershipEnum.DK, "贷款公司_管理费"),
    
    /** 利息 */
    DK_LX("DK_LX", FeeEnum.LX, FeeOwnershipEnum.DK, "贷款公司_利息"),
    
    KH_LX("KH_LX", FeeEnum.LX, FeeOwnershipEnum.KH, "客户_利息"),
    
    /** 扣款失败手续费 */
    DK_KQSBSXF("DK_KQSBSXF", FeeEnum.KQSBSXF, FeeOwnershipEnum.DK, "贷款公司_扣款失败手续费"),
    
    ZX_KQSBSXF("ZX_KQSBSXF", FeeEnum.KQSBSXF, FeeOwnershipEnum.ZX,
            "咨询公司_扣款失败手续费"),
    
    /** 逾期利息 */
    DK_YQLX("DK_YQLX", FeeEnum.YQLX, FeeOwnershipEnum.DK, "贷款公司_逾期利息"),
    
    ZX_YQLX("ZX_YQLX", FeeEnum.YQLX, FeeOwnershipEnum.ZX, "咨询公司_逾期利息"),
    
    /** 提前还款违约金 */
    DK_TQHKWYJ("DK_TQHKWYJ", FeeEnum.TQHKWYJ, FeeOwnershipEnum.DK, "贷款公司_提前还款违约金"),
    
    ZX_TQHKWYJ("ZX_TQHKWYJ", FeeEnum.TQHKWYJ, FeeOwnershipEnum.ZX,
            "咨询公司_提前还款违约金"),
    
    /** 贷后展期费 */
    DK_DHZQF("DK_DHZQF", FeeEnum.DHZQF, FeeOwnershipEnum.DK, "贷款公司_贷后展期费"),
    
    ZX_DHZQF("ZX_DHZQF", FeeEnum.DHZQF, FeeOwnershipEnum.ZX, "咨询公司_贷后展期费"),
    
    /** 外包佣金 */
    DK_WBYJ("DK_WBYJ", FeeEnum.WBYJ, FeeOwnershipEnum.DK, "贷款公司_外包佣金"),
    
    ZX_WBYJ("ZX_WBYJ", FeeEnum.WBYJ, FeeOwnershipEnum.ZX, "咨询公司_外包佣金"),
    
    /** 调账金额 */
    DK_TZJE("DK_TZJE", FeeEnum.TZJE, FeeOwnershipEnum.DK, "贷款公司_调账金额"),
    
    ZX_TZJE("ZX_TZJE", FeeEnum.TZJE, FeeOwnershipEnum.ZX, "咨询公司_调账金额"),
    
    /** 超额还款 */
    ZX_CEHK("ZX_CEHK", FeeEnum.CEHK, FeeOwnershipEnum.ZX, "咨询公司_超额还款", false),
    
    DK_CEHK("DK_CEHK", FeeEnum.CEHK, FeeOwnershipEnum.DK, "贷款公司_超额还款", false),
    
    /** 注销后回款 */
    ZX_ZXHHK("ZX_ZXHHK", FeeEnum.ZXHHK, FeeOwnershipEnum.ZX, "咨询公司_注销后回款", false),
    
    DK_ZXHHK("DK_ZXHHK", FeeEnum.ZXHHK, FeeOwnershipEnum.DK, "贷款公司_注销后回款",
            false);
    
    /** 费用项关键字 */
    private final String key;
    
    /** 费用项关键字 */
    private final String code;
    
    /** 对应的费用关键字 */
    private final FeeEnum fee;
    
    /** 费用归属方 */
    private final FeeOwnershipEnum ownership;
    
    /** 费用项名称 */
    private final String name;
    
    /** 是否需要校验: 应收 + 豁免 - 实收 >= 0 */
    private final boolean needValidate;
    
    /** <默认构造函数> */
    private FeeItemEnum(String key, final FeeEnum fee,
            FeeOwnershipEnum ownership, String name) {
        this.key = key;
        this.code = key;
        this.fee = fee;
        this.ownership = ownership;
        this.name = name;
        this.needValidate = true;
    }
    
    /** <默认构造函数> */
    private FeeItemEnum(String key, final FeeEnum fee,
            FeeOwnershipEnum ownership, String name, boolean needValidate) {
        this.key = key;
        this.code = key;
        this.fee = fee;
        this.ownership = ownership;
        this.name = name;
        this.needValidate = needValidate;
    }
    
    /**
     * @return 返回 key
     */
    public String getKey() {
        return key;
    }
    
    /**
     * @return 返回 code
     */
    public String getCode() {
        return code;
    }
    
    /**
     * @return 返回 fee
     */
    public FeeEnum getFee() {
        return fee;
    }
    
    /**
     * @return 返回 ownership
     */
    public FeeOwnershipEnum getOwnership() {
        return ownership;
    }
    
    /**
     * @return 返回 name
     */
    public String getName() {
        return name;
    }
    
    /**
     * @return 返回 needValidate
     */
    public boolean isNeedValidate() {
        return needValidate;
    }
}
