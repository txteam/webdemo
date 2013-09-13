/*
 * 描          述:  <描述>
 * 修  改   人:  brady
 * 修改时间:  2013-9-12
 * <修改描述:>
 */
package com.tx.component.mainframe.treeview;


 /**
  * 树节点实例<br/>
  * <功能详细描述>
  * 
  * @author  brady
  * @version  [版本号, 2013-9-12]
  * @see  [相关类/方法]
  * @since  [产品/模块版本]
  */
public class TreeNode {
    
    /** 树节点id */
    private String id;
    
    /** 树节点类型 */
    private int type;
    
    /** 上级树节点id */
    private String parentId;
    
    /** 树节点名 */
    private String name;
    
    /**
     * <默认构造函数>
     */
    public <T> TreeNode(TreeNodeAdapter<T> adapter,T obj){
        this.id = adapter.getId(obj);
        this.type = adapter.getType(obj);
        this.name = adapter.getName(obj);
        this.parentId = adapter.getParentId(obj);
    }

    /**
     * @return 返回 id
     */
    public String getId() {
        return id;
    }

    /**
     * @param 对id进行赋值
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return 返回 type
     */
    public int getType() {
        return type;
    }

    /**
     * @param 对type进行赋值
     */
    public void setType(int type) {
        this.type = type;
    }

    /**
     * @return 返回 parentId
     */
    public String getParentId() {
        return parentId;
    }

    /**
     * @param 对parentId进行赋值
     */
    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    /**
     * @return 返回 name
     */
    public String getName() {
        return name;
    }

    /**
     * @param 对name进行赋值
     */
    public void setName(String name) {
        this.name = name;
    }
}
