/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2017年12月15日
 * <修改描述:>
 */
package com.tx.local.taxsettle.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.tx.component.basicdata.model.BasicDataEnum;
import com.tx.component.basicdata.model.BasicDataEnumJsonSerializer;

/**
  * 结息计划类型<br/>
  * <功能详细描述>
  * 
  * @author  Administrator
  * @version  [版本号, 2017年12月15日]
  * @see  [相关类/方法]
  * @since  [产品/模块版本]
  */
@JsonSerialize(using = BasicDataEnumJsonSerializer.class)
public enum LATaxSettleScheduleTypeEnum implements BasicDataEnum{
    
    /** 前计划 */
    BEFORE("BEFORE","前计划"),
    
    /** 后计划 */
    AFTER("AFTER","后计划");
    
    /** 编码 */
    private final String code;
    
    /** 名称 */
    private final String name;

    /** <默认构造函数> */
    private LATaxSettleScheduleTypeEnum(String code, String name) {
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
