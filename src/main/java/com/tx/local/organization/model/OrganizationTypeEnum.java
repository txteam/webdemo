/*
 * 描          述:  <描述>
 * 修  改   人:  PengQingyang
 * 修改时间:  2013-8-27
 * <修改描述:>
 */
package com.tx.local.organization.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.tx.component.basicdata.model.BasicDataEnum;
import com.tx.component.basicdata.model.BasicDataEnumJsonSerializer;

/**
 * 组织类型<br/>
 * <功能详细描述>
 * 
 * @author  PengQingyang
 * @version  [版本号, 2013-8-27]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@JsonSerialize(using = BasicDataEnumJsonSerializer.class)
public enum OrganizationTypeEnum implements BasicDataEnum {
    
    /** 机关　*/
    PTYY("PTYY", "平台运营"),
    
    /** 机构分组*/
    JG_GROUP("JG_GROUP", "机构分组"),
    
    /** 机关　*/
    JG("JG", "机关"),
    
    /** 企业　*/
    QY("QY", "企业"),
    
    /** 事业单位　*/
    SYDW("SYDW", "事业单位"),
    
    /** 社团组织　*/
    STZZ("STZZ", "社团组织"),
    
    /** JCS　*/
    JCS("JCS", "基层社"),
    
    /** JCSDW　*/
    JCSDW("JCSDW", "基层社单位"),
    
    /** ZYHZS　*/
    ZYHZS("ZYHZS", "专业合作社"),
    
    /** ZHFWS*/
    ZHFWS("ZHFWS", "综合服务社"),
    
    /** 集团公司 */
    GROUP_COMPANY("GROUP_COMPANY", "集团公司"),
    
    /** 公司 */
    COMPANY("COMPANY", "公司"),
    
    /** 分公司 */
    BRANCH_COMPANY("BRANCH_COMPANY", "分公司"),
    
    /** 部门(科室) */
    DEPARTMENT("DEPARTMENT", "部门(科室)"),
    
    /** 分部 */
    BRANCH_DEPARTMENT("BRANCH_DEPARTMENT", "分部"),
    
    /** 分组 */
    GROUP("GROUP", "分组");
    
    /** 编码 */
    private final String code;
    
    /** 名称 */
    private final String name;
    
    /** <默认构造函数> */
    private OrganizationTypeEnum(String code, String name) {
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
