/*
 * 描          述:  <描述>
 * 修  改   人:  PengQingyang
 * 修改时间:  2020年5月20日
 * <修改描述:>
 */
package com.tx.local.qxb.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.tx.local.creditinfo.model.CreditSingleLinked;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 企业工商注册信息<br/>
 * 测试环境： http://api.qixin.com/APITestService/v2/enterprise/getDetailByNameOnline 
 * 正式环境： http://api.qixin.com/APIService/v2/enterprise/getDetailByNameOnline 
 * <功能详细描述>
 * 
 * @author  PengQingyang
 * @version  [版本号, 2020年5月20日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "qxb_enterprise")
public class QxbEnterpriseInfo implements CreditSingleLinked {
    
    /** 注释内容 */
    private static final long serialVersionUID = -3761660274012435287L;
    
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
    
    /** 企业名称 */
    @Column(length = 64, updatable = true, nullable = false)
    private String name;
    
    /** 企业类型 */
    @Column(length = 64, updatable = true, nullable = true)
    @JsonAlias("econ_kind")
    private String econKind;
    
    /** 注册资本 */
    @Column(length = 64, updatable = true, nullable = true)
    @JsonAlias("regist_capi")
    private String registCapi;
    
    /** 历史名称（数组） */
    @Column(length = 64, updatable = true, nullable = true)
    @JsonAlias("history_names")
    private String historyNames;
    
    /** 地址 */
    @Column(length = 128, updatable = true, nullable = true)
    @JsonAlias("address")
    private String address;
    
    /** 企业注册号 */
    @Column(length = 64, updatable = true, nullable = true)
    @JsonAlias("reg_no")
    private String regNo;
    
    /** 经营范围 */
    @Column(length = 500, updatable = true, nullable = true)
    @JsonAlias("scope")
    private String scope;
    
    /** 营业开始日期 */
    @Column(updatable = true, nullable = true)
    @JsonAlias("term_start")
    private Date termStart;
    
    /** 营业结束日期 */
    @Column(updatable = true, nullable = true)
    @JsonAlias("term_end")
    private Date termEnd;
    
    /** 所属工商局 */
    @Column(length = 256, updatable = true, nullable = true)
    @JsonAlias("belong_org")
    private String belongOrg;
    
    /** 企业法定代表人 */
    @Column(length = 64, updatable = true, nullable = true)
    @JsonAlias("oper_name")
    private String operName;
    
    /** 成立日期 */
    @Column(updatable = true, nullable = true)
    @JsonAlias("start_date")
    private Date startDate;
    
    /** 注销日期 */
    @Column(updatable = true, nullable = true)
    @JsonAlias("end_date")
    private Date endDate;
    
    /** 核准日期 */
    @Column(updatable = true, nullable = true)
    @JsonAlias("check_date")
    private Date checkDate;
    
    /** 经营状态 */
    @Column(length = 64, updatable = true, nullable = true)
    @JsonAlias("status")
    private String status;
    
    /** 组织机构号 */
    @Column(length = 64, updatable = true, nullable = true)
    @JsonAlias("org_no")
    private String orgNo;
    
    /** 统一社会信用代码 */
    @Column(length = 64, updatable = true, nullable = true)
    @JsonAlias("credit_no")
    private String creditNo;
    
    /** 省份缩写（参见附录A） */
    @Column(length = 64, updatable = true, nullable = true)
    @JsonAlias("province")
    private String province;
    
    /** 城市编码 */
    @Column(length = 64, updatable = true, nullable = true)
    @JsonAlias("city")
    private String city;
    
    /** 行业 */
    @Column(length = 500, updatable = true, nullable = true)
    @JsonAlias("domains")
    private String domains;
    
    /** 是否上市(0:未上市，1:上市) */
    @Column(length = 64, updatable = true, nullable = true)
    @JsonAlias("is_quoted")
    private String isQuoted;
    
    /** 上市类型，数组，1:新三板，6:沪深市，9:香港上市 */
    @Column(length = 64, updatable = true, nullable = true)
    @JsonAlias("quoted_type")
    private String quotedType;
    
    /** 联系方式 */
    @OneToMany
    @JsonAlias("contact")
    private QxbEntContact contact;
    
    /** 企业网址 */
    @OneToMany
    @JsonAlias("websites")
    private List<QxbEntWebsite> websiteList;
    
    /** 主要人员 */
    @OneToMany
    @JsonAlias("employees")
    private List<QxbEntEmployee> employeeList;
    
    /** 分支机构 */
    @OneToMany
    @JsonAlias("branches")
    private List<QxbEntBranche> branchesList;
    
    /** 变更记录 */
    @OneToMany
    @JsonAlias("changerecords")
    private List<QxbEntChangeRecord> changeRecordList;
    
    /** 股东 */
    @OneToMany
    @JsonAlias("partners")
    private List<QxbEntPartner> partnerList;
    
    /** 股东 */
    @OneToMany
    @JsonAlias("abnormal_items")
    private List<QxbEntAbnormalItem> abnormalItemList;
    
}
