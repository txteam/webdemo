/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2017年5月17日
 * <修改描述:>
 */
package com.tx.local.loanaccount.helper.feecfgitem;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import com.tx.core.exceptions.util.AssertUtils;
import com.tx.local.basicdata.model.FeeItemEnum;
import com.tx.local.loanaccount.model.LoanAccountFeeItem;
import com.tx.local.loanaccount.service.LoanAccountFeeItemService;

/**
  * 贷款账户费用项Map
  * <功能详细描述>
  * 
  * @author  Administrator
  * @version  [版本号, 2017年5月17日]
  * @see  [相关类/方法]
  * @since  [产品/模块版本]
  */
public class LoanAccountFeeItemLazyMap
        extends TreeMap<FeeItemEnum, LoanAccountFeeItem> {
    
    /** 注释内容 */
    private static final long serialVersionUID = 1141975525766949905L;
    
    /** 是否已加载 */
    private boolean loaded = false;
    
    /** 贷款账户id */
    private final String loanAccountId;
    
    /** 业务层句柄 */
    private LoanAccountFeeItemService loanAccountFeeItemService;
    
    /** <默认构造函数> */
    public LoanAccountFeeItemLazyMap(String loanAccountId,
            LoanAccountFeeItemService loanAccountFeeItemService) {
        super();
        AssertUtils.notEmpty(loanAccountId, "loanAccountId is empty.");
        AssertUtils.notNull(loanAccountFeeItemService,
                "loanAccountFeeItemService is empty.");
        this.loanAccountId = loanAccountId;
        this.loanAccountFeeItemService = loanAccountFeeItemService;
    }
    
    /** <默认构造函数> */
    public LoanAccountFeeItemLazyMap(String loanAccountId,
            List<LoanAccountFeeItem> feeItemList) {
        super();
        AssertUtils.notEmpty(loanAccountId, "loanAccountId is empty.");
        AssertUtils.notNull(feeItemList, "feeItemList is null.");
        
        this.loanAccountId = loanAccountId;
        for (LoanAccountFeeItem fci : feeItemList) {
            super.put(fci.getFeeItem(), fci);
        }
        this.loaded = true;
    }
    
    /**
      * 加载贷款账户费用项设置<br/>
      * <功能详细描述> [参数说明]
      * 
      * @return void [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    private void loadFeeCfgItem() {
        if (loaded) {
            return;
        }
        List<LoanAccountFeeItem> feeItems = this.loanAccountFeeItemService
                .queryListByLoanAccountId(this.loanAccountId);
        for (LoanAccountFeeItem fci : feeItems) {
            super.put(fci.getFeeItem(), fci);
        }
        this.loaded = true;
    }
    
    /**
     * @param key
     * @return
     */
    @Override
    public LoanAccountFeeItem get(Object key) {
        loadFeeCfgItem();
        return super.get(key);
    }
    
    /**
     * @param key
     * @return
     */
    @Override
    public boolean containsKey(Object key) {
        loadFeeCfgItem();
        return super.containsKey(key);
    }
    
    /**
     * @param value
     * @return
     */
    @Override
    public boolean containsValue(Object value) {
        loadFeeCfgItem();
        return super.containsValue(value);
    }
    
    /**
     * @return
     */
    @Override
    public Set<FeeItemEnum> keySet() {
        loadFeeCfgItem();
        return super.keySet();
    }
    
    /**
     * @return
     */
    @Override
    public Collection<LoanAccountFeeItem> values() {
        loadFeeCfgItem();
        return super.values();
    }
    
    /**
     * @return
     */
    @Override
    public Set<Entry<FeeItemEnum, LoanAccountFeeItem>> entrySet() {
        loadFeeCfgItem();
        return super.entrySet();
    }
    
    /**
     * @return
     */
    @Override
    public boolean isEmpty() {
        loadFeeCfgItem();
        return super.isEmpty();
    }
    
    /**
     * @param key
     * @param value
     * @return
     */
    @Override
    public LoanAccountFeeItem putIfAbsent(FeeItemEnum key,
            LoanAccountFeeItem value) {
        throw new UnsupportedOperationException();
    }
    
    /**
     * @param map
     */
    @Override
    public void putAll(
            Map<? extends FeeItemEnum, ? extends LoanAccountFeeItem> map) {
        throw new UnsupportedOperationException();
    }
    
    /**
     * @param key
     * @param value
     * @return
     */
    @Override
    public LoanAccountFeeItem put(FeeItemEnum key, LoanAccountFeeItem value) {
        throw new UnsupportedOperationException();
    }
}
