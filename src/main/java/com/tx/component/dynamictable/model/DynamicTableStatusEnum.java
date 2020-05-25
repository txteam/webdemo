/*
 * 描          述:  <描述>
 * 修  改   人:  brady
 * 修改时间:  2013-10-12
 * <修改描述:>
 */
package com.tx.component.dynamictable.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.tx.component.basicdata.model.BasicDataEnum;
import com.tx.component.basicdata.model.BasicDataEnumJsonSerializer;

/**
 * 模板表状态枚举型
 *     待创建：数据库中实际未创建表.
 *     运营态: 数据库实例与动态表定义中一致.
 *     待升级态：数据库实例需要升级后才能一致.
 * @author  brady
 * @version  [版本号, 2013-10-12]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@JsonSerialize(using = BasicDataEnumJsonSerializer.class)
public enum DynamicTableStatusEnum implements BasicDataEnum {
    
    /** 待创建态 */
    WAIT_CREATE("WAIT_CREATE", "待创建态"),
    
    /** 运营态 */
    OPERATION("OPERATION", "运营态"),
    
    /** 待升级态：运营态的表：一旦发生更新，则进入待升级态 */
    SETTING("SETTING", "配置态"),
    
    /** 停止态：表升级后备份表 */
    STOP("STOP", "停止态");
    
    /** 编码 */
    private final String code;
    
    /** 名称 */
    private final String name;
    
    /** <默认构造函数> */
    private DynamicTableStatusEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }
    
    /**
     * @return 返回 code
     */
    public String getCode() {
        return code;
    }
    
    /**
     * @return 返回 name
     */
    public String getName() {
        return name;
    }
}
