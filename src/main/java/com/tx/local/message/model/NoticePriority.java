///*
// * 描          述:  <描述>
// * 修  改   人:  Administrator
// * 修改时间:  2020年1月19日
// * <修改描述:>
// */
//package com.tx.local.message.model;
//
//import java.util.Date;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.Id;
//import javax.persistence.ManyToOne;
//import javax.persistence.Table;
//
//import com.tx.component.basicdata.annotation.BasicDataEntity;
//import com.tx.component.basicdata.model.BasicData;
//import com.tx.component.basicdata.model.BasicDataViewTypeEnum;
//
//import io.swagger.annotations.ApiModel;
//
///**
// * 通知优先级依据(可以为new,hot;也可以为 一级，二级等)<br/>
// * <功能详细描述>
// * 
// * @author  Administrator
// * @version  [版本号, 2020年1月19日]
// * @see  [相关类/方法]
// * @since  [产品/模块版本]
// */
//@Entity
//@Table(name = "msg_notice_priority")
//@ApiModel("公告消息优先级")
//@BasicDataEntity(name = "公告消息优先级", viewType = BasicDataViewTypeEnum.LIST)
//public class NoticePriority implements BasicData {
//    
//    /** 注释内容 */
//    private static final long serialVersionUID = 2139564328359703673L;
//
//    /** 唯一键id */
//    @Id
//    private String id;
//    
//    /** 所属虚中心id */
//    //在该类中允许虚中心为null，当虚中心为空时，则为多虚中心公用
//    @Column(nullable = false)
//    private String vcid;
//    
//    /** 站内消息优先级 */
//    @ManyToOne
//    @Column(name = "noticeCatalogId")
//    private NoticeCatalog noticeCatalog;
//    
//    /** 通知消息优先级编码 */
//    @Column(nullable = false, updatable = false, unique = true, length = 64)
//    private String code;
//    
//    /** 消息优先级名称 */
//    @Column(nullable = false, updatable = false, unique = true, length = 64)
//    private String name;
//    
//    /** 是否有效 */
//    private boolean valid;
//    
//    /** 是否可编辑 */
//    private boolean modifyAble;
//    
//    /** 备注 */
//    private String remark;
//    
//    /** 创建时间 */
//    private Date createDate;
//    
//    /** 最后更新时间 */
//    private Date lastUpdateDate;
//
//    /**
//     * @return 返回 id
//     */
//    public String getId() {
//        return id;
//    }
//
//    /**
//     * @param 对id进行赋值
//     */
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    /**
//     * @return 返回 vcid
//     */
//    public String getVcid() {
//        return vcid;
//    }
//
//    /**
//     * @param 对vcid进行赋值
//     */
//    public void setVcid(String vcid) {
//        this.vcid = vcid;
//    }
//
//    /**
//     * @return 返回 noticeCatalog
//     */
//    public NoticeCatalog getNoticeCatalog() {
//        return noticeCatalog;
//    }
//
//    /**
//     * @param 对noticeCatalog进行赋值
//     */
//    public void setNoticeCatalog(NoticeCatalog noticeCatalog) {
//        this.noticeCatalog = noticeCatalog;
//    }
//
//    /**
//     * @return 返回 code
//     */
//    public String getCode() {
//        return code;
//    }
//
//    /**
//     * @param 对code进行赋值
//     */
//    public void setCode(String code) {
//        this.code = code;
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
//     * @return 返回 valid
//     */
//    public boolean isValid() {
//        return valid;
//    }
//
//    /**
//     * @param 对valid进行赋值
//     */
//    public void setValid(boolean valid) {
//        this.valid = valid;
//    }
//
//    /**
//     * @return 返回 modifyAble
//     */
//    public boolean isModifyAble() {
//        return modifyAble;
//    }
//
//    /**
//     * @param 对modifyAble进行赋值
//     */
//    public void setModifyAble(boolean modifyAble) {
//        this.modifyAble = modifyAble;
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
//
//    /**
//     * @return 返回 createDate
//     */
//    public Date getCreateDate() {
//        return createDate;
//    }
//
//    /**
//     * @param 对createDate进行赋值
//     */
//    public void setCreateDate(Date createDate) {
//        this.createDate = createDate;
//    }
//
//    /**
//     * @return 返回 lastUpdateDate
//     */
//    public Date getLastUpdateDate() {
//        return lastUpdateDate;
//    }
//
//    /**
//     * @param 对lastUpdateDate进行赋值
//     */
//    public void setLastUpdateDate(Date lastUpdateDate) {
//        this.lastUpdateDate = lastUpdateDate;
//    }
//    
//    
//}
