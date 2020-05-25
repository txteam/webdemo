/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2020年1月4日
 * <修改描述:>
 */
package com.tx.local.clientinfo.model;

import javax.persistence.Column;

import com.tx.component.servicelogger.annotation.ServiceLog;
import com.tx.component.servicelogger.model.AbstractServiceLogger;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 操作人员登陆日志<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2020年1月4日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ServiceLog("CL_LOGIN_LOG")
public class ClientLoginLog extends AbstractServiceLogger {
    
    //:操作人员id
    @ApiModelProperty(value = "客户ID", hidden = true, position = 0)
    @Column(nullable = false, length = 64)
    private String clientId;
    
    //:操作人员id
    @ApiModelProperty(value = "用户名", position = 1)
    @Column(nullable = false, length = 64)
    private String clientUsername;
    
    //:登陆方式
    @Column(nullable = false, length = 64)
    private String loginMode;
    
    //:客户端ip地址
    @Column(nullable = false, length = 64)
    private String clientIpAddress;
    
    //:登陆信息
    @Column(nullable = true, length = 256)
    private String message;
    
    //： 设备签名
    @Column(nullable = true, length = 64)
    private String token;
    
    //: 设备的唯一标识
    @Column(nullable = true, length = 64)
    private String deviceId;
    
    //:客户端真实ip地址
    @Column(nullable = true, length = 64)
    private String realIpAddress;
    
    //中转ip地址(写入以前应该对长度进行特殊处理)
    @Column(nullable = true, length = 256)
    private String forwardedIpAddress;
    
    //远端调用ip地址
    @Column(nullable = true, length = 64)
    private String remoteIpAddress;
    
    //设备的国际移动设备身份码
    @Column(nullable = true, length = 64)
    private String imei;
    
    //操作系统类型
    //@Enumerated(EnumType.STRING)
    @Column(nullable = true, length = 64)
    private String osAgentType;
    
    //操作系统版本
    @Column(nullable = true, length = 64)
    private String osAgentVersion;
}
