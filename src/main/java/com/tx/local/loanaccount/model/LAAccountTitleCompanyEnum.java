/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2015年1月8日
 * <修改描述:>
 */
package com.tx.local.loanaccount.model;

import com.tx.core.support.json.BaseEnum;

/**
 * 公司枚举<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2015年1月8日]
 * @see  [相关类/方法] 
 * @since  [产品/模块版本]
 */
public enum LAAccountTitleCompanyEnum implements BaseEnum {
    
    /** 重庆- 重庆 - 交易中心 */
    CQ_PT("CQ_PT", "交易平台"),
    
    /** 重庆- 重庆 - 交易中心 */
    CQ_ZX("CQ_ZX", "咨询中心"),
    
    /** 重庆- 重庆 - 贷款公司 */
    CQ_DK("CQ_CQ_DK", "贷款公司");
    
    /** 关键字 */
    private final String code;
    
    /** 名称 */
    private final String name;
    
    /** <默认构造函数> */
    private LAAccountTitleCompanyEnum(String code, String name) {
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
