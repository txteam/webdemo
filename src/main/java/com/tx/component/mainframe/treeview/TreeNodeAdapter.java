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
public interface TreeNodeAdapter<T> {
    
    public Object getTarget(T obj);
    
    /**
     * @return 返回 id
     */
    public String getId(T obj);

    /**
     * @return 返回 type
     */
    public int getType(T obj);
    
    /**
     * @return 返回 parentId
     */
    public String getParentId(T obj);

    /**
     * @return 返回 name
     */
    public String getName(T obj);
}
