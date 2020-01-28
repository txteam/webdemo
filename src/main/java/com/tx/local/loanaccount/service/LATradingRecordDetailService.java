/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2017年6月12日
 * <修改描述:>
 */
package com.tx.local.loanaccount.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.tx.core.exceptions.util.AssertUtils;
import com.tx.local.loanaccount.model.ChargeRecord;
import com.tx.local.loanaccount.model.ChargeRecordEntry;
import com.tx.local.loanaccount.model.ExemptRecord;
import com.tx.local.loanaccount.model.ExemptRecordEntry;
import com.tx.local.loanaccount.model.LATradingRecord;
import com.tx.local.loanaccount.model.LATradingRecordEntry;
import com.tx.local.loanaccount.model.PaymentRecord;
import com.tx.local.loanaccount.model.PaymentRecordEntry;

/**
  * 交易记录详情业务层<br/>
  * <功能详细描述>
  * 
  * @author  Administrator
  * @version  [版本号, 2017年6月12日]
  * @see  [相关类/方法]
  * @since  [产品/模块版本]
  */
@Component("laTradingRecordDetailService")
public class LATradingRecordDetailService {
    
    /** 交易记录业务层 */
    @Resource(name = "laTradingRecordService")
    private LATradingRecordService laTradingRecordService;
    
    /** 交易记录分项业务层 */
    @Resource(name = "laTradingRecordEntryService")
    private LATradingRecordEntryService tradingRecordEntryService;
    
    /** 实收记录业务层 */
    @Resource(name = "paymentRecordService")
    private PaymentRecordService paymentRecordService;
    
    /** 实收记录分项业务层 */
    @Resource(name = "paymentRecordEntryService")
    private PaymentRecordEntryService paymentRecordEntryService;
    
    /** 计费记录分项业务层 */
    @Resource(name = "chargeRecordService")
    private ChargeRecordService chargeRecordService;
    
    /** 计费记录分项业务层 */
    @Resource(name = "chargeRecordEntryService")
    private ChargeRecordEntryService chargeRecordEntryService;
    
    /** 计费记录分项业务层 */
    @Resource(name = "exemptRecordService")
    private ExemptRecordService exemptRecordService;
    
    /** 计费记录分项业务层 */
    @Resource(name = "exemptRecordEntryService")
    private ExemptRecordEntryService exemptRecordEntryService;
    
    /** 
     * 根据交易id查询实收记录详情<br/>
     * <功能详细描述>
     * @param tradingRecordId
     * @return [参数说明]
     * 
     * @return List<PaymentRecordEntry> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    private void setupPaymentRecord(List<LATradingRecord> resList, List<PaymentRecord> prList,
            List<PaymentRecordEntry> preList) {
        doSetupPaymentRecord(prList, preList);
        
        MultiValueMap<String, PaymentRecord> prMMap = new LinkedMultiValueMap<String, PaymentRecord>();
        for (PaymentRecord prTemp : prList) {
            prMMap.add(prTemp.getTradingRecord().getId(), prTemp);
        }
        MultiValueMap<String, PaymentRecordEntry> preMMap = new LinkedMultiValueMap<String, PaymentRecordEntry>();
        for (PaymentRecordEntry preTemp : preList) {
            preMMap.add(preTemp.getTradingRecord().getId(), preTemp);
        }
        for (LATradingRecord trTemp : resList) {
            //加载实收记录
            List<PaymentRecord> prListTemp = prMMap.get(trTemp.getId());
            List<PaymentRecordEntry> preListTemp = preMMap.get(trTemp.getId());
            if (!CollectionUtils.isEmpty(prList)) {
                trTemp.setPaymentRecordList(prListTemp);
                trTemp.setPaymentRecordEntryList(preListTemp);
            }
        }
    }
    
    /** 
     * 装载实收记录<br/>
     * <功能详细描述>
     * @param prList
     * @param preList [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    private void doSetupPaymentRecord(List<PaymentRecord> prList, List<PaymentRecordEntry> preList) {
        MultiValueMap<String, PaymentRecordEntry> entryMultiValueMap = new LinkedMultiValueMap<String, PaymentRecordEntry>();
        for (PaymentRecordEntry entry : preList) {
            entryMultiValueMap.add(entry.getPaymentRecord().getId(), entry);
        }
        for (PaymentRecord record : prList) {
            List<PaymentRecordEntry> entryList = entryMultiValueMap.get(record.getId());
            record.setPaymentRecordEntryList(entryList);
        }
    }
    
    /** 
     * 根据交易id查询实收记录详情<br/>
     * <功能详细描述>
     * @param tradingRecordId
     * @return [参数说明]
     * 
     * @return List<PaymentRecordEntry> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    private void setupChargeRecord(List<LATradingRecord> resList, List<ChargeRecord> crList,
            List<ChargeRecordEntry> creList) {
        doSetupChargeRecord(crList, creList);
        
        MultiValueMap<String, ChargeRecord> crMMap = new LinkedMultiValueMap<String, ChargeRecord>();
        for (ChargeRecord prTemp : crList) {
            crMMap.add(prTemp.getTradingRecord().getId(), prTemp);
        }
        MultiValueMap<String, ChargeRecordEntry> creMMap = new LinkedMultiValueMap<String, ChargeRecordEntry>();
        for (ChargeRecordEntry preTemp : creList) {
            creMMap.add(preTemp.getTradingRecord().getId(), preTemp);
        }
        for (LATradingRecord trTemp : resList) {
            //加载实收记录
            List<ChargeRecord> crListTemp = crMMap.get(trTemp.getId());
            List<ChargeRecordEntry> preListTemp = creMMap.get(trTemp.getId());
            if (!CollectionUtils.isEmpty(crListTemp)) {
                trTemp.setChargeRecordList(crListTemp);
                trTemp.setChargeRecordEntryList(preListTemp);
            }
        }
    }
    
    /** 
     * 装载计费记录<br/>
     * <功能详细描述>
     * @param crList
     * @param creList [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    private void doSetupChargeRecord(List<ChargeRecord> crList, List<ChargeRecordEntry> creList) {
        MultiValueMap<String, ChargeRecordEntry> entryMultiValueMap = new LinkedMultiValueMap<String, ChargeRecordEntry>();
        for (ChargeRecordEntry entry : creList) {
            entryMultiValueMap.add(entry.getChargeRecord().getId(), entry);
        }
        for (ChargeRecord record : crList) {
            List<ChargeRecordEntry> entryList = entryMultiValueMap.get(record.getId());
            record.setChargeRecordEntryList(entryList);
        }
    }
    
    /** 
     * 根据交易id查询实收记录详情<br/>
     * <功能详细描述>
     * @param tradingRecordId
     * @return [参数说明]
     * 
     * @return List<PaymentRecordEntry> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    private void setupExemptRecord(List<LATradingRecord> resList, List<ExemptRecord> erList,
            List<ExemptRecordEntry> ereList) {
        doSetupExemptRecord(erList, ereList);
        
        MultiValueMap<String, ExemptRecord> crMMap = new LinkedMultiValueMap<String, ExemptRecord>();
        for (ExemptRecord prTemp : erList) {
            crMMap.add(prTemp.getTradingRecord().getId(), prTemp);
        }
        MultiValueMap<String, ExemptRecordEntry> creMMap = new LinkedMultiValueMap<String, ExemptRecordEntry>();
        for (ExemptRecordEntry preTemp : ereList) {
            creMMap.add(preTemp.getTradingRecord().getId(), preTemp);
        }
        for (LATradingRecord trTemp : resList) {
            //加载实收记录
            List<ExemptRecord> crListTemp = crMMap.get(trTemp.getId());
            List<ExemptRecordEntry> ereListTemp = creMMap.get(trTemp.getId());
            if (!CollectionUtils.isEmpty(crListTemp)) {
                trTemp.setExemptRecordList(crListTemp);
                trTemp.setExemptRecordEntryList(ereListTemp);
            }
        }
    }
    
    /** 
     * 装载豁免详情<br/>
     * <功能详细描述>
     * @param erList
     * @param ereList [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    private void doSetupExemptRecord(List<ExemptRecord> erList, List<ExemptRecordEntry> ereList) {
        MultiValueMap<String, ExemptRecordEntry> entryMultiValueMap = new LinkedMultiValueMap<String, ExemptRecordEntry>();
        for (ExemptRecordEntry entry : ereList) {
            entryMultiValueMap.add(entry.getExemptRecord().getId(), entry);
        }
        for (ExemptRecord record : erList) {
            List<ExemptRecordEntry> entryList = entryMultiValueMap.get(record.getId());
            record.setExemptRecordEntryList(entryList);
        }
    }
    
    /**
     * 根据id查询交易记录并附带分项<br/>
     * <功能详细描述>
     * @param id
     * @return [参数说明]
     * 
     * @return TradingRecord [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
    public LATradingRecord findRepaymentDetailById(String id) {
        AssertUtils.notEmpty(id, "id is empty.");
        
        LATradingRecord res = this.laTradingRecordService.findById(id);
        
        //查询交易记录
        List<LATradingRecordEntry> tradingRecordEntryList = this.tradingRecordEntryService
                .queryListByTradingRecordId(id);
        if (!CollectionUtils.isEmpty(tradingRecordEntryList)) {
            res.setTradingRecordEntryList(tradingRecordEntryList);
        }
        
        //加载实收记录
        List<PaymentRecord> prList = this.paymentRecordService.queryListByTradingRecordId(id);
        List<PaymentRecordEntry> preList = this.paymentRecordEntryService.queryListByTradingRecordId(id);
        if (!CollectionUtils.isEmpty(prList)) {
            doSetupPaymentRecord(prList, preList);
            res.setPaymentRecordList(prList);
            res.setPaymentRecordEntryList(preList);
        }
        return res;
    }
    
    /**
     * 根据id查询交易记录并附带分项<br/>
     * <功能详细描述>
     * @param id
     * @return [参数说明]
     * 
     * @return TradingRecord [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
    public void setupRepaymentDetail(LATradingRecord tradingRecord) {
        AssertUtils.notNull(tradingRecord, "tradingRecord is null.");
        AssertUtils.notEmpty(tradingRecord.getId(), "tradingRecord.id is empty.");
        
        String id = tradingRecord.getId();
        //查询交易记录
        List<LATradingRecordEntry> tradingRecordEntryList = this.tradingRecordEntryService
                .queryListByTradingRecordId(id);
        if (!CollectionUtils.isEmpty(tradingRecordEntryList)) {
            tradingRecord.setTradingRecordEntryList(tradingRecordEntryList);
        }
        
        //加载实收记录
        List<PaymentRecord> prList = this.paymentRecordService.queryListByTradingRecordId(id);
        List<PaymentRecordEntry> preList = this.paymentRecordEntryService.queryListByTradingRecordId(id);
        if (!CollectionUtils.isEmpty(prList)) {
            doSetupPaymentRecord(prList, preList);
            tradingRecord.setPaymentRecordList(prList);
            tradingRecord.setPaymentRecordEntryList(preList);
        }
    }
    
    /**
     * 根据贷款账户id和交易类型查询交易记录和还款记录
     * <功能详细描述>
     * @param loanAccountId
     * @param tradingTypes
     * @return [参数说明]
     * 
     * @return List<TradingRecord> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
    public List<LATradingRecord> queryRepaymentListByLoanAccountId(String loanAccountId, Boolean viewAble,
            Boolean revokeAble, Map<String, Object> params) {
        AssertUtils.notEmpty(loanAccountId, "loanAccountId is empty.");
        
        params = params == null ? new HashMap<>() : params;
        List<LATradingRecord> resList = this.laTradingRecordService.queryListByLoanAccountId(loanAccountId,
                viewAble,
                revokeAble,
                params);
        
        //装载交易分项数据
        List<LATradingRecordEntry> tradingRecordEntryList = this.tradingRecordEntryService
                .queryListByLoanAccountId(loanAccountId);
        MultiValueMap<String, LATradingRecordEntry> treMMap = new LinkedMultiValueMap<String, LATradingRecordEntry>();
        for (LATradingRecordEntry tradingRecordEntry : tradingRecordEntryList) {
            treMMap.add(tradingRecordEntry.getTradingRecord().getId(), tradingRecordEntry);
        }
        for (LATradingRecord trTemp : resList) {
            List<LATradingRecordEntry> treListTemp = treMMap.get(trTemp.getId());
            //查询交易记录 
            if (!CollectionUtils.isEmpty(treListTemp)) {
                trTemp.setTradingRecordEntryList(treListTemp);
            }
        }
        
        List<PaymentRecord> prList = this.paymentRecordService.queryListByLoanAccountId(loanAccountId);//装载实收记录
        List<PaymentRecordEntry> preList = this.paymentRecordEntryService.queryListByLoanAccountId(loanAccountId);//装载实收分项记录
        setupPaymentRecord(resList, prList, preList);
        
        return resList;
    }
    
    /**
     * 根据贷款账户id和交易类型查询交易记录和还款记录
     * <功能详细描述>
     * @param loanAccountId
     * @param tradingTypes
     * @return [参数说明]
     * 
     * @return List<TradingRecord> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
    public List<LATradingRecord> queryListByLoanAccountId(String loanAccountId, Boolean viewAble, Boolean revokeAble,
            Map<String, Object> params) {
        AssertUtils.notEmpty(loanAccountId, "loanAccountId is empty.");
        
        params = params == null ? new HashMap<>() : params;
        List<LATradingRecord> resList = this.laTradingRecordService.queryListByLoanAccountId(loanAccountId,
                viewAble,
                revokeAble,
                params);
        
        //装载交易分项数据
        List<LATradingRecordEntry> tradingRecordEntryList = this.tradingRecordEntryService
                .queryListByLoanAccountId(loanAccountId);
        MultiValueMap<String, LATradingRecordEntry> treMMap = new LinkedMultiValueMap<String, LATradingRecordEntry>();
        for (LATradingRecordEntry tradingRecordEntry : tradingRecordEntryList) {
            treMMap.add(tradingRecordEntry.getTradingRecord().getId(), tradingRecordEntry);
        }
        for (LATradingRecord trTemp : resList) {
            List<LATradingRecordEntry> treListTemp = treMMap.get(trTemp.getId());
            //查询交易记录 
            if (!CollectionUtils.isEmpty(treListTemp)) {
                trTemp.setTradingRecordEntryList(treListTemp);
            }
        }
        
        List<PaymentRecord> prList = this.paymentRecordService.queryListByLoanAccountId(loanAccountId);//装载实收记录
        List<PaymentRecordEntry> preList = this.paymentRecordEntryService.queryListByLoanAccountId(loanAccountId);//装载实收分项记录
        setupPaymentRecord(resList, prList, preList);
        
        List<ChargeRecord> crList = this.chargeRecordService.queryListByLoanAccountId(loanAccountId);//装载实收记录
        List<ChargeRecordEntry> creList = this.chargeRecordEntryService.queryListByLoanAccountId(loanAccountId);//装载实收分项记录
        setupChargeRecord(resList, crList, creList);
        
        List<ExemptRecord> erList = this.exemptRecordService.queryListByLoanAccountId(loanAccountId);//装载实收记录
        List<ExemptRecordEntry> ereList = this.exemptRecordEntryService.queryListByLoanAccountId(loanAccountId);//装载实收分项记录
        setupExemptRecord(resList, erList, ereList);
        return resList;
    }
    
    //    /**
    //     * 根据贷款账户id和交易类型查询交易记录
    //     * <功能详细描述>
    //     * @param loanAccountId
    //     * @param tradingTypes
    //     * @return [参数说明]
    //     * 
    //     * @return List<TradingRecord> [返回类型说明]
    //     * @exception throws [异常类型] [异常说明]
    //     * @see [类、类#方法、类#成员]
    //    */
    //    public List<LATradingRecord> queryListByLoanAccountId(String loanAccountId, LATradingCategoryEnum[] tradingTypes) {
    //        AssertUtils.notEmpty(loanAccountId, "loanAccountId is empty.");
    //        //AssertUtils.notEmpty(tradingTypes,"tradingTypes is empty.");
    //        
    //        Map<String, Object> params = new HashMap<String, Object>();
    //        params.put("loanAccountId", loanAccountId);
    //        params.put("tradingTypes", tradingTypes);
    //        
    //        List<LATradingRecord> resList = this.tradingRecordDao.queryList(params);
    //        
    //        List<LATradingRecordEntry> tradingRecordEntryList = this.tradingRecordEntryService
    //                .queryListByLoanAccountId(loanAccountId);
    //        
    //        MultiValueMap<String, LATradingRecordEntry> entryMultiValueMap = new LinkedMultiValueMap<String, LATradingRecordEntry>();
    //        for (LATradingRecordEntry tradingRecordEntry : tradingRecordEntryList) {
    //            entryMultiValueMap.add(tradingRecordEntry.getTradingRecord().getId(), tradingRecordEntry);
    //        }
    //        for (LATradingRecord tradingRecord : resList) {
    //            List<LATradingRecordEntry> entryList = entryMultiValueMap.get(tradingRecord.getId());
    //            if (!CollectionUtils.isEmpty(entryList)) {
    //                tradingRecord.setTradingRecordEntryList(entryList);
    //            }
    //        }
    //        return resList;
    //    }
    //    /**
    //     * 根据贷款账户id和交易类型查询交易记录和还款记录
    //     *<功能详细描述>
    //     * @param loanAccountId
    //     * @param tradingTypes
    //     * @param minProcessDate
    //     * @param maxProcessDate
    //     * @return [参数说明]
    //     * 
    //     * @return List<TradingRecord> [返回类型说明]
    //     * @exception throws [异常类型] [异常说明]
    //     * @see [类、类#方法、类#成员]
    //    */
    //    public List<LATradingRecord> queryListByLoanAccountId(String loanAccountId, LATradingCategoryEnum[] tradingTypes,
    //            Date minProcessDate, Date maxProcessDate) {
    //        AssertUtils.notEmpty(loanAccountId, "loanAccountId is empty.");
    //        
    //        Map<String, Object> params = new HashMap<String, Object>();
    //        params.put("loanAccountId", loanAccountId);
    //        params.put("tradingTypes", tradingTypes);
    //        params.put("minProcessDate", minProcessDate);
    //        params.put("maxProcessDate", maxProcessDate);
    //        
    //        List<LATradingRecord> resList = this.tradingRecordDao.queryList(params);
    //        
    //        List<LATradingRecordEntry> tradingRecordEntryList = this.tradingRecordEntryService
    //                .queryListByLoanAccountId(loanAccountId);
    //        
    //        List<PaymentRecord> paymentRecordList = this.paymentRecordService.queryDetailListByLoanAccountId(loanAccountId);
    //        
    //        MultiValueMap<String, LATradingRecordEntry> entryMultiValueMap = new LinkedMultiValueMap<String, LATradingRecordEntry>();
    //        MultiValueMap<String, PaymentRecord> paymentRecordMultiValueMap = new LinkedMultiValueMap<String, PaymentRecord>();
    //        
    //        for (LATradingRecordEntry tradingRecordEntry : tradingRecordEntryList) {
    //            entryMultiValueMap.add(tradingRecordEntry.getTradingRecord().getId(), tradingRecordEntry);
    //        }
    //        for (PaymentRecord paymentRecord : paymentRecordList) {
    //            paymentRecordMultiValueMap.add(paymentRecord.getTradingRecord().getId(), paymentRecord);
    //        }
    //        for (LATradingRecord tradingRecord : resList) {
    //            List<LATradingRecordEntry> entryList = entryMultiValueMap.get(tradingRecord.getId());
    //            if (!CollectionUtils.isEmpty(entryList)) {
    //                tradingRecord.setTradingRecordEntryList(entryList);
    //            }
    //            
    //            List<PaymentRecord> recordList = paymentRecordMultiValueMap.get(tradingRecord.getId());
    //            List<PaymentRecordEntry> preList = new ArrayList<>();
    //            if (!CollectionUtils.isEmpty(recordList)) {
    //                tradingRecord.setPaymentRecordList(recordList);
    //                
    //                for (PaymentRecord prTemp : recordList) {
    //                    if (CollectionUtils.isEmpty(prTemp.getPaymentRecordEntryList())) {
    //                        continue;
    //                    }
    //                    preList.addAll(prTemp.getPaymentRecordEntryList());
    //                }
    //            }
    //            tradingRecord.setPaymentRecordEntryList(preList);
    //        }
    //        return resList;
    //    }
}
