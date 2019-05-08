/*
 * 描          述:  <描述>
 * 修  改   人:  ZB
 * 修改时间:  2016年8月22日
 * <修改描述:>
 */
package com.tx.local.client.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.tx.core.jdbc.sqlsource.annotation.QueryConditionEqual;
import com.tx.core.jdbc.sqlsource.annotation.UpdateAble;
import com.tx.local.basicdata.model.District;

/**
 * 收货地址<br/>
 * @author ZB
 * @version  [版本号, 2016年8月22日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Entity
@Table(name = "cli_shipping_address")
public class ClientShippingAddress implements Serializable {
    
    /** 注释内容 */
    private static final long serialVersionUID = 5812145819513583013L;
    
    /**唯一id*/
    @Id
    private String id;
    
    /** 会员 */
    @QueryConditionEqual
    private String clientId;
    
    /** 收货人 姓名 */
    @UpdateAble
    private String name;
    
    /** 邮编 */
    @UpdateAble
    @QueryConditionEqual
    private String postcode;
    
    /** 归属区域 */
    @UpdateAble
    @Column(name = "districtId")
    @QueryConditionEqual
    private District district;
    
    /** 联系电话：可以为座机也可以为手机 */
    @UpdateAble
    @QueryConditionEqual
    private String phoneNumber;
    
    /** 地址 */
    @UpdateAble
    private String address;
    
    /** 全地址 */
    @UpdateAble
    private String fullAddress;
    
    /** 默认地址 */
    @UpdateAble
    private boolean defaultAddress;
    
    /** 最后更新时间 */
    @UpdateAble
    private Date lastUpdateDate;
    
    /** 创建时间 */
    private Date createDate;
    
    /** 备注 */
    @UpdateAble
    private String remark;
    
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
     * @return 返回 clientId
     */
    public String getClientId() {
        return clientId;
    }
    
    /**
     * @param 对clientId进行赋值
     */
    public void setClientId(String clientId) {
        this.clientId = clientId;
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
     * @return 返回 district
     */
    public District getDistrict() {
        return district;
    }
    
    /**
     * @param 对district进行赋值
     */
    public void setDistrict(District district) {
        this.district = district;
    }
    
    /**
     * @return 返回 postcode
     */
    public String getPostcode() {
        return postcode;
    }
    
    /**
     * @param 对postcode进行赋值
     */
    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }
    
    /**
     * @return 返回 phoneNumber
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * @param 对phoneNumber进行赋值
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * @return 返回 address
     */
    public String getAddress() {
        return address;
    }
    
    /**
     * @param 对address进行赋值
     */
    public void setAddress(String address) {
        this.address = address;
    }
    
    /**
     * @return 返回 fullAddress
     */
    public String getFullAddress() {
        return fullAddress;
    }
    
    /**
     * @param 对fullAddress进行赋值
     */
    public void setFullAddress(String fullAddress) {
        this.fullAddress = fullAddress;
    }
    
    /**
     * @return 返回 defaultAddress
     */
    public boolean isDefaultAddress() {
        return defaultAddress;
    }
    
    /**
     * @param 对defaultAddress进行赋值
     */
    public void setDefaultAddress(boolean defaultAddress) {
        this.defaultAddress = defaultAddress;
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
     * @return 返回 remark
     */
    public String getRemark() {
        return remark;
    }
    
    /**
     * @param 对remark进行赋值
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }
    
}
