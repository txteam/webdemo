/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2017年6月23日
 * <修改描述:>
 */
package com.tx.local.loanaccount.strategy.feeitem;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.tx.core.exceptions.util.AssertUtils;
import com.tx.local.basicdata.model.FeeItemCfg;
import com.tx.local.basicdata.model.FeeItemEnum;
import com.tx.local.loanaccount.model.ExemptIntention;
import com.tx.local.loanaccount.model.FeeItemGroup;
import com.tx.local.loanaccount.model.FeeItemGroupTypeEnum;
import com.tx.local.loanaccount.model.RepayIntention;

/**
  * 费用项目策略实现<br/>
  * <功能详细描述>
  * 
  * @author  Administrator
  * @version  [版本号, 2017年6月23日]
  * @see  [相关类/方法]
  * @since  [产品/模块版本]
  */
public abstract class AbstractFeeItemStrategy
        implements FeeItemStrategy, InitializingBean {
    
    /** 费用项配置映射 */
    protected final Map<FeeItemEnum, FeeItemCfg> feeItemCfgMap = new HashMap<>();
    
    /** 费用项分组映射 */
    protected final List<FeeItemGroup> repayFeeItemGroups = new ArrayList<>();
    
    /** 费用项分组映射 */
    protected final List<FeeItemGroup> settleFeeItemGroups = new ArrayList<>();
    
    /** 费用项分组映射 */
    protected final List<FeeItemGroup> repayExemptFeeItemGroups = new ArrayList<>();
    
    /** 费用项分组映射 */
    protected final List<FeeItemGroup> settleExemptFeeItemGroups = new ArrayList<>();
    
    /**
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        initFeeItemCfgMap();
        
        initRepayFeeItemGroups();
        initSettleFeeItemGroups();
        
        initRepayExemptFeeItemGroups();
        initSettleExemptFeeItemGroups();
    }
    
    /**
     * 初始化费用项配置<br/>
     */
    protected void initFeeItemCfgMap() {
        //设置费用项配置映射
        this.feeItemCfgMap.put(FeeItemEnum.DK_DQKCF,
                new FeeItemCfg(FeeItemEnum.DK_DQKCF, false, 0, 0, 0));//贷前考察费
        this.feeItemCfgMap.put(FeeItemEnum.ZX_DQKCF,
                new FeeItemCfg(FeeItemEnum.ZX_DQKCF, false, 0, 0, 0));//贷前考察费
        this.feeItemCfgMap.put(FeeItemEnum.DK_YCXSXF,
                new FeeItemCfg(FeeItemEnum.DK_YCXSXF, false, 0, 0, 0));//一次性手续费
        this.feeItemCfgMap.put(FeeItemEnum.ZX_YCXSXF,
                new FeeItemCfg(FeeItemEnum.ZX_YCXSXF, false, 0, 0, 0));//一次性手续费
        this.feeItemCfgMap.put(FeeItemEnum.DK_DQYQF,
                new FeeItemCfg(FeeItemEnum.DK_DQYQF, false, 0, 0, 0));//贷前延期非
        this.feeItemCfgMap.put(FeeItemEnum.ZX_DQYQF,
                new FeeItemCfg(FeeItemEnum.ZX_DQYQF, false, 0, 0, 0));//贷前延期非
        
        //逾期依赖费用
        this.feeItemCfgMap.put(FeeItemEnum.ZX_GLF,
                new FeeItemCfg(FeeItemEnum.ZX_GLF, true, 1, 11, 2));//管理费
        this.feeItemCfgMap.put(FeeItemEnum.DK_GLF,
                new FeeItemCfg(FeeItemEnum.DK_GLF, true, 1, 11, 2));//管理费
        this.feeItemCfgMap.put(FeeItemEnum.DK_LX,
                new FeeItemCfg(FeeItemEnum.DK_LX, true, 2, 10, 3));//利息
        this.feeItemCfgMap.put(FeeItemEnum.DK_BJ,
                new FeeItemCfg(FeeItemEnum.DK_BJ, true, false, true, 13, 0, 1));//本金
        
        //逾期利息
        this.feeItemCfgMap.put(FeeItemEnum.ZX_YQLX,
                new FeeItemCfg(FeeItemEnum.ZX_YQLX, false, 3, 9, 4));//逾期利息
        this.feeItemCfgMap.put(FeeItemEnum.DK_YQLX,
                new FeeItemCfg(FeeItemEnum.DK_YQLX, false, 4, 8, 5));//逾期利息
        this.feeItemCfgMap.put(FeeItemEnum.ZX_KQSBSXF,
                new FeeItemCfg(FeeItemEnum.ZX_KQSBSXF, false, 5, 7, 6));//扣款失败手续费
        this.feeItemCfgMap.put(FeeItemEnum.DK_KQSBSXF,
                new FeeItemCfg(FeeItemEnum.DK_KQSBSXF, false, 6, 6, 7));//扣款失败手续费
        this.feeItemCfgMap.put(FeeItemEnum.ZX_WBYJ,
                new FeeItemCfg(FeeItemEnum.ZX_WBYJ, false, 7, 5, 8));//外包佣金
        this.feeItemCfgMap.put(FeeItemEnum.DK_WBYJ,
                new FeeItemCfg(FeeItemEnum.DK_WBYJ, false, 8, 4, 9));//外包佣金
        this.feeItemCfgMap.put(FeeItemEnum.ZX_DHZQF,
                new FeeItemCfg(FeeItemEnum.ZX_DHZQF, false, 9, 3, 10));//贷后展期费
        this.feeItemCfgMap.put(FeeItemEnum.DK_DHZQF,
                new FeeItemCfg(FeeItemEnum.DK_DHZQF, false, 10, 2, 11));//贷后展期费
        this.feeItemCfgMap.put(FeeItemEnum.ZX_TQHKWYJ,
                new FeeItemCfg(FeeItemEnum.ZX_TQHKWYJ, false, 11, 1, 12));//提前还款违约金
        this.feeItemCfgMap.put(FeeItemEnum.DK_TQHKWYJ,
                new FeeItemCfg(FeeItemEnum.DK_TQHKWYJ, false, 12, 1, 12));//提前还款违约金
        
        //超额还款
        this.feeItemCfgMap.put(FeeItemEnum.ZX_CEHK,
                new FeeItemCfg(FeeItemEnum.ZX_CEHK, false, 21, 21, 21));//超额还款
        this.feeItemCfgMap.put(FeeItemEnum.DK_CEHK,
                new FeeItemCfg(FeeItemEnum.DK_CEHK, false, 22, 22, 22));//超额还款
        
        //注销后还款
        this.feeItemCfgMap.put(FeeItemEnum.ZX_ZXHHK,
                new FeeItemCfg(FeeItemEnum.ZX_ZXHHK, false, 31, 21, 21));//注销后还款
        this.feeItemCfgMap.put(FeeItemEnum.DK_ZXHHK,
                new FeeItemCfg(FeeItemEnum.DK_ZXHHK, false, 32, 22, 22));//注销后还款
    }
    
    /**
     * @param repayIntention
     * @return
     */
    @Override
    public Collection<FeeItemEnum> getFeeItemsByRepayIntention(
            RepayIntention repayIntention) {
        AssertUtils.notNull(repayIntention, "repayIntention is null.");
        
        Set<FeeItemEnum> resSet = new HashSet<>();
        FeeItemGroupTypeEnum groupType = repayIntention.getGroupType() == null
                ? FeeItemGroupTypeEnum.RECEIVABLE_REPAY_SUM
                : repayIntention.getGroupType();
        switch (groupType) {
            case MONTHLY_REPAY_SUM:
                for (FeeItemCfg feeItemCfgTemp : this.feeItemCfgMap.values()) {
                    if (feeItemCfgTemp.isOverdueDepend()) {
                        resSet.add(feeItemCfgTemp.getFeeItem());
                    }
                }
                break;
            case RECEIVABLE_REPAY_SUM:
            default:
                for (FeeItemCfg feeItemCfgTemp : this.feeItemCfgMap.values()) {
                    resSet.add(feeItemCfgTemp.getFeeItem());
                }
                break;
        }
        return resSet;
    }
    
    /**
     * @param exemptIntention
     * @return
     */
    @Override
    public Collection<FeeItemEnum> getFeeItemsByExemptIntention(
            ExemptIntention exemptIntention) {
        AssertUtils.notNull(exemptIntention, "exemptIntention is null.");
        
        Set<FeeItemEnum> resSet = new HashSet<>();
        FeeItemGroupTypeEnum groupType = exemptIntention.getGroupType() == null
                ? FeeItemGroupTypeEnum.RECEIVABLE_REPAY_SUM
                : exemptIntention.getGroupType();
        switch (groupType) {
            case MONTHLY_REPAY_SUM:
                for (FeeItemCfg feeItemCfgTemp : this.feeItemCfgMap.values()) {
                    if (feeItemCfgTemp.isPrincipal()
                            || !feeItemCfgTemp.isExemptAble()) {
                        continue;
                    }
                    if (feeItemCfgTemp.isOverdueDepend()) {
                        resSet.add(feeItemCfgTemp.getFeeItem());
                    }
                }
                break;
            case RECEIVABLE_REPAY_SUM:
            default:
                for (FeeItemCfg feeItemCfgTemp : this.feeItemCfgMap.values()) {
                    if (feeItemCfgTemp.isPrincipal()
                            || !feeItemCfgTemp.isExemptAble()) {
                        continue;
                    }
                    resSet.add(feeItemCfgTemp.getFeeItem());
                }
                break;
        }
        return resSet;
    }
    
    /**
     * 初始化费用项配置<br/>
     */
    protected void initRepayFeeItemGroups() {
        MultiValueMap<String, FeeItemEnum> fiMMap1 = new LinkedMultiValueMap<>();
        //费用项目根据归属方，逾期依赖进行分组
        for (FeeItemCfg ficTemp : this.feeItemCfgMap.values()) {
            StringBuffer sb = new StringBuffer("_");
            //是否逾期依赖
            sb.append(ficTemp.isOverdueDepend() ? "Y" : "N").append("_");
            fiMMap1.add(sb.toString().toUpperCase(), ficTemp.getFeeItem());
        }
        Map<String, FeeItemGroup> figMap1 = new HashMap<>();
        for (Entry<String, List<FeeItemEnum>> entryTemp : fiMMap1.entrySet()) {
            Set<FeeItemEnum> feeItemSet = new HashSet<>(entryTemp.getValue());
            FeeItemEnum[] feeItems = new FeeItemEnum[feeItemSet.size()];
            feeItems = feeItemSet.toArray(feeItems);
            
            //费用项分组
            figMap1.put(entryTemp.getKey(),
                    new FeeItemGroup(entryTemp.getKey(), feeItemSet, true));
        }
        this.repayFeeItemGroups.addAll(figMap1.values());
        
    }
    
    /**
     * 初始化费用项配置<br/>
     */
    protected void initSettleFeeItemGroups() {
    }
    
    /**
     * 初始化费用项配置<br/>
     */
    protected void initRepayExemptFeeItemGroups() {
        MultiValueMap<String, FeeItemEnum> fiMMap1 = new LinkedMultiValueMap<>();
        //费用项目根据归属方，逾期依赖进行分组
        for (FeeItemCfg ficTemp : this.feeItemCfgMap.values()) {
            if (ficTemp.isPrincipal() || !ficTemp.isExemptAble()) {
                continue;
            }
            StringBuffer sb = new StringBuffer("_");
            //是否逾期依赖
            sb.append(ficTemp.isOverdueDepend() ? "Y" : "N").append("_");
            fiMMap1.add(sb.toString().toUpperCase(), ficTemp.getFeeItem());
        }
        
        Map<String, FeeItemGroup> figMap1 = new HashMap<>();
        for (Entry<String, List<FeeItemEnum>> entryTemp : fiMMap1.entrySet()) {
            Set<FeeItemEnum> feeItemSet = new HashSet<>(entryTemp.getValue());
            FeeItemEnum[] feeItems = new FeeItemEnum[feeItemSet.size()];
            feeItems = feeItemSet.toArray(feeItems);
            
            //费用项分组
            figMap1.put(entryTemp.getKey(),
                    new FeeItemGroup(entryTemp.getKey(), feeItemSet, true));
        }
        this.repayExemptFeeItemGroups.addAll(figMap1.values());
    }
    
    /**
     * 初始化费用项配置<br/>
     */
    protected void initSettleExemptFeeItemGroups() {
        MultiValueMap<String, FeeItemEnum> fiMMap1 = new LinkedMultiValueMap<>();
        //费用项目根据归属方，逾期依赖进行分组
        for (FeeItemCfg ficTemp : this.feeItemCfgMap.values()) {
            if (ficTemp.isPrincipal() || !ficTemp.isExemptAble()) {
                continue;
            }
            //StringBuffer sb = new StringBuffer("_");
            //是否逾期依赖
            //sb.append(ficTemp.isOverdueDepend() ? "Y" : "N").append("_");
            fiMMap1.add("exemptAbleAll", ficTemp.getFeeItem());
        }
        
        Map<String, FeeItemGroup> figMap1 = new HashMap<>();
        for (Entry<String, List<FeeItemEnum>> entryTemp : fiMMap1.entrySet()) {
            Set<FeeItemEnum> feeItemSet = new HashSet<>(entryTemp.getValue());
            FeeItemEnum[] feeItems = new FeeItemEnum[feeItemSet.size()];
            feeItems = feeItemSet.toArray(feeItems);
            
            //费用项分组
            figMap1.put(entryTemp.getKey(),
                    new FeeItemGroup(entryTemp.getKey(), feeItemSet, true));
        }
        this.settleExemptFeeItemGroups.addAll(figMap1.values());
    }
    
    /**
     * @return
     */
    @Override
    public final Map<FeeItemEnum, FeeItemCfg> getFeeItemCfgMap() {
        return this.feeItemCfgMap;
    }
    
    /**
     * @return
     */
    @Override
    public final List<FeeItemGroup> getFeeItemGroupsOfRepay() {
        return this.repayFeeItemGroups;
    }
    
    /**
     * @return
     */
    @Override
    public final List<FeeItemGroup> getFeeItemGroupsOfSettle() {
        return this.settleFeeItemGroups;
    }
    
    /**
     * @return
     */
    @Override
    public Collection<FeeItemGroup> getFeeItemGroupsOfRepayExempt() {
        return this.repayExemptFeeItemGroups;
    }
    
    /**
     * @return
     */
    @Override
    public Collection<FeeItemGroup> getFeeItemGroupsOfSettleExempt() {
        return this.settleExemptFeeItemGroups;
    }
    
    //  //设置费用项配置映射
    //        this.repayFeeItemGroups.add(
    //                new FeeItemGroup("_Y_", Arrays.asList(FeeItemEnum.DK_BJ, FeeItemEnum.DK_LX, FeeItemEnum.ZX_GLF), true));
    //        //归属方
    //        this.repayFeeItemGroups.add(new FeeItemGroup("_N_",
    //                Arrays.asList(FeeItemEnum.DK_TQHKWYJ,
    //                        FeeItemEnum.DK_YQLX,
    //                        FeeItemEnum.ZX_YQLX,
    //                        FeeItemEnum.DK_KQSBSXF,
    //                        FeeItemEnum.ZX_KQSBSXF,
    //                        FeeItemEnum.DK_WBYJ,
    //                        FeeItemEnum.ZX_WBYJ,
    //                        FeeItemEnum.DK_DHZQF,
    //                        FeeItemEnum.ZX_DHZQF),
    //                true));
    //      MultiValueMap<String, FeeItemEnum> fiMMap1 = new LinkedMultiValueMap<>();
    //      //费用项目根据归属方，逾期依赖进行分组
    //      for (FeeItemCfg ficTemp : this.feeItemCfgMap.values()) {
    //          StringBuffer sb = new StringBuffer("_");
    //          //归属方
    //          sb.append(ficTemp.getFeeItem().getOwnership().getKey()).append("_");
    //          //是否逾期依赖
    //          sb.append(ficTemp.isOverdueDepend() ? "Y" : "N").append("_");
    //          
    //          fiMMap1.add(sb.toString().toUpperCase(), ficTemp.getFeeItem());
    //      }
    //      MultiValueMap<String, String> fiMMap2 = new LinkedMultiValueMap<>();
    //      Map<String, FeeItemGroup> figMap1 = new HashMap<>();
    //      for (Entry<String, List<FeeItemEnum>> entryTemp : fiMMap1.entrySet()) {
    //          Set<FeeItemEnum> feeItemSet = new HashSet<>(entryTemp.getValue());
    //          FeeItemEnum[] feeItems = new FeeItemEnum[feeItemSet.size()];
    //          feeItems = feeItemSet.toArray(feeItems);
    //          
    //          FeeOwnershipEnum ownership = feeItemSet.iterator().next().getOwnership();
    //          StringBuffer sb = new StringBuffer("_");
    //          //归属方
    //          sb.append(ownership.getKey()).append("_");
    //          //费用项分组
    //          figMap1.put(entryTemp.getKey(), new FeeItemGroup(entryTemp.getKey(), feeItemSet));
    //          
    //          fiMMap2.add(sb.toString().toUpperCase(), entryTemp.getKey());
    //      }
    //      Map<String, FeeItemGroup> figMap2 = new HashMap<>();
    //      for (Entry<String, List<String>> entryTemp : fiMMap2.entrySet()) {
    //          Set<String> groupCodeSet = new HashSet<>(entryTemp.getValue());
    //          Set<FeeItemGroup> figSet = new HashSet<>();
    //          
    //          for (String groupCodeTemp : groupCodeSet) {
    //              FeeItemGroup figTemp = figMap1.get(groupCodeTemp);
    //              AssertUtils.notNull(figTemp, "figTemp is not exists.groupCode:{}", groupCodeTemp);
    //              
    //              figSet.add(figTemp);
    //          }
    //          figMap2.put(entryTemp.getKey(), new FeeItemGroup(entryTemp.getKey(), new ArrayList<>(figSet)));
    //      }
    //      this.feeItemGroupMap.putAll(figMap2);
}
