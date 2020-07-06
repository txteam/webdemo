package com.tx.local.clientinfo.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.tx.local.basicdata.model.IDCardTypeEnum;

/**
 * 基础客户信息<br/>
 * CreditInfoLinkAble,
 * 
 * @author bobby
 * @version [版本号, 2015年12月20日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Entity
@Table(name = "cl_client_info")
public class ClientInfo implements Client, Serializable {
    
    /** 注释内容 */
    private static final long serialVersionUID = 4965265546304077871L;
    
    /** 唯一键 */
    @Id
    @Column(nullable = false, updatable = false, length = 64)
    private String id;
    
    /** 虚中心 */
    @Column(length = 64, updatable = false, nullable = false)
    private String vcid;
    
    /** 序列号: 席位号|客户编号|等(客户有序编号)... */
    private String code;
    
    /** 是否完成信用信息绑定 */
    @Column(nullable = false, updatable = true)
    private boolean creditInfoBinding;
    
    /** 之所以要将设计过程中的机构信用信息与机构信息进行隔离是因为，可能先存在机构信息，再存在机构信用信息，机构信用信息是在机构完成实名认证后绑定的信息，如果需要 */
    @Column(length = 64, updatable = true, nullable = true)
    private String creditInfoId;
    
    /** 账户名 */
    private String username;
    
    /** 客户类型 */
    private ClientTypeEnum type;
    
    /** 用户名 */
    private int usernameChangeCount = 0;
    
    /** 用户名是否能够变更 */
    private boolean usernameChangeAble = true;
    
    /** 客户的名称 */
    private String name;
    
    /*** 状态：激活|禁用：禁用的帐号不允许前端系统登录 */
    private ClientStatusEnum status = ClientStatusEnum.WAIT_ACTIVATE;
    
    /** 客户来源 */
    @Column(name = "clientSourceId", nullable = true, length = 64)
    private ClientSource clientSource;
    
    /** 客户推广渠道 */
    @Column(name = "promotionChannelId", nullable = true, length = 64)
    private ClientPromotionChannel promotionChannel;
    
    /** 电话 */
    private String mobileNumber;
    
    /** 是否绑定电话号码 */
    private boolean mobileBinding = false;
    
    /** 是否可以手机登陆：特指手机接收短信验证码实现登陆 */
    private boolean mobileLoginEnable = false;
    
    /** 电子邮件 */
    private String email;
    
    /** 是否绑定email */
    private boolean emailBinding = false;
    
    /** 证件类型 */
    @Column(length = 64, updatable = true, nullable = true)
    private IDCardTypeEnum idCardType;
    
    /** 身份证号码 */
    @Column(length = 64, updatable = true, nullable = true)
    private String idCardNumber;
    
    /** 身份证到期日 */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date idCardExpiryDate;
    
    /** 是否实名认证 */
    private boolean realNameAuthenticated = false;
    
    /** 实名认证错误次数:由于实名认证存在成本，所以增加该字段以便控制在一段时间内控制认证次数 */
    private int realNameErrCount;
    
    /** 实名认证错误时间 */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date realNameLastErrDate;
    
    /** 密码 */
    private String password;
    
    /** 历史密码 */
    private String hisPwd;
    
    /**用户最近一次修改密码时间*/
    private Date pwdUpdateDate;
    
    /** 用户输错密码的次数 */
    private Integer pwdErrCount = 0;
    
    /** 密码输入错误时间 */
    private Date pwdLastErrDate;
    
    /** 支付密码 */
    private String payPassword;
    
    /** 历史密码 */
    private String hisPayPwd;
    
    /** 用户最近一次修改密码时间 */
    private Date payPwdUpdateDate;
    
    /** 用户输错密码的次数 */
    private Integer payPwdErrCount = 0;
    
    /** 密码输入错误时间 */
    private Date payPwdLastErrDate;
    
    /** 推荐码:系统自动分配给客户的推荐码 */
    private String referralCode;
    
    /** 锁定 */
    private boolean locked = false;
    
    /** 是否有效 */
    private boolean valid = true;
    
    /** 失效时间 */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date invalidDate;
    
    /** 创建日期 */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;
    
    /** 最后跟新时间 */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastUpdateDate;
    
    /**
     * @return 返回 id
     */
    public String getId() {
        return id;
    }
    
    /**
     * @param 对id进行赋值
     */
    public void setId(String id) {
        this.id = id;
    }
    
    /**
     * @return 返回 vcid
     */
    public String getVcid() {
        return vcid;
    }
    
    /**
     * @param 对vcid进行赋值
     */
    public void setVcid(String vcid) {
        this.vcid = vcid;
    }
    
    /**
     * @return 返回 code
     */
    public String getCode() {
        return code;
    }
    
    /**
     * @param 对code进行赋值
     */
    public void setCode(String code) {
        this.code = code;
    }
    
    /**
     * @return 返回 creditInfoBinding
     */
    public boolean isCreditInfoBinding() {
        return creditInfoBinding;
    }
    
    /**
     * @param 对creditInfoBinding进行赋值
     */
    public void setCreditInfoBinding(boolean creditInfoBinding) {
        this.creditInfoBinding = creditInfoBinding;
    }
    
    /**
     * @return 返回 creditInfoId
     */
    public String getCreditInfoId() {
        return creditInfoId;
    }
    
    /**
     * @param 对creditInfoId进行赋值
     */
    public void setCreditInfoId(String creditInfoId) {
        this.creditInfoId = creditInfoId;
    }
    
    /**
     * @return 返回 username
     */
    public String getUsername() {
        return username;
    }
    
    /**
     * @param 对username进行赋值
     */
    public void setUsername(String username) {
        this.username = username;
    }
    
    /**
     * @return 返回 type
     */
    public ClientTypeEnum getType() {
        return type;
    }
    
    /**
     * @param 对type进行赋值
     */
    public void setType(ClientTypeEnum type) {
        this.type = type;
    }
    
    /**
     * @return 返回 usernameChangeCount
     */
    public int getUsernameChangeCount() {
        return usernameChangeCount;
    }
    
    /**
     * @param 对usernameChangeCount进行赋值
     */
    public void setUsernameChangeCount(int usernameChangeCount) {
        this.usernameChangeCount = usernameChangeCount;
    }
    
    /**
     * @return 返回 usernameChangeAble
     */
    public boolean isUsernameChangeAble() {
        return usernameChangeAble;
    }
    
    /**
     * @param 对usernameChangeAble进行赋值
     */
    public void setUsernameChangeAble(boolean usernameChangeAble) {
        this.usernameChangeAble = usernameChangeAble;
    }
    
    /**
     * @return 返回 name
     */
    public String getName() {
        return name;
    }
    
    /**
     * @param 对name进行赋值
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * @return 返回 status
     */
    public ClientStatusEnum getStatus() {
        return status;
    }
    
    /**
     * @param 对status进行赋值
     */
    public void setStatus(ClientStatusEnum status) {
        this.status = status;
    }
    
    /**
     * @return 返回 clientSource
     */
    public ClientSource getClientSource() {
        return clientSource;
    }
    
    /**
     * @param 对clientSource进行赋值
     */
    public void setClientSource(ClientSource clientSource) {
        this.clientSource = clientSource;
    }
    
    /**
     * @return 返回 promotionChannel
     */
    public ClientPromotionChannel getPromotionChannel() {
        return promotionChannel;
    }
    
    /**
     * @param 对promotionChannel进行赋值
     */
    public void setPromotionChannel(ClientPromotionChannel promotionChannel) {
        this.promotionChannel = promotionChannel;
    }
    
    /**
     * @return 返回 mobileNumber
     */
    public String getMobileNumber() {
        return mobileNumber;
    }
    
    /**
     * @param 对mobileNumber进行赋值
     */
    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
    
    /**
     * @return 返回 mobileBinding
     */
    public boolean isMobileBinding() {
        return mobileBinding;
    }
    
    /**
     * @param 对mobileBinding进行赋值
     */
    public void setMobileBinding(boolean mobileBinding) {
        this.mobileBinding = mobileBinding;
    }
    
    /**
     * @return 返回 mobileLoginEnable
     */
    public boolean isMobileLoginEnable() {
        return mobileLoginEnable;
    }
    
    /**
     * @param 对mobileLoginEnable进行赋值
     */
    public void setMobileLoginEnable(boolean mobileLoginEnable) {
        this.mobileLoginEnable = mobileLoginEnable;
    }
    
    /**
     * @return 返回 email
     */
    public String getEmail() {
        return email;
    }
    
    /**
     * @param 对email进行赋值
     */
    public void setEmail(String email) {
        this.email = email;
    }
    
    /**
     * @return 返回 emailBinding
     */
    public boolean isEmailBinding() {
        return emailBinding;
    }
    
    /**
     * @param 对emailBinding进行赋值
     */
    public void setEmailBinding(boolean emailBinding) {
        this.emailBinding = emailBinding;
    }
    
    /**
     * @return 返回 idCardType
     */
    public IDCardTypeEnum getIdCardType() {
        return idCardType;
    }
    
    /**
     * @param 对idCardType进行赋值
     */
    public void setIdCardType(IDCardTypeEnum idCardType) {
        this.idCardType = idCardType;
    }
    
    /**
     * @return 返回 idCardNumber
     */
    public String getIdCardNumber() {
        return idCardNumber;
    }
    
    /**
     * @param 对idCardNumber进行赋值
     */
    public void setIdCardNumber(String idCardNumber) {
        this.idCardNumber = idCardNumber;
    }
    
    /**
     * @return 返回 idCardExpiryDate
     */
    public Date getIdCardExpiryDate() {
        return idCardExpiryDate;
    }
    
    /**
     * @param 对idCardExpiryDate进行赋值
     */
    public void setIdCardExpiryDate(Date idCardExpiryDate) {
        this.idCardExpiryDate = idCardExpiryDate;
    }
    
    /**
     * @return 返回 realNameAuthenticated
     */
    public boolean isRealNameAuthenticated() {
        return realNameAuthenticated;
    }
    
    /**
     * @param 对realNameAuthenticated进行赋值
     */
    public void setRealNameAuthenticated(boolean realNameAuthenticated) {
        this.realNameAuthenticated = realNameAuthenticated;
    }
    
    /**
     * @return 返回 realNameErrCount
     */
    public int getRealNameErrCount() {
        return realNameErrCount;
    }
    
    /**
     * @param 对realNameErrCount进行赋值
     */
    public void setRealNameErrCount(int realNameErrCount) {
        this.realNameErrCount = realNameErrCount;
    }
    
    /**
     * @return 返回 realNameLastErrDate
     */
    public Date getRealNameLastErrDate() {
        return realNameLastErrDate;
    }
    
    /**
     * @param 对realNameLastErrDate进行赋值
     */
    public void setRealNameLastErrDate(Date realNameLastErrDate) {
        this.realNameLastErrDate = realNameLastErrDate;
    }
    
    /**
     * @return 返回 password
     */
    public String getPassword() {
        return password;
    }
    
    /**
     * @param 对password进行赋值
     */
    public void setPassword(String password) {
        this.password = password;
    }
    
    /**
     * @return 返回 hisPwd
     */
    public String getHisPwd() {
        return hisPwd;
    }
    
    /**
     * @param 对hisPwd进行赋值
     */
    public void setHisPwd(String hisPwd) {
        this.hisPwd = hisPwd;
    }
    
    /**
     * @return 返回 pwdUpdateDate
     */
    public Date getPwdUpdateDate() {
        return pwdUpdateDate;
    }
    
    /**
     * @param 对pwdUpdateDate进行赋值
     */
    public void setPwdUpdateDate(Date pwdUpdateDate) {
        this.pwdUpdateDate = pwdUpdateDate;
    }
    
    /**
     * @return 返回 pwdErrCount
     */
    public Integer getPwdErrCount() {
        return pwdErrCount;
    }
    
    /**
     * @param 对pwdErrCount进行赋值
     */
    public void setPwdErrCount(Integer pwdErrCount) {
        this.pwdErrCount = pwdErrCount;
    }
    
    /**
     * @return 返回 pwdLastErrDate
     */
    public Date getPwdLastErrDate() {
        return pwdLastErrDate;
    }
    
    /**
     * @param 对pwdLastErrDate进行赋值
     */
    public void setPwdLastErrDate(Date pwdLastErrDate) {
        this.pwdLastErrDate = pwdLastErrDate;
    }
    
    /**
     * @return 返回 payPassword
     */
    public String getPayPassword() {
        return payPassword;
    }
    
    /**
     * @param 对payPassword进行赋值
     */
    public void setPayPassword(String payPassword) {
        this.payPassword = payPassword;
    }
    
    /**
     * @return 返回 hisPayPwd
     */
    public String getHisPayPwd() {
        return hisPayPwd;
    }
    
    /**
     * @param 对hisPayPwd进行赋值
     */
    public void setHisPayPwd(String hisPayPwd) {
        this.hisPayPwd = hisPayPwd;
    }
    
    /**
     * @return 返回 payPwdUpdateDate
     */
    public Date getPayPwdUpdateDate() {
        return payPwdUpdateDate;
    }
    
    /**
     * @param 对payPwdUpdateDate进行赋值
     */
    public void setPayPwdUpdateDate(Date payPwdUpdateDate) {
        this.payPwdUpdateDate = payPwdUpdateDate;
    }
    
    /**
     * @return 返回 payPwdErrCount
     */
    public Integer getPayPwdErrCount() {
        return payPwdErrCount;
    }
    
    /**
     * @param 对payPwdErrCount进行赋值
     */
    public void setPayPwdErrCount(Integer payPwdErrCount) {
        this.payPwdErrCount = payPwdErrCount;
    }
    
    /**
     * @return 返回 payPwdLastErrDate
     */
    public Date getPayPwdLastErrDate() {
        return payPwdLastErrDate;
    }
    
    /**
     * @param 对payPwdLastErrDate进行赋值
     */
    public void setPayPwdLastErrDate(Date payPwdLastErrDate) {
        this.payPwdLastErrDate = payPwdLastErrDate;
    }
    
    /**
     * @return 返回 referralCode
     */
    public String getReferralCode() {
        return referralCode;
    }
    
    /**
     * @param 对referralCode进行赋值
     */
    public void setReferralCode(String referralCode) {
        this.referralCode = referralCode;
    }
    
    /**
     * @return 返回 locked
     */
    public boolean isLocked() {
        return locked;
    }
    
    /**
     * @param 对locked进行赋值
     */
    public void setLocked(boolean locked) {
        this.locked = locked;
    }
    
    /**
     * @return 返回 valid
     */
    public boolean isValid() {
        return valid;
    }
    
    /**
     * @param 对valid进行赋值
     */
    public void setValid(boolean valid) {
        this.valid = valid;
    }
    
    /**
     * @return 返回 invalidDate
     */
    public Date getInvalidDate() {
        return invalidDate;
    }
    
    /**
     * @param 对invalidDate进行赋值
     */
    public void setInvalidDate(Date invalidDate) {
        this.invalidDate = invalidDate;
    }
    
    /**
     * @return 返回 createDate
     */
    public Date getCreateDate() {
        return createDate;
    }
    
    /**
     * @param 对createDate进行赋值
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    
    /**
     * @return 返回 lastUpdateDate
     */
    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }
    
    /**
     * @param 对lastUpdateDate进行赋值
     */
    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }
}
