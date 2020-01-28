/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2014年5月20日
 * <修改描述:>
 */
package com.tx.local.loanaccount.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.tx.component.basicdata.model.BasicDataEnum;
import com.tx.component.basicdata.model.BasicDataEnumJsonSerializer;

/**
 * 账户状态枚举<br/>
 * <功能详细描述>
 * 
 * @author Administrator
 * @version [版本号, 2014年5月20日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@JsonSerialize(using = BasicDataEnumJsonSerializer.class)
public enum AccountStatusEnum implements BasicDataEnum {
    
    /** 存活 */
    AC("AC", "存活"),
    
    /** 贷后取消 */
    ACCN("ACCN", "贷后取消"),
    
    /** 续贷结清 */
    RS("RS", "续贷结清"),
    
    /** 续贷结清 */
    RSL("RSL", "续贷结清(仅逾期依赖的费用项结清)"),
    
    /** 提前结清 */
    ES("ES", "提前结清"),
    
    /** 提前结清 */
    ESL("ESL", "提前结清(仅逾期依赖的费用项结清)"),
    
    /** 所有费用全部结清 */
    FP("FP", "结清"),
    
    /** 结清(仅逾期依赖的费用项结清) */
    SL("SL", "结清(仅逾期依赖的费用项结清)"),
    
    /** 注销 */
    WO("WO", "注销"),
    
    /** 注销账户结清非逾期依赖项 */
    WSL("WSL", "注销账户结清非逾期依赖项"),
    
    /** 注销后账户所有费用全部结清 */
    WFP("WFP", "注销后账户所有费用全部结清");
    
    /** 编码 */
    private final String code;
    
    /** 名称 */
    private final String name;
    
    /** 账户状态 */
    private AccountStatusEnum(String code, String name) {
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
