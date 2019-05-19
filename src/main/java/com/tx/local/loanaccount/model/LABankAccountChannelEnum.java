/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2017年5月24日
 * <修改描述:>
 */
package com.tx.local.loanaccount.model;

import java.util.Arrays;
import java.util.List;

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
public enum LABankAccountChannelEnum {
    
    DK_ZX("DK_ZX", Arrays.asList(FeeOwnershipEnum.DK, FeeOwnershipEnum.ZX)),
    
    ZX_DK("ZX_DK", Arrays.asList(FeeOwnershipEnum.ZX, FeeOwnershipEnum.DK)),
    
    DK_FOR_ZX("DK_FOR_ZX", Arrays.asList(FeeOwnershipEnum.ZX)),
    
    DK("DK", Arrays.asList(FeeOwnershipEnum.DK)),
    
    ZX("ZX", Arrays.asList(FeeOwnershipEnum.ZX)),
    
    PT("PT", Arrays.asList(FeeOwnershipEnum.PT)),;
    
    /** 关键字 */
    private final String key;
    
    /** 费用归属方 */
    private final List<FeeOwnershipEnum> ownerships;
    
    /** <默认构造函数> */
    private LABankAccountChannelEnum(String key, List<FeeOwnershipEnum> ownerships) {
        this.key = key;
        this.ownerships = ownerships;
    }
    
    /**
     * @return 返回 key
     */
    public String getKey() {
        return key;
    }
    
    /**
     * @return 返回 ownerships
     */
    public List<FeeOwnershipEnum> getOwnerships() {
        return ownerships;
    }
}
