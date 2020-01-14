//package com.tx.local.personal.model;
//
//import java.util.Date;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.Table;
//
//import com.tx.component.basicdata.model.ClientRelationType;
//import com.tx.core.jdbc.sqlsource.annotation.QueryConditionEqual;
//import com.tx.core.jdbc.sqlsource.annotation.UpdateAble;
//import com.tx.local.basicdata.model.District;
//import com.tx.local.creditinfo.annotation.MultipCreditInfo;
//import com.tx.local.creditinfo.context.AbstractCreditInfo;
//import com.tx.local.creditinfo.dto.CreditInfoOfMultip;
//import com.tx.local.creditinfo.dto.CreditInfoRefTypeEnum;
//import com.tx.local.creditinfo.model.Education;
//import com.tx.local.creditinfo.model.IdCardDeadline;
//import com.tx.local.creditinfo.model.IdentityState;
//import com.tx.local.creditinfo.model.LinkPhoneTypeEnum;
//import com.tx.local.creditinfo.model.LiveStatus;
//import com.tx.local.creditinfo.model.MaritalStatus;
//
//import lombok.Data;
//import lombok.EqualsAndHashCode;
//import lombok.NoArgsConstructor;
//
///**
// * 电话联系信息<br/>
// * <功能详细描述>
// * 
// * @author  Administrator
// * @version  [版本号, 2016年11月15日]
// * @see  [相关类/方法]
// * @since  [产品/模块版本]
// */
//@MultipCreditInfo
//@Entity
//@Table(name = "ci_phone_link")
//@Data
//@EqualsAndHashCode(callSuper = true)
//@NoArgsConstructor
//public class LinkPhone extends AbstractCreditInfo  {
//    
//    /** 注释内容 */
//    private static final long serialVersionUID = 2156000839332341667L;
//    
//    /** 电话类型 */
//    private LinkPhoneTypeEnum type;
//    
//    /** 姓名 */
//    private String name;
//    
//    /** 联系电话关联类型 */
//    private CreditInfoRefTypeEnum refType;
//    
//    /** 联系电话关联id:可能关联客户信息，配偶信息，联系人信息 */
//    private String refId;
//    
//    /** 客户关联类型 */
//    @Column(name = "clientRelationTypeId")
//    private ClientRelationType clientRelationType;
//    
//    /** 区号 */
//    private String areaNumber;
//    
//    /** 电话号码 */
//    private String telephoneNumber;
//    
//    /** 分机号 */
//    private String extensionNumber;
//    
//    /** 是否实名认证 */
//    private Boolean realName;
//    
//    /** 实名认证是否匹配 */
//    private Boolean realNameMatched;
//    
//    /** 是否核查 */
//    private Boolean inspect;
//    
//    /** 最后核查时间 */
//    private Date lastInspectDate;
//    
//    /** 时长:开始使用时间 */
//    private Date startUseDate;
//    
//    /** 备注 */
//    private String remark;
//    
//    public String getPhoneTypeName() {
//        if (null != phoneType) {
//            return phoneType.getName();
//        }
//        return "";
//    }
//    
//    /**
//     * @return 返回 phoneType
//     */
//    public LinkPhoneTypeEnum getPhoneType() {
//        return phoneType;
//    }
//    
//    /**
//     * @param 对phoneType进行赋值
//     */
//    public void setPhoneType(LinkPhoneTypeEnum phoneType) {
//        this.phoneType = phoneType;
//    }
//    
//    /**
//     * @return 返回 name
//     */
//    public String getName() {
//        return name;
//    }
//    
//    /**
//     * @param 对name进行赋值
//     */
//    public void setName(String name) {
//        this.name = name;
//    }
//    
//    /**
//     * @return 返回 refType
//     */
//    public CreditInfoRefTypeEnum getRefType() {
//        return refType;
//    }
//    
//    /**
//     * @param 对refType进行赋值
//     */
//    public void setRefType(CreditInfoRefTypeEnum refType) {
//        this.refType = refType;
//    }
//    
//    /**
//     * @return 返回 refId
//     */
//    public String getRefId() {
//        return refId;
//    }
//    
//    /**
//     * @param 对refId进行赋值
//     */
//    public void setRefId(String refId) {
//        this.refId = refId;
//    }
//    
//    /**
//     * @return 返回 clientRelationType
//     */
//    public ClientRelationType getClientRelationType() {
//        return clientRelationType;
//    }
//    
//    /**
//     * @param 对clientRelationType进行赋值
//     */
//    public void setClientRelationType(ClientRelationType clientRelationType) {
//        this.clientRelationType = clientRelationType;
//    }
//    
//    /**
//     * @return 返回 areaNumber
//     */
//    public String getAreaNumber() {
//        return areaNumber;
//    }
//    
//    /**
//     * @param 对areaNumber进行赋值
//     */
//    public void setAreaNumber(String areaNumber) {
//        this.areaNumber = areaNumber;
//    }
//    
//    /**
//     * @return 返回 telephoneNumber
//     */
//    public String getTelephoneNumber() {
//        return telephoneNumber;
//    }
//    
//    /**
//     * @param 对telephoneNumber进行赋值
//     */
//    public void setTelephoneNumber(String telephoneNumber) {
//        this.telephoneNumber = telephoneNumber;
//    }
//    
//    /**
//     * @return 返回 extensionNumber
//     */
//    public String getExtensionNumber() {
//        return extensionNumber;
//    }
//    
//    /**
//     * @param 对extensionNumber进行赋值
//     */
//    public void setExtensionNumber(String extensionNumber) {
//        this.extensionNumber = extensionNumber;
//    }
//    
//    /**
//     * @return 返回 realName
//     */
//    public Boolean getRealName() {
//        return realName;
//    }
//    
//    /**
//     * @param 对realName进行赋值
//     */
//    public void setRealName(Boolean realName) {
//        this.realName = realName;
//    }
//    
//    /**
//     * @return 返回 realNameMatched
//     */
//    public Boolean getRealNameMatched() {
//        return realNameMatched;
//    }
//    
//    /**
//     * @param 对realNameMatched进行赋值
//     */
//    public void setRealNameMatched(Boolean realNameMatched) {
//        this.realNameMatched = realNameMatched;
//    }
//    
//    /**
//     * @return 返回 inspect
//     */
//    public Boolean getInspect() {
//        return inspect;
//    }
//    
//    /**
//     * @param 对inspect进行赋值
//     */
//    public void setInspect(Boolean inspect) {
//        this.inspect = inspect;
//    }
//    
//    /**
//     * @return 返回 lastInspectDate
//     */
//    public Date getLastInspectDate() {
//        return lastInspectDate;
//    }
//    
//    /**
//     * @param 对lastInspectDate进行赋值
//     */
//    public void setLastInspectDate(Date lastInspectDate) {
//        this.lastInspectDate = lastInspectDate;
//    }
//    
//    /**
//     * @return 返回 startUseDate
//     */
//    public Date getStartUseDate() {
//        return startUseDate;
//    }
//    
//    /**
//     * @param 对startUseDate进行赋值
//     */
//    public void setStartUseDate(Date startUseDate) {
//        this.startUseDate = startUseDate;
//    }
//    
//    /**
//     * @return 返回 remark
//     */
//    public String getRemark() {
//        return remark;
//    }
//    
//    /**
//     * @param 对remark进行赋值
//     */
//    public void setRemark(String remark) {
//        this.remark = remark;
//    }
//}
