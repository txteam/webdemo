/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2014年4月22日
 * <修改描述:>
 */
package com.tx.local.loanaccount.model;

import java.io.Serializable;

import javax.persistence.Column;

import com.alibaba.fastjson.JSON;
import com.tx.component.servicelogger.annotation.ServiceLog;
import com.tx.component.servicelogger.model.AbstractServiceLogger;
import com.tx.local.loanaccount.context.request.AbstractLoanAccountRequest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 贷款单账户操作日志记录<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2014年4月22日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@Builder
@ServiceLog("LA_REQUEST_LOG")
public class LARequestLog extends AbstractServiceLogger
        implements Serializable {
    
    /** 注释内容 */
    private static final long serialVersionUID = -6031308364401303434L;
    
    //操作请求实例
    @Column(nullable = true, length = 64)
    private String requestId;
    
    //贷款单账户id
    @Column(nullable = true, length = 64)
    private String loanAccountId;
    
    //贷款账户类型
    @Column(nullable = true, length = 64)
    private LoanAccountTypeEnum loanAccountType;
    
    //交易分类
    @Column(nullable = true, length = 64)
    private LATradingCategoryEnum tradingCategory;
    
    //交易类型
    @Column(nullable = true, length = 64)
    private LATradingRecordTypeEnum tradingRecordType;
    
    //操作请求
    @Column(nullable = true, length = 64)
    private RequestSourceTypeEnum sourceType;
    
    /** 报文内容 */
    @Column(nullable = false, length = 4000)
    private String message;
    
    /** 备注 */
    @Column(nullable = false, length = 500)
    private String remark;
    
    //:操作人员id
    @Column(nullable = false, length = 64)
    private String operatorId;
    
    //:操作人员id
    @Column(nullable = false, length = 64)
    private String operatorUsername;
    
    //:客户端ip地址
    @Column(nullable = false, length = 64)
    private String clientIpAddress;
    
    //:客户端真实ip地址
    @Column(nullable = true, length = 64)
    private String realIpAddress;
    
    //中转ip地址(写入以前应该对长度进行特殊处理)
    @Column(nullable = true, length = 256)
    private String forwardedIpAddress;
    
    //远端调用ip地址
    @Column(nullable = true, length = 64)
    private String remoteIpAddress;
    
    /** <默认构造函数> */
    public LARequestLog() {
        super();
    }
    
    /** <默认构造函数> */
    public LARequestLog(AbstractLoanAccountRequest request) {
        super();
        
        this.requestId = request.getId();
        this.loanAccountId = request.getLoanAccountId();
        this.loanAccountType = request.getLoanAccountType();
        this.tradingCategory = request.getTradingCategory();
        this.tradingRecordType = request.getTradingRecordType();
        this.sourceType = request.getSourceType();
        this.remark = request.getRemark();
        
        this.message = JSON.toJSONString(request);
    }
    
    /**
     * <默认构造函数>
     */
    public LARequestLog(AbstractLoanAccountRequest request, String message) {
        super();
        
        this.requestId = request.getId();
        this.loanAccountId = request.getLoanAccountId();
        this.loanAccountType = request.getLoanAccountType();
        this.tradingCategory = request.getTradingCategory();
        this.tradingRecordType = request.getTradingRecordType();
        this.sourceType = request.getSourceType();
        this.remark = request.getRemark();
        
        this.message = JSON.toJSONString(request);
    }
    
}
