/*
 * 描          述:  <描述>
 * 修  改   人:  brady
 * 修改时间:  2013-9-12
 * <修改描述:>
 */
package com.tx.component.mainframe.treeview;

/**
 * 可选择的树节点模型，用以支持显示时支持哪些实体是选中的<br/>
 *     用以支持
 *     选择人员
 *     选择职位
 *     选择权限
 *     选择组织等<br/>
 * <功能详细描述>
 * 
 * @author  brady
 * @version  [版本号, 2013-9-12]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class CheckAbleTreeNode extends TreeNode {
    
    /** 是否选中 */
    private boolean checked;
    
    /** <默认构造函数> */
    public <T> CheckAbleTreeNode(CheckAbleTreeNodeAdapter<T> adapter, T obj) {
        super(adapter, obj);
        this.checked = adapter.isChecked(obj);
    }
    
    /** <默认构造函数> */
    public <T> CheckAbleTreeNode(TreeNodeAdapter<T> adapter, T obj,
            boolean checked) {
        super(adapter, obj);
        this.checked = checked;
        //setName(getName() + this.checked);
    }
    
    /**
     * @return 返回 checked
     */
    public boolean isChecked() {
        return checked;
    }
    
    /**
     * @param 对checked进行赋值
     */
    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
