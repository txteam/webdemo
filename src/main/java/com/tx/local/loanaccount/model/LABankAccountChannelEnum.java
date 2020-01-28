/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2017年5月24日
 * <修改描述:>
 */
package com.tx.local.loanaccount.model;

import java.util.Arrays;
import java.util.List;

import com.tx.component.basicdata.model.BasicDataEnum;
import com.tx.local.basicdata.model.FeeOwnershipEnum;

/**
 * 银行账户渠道枚举<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2017年5月24日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public enum LABankAccountChannelEnum implements BasicDataEnum {
    
    /** 贷款公司收款，同时代收咨询公司费用 */
    DK_ZX("DK_ZX", Arrays.asList(FeeOwnershipEnum.DK, FeeOwnershipEnum.ZX)),
    
    /** 咨询公司收款，同时代收贷款公司费用 */
    ZX_DK("ZX_DK", Arrays.asList(FeeOwnershipEnum.ZX, FeeOwnershipEnum.DK)),
    
    /** 贷款公司为咨询公司代收费用 */
    DK_FOR_ZX("DK_FOR_ZX", Arrays.asList(FeeOwnershipEnum.ZX)),
    
    /** 贷款公司 */
    DK("DK", Arrays.asList(FeeOwnershipEnum.DK)),
    
    /** 咨询公司 */
    ZX("ZX", Arrays.asList(FeeOwnershipEnum.ZX)),
    
    /** 平台公司 */
    PT("PT", Arrays.asList(FeeOwnershipEnum.PT));
    
    /** 关键字 */
    private final String code;
    
    /** 费用归属方 */
    private final List<FeeOwnershipEnum> ownerships;
    
    /** <默认构造函数> */
    private LABankAccountChannelEnum(String code,
            List<FeeOwnershipEnum> ownerships) {
        this.code = code;
        this.ownerships = ownerships;
    }
    
    /**
     * @return 返回 code
     */
    public String getCode() {
        return code;
    }
    
    /**
     * @return 返回 ownerships
     */
    public List<FeeOwnershipEnum> getOwnerships() {
        return ownerships;
    }
    
}
