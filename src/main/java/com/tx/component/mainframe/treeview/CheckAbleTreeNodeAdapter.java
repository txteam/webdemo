/*
 * 描          述:  <描述>
 * 修  改   人:  brady
 * 修改时间:  2013-9-12
 * <修改描述:>
 */
package com.tx.component.mainframe.treeview;

/**
 * 树节点转换适配器
 * <功能详细描述>
 * 
 * @author  brady
 * @version  [版本号, 2013-9-12]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface CheckAbleTreeNodeAdapter<T> extends TreeNodeAdapter<T> {
    
    /**
      *节点是否选中
      *<功能详细描述>
      * @param obj
      * @return [参数说明]
      * 
      * @return boolean [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public boolean isChecked(T obj);
}
