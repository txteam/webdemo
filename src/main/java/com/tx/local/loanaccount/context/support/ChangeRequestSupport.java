/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2014年5月21日
 * <修改描述:>
 */
package com.tx.local.loanaccount.context.support;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.tx.component.command.context.CommandResponse;
import com.tx.component.servicelogger.util.ServiceLoggerUtils;
import com.tx.core.exceptions.util.AssertUtils;
import com.tx.core.util.MessageUtils;
import com.tx.local.loanaccount.context.receiver.change.AbstractChangeReceiver;
import com.tx.local.loanaccount.context.request.change.AbstractChangeRequest;
import com.tx.local.loanaccount.helper.tradingrecord.LATradingRecordHelper;
import com.tx.local.loanaccount.model.LARequestLog;
import com.tx.local.loanaccount.model.LATradingRecord;
import com.tx.local.loanaccount.model.LoanAccount;

/**
 * 账户改变请求支持类<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2014年5月21日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Scope("prototype")
@Component("changeRequestSupport")
public class ChangeRequestSupport extends
        AbstractLoanAccountRequestSupport<AbstractChangeRequest, AbstractChangeReceiver<AbstractChangeRequest>> {
    
    /** <默认构造函数> */
    public ChangeRequestSupport() {
        super();
    }
    
    /** <默认构造函数> */
    public ChangeRequestSupport(
            AbstractChangeReceiver<AbstractChangeRequest> receiver) {
        super(receiver);
    }
    
    /**
     * @param request
     * @param response
     * @return
     */
    @Override
    protected boolean isLockWhenHandle(AbstractChangeRequest request,
            CommandResponse response) {
        return false;
    }
    
    /**
      * 账户改变相关业务层<br/>
      * <功能详细描述>
      * @param request
      * @param response
      * @param loanAccount [参数说明]
      * 
      * @return void [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    protected void doHandle(AbstractChangeRequest request,
            CommandResponse response) {
        String loanAccountId = request.getLoanAccountId();
        AssertUtils.notNull(request.getLoanAccountId(),
                "request.loanAccountId is null.");
        
        //查询贷款账户实例
        LoanAccount loanAccount = loanAccountService.findById(loanAccountId);
        //做合法性校验
        this.receiver.validateRequest(request, response, loanAccount);
        
        //创建交易
        LATradingRecord tradingRecord = this.receiver
                .createTradingRecord(request, response, loanAccount);
        preProcessTradingRecord(request, loanAccount, tradingRecord);//交易前置处理
        
        //提交请求进行操作
        this.receiver.posthandle(request, response, loanAccount);
        //更新贷款账户的：
        //   最后更新时间
        this.loanAccountService.updateWhenChangeRequestHandle(loanAccount);
        
        //后置请求进行操作
        this.receiver.afterHandle(request, response, loanAccount);
        
        afterProcessTradingRecord(request, loanAccount, tradingRecord);//交易后置处理
        
        //如果交易记录存在，则添加交易记录
        if (tradingRecord != null) {
            this.tradingRecordService.insert(tradingRecord);
        }
        
        // 记录操作日志
        ServiceLoggerUtils.log(new LARequestLog(request, MessageUtils
                .format("账户编辑_[{}]", new Object[] { loanAccountId })));
    }
    
    /**
     * 前置交易记录处理 对交易记录中的request相关信息 loanAccount相关信息 默认信息等进行写入 <功能详细描述>
     * 
     * @param request
     * @param loanAccount
     * @param tradingRecord
     *            [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    private void preProcessTradingRecord(AbstractChangeRequest request,
            LoanAccount loanAccount, LATradingRecord tradingRecord) {
        AssertUtils.notNull(request, "request is null.");
        AssertUtils.notNull(tradingRecord, "tradingRecord is null.");
        
        Date nowTime = new Date();
        
        // 设置时间
        tradingRecord.setLastUpdateDate(request.getCreateDate() != null
                ? request.getCreateDate() : nowTime);
        tradingRecord.setCreateDate(request.getCreateDate() != null
                ? request.getCreateDate() : nowTime);
        
        //设置交易id
        if (StringUtils.isEmpty(tradingRecord.getId())) {
            tradingRecord.setId(LATradingRecordHelper
                    .generateLATradingRecordId(tradingRecord.getCreateDate()));
        }
        //交易的vcid应当与贷款账户一致
        tradingRecord.setVcid(loanAccount.getVcid());
        tradingRecord.setLoanAccountId(loanAccount.getId());
        
        // 交易与请求相关信息
        tradingRecord.setRequestId(request.getId());// 请求id
        tradingRecord.setOrganizationId(request.getOrganizationId());
        tradingRecord.setOperatorId(request.getOperatorId());
        tradingRecord.setLastUpdateOperatorId(request.getOperatorId());
        
        // 设置交易类型及交易记录类型
        tradingRecord.setCategory(request.getTradingCategory());
        tradingRecord.setType(request.getTradingRecordType());
        tradingRecord.setSummary(request.getTradingSummary());
        
        // 设置时间
        tradingRecord.setLastUpdateDate(nowTime);
        tradingRecord.setCreateDate(nowTime);
        
        // 构建交易的撤销相关信息
        tradingRecord.setRevoked(false);
        //tradingRecord.setRevokeResean(null);
        tradingRecord.setRevokeOperatorId(null);
        
        // 填入交易与贷款账户相关的信息
        tradingRecord.setWriteOff(loanAccount.isWriteOff());
        
        // 获取
        tradingRecord.setCurrentPeriod(loanAccount.getCurrentPeriod());
        //如果还期数为空，则显示为当前期数
        if (StringUtils.isEmpty(tradingRecord.getRepayDatePeriod())) {
            tradingRecord.setRepayDatePeriod(loanAccount.getCurrentPeriod());
        }
        
        // 设置交易前置信息
        tradingRecord.setBeforeAccountStatus(loanAccount.getAccountStatus());
        tradingRecord
                .setBeforeCollectionStatus(loanAccount.getCollectionStatus());
        tradingRecord.setBeforeIsClose(loanAccount.isClosed());
        tradingRecord.setBeforeIsDie(loanAccount.isDied());
        tradingRecord.setBeforeIsLegalProcedure(loanAccount.isLegalProcedure());
        tradingRecord.setBeforeSettleInterestStatus(
                loanAccount.getSettleInterestStatus());
        tradingRecord
                .setBeforePrincipalBalance(loanAccount.getPrincipalBalance());
        tradingRecord.setBeforePrincipalBalanceIrr(
                loanAccount.getPrincipalBalanceIrr());
        tradingRecord.setBeforeIsOverdue(loanAccount.isOverdue());
        tradingRecord.setBeforeOverdueDate(loanAccount.getOverdueDate());
        tradingRecord.setBeforeOverdueAmount(loanAccount.getOverdueAmount());
        tradingRecord.setBeforeOverdueSum(loanAccount.getOverdueSum());
        
        tradingRecord.setPrincipalBalance(loanAccount.getPrincipalBalance());
        tradingRecord
                .setPrincipalBalanceIrr(loanAccount.getPrincipalBalanceIrr());
    }
    
    /**
     * 后置交易处理用于记录交易完成后贷款账户的信息 <功能详细描述>
     * 
     * @param request
     * @param loanAccount
     * @param tradingRecord
     *            [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    private void afterProcessTradingRecord(AbstractChangeRequest request,
            LoanAccount loanAccount, LATradingRecord tradingRecord) {
        if (tradingRecord == null) {
            return;
        }
        // 设置交易前置信息
        tradingRecord.setAfterAccountStatus(loanAccount.getAccountStatus());
        tradingRecord
                .setAfterCollectionStatus(loanAccount.getCollectionStatus());
        tradingRecord.setAfterIsClose(loanAccount.isClosed());
        tradingRecord.setAfterIsDie(loanAccount.isDied());
        tradingRecord.setAfterIsLegalProcedure(loanAccount.isLegalProcedure());
        tradingRecord
                .setAfterPrincipalBalance(loanAccount.getPrincipalBalance());
        tradingRecord.setAfterPrincipalBalanceIrr(
                loanAccount.getPrincipalBalanceIrr());
        tradingRecord.setAfterSettleInterestStatus(
                loanAccount.getSettleInterestStatus());
        
        tradingRecord.setAfterIsOverdue(loanAccount.isOverdue());
        tradingRecord.setAfterOverdueDate(loanAccount.getOverdueDate());
        tradingRecord.setAfterOverdueAmount(loanAccount.getOverdueAmount());
        tradingRecord.setAfterOverdueSum(loanAccount.getOverdueSum());
    }
}
