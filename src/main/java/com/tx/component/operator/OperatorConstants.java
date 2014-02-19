/*
 * 描          述:  <描述>
 * 修  改   人:  brady
 * 修改时间:  2013-9-12
 * <修改描述:>
 */
package com.tx.component.operator;

/**
 * 操作员常量<br/>
 * <功能详细描述>
 * 
 * @author  brady
 * @version  [版本号, 2013-9-12]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface OperatorConstants {
    
    /** 组织职位树 节点类型：组织 */
    int TREENODE_TYPE_ORGANIZATION = 0;
    
    /** 组织职位树 节点类型：职位 */
    int TREENODE_TYPE_POST = 1;
    
    /** 人员有效  */
    int OPERATOR_VALID = 1;
    
    /** 人员无效  */
    int OPERATOR_INVALID = 0;
    
    /** 人员锁定 */
    int OPERATOR_LOCKED_TRUE = 1;
    
    /** 人员未锁定 */
    int OPERATOR_LOCKED_FALSE = 0;
    
    /** 权限项：查询所有组织 */
    String AUTHKEY_QUERY_ALL_VC = "query_all_vc";
    
    /** 权限项：查询所有组织 */
    String AUTHKEY_QUERY_ALL_ORG = "query_all_org";
    
    /** 人员引用类型：职位 */
    String OPERATORREF_TYPE_POST = "post";
    
    /** 人员引用类型: 组织 */
    String OPERATORREF_TYPE_ORGANIZATIOIN = "organization";
}
