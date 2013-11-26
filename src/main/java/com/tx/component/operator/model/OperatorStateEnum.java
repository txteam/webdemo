/*
 * 描          述:  <描述>
 * 修  改   人:  PengQingyang
 * 修改时间:  2013-11-26
 * <修改描述:>
 */
package com.tx.component.operator.model;

/**
 * 操作园状态枚举型
 * <功能详细描述>
 * 
 * @author  PengQingyang
 * @version  [版本号, 2013-11-26]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public enum OperatorStateEnum {
    /**
     * valid = true
     * locked = false
     */
    正常, 
    /**
     * valid = true
     * locked = true;
     */
    锁定, 
    /**
     * valid = false
     */
    停用;
}
