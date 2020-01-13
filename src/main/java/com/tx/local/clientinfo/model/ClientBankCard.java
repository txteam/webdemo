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
 * <功能简述>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2020年1月13日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class ClientBankCard {
    
    /**银行名称*/
    @Column(name = "bankInfoId")
    private BankInfo bankInfo;
    
    /** 卡类型借记卡=DE；信用卡=CR */
    private BankCardTypeEnum bankCardType;
    
    /** 银行卡号 */
    private String bankCardNumber;
    
    /** 是否绑定银行卡 */
    private boolean bankCardBinding;
    
    /** 银行卡绑定错误时间 */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date bankCardErrLastDate;
    
    /** 银行卡实名认证错误次数 */
    private int bankCardErrCount;
}
