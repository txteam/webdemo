/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2017年6月14日
 * <修改描述:>
 */
package com.tx.local.loanaccount.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;

import com.tx.core.exceptions.util.AssertUtils;
import com.tx.local.basicdata.model.FeeItemEnum;

/**
  * 费用项分配分组<br/>
  * <功能详细描述>
  * 
  * @author  Administrator
  * @version  [版本号, 2017年6月14日]
  * @see  [相关类/方法]
  * @since  [产品/模块版本]
  */
public class FeeItemAssignGroup {
    
    /** 分组编码：便于直接给分组进行设值 */
    private final String groupCode;
    
    /** 分配金额 */
    private BigDecimal assignSum;
    
    /** 父级分配分组 */
    private FeeItemAssignGroup parent;
    
    /** 费用项分组 */
    private final List<FeeItemAssignGroup> childs;
    
    /** 分配组映射 */
    private final Map<String, FeeItemAssignGroup> assignGroupMap;
    
    /** 费用项集合 */
    private final Map<FeeItemEnum, Entry<FeeItemEnum, BigDecimal>> feeItemAssignEntryMap;
    
    /** 是否叶节点 */
    private final boolean leaf;
    
    /**
      * 构建费用项分配分组<br/>
      * <功能详细描述>
      * @param scheduleType
      * @param feeItemGroups
      * @param feeItem2amountMap
      * @return [参数说明]
      * 
      * @return List<FeeItemAssignGroup> [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public static List<FeeItemAssignGroup> buildFeeItemAssignGroups(ScheduleTypeEnum scheduleType,
            Collection<FeeItemGroup> feeItemGroups, final Map<String, Map<FeeItemEnum, BigDecimal>> assignMap) {
        AssertUtils.notNull(assignMap, "assignMap is empty.");
        
        Map<FeeItemEnum, BigDecimal> feeItem2amountMap = new HashMap<>();
        for (Map<FeeItemEnum, BigDecimal> mapTemp : assignMap.values()) {
            for (Entry<FeeItemEnum, BigDecimal> entryTemp : mapTemp.entrySet()) {
                if (!feeItem2amountMap.containsKey(entryTemp.getKey())) {
                    feeItem2amountMap.put(entryTemp.getKey(), BigDecimal.ZERO);
                }
                feeItem2amountMap.put(entryTemp.getKey(),
                        feeItem2amountMap.get(entryTemp.getKey()).add(entryTemp.getValue()));
            }
        }
        List<FeeItemAssignGroup> resList = buildFeeItemAssignGroups(scheduleType,
                feeItemGroups,
                feeItem2amountMap,
                null);
        return resList;
    }
    
    /**
     * 根据费用项分组构建费用项分配分组<br/>
     * <功能详细描述>
     * @param assignGroups
     * @param feeItem2amountMap
     * @param group2amountMap
     * @return [参数说明]
     * 
     * @return FeeItemAssignGroup [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public static List<FeeItemAssignGroup> buildFeeItemAssignGroups(ScheduleTypeEnum scheduleType,
            Collection<FeeItemGroup> feeItemGroups, final Map<FeeItemEnum, BigDecimal> feeItem2amountMap,
            final Map<String, BigDecimal> groupCode2amountMap) {
        //构建费用项分配分组
        List<FeeItemAssignGroup> fiagList = doBuildFeeItemAssignGroups(scheduleType, feeItemGroups, feeItem2amountMap);
        //构建顶级AssignGroup检测费用项是否重复
        FeeItemAssignGroup root = new FeeItemAssignGroup("ROOT", fiagList);
        if (!MapUtils.isEmpty(groupCode2amountMap)) {
            for (Entry<String, BigDecimal> entryTemp : groupCode2amountMap.entrySet()) {
                String groupCodeTemp = entryTemp.getKey();
                AssertUtils.isTrue(root.getAssignGroupMap().containsKey(groupCodeTemp),
                        "assignGroupMap should containsKey:{}",
                        new Object[] { groupCodeTemp });
                
                root.getAssignGroupMap().get(groupCodeTemp).setAssignSum(entryTemp.getValue());
            }
        }
        for (FeeItemAssignGroup fiagTemp : root.getChilds()) {
            fiagTemp.setParent(null);
        }
        return fiagList;
    }
    
    /**
     * 构建费用项分组集合<br/>
     * <功能详细描述>
     * @param childFeeItemGroups
     * @param feeItem2amountMap
     * @param groupCode2amountMap
     * @return [参数说明]
     * 
     * @return List<FeeItemAssignGroup> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    private static List<FeeItemAssignGroup> doBuildFeeItemAssignGroups(ScheduleTypeEnum scheduleType,
            Collection<FeeItemGroup> childFeeItemGroups, final Map<FeeItemEnum, BigDecimal> feeItem2amountMap) {
        AssertUtils.notNull(scheduleType, "scheduleType is null.");
        
        List<FeeItemAssignGroup> fiagList = new ArrayList<>();
        if (CollectionUtils.isEmpty(childFeeItemGroups)) {
            return fiagList;
        }
        for (FeeItemGroup figTemp : childFeeItemGroups) {
            boolean isNeedAdjust = figTemp.isNeedAdjust();//!ScheduleTypeEnum.MAIN.equals(scheduleType) && figTemp.isNeedAdjust();
            Collection<FeeItemEnum> feeItems = figTemp.getFeeItemSet();
            FeeItemAssignGroup fiagTemp = null;
            if (!figTemp.isLeaf()) {
                List<FeeItemAssignGroup> childsTemp = doBuildFeeItemAssignGroups(scheduleType,
                        figTemp.getChilds(),
                        feeItem2amountMap);
                if (childsTemp == null) {
                    continue;
                }
                
                fiagTemp = new FeeItemAssignGroup(figTemp.getGroupCode(), childsTemp);
                if (isNeedAdjust) {
                    if (isNeedAdjust) {
                        BigDecimal assignSum = BigDecimal.ZERO;
                        for (Entry<FeeItemEnum, BigDecimal> entryTemp : feeItem2amountMap.entrySet()) {
                            if (!feeItems.contains(entryTemp.getKey())) {
                                continue;
                            }
                            assignSum = assignSum.add(entryTemp.getValue());
                        }
                        fiagTemp.setAssignSum(assignSum);
                    }
                }
                fiagList.add(fiagTemp);
            } else {
                fiagTemp = doBuildFeeItemAssignGroup(figTemp.getGroupCode(),
                        figTemp.getFeeItemSet(),
                        feeItem2amountMap);
                if (isNeedAdjust) {
                    BigDecimal assignSum = BigDecimal.ZERO;
                    for (Entry<FeeItemEnum, BigDecimal> entryTemp : feeItem2amountMap.entrySet()) {
                        if (!feeItems.contains(entryTemp.getKey())) {
                            continue;
                        }
                        assignSum = assignSum.add(entryTemp.getValue());
                    }
                    fiagTemp.setAssignSum(assignSum);
                }
                
                fiagList.add(fiagTemp);
            }
        }
        return fiagList;
    }
    
    /**
     * 构建费用项分配分组<br/>
     * <功能详细描述>
     * @param groupCode
     * @param feeItems
     * @param feeItem2amountMap
     * @return [参数说明]
     * 
     * @return FeeItemAssignGroup [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    private static FeeItemAssignGroup doBuildFeeItemAssignGroup(String groupCode, Collection<FeeItemEnum> feeItems,
            final Map<FeeItemEnum, BigDecimal> feeItem2amountMap) {
        AssertUtils.notEmpty(groupCode, "groupCode is empty.");
        AssertUtils.notEmpty(feeItems, "feeItems is empty.");
        
        FeeItemAssignGroup fiagTemp = new FeeItemAssignGroup(groupCode, feeItems, feeItem2amountMap);
        return fiagTemp;
    }
    
    /** <默认构造函数> */
    public FeeItemAssignGroup(String groupCode, Collection<FeeItemAssignGroup> childs) {
        super();
        AssertUtils.notEmpty(groupCode, "groupCode is empty.");
        AssertUtils.notEmpty(childs, "childs is empty.");
        
        this.groupCode = groupCode;
        this.childs = new ArrayList<>(childs);
        this.assignGroupMap = new HashMap<>();
        this.feeItemAssignEntryMap = new HashMap<>();
        
        for (FeeItemAssignGroup group : this.childs) {
            AssertUtils.isNull(group.getParent(), "group.parent is not null.");
            group.setParent(this);
            AssertUtils.notEmpty(group.getGroupCode(), "group.getGroupCode() is empty.");
            this.assignGroupMap.put(group.getGroupCode(), group);
            AssertUtils.notEmpty(group.getFeeItemAssignEntryMap(), "group.feeItemAssignEntryMap is empty.");
            
            //将所有子类的费用项写入父级<,<FeeItemEnum, BigDecimal>>
            for (Entry<FeeItemEnum, Entry<FeeItemEnum, BigDecimal>> entry : group.getFeeItemAssignEntryMap()
                    .entrySet()) {
                FeeItemEnum feeItem = entry.getKey();
                AssertUtils.isTrue(!this.feeItemAssignEntryMap.containsKey(feeItem),
                        "费用项:{} 存在于两个不同的分组中.",
                        new Object[] { feeItem });
                
                this.feeItemAssignEntryMap.put(entry.getKey(), entry.getValue());
            }
            //将所有子类的费用项写入父级
            for (FeeItemAssignGroup groupTemp : group.getAssignGroupMap().values()) {
                String groupCodeTemp = groupTemp.getGroupCode();
                AssertUtils.isTrue(!this.getAssignGroupMap().containsKey(groupCodeTemp),
                        "分配组:{} 存在于两个不同的分组中.",
                        new Object[] { groupCodeTemp });
                
                this.assignGroupMap.put(groupCodeTemp, groupTemp);
            }
        }
        this.leaf = false;
    }
    
    /** <默认构造函数> */
    public FeeItemAssignGroup(String groupCode, Collection<FeeItemEnum> feeItemCollection,
            Map<FeeItemEnum, BigDecimal> feeItem2amountMap) {
        super();
        AssertUtils.notEmpty(groupCode, "groupCode is empty.");
        AssertUtils.notEmpty(feeItemCollection, "feeItemCollection is empty.");
        AssertUtils.notNull(feeItem2amountMap, "feeItem2amountMap is null.");
        
        this.groupCode = groupCode;
        this.childs = new ArrayList<>();
        this.assignGroupMap = new HashMap<>();
        this.feeItemAssignEntryMap = new HashMap<>();
        
        for (Entry<FeeItemEnum, BigDecimal> entryTemp : feeItem2amountMap.entrySet()) {
            if (feeItemCollection.contains(entryTemp.getKey())) {
                this.feeItemAssignEntryMap.put(entryTemp.getKey(), entryTemp);
            }
        }
        for (FeeItemEnum feeItemTemp : feeItemCollection) {
            if (!this.feeItemAssignEntryMap.containsKey(feeItemTemp)) {
                this.feeItemAssignEntryMap.put(feeItemTemp, null);
            }
        }
        this.leaf = true;
    }
    
    /**
     * @return 返回 leaf
     */
    public boolean isLeaf() {
        return leaf;
    }
    
    /**
      * 是否需要进行分配调整<br/>
      * <功能详细描述>
      * @return [参数说明]
      * 
      * @return boolean [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public boolean isNeedAdjust() {
        if (this.assignSum == null) {
            return false;
        } else {
            return true;
        }
    }
    
    /**
     * @return 返回 assignSum
     */
    public BigDecimal getAssignSum() {
        BigDecimal minAssignSum = BigDecimal.ZERO;
        for (Entry<FeeItemEnum, BigDecimal> assignEntry : this.feeItemAssignEntryMap.values()) {
            if (assignEntry == null || assignEntry.getValue() == null) {
                continue;
            }
            minAssignSum = minAssignSum.add(assignEntry.getValue());
        }
        return this.assignSum == null || this.assignSum.compareTo(minAssignSum) <= 0 ? minAssignSum : this.assignSum;
    }
    
    /**
      * 获取费用项对金额的映射<br/>
      * <功能详细描述>
      * @return [参数说明]
      * 
      * @return Map<FeeItemEnum,BigDecimal> [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public Map<FeeItemEnum, BigDecimal> getFeeItem2AmountMap() {
        Map<FeeItemEnum, BigDecimal> feeItem2amountMap = new HashMap<>();
        
        for (Entry<FeeItemEnum, BigDecimal> entryTemp : this.feeItemAssignEntryMap.values()) {
            if (entryTemp == null || entryTemp.getValue() == null) {
                continue;
            }
            if (!feeItem2amountMap.containsKey(entryTemp.getKey())) {
                feeItem2amountMap.put(entryTemp.getKey(), BigDecimal.ZERO);
            }
            feeItem2amountMap.put(entryTemp.getKey(),
                    feeItem2amountMap.get(entryTemp.getKey()).add(entryTemp.getValue()));
        }
        return feeItem2amountMap;
    }
    
    /**
      * 获取分配分组的费用项集合<br/>
      * <功能详细描述>
      * @return [参数说明]
      * 
      * @return Set<FeeItemEnum> [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public Set<FeeItemEnum> getFeeItemSet() {
        Set<FeeItemEnum> feeItemSet = new HashSet<>(this.feeItemAssignEntryMap.keySet());
        
        return feeItemSet;
    }
    
    /**
     * @param 对assignSum进行赋值
     */
    public void setAssignSum(BigDecimal assignSum) {
        this.assignSum = assignSum;
    }
    
    /**
     * @return 返回 parent
     */
    public FeeItemAssignGroup getParent() {
        return parent;
    }
    
    /**
     * @param 对parent进行赋值
     */
    public void setParent(FeeItemAssignGroup parent) {
        this.parent = parent;
    }
    
    /**
     * @return 返回 childs
     */
    public List<FeeItemAssignGroup> getChilds() {
        return childs;
    }
    
    /**
     * @return 返回 assignGroupMap
     */
    public Map<String, FeeItemAssignGroup> getAssignGroupMap() {
        return assignGroupMap;
    }
    
    /**
     * @return 返回 feeItemAssignMap
     */
    public Map<FeeItemEnum, Entry<FeeItemEnum, BigDecimal>> getFeeItemAssignEntryMap() {
        return feeItemAssignEntryMap;
    }
    
    /**
     * @return 返回 groupCode
     */
    public String getGroupCode() {
        return groupCode;
    }
}
