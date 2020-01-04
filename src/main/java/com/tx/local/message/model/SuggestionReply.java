/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2020年1月4日
 * <修改描述:>
 */
package com.tx.local.message.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;

/**
 * 意见、反馈、问题<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2020年1月4日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Entity
@Table(name = "msg_suggestion")
@ApiModel("意见反馈")
public class SuggestionReply implements Serializable{
    
    /** 注释内容 */
    private static final long serialVersionUID = 760874841288229239L;
    
    /** 用户唯一键  */
    @Id
    private String id;
    
    /** 所属虚中心id */
    private String vcid;
    
    /** 用户类型 */
    private String userType;
    
    /** 用户id */
    private String userId;
    
    
}
