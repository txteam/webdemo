package com.tx.local.clientinfo.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.tx.local.basicdata.model.BankCardTypeEnum;
import com.tx.local.basicdata.model.BankInfo;
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
    private String id;
    
    /** 信用信息id */
    private String creditInfoId;
    
    /** 序列号: 席位号|客户编号|等(客户有序编号)... */
    private String serialNumber;
    
    /*** 状态：激活|禁用：禁用的帐号不允许前端系统登录 */
    private ClientStatusEnum status = ClientStatusEnum.WAIT_ACTIVATE;
    
    /** 客户类型 */
    private ClientTypeEnum type;
    
    /** 客户来源 */
    @Column(name = "clientSourceId")
    private ClientSource clientSource;
    
    /** 客户推广渠道 */
    @Column(name = "promotionChannelId")
    private ClientPromotionChannel promotionChannel;
    
    /** 登录名是否可编辑 */
    private boolean loginNameModifyAble;
    
    /** 电话 */
    private String mobilePhoneNumber;
    
    /** 电子邮件 */
    private String email;
    
    /** 登陆名 */
    private String loginname;
    
    /** 账户名 */
    private String username;
    
    /** 证件类型 */
    private IDCardTypeEnum idCardType = IDCardTypeEnum.身份证;
    
    /** 身份证号码 */
    private String idCardNumber;
    
    /** 身份证到期日 */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date idCardExpiryDate;
    
    /**银行名称*/
    @Column(name = "bankInfoId")
    private BankInfo bankInfo;
    
    /** 卡类型借记卡=DE；信用卡=CR */
    private BankCardTypeEnum bankCardType;
    
    /** 银行卡号 */
    private String bankCardNumber;
    
    /** 是否绑定电话号码 */
    private boolean mobilePhoneBinding = false;
    
    /** 是否绑定email */
    private boolean emailBinding = false;
    
    /** 是否实名认证 */
    private boolean realNameBinding = false;
    
    /** 实名认证错误次数:由于实名认证存在成本，所以增加该字段以便控制在一段时间内控制认证次数 */
    private int realNameErrCount;
    
    /** 实名认证错误时间 */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date realNameErrLastDate;
    
    /** 是否绑定银行卡 */
    private boolean bankCardBinding;
    
    /** 银行卡绑定错误时间 */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date bankCardErrLastDate;
    
    /** 银行卡实名认证错误次数 */
    private int bankCardErrCount;
    
    /** 密码 */
    private String password;
    
    /** 历史密码 */
    private String hisPwd;
    
    /** 用户输错密码的次数 */
    private Integer pwdErrCount = 0;
    
    /** 密码输入错误时间 */
    private Date pwdLastErrDate;
    
    /** 用户最近一次修改密码时间 */
    private Date pwdLastUpdateDate;
    
    /** 支付密码 */
    private String payPassword;
    
    /** 历史密码 */
    private String hisPayPwd;
    
    /** 用户输错密码的次数 */
    private Integer payPwdErrCount = 0;
    
    /** 密码输入错误时间 */
    private Date payPwdLastErrDate;
    
    /** 用户最近一次修改密码时间 */
    private Date payPwdLastUpdateDate;
    
    /** 推荐码:系统自动分配给客户的推荐码 */
    private String referralCode;
    
    /** 用户注册期间填写的推荐码|电话号码...等 */
    private String belongReferralCode;
    
    /** 所属介绍人客户id */
    private String belongReferralClientId;
    
    /** 客户图标文件id */
    private String clientIconFileId;
    
    /** 客户图标url */
    private String clientIconUrl;
    
    /** 是否新手的标志： */
    private boolean newhand = true;
    
    /** 锁定 */
    private boolean locked = false;
    
    /** 是否有效 */
    private boolean valid = true;
    
    /** 创建日期 */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;
    
    /** 最后跟新时间 */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastUpdateDate;
    
    
    
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
     * @return 返回 serialNumber
     */
    public String getSerialNumber() {
        return serialNumber;
    }
    
    /**
     * @param 对serialNumber进行赋值
     */
    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
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
     * @return 返回 loginNameModifyAble
     */
    public boolean isLoginNameModifyAble() {
        return loginNameModifyAble;
    }
    
    /**
     * @param 对loginNameModifyAble进行赋值
     */
    public void setLoginNameModifyAble(boolean loginNameModifyAble) {
        this.loginNameModifyAble = loginNameModifyAble;
    }
    
    /**
     * @return 返回 mobilePhoneNumber
     */
    public String getMobilePhoneNumber() {
        return mobilePhoneNumber;
    }
    
    /**
     * @param 对mobilePhoneNumber进行赋值
     */
    public void setMobilePhoneNumber(String mobilePhoneNumber) {
        this.mobilePhoneNumber = mobilePhoneNumber;
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
     * @return 返回 userName
     */
    public String getUsername() {
        return username;
    }
    
    /**
     * @param 对userName进行赋值
     */
    public void setUserName(String username) {
        this.username = username;
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
     * @return 返回 bankInfo
     */
    public BankInfo getBankInfo() {
        return bankInfo;
    }
    
    /**
     * @param 对bankInfo进行赋值
     */
    public void setBankInfo(BankInfo bankInfo) {
        this.bankInfo = bankInfo;
    }
    
    /**
     * @return 返回 bankCardType
     */
    public BankCardTypeEnum getBankCardType() {
        return bankCardType;
    }
    
    /**
     * @param 对bankCardType进行赋值
     */
    public void setBankCardType(BankCardTypeEnum bankCardType) {
        this.bankCardType = bankCardType;
    }
    
    /**
     * @return 返回 bankCardNumber
     */
    public String getBankCardNumber() {
        return bankCardNumber;
    }
    
    /**
     * @param 对bankCardNumber进行赋值
     */
    public void setBankCardNumber(String bankCardNumber) {
        this.bankCardNumber = bankCardNumber;
    }
    
    /**
     * @return 返回 mobilePhoneBinding
     */
    public boolean isMobilePhoneBinding() {
        return mobilePhoneBinding;
    }
    
    /**
     * @param 对mobilePhoneBinding进行赋值
     */
    public void setMobilePhoneBinding(boolean mobilePhoneBinding) {
        this.mobilePhoneBinding = mobilePhoneBinding;
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
     * @return 返回 realNameBinding
     */
    public boolean isRealNameBinding() {
        return realNameBinding;
    }
    
    /**
     * @param 对realNameBinding进行赋值
     */
    public void setRealNameBinding(boolean realNameBinding) {
        this.realNameBinding = realNameBinding;
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
     * @return 返回 realNameErrLastDate
     */
    public Date getRealNameErrLastDate() {
        return realNameErrLastDate;
    }
    
    /**
     * @param 对realNameErrLastDate进行赋值
     */
    public void setRealNameErrLastDate(Date realNameErrLastDate) {
        this.realNameErrLastDate = realNameErrLastDate;
    }
    
    /**
     * @return 返回 bankCardBinding
     */
    public boolean isBankCardBinding() {
        return bankCardBinding;
    }
    
    /**
     * @param 对bankCardBinding进行赋值
     */
    public void setBankCardBinding(boolean bankCardBinding) {
        this.bankCardBinding = bankCardBinding;
    }
    
    /**
     * @return 返回 bankCardErrLastDate
     */
    public Date getBankCardErrLastDate() {
        return bankCardErrLastDate;
    }
    
    /**
     * @param 对bankCardErrLastDate进行赋值
     */
    public void setBankCardErrLastDate(Date bankCardErrLastDate) {
        this.bankCardErrLastDate = bankCardErrLastDate;
    }
    
    /**
     * @return 返回 bankCardErrCount
     */
    public int getBankCardErrCount() {
        return bankCardErrCount;
    }
    
    /**
     * @param 对bankCardErrCount进行赋值
     */
    public void setBankCardErrCount(int bankCardErrCount) {
        this.bankCardErrCount = bankCardErrCount;
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
     * @return 返回 pwdLastUpdateDate
     */
    public Date getPwdLastUpdateDate() {
        return pwdLastUpdateDate;
    }
    
    /**
     * @param 对pwdLastUpdateDate进行赋值
     */
    public void setPwdLastUpdateDate(Date pwdLastUpdateDate) {
        this.pwdLastUpdateDate = pwdLastUpdateDate;
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
     * @return 返回 payPwdLastUpdateDate
     */
    public Date getPayPwdLastUpdateDate() {
        return payPwdLastUpdateDate;
    }
    
    /**
     * @param 对payPwdLastUpdateDate进行赋值
     */
    public void setPayPwdLastUpdateDate(Date payPwdLastUpdateDate) {
        this.payPwdLastUpdateDate = payPwdLastUpdateDate;
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
     * @return 返回 belongReferral
     */
    public String getBelongReferralCode() {
        return belongReferralCode;
    }
    
    /**
     * @param 对belongReferral进行赋值
     */
    public void setBelongReferralCode(String belongReferral) {
        this.belongReferralCode = belongReferral;
    }
    
    /**
     * @return 返回 belongReferralClientId
     */
    public String getBelongReferralClientId() {
        return belongReferralClientId;
    }
    
    /**
     * @param 对belongReferralClientId进行赋值
     */
    public void setBelongReferralClientId(String belongReferralClientId) {
        this.belongReferralClientId = belongReferralClientId;
    }
    
    /**
     * @return 返回 clientIconFileId
     */
    public String getClientIconFileId() {
        return clientIconFileId;
    }
    
    /**
     * @param 对clientIconFileId进行赋值
     */
    public void setClientIconFileId(String clientIconFileId) {
        this.clientIconFileId = clientIconFileId;
    }
    
    /**
     * @return 返回 clientIconUrl
     */
    public String getClientIconUrl() {
        return clientIconUrl;
    }
    
    /**
     * @param 对clientIconUrl进行赋值
     */
    public void setClientIconUrl(String clientIconUrl) {
        this.clientIconUrl = clientIconUrl;
    }
    
    /**
     * @return 返回 newhand
     */
    public boolean isNewhand() {
        return newhand;
    }
    
    /**
     * @param 对newhand进行赋值
     */
    public void setNewhand(boolean newhand) {
        this.newhand = newhand;
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
     * @return
     */
    //@Override
    public String getName() {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * @return 返回 loginname
     */
    public String getLoginname() {
        return loginname;
    }

    /**
     * @param 对loginname进行赋值
     */
    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }

    /**
     * @param 对username进行赋值
     */
    public void setUsername(String username) {
        this.username = username;
    }
}
