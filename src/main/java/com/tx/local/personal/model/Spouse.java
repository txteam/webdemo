///*
// * 描          述:  <描述>
// * 修  改   人:  Administrator
// * 修改时间:  2016年11月10日
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
//import com.tx.component.basicdata.model.IDCardTypeEnum;
//import com.tx.component.basicdata.model.Industry;
//import com.tx.component.basicdata.model.Position;
//import com.tx.core.jdbc.sqlsource.annotation.UpdateAble;
//import com.tx.local.basicdata.model.BankInfo;
//import com.tx.local.basicdata.model.District;
//import com.tx.local.creditinfo.annotation.SingleCreditInfo;
//import com.tx.local.creditinfo.context.AbstractCreditInfo;
//import com.tx.local.creditinfo.dto.CreditInfoOfSingle;
//import com.tx.local.creditinfo.model.UnitNature;
//
//import lombok.Data;
//import lombok.EqualsAndHashCode;
//import lombok.NoArgsConstructor;
//
///**
// * <配偶资料>
// * <功能详细描述>
// * 
// * @author  bobby
// * @version  [版本号, 2017年5月5日]
// * @see  [相关类/方法]
// * @since  [产品/模块版本]
// */
//@SingleCreditInfo
//@Entity
//@Table(name = "ci_spouse")
//@Data
//@EqualsAndHashCode(callSuper = true)
//@NoArgsConstructor
//public class Spouse extends AbstractCreditInfo {
//    
//    /** 注释内容 */
//    private static final long serialVersionUID = -2454871279373509176L;
//    
//    /** 证件类型 */
//    private IDCardTypeEnum idCardType = IDCardTypeEnum.身份证;
//    
//    /** 身份证号码 */
//    private String idCardNumber;
//    
//    /** 姓 */
//    private String firstName;
//    
//    /** 名 */
//    private String lastName;
//    
//    /** 姓名 */
//    private String name;
//    
//    /** 年龄 */
//    private Integer age;
//    
//    /** 出生日期 */
//    @DateTimeFormat(pattern = "yyyy-MM-dd")
//    private Date birthday;
//    
//    /** 是否可进行促销推广 */
//    private boolean promotion;
//    
//    /** 家庭是否知悉此项贷款:未来是否知悉贷款可能需要独立的对象进行持有  */
//    private boolean know;
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
//    @UpdateAble
//    @Column(name = "fourPositionId")
//    private Position fourPosition;
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
//    /**单位名称*/
//    private String unitName;
//    
//    /**职位备注*/
//    private String positionNote;
//    
//    /**服务年限*/
//    @DateTimeFormat(pattern = "yyyy-MM-dd")
//    private Date serviceDate;
//}
