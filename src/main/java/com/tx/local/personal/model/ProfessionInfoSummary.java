///*
// * 描          述:  <描述>
// * 修  改   人:  Bobby
// * 修改时间:  2016年11月11日
// * <修改描述:>
// */
//package com.tx.local.personal.model;
//
//import java.math.BigDecimal;
//import java.util.Date;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.Table;
//
//import org.springframework.format.annotation.DateTimeFormat;
//
//import com.tx.local.basicdata.model.BankInfo;
//import com.tx.local.basicdata.model.District;
//import com.tx.local.basicdata.model.Industry;
//import com.tx.local.creditinfo.annotation.SingleCreditInfo;
//import com.tx.local.creditinfo.context.AbstractCreditInfo;
//import com.tx.local.creditinfo.model.UnitNature;
//
//import lombok.Data;
//import lombok.EqualsAndHashCode;
//import lombok.NoArgsConstructor;
//
///**
//  * 
//  * <职业信息概要>
//  * <功能详细描述>
//  * 
//  * @author  bobby
//  * @version  [版本号, 2017年6月1日]
//  * @see  [相关类/方法]
//  * @since  [产品/模块版本]
// */
//@SingleCreditInfo
//@Entity
//@Table(name = "ci_profession_info")
//@Data
//@EqualsAndHashCode(callSuper = true)
//@NoArgsConstructor
//public class ProfessionInfoSummary extends AbstractCreditInfo {
//    
//    /** 注释内容 */
//    private static final long serialVersionUID = 3705268114508643645L;
//    
//    /**公司名称*/
//    private String companyName;
//    
//    /**服务年限*/
//    @DateTimeFormat(pattern = "yyyy-MM-dd")
//    private Date serviceDate;
//    
//    /**职位备注*/
//    private String positionNote;
//    
//    /**基本月薪*/
//    private BigDecimal basicSalary = new BigDecimal(0);
//    
//    /**基本月薪类型*/
//    private String salaryType;
//    
//    /**补贴*/
//    private BigDecimal subsidy = new BigDecimal(0);
//    
//    /**补贴类型*/
//    private String subsidyType;
//    
//    /**其他收入*/
//    private BigDecimal otherIncome = new BigDecimal(0);
//    
//    /**其他收入类型   */
//    private String otherIncomeType;
//    
//    /**加班费*/
//    private BigDecimal overtimePay = new BigDecimal(0);
//    
//    /**加班费类型*/
//    private String overtimePayType;
//    
//    /**总收入*/
//    private BigDecimal totalIncome = new BigDecimal(0);
//    
//    /**一级行业*/
//    @Column(name = "oneIndustryId")
//    private Industry oneIndustry;
//    
//    /**二级行业*/
//    @Column(name = "twoIndustryId")
//    private Industry twoIndustry;
//    
//    /**三级行业**/
//    @Column(name = "threeIndustryId")
//    private Industry threeIndustry;
//    
//    /**四级行业**/
//    @Column(name = "fourIndustryId")
//    private Industry fourIndustry;
//    
//    /**一级职位**/
//    @Column(name = "onePositionId")
//    private Position onePosition;
//    
//    /**二级职位**/
//    @Column(name = "twoPositionId")
//    private Position twoPosition;
//    
//    /**三级职位**/
//    @Column(name = "threePositionId")
//    private Position threePosition;
//    
//    /**四级职位**/
//    @Column(name = "fourPositionId")
//    private Position fourPosition;
//    
//    /** 部门 */
//    private String departmentName;
//    
//    /** 社保编号 */
//    private String sbNumber;
//    
//    /** 社保基数 */
//    private BigDecimal sbRadix;
//    
//    /** 每月社保支出 */
//    private BigDecimal monthlySb;
//    
//    /** 每月税金支出 */
//    private BigDecimal monthlySJ;
//    
//    /** 住房公积金 */
//    private BigDecimal houseFund;
//    
//    /** 单位性质 */
//    @Column(name = "unitNatureId")
//    private UnitNature unitNature;
//    
//    /**职务id**/
//    @Column(name = "dutyId")
//    private Duty duty;
//    
//    /**支薪银行*/
//    @Column(name = "bankId")
//    private BankInfo bank;
//    
//    /** 专业年限 */
//    @DateTimeFormat(pattern = "yyyy-MM-dd")
//    private Date professionDate;
//    
//    /** 公积金区域id */
//    @Column(name = "gjjCountryId")
//    private District gjjCountry;
//    
//    /**每月支薪日期*/
//    private Integer payday;
//    
//    /**职称**/
//    @Column(name = "postTitleId")
//    private PostTitle postTitle;
//}
