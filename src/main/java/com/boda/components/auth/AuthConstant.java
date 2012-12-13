/*
 * 描          述:  <描述>
 * 修  改   人:  brady
 * 修改时间:  2012-11-30
 * <修改描述:>
 */
package com.boda.components.auth;


 /**
  * <权限常量>
  * <功能详细描述>
  * 
  * @author  brady
  * @version  [版本号, 2012-11-30]
  * @see  [相关类/方法]
  * @since  [产品/模块版本]
  */
public interface AuthConstant {
    
    /** 权限类型：抽象权限根，抽象权限不配置权限映射  */
    public final static String ABSTRACT_AUTH_END = "_ABS";
    
    /** 权限项名 */
    public final static String AUTH_ELEMENT_NAME = "Auth";
    
    /** 权限节点id */
    public final static String AUTH_ELEMENT_ATTR_ID = "key";
    
    /** 权限节点name */
    public final static String AUTH_ELEMENT_ATTR_NAME = "name";
    
    /** 权限节点authType */
    public final static String AUTH_ELEMENT_ATTR_AUTHTYPE = "authType";
    
    /** 权限节点description */
    public final static String AUTH_ELEMENT_ATTR_DESCRIPTION = "description";
    
    /** 权限类型：根权限名 */
    public final static String AUTH_ABS_NAME = "权限";
    
    /** 权限类型：根权限 */
    public final static String AUTH_ABS = "AUTH_ABS";
    
    /** 权限类型：操作权限父节类型，抽象权限，不是真是的权限  */
    public final static String TYPE_ABS_OPERATE = "TYPE_OPERATE_ABS";
    
    /** 权限类型：操作权限 */
    public final static String TYPE_OPERATE = "TYPE_OPERATE";
    
    /** 权限类型：数据权限 */
    public final static String TYPE_ABS_DATA = "TYPE_DATA_ABS";
    
    /** 权限类型：数据列权限父节类型，抽象权限，不是真是的权限 */
    public final static String TYPE_ABS_DATA_COLUMN = "TYPE_DATA_COLUMN_ABS";
    
    /** 权限类型：数据列权限 */
    public final static String TYPE_DATA_COLUMN = "TYPE_DATA_COLUMN";
    
    /** 权限类型: 数据行权限父节类型，抽象权限，不是真是的权限 */
    public final static String TYPE_ABS_DATA_ROW = "TYPE_DATA_ROW_ABS";
    
    /** 权限类型: 数据行权限 */
    public final static String TYPE_DATA_ROW = "TYPE_DATA_ROW";
    
    /** 操作员权限引用项：操作员权限引用 */
    public static final String AUTHREFTYPE_OPERATOR = "AUTHREFTYPE_OPERATOR";
    
    /** 操作员权限引用项：操作员临时权限引用 */
    public static final  String AUTHREFTYPE_OPERATOR_TEMP = "AUTHREFTYPE_OPERATOR_TEMP"; 
    
    /** 操作员权限引用项：角色权限引用 */
    public static final  String AUTHREFTYPE_ROLE = "AUTHREFTYPE_ROLE";
    
}
