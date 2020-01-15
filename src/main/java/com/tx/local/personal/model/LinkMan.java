/*
 * 描          述:  <描述>
 * 修  改   人:  Bobby
 * 修改时间:  2016年11月11日
 * <修改描述:>
 */
package com.tx.local.personal.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.tx.local.basicdata.model.District;
import com.tx.local.creditinfo.annotation.MultipCreditInfo;
import com.tx.local.creditinfo.context.AbstractCreditInfo;
import com.tx.local.creditinfo.model.LinkManRelation;
import com.tx.local.creditinfo.model.LinkManTypeEnum;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 联系人信息<br/>
 * <功能详细描述>
 * 
 * @author  bobby
 * @version  [版本号, 2017年5月2日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@MultipCreditInfo
@Entity
@Table(name = "ci_link_man")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class LinkMan extends AbstractCreditInfo {
    
    /** 注释内容 */
    private static final long serialVersionUID = -7312915830507027338L;
    
    /** 联系人类型 */
    @Column(name = "unitName", nullable = false, length = 64)
    private LinkManTypeEnum type;
    
    /** 与主贷人关系 */
    @Column(name = "relationId")
    private LinkManRelation relation;
    
    /** 年龄 */
    @Column(name = "age", nullable = true, length = 3)
    private Integer age;
    
    /** 职位 */
    @Column(name = "job", nullable = true, length = 64)
    private String job;
    
    /** 姓名 */
    @Column(name = "name", nullable = true, length = 16)
    private String name;
    
    /** 单位名称 */
    @Column(name = "unitName", nullable = true, length = 64)
    private String unitName;
    
    /**省ID*/
    @Column(name = "provinceId")
    private District province;
    
    /** 市ID */
    @Column(name = "cityId")
    private District city;
    
    /**区/县ID*/
    @Column(name = "countyId")
    private District county;
    
    /**详细地址*/
    private String address;
    
    /**地址全称**/
    private String fullAddress;
    
    /**是否知悉此项贷款**/
    private boolean know;
    
    /** 性别 */
    private boolean gender;
    
    /**关系备注**/
    private String remark;
}
