/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2014年4月23日
 * <修改描述:>
 */
package com.tx.local.loanaccount.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.tx.component.basicdata.model.BasicDataEnum;
import com.tx.component.basicdata.model.BasicDataEnumJsonSerializer;

/**
 * 交易分类<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2014年4月23日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@JsonSerialize(using = BasicDataEnumJsonSerializer.class)
public enum LATradingCategoryEnum implements BasicDataEnum{
    /**
     * 构建账户
     */
    BUILD("BUILD", "构建账户"),
    /**
     * 放款:服务于:放款,新贷,再贷
     */
    LOAN("LOAN", "放款"),
    /**
     * 撤销放款
     */
    REVOKE_LOAN("REVOKE_LOAN", "撤销放款"),
    /**
     * 计费;
     */
    CHARGE("CHARGE", "计费"),
    /**
     * 撤销计费
     */
    REVOKE_CHARGE("REVOKE_CHARGE", "撤销计费"),
    /**
     * 豁免
     */
    EXEMPT("EXEMPT", "豁免"),
    /**
     * 撤销豁免
     */
    REVOKE_EXEMPT("REVOKE_EXEMPT", "撤销豁免"),
    /**
     * 收款 -> 还款
     */
    REPAY("REPAY", "还款"),
    /**
     * 撤销 -> 撤销还款
     */
    REVOKE_REPAY("REVOKE_TRADING_REPAY", "撤销还款"),
    /**
     * 结清
     */
    SETTLE("SETTLE", "结清"),
    /**
     * 结清撤销
     */
    REVOKE_SETTLE("REVOKE_SETTLE", "撤销结清"),
    /**
     * 暂缓
     */
    SUSPEND("SUSPEND","暂缓"),
    /**
     * 撤销暂缓
     */
    REVOKE_SUSPEND("REVOKE_SUSPEND","撤销暂缓"),
    /**
     * 还款到帐
     */
    SUSPEND_RECEIVED("REPAY_RECEIVED","还款到帐"),
    /**
     * 重组账户
     */
    REBUILD("REBUILD", "重组账户"),
    /**
     * 撤销延期
     */
    REVOKE_REBUILD("REVOKE_REBUILD", "撤销重组账户"),
    /**
     * 修改账户状态
     */
    CHANGE("CHANGE","修改账户信息");
    
    /** key值 */
    private final String key;
    
    /** name值 */
    private final String name;
    
    /** <默认构造函数> */
    private LATradingCategoryEnum(String key, String name) {
        this.key = key;
        this.name = name;
    }
    
    /**
     * @return 返回 key
     */
    public String getKey() {
        return key;
    }
    
    /**
     * @return 返回 name
     */
    public String getName() {
        return name;
    }
}
