/*
 * 描          述:  <描述>
 * 修  改   人:  PengQingyang
 * 修改时间:  2020年6月12日
 * <修改描述:>
 */
package com.tx.local.qxb.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.tx.local.creditinfo.model.CreditMultipLinked;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * <功能简述>
 * <功能详细描述>
 * 
 * @author  PengQingyang
 * @version  [版本号, 2020年6月12日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "qxb_ent_abnormal_item")
public class QxbEntAbnormalItem implements CreditMultipLinked {
    
    /** 注释内容 */
    private static final long serialVersionUID = -4439013811352455092L;
    
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
    
    /** 经营异常列入原因 */
    @Column(length = 500, updatable = true, nullable = true)
    @JsonAlias("in_reason")
    private String inReason;
    
    /** 列入日期 */
    @Column(nullable = true, updatable = true)
    @JsonAlias("in_date")
    private Date inDate;
    
    /** 移出原因 */
    @Column(length = 500, updatable = true, nullable = true)
    @JsonAlias("out_reason")
    private String outReason;
    
    /** 列入日期 */
    @Column(nullable = true, updatable = true)
    @JsonAlias("out_date")
    private Date outDate;
    
    /** 处罚部门 */
    @Column(length = 256, updatable = true, nullable = true)
    @JsonAlias("department")
    private String department;
}
