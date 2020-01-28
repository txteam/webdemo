/*
 * 描          述:  <描述>
 * 修  改   人:  huangdonggang
 * 修改时间:  2017年12月9日
 * <修改描述:>
 */
package com.tx.local.loanaccount.model;

/**
 * 贷款账户客户银行账户<br/>
 * <功能详细描述>
 * 
 * @author  huangdonggang
 * @version  [版本号, 2017年12月9日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public enum LAClientBankAccountTypeEnum {
    
    DEDUCT("DEDUCT", "扣款还款账户"),
    
    LOAN("LOAN", "放款银行账户");
    
    /** 关键字 */
    private final String code;
    
    /** 名称 */
    private final String name;
    
    /** <默认构造函数> */
    private LAClientBankAccountTypeEnum(String code, String name) {
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
