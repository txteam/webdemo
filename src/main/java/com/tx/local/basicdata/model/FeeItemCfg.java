/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2017年5月19日
 * <修改描述:>
 */
package com.tx.local.basicdata.model;

/**
 * 费用项配置<br/>
 *    根据类型差异而与具体的哪一客户无关的设置<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2017年5月19日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class FeeItemCfg {
    
    /** 费用项 */
    private FeeItemEnum feeItem;
    
    /** 还款分配优先级： */
    private int repayPriority;
    
    /** 停息后还款优先级 */
    private int stopInterestRepayPriority;
    
    /** 豁免分配优先级： */
    private int exemptPriority;
    
    /** 是否是本金： */
    private boolean principal;
    
    /** 是否可豁免 */
    private boolean exemptAble;
    
    /** 是否逾期依赖 */
    private boolean overdueDepend;
    
    /** <默认构造函数> */
    public FeeItemCfg() {
        super();
    }
    
    /** <默认构造函数> */
    public FeeItemCfg(FeeItemEnum feeItem, boolean overdueDepend, int repayPriority, int exemptPriority,
            int stopInterestRepayPriority) {
        super();
        this.principal = false;
        this.exemptAble = true;
        
        this.feeItem = feeItem;
        this.overdueDepend = overdueDepend;
        this.repayPriority = repayPriority;
        this.exemptPriority = exemptPriority;
        this.stopInterestRepayPriority = stopInterestRepayPriority;
    }
    
    /** <默认构造函数> */
    public FeeItemCfg(FeeItemEnum feeItem, boolean principal, boolean exemptAble, boolean overdueDepend,
            int repayPriority, int exemptPriority, int stopInterestRepayPriority) {
        super();
        this.feeItem = feeItem;
        this.repayPriority = repayPriority;
        this.stopInterestRepayPriority = stopInterestRepayPriority;
        this.exemptPriority = exemptPriority;
        this.principal = principal;
        this.exemptAble = exemptAble;
        this.overdueDepend = overdueDepend;
    }
    
    /**
     * @return 返回 feeItem
     */
    public FeeItemEnum getFeeItem() {
        return feeItem;
    }
    
    /**
     * @param 对feeItem进行赋值
     */
    public void setFeeItem(FeeItemEnum feeItem) {
        this.feeItem = feeItem;
    }
    
    /**
     * @return 返回 repayPriority
     */
    public int getRepayPriority() {
        return repayPriority;
    }
    
    /**
     * @param 对repayPriority进行赋值
     */
    public void setRepayPriority(int repayPriority) {
        this.repayPriority = repayPriority;
    }
    
    /**
     * @return 返回 stopInterestRepayPriority
     */
    public int getStopInterestRepayPriority() {
        return stopInterestRepayPriority;
    }
    
    /**
     * @param 对stopInterestRepayPriority进行赋值
     */
    public void setStopInterestRepayPriority(int stopInterestRepayPriority) {
        this.stopInterestRepayPriority = stopInterestRepayPriority;
    }
    
    /**
     * @return 返回 exemptPriority
     */
    public int getExemptPriority() {
        return exemptPriority;
    }
    
    /**
     * @param 对exemptPriority进行赋值
     */
    public void setExemptPriority(int exemptPriority) {
        this.exemptPriority = exemptPriority;
    }
    
    /**
     * @return 返回 principal
     */
    public boolean isPrincipal() {
        return principal;
    }
    
    /**
     * @param 对principal进行赋值
     */
    public void setPrincipal(boolean principal) {
        this.principal = principal;
    }
    
    /**
     * @return 返回 exemptAble
     */
    public boolean isExemptAble() {
        return exemptAble;
    }
    
    /**
     * @param 对exemptAble进行赋值
     */
    public void setExemptAble(boolean exemptAble) {
        this.exemptAble = exemptAble;
    }
    
    /**
     * @return 返回 overdueDepend
     */
    public boolean isOverdueDepend() {
        return overdueDepend;
    }
    
    /**
     * @param 对overdueDepend进行赋值
     */
    public void setOverdueDepend(boolean overdueDepend) {
        this.overdueDepend = overdueDepend;
    }
}
