/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2014年2月22日
 * <修改描述:>
 */
package com.tx.component.mainframe.treeview;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.tx.core.exceptions.util.AssertUtils;

/**
 * 树节点工具类
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2014年2月22日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public abstract class TreeNodeUtils {
    
    /**
      * 将原列表转换为树节点列表<br/>
      *<功能详细描述>
      * @param sourceList
      * @param adapter
      * @return [参数说明]
      * 
      * @return List<TreeNode> [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public static <T> List<TreeNode> transformedList(List<T> sourceList,
            final TreeNodeAdapter<T> adapter) {
        AssertUtils.notNull(adapter, "adapter is null");
        if (CollectionUtils.isEmpty(sourceList)) {
            return new ArrayList<TreeNode>();
        }
        List<TreeNode> resList = new ArrayList<TreeNode>();
        for(T obj : sourceList){
            resList.add(new TreeNode(adapter, obj));
        }
        return resList;
    }
    
    /**
     * 将原列表转换为树节点列表<br/>
     *<功能详细描述>
     * @param sourceList
     * @param adapter
     * @return [参数说明]
     * 
     * @return List<TreeNode> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
    public static <T> Collection<TreeNode> transformedCollection(
            Collection<T> sourceCollection, final TreeNodeAdapter<T> adapter) {
        AssertUtils.notNull(adapter, "adapter is null");
        if (CollectionUtils.isEmpty(sourceCollection)) {
            return new ArrayList<TreeNode>();
        }
        Collection<TreeNode> resList = new ArrayList<TreeNode>();
        for(T obj : sourceCollection){
            resList.add(new TreeNode(adapter, obj));
        }
        return resList;
    }
}
