/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2014年5月20日
 * <修改描述:>
 */
package com.tx.local.basicdata.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.tx.component.basicdata.model.BasicDataEnum;
import com.tx.component.basicdata.model.BasicDataEnumJsonSerializer;

/**
 * 身份证件类型大类<br/>
 * <功能详细描述>
 * 
 * @author Administrator
 * @version [版本号, 2014年5月20日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@JsonSerialize(using = BasicDataEnumJsonSerializer.class)
public enum IDCardTypeEnum implements BasicDataEnum{
    
    SFZ("SFZ", "身份证"),
    
    XGSFZ("XGSFZ", "香港身份证"),
    
    LSSFZ("LSSFZ", "临时身份证"),
    
    HZ("HZ", "护照"),
    
    HKB("HKB", "户口簿"),
    
    SBZ("SBZ", "士兵证"),
    
    JINGGZ("JGZ", "警官证"),
    
    WZGBZ("WZGBZ", "文职干部证"),
    
    WZRYZ("WZRYZ", "文职人员证"),
    
    YWBZ("YWBZ", "义务兵证"),
    
    JGZ("JUNGZ", "军官证"),
    
    SGZ("SGZ", "士官证"),
    
    //    港澳台居民往来内地通行证,
    //    
    //    外交人员身份证,
    //    
    //    外国人居留许可证,
    //    
    //    边民出入境通行证,
    //    
    //    对私其它,
    //        
    //    驻华机构登记证,
    
    TYSHXYDM("TYSHXYDM", "统一社会信用代码");
    
    private final String code;
    
    private final String name;
    
    /** <默认构造函数> */
    private IDCardTypeEnum(String code, String name) {
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
