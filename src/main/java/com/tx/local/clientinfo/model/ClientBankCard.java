/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2020年1月13日
 * <修改描述:>
 */
package com.tx.local.clientinfo.model;

import java.util.Date;

import javax.persistence.Column;

import org.springframework.format.annotation.DateTimeFormat;

import com.tx.local.basicdata.model.BankCardTypeEnum;
import com.tx.local.basicdata.model.BankInfo;

/**
 * 客户银行卡信息<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2020年1月13日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class ClientBankCard {
    
    /** 客户银行卡id */
    private String id;
    
    /** 客户id */
    @Column(name = "bankInfoId", length = 64, nullable = false, updatable = false)
    private String clientId;
    
    /**银行名称*/
    @Column(name = "bankInfoId", length = 64, nullable = false, updatable = false)
    private BankInfo bankInfo;
    
    /** 卡类型借记卡=DE；信用卡=CR */
    private BankCardTypeEnum type;
    
    /** 银行卡号 */
    private String number;
    
    /** 是否是默认的银行卡 */
    private boolean defaults;
    
    /** 状态： */
    private String state;
    
    /** 银行卡绑定错误时间 */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date bankCardErrLastDate;
    
    /** 银行卡实名认证错误次数 */
    private int bankCardErrCount;
}
