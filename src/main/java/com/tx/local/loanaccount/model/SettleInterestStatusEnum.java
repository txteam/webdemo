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
 * 结息状态<br/>
 *    
 * @author  Administrator
 * @version  [版本号, 2014年5月20日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@JsonSerialize(using = BasicDataEnumJsonSerializer.class)
public enum SettleInterestStatusEnum implements BasicDataEnum {
    
    //  
    //    结清待结息,
    //    
    //    结清退出待结息,
    /** 正常:待结息 */
    WSI("WSI", "正常"),
    
    /** 停息: STOP SETTLE INTEREST */
    SSI("SSI", "停息"),
    
    /** 取消待结息 */
    CWSI("CWSI", "取消待结息"),
    
    /** 结息完成 */
    FSI("FSI", "完成结息");
    
    /** key值 */
    private final String key;
    
    /** code值 */
    private final String code;
    
    /** name值 */
    private final String name;
    
    /** <默认构造函数> */
    private SettleInterestStatusEnum(String key, String name) {
        this.key = key;
        this.code = key;
        this.name = name;
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
     * @return 返回 name
     */
    public String getName() {
        return name;
    }
}
