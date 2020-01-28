/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2017年6月14日
 * <修改描述:>
 */
package com.tx.local.loanaccount.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.tx.core.exceptions.util.AssertUtils;
import com.tx.local.basicdata.model.FeeItemEnum;

/**
  * 费用项还款分组<br/>
  * <功能详细描述>
  * 
  * @author  Administrator
  * @version  [版本号, 2017年6月14日]
  * @see  [相关类/方法]
  * @since  [产品/模块版本]
  */
public class FeeItemGroup {
    
    /** 分组编码 */
    private final String groupCode;
    
    /** 费用项集合 */
    private final Set<FeeItemEnum> feeItemSet;
    
    /** 费用项分组 */
    private final List<FeeItemGroup> childs;
    
    /** 是否叶节点 */
    private final boolean leaf;
    
    /** 需要在该费用分组内进行调整: mainAssign生成分配分组时将不考虑该数据，在根据主分配计划进行生成分配分组时才会读取该属性 */
    private final boolean needAdjust;
    
    /** <默认构造函数> */
    public FeeItemGroup(String groupCode, List<FeeItemGroup> childs) {
        this(groupCode, childs, false);
    }
    
    /** <默认构造函数> */
    public FeeItemGroup(String groupCode, Collection<FeeItemEnum> feeItems) {
        this(groupCode, feeItems, false);
    }
    
    /** <默认构造函数> */
    public FeeItemGroup(String groupCode, List<FeeItemGroup> childs, boolean needAdjust) {
        super();
        this.groupCode = groupCode;
        this.childs = childs;
        this.feeItemSet = new HashSet<>();
        
        for (FeeItemGroup group : childs) {
            for (FeeItemEnum feeItem : group.getFeeItemSet()) {
                AssertUtils.isTrue(!feeItemSet.contains(feeItem), "费用项:{} 存在于两个不同的分组中.", new Object[] { feeItem });
                
                feeItemSet.add(feeItem);
            }
        }
        this.leaf = false;
        this.needAdjust = needAdjust;
    }
    
    /** <默认构造函数> */
    public FeeItemGroup(String groupCode, Collection<FeeItemEnum> feeItems, boolean needAdjust) {
        super();
        this.groupCode = groupCode;
        this.childs = new ArrayList<>();
        this.feeItemSet = new HashSet<>(feeItems);
        this.leaf = true;
        this.needAdjust = needAdjust;
    }
    
    /**
     * @return 返回 leaf
     */
    public boolean isLeaf() {
        return leaf;
    }
    
    /**
     * @return 返回 groupCode
     */
    public String getGroupCode() {
        return groupCode;
    }
    
    /**
     * @return 返回 groups
     */
    public List<FeeItemGroup> getChilds() {
        return childs;
    }
    
    /**
     * @return 返回 feeItemSet
     */
    public Set<FeeItemEnum> getFeeItemSet() {
        return feeItemSet;
    }
    
    /**
     * @return 返回 needAdjust
     */
    public boolean isNeedAdjust() {
        return needAdjust;
    }
}
