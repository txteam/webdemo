package com.tx.local.basicdata.model;
///*
// * 描          述:  <描述>
// * 修  改   人:  Administrator
// * 修改时间:  2016年12月29日
// * <修改描述:>
// */
//package com.tx.component.basicdata.model;
//
//import java.io.Serializable;
//import java.util.Date;
//
//import javax.persistence.Entity;
//import javax.persistence.Table;
//
//import com.tx.component.basicdata.annotation.BasicDataType;
//import com.tx.core.jdbc.sqlsource.annotation.QueryConditionEqual;
//import com.tx.core.jdbc.sqlsource.annotation.UpdateAble;
//import com.tx.core.support.initable.model.ConfigInitAble;
//
///**
// * 费用项<br/>
// * <功能详细描述>
// * 
// * @author  Administrator
// * @version  [版本号, 2016年12月29日]
// * @see  [相关类/方法]
// * @since  [产品/模块版本]
// */
//@Entity
//@Table(name = "bd_feeitem")
//@BasicDataType(name = "费用信息", common = false)
//public class FeeItem implements Serializable, ConfigInitAble, BasicData {
//    
//    /** 注释内容 */
//    private static final long serialVersionUID = 3513079230545512716L;
//    
//    /** 费用项：唯一键 */
//    private String id;
//    
//    /** 银行英文简称*/
//    @UpdateAble
//    @QueryConditionEqual
//    private String code;
//    
//    /** 费用项枚举Key */
//    @QueryConditionEqual
//    private FeeItemEnum feeItem;
//    
//    /** 费用项归属方关键字 */
//    @QueryConditionEqual
//    private FeeOwnershipEnum ownership;
//    
//    /** 是否有效 */
//    @UpdateAble
//    @QueryConditionEqual
//    private boolean valid;
//    
//    /** 是否可编辑 */
//    @UpdateAble
//    @QueryConditionEqual
//    private boolean modifyAble = true;
//    
//    /** 费用项名称 */
//    @UpdateAble
//    @QueryConditionEqual
//    private String name;
//    
//    /** 是否本金 */
//    @UpdateAble
//    @QueryConditionEqual
//    private boolean principal;
//    
//    /** 是否逾期依赖 */
//    @UpdateAble
//    @QueryConditionEqual
//    private boolean overdueDepend;
//    
//    /** 还款优先级 */
//    @UpdateAble
//    private int repayPriority;
//    
//    /** 停息后还款优先级 */
//    @UpdateAble
//    private int stopInterestRepayPriority;
//    
//    /** 是否可豁免 */
//    @UpdateAble
//    @QueryConditionEqual
//    private boolean exemptAble;
//    
//    /** 豁免优先级 */
//    @UpdateAble
//    private int exemptPriority;
//    
//    /** 备注 */
//    @UpdateAble
//    private String remark;
//    
//    /** 创建时间 */
//    private Date createDate;
//    
//    /** 最后更新时间 */
//    @UpdateAble
//    private Date lastUpdateDate;
//    
//    /**
//     * @return 返回 id
//     */
//    public String getId() {
//        return id;
//    }
//    
//    /**
//     * @param 对id进行赋值
//     */
//    public void setId(String id) {
//        this.id = id;
//    }
//    
//    /**
//     * @return 返回 feeItem
//     */
//    public FeeItemEnum getFeeItem() {
//        return feeItem;
//    }
//    
//    /**
//     * @param 对feeItem进行赋值
//     */
//    public void setFeeItem(FeeItemEnum feeItem) {
//        this.feeItem = feeItem;
//    }
//    
//    /**
//     * @return 返回 ownership
//     */
//    public FeeOwnershipEnum getOwnership() {
//        return ownership;
//    }
//    
//    /**
//     * @param 对ownership进行赋值
//     */
//    public void setOwnership(FeeOwnershipEnum ownership) {
//        this.ownership = ownership;
//    }
//    
//    /**
//     * @return 返回 code
//     */
//    public String getCode() {
//        return code;
//    }
//    
//    /**
//     * @param 对code进行赋值
//     */
//    public void setCode(String code) {
//        this.code = code;
//    }
//    
//    /**
//     * @return 返回 valid
//     */
//    public boolean isValid() {
//        return valid;
//    }
//    
//    /**
//     * @param 对valid进行赋值
//     */
//    public void setValid(boolean valid) {
//        this.valid = valid;
//    }
//    
//    /**
//     * @return 返回 modifyAble
//     */
//    public boolean isModifyAble() {
//        return modifyAble;
//    }
//    
//    /**
//     * @param 对modifyAble进行赋值
//     */
//    public void setModifyAble(boolean modifyAble) {
//        this.modifyAble = modifyAble;
//    }
//    
//    /**
//     * @return 返回 name
//     */
//    public String getName() {
//        return name;
//    }
//    
//    /**
//     * @param 对name进行赋值
//     */
//    public void setName(String name) {
//        this.name = name;
//    }
//    
//    /**
//     * @return 返回 principal
//     */
//    public boolean isPrincipal() {
//        return principal;
//    }
//    
//    /**
//     * @param 对principal进行赋值
//     */
//    public void setPrincipal(boolean principal) {
//        this.principal = principal;
//    }
//    
//    /**
//     * @return 返回 overdueDepend
//     */
//    public boolean isOverdueDepend() {
//        return overdueDepend;
//    }
//    
//    /**
//     * @param 对overdueDepend进行赋值
//     */
//    public void setOverdueDepend(boolean overdueDepend) {
//        this.overdueDepend = overdueDepend;
//    }
//    
//    /**
//     * @return 返回 repayPriority
//     */
//    public int getRepayPriority() {
//        return repayPriority;
//    }
//
//    /**
//     * @param 对repayPriority进行赋值
//     */
//    public void setRepayPriority(int repayPriority) {
//        this.repayPriority = repayPriority;
//    }
//
//    /**
//     * @return 返回 stopInterestRepayPriority
//     */
//    public int getStopInterestRepayPriority() {
//        return stopInterestRepayPriority;
//    }
//
//    /**
//     * @param 对stopInterestRepayPriority进行赋值
//     */
//    public void setStopInterestRepayPriority(int stopInterestRepayPriority) {
//        this.stopInterestRepayPriority = stopInterestRepayPriority;
//    }
//
//    /**
//     * @return 返回 exemptAble
//     */
//    public boolean isExemptAble() {
//        return exemptAble;
//    }
//
//    /**
//     * @param 对exemptAble进行赋值
//     */
//    public void setExemptAble(boolean exemptAble) {
//        this.exemptAble = exemptAble;
//    }
//
//    /**
//     * @return 返回 exemptPriority
//     */
//    public int getExemptPriority() {
//        return exemptPriority;
//    }
//
//    /**
//     * @param 对exemptPriority进行赋值
//     */
//    public void setExemptPriority(int exemptPriority) {
//        this.exemptPriority = exemptPriority;
//    }
//
//    /**
//     * @return 返回 remark
//     */
//    public String getRemark() {
//        return remark;
//    }
//    
//    /**
//     * @param 对remark进行赋值
//     */
//    public void setRemark(String remark) {
//        this.remark = remark;
//    }
//    
//    /**
//     * @return 返回 createDate
//     */
//    public Date getCreateDate() {
//        return createDate;
//    }
//    
//    /**
//     * @param 对createDate进行赋值
//     */
//    public void setCreateDate(Date createDate) {
//        this.createDate = createDate;
//    }
//    
//    /**
//     * @return 返回 lastUpdateDate
//     */
//    public Date getLastUpdateDate() {
//        return lastUpdateDate;
//    }
//    
//    /**
//     * @param 对lastUpdateDate进行赋值
//     */
//    public void setLastUpdateDate(Date lastUpdateDate) {
//        this.lastUpdateDate = lastUpdateDate;
//    }
//}
