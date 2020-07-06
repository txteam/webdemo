/*
 * 描          述:  <描述>
 * 修  改   人:  PengQingyang
 * 修改时间:  2020年6月14日
 * <修改描述:>
 */
package com.tx.local.qxb.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonRawValue;
import com.tx.local.creditinfo.model.CreditMultipLinked;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 企业股东信息<br/>
 * <功能详细描述>
 * 
 * @author  PengQingyang
 * @version  [版本号, 2020年6月14日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "qxb_ent_partner")
public class QxbEntPartner implements CreditMultipLinked {
    
    /** 注释内容 */
    private static final long serialVersionUID = 2121344400678348199L;
    
    /** 主键 */
    @Id
    @Column(length = 64, updatable = false, nullable = false)
    private String id;
    
    /** 客户id */
    @Column(length = 64, updatable = false, nullable = false)
    private String clientId;
    
    /** 信用信息id */
    @Column(length = 64, updatable = false, nullable = false)
    private String creditInfoId;
    
    /** 最后更新时间 */
    @Column(nullable = false, updatable = true)
    private Date lastUpdateDate;
    
    /** 创建时间 */
    @Column(nullable = false, updatable = false)
    private Date createDate;
    
    /** 股东姓名 */
    @Column(length = 64, updatable = true, nullable = true)
    @JsonAlias("name")
    private String name;
    
    /** 股东类型 */
    @Column(length = 64, updatable = true, nullable = true)
    @JsonAlias("stock_type")
    private String stockType;
    
    /** 证照/证件类型 */
    @Column(length = 64, updatable = true, nullable = true)
    @JsonAlias("identify_type")
    private String identifyType;
    
    /** 证照/证件号码 */
    @Column(length = 64, updatable = true, nullable = true)
    @JsonAlias("identify_no")
    private String identifyNo;
    
    /** 股东总实缴 */
    @Column(length = 64, updatable = true, nullable = true)
    @JsonAlias("total_real_capi")
    private String totalRealCapi;
    
    /** 股东总认缴 */
    @Column(length = 64, updatable = true, nullable = true)
    @JsonAlias("total_should_capi")
    private String totalShouldCapi;
    
    /** 认缴出资额  */
    @Column(length = 500, updatable = true, nullable = true)
    @JsonAlias("should_capi_items")
    @JsonRawValue
    private List<QxbEntParShouldCapiItem> shouldCapiItems;
    
    //    partners.should_capi_items.shoud_capi
    //    认缴出资额 
    //    partners.should_capi_items.invest_type
    //    出资方式
    //    partners.should_capi_items.should_capi_date
    //    出资时间
    
    /** 实缴出资额  */
    @Column(length = 500, updatable = true, nullable = true)
    @JsonAlias("real_capi_items")
    @JsonRawValue
    private List<QxbEntParRealCapiItem> realCapiItems;
    
    //    partners.real_capi_items.real_capi
    //    实缴出资额
    //    partners.real_capi_items.invest_type
    //    出资方式
    //    partners.real_capi_items.real_capi_date
    //    实缴时间
}
